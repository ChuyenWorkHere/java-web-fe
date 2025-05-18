package servlet.admin.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/admin/single-order-process-view")
public class SingleOrderProcessView extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public SingleOrderProcessView() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
		headerDispatcher.include(request, response);



		out.append("<main id=\"main\" class=\"main\">");
		out.append("    <div class=\"pagetitle\">");
		out.append("      <nav class=\"d-flex justify-content-between align-items-center flex-wrap\">");
		out.append("        <ol class=\"breadcrumb fs-6\">");
		out.append("          <li class=\"breadcrumb-item\"><a href=\"index.html\">Trang chủ</a></li>");
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
		out.append("                <h2 class=\"h5 fw-bold\">Order ID: <span class=\"text-dark fw-bold\">#FC001</span>");
		out.append("                  <!-- <span class=\"badge bg-warning text-dark\">Pending</span> -->");
		out.append("                </h2>");
		out.append("              </div>");
		out.append("              <div class=\"d-flex align-items-center\">");
		out.append("                <button class=\"btn btn-secondary\">Đang chờ xác nhận</button>");
		out.append("              </div>");
		out.append("            </div>");
		out.append("            <div class=\"row mb-4 mx-0 py-4 rounded\">");
		out.append("              <div class=\"col-md-4\">");
		out.append("                <h3 class=\"fs-custom text-dark fw-bold mb-3\">Chi tiết khách hàng</h3>");
		out.append("                <p class=\"fs-6\">Đặng Ánh Nhật</p>");
		out.append("                <p class=\"fs-6\">nhat@gmail.com</p>");
		out.append("                <p class=\"fs-6\">086431691</p>");
		out.append("                <a href=\"#\" class=\"text-success text-decoration-none small\">Xem Profile</a>");
		out.append("              </div>");
		out.append("              <div class=\"col-md-4\">");
		out.append("                <h3 class=\"fs-custom text-dark fw-bold mb-3\">Địa chỉ nhận hàng</h3>");
		out.append("                <p class=\"fs-6\">số 5 Nguyên Xá</p>");
		out.append("                <p class=\"fs-6\">Bắc Từ Liêm, Hà Nội, Việt Nam</p>");
		out.append("              </div>");
		out.append("              <div class=\"col-md-4\">");
		out.append("                <h3 class=\"fs-custom text-dark fw-bold mb-3\">Chi tiết hoá đơn</h3>");
		out.append("                <p class=\"fs-6\">Mã hoá đơn: FC001</p>");
		out.append("                <p class=\"fs-6\">Thời gian: 12 P.M</p>");
		out.append("                <p class=\"fs-6\">Ngày đặt hàng: 17/3/2025</p>");
		out.append("                <p class=\"fs-6\">Tổng số tiền: 850000đ</p>");
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
		out.append("                  <tr>");
		out.append("                    <td class=\"align-middle\"><img src=\"../admin/img/pro8.jpg\" class=\"product-img\" alt=\"\"></td>");
		out.append("                    <td class=\"align-middle\"><a href=\"single-product\"");
		out.append("                        class=\"text-dark text-decoration-none hover-primary\">");
		out.append("                        Unity Pugh");
		out.append("                      </a></td>");
		out.append("                    <td class=\"align-middle\">250000đ</td>");
		out.append("                    <td class=\"align-middle\">1</td>");
		out.append("                    <td class=\"align-middle\">250000đ</td>");
		out.append("                  </tr>");
		out.append("                  <tr>");
		out.append("                    <td class=\"align-middle\"><img src=\"../admin/img/pro8.jpg\" class=\"product-img\" alt=\"\"></td>");
		out.append("                    <td class=\"align-middle\"><a href=\"single-product\"");
		out.append("                        class=\"text-dark text-decoration-none hover-primary\">");
		out.append("                        Unity Pugh");
		out.append("                      </a></td>");
		out.append("                    <td class=\"align-middle\">250000đ</td>");
		out.append("                    <td class=\"align-middle\">1</td>");
		out.append("                    <td class=\"align-middle\">250000đ</td>");
		out.append("                  </tr>");
		out.append("                  <tr>");
		out.append("                    <td class=\"align-middle\"><img src=\"../admin/img/pro8.jpg\" class=\"product-img\" alt=\"\"></td>");
		out.append("                    <td class=\"align-middle\"><a href=\"single-product\"");
		out.append("                        class=\"text-dark text-decoration-none hover-primary\">");
		out.append("                        Unity Pugh");
		out.append("                      </a></td>");
		out.append("                    <td class=\"align-middle\">250000đ</td>");
		out.append("                    <td class=\"align-middle\">1</td>");
		out.append("                    <td class=\"align-middle\">250000đ</td>");
		out.append("                  </tr>");
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
		out.append("                        <span>Hình thức:</span>");
		out.append("                        <span class=\"fw-semibold text-primary\">Thanh toán khi nhận hàng</span>");
		out.append("                      </li>");
		out.append("                      <li class=\"mb-2 d-flex justify-content-between\">");
		out.append("                        <span>Tổng tiền hàng:</span>");
		out.append("                        <span class=\"fw-semibold\">750,000₫</span>");
		out.append("                      </li>");
		out.append("                      <li class=\"mb-2 d-flex justify-content-between\">");
		out.append("                        <span>Phí vận chuyển:</span>");
		out.append("                        <span class=\"fw-semibold\">30,000₫</span>");
		out.append("                      </li>");
		out.append("                      <li class=\"border-top pt-2 mt-2 d-flex justify-content-between\">");
		out.append("                        <span class=\"fw-bold text-dark\">Tổng cộng:</span>");
		out.append("                        <span class=\"fw-bold text-success fs-5\">780,000₫</span>");
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
		out.append("                    <textarea class=\"form-control mb-3\" rows=\"4\" disabled placeholder=\"Ghi chú của khách hàng\"></textarea>");
		out.append("                  </div>");
		out.append("                </div>");
		out.append("              </div>");
		out.append("            </div>");
		out.append("");
		out.append("          </div>");
		out.append("          <a href=\"../admin/orders-view\" class=\"btn btn-success float-end mb-5 py-2 mx-4\">Xác nhận đơn</a>");
		out.append("        </div>");
		out.append("      </div>");
		out.append("    </section>");
		out.append("  </main>");
		out.append("");
		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
		footerDispatcher.include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
