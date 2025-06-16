package servlet.admin.view;

import servlet.dao.OrderDAO;
import servlet.dao.impl.OrderDAOImpl;
import servlet.models.Order;
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

@WebServlet("/admin/trash-view")
public class TrashView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TrashView() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		request.setAttribute("view", "trash");
		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
		headerDispatcher.include(request, response);

		String pageNo = request.getParameter("pageNo");
		int currentPage = pageNo != null ? Integer.parseInt(pageNo) : 1;

		OrderDAO orderDAO = new OrderDAOImpl();
		List<Order> deletedOrders = orderDAO.getDeletedOrders(currentPage, 12);
		int pageNumbers = (int) Math.ceil((double) orderDAO.countDeletedOrders() / 12);

		out.append("<main id=\"main\" class=\"main\">");

		out.append("    <div class=\"pagetitle d-flex justify-content-between align-items-center\">");
		out.append("      <nav>");
		out.append("        <ol class=\"breadcrumb\">");
		out.append("          <li class=\"breadcrumb-item fs-6\"><a href=\"../admin/home-view\">Trang chủ</a></li>");
		out.append("          <li class=\"breadcrumb-item fs-6\">Thùng rác</li>");
		out.append("        </ol>");
		out.append("      </nav>");
		out.append("      <a class=\"btn btn-secondary px-1\" href=\"../admin/orders-view\">Trở về đơn hàng</a>");
		out.append("    </div><!-- End Page Title -->");


		out.append("    <section class=\"section\">");
		out.append("      <div class=\"row\">");
		out.append("        <div class=\"col-lg-12\">");

		out.append("          <div class=\"card\">");
		out.append("            <div class=\"card-body\">");
		out.append("              <div class=\"table-responsive\">");
		out.append("                <table class=\"table table-striped datatable table-hover\">");
		out.append("                  <thead>");
		out.append("                    <tr>");
		out.append("                      <th><input type=\"checkbox\" id=\"selectAll\"></th>");
		out.append("                      <th>Mã ĐH</th>");
		out.append("                      <th>Khách hàng</th>");
		out.append("                      <th>Thời gian</th>");
		out.append("                      <th>Tổng tiền</th>");
		out.append("                      <th>Chức năng</th>");
		out.append("                    </tr>");
		out.append("                  </thead>");
		out.append("                  <tbody>");

		for (Order order : deletedOrders) {
			out.append("                    <tr>");
			out.append("                      <td><input type=\"checkbox\" class=\"orderCheckbox\" data-order-id=\"" + order.getOrderId() + "\"></td>");
			out.append("                      <td>DH#" + order.getOrderId() + "</td>");
			out.append("                      <td>" + order.getUser ().getFullname() + "</td>");
			out.append("                      <td>" + ProductUtils.formatDate(order.getCreatedAt()) + "</td>");
			out.append("                      <td>" + ProductUtils.formatNumber(order.getTotalPrice()) + "đ</td>");
			out.append("                      <td>");
			out.append("                        <button class=\"btn btn-success restoreBtn\" data-order-id=\"" + order.getOrderId() + "\">Khôi phục</button>");
			out.append("                        <button class=\"btn btn-danger deleteBtn softdeleteIcon\" data-order-id=\"" + order.getOrderId() + "\">Xóa</button>");
			out.append("                      </td>");
			out.append("                    </tr>");
		}

		out.append("                  </tbody>");
		out.append("                </table>");
		out.append("              </div>");

		out.append("              <div class=\"d-flex justify-content-between align-items-center mt-3\">");
		out.append("                <button class=\"btn btn-danger\" id=\"deleteSelected\">Xóa đã chọn</button>");
		out.append("                <button class=\"btn btn-success\" id=\"restoreSelected\">Khôi phục đã chọn</button>");
		out.append("              </div>");

		out.append("              <nav aria-label=\"Page navigation example\" class=\"mt-4\">");
		out.append("                <ul class=\"pagination\">");

		// Pagination logic
		if (currentPage > 1) {
			out.append("    <li class=\"page-item\">");
			out.append("      <a class=\"page-link\" href=\"" + getPageUrl(currentPage - 1) + "\" aria-label=\"Previous\">");
			out.append("        <span aria-hidden=\"true\">&laquo;</span>");
			out.append("      </a>");
			out.append("    </li>");
		}

		for (int i = 1; i <= pageNumbers; i++) {
			out.append(createPageItem(i, currentPage));
		}

		if (currentPage < pageNumbers) {
			out.append("    <li class=\"page-item\">");
			out.append("      <a class=\"page-link\" href=\"" + getPageUrl(currentPage + 1) + "\" aria-label=\"Next\">");
			out.append("        <span aria-hidden=\"true\">&raquo;</span>");
			out.append("      </a>");
			out.append("    </li>");
		}

		out.append("                </ul>");
		out.append("              </nav>");

		out.append("            </div>");
		out.append("          </div>");
		out.append("        </div>");
		out.append("      </div>");
		out.append("    </section>");
		out.append("  </main><!-- End #main -->");

		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
		footerDispatcher.include(request, response);
	}


	private String createPageItem(int page, int currentPage) {
		StringBuilder item = new StringBuilder();
		item.append("    <li class=\"page-item " + (page == currentPage ? "active" : "") + "\">");
		item.append("      <a class=\"page-link\" href=\"" + getPageUrl(page) + "\">" + page + "</a>");
		item.append("    </li>");
		return item.toString();
	}

	private String getPageUrl(int page) {
		return "../admin/trash-view?pageNo=" + page;
	}
}
