package servlet.user.controller;

import servlet.configs.VnPayConfig;
import servlet.constants.OrderStatus;
import servlet.constants.PaymentStatus;
import servlet.dao.CartDAO;
import servlet.dao.OrderDAO;
import servlet.dao.OrderItemsDAO;
import servlet.dao.ProductDAO;
import servlet.dao.impl.CartDAOImpl;
import servlet.dao.impl.OrderDAOImpl;
import servlet.dao.impl.OrderItemsDAOImpl;
import servlet.dao.impl.ProductDAOImpl;
import servlet.models.Order;

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
    private CartDAO cartDAO;

    public UserCheckoutCallback() {
        productDAO = new ProductDAOImpl();
        orderDAO = new OrderDAOImpl();
        orderItemsDAO = new OrderItemsDAOImpl();
        cartDAO = new CartDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = (String) params.nextElement();
            String fieldValue = request.getParameter(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = (String) fields.remove("vnp_SecureHash");
        int orderId = Integer.parseInt((String) fields.get("vnp_TxnRef"));
        String signValue = VnPayConfig.hmacSHA512(VnPayConfig.secretKey, VnPayConfig.buildHashData(fields));

        response.setContentType("text/html;charset=UTF-8");
        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(request.getParameter("vnp_ResponseCode"))) {
                Order order = orderDAO.findOrderByOrderId(orderId);

                order.setPaymentStatus(String.valueOf(PaymentStatus.PAID));

                boolean isUpdated = orderDAO.updatePaymentStatus(orderId, PaymentStatus.PAID);

                if (isUpdated) {
                    response.sendRedirect(request.getContextPath() + "/customer/order-success");
                } else {
                    response.sendRedirect(request.getContextPath() + "/customer/order-failed");
                }

            } else {
                response.sendRedirect(request.getContextPath() + "/customer/order-failed");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/customer/order-failed");
        }
    }
}