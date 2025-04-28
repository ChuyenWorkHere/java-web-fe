package servlet.admin.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/products-view")
public class ProductsView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductsView() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		request.setAttribute("view", "list");
		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
		headerDispatcher.include(request, response);

		out.append("<main id=\"main\" class=\"main\">");
		out.append("    <div class=\"pagetitle d-flex justify-content-between\">");
		out.append("      <h1>Sản Phẩm</h1>");
		out.append("      <nav class=\"d-flex align-items-center\" >");
		out.append("        <ol class=\"breadcrumb  mb-0\">");
		out.append(
				"          <li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-fill\"></i></a></li>");
		out.append("          <li class=\"breadcrumb-item active\">Sản phẩm</li>");
		out.append("        </ol>");
		out.append("      </nav>");
		out.append("    </div><!-- End Page Title -->");

		out.append("    <section class=\"section dashboard\">");
		out.append("      <div class=\"row\">");
		out.append("        <div class=\"col-12\">");
		out.append("          <div class=\"card shadow-sm pt-3 pe-3 ps-3 rounded-4\">");
		out.append("            <div class=\"row d-flex justify-content-between g-2 mb-2\">");
		out.append("              <!-- Bộ lọc -->");
		out.append("              <div class=\"col-12 col-md-4 col-lg-3\">");
		out.append(
				"                <input type=\"text\" class=\"form-control rounded-2\" placeholder=\"Nhập tên sản phẩm...\">");
		out.append("              </div>");
		out.append("              <div class=\"col-12 col-md-3 col-lg-2\">");
		out.append("                <select class=\"form-select rounded-2\">");
		out.append("                  <option value=\"\">Chọn khoảng giá</option>");
		out.append("                  <option value=\"1\">Dưới 100K</option>");
		out.append("                  <option value=\"2\">100K - 500K</option>");
		out.append("                  <option value=\"3\">500K - 1 Triệu</option>");
		out.append("                  <option value=\"4\">Trên 1 Triệu</option>");
		out.append("                </select>");
		out.append("              </div>");
		out.append("              <div class=\"col-12 col-md-3 col-lg-2\">");
		out.append("                <select class=\"form-select rounded-2\">");
		out.append("                  <option value=\"\">Chọn danh mục</option>");
		out.append("                  <option value=\"1\">Điện thoại</option>");
		out.append("                  <option value=\"2\">Laptop</option>");
		out.append("                  <option value=\"3\">Phụ kiện</option>");
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
		out.append(
				"                <button type=\"button\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#verticalycentered\">");
		out.append("                  Tìm kiếm");
		out.append("                </button>");
		out.append("              </div>");
		out.append("            </div>");
		out.append("            ");

		out.append("            <div class=\"card\">");
		out.append("              <div class=\"card-body\">");
		out.append("                <div class=\"table-responsive\">");
		out.append("                <table class=\"table table-bordered table-striped mt-4\">");
		out.append("                  <thead>");
		out.append("                    <tr>");
		out.append("                        <th scope=\"col\">Ảnh</th>");
		out.append("                        <th scope=\"col\">Tên sản phẩm</th>");
		out.append("                        <th scope=\"col\">Danh mục</th>");
		out.append("                        <th scope=\"col\">Số lượng</th>");
		out.append("                        <th scope=\"col\">Giá gốc</th>");
		out.append("                        <th scope=\"col\">Giá bán</th>");
		out.append("                        <th scope=\"col\">Trạng thái</th>");
		out.append("                        <th scope=\"col\">Chức năng</th>");
		out.append("                    </tr>");
		out.append("                  </thead>");
		out.append("                  <tbody>");
		out.append("                    <tr>");
		out.append(
				"                      <td class=\"align-middle\"><img src=\"img/pro8.jpg\" class=\"product-img\" alt=\"\"></td>");
		out.append(
				"                        <td class=\"align-middle\"><a href=\"single-product\" class=\"text-dark text-decoration-none hover-primary\">");
		out.append("                          Tủ Sách");
		out.append("                        </a></td>");
		out.append("                        <td class=\"align-middle\">Nội Thất Phòng Ngủ</td>");
		out.append("                        <td class=\"align-middle\">50</td>");
		out.append("                        <td class=\"align-middle\">300000đ</td>");
		out.append("                        <td class=\"align-middle\">250000đ</td>");
		out.append("                        <td class=\"align-middle\">Hoạt động</td>");
		out.append("                        <td class=\"align-middle\">                     ");
		out.append("                          <a href=\"edit-product.html\">");
		out.append("                            <i class=\"bi bi-pencil-square fs-6 text-primary ms-2 pointer\"></i>");
		out.append("                          </a>");
		out.append("                          <i class=\"bi bi-trash fs-6 text-danger ms-2 pointer\"></i>");
		out.append("                        </td>");
		out.append("                    </tr>");
		out.append("                    <tr>");
		out.append(
				"                      <td class=\"align-middle\"><img src=\"img/pro8.jpg\" class=\"product-img\" alt=\"\"></td>");
		out.append(
				"                        <td class=\"align-middle\"><a href=\"single-product\" class=\"text-dark text-decoration-none hover-primary\">");
		out.append("                          Giường Gỗ Hương");
		out.append("                        </a></td>");
		out.append("                        <td class=\"align-middle\">Nội Thất Phòng Ngủ</td>");
		out.append("                        <td class=\"align-middle\">50</td>");
		out.append("                        <td class=\"align-middle\">300000đ</td>");
		out.append("                        <td class=\"align-middle\">250000đ</td>");
		out.append("                        <td class=\"align-middle\">Hoạt động</td>");
		out.append("                        <td class=\"align-middle\">                     ");
		out.append("                          <a href=\"edit-product.html\">");
		out.append("                            <i class=\"bi bi-pencil-square fs-6 text-primary ms-2 pointer\"></i>");
		out.append("                          </a>");
		out.append("                          <i class=\"bi bi-trash fs-6 text-danger ms-2 pointer\"></i>");
		out.append("                        </td>");
		out.append("                    </tr>");
		out.append("                    <tr>");
		out.append(
				"                      <td class=\"align-middle\"><img src=\"img/pro8.jpg\" class=\"product-img\" alt=\"\"></td>");
		out.append(
				"                        <td class=\"align-middle\"><a href=\"single-product\" class=\"text-dark text-decoration-none hover-primary\">");
		out.append("                          Giường Gỗ Hương");
		out.append("                        </a></td>");
		out.append("                        <td class=\"align-middle\">Nội Thất Phòng Ngủ</td>");
		out.append("                        <td class=\"align-middle\">50</td>");
		out.append("                        <td class=\"align-middle\">300000đ</td>");
		out.append("                        <td class=\"align-middle\">250000đ</td>");
		out.append("                        <td class=\"align-middle\">Hoạt động</td>");
		out.append("                        <td class=\"align-middle\">                     ");
		out.append("                          <a href=\"edit-product.html\">");
		out.append("                            <i class=\"bi bi-pencil-square fs-6 text-primary ms-2 pointer\"></i>");
		out.append("                          </a>");
		out.append("                          <i class=\"bi bi-trash fs-6 text-danger ms-2 pointer\"></i>");
		out.append("                        </td>");
		out.append("                    </tr>");
		out.append("                    <tr>");
		out.append(
				"                      <td class=\"align-middle\"><img src=\"img/pro8.jpg\" class=\"product-img\" alt=\"\"></td>");
		out.append(
				"                        <td class=\"align-middle\"><a href=\"single-product\" class=\"text-dark text-decoration-none hover-primary\">");
		out.append("                          Đèn Bàn");
		out.append("                        </a></td>");
		out.append("                        <td class=\"align-middle\">Nội Thất Phòng Ngủ</td>");
		out.append("                        <td class=\"align-middle\">50</td>");
		out.append("                        <td class=\"align-middle\">300000đ</td>");
		out.append("                        <td class=\"align-middle\">250000đ</td>");
		out.append("                        <td class=\"align-middle\">Hoạt động</td>");
		out.append("                        <td class=\"align-middle\">                     ");
		out.append("                          <a href=\"edit-product.html\">");
		out.append("                            <i class=\"bi bi-pencil-square fs-6 text-primary ms-2 pointer\"></i>");
		out.append("                          </a>");
		out.append("                          <i class=\"bi bi-trash fs-6 text-danger ms-2 pointer\"></i>");
		out.append("                        </td>");
		out.append("                    </tr>");
		out.append("                    <tr>");
		out.append(
				"                      <td class=\"align-middle\"><img src=\"img/pro8.jpg\" class=\"product-img\" alt=\"\"></td>");
		out.append(
				"                        <td class=\"align-middle\"><a href=\"single-product\" class=\"text-dark text-decoration-none hover-primary\">");
		out.append("                          Giường Gỗ Hương");
		out.append("                        </a></td>");
		out.append("                        <td class=\"align-middle\">Nội Thất Phòng Ngủ</td>");
		out.append("                        <td class=\"align-middle\">50</td>");
		out.append("                        <td class=\"align-middle\">300000đ</td>");
		out.append("                        <td class=\"align-middle\">250000đ</td>");
		out.append("                        <td class=\"align-middle\">Hoạt động</td>");
		out.append("                        <td class=\"align-middle\">                     ");
		out.append("                          <a href=\"edit-product.html\">");
		out.append("                            <i class=\"bi bi-pencil-square fs-6 text-primary ms-2 pointer\"></i>");
		out.append("                          </a>");
		out.append("                          <i class=\"bi bi-trash fs-6 text-danger ms-2 pointer\"></i>");
		out.append("                        </td>");
		out.append("                    </tr>");
		out.append("                    <tr>");
		out.append(
				"                      <td class=\"align-middle\"><img src=\"img/pro8.jpg\" class=\"product-img\" alt=\"\"></td>");
		out.append(
				"                        <td class=\"align-middle\"><a href=\"single-product\" class=\"text-dark text-decoration-none hover-primary\">");
		out.append("                          Giường Gỗ Hương");
		out.append("                        </a></td>");
		out.append("                        <td class=\"align-middle\">Nội Thất Phòng Ngủ</td>");
		out.append("                        <td class=\"align-middle\">50</td>");
		out.append("                        <td class=\"align-middle\">300000đ</td>");
		out.append("                        <td class=\"align-middle\">250000đ</td>");
		out.append("                        <td class=\"align-middle\">Hoạt động</td>");
		out.append("                        <td class=\"align-middle\">                     ");
		out.append("                          <a href=\"edit-product.html\">");
		out.append("                            <i class=\"bi bi-pencil-square fs-6 text-primary ms-2 pointer\"></i>");
		out.append("                          </a>");
		out.append("                          <i class=\"bi bi-trash fs-6 text-danger ms-2 pointer\"></i>");
		out.append("                        </td>");
		out.append("                    </tr>");
		out.append("                    <tr>");
		out.append(
				"                      <td class=\"align-middle\"><img src=\"img/pro8.jpg\" class=\"product-img\" alt=\"\"></td>");
		out.append(
				"                        <td class=\"align-middle\"><a href=\"single-product\" class=\"text-dark text-decoration-none hover-primary\">");
		out.append("                          Giường Gỗ Hương");
		out.append("                        </a></td>");
		out.append("                        <td class=\"align-middle\">Nội Thất Phòng Ngủ</td>");
		out.append("                        <td class=\"align-middle\">50</td>");
		out.append("                        <td class=\"align-middle\">300000đ</td>");
		out.append("                        <td class=\"align-middle\">250000đ</td>");
		out.append("                        <td class=\"align-middle\">Hoạt động</td>");
		out.append("                        <td class=\"align-middle\">                     ");
		out.append("                          <a href=\"edit-product.html\">");
		out.append("                            <i class=\"bi bi-pencil-square fs-6 text-primary ms-2 pointer\"></i>");
		out.append("                          </a>");
		out.append("                          <i class=\"bi bi-trash fs-6 text-danger ms-2 pointer\"></i>");
		out.append("                        </td>");
		out.append("                    </tr>");
		out.append("                    <tr>");
		out.append(
				"                      <td class=\"align-middle\"><img src=\"img/pro8.jpg\" class=\"product-img\" alt=\"\"></td>");
		out.append(
				"                        <td class=\"align-middle\"><a href=\"single-product\" class=\"text-dark text-decoration-none hover-primary\">");
		out.append("                          Tủ Sách");
		out.append("                        </a></td>");
		out.append("                        <td class=\"align-middle\">Nội Thất Phòng Ngủ</td>");
		out.append("                        <td class=\"align-middle\">50</td>");
		out.append("                        <td class=\"align-middle\">300000đ</td>");
		out.append("                        <td class=\"align-middle\">250000đ</td>");
		out.append("                        <td class=\"align-middle\">Hoạt động</td>");
		out.append("                        <td class=\"align-middle\">                     ");
		out.append("                          <a href=\"edit-product.html\">");
		out.append("                            <i class=\"bi bi-pencil-square fs-6 text-primary ms-2 pointer\"></i>");
		out.append("                          </a>");
		out.append("                          <i class=\"bi bi-trash fs-6 text-danger ms-2 pointer\"></i>");
		out.append("                        </td>");
		out.append("                    </tr>");
		out.append("                  </tbody>");
		out.append("                </table>");
		out.append("              </div>");
		out.append("                <!-- End Default Table Example -->");
		out.append("              </div>");
		out.append("            </div>");

		out.append("            <nav aria-label=\"Page navigation\">");
		out.append("              <ul class=\"pagination pagination-sm justify-content-end mt-2\">");
		out.append("                <li class=\"page-item mx-1\">");
		out.append("                  <a class=\"page-link rounded-1\" href=\"#\" aria-label=\"Previous\">");
		out.append("                    <span aria-hidden=\"true\">&laquo;</span>");
		out.append("                  </a>");
		out.append("                </li>");
		out.append(
				"                <li class=\"page-item mx-1\"><a class=\"page-link active rounded-1\" href=\"#\">1</a></li>");
		out.append(
				"                <li class=\"page-item mx-1\"><a class=\"page-link rounded-1\" href=\"#\">2</a></li>");
		out.append(
				"                <li class=\"page-item mx-1\"><a class=\"page-link rounded-1\" href=\"#\">3</a></li>");
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
