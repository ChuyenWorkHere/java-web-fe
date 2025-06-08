package servlet.admin.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin/category-report")
public class CategoriesReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CategoriesReport() {
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
		out.append("canvas {\n" +
				"      max-height: 350px;\n" +
				"    }");
		out.append("<script src=\"https://cdn.jsdelivr.net/npm/chart.js@4.4.3/dist/chart.umd.min.js\"></script>");
		out.append("<main id=\"main\" class=\"main\">");
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
		out.append("      <!-- Biểu đồ cột: Doanh thu theo danh mục -->");
		out.append("      <div class=\"col-lg-6 mb-4\">");
		out.append("        <div class=\"card\">");
		out.append("          <div class=\"card-body\">");
		out.append("            <h5 class=\"card-title\">Doanh thu theo danh mục</h5>");
		out.append("            <canvas id=\"barChart\"></canvas>");
		out.append("          </div>");
		out.append("        </div>");
		out.append("      </div>");
		out.append("");
		out.append("      <!-- Biểu đồ tròn: Tỷ lệ doanh thu -->");
		out.append("      <div class=\"col-lg-5 mb-4\">");
		out.append("        <div class=\"card\">");
		out.append("          <div class=\"card-body\">");
		out.append("            <h5 class=\"card-title\">Tỷ lệ doanh thu</h5>");
		out.append("            <canvas id=\"pieChart\"></canvas>");
		out.append("          </div>");
		out.append("        </div>");
		out.append("      </div>");
		out.append("");
		out.append("");
		out.append("  <!-- JavaScript để tạo biểu đồ -->");
		out.append("  <script>");
		out.append("    // Biểu đồ cột: Doanh thu theo danh mục");
		out.append("\n");
		out.append("    const ctxBar = document.getElementById('barChart').getContext('2d');");
		out.append("    new Chart(ctxBar, {");
		out.append("      type: 'bar',");
		out.append("      data: {");
		out.append("        labels: ['Điện tử', 'Thời trang', 'Thực phẩm', 'Gia dụng'],");
		out.append("        datasets: [{");
		out.append("          label: 'Doanh thu (triệu VND)',");
		out.append("          data: [150, 80, 50, 120],");
		out.append("          backgroundColor: ['#4CAF50', '#2196F3', '#FFC107', '#FF5722'],");
		out.append("          borderColor: ['#388E3C', '#1976D2', '#FFA000', '#D81B60'],");
		out.append("          borderWidth: 1");
		out.append("        }]");
		out.append("      },");
		out.append("      options: {");
		out.append("        scales: {");
		out.append("          y: {");
		out.append("            beginAtZero: true,");
		out.append("            title: {");
		out.append("              display: true,");
		out.append("              text: 'Doanh thu (triệu VND)'");
		out.append("            }");
		out.append("          },");
		out.append("          x: {");
		out.append("            title: {");
		out.append("              display: true,");
		out.append("              text: 'Danh mục sản phẩm'");
		out.append("            }");
		out.append("          }");
		out.append("        },");
		out.append("        plugins: {");
		out.append("          legend: {");
		out.append("            display: false");
		out.append("          }");
		out.append("        }");
		out.append("      }");
		out.append("    });");
		out.append("");
		out.append("    // Biểu đồ tròn: Tỷ lệ doanh thu");
		out.append("\n");
		out.append("    const ctxPie = document.getElementById('pieChart').getContext('2d');");
		out.append("    new Chart(ctxPie, {");
		out.append("      type: 'pie',");
		out.append("      data: {");
		out.append("        labels: ['Điện thoại', 'Laptop', 'Phụ kiện', 'Khác'],");
		out.append("        datasets: [{");
		out.append("          data: [40, 30, 20, 10],");
		out.append("          backgroundColor: ['#FF5722', '#4CAF50', '#2196F3', '#FFC107'],");
		out.append("          borderColor: ['#D81B60', '#388E3C', '#1976D2', '#FFA000'],");
		out.append("          borderWidth: 1");
		out.append("        }]");
		out.append("      },");
		out.append("      options: {");
		out.append("        plugins: {");
		out.append("          legend: {");
		out.append("            position: 'right'");
		out.append("          },");
		out.append("          tooltip: {");
		out.append("            callbacks: {");
		out.append("              label: function(context) {");
		out.append("                return context.label + ': ' + context.parsed + '%';");
		out.append("              }");
		out.append("            }");
		out.append("          }");
		out.append("        }");
		out.append("      }");
		out.append("    });");
		out.append("");
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
