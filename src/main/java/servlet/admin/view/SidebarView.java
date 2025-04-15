package servlet.admin.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/sidebar-view")
public class SidebarView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SidebarView() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		out.append("  <!-- ======= Sidebar ======= -->");
		out.append("  <aside id=\"sidebar\" class=\"sidebar\">");

		out.append("    <ul class=\"sidebar-nav\" id=\"sidebar-nav\">");

		out.append("      <li class=\"nav-item\">");
		out.append("        <a class=\"nav-link \" href=\"/Furniture/admin/home-view\">");
		out.append("          <i class=\"bi bi-grid\"></i>");
		out.append("          <span>Trang chủ</span>");
		out.append("        </a>");
		out.append("      </li><!-- End Dashboard Nav -->");

		out.append("      <li class=\"nav-item\">");
		out.append("        <a class=\"nav-link collapsed\" href=\"/Furniture/admin/categories-view\">");
		out.append("            <i class=\"bi bi-folder\"></i>    ");
		out.append("          <span>Danh mục</span>");
		out.append("        </a>   ");
		out.append("      </li><!-- End Components Nav -->");

		out.append("      <li class=\"nav-item\">");
		out.append(
				"        <a class=\"nav-link collapsed\" data-bs-target=\"#product-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.append(
				"            <i class=\"bi bi-box-seam\"></i><span>Sản phẩm</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.append("        </a>");
		out.append("        <ul id=\"product-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">");
		out.append("          <li>");
		out.append("            <a href=\"/Furniture/admin/products-view\">");
		out.append("                <i class=\"bi bi-list-task\"></i><span>Danh Sách</span>");
		out.append("            </a>");
		out.append("          </li>");
		out.append("          <li>");
		out.append("            <a href=\"add-product.html\">");
		out.append("                <i class=\"bi bi-plus-circle-fill\"></i><span>Thêm mới</span>");
		out.append("            </a>");
		out.append("          </li>");
		out.append("          <li>");
		out.append("            <a href=\"icons-remix.html\">");
		out.append("                <i class=\"bi bi-bar-chart\"></i><span>Thống kê</span>");
		out.append("            </a>");
		out.append("          </li>");
		out.append("        </ul>");
		out.append("      </li><!-- End Icons Nav -->");

		out.append("      <li class=\"nav-item\">");
		out.append("        <a class=\"nav-link collapsed\" href=\"/Furniture/admin/orders-view\">");
		out.append("          <i class=\"bi bi-cart\"></i><span>Đơn hàng</span>");
		out.append("        </a>");
		out.append("      </li><!-- End Tables Nav -->");

		out.append("      <li class=\"nav-item\">");
		out.append("        <a class=\"nav-link collapsed\" href=\"/Furniture/admin/accounts-view\">");
		out.append("          <i class=\"bi bi-people\"></i><span>Tài khoản</span>");
		out.append("        </a>");
		out.append("      </li><!-- End Charts Nav -->");

		out.append("      <li class=\"nav-item\">");
		out.append(
				"        <a class=\"nav-link collapsed\" data-bs-target=\"#icons-nav\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.append(
				"          <i class=\"bi bi-star\"></i><span>Đánh giá</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.append("        </a>");
		out.append("        <ul id=\"icons-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">");
		out.append("          <li>");
		out.append("            <a href=\"icons-bootstrap.html\">");
		out.append("              <i class=\"bi bi-circle\"></i><span>Bootstrap Icons</span>");
		out.append("            </a>");
		out.append("          </li>");
		out.append("          <li>");
		out.append("            <a href=\"icons-remix.html\">");
		out.append("              <i class=\"bi bi-circle\"></i><span>Remix Icons</span>");
		out.append("            </a>");
		out.append("          </li>");
		out.append("          <li>");
		out.append("            <a href=\"icons-boxicons.html\">");
		out.append("              <i class=\"bi bi-circle\"></i><span>Boxicons</span>");
		out.append("            </a>");
		out.append("          </li>");
		out.append("        </ul>");
		out.append("      </li><!-- End Icons Nav -->");

		out.append("      <li class=\"nav-item\">");
		out.append(
				"        <a class=\"nav-link collapsed\" data-bs-target=\"#report\" data-bs-toggle=\"collapse\" href=\"#\">");
		out.append(
				"            <i class=\"bi bi-bar-chart\"></i><span>Thống kê</span><i class=\"bi bi-chevron-down ms-auto\"></i>");
		out.append("        </a>");
		out.append("        <ul id=\"report\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">");
		out.append("          <li>");
		out.append("            <a href=\"/Furniture/admin/products-report\">");
		out.append("                <i class=\"bi bi-box-seam\"></i><span>Sản phẩm</span>");
		out.append("            </a>");
		out.append("          </li>");
		out.append("          <li>");
		out.append("            <a href=\"/Furniture/admin/orders-report\">");
		out.append("                <i class=\"bi bi-cart\"></i><span>Đơn hàng</span>");
		out.append("            </a>");
		out.append("          </li>");
		out.append("        </ul>");
		out.append("      </li><!-- End Icons Nav -->");

		out.append("      <li class=\"nav-heading\">Pages</li>");

		out.append("      <li class=\"nav-item\">");
		out.append("        <a class=\"nav-link collapsed\" href=\"users-profile.html\">");
		out.append("          <i class=\"bi bi-person\"></i>");
		out.append("          <span>Hồ sơ</span>");
		out.append("        </a>");
		out.append("      </li><!-- End Profile Page Nav -->");

		out.append("      <li class=\"nav-item\">");
		out.append("        <a class=\"nav-link collapsed\" href=\"pages-faq.html\">");
		out.append("          <i class=\"bi bi-question-circle\"></i>");
		out.append("          <span>F.A.Q</span>");
		out.append("        </a>");
		out.append("      </li><!-- End F.A.Q Page Nav -->");

		out.append("      <li class=\"nav-item\">");
		out.append("        <a class=\"nav-link collapsed\" href=\"pages-contact.html\">");
		out.append("          <i class=\"bi bi-envelope\"></i>");
		out.append("          <span>Contact</span>");
		out.append("        </a>");
		out.append("      </li><!-- End Contact Page Nav -->");

		out.append("      <li class=\"nav-item\">");
		out.append("        <a class=\"nav-link collapsed\" href=\"pages-register.html\">");
		out.append("          <i class=\"bi bi-card-list\"></i>");
		out.append("          <span>Đăng Ký</span>");
		out.append("        </a>");
		out.append("      </li><!-- End Register Page Nav -->");

		out.append("      <li class=\"nav-item\">");
		out.append("        <a class=\"nav-link collapsed\" href=\"pages-login.html\">");
		out.append("          <i class=\"bi bi-box-arrow-in-right\"></i>");
		out.append("          <span>Đăng Nhập</span>");
		out.append("        </a>");
		out.append("      </li><!-- End Login Page Nav -->");

		out.append("      <li class=\"nav-item\">");
		out.append("        <a class=\"nav-link collapsed\" href=\"pages-error-404.html\">");
		out.append("          <i class=\"bi bi-dash-circle\"></i>");
		out.append("          <span>Error 404</span>");
		out.append("        </a>");
		out.append("      </li><!-- End Error 404 Page Nav -->");

		out.append("      <li class=\"nav-item\">");
		out.append("        <a class=\"nav-link collapsed\" href=\"pages-blank.html\">");
		out.append("          <i class=\"bi bi-file-earmark\"></i>");
		out.append("          <span>Blank</span>");
		out.append("        </a>");
		out.append("      </li><!-- End Blank Page Nav -->");

		out.append("    </ul>");

		out.append("  </aside><!-- End Sidebar-->");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
