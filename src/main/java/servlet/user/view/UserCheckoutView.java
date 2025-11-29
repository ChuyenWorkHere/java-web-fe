package servlet.user.view;

import servlet.dao.CartDAO;
import servlet.dao.impl.CartDAOImpl;
import servlet.models.Cart;
import servlet.models.User;
import servlet.utils.ProductUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/customer/checkout-view")
public class UserCheckoutView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private CartDAO cartDAO;
    public UserCheckoutView() {
        super();
		cartDAO = new CartDAOImpl();
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
		long cartSubtotal = 0;
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
		out.append("                            <h1>Thanh toán</h1>");
		out.append("                            <ul class=\"breadcrumb-menu\">");
		out.append("                                <li><a href=\""+ request.getContextPath() +"/public/home\">TRANG CHỦ</a></li>");
		out.append("                                <li><span>THANH TOÁN</span></li>");
		out.append("                            </ul>");
		out.append("                        </div>");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </div>");
		out.append("        </section>");
		out.append("        <!-- breadcrumb-area-end -->");
		out.append("");
		out.append("        <!-- checkout-area start -->");
		out.append("        <section class=\"checkout-area pb-70\">");
		out.append("            <div class=\"container\">");
		out.append("                <form action=\""+request.getContextPath() + "/customer/checkout\" method=\"post\">");
		out.append("                    <div class=\"row\">");
		out.append("                        <div class=\"col-lg-6\">");
		out.append("                            <div class=\"checkbox-form\">");
		out.append("                                <p class=\"pt-5\"><strong>Lưu ý:</strong> Để trải nghiệm mua sắm được tối ưu nhất, vui lòng cập nhật thông tin tài khoản của bạn ngay tại đây. <a class=\"text-danger\" href=\""+ (request.getContextPath()+ "/customer/profile") +"\">Cập nhật</a></p>");
		out.append("                                <h3 class=\"pt-5\">Thông tin thanh toán</h3>");
		out.append("                                <div class=\"row\">");
		out.append("                                    <div class=\"col-md-12\">");
		out.append("                                        <div class=\"checkout-form-list\">");
		out.append("                                            <label>Họ và tên <span class=\"required\">*</span></label>");
		out.append("                                            <input type=\"text\" name=\"fullName\" value=\""+loggedInUser.getFullname()+"\" placeholder=\"\" />");
		out.append("                                        </div>");
		out.append("                                    </div>");
		out.append("                                    <div class=\"col-md-12\">");
		out.append("                                        <div class=\"checkout-form-list\">");
		out.append("                                            <label>Địa chỉ <span class=\"required\">*</span></label>");
		out.append("                                            <input type=\"text\" name=\"address\" value=\""+loggedInUser.getAddress()+"\" placeholder=\"Địa chỉ chi tiết\" />");
		out.append("                                        </div>");
		out.append("                                    </div>");
		out.append("                                    <div class=\"col-md-12\">");
		out.append("                                        <div class=\"checkout-form-list\">");
		out.append("                                            <label>Tỉnh/ Thành phố <span class=\"required\">*</span></label>");
		out.append("                                            <input type=\"text\" name=\"city\" placeholder=\"Tỉnh/ Thành phố\" />");
		out.append("                                        </div>");
		out.append("                                    </div>");
		out.append("                                    <div class=\"col-md-6\">");
		out.append("                                        <div class=\"checkout-form-list\">");
		out.append("                                            <label>Quận/Huyện <span class=\"required\">*</span></label>");
		out.append("                                            <input type=\"text\" name=\"district\" placeholder=\"\" />");
		out.append("                                        </div>");
		out.append("                                    </div>");
		out.append("                                    <div class=\"col-md-6\">");
		out.append("                                        <div class=\"checkout-form-list\">");
		out.append("                                            <label>Xã/Phường/Thị trấn <span class=\"required\">*</span></label>");
		out.append("                                            <input type=\"text\" name=\"ward\" placeholder=\"\" />");
		out.append("                                        </div>");
		out.append("                                    </div>");
		out.append("                                    <div class=\"col-md-6\">");
		out.append("                                        <div class=\"checkout-form-list\">");
		out.append("                                            <label>Số điện thoại <span class=\"required\">*</span></label>");
		out.append("                                            <input type=\"text\" name=\"phone\" value=\""+loggedInUser.getPhoneNumber()+"\" placeholder=\"\" />");
		out.append("                                        </div>");
		out.append("                                    </div>");
		out.append("                                    <div class=\"col-md-6\">");
		out.append("                                        <div class=\"checkout-form-list\">");
		out.append("                                            <label>Địa chỉ email <span class=\"required\">*</span></label>");
		out.append("                                            <input type=\"email\" name=\"email\" value=\""+loggedInUser.getEmail()+"\" placeholder=\"\" />");
		out.append("                                        </div>");
		out.append("                                    </div>");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("                        <div class=\"col-lg-6\">");
		out.append("                            <div class=\"your-order mb-30 \">");
		out.append("                                <h3>Đơn hàng của bạn</h3>");
		out.append("                                <div class=\"your-order-table table-responsive\">");
		out.append("                                    <table>");
		out.append("                                        <thead>");
		out.append("                                            <tr>");
		out.append("                                                <th class=\"product-name\">Sản phẩm</th>");
		out.append("                                                <th class=\"product-total\">Tổng</th>");
		out.append("                                            </tr>");
		out.append("                                        </thead>");
		out.append("                                        <tbody>");
		cartItems.forEach(item -> {
			out.append("                                            <tr class=\"cart_item\">");
			out.append("                                                <td class=\"product-name\">");
			out.append("                                                    "+item.getProduct().getProductName()+" <strong class=\"product-quantity\"> × "+item.getQuantity()+"</strong>");
			out.append("                                                </td>");
			out.append("                                                <td class=\"product-total\">");
			out.append("                                                    <span class=\"amount\">"+ ProductUtils.formatNumber(item.getProduct().getProductDiscountPrice())+"</span>");
			out.append("                                                </td>");
			out.append("                                            </tr>");
		});
		out.append("                                        </tbody>");
		out.append("                                        <tfoot>");
		out.append("                                            <tr class=\"cart-subtotal\">");
		out.append("                                                <th>Tạm tính</th>");
		out.append("                                                <td><span class=\"amount\">"+ProductUtils.formatNumber(cartSubtotal)+"</span></td>");
		out.append("                                            </tr>");
		out.append("                                            <tr class=\"shipping\">");
		out.append("                                                <th>Phí vận chuyển</th>");
		out.append("                                                <td>");
		out.append("                                                    Free Shipping");
		out.append("                                                </td>");
		out.append("                                            </tr>");
		out.append("                                            <tr class=\"order-total\">");
		out.append("                                                <th>Tổng đơn hàng</th>");
		out.append("                                                <td><strong><span class=\"amount\">"+ProductUtils.formatNumber(cartSubtotal)+"</span></strong>");
		out.append("                                                </td>");
		out.append("                                            	<input type=\"hidden\" name=\"amount\" value=\""+ String.valueOf(cartSubtotal) +"\" />");
		out.append("                                            </tr>");
		out.append("                                        </tfoot>");
		out.append("                                    </table>");
		out.append("                                </div>");
		out.append("");
		out.append("                                <div class=\"payment-method\">");
		out.append("                                    <div class=\"accordion\" id=\"accordionExample\">");
		out.append("										<hr>");
		out.append("                                        <div class=\"form-check mb-2\">");
		out.append("                                            <input class=\"form-check-input\" type=\"radio\" name=\"payment\" id=\"paymentCOD\"");
		out.append("                                                value=\"cod\" checked>");
		out.append("                                            <label class=\"form-check-label fw-bold\" for=\"paymentCOD\">");
		out.append("                                                Thanh toán khi nhận hàng");
		out.append("                                            </label>");
		out.append("                                            <div class=\"form-text ms-4\">Thanh toán bằng tiền mặt khi nhận hàng.</div>");
		out.append("                                        </div>");
		out.append("                                        <hr>");
		out.append("                                        <div class=\"form-check\">");
		out.append("                                            <input class=\"form-check-input\" type=\"radio\" name=\"payment\"");
		out.append("                                                id=\"paymentVNPAY\" value=\"vnpay\">");
		out.append("                                            <label class=\"form-check-label fw-bold\" for=\"paymentVNPAY\">");
		out.append("                                                Ví điện tử VNPay");
		out.append("                                            </label>");
		out.append("                                            <div class=\"form-text ms-4\">Thanh toán nhanh qua VNPay.</div>");
		out.append("                                        </div>");
		out.append("                                        <hr>");
		out.append("                                    </div>");
		out.append("                                    <div class=\"order-button-payment mt-20\">");
		out.append("                                        <button type=\"submit\" class=\"btn theme-btn\">Đặt hàng</button>");
		out.append("                                    </div>");
		out.append("                                </div>");
		out.append("                            </div>");
		out.append("                        </div>");
		out.append("                    </div>");
		out.append("                </form>");
		out.append("            </div>");
		out.append("        </section>");
		out.append("        <!-- checkout-area end -->");
		out.append("");
		out.append("");
		out.append("</main>");

		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/public/footer-view");
		footerDispatcher.include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
