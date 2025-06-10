package servlet.admin.view;

import servlet.dao.SalesDAO;
import servlet.dao.impl.SalesDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Map;

@WebServlet("/admin/sales-report")
public class SalesReport extends HttpServlet {

	private SalesDAO salesDAO = new SalesDAOImpl();

	private static final long serialVersionUID = 1L;

	public SalesReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		LocalDate now = LocalDate.now();
		int yearSearch = -1, monthSearch =-1;

		//Kiểm tra để hiển thị label biểu đồ, true => label : month, false => label: day
		boolean flag = true;

		if(request.getParameter("year") != null) {
			try {
				yearSearch = Integer.parseInt(request.getParameter("year"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				yearSearch = now.getYear();
			}
		} else {
			yearSearch = now.getYear();
		}

		if(request.getParameter("month") != null) {
			try {
				monthSearch = Integer.parseInt(request.getParameter("month"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		} else {
			monthSearch = -1;
		}
		if(monthSearch > 0 && yearSearch > 0) {
			flag = false;
		}
		int dayLeft = 0, monthLeft = 0;
		if(flag && yearSearch == now.getYear()) {
			monthLeft = 12 - now.getMonthValue();
		} else if (!flag && monthSearch == now.getMonthValue() && yearSearch == now.getYear()) {
			dayLeft = now.lengthOfMonth() - now.getDayOfMonth();
		}


		Map<Integer, Double> salesReport = salesDAO.reportChartBuilder(monthSearch, yearSearch);
		PrintWriter out = response.getWriter();
		request.setAttribute("view", "report-category");

		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
		headerDispatcher.include(request, response);

		out.append("<style>");
		out.append("canvas { width: 100% !important; height: 500px !important; }");
		out.append("@media screen and (max-width: 600px) { canvas { height: 300px !important; } }");
		out.append("</style>");
		out.append("<script src=\"https://cdn.jsdelivr.net/npm/chart.js@4.4.3/dist/chart.umd.min.js\"></script>");
		out.append("<main id=\"main\" class=\"main dashboard\">");
		out.append("");
		out.append("    <div class=\"pagetitle d-flex justify-content-between\">");
		out.append("      <h1>Thống Kê</h1>");
		out.append("      <nav class=\"d-flex align-items-center\">");
		out.append("        <ol class=\"breadcrumb  mb-0\">");
		out.append("          <li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-fill\"></i></a></li>");
		out.append("          <li class=\"breadcrumb-item \">Thống Kê</li>");
		out.append("          <li class=\"breadcrumb-item active\">Sản Phẩm</li>");
		out.append("        </ol>");
		out.append("      </nav>");
		out.append("    </div><!-- End Page Title -->");
		out.append("");
		out.append("<div class=\"row mb-3 align-items-center\">");
		out.append("      <!-- Biểu đồ cột: Doanh thu theo danh mục -->");
		out.append("      <div class=\"col-lg-12 col-12 mb-4\">");
		out.append("        <div class=\"card\">");
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
		out.append("          <div class=\"card-body\">");
		out.append("            <canvas id=\"salesChart\"></canvas>");
		out.append("          </div>");
		out.append("        </div>");
		out.append("      </div>");
		out.append("");
		out.append("");
		out.append("  <!-- JavaScript để tạo biểu đồ -->");
		out.append("<script>");
		out.append("const salesData = [");
		for (Integer key : salesReport.keySet()) {
			if(flag)
				out.append("{ month: '"+key+"', revenue: "+salesReport.get(key)/1_000_000+" },");
			else
				out.append("{ day: '"+key+"', revenue: "+salesReport.get(key)/1_000_000+" },");
		}
		out.append("];");

		out.append("const labels = salesData.map(item => "+(flag? "item.month" : "item.day")+");");
		out.append("const revenues = salesData.map(item => item.revenue);");
		if(flag) {
			out.append("const actualData = revenues.slice(0, salesData.length - "+monthLeft+");");
			out.append("const forecastData = revenues.slice(salesData.length - "+monthLeft+");");
		} else {
			out.append("const actualData = revenues.slice(0, salesData.length - "+dayLeft+");");
			out.append("const forecastData = revenues.slice(salesData.length - "+dayLeft+");");
		}

		out.append("new Chart(document.getElementById('salesChart'), {");
		out.append("type: 'line',");
		out.append("data: {");
		out.append("labels: labels,");
		out.append("datasets: [");
		out.append("{");
		out.append("label: 'Doanh thu thực tế',");
		out.append("data: [...actualData,");
		if (flag) {
			for (int i = 1; i <= monthLeft; i++)
				out.append("null,");
		} else {
			for (int i = 1; i <= dayLeft; i++)
				out.append("null,");
		}
		out.append("],");
		out.append("borderColor: '#2563eb',");
		out.append("fill: false,");
		out.append("pointRadius: 5,");
		out.append("pointHoverRadius: 8,");
		if (flag) {
			out.append("pointBackgroundColor: salesData.map((_, index) => index === salesData.length - "+(monthLeft + 1)+" ? '#2563eb' : '#2563eb'),");
			out.append("pointBorderColor: salesData.map((_, index) => index === salesData.length - "+(monthLeft+1)+" ? '#2563eb' : '#2563eb')");
		} else {
			out.append("pointBackgroundColor: salesData.map((_, index) => index === salesData.length - "+(dayLeft + 1)+" ? '#2563eb' : '#2563eb'),");
			out.append("pointBorderColor: salesData.map((_, index) => index === salesData.length - "+(dayLeft+1)+" ? '#2563eb' : '#2563eb')");
		}
		out.append("},");
		out.append("{");
		out.append("label: 'Doanh thu dự báo',");
		out.append("data: [...Array(actualData.length - 1).fill(null), actualData[actualData.length - 1], ...forecastData],");
		out.append("borderColor: '#f59e0b',");
		out.append("borderDash: [5, 5],");
		out.append("fill: false,");
		out.append("pointRadius: 5,");
		out.append("pointHoverRadius: 8,");
		out.append("pointBackgroundColor: ['#f59e0b', '#f59e0b', '#f59e0b', '#f59e0b'],");
		out.append("pointBorderColor: ['#f59e0b', '#f59e0b', '#f59e0b', '#f59e0b']");
		out.append("}");
		out.append("]");
		out.append("},");
		out.append("options: {");
		out.append("responsive: true,");
		out.append("maintainAspectRatio: false,");
		out.append("scales: {");
		out.append("y: {");
		out.append("beginAtZero: true,");
		out.append("title: { display: true, text: 'Doanh thu (triệu VNĐ)', font: { size: 14 } }");
		out.append("},");
		out.append("x: {");
		out.append("title: { display: true, text: '"+(flag? ("Tháng / "+yearSearch) : ("Ngày / "+monthSearch+" / "+yearSearch)) +"', font: { size: 14 } }");
		out.append("}");
		out.append("},");
		out.append("plugins: {");
		out.append("title: {");
		out.append("display: true,");
		out.append("text: 'Doanh Thu và Dự báo',");
		out.append("font: { size: 18 }");
		out.append("},");
		out.append("legend: { position: 'top' },");
		out.append("tooltip: {");
		out.append("callbacks: {");
		out.append("label: function(context) {");
		out.append("let label = context.dataset.label || '';");
		out.append("if (label) { label += ': '; }");
		out.append("label += context.parsed.y !== null ? context.parsed.y.toFixed(2) + ' triệu VNĐ' : 'N/A';");
		out.append("return label;");
		out.append("}");
		out.append("}");
		out.append("}");
		out.append("}");
		out.append("}");
		out.append("});");
		out.append("</script>");
		out.append("  </main><!-- End #main -->");
		out.append("  <script src=\"../admin/js/reportSales.js\"></script>");

		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
		footerDispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
