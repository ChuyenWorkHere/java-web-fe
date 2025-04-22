package servlet.admin.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/admin/signin")
public class SignInView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SignInView() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		out.append("<!DOCTYPE html>");
		out.append("<html lang=\"en\">");
		out.append("");
		out.append("<head>");
		out.append("  <meta charset=\"utf-8\">");
		out.append("  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
		out.append("");
		out.append("  <title>Pages / Login - NiceAdmin Bootstrap Template</title>");
		out.append("  <meta content=\"\" name=\"description\">");
		out.append("  <meta content=\"\" name=\"keywords\">");
		out.append("");
		out.append("  <!-- Favicons -->");
		out.append("  <link href=\"img/favicon.png\" rel=\"icon\">");
		out.append("  <link href=\"img/apple-touch-icon.png\" rel=\"apple-touch-icon\">");
		out.append("");
		out.append("  <!-- Google Fonts -->");
		out.append("  <link href=\"https://fonts.gstatic.com\" rel=\"preconnect\">");
		out.append("  <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i\" rel=\"stylesheet\">");
		out.append("");
		out.append("  <!-- Vendor CSS Files -->");
		out.append("  <link href=\"vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"vendor/bootstrap-icons/bootstrap-icons.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"vendor/boxicons/css/boxicons.min.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"vendor/quill/quill.snow.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"vendor/quill/quill.bubble.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"vendor/remixicon/remixicon.css\" rel=\"stylesheet\">");
		out.append("  <link href=\"vendor/simple-datatables/style.css\" rel=\"stylesheet\">");
		out.append("");
		out.append("  <!-- Template Main CSS File -->");
		out.append("  <link href=\"css/style.css\" rel=\"stylesheet\">");
		out.append("");
		out.append("</head>");
		out.append("");
		out.append("<body>");
		out.append("");
		out.append("  <main>");
		out.append("    <div class=\"container\">");
		out.append("");
		out.append("      <section class=\"section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4\">");
		out.append("        <div class=\"container\">");
		out.append("          <div class=\"row justify-content-center\">");
		out.append("            <div class=\"col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center\">");
		out.append("");
		out.append("              <div class=\"d-flex justify-content-center py-4\">");
		out.append("                <a href=\"index.html\" class=\"logo d-flex align-items-center w-auto\">");
		out.append("                  <img src=\"img/logo.png\" alt=\"\">");
		out.append("                  <span class=\"d-none d-lg-block\">LuxHome</span>");
		out.append("                </a>");
		out.append("              </div><!-- End Logo -->");
		out.append("");
		out.append("              <div class=\"card mb-3\">");
		out.append("");
		out.append("                <div class=\"card-body\">");
		out.append("");
		out.append("                  <div class=\"pt-4 pb-2\">");
		out.append("                    <h5 class=\"card-title text-center pb-0 fs-4\">Đăng Nhập</h5>");
		out.append("                  </div>");
		out.append("");
		out.append("                  <form class=\"row g-3 needs-validation\" novalidate>");
		out.append("");
		out.append("                    <div class=\"col-12\">");
		out.append("                      <label for=\"yourUsername\" class=\"form-label\">Tài Khoản</label>");
		out.append("                      ");
		out.append("                      <div class=\"input-group has-validation\">");
		out.append("                        <span class=\"input-group-text\"><i class=\"bi bi-person-fill fs-5\"></i></span><input type=\"text\" name=\"username\" class=\"form-control\" id=\"yourUsername\" required> ");
		out.append("                        <div class=\"invalid-feedback\">Please enter your username.</div>");
		out.append("                      </div>");
		out.append("                    </div>");
		out.append("");
		out.append("                    <div class=\"col-12\">");
		out.append("                      <label for=\"yourPassword\" class=\"form-label\">Mật Khẩu</label>");
		out.append("                      <div class=\"input-group has-validation\">");
		out.append("                        <span class=\"input-group-text\"><i class=\"bi bi-key-fill fs-5\"></i></span><input type=\"password\" name=\"password\" class=\"form-control\" id=\"yourPassword\" required>");
		out.append("                        <div class=\"invalid-feedback\">Please enter your password!</div>");
		out.append("                      </div>");
		out.append("                    </div>");
		out.append("");
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
		out.append("");
		out.append("                </div>");
		out.append("              </div>");
		out.append("");
		out.append("            </div>");
		out.append("          </div>");
		out.append("        </div>");
		out.append("");
		out.append("      </section>");
		out.append("");
		out.append("    </div>");
		out.append("  </main><!-- End #main -->");
		out.append("");
		out.append("  <a href=\"#\" class=\"back-to-top d-flex align-items-center justify-content-center\"><i class=\"bi bi-arrow-up-short\"></i></a>");
		out.append("");
		out.append("  <!-- Vendor JS Files -->");
		out.append("  <script src=\"vendor/apexcharts/apexcharts.min.js\"></script>");
		out.append("  <script src=\"vendor/bootstrap/js/bootstrap.bundle.min.js\"></script>");
		out.append("  <script src=\"vendor/chart.js/chart.umd.js\"></script>");
		out.append("  <script src=\"vendor/echarts/echarts.min.js\"></script>");
		out.append("  <script src=\"vendor/quill/quill.js\"></script>");
		out.append("  <script src=\"vendor/simple-datatables/simple-datatables.js\"></script>");
		out.append("  <script src=\"vendor/tinymce/tinymce.min.js\"></script>");
		out.append("  <script src=\"vendor/php-email-form/validate.js\"></script>");
		out.append("");
		out.append("  <!-- Template Main JS File -->");
		out.append("  <script src=\"js/main.js\"></script>");
		out.append("");
		out.append("</body>");
		out.append("");
		out.append("</html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
