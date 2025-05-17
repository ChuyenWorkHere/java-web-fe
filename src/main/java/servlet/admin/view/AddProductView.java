package servlet.admin.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/add-product-view")
public class AddProductView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddProductView() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		request.setAttribute("view", "add");
		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
		headerDispatcher.include(request, response);

		out.append("<!-- ======= Main ======= -->");
		out.append("  <main id=\"main\" class=\"main\">");
		out.append("    <div class=\"pagetitle d-flex justify-content-between\">");
		out.append("      <h1>Thêm sản phẩm</h1>");
		out.append("      <nav class=\"d-flex align-items-center\">");
		out.append("        <ol class=\"breadcrumb mb-0\">");
		out.append("          <li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-fill\"></i></a></li>");
		out.append("          <li class=\"breadcrumb-item\">Sản Phẩm</li>");
		out.append("          <li class=\"breadcrumb-item active\">Thêm Mới</li>");
		out.append("        </ol>");
		out.append("      </nav>");
		out.append("    </div><!-- End Page Title -->");
		out.append("");
		out.append("    <section class=\"section\">");
		out.append("      <div class=\"row bg-white\">");
		out.append("        <div class=\"col-lg-6\">");
		out.append("          <div class=\"card\">");
		out.append("            <div class=\"card-body\">");
		out.append("              <h5 class=\"card-title\">Thông Tin Sản Phẩm</h5>");
		out.append("              <div class=\"row\">");
		out.append("                <div class=\"col-md-12 mb-3\">");
		out.append("                  <label for=\"productName\" class=\"form-label\">Sản phẩm</label>");
		out.append("                  <input type=\"text\" class=\"form-control\" id=\"productName\" placeholder=\"Nhập tên sản phẩm\" />");
		out.append("                </div>");
		out.append("              </div>");
		out.append("              <div class=\"row\">");
		out.append("                <div class=\"col-md-12 mb-3\">");
		out.append("                  <label for=\"productSize\" class=\"form-label\">Kích thước</label>");
		out.append("                  <input type=\"text\" class=\"form-control\" id=\"productSize\" placeholder=\"Nhập kích thước\" />");
		out.append("                </div>");
		out.append("              </div>");
		out.append("");
		out.append("              <div class=\"row\">");
		out.append("                <div class=\"col-md-12 mb-3\">");
		out.append("                  <label for=\"productMaterial\" class=\"form-label\">Chất liệu</label>");
		out.append("                  <input type=\"text\" class=\"form-control\" id=\"productMaterial\" placeholder=\"Nhập chất liệu\" />");
		out.append("                </div>");
		out.append("              </div>");
		out.append("");
		out.append("              <div class=\"row\">");
		out.append("                <div class=\"col-md-6 mb-3\">");
		out.append("                  <label for=\"category\" class=\"form-label\">Danh mục</label>");
		out.append("                  <select class=\"form-select\" id=\"category\">");
		out.append("                    <option selected>Danh mục</option>");
		out.append("                    <option value=\"1\">Category 1</option>");
		out.append("                    <option value=\"2\">Category 2</option>");
		out.append("                    <option value=\"3\">Category 3</option>");
		out.append("                  </select>");
		out.append("                </div>");
		out.append("                <div class=\"col-md-6 mb-3\">");
		out.append("                  <label for=\"brand\" class=\"form-label\">Thương hiệu</label>");
		out.append("                  <select class=\"form-select\" id=\"brand\">");
		out.append("                    <option selected>Thương hiệu</option>");
		out.append("                    <option value=\"1\">MOHO 1</option>");
		out.append("                    <option value=\"2\">MOHO 2</option>");
		out.append("                    <option value=\"3\">MOHO 3</option>");
		out.append("                  </select>");
		out.append("                </div>");
		out.append("                ");
		out.append("              </div>");
		out.append("              <div class=\"row\">");
		out.append("                  <div class=\"col-md-6 mb-3\">");
		out.append("                    <label for=\"productStock\" class=\"form-label\">Số lượng</label>");
		out.append("                    <input type=\"text\" class=\"form-control\" id=\"productStock\" placeholder=\"Nhập số lượng\" />");
		out.append("                  </div>");
		out.append("                  <div class=\"col-md-6 mb-3\">");
		out.append("                    <label for=\"status\" class=\"form-label\">Trạng thái</label>");
		out.append("                    <select id=\"status\" class=\"form-select text-white fw-semibold bg-success\" onchange=\"");
		out.append("                    this.classList.remove('bg-success', 'bg-danger');");
		out.append("                    this.classList.add(this.value === 'Hoạt động' ? 'bg-success' : 'bg-danger');");
		out.append("                  \">");
		out.append("                      <option value=\"Hoạt động\" selected>Hoạt động</option>");
		out.append("                      <option value=\"Bảo trì\">Bảo trì</option>");
		out.append("                    </select>");
		out.append("                    ");
		out.append("                  </div>");
		out.append("");
		out.append("                </div>");
		out.append("                <h5 class=\"card-title\">Giá sản phẩm</h5>");
		out.append("                <div class=\"row\">");
		out.append("                  ");
		out.append("                    <div class=\"mb-3\">");
		out.append("                      <label for=\"regularPrice\" class=\"form-label\">Giá gốc</label>");
		out.append("                      <input type=\"number\" class=\"form-control\" id=\"regularPrice\" />");
		out.append("                    </div>");
		out.append("                    <div class=\"mb-3\">");
		out.append("                      <label for=\"salePrice\" class=\"form-label\">Giá bán</label>");
		out.append("                      <input type=\"number\" class=\"form-control\" id=\"salePrice\" />");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </div>");
		out.append("          </div>");
		out.append("        </div>");
		out.append("        <div class=\"col-lg-6\">");
		out.append("          <!--Thêm ảnh-->");
		out.append("          <div style=\"max-height: 250px; overflow-y: scroll;\" class=\"row align-items-start mt-3 product-images-col danhsachsanpham\">");
		out.append("            <div class=\"row\">");
		out.append("              <div class=\"col-xl-9\">");
		out.append("                <h5 class=\"card-title\">Ảnh minh họa</h5>");
		out.append("                <div class=\"\">");
		out.append("                  <div class=\"drop-zone text-center p-3 bg-light rounded position-relative\"");
		out.append("                    style=\"border: 1px dashed #ccc; min-height: 150px\" id=\"dropZone\">");
		out.append("                    <span class=\"text-muted position-absolute top-50 start-50 translate-middle\" id=\"dropZoneText\">Drop");
		out.append("                      files here to upload</span>");
		out.append("                    <div id=\"preview\" class=\"d-flex flex-wrap justify-content-center align-items-center\"");
		out.append("                      style=\"min-height: 150px\"></div>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("              </div>");
		out.append("              <div class=\"col-xl-3 col-md-4\">");
		out.append("                <h5 class=\"card-title\">Màu sắc</h5>");
		out.append("                <div class=\"color-container row\">");
		out.append("                  <div class=\"color-box col-xl-2\" data-color=\"#1A1A2E\" style=\"background-color: #1a1a2e\"");
		out.append("                    onclick=\"selectColor(this)\">");
		out.append("                    <span class=\"checkmark\">✔</span>");
		out.append("                  </div>");
		out.append("                  <div class=\"color-box col-xl-2\" data-color=\"#0A2E8F\" style=\"background-color: #0a2e8f\"");
		out.append("                    onclick=\"selectColor(this)\">");
		out.append("                    <span class=\"checkmark\">✔</span>");
		out.append("                  </div>");
		out.append("                  <div class=\"color-box col-xl-2\" data-color=\"#6B48FF\" style=\"background-color: #6b48ff\"");
		out.append("                    onclick=\"selectColor(this)\">");
		out.append("                    <span class=\"checkmark\">✔</span>");
		out.append("                  </div>");
		out.append("                  <div class=\"color-box col-xl-2\" data-color=\"#00C4B4\" style=\"background-color: #00c4b4\"");
		out.append("                    onclick=\"selectColor(this)\">");
		out.append("                    <span class=\"checkmark\">✔</span>");
		out.append("                  </div>");
		out.append("                  <div class=\"color-box col-xl-2\" data-color=\"#00FF00\" style=\"background-color: #00ff00\"");
		out.append("                    onclick=\"selectColor(this)\">");
		out.append("                    <span class=\"checkmark\">✔</span>");
		out.append("                  </div>");
		out.append("                  <div class=\"color-box col-xl-2\" data-color=\"#FF4040\" style=\"background-color: #ff4040\"");
		out.append("                    onclick=\"selectColor(this)\">");
		out.append("                    <span class=\"checkmark\">✔</span>");
		out.append("                  </div>");
		out.append("                  <div class=\"color-box col-xl-2\" data-color=\"#FFA500\" style=\"background-color: #ffa500\"");
		out.append("                    onclick=\"selectColor(this)\">");
		out.append("                    <span class=\"checkmark\">✔</span>");
		out.append("                  </div>");
		out.append("                  <input type=\"color\" class=\"color-box col-xl-2 p-0\">");
		out.append("                </div>");
		out.append("              </div>");
		out.append("            </div>");
		out.append("          </div>");
		out.append("          <div class=\"d-flex justify-content-center mt-3\">");
		out.append("            <button onclick=\"themsanpham()\" type=\"button\" class=\"btn btn-warning d-flex align-items-center gap-2 px-4\">");
		out.append("              <i class=\"bi bi-plus fs-5\"></i>");
		out.append("              Thêm ảnh");
		out.append("            </button>");
		out.append("          </div>");
		out.append("");
		out.append("          <!--Mô tả chi tiết-->");
		out.append("          <h5 class=\"card-title\">Mô tả chi tiết</h5>");
		out.append("          <div class=\"col-md-12 col-lg-12\">");
		out.append("            <textarea name=\"about\" class=\"form-control tinymce-editor bg-white text-dark\" id=\"about\"");
		out.append("              style=\"height: 400px\"></textarea>");
		out.append("          </div>");
		out.append("          ");
		out.append("        </div>");
		out.append("        <div class=\"d-flex align-items-center justify-content-center\">");
		out.append("            <button type=\"button\" class=\"btn btn-primary px-4\">");
		out.append("              <i class=\"bi bi-plus fs-5\"></i>");
		out.append("              Thêm Mới");
		out.append("            </button>");
		out.append("          </div>");
		out.append("      </div>");
		out.append("    </section>");
		out.append("  </main>");
		out.append("  <!-- End #main -->");

		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
		footerDispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
