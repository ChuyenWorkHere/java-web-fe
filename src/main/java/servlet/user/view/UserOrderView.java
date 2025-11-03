package servlet.user.view;

import servlet.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/customer/orders")
public class UserOrderView extends HttpServlet {

    public UserOrderView() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        User loggedInUser = (User) session.getAttribute("customer");
        if(loggedInUser == null) {
            response.sendRedirect("../public/login");
            return;
        }

        PrintWriter out = response.getWriter();
        RequestDispatcher headerDispatcher = request.getRequestDispatcher("/public/header-view");
        headerDispatcher.include(request, response);

        out.append("<main>");
        out.append("        <!-- breadcrumb-area-start -->");
        out.append("        <section class=\"breadcrumb-area\" data-background=\"../user/img/bg/page-title.png\">");
        out.append("            <div class=\"container\">");
        out.append("                <div class=\"row\">");
        out.append("                    <div class=\"col-xl-12\">");
        out.append("                        <div class=\"breadcrumb-text text-center\">");
        out.append("                            <h1>Order History</h1>");
        out.append("                        </div>");
        out.append("                    </div>");
        out.append("                </div>");
        out.append("            </div>");
        out.append("        </section>");
        out.append("        <!-- breadcrumb-area-end -->");
        out.append("");
        out.append("        <!-- Order History Area Start -->");
        out.append("        <section class=\"cart-area pt-100 pb-100\">");
        out.append("            <div class=\"container\">");
        out.append("                <div class=\"row\">");
        out.append("                    <div class=\"col-12\">");
        out.append("");
        out.append("                        <!-- Order 1 -->");
        out.append("                        <div class=\"order-box mb-4 p-4 border rounded\">");
        out.append("                            <div class=\"d-flex justify-content-between align-items-center mb-3\">");
        out.append("                                <h5 class=\"fw-bold mb-0\">Đã giao ngày 12/2/2025</h5>");
        out.append("                                <span class=\"text-success fw-semibold\">");
        out.append("                                    <i class=\"fas fa-truck\"></i> Giao hàng thành công");
        out.append("                                </span>");
        out.append("                            </div>");
        out.append("                            <hr>");
        out.append("");
        out.append("                            <div class=\"d-flex justify-content-between align-items-center\">");
        out.append("                                <div class=\"d-flex gap-4\">");
        out.append("                                    <div style=\"flex-shrink: 0;\">");
        out.append("                                        <img src=\"../user/img/product/pro1.jpg\" alt=\"product\" width=\"120\" class=\"rounded\">");
        out.append("                                    </div>");
        out.append("                                    <div class=\"flex-grow-1 d-flex flex-column ml-3\">");
        out.append("                                        <h5 class=\"mb-1\">Bakix Furniture</h5>");
        out.append("                                        <small class=\"text-muted\">Phân loại hàng: Đồ gỗ</small>");
        out.append("                                        <small class=\"text-muted\">Số lượng: 1</small>");
        out.append("                                    </div>");
        out.append("                                </div>");
        out.append("                                <div class=\"text-end\">");
        out.append("                                    <p class=\"mb-0 text-danger fw-bold\">26.488đ</p>");
        out.append("                                </div>");
        out.append("                            </div>");
        out.append("                            <hr>");
        out.append("                            <div>");
        out.append("                                <p class=\"text-black mb-0 text-right\">");
        out.append("                                    Thành tiền: <span class=\"text-danger fw-bold ml-1\"");
        out.append("                                        style=\"font-size: 1.2rem;\">200.000");
        out.append("                                        đ</span>");
        out.append("                                </p>");
        out.append("                            </div>");
        out.append("                            <div class=\"d-flex justify-content-end align-items-center flex-wrap gap-2\">");
        out.append("                                <button class=\"btn btn-sm px-4 mt-3\" style=\"background-color: #7f8c8d; color: white;\">");
        out.append("                                    Yêu cầu hoàn tiền");
        out.append("                                </button>");
        out.append("                                <button class=\"btn btn-sm px-4 mt-3 ml-3\"");
        out.append("                                    style=\"background-color: #FE4536; color: white;\">");
        out.append("                                    Đánh giá");
        out.append("                                </button>");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("");
        out.append("                        <!-- Order 2 -->");
        out.append("                        <div class=\"order-box mb-4 p-4 border rounded\">");
        out.append("                            <div class=\"d-flex justify-content-between align-items-center mb-3\">");
        out.append("                                <h5 class=\"fw-bold mb-0\">Đã giao ngày 12/2/2025</h5>");
        out.append("                                <span class=\"text-success fw-semibold\">");
        out.append("                                    <i class=\"fas fa-truck\"></i> Giao hàng thành công");
        out.append("                                </span>");
        out.append("                            </div>");
        out.append("                            <hr>");
        out.append("");
        out.append("                            <div class=\"d-flex justify-content-between align-items-center\">");
        out.append("                                <div class=\"d-flex gap-4\">");
        out.append("                                    <div style=\"flex-shrink: 0;\">");
        out.append("                                        <img src=\"../user/img/product/pro1.jpg\" alt=\"product\" width=\"120\" class=\"rounded\">");
        out.append("                                    </div>");
        out.append("                                    <div class=\"flex-grow-1 d-flex flex-column ml-3\">");
        out.append("                                        <h5 class=\"mb-1\">Bakix Furniture</h5>");
        out.append("                                        <small class=\"text-muted\">Phân loại hàng: Đồ gỗ</small>");
        out.append("                                        <small class=\"text-muted\">Số lượng: 1</small>");
        out.append("                                    </div>");
        out.append("                                </div>");
        out.append("                                <div class=\"text-end\">");
        out.append("                                    <p class=\"mb-0 text-danger fw-bold\">26.488đ</p>");
        out.append("                                </div>");
        out.append("                            </div>");
        out.append("                            <hr>");
        out.append("                            <div>");
        out.append("                                <p class=\"text-black mb-0 text-right\">");
        out.append("                                    Thành tiền: <span class=\"text-danger fw-bold ml-1\"");
        out.append("                                        style=\"font-size: 1.2rem;\">200.000");
        out.append("                                        đ</span>");
        out.append("                                </p>");
        out.append("                            </div>");
        out.append("                            <div class=\"d-flex justify-content-end align-items-center flex-wrap gap-2\">");
        out.append("                                <button class=\"btn btn-sm px-4 mt-3\" style=\"background-color: #7f8c8d; color: white;\">");
        out.append("                                    Yêu cầu hoàn tiền");
        out.append("                                </button>");
        out.append("                                <button class=\"btn btn-sm px-4 mt-3 ml-3\"");
        out.append("                                    style=\"background-color: #FE4536; color: white;\">");
        out.append("                                    Đánh giá");
        out.append("                                </button>");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("                    </div>");
        out.append("                </div>");
        out.append("            </div>");
        out.append("        </section>");
        out.append("        <!-- Order History Area End -->");
        out.append("</main>");

        RequestDispatcher footerDispatcher = request.getRequestDispatcher("/public/footer-view");
        footerDispatcher.include(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
