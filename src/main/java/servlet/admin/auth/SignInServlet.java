package servlet.admin.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/public/signin")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SignInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				
				String errorMessage = "";
				
				if(request.getAttribute("errorMessage") != null) {
					errorMessage = request.getAttribute("errorMessage").toString();
					request.removeAttribute("errorMessage");
				}
				
				out.append("<!DOCTYPE html>");
				out.append("<html lang=\"en\">");
				
				out.append("<head>");
				out.append("  <meta charset=\"utf-8\">");
				out.append("  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
				
				out.append("  <title>Pages / Login - LuxHome</title>");
				out.append("  <meta content=\"\" name=\"description\">");
				out.append("  <meta content=\"\" name=\"keywords\">");
				
				out.append("  <!-- Favicons -->");
				out.append("  <link href=\"img/favicon.png\" rel=\"icon\">");
				out.append("  <link href=\"img/apple-touch-icon.png\" rel=\"apple-touch-icon\">");
				
				out.append("  <!-- Google Fonts -->");
				out.append("  <link href=\"https://fonts.gstatic.com\" rel=\"preconnect\">");
				out.append("  <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i\" rel=\"stylesheet\">");
				
				out.append("  <!-- Vendor CSS Files -->");
				out.append("  <link href=\"../admin/vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");
				out.append("  <link href=\"../admin/vendor/bootstrap-icons/bootstrap-icons.css\" rel=\"stylesheet\">");
				
				out.append("  <link href=\"../admin/vendor/quill/quill.snow.css\" rel=\"stylesheet\">");
				out.append("  <link href=\"../admin/vendor/quill/quill.bubble.css\" rel=\"stylesheet\">");
				out.append("  <link href=\"../admin/vendor/remixicon/remixicon.css\" rel=\"stylesheet\">");
				out.append("  <link href=\"../admin/vendor/simple-datatables/style.css\" rel=\"stylesheet\">");
				
				out.append("  <!-- Template Main CSS File -->");
				out.append("  <link href=\"../admin/css/style.css\" rel=\"stylesheet\">");
				
				out.append("</head>");
				
				out.append("<body>");
				
				out.append("  <main>");
				out.append("    <div class=\"container\">");
				
				out.append("      <section class=\"section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4\">");
				out.append("        <div class=\"container\">");
				out.append("          <div class=\"row justify-content-center\">");
				out.append("            <div class=\"col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center\">");
				
				out.append("              <div class=\"d-flex justify-content-center py-4\">");
				out.append("                <a href=\"index.html\" class=\"logo d-flex align-items-center w-auto\">");
				out.append("                  <img src=\"../admin/img/logo.png\" alt=\"\">");
				out.append("                  <span class=\"d-none d-lg-block\">LuxHome</span>");
				out.append("                </a>");
				out.append("              </div><!-- End Logo -->");
				
				out.append("              <div class=\"card mb-3\">");
				
				out.append("                <div class=\"card-body\">");
				
				out.append("                  <div class=\"pt-4 pb-2\">");
				out.append("                    <h5 class=\"card-title text-center pb-0 fs-4\">Đăng Nhập</h5>");
				out.append("                  </div>");
				
				out.append("                  <form id = \"loginForm\" action= \"/Furniture/public/signin\" method=\"POST\" class=\"row g-3 needs-validation\" novalidate>");
				out.append("					<p id =\"loginMessage\">"+errorMessage+"</p>");
				out.append("                    <div class=\"col-12\">");
				out.append("                      <label for=\"yourUsername\" class=\"form-label\">Tài Khoản</label>");
				out.append("                      ");
				out.append("                      <div class=\"input-group has-validation\">");
				out.append("                        <span class=\"input-group-text\"><i class=\"bi bi-person-fill fs-5\"></i></span><input id=\"email\" type=\"text\" name=\"username\" class=\"form-control\" required> ");
				out.append("                        <div class=\"invalid-feedback\">Please enter your username.</div>");
				out.append("                      </div>");
				out.append("                    </div>");
				
				out.append("                    <div class=\"col-12\">");
				out.append("                      <label for=\"yourPassword\" class=\"form-label\">Mật Khẩu</label>");
				out.append("                      <div class=\"input-group has-validation\">");
				out.append("                        <span class=\"input-group-text\"><i class=\"bi bi-key-fill fs-5\"></i></span><input id=\"password\" type=\"password\" name=\"password\" class=\"form-control\" required>");
				out.append("                        <div class=\"invalid-feedback\">Please enter your password!</div>");
				out.append("                      </div>");
				out.append("                    </div>");
				
				out.append("                    <div class=\"col-12\">");
				out.append("                      <div class=\"form-check\">");
				out.append("                        <input class=\"form-check-input\" type=\"checkbox\" name=\"remember\" value=\"true\" id=\"rememberMe\">");
				out.append("                        <label class=\"form-check-label\" for=\"rememberMe\">Ghi nhớ đăng nhập</label>");
				out.append("                      </div>");
				out.append("                    </div>");
				out.append("                    <div class=\"col-12\">");
				out.append("                      <button class=\"btn btn-primary w-100\" type=\"submit\">Login</button>");
				out.append("                    </div>");
				out.append("                    <div class=\"col-12\">");
				out.append("                      <p class=\"small mb-0\">Bạn không có tài khoản? <a href=\"pages-register.html\">Đăng ký ngay</a></p>");
				out.append("                    </div>");
				out.append("                  </form>");
				
				out.append("                </div>");
				out.append("              </div>");
				
				out.append("            </div>");
				out.append("          </div>");
				out.append("        </div>");
				
				out.append("      </section>");
				
				out.append("    </div>");
				out.append("  </main><!-- End #main -->");
				
				out.append("  <a href=\"#\" class=\"back-to-top d-flex align-items-center justify-content-center\"><i class=\"bi bi-arrow-up-short\"></i></a>");
				
				out.append("  <!-- Vendor JS Files -->");
				out.append("  <script src=\"../admin/vendor/apexcharts/apexcharts.min.js\"></script>");
				out.append("  <script src=\"../admin/vendor/bootstrap/js/bootstrap.bundle.min.js\"></script>");
				out.append("  <script src=\"../admin/vendor/chart.js/chart.umd.js\"></script>");
				out.append("  <script src=\"../admin/vendor/echarts/echarts.min.js\"></script>");
				out.append("  <script src=\"../admin/vendor/simple-datatables/simple-datatables.js\"></script>");
				out.append("  <script src=\"../admin/vendor/tinymce/tinymce.min.js\"></script>");
				
				out.append("  <!-- Template Main JS File -->");
				out.append("  <script src=\"../admin/js/main.js\"></script>");
				out.append("  <script src=\"../admin/js/validAuth.js\"></script>");
				
				out.append("</body>");
				
				out.append("</html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
//		
//		if( !"".equalsIgnoreCase(username) && !"".equals(password)) {
//			System.out.println("1234");
//			response.sendRedirect("../admin/home-view");
//		} else {
//			request.setAttribute("errorMessage", "Tài khoản hoặc mật khẩu không hợp lệ");
//			doGet(request, response);
//		}
		
		String role = null;
		if("admin@gmail.com".equals(username) && "Admin123!".equals(password)) {
			role = "ADMIN";
		}else if("customer@gmail.com".equals(username) && "Customer123!".equals(password)) {
			role = "CUSTOMER";
		}
		
		if(role != null) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("role", role);
			if("ADMIN".equals(role)) {
				response.sendRedirect("../admin/home-view");
			}else {
				response.sendRedirect("../customer/home-view");
			}
		}else {
			request.setAttribute("errorMessage", "không có quyền truy cập");
			doGet(request, response);
		}
	
	}

}
