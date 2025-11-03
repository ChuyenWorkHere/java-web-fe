package servlet.user.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.dao.CartDAO;
import servlet.dao.impl.CartDAOImpl;
import servlet.models.Cart;
import servlet.models.User;
import servlet.utils.ProductUtils;


@WebServlet("/customer/cart")
public class UserCartView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	private CartDAO cartDAO;

    public UserCartView() {
        super();
		this.cartDAO = new CartDAOImpl();
    }
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("customer");

		if(loggedInUser == null) {
			response.sendRedirect(request.getContextPath() + "/public/login");
			return;
		}

		List<Cart> cartItems = cartDAO.getCartByUserId(loggedInUser.getUserId());
		double cartSubtotal = 0;
		for (Cart item : cartItems) {
			double price = item.getProduct().getProductDiscountPrice() > 0 ? item.getProduct().getProductDiscountPrice() : item.getProduct().getProductPrice();
			cartSubtotal += price * item.getQuantity();
		}

		PrintWriter out = response.getWriter();
		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/public/header-view");
		headerDispatcher.include(request, response);

		out.append("<main>");
		out.append("");
		out.append("        <!-- breadcrumb-area-start -->");
		out.append("        <section class=\"breadcrumb-area\" data-background=\""+ request.getContextPath() +"/user/img/bg/page-title.png\">");
		out.append("            <div class=\"container\">");
		out.append("                <div class=\"row\">");
		out.append("                    <div class=\"col-xl-12\">");
		out.append("                        <div class=\"breadcrumb-text text-center\">");
		out.append("                            <h1>Giỏ hàng</h1>");
		out.append("                            <ul class=\"breadcrumb-menu\">");
		out.append("                                <li><a href=\""+ request.getContextPath() +"/public/home\">Trang chủ</a></li>");
		out.append("                                <li><span>Giỏ hàng</span></li>");
		out.append("                            </ul>");
		out.append("                        </div>");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </div>");
		out.append("        </section>");
		out.append("        <!-- breadcrumb-area-end -->");
		out.append("");
		out.append("        <!-- Cart Area Strat-->");
		out.append("        <section class=\"cart-area pt-100 pb-100\">");
		out.append("            <div class=\"container\">");
		out.append("                <div class=\"row\">");
		out.append("                    <div class=\"col-12\">");
		out.append("                        <form action=\""+ request.getContextPath() +"/customer/cart/update-quantities\" method=\"POST\">");
		out.append("                            <div class=\"table-content table-responsive\">");
		out.append("                                <table class=\"table\">");
		out.append("                                    <thead>");
		out.append("                                        <tr>");
		out.append("                                            <th class=\"product-thumbnail\">Ảnh</th>");
		out.append("                                            <th class=\"cart-product-name\">Tên sản phẩm</th>");
		out.append("                                            <th class=\"product-price\">Giá bán</th>");
		out.append("                                            <th class=\"product-quantity\">Số lượng</th>");
		out.append("                                            <th class=\"product-subtotal\">Tổng giá</th>");
		out.append("                                            <th class=\"product-remove\">Xoá</th>");
		out.append("                                        </tr>");
		out.append("                                    </thead>");
		out.append("                                    <tbody>");
		if (cartItems.isEmpty()) {
			out.append("<tr><td colspan=\"6\" class=\"text-center\">Giỏ hàng của bạn đang trống.</td></tr>");
		} else {
			for (Cart item : cartItems) {
				double price = item.getProduct().getProductDiscountPrice() > 0 ? item.getProduct().getProductDiscountPrice() : item.getProduct().getProductPrice();
				double subtotal = price * item.getQuantity();
				String productImgUrl = ProductUtils.urlArray(item.getProduct().getProductImageUrl())[0];
				out.append("                                        <tr>");
				out.append("                                            <td class=\"product-thumbnail\"><a href=\""+ request.getContextPath() +"/public/product-detail?productId="+ item.getProduct().getProductId() +"\"><img src=\""+ request.getContextPath() + productImgUrl +"\" alt=\""+ item.getProduct().getProductName() +"\"></a></td>");
				out.append("                                            <td class=\"product-name\"><a href=\""+ request.getContextPath() +"/public/product-detail?productId="+ item.getProduct().getProductId() +"\">"+ item.getProduct().getProductName() +"</a></td>");
				out.append("                                            <td class=\"product-price\"><span class=\"amount\">"+ ProductUtils.formatNumber(price) +"</span></td>");
				out.append("                                            <td class=\"product-quantity\">");
				out.append("                                                <div class=\"cart-plus-minus\"><input type=\"text\" name=\"quantity_"+ item.getCartId() +"\" value=\""+ item.getQuantity() +"\" /></div>");
				out.append("                                            </td>");
				out.append("                                            <td class=\"product-subtotal\"><span class=\"amount\">"+ ProductUtils.formatNumber(subtotal) +"</span></td>");
				out.append("                                            <td class=\"product-remove\"><a href=\""+ request.getContextPath() +"/customer/cart/remove?cartId="+ item.getCartId() +"\" title=\"Xóa sản phẩm\"><i class=\"fa fa-times\"></i></a></td>");
				out.append("                                        </tr>");
			}
		}
		out.append("                                    </tbody>");
		out.append("                                </table>");
		out.append("                            </div>");
		out.append("                            <div class=\"row\">");
		out.append("                                <div class=\"col-12\">");
		out.append("                                    <div class=\"coupon-all\">");
		out.append("                                        <div class=\"coupon\">");
		out.append("                                            <a href=\""+ request.getContextPath() +"/public/shop\" class=\"btn theme-btn-2\" >");
		out.append("                                                Tiếp tục mua sắm");
		out.append("                                            </a>");
		out.append("                                        </div>");
		if (!cartItems.isEmpty()) {
			out.append("                                        <div class=\"coupon2\">");
			out.append("                                            <button type=\"submit\" class=\"btn theme-btn\">Cập nhật giỏ hàng</button>");
			out.append("                                        </div>");
		}
		out.append("                                    </div>");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("                            <div class=\"row\">");
		out.append("                                <div class=\"col-md-5 ml-auto\">");
		out.append("                                    <div class=\"cart-page-total\">");
		out.append("                                        <h2>Tổng giỏ hàng</h2>");
		out.append("                                        <ul class=\"mb-20\">");
		out.append("                                            <li>Tổng số sản phẩm <span>"+ cartItems.size() +"</span></li>");
		out.append("                                            <li>Tổng tiền thanh toán <span>"+ ProductUtils.formatNumber(cartSubtotal) +"</span></li>");
		out.append("                                        </ul>");
		out.append("                                        <a class=\"btn theme-btn\" href=\""+ request.getContextPath() +"/customer/checkout\">Tiến hành thanh toán</a>");
		out.append("                                    </div>");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("                        </form>");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </div>");
		out.append("        </section>");
		out.append("        <!-- Cart Area End-->");
		out.append("");
		out.append("");
		out.append("        </main>");

		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/public/footer-view");
		footerDispatcher.include(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
