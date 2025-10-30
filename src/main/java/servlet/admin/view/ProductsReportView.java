package servlet.admin.view;

import servlet.dao.ProductDAO;
import servlet.dao.ProductReportDAO;
import servlet.dao.impl.ProductDAOImpl;
import servlet.dao.impl.ProductReportDAOImpl;
import servlet.models.Product;
import servlet.response.ReportResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/products-report")
public class ProductsReportView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductReportDAO productReportDAO = new ProductReportDAOImpl();
	private ProductDAO productDAO = new ProductDAOImpl();

	public ProductsReportView() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		request.setAttribute("view", "report-product");

		int yearSearch = -1, monthSearch =-1;

		if(request.getParameter("year") != null) {
			try {
				yearSearch = Integer.parseInt(request.getParameter("year"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				yearSearch = 2025;
			}
		} else {
			yearSearch = 2025;
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

		ReportResponse<Integer> reportChartProduct = productReportDAO.buildChartData(monthSearch, yearSearch);

		String[] colors = {
				"#2196F3", "#F44336", "#4CAF50", "#FFC107", "#9C27B0", "#FF9800", "#009688"
		};
		String[] backgroundColors = {
				"rgba(33,150,243,0.2)", "rgba(244,67,54,0.2)", "rgba(76,175,80,0.2)",
				"rgba(255,193,7,0.2)", "rgba(156,39,176,0.2)", "rgba(255,152,0,0.2)", "rgba(0,150,136,0.2)"
		};

		List<String> labels = reportChartProduct.getLabels();
		Map<Integer, List<Integer>> dataset = reportChartProduct.getObject();

		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
		headerDispatcher.include(request, response);
		out.append("<style> canvas {\n" +
				"      max-height: 412px;\n" +
				"    } </style>");
		out.append("<script src=\"https://cdn.jsdelivr.net/npm/chart.js@4.4.3/dist/chart.umd.min.js\"></script>");
		out.append("<main id=\"main\" class=\"main dashboard\">");
		out.append("");
		out.append("    <div class=\"pagetitle d-flex justify-content-between align-items-center\">");
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
		out.append("<div class=\"row mt-3 mb-3\">");
		out.append("      <!-- Biểu đồ đường: Xu hướng bán hàng -->");
		out.append("      <div class=\"col-12\">");
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
		out.append("          <div class=\"card-body p-0 p-sm-3\">");
		out.append("            <h5 class=\"card-title ps-3 p-sm-0\">Sản phẩm bán chạy</h5>");
		out.append("            <canvas id=\"lineChart\"></canvas>");
		out.append("          </div>");
		out.append("        </div>");
		out.append("      </div>");
		out.append("");
		out.append("    </div>");

		out.append("<div class=\"row mt-3 mb-3\">");
		out.append("            <!-- Bán Chạy Nhất -->");
		out.append("            <div class=\"col-lg-7 col-12\">");
		out.append("              <div class=\"card top-selling overflow-auto\">");
		out.append("");
		out.append("                <div class=\"filter\">");
		out.append("                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.append("                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.append("                    <li class=\"dropdown-header text-start\">");
		out.append("                      <h6>Filter</h6>");
		out.append("                    </li>");
		out.append("");
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Hôm Nay</a></li>");
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Tháng Này</a></li>");
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Năm Nay</a></li>");
		out.append("                  </ul>");
		out.append("                </div>");
		out.append("");
		out.append("    </div>");
		out.append("  </div>");

		out.append("  <!-- JavaScript để tạo biểu đồ -->");
		out.append("  <script>");
		out.append("    // Biểu đồ đường: Xu hướng bán hàng");
		out.append("\n");
		out.append("    const ctxLine = document.getElementById('lineChart').getContext('2d');");
		out.append("    new Chart(ctxLine, {");
		out.append("      type: 'line',");
		out.append("      data: {");
		out.append("        labels: [");
		for (String label: labels) {
			out.append("'"+label+"',");
		}
		out.append("        ],");
		out.append("        datasets: [");
		int colorIndex = 0;
		for (Integer key : dataset.keySet()) {

			Product product = productDAO.findById(key);
			out.append("          {");
			out.append("            label: '"+product.getProductName()+"',");
			out.append("            data: [");
			for (Integer data: dataset.get(key)){
				out.append(data +",");
			}
			out.append("            ],");
			out.append("            borderColor: '"+colors[colorIndex % colors.length]+"',");
			out.append("            backgroundColor: '"+backgroundColors[colorIndex % backgroundColors.length]+"',");
			out.append("            fill: false");
			out.append("          },");
			colorIndex++;
		}
		out.append("        ]");
		out.append("      },");
		out.append("      options: {");
		out.append("        scales: {");
		out.append("          y: {");
		out.append("            beginAtZero: true,");
		out.append("            title: {");
		out.append("              display: false,");
		out.append("              text: 'Số lượng bán ra'");
		out.append("            }");
		out.append("          },");
		out.append("          x: {");
		out.append("            title: {");
		out.append("              display: false,");
		out.append("              text: 'Tháng'");
		out.append("            }");
		out.append("          }");
		out.append("        }");
		out.append("      }");
		out.append("    });");
		out.append("");
		out.append("\n");
		out.append("  </script>");
		out.append("  <script src=\"../admin/js/reportProduct.js\"></script>");
		
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
