package servlet.admin.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserProfileView
 */
@WebServlet("/admin/profile")
public class UserProfileView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");		 
		
		PrintWriter out = response.getWriter();
		
		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
	    headerDispatcher.include(request, response);
	    
	    out.append("<main id=\"main\" class=\"main\">");
	    out.append("");
	    out.append("    <div class=\"pagetitle d-flex justify-content-between\">");
	    out.append("      <h1>Profile</h1>");
	    out.append("      <nav class=\"d-flex align-items-center\">");
	    out.append("        <ol class=\"breadcrumb mb-0\">");
	    out.append("          <li class=\"breadcrumb-item\"><a href=\"index.html\">Home</a></li>");
	    out.append("          <li class=\"breadcrumb-item\">Users</li>");
	    out.append("          <li class=\"breadcrumb-item active\">Profile</li>");
	    out.append("        </ol>");
	    out.append("      </nav>");
	    out.append("    </div><!-- End Page Title -->");
	    out.append("");
	    out.append("    <section class=\"section profile\">");
	    out.append("      <div class=\"row\">");
	    out.append("        <div class=\"col-xl-4\">");
	    out.append("");
	    out.append("          <div class=\"card\">");
	    out.append("            <div class=\"card-body profile-card pt-4 d-flex flex-column align-items-center\">");
	    out.append("");
	    out.append("              <img src=\"img/profile-img.jpg\" alt=\"Profile\" class=\"rounded-circle\">");
	    out.append("              <h2>Kevin Anderson</h2>");
	    out.append("              <h3>Web Designer</h3>");
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
	    out.append("                  <button class=\"nav-link\" data-bs-toggle=\"tab\" data-bs-target=\"#profile-settings\"><i class=\"bi bi-gear-fill me-2\"></i>Cài đặt</button>");
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
	    out.append("                  <h5 class=\"card-title\">About</h5>");
	    out.append("                  <p class=\"small fst-italic\">Sunt est soluta temporibus accusantium neque nam maiores cumque temporibus. Tempora libero non est unde veniam est qui dolor. Ut sunt iure rerum quae quisquam autem eveniet perspiciatis odit. Fuga sequi sed ea saepe at unde.</p>");
	    out.append("");
	    out.append("                  <h5 class=\"card-title\">Profile Details</h5>");
	    out.append("");
	    out.append("                  <div class=\"row\">");
	    out.append("                    <div class=\"col-lg-3 col-md-4 label \">Full Name</div>");
	    out.append("                    <div class=\"col-lg-9 col-md-8\">Kevin Anderson</div>");
	    out.append("                  </div>");
	    out.append("");
	    out.append("                  <div class=\"row\">");
	    out.append("                    <div class=\"col-lg-3 col-md-4 label\">Company</div>");
	    out.append("                    <div class=\"col-lg-9 col-md-8\">Lueilwitz, Wisoky and Leuschke</div>");
	    out.append("                  </div>");
	    out.append("");
	    out.append("                  <div class=\"row\">");
	    out.append("                    <div class=\"col-lg-3 col-md-4 label\">Job</div>");
	    out.append("                    <div class=\"col-lg-9 col-md-8\">Web Designer</div>");
	    out.append("                  </div>");
	    out.append("");
	    out.append("                  <div class=\"row\">");
	    out.append("                    <div class=\"col-lg-3 col-md-4 label\">Country</div>");
	    out.append("                    <div class=\"col-lg-9 col-md-8\">USA</div>");
	    out.append("                  </div>");
	    out.append("");
	    out.append("                  <div class=\"row\">");
	    out.append("                    <div class=\"col-lg-3 col-md-4 label\">Address</div>");
	    out.append("                    <div class=\"col-lg-9 col-md-8\">A108 Adam Street, New York, NY 535022</div>");
	    out.append("                  </div>");
	    out.append("");
	    out.append("                  <div class=\"row\">");
	    out.append("                    <div class=\"col-lg-3 col-md-4 label\">Phone</div>");
	    out.append("                    <div class=\"col-lg-9 col-md-8\">(436) 486-3538 x29071</div>");
	    out.append("                  </div>");
	    out.append("");
	    out.append("                  <div class=\"row\">");
	    out.append("                    <div class=\"col-lg-3 col-md-4 label\">Email</div>");
	    out.append("                    <div class=\"col-lg-9 col-md-8\">k.anderson@example.com</div>");
	    out.append("                  </div>");
	    out.append("");
	    out.append("                </div>");
	    out.append("");
	    out.append("                <div class=\"tab-pane fade profile-edit pt-3\" id=\"profile-edit\">");
	    out.append("");
	    out.append("                  <!-- Profile Edit Form -->");
	    out.append("                  <form>");
	    out.append("                    <div class=\"row mb-3\">");
	    out.append("                      <label for=\"profileImage\" class=\"col-md-4 col-lg-3 col-form-label\">Profile Image</label>");
	    out.append("                      <div class=\"col-md-8 col-lg-9\">");
	    out.append("                        <img src=\"../img/profile-img.jpg\" alt=\"Profile\">");
	    out.append("                        <div class=\"pt-2\">");
	    out.append("                          <a href=\"#\" class=\"btn btn-primary btn-sm\" title=\"Upload new profile image\"><i class=\"bi bi-upload\"></i></a>");
	    out.append("                          <a href=\"#\" class=\"btn btn-danger btn-sm\" title=\"Remove my profile image\"><i class=\"bi bi-trash\"></i></a>");
	    out.append("                        </div>");
	    out.append("                      </div>");
	    out.append("                    </div>");
	    out.append("");
	    out.append("                    <div class=\"row mb-3\">");
	    out.append("                      <label for=\"fullName\" class=\"col-md-4 col-lg-3 col-form-label\">Full Name</label>");
	    out.append("                      <div class=\"col-md-8 col-lg-9\">");
	    out.append("                        <input name=\"fullName\" type=\"text\" class=\"form-control\" id=\"fullName\" value=\"Kevin Anderson\">");
	    out.append("                      </div>");
	    out.append("                    </div>");
	    out.append("");
	    out.append("                    <div class=\"row mb-3\">");
	    out.append("                      <label for=\"company\" class=\"col-md-4 col-lg-3 col-form-label\">Company</label>");
	    out.append("                      <div class=\"col-md-8 col-lg-9\">");
	    out.append("                        <input name=\"company\" type=\"text\" class=\"form-control\" id=\"company\" value=\"Lueilwitz, Wisoky and Leuschke\">");
	    out.append("                      </div>");
	    out.append("                    </div>");
	    out.append("");
	    out.append("                    <div class=\"row mb-3\">");
	    out.append("                      <label for=\"Job\" class=\"col-md-4 col-lg-3 col-form-label\">Job</label>");
	    out.append("                      <div class=\"col-md-8 col-lg-9\">");
	    out.append("                        <input name=\"job\" type=\"text\" class=\"form-control\" id=\"Job\" value=\"Web Designer\">");
	    out.append("                      </div>");
	    out.append("                    </div>");
	    out.append("");
	    out.append("                    <div class=\"row mb-3\">");
	    out.append("                      <label for=\"Country\" class=\"col-md-4 col-lg-3 col-form-label\">Country</label>");
	    out.append("                      <div class=\"col-md-8 col-lg-9\">");
	    out.append("                        <input name=\"country\" type=\"text\" class=\"form-control\" id=\"Country\" value=\"USA\">");
	    out.append("                      </div>");
	    out.append("                    </div>");
	    out.append("");
	    out.append("                    <div class=\"row mb-3\">");
	    out.append("                      <label for=\"Address\" class=\"col-md-4 col-lg-3 col-form-label\">Address</label>");
	    out.append("                      <div class=\"col-md-8 col-lg-9\">");
	    out.append("                        <input name=\"address\" type=\"text\" class=\"form-control\" id=\"Address\" value=\"A108 Adam Street, New York, NY 535022\">");
	    out.append("                      </div>");
	    out.append("                    </div>");
	    out.append("");
	    out.append("                    <div class=\"row mb-3\">");
	    out.append("                      <label for=\"Phone\" class=\"col-md-4 col-lg-3 col-form-label\">Phone</label>");
	    out.append("                      <div class=\"col-md-8 col-lg-9\">");
	    out.append("                        <input name=\"phone\" type=\"text\" class=\"form-control\" id=\"Phone\" value=\"(436) 486-3538 x29071\">");
	    out.append("                      </div>");
	    out.append("                    </div>");
	    out.append("");
	    out.append("                    <div class=\"row mb-3\">");
	    out.append("                      <label for=\"Email\" class=\"col-md-4 col-lg-3 col-form-label\">Email</label>");
	    out.append("                      <div class=\"col-md-8 col-lg-9\">");
	    out.append("                        <input name=\"email\" type=\"email\" class=\"form-control\" id=\"Email\" value=\"k.anderson@example.com\">");
	    out.append("                      </div>");
	    out.append("                    </div>");
	    out.append("");
	    out.append("                    <div class=\"row mb-3\">");
	    out.append("                      <label for=\"about\" class=\"col-md-4 col-lg-3 col-form-label\">About</label>");
	    out.append("                      <div class=\"col-md-8 col-lg-9\">");
	    out.append("                        <textarea name=\"about\" class=\"form-control tinymce-editor\" id=\"about\" style=\"height: 100px\">Sunt est soluta temporibus accusantium neque nam maiores cumque temporibus. Tempora libero non est unde veniam est qui dolor. Ut sunt iure rerum quae quisquam autem eveniet perspiciatis odit. Fuga sequi sed ea saepe at unde.</textarea>");
	    out.append("                      </div>");
	    out.append("                    </div>");
	    out.append("                    <div class=\"text-center\">");
	    out.append("                      <button type=\"submit\" class=\"btn btn-primary\">Save Changes</button>");
	    out.append("                    </div>");
	    out.append("                  </form><!-- End Profile Edit Form -->");
	    out.append("");
	    out.append("                </div>");
	    out.append("");
	    out.append("                <div class=\"tab-pane fade pt-3\" id=\"profile-settings\">");
	    out.append("");
	    out.append("                  <!-- Settings Form -->");
	    out.append("                  <form>");
	    out.append("");
	    out.append("                    <div class=\"row mb-3\">");
	    out.append("                      <label for=\"fullName\" class=\"col-md-4 col-lg-3 col-form-label\">Email Notifications</label>");
	    out.append("                      <div class=\"col-md-8 col-lg-9\">");
	    out.append("                        <div class=\"form-check\">");
	    out.append("                          <input class=\"form-check-input\" type=\"checkbox\" id=\"changesMade\" checked>");
	    out.append("                          <label class=\"form-check-label\" for=\"changesMade\">");
	    out.append("                            Changes made to your account");
	    out.append("                          </label>");
	    out.append("                        </div>");
	    out.append("                        <div class=\"form-check\">");
	    out.append("                          <input class=\"form-check-input\" type=\"checkbox\" id=\"newProducts\" checked>");
	    out.append("                          <label class=\"form-check-label\" for=\"newProducts\">");
	    out.append("                            Information on new products and services");
	    out.append("                          </label>");
	    out.append("                        </div>");
	    out.append("                        <div class=\"form-check\">");
	    out.append("                          <input class=\"form-check-input\" type=\"checkbox\" id=\"proOffers\">");
	    out.append("                          <label class=\"form-check-label\" for=\"proOffers\">");
	    out.append("                            Marketing and promo offers");
	    out.append("                          </label>");
	    out.append("                        </div>");
	    out.append("                        <div class=\"form-check\">");
	    out.append("                          <input class=\"form-check-input\" type=\"checkbox\" id=\"securityNotify\" checked disabled>");
	    out.append("                          <label class=\"form-check-label\" for=\"securityNotify\">");
	    out.append("                            Security alerts");
	    out.append("                          </label>");
	    out.append("                        </div>");
	    out.append("                      </div>");
	    out.append("                    </div>");
	    out.append("");
	    out.append("                    <div class=\"text-center\">");
	    out.append("                      <button type=\"submit\" class=\"btn btn-primary\">Save Changes</button>");
	    out.append("                    </div>");
	    out.append("                  </form><!-- End settings Form -->");
	    out.append("");
	    out.append("                </div>");
	    out.append("");
	    out.append("                <div class=\"tab-pane fade pt-3\" id=\"profile-change-password\">");
	    out.append("                  <!-- Change Password Form -->");
	    out.append("                  <form>");
	    out.append("");
	    out.append("                    <div class=\"row mb-3\">");
	    out.append("                      <label for=\"currentPassword\" class=\"col-md-4 col-lg-3 col-form-label\">Current Password</label>");
	    out.append("                      <div class=\"col-md-8 col-lg-9\">");
	    out.append("                        <input name=\"password\" type=\"password\" class=\"form-control\" id=\"currentPassword\">");
	    out.append("                      </div>");
	    out.append("                    </div>");
	    out.append("");
	    out.append("                    <div class=\"row mb-3\">");
	    out.append("                      <label for=\"newPassword\" class=\"col-md-4 col-lg-3 col-form-label\">New Password</label>");
	    out.append("                      <div class=\"col-md-8 col-lg-9\">");
	    out.append("                        <input name=\"newpassword\" type=\"password\" class=\"form-control\" id=\"newPassword\">");
	    out.append("                      </div>");
	    out.append("                    </div>");
	    out.append("");
	    out.append("                    <div class=\"row mb-3\">");
	    out.append("                      <label for=\"renewPassword\" class=\"col-md-4 col-lg-3 col-form-label\">Re-enter New Password</label>");
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
	    
	    RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
		footerDispatcher.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
