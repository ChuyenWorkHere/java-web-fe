package servlet.admin.view;

import servlet.dao.BrandDAO;
import servlet.dao.CategoryDAO;
import servlet.dao.impl.BrandDAOImpl;
import servlet.dao.impl.CategoryDAOImpl;
import servlet.models.Brand;
import servlet.models.Category;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/add-product-view")
public class AddProductView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CategoryDAO categoryDAO = new CategoryDAOImpl();
	private BrandDAO brandDAO = new BrandDAOImpl();

	public AddProductView() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		List<Category> categories = categoryDAO.findAllActiveCategories(100, 1, "ASC", "category_id" );
		List<Brand> brands = brandDAO.findAll();

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
		out.append("        <form action=\"../admin/product/add\" method=\"POST\" enctype=\"multipart/form-data\" class=\"row formProduct\">");
		out.append("          <div class=\"col-lg-6\">");
		out.append("            <div class=\"card\">");
		out.append("              <div class=\"card-body\">");
		out.append("                <h5 class=\"card-title\">Thông Tin Sản Phẩm</h5>");
		out.append("                <div class=\"row\">");
		out.append("                  <div class=\"col-md-12 mb-3\">");
		out.append("                    <label for=\"productName\" class=\"form-label\">Sản phẩm</label>");
		out.append("                    <input type=\"text\" name=\"productName\" class=\"form-control\" id=\"productName\" required placeholder=\"Nhập tên sản phẩm\" />");
		out.append("                    <p class=\"text-danger m-0 mt-2\" id=\"nameMessage\"></p>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("                <div class=\"row\">");
		out.append("                  <div class=\"col-md-12 mb-3\">");
		out.append("                    <label for=\"productSize\" class=\"form-label\">Kích thước</label>");
		out.append("                    <input type=\"text\" name =\"productSize\" class=\"form-control\" id=\"productSize\" placeholder=\"Nhập kích thước\" />");
		out.append("                    <p class=\"text-danger m-0 mt-2\" id=\"sizeMessage\"></p>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("");
		out.append("                <div class=\"row\">");
		out.append("                  <div class=\"col-md-12 mb-3\">");
		out.append("                    <label for=\"productMaterial\" class=\"form-label\">Chất liệu</label>");
		out.append("                    <input type=\"text\" name=\"productMaterial\" class=\"form-control\" id=\"productMaterial\" placeholder=\"Nhập chất liệu\" />");
		out.append("                    <p class=\"text-danger m-0 mt-2\" id=\"materialMessage\"></p>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("");
		out.append("                <div class=\"row\">");
		out.append("                  <div class=\"col-md-6 mb-3\">");
		out.append("                    <label for=\"category\" class=\"form-label\">Danh mục</label>");
		out.append("                    <select class=\"form-select\" name=\"category\" id=\"category\">");
		out.append("                      <option selected>Danh mục</option>");
		for(Category category : categories) {
			out.append("<option value=\""+category.getCategoryId()+"\">"+category.getCategoryName()+"</option>");
		}
		out.append("                    </select>");
		out.append("                    <p class=\"text-danger m-0 mt-2\" id=\"categoryMessage\"></p>");
		out.append("                  </div>");
		out.append("                  <div class=\"col-md-6 mb-3\">");
		out.append("                    <label for=\"brand\" class=\"form-label\">Thương hiệu</label>");
		out.append("                    <select class=\"form-select\" name=\"brand\" id=\"brand\">");
		out.append("                      <option selected>Thương hiệu</option>");
		for(Brand brand : brands) {
			out.append("<option value=\""+brand.getBrandId()+"\">"+brand.getBrandName()+"</option>");
		}
		out.append("                    </select>");
		out.append("                    <p class=\"text-danger m-0 mt-2\" id=\"brandMessage\"></p>");
		out.append("                  </div>");
		out.append("");
		out.append("                </div>");
		out.append("                <div class=\"row\">");
		out.append("                  <div class=\"col-md-6 mb-3\">");
		out.append("                    <label for=\"productStock\" class=\"form-label\">Số lượng</label>");
		out.append("                    <input type=\"text\" class=\"form-control\" name=\"productStock\" id=\"productStock\" placeholder=\"Nhập số lượng\" />");
		out.append("                    <p class=\"text-danger m-0 mt-2\" id=\"quantityMessage\"></p>");
		out.append("                  </div>");
		out.append("                  <div class=\"col-md-6 mb-3\">");
		out.append("                    <label for=\"status\" class=\"form-label\">Trạng thái</label>");
		out.append("                    <select id=\"status\" name=\"status\" class=\"form-select\" onchange=\"");
		out.append("                  \">");
		out.append("                      <option value=\"1\" selected>Hoạt động</option>");
		out.append("                      <option value=\"0\">Bảo trì</option>");
		out.append("                    </select>");
		out.append("");
		out.append("                  </div>");
		out.append("");
		out.append("                </div>");
		out.append("                <h5 class=\"card-title\">Giá sản phẩm</h5>");
		out.append("                <div class=\"row\">");
		out.append("");
		out.append("                  <div class=\"mb-3\">");
		out.append("                    <label for=\"regularPrice\" class=\"form-label\">Giá gốc</label>");
		out.append("                    <input type=\"number\" name=\"regularPrice\" class=\"form-control\" id=\"regularPrice\" />");
		out.append("                    <p class=\"text-danger m-0 mt-2\" id=\"regularMessage\"></p>");
		out.append("                  </div>");
		out.append("                  <div class=\"mb-3\">");
		out.append("                    <label for=\"salePrice\" class=\"form-label\">Giá bán</label>");
		out.append("                    <input type=\"number\" name=\"salePrice\" class=\"form-control\" id=\"salePrice\" />");
		out.append("                    <p class=\"text-danger m-0 mt-2\" id=\"saleMessage\"></p>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("              </div>");
		out.append("            </div>");
		out.append("          </div>");
		out.append("          <div class=\"col-lg-6\">");
		out.append("            <!--Thêm ảnh-->");
		out.append("			<div style=\"max-height: 250px; overflow-y: scroll;\" class=\"row align-items-start mt-3 product-images-col\">");
		out.append("              <div class=\"row\">");
		out.append("                <div class=\"col-xl-12\">");
		out.append("                  <h5 class=\"card-title\">Ảnh minh họa</h5>");
		out.append("                  <input type=\"file\" name=\"productImg\" class=\"form-control image-input\" multiple accept=\"image/*\">");
		out.append("                  <div class=\"image-preview d-flex flex-wrap mt-2\">");

		out.append("                  </div>");
		out.append("                </div>");
		out.append("                <div class=\"col-xl-12 col-md-12\">");
		out.append("                  <h5 class=\"card-title\">Màu sắc</h5>");
		out.append("                  <div class=\"color-container\">");
		out.append("                    <div class=\"color-wrapper col-auto mb-2\">");
		out.append("                      <input type=\"color\" name=\"productColors[]\" class=\"color-box p-0\" value=\"#fff\">");
		out.append("                    </div>");
		out.append("                    <div class=\"color-wrapper col-auto mb-2\">");
		out.append("                      <button type=\"button\" class=\"color-box add-color\"><i class=\"bi bi-plus\"></i></button>");
		out.append("                    </div>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("              </div>");
		out.append("            </div>");
		out.append("            <!--Mô tả chi tiết-->");
		out.append("            <h5 class=\"card-title\">Mô tả chi tiết</h5>");
		out.append("            <div class=\"col-md-12 col-lg-12\">");
		out.append("              <textarea name=\"about\" class=\"form-control tinymce-editor bg-white text-dark\" id=\"about\"");
		out.append("                style=\"height: 400px\"></textarea>");
		out.append("                    <p class=\"text-danger m-0 mt-2\" id=\"detailMessage\"></p>");
		out.append("            </div>");
		out.append("");
		out.append("          </div>");
		out.append("          <div class=\"d-flex align-items-center justify-content-center\">");
		out.append("            <button type=\"button\" class=\"btn btn-primary px-4\" id=\"product-submit\">");
		out.append("              <i class=\"bi bi-plus fs-5\"></i>");
		out.append("              Thêm Mới");
		out.append("            </button>");
		out.append("          </div>");
		out.append("        </form>");
		out.append("      </div>");
		out.append("    </section>");
		out.append("  </main>");
		out.append("  <!-- End #main -->");
		out.append("  <script src=\"../admin/js/UpdateProduct.js\"></script>");

		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
		footerDispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
