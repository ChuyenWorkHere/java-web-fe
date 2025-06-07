package servlet.admin.view;

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

import servlet.dao.UserDAO;
import servlet.dao.impl.UserDAOImpl;
import servlet.models.User;
import servlet.utils.ProductUtils;


@WebServlet("/admin/accounts-view")
public class AccountsView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AccountsView() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");		 
		
		PrintWriter out = response.getWriter();
		request.setAttribute("view", "account");
		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
	    headerDispatcher.include(request, response);
	    
	    List<User> users = (List<User>) request.getAttribute("users");
	    String status = (String) request.getAttribute("status");
	    if(status == null)
	    	status = "all";

		//lấy keyword
		String keyword = (String) request.getAttribute("keyword");

		//lấy trạng thái url request
		String statusLink = (String) request.getAttribute("statusLink");

		//lấy thông tin trang được chọn
		int currentPage = 1;
		String pageNo = request.getParameter("pageNo");

		if (pageNo != null){
			currentPage = Integer.parseInt(pageNo);
		}

		//lấy tổng số trang
		Integer pages = (Integer) request.getAttribute("pageNumbers");

		int pageNumbers = 0;
		if (pages != null){
			pageNumbers = pages;
		}

		if (users == null) {
	        UserDAO userDAO = new UserDAOImpl();
	        users = userDAO.findAllPage(currentPage, 12);
			pageNumbers = (int) Math.ceil((double) userDAO.countAllUsers() / 12);
	    }

	    out.append("<main id=\"main\" class=\"main\">");
	    out.append("    <div class=\"pagetitle d-flex justify-content-between\">");
	    out.append("      <h1>Tài khoản</h1>");
	    out.append("      <nav class=\"d-flex align-items-center\">");
	    out.append("        <ol class=\"breadcrumb  mb-0\">");
	    out.append("          <li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-fill\"></i></a></li>");
	    out.append("          <li class=\"breadcrumb-item active\">Tài khoản</li>");
	    out.append("        </ol>");
	    out.append("      </nav>");
	    out.append("    </div><!-- End Page Title -->");
	    out.append("    <div id=\"toast-message\" class=\"toast-success\">Đã xoá thành công!</div>");
	    
	    out.append("    <section class=\"section dashboard\">");
	    out.append("      <div class=\"row product account \">");
	    out.append("        <div class=\"col-md-9 mt-4 order-2 order-md-1\">");
	    out.append("          <div class=\"row product inner-list-account\" style=\"background-color: #f5f8fe;\">");
	    for(User user : users) {
	    	out.append("            <div class=\"col-xl-3 col-md-6 py-0\">");
		    out.append("              <div class=\"card\">");
		    out.append("                <div class=\"card-body\">");
		    out.append("                  <div class=\"d-flex flex-column align-items-center text-center\">");
		    out.append("                    <img src=\"../admin/img/"+ ("Nam".equals(user.getGender()) ? "avtboy.png":"avtgirl.jpg")
		    		+"\" alt=\"Admin\" class=\"rounded-circle\"");
		    out.append("                      width=\"60\">");
		    out.append("                    <div class=\"mt-3\">");
		    out.append("                      <h5>"+ user.getFullname() +"</h5>");
		    out.append("                      <p class=\"mb-1 small\">Số lần truy cập: "+ user.getLoginCount() +"</p>");
		    
		    if(user.isActive()) {
		    	out.append("                      <p class=\"font-size-sm small\">Trạng thái: "+"<span class=\"status-text text-success\">Hoạt động </span>"+"</p>");
		    }else {
		    	out.append("                      <p class=\"font-size-sm small primary\">Trạng thái: "+"<span class=\"text-danger\">Tạm ngừng </span>"+"</p>");
		    }
		    out.append("<button class='btn btn-primary viewBtn me-2' ")
			    .append("data-bs-toggle='modal' data-bs-target='#largeModal' ")
			    .append("data-name='" + user.getFullname() + "' ")
			    .append("data-gender='" + user.getGender() + "' ")
			    .append("data-phone='" + user.getPhoneNumber() + "' ")
			    .append("data-email='" + user.getEmail() + "' ")
			    .append("data-address='" + user.getAddress() + "' ")
			    .append("data-created='" + ProductUtils.formatDate(user.getCreateDate())+ "' ")
			    .append("data-updated='" + ProductUtils.formatDate(user.getModifiedDate()) + "' ")
			    .append("data-status='" + user.isActive() + "'>")
			    .append("<i class='bi bi-eye'></i>")
			    .append("</button>");
		    if(user.isActive()) {
				out.append("<button class='btn btn-danger btn-delete' data-bs-toggle='modal' ");
				out.append("data-bs-target='#smallModal' data-id='" + user.getUserId() + "' ");
				out.append("data-name='" + user.getFullname() + "'>");
				out.append("<i class='bi bi-trash'></i></button>");

			}

		    out.append("                    </div>");
		    out.append("                  </div>");
		    out.append("                </div>");
		    out.append("              </div>");
		    out.append("            </div>");
	    }
	    
	    out.append("          </div>");
	    out.append("        </div>");
	    out.append("        <div class=\"col-md-3 mt-4 order-1 order-md-2\">");
	    out.append("          <div class=\"row card py-2\" style=\" position: sticky; top: 100px;\">");
	    out.append("            <div class=\"container py-4 d-flex flex-column align-items-start gap-3\" style=\"max-width: 320px;\">");
	    
	    out.append("<form method=\"get\" action=\"../admin/search-user\">");
	    out.append("  <div class=\"d-flex align-items-center col-12\">");
	    out.append("    <input type=\"text\" id=\"searchInput\" name=\"keyword\" placeholder=\"Tên người dùng...\" class=\"w-100 py-2 px-3 form-control custom-box-shadow\">");
	    out.append("    <button type=\"submit\" class=\"btn btn-link p-0\"><i class=\"bi bi-search icon-search pointer\"></i></button>");
	    out.append("  </div>");
	    out.append("</form>");
	    
	    out.append("<form method=\"get\" class=\"col-12\" action=\"../admin/filter\">");
	    
	    out.append("              <div class=\"d-flex align-items-center gap-2 mb-3 filter-label\">");
	    out.append("                <i class=\"bi bi-funnel-fill\"></i>");
	    out.append("                <span>BỘ LỌC</span>");
	    out.append("              </div>");
	    out.append("              <select class=\"form-select col-12 mb-2\" name=\"status\" aria-label=\"Danh mục\">");
	    out.append("                <option value=\"all\">Tất cả</option>");
	    out.append("                <option value=\"active\"" + (status.equals("active") ? " selected" : "") + ">Hoạt động</option>");
	    out.append("                <option value=\"inactive\"" + (status.equals("inactive") ? " selected" : "") + ">Tạm ngừng</option>");
	    out.append("              </select>");
	    
	    out.append("              <button type=\"submit\" class=\"btn-search\">LỌC</button>");
	    out.append("</form>");
	    out.append("            </div>");
	    out.append("          </div>");
	    out.append("        </div>");

		/*		navigation     */
		out.append("<nav aria-label=\"Page navigation example\" class=\"mt-4 d-flex justify-content-center order-3 order-md-3\">");
		out.append("  <ul class=\"pagination\">");

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


		out.append("    </section>");

		RequestDispatcher accountModalDispatcher = request.getRequestDispatcher("/admin/account-modal");
	    accountModalDispatcher.include(request, response);

	    out.append("    <!-- Start Small Modal-->");
	    out.append("    <div class=\"modal fade\" id=\"smallModal\" tabindex=\"-1\">");
	    out.append("      <div class=\"modal-dialog modal-sm\">");
	    out.append("        <div class=\"modal-content\">");
		out.append("  <div class=\"modal-header bg-danger text-white\">");
		out.append("    <h5 class=\"modal-title userFullname\"></h5>");
		out.append("    <button type=\"button\" class=\"btn-close btn-close-white\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		out.append("  </div>");
		out.append("          <div class=\"modal-body\">");
	    out.append("            Bạn có chắc chắn muốn xoá không?");
	    out.append("          </div>");
	    out.append("          <div class=\"modal-footer\">");
	    out.append("            <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Huỷ</button>");
	    out.append("            <button type=\"button\" class=\"btn btn-danger\" id=\"confirmDeleteBtn\" ><i class='bi bi-trash me-2'></i>Xoá</button>");
	    out.append("          </div>");
	    out.append("        </div>");
	    out.append("      </div>");
	    out.append("    </div><!-- End Small Modal-->");
	    out.append("  </main>");
		out.append("  <script src=\"../admin/js/account.js\"></script>");
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
			return "../admin/accounts-view?pageNo=" + page;
		}
		if (statusLink.equals("/admin/search-user")) {
			return "../admin/search-user?pageNo=" + page + "&keyword=" + keyword;
		}
		return "../admin/filter?pageNo=" + page + "&status=" + status;
	}

}
