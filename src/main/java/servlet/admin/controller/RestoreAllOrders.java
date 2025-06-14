package servlet.admin.controller;

import servlet.dao.OrderDAO;
import servlet.dao.impl.OrderDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin/order-restore-all")
public class RestoreAllOrders extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] orderIds = request.getParameterValues("orderIds");
        boolean success = true;

        if (orderIds != null) {
            OrderDAO orderDAO = new OrderDAOImpl();
            for (String orderId : orderIds) {
                boolean result = orderDAO.restoreOrder(Integer.parseInt(orderId)); // Gọi phương thức phục hồi
                success &= result; // Nếu một trong các lần phục hồi không thành công, success sẽ trở thành false
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print("{\"success\":" + success + "}");
        out.flush();
    }
}
