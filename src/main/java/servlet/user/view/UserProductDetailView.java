package servlet.user.view;

import servlet.dao.ProductDAO;
import servlet.dao.impl.ProductDAOImpl;
import servlet.models.Product;
import servlet.response.ProductResponse;
import servlet.utils.ProductUtils;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/public/product-detail")
public class UserProductDetailView extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ProductDAO productDAO;
    
    public UserProductDetailView() {
        super();
        productDAO = new ProductDAOImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		
		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/public/header-view");
		headerDispatcher.include(request, response);

		Product product = new Product();
		ProductResponse productResponse = new ProductResponse();

		try {
			int productId = Integer.parseInt(request.getParameter("productId"));
			product = productDAO.findById(productId);
			ProductUtils.copyProductToProductResponse(product, productResponse);
		} catch (Exception e) {
			response.sendRedirect("../public/shop");
			return;
		}
		
		out.append("<main>");
		out.append("");
		out.append("        <!-- breadcrumb-area-start -->");
		out.append("        <section class=\"breadcrumb-area\" data-background=\"../user/img/bg/page-title.png\">");
		out.append("            <div class=\"container\">");
		out.append("                <div class=\"row\">");
		out.append("                    <div class=\"col-xl-12\">");
		out.append("                        <div class=\"breadcrumb-text text-center\">");
		out.append("                            <h1>Details</h1>");
		out.append("                            <ul class=\"breadcrumb-menu\">");
		out.append("                                <li><a href=\"../public/home\">home</a></li>");
		out.append("                                <li><span>shop details</span></li>");
		out.append("                            </ul>");
		out.append("                        </div>");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </div>");
		out.append("        </section>");
		out.append("        <!-- breadcrumb-area-end -->");
		out.append("");
		out.append("        <!-- shop-area start -->");
		out.append("        <section class=\"shop-details-area pt-100 pb-100\">");
		out.append("            <div class=\"container\">");
		out.append("                <div class=\"row\">");
		out.append("                    <div class=\"col-xl-6 col-lg-4\">");
		out.append("                        <div class=\"product-details-img mb-10\">");
		out.append("                            <div class=\"tab-content\" id=\"myTabContentpro\">");
		out.append("                                <div id=\"carouselExampleControls\" class=\"carousel slide\" data-ride=\"carousel\">");
		out.append("                                    <div class=\"carousel-inner\">");
		if (productResponse.getProductImageUrls() != null && !productResponse.getProductImageUrls().isEmpty()) {
			out.append("                                        <div class=\"carousel-item active\">");
			out.append("                                            <img class=\"d-block w-100\" src=\""+ request.getContextPath() + productResponse.getProductImageUrls().get(0) +"\" alt=\""+ productResponse.getProductName() +"\">");
			out.append("                                        </div>");
			for (int i = 1; i < productResponse.getProductImageUrls().size(); i++) {
				out.append("                                        <div class=\"carousel-item\">");
				out.append("                                            <img class=\"d-block w-100\" src=\""+ request.getContextPath() + productResponse.getProductImageUrls().get(i) +"\" alt=\""+ productResponse.getProductName() +"\">");
				out.append("                                        </div>");
			}
		} else {
			out.append("                                        <div class=\"carousel-item active\"><img class=\"d-block w-100\" src=\""+ request.getContextPath() +"/user/img/product/pro-details-l.jpg\" alt=\"No Image Available\"></div>");
		}
		out.append("                                    </div>");
		out.append("                                    <a class=\"carousel-control-prev\" href=\"#carouselExampleControls\" role=\"button\"");
		out.append("                                        data-slide=\"prev\">");
		out.append("                                        <span class=\"carousel-control-prev-icon\" aria-hidden=\"true\"></span>");
		out.append("                                        <span class=\"sr-only\">Previous</span>");
		out.append("                                    </a>");
		out.append("                                    <a class=\"carousel-control-next\" href=\"#carouselExampleControls\" role=\"button\"");
		out.append("                                        data-slide=\"next\">");
		out.append("                                        <span class=\"carousel-control-next-icon\" aria-hidden=\"true\"></span>");
		out.append("                                        <span class=\"sr-only\">Next</span>");
		out.append("                                    </a>");
		out.append("                                </div>");
		// --- KẾT THÚC SỬA SLIDER ---
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("                    </div>"); // Đóng thẻ div class="col-xl-6 col-lg-4"
		out.append("                    <div class=\"col-xl-6 col-lg-8\">");
		out.append("                        <div class=\"product-details mb-30 pl-30\">");
		out.append("                            <div class=\"details-cat mb-20\">");
		out.append("                                <a href=\"#\">"+productResponse.getCategory().getCategoryName()+"</a>");
		out.append("                            </div>");
		out.append("                            <h2 class=\"pro-details-title mb-15\">"+productResponse.getProductName()+"</h2>");
		out.append("                            <div class=\"details-price mb-20\">");
		out.append("                                <span>"+ProductUtils.formatNumber(productResponse.getProductDiscountPrice())+"</span>");
		out.append("                                <span class=\"old-price\">"+ProductUtils.formatNumber(productResponse.getProductPrice())+"</span>");
		out.append("                            </div>");
		out.append("                            <div class=\"product-variant\">");
		out.append("");
		out.append("                                <div class=\"product-Metarial variant-item\">");
		out.append("                                    <div class=\"variant-name\">");
		out.append("                                        <span>Material</span>");
		out.append("                                    </div>");
		out.append("                                    <ul class=\"shop-link shop-size\">");
		out.append("                                        <li><a href=\"shop.html\">leather</a></li>");
		out.append("                                        <li class=\"active\"><a href=\"shop.html\">resin</a></li>");
		out.append("                                        <li><a href=\"shop.html\">metal </a></li>");
		out.append("                                        <li><a href=\"shop.html\">fiber </a></li>");
		out.append("                                        <li><a href=\"shop.html\">slag </a></li>");
		out.append("                                    </ul>");
		out.append("                                    <div class=\"product-desc mt-4\">");
		out.append("                                        <p>"+productResponse.getProductMaterial()+"</p>");
		out.append("                                    </div>");
		out.append("                                </div>");
		out.append("");
		out.append("                                <div class=\"product-info-list variant-item\">");
		out.append("                                    <ul>");
		out.append("                                        <li><span>Thương hiệu:</span> "+productResponse.getBrand().getBrandName()+"</li>");
		out.append("                                        <li><span>Product Code:</span> "+productResponse.getProductCode()+"</li>");
		out.append("                                        <li><span>Kích thước:</span> "+productResponse.getProductSize()+"</li>");
		out.append("                                        <li><span>Tình trạng:</span> <span class=\"in-stock "+(productResponse.getProductTotal() > 0 ? "text-success" : "text-danger")+"\">"+(productResponse.getProductTotal() > 0 ? "Còn hàng" : "Hết hàng")+"</span></li>");
		out.append("                                    </ul>");
		out.append("                                </div>");
		out.append("");
		out.append("                                <div class=\"product-action-details variant-item\">");
		out.append("                                    <div class=\"product-details-action\">");
		out.append("                                        <form action=\"#\">");
		out.append("                                            <div class=\"plus-minus\">");
		out.append("                                                <div class=\"cart-plus-minus\"><input type=\"text\" value=\"1\" /></div>");
		out.append("                                            </div>");
		out.append("                                            <button class=\"details-action-icon\" type=\"submit\"><i");
		out.append("                                                    class=\"fas fa-heart\"></i></button>");
		out.append("                                            <button class=\"details-action-icon\" type=\"submit\"><i");
		out.append("                                                    class=\"fas fa-hourglass\"></i></button>");
		out.append("                                            <div class=\"details-cart mt-40\">");
		out.append("                                                <button class=\"btn theme-btn\">Mua ngay</button>");
		out.append("                                            </div>");
		out.append("                                        </form>");
		out.append("                                    </div>");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("                <div class=\"row mt-50\">");
		out.append("                    <div class=\"col-xl-12 col-lg-12\">");
		out.append("                        <div class=\"product-review\">");
		out.append("                            <ul class=\"nav review-tab\" id=\"myTabproduct\" role=\"tablist\">");
		out.append("                                <li class=\"nav-item\">");
		out.append("                                    <a class=\"nav-link active\" id=\"home-tab6\" data-toggle=\"tab\" href=\"#home6\" role=\"tab\"");
		out.append("                                        aria-controls=\"home\" aria-selected=\"true\">Description </a>");
		out.append("                                </li>");
		out.append("                                <li class=\"nav-item\">");
		out.append("                                    <a class=\"nav-link\" id=\"profile-tab6\" data-toggle=\"tab\" href=\"#profile6\" role=\"tab\"");
		out.append("                                        aria-controls=\"profile\" aria-selected=\"false\">Reviews (2)</a>");
		out.append("                                </li>");
		out.append("                            </ul>");
		out.append("                            <div class=\"tab-content\" id=\"myTabContent2\">");
		out.append("                                <div class=\"tab-pane fade show active\" id=\"home6\" role=\"tabpanel\"");
		out.append("                                    aria-labelledby=\"home-tab6\">");
		out.append("                                    <div class=\"desc-text\">");
		out.append("                                        <p>"+productResponse.getProductDescription()+"</p>");
		out.append("                                    </div>");
		out.append("                                </div>");
		out.append("                                <div class=\"tab-pane fade\" id=\"profile6\" role=\"tabpanel\" aria-labelledby=\"profile-tab6\">");
		out.append("                                    <div class=\"desc-text review-text\">");
		out.append("                                        <div class=\"product-commnets\">");
		out.append("                                            <div class=\"product-commnets-list mb-25 pb-15\">");
		out.append("                                                <div class=\"pro-comments-img\">");
		out.append("                                                    <img src=\"../user/img/product/comments/01.png\" alt=\"\">");
		out.append("                                                </div>");
		out.append("                                                <div class=\"pro-commnets-text\">");
		out.append("                                                    <h4>Roger West -");
		out.append("                                                        <span>June 5, 2018</span>");
		out.append("                                                    </h4>");
		out.append("                                                    <div class=\"pro-rating\">");
		out.append("                                                        <i class=\"far fa-star\"></i>");
		out.append("                                                        <i class=\"far fa-star\"></i>");
		out.append("                                                        <i class=\"far fa-star\"></i>");
		out.append("                                                        <i class=\"far fa-star\"></i>");
		out.append("                                                    </div>");
		out.append("                                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do");
		out.append("                                                        eiusmod tempor");
		out.append("                                                        incididunt");
		out.append("                                                        ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis");
		out.append("                                                        nostrud");
		out.append("                                                        exercitation.</p>");
		out.append("                                                </div>");
		out.append("                                            </div>");
		out.append("                                            <div class=\"product-commnets-list mb-25 pb-15\">");
		out.append("                                                <div class=\"pro-comments-img\">");
		out.append("                                                    <img src=\"../user/img/product/comments/02.png\" alt=\"\">");
		out.append("                                                </div>");
		out.append("                                                <div class=\"pro-commnets-text\">");
		out.append("                                                    <h4>Roger West -");
		out.append("                                                        <span>June 5, 2018</span>");
		out.append("                                                    </h4>");
		out.append("                                                    <div class=\"pro-rating\">");
		out.append("                                                        <i class=\"far fa-star\"></i>");
		out.append("                                                        <i class=\"far fa-star\"></i>");
		out.append("                                                        <i class=\"far fa-star\"></i>");
		out.append("                                                        <i class=\"far fa-star\"></i>");
		out.append("                                                    </div>");
		out.append("                                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do");
		out.append("                                                        eiusmod tempor");
		out.append("                                                        incididunt");
		out.append("                                                        ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis");
		out.append("                                                        nostrud");
		out.append("                                                        exercitation.</p>");
		out.append("                                                </div>");
		out.append("                                            </div>");
		out.append("                                        </div>");
		out.append("                                        <div class=\"review-box mt-50\">");
		out.append("                                            <h4>Add a Review</h4>");
		out.append("                                            <div class=\"your-rating mb-40\">");
		out.append("                                                <span>Your Rating:</span>");
		out.append("                                                <div class=\"rating-list\">");
		out.append("                                                    <a href=\"#\">");
		out.append("                                                        <i class=\"far fa-star\"></i>");
		out.append("                                                    </a>");
		out.append("                                                    <a href=\"#\">");
		out.append("                                                        <i class=\"far fa-star\"></i>");
		out.append("                                                    </a>");
		out.append("                                                    <a href=\"#\">");
		out.append("                                                        <i class=\"far fa-star\"></i>");
		out.append("                                                    </a>");
		out.append("                                                    <a href=\"#\">");
		out.append("                                                        <i class=\"far fa-star\"></i>");
		out.append("                                                    </a>");
		out.append("                                                    <a href=\"#\">");
		out.append("                                                        <i class=\"far fa-star\"></i>");
		out.append("                                                    </a>");
		out.append("                                                </div>");
		out.append("                                            </div>");
		out.append("                                            <form class=\"review-form\" action=\"#\">");
		out.append("                                                <div class=\"row\">");
		out.append("                                                    <div class=\"col-xl-12\">");
		out.append("                                                        <label for=\"message\">YOUR REVIEW</label>");
		out.append("                                                        <textarea name=\"message\" id=\"message\" cols=\"30\"");
		out.append("                                                            rows=\"10\"></textarea>");
		out.append("                                                    </div>");
		out.append("                                                    <div class=\"col-xl-6\">");
		out.append("                                                        <label for=\"r-name\">Name</label>");
		out.append("                                                        <input type=\"text\" id=\"r-name\">");
		out.append("                                                    </div>");
		out.append("                                                    <div class=\"col-xl-6\">");
		out.append("                                                        <label for=\"r-email\">Email</label>");
		out.append("                                                        <input type=\"email\" id=\"r-email\">");
		out.append("                                                    </div>");
		out.append("                                                    <div class=\"col-xl-12\">");
		out.append("                                                        <button class=\"btn theme-btn\">Add your Review</button>");
		out.append("                                                    </div>");
		out.append("                                                </div>");
		out.append("                                            </form>");
		out.append("                                        </div>");
		out.append("                                    </div>");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </div>");
		out.append("        </section>");
		out.append("        <!-- shop-area end -->");
		out.append("    </main>");

		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/public/footer-view");
		footerDispatcher.include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
