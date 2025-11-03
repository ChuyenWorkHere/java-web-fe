package servlet.user.view;

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
import servlet.dao.ProductDAO;
import servlet.dao.impl.ProductDAOImpl;
import servlet.models.Product;
import servlet.response.ProductResponse;
import servlet.utils.ProductUtils;


@WebServlet("/public/home")
public class UserHomeView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	private ProductDAO productDAO;

    public UserHomeView() {
        super();
		this.productDAO = new ProductDAOImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");


		List<Product> newestProducts = productDAO.findNewestProducts(8);
		List<Product> bestSellProducts = productDAO.findBestSellProducts(8);

		List<ProductResponse> newestProductResponses = new ArrayList<>();
		List<ProductResponse> bestSellProductResponses = new ArrayList<>();

		newestProducts.forEach(product -> {
			ProductResponse productResponse = new ProductResponse();
			ProductUtils.copyProductToProductResponse(product, productResponse);
			newestProductResponses.add(productResponse);
		});

		bestSellProducts.forEach(product -> {
			ProductResponse productResponse = new ProductResponse();
			ProductUtils.copyProductToProductResponse(product, productResponse);
			bestSellProductResponses.add(productResponse);
		});


		PrintWriter out = response.getWriter();

		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/public/header-view");
		headerDispatcher.include(request, response);


		out.append("<main>");
		out.append("            <!-- slider-area start -->");
		out.append("            <section class=\"slider-area pos-relative\">");
		out.append("                <div class=\"slider-active\">");

		for (ProductResponse product : newestProductResponses.subList(0, Math.min(newestProductResponses.size(), 3))) {
			out.append("                    <div class=\"single-slider slide-1-style slide-height d-flex align-items-center\">");
			out.append("                        <div class=\"shape-title bounce-animate\">");
			out.append("                            <h2>"+product.getBrand().getBrandName()+"</h2>");
			out.append("                        </div>");
			out.append("                        <div class=\"container-fluid\">");
			out.append("                            <div class=\"row\">");
			out.append("                                <div class=\"col-xl-7\">");
			out.append("                                    <div class=\"slide-content\">");
			out.append("                                        <span data-animation=\"fadeInRight\" data-delay=\".2s\">"+ product.getCategory().getCategoryName() +"</span>");
			out.append("                                        <h1 data-animation=\"fadeInUp\" data-delay=\".5s\">"+ product.getProductName() +"</h1>");
			out.append("                                        <div class=\"slide-btn\">");
			out.append("                                            <a class=\"btn theme-btn\" href=\"../public/product-detail?productId="+ product.getProductId() +"\" data-animation=\"fadeInLeft\" data-delay=\".7s\">MUA NGAY</a>");
			out.append("                                            <a class=\"btn white-btn\" href=\""+ request.getContextPath() +"/public/shop\" data-animation=\"fadeInRight\" data-delay=\".9s\">yêu thích</a>");
			out.append("                                        </div>");
			out.append("                                    </div>");
			out.append("                                </div>");
			out.append("								<div class=\"col-xl-5\">");
			out.append("									<div class=\"slide-shape1 d-none d-lg-block\" data-animation=\"bounceInRight\" data-delay=\".9s\">");
			out.append("                                        <img class=\"rounded-circle\" src=\""+ request.getContextPath() + product.getProductImageUrls().get(0) +"\" alt=\""+ product.getProductName() +"\">");
			out.append("                                    </div>");
			out.append("								</div>");
			out.append("                            </div>");
			out.append("                        </div>");
			out.append("                    </div>");
		}

		out.append("                </div>");
		out.append("            </section>");
		out.append("            <!-- slider-area end -->");
		out.append("");
		out.append("            <!-- banner area start -->");
		out.append("            <section class=\"banner-area pt-30 pl-15 pr-15\">");
		out.append("                <div class=\"container-fluid\">");
		out.append("                    <div class=\"row\">");
		out.append("                        <div class=\"col-lg-4 col-md-6\">");
		out.append("                            <div class=\"banner mb-30\">");
		out.append("                                <a href=\"../public/shop\"><img src=\"../user/img/banner/banner-1/banner1.jpg\" alt=\"\"></a>");
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("                        <div class=\"col-lg-4 col-md-6\">");
		out.append("                            <div class=\"banner mb-30\">");
		out.append("                                <a href=\"../public/shop\"><img src=\"../user/img/banner/banner-1/banner2.jpg\" alt=\"\"></a>");
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("                        <div class=\"col-lg-4 col-md-6\">");
		out.append("                            <div class=\"banner mb-30\">");
		out.append("                                <a href=\"../public/shop\"><img src=\"../user/img/banner/banner-1/banner3.jpg\" alt=\"\"></a>");
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </section>");
		out.append("            <!-- banner area end -->");
		out.append("");

		out.append("            <!-- product-area start -->");
		out.append("            <section class=\"product-area box-90 pt-70 pb-40\">");
		out.append("                <div class=\"container-fluid\">");
		out.append("                    <div class=\"row\">");
		out.append("                        <div class=\"col-xl-5 col-lg-12\">");
		out.append("                            <div class=\"area-title mb-50\">");
		out.append("                                <h2>MỚI NHẤT</h2>");
		out.append("                                <p>Khám phá các sản phẩm mới nhất của chúng tôi</p>");
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("                        <div class=\"col-xl-7 col-lg-12\">");

		out.append("                        </div>");
		out.append("                    </div>");
		out.append("                    <div class=\"row\">");
		out.append("                        <div class=\"col-xl-12\">");
		out.append("                            <div class=\"product-tab-content\">");
		out.append("                                <div class=\"tab-content\" id=\"myTabContent\">");
		out.append("                                    <div class=\"tab-pane fade show active\" id=\"home\" role=\"tabpanel\" aria-labelledby=\"home-tab\">");
		out.append("                                        <div class=\"product-slider owl-carousel\">");

		newestProductResponses.forEach(product -> {
			out.append("                                            <div class=\"pro-item\">");
			out.append("                                                <div class=\"product-wrapper mb-50\">");
			out.append("                                                    <div class=\"product-img mb-25\">");
			out.append("                                                        <a href=\"../public/product-detail?productId="+product.getProductId()+"\">");
			out.append("                                                            <img src=\".."+product.getProductImageUrls().get(0)+"\" alt=\"\">");
			out.append("                                                            <img class=\"secondary-img\" src=\".."+product.getProductImageUrls().get(1)+"\" alt=\"\">");
			out.append("                                                        </a>");
			out.append("                                                        <div class=\"product-action text-center\">");
			out.append("                                                            <a href=\""+request.getContextPath()  + "/customer/cart/update?productId="+product.getProductId()+"\" title=\"Shopping Cart\">");
			out.append("                                                                <i class=\"flaticon-shopping-cart\"></i>");
			out.append("                                                            </a>");
			out.append("                                                            <a href=\""+request.getContextPath() + "/public/product-detail?productId="+product.getProductId()+"\"" +"\" title=\"Xem chi tiết\">");
			out.append("                                                                <i class=\"flaticon-eye\"></i>");
			out.append("                                                            </a>");
			out.append("                                                        </div>");
			out.append("                                                        <div class=\"sale-tag\">");
			out.append("                                                            <span class=\"new\">new</span>");
			if(product.getProductDiscountPrice() > 0) {
				out.append("                                                            <span class=\"sale\">sale</span>");
			}
			out.append("                                                        </div>");
			out.append("                                                    </div>");
			out.append("                                                    <div class=\"product-content\">");
			out.append("                                                        <div class=\"pro-cat mb-10\">");
			out.append("                                                            <a href=\"../public/product-detail?productId="+product.getProductId()+"\">"+product.getCategory().getCategoryName()+"</a>");
			out.append("                                                        </div>");
			out.append("                                                        <h4>");
			out.append("                                                            <a href=\"../public/product-detail?productId="+product.getProductId()+"\">"+product.getProductName()+"</a>");
			out.append("                                                        </h4>");
			out.append("                                                        <div class=\"product-meta\">");
			out.append("                                                            <div class=\"pro-price\">");
			out.append("                                                                <span>"+ ProductUtils.formatNumber(product.getProductPrice()) +"</span>");
			out.append("                                                                <span class=\"old-price\">"+ProductUtils.formatNumber(product.getProductDiscountPrice())+"</span>");
			out.append("                                                            </div>");
			out.append("                                                        </div>");
			out.append("                                                        <div class=\"product-wishlist\">");
			out.append("                                                            <a href=\"#\"><i class=\"far fa-heart\" title=\"Wishlist\"></i></a>");
			out.append("                                                        </div>");
			out.append("                                                    </div>");
			out.append("                                                </div>");
			out.append("                                            </div>");
		});
		out.append("                                        </div>");
		out.append("                                    </div>");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </section>");
		out.append("            <!-- product-area end -->");
		out.append("");
		out.append("            <!-- product-area start -->");
		out.append("            <section class=\"product-area box-90 pt-45 pb-40\">");
		out.append("                <div class=\"container-fluid\">");
		out.append("                    <div class=\"row\">");
		out.append("                        <div class=\"col-xl-5 col-lg-12\">");
		out.append("                            <div class=\"area-title mb-50\">");
		out.append("                                <h2>BÁN CHẠY NHẤT</h2>");
		out.append("                                <p>Khám phá các sản phẩm mới nhất của chúng tôi</p>");
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("                        <div class=\"col-xl-7 col-lg-12\">");
		out.append("                        </div>");
		out.append("                    </div>");
		out.append("                    <div class=\"row\">");
		out.append("                        <div class=\"col-xl-12\">");
		out.append("                            <div class=\"product-tab-content\">");
		out.append("                                <div class=\"tab-content\" id=\"myTabContent1\">");
		out.append("                                    <div class=\"tab-pane fade show active\" id=\"home1\" role=\"tabpanel\" aria-labelledby=\"home-tab\">");
		out.append("                                        <div class=\"product-slider owl-carousel\">");
		bestSellProductResponses.forEach(product -> {
			out.append("                                            <div class=\"pro-item\">");
			out.append("                                                <div class=\"product-wrapper mb-50\">");
			out.append("                                                    <div class=\"product-img mb-25\">");
			out.append("                                                        <a href=\"../public/product-detail?productId="+product.getProductId()+"\">");
			out.append("                                                            <img src=\".."+product.getProductImageUrls().get(0)+"\" alt=\"\">");
			out.append("                                                            <img class=\"secondary-img\" src=\".."+ (product.getProductImageUrls().get(1) == null ? product.getProductImageUrls().get(1) : product.getProductImageUrls().get(0) )+"\" alt=\"\">");
			out.append("                                                        </a>");
			out.append("                                                        <div class=\"product-action text-center\">");
			out.append("                                                            <a href=\""+request.getContextPath() + "/customer/cart/update?productId="+product.getProductId()+"\" title=\"Shopping Cart\">");
			out.append("                                                                <i class=\"flaticon-shopping-cart\"></i>");
			out.append("                                                            </a>");
			out.append("                                                            <a href=\""+request.getContextPath() + "/public/product-detail?productId="+product.getProductId() +"\" title=\"Xem chi tiết\">");
			out.append("                                                                <i class=\"flaticon-eye\"></i>");
			out.append("                                                            </a>");
			out.append("                                                        </div>");
			out.append("                                                        <div class=\"sale-tag\">");
			out.append("                                                            <span class=\"new\">new</span>");
			if(product.getProductDiscountPrice() > 0) {
				out.append("                                                            <span class=\"sale\">sale</span>");
			}
			out.append("                                                        </div>");
			out.append("                                                    </div>");
			out.append("                                                    <div class=\"product-content\">");
			out.append("                                                        <div class=\"pro-cat mb-10\">");
			out.append("                                                            <a href=\"../public/product-detail?productId="+product.getProductId()+"\">"+product.getCategory().getCategoryName()+"</a>");
			out.append("                                                        </div>");
			out.append("                                                        <h4>");
			out.append("                                                            <a href=\"../public/product-detail?productId="+product.getProductId()+"\">"+product.getProductName()+"</a>");
			out.append("                                                        </h4>");
			out.append("                                                        <div class=\"product-meta\">");
			out.append("                                                            <div class=\"pro-price\">");
			out.append("                                                                <span>"+ProductUtils.formatNumber(product.getProductPrice()) +"</span>");
			out.append("                                                                <span class=\"old-price\">"+ProductUtils.formatNumber(product.getProductDiscountPrice())+"</span>");
			out.append("                                                            </div>");
			out.append("                                                        </div>");
			out.append("                                                        <div class=\"product-wishlist\">");
			out.append("                                                            <a href=\"#\"><i class=\"far fa-heart\" title=\"Wishlist\"></i></a>");
			out.append("                                                        </div>");
			out.append("                                                    </div>");
			out.append("                                                </div>");
			out.append("                                            </div>");
		});
		out.append("                                        </div>");
		out.append("                                    </div>");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </section>");
		out.append("            <!-- product-area end -->");
		out.append("");
		out.append("            <!-- latest-blog-area start -->");
		out.append("            <section class=\"latest-blog-area pt-95 pb-60 box-90\">");
		out.append("                <div class=\"container-fluid\">");
		out.append("                    <div class=\"row\">");
		out.append("                        <div class=\"col-xl-12\">");
		out.append("                            <div class=\"area-title text-center mb-50\">");
		out.append("                                <h2>News Feeds</h2>");
		out.append("                                <p>Check it out every updates</p>");
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("                    </div>");
		out.append("                    <div class=\"row\">");
		out.append("                        <div class=\"col-xl-4 col-lg-6 col-md-6\">");
		out.append("                            <div class=\"latest-news mb-40\">");
		out.append("                                <div class=\"news__thumb mb-25\">");
		out.append("                                    <img src=\"../user/img/blog/latest/lb1.jpg\" alt=\"\">");
		out.append("                                </div>");
		out.append("                                <div class=\"news__caption white-bg\">");
		out.append("                                    <div class=\"news-meta mb-15\">");
		out.append("                                        <span><i class=\"far fa-calendar-check\"></i> Sep 15, 2018 </span>");
		out.append("                                        <span><a href=\"#\"><i class=\"far fa-user\"></i> Diboli</a></span>");
		out.append("                                        <span><a href=\"#\"><i class=\"far fa-comments\"></i> 23 Comments</a></span>");
		out.append("                                    </div>");
		out.append("                                    <h2><a href=\"blog-details.html\">Inspiration Is Under Construction Business &");
		out.append("                                    Fashion 2019. In this situation we do that..</a></h2>");
		out.append("                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna");
		out.append("                                    aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo..</p>");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("                        <div class=\"col-xl-4 col-lg-6 col-md-6\">");
		out.append("                            <div class=\"latest-news mb-40\">");
		out.append("                                <div class=\"news__thumb mb-25\">");
		out.append("                                    <img src=\"../user/img/blog/latest/lb2.jpg\" alt=\"\">");
		out.append("                                </div>");
		out.append("                                <div class=\"news__caption white-bg\">");
		out.append("                                    <div class=\"news-meta mb-15\">");
		out.append("                                        <span><i class=\"far fa-calendar-check\"></i> Sep 15, 2018 </span>");
		out.append("                                        <span><a href=\"#\"><i class=\"far fa-user\"></i> Joly</a></span>");
		out.append("                                        <span><a href=\"#\"><i class=\"far fa-comments\"></i> 23 Comments</a></span>");
		out.append("                                    </div>");
		out.append("                                    <h2><a href=\"blog-details.html\">Inspiration Is Under Construction Business &");
		out.append("                                    Fashion 2019. In this situation we do that..</a></h2>");
		out.append("                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna");
		out.append("                                    aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo..</p>");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("                        <div class=\"col-xl-4 col-lg-6 col-md-6\">");
		out.append("                            <div class=\"latest-news mb-40\">");
		out.append("                                <div class=\"news__thumb mb-25\">");
		out.append("                                    <img src=\"../user/img/blog/latest/lb3.jpg\" alt=\"\">");
		out.append("                                </div>");
		out.append("                                <div class=\"news__caption white-bg\">");
		out.append("                                    <div class=\"news-meta mb-15\">");
		out.append("                                        <span><i class=\"far fa-calendar-check\"></i> Sep 15, 2018 </span>");
		out.append("                                        <span><a href=\"#\"><i class=\"far fa-user\"></i> Joly</a></span>");
		out.append("                                        <span><a href=\"#\"><i class=\"far fa-comments\"></i> 23 Comments</a></span>");
		out.append("                                    </div>");
		out.append("                                    <h2><a href=\"blog-details.html\">Inspiration Is Under Construction Business &");
		out.append("                                    Fashion 2019. In this situation we do that..</a></h2>");
		out.append("                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna");
		out.append("                                    aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo..</p>");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </section>");
		out.append("            <!-- latest-blog-area end -->");
		out.append("");
		out.append("            <!-- subscribe-area start -->");
		out.append("            <section class=\"subscribe-area box-105\">");
		out.append("                <div class=\"subscribe-inner black-bg pt-70 pb-20\">");
		out.append("                    <div class=\"container-fluid\">");
		out.append("                        <div class=\"row\">");
		out.append("                            <div class=\"col-xl-5\">");
		out.append("                                <div class=\"subscribe d-flex fix\">");
		out.append("                                    <div class=\"subscribe-icon\">");
		out.append("                                        <img src=\"../user/img/icon/subscribe.png\" alt=\"\">");
		out.append("                                    </div>");
		out.append("                                    <div class=\"area-title white-color mb-50\">");
		out.append("                                        <h2>Newsletter</h2>");
		out.append("                                        <p>Subsribe here for get every single updates</p>");
		out.append("                                    </div>");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("                            <div class=\"col-xl-7\">");
		out.append("                                <div class=\"subscribe fix\">");
		out.append("                                    <div class=\"subscribe-form mb-50\">");
		out.append("                                        <form action=\"#\">");
		out.append("                                            <input type=\"text\" placeholder=\"Enter your email address\">");
		out.append("                                            <button class=\"btn theme-btn\" type=\"submit\">subscribe now</button>");
		out.append("                                        </form>");
		out.append("                                    </div>");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </section>");
		out.append("            <!-- subscribe-area end -->");
		out.append("        </main>");

		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/public/footer-view");
		footerDispatcher.include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
