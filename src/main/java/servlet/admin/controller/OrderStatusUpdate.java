package servlet.admin.controller;

import servlet.constants.OrderStatus;
import servlet.constants.PaymentStatus;
import servlet.dao.OrderDAO;
import servlet.dao.OrderItemsDAO;
import servlet.dao.ProductDAO;
import servlet.dao.impl.OrderDAOImpl;
import servlet.dao.impl.OrderItemsDAOImpl;
import servlet.dao.impl.ProductDAOImpl;
import servlet.models.OrderItem;
import servlet.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/order-status-update")
public class OrderStatusUpdate extends HttpServlet {


    private static final long serialVersionUID = 1L;
    private OrderDAO orderDAO;
    private ProductDAO productDAO;
    private OrderItemsDAO orderItemsDAO;

    public OrderStatusUpdate() {
        super();
        orderDAO = new OrderDAOImpl();
        productDAO = new ProductDAOImpl();
        orderItemsDAO = new OrderItemsDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String orderStatus = request.getParameter("orderStatus");

        boolean check = false;

        if(String.valueOf(OrderStatus.SHIPPING).equalsIgnoreCase(orderStatus)) {
            check = orderDAO.updateOrderStatus(orderId, OrderStatus.SHIPPING);

        } else if (String.valueOf(OrderStatus.DELIVERED).equalsIgnoreCase(orderStatus)) {
            check = orderDAO.updateOrderStatus(orderId, OrderStatus.DELIVERED)
                    && orderDAO.updatePaymentStatus(orderId, PaymentStatus.PAID);
        } else if (String.valueOf(OrderStatus.CANCELLED).equalsIgnoreCase(orderStatus)) {

            List<OrderItem> orderItems = orderItemsDAO.getAllOrderItemsByOrderId(orderId);
            for (OrderItem orderItem : orderItems) {
                Product product = productDAO.findById(orderItem.getProduct().getProductId());

                product.setProductTotal(product.getProductTotal() + orderItem.getOrderQuantity());

                productDAO.editProduct(product.getProductId(), product);
            }
            check = orderDAO.updateOrderStatus(orderId, OrderStatus.CANCELLED);
        }

        if(check){
            response.sendRedirect(request.getContextPath() + "/admin/orders-view");
        }else{
            response.sendRedirect(request.getContextPath() +"/admin/single-order-view");
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
