package servlet.user.controller;

import servlet.configs.VnPayConfig;
import servlet.constants.OrderStatus;
import servlet.constants.PaymentStatus;
import servlet.dao.OrderDAO;
import servlet.dao.OrderItemsDAO;
import servlet.dao.ProductDAO;
import servlet.dao.impl.OrderDAOImpl;
import servlet.dao.impl.OrderItemsDAOImpl;
import servlet.dao.impl.ProductDAOImpl;
import servlet.models.Order;
import servlet.models.OrderItem;
import servlet.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/customer/checkout-callback")
public class UserCheckoutCallback extends HttpServlet {

    private ProductDAO productDAO;
    private OrderDAO orderDAO;
    private OrderItemsDAO orderItemsDAO;

    public UserCheckoutCallback() {
        productDAO = new ProductDAOImpl();
        orderDAO = new OrderDAOImpl();
        orderItemsDAO = new OrderItemsDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Lấy toàn bộ tham số từ VNPAY trả về
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = (String) params.nextElement();
            String fieldValue = request.getParameter(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        // 2. Xác thực chữ ký (Secure Hash)
        String vnp_SecureHash = (String) fields.remove("vnp_SecureHash");
        int orderId = -1;
        try {
            orderId = Integer.parseInt((String) fields.get("vnp_TxnRef"));
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/customer/order-failed");
            return;
        }

        String signValue = VnPayConfig.hmacSHA512(VnPayConfig.secretKey, VnPayConfig.buildHashData(fields));

        if (signValue.equals(vnp_SecureHash)) {

            Order order = orderDAO.findOrderByOrderId(orderId);
            if (order == null) {
                response.sendRedirect(request.getContextPath() + "/customer/order-failed");
                return;
            }

            // Tránh trường hợp User F5 lại trang callback
            if (!String.valueOf(PaymentStatus.UNPAID).equals(order.getPaymentStatus())) {
                if (String.valueOf(PaymentStatus.PAID).equals(order.getPaymentStatus())) {
                    response.sendRedirect(request.getContextPath() + "/customer/order-success");
                } else {
                    response.sendRedirect(request.getContextPath() + "/customer/order-failed");
                }
                return;
            }

            if ("00".equals(request.getParameter("vnp_ResponseCode"))) {

                orderDAO.updatePaymentStatus(order.getOrderId(), PaymentStatus.PAID);
                orderDAO.updateOrderStatus(order.getOrderId(), OrderStatus.SHIPPING);

                response.sendRedirect(request.getContextPath() + "/customer/order-success");

            } else {

                orderDAO.updateOrderStatus(order.getOrderId(), OrderStatus.CANCELLED);
                orderDAO.updatePaymentStatus(order.getOrderId(), PaymentStatus.UNPAID);

                List<OrderItem> items = orderItemsDAO.getAllOrderItemsByOrderId(orderId);
                for (OrderItem orderItem : items) {
                    Product product = productDAO.findById(orderItem.getProduct().getProductId());

                    product.setProductTotal(product.getProductTotal() + orderItem.getOrderQuantity());

                    productDAO.editProduct(product.getProductId(), product);
                }

                response.sendRedirect(request.getContextPath() + "/customer/order-failed");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/customer/order-failed");
        }
    }
}