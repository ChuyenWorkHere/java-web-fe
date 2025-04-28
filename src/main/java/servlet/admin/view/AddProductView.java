package servlet.admin.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/add-product-view")
public class AddProductView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddProductView() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
		headerDispatcher.include(request, response);

		out.append("<main id=\"main\" class=\"main\">");
		out.append("    <div class=\"pagetitle\">");
		out.append("      <nav class=\"d-flex justify-content-between align-items-center flex-wrap\">");
		out.append("        <ol class=\"breadcrumb fs-6\">");
		out.append("          <li class=\"breadcrumb-item\"><a href=\"/Furniture/admin/home-view\">Home</a></li>");
		out.append("          <li class=\"breadcrumb-item active\">Thêm sản phẩm</li>");
		out.append("        </ol>");
		out.append("      </nav>");
		out.append("    </div>");
		
		out.append("    <section class=\"section dashboard\">");
		out.append("      <div class=\"row\">");
		out.append("        <div class=\"col-lg-12\">");
		out.append("            <div class=\"card shadow-sm\">");
		out.append("                <div class=\"card-body\">");
		out.append("                  <h2 class=\"card-title\">Thông tin sản phẩm</h2>");
		out.append("                  <div class=\"row g-3\">");
		out.append("                    <div class=\"col-md-6\">");
		out.append("                        <label class=\"form-label\">Tên sản phẩm</label>");
		out.append(
				"                        <input type=\"text\" class=\"form-control\" placeholder=\"Nhập tên sản phẩm\">");
		out.append("                    </div>");
		out.append("                    <div class=\"col-md-6\">");
		out.append("                        <label class=\"form-label\">Danh mục</label>");
		out.append("                        <select class=\"form-select\">");
		out.append("                            <option>Danh mục 1</option>");
		out.append("                            <option>Danh mục 2</option>");
		out.append("                        </select>");
		out.append("                    </div>");
		out.append("                    <div class=\"col-md-6\">");
		out.append("                        <label class=\"form-label\">Số lượng</label>");
		out.append(
				"                        <input type=\"text\" class=\"form-control\" placeholder=\"Nhập số lượng\">");
		out.append("                    </div>");
		out.append("                    <div class=\"col-md-6\">");
		out.append("                      <label class=\"form-label\">Trạng thái</label>");
		out.append("                      <div class=\"d-flex align-items-center\">");
		out.append("                        <div class=\"form-check me-3\">");
		out.append(
				"                          <input class=\"form-check-input\" type=\"radio\" name=\"status\" id=\"activeStatus\" checked>");
		out.append(
				"                          <label class=\"form-check-label\" for=\"activeStatus\">Hoạt động</label>");
		out.append("                        </div>");
		out.append("                        <div class=\"form-check\">");
		out.append(
				"                          <input class=\"form-check-input\" type=\"radio\" name=\"status\" id=\"disabledStatus\">");
		out.append(
				"                          <label class=\"form-check-label\" for=\"disabledStatus\">Ngừng hoạt động</label>");
		out.append("                        </div>");
		out.append("                      </div>           ");
		out.append("                    </div>  ");
		out.append("                  </div>                                                             ");
		out.append("                  <h2 class=\"card-title\">Giá sản phẩm</h2>");
		out.append("                  <div class=\"row g-3\">");
		out.append("                    <div class=\"col-md-6\">");
		out.append("                      <label class=\"form-label\">Giá gốc</label>");
		out.append("                      <input type=\"text\" class=\"form-control\" placeholder=\"Nhập giá gốc\">");
		out.append("                    </div>");
		out.append("                    <div class=\"col-md-6\">");
		out.append("                      <label class=\"form-label\">Giá khuyến mãi</label>");
		out.append(
				"                      <input type=\"text\" class=\"form-control\" placeholder=\"Nhập giá khuyến mãi\">");
		out.append("                    </div>");
		out.append("                  </div>  ");
		out.append("                  <div class=\"row g-3\">");
		out.append("                    <div class=\"col-md-6\">");
		out.append("                      <h2 class=\"card-title\">Ảnh sản phẩm</h2>           ");
		out.append("                      <input type=\"file\" class=\"form-control\" accept=\"image/*\">");
		out.append("                    </div>");
		out.append("                    <div class=\"col-md-6\">");
		out.append("                      <h2 class=\"card-title\">Mô tả</h2>");
		out.append(
				"                      <textarea class=\"form-control\" rows=\"5\" placeholder=\"Nhập mô tả sản phẩm...\"></textarea>");
		out.append("                    </div>");
		out.append("                  </div>               ");
		out.append("                  <div class=\"text-end mt-4\">");
		out.append("                    <button class=\"btn btn-primary\">Thêm sản phẩm</button>");
		out.append("                  </div>");
		out.append("                  </div>");
		out.append("            </div>");
		out.append("        </div>");
		out.append("      </div>");
		out.append("    </section>");
		out.append("  </main>");

		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
		footerDispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
