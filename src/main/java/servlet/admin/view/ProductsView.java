package servlet.admin.view;

import servlet.constants.SearchConstants;
import servlet.dao.BrandDAO;
import servlet.dao.CategoryDAO;
import servlet.dao.MaterialDAO;
import servlet.dao.ProductDAO;
import servlet.dao.impl.BrandDAOImpl;
import servlet.dao.impl.CategoryDAOImpl;
import servlet.dao.impl.MaterialDAOImpl;
import servlet.dao.impl.ProductDAOImpl;
import servlet.models.Brand;
import servlet.models.Category;
import servlet.models.Material;
import servlet.models.Product;
import servlet.utils.ProductUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/products-view")
public class ProductsView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;
	private CategoryDAO categoryDAO ;
	private BrandDAO brandDAO;
	private MaterialDAO materialDAO;

	public ProductsView() {
		super();
		productDAO = new ProductDAOImpl();
		categoryDAO = new CategoryDAOImpl();
		brandDAO = new BrandDAOImpl();
		materialDAO = new MaterialDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		int totalProduct = productDAO.productCounter();

		//Hiển thị category và brand
		List<Category> categories = categoryDAO.findAll(1000, 1, "ASC", "category_id", null, 1);
		List<Brand> brands = brandDAO.findAll();
		List<Material> materials = materialDAO.findAll();

		//Url search
		int size = SearchConstants.PRODUCT_DEFAULT_SIZE; //default
		int page = SearchConstants.DEFAULT_PAGE; //default
		String orderBy =  "product_discount_price";//default
		String sortBy = SearchConstants.DEFAULT_DIR;
		String keyWord = "";
		int categoryId = -1;
		int brandId = -1;
		String price = null;
		int materialId = -1;

		StringBuilder urlSearch = new StringBuilder();
		urlSearch.append("/admin/products-view?");

		if(request.getParameter("size") != null) {
			urlSearch.append("size=");
			try {
				size = Integer.parseInt(request.getParameter("size"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			urlSearch.append(size);
		} else {
			urlSearch.append("size="+size);
		}

		if(request.getParameter("page") != null) {
			urlSearch.append("&page=");
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (Exception e) {
				page = 1;
			}
			urlSearch.append(page);
		} else {
			urlSearch.append("&page=1");
		}

		if(request.getParameter("dir") != null) {
			urlSearch.append("&dir=");
			sortBy = request.getParameter("dir");
			urlSearch.append(sortBy);
		} else {
			urlSearch.append("&dir=ASC");
		}

		if(request.getParameter("orderBy") != null) {
			urlSearch.append("&orderBy=");
			orderBy = request.getParameter("orderBy");
			urlSearch.append(orderBy);
		} else {
			urlSearch.append("&orderBy=product_discount_price");
		}

		if(request.getParameter("keyWord") != null &&
				!request.getParameter("keyWord").trim().equalsIgnoreCase("")) {
			urlSearch.append("&keyWord=");
			keyWord = request.getParameter("keyWord");
			urlSearch.append(keyWord);
		}

		if(request.getParameter("category") != null) {
			urlSearch.append("&category=");
			try {
				categoryId = Integer.parseInt(request.getParameter("category"));
				urlSearch.append(categoryId);
			} catch (Exception e) {
				categoryId = -1;
				urlSearch.append(categoryId);
			}
		}

		if(request.getParameter("brand") != null) {
			urlSearch.append("&brand=");
			try {
				brandId = Integer.parseInt(request.getParameter("brand"));
				urlSearch.append(brandId);
			} catch (Exception e) {
				brandId = -1;
				urlSearch.append(brandId);
			}
		}

		if(request.getParameter("material") != null) {
			urlSearch.append("&material=");
			try {
				materialId = Integer.parseInt(request.getParameter("material"));
				urlSearch.append(materialId);
			} catch (Exception e) {
				materialId = -1;
				urlSearch.append(materialId);
			}
		}

		if(request.getParameter("price") != null &&
				!request.getParameter("price").trim().equalsIgnoreCase("")) {
			urlSearch.append("&price=");
			price = request.getParameter("price");
			urlSearch.append(price);
		}

		//Danh sách product để hiển thị
		List<Product> products = productDAO.findAllBySearchConditions(size, page, sortBy, orderBy, keyWord, categoryId, brandId,materialId, price);

		//Danh sách product để lấy size, tạo pagination
		List<Product> allSearchedProduct = productDAO.findAllBySearchConditions(totalProduct, 1, sortBy, orderBy, keyWord, categoryId, brandId,materialId, price);
		int totalProductSearched = allSearchedProduct.size();
		int totalPages = 1;
		if(totalProductSearched % size != 0)
			totalPages = totalProductSearched/size + 1;
		else
			totalPages = totalProductSearched/size;

		PrintWriter out = response.getWriter();
		request.setAttribute("view", "list");
		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
		headerDispatcher.include(request, response);

		out.append("<main id=\"main\" class=\"main\">");
		out.append("    <div class=\"pagetitle d-flex justify-content-between m-0 p-0 pt-2\">");
		out.append("      <h1>Sản phẩm</h1>");
		out.append("      <nav class=\"d-flex align-items-center\">");
		out.append("        <ol class=\"breadcrumb  mb-0\">");
		out.append("          <li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-fill\"></i></a></li>");
		out.append("          <li class=\"breadcrumb-item active\">Sản phẩm</li>");
		out.append("        </ol>");
		out.append("      </nav>");
		out.append("    </div><!-- End Page Title -->");
		out.append("");
		out.append("    <section class=\"section dashboard\">");
		out.append("");
		out.append("      <!-- Container để chứa alert -->");
		out.append("      <div id=\"alert-container\" class = \"z-2 position-absolute\"></div>");
		out.append("");

		for (int i = 0; i < products.size(); i++) {

			Product product = products.get(i);

			out.append("<!--Modal Delete Product-->");
			out.append("        <div class=\"modal fade\" id=\"modalDel"+product.getProductId()+"\" tabindex=\"-1\">");
			out.append("          <div class=\"modal-dialog modal-dialog-centered\">");
			out.append("            <div class=\"modal-content\">");
			out.append("              <div class=\"modal-body text-center py-3\">");
			out.append("                <div class=\"my-3\"><i class=\"bi bi-exclamation-octagon me-1\"></i>Xác nhận xóa sản phẩm "+product.getProductName()+"?</div>");
			out.append("                <form id=\"form"+product.getProductId()+"\" method=\"post\" action=\"../admin/product-delete\">");
			out.append("                  <input type=\"hidden\" name=\"productId\" value=\"" + product.getProductId() + "\">");
			out.append("                  <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Đóng</button>");
			out.append("                  <button type=\"submit\" class=\"btn btn-danger del-confirm\">Xóa</button>");
			out.append("                </form>");
			out.append("              </div>");
			out.append("            </div>");
			out.append("          </div>");
			out.append("        </div><!-- End Modal Delete Product-->");
		}

		out.append("");
		out.append("      <div class=\"row product\">");
		out.append("        <div class=\"col-xl-9 order-2 order-xl-1 mt-4\">");
		out.append("          <div class=\"row\" style=\"background-color: #f5f8fe;\">");

		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			String[] imgUrls = ProductUtils.urlArray(product.getProductImageUrl());
			String[] colorArray = ProductUtils.colorArray(product.getProductImageUrl());
			out.append("            <div class=\"col-xl-4 col-md-6 mb-3\">");
			out.append("              <div class=\"product__item\">");
			out.append("                <!-- Slides with controls -->");
			out.append("                <div id=\""+product.getProductId()+"\" class=\"carousel carousel-fade slide\" data-bs-ride=\"carousel\">");
			out.append("                  <div class=\"carousel-inner\">");

			for (int j = 0; j < imgUrls.length; j++) {
				out.append("                    <div style=\"max-width: fit-content; max-height: auto;\" class=\"carousel-item " + (j == 0 ? "active" : "")  +"\" data-color=\"\">");
				out.append("                      <img");
				out.append("                        src=\".."+imgUrls[j]+"\"");
				out.append("                        class=\"d-block w-100\" alt=\"...\">");
				out.append("                    </div>");
			}

			out.append("                  </div>");
			out.append("");
			out.append("                  <button class=\"carousel-control-prev\" type=\"button\" data-bs-target=\"#"+product.getProductId()+"\"");
			out.append("                    data-bs-slide=\"prev\">");
			out.append("                    <span class=\"carousel-control-prev-icon\" aria-hidden=\"true\"></span>");
			out.append("                    <span class=\"visually-hidden\">Previous</span>");
			out.append("                  </button>");
			out.append("                  <button class=\"carousel-control-next\" type=\"button\" data-bs-target=\"#"+product.getProductId()+"\"");
			out.append("                    data-bs-slide=\"next\">");
			out.append("                    <span class=\"carousel-control-next-icon\" aria-hidden=\"true\"></span>");
			out.append("                    <span class=\"visually-hidden\">Next</span>");
			out.append("                  </button>");
			out.append("");
			out.append("                </div><!-- End Slides with controls -->");
			out.append("                <div class=\"fw-bold product__item-name\">");
			out.append("                  "+product.getProductName()+"");
			out.append("                </div>");
			out.append("                <div class=\"product__item-price d-flex align-items-center\">");
			out.append("                  <div class=\"product__item-price-new my-2\">"+ProductUtils.formatNumber(product.getProductDiscountPrice())+"đ</div>");
			out.append("                  <div class=\"product__item-price_old ms-2\">"+ProductUtils.formatNumber(product.getProductPrice())+"đ</div>");
			out.append("                </div>");
			out.append("");

			out.append("                <div class=\"product__item-button d-flex justify-content-center mt-3\">");
			out.append("                  <button onclick=\"window.location.href='../admin/product-detail-view?pId="+product.getProductId()+"'\" type=\"button\" class=\"btn bg-primary me-2\" data-bs-toggle=\"modal\" data-bs-target=\"\">");
			out.append("                    <i class=\"bi bi-eye text-white\"></i>");
			out.append("                  </button>");
			out.append("                  <button onclick=\"window.location.href='../admin/edit-product-view?pId="+product.getProductId()+"'\" type=\"button\" class=\"btn bg-warning me-2\" data-bs-toggle=\"modal\" data-bs-target=\"\">");
			out.append("                    <i class=\"bi bi-pencil text-white\"></i>");
			out.append("                  </button>");
			out.append("                  <button type=\"button\" class=\"btn bg-danger me-2\" data-bs-toggle=\"modal\" data-bs-target=\"#modalDel"+product.getProductId()+"\">");
			out.append("                    <i class=\"bi bi-trash text-white\"></i>");
			out.append("                  </button>");
			out.append("                </div>");
			out.append("              </div>");
			out.append("            </div>");
		}

		out.append("          </div>");
		out.append("              <!-- Pagination with icons -->");
		out.append("              <nav aria-label=\"Page navigation example \">");
		out.append("                <div class=\"d-flex flex-end justify-content-end\">");
		out.append("                  <ul class=\"pagination\">");
		out.append("                  <li class=\"page-item	"+(page == 1? "disabled": "")+"\">");
		out.append("                    <a class=\"page-link\" "+ (page == 1 ? "aria-disabled=\"true\"" : " href=\"/Furniture"+urlSearch.toString().replace("page="+page,  "page="+(page -1)) + "\" aria-label=\"Trước\">"));
		out.append("                      <span aria-hidden=\"true\">&laquo;</span>");
		out.append("                    </a>");
		out.append("                  </li>");
		for (int i = 1; i <= totalPages ; i++) {
			out.append("                  <li class=\"page-item\"><a class=\"page-link "+ (i == page ? "active" : "") +"\" href=\"/Furniture"+urlSearch.toString().replace("page="+page, "page="+i)+"\">"+i+"</a></li>");
		}
		out.append("                  <li class=\"page-item "+(page == totalPages? "disabled": "")+"\">");
		out.append("                    <a class=\"page-link\" "+(page == totalPages ? "aria-disabled=\"true\"" : " href=\"/Furniture"+urlSearch.toString().replace("page="+page,  "page="+(page +1))+"\""+" aria-label=\"Sau\">"));
		out.append("                      <span aria-hidden=\"true\">&raquo;</span>");
		out.append("                    </a>");
		out.append("                  </li>");
		out.append("                </ul>");
		out.append("                </div>");
		out.append("              </nav><!-- End Pagination with icons -->");
		out.append("        </div>");
		out.append("        <div class=\"col-xl-3 order-1 order-xl-2 mt-4\">");
		out.append("          <div class=\"row card py-2\" style=\"background-color: #f5f8fe; position: sticky; top: 100px;\">");
		out.append("            <form id=\"searchForm\" action=\"/Furniture/admin/products-view\" method=\"GET\" class=\"container py-4 d-flex flex-column align-items-start gap-3\" style=\"max-width: 320px;\">");
		out.append("              <div class=\"d-flex align-items-center gap-2 filter-label\">");
		out.append("                <i class=\"bi bi-funnel-fill\"></i>");
		out.append("                <span>BỘ LỌC</span>");
		out.append("              </div>");
		out.append("              <input type=\"text\" class=\"form-control\" value=\""+keyWord+"\" placeholder=\"Tên sản phẩm...\" name=\"keyWord\">");

		//Material
		out.append("                    <select class=\"form-select\" name=\"material\" id=\"material\">");
		out.append("                      <option value=\"-1\">CHẤT LIỆU</option>");
		for(Material material : materials) {
			out.append("<option "+(materialId == material.getMaterialId() ? "selected" : "")+" value=\""+material.getMaterialId()+"\">"+material.getMaterialName()+"</option>");
		}
		out.append("                    </select>");

		//Category
		out.append("                    <select class=\"form-select\" name=\"category\" id=\"category\">");
		out.append("                      <option value=\"-1\">DANH MỤC</option>");
		for(Category category : categories) {
			out.append("<option "+(categoryId == category.getCategoryId() ? "selected" : "")+" value=\""+category.getCategoryId()+"\">"+category.getCategoryName()+"</option>");
		}
		out.append("                    </select>");

		//Brand
		out.append("                    <select class=\"form-select\" name=\"brand\" id=\"brand\">");
		out.append("                      <option value=\"-1\">THƯƠNG HIỆU</option>");
		for(Brand brand : brands) {
			out.append("<option "+(brandId == brand.getBrandId() ? " selected " : "")+" value=\""+brand.getBrandId()+"\">"+brand.getBrandName()+"</option>");
		}
		out.append("                    </select>");

		out.append("              <select class=\"form-select\" name=\"price\" aria-label=\"Giá sản phẩm\">");
		out.append("                <option value=\"\">GIÁ SẢN PHẨM</option>");
		out.append("                <option "+("sm".equalsIgnoreCase(price) ? " selected " : "")+" value=\"sm\">Dưới 1 triệu</option>");
		out.append("                <option "+("md".equalsIgnoreCase(price) ? " selected " : "")+" value=\"md\">Từ 1 đến 3 triệu</option>");
		out.append("                <option "+("lg".equalsIgnoreCase(price) ? " selected " : "")+" value=\"lg\">Từ 3 đến 5 triệu</option>");
		out.append("                <option "+("xl".equalsIgnoreCase(price) ? " selected " : "")+" value=\"xl\">Từ 5 đến 9 triệu</option>");
		out.append("                <option "+("xxl".equalsIgnoreCase(price) ? " selected " : "")+" value=\"xxl\">Trên 9 triệu</option>");
		out.append("              </select>");

		out.append("              <select class=\"form-select\" name=\"dir\" aria-label=\"Danh mục\">");
		out.append("                <option value=\"ASC\">SẮP XẾP THEO</option>");
		out.append("                <option "+ ("ASC".equalsIgnoreCase(sortBy) ? "selected" : "")+" value=\"ASC\">TĂNG DẦN</option>");
		out.append("                <option "+("DESC".equalsIgnoreCase(sortBy) ? "selected" : "")+" value=\"DESC\">GIẢM DẦN</option>");
		out.append("              </select>");
		out.append("              <select class=\"form-select\" name=\"size\" aria-label=\"Danh mục\">");
		out.append("                <option value=\"6\">HIỂN THỊ</option>");
		out.append("                <option "+(size==6 ? "selected" : "")+" value=\"6\">6 BẢN GHI</option>");
		out.append("                <option "+(size==9 ? "selected" : "")+" value=\"9\">9 BẢN GHI</option>");
		out.append("              </select>");
		out.append("              <select class=\"form-select\" name=\"orderBy\" aria-label=\"Danh mục\">");
		out.append("                <option value=\"category_id\">SẮP XẾP THEO</option>");
		out.append("                <option "+("product_id".equalsIgnoreCase(orderBy) ? "selected" : "")+" value=\"product_id\">ID</option>");
		out.append("                <option "+("product_name".equalsIgnoreCase(orderBy) ? "selected" : "")+" value=\"product_name\">TÊN</option>");
		out.append("                <option "+("product_discount_price".equalsIgnoreCase(orderBy) ? "selected" : "")+" value=\"product_discount_price\">GIÁ</option>");
		out.append("              </select>");
		out.append("");
		out.append("				<div class=\"d-flex justify-content-between align-items-center w-100\">");
		out.append("              		<a class=\"btn bg-danger text-white\" href=\"../admin/products-view\">XÓA BỘ LỌC</a>");
		out.append("              		<button type=\"submit\" class=\"btn btn-search w-50\">TÌM KIẾM</button>");
		out.append("				</div>");
		out.append("            </form>");
		out.append("          </div>");
		out.append("        </div>");
		out.append("    </section>");
		out.append("  </main>");
		out.append("  <script src=\"../admin/js/DeleteProduct.js\"></script>");
		out.append("  <script src=\"../admin/js/showAlert.js\"></script>");
		out.append("  <script>");
		out.append("	document.getElementById(\"searchForm\").addEventListener(\"submit\", function () {");
		out.append("	});");
		out.append("  </script>");

		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
		footerDispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
