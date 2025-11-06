package servlet.user.view;

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
import servlet.response.ProductResponse;
import servlet.utils.ProductUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/public/shop")
public class UserShopView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDAO productDAO;
	private CategoryDAO categoryDAO ;
	private BrandDAO brandDAO;
	private MaterialDAO materialDAO;
    
    public UserShopView() {
        super();
		productDAO = new ProductDAOImpl();
		categoryDAO = new CategoryDAOImpl();
		brandDAO = new BrandDAOImpl();
		materialDAO = new MaterialDAOImpl();
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		Map<String, String> sortOptions = new LinkedHashMap<>();
		sortOptions.put("created_at-DESC", "Mới nhất");
		sortOptions.put("product_discount_price-ASC", "Giá: Thấp đến Cao");
		sortOptions.put("product_discount_price-DESC", "Giá: Cao đến Thấp");
		sortOptions.put("product_name-ASC", "Tên: A-Z");
		sortOptions.put("product_name-DESC", "Tên: Z-A");


		int totalProduct = productDAO.productCounter();

		//Hiển thị category và brand
		List<Category> categories = categoryDAO.findAll(1000, 1, "ASC", "category_id", null, 1);
		List<Brand> brands = brandDAO.findAll();
		List<Material> materials = materialDAO.findAll();

		//Url search
		int size = SearchConstants.USER_PRODUCT_SIZE; //default
		int page = SearchConstants.DEFAULT_PAGE; //default
		String orderBy =  "product_discount_price";//default
		String sortBy = SearchConstants.DEFAULT_DIR;
		String keyWord = null;
		int categoryId = -1;
		int brandId = -1;
		String price = null;
		int materialId = -1;
		boolean isFilterActive = false;

		StringBuilder urlSearch = new StringBuilder();
		urlSearch.append("/public/shop?");

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
			urlSearch.append("&");
			urlSearch.append("orderBy=product_discount_price");
		}

		if(request.getParameter("keyWord") != null &&
				!request.getParameter("keyWord").trim().equalsIgnoreCase("")) {
			urlSearch.append("&");
			urlSearch.append("keyWord=");
			keyWord = request.getParameter("keyWord");
			isFilterActive = true;
			urlSearch.append(keyWord);
		}

		if(request.getParameter("category") != null) {
			urlSearch.append("&category=");
			try {
				categoryId = Integer.parseInt(request.getParameter("category"));
				if (categoryId != -1) isFilterActive = true;
				urlSearch.append(categoryId);
			} catch (Exception e) {
				categoryId = -1;
				urlSearch.append(categoryId);
			}
		} else {
			urlSearch.append("&category=-1");
		}

		if(request.getParameter("brand") != null) {
			urlSearch.append("&brand=");
			try {
				brandId = Integer.parseInt(request.getParameter("brand"));
				if (brandId != -1) isFilterActive = true;
				urlSearch.append(brandId);
			} catch (Exception e) {
				brandId = -1;
				urlSearch.append(brandId);
			}
		} else {
			urlSearch.append("&brand=-1");
		}

		if(request.getParameter("material") != null) {
			urlSearch.append("&material=");
			try {
				materialId = Integer.parseInt(request.getParameter("material"));
				if (materialId != -1) isFilterActive = true;
				urlSearch.append(materialId);
			} catch (Exception e) {
				materialId = -1;
				urlSearch.append(materialId);
			}
		} else {
			urlSearch.append("&material=-1");
		}

		if(request.getParameter("price") != null &&
				!request.getParameter("price").trim().equalsIgnoreCase("")) {
			urlSearch.append("&price=");
			price = request.getParameter("price");
			isFilterActive = true;
			urlSearch.append(price);
		} else {
			price= "all";
			urlSearch.append("&price="+price);
		}

		//Danh sách product để lấy size, tạo pagination
		List<Product> allSearchedProduct = productDAO.findAllBySearchConditions(totalProduct, 1, sortBy, orderBy, keyWord, categoryId, brandId,materialId, price);
		int totalProductSearched = allSearchedProduct.size();
		int totalPages = 1;
		if(totalProductSearched % size != 0){
			totalPages = totalProductSearched/size + 1;
			if(page > totalPages && totalPages > 0) {
				page = totalPages;
			}
		} else {
			totalPages = totalProductSearched/size;
			if(page > totalPages && totalPages > 0) {
				page = totalPages;
			}
		}

		//Danh sách product để hiển thị
		List<Product> products = productDAO.findAllBySearchConditions(size, page, sortBy, orderBy, keyWord, categoryId, brandId,materialId, price);

		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/public/header-view");
		headerDispatcher.include(request, response);
		PrintWriter out = response.getWriter();

		out.append("<main>");
		out.append("");
		out.append("        <!-- breadcrumb-area-start -->");
		out.append("        <section class=\"breadcrumb-area\" data-background=\"../user/img/bg/page-title.png\">");
		out.append("            <div class=\"container\">");
		out.append("                <div class=\"row\">");
		out.append("                    <div class=\"col-xl-12\">");
		out.append("                        <div class=\"breadcrumb-text text-center\">");
		out.append("                            <h1>Our Shop</h1>");
		out.append("                            <ul class=\"breadcrumb-menu\">");
		out.append("                                <li><a href=\"../public/home\">home</a></li>");
		out.append("                                <li><span>shop</span></li>");
		out.append("                            </ul>");
		out.append("                        </div>");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </div>");
		out.append("        </section>");
		out.append("        <!-- breadcrumb-area-end -->");
		out.append("");
		out.append("        <!-- shop-area start -->");
		out.append("        <section class=\"shop-area pt-100 pb-100\">");
		out.append("            <div class=\"container\">");
		out.append("                <div class=\"row\">");
		out.append("                    <div class=\"col-xl-8 col-lg-8\">");
		out.append("                        <!-- tab filter -->");
		out.append("                        <div class=\"row mb-10\">");
		out.append("                            <div class=\"col-xl-5 col-lg-6 col-md-6 d-flex\">");
		if (isFilterActive) {
			out.append("                                <div class=\"d-flex justify-content-center align-items-center mb-40 mr-3 border border-1 box-shadow\">");
			out.append("                                    <a class=\"text-center p-2 px-4 text-danger\" href=\"../public/shop\" " +
																"title=\"Xóa tất cả bộ lọc\">" +
																"<i class=\"fas fa-times\"></i></a>");
			out.append("                                </div>");
		}
		out.append("                                <div class=\"product-showing mb-40\">");
		out.append("                                    <p>Showing "+products.size()+" of "+totalProductSearched+" results</p>");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("                            <div class=\"col-xl-7 col-lg-6 col-md-6\">");
		out.append("                                <div class=\"shop-tab f-right\">");
		out.append("                                    <ul class=\"nav text-center\" id=\"myTab\" role=\"tablist\">");
		out.append("                                        <li class=\"nav-item\">");
		out.append("                                            <a class=\"nav-link active\" id=\"home-tab\" data-toggle=\"tab\" href=\"#home\" role=\"tab\" aria-controls=\"home\"");
		out.append("                                                aria-selected=\"true\"><i class=\"fas fa-list-ul\"></i> </a>");
		out.append("                                        </li>");
		out.append("                                    </ul>");
		out.append("                                </div>");
		out.append("                                <div class=\"pro-filter mb-40 f-right\">");
		out.append("                                    <form id=\"sortForm\">");
		out.append("                                        <select name=\"pro-filter\" id=\"sort-options\">");
		String currentSort = orderBy + "-" + sortBy;
		for(Map.Entry<String, String> option : sortOptions.entrySet()) {
			String optionValue = option.getKey();
			String optionText = option.getValue();
			String selected = optionValue.equalsIgnoreCase(currentSort) ? "selected" : "";
			out.append("                                            <option value=\""+ optionValue +"\" "+ selected +">"+ optionText +"</option>");
		}
		out.append("                                        </select>");
		out.append("                                    </form>");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("                        <!-- tab content -->");
		out.append("                        <div class=\"tab-content\" id=\"myTabContent\">");
		out.append("                            <div class=\"tab-pane fade show active\" id=\"home\" role=\"tabpanel\" aria-labelledby=\"home-tab\">");
		out.append("                                <div class=\"row\">");

		for (int i = 0; i < products.size(); i++) {

			Product product = products.get(i);
			String[] imgUrls = ProductUtils.urlArray(product.getProductImageUrl());

			out.append("                                    <div class=\"col-lg-6 col-md-6\">");
			out.append("                                        <div class=\"product-wrapper mb-50\">");
			out.append("                                            <div class=\"product-img mb-25\">");
			out.append("                                                <a href=\"../public/product-detail?productId="+product.getProductId()+"\">");
			out.append("                                                    <img src=\".."+imgUrls[0]+"\" alt=\"\">");
			for (int j = 1; j < imgUrls.length; j++) {
				out.append("                                                    <img class=\"secondary-img\" src=\".."+imgUrls[j]+"\" alt=\"\">");
			}

			out.append("                                                </a>");
			out.append("                                                <div class=\"product-action text-center\">");
			out.append("                                                    <a href=\""+ (request.getContextPath() + "/customer/cart/update?productId="+product.getProductId()) +"\" title=\"Thêm vào giỏ hàng\">");
			out.append("                                                        <i class=\"flaticon-shopping-cart\"></i>");
			out.append("                                                    </a>");
			out.append("                                                    <a href=\""+request.getContextPath() +"/public/product-detail?productId="+product.getProductId()+"\" title=\"Chi tiết\">");
			out.append("                                                        <i class=\"flaticon-eye\"></i>");
			out.append("                                                    </a>");
			out.append("                                                </div>");
			out.append("                                                <div class=\"sale-tag\">");
			if(product.getProductDiscountPrice() > 0) {
				out.append("                                                    <span class=\"sale\">sale</span>");
			}
			out.append("                                                    <span class=\"new\">new</span>");
			out.append("                                                </div>");
			out.append("                                            </div>");
			out.append("                                            <div class=\"product-content\">");
			out.append("                                                <div class=\"pro-cat mb-10\">");
			out.append("                                                    <a href=\"../public/product-detail?productId="+product.getProductId()+"\">"+product.getCategory().getCategoryName()+"</a>");
			out.append("                                                </div>");
			out.append("                                                <h4>");
			out.append("                                                    <a href=\"../public/product-detail?productId="+product.getProductId()+"\">"+product.getProductName()+"</a>");
			out.append("                                                </h4>");
			out.append("                                                <div class=\"product-meta\">");
			out.append("                                                    <div class=\"pro-price\">");
			out.append("                                                        <span>"+ProductUtils.formatNumber(product.getProductDiscountPrice())+"</span>");
			out.append("                                                        <span class=\"old-price\">"+ProductUtils.formatNumber(product.getProductPrice())+"</span>");
			out.append("                                                    </div>");
			out.append("                                                </div>");
			out.append("                                                <div class=\"product-wishlist\">");
			out.append("                                                    <a href=\"#\"><i class=\"far fa-heart\" title=\"Wishlist\"></i></a>");
			out.append("                                                </div>");
			out.append("                                            </div>");
			out.append("                                        </div>");
			out.append("                                    </div>");
		}

		out.append("                                </div>");
		out.append("                            </div>");

		out.append("                        </div>");
		out.append("                        <div class=\"basic-pagination basic-pagination-2 text-center mt-20\">");
		out.append("                            <ul>");
		out.append("                                <li><a href=\""+ (page == 1 ? ("/Furniture"+ urlSearch) : ("/Furniture"+urlSearch.toString().replace("page="+page,  "page="+(page -1)))) +"\"><i class=\"fas fa-angle-double-left\"></i></a></li>");
		for (int i = 1; i <= totalPages ; i++) {
			out.append("                  <li class=\""+ (i == page ? "active" : "") + "\") +\"\"><a href=\"/Furniture"+urlSearch.toString().replace("page="+page, "page="+i)+"\">"+i+"</a></li>");
		}
		out.append("                                <li><a href=\""+ (page == totalPages ? ("/Furniture"+ urlSearch) : ("/Furniture"+urlSearch.toString().replace("page="+page,  "page="+(page + 1)))) +"\"><i class=\"fas fa-angle-double-right\"></i></a></li>");
		out.append("                            </ul>");
		out.append("                        </div>");
		out.append("                    </div>");
		out.append("                    <div class=\"col-xl-4 col-lg-4\">");
		out.append("                        <div class=\"sidebar-box\">");
		out.append("");
		out.append("                            <div class=\"shop-widget\">");
		out.append("                                <h3 class=\"shop-title\">Search by</h3>");
		out.append("                                <form action=\""+ request.getContextPath() +"/public/shop\" method=\"GET\" class=\"shop-search\">");
		if (categoryId != -1) {
			out.append("                                    <input type=\"hidden\" name=\"category\" value=\""+ categoryId +"\">");
		}
		if (materialId != -1) {
			out.append("                                    <input type=\"hidden\" name=\"material\" value=\""+ materialId +"\">");
		}
		if (brandId != -1) {
			out.append("                                    <input type=\"hidden\" name=\"brand\" value=\""+ brandId +"\">");
		}
		if (price != null) {
			out.append("                                    <input type=\"hidden\" name=\"price\" value=\""+ price +"\">");
		}
		out.append("                                    <input type=\"text\" name=\"keyWord\" placeholder=\"Your keyword....\" value=\""+ (keyWord != null ? keyWord : "") +"\">");
		out.append("                                    <button type=\"submit\"><i class=\"fa fa-search\"></i></button>");
		out.append("                                </form>");
		out.append("                            </div>");
		out.append("");
		out.append("                            <div class=\"shop-widget\">");
		out.append("                                <h3 class=\"shop-title\">Brand</h3>");
		out.append("                                <ul class=\"shop-link\">");
		for(Brand brand : brands) {
			out.append("                                    <li><a href=\""+ "/Furniture" + urlSearch.toString().replace("brand="+brandId, "brand="+brand.getBrandId()) +"\"><i class=\"far "+ ((brandId == brand.getBrandId()) ? " fa-check-square text-danger " : " far fa-square ") +"\"></i> "+brand.getBrandName()+"</a></li>");
		}
		out.append("                                </ul>");
		out.append("                            </div>");
		out.append("");
		out.append("                            <div class=\"shop-widget\">");
		out.append("                                <h3 class=\"shop-title\">Filter selection</h3>");
		out.append("                                <div class=\"price-filter\">");
		out.append("                                    <div id=\"slider-range\"></div>");
		out.append("                                    <input type=\"text\" id=\"amount\">");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("");
		out.append("                            <div class=\"shop-widget\">");
		out.append("                                <h3 class=\"shop-title\">Price filter</h3>");
		out.append("                                <ul class=\"shop-link\">");
		out.append("                                    <li><a href=\""+ "/Furniture" + urlSearch.toString().replace("price="+price, "price=sm") +"\"><i class=\"far "+ ((price.equalsIgnoreCase("sm") ? " fa-check-square text-danger " : " far fa-square ") +"\"></i> Dưới 1 triệu </a></li>"));
		out.append("                                    <li><a href=\""+ "/Furniture" + urlSearch.toString().replace("price="+price, "price=md") +"\"><i class=\"far "+ ((price.equalsIgnoreCase("md") ? " fa-check-square text-danger " : " far fa-square ") +"\"></i> 1 triệu - 3 triệu </a></li>"));
		out.append("                                    <li><a href=\""+ "/Furniture" + urlSearch.toString().replace("price="+price, "price=lg") +"\"><i class=\"far "+ ((price.equalsIgnoreCase("lg") ? " fa-check-square text-danger " : " far fa-square ") +"\"></i> 3 triệu - 5 triệu </a></li>"));
		out.append("                                    <li><a href=\""+ "/Furniture" + urlSearch.toString().replace("price="+price, "price=xl") +"\"><i class=\"far "+ ((price.equalsIgnoreCase("xl") ? " fa-check-square text-danger " : " far fa-square ") +"\"></i> 5 triệu - 9 triệu </a></li>"));
		out.append("                                    <li><a href=\""+ "/Furniture" + urlSearch.toString().replace("price="+price, "price=xxl") +"\"><i class=\"far "+ ((price.equalsIgnoreCase("xxl") ? " fa-check-square text-danger " : " far fa-square ") +"\"></i> Trên 9 triệu </a></li>"));
		out.append("                                </ul>");
		out.append("                            </div>");
		out.append("");
		out.append("                            <div class=\"shop-widget\">");
		out.append("                                <h3 class=\"shop-title\">Product Material</h3>");
		out.append("                                <ul class=\"shop-link\">");
		for(Material material : materials) {
			out.append("                                    <li><a href=\""+ "/Furniture" + urlSearch.toString().replace("material="+materialId, "material="+material.getMaterialId()) +"\"><i class=\"far "+ ((materialId == material.getMaterialId()) ? " fa-check-square text-danger " : " far fa-square ") +"\"></i> "+material.getMaterialName()+"</a></li>");
		}
		out.append("                                </ul>");
		out.append("                            </div>");
		out.append("");
		out.append("                            <div class=\"shop-widget\">");
		out.append("                                <h3 class=\"shop-title\">Catergories</h3>");
		out.append("                                <ul class=\"shop-link\">");
		for(Category category : categories) {
			out.append("                                    <li><a href=\""+ "/Furniture" + urlSearch.toString().replace("category="+categoryId, "category="+category.getCategoryId()) +"\"><i class=\"far "+ ((categoryId == category.getCategoryId()) ? " fa-check-square text-danger " : " far fa-square ") +"\"></i> "+category.getCategoryName()+"</a></li>");
		}
		out.append("                                </ul>");
		out.append("                            </div>");

		out.append("");
		out.append("                            <div class=\"shop-widget\">");
		out.append("                                <div class=\"shop-sidebar-banner\">");
		out.append("                                    <a href=\"shop.html\"><img src=\"../user/img/banner/shop-banner.jpg\" alt=\"\"></a>");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </div>");
		out.append("        </section>");
		out.append("        <!-- shop-area end -->");
		out.append("");
		out.append("");
		out.append("        </main>");

		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/public/footer-view");
		footerDispatcher.include(request, response);

		out.append("<script>");
		out.append("document.getElementById('sort-options').addEventListener('change', function() {");
		out.append("    var selectedValue = this.value;");
		out.append("    var parts = selectedValue.split('-');");
		out.append("    var newOrderBy = parts[0];");
		out.append("    var newDir = parts[1];");
		out.append("    var currentUrl = new URL(window.location.href);");
		out.append("    currentUrl.searchParams.set('orderBy', newOrderBy);");
		out.append("    currentUrl.searchParams.set('dir', newDir);");
		out.append("    window.location.href = currentUrl.toString();");
		out.append("});");
		out.append("</script>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
