package servlet.user.view;

import servlet.constants.Avatar;
import servlet.dao.ProductDAO;
import servlet.dao.ReviewDAO;
import servlet.dao.impl.ProductDAOImpl;
import servlet.dao.impl.ReviewDAOImpl;
import servlet.models.Product;
import servlet.models.Review;
import servlet.models.User;
import servlet.response.ProductResponse;
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
import javax.servlet.http.HttpSession;


@WebServlet("/public/product-detail")
public class UserProductDetailView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDAO productDAO;
	private ReviewDAO reviewDAO;

    public UserProductDetailView() {
        super();
        productDAO = new ProductDAOImpl();
		reviewDAO = new ReviewDAOImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("customer");

		Product product;
		List<Review> reviews = new ArrayList<>();
		ProductResponse productResponse = new ProductResponse();

		try {
			int productId = Integer.parseInt(request.getParameter("productId"));
			product = productDAO.findById(productId);
			reviews = reviewDAO.getReviewsByProductId(productId);
			ProductUtils.copyProductToProductResponse(product, productResponse);
		} catch (Exception e) {
			response.sendRedirect("../public/shop");
			return;
		}

		//Chế độ review
		String mode = "";
		try {
			mode = request.getParameter("mode");
		} catch (Exception e) {
			response.sendRedirect("../public/shop");
			return;
		}

		int orderId = -1;
		try {
			if(request.getParameter("orderId") != null){
				orderId = Integer.parseInt(request.getParameter("orderId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();

		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/public/header-view");
		headerDispatcher.include(request, response);
		out.append("<main>");
		out.append("");
		out.append("        <!-- breadcrumb-area-start -->");
		out.append("        <section class=\"breadcrumb-area\" data-background=\"../user/img/bg/page-title.png\">");
		out.append("            <div class=\"container\">");
		out.append("                <div class=\"row\">");
		out.append("                    <div class=\"col-xl-12\">");
		out.append("                        <div class=\"breadcrumb-text text-center\">");
		out.append("                            <h1>Chi tiết sản phẩm</h1>");
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
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("                    </div>");
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
		out.append("                                <div class=\"product-Metarial variant-item py-3\">");
		out.append("                                    <div class=\"variant-name\">");
		out.append("                                        <span>Chất liệu: "+productResponse.getMaterial().getMaterialName()+"</span>");
		out.append("                                    </div>");
		out.append("                                    <div class=\"product-desc mt-2\">");
		out.append("                                        <p>"+productResponse.getMaterial().getDescription()+"</p>");
		out.append("                                    </div>");
		out.append("                                </div>");
		out.append("                                <div class=\"product-Metarial variant-item py-3\">");
		out.append("                                    <div class=\"variant-name\">");
		out.append("                                        <span>Kích thước</span>");
		out.append("                                    </div>");
		out.append("                                    <div class=\"product-desc mt-2\">");
		out.append("                                        <p>"+productResponse.getProductSize()+"</p>");
		out.append("                                    </div>");
		out.append("                                </div>");
		out.append("");
		out.append("                                <div class=\"product-info-list variant-item\">");
		out.append("                                    <ul>");
		out.append("                                        <li><span>Thương hiệu:</span> "+productResponse.getBrand().getBrandName()+"</li>");
		out.append("                                        <li><span>Số lượng :</span> "+productResponse.getProductTotal()+"</li>");
		out.append("                                        <li><span>Tình trạng:</span> <span class=\"in-stock "+(productResponse.getProductTotal() > 0 ? "text-success" : "text-danger")+"\">"+(productResponse.getProductTotal() > 0 ? "Còn hàng" : "Hết hàng")+"</span></li>");
		out.append("                                        <li><span>Đánh giá:</span> "+reviewDAO.getAverageRatingByProductId(product.getProductId())+"/5.0</li>");
		out.append("                                    </ul>");
		out.append("                                </div>");
		out.append("");
		out.append("                                <div class=\"product-action-details variant-item\">");
		out.append("                                    <div class=\"product-details-action\">");
		out.append("                                        <form action=\""+request.getContextPath() + "/customer/cart/update" +"\" method=\"post\">");
		out.append("                                            <div class=\"plus-minus\">");
		out.append("                                                <div class=\"cart-plus-minus\"><input type=\"text\" name=\"quantity\" value=\"1\" /></div>");
		out.append("                                            	<input type=\"hidden\" name=\"productId\" value=\""+product.getProductId()+"\" />");
		out.append("                                            </div>");
		out.append("                                            <button class=\"details-action-icon\"><i");
		out.append("                                                    class=\"fas fa-heart\"></i></button>");
		out.append("                                            <button class=\"details-action-icon\"><i");
		out.append("                                                    class=\"fas fa-hourglass\"></i></button>");
		out.append("                                            <div class=\"details-cart mt-40\">");
		out.append("                                                <button type=\"submit\" class=\"btn theme-btn\">Thêm vào giỏ hàng</button>");
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
		out.append("                                    <a class=\"nav-link "+(!"review".equalsIgnoreCase(mode) ? "active" : "")+"\" id=\"home-tab6\" data-toggle=\"tab\" href=\"#home6\" role=\"tab\"");
		out.append("                                        aria-controls=\"home\" aria-selected=\"true\">Description </a>");
		out.append("                                </li>");
		out.append("                                <li class=\"nav-item\">");
		out.append("                                    <a class=\"nav-link "+("review".equalsIgnoreCase(mode) ? "active" : "")+"\" id=\"profile-tab6\" data-toggle=\"tab\" href=\"#profile6\" role=\"tab\"");
		out.append("                                        aria-controls=\"profile\" aria-selected=\"false\">Reviews ("+reviews.size()+")</a>");
		out.append("                                </li>");
		out.append("                            </ul>");
		out.append("                            <div class=\"tab-content\" id=\"myTabContent2\">");
		out.append("                                <div class=\"tab-pane fade "+ (!"review".equalsIgnoreCase(mode) ? "active show" : "")+"\" id=\"home6\" role=\"tabpanel\"");
		out.append("                                    aria-labelledby=\"home-tab6\">");
		out.append("                                    <div class=\"desc-text\">");
		out.append("                                        <p>"+productResponse.getProductDescription()+"</p>");
		out.append("                                    </div>");
		out.append("                                </div>");
		out.append("                                <div class=\"tab-pane fade "+ ("review".equalsIgnoreCase(mode) ? "active show" : "") +"\" id=\"profile6\" role=\"tabpanel\" aria-labelledby=\"profile-tab6\">");
		out.append("                                    <div class=\"desc-text review-text\">");
		if (reviews.isEmpty()) {
			out.append("									<p class=\"text-center\">Sản phẩm này hiện tại chưa có đánh giá nào!</p>");
		} else {
			for (Review r : reviews) {
				out.append("                                        <div class=\"product-commnets\">");
				out.append("                                            <div class=\"product-commnets-list mb-25 pb-15\">");
				out.append("                                                <div class=\"pro-comments-img\">");
				out.append("                                                    <img style=\"width: 40px; height: 40px;\" class=\"rounded-circle\" src=\""+ (r.getUser().getAvatar() == null ? Avatar.DEFAULT_AVATAR : (request.getContextPath() + r.getUser().getAvatar())) +"\" alt=\"User Avatar\">");
				out.append("                                                </div>");
				out.append("                                                <div class=\"pro-commnets-text\">");
				out.append("                                                    <h4>"+r.getUser().getFullname()+" -");
				out.append("                                                        <span>"+r.getCreatedAt()+"</span>");
				out.append("                                                    </h4>");
				out.append("                                                    <div class=\"pro-rating\">");
				for (int i = 1; i <= 5; i++){
					if(i <= r.getRate()){
						out.append("                                                        <i class=\"fas fa-star\"></i>");
					} else {
						out.append("                                                        <i class=\"far fa-star\"></i>");
					}
				}
				out.append("                                                    </div>");
				out.append("                                                    <p>"+r.getComment()+"</p>");
				out.append("                                                </div>");
				out.append("                                            </div>");
				out.append("                                         </div>");
			}
		}

		if(user != null && !reviewDAO.hasUserReviewedProduct(user.getUserId(), product.getProductId())) {
			out.append("                                        <div class=\"review-box mt-50\">");
			out.append("                                            <h4>Đánh giá</h4>");
			out.append("                                            <div class=\"your-rating mb-40\">");
			out.append("                                                <span>Rating:</span>");
			out.append("                                                <div class=\"rating-list\">");
			out.append("                                                    <a href=\"javascript:void(0);\" onclick=\"setRating(1)\">");
			out.append("                                                        <i class=\"far fa-star\" id=\"star-1\"></i>");
			out.append("                                                    </a>");
			out.append("                                                    <a href=\"javascript:void(0);\" onclick=\"setRating(2)\">");
			out.append("                                                        <i class=\"far fa-star\" id=\"star-2\"></i>");
			out.append("                                                    </a>");
			out.append("                                                    <a href=\"javascript:void(0);\" onclick=\"setRating(3)\">");
			out.append("                                                        <i class=\"far fa-star\" id=\"star-3\"></i>");
			out.append("                                                    </a>");
			out.append("                                                    <a href=\"javascript:void(0);\" onclick=\"setRating(4)\">");
			out.append("                                                        <i class=\"far fa-star\" id=\"star-4\"></i>");
			out.append("                                                    </a>");
			out.append("                                                    <a href=\"javascript:void(0);\" onclick=\"setRating(5)\">");
			out.append("                                                        <i class=\"far fa-star\" id=\"star-5\"></i>");
			out.append("                                                    </a>");
			out.append("                                                </div>");
			out.append("                                            </div>");
			out.append("                                            <form class=\"review-form\" action=\""+request.getContextPath()+"/customer/review/add\" method=\"POST\">");
			out.append("                                                <div class=\"row\">");
			out.append("                                                    <div class=\"col-xl-12\">");
			out.append("                                                        <input type=\"hidden\" name=\"rating\" id=\"rating-value\" value=\"0\">");
			out.append("                                                        <input type=\"hidden\" name=\"productId\" value=\""+product.getProductId()+"\">");
			out.append("                                                        <input type=\"hidden\" name=\"orderId\" value=\""+orderId+"\">");
			out.append("                                                        <label for=\"message\">Đánh giá của bạn</label>");
			out.append("                                                        <textarea name=\"message\" id=\"message\" cols=\"30\" rows=\"15\"></textarea>");
			out.append("                                                    </div>");
			out.append("                                                    <div class=\"col-xl-12\">");
			out.append("                                                        <button class=\"btn theme-btn\" type=\"submit\">Đánh giá</button>");
			out.append("                                                    </div>");
			out.append("                                                </div>");
			out.append("                                            </form>");
			out.append("                                        </div>");
		}
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
		out.append("  <script src=\""+ request.getContextPath() +"/user/js/review.js\"></script>");
		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/public/footer-view");
		footerDispatcher.include(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
