package servlet.admin.controller;

import servlet.dao.OrderDAO;
import servlet.dao.impl.OrderDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/soft-order-delete")
public class SoftOrderDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SoftOrderDelete() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        System.out.println("Xóa mềm đơn hàng với orderId: " + orderId);

        OrderDAO orderDAO = new OrderDAOImpl();
        boolean success = orderDAO.softDeleteOrder(orderId); // Sử dụng hàm xóa mềm

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"success\":" + success + "}");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
