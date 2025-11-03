package servlet.user.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/public/signup")
public class UserRegisterView extends HttpServlet {

    public UserRegisterView() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String errorMessage = "";

        if(request.getParameter("errorMessage") != null){
            errorMessage = request.getParameter("errorMessage");
        }

        RequestDispatcher headerDispatcher = request.getRequestDispatcher("/public/header-view");
        headerDispatcher.include(request, response);
        PrintWriter out = response.getWriter();

        out.append("<main>");
        out.append("");
        out.append("    <!-- breadcrumb-area-start -->");
        out.append("    <section class=\"breadcrumb-area\" data-background=\"img/bg/page-title.png\">");
        out.append("        <div class=\"container\">");
        out.append("            <div class=\"row\">");
        out.append("                <div class=\"col-xl-12\">");
        out.append("                    <div class=\"breadcrumb-text text-center\">");
        out.append("                        <h1>Register</h1>");
        out.append("                        <ul class=\"breadcrumb-menu\">");
        out.append("                            <li><a href=\"/Furniture/public/home\">home</a></li>");
        out.append("                            <li><span>Register</span></li>");
        out.append("                        </ul>");
        out.append("                    </div>");
        out.append("                </div>");
        out.append("            </div>");
        out.append("        </div>");
        out.append("    </section>");
        out.append("    <!-- breadcrumb-area-end -->");
        out.append("");
        out.append("    <!-- login Area Strat-->");
        out.append("    <section class=\"login-area pt-100 pb-100\">");
        out.append("        <div class=\"container\">");
        out.append("            <div class=\"row\">");
        out.append("                <div class=\"col-lg-8 offset-lg-2\">");
        out.append("                    <div class=\"basic-login\">");
        out.append("                        <h3 class=\"text-center mb-60\">ĐĂNG KÝ</h3>");
        out.append("                        <form id=\"registerForm\" action=\"/Furniture/public/customer-signup\" method=\"post\">");
        out.append("                            <label for=\"fullname\">Họ và tên <span>**</span></label>");
        out.append("                            <input id=\"fullname\" name=\"fullname\" type=\"text\" placeholder=\"Nhập họ và tên...\" required />");
        out.append("                            <label for=\"email\">Email <span>**</span></label>");
        out.append("                            <input id=\"email\" name=\"email\" type=\"email\" placeholder=\"Nhập email...\" required />");
        out.append("                            <label for=\"password\">Mật khẩu <span>**</span></label>");
        out.append("                            <input id=\"password\" name=\"password\" type=\"password\" placeholder=\"Nhập mật khẩu...\" required />");
        out.append("                            <label for=\"confirm_password\">Xác nhận mật khẩu <span>**</span></label>");
        out.append("                            <input id=\"confirm_password\" name=\"confirm_password\" type=\"password\" placeholder=\"Xác nhận mật khẩu...\" required />");
        out.append("                            <div class=\"mb-2 text-center text-danger\">"+errorMessage+"</div>");
        out.append("                            <button type=\"submit\" class=\"btn theme-btn-2 w-100\">Đăng Ký</button>");
        out.append("                            <div class=\"mt-20 text-center\">");
        out.append("                                Đã có tài khoản? <a href=\"/Furniture/public/login\">Đăng nhập ngay</a>");
        out.append("                            </div>");
        out.append("                        </form>");
        out.append("                    </div>");
        out.append("                </div>");
        out.append("            </div>");
        out.append("        </div>");
        out.append("    </section>");
        out.append("    <!-- login Area End-->");
        out.append("</main>");

        RequestDispatcher footerDispatcher = request.getRequestDispatcher("/public/footer-view");
        footerDispatcher.include(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
