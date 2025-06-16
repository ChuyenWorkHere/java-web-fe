package servlet.admin.view;

import servlet.dao.HomeDAO;
import servlet.dao.UserDAO;
import servlet.dao.impl.HomeDAOImpl;
import servlet.dao.impl.UserDAOImpl;
import servlet.models.User;
import servlet.utils.ProductUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/home-view")
public class HomeView extends HttpServlet {

	private HomeDAO homeDAO = new HomeDAOImpl();

	private static final long serialVersionUID = 1L;

	public HomeView() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");		 
		
		PrintWriter out = response.getWriter();

		request.setAttribute("view", "home");
		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
	    headerDispatcher.include(request, response);

		UserDAO userDAO = new UserDAOImpl();
		List<User> users = userDAO.findLatestRegisteredAccount();

		Map<String, String> filterInfo = new HashMap<>();

		StringBuilder urlSearch = new StringBuilder();
		urlSearch.append("../admin/home-view?");

		String sales = "year";
		String customers = "year";
		String revenue = "year";

		if(request.getParameter("sales") != null
			&& request.getParameter("sales").trim() != ""){
			sales = request.getParameter("sales");

		}
		filterInfo.put("sales", sales);
		urlSearch.append("sales=" + sales);

		if(request.getParameter("customers") != null
				&& request.getParameter("customers").trim() != ""){
			customers = request.getParameter("customers");

		}
		filterInfo.put("customers", customers);
		urlSearch.append("&customers=" + customers);

		if(request.getParameter("revenue") != null
				&& request.getParameter("revenue").trim() != ""){
			revenue = request.getParameter("revenue");

		}
		filterInfo.put("revenue", revenue);
		urlSearch.append("&revenue=" + revenue);

		Map<String, Map<String, Double>> dataCardMap = homeDAO.loadCard(filterInfo);

	    out.append("<main id=\"main\" class=\"main\">");
	    out.append("");
	    out.append("    <div class=\"pagetitle d-flex justify-content-between\">");
	    out.append("      <h1>Trang chủ</h1>");
	    out.append("      <nav class=\"d-flex align-items-center\">");
	    out.append("        <ol class=\"breadcrumb  mb-0\">");
	    out.append("          <li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-fill\"></i></a></li>");
	    out.append("          <li class=\"breadcrumb-item active\">Trang chủ</li>");
	    out.append("        </ol>");
	    out.append("      </nav>");
	    out.append("    </div><!-- End Page Title -->");
	    out.append("");
	    out.append("    <section class=\"section dashboard\">");
	    out.append("      <div class=\"row\">");
	    out.append("        <!-- Sales Card -->");
	    out.append("        <div class=\"col-md-4 col-12\">");
	    out.append("          <div class=\"card info-card sales-card\">");
	    out.append("");
	    out.append("            <div class=\"filter\">");
	    out.append("              <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
	    out.append("              <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
	    out.append("                <li class=\"dropdown-header text-start\">");
	    out.append("                  <h6>Filter</h6>");
	    out.append("                </li>");
	    out.append("");
	    out.append("                <li><a class=\"dropdown-item\" href=\""+urlSearch.toString().replace("sales="+sales, "sales=day")+"\">Hôm Nay</a></li>");
	    out.append("                <li><a class=\"dropdown-item\" href=\""+urlSearch.toString().replace("sales="+sales, "sales=month")+"\">Tháng Này</a></li>");
	    out.append("                <li><a class=\"dropdown-item\" href=\""+urlSearch.toString().replace("sales="+sales, "sales=year")+"\">Năm Này</a></li>");
	    out.append("              </ul>");
	    out.append("            </div>");
	    out.append("");
	    out.append("            <div class=\"card-body\">");
	    out.append("              <h5 class=\"card-title\">Bán Hàng <span>| "+(sales.equalsIgnoreCase("day") ? "Hôm nay" : ((sales.equalsIgnoreCase("month")) ? "Tháng này" : "Năm nay"))+"</span></h5>");
	    out.append("");
	    out.append("              <div class=\"d-flex align-items-center\">");
	    out.append("                <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
	    out.append("                  <i class=\"bi bi-cart\"></i>");
	    out.append("                </div>");
	    out.append("                <div class=\"ps-3\">");
	    out.append("                  <h6>"+ ProductUtils.formatNumber(dataCardMap.get("sales").get("now"))+"</h6>");

		double salesNow = dataCardMap.get("sales").get("now");
		double salesBefore = dataCardMap.get("sales").get("before");
		int salesGrowthPercentage = salesNow != 0 ? (int) ((salesNow - salesBefore)/salesNow * 100) : -100;

	    out.append("                  <span class=\"text-"+ (salesGrowthPercentage > 0 ? "success" : "danger")+" small pt-1 fw-bold\">"+ salesGrowthPercentage +"%</span> <span");
	    out.append("                    class=\"text-muted small pt-2 ps-1\">"+ (salesGrowthPercentage > 0 ? "tăng" : "giảm") +"</span>");
	    out.append("");
	    out.append("                </div>");
	    out.append("              </div>");
	    out.append("            </div>");
	    out.append("");
	    out.append("          </div>");
	    out.append("        </div><!-- End Sales Card -->");
	    out.append("");
	    out.append("        <!-- Revenue Card -->");
	    out.append("        <div class=\"col-md-4 col-12\">");
	    out.append("          <div class=\"card info-card revenue-card\">");
	    out.append("");
	    out.append("            <div class=\"filter\">");
	    out.append("              <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
	    out.append("              <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
	    out.append("                <li class=\"dropdown-header text-start\">");
	    out.append("                  <h6>Lọc</h6>");
	    out.append("                </li>");
	    out.append("");
	    out.append("                <li><a class=\"dropdown-item\" href=\""+urlSearch.toString().replace("revenue="+revenue, "revenue=day")+"\">Hôm Nay</a></li>");
	    out.append("                <li><a class=\"dropdown-item\" href=\""+urlSearch.toString().replace("revenue="+revenue, "revenue=month")+"\">Tháng Này</a></li>");
	    out.append("                <li><a class=\"dropdown-item\" href=\""+urlSearch.toString().replace("revenue="+revenue, "revenue=year")+"\">Năm Này</a></li>");
	    out.append("              </ul>");
	    out.append("            </div>");
	    out.append("");
	    out.append("            <div class=\"card-body\">");
	    out.append("              <h5 class=\"card-title\">Doanh Thu <span>| "+(revenue.equalsIgnoreCase("day") ? "Hôm nay" : ((revenue.equalsIgnoreCase("month")) ? "Tháng này" : "Năm nay"))+"</span></h5>");
	    out.append("");
	    out.append("              <div class=\"d-flex align-items-center\">");
	    out.append("                <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
	    out.append("                  <i class=\"bi bi-currency-dollar\"></i>");
	    out.append("                </div>");

		double revenueNow = dataCardMap.get("revenue").get("now");
		double revenueBefore = dataCardMap.get("revenue").get("before");
		int revenueGrowthPercentage = revenueNow != 0 ? (int) ((revenueNow - revenueBefore)/revenueNow * 100) : -100;
	    out.append("                <div class=\"ps-3\">");
	    out.append("                  <h6>"+ProductUtils.formatNumber(revenueNow)+"</h6>");
	    out.append("                  <span class=\"text-"+(revenueGrowthPercentage > 0 ? "success" : "danger")+" small pt-1 fw-bold\">"+revenueGrowthPercentage+"%</span> <span");
	    out.append("                    class=\"text-muted small pt-2 ps-1\">"+(revenueGrowthPercentage > 0 ? "tăng" : "giảm")+"</span>");
	    out.append("");
	    out.append("                </div>");
	    out.append("              </div>");
	    out.append("            </div>");
	    out.append("");
	    out.append("          </div>");
	    out.append("        </div><!-- End Revenue Card -->");
	    out.append("");
	    out.append("        <!-- Customers Card -->");
	    out.append("        <div class=\"col-md-4 col-12\">");
	    out.append("");
	    out.append("          <div class=\"card info-card customers-card\">");
	    out.append("");
	    out.append("            <div class=\"filter\">");
	    out.append("              <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
	    out.append("              <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
	    out.append("                <li class=\"dropdown-header text-start\">");
	    out.append("                  <h6>Filter</h6>");
	    out.append("                </li>");
	    out.append("");
	    out.append("                <li><a class=\"dropdown-item\" href=\""+urlSearch.toString().replace("customers="+customers, "customers=day")+"\">Hôm Nay</a></li>");
	    out.append("                <li><a class=\"dropdown-item\" href=\""+urlSearch.toString().replace("customers="+customers, "customers=month")+"\">Tháng Này</a></li>");
	    out.append("                <li><a class=\"dropdown-item\" href=\""+urlSearch.toString().replace("customers="+customers, "customers=year")+"\">Năm Này</a></li>");
	    out.append("              </ul>");
	    out.append("            </div>");
	    out.append("");
	    out.append("            <div class=\"card-body\">");
	    out.append("              <h5 class=\"card-title\">Khách Hàng <span>| "+(customers.equalsIgnoreCase("day") ? "Hôm nay" : ((customers.equalsIgnoreCase("month")) ? "Tháng này" : "Năm nay"))+"</span></h5>");
	    out.append("");
	    out.append("              <div class=\"d-flex align-items-center\">");
	    out.append("                <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">");
	    out.append("                  <i class=\"bi bi-people\"></i>");
	    out.append("                </div>");
		Double customersNow = dataCardMap.get("customers").get("now");
		double customersBefore = dataCardMap.get("customers").get("before");
		int customersGrowthPercentage = customersNow != 0 ? (int) ((customersNow - customersBefore)/customersNow * 100) : -100;
		out.append("                <div class=\"ps-3\">");
		out.append("                  <h6>"+customersNow.intValue()+"</h6>");
		out.append("                  <span class=\"text-"+(customersGrowthPercentage > 0 ? "success" : "danger")+" small pt-1 fw-bold\">"+customersGrowthPercentage+"%</span> <span");
		out.append("                    class=\"text-muted small pt-2 ps-1\">"+(customersGrowthPercentage > 0 ? "tăng" : "giảm")+"</span>");
		out.append("");
		out.append("                </div>");
	    out.append("              </div>");
	    out.append("");
	    out.append("            </div>");
	    out.append("          </div>");
	    out.append("");
	    out.append("        </div><!-- End Customers Card -->");
	    out.append("");
	    out.append("      </div>");
	    out.append("      <div class=\"row\">");
	    out.append("        <!-- Left side columns -->");
	    out.append("        <div class=\"col-lg-8\">");
	    out.append("          <div class=\"row\">");
	    out.append("");
	    out.append("            <!-- Reports -->");
	    out.append("            <div class=\"col-12\">");
	    out.append("              <div class=\"card\">");
	    out.append("");
	    out.append("                <div class=\"filter\">");
	    out.append("                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
	    out.append("                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
	    out.append("                    <li class=\"dropdown-header text-start\">");
	    out.append("                      <h6>Filter</h6>");
	    out.append("                    </li>");
	    out.append("");
	    out.append("                    <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>");
	    out.append("                    <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>");
	    out.append("                  </ul>");
	    out.append("                </div>");
	    out.append("");
	    out.append("                <div class=\"card-body p-0\">");
	    out.append("                  <h5 class=\"card-title ps-4\">Báo cáo <span>/Hôm nay</span></h5>");
	    out.append("");
	    out.append("                  <!-- Line Chart -->");
	    out.append("                  <div id=\"reportsChart\"></div>");
	    out.append("");
		out.append("                  <script>");
		out.append("                    document.addEventListener(\"DOMContentLoaded\", () => {");
		out.append("                      new ApexCharts(document.querySelector(\"#reportsChart\"), {");
		out.append("                        series: [{");
		out.append("                          name: 'Sales',");
		out.append("                          data: [192, 180, 200, 210, 190, 192, 185],");
		out.append("                          type: 'line'");
		out.append("                        }, {");
		out.append("                          name: 'Lợi Nhuận (VND)',");
		out.append("                          data: [2752600000, 2600000000, 2800000000, 2900000000, 2700000000, 2752600000, 2650000000],");
		out.append("                          type: 'line'");
		out.append("                        }, {");
		out.append("                          name: 'Khách Hàng',");
		out.append("                          data: [60, 55, 65, 70, 62, 60, 58],");
		out.append("                          type: 'line'");
		out.append("                        }],");
		out.append("                        chart: {");
		out.append("                          height: 350,");
		out.append("                          type: 'line',");
		out.append("                          toolbar: {");
		out.append("                            show: false");
		out.append("                          },");
		out.append("                          zoom: {");
		out.append("                            enabled: false");
		out.append("                          }");
		out.append("                        },");
		out.append("                        markers: {");
		out.append("                          size: 4");
		out.append("                        },");
		out.append("                        colors: ['#4154f1', '#2eca6a', '#ff771d'],");
		out.append("                        fill: {");
		out.append("                          type: 'gradient',");
		out.append("                          gradient: {");
		out.append("                            shadeIntensity: 1,");
		out.append("                            opacityFrom: 0.3,");
		out.append("                            opacityTo: 0.4,");
		out.append("                            stops: [0, 90, 100]");
		out.append("                          }");
		out.append("                        },");
		out.append("                        dataLabels: {");
		out.append("                          enabled: false");
		out.append("                        },");
		out.append("                        stroke: {");
		out.append("                          curve: 'smooth',");
		out.append("                          width: 2");
		out.append("                        },");
		out.append("                        xaxis: {");
		out.append("                          categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],");
		out.append("                          labels: {");
		out.append("                            show: true,");
		out.append("                            rotate: 0,");
		out.append("                            rotateAlways: true, offsetY: 5,");
		out.append("                            hideOverlappingLabels: false");
		out.append("                          },");
		out.append("                          tickAmount: 6");
		out.append("                        },");
		out.append("                        yaxis: [{");
		out.append("                          seriesName: 'Sales',");
		out.append("                          opposite: false,");
		out.append("                          title: {");
		out.append("                            text: 'Sales & Customers'");
		out.append("                          },");
		out.append("                          labels: {");
		out.append("                            formatter: function(val) { return val; }");
		out.append("                          }");
		out.append("                        }, {");
		out.append("                          seriesName: 'Revenue (VND)',");
		out.append("                          opposite: true,");
		out.append("                          title: {");
		out.append("                            text: 'Revenue (VND)'");
		out.append("                          },");
		out.append("                          labels: {");
		out.append("                            formatter: function(val) { return (val / 1000000).toFixed(0) + 'M'; }");
		out.append("                          }");
		out.append("                        }],");
		out.append("                        tooltip: {");
		out.append("                          y: {");
		out.append("                            formatter: function(val, { seriesIndex }) {");
		out.append("                              if (seriesIndex === 1) return (val / 1000000).toFixed(0) + 'M VND';");
		out.append("                              return val;");
		out.append("                            }");
		out.append("                          }");
		out.append("                        }");
		out.append("                      }).render();");
		out.append("                    });");
		out.append("                  </script>");
		out.append("                  <!-- End Line Chart -->");
	    out.append("");
	    out.append("                </div>");
	    out.append("");
	    out.append("              </div>");
	    out.append("            </div><!-- End Reports -->");
	    out.append("");
	    out.append("            <!-- Top Selling -->");
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
	    out.append("                    <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>");
	    out.append("                    <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>");
	    out.append("                    <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>");
	    out.append("                  </ul>");
	    out.append("                </div>");
	    out.append("");
	    out.append("                <div class=\"card-body pb-0\">");
	    out.append("                  <h5 class=\"card-title\">Bán Chạy Nhất <span>| Today</span></h5>");
	    out.append("");
	    out.append("                  <table class=\"table table-borderless\">");
	    out.append("                    <thead>");
	    out.append("                      <tr>");
	    out.append("                        <th scope=\"col\">Ảnh</th>");
	    out.append("                        <th scope=\"col\">Sản Phẩm</th>");
	    out.append("                        <th scope=\"col\">Gía</th>");
	    out.append("                        <th scope=\"col\">Lượt Bán</th>");
	    out.append("                      </tr>");
	    out.append("                    </thead>");
	    out.append("                    <tbody>");
	    out.append("                      <tr>");
	    out.append("                        <th scope=\"row\"><a href=\"#\"><img src=\"../admin/img/product-1.jpg\" alt=\"\"></a></th>");
	    out.append("                        <td><a href=\"#\" class=\"text-primary fw-bold\">Ut inventore ipsa voluptas nulla</a></td>");
	    out.append("                        <td>$64</td>");
	    out.append("                        <td class=\"fw-bold\">124</td>");

	    out.append("                      </tr>");
	    out.append("                      <tr>");
	    out.append("                        <th scope=\"row\"><a href=\"#\"><img src=\"../admin/img/product-2.jpg\" alt=\"\"></a></th>");
	    out.append("                        <td><a href=\"#\" class=\"text-primary fw-bold\">Exercitationem similique doloremque</a></td>");
	    out.append("                        <td>$46</td>");
	    out.append("                        <td class=\"fw-bold\">98</td>");

	    out.append("                      </tr>");
	    out.append("                      <tr>");
	    out.append("                        <th scope=\"row\"><a href=\"#\"><img src=\"../admin/img/product-3.jpg\" alt=\"\"></a></th>");
	    out.append("                        <td><a href=\"#\" class=\"text-primary fw-bold\">Doloribus nisi exercitationem</a></td>");
	    out.append("                        <td>$59</td>");
	    out.append("                        <td class=\"fw-bold\">74</td>");

	    out.append("                      </tr>");
	    out.append("                      <tr>");
	    out.append("                        <th scope=\"row\"><a href=\"#\"><img src=\"../admin/img/product-4.jpg\" alt=\"\"></a></th>");
	    out.append("                        <td><a href=\"#\" class=\"text-primary fw-bold\">Officiis quaerat sint rerum error</a></td>");
	    out.append("                        <td>$32</td>");
	    out.append("                        <td class=\"fw-bold\">63</td>");

	    out.append("                      </tr>");
	    out.append("                      <tr>");
	    out.append("                        <th scope=\"row\"><a href=\"#\"><img src=\"../admin/img/product-5.jpg\" alt=\"\"></a></th>");
	    out.append("                        <td><a href=\"#\" class=\"text-primary fw-bold\">Sit unde debitis delectus repellendus</a></td>");
	    out.append("                        <td>$79</td>");
	    out.append("                        <td class=\"fw-bold\">41</td>");

	    out.append("                      </tr>");
	    out.append("                    </tbody>");
	    out.append("                  </table>");
	    out.append("");
	    out.append("                </div>");
	    out.append("");
	    out.append("              </div>");
	    out.append("            </div><!-- End Top Selling -->");
	    out.append("");
	    out.append("          </div>");
	    out.append("        </div><!-- End Left side columns -->");
	    out.append("");
	    out.append("        <!-- Right side columns -->");
	    out.append("        <div class=\"col-md-4 new-customer\">");
	    out.append("");
	    out.append("          <div class=\"card p-4 mx-auto\">");
	    out.append("            <div class=\"d-flex justify-content-between align-items-center mb-4\">");
	    out.append("              <h2 class=\"text-slate-900 fw-semibold fs-5 mb-0\">Khách Hàng Mới</h2>");
	    out.append("              <i class=\"fas fa-ellipsis-h text-secondary\" aria-hidden=\"true\"></i>");
	    out.append("            </div>");
	    out.append("            <ul class=\"list-unstyled mb-0\">");

		for(User user : users){
			out.append("              <li class=\"d-flex justify-content-between align-items-center mb-4\">");
			out.append("                <div class=\"d-flex align-items-center gap-3\">");
			out.append("                  <img src=\""+(user.getGender().equals("Nam")
					? "../admin/img/avtboy.png" : "../admin/img/avtgirl.jpg")+"\"");
			out.append("                    alt=\"avtprofile\" class=\"avatar-img\" />");
			out.append("                  <div>");
			out.append("                    <p class=\"text-slate-900 fw-semibold mb-0\" style=\"font-size: 0.875rem;\">"+user.getFullname()+"</p>");
			out.append("                    <p class=\"text-slate-400 mb-0\" style=\"font-size: 0.75rem;\">Khách hàng</p>");
			out.append("                  </div>");
			out.append("                </div>");
			out.append("  				<div class=\"d-flex gap-3\">");
			out.append("<button class='btn btn-primary rounded-circle viewBtn me-2' ")
					.append("data-bs-toggle='modal' data-bs-target='#largeModal' ")
					.append("data-name='" + user.getFullname() + "' ")
					.append("data-gender='" + user.getGender() + "' ")
					.append("data-phone='" + user.getPhoneNumber() + "' ")
					.append("data-email='" + user.getEmail() + "' ")
					.append("data-address='" + user.getAddress() + "' ")
					.append("data-created='" + ProductUtils.formatDate(user.getCreateDate()) + "' ")
					.append("data-updated='" + ProductUtils.formatDate(user.getModifiedDate()) + "' ")
					.append("data-status='" + user.isActive() + "'>")
					.append("<i class='bi bi-eye'></i>")
					.append("</button>");
			out.append("    				</a>");
			out.append("  				</div>");
			out.append("              </li>");
		}


	    out.append("            </ul>");
	    out.append("          </div>");
	    out.append("");

		RequestDispatcher accountModalDispatcher = request.getRequestDispatcher("/admin/account-modal");
		accountModalDispatcher.include(request, response);

	    out.append("          <!-- News & Updates Traffic -->");
	    out.append("          <div class=\"card\">");
	    out.append("            <div class=\"filter\">");
	    out.append("              <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>");
	    out.append("              <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">");
	    out.append("                <li class=\"dropdown-header text-start\">");
	    out.append("                  <h6>Filter</h6>");
	    out.append("                </li>");
	    out.append("");
	    out.append("                <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>");
	    out.append("                <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>");
	    out.append("                <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>");
	    out.append("              </ul>");
	    out.append("            </div>");
	    out.append("");
	    out.append("            <div class=\"card-body pb-0\">");
	    out.append("              <h5 class=\"card-title\">News &amp; Updates <span>| Today</span></h5>");
	    out.append("");
	    out.append("              <div class=\"news\">");
	    out.append("                <div class=\"post-item clearfix\">");
	    out.append("                  <img src=\"../admin/img/news-1.jpg\" alt=\"\">");
	    out.append("                  <h4><a href=\"#\">Nihil blanditiis at in nihil autem</a></h4>");
	    out.append("                  <p>Sit recusandae non aspernatur laboriosam. Quia enim eligendi sed ut harum...</p>");
	    out.append("                </div>");
	    out.append("");
	    out.append("                <div class=\"post-item clearfix\">");
	    out.append("                  <img src=\"../admin/img/news-2.jpg\" alt=\"\">");
	    out.append("                  <h4><a href=\"#\">Quidem autem et impedit</a></h4>");
	    out.append("                  <p>Illo nemo neque maiores vitae officiis cum eum turos elan dries werona nande...</p>");
	    out.append("                </div>");
	    out.append("");
	    out.append("                <div class=\"post-item clearfix\">");
	    out.append("                  <img src=\"../admin/img/news-3.jpg\" alt=\"\">");
	    out.append("                  <h4><a href=\"#\">Id quia et et ut maxime similique occaecati ut</a></h4>");
	    out.append("                  <p>Fugiat voluptas vero eaque accusantium eos. Consequuntur sed ipsam et totam...</p>");
	    out.append("                </div>");
	    out.append("");
	    out.append("                <div class=\"post-item clearfix\">");
	    out.append("                  <img src=\"../admin/img/news-4.jpg\" alt=\"\">");
	    out.append("                  <h4><a href=\"#\">Laborum corporis quo dara net para</a></h4>");
	    out.append("                  <p>Qui enim quia optio. Eligendi aut asperiores enim repellendusvel rerum cuder...</p>");
	    out.append("                </div>");
	    out.append("");
	    out.append("                <div class=\"post-item clearfix\">");
	    out.append("                  <img src=\"../admin/img/news-5.jpg\" alt=\"\">");
	    out.append("                  <h4><a href=\"#\">Et dolores corrupti quae illo quod dolor</a></h4>");
	    out.append("                  <p>Odit ut eveniet modi reiciendis. Atque cupiditate libero beatae dignissimos eius...</p>");
	    out.append("                </div>");
	    out.append("");
	    out.append("              </div><!-- End sidebar recent posts-->");
	    out.append("");
	    out.append("            </div>");
	    out.append("          </div><!-- End News & Updates -->");
	    out.append("");
	    out.append("        </div><!-- End Right side columns -->");
	    out.append("");
	    out.append("      </div>");
	    out.append("    </section>");
	    out.append("");
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
