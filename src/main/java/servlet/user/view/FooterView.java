package servlet.user.view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/public/footer-view")
public class FooterView extends HttpServlet {

    public FooterView() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.append("        <!-- footer start -->");
        out.append("        <footer>");
        out.append("            <div class=\"footer-area box-90 pt-100 pb-60\">");
        out.append("                <div class=\"container-fluid\">");
        out.append("                    <div class=\"row\">");
        out.append("                        <div class=\"col-xl-3 col-lg-5 col-md-6 \">");
        out.append("                            <div class=\"footer-widget mb-40\">");
        out.append("                                <div class=\"footer-logo\">");
        out.append("                                    <a href=\"index.html\"><img src=\"../user/img/logo/footer-logo.png\" alt=\"\"></a>");
        out.append("                                </div>");
        out.append("                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore mag na");
        out.append("                                aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        out.append("                                </p>");
        out.append("                                <div class=\"footer-time d-flex mt-30\">");
        out.append("                                    <div class=\"time-icon\">");
        out.append("                                        <img src=\"../user/img/icon/time.png\" alt=\"\">");
        out.append("                                    </div>");
        out.append("                                    <div class=\"time-text\">");
        out.append("                                        <span>Got Questions ? Call us 24/7!</span>");
        out.append("                                        <h2>(0600) 874 548</h2>");
        out.append("                                    </div>");
        out.append("                                </div>");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("                        <div class=\"col-xl-2 col-lg-2 col-md-3 d-lg-none d-xl-block\">");
        out.append("                            <div class=\"footer-widget pl-50 mb-40\">");
        out.append("                                <h3>Social Media</h3>");
        out.append("                                <ul class=\"footer-link\">");
        out.append("                                    <li><a href=\"#\">Facebook</a></li>");
        out.append("                                    <li><a href=\"#\">Twitter</a></li>");
        out.append("                                    <li><a href=\"#\">Behance</a></li>");
        out.append("                                    <li><a href=\"#\"> Dribbble</a></li>");
        out.append("                                    <li><a href=\"#\">Linkedin</a></li>");
        out.append("                                    <li><a href=\"#\">Youtube</a></li>");
        out.append("                                </ul>");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("                        <div class=\"col-xl-2 col-lg-2 col-md-3 d-lg-none d-xl-block\">");
        out.append("                            <div class=\"footer-widget pl-30 mb-40\">");
        out.append("                                <h3>Location</h3>");
        out.append("                                <ul class=\"footer-link\">");
        out.append("                                    <li><a href=\"#\">New York</a></li>");
        out.append("                                    <li><a href=\"#\">Tokyo</a></li>");
        out.append("                                    <li><a href=\"#\">Dhaka</a></li>");
        out.append("                                    <li><a href=\"#\"> Chittagong</a></li>");
        out.append("                                    <li><a href=\"#\">China</a></li>");
        out.append("                                    <li><a href=\"#\">Japan</a></li>");
        out.append("                                </ul>");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("                        <div class=\"col-xl-2 col-lg-2 col-md-3\">");
        out.append("                            <div class=\"footer-widget mb-40\">");
        out.append("                                <h3>About</h3>");
        out.append("                                <ul class=\"footer-link\">");
        out.append("                                    <li><a href=\"#\">Terms & Conditions</a></li>");
        out.append("                                    <li><a href=\"#\"> Privacy Policy</a></li>");
        out.append("                                    <li><a href=\"#\">Contact Us</a></li>");
        out.append("                                    <li><a href=\"#\">FAQ</a></li>");
        out.append("                                    <li><a href=\"#\">Wholesale</a></li>");
        out.append("                                    <li><a href=\"#\">Direction</a></li>");
        out.append("                                </ul>");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("                        <div class=\"col-xl-3 col-lg-5 col-md-6\">");
        out.append("                            <div class=\"footer-widget mb-40\">");
        out.append("                                <div class=\"footer-banner\">");
        out.append("                                    <a href=\"shop.html\"><img src=\"../user/img/banner/add.jpg\" alt=\"\"></a>");
        out.append("                                </div>");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("                    </div>");
        out.append("                </div>");
        out.append("            </div>");
        out.append("            <div class=\"copyright-area box-105\">");
        out.append("                <div class=\"container-fluid\">");
        out.append("                    <div class=\"copyright-border pt-30 pb-30\">");
        out.append("                        <div class=\"row\">");
        out.append("                            <div class=\"col-xl-6 col-lg-6 col-md-6\">");
        out.append("                                <div class=\"copyright text-center text-md-left\">");
        out.append("                                    <p>Copyright © 2019 <a href=\"#\">BasicTheme</a>. All Rights Reserved</p>");
        out.append("                                </div>");
        out.append("                            </div>");
        out.append("                            <div class=\"col-xl-6 col-lg-6 col-md-6\">");
        out.append("                                <div class=\"footer-icon text-center text-md-right \">");
        out.append("                                    <a href=\"#\"><i class=\"fab fa-facebook-f\"></i></a>");
        out.append("                                    <a href=\"#\"><i class=\"fab fa-twitter\"></i></a>");
        out.append("                                    <a href=\"#\"><i class=\"fab fa-behance\"></i></a>");
        out.append("                                    <a href=\"#\"><i class=\"fab fa-linkedin-in\"></i></a>");
        out.append("                                    <a href=\"#\"><i class=\"fab fa-youtube\"></i></a>");
        out.append("                                </div>");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("                    </div>");
        out.append("                </div>");
        out.append("            </div>");
        out.append("        </footer>");
        out.append("        <!-- footer end -->");
        out.append("");
        out.append("    <!-- Fullscreen search -->");
        out.append("    <div class=\"search-wrap\">");
        out.append("        <div class=\"search-inner\">");
        out.append("            <i class=\"fas fa-times search-close\" id=\"search-close\"></i>");
        out.append("            <div class=\"search-cell\">");
        out.append("                <form method=\"get\">");
        out.append("                    <div class=\"search-field-holder\">");
        out.append("                        <input type=\"search\" class=\"main-search-input\" placeholder=\"Search Entire Store...\">");
        out.append("                    </div>");
        out.append("                </form>");
        out.append("            </div>");
        out.append("        </div>");
        out.append("    </div> <!-- end fullscreen search -->");
        out.append("      <!-- Container để chứa alert -->");
        out.append("      <div id=\"alert-container\" class = \"z-2 position-absolute\"></div>");
        out.append("");
        out.append("");
        out.append("		<!-- JS here -->");
        out.append("        <script src=\"../user/js/vendor/jquery-1.12.4.min.js\"></script>");
        out.append("        <script src=\"../user/js/popper.min.js\"></script>");
        out.append("        <script src=\"../user/js/bootstrap.min.js\"></script>");
        out.append("        <script src=\"../user/js/owl.carousel.min.js\"></script>");
        out.append("        <script src=\"../user/js/isotope.pkgd.min.js\"></script>");
        out.append("        <script src=\"../user/js/one-page-nav-min.js\"></script>");
        out.append("        <script src=\"../user/js/slick.min.js\"></script>");
        out.append("        <script src=\"../user/js/jquery.meanmenu.min.js\"></script>");
        out.append("        <script src=\"../user/js/ajax-form.js\"></script>");
        out.append("        <script src=\"../user/js/wow.min.js\"></script>");
        out.append("        <script src=\"../user/js/jquery.scrollUp.min.js\"></script>");
        out.append("        <script src=\"../user/js/jquery.final-countdown.min.js\"></script>");
        out.append("        <script src=\"../user/js/imagesloaded.pkgd.min.js\"></script>");
        out.append("        <script src=\"../user/js/jquery.magnific-popup.min.js\"></script>");
        out.append("        <script src=\"../user/js/plugins.js\"></script>");
        out.append("        <script src=\"../user/js/main.js\"></script>");
        out.append("        <script src=\"../user/js/showAlert.js\"></script>");
        out.append("    </body>");
        out.append("");
        out.append("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
