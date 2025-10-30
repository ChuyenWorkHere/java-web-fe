package servlet.admin.view;

import servlet.dao.BrandDAO;
import servlet.dao.CategoryDAO;
import servlet.dao.ProductDAO;
import servlet.dao.impl.BrandDAOImpl;
import servlet.dao.impl.CategoryDAOImpl;
import servlet.dao.impl.ProductDAOImpl;
import servlet.models.Brand;
import servlet.models.Category;
import servlet.models.Product;
import servlet.utils.ProductUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/edit-product-view")
public class EditProductView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDAO productDAO;
	private CategoryDAO categoryDAO;
	private BrandDAO brandDAO ;

	public EditProductView() {
		super();
		productDAO = new ProductDAOImpl();
		categoryDAO = new CategoryDAOImpl();
		brandDAO = new BrandDAOImpl();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		int productId = Integer.parseInt(request.getParameter("pId"));
		Product product = productDAO.findById(productId);

		String productImgUrl = product.getProductImageUrl();

		List<String> colorArray = List.of(ProductUtils.colorArray(productImgUrl));
		List<String> fileArray = List.of(ProductUtils.urlArray(productImgUrl));

		List<Category> categories = categoryDAO.findAllActiveCategories(100, 1, "ASC", "category_id" );
		List<Brand> brands = brandDAO.findAll();

		PrintWriter out = response.getWriter();
		request.setAttribute("view", "product");
		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
		headerDispatcher.include(request, response);

		out.append("<!-- ======= Main ======= -->");
		out.append("  <main id=\"main\" class=\"main\">");
		out.append("    <div class=\"pagetitle d-flex justify-content-between\">");
		out.append("      <h1>Chỉnh sửa</h1>");
		out.append("      <nav class=\"d-flex align-items-center\">");
		out.append("        <ol class=\"breadcrumb mb-0\">");
		out.append("          <li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-fill\"></i></a></li>");
		out.append("          <li class=\"breadcrumb-item\">Sản Phẩm</li>");
		out.append("          <li class=\"breadcrumb-item active\">Chỉnh sửa</li>");
		out.append("        </ol>");
		out.append("      </nav>");
		out.append("    </div><!-- End Page Title -->");
		out.append("");
		out.append("    <section class=\"section\">");
		out.append("      <div class=\"row bg-white\">");
		out.append("        <form action=\"../admin/product/edit?pId="+productId+"\" method=\"POST\" enctype=\"multipart/form-data\" class=\"row formProduct\">");
		out.append("          <div class=\"col-lg-6\">");
		out.append("            <div class=\"card\">");
		out.append("              <div class=\"card-body\">");
		out.append("                <h5 class=\"card-title\">Thông Tin Sản Phẩm</h5>");
		out.append("                <div class=\"row\">");
		out.append("                  <div class=\"col-md-12 mb-3\">");
		out.append("					<input type=\"hidden\" name=\"pId\" value=\""+productId+"\">");
		out.append("                    <label for=\"productName\" class=\"form-label\">Sản phẩm</label>");
		out.append("                    <input type=\"text\" class=\"form-control\" name=\"productName\" id=\"productName\" value=\""+product.getProductName()+"\" />");
		out.append("                    <p class=\"text-danger m-0 mt-2\" id=\"nameMessage\"></p>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("                <div class=\"row\">");
		out.append("                  <div class=\"col-md-12 mb-3\">");
		out.append("                    <label for=\"productSize\" class=\"form-label\">Kích thước</label>");
		out.append("                    <input type=\"text\" class=\"form-control\" name=\"productSize\" id=\"productSize\" value=\""+product.getProductSize()+"\" />");
		out.append("                    <p class=\"text-danger m-0 mt-2\" id=\"sizeMessage\"></p>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("");
		out.append("                <div class=\"row\">");
		out.append("                  <div class=\"col-md-12 mb-3\">");
		out.append("                    <label for=\"productMaterial\" class=\"form-label\">Chất liệu</label>");
		out.append("                    <input type=\"text\" class=\"form-control\" name=\"productMaterial\" id=\"productMaterial\" value=\""+product.getProductMaterial()+"\" />");
		out.append("                    <p class=\"text-danger m-0 mt-2\" id=\"materialMessage\"></p>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("");
		out.append("                <div class=\"row\">");
		out.append("                  <div class=\"col-md-6 mb-3\">");
		out.append("                    <label for=\"category\" class=\"form-label\">Danh mục</label>");
		out.append("                    <select class=\"form-select\" name=\"category\" id=\"category\">");
		out.append("                      <option>Danh mục</option>");
		for (Category category : categories) {
			out.append("<option "+(category.getCategoryId() == product.getCategory().getCategoryId() ? "selected" : "" )+" value=\""+category.getCategoryId()+"\">"+category.getCategoryName()+"</option>");
		}
		out.append("                    </select>");
		out.append("                    <p class=\"text-danger m-0 mt-2\" id=\"categoryMessage\"></p>");
		out.append("                  </div>");
		out.append("                  <div class=\"col-md-6 mb-3\">");
		out.append("                    <label for=\"brand\" class=\"form-label\">Thương hiệu</label>");
		out.append("                    <select class=\"form-select\" name=\"brand\" id=\"brand\">");
		out.append("                      <option>Thương hiệu</option>");
		for (Brand brand : brands) {
			out.append("<option "+(brand.getBrandId() == product.getBrand().getBrandId() ? "selected" : "" )+" value=\""+brand.getBrandId()+"\">"+brand.getBrandName()+"</option>");
		}
		out.append("                    </select>");
		out.append("                    <p class=\"text-danger m-0 mt-2\" id=\"brandMessage\"></p>");
		out.append("                  </div>");
		out.append("");
		out.append("                </div>");
		out.append("                <div class=\"row\">");
		out.append("                  <div class=\"col-md-6 mb-3\">");
		out.append("                    <label for=\"productStock\" class=\"form-label\">Số lượng</label>");
		out.append("                    <input type=\"text\" class=\"form-control\" name=\"productStock\" id=\"productStock\" value=\""+product.getProductTotal()+"\" />");
		out.append("                  </div>");
		out.append("                  <div class=\"col-md-6 mb-3\">");
		out.append("                    <label for=\"status\" class=\"form-label\">Trạng thái</label>");
		out.append("                    <select id=\"status\" name=\"status\" class=\"form-select text-white fw-semibold "+(product.isProductEnable() ? "bg-success" : "bg-danger")+" \" onchange=\"");
		out.append("                    this.classList.remove('bg-success', 'bg-danger');");
		out.append("                    this.classList.add(this.value === 'Hoạt động' ? 'bg-success' : 'bg-danger');");
		out.append("                  \">");
		out.append("                      <option value=\"1\" "+ (product.isProductEnable() ? "selected" : "")+">Hoạt động</option>");
		out.append("                      <option value=\"0\" "+(product.isProductEnable() ? "" : "selected")+">Bảo trì</option>");
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
		out.append("                    <input type=\"number\" class=\"form-control\" name=\"regularPrice\" id=\"regularPrice\" value=\""+String.format("%.0f",product.getProductPrice())+"\"/>");
		out.append("                    <p class=\"text-danger m-0 mt-2\" id=\"regularMessage\"></p>");
		out.append("                  </div>");
		out.append("                  <div class=\"mb-3\">");
		out.append("                    <label for=\"salePrice\" class=\"form-label\">Giá bán</label>");
		out.append("                    <input type=\"number\" class=\"form-control\" name=\"salePrice\" id=\"salePrice\" value=\""+ String.format("%.0f",product.getProductDiscountPrice())+"\"/>");
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

		for(String filePath : fileArray) {
			out.append("					<div class=\"image-wrapper position-relative m-2\">");
			out.append("                      <img src=\".."+filePath+"\" alt=\"\" class=\"me-2\" style=\"width: 100px; height: 100px; border: 1px solid #ccc; border-radius: 4px;\">");
			out.append("                    </div>");
		}

		out.append("                  </div>");
		out.append("                </div>");
		out.append("                <div class=\"col-xl-12 col-md-12\">");
		out.append("                  <h5 class=\"card-title\">Màu sắc</h5>");
		out.append("                  <div class=\"color-container\">");

		for(String color : colorArray) {
			out.append("                    <div class=\"color-wrapper col-auto mb-2\">");
			out.append("                      <input type=\"color\" name=\"productColors[]\" class=\"color-box p-0\" value=\""+color+"\">");
			out.append("                    </div>");
		}

		out.append("                    <div class=\"color-wrapper col-auto mb-2\">");
		out.append("                      <button type=\"button\" class=\"color-box add-color\"><i class=\"bi bi-plus\"></i></button>");
		out.append("                    </div>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("              </div>");
		out.append("            </div>");
		out.append("");
		out.append("            <!--Mô tả chi tiết-->");
		out.append("            <h5 class=\"card-title\">Mô tả chi tiết</h5>");
		out.append("            <div class=\"col-md-12 col-lg-12\">");
		out.append("              <textarea name=\"about\" class=\"form-control tinymce-editor bg-white text-dark\" id=\"about\"");
		out.append("                style=\"height: 400px\">"+product.getProductDescription()+"</textarea>");
		out.append("                    <p class=\"text-danger m-0 mt-2\" id=\"detailMessage\"></p>");
		out.append("            </div>");
		out.append("");
		out.append("          </div>");
		out.append("          <div class=\"d-flex align-items-center justify-content-center\">");
		out.append("            <button type=\"button\" class=\"btn btn-primary px-4 confirm\" id=\"product-submit\">");
		out.append("              <i class=\"bi bi-pencil fs-5\"></i>");
		out.append("              Chỉnh sửa");
		out.append("            </button>");
		out.append("          </div>");
		out.append("        </form>");
		out.append("      </div>");
		out.append("    </section>");
		out.append("  </main>");
		out.append("  <!-- End #main -->");

		out.append("  <script src=\"../admin/js/UpdateProduct.js\"></script>");
		out.append("  <script src=\"../admin/js/utils.js\"></script>");

		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
		footerDispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
