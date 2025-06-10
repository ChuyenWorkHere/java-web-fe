package servlet.admin.view;

import servlet.dao.ProductDAO;
import servlet.dao.ProductReportDAO;
import servlet.dao.impl.ProductDAOImpl;
import servlet.dao.impl.ProductReportDAOImpl;
import servlet.models.Product;
import servlet.response.ReportChartResponse;

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
public class ProductsReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductReportDAO productReportDAO = new ProductReportDAOImpl();
	private ProductDAO productDAO = new ProductDAOImpl();

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

		ReportChartResponse<Integer> reportChartProduct = productReportDAO.buildChartData(monthSearch, yearSearch);

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
		out.append("            <h5 class=\"card-title ps-3 p-sm-0\">Xu Hướng</h5>");
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
		out.append("                <div class=\"card-body pb-0\">");
		out.append("                  <h5 class=\"card-title\">Xem Nhiều <span>| Hôm Nay</span></h5>");
		out.append("");
		out.append("                  <table class=\"table table-borderless\">");
		out.append("                    <thead>");
		out.append("                      <tr>");
		out.append("                        <th scope=\"col\">Ảnh</th>");
		out.append("                        <th scope=\"col\">Sản Phẩm</th>");
		out.append("                        <th scope=\"col\">Giá</th>");
		out.append("                        <th scope=\"col\">Đã Bán</th>");
		out.append("                        <th scope=\"col\">Doanh Thu</th>");
		out.append("                        <th scope=\"col\">Tình Trạng</th>");
		out.append("                      </tr>");
		out.append("                    </thead>");
		out.append("                    <tbody>");
		out.append("                      <tr>");
		out.append("                        <th scope=\"row\"><a href=\"#\"><img src=\"../admin/img/product-1.jpg\" alt=\"\"></a></th>");
		out.append("                        <td><a href=\"#\" class=\"text-primary fw-bold\">Ut inventore ipsa voluptas nulla</a></td>");
		out.append("                        <td>$64</td>");
		out.append("                        <td class=\"fw-bold\">124</td>");
		out.append("                        <td>$5,828</td>");
		out.append("                        <td>Còn Hàng</td>");
		out.append("                      </tr>");
		out.append("                      <tr>");
		out.append("                        <th scope=\"row\"><a href=\"#\"><img src=\"../admin/img/product-2.jpg\" alt=\"\"></a></th>");
		out.append("                        <td><a href=\"#\" class=\"text-primary fw-bold\">Exercitationem similique doloremque</a></td>");
		out.append("                        <td>$46</td>");
		out.append("                        <td class=\"fw-bold\">98</td>");
		out.append("                        <td>$4,508</td>");
		out.append("                        <td>Còn Hàng</td>");
		out.append("                      </tr>");
		out.append("                      <tr>");
		out.append("                        <th scope=\"row\"><a href=\"#\"><img src=\"../admin/img/product-3.jpg\" alt=\"\"></a></th>");
		out.append("                        <td><a href=\"#\" class=\"text-primary fw-bold\">Doloribus nisi exercitationem</a></td>");
		out.append("                        <td>$59</td>");
		out.append("                        <td class=\"fw-bold\">74</td>");
		out.append("                        <td>$4,366</td>");
		out.append("                        <td>Còn Hàng</td>");
		out.append("                      </tr>");
		out.append("                      <tr>");
		out.append("                        <th scope=\"row\"><a href=\"#\"><img src=\"../admin/img/product-4.jpg\" alt=\"\"></a></th>");
		out.append("                        <td><a href=\"#\" class=\"text-primary fw-bold\">Officiis quaerat sint rerum error</a></td>");
		out.append("                        <td>$32</td>");
		out.append("                        <td class=\"fw-bold\">63</td>");
		out.append("                        <td>$2,016</td>");
		out.append("                        <td>Còn Hàng</td>");
		out.append("                      </tr>");
		out.append("                      <tr>");
		out.append("                        <th scope=\"row\"><a href=\"#\"><img src=\"../admin/img/product-5.jpg\" alt=\"\"></a></th>");
		out.append("                        <td><a href=\"#\" class=\"text-primary fw-bold\">Sit unde debitis delectus repellendus</a></td>");
		out.append("                        <td>$79</td>");
		out.append("                        <td class=\"fw-bold\">41</td>");
		out.append("                        <td>$3,239</td>");
		out.append("                        <td>Còn Hàng</td>");
		out.append("                      </tr>");
		out.append("                    </tbody>");
		out.append("                  </table>");
		out.append("");
		out.append("                </div>");
		out.append("");
		out.append("              </div>");
		out.append("            </div><!-- End Top Selling -->");
		out.append("");
		out.append("      <!-- Biểu đồ vùng cực: Tỷ lệ tồn kho -->");
		out.append("      <div class=\"col-lg-5 col-12\">");
		out.append("        <div class=\"card\">");
		out.append("          <div class=\"card-body\">");
		out.append("            <h5 class=\"card-title\">Tồn Kho</h5>");
		out.append("            <canvas id=\"polarChart\"></canvas>");
		out.append("          </div>");
		out.append("        </div>");
		out.append("      </div>");
		out.append("    </div>");
		out.append("  </div>");
		out.append("");
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
		out.append("    // Biểu đồ vùng cực: Tỷ lệ tồn kho");
		out.append("\n");
		out.append("    const ctxPolar = document.getElementById('polarChart').getContext('2d');");
		out.append("    new Chart(ctxPolar, {");
		out.append("      type: 'polarArea',");
		out.append("      data: {");
		out.append("        labels: ['Điện tử', 'Thời trang', 'Thực phẩm', 'Gia dụng'],");
		out.append("        datasets: [{");
		out.append("          data: [500, 300, 250, 400],");
		out.append("          backgroundColor: ['rgba(33, 150, 243, 0.5)', 'rgba(76, 175, 80, 0.5)', 'rgba(255, 193, 7, 0.5)', 'rgba(255, 87, 34, 0.5)'],");
		out.append("          borderColor: ['#1976D2', '#388E3C', '#FFA000', '#D81B60'],");
		out.append("          borderWidth: 1");
		out.append("        }]");
		out.append("      },");
		out.append("      options: {");
		out.append("        plugins: {");
		out.append("          legend: {");
		out.append("            position: 'right'");
		out.append("          }");
		out.append("        }");
		out.append("      }");
		out.append("    });");
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
