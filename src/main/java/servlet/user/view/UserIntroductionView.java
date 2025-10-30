package servlet.user.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/public/introduction")
public class UserIntroductionView extends HttpServlet {

    public UserIntroductionView() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();

        RequestDispatcher headerDispatcher = req.getRequestDispatcher("/public/header-view");
        headerDispatcher.include(req, resp);

        out.append("<main>");
        out.append("");
        out.append("        <!-- breadcrumb-area-start -->");
        out.append("        <section class=\"breadcrumb-area\" data-background=\""+ req.getContextPath() +"/user/img/bg/page-title.png\">");
        out.append("            <div class=\"container\">");
        out.append("                <div class=\"row\">");
        out.append("                    <div class=\"col-xl-12\">");
        out.append("                        <div class=\"breadcrumb-text text-center\">");
        out.append("                            <h1>Giới Thiệu</h1>");
        out.append("                            <ul class=\"breadcrumb-menu\">");
        out.append("                                <li><a href=\""+ req.getContextPath() +"/public/home\">Trang Chủ</a></li>");
        out.append("                                <li><span>Giới Thiệu</span></li>");
        out.append("                            </ul>");
        out.append("                        </div>");
        out.append("                    </div>");
        out.append("                </div>");
        out.append("            </div>");
        out.append("        </section>");
        out.append("        <!-- breadcrumb-area-end -->");
        out.append("");
        out.append("            <!-- page-title-area end -->");
        out.append("            <section class=\"about-area pt-100\">");
        out.append("                <div class=\"container\">");
        out.append("                    <div class=\"row\">");
        out.append("                        <div class=\"col-xl-5\">");
        out.append("                            <div class=\"section-title mb-25\">");
        out.append("                                <p><span></span> Về Chúng Tôi</p>");
        out.append("                                <h1>Câu Chuyện Về Song Lê</h1>");
        out.append("                            </div>");
        out.append("                            <div class=\"about-community mb-30\">");
        out.append("                                <p>Nội thất Song Lê được thành lập với một niềm đam mê mãnh liệt: kiến tạo nên những không gian sống đẳng cấp, nơi mỗi món đồ nội thất không chỉ là vật dụng mà còn là một tác phẩm nghệ thuật, thể hiện phong cách và cá tính của gia chủ.</p>");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("                        <div class=\"col-xl-7\">");
        out.append("                            <div class=\"about-community-text mb-30\">");
        out.append("                                <p>Chúng tôi tin rằng một không gian sống hoàn hảo được tạo nên từ sự kết hợp hài hòa giữa thẩm mỹ tinh tế và công năng vượt trội. Từng sản phẩm của Song Lê, từ bộ sofa phòng khách đến chiếc giường ngủ êm ái, đều được chăm chút tỉ mỉ từ khâu lựa chọn vật liệu cao cấp đến quá trình chế tác công phu bởi những người thợ lành nghề.</p>");
        out.append("                                <p>Mỗi thiết kế mang đậm dấu ấn riêng, là sự giao thoa giữa vẻ đẹp đương đại và những giá trị bền vững với thời gian. Chúng tôi không ngừng tìm tòi, sáng tạo để mang đến những giải pháp nội thất thông minh, tối ưu hóa không gian và nâng tầm chất lượng cuộc sống cho khách hàng.</p>");
        out.append("                                <p>Với đội ngũ kiến trúc sư và chuyên viên tư vấn tận tâm, Song Lê cam kết đồng hành cùng bạn trên hành trình kiến tạo ngôi nhà mơ ước, một không gian thực sự là của riêng bạn.</p>");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("                    </div>");
        out.append("                    <div class=\"row\">");
        out.append("                        <div class=\"col-12\">");
        out.append("                            <div class=\"overview text-center mt-90\">");
        out.append("                                <p>Song Lê không chỉ bán đồ nội thất, chúng tôi mang đến những giải pháp toàn diện để biến mỗi ngôi nhà trở thành một tổ ấm đích thực, nơi vẻ đẹp, sự tiện nghi và cảm hứng sống được thăng hoa. Chúng tôi tự hào khi được hàng ngàn gia đình Việt tin tưởng và lựa chọn.</p>");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("                    </div>");
        out.append("                </div>");
        out.append("            </section>");
        out.append("            <section class=\"video-area\">");
        out.append("                <div class=\"bakix-video\">");
        out.append("                    <img src=\""+ req.getContextPath() +"/user/img/bg/video.jpg\" alt=\"\">");
        out.append("                    <a class=\"popup-video\" href=\"https://www.youtube.com/watch?v=H9TodoxwkDo\"><i class=\"fas fa-play\"></i></a>");
        out.append("                </div>");
        out.append("            </section>");
        out.append("            <section class=\"mission-area pt-100 pb-70 \">");
        out.append("                <div class=\"container\">");
        out.append("                    <div class=\"row\">");
        out.append("                        <div class=\"col-xl-5 d-lg-none d-xl-block\">");
        out.append("                            <div class=\"mission-img mb-30\">");
        out.append("                                <img src=\""+ req.getContextPath() +"/user/img/bg/mission.jpg\" alt=\"\">");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("                        <div class=\"col-xl-7\">");
        out.append("                            <div class=\"mission-text mb-30\">");
        out.append("                                <div class=\"mission-title mb-30\">");
        out.append("                                    <p><span></span> Sứ Mệnh</p>");
        out.append("                                    <h1>Sứ Mệnh Của Chúng Tôi Là Kiến Tạo Không Gian Sống Đẳng Cấp.</h1>");
        out.append("                                </div>");
        out.append("                                <p>Tại Song Lê, chúng tôi theo đuổi sứ mệnh nâng tầm không gian sống của người Việt thông qua những sản phẩm nội thất chất lượng vượt trội, thiết kế tinh xảo và dịch vụ khách hàng tận tâm. Chúng tôi mong muốn mỗi sản phẩm không chỉ đáp ứng nhu cầu sử dụng mà còn mang lại giá trị thẩm mỹ và cảm xúc cho người sở hữu.</p>");
        out.append("                                <p>Chúng tôi cam kết sử dụng những vật liệu tốt nhất, áp dụng công nghệ sản xuất hiện đại và không ngừng đổi mới trong thiết kế để tạo ra những sản phẩm bền vững, an toàn cho sức khỏe và thân thiện với môi trường. Sự hài lòng của khách hàng chính là thước đo thành công và là động lực để chúng tôi phát triển mỗi ngày.</p>");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("                    </div>");
        out.append("                </div>");
        out.append("            </section>");
        out.append("            <section class=\"big-team-area\">");
        out.append("                <div class=\"big-image\">");
        out.append("                    <img src=\""+ req.getContextPath() +"/user/img/bg/banner.jpg\" alt=\"\">");
        out.append("                </div>");
        out.append("                <div class=\"container\">");
        out.append("                    <div class=\"row\">");
        out.append("                        <div class=\"col-12\">");
        out.append("                            <div class=\"testimonial-active owl-carousel theme-bg\">");
        out.append("                                <div class=\"testimonial-item text-center\">");
        out.append("                                    <p>“Tôi thực sự hài lòng với bộ sofa của Song Lê. Chất liệu da thật, đường may tỉ mỉ và thiết kế rất sang trọng, làm bừng sáng cả phòng khách nhà tôi.”</p>");
        out.append("                                    <span>- Anh Minh, Biệt thự The Manor</span>");
        out.append("                                </div>");
        out.append("                                <div class=\"testimonial-item text-center\">");
        out.append("                                    <p>“Đội ngũ tư vấn rất chuyên nghiệp, đã giúp gia đình tôi chọn được bộ nội thất phòng ngủ vô cùng ưng ý, vừa đẹp vừa tiện nghi. Chắc chắn sẽ quay lại.”</p>");
        out.append("                                    <span>- Chị Hà, Căn hộ Royal City</span>");
        out.append("                                </div>");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("                    </div>");
        out.append("                </div>");
        out.append("            </section>");
        out.append("            <!-- team-area start -->");
        out.append("            <section class=\"team-area pt-100 pb-70\">");
        out.append("                <div class=\"container\">");
        out.append("                    <div class=\"row\">");
        out.append("                        <div class=\"col-xl-12\">");
        out.append("                            <div class=\"area-title text-center mb-50\">");
        out.append("                                <h2>Đội Ngũ Của Chúng Tôi</h2>");
        out.append("                                <p>Những con người tâm huyết làm nên Song Lê</p>");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("                    </div>");
        out.append("                    <div class=\"row\">");
        out.append("                        <div class=\"col-lg-4 col-md-6\">");
        out.append("                            <div class=\"team mb-30\">");
        out.append("                                <div class=\"team__img\">");
        out.append("                                    <img src=\""+ req.getContextPath() +"/user/img/team/team4.jpg\" alt=\"\">");
        out.append("                                </div>");
        out.append("                                <div class=\"team__content text-center white-bg\">");
        out.append("                                    <h4>Lê Văn Song</h4>");
        out.append("                                    <span>Nhà Sáng Lập</span>");
        out.append("                                </div>");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("                        <div class=\"col-lg-4 col-md-6\">");
        out.append("                            <div class=\"team mb-30\">");
        out.append("                                <div class=\"team__img\">");
        out.append("                                    <img src=\""+ req.getContextPath() +"/user/img/team/team5.jpg\" alt=\"\">");
        out.append("                                </div>");
        out.append("                                <div class=\"team__content text-center white-bg\">");
        out.append("                                    <h4>Trần Thu Hà</h4>");
        out.append("                                    <span>Kiến Trúc Sư Trưởng</span>");
        out.append("                                </div>");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("                        <div class=\"col-lg-4 col-md-6\">");
        out.append("                            <div class=\"team mb-30\">");
        out.append("                                <div class=\"team__img\">");
        out.append("                                    <img src=\""+ req.getContextPath() +"/user/img/team/team6.jpg\" alt=\"\">");
        out.append("                                </div>");
        out.append("                                <div class=\"team__content text-center white-bg\">");
        out.append("                                    <h4>Nguyễn Hoàng An</h4>");
        out.append("                                    <span>Chuyên Viên Tư Vấn</span>");
        out.append("                                </div>");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("                    </div>");
        out.append("                </div>");
        out.append("            </section>");
        out.append("            <!-- team-area end -->");
        out.append("");
        out.append("");
        out.append("        </main>");

        RequestDispatcher footerDispatcher = req.getRequestDispatcher("/public/footer-view");
        footerDispatcher.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
