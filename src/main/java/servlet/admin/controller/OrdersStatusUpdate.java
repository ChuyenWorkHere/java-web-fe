package servlet.admin.controller;

import servlet.dao.OrderDAO;
import servlet.dao.impl.OrderDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/order-status-update")
public class OrdersStatusUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public OrdersStatusUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId")) ;

        System.out.println("orderId = " + orderId);
        OrderDAO orderDAO = new OrderDAOImpl();
        boolean check = orderDAO.updateOrderStatus(orderId);

        if(check){
            response.sendRedirect("../admin/orders-view");
        }else{
            response.sendRedirect("../admin/single-order-view");
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
