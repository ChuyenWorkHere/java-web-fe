package servlet.admin.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
		
		out.append("<main id=\"main\" class=\"main\">");
		out.append("    <div class=\"pagetitle\">");
		out.append("      <nav class=\"d-flex justify-content-between align-items-center flex-wrap\">");
		out.append("        <ol class=\"breadcrumb fs-6\">");
		out.append("          <li class=\"breadcrumb-item\"><a href=\"index.html\">Trang chủ</a></li>");
		out.append("          <li class=\"breadcrumb-item active\">Chi tiết đơn hàng</li>");
		out.append("        </ol>");
		out.append("        <a href=\"/Furniture/admin/orders-view\">");
		out.append("          <button type=\"button\" class=\"btn btn-success btn-sm\">Trở về đơn hàng</button>");
		out.append("        </a>");
		out.append("      </nav>");
		out.append("    </div>");
		
		out.append("    <section class=\"section dashboard\">");
		out.append("      <div class=\"row mb-5\">");
		out.append("        <div class=\"col-12\">");
		out.append("          <div class=\"card p-4 rounded-4\">");
		out.append("            <div class=\"d-flex justify-content-between align-items-center mb-4\">");
		out.append("                <div>");
		out.append("                    <h2 class=\"h5 fw-bold\">Order ID: <span class=\"text-dark fw-bold\">#FC001</span> ");
		out.append("                        <!-- <span class=\"badge bg-warning text-dark\">Pending</span> -->");
		out.append("                    </h2>");
		out.append("                </div>");
		out.append("                <div class=\"d-flex align-items-center\">");
		out.append("                    <select class=\"form-select me-2\" style=\"width: auto;\">");
		out.append("                      <option value=\"active\">Thành công</option>");
		out.append("                      <option value=\"inactive\">Chờ phê duyệt</option>");
		out.append("                      <option value=\"inactive\">Đang gửi</option>");
		out.append("                      <option value=\"inactive\">Đã huỷ</option>");
		out.append("                    </select>");
		out.append("                </div>");
		out.append("            </div>");
		out.append("            <div class=\"row mb-4\">");
		out.append("                <div class=\"col-md-4\">");
		out.append("                    <h3 class=\"fs-custom text-dark fw-bold\">Chi tiết khách hàng</h3>");
		out.append("                    <p class=\"fs-6\">Đặng Ánh Nhật</p>");
		out.append("                    <p class=\"fs-6\">nhat@gmail.com</p>");
		out.append("                    <p class=\"fs-6\">086431691</p>");
		out.append("                    <a href=\"#\" class=\"text-success text-decoration-none small\">View Profile</a>");
		out.append("                </div>");
		out.append("                <div class=\"col-md-4\">");
		out.append("                    <h3 class=\"fs-custom text-dark fw-bold\">Địa chỉ nhận hàng</h3>");
		out.append("                    <p class=\"fs-6\">số 5 Nguyên Xá</p>");
		out.append("                    <p class=\"fs-6\">Bắc Từ Liêm, Hà Nội, Việt Nam</p>");
		out.append("                    <p class=\"fs-6\">Sđt: 0863164715</p>");
		out.append("                </div>");
		out.append("                <div class=\"col-md-4\">");
		out.append("                    <h3 class=\"fs-custom text-dark fw-bold\">Chi tiết hoá đơn</h3>");
		out.append("                    <p class=\"fs-6\">Mã hoá đơn: FC001</p>");
		out.append("                    <p class=\"fs-6\">Ngày đặt hàng: 17/3/2025</p>");
		out.append("                    <p class=\"fs-6\">Tổng số tiền: 850000đ</p>");
		out.append("                </div>");
		out.append("            </div>");
		out.append("            <div class=\"table-responsive\">");
		out.append("              <table class=\"table\">");
		out.append("                <thead class=\"table-light\">");
		out.append("                    <tr>");
		out.append("                        <th scope=\"col\">Ảnh</th>");
		out.append("                        <th scope=\"col\">Tên sản phẩm</th>");
		out.append("                        <th scope=\"col\">Giá</th>");
		out.append("                        <th scope=\"col\">Số lượng</th>");
		out.append("                        <th scope=\"col\">Tổng giá</th>");
		out.append("                    </tr>");
		out.append("                </thead>");
		out.append("                <tbody>");
		out.append("                  <tr>");
		out.append("                    <td class=\"align-middle\"><img src=\"assets/img/pro8.jpg\" class=\"product-img\" alt=\"\"></td>");
		out.append("                    <td class=\"align-middle\"><a href=\"single-product\" class=\"text-dark text-decoration-none hover-primary\">");
		out.append("                      Unity Pugh");
		out.append("                    </a></td>");
		out.append("                    <td class=\"align-middle\">250000đ</td> ");
		out.append("                    <td class=\"align-middle\">1</td>");
		out.append("                    <td class=\"align-middle\">250000đ</td>               ");
		out.append("                  </tr>");
		out.append("                  <tr>");
		out.append("                    <td class=\"align-middle\"><img src=\"assets/img/pro8.jpg\" class=\"product-img\" alt=\"\"></td>");
		out.append("                    <td class=\"align-middle\"><a href=\"single-product\" class=\"text-dark text-decoration-none hover-primary\">");
		out.append("                      Unity Pugh");
		out.append("                    </a></td>");
		out.append("                    <td class=\"align-middle\">250000đ</td> ");
		out.append("                    <td class=\"align-middle\">1</td>");
		out.append("                    <td class=\"align-middle\">250000đ</td>               ");
		out.append("                  </tr>");
		out.append("                  <tr>");
		out.append("                    <td class=\"align-middle\"><img src=\"assets/img/pro8.jpg\" class=\"product-img\" alt=\"\"></td>");
		out.append("                    <td class=\"align-middle\"><a href=\"single-product\" class=\"text-dark text-decoration-none hover-primary\">");
		out.append("                      Unity Pugh");
		out.append("                    </a></td>");
		out.append("                    <td class=\"align-middle\">250000đ</td> ");
		out.append("                    <td class=\"align-middle\">1</td>");
		out.append("                    <td class=\"align-middle\">250000đ</td>               ");
		out.append("                  </tr>");
		out.append("                </tbody>");
		out.append("              </table>");
		out.append("            </div>");
		out.append("            ");
		out.append("            <div class=\"d-flex justify-content-end mb-4\">");
		out.append("                <div class=\"w-50\">");
		out.append("                    <div class=\"d-flex justify-content-between border-bottom py-2\">");
		out.append("                        <span>Tổng tiền:</span>");
		out.append("                        <span>750000đ</span>");
		out.append("                    </div>");
		out.append("                    <div class=\"d-flex justify-content-between border-bottom py-2\">");
		out.append("                        <span>Phí vận chuyển:</span>");
		out.append("                        <span>30000đ</span>");
		out.append("                    </div>");
		out.append("                    <div class=\"d-flex justify-content-between py-2 fw-bold\">");
		out.append("                        <span>Tổng cộng:</span>");
		out.append("                        <span>780000đ</span>");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </div>            ");
		out.append("            <div class=\"d-flex flex-column flex-md-row justify-content-between\">");
		out.append("              <div class=\"w-100 w-md-50 pe-md-3\">");
		out.append("                  <h3 class=\"fs-custom text-dark fw-bold\">Thông tin thanh toán</h3>");
		out.append("                  <p class=\"fs-6\">Thanh toán khi nhận hàng</p>");
		out.append("              </div>");
		out.append("              <div class=\"w-100 w-md-50\">");
		out.append("                  <h3 class=\"fs-custom text-dark fw-bold\">Ghi chú</h3>");
		out.append("                  <textarea class=\"form-control mb-2\" rows=\"4\" placeholder=\"Viết ghi chú cho đơn hàng\"></textarea>");
		out.append("                  <button class=\"btn btn-success\">Lưu ghi chú</button>");
		out.append("              </div>");
		out.append("            </div>    ");
		out.append("          </div>");
		out.append("        </div>");
		out.append("      </div>");
		out.append("    </section>");
		out.append("  </main>");
		
		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
		footerDispatcher.include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
