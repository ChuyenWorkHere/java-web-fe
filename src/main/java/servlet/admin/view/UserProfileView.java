package servlet.admin.view;

import servlet.dao.UserDAO;
import servlet.dao.impl.UserDAOImpl;
import servlet.models.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserProfileView
 */
@WebServlet("/admin/profile")
public class UserProfileView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO userDAO;

    public UserProfileView() {
        super();
		userDAO = new UserDAOImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		User loggedInUser = userDAO.getLoggedInUser(session);

		if(loggedInUser == null){
			response.sendRedirect("/Furniture/admin/signin");
			return;
		}
		
		PrintWriter out = response.getWriter();
		
		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
	    headerDispatcher.include(request, response);
	    
	    out.append("<main id=\"main\" class=\"main\">");
	    out.append("");
	    out.append("    <div class=\"pagetitle d-flex justify-content-between\">");
	    out.append("      <h1>Thông tin</h1>");
	    out.append("      <nav class=\"d-flex align-items-center\">");
	    out.append("        <ol class=\"breadcrumb mb-0\">");
	    out.append("          <li class=\"breadcrumb-item\"><a href=\"../admin/home-view\">Home</a></li>");
	    out.append("          <li class=\"breadcrumb-item\">Users</li>");
	    out.append("          <li class=\"breadcrumb-item active\">Profile</li>");
	    out.append("        </ol>");
	    out.append("      </nav>");
	    out.append("    </div><!-- End Page Title -->");
	    out.append("");
	    out.append("    <section class=\"section profile\">");
		out.append("      <!-- Container để chứa alert -->");
		out.append("      <div id=\"alert-container\" class = \"z-2 position-absolute\"></div>");
	    out.append("      <div class=\"row\">");
	    out.append("        <div class=\"col-xl-4\">");
	    out.append("");
	    out.append("          <div class=\"card\">");
	    out.append("            <div class=\"card-body profile-card pt-4 d-flex flex-column align-items-center\">");
	    out.append("");
	    out.append("              <img src=\"img/profile-img.jpg\" alt=\"Profile\" class=\"rounded-circle\">");
	    out.append("              <h2>"+loggedInUser.getFullname()+"</h2>");
	    out.append("              <h3>"+loggedInUser.getEmail()+"</h3>");
	    out.append("              <div class=\"social-links mt-2\">");
	    out.append("                <a href=\"#\" class=\"twitter\"><i class=\"bi bi-twitter\"></i></a>");
	    out.append("                <a href=\"#\" class=\"facebook\"><i class=\"bi bi-facebook\"></i></a>");
	    out.append("                <a href=\"#\" class=\"instagram\"><i class=\"bi bi-instagram\"></i></a>");
	    out.append("                <a href=\"#\" class=\"linkedin\"><i class=\"bi bi-linkedin\"></i></a>");
	    out.append("              </div>");
	    out.append("            </div>");
	    out.append("          </div>");
	    out.append("");
	    out.append("        </div>");
	    out.append("");
	    out.append("        <div class=\"col-xl-8\">");
	    out.append("");
	    out.append("          <div class=\"card\">");
	    out.append("            <div class=\"card-body pt-3\">");
	    out.append("              <!-- Bordered Tabs -->");
	    out.append("              <ul class=\"nav nav-tabs nav-tabs-bordered\">");
	    out.append("");
	    out.append("                <li class=\"nav-item\">");
	    out.append("                  <button class=\"nav-link active\" data-bs-toggle=\"tab\" data-bs-target=\"#profile-overview\"><i class=\"bi bi-info-circle me-2\"></i>Thông tin</button>");
	    out.append("                </li>");
	    out.append("");
	    out.append("                <li class=\"nav-item\">");
	    out.append("                  <button class=\"nav-link\" data-bs-toggle=\"tab\" data-bs-target=\"#profile-edit\"><i class=\"bi bi-pencil-square me-2\"></i>Chỉnh sửa</button>");
	    out.append("                </li>");
	    out.append("");
	    out.append("                <li class=\"nav-item\">");
	    out.append("                  <button class=\"nav-link\" data-bs-toggle=\"tab\" data-bs-target=\"#profile-change-password\"><i class=\"bi bi-key-fill me-2\"></i>Đổi mật khẩu</button>");
	    out.append("                </li>");
	    out.append("");
	    out.append("              </ul>");
	    out.append("              <div class=\"tab-content pt-2\">");
	    out.append("");
	    out.append("                <div class=\"tab-pane fade show active profile-overview\" id=\"profile-overview\">");
	    out.append("");
	    out.append("                  <div class=\"row\">");
	    out.append("                    <div class=\"col-lg-3 col-md-4 label \">Họ tên</div>");
	    out.append("                    <div class=\"col-lg-9 col-md-8\">"+loggedInUser.getFullname()+"</div>");
	    out.append("                  </div>");
	    out.append("");
	    out.append("                  <div class=\"row\">");
	    out.append("                    <div class=\"col-lg-3 col-md-4 label\">Giới tính</div>");
	    out.append("                    <div class=\"col-lg-9 col-md-8\">"+loggedInUser.getGender()+"</div>");
	    out.append("                  </div>");
	    out.append("");
	    out.append("                  <div class=\"row\">");
	    out.append("                    <div class=\"col-lg-3 col-md-4 label\">Vai trò</div>");
	    out.append("                    <div class=\"col-lg-9 col-md-8\">"+loggedInUser.getRole().getRoleName()+"</div>");
	    out.append("                  </div>");
	    out.append("");
	    out.append("                  <div class=\"row\">");
	    out.append("                    <div class=\"col-lg-3 col-md-4 label\">Địa chỉ</div>");
	    out.append("                    <div class=\"col-lg-9 col-md-8\">"+loggedInUser.getAddress()+"</div>");
	    out.append("                  </div>");
	    out.append("");
	    out.append("                  <div class=\"row\">");
	    out.append("                    <div class=\"col-lg-3 col-md-4 label\">SĐT</div>");
	    out.append("                    <div class=\"col-lg-9 col-md-8\">"+loggedInUser.getPhoneNumber()+"</div>");
	    out.append("                  </div>");
	    out.append("");
	    out.append("                  <div class=\"row\">");
	    out.append("                    <div class=\"col-lg-3 col-md-4 label\">Email</div>");
	    out.append("                    <div class=\"col-lg-9 col-md-8\">"+loggedInUser.getEmail()+"</div>");
	    out.append("                  </div>");
	    out.append("");
	    out.append("                  <div class=\"row\">");
	    out.append("                    <div class=\"col-lg-3 col-md-4 label\">Ngày tạo tài khoản</div>");
	    out.append("                    <div class=\"col-lg-9 col-md-8\">"+loggedInUser.getCreateDate()+"</div>");
	    out.append("                  </div>");
	    out.append("");
		out.append("                  <div class=\"row\">");
		out.append("                    <div class=\"col-lg-3 col-md-4 label\">Tình trạng</div>");
		out.append("                    <div class=\"col-lg-9 col-md-8\">"+ (loggedInUser.isActive() ? "Hoạt động"  : "Bảo trì")+"</div>");
		out.append("                  </div>");
	    out.append("                </div>");
	    out.append("");
	    out.append("                <div class=\"tab-pane fade profile-edit pt-3\" id=\"profile-edit\">");
	    out.append("");
	    out.append("                  <!-- Profile Edit Form -->");
		out.append("                  <form method=\"post\" action=\"/Furniture/admin/update-profile\">");

		out.append("                    <div class=\"row mb-3\">");
		out.append("                      <label for=\"fullName\" class=\"col-md-4 col-lg-3 col-form-label\">Họ tên</label>");
		out.append("                      <div class=\"col-md-8 col-lg-9\">");
		out.append("                        <input name=\"fullName\" type=\"text\" class=\"form-control\" id=\"fullName\" value=\"" + loggedInUser.getFullname() + "\">");
		out.append("                      </div>");
		out.append("                    </div>");

		out.append("                    <div class=\"row mb-3\">");
		out.append("                      <label for=\"gender\" class=\"col-md-4 col-lg-3 col-form-label\">Giới tính</label>");
		out.append("                      <div class=\"col-md-8 col-lg-9\">");
		out.append("                        <select name=\"gender\" id=\"gender\" class=\"form-select\">");
		out.append("                          <option value=\"Nam\"" + ("Nam".equalsIgnoreCase(loggedInUser.getGender()) ? " selected" : "") + ">Nam</option>");
		out.append("                          <option value=\"Nữ\"" + ("Nữ".equalsIgnoreCase(loggedInUser.getGender()) ? " selected" : "") + ">Nữ</option>");
		out.append("                        </select>");
		out.append("                      </div>");
		out.append("                    </div>");

		out.append("                    <div class=\"row mb-3\">");
		out.append("                      <label for=\"role\" class=\"col-md-4 col-lg-3 col-form-label\">Vai trò</label>");
		out.append("                      <div class=\"col-md-8 col-lg-9\">");
		out.append("                        <input name=\"role\" type=\"text\" class=\"form-control\" id=\"role\" value=\"" + loggedInUser.getRole().getRoleName() + "\" readonly>");
		out.append("                      </div>");
		out.append("                    </div>");

		out.append("                    <div class=\"row mb-3\">");
		out.append("                      <label for=\"address\" class=\"col-md-4 col-lg-3 col-form-label\">Địa chỉ</label>");
		out.append("                      <div class=\"col-md-8 col-lg-9\">");
		out.append("                        <input name=\"address\" type=\"text\" class=\"form-control\" id=\"address\" value=\"" + loggedInUser.getAddress() + "\">");
		out.append("                      </div>");
		out.append("                    </div>");

		out.append("                    <div class=\"row mb-3\">");
		out.append("                      <label for=\"phoneNumber\" class=\"col-md-4 col-lg-3 col-form-label\">SĐT</label>");
		out.append("                      <div class=\"col-md-8 col-lg-9\">");
		out.append("                        <input name=\"phoneNumber\" type=\"text\" class=\"form-control\" id=\"phoneNumber\" value=\"" + loggedInUser.getPhoneNumber() + "\">");
		out.append("                      </div>");
		out.append("                    </div>");

		out.append("                    <div class=\"row mb-3\">");
		out.append("                      <label for=\"email\" class=\"col-md-4 col-lg-3 col-form-label\">Email</label>");
		out.append("                      <div class=\"col-md-8 col-lg-9\">");
		out.append("                        <input name=\"email\" type=\"email\" class=\"form-control\" id=\"email\" value=\"" + loggedInUser.getEmail() + "\">");
		out.append("                      </div>");
		out.append("                    </div>");

		out.append("                    <div class=\"row mb-3\">");
		out.append("                      <label for=\"createDate\" class=\"col-md-4 col-lg-3 col-form-label\">Ngày tạo tài khoản</label>");
		out.append("                      <div class=\"col-md-8 col-lg-9\">");
		out.append("                        <input name=\"createDate\" type=\"text\" class=\"form-control\" id=\"createDate\" value=\"" + loggedInUser.getCreateDate() + "\" readonly>");
		out.append("                      </div>");
		out.append("                    </div>");

		out.append("                    <div class=\"row mb-3\">");
		out.append("                      <label for=\"status\" class=\"col-md-4 col-lg-3 col-form-label\">Tình trạng</label>");
		out.append("                      <div class=\"col-md-8 col-lg-9\">");
		out.append("                        <input name=\"status\" type=\"text\" class=\"form-control\" id=\"status\" value=\"" + (loggedInUser.isActive() ? "Hoạt động" : "Bảo trì") + "\" readonly>");
		out.append("                      </div>");
		out.append("                    </div>");

		out.append("                    <div class=\"text-center\">");
		out.append("                      <button type=\"submit\" class=\"btn btn-primary\">Cập nhật</button>");
		out.append("                    </div>");

		out.append("                  </form><!-- End Profile Edit Form -->");
	    out.append("");
	    out.append("                </div>");
	    out.append("");
	    out.append("                <div class=\"tab-pane fade pt-3\" id=\"profile-settings\">");
	    out.append("");
	    out.append("                </div>");
	    out.append("");
	    out.append("                <div class=\"tab-pane fade pt-3\" id=\"profile-change-password\">");
	    out.append("                  <!-- Change Password Form -->");
	    out.append("                  <form>");
	    out.append("");
	    out.append("                    <div class=\"row mb-3\">");
	    out.append("                      <label for=\"currentPassword\" class=\"col-md-4 col-lg-3 col-form-label\">Mật khẩu hiện tại</label>");
	    out.append("                      <div class=\"col-md-8 col-lg-9\">");
	    out.append("                        <input name=\"password\" type=\"password\" class=\"form-control\" id=\"currentPassword\">");
	    out.append("                      </div>");
	    out.append("                    </div>");
	    out.append("");
	    out.append("                    <div class=\"row mb-3\">");
	    out.append("                      <label for=\"newPassword\" class=\"col-md-4 col-lg-3 col-form-label\">Mật khẩu mới</label>");
	    out.append("                      <div class=\"col-md-8 col-lg-9\">");
	    out.append("                        <input name=\"newpassword\" type=\"password\" class=\"form-control\" id=\"newPassword\">");
	    out.append("                      </div>");
	    out.append("                    </div>");
	    out.append("");
	    out.append("                    <div class=\"row mb-3\">");
	    out.append("                      <label for=\"renewPassword\" class=\"col-md-4 col-lg-3 col-form-label\">Nhập lại mật khẩu mới</label>");
	    out.append("                      <div class=\"col-md-8 col-lg-9\">");
	    out.append("                        <input name=\"renewpassword\" type=\"password\" class=\"form-control\" id=\"renewPassword\">");
	    out.append("                      </div>");
	    out.append("                    </div>");
	    out.append("");
	    out.append("                    <div class=\"text-center\">");
	    out.append("                      <button type=\"submit\" class=\"btn btn-primary\">Change Password</button>");
	    out.append("                    </div>");
	    out.append("                  </form><!-- End Change Password Form -->");
	    out.append("");
	    out.append("                </div>");
	    out.append("");
	    out.append("              </div><!-- End Bordered Tabs -->");
	    out.append("");
	    out.append("            </div>");
	    out.append("          </div>");
	    out.append("");
	    out.append("        </div>");
	    out.append("      </div>");
	    out.append("    </section>");
	    out.append("");
	    out.append("  </main><!-- End #main -->");
		out.append("  <script src=\"../admin/js/showAlert.js\"></script>");

		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
		footerDispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
