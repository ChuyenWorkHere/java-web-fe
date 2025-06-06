package servlet.admin.view;

import servlet.dao.OrderDAO;
import servlet.dao.impl.OrderDAOImpl;
import servlet.models.Order;
import servlet.utils.ProductUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/admin/orders-view")
public class OrdersView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public OrdersView() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");		 
		
		PrintWriter out = response.getWriter();
		request.setAttribute("view", "order");
		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
	    headerDispatcher.include(request, response);

		OrderDAO orderDAO = new OrderDAOImpl();
		List<Order> orders = orderDAO.getAllOrders(1, 12);
		List<Map<String, Integer>> orderStatusCount = orderDAO.orderStatusCount();
		int pageNumbers = (int) Math.ceil((double)orderDAO.countAllOrders() / 12);

		out.append("\"<main id=\"main\" class=\"main\">");
		
		out.append("    <div class=\"pagetitle\">");
		out.append("      <nav>");
		out.append("        <ol class=\"breadcrumb\">");
		out.append("          <li class=\"breadcrumb-item fs-6\"><a href=\"../admin/home-view\">Trang chủ</a></li>");
		out.append("          <li class=\"breadcrumb-item fs-6\">Đơn hàng</li>");
		out.append("        </ol>");
		out.append("      </nav>");
		out.append("    </div><!-- End Page Title -->");
		
		out.append("    <section class=\"section\">");
		out.append("      <div class=\"row\">");
		out.append("        <div class=\"col-lg-12\">");
		
		out.append("          <div class=\"card mb-3\">");
		out.append("            <div class=\"card-body\">");
		out.append("              <div class=\"d-flex align-items-center mt-2 mb-3 filter-label\">");
		out.append("                <i class=\"bi bi-funnel-fill me-2\"></i>");
		out.append("                <span>BỘ LỌC</span>");
		out.append("              </div>");
		
		out.append("              <form id=\"orderFilterForm\" class=\"container\">");
		out.append("                <div class=\"row g-3 align-items-end\">");
		out.append("                  <!-- Khoảng giá -->");
		out.append("                  <div class=\"col-md-2\">");
		out.append("                    <label for=\"priceRange\" class=\"form-label\">Khoảng giá</label>");
		out.append("                    <select class=\"form-select\" id=\"priceRange\">");
		out.append("                      <option value=\"\">Tất cả</option>");
		out.append("                      <option value=\"low\">Dưới 500k</option>");
		out.append("                      <option value=\"mid\">500k - 1 triệu</option>");
		out.append("                      <option value=\"high\">Trên 1 triệu</option>");
		out.append("                    </select>");
		out.append("                  </div>");
		
		out.append("                  <!-- Trạng thái -->");
		out.append("                  <div class=\"col-md-2\">");
		out.append("                    <label for=\"orderStatus\" class=\"form-label\">Trạng thái</label>");
		out.append("                    <select class=\"form-select\" id=\"orderStatus\">");
		out.append("                      <option value=\"\">Tất cả</option>");
		out.append("                      <option value=\"pending\">Chờ xác nhận</option>");
		out.append("                      <option value=\"shipping\">Đang giao</option>");
		out.append("                      <option value=\"shipped\">Đã giao</option>");
		out.append("                      <option value=\"cancelled\">Đã hủy</option>");
		out.append("                    </select>");
		out.append("                  </div>");
		
		out.append("                  <!-- Phương thức thanh toán -->");
		out.append("                  <div class=\"col-md-2\">");
		out.append("                    <label for=\"paymentMethod\" class=\"form-label\">Thanh toán</label>");
		out.append("                    <select class=\"form-select\" id=\"paymentMethod\">");
		out.append("                      <option value=\"\">Tất cả</option>");
		out.append("                      <option value=\"cod\">COD</option>");
		out.append("                      <option value=\"bank\">Chuyển khoản</option>");
		out.append("                    </select>");
		out.append("                  </div>");
		
		out.append("                  <!-- Sắp xếp -->");
		out.append("                  <div class=\"col-md-2\">");
		out.append("                    <label for=\"orderSort\" class=\"form-label\">Đơn đặt hàng</label>");
		out.append("                    <select class=\"form-select\" id=\"orderSort\">");
		out.append("                      <option value=\"\">Tất cả</option>");
		out.append("                      <option value=\"newest\">Mới Nhất</option>");
		out.append("                      <option value=\"oldest\">Cũ nhất</option>");
		out.append("                    </select>");
		out.append("                  </div>");
		
		out.append("                  <!-- Nút lọc -->");
		out.append("                  <div class=\"col-md-1\">");
		out.append("                    <button type=\"button\" class=\"btn btn-primary mt-3 w-100\" onclick=\"applyOrderFilter()\">Lọc</button>");
		out.append("                  </div>");
		
		out.append("                </div>");
		out.append("              </form>");
		
		out.append("              <!-- Tổng quan số lượng đơn hàng -->");
		out.append("              <div class=\"my-4\">");
		out.append("                <h5 class=\"my-3 fw-bold\">Tổng quan đơn hàng</h5>");
		out.append("                <div class=\"row text-center g-4\">");

		for(Map<String, Integer> statusCount : orderStatusCount){
			String status = statusCount.keySet().iterator().next();
			int total = statusCount.get(status);

			String label = "";
			String textColor = "";
			String bgColor = "";
			String idAttr = "";

			switch (status){
				case "DELIVERED":
					label = "Thành công";
					bgColor = "bg-success bg-opacity-25";
					idAttr = " id=\"successful-orders\"";
					break;
				case "PENDING":
					label = "Đang xử lý";
					bgColor = "bg-warning bg-opacity-25";
					idAttr = " id=\"processing-orders\"";
					break;
				case "SHIPPED":
					label = "Đang giao";
					bgColor = "bg-primary bg-opacity-25";
					idAttr = "";
					break;
				case "CANCELLED":
					label = "Đã huỷ";
					bgColor = "bg-danger bg-opacity-25";
					idAttr = " id=\"cancelled-orders\"";
					break;
			}

			out.append("  <div class=\"col-md-3 col-6\">");
			out.append("    <div class=\"border rounded p-3 " + bgColor + "\">");
			out.append("      <h6 class=\"fs-5 fw-bold mt-2\">" + label + "</h6>");
			out.append("      <p class=\"fs-4 fw-bold text-dark\"" + idAttr + ">" + total + "</p>");
			out.append("    </div>");
			out.append("  </div>");
		}

		out.append("                </div>");
		out.append("              </div>");
		out.append("            </div>");
		
		out.append("          </div>");
		
		out.append("          <div class=\"card\">");
		out.append("            <div class=\"card-body\">");
		
		out.append("              <div class=\"table-responsive\">");
		
		out.append("                <!-- Table with stripped rows -->");
		out.append("                <table class=\"table table-striped datatable table-hover\">");
		out.append("                  <thead>");
		out.append("                    <tr>");
		out.append("                      <th>");
		out.append("                        Mã đơn hàng");
		out.append("                      </th>");
		out.append("                      <th>Khách hàng</th>");
		out.append("                      <th data-type=\"date\" data-format=\"YYYY/DD/MM\">Thời gian</th>");
		out.append("                      <th>Tổng tiền</th>");
		out.append("                      <th>Trạng thái</th>");
		out.append("                      <th>Chức năng</th>");
		out.append("                    </tr>");
		out.append("                  </thead>");
		out.append("                  <tbody>");

		for(Order order : orders){
			out.append("                    <tr>");
			out.append("                      <td class=\"align-middle\">DH#"+order.getOrderId()+"</td>");
			out.append("                      <td class=\"align-middle\">"+order.getUser().getFullname()+"</td>");
			out.append("                      <td class=\"align-middle\">"+order.getCreatedAt()+"</td>");
			out.append("                      <td class=\"align-middle\">"+ ProductUtils.formatNumber(order.getTotalPrice())+"đ</td>");
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
			out.append("                      <td class=\"align-middle\">");
			out.append("                        <span class=\"p-2 badge ");
			out.append(className +"\">"+(order.getOrderStatus().equals("PENDING")
					? "<a class=\"text-white\" href=\"../admin/single-order-view?orderId="+order.getOrderId()+"\">"+orderStatus+"</a>" : orderStatus) + "</span>");

			out.append("                      </td>");
			out.append("                      <td class=\"align-middle\">");
			out.append("                        <a href=\"../admin/single-order-view?orderId="+order.getOrderId()+"\"><i class=\"bi bi-eye fs-6 text-secondary ms-2 pointer\"></i></a>");

			out.append("                        <i class=\"deleteIcon bi bi-trash fs-6 text-danger ms-2 pointer\"></i>");
			out.append("                      </td>");
			out.append("                    </tr>");
		}
		
		out.append("                  </tbody>");
		out.append("                </table>");
		out.append("                <!-- End Table with stripped rows -->");
		
		out.append("              </div>");

		out.append("              <div class=\"d-flex justify-content-between align-items-center mt-3\">");

		out.append("                <!-- Nút Xuất dữ liệu -->");
		out.append("                <button type=\"button\" class=\"btn btn-primary\">");
		out.append("                  <i class=\"bi bi-download me-2\"></i> Xuất dữ liệu");
		out.append("                </button>");

		out.append("                <!-- Pagination with icons -->");
		out.append("<nav aria-label=\"Page navigation example\" class=\"mt-4 d-flex justify-content-center order-3 order-md-3\">");
		out.append("  <ul class=\"pagination\">");

		String pageNo = request.getParameter("pageNo");
		int currentPage = pageNo != null ? Integer.parseInt(pageNo) : 1;

		String statusLink = "";
		String status = "";
		String keyword = "";
// Previous button
		if (currentPage > 1) {
			out.append("    <li class=\"page-item\">");
			out.append("      <a class=\"page-link\" href=\"" + getPageUrl(currentPage - 1, statusLink, keyword, status) + "\" aria-label=\"Previous\">");
			out.append("        <span aria-hidden=\"true\">&laquo;</span>");
			out.append("      </a>");
			out.append("    </li>");
		}

// Always show page 1
		out.append(createPageItem(1, currentPage, statusLink, keyword, status));

// Add ... after page 1 if needed
		if (currentPage > 3) {
			out.append("    <li class=\"page-item disabled\"><a class=\"page-link\">...</a></li>");
		}

// Show current page if it's not 1 or last
		if (currentPage != 1 && currentPage != pageNumbers) {
			out.append(createPageItem(currentPage, currentPage, statusLink, keyword, status));
		}

// Add ... before last page if needed
		if (currentPage < pageNumbers - 2) {
			out.append("    <li class=\"page-item disabled\"><a class=\"page-link\">...</a></li>");
		}

// Always show last page if more than 1 page
		if (pageNumbers > 1) {
			out.append(createPageItem(pageNumbers, currentPage, statusLink, keyword, status));
		}

// Next button
		if (currentPage < pageNumbers) {
			out.append("    <li class=\"page-item\">");
			out.append("      <a class=\"page-link\" href=\"" + getPageUrl(currentPage + 1, statusLink, keyword, status) + "\" aria-label=\"Next\">");
			out.append("        <span aria-hidden=\"true\">&raquo;</span>");
			out.append("      </a>");
			out.append("    </li>");
		}

		out.append("  </ul>");
		out.append("</nav>");

		out.append("              </div>");


		out.append("            </div>");
		out.append("          </div>");
		out.append("        </div>");
		out.append("      </div>");
		out.append("    </section>");
		out.append("    <!-- thông báo -->");
		out.append("    <div class=\"modal fade\" id=\"confirmDeleteModal\" tabindex=\"-1\" aria-labelledby=\"confirmDeleteLabel\"");
		out.append("      aria-hidden=\"true\">");
		out.append("      <div class=\"modal-dialog modal-sm animate__animated animate__fadeInDown\">");
		out.append("        <div class=\"modal-content shadow\">");
		out.append("          <div class=\"modal-header\">");
		out.append("            <h5 class=\"modal-title\" id=\"confirmDeleteLabel\">Xác nhận xoá</h5>");
		out.append("            <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Đóng\"></button>");
		out.append("          </div>");
		out.append("          <div class=\"modal-body\">");
		out.append("            Bạn có chắc chắn muốn xoá đơn hàng này không?");
		out.append("          </div>");
		out.append("          <div class=\"modal-footer\">");
		out.append("            <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Huỷ</button>");
		out.append("            <button type=\"button\" class=\"btn btn-danger\" id=\"confirmDeleteBtn\">Xoá</button>");
		out.append("          </div>");
		out.append("        </div>");
		out.append("      </div>");
		out.append("    </div>");
		out.append("    <!-- end thông báo -->");
		out.append("  </main><!-- End #main -->");
		out.append("  <script src=\"../admin/js/jquery.js\"></script>");
		out.append("  <script src=\"https://code.jquery.com/jquery-3.6.0.min.js\"></script>");
		

		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
		footerDispatcher.include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private String createPageItem(int page, int currentPage, String statusLink, String keyword, String status) {
		StringBuilder item = new StringBuilder();
		item.append("    <li class=\"page-item " + (page == currentPage ? "active" : "") + "\">");
		item.append("      <a class=\"page-link\" href=\"" + getPageUrl(page, statusLink, keyword, status) + "\">" + page + "</a>");
		item.append("    </li>");
		return item.toString();
	}

	private String getPageUrl(int page, String statusLink, String keyword, String status) {
		if (statusLink == null) {
			return "../admin/orders-view?pageNo=" + page;
		}
//		if (statusLink.equals("/admin/search-user")) {
//			return "../admin/search-user?pageNo=" + page + "&keyword=" + keyword;
//		}
		return "../admin/orders-view?pageNo=" + page + "&status=" + status;
	}

}
