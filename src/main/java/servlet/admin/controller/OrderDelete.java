package servlet.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.dao.OrderDAO;
import servlet.dao.UserDAO;
import servlet.dao.impl.OrderDAOImpl;
import servlet.dao.impl.UserDAOImpl;

@WebServlet("/admin/order-delete")
public class OrderDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public OrderDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId")) ;
        System.out.println("orderId: " + orderId);

        OrderDAO orderDAO = new OrderDAOImpl();
        boolean success =  orderDAO.deleteOrder(orderId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"success\":" + success + "}");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
