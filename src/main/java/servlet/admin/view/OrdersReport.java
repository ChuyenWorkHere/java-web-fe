package servlet.admin.view;

import servlet.dao.OrdersReportDAO;
import servlet.dao.impl.OrdersReportDAOImpl;
import servlet.models.Order;
import servlet.response.OrderResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/orders-report")
public class OrdersReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OrdersReportDAO ordersReportDAO;

	public OrdersReport() {
		super();
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
		out.append("        <div class=\"col-lg-12\">");
		out.append("          <div class=\"row\">");

		out.append("            <!-- Reports -->");
		out.append("            <div class=\"col-12\">");
		out.append("              <div class=\"card\">");

		orderLineChart(request, response);

		out.append("              </div>");
		out.append("            </div><!-- End Reports -->");
		out.append("          </div>");
		out.append("        </div><!-- End Left side columns -->");

//		out.append("        <!-- Right side columns -->");
//		out.append("        <div class=\"col-lg-4\">");

//		out.append("          <div class=\"row\">");
//		out.append("            <!-- All Orders Card -->");
//		out.append("            <div class=\"col-xxl-12 col-sm-6 col-lg-12 \">");
//		out.append("              <div class=\"card info-card all-orders-card mb-3\">");
//
//		out.append("                <div class=\"filter\">");
//		out.append(
//				"                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
//		out.append("                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
//		out.append("                    <li class=\"dropdown-header text-start\">");
//		out.append("                      <h6>Lọc</h6>");
//		out.append("                    </li>");
//
//		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Hôm nay</a></li>");
//		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Tháng này</a></li>");
//		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Năm nay</a></li>");
//		out.append("                  </ul>");
//		out.append("                </div>");
//
//		out.append("                <div class=\"card-body px-3 py-1\">");
//		out.append("                  <h5 class=\"card-title px-2 py-2 m-0\">Tất Cả <span>| Hôm nay</span></h5>");
//
//		out.append("                  <div class=\"d-flex align-items-center\">");
//		out.append(
//				"                    <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
//		out.append("                      <i class=\"bi bi-receipt\"></i>");
//		out.append("                    </div>");
//		out.append("                    <div class=\"ps-3\">");
//		out.append("                      <h6>145</h6>");
//		out.append(
//				"                      <span class=\"text-success small pt-1 fw-bold\">12%</span> <span class=\"text-muted small pt-2 ps-1\">Tăng</span>");
//
//		out.append("                    </div>");
//		out.append("                  </div>");
//		out.append("                </div>");
//
//		out.append("              </div>");
//		out.append("            </div><!-- End All Orders Card -->");
//
//		out.append("            <!-- Order Success Card -->");
//		out.append("            <div class=\"col-xxl-12 col-sm-6 col-lg-12\">");
//		out.append("              <div class=\"card info-card order-success-card mb-3\">");
//
//		out.append("                <div class=\"filter\">");
//		out.append(
//				"                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
//		out.append("                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
//		out.append("                    <li class=\"dropdown-header text-start\">");
//		out.append("                      <h6>Lọc</h6>");
//		out.append("                    </li>");
//
//		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Hôm nay</a></li>");
//		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Tháng này</a></li>");
//		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Năm nay</a></li>");
//		out.append("                  </ul>");
//		out.append("                </div>");
//
//		out.append("                <div class=\"card-body px-3 py-1\">");
//		out.append("                  <h5 class=\"card-title px-2 py-2 m-0\">Thành Công <span>| Hôm nay</span></h5>");
//
//		out.append("                  <div class=\"d-flex align-items-center\">");
//		out.append(
//				"                    <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
//		out.append("                      <i class=\"bi bi-check-circle-fill\"></i>");
//		out.append("                    </div>");
//		out.append("                    <div class=\"ps-3\">");
//		out.append("                      <h6>145</h6>");
//		out.append(
//				"                      <span class=\"text-success small pt-1 fw-bold\">12%</span> <span class=\"text-muted small pt-2 ps-1\">Tăng</span>");
//
//		out.append("                    </div>");
//		out.append("                  </div>");
//		out.append("                </div>");
//
//		out.append("              </div>");
//		out.append("            </div><!-- End Order Success Card -->");
//		out.append("            ");
//		out.append("            <!-- Order Pending Card -->");
//		out.append("            <div class=\"col-xxl-12 col-sm-6 col-lg-12\">");
//		out.append("              <div class=\"card info-card order-pending-card mb-3\">");
//
//		out.append("                <div class=\"filter\">");
//		out.append(
//				"                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
//		out.append("                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
//		out.append("                    <li class=\"dropdown-header text-start\">");
//		out.append("                      <h6>Lọc</h6>");
//		out.append("                    </li>");
//
//		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Hôm nay</a></li>");
//		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Tháng này</a></li>");
//		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Năm nay</a></li>");
//		out.append("                  </ul>");
//		out.append("                </div>");
//
//		out.append("                <div class=\"card-body px-3 py-1\">");
//		out.append("                  <h5 class=\"card-title px-2 py-2 m-0\">Đang Chờ <span>| Hôm nay</span></h5>");
//
//		out.append("                  <div class=\"d-flex align-items-center\">");
//		out.append(
//				"                    <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
//		out.append("                      <i class=\"bi bi-hourglass-split\"></i>");
//		out.append("                    </div>");
//		out.append("                    <div class=\"ps-3\">");
//		out.append("                      <h6>145</h6>");
//		out.append(
//				"                      <span class=\"text-success small pt-1 fw-bold\">12%</span> <span class=\"text-muted small pt-2 ps-1\">Tăng</span>");
//
//		out.append("                    </div>");
//		out.append("                  </div>");
//		out.append("                </div>");
//
//		out.append("              </div>");
//		out.append("            </div><!-- End Order Pending Card -->");
//
//		out.append("            <!-- Order Failed Card -->");
//		out.append("            <div class=\"col-xxl-12 col-sm-6 col-lg-12\">");
//		out.append("              <div class=\"card info-card order-failed-card mb-3\">");
//
//		out.append("                <div class=\"filter\">");
//		out.append(
//				"                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
//		out.append("                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
//		out.append("                    <li class=\"dropdown-header text-start\">");
//		out.append("                      <h6>Lọc</h6>");
//		out.append("                    </li>");
//
//		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Hôm nay</a></li>");
//		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Tháng này</a></li>");
//		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Năm nay</a></li>");
//		out.append("                  </ul>");
//		out.append("                </div>");
//
//		out.append("                <div class=\"card-body px-3 py-1\">");
//		out.append("                  <h5 class=\"card-title px-2 py-2 m-0\">Bị Hủy <span>| Hôm nay</span></h5>");
//
//		out.append("                  <div class=\"d-flex align-items-center\">");
//		out.append(
//				"                    <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
//		out.append("                      <i class=\"bi bi-x-circle-fill\"></i>");
//		out.append("                    </div>");
//		out.append("                    <div class=\"ps-3\">");
//		out.append("                      <h6>145</h6>");
//		out.append(
//				"                      <span class=\"text-success small pt-1 fw-bold\">12%</span> <span class=\"text-muted small pt-2 ps-1\">Tăng</span>");
//
//		out.append("                    </div>");
//		out.append("                  </div>");
//		out.append("                </div>");
//
//		out.append("              </div>");
//		out.append("            </div><!-- End Order Failed Card -->");
//		out.append("          </div>");
//		out.append("        </div><!-- End Right side columns -->");

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
		out.append("                  <h6>Lọc</h6>");
		out.append("                </li>");

		out.append("                <li><a class=\"dropdown-item\" href=\"../admin/orders-report?timeFilter=today\">Hôm nay</a></li>");
		out.append("                <li><a class=\"dropdown-item\" href=\"../admin/orders-report?timeFilter=month\">Tháng này</a></li>");
		out.append("                <li><a class=\"dropdown-item\" href=\"../admin/orders-report?timeFilter=year\">Năm nay</a></li>");
		out.append("              </ul>");
		out.append("            </div>");

		notification(request, response);

		out.append("          </div>");
		out.append("        </div><!-- End Recent Sales -->");

		out.append("      </div>");
		out.append("    </section>");

		out.append("  </main><!-- End #main -->");
		out.append("  <script src=\"../admin/js/OrdersReport.js\"></script>");

		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
		footerDispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void orderLineChart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		ordersReportDAO = new OrdersReportDAOImpl();

		out.append("  <div class=\"filter dropdown\">");
		out.append("    <a class=\"icon\" data-bs-toggle=\"dropdown\">");
		out.append("      <i class=\"bi bi-three-dots\"></i>");
		out.append("    </a>");

		out.append("    <div class=\"dropdown-menu dropdown-menu-end p-4 shadow\" data-bs-auto-close=\"outside\" style=\"min-width: 350px;\">");

		out.append("      <h6 class=\"fw-bold mb-3\">Lọc theo</h6>");

		out.append("      <div class=\"d-flex align-items-center mb-3 gap-3\">");
		out.append("        <div class=\"form-check\">");
		out.append("          <input class=\"form-check-input\" type=\"radio\" name=\"filterMode\" id=\"filterYear\" value=\"year\" checked onclick=\"toggleFilterMode()\">");
		out.append("          <label class=\"form-check-label\" for=\"filterYear\">Năm</label>");
		out.append("        </div>");
		out.append("        <div class=\"form-check\">");
		out.append("          <input class=\"form-check-input\" type=\"radio\" name=\"filterMode\" id=\"filterMonth\" value=\"month\" onclick=\"toggleFilterMode()\">");
		out.append("          <label class=\"form-check-label\" for=\"filterMonth\">Tháng</label>");
		out.append("        </div>");
		out.append("      </div>");

		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();

		out.append("      <div id=\"yearFilterGroup\" class=\"mb-3\">");
		out.append("        <select class=\"form-select\" id=\"yearSelectOnly\">");
		for (int year = currentYear; year >= 2015; year--) {
			out.append("          <option value=\"" + year + "\">" + year + "</option>");
		}
		out.append("        </select>");
		out.append("        <a id=\"yearSubmit\" class=\"btn btn-primary w-100 mt-2\" href=\"#\" onclick=\"submitYear()\">Xem kết quả</a>");
		out.append("      </div>");

		out.append("      <div id=\"monthFilterGroup\" class=\"mb-3\" style=\"display: none;\">");
		out.append("        <div class=\"d-flex gap-2 mb-2\">");
		out.append("          <select class=\"form-select\" id=\"monthYearSelect\">");
		for (int year = currentYear; year >= 2015; year--) {
			out.append("            <option value=\"" + year + "\">" + year + "</option>");
		}
		out.append("          </select>");
		out.append("          <select class=\"form-select\" id=\"monthSelect\" style=\"min-width: 102px;\">");
		for (int m = 1; m <= 12; m++) {
			out.append("            <option value=\"" + m + "\">Tháng " + m + "</option>");
		}
		out.append("          </select>");
		out.append("        </div>");
		out.append("        <a id=\"monthSubmit\" class=\"btn btn-primary w-100 mt-2\" href=\"#\" onclick=\"submitMonth()\">Xem kết quả</a>");
		out.append("      </div>");

		out.append("    </div>");
		out.append("  </div>");

		List<OrderResponse> orderResponseList;
		List<String> data = new ArrayList<>();
		List<Integer> successData = new ArrayList<>();
		List<Integer> cancelData = new ArrayList<>();

		String yearParam = request.getParameter("year");
		int year = (yearParam == null) ? currentYear : Integer.parseInt(yearParam);
		String monthParam = request.getParameter("month");

// Dùng TreeSet để tự động sắp xếp tăng dần
		Set<Integer> timeUnits = new TreeSet<>();

// Lưu dữ liệu đếm theo thời gian và trạng thái
		Map<Integer, Integer> deliveredMap = new HashMap<>();
		Map<Integer, Integer> cancelledMap = new HashMap<>();

		if (monthParam != null) {
			// THỐNG KÊ THEO NGÀY TRONG THÁNG
			int month = Integer.parseInt(monthParam);
			orderResponseList = ordersReportDAO.getDaylyOrderStatus(year, month);

			for (OrderResponse order : orderResponseList) {
				int day = order.getDay(); // ngày trong tháng
				timeUnits.add(day);

				if ("DELIVERED".equals(order.getOrderStatus())) {
					deliveredMap.put(day, order.getTotal());
				} else if ("CANCELLED".equals(order.getOrderStatus())) {
					cancelledMap.put(day, order.getTotal());
				}
			}

			for (int day : timeUnits) {
				data.add("\"" + day + "\"");
				successData.add(deliveredMap.getOrDefault(day, 0));
				cancelData.add(cancelledMap.getOrDefault(day, 0));
			}

		} else {
			// THỐNG KÊ THEO THÁNG TRONG NĂM
			orderResponseList = ordersReportDAO.getMonthlyOrderStatus(year);

			for (OrderResponse order : orderResponseList) {
				int month = order.getMonth(); // tháng trong năm
				timeUnits.add(month);

				if ("DELIVERED".equals(order.getOrderStatus())) {
					deliveredMap.put(month, order.getTotal());
				} else if ("CANCELLED".equals(order.getOrderStatus())) {
					cancelledMap.put(month, order.getTotal());
				}
			}

			for (int month : timeUnits) {
				data.add("\"Tháng " + month + "\"");
				successData.add(deliveredMap.getOrDefault(month, 0));
				cancelData.add(cancelledMap.getOrDefault(month, 0));
			}
		}

//		String data = "[\"1\", \"2\", \"3\", \"4\", \"5\", \"6\", \"7\", \"8\", \"9\", \"10\", \"11\", \"12\", \"13\", \"14\", \"15\", \"16\", \"17\", \"18\", \"19\", \"20\", \"21\", \"22\", \"23\", \"24\", \"25\", \"26\", \"27\", \"28\", \"29\", \"30\", \"31\"]";
//
//		String successData = "[3, 5, 2, 6, 8, 4, 7, 3, 6, 5, 7, 8, 4, 6, 5, 3, 4, 6, 8, 7, 6, 4, 5, 7, 6, 4, 3, 5, 6, 7, 6]";
//
//		String cancelData = "[1, 0, 2, 1, 1, 0, 2, 1, 0, 1, 2, 1, 0, 1, 0, 2, 1, 0, 1, 0, 2, 1, 0, 1, 2, 1, 0, 1, 0, 2, 1]";

		if(orderResponseList.isEmpty()){
			out.append("  <div class=\"card-body\">");
			out.append("    <h5 class=\"card-title\">Line Chart <small class=\"text-muted\">| ");
			out.append((monthParam != null ? ("Tháng " + monthParam + " ") : "") + "Năm " + year + "</small></h5>");
			out.append("    <div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">");
			out.append("      <i class=\"bi bi-exclamation-triangle me-1\"></i>");
			out.append("      Không có dữ liệu để hiển thị biểu đồ.");
			out.append("    </div>");
			out.append("  </div>");
		}else{
			out.append("                <div class=\"card-body \">");
			out.append("  <h5 class=\"card-title\">Line Chart <small class=\"text-muted\">| ");
			out.append((monthParam != null ? ("Tháng " + monthParam + " ") : "") +" Năm " + year + "</small></h5>");
			out.append("                  <!-- Line Chart -->");
			out.append("                  <canvas id=\"lineChart\" style=\"max-height: 400px; height: 100px;\" ></canvas>");
			out.append("<script src=\"https://cdn.jsdelivr.net/npm/chart.js\"></script>");
			out.append("                  <script>");
			out.append("                    document.addEventListener(\"DOMContentLoaded\", () => {");
			out.append("                      new Chart(document.querySelector('#lineChart'), {");
			out.append("                        type: 'line',");
			out.append("                        data: {");
			out.append(
					"                          labels: "+data+",");
			out.append("                          datasets: [");
			out.append("                          {");
			out.append("                            label: 'Thành công',");
			out.append("                            data: "+successData+",");
			out.append("                            fill: false,");
			out.append("                            borderColor: 'rgb(75, 192, 192)',");
			out.append("                            tension: 0.1");
			out.append("                          },");
			out.append("                          {");
			out.append("                            label: 'Bị huỷ',");
			out.append("                            data: "+cancelData+",");
			out.append("                            fill: false,");
			out.append("                            borderColor: 'rgb(255, 99, 132)',");
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
		}
	}

	protected void notification(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String timeFilter = request.getParameter("timeFilter");
		timeFilter = timeFilter == null ? "today" : timeFilter;

		LocalDate today = LocalDate.now();
		int year = today.getYear();
		int month = today.getMonthValue();
		int day = today.getDayOfMonth();

		if("month".equals(timeFilter)){
			day = 0;
		}
		if ("year".equals(timeFilter)){
			day = 0;
			month = 0;
		}

		ordersReportDAO = new OrdersReportDAOImpl();
		List<Order> orders = ordersReportDAO.findByCreatedAt(year, month, day, 1, 5);

		PrintWriter out = response.getWriter();

		out.append("            <div class=\"card-body\">");
		out.append("              <h5 class=\"card-title\">Thông báo mới <span>| " +
				("today".equals(timeFilter) ? "Hôm nay" :
						("month".equals(timeFilter) ? "Tháng này" : "Năm này"))+
				"</span></h5>");

		if(orders.isEmpty()){
			out.append("              <div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">");
			out.append("                <i class=\"bi bi-check-circle me-1\"></i>");
			out.append("                Chưa có trạng thái đơn hàng nào!");
			out.append("              </div>");
		}else{
			for(Order order : orders){

				String label = "";
				String bgColor = "";
				String biClass = "";

				switch (order.getOrderStatus()){
					case "DELIVERED":
						label = " được giao thành công.";
						bgColor = " alert-success";
						biClass = " bi-check-circle";
						break;
					case "PENDING":
						label = " đang chờ xác nhận.";
						bgColor = " alert-warning";
						biClass = " bi-exclamation-triangle";
						break;
					case "SHIPPED":
						label = " đang giao";
						bgColor = " alert-info";
						biClass = " bi-exclamation-triangle";
						break;
					case "CANCELLED":
						label = " đã bị hủy.";
						bgColor = " alert-danger";
						biClass = " bi-exclamation-octagon";
						break;
				}

				out.append("              <div class=\"alert"+bgColor+" alert-dismissible fade show\" role=\"alert\">");
				out.append("                <i class=\"bi"+biClass+" me-1\"></i>");
				out.append("                Đơn hàng DH#"+order.getOrderId()+label);
				out.append(
						"                <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>");
				out.append("              </div>");
			}

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
		}

	}

}
