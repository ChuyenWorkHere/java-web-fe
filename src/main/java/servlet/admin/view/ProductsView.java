package servlet.admin.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/products-view")
public class ProductsView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductsView() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		request.setAttribute("view", "list");
		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
		headerDispatcher.include(request, response);

		out.append("<main id=\"main\" class=\"main\">");
		out.append("    <div class=\"pagetitle\">");
		out.append("      <nav class=\"d-flex justify-content-between align-items-center flex-wrap\">");
		out.append("        <ol class=\"breadcrumb fs-6\">");
		out.append("          <li class=\"breadcrumb-item\"><a href=\"index.html\">Home</a></li>");
		out.append("          <li class=\"breadcrumb-item active\">Sản phẩm</li>");
		out.append("        </ol>");
		out.append("        <a href=\"add-product.html\">");
		out.append("          <button type=\"button\" class=\"btn btn-success btn-sm\">Thêm sản phẩm</button>");
		out.append("        </a>");
		out.append("      </nav>");
		out.append("    </div>");
		
		out.append("    <section class=\"section dashboard\">");
		out.append("      <div class=\"row\">");
		out.append("        <div class=\"col-12\">");
		out.append("          <div class=\"card shadow-sm pt-3 pe-3 ps-3 rounded-4\">");
		out.append("            <div class=\"row d-flex justify-content-between g-2 mb-2\">");
		out.append("              <div class=\"col-12 col-md-6 col-lg-4\">");
		out.append("                <input type=\"text\" class=\"form-control rounded-2\" placeholder=\"Tìm sản phẩm...\">");
		out.append("              </div>");
		out.append("              <div class=\"col-12 col-md-4 col-lg-2\">");
		out.append("                <select class=\"form-select rounded-2\">");
		out.append("                  <option value=\"\">Tất cả trạng thái</option>");
		out.append("                  <option value=\"active\">Kích hoạt</option>");
		out.append("                  <option value=\"inactive\">Tạm dừng</option>");
		out.append("                </select>");
		out.append("              </div>");
		out.append("            </div>");
		
		out.append("            <div class=\"mt-4\">");
		out.append("              <div class=\"row product g-4\">");
		out.append("                <div class=\"col-xl-3\">");
		out.append("                  <div class=\"product__item\">");
		out.append("                    <div class=\"product__item-img\">");
		out.append("                      <img src=\"../admin/img/pro_nau_noi_that_moho_tu_quan_ao_1m2_5_2ea03038e88c4fa5bdd65d40a2787408_large.webp\" alt=\"\">");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-name\">");
		out.append("                      Tủ Quần Áo Gỗ Có Gương MOHO GRENAA 2 Nhiều Kích Thước");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-price d-flex align-items-center\">");
		out.append("                      <div class=\"product__item-price-new\">4500000đ</div>");
		out.append("                      <div class=\"product__item-price_old ms-2\">5000000 VNĐ</div>");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-li\">");
		out.append("                      <span>Chất liệu:</span>");
		out.append("                      Gỗ công nghiệp chuẩn MDF");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-li\">");
		out.append("                      <span>Nhãn hiệu:</span>");
		out.append("                      MoHo");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-li\">");
		out.append("                      <span>Danh mục:</span>");
		out.append("                      Bàn");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-li\">");
		out.append("                      <span>Kích thước:</span>");
		out.append("                      H75 x W180 x D90 cm");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-color d-flex align-items-center\">");
		out.append("                      <span>Màu sắc:</span>");
		out.append("                      <button class=\"color-img ms-3 color-img-1\"></button>");
		out.append("                      <button class=\"color-img ms-2 color-img-2\"></button>");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-button d-flex justify-content-between\">");
		out.append("                      <a href=\"#\">Xem chi tiết</a>");
		out.append("                      <a href=\"#\" >Xóa</a>");
		out.append("                    </div>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("                <div class=\"col-xl-3\">");
		out.append("                  <div class=\"product__item\">");
		out.append("                    <div class=\"product__item-img\">");
		out.append("                      <img src=\"../admin/img/pro_nau_noi_that_moho_tu_quan_ao_1m2_5_2ea03038e88c4fa5bdd65d40a2787408_large.webp\" alt=\"\">");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-name\">");
		out.append("                      Tủ Quần Áo Gỗ Có Gương MOHO GRENAA 2 Nhiều Kích Thước");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-price d-flex align-items-center\">");
		out.append("                      <div class=\"product__item-price-new\">4500000đ</div>");
		out.append("                      <div class=\"product__item-price_old ms-2\">5000000 VNĐ</div>");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-li\">");
		out.append("                      <span>Chất liệu:</span>");
		out.append("                      Gỗ công nghiệp chuẩn MDF");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-li\">");
		out.append("                      <span>Nhãn hiệu:</span>");
		out.append("                      MoHo");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-li\">");
		out.append("                      <span>Danh mục:</span>");
		out.append("                      Bàn");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-li\">");
		out.append("                      <span>Kích thước:</span>");
		out.append("                      H75 x W180 x D90 cm");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-color d-flex align-items-center\">");
		out.append("                      <span>Màu sắc:</span>");
		out.append("                      <button class=\"color-img ms-3 color-img-1\"></button>");
		out.append("                      <button class=\"color-img ms-2 color-img-2\"></button>");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-button d-flex justify-content-between\">");
		out.append("                      <a href=\"#\">Xem chi tiết</a>");
		out.append("                      <a href=\"#\" >Xóa</a>");
		out.append("                    </div>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("                <div class=\"col-xl-3\">");
		out.append("                  <div class=\"product__item\">");
		out.append("                    <div class=\"product__item-img\">");
		out.append("                      <img src=\"../admin/img/pro_nau_noi_that_moho_tu_quan_ao_1m2_5_2ea03038e88c4fa5bdd65d40a2787408_large.webp\" alt=\"\">");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-name\">");
		out.append("                      Tủ Quần Áo Gỗ Có Gương MOHO GRENAA 2 Nhiều Kích Thước");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-price d-flex align-items-center\">");
		out.append("                      <div class=\"product__item-price-new\">4500000đ</div>");
		out.append("                      <div class=\"product__item-price_old ms-2\">5000000 VNĐ</div>");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-li\">");
		out.append("                      <span>Chất liệu:</span>");
		out.append("                      Gỗ công nghiệp chuẩn MDF");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-li\">");
		out.append("                      <span>Nhãn hiệu:</span>");
		out.append("                      MoHo");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-li\">");
		out.append("                      <span>Danh mục:</span>");
		out.append("                      Bàn");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-li\">");
		out.append("                      <span>Kích thước:</span>");
		out.append("                      H75 x W180 x D90 cm");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-color d-flex align-items-center\">");
		out.append("                      <span>Màu sắc:</span>");
		out.append("                      <button class=\"color-img ms-3 color-img-1\"></button>");
		out.append("                      <button class=\"color-img ms-2 color-img-2\"></button>");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-button d-flex justify-content-between\">");
		out.append("                      <a href=\"#\">Xem chi tiết</a>");
		out.append("                      <a href=\"#\" >Xóa</a>");
		out.append("                    </div>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("                <div class=\"col-xl-3\">");
		out.append("                  <div class=\"product__item\">");
		out.append("                    <div class=\"product__item-img\">");
		out.append("                      <img src=\"../admin/img/pro_nau_noi_that_moho_tu_quan_ao_1m2_5_2ea03038e88c4fa5bdd65d40a2787408_large.webp\" alt=\"\">");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-name\">");
		out.append("                      Tủ Quần Áo Gỗ Có Gương MOHO GRENAA 2 Nhiều Kích Thước");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-price d-flex align-items-center\">");
		out.append("                      <div class=\"product__item-price-new\">4500000đ</div>");
		out.append("                      <div class=\"product__item-price_old ms-2\">5000000 VNĐ</div>");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-li\">");
		out.append("                      <span>Chất liệu:</span>");
		out.append("                      Gỗ công nghiệp chuẩn MDF");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-li\">");
		out.append("                      <span>Nhãn hiệu:</span>");
		out.append("                      MoHo");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-li\">");
		out.append("                      <span>Danh mục:</span>");
		out.append("                      Bàn");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-li\">");
		out.append("                      <span>Kích thước:</span>");
		out.append("                      H75 x W180 x D90 cm");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-color d-flex align-items-center\">");
		out.append("                      <span>Màu sắc:</span>");
		out.append("                      <button class=\"color-img ms-3 color-img-1\"></button>");
		out.append("                      <button class=\"color-img ms-2 color-img-2\"></button>");
		out.append("                    </div>");
		out.append("                    <div class=\"product__item-button d-flex justify-content-between\">");
		out.append("                      <a href=\"#\">Xem chi tiết</a>");
		out.append("                      <a href=\"#\" >Xóa</a>");
		out.append("                    </div>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("              </div>");
		out.append("            </div>");
		
		out.append("            <nav aria-label=\"Page navigation\">");
		out.append("              <ul class=\"pagination pagination-sm justify-content-end mt-2\">");
		out.append("                <li class=\"page-item mx-1\">");
		out.append("                  <a class=\"page-link rounded-1\" href=\"#\" aria-label=\"Previous\">");
		out.append("                    <span aria-hidden=\"true\">&laquo;</span>");
		out.append("                  </a>");
		out.append("                </li>");
		out.append("                <li class=\"page-item mx-1\"><a class=\"page-link active rounded-1\" href=\"#\">1</a></li>");
		out.append("                <li class=\"page-item mx-1\"><a class=\"page-link rounded-1\" href=\"#\">2</a></li>");
		out.append("                <li class=\"page-item mx-1\"><a class=\"page-link rounded-1\" href=\"#\">3</a></li>");
		out.append("                <li class=\"page-item mx-1\">");
		out.append("                  <a class=\"page-link rounded-1\" href=\"#\" aria-label=\"Next\">");
		out.append("                    <span aria-hidden=\"true\">&raquo;</span>");
		out.append("                  </a>");
		out.append("                </li>");
		out.append("              </ul>");
		out.append("            </nav>");
		out.append("          </div>");
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
