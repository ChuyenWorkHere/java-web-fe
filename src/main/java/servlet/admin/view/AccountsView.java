package servlet.admin.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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

	    out.append(" <main id=\"main\" class=\"main\">");
	    out.append("    <div class=\"pagetitle d-flex justify-content-between\">");
	    out.append("      <h1>Tài khoản</h1>");
	    out.append("      <nav class=\"d-flex align-items-center\">");
	    out.append("        <ol class=\"breadcrumb  mb-0\">");
	    out.append("          <li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-fill\"></i></a></li>");
	    out.append("          <li class=\"breadcrumb-item active\">Tài khoản</li>");
	    out.append("        </ol>");
	    out.append("      </nav>");
	    out.append("    </div><!-- End Page Title -->");
	    out.append("  ");
	    out.append("    <section class=\"section dashboard\">");
	    out.append("      <div class=\"row product\">");
	    out.append("        <div class=\"col-9 mt-4\">");
	    out.append("          <div class=\"row\" style=\"background-color: #f5f8fe;\">");
	    out.append("            <div class=\"col-xl-3 py-0\">");
	    out.append("              <div class=\"card\">");
	    out.append("                <div class=\"card-body\">");
	    out.append("                  <div class=\"d-flex flex-column align-items-center text-center\">");
	    out.append("                    <img src=\"https://bootdey.com/img/Content/avatar/avatar7.png\" alt=\"Admin\" class=\"rounded-circle\" width=\"60\">");
	    out.append("                    <div class=\"mt-3\">");
	    out.append("                      <h5>John Doe</h5>");
	    out.append("                      <p class=\"text-secondary mb-1 small\">Full Stack Developer</p>");
	    out.append("                      <p class=\"text-muted font-size-sm small\">Bay Area, San Francisco, CA</p>");
	    out.append("                      <button class=\"btn btn-primary\"><i class=\"bi bi-eye\"></i></button>");
	    out.append("                      <button class=\"btn btn-danger\"><i class=\"bi bi-trash\"></i></button>");
	    out.append("                    </div>");
	    out.append("                  </div>");
	    out.append("                </div>");
	    out.append("              </div>");
	    out.append("            </div>");
	    out.append("            <div class=\"col-xl-3 py-0\">");
	    out.append("              <div class=\"card\">");
	    out.append("                <div class=\"card-body\">");
	    out.append("                  <div class=\"d-flex flex-column align-items-center text-center\">");
	    out.append("                    <img src=\"https://bootdey.com/img/Content/avatar/avatar7.png\" alt=\"Admin\" class=\"rounded-circle\" width=\"60\">");
	    out.append("                    <div class=\"mt-3\">");
	    out.append("                      <h5>John Doe</h5>");
	    out.append("                      <p class=\"text-secondary mb-1 small\">Full Stack Developer</p>");
	    out.append("                      <p class=\"text-muted font-size-sm small\">Bay Area, San Francisco, CA</p>");
	    out.append("                      <button class=\"btn btn-primary\"><i class=\"bi bi-eye\"></i></button>");
	    out.append("                      <button class=\"btn btn-danger\"><i class=\"bi bi-trash\"></i></button>");
	    out.append("                    </div>");
	    out.append("                  </div>");
	    out.append("                </div>");
	    out.append("              </div>");
	    out.append("            </div>");
	    out.append("            <div class=\"col-xl-3 py-0\">");
	    out.append("              <div class=\"card\">");
	    out.append("                <div class=\"card-body\">");
	    out.append("                  <div class=\"d-flex flex-column align-items-center text-center\">");
	    out.append("                    <img src=\"https://bootdey.com/img/Content/avatar/avatar7.png\" alt=\"Admin\" class=\"rounded-circle\" width=\"60\">");
	    out.append("                    <div class=\"mt-3\">");
	    out.append("                      <h5>John Doe</h5>");
	    out.append("                      <p class=\"text-secondary mb-1 small\">Full Stack Developer</p>");
	    out.append("                      <p class=\"text-muted font-size-sm small\">Bay Area, San Francisco, CA</p>");
	    out.append("                      <button class=\"btn btn-primary\"><i class=\"bi bi-eye\"></i></button>");
	    out.append("                      <button class=\"btn btn-danger\"><i class=\"bi bi-trash\"></i></button>");
	    out.append("                    </div>");
	    out.append("                  </div>");
	    out.append("                </div>");
	    out.append("              </div>");
	    out.append("            </div>");
	    out.append("            <div class=\"col-xl-3 py-0\">");
	    out.append("              <div class=\"card\">");
	    out.append("                <div class=\"card-body\">");
	    out.append("                  <div class=\"d-flex flex-column align-items-center text-center\">");
	    out.append("                    <img src=\"https://bootdey.com/img/Content/avatar/avatar7.png\" alt=\"Admin\" class=\"rounded-circle\" width=\"60\">");
	    out.append("                    <div class=\"mt-3\">");
	    out.append("                      <h5>John Doe</h5>");
	    out.append("                      <p class=\"text-secondary mb-1 small\">Full Stack Developer</p>");
	    out.append("                      <p class=\"text-muted font-size-sm small\">Bay Area, San Francisco, CA</p>");
	    out.append("                      <button class=\"btn btn-primary\"><i class=\"bi bi-eye\"></i></button>");
	    out.append("                      <button class=\"btn btn-danger\"><i class=\"bi bi-trash\"></i></button>");
	    out.append("                    </div>");
	    out.append("                  </div>");
	    out.append("                </div>");
	    out.append("              </div>");
	    out.append("            </div>");
	    out.append("            <div class=\"col-xl-3 py-0\">");
	    out.append("              <div class=\"card\">");
	    out.append("                <div class=\"card-body\">");
	    out.append("                  <div class=\"d-flex flex-column align-items-center text-center\">");
	    out.append("                    <img src=\"https://bootdey.com/img/Content/avatar/avatar7.png\" alt=\"Admin\" class=\"rounded-circle\" width=\"60\">");
	    out.append("                    <div class=\"mt-3\">");
	    out.append("                      <h5>John Doe</h5>");
	    out.append("                      <p class=\"text-secondary mb-1 small\">Full Stack Developer</p>");
	    out.append("                      <p class=\"text-muted font-size-sm small\">Bay Area, San Francisco, CA</p>");
	    out.append("                      <button class=\"btn btn-primary\"><i class=\"bi bi-eye\"></i></button>");
	    out.append("                      <button class=\"btn btn-danger\"><i class=\"bi bi-trash\"></i></button>");
	    out.append("                    </div>");
	    out.append("                  </div>");
	    out.append("                </div>");
	    out.append("              </div>");
	    out.append("            </div>");
	    out.append("            <div class=\"col-xl-3 py-0\">");
	    out.append("              <div class=\"card\">");
	    out.append("                <div class=\"card-body\">");
	    out.append("                  <div class=\"d-flex flex-column align-items-center text-center\">");
	    out.append("                    <img src=\"https://bootdey.com/img/Content/avatar/avatar7.png\" alt=\"Admin\" class=\"rounded-circle\" width=\"60\">");
	    out.append("                    <div class=\"mt-3\">");
	    out.append("                      <h5>John Doe</h5>");
	    out.append("                      <p class=\"text-secondary mb-1 small\">Full Stack Developer</p>");
	    out.append("                      <p class=\"text-muted font-size-sm small\">Bay Area, San Francisco, CA</p>");
	    out.append("                      <button class=\"btn btn-primary\"><i class=\"bi bi-eye\"></i></button>");
	    out.append("                      <button class=\"btn btn-danger\"><i class=\"bi bi-trash\"></i></button>");
	    out.append("                    </div>");
	    out.append("                  </div>");
	    out.append("                </div>");
	    out.append("              </div>");
	    out.append("            </div>");
	    out.append("          </div>");
	    out.append("        </div>");
	    out.append("        <div class=\"col-3 mt-4\">");
	    out.append("          <div class=\"row card py-2\" style=\"background-color: #f5f8fe; position: sticky; top: 100px;\">");
	    out.append("            <div class=\"container py-4 d-flex flex-column align-items-start gap-3\" style=\"max-width: 320px;\">");
	    out.append("              <div class=\"d-flex align-items-center gap-2 filter-label\">");
	    out.append("                <i class=\"bi bi-funnel-fill\"></i>");
	    out.append("                <span>BỘ LỌC</span>");
	    out.append("              </div>");
	    out.append("              <input type=\"text\" placeholder=\"Tên người dùng...\" class=\"w-100 py-2 px-3 form-control\" style=\"outline: none;\">");
	    out.append("              <select class=\"form-select\" aria-label=\"Danh mục\">");
	    out.append("                <option selected>DANH MỤC</option>");
	    out.append("                <option value=\"1\">Option 1</option>");
	    out.append("                <option value=\"2\">Option 2</option>");
	    out.append("              </select>");
	    out.append("              <select class=\"form-select\" aria-label=\"Giá sản phẩm\">");
	    out.append("                <option selected>GIÁ SẢN PHẨM</option>");
	    out.append("                <option value=\"1\">Option 1</option>");
	    out.append("                <option value=\"2\">Option 2</option>");
	    out.append("              </select>");
	    out.append("              <select class=\"form-select\" aria-label=\"Kích thước\">");
	    out.append("                <option selected>KÍCH THƯỚC</option>");
	    out.append("                <option value=\"1\">Option 1</option>");
	    out.append("                <option value=\"2\">Option 2</option>");
	    out.append("              </select>");
	    out.append("              <button type=\"button\" class=\"btn-search\">TÌM KIẾM</button>");
	    out.append("            </div>");
	    out.append("          </div>");
	    out.append("        </div>");
	    out.append("    </section>");
	    out.append("  </main>");

		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
		footerDispatcher.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
