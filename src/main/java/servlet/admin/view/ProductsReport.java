package servlet.admin.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/products-report")
public class ProductsReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductsReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		request.setAttribute("view", "report-product");

		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
		headerDispatcher.include(request, response);

		out.append("<main id=\"main\" class=\"main\">");
		
		out.append("    <div class=\"pagetitle d-flex justify-content-between\">");
		out.append("      <h1>Thống kê sản phẩm</h1>");
		out.append("      <nav class=\"d-flex align-items-center\" >");
		out.append("        <ol class=\"breadcrumb  mb-0\">");
		out.append(
				"          <li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-fill\"></i></a></li>");
		out.append("          <li class=\"breadcrumb-item active\">Thống kê</li>");
		out.append("        </ol>");
		out.append("      </nav>");
		out.append("    </div><!-- End Page Title -->");
		
		out.append("    <section class=\"section dashboard\">");
		out.append("      <div class=\"row\">");
		
		out.append("        <!-- Left side columns -->");
		out.append("        <div class=\"col-lg-8\">");
		out.append("          <div class=\"row\">");
		out.append("            <!-- Reports -->");
		out.append("            <div class=\"col-12\">");
		out.append("              <div class=\"card\">");
		
		out.append("                <div class=\"filter\">");
		out.append(
				"                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.append("                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.append("                    <li class=\"dropdown-header text-start\">");
		out.append("                      <h6>Lọc</h6>");
		out.append("                    </li>");
		
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Hôm nay</a></li>");
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Tháng này</a></li>");
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Năm nay</a></li>");
		out.append("                  </ul>");
		out.append("                </div>");
		
		out.append("                <div class=\"card-body \">");
		out.append("                  <h5 class=\"card-title\">");
		out.append(
				"                    <i class=\"fas fa-chart-bar me-2\"></i> Số lượng nhập & bán (6 tháng gần nhất)");
		out.append("                  </h5>");
		out.append("                  <canvas id=\"barChart\"></canvas> ");
		out.append("<script src=\"https://cdn.jsdelivr.net/npm/chart.js\"></script>");
		out.append(
				"                  <script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js\"></script>");
		out.append(
				"      <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js\"></script>");
		out.append("<script>");
		out.append("window.addEventListener('DOMContentLoaded', function () {");
		out.append("  const barChartData = {");
		out.append("    labels: ['January', 'February', 'March', 'April', 'May', 'June'],");
		out.append("    datasets: [");
		out.append("      {");
		out.append("        label: 'Products Received',");
		out.append("        backgroundColor: 'rgba(75, 192, 192, 0.2)',");
		out.append("        borderColor: 'rgba(75, 192, 192, 1)',");
		out.append("        borderWidth: 1,");
		out.append("        data: [65, 59, 80, 81, 56, 55]");
		out.append("      },");
		out.append("      {");
		out.append("        label: 'Products Sold',");
		out.append("        backgroundColor: 'rgba(153, 102, 255, 0.2)',");
		out.append("        borderColor: 'rgba(153, 102, 255, 1)',");
		out.append("        borderWidth: 1,");
		out.append("        data: [28, 48, 40, 19, 86, 27]");
		out.append("      }");
		out.append("    ]");
		out.append("  };");
		out.append("  const barCtx = document.getElementById('barChart').getContext('2d');");
		out.append("  new Chart(barCtx, {");
		out.append("    type: 'bar',");
		out.append("    data: barChartData,");
		out.append("    options: {");
		out.append("      responsive: true,");
		out.append("      scales: {");
		out.append("        x: { beginAtZero: true },");
		out.append("        y: { beginAtZero: true }");
		out.append("      }");
		out.append("    }");
		out.append("  });");
		out.append("});");
		out.append("</script>");

		out.append("                  <!-- End Line CHart -->");
		out.append("    ");
		out.append("                </div>");
		
		out.append("              </div>");
		out.append("            </div><!-- End Reports -->");
		
		out.append("            <!-- Top Selling -->");
		out.append("            <div class=\"col-12\">");
		out.append("              <div class=\"card top-selling overflow-auto\">");
		
		out.append("                <div class=\"filter\">");
		out.append(
				"                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.append("                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.append("                    <li class=\"dropdown-header text-start\">");
		out.append("                      <h6>Lọc</h6>");
		out.append("                    </li>");
		
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Hôm nay</a></li>");
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Tháng này</a></li>");
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Năm nay</a></li>");
		out.append("                  </ul>");
		out.append("                </div>");
		
		out.append("              </div>");
		out.append("            </div><!-- End Top Selling -->");
		
		out.append("          </div>");
		out.append("        </div><!-- End Left side columns -->");
		
		out.append("        <!-- Right side columns -->");
		out.append("        <div class=\"col-lg-4\">");
		
		out.append("          <div class=\"row\">");
		out.append("            <!-- All Orders Card -->");
		out.append("            <div class=\"col-xxl-12 col-sm-6 col-lg-12 \">");
		out.append("              <div class=\"card info-card all-orders-card mb-3\">");
		
		out.append("                <div class=\"filter\">");
		out.append(
				"                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.append("                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.append("                    <li class=\"dropdown-header text-start\">");
		out.append("                      <h6>Lọc</h6>");
		out.append("                    </li>");
		
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Hôm nay</a></li>");
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Tháng này</a></li>");
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Năm nay</a></li>");
		out.append("                  </ul>");
		out.append("                </div>");
		
		out.append("                <div class=\"card-body px-3 py-1\">");
		out.append("                  <h5 class=\"card-title px-2 py-2 m-0\">Nhập kho <span>| Hôm nay</span></h5>");
		
		out.append("                  <div class=\"d-flex align-items-center\">");
		out.append(
				"                    <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
		out.append("                      <i class=\"bi-file-earmark-text\"></i>");
		out.append("                    </div>");
		out.append("                    <div class=\"ps-3\">");
		out.append("                      <h6>145</h6>");
		out.append(
				"                      <span class=\"text-success small pt-1 fw-bold\">12%</span> <span class=\"text-muted small pt-2 ps-1\">Tăng</span>");
		
		out.append("                    </div>");
		out.append("                  </div>");
		out.append("                </div>");
		
		out.append("              </div>");
		out.append("            </div><!-- End All Orders Card -->");
		
		out.append("            <!-- Order Success Card -->");
		out.append("            <div class=\"col-xxl-12 col-sm-6 col-lg-12\">");
		out.append("              <div class=\"card info-card order-success-card mb-3\">");
		
		out.append("                <div class=\"filter\">");
		out.append(
				"                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.append("                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.append("                    <li class=\"dropdown-header text-start\">");
		out.append("                      <h6>Lọc</h6>");
		out.append("                    </li>");
		
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Hôm nay</a></li>");
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Tháng này</a></li>");
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Năm nay</a></li>");
		out.append("                  </ul>");
		out.append("                </div>");
		
		out.append("                <div class=\"card-body px-3 py-1\">");
		out.append("                  <h5 class=\"card-title px-2 py-2 m-0\">Bán ra <span>| Hôm nay</span></h5>");
		
		out.append("                  <div class=\"d-flex align-items-center\">");
		out.append(
				"                    <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
		out.append("                      <i class=\"bi-check-circle-fill\"></i>");
		out.append("                    </div>");
		out.append("                    <div class=\"ps-3\">");
		out.append("                      <h6>145</h6>");
		out.append(
				"                      <span class=\"text-success small pt-1 fw-bold\">12%</span> <span class=\"text-muted small pt-2 ps-1\">Tăng</span>");
		
		out.append("                    </div>");
		out.append("                  </div>");
		out.append("                </div>");
		
		out.append("              </div>");
		out.append("            </div><!-- End Order Success Card -->");
		out.append("            ");
		out.append("            <!-- Order Pending Card -->");
		out.append("            <div class=\"col-xxl-12 col-sm-6 col-lg-12\">");
		out.append("              <div class=\"card info-card order-pending-card mb-3\">");
		
		out.append("                <div class=\"filter\">");
		out.append(
				"                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.append("                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.append("                    <li class=\"dropdown-header text-start\">");
		out.append("                      <h6>Lọc</h6>");
		out.append("                    </li>");
		
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Hôm nay</a></li>");
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Tháng này</a></li>");
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Năm nay</a></li>");
		out.append("                  </ul>");
		out.append("                </div>");
		
		out.append("                <div class=\"card-body px-3 py-1\">");
		out.append("                  <h5 class=\"card-title px-2 py-2 m-0\">Tồn kho <span>| Hôm nay</span></h5>");
		
		out.append("                  <div class=\"d-flex align-items-center\">");
		out.append(
				"                    <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
		out.append("                      <i class=\"bi-hourglass-split\"></i>");
		out.append("                    </div>");
		out.append("                    <div class=\"ps-3\">");
		out.append("                      <h6>145</h6>");
		out.append(
				"                      <span class=\"text-success small pt-1 fw-bold\">12%</span> <span class=\"text-muted small pt-2 ps-1\">Tăng</span>");
		
		out.append("                    </div>");
		out.append("                  </div>");
		out.append("                </div>");
		
		out.append("              </div>");
		out.append("            </div><!-- End Order Pending Card -->");
		
		out.append("            <!-- Order Failed Card -->");
		out.append("            <div class=\"col-xxl-12 col-sm-6 col-lg-12\">");
		out.append("              <div class=\"card info-card order-failed-card mb-3\">");
		
		out.append("                <div class=\"filter\">");
		out.append(
				"                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.append("                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.append("                    <li class=\"dropdown-header text-start\">");
		out.append("                      <h6>Lọc</h6>");
		out.append("                    </li>");
		
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Hôm nay</a></li>");
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Tháng này</a></li>");
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Năm nay</a></li>");
		out.append("                  </ul>");
		out.append("                </div>");
		
		out.append("                <div class=\"card-body px-3 py-1\">");
		out.append("                  <h5 class=\"card-title px-2 py-2 m-0\">Doanh thu <span>| Hôm nay</span></h5>");
		
		out.append("                  <div class=\"d-flex align-items-center\">");
		out.append(
				"                    <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
		out.append("                      <i class=\"bi-cash-stack\"></i>");
		out.append("                    </div>");
		out.append("                    <div class=\"ps-3\">");
		out.append("                      <h6>145</h6>");
		out.append(
				"                      <span class=\"text-success small pt-1 fw-bold\">12%</span> <span class=\"text-muted small pt-2 ps-1\">Tăng</span>");
		
		out.append("                    </div>");
		out.append("                  </div>");
		out.append("                </div>");
		
		out.append("              </div>");
		out.append("            </div><!-- End Order Failed Card -->");
		out.append("          </div>");
		out.append("        </div><!-- End Right side columns -->");
		
		out.append("      </div>");
		out.append("    </section>");
		
		out.append("  </main><!-- End #main -->");

		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
		footerDispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
