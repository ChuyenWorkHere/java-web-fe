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
import java.util.Arrays;

@WebServlet("/admin/order-delete-all-trash")
public class DeleteAllOrderTrash extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] orderIds = request.getParameterValues("orderIds");
        boolean success = true;

        System.out.println("Xóa các đơn hàng có ID: " + Arrays.toString(orderIds));

        if (orderIds != null) {
            OrderDAO orderDAO = new OrderDAOImpl();
            for (String orderId : orderIds) {
                // Gọi phương thức xóa đơn hàng
                boolean result = orderDAO.deleteOrder(Integer.parseInt(orderId));
                success &= result; // Nếu một trong các lần xóa không thành công, success sẽ trở thành false
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print("{\"success\":" + success + "}");
        out.flush();
    }
}
