package servlet.admin.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/footer-view")
public class FooterView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public FooterView() {
        super();    
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.append("  <!-- ======= Footer ======= -->");
		out.append("  <footer id=\"footer\" class=\"footer\">");
		out.append("    <div class=\"copyright\">");
		out.append("      &copy; Copyright <strong><span>NiceAdmin</span></strong>. All Rights Reserved");
		out.append("    </div>");
		out.append("    <div class=\"credits\">");
		out.append("      <!-- All the links in the footer should remain intact. -->");
		out.append("      <!-- You can delete the links only if you purchased the pro version. -->");
		out.append("      <!-- Licensing information: https://bootstrapmade.com/license/ -->");
		out.append(
				"      <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->");
		out.append("      Designed by <a href=\"https://bootstrapmade.com/\">BootstrapMade</a>");
		out.append("    </div>");
		out.append("  </footer><!-- End Footer -->");

		out.append(
				"  <a href=\"#\" class=\"back-to-top d-flex align-items-center justify-content-center\"><i class=\"bi bi-arrow-up-short\"></i></a>");

		out.append("  <!-- Vendor JS Files -->");
		out.append("  <script src=\"../admin/vendor/apexcharts/apexcharts.min.js\"></script>");
		out.append("  <script src=\"../admin/vendor/bootstrap/js/bootstrap.bundle.min.js\"></script>");
//		out.append("  <script src=\"../admin/vendor/chart.js/chart.umd.js\"></script>");
		out.append("  <script src=\"../admin/vendor/echarts/echarts.min.js\"></script>");
//		out.append("  <script src=\"../admin/vendor/quill/quill.js\"></script>");
//		out.append("  <script src=\"../admin/vendor/simple-datatables/simple-datatables.js\"></script>");
		out.append("  <script src=\"../admin/vendor/tinymce/tinymce.min.js\"></script>");
//		out.append("  <script src=\"../admin/vendor/php-email-form/validate.js\"></script>");

		out.append("  <!-- Template Main JS File -->");
		out.append("  <script src=\"../admin/js/main.js\"></script>");
		
		
		out.append("</body>");

		out.append("</html>");

		out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
