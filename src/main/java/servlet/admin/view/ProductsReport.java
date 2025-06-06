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
		out.append("<style> canvas {\n" +
				"      max-height: 320px;\n" +
				"    } </style>");
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
		out.append("<div class=\"row mt-3 mb-3\">");
		out.append("      <!-- Biểu đồ đường: Xu hướng bán hàng -->");
		out.append("      <div class=\"col-lg-7 col-12\">");
		out.append("        <div class=\"card\">");
		out.append("			<div class=\"filter\">");
		out.append("                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
		out.append("                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
		out.append("                    <li class=\"dropdown-header text-start\">");
		out.append("                      <h6>Lọc</h6>");
		out.append("                    </li>");
		out.append("");
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">2025</a></li>");
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">2024</a></li>");
		out.append("                    <li><a class=\"dropdown-item\" href=\"#\">2023</a></li>");
		out.append("                  </ul>");
		out.append("             </div>");
		out.append("          <div class=\"card-body\">");
		out.append("            <h5 class=\"card-title\">Xu hướng bán hàng</h5>");
		out.append("            <canvas id=\"lineChart\"></canvas>");
		out.append("          </div>");
		out.append("        </div>");
		out.append("      </div>");
		out.append("");
		out.append("      <!-- Biểu đồ vùng cực: Tỷ lệ tồn kho -->");
		out.append("      <div class=\"col-lg-5 col-12\">");
		out.append("        <div class=\"card\">");
		out.append("          <div class=\"card-body\">");
		out.append("            <h5 class=\"card-title\">Tỷ lệ tồn kho</h5>");
		out.append("            <canvas id=\"polarChart\"></canvas>");
		out.append("          </div>");
		out.append("        </div>");
		out.append("      </div>");
		out.append("    </div>");
		out.append("            <!-- Bán Chạy Nhất -->");
		out.append("            <div class=\"col-12\">");
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
		out.append("                  <h5 class=\"card-title\">Bán Chạy Nhất <span>| Hôm Nay</span></h5>");
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
		out.append("        labels: ['T1', 'T2', 'T3', 'T4', 'T5', 'T6'],");
		out.append("        datasets: [");
		out.append("          {");
		out.append("            label: 'Sản phẩm A',");
		out.append("            data: [100, 120, 150, 130, 170, 200],");
		out.append("            borderColor: '#2196F3',");
		out.append("            backgroundColor: 'rgba(33, 150, 243, 0.2)',");
		out.append("            fill: true");
		out.append("          },");
		out.append("          {");
		out.append("            label: 'Sản phẩm B',");
		out.append("            data: [80, 90, 110, 100, 120, 140],");
		out.append("            borderColor: '#4CAF50',");
		out.append("            backgroundColor: 'rgba(76, 175, 80, 0.2)',");
		out.append("            fill: true");
		out.append("          }");
		out.append("        ]");
		out.append("      },");
		out.append("      options: {");
		out.append("        scales: {");
		out.append("          y: {");
		out.append("            beginAtZero: true,");
		out.append("            title: {");
		out.append("              display: true,");
		out.append("              text: 'Số lượng bán ra'");
		out.append("            }");
		out.append("          },");
		out.append("          x: {");
		out.append("            title: {");
		out.append("              display: true,");
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
