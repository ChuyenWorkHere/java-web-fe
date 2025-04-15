package servlet.admin.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/admin/accounts-view")
public class AccountsView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AccountsView() {
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
	    out.append("    <div class=\"pagetitle\">");
	    out.append("      <nav class=\"d-flex justify-content-between align-items-center flex-wrap\">");
	    out.append("        <ol class=\"breadcrumb fs-6\">");
	    out.append("          <li class=\"breadcrumb-item\"><a href=\"index.html\">Home</a></li>");
	    out.append("          <li class=\"breadcrumb-item active\">Tài khoản</li>");
	    out.append("        </ol>");
	    out.append("      </nav>");
	    out.append("    </div>");
	    
	    out.append("    <section class=\"section dashboard\">");
	    out.append("      <div class=\"row\">");
	    out.append("        <div class=\"col-12\">");
	    out.append("          <div class=\"card shadow-sm pt-3 pe-3 ps-3 rounded-4\">");
	    out.append("            <div class=\"row d-flex justify-content-between g-2 mb-2\">");
	    out.append("              <!-- Bộ lọc -->");
	    out.append("              <div class=\"col-12 col-md-4 col-lg-3\">");
	    out.append("                <input type=\"text\" class=\"form-control rounded-2\" placeholder=\"Nhập tên người dùng...\">");
	    out.append("              </div>");
	    out.append("              <div class=\"col-12 col-md-3 col-lg-2\">");
	    out.append("                <select class=\"form-select rounded-2\">");
	    out.append("                  <option value=\"\">Phân loại</option>");
	    out.append("                  <option value=\"1\">Khách hàng</option>");
	    out.append("                  <option value=\"2\">Quản trị</option>");
	    out.append("                </select>");
	    out.append("              </div>");
	    out.append("              <div class=\"col-12 col-md-3 col-lg-2\">");
	    out.append("                <select class=\"form-select rounded-2\">");
	    out.append("                  <option value=\"1\">Hoạt động</option>");
	    out.append("                  <option value=\"2\">Không hoạt động</option>");
	    out.append("                </select>");
	    out.append("              </div>");
	    out.append("            ");
	    out.append("              <!-- Nút thêm mới -->");
	    out.append("              <div class=\"col-12 col-md-2 d-flex justify-content-end\">");
	    out.append("                <button type=\"button\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#verticalycentered\">");
	    out.append("                  Tìm kiếm");
	    out.append("                </button>");
	    out.append("              </div>");
	    out.append("            </div>");
	    
	    out.append("            <div class=\"card\">");
	    out.append("              <div class=\"card-body\">");
	    out.append("                <div class=\"table-responsive\">");
	    out.append("                  <table class=\"table\">");
	    out.append("                    <thead>");
	    out.append("                      <tr>");
	    out.append("                        <th scope=\"col\">Id</th>");
	    out.append("                        <th scope=\"col\">Họ tên</th>");
	    out.append("                        <th scope=\"col\">Vai trò</th>");
	    out.append("                        <th scope=\"col\">Số điện thoại</th>");
	    out.append("                        <th scope=\"col\">Email</th>");
	    out.append("                        <th scope=\"col\">Địa chỉ</th>");
	    out.append("                        <th scope=\"col\">Trạng thái</th>");
	    out.append("                        <th scope=\"col\">Chức năng</th>");
	    out.append("                      </tr>");
	    out.append("                    </thead>");
	    out.append("                    <tbody>");
	    out.append("                      <tr>");
	    out.append("                        <td class=\"align-middle\">101</td>  ");
	    out.append("                        <td class=\"align-middle\">");
	    out.append("                          <a href=\"users-profile.html\">Đặng Ánh Nhật</a>");
	    out.append("                        </td>");
	    out.append("                        <td class=\"align-middle\">Khách hàng</td>");
	    out.append("                        <td class=\"align-middle\">0317564354</td>");
	    out.append("                        <td class=\"align-middle\">nhat@gmail.com</td>");
	    out.append("                        <td class=\"align-middle\">Nguyên Xá, Bắc Từ Liêm, Hà Nội</td>");
	    out.append("                        <td class=\"align-middle\">Hoạt động</td>");
	    out.append("                        <td class=\"align-middle\">                     ");
	    out.append("                          <i class=\"bi bi-pencil-square fs-6 text-primary ms-2 pointer\"></i>");
	    out.append("                          <i class=\"bi bi-trash fs-6 text-danger ms-2 pointer\"></i>");
	    out.append("                        </td>");
	    out.append("                      </tr>");
	    out.append("                      <tr>");
	    out.append("                        <td class=\"align-middle\">101</td>");
	    out.append("                        <td class=\"align-middle\">");
	    out.append("                          <a href=\"users-profile.html\">Đặng Ánh Nhật</a>");
	    out.append("                        </td>");
	    out.append("                        <td class=\"align-middle\">Khách hàng</td>");
	    out.append("                        <td class=\"align-middle\">0317564354</td>");
	    out.append("                        <td class=\"align-middle\">nhat@gmail.com</td>");
	    out.append("                        <td class=\"align-middle\">Nguyên Xá, Bắc Từ Liêm, Hà Nội</td>");
	    out.append("                        <td class=\"align-middle\">Hoạt động</td>");
	    out.append("                        <td class=\"align-middle\">                     ");
	    out.append("                          <i class=\"bi bi-pencil-square fs-6 text-primary ms-2 pointer\"></i>");
	    out.append("                          <i class=\"bi bi-trash fs-6 text-danger ms-2 pointer\"></i>");
	    out.append("                        </td>");
	    out.append("                      </tr>");
	    out.append("                      <tr>");
	    out.append("                        <td class=\"align-middle\">101</td>");
	    out.append("                        <td class=\"align-middle\">");
	    out.append("                          <a href=\"users-profile.html\">Đặng Ánh Nhật</a>");
	    out.append("                        </td>");
	    out.append("                        <td class=\"align-middle\">Khách hàng</td>");
	    out.append("                        <td class=\"align-middle\">0317564354</td>");
	    out.append("                        <td class=\"align-middle\">nhat@gmail.com</td>");
	    out.append("                        <td class=\"align-middle\">Nguyên Xá, Bắc Từ Liêm, Hà Nội</td>");
	    out.append("                        <td class=\"align-middle\">Hoạt động</td>");
	    out.append("                        <td class=\"align-middle\">                     ");
	    out.append("                          <i class=\"bi bi-pencil-square fs-6 text-primary ms-2 pointer\"></i>");
	    out.append("                          <i class=\"bi bi-trash fs-6 text-danger ms-2 pointer\"></i>");
	    out.append("                        </td>");
	    out.append("                      </tr>");
	    out.append("                      <tr>");
	    out.append("                        <td class=\"align-middle\">101</td>");
	    out.append("                          ");
	    out.append("                        <td class=\"align-middle\">Đặng Ánh Nhật</td>");
	    out.append("                        <td class=\"align-middle\">Khách hàng</td>");
	    out.append("                        <td class=\"align-middle\">0317564354</td>");
	    out.append("                        <td class=\"align-middle\">nhat@gmail.com</td>");
	    out.append("                        <td class=\"align-middle\">Nguyên Xá, Bắc Từ Liêm, Hà Nội</td>");
	    out.append("                        <td class=\"align-middle\">Hoạt động</td>");
	    out.append("                        <td class=\"align-middle\">                     ");
	    out.append("                          <i class=\"bi bi-pencil-square fs-6 text-primary ms-2 pointer\"></i>");
	    out.append("                          <i class=\"bi bi-trash fs-6 text-danger ms-2 pointer\"></i>");
	    out.append("                        </td>");
	    out.append("                      </tr>");
	    out.append("                      <tr>");
	    out.append("                        <td class=\"align-middle\">101</td>");
	    out.append("                          ");
	    out.append("                        <td class=\"align-middle\">Đặng Ánh Nhật</td>");
	    out.append("                        <td class=\"align-middle\">Khách hàng</td>");
	    out.append("                        <td class=\"align-middle\">0317564354</td>");
	    out.append("                        <td class=\"align-middle\">nhat@gmail.com</td>");
	    out.append("                        <td class=\"align-middle\">Nguyên Xá, Bắc Từ Liêm, Hà Nội</td>");
	    out.append("                        <td class=\"align-middle\">Hoạt động</td>");
	    out.append("                        <td class=\"align-middle\">                     ");
	    out.append("                          <i class=\"bi bi-pencil-square fs-6 text-primary ms-2 pointer\"></i>");
	    out.append("                          <i class=\"bi bi-trash fs-6 text-danger ms-2 pointer\"></i>");
	    out.append("                        </td>");
	    out.append("                      </tr>");
	    out.append("                      <tr>");
	    out.append("                        <td class=\"align-middle\">101</td>");
	    out.append("                          ");
	    out.append("                        <td class=\"align-middle\">Đặng Ánh Nhật</td>");
	    out.append("                        <td class=\"align-middle\">Khách hàng</td>");
	    out.append("                        <td class=\"align-middle\">0317564354</td>");
	    out.append("                        <td class=\"align-middle\">nhat@gmail.com</td>");
	    out.append("                        <td class=\"align-middle\">Nguyên Xá, Bắc Từ Liêm, Hà Nội</td>");
	    out.append("                        <td class=\"align-middle\">Hoạt động</td>");
	    out.append("                        <td class=\"align-middle\">                     ");
	    out.append("                          <i class=\"bi bi-pencil-square fs-6 text-primary ms-2 pointer\"></i>");
	    out.append("                          <i class=\"bi bi-trash fs-6 text-danger ms-2 pointer\"></i>");
	    out.append("                        </td>");
	    out.append("                      </tr>");
	    out.append("                      <tr>");
	    out.append("                        <td class=\"align-middle\">101</td>");
	    out.append("                          ");
	    out.append("                        <td class=\"align-middle\">Đặng Ánh Nhật</td>");
	    out.append("                        <td class=\"align-middle\">Khách hàng</td>");
	    out.append("                        <td class=\"align-middle\">0317564354</td>");
	    out.append("                        <td class=\"align-middle\">nhat@gmail.com</td>");
	    out.append("                        <td class=\"align-middle\">Nguyên Xá, Bắc Từ Liêm, Hà Nội</td>");
	    out.append("                        <td class=\"align-middle\">Hoạt động</td>");
	    out.append("                        <td class=\"align-middle\">                     ");
	    out.append("                          <i class=\"bi bi-pencil-square fs-6 text-primary ms-2 pointer\"></i>");
	    out.append("                          <i class=\"bi bi-trash fs-6 text-danger ms-2 pointer\"></i>");
	    out.append("                        </td>");
	    out.append("                      </tr>");
	    out.append("                      <tr>");
	    out.append("                        <td class=\"align-middle\">101</td>");
	    out.append("                          ");
	    out.append("                        <td class=\"align-middle\">Đặng Ánh Nhật</td>");
	    out.append("                        <td class=\"align-middle\">Khách hàng</td>");
	    out.append("                        <td class=\"align-middle\">0317564354</td>");
	    out.append("                        <td class=\"align-middle\">nhat@gmail.com</td>");
	    out.append("                        <td class=\"align-middle\">Nguyên Xá, Bắc Từ Liêm, Hà Nội</td>");
	    out.append("                        <td class=\"align-middle\">Hoạt động</td>");
	    out.append("                        <td class=\"align-middle\">                     ");
	    out.append("                          <i class=\"bi bi-pencil-square fs-6 text-primary ms-2 pointer\"></i>");
	    out.append("                          <i class=\"bi bi-trash fs-6 text-danger ms-2 pointer\"></i>");
	    out.append("                        </td>");
	    out.append("                      </tr>");
	    out.append("                    </tbody>");
	    out.append("                  </table>");
	    out.append("                </div>");
	    out.append("              </div>");
	    out.append("            </div>");
	    
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
	    out.append("  </main>");

		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
		footerDispatcher.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
