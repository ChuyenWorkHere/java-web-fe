package servlet.admin.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/orders-report")
public class OrdersReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrdersReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		request.setAttribute("view", "report-order");

		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
		headerDispatcher.include(request, response);

		out.append("<main id=\"main\" class=\"main\">");

		out.append("    <div class=\"pagetitle d-flex justify-content-between\">");
		out.append("      <h1>Thống kê đơn hàng</h1>");
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
		out.append("                  <h5 class=\"card-title\">Line Chart</h5>");

		out.append("                  <!-- Line Chart -->");
		out.append("                  <canvas id=\"lineChart\" style=\"max-height: 400px; height: 100px;\" ></canvas>");
		out.append("<script src=\"https://cdn.jsdelivr.net/npm/chart.js\"></script>");
		out.append("                  <script>");
		out.append("                    document.addEventListener(\"DOMContentLoaded\", () => {");
		out.append("                      new Chart(document.querySelector('#lineChart'), {");
		out.append("                        type: 'line',");
		out.append("                        data: {");
		out.append(
				"                          labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],");
		out.append("                          datasets: [{");
		out.append("                            label: 'Line Chart',");
		out.append("                            data: [65, 59, 80, 81, 56, 55, 40],");
		out.append("                            fill: false,");
		out.append("                            borderColor: 'rgb(75, 192, 192)',");
		out.append("                            tension: 0.1");
		out.append("                          }]");
		out.append("                        },");
		out.append("                        options: {");
		out.append("                          scales: {");
		out.append("                            y: {");
		out.append("                              beginAtZero: true");
		out.append("                            }");
		out.append("                          }");
		out.append("                        }");
		out.append("                      });");
		out.append("                    });");
		out.append("                  </script>");
		out.append("                  <!-- End Line CHart -->");

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
		out.append("                  <h5 class=\"card-title px-2 py-2 m-0\">Tất Cả <span>| Hôm nay</span></h5>");

		out.append("                  <div class=\"d-flex align-items-center\">");
		out.append(
				"                    <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
		out.append("                      <i class=\"bi bi-receipt\"></i>");
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
		out.append("                  <h5 class=\"card-title px-2 py-2 m-0\">Thành Công <span>| Hôm nay</span></h5>");

		out.append("                  <div class=\"d-flex align-items-center\">");
		out.append(
				"                    <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
		out.append("                      <i class=\"bi bi-check-circle-fill\"></i>");
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
		out.append("                  <h5 class=\"card-title px-2 py-2 m-0\">Đang Chờ <span>| Hôm nay</span></h5>");

		out.append("                  <div class=\"d-flex align-items-center\">");
		out.append(
				"                    <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
		out.append("                      <i class=\"bi bi-hourglass-split\"></i>");
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
		out.append("                  <h5 class=\"card-title px-2 py-2 m-0\">Bị Hủy <span>| Hôm nay</span></h5>");

		out.append("                  <div class=\"d-flex align-items-center\">");
		out.append(
				"                    <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
		out.append("                      <i class=\"bi bi-x-circle-fill\"></i>");
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
		out.append("      <div class=\"row\">");
		out.append("        <!-- Recent Sales -->");
		out.append("        <div class=\"col-lg-8\">");
		out.append("          <div class=\"card recent-sales overflow-auto\">");

		out.append("            <div class=\"filter\">");
		out.append(
				"              <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.append("              <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.append("                <li class=\"dropdown-header text-start\">");
		out.append("                  <h6>Filter</h6>");
		out.append("                </li>");

		out.append("                <li><a class=\"dropdown-item\" href=\"#\">Hôm nay</a></li>");
		out.append("                <li><a class=\"dropdown-item\" href=\"#\">Tháng này</a></li>");
		out.append("                <li><a class=\"dropdown-item\" href=\"#\">Năm nay</a></li>");
		out.append("              </ul>");
		out.append("            </div>");

		out.append("            <div class=\"card-body\">");
		out.append("              <h5 class=\"card-title\">Thông báo mới <span>| Hôm nay</span></h5>");
		out.append("              <div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">");
		out.append("                <i class=\"bi bi-check-circle me-1\"></i>");
		out.append("                Đơn hàng #2eca6a được giao thành công.");
		out.append(
				"                <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>");
		out.append("              </div>");

		out.append("              <div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">");
		out.append("                <i class=\"bi bi-exclamation-octagon me-1\"></i>");
		out.append("                Đơn hàng #2eca6a đã bị hủy.");
		out.append(
				"                <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>");
		out.append("              </div>");
		out.append("              <div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">");
		out.append("                <i class=\"bi bi-exclamation-triangle me-1\"></i>");
		out.append("                Đơn hàng #12345 đang chờ xác nhận.");
		out.append(
				"                <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>");
		out.append("              </div>");
		out.append("              <div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">");
		out.append("                <i class=\"bi bi-check-circle me-1\"></i>");
		out.append("                Đơn hàng #2eca6a được giao thành công.");
		out.append(
				"                <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>");
		out.append("              </div>");
		out.append("              <div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">");
		out.append("                <i class=\"bi bi-check-circle me-1\"></i>");
		out.append("                Đơn hàng #2eca6a được giao thành công.");
		out.append(
				"                <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>");
		out.append("              </div>");
		out.append("              <!-- Pagination with icons -->");
		out.append("              <nav aria-label=\"Page navigation example\" class=\"d-flex justify-content-end\">");
		out.append("                <ul class=\"pagination\">");
		out.append("                  <li class=\"page-item\">");
		out.append("                    <a class=\"page-link\" href=\"#\" aria-label=\"Previous\">");
		out.append("                      <span aria-hidden=\"true\">&laquo;</span>");
		out.append("                    </a>");
		out.append("                  </li>");
		out.append("                  <li class=\"page-item\"><a class=\"page-link\" href=\"#\">1</a></li>");
		out.append("                  <li class=\"page-item\"><a class=\"page-link\" href=\"#\">2</a></li>");
		out.append("                  <li class=\"page-item\"><a class=\"page-link\" href=\"#\">3</a></li>");
		out.append("                  <li class=\"page-item\">");
		out.append("                    <a class=\"page-link\" href=\"#\" aria-label=\"Next\">");
		out.append("                      <span aria-hidden=\"true\">&raquo;</span>");
		out.append("                    </a>");
		out.append("                  </li>");
		out.append("                </ul>");
		out.append("              </nav><!-- End Pagination with icons -->");
		out.append("            </div>");

		out.append("          </div>");
		out.append("        </div><!-- End Recent Sales -->");

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
