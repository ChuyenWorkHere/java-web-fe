package servlet.admin.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/admin/categories-view")
public class CategoriesView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CategoriesView() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");		 
		
		PrintWriter out = response.getWriter();
		
		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
	    headerDispatcher.include(request, response);

	    out.append("<main id=\"main\" class=\"main\">");
	    
	    out.append("    <div class=\"pagetitle d-flex justify-content-between\">");
	    out.append("      <h1>Danh mục</h1>");
	    out.append("      <nav class=\"d-flex align-items-center\" >");
	    out.append("        <ol class=\"breadcrumb  mb-0\">");
	    out.append("          <li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-fill\"></i></a></li>");
	    out.append("          <li class=\"breadcrumb-item active\">Danh mục</li>");
	    out.append("        </ol>");
	    out.append("      </nav>");
	    out.append("    </div><!-- End Page Title -->");
	    
	    out.append("    <section class=\"section dashboard\">");
	    out.append("      <div class=\"row\">");
	    out.append("        <div class=\"col-12\">");
	    out.append("          <!-- Bọc toàn bộ nội dung trong card để có bo cong -->");
	    out.append("          <div class=\"card shadow-sm pt-3 pe-3 ps-3 rounded-4\">");
	    out.append("            <!-- Thanh tìm kiếm và chọn trạng thái -->");
	    out.append("            <div class=\"d-flex justify-content-between flex-wrap align-items-center mb-2\">");
	    out.append("              <!-- Tìm danh mục -->");
	    out.append("              <div class=\"col-12 col-md-4 mb-2 mb-md-0\">");
	    out.append("                <input type=\"text\" class=\"form-control rounded-2 w-100\" placeholder=\"Tìm danh mục...\">");
	    out.append("              </div>");
	    out.append("              <!-- Chọn trạng thái -->");
	    out.append("              <div class=\"col-12 col-md-2 d-flex justify-content-end\">");
	    out.append("                <button type=\"button\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#verticalycentered\">");
	    out.append("                  Thêm Mới");
	    out.append("                </button>");
	    out.append("                <div class=\"modal fade\" id=\"verticalycentered\" tabindex=\"-1\" style=\"display: none;\" aria-hidden=\"true\">");
	    out.append("                  <div class=\"modal-dialog modal-dialog-centered\">");
	    out.append("                    <div class=\"modal-content\">");
	    out.append("                      <div class=\"modal-header\">");
	    out.append("                        <h5 class=\"modal-title\">Thêm mới danh mục</h5>");
	    out.append("                        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
	    out.append("                      </div>");
	    out.append("                      <div class=\"modal-body\">");
	    out.append("                        <div class=\"card mb-0\">");
	    out.append("                          <div class=\"card-body mt-4\">");
	    out.append("                            <!-- Floating Labels Form -->");
	    out.append("                            <form class=\"row g-3\">");
	    out.append("                              <div class=\"col-md-12\">");
	    out.append("                                <div class=\"form-floating\">");
	    out.append("                                  <input type=\"text\" class=\"form-control\" id=\"floatingName\" placeholder=\"Your Name\">");
	    out.append("                                  <label for=\"floatingName\">Tên danh mục</label>");
	    out.append("                                </div>");
	    out.append("                              </div>");
	    out.append("                              ");
	    out.append("                              <div class=\"col-12\">");
	    out.append("                                <div class=\"form-floating\">");
	    out.append("                                  <textarea class=\"form-control\" placeholder=\"Address\" id=\"floatingTextarea\" style=\"height: 100px;\"></textarea>");
	    out.append("                                  <label for=\"floatingTextarea\">Mô tả</label>");
	    out.append("                                </div>");
	    out.append("                              </div>");
	    out.append("                            </form><!-- End floating Labels Form -->");
	    out.append("                          </div>");
	    out.append("                        </div>");
	    out.append("                      </div>");
	    out.append("                      <div class=\"modal-footer\">");
	    out.append("                        <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Đóng</button>");
	    out.append("                        <button type=\"button\" class=\"btn btn-primary\">Thêm mới</button>");
	    out.append("                      </div>");
	    out.append("                    </div>");
	    out.append("                  </div>");
	    out.append("                </div>");
	    out.append("              </div>");
	    out.append("            </div>");
	    out.append("            <!-- Bảng danh mục -->");
	    out.append("            <div class=\"card\">");
	    out.append("              <div class=\"card-body\">");
	    out.append("                <div class=\"table-responsive\">");
	    out.append("                <table class=\"table table-bordered table-striped mt-4\">");
	    out.append("                  <thead>");
	    out.append("                    <tr>");
	    out.append("                      <th scope=\"col\" class=\"col-1\">#</th>");
	    out.append("                      <th scope=\"col\" class=\"col-2\">Danh Mục</th>");
	    out.append("                      <th scope=\"col\" class=\"col-2\">SL. Sản Phẩm</th>");
	    out.append("                      <th scope=\"col\" class=\"col-5\">Mô Tả</th>");
	    out.append("                      <th scope=\"col\" class=\"col-2\">Thao Tác</th>");
	    out.append("                    </tr>");
	    out.append("                  </thead>");
	    out.append("                  <tbody>");
	    out.append("                    <tr>");
	    out.append("                      <td>1</td>");
	    out.append("                      <td>Unity Pugh</td>");
	    out.append("                      <td>9958</td>");
	    out.append("                      <td>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Quod nam voluptatem harum?</td>");
	    out.append("                      <td>");
	    out.append("                        <i class=\"bi bi-pencil-square fs-5 text-primary  pointer\"></i>");
	    out.append("                        <i class=\"bi bi-trash fs-5 text-danger ms-3 pointer\"></i>");
	    out.append("                      </td>");
	    out.append("                    </tr>");
	    out.append("                    <tr>");
	    out.append("                      <td>2</td>");
	    out.append("                      <td>Olivia Carter</td>");
	    out.append("                      <td>7823</td>");
	    out.append("                      <td>Temporibus voluptatem magni recusandae, adipisci ab placeat similique?</td>");
	    out.append("                      <td>");
	    out.append("                        <i class=\"bi bi-pencil-square fs-5 text-primary pointer\"></i>");
	    out.append("                        <i class=\"bi bi-trash fs-5 text-danger ms-3 pointer\"></i>");
	    out.append("                      </td>");
	    out.append("                    </tr>");
	    out.append("                    <tr>");
	    out.append("                      <td>3</td>");
	    out.append("                      <td>Michael Johnson</td>");
	    out.append("                      <td>10567</td>");
	    out.append("                      <td>Molestiae incidunt blanditiis laudantium aperiam iusto rem?</td>");
	    out.append("                      <td>");
	    out.append("                        <i class=\"bi bi-pencil-square fs-5 text-primary pointer\"></i>");
	    out.append("                        <i class=\"bi bi-trash fs-5 text-danger ms-3 pointer\"></i>");
	    out.append("                      </td>");
	    out.append("                    </tr>");
	    out.append("                    <tr>");
	    out.append("                      <td>4</td>");
	    out.append("                      <td>Sophia Bennett</td>");
	    out.append("                      <td>5690</td>");
	    out.append("                      <td>Eligendi optio ipsa excepturi quia impedit repellendus doloremque.</td>");
	    out.append("                      <td>");
	    out.append("                        <i class=\"bi bi-pencil-square fs-5 text-primary pointer\"></i>");
	    out.append("                        <i class=\"bi bi-trash fs-5 text-danger ms-3 pointer\"></i>");
	    out.append("                      </td>");
	    out.append("                    </tr>");
	    out.append("                    <tr>");
	    out.append("                      <td>5</td>");
	    out.append("                      <td>James Wilson</td>");
	    out.append("                      <td>4321</td>");
	    out.append("                      <td>Consectetur doloribus reprehenderit odio, provident fugiat animi dignissimos?</td>");
	    out.append("                      <td>");
	    out.append("                        <i class=\"bi bi-pencil-square fs-5 text-primary pointer\"></i>");
	    out.append("                        <i class=\"bi bi-trash fs-5 text-danger ms-3 pointer\"></i>");
	    out.append("                      </td>");
	    out.append("                    </tr>");
	    out.append("                    <tr>");
	    out.append("                      <td>6</td>");
	    out.append("                      <td>Emma Watson</td>");
	    out.append("                      <td>9876</td>");
	    out.append("                      <td>Suscipit necessitatibus earum officiis deleniti assumenda deserunt maxime?</td>");
	    out.append("                      <td>");
	    out.append("                        <i class=\"bi bi-pencil-square fs-5 text-primary pointer\"></i>");
	    out.append("                        <i class=\"bi bi-trash fs-5 text-danger ms-3 pointer\"></i>");
	    out.append("                      </td>");
	    out.append("                    </tr>");
	    out.append("                    <tr>");
	    out.append("                      <td>7</td>");
	    out.append("                      <td>William Brown</td>");
	    out.append("                      <td>12034</td>");
	    out.append("                      <td>Voluptatem adipisci doloremque quidem rem pariatur esse quas?</td>");
	    out.append("                      <td>");
	    out.append("                        <i class=\"bi bi-pencil-square fs-5 text-primary pointer\"></i>");
	    out.append("                        <i class=\"bi bi-trash fs-5 text-danger ms-3 pointer\"></i>");
	    out.append("                      </td>");
	    out.append("                    </tr>");
	    out.append("                    <tr>");
	    out.append("                      <td>8</td>");
	    out.append("                      <td>Ava Martinez</td>");
	    out.append("                      <td>7654</td>");
	    out.append("                      <td>Consequatur at inventore velit fugiat esse? Officia?</td>");
	    out.append("                      <td>");
	    out.append("                        <i class=\"bi bi-pencil-square fs-5 text-primary pointer\"></i>");
	    out.append("                        <i class=\"bi bi-trash fs-5 text-danger ms-3 pointer\"></i>");
	    out.append("                      </td>");
	    out.append("                    </tr>");
	    out.append("                  </tbody>");
	    out.append("                </table>");
	    out.append("              </div>");
	    out.append("                <!-- End Default Table Example -->");
	    out.append("              </div>");
	    out.append("            </div>");
	    out.append("            <!-- Phân trang căn phải -->");
	    out.append("            <nav aria-label=\"Page navigation\">");
	    out.append("              <ul class=\"pagination pagination-sm justify-content-end mt-2\">");
	    out.append("                <li class=\"page-item mx-1\">");
	    out.append("                  <a class=\"page-link rounded-1\" href=\"#\" aria-label=\"Previous\">");
	    out.append("                    <span aria-hidden=\"true\">&laquo;</span>");
	    out.append("                  </a>");
	    out.append("                </li>");
	    out.append("                <li class=\"page-item mx-1\"><a class=\"page-link active rounded-1\" href=\"#\">1</a></li>");
	    out.append("                <li class=\"page-item mx-1\"><a class=\"page-link rounded-1\" href=\"#\">2</a></li>");
	    out.append("                <li class=\"page-item mx-1\"><a class=\"page-link rounded-1\" href=\"#\">3</a></li>");
	    out.append("                <li class=\"page-item mx-1\">");
	    out.append("                  <a class=\"page-link rounded-1\" href=\"#\" aria-label=\"Next\">");
	    out.append("                    <span aria-hidden=\"true\">&raquo;</span>");
	    out.append("                  </a>");
	    out.append("                </li>");
	    out.append("              </ul>");
	    out.append("            </nav>");
	    out.append("          </div>");
	    out.append("        </div>");
	    out.append("      </div>");
	    out.append("    </section>");
	    
	    out.append("  </main><!-- End #main -->");

		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
		footerDispatcher.include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
