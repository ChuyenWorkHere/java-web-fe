package servlet.user.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/public/contact")
public class UserContactView extends HttpServlet {


    public UserContactView() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();

        RequestDispatcher headerDispatcher = req.getRequestDispatcher("/public/header-view");
        headerDispatcher.include(req, resp);

        out.append("	<main>");
        out.append("        <!-- breadcrumb-area-start -->");
        out.append("        <section class=\"breadcrumb-area\" data-background=\""+ req.getContextPath() +"/user/img/bg/page-title.png\">");
        out.append("            <div class=\"container\">");
        out.append("                <div class=\"row\">");
        out.append("                    <div class=\"col-xl-12\">");
        out.append("                        <div class=\"breadcrumb-text text-center\">");
        out.append("                            <h1>Liên Hệ</h1>");
        out.append("                            <ul class=\"breadcrumb-menu\">");
        out.append("                                <li><a href=\""+ req.getContextPath() +"/public/home\">Trang Chủ</a></li>");
        out.append("                                <li><span>Liên Hệ</span></li>");
        out.append("                            </ul>");
        out.append("                        </div>");
        out.append("                    </div>");
        out.append("                </div>");
        out.append("            </div>");
        out.append("        </section>");
        out.append("        <!-- breadcrumb-area-end -->");
        out.append("");
        out.append("        <!-- contact-area start -->");
        out.append("        <section class=\"contact-area pt-120 pb-90\" data-background=\"assets/img/bg/bg-map.html\">");
        out.append("            <div class=\"container\">");
        out.append("                <div class=\"row\">");
        out.append("                    <div class=\"col-xl-4 col-lg-4 col-md-4\">");
        out.append("                        <div class=\"contact text-center mb-30\">");
        out.append("                            <i class=\"fas fa-envelope\"></i>");
        out.append("                            <h3>Gửi Email</h3>");
        out.append("                            <p>noithatsongle@gmail.com</p>");
        out.append("                            <p>info@songle.vn</p>");
        out.append("                        </div>");
        out.append("                    </div>");
        out.append("                    <div class=\"col-xl-4 col-lg-4 col-md-4\">");
        out.append("                        <div class=\"contact text-center mb-30\">");
        out.append("                            <i class=\"fas fa-map-marker-alt\"></i>");
        out.append("                            <h3>Địa Chỉ</h3>");
        out.append("                            <p>Số 19, Phố Tôn Thất Thuyết,");
        out.append("                                Cầu Giấy, Hà Nội</p>");
        out.append("                        </div>");
        out.append("                    </div>");
        out.append("                    <div class=\"col-xl-4  col-lg-4 col-md-4 \">");
        out.append("                        <div class=\"contact text-center mb-30\">");
        out.append("                            <i class=\"fas fa-phone\"></i>");
        out.append("                            <h3>Gọi Ngay</h3>");
        out.append("                            <p>098.123.4567</p>");
        out.append("                            <p>1900.1234</p>");
        out.append("                        </div>");
        out.append("                    </div>");
        out.append("                </div>");
        out.append("            </div>");
        out.append("        </section>");
        out.append("        <!-- contact-area end -->");
        out.append("");
        out.append("        <!-- contact-form-area start -->");
        out.append("        <section class=\"contact-form-area grey-bg pt-100 pb-100\">");
        out.append("            <div class=\"container\">");
        out.append("                <div class=\"form-wrapper\">");
        out.append("                    <div class=\"row align-items-center\">");
        out.append("                        <div class=\"col-xl-8 col-lg-8\">");
        out.append("                            <div class=\"section-title mb-55\">");
        out.append("                                <p><span></span> Bạn có thắc mắc?</p>");
        out.append("                                <h1>Gửi Yêu Cầu Cho Chúng Tôi</h1>");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("                        <div class=\"col-xl-4 col-lg-3 d-none d-xl-block \">");
        out.append("                            <div class=\"section-link mb-80 text-right\">");
        out.append("                                <a class=\"btn theme-btn\" href=\"tel:19001234\"><i class=\"fas fa-phone\"></i> Gọi tư vấn</a>");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("                    </div>");
        out.append("                    <div class=\"contact-form\">");
        out.append("                        <form id=\"contact-form\" action=\"#\">");
        out.append("                            <div class=\"row\">");
        out.append("                                <div class=\"col-lg-6\">");
        out.append("                                    <div class=\"form-box user-icon mb-30\">");
        out.append("                                        <input type=\"text\" name=\"name\" placeholder=\"Họ và tên của bạn\">");
        out.append("                                    </div>");
        out.append("                                </div>");
        out.append("                                <div class=\"col-lg-6\">");
        out.append("                                    <div class=\"form-box email-icon mb-30\">");
        out.append("                                        <input type=\"text\" name=\"email\" placeholder=\"Địa chỉ email\">");
        out.append("                                    </div>");
        out.append("                                </div>");
        out.append("                                <div class=\"col-lg-6\">");
        out.append("                                    <div class=\"form-box phone-icon mb-30\">");
        out.append("                                        <input type=\"text\" name=\"phone\" placeholder=\"Số điện thoại\">");
        out.append("                                    </div>");
        out.append("                                </div>");
        out.append("                                <div class=\"col-lg-6\">");
        out.append("                                    <div class=\"form-box subject-icon mb-30\">");
        out.append("                                        <input type=\"text\" name=\"subject\" placeholder=\"Chủ đề\">");
        out.append("                                    </div>");
        out.append("                                </div>");
        out.append("                                <div class=\"col-lg-12\">");
        out.append("                                    <div class=\"form-box message-icon mb-30\">");
        out.append("                                        <textarea name=\"message\" id=\"message\" cols=\"30\" rows=\"10\" placeholder=\"Nội dung tin nhắn\"></textarea>");
        out.append("                                    </div>");
        out.append("                                    <div class=\"contact-btn text-center\">");
        out.append("                                        <button class=\"btn theme-btn\" type=\"submit\">Gửi Tin Nhắn</button>");
        out.append("                                    </div>");
        out.append("                                </div>");
        out.append("                            </div>");
        out.append("                        </form>");
        out.append("                        <p class=\"ajax-response text-center\"></p>");
        out.append("                    </div>");
        out.append("                </div>");
        out.append("            </div>");
        out.append("        </section>");
        out.append("        <!-- contact-form-area end -->");
        out.append("");
        out.append("        <section class=\"map-area\">");
        out.append("            <div id=\"contact-map\" class=\"contact-map\"></div>");
        out.append("        </section>");
        out.append("    </main>");

        RequestDispatcher footerDispatcher = req.getRequestDispatcher("/public/footer-view");
        footerDispatcher.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
