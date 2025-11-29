package servlet.user.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/customer/order-success")
public class OrderSuccessView extends HttpServlet {

    public OrderSuccessView() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        RequestDispatcher headerDispatcher = request.getRequestDispatcher("/public/header-view");
        headerDispatcher.include(request, response);

        out.append("	<div class=\"container mt-5\">");
        out.append("        <div class=\"row justify-content-center\">");
        out.append("            <div class=\"col-md-8\">");
        out.append("                <div class=\"alert alert-success text-center\" role=\"alert\">");
        out.append("                    <h4 class=\"alert-heading\">Đặt hàng thành công!</h4>");
        out.append("                    <p>Cảm ơn bạn đã hoàn tất đặt hàng. Đơn hàng của bạn đang chờ xác nhận.</p>");
        out.append("                    <hr>");
        out.append("                    <p class=\"mb-0\">Bạn có thể kiểm tra chi tiết đơn hàng trong lịch sử.</p>");
        out.append("                </div>");
        out.append("                <div class=\"text-center\">");
        out.append("                    <a href=\""+(request.getContextPath() + "/customer/orders" )+"\" class=\"btn bg-primary text-white\">Xem lịch sử đơn hàng</a>");
        out.append("                </div>");
        out.append("            </div>");
        out.append("        </div>");
        out.append("    </div>");


        RequestDispatcher footerDispatcher = request.getRequestDispatcher("/public/footer-view");
        footerDispatcher.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
