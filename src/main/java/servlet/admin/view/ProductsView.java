package servlet.admin.view;

import servlet.dao.ProductDAO;
import servlet.dao.impl.ProductDAOImpl;
import servlet.models.Product;
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

@WebServlet("/admin/products-view")
public class ProductsView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;

	public ProductsView() {
		super();
		productDAO = new ProductDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		List<Product> productList = productDAO.findAll(100, 1, "ASC", "product_id");

		PrintWriter out = response.getWriter();
		request.setAttribute("view", "list");
		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
		headerDispatcher.include(request, response);

		out.append("<main id=\"main\" class=\"main\">");
		out.append("    <div class=\"pagetitle d-flex justify-content-between\">");
		out.append("      <h1>Sản phẩm</h1>");
		out.append("      <nav class=\"d-flex align-items-center\">");
		out.append("        <ol class=\"breadcrumb  mb-0\">");
		out.append("          <li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-fill\"></i></a></li>");
		out.append("          <li class=\"breadcrumb-item active\">Sản phẩm</li>");
		out.append("        </ol>");
		out.append("      </nav>");
		out.append("    </div><!-- End Page Title -->");
		out.append("");
		out.append("    <section class=\"section dashboard\">");
		out.append("");
		out.append("      <!-- Container để chứa alert -->");
		out.append("      <div id=\"alert-container\" class = \"z-2 position-absolute\"></div>");
		out.append("");
		out.append("      <!--Modal Delete Product-->");
		out.append("      <div class=\"modal fade\" id=\"modalDel\" tabindex=\"-1\">");
		out.append("        <div class=\"modal-dialog modal-dialog-centered\">");
		out.append("          <div class=\"modal-content\">");
		out.append("            <div class=\"modal-header\">");
		out.append("              <h5 class=\"modal-title\">Xác nhận xóa</h5>");
		out.append("              <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		out.append("            </div>");
		out.append("            <div class=\"modal-body text-center\">");
		out.append("              Xác nhận xóa sản phẩm Tủ Quần Áo Gỗ Có Gương MOHO GRENAA 2 Nhiều Kích Thước?");
		out.append("            </div>");
		out.append("            <div class=\"modal-footer\">");
		out.append("              <button  type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Đóng</button>");
		out.append("              <button  type=\"button\" class=\"btn btn-danger confirm\" data-bs-dismiss=\"modal\">Xóa</button>");
		out.append("            </div>");
		out.append("          </div>");
		out.append("        </div>");
		out.append("      </div>");
		out.append("      <!-- End Modal Delete Product-->");
		out.append("");
		out.append("      <div class=\"row product\">");
		out.append("        <div class=\"col-xl-9 order-2 order-xl-1 mt-4\">");
		out.append("          <div class=\"row\" style=\"background-color: #f5f8fe;\">");

		for (int i = 0; i < productList.size(); i++) {
			Product product = productList.get(i);
			String[] imgUrls = ProductUtils.urlArray(product.getProductImageUrl());
			String[] colorArray = ProductUtils.colorArray(product.getProductImageUrl());
			out.append("            <div class=\"col-xl-4 col-md-6 mb-3\">");
			out.append("              <div class=\"product__item\">");
			out.append("                <!-- Slides with controls -->");
			out.append("                <div id=\""+product.getProductId()+"\" class=\"carousel carousel-fade slide\" data-bs-ride=\"carousel\">");
			out.append("                  <div class=\"carousel-inner\">");

			for (int j = 0; j < imgUrls.length; j++) {
				out.append("                    <div style=\"max-width: fit-content; max-height: auto;\" class=\"carousel-item " + (j == 0 ? "active" : "")  +"\" data-color=\"\">");
				out.append("                      <img");
				out.append("                        src=\".."+imgUrls[j]+"\"");
				out.append("                        class=\"d-block w-100\" alt=\"...\">");
				out.append("                    </div>");
			}

			out.append("                  </div>");
			out.append("");
			out.append("                  <button class=\"carousel-control-prev\" type=\"button\" data-bs-target=\"#"+product.getProductId()+"\"");
			out.append("                    data-bs-slide=\"prev\">");
			out.append("                    <span class=\"carousel-control-prev-icon\" aria-hidden=\"true\"></span>");
			out.append("                    <span class=\"visually-hidden\">Previous</span>");
			out.append("                  </button>");
			out.append("                  <button class=\"carousel-control-next\" type=\"button\" data-bs-target=\"#"+product.getProductId()+"\"");
			out.append("                    data-bs-slide=\"next\">");
			out.append("                    <span class=\"carousel-control-next-icon\" aria-hidden=\"true\"></span>");
			out.append("                    <span class=\"visually-hidden\">Next</span>");
			out.append("                  </button>");
			out.append("");
			out.append("                </div><!-- End Slides with controls -->");
			out.append("                <div class=\"fw-bold product__item-name\">");
			out.append("                  "+product.getProductName()+"");
			out.append("                </div>");
			out.append("                <div class=\"product__item-price d-flex align-items-center\">");
			out.append("                  <div class=\"product__item-price-new my-2\">"+ProductUtils.formatNumber(product.getProductDiscountPrice())+"đ</div>");
			out.append("                  <div class=\"product__item-price_old ms-2\">"+ProductUtils.formatNumber(product.getProductPrice())+"đ</div>");
			out.append("                </div>");
			out.append("");
			out.append("                <div class=\"product__item-color row align-items-start\">");
			out.append("                  <div class=\"col-4 fw-bold pe-0\">Màu sắc:</div>");
			out.append("					<div class=\"row col-8\">");
			for (int j = 0; j < colorArray.length; j++) {
				out.append("                  <button style = \" background-color: " +colorArray[j]+";\" class=\"color-img d-block col-3 me-2 mt-1\" data-color=\""+colorArray[j]+"\"></button>");
			}
			out.append("                	</div>");
			out.append("                </div>");
			out.append("                <div class=\"product__item-button d-flex justify-content-center mt-3\">");
			out.append("                  <button onclick=\"window.location.href='../admin/product-detail-view?pId="+product.getProductId()+"'\" type=\"button\" class=\"btn bg-primary me-2\" data-bs-toggle=\"modal\" data-bs-target=\"\">");
			out.append("                    <i class=\"bi bi-eye text-white\"></i>");
			out.append("                  </button>");
			out.append("                  <button onclick=\"window.location.href='../admin/edit-product-view?pId="+product.getProductId()+"'\" type=\"button\" class=\"btn bg-warning me-2\" data-bs-toggle=\"modal\" data-bs-target=\"\">");
			out.append("                    <i class=\"bi bi-pencil text-white\"></i>");
			out.append("                  </button>");
			out.append("                  <button type=\"button\" class=\"btn bg-danger me-2\" data-bs-toggle=\"modal\" data-bs-target=\"#modalDel\">");
			out.append("                    <i class=\"bi bi-trash text-white\"></i>");
			out.append("                  </button>");
			out.append("                </div>");
			out.append("              </div>");
			out.append("            </div>");
		}

		out.append("          </div>");
		out.append("          <!-- Centered Pagination -->");
		out.append("              <nav aria-label=\"Page navigation example\">");
		out.append("                <ul class=\"pagination justify-content-center\">");
		out.append("                  <li class=\"page-item disabled\">");
		out.append("                    <a class=\"page-link\" href=\"#\" tabindex=\"-1\" aria-disabled=\"true\">Trước</a>");
		out.append("                  </li>");
		out.append("                  <li class=\"page-item\"><a class=\"page-link active\" href=\"#\">1</a></li>");
		out.append("                  <li class=\"page-item\"><a class=\"page-link\" href=\"#\">2</a></li>");
		out.append("                  <li class=\"page-item\"><a class=\"page-link\" href=\"#\">3</a></li>");
		out.append("                  <li class=\"page-item\">");
		out.append("                    <a class=\"page-link\" href=\"#\">Sau</a>");
		out.append("                  </li>");
		out.append("                </ul>");
		out.append("              </nav><!-- End Centered Pagination -->");
		out.append("        </div>");
		out.append("        <div class=\"col-xl-3 order-1 order-xl-2 mt-4\">");
		out.append("          <div class=\"row card py-2\" style=\"background-color: #f5f8fe; position: sticky; top: 100px;\">");
		out.append("            <div class=\"container py-4 d-flex flex-column align-items-start gap-3\" style=\"max-width: 320px;\">");
		out.append("              <div class=\"d-flex align-items-center gap-2 filter-label\">");
		out.append("                <i class=\"bi bi-funnel-fill\"></i>");
		out.append("                <span>BỘ LỌC</span>");
		out.append("              </div>");
		out.append("              <select class=\"form-select\" aria-label=\"Danh mục\">");
		out.append("                <option selected>SẢN PHẨM NỔI BẬT</option>");
		out.append("                <option value=\"1\">Option 1</option>");
		out.append("                <option value=\"2\">Option 2</option>");
		out.append("              </select>");
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
		out.append("              <div class=\"color-select\">");
		out.append("                <span>MÀU SẮC</span>");
		out.append("                <div class=\"color-container\">");
		out.append("                  <div class=\"color-box\" data-color=\"#1A1A2E\" style=\"background-color: #1A1A2E;\"");
		out.append("                    onclick=\"selectColor(this)\">");
		out.append("                    <span class=\"checkmark\">✔</span>");
		out.append("                  </div>");
		out.append("                  <div class=\"color-box\" data-color=\"#0A2E8F\" style=\"background-color: #0A2E8F;\"");
		out.append("                    onclick=\"selectColor(this)\">");
		out.append("                    <span class=\"checkmark\">✔</span>");
		out.append("                  </div>");
		out.append("                  <div class=\"color-box\" data-color=\"#6B48FF\" style=\"background-color: #6B48FF;\"");
		out.append("                    onclick=\"selectColor(this)\">");
		out.append("                    <span class=\"checkmark\">✔</span>");
		out.append("                  </div>");
		out.append("                  <div class=\"color-box\" data-color=\"#00C4B4\" style=\"background-color: #00C4B4;\"");
		out.append("                    onclick=\"selectColor(this)\">");
		out.append("                    <span class=\"checkmark\">✔</span>");
		out.append("                  </div>");
		out.append("                  <div class=\"color-box\" data-color=\"#00FF00\" style=\"background-color: #00FF00;\"");
		out.append("                    onclick=\"selectColor(this)\">");
		out.append("                    <span class=\"checkmark\">✔</span>");
		out.append("                  </div>");
		out.append("                  <div class=\"color-box\" data-color=\"#FF4040\" style=\"background-color: #FF4040;\"");
		out.append("                    onclick=\"selectColor(this)\">");
		out.append("                    <span class=\"checkmark\">✔</span>");
		out.append("                  </div>");
		out.append("                  <div class=\"color-box\" data-color=\"#FFA500\" style=\"background-color: #FFA500;\"");
		out.append("                    onclick=\"selectColor(this)\">");
		out.append("                    <span class=\"checkmark\">✔</span>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("              </div>");
		out.append("");
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
		out.append("  <script src=\"../admin/js/mainProduct.js\"></script>");
		out.append("  <script src=\"../admin/js/showAlert.js\"></script>");

		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
		footerDispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
