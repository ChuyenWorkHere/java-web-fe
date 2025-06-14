package servlet.admin.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.dao.OrderDAO;
import servlet.dao.impl.OrderDAOImpl;
import java.io.IOException;

@WebServlet("/admin/order-restore")
public class OrderRestore extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public OrderRestore() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderDAO orderDAO = new OrderDAOImpl();
        boolean success = orderDAO.restoreOrder(orderId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"success\":" + success + "}");
    }
}
