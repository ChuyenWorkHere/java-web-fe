package servlet.admin.view;

import servlet.dao.OrdersReportDAO;
import servlet.dao.impl.OrdersReportDAOImpl;
import servlet.models.Order;
import servlet.models.User;
import servlet.response.OrderResponse;
import servlet.response.UserReportResponse;
import servlet.utils.ProductUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.YearMonth;
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
		/*them thu vien*/
		out.append("<script src=\"https://html2canvas.hertzen.com/dist/html2canvas.min.js\"></script>");
		/*them thu vien*/

		out.append("<main id=\"main\" class=\"main\">");

		out.append("    <div class=\"pagetitle d-flex justify-content-between\">");
		out.append("      <h1>Thống kê đơn hàng</h1>");
		out.append("      <nav class=\"d-flex align-items-center\" >");
		out.append("        <ol class=\"breadcrumb  mb-0\">");
		out.append(
				"          <li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-fill\"></i></a></li>");
		out.append("          <li class=\"breadcrumb-item\">Thống kê</li>");
		out.append("          <li class=\"breadcrumb-item active\">Đơn hàng</li>");
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

		/*--------------line chart------------------*/

		orderLineChart(request, response);

		/* start nut xuat du lieu*/
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();

		out.append("  <div class=\"dropdown\">");
		out.append("    <a class=\"btn btn-primary dropdown-toggle\" href=\"#\" role=\"button\" data-bs-toggle=\"dropdown\">");
		out.append("      <i class=\"bi bi-download me-2\"></i> Xuất PDF");
		out.append("    </a>");
		out.append("    <div class=\"dropdown-menu p-4 shadow\" style=\"min-width: 350px;\">");
		out.append("      <h6 class=\"fw-bold mb-3\">Lọc theo</h6>");
		out.append("      <div class=\"d-flex align-items-center mb-3 gap-3\">");
		out.append("        <div class=\"form-check\">");
		out.append("          <input class=\"form-check-input\" type=\"radio\" name=\"pdfExportMode\" id=\"pdfExportYear\" value=\"year\" checked onclick=\"togglePdfExportMode()\">");
		out.append("          <label class=\"form-check-label\" for=\"pdfExportYear\">Năm</label>");
		out.append("        </div>");
		out.append("        <div class=\"form-check\">");
		out.append("          <input class=\"form-check-input\" type=\"radio\" name=\"pdfExportMode\" id=\"pdfExportMonth\" value=\"month\" onclick=\"togglePdfExportMode()\">");
		out.append("          <label class=\"form-check-label\" for=\"pdfExportMonth\">Tháng</label>");
		out.append("        </div>");
		out.append("      </div>");
		out.append("      <div id=\"pdfExportYearGroup\" class=\"mb-3\">");
		out.append("        <select class=\"form-select\" id=\"pdfExportYearSelect\">");
		for (int year = currentYear; year >= 2015; year--) {
			out.append("          <option value=\"" + year + "\">" + year + "</option>");
		}
		out.append("        </select>");
		out.append("        <a class=\"btn btn-primary w-100 mt-2\" href=\"#\" onclick=\"submitPdfExportYear()\">Xuất PDF</a>");
		out.append("      </div>");
		out.append("      <div id=\"pdfExportMonthGroup\" class=\"mb-3\" style=\"display: none;\">");
		out.append("        <div class=\"d-flex gap-2 mb-2\">");
		out.append("          <select class=\"form-select\" id=\"pdfExportMonthYearSelect\">");
		for (int year = currentYear; year >= 2015; year--) {
			out.append("            <option value=\"" + year + "\">" + year + "</option>");
		}
		out.append("          </select>");
		out.append("          <select class=\"form-select\" id=\"pdfExportMonthSelect\" style=\"min-width: 102px;\">");
		for (int m = 1; m <= 12; m++) {
			out.append("            <option value=\"" + m + "\">Tháng " + m + "</option>");
		}
		out.append("          </select>");
		out.append("        </div>");
		out.append("        <a class=\"btn btn-primary w-100 mt-2\" href=\"#\" onclick=\"submitPdfExportMonth()\">Xuất PDF</a>");
		out.append("      </div>");
		out.append("    </div>");
		out.append("  </div>");

		/* end nut xuat du lieu*/
		out.append("  </div>");
		/*end them thong bao top*/

		/*--------------line chart------------------*/

		out.append("        <!-- Recent Sales -->");
		out.append("        <div class=\"col-lg-4\">");
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

		/*---------------start filter dropdown------------*/
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
		out.append("        <select class=\"form-select\" id=\"filterYearSelect\">");
//		out.append("        <select class=\"form-select\" id=\"yearSelectOnly\">");
		for (int year = currentYear; year >= 2015; year--) {
			out.append("          <option value=\"" + year + "\">" + year + "</option>");
		}
		out.append("        </select>");
		out.append("        <a id=\"yearSubmit\" class=\"btn btn-primary w-100 mt-2\" href=\"#\" onclick=\"submitYear()\">Xem kết quả</a>");
		out.append("      </div>");
		out.append("      <div id=\"monthFilterGroup\" class=\"mb-3\" style=\"display: none;\">");
		out.append("        <div class=\"d-flex gap-2 mb-2\">");
		out.append("          <select class=\"form-select\" id=\"filterMonthYearSelect\">");
//		out.append("          <select class=\"form-select\" id=\"monthYearSelect\">");
		for (int year = currentYear; year >= 2015; year--) {
			out.append("            <option value=\"" + year + "\">" + year + "</option>");
		}
		out.append("          </select>");
		out.append("          <select class=\"form-select\" id=\"filterMonthSelect\" style=\"min-width: 102px;\">");
//		out.append("          <select class=\"form-select\" id=\"monthSelect\" style=\"min-width: 102px;\">");
		for (int m = 1; m <= 12; m++) {
			out.append("            <option value=\"" + m + "\">Tháng " + m + "</option>");
		}
		out.append("          </select>");
		out.append("        </div>");
		out.append("        <a id=\"monthSubmit\" class=\"btn btn-primary w-100 mt-2\" href=\"#\" onclick=\"submitMonth()\">Xem kết quả</a>");
		out.append("      </div>");
		out.append("    </div>");
		out.append("  </div>");
		/*---------------end filter dropdown------------*/

		List<OrderResponse> orderResponseList;
		List<String> data = new ArrayList<>();
		List<Integer> successData = new ArrayList<>();
		List<Integer> cancelData = new ArrayList<>();

		int currentMonth = LocalDate.now().getMonthValue();
		int currentDay = LocalDate.now().getDayOfMonth();

		String yearParam = request.getParameter("year");
		int year = (yearParam == null) ? currentYear : Integer.parseInt(yearParam);
		String monthParam = request.getParameter("month");

		Map<Integer, Integer> deliveredMap = new HashMap<>();
		Map<Integer, Integer> cancelledMap = new HashMap<>();

		if (monthParam != null) {
			// ===== Thống kê theo ngày trong tháng =====
			int month = Integer.parseInt(monthParam);
			orderResponseList = ordersReportDAO.getDaylyOrderStatus(year, month);

			for (OrderResponse order : orderResponseList) {
				int day = order.getDay();
				if ("DELIVERED".equals(order.getOrderStatus())) {
					deliveredMap.put(day, order.getTotal());
				} else if ("CANCELLED".equals(order.getOrderStatus())) {
					cancelledMap.put(day, order.getTotal());
				}
			}

			int lastDay;
			if (year < currentYear || (year == currentYear && month < currentMonth)) {
				// Lấy toàn bộ ngày trong tháng đó
				lastDay = YearMonth.of(year, month).lengthOfMonth();
			} else {
				// Chỉ lấy đến ngày hiện tại
				lastDay = currentDay;
			}

			for (int day = 1; day <= lastDay; day++) {
				data.add("\"" + day + "\"");
				successData.add(deliveredMap.getOrDefault(day, 0));
				cancelData.add(cancelledMap.getOrDefault(day, 0));
			}

		} else {
			// ===== Thống kê theo tháng trong năm =====
			orderResponseList = ordersReportDAO.getMonthlyOrderStatus(year);

			for (OrderResponse order : orderResponseList) {
				int month = order.getMonth();
				if ("DELIVERED".equals(order.getOrderStatus())) {
					deliveredMap.put(month, order.getTotal());
				} else if ("CANCELLED".equals(order.getOrderStatus())) {
					cancelledMap.put(month, order.getTotal());
				}
			}

			int lastMonth = (year < currentYear) ? 12 : currentMonth;

			for (int month = 1; month <= lastMonth; month++) {
				data.add("\"Tháng " + month + "\"");
				successData.add(deliveredMap.getOrDefault(month, 0));
				cancelData.add(cancelledMap.getOrDefault(month, 0));
			}
		}

		double cancelMean = calculateMean(cancelData);
		double cancelStdDev = calculateStdDev(cancelData);
		List<Integer> highCancelIndexes = new ArrayList<>();
		List<String> anomalyDetails = new ArrayList<>();

		for (int i = 0; i < cancelData.size(); i++) {
			int cancelCount = cancelData.get(i);
			if (cancelCount > cancelMean + 2 * cancelStdDev) {
				highCancelIndexes.add(i);

				String label = monthParam != null
						? "Ngày " + (i + 1)
						: "Tháng " + (i + 1);

				anomalyDetails.add(label + " có " + String.format("%.3f", (double) cancelCount) + " đơn hàng bị huỷ, vượt quá ngưỡng bất thường.\n"
						+ "(Trung bình: " + String.format("%.3f", cancelMean) + ", Độ lệch chuẩn: " + String.format("%.3f", cancelStdDev)
						+ ", Ngưỡng: " + String.format("%.3f", cancelMean + 2 * cancelStdDev) + ")");
			}
		}

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
			out.append("                            x: {");
			out.append("                              display: true,");
			out.append("                              title: {");
			out.append("                                display: true,");
			out.append("                                text: \""+(monthParam != null ? "Ngày" : "Tháng")+"\"");
			out.append("                              }");
			out.append("                            },");
			out.append("                            y: {");
			out.append("                              display: true,");
			out.append("                              title: {");
			out.append("                                display: true,");
			out.append("                                text: \"Số lượng\"");
			out.append("                              },");
			out.append("                              ticks: {");
			out.append("                                beginAtZero: true");
			out.append("                              }");
			out.append("                            }");
			out.append("                          }");
			out.append("                        }");


			out.append("                      });");
			out.append("                    });");
			out.append("                  </script>");
			out.append("                  <!-- End Line CHart -->");

			if (!highCancelIndexes.isEmpty()) {
				out.append("<div class='alert alert-danger mt-3'>");
				out.append("<strong>Cảnh báo:</strong> Có ");
				out.append(String.valueOf(highCancelIndexes.size()));
				out.append(" ");
				out.append((monthParam != null ? "ngày" : "tháng"));
				out.append(" có số đơn hàng bị hủy cao bất thường. Hãy kiểm tra lý do.");
				out.append("<ul>");
				for (String detail : anomalyDetails) {
					out.append("<li>").append(detail).append("</li>");
				}
				out.append("</ul>");
				out.append("</div>");
			}

			out.append("                </div>");
		}
		/*-------------------- Thong bao top nguoi mua/huy -------------------*/
		if(monthParam != null){
			int month = Integer.parseInt(monthParam);
			generateTopUserSection(request, response, year, month);
		}else{
			generateTopUserSection(request, response, year, 0);
		}

	}

	private void generateTopUserSection(HttpServletRequest request, HttpServletResponse response,
										int year, Integer month) throws ServletException, IOException{

		List<UserReportResponse> completedResults = ordersReportDAO.getTopBuyers(year, month, 8);
		List<UserReportResponse> canceledResults = ordersReportDAO.getTopCancellers(year, month, 8);

		PrintWriter out = response.getWriter();
		out.append("              </div>");
		out.append("            </div><!-- End Reports -->");
		out.append("          </div>");
		out.append("        </div><!-- End Left side columns -->");
		out.append("      </div>");

		out.append("      <div class=\"row\">");
		/* them thong bao top*/
		out.append("        <div class=\"col-lg-8\">");
		out.append("<!-- Top Users Section -->");
		out.append("<div class=\"row\">");

		// Cột trái - Top Người Mua
		out.append("  <div class=\"col-12 col-md-6 col-lg-6 mb-4\">");
		out.append("    <div class=\"card h-100\">");
		out.append("      <div class=\"card-body\">");
		out.append("        <h5 class=\"card-title\">Top Người Mua</h5>");
		out.append("        <ul class=\"list-group\">");
		for (UserReportResponse com : completedResults){
			out.append("          <li class=\"list-group-item d-flex justify-content-between align-items-center\">");
			out.append("            "+ com.getFullname() +" <span class=\"badge bg-success rounded-pill\">"
					+com.getTotalOrders()+" đơn - "+ ProductUtils.formatNumber(com.getTotalAmount()) +"đ</span>");
			out.append("          </li>");
		}

		out.append("        </ul>");
		out.append("      </div>");
		out.append("    </div>");
		out.append("  </div>");

		// Cột phải - Top Người Huỷ
		out.append("  <div class=\"col-12 col-md-6 col-lg-6 mb-4\">");
		out.append("    <div class=\"card h-100\">");
		out.append("      <div class=\"card-body\">");
		out.append("        <h5 class=\"card-title\">Top Người Huỷ</h5>");
		out.append("        <ul class=\"list-group\">");
		for (UserReportResponse can : canceledResults){
			out.append("          <li class=\"list-group-item d-flex justify-content-between align-items-center\">");
			out.append("            "+ can.getFullname() +" <span class=\"badge bg-danger rounded-pill\">"
					+can.getTotalOrders()+" đơn - "+ ProductUtils.formatNumber(can.getTotalAmount()) +"đ</span>");
			out.append("          </li>");
		}
		out.append("        </ul>");
		out.append("      </div>");
		out.append("    </div>");
		out.append("  </div>");
		out.append("</div>");
	}

	protected void notification(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String timeFilter = request.getParameter("timeFilter");
		timeFilter = timeFilter == null ? "today" : timeFilter;

		String pageNo = request.getParameter("pageNo");
		int currentPage = 1;
		if (pageNo != null)
			currentPage = Integer.parseInt(pageNo);

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
		List<Order> orders = ordersReportDAO.findByCreatedAt(year, month, day, currentPage, 5);
		int pageNumbers = (int) Math.ceil((double) ordersReportDAO.countCreatedAt(year, month, day) / 5);

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

			out.append("<nav aria-label=\"Page navigation example\" class=\"mt-4 d-flex justify-content-center order-3 order-md-3\">");
			out.append("  <ul class=\"pagination\">");

			String statusLink = null;
			String status = "";
			String keyword = "";
// Previous button
			if (currentPage > 1) {
				out.append("    <li class=\"page-item\">");
//			out.append("      <a class=\"page-link\" href=\"" + getPageUrl(currentPage - 1, statusLink, keyword, status) + "\" aria-label=\"Previous\">");
				out.append("      <a class=\"page-link\" href=\"" +
						getPageUrl(currentPage - 1, timeFilter)
						+ "\" aria-label=\"Previous\">");
				out.append("        <span aria-hidden=\"true\">&laquo;</span>");
				out.append("      </a>");
				out.append("    </li>");
			}

// Always show page 1
			out.append(createPageItem(1, currentPage, timeFilter));
// Add ... after page 1 if needed
			if (currentPage > 3) {
				out.append("    <li class=\"page-item disabled\"><a class=\"page-link\">...</a></li>");
			}

// Show current page if it's not 1 or last
			if (currentPage != 1 && currentPage != pageNumbers) {
				out.append(createPageItem(currentPage, currentPage, timeFilter));
			}

// Add ... before last page if needed
			if (currentPage < pageNumbers - 2) {
				out.append("    <li class=\"page-item disabled\"><a class=\"page-link\">...</a></li>");
			}

// Always show last page if more than 1 page
			if (pageNumbers > 1) {
				out.append(createPageItem(pageNumbers, currentPage, timeFilter));
			}

// Next button
			if (currentPage < pageNumbers) {
				out.append("    <li class=\"page-item\">");
				out.append("      <a class=\"page-link\" href=\"" +
						getPageUrl(currentPage + 1, timeFilter)
						+ "\" aria-label=\"Next\">");
				out.append("        <span aria-hidden=\"true\">&raquo;</span>");
				out.append("      </a>");
				out.append("    </li>");
			}

			out.append("  </ul>");
			out.append("</nav>");


			out.append("            </div>");
		}

	}

	private String createPageItem(int page, int currentPage, String timeFilter) {
		StringBuilder item = new StringBuilder();
		item.append("    <li class=\"page-item " + (page == currentPage ? "active" : "") + "\">");
		item.append("      <a class=\"page-link\" href=\"" +
				getPageUrl(page, timeFilter) + "\">"
				+ page + "</a>");

		item.append("    </li>");
		return item.toString();
	}

	private String getPageUrl(int pageNo, String timeFilter){
		String url = "../admin/orders-report?pageNo=" + pageNo;
		url += "&timeFilter=" + timeFilter;
		return url;
	}

	/* phát hện sự bất thường trong biểu đồ */
	// tính trung bình
	public static double calculateMean(List<Integer> data) {
		double sum = 0;
		for (int val : data) {
			sum += val;
		}
		return sum / data.size();
	}

	//tính độ lệch chuẩn
	public static double calculateStdDev(List<Integer> data) {
		double mean = calculateMean(data);
		double sumSquaredDiffs = 0;
		for (int val : data) {
			sumSquaredDiffs += Math.pow(val - mean, 2);
		}
		return Math.sqrt(sumSquaredDiffs / data.size());
	}

	/* phát hện sự bất thường trong biểu đồ */
}
