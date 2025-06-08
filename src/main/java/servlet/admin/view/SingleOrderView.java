package servlet.admin.view;

import servlet.dao.OrderDAO;
import servlet.dao.impl.OrderDAOImpl;
import servlet.models.Order;
import servlet.models.OrderItem;
import servlet.utils.ProductUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/admin/single-order-view")
public class SingleOrderView extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public SingleOrderView() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
		headerDispatcher.include(request, response);

		int orderId = Integer.parseInt(request.getParameter("orderId"));

		OrderDAO orderDAO = new OrderDAOImpl();
		Order order = orderDAO.getOrderDetailByOrderId(orderId);

		out.append("<main id=\"main\" class=\"main\">");
		out.append("    <div class=\"pagetitle\">");
		out.append("      <nav class=\"d-flex justify-content-between align-items-center flex-wrap\">");
		out.append("        <ol class=\"breadcrumb fs-6\">");
		out.append("          <li class=\"breadcrumb-item\"><a href=\"../admin/home-view\">Trang chủ</a></li>");
		out.append("          <li class=\"breadcrumb-item active\">Chi tiết đơn hàng</li>");
		out.append("        </ol>");
		out.append("        <a href=\"../admin/orders-view\">");
		out.append("          <button type=\"button\" class=\"btn btn-success btn-sm\">Trở về đơn hàng</button>");
		out.append("        </a>");
		out.append("      </nav>");
		out.append("    </div>");
		out.append("");
		out.append("    <section class=\"section dashboard\">");
		out.append("      <div class=\"row mb-5\">");
		out.append("        <div class=\"col-12\">");
		out.append("          <div class=\"card p-4 rounded-4\">");
		out.append("            <div class=\"d-flex justify-content-between align-items-center mb-4\">");
		out.append("              <div>");
		out.append("                <h2 class=\"h5 fw-bold\">Order ID: <span class=\"text-dark fw-bold\">DH#"+order.getOrderId()+"</span>");
		out.append("                  <!-- <span class=\"badge bg-warning text-dark\">Pending</span> -->");
		out.append("                </h2>");
		out.append("              </div>");
		out.append("              <div class=\"d-flex align-items-center\">");
		String className = "";
		String orderStatus = "";
		switch (order.getOrderStatus()){
			case "PENDING":
				className = "btn bg-secondary";
				orderStatus = "Chờ xác nhận";
				break;
			case "SHIPPED":
				className = "bg-warning text-dark";
				orderStatus = "Đang giao";
				break;
			case "DELIVERED":
				className = "bg-success";
				orderStatus = "Thành công";
				break;
			case "CANCELLED":
				className = "bg-danger";
				orderStatus = "Đã huỷ";
				break;
		}
		out.append("                        <span class=\"p-2 badge ");
		out.append(className +"\">"+(order.getOrderStatus().equals("PENDING")
				? "<a class=\"text-white fs-6\" href=\"../admin/single-order-view\">"+orderStatus+"</a>" : orderStatus) + "</span>");

		out.append("              </div>");
		out.append("            </div>");
		out.append("            <div class=\"row mb-4 mx-0 py-4 rounded\">");
		out.append("              <div class=\"col-md-4\">");
		out.append("                <h3 class=\"fs-custom text-dark fw-bold mb-3\">Chi tiết khách hàng</h3>");
		out.append("                <p class=\"fs-6\">Tên khách hàng: "+ order.getUser().getFullname() +"</p>");
		out.append("                <p class=\"fs-6\">Email: "+ order.getUser().getEmail() +"</p>");
		out.append("                <p class=\"fs-6\">Số điện thoại: "+ order.getUser().getPhoneNumber() +"</p>");
		out.append("<a class=\"text-success text-decoration-none pointer\"")
				.append("data-bs-toggle='modal' data-bs-target='#largeModal' ")
				.append("data-name='" + order.getUser().getFullname() + "' ")
				.append("data-gender='" + order.getUser().getGender() + "' ")
				.append("data-phone='" + order.getUser().getPhoneNumber() + "' ")
				.append("data-email='" + order.getUser().getEmail() + "' ")
				.append("data-address='" + order.getUser().getAddress() + "' ")
				.append("data-created='" + ProductUtils.formatDate(order.getUser().getCreateDate()) + "' ")
				.append("data-updated='" + ProductUtils.formatDate(order.getUser().getModifiedDate()) + "' ")
				.append("data-status='" + order.getUser().isActive() + "'>")
				.append("Xem chi tiết")
				.append("</a>");
		out.append("              </div>");
		out.append("              <div class=\"col-md-4\">");

		RequestDispatcher accountModalDispatcher = request.getRequestDispatcher("/admin/account-modal");
		accountModalDispatcher.include(request, response);

		out.append("                <h3 class=\"fs-custom text-dark fw-bold mb-3\">Thông tin nhận hàng</h3>");
		out.append("                <p class=\"fs-6\">Tên người nhận: "+order.getShippingAddress().getFullname()+"</p>");
		out.append("                <p class=\"fs-6\">SĐT người nhận: "+order.getShippingAddress().getPhoneNumber()+"</p>");
		out.append("                <p class=\"fs-6\">Địa chỉ: "+order.getShippingAddress().getAddress()+"</p>");
		out.append("              </div>");
		out.append("              <div class=\"col-md-4\">");
		out.append("                <h3 class=\"fs-custom text-dark fw-bold mb-3\">Chi tiết hoá đơn</h3>");
		out.append("                <p class=\"fs-6\">Mã hoá đơn: DH#"+order.getOrderId()+"</p>");
		out.append("                <p class=\"fs-6\">Ngày đặt hàng: "+order.getCreatedAt()+"</p>");
		out.append("                <p class=\"fs-6\">Tổng tiền: "+ProductUtils.formatNumber(order.getTotalPrice())+"</p>");
		out.append("                <p class=\"fs-6\">Trạng thái thanh toán: "+
				(order.getPaymentStatus().equals("PAID") ? "Đã thanh toán" : "Chưa thanh toán")+"</p>");
		out.append("              </div>");
		out.append("            </div>");
		out.append("            <div class=\"table-responsive\">");
		out.append("              <table class=\"table table-striped datatable table-hover\">");
		out.append("                <thead class=\"table-light\">");
		out.append("                  <tr>");
		out.append("                    <th scope=\"col\">Ảnh</th>");
		out.append("                    <th scope=\"col\">Tên sản phẩm</th>");
		out.append("                    <th scope=\"col\">Giá</th>");
		out.append("                    <th scope=\"col\">Số lượng</th>");
		out.append("                    <th scope=\"col\">Tổng giá</th>");
		out.append("                  </tr>");
		out.append("                </thead>");
		out.append("                <tbody>");

		for(OrderItem orderItem : order.getOrderItems()){
			out.append("                  <tr>");
			out.append("                    <td class=\"align-middle\"><img src=\""+orderItem.getProduct().getProductImageUrl()+"\" class=\"product-img\" alt=\"\"></td>");
			out.append("                    <td class=\"align-middle\">" +
					"<a href=\"../admin/product-detail-view?pId="+orderItem.getProduct().getProductId()+"\"");
			out.append("                        class=\"text-success text-decoration-none hover-primary\">");
			out.append(orderItem.getProduct().getProductName());
			out.append("                      </a></td>");
			out.append("                    <td class=\"align-middle\">"+ ProductUtils.formatNumber(orderItem.getOrderPrice())+"đ</td>");
			out.append("                    <td class=\"align-middle\">"+orderItem.getOrderQuantity()+"</td>");
			out.append("                    <td class=\"align-middle\">"+
					ProductUtils.formatNumber((orderItem.getOrderPrice() * orderItem.getOrderQuantity()))+"đ</td>");
			out.append("                  </tr>");
		}


		out.append("                </tbody>");
		out.append("              </table>");
		out.append("            </div>");
		out.append("");
		out.append("");
		out.append("            <div class=\"d-flex flex-column flex-md-row justify-content-between\">");
		out.append("              <!-- Thông tin thanh toán -->");
		out.append("              <div class=\"w-100 w-md-50 pe-md-3\">");
		out.append("                <div class=\"card h-100 border-0 shadow-sm mb-3\">");
		out.append("                  <div class=\"card-body\">");
		out.append("                    <h5 class=\"card-title text-dark fw-bold mb-3\">Thông tin thanh toán</h5>");
		out.append("                    <ul class=\"list-unstyled mb-0\">");
		out.append("                      <li class=\"mb-2 d-flex justify-content-between\">");
		String paymentMethod = "";
		switch (order.getPaymentMethod()){
			case "BANK_TRANSFER":
				paymentMethod = "Chuyển khoản ngân hàng";
				break;
			case "CASH_ON_DELIVERY":
				paymentMethod = "Thanh toán khi nhận hàng (COD)";
				break;
			case "CREDIT_CARD":
				paymentMethod = "Thẻ tín dụng";
				break;
		}
		out.append("                        <span>Hình thức:</span>");
		out.append("                        <span class=\"fw-semibold text-primary\">"+paymentMethod+"</span>");
		out.append("                      </li>");
		out.append("                      <li class=\"mb-2 d-flex justify-content-between\">");
		out.append("                        <span>Tổng tiền hàng:</span>");
		out.append("                        <span class=\"fw-semibold\">"+ProductUtils.formatNumber(order.getTotalPrice())+"₫</span>");
		out.append("                      </li>");
		out.append("                      <li class=\"mb-2 d-flex justify-content-between\">");
		out.append("                        <span>Phí vận chuyển:</span>");
		out.append("                        <span class=\"fw-semibold\">30,000₫</span>");
		out.append("                      </li>");
		out.append("                      <li class=\"border-top pt-2 mt-2 d-flex justify-content-between\">");
		out.append("                        <span class=\"fw-bold text-dark\">Tổng cộng:</span>");
		out.append("                        <span class=\"fw-bold text-success fs-5\">"+ProductUtils.formatNumber((order.getTotalPrice() + 300000))+"₫</span>");
		out.append("                      </li>");
		out.append("                    </ul>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("              </div>");
		out.append("");
		out.append("              <!-- Ghi chú -->");
		out.append("              <div class=\"w-100 w-md-50\">");
		out.append("                <div class=\"card h-100 border-0 shadow-sm\">");
		out.append("                  <div class=\"card-body\">");
		out.append("                    <h5 class=\"card-title text-dark fw-bold mb-3\">Ghi chú</h5>");
		out.append("                    <textarea class=\"form-control mb-3\" rows=\"4\" disabled ")
				.append(order.getOrderNote() == null ? "placeholder=\"Ghi chú của khách hàng\">" : ">")
				.append(order.getOrderNote() == null ? "" : order.getOrderNote())
				.append("</textarea>");

		out.append("                  </div>");
		out.append("                </div>");
		out.append("              </div>");
		out.append("            </div>");
		out.append("");
		out.append("          </div>");
		if(order.getOrderStatus().equals("PENDING")){
			out.append("          <a href=\"../admin/order-status-update?orderId="+order.getOrderId()+"\" class=\"btn btn-success float-end mb-5 py-2 mx-4\">Xác nhận đơn</a>");
		}
		out.append("        </div>");
		out.append("      </div>");
		out.append("    </section>");
		out.append("  </main>");
		out.append("  <script src=\"../admin/js/jquery.js\"></script>");
		out.append("  <script src=\"https://code.jquery.com/jquery-3.6.0.min.js\"></script>");
		out.append("  <script src=\"../admin/js/account.js\"></script>");
		out.append("");
		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
		footerDispatcher.include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
