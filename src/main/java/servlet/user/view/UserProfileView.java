package servlet.user.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

import servlet.constants.Avatar;
import servlet.models.User;

@WebServlet("/customer/profile")
public class UserProfileView extends HttpServlet {

    public UserProfileView() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("customer");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/public/login");
            return;
        }

        String userAddress = user.getAddress();
        String city = "";
        String district = "";
        String convince = "";
        String address = "";

        if(userAddress != null) {
            String[] addressParts = userAddress.split("##");
            if (addressParts.length == 4) {
                city = addressParts[0];
                district = addressParts[1];
                convince = addressParts[2];
                address = addressParts[3];
            }
        }

        RequestDispatcher headerDispatcher = request.getRequestDispatcher("/public/header-view");
        headerDispatcher.include(request, response);
        PrintWriter out = response.getWriter();

        out.append("<main>");
        out.append("    <!-- breadcrumb identical to template -->");
        out.append("    <section class=\"breadcrumb-area\" data-background=\""+ request.getContextPath() +"/user/img/bg/page-title.png\">");
        out.append("      <div class=\"container\">");
        out.append("        <div class=\"row\">");
        out.append("          <div class=\"col-xl-12\">");
        out.append("            <div class=\"breadcrumb-text text-center\">");
        out.append("              <h1>Hồ Sơ Cá Nhân</h1>");
        out.append("              <ul class=\"breadcrumb-menu\">");
        out.append("                <li><a href=\""+ request.getContextPath() +"/public/home\">Trang Chủ</a></li>");
        out.append("                <li><span>Hồ Sơ</span></li>");
        out.append("              </ul>");
        out.append("            </div>");
        out.append("          </div>");
        out.append("        </div>");
        out.append("      </div>");
        out.append("    </section>");
        out.append("");
        out.append("    <!-- profile section -->");
        out.append("    <section class=\"profile-area\">");
        out.append("      <div class=\"container\">");
        out.append("        <div class=\"row justify-content-center\">");
        out.append("          <div class=\"col-lg-8 col-md-10 col-sm-12\">");
        out.append("            <div class=\"profile-card\">");
        out.append("              <div class=\"avatar-wrapper\">");
        String avatarUrl = (user.getAvatar() != null && !user.getAvatar().isEmpty())
                ? request.getContextPath() + user.getAvatar()
                : Avatar.DEFAULT_AVATAR;
        out.append("                <img id=\"avatarPreview\" src=\""+ avatarUrl +"\" alt=\"Avatar\" class=\"avatar-img\">");
        out.append("              </div>");
        out.append("              <form action=\"\">");
        out.append("                <label for=\"avatarUpload\" class=\"btn-edit mt-2\" style=\"cursor:pointer;\">");
        out.append("                  <i class=\"fas fa-camera\"></i> Chọn Ảnh");
        out.append("                </label>");
        out.append("");
        out.append("                <input type=\"file\" id=\"avatarUpload\" accept=\"image/*\" style=\"display:none;\">");
        out.append("              </form>");
        out.append("");
        out.append("");
        String joinDate = user.getCreateDate() != null ? String.valueOf(user.getCreateDate()) : "N/A";
        out.append("              <h3 id=\"displayName\">"+ user.getFullname() +"</h3>");
        out.append("              <p class=\"text-muted\"><i class=\"far fa-calendar-alt\"></i> Thành viên từ: "+ joinDate +"</p>");
        out.append("");
        out.append("              <div class=\"profile-info mt-4\" id=\"profileView\">");
        out.append("                <p><i class=\"fas fa-user\"></i> Họ tên: <span id=\"nameText\">"+ user.getFullname() +"</span></p>");
        out.append("                <p><i class=\"fas fa-venus-mars\"></i> Giới tính: <span id=\"genderText\">"+ (user.getGender() != null ? user.getGender() : "Chưa cập nhật") +"</span></p>");
        out.append("                <p><i class=\"fas fa-envelope\"></i> Email: <span id=\"emailText\">"+ user.getEmail() +"</span>");
        out.append("                </p>");
        out.append("                <p><i class=\"fas fa-phone\"></i> SĐT: <span id=\"phoneText\">"+ (user.getPhoneNumber() != null ? user.getPhoneNumber() : "Chưa cập nhật") +"</span></p>");
        out.append("                <p><i class=\"fas fa-map-marker-alt\"></i> Địa chỉ: <span id=\"addressText\">"+ (user.getAddress() != null ? user.getAddress().replaceAll("##", ", ") : "Chưa cập nhật") +"</span></p>");
        out.append("                <a href=\"#\" class=\"btn-edit\" id=\"editBtn\"><i class=\"fas fa-edit text-white\"></i> Sửa Hồ Sơ</a>");
        out.append("              </div>");
        out.append("");
        out.append("              <div class=\"profile-info mt-4\" id=\"profileEdit\" style=\"display:none;\">");
        out.append("                <form id=\"profileForm\" action=\"/Furniture/customer/profile/update\" method=\"post\">");
        out.append("                  <div class=\"form-group mb-2\">");
        out.append("                    <label>Họ tên:</label>");
        out.append("                    <input type=\"text\" class=\"form-control\" id=\"nameInput\" name=\"nameInput\" required value=\""+ user.getFullname() +"\">");
        out.append("                    <div class=\"invalid-feedback\">Họ tên không được để trống.</div>");
        out.append("                  </div>");
        out.append("                  <div class=\"form-group mb-2\">");
        out.append("                    <label>Giới tính:</label>");
        out.append("                    <select class=\"form-control\" id=\"genderInput\" name=\"genderInput\">");
        out.append("                      <option value=\"Nam\" "+ ("Nam".equals(user.getGender()) ? "selected" : "") +">Nam</option>");
        out.append("                      <option value=\"Nữ\" "+ ("Nữ".equals(user.getGender()) ? "selected" : "") +">Nữ</option>");
        out.append("                      <option value=\"Khác\" "+ ("Khác".equals(user.getGender()) ? "selected" : "") +">Khác</option>");
        out.append("                    </select>");
        out.append("                  </div>");
        out.append("                  <div class=\"form-group mb-2\">");
        out.append("                    <label>Email:</label>");
        out.append("                    <input type=\"email\" class=\"form-control\" id=\"emailInput\" name=\"emailInput\" required value=\""+ user.getEmail() +"\" readonly>");
        out.append("                  </div>");
        out.append("                  <div class=\"form-group mb-2\">");
        out.append("                    <label>SĐT:</label>");
        out.append("                    <input type=\"text\" class=\"form-control\" id=\"phoneInput\" name=\"phoneInput\" required value=\""+ (user.getPhoneNumber() != null ? user.getPhoneNumber() : "") +"\">");
        out.append("                    <div class=\"invalid-feedback\">Vui lòng nhập số điện thoại hợp lệ (10 số, bắt đầu bằng 0).</div>");
        out.append("                  </div>");
        out.append("                  <div class=\"form-group mb-3\">");
        out.append("                    <label>Địa chỉ chi tiết:</label>");
        out.append("                    <input type=\"text\" class=\"form-control\" id=\"addressInput\" name=\"addressInput\" required value=\""+ (!"".equals(address) ? address : "") +"\">");
        out.append("                  </div>");
        out.append("                  <div class=\"form-group mb-3\">");
        out.append("                    <label>Tỉnh/ Thành phố:</label>");
        out.append("                    <input type=\"text\" class=\"form-control\" id=\"city\" name=\"city\" required value=\""+ (!"".equals(city) ? city : "") +"\">");
        out.append("                  </div>");
        out.append("                  <div class=\"form-group mb-3\">");
        out.append("                    <label>Quận/Huyện:</label>");
        out.append("                    <input type=\"text\" class=\"form-control\" id=\"district\" name=\"district\" required value=\""+ (!"".equals(district) ? district : "") +"\">");
        out.append("                  </div>");
        out.append("                  <div class=\"form-group mb-3\">");
        out.append("                    <label>Xã/Phường/Thị trấn:</label>");
        out.append("                    <input type=\"text\" class=\"form-control\" id=\"convince\" name=\"convince\" required value=\""+ (!"".equals(convince) ? convince : "") +"\">");
        out.append("                  </div>");
        out.append("");
        out.append("                  <button type=\"submit\" class=\"btn-edit\" id=\"saveBtn\"><i class=\"fas fa-save text-white\"></i> Lưu</button>");
        out.append("                  <button type=\"button\" class=\"btn-edit\" style=\"background: #6c757d;\" id=\"cancelBtn\">");
        out.append("                    <i class=\"fas fa-times text-white\"></i> Hủy");
        out.append("                  </button>");
        out.append("                </form>");
        out.append("              </div>");
        out.append("            </div>");
        out.append("          </div>");
        out.append("        </div>");
        out.append("      </div>");
        out.append("    </section>");
        out.append("  </main>");
        out.append("  <script src=\""+ request.getContextPath() +"/user/js/profile.js\"></script>");

        RequestDispatcher footerDispatcher = request.getRequestDispatcher("/public/footer-view");
        footerDispatcher.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
