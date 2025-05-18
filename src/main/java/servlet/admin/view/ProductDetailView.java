package servlet.admin.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/admin/product-detail-view")
public class ProductDetailView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductDetailView() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");		 
		
		PrintWriter out = response.getWriter();
		request.setAttribute("view", "product");
		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
	    headerDispatcher.include(request, response);

	    out.append("<main id=\"main\" class=\"main\">");
	    out.append("");
	    out.append("    <div class=\"pagetitle d-flex justify-content-between\">");
	    out.append("      <h1>Chi Tiết</h1>");
	    out.append("      <nav class=\"d-flex align-items-center\">");
	    out.append("        <ol class=\"breadcrumb  mb-0\">");
	    out.append("          <li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-fill\"></i></a></li>");
	    out.append("          <li class=\"breadcrumb-item\">Sản Phẩm</li>");
	    out.append("          <li class=\"breadcrumb-item active\">Chi Tiết</li>");
	    out.append("        </ol>");
	    out.append("      </nav>");
	    out.append("    </div><!-- End Page Title -->");
	    out.append("");
	    out.append("    <section class=\"section profile\">");
	    out.append("      <div class=\"row\">");
	    out.append("        <div class=\"col-xl-6\">");
	    out.append("          <div class=\"card\">");
	    out.append("            <div class=\"card-body\">");
	    out.append("              <h5 class=\"card-title fw-bold\">THÔNG TIN CHUNG</h5>");
	    out.append("              <!-- Slides with controls -->");
	    out.append("              <div id=\"carouselExampleControls\" class=\"carousel carousel-fade slide\" data-bs-ride=\"carousel\">");
	    out.append("                <div class=\"carousel-inner\">");
	    out.append("                  <div class=\"carousel-item active\">");
	    out.append("                    <img src=\"https://product.hstatic.net/200000065946/product/pro_combo_ban_an_6_ghe_noi_that_moho_combo___1__ad0df2a7591b4ef0b15817bbbcff3587_master.jpg\" class=\"d-block w-100\" alt=\"...\">");
	    out.append("                  </div>");
	    out.append("                  <div class=\"carousel-item\">");
	    out.append("                    <img src=\"https://product.hstatic.net/200000065946/product/pro_combo_ban_an_6_ghe_noi_that_moho_combo___3__33928d88619d4e1dab37bac5a44bfbed_master.jpg\" class=\"d-block w-100\" alt=\"...\">");
	    out.append("                  </div>");
	    out.append("                  <div class=\"carousel-item\">");
	    out.append("                    <img src=\"https://product.hstatic.net/200000065946/product/pro_combo_ban_an_6_ghe_noi_that_moho_combo___2__b0fb69e96f5c416b83c736312324712c_master.jpg\" class=\"d-block w-100\" alt=\"...\">");
	    out.append("                  </div>");
	    out.append("                </div>");
	    out.append("");
	    out.append("                <button class=\"carousel-control-prev\" type=\"button\" data-bs-target=\"#carouselExampleControls\"");
	    out.append("                  data-bs-slide=\"prev\">");
	    out.append("                  <span class=\"carousel-control-prev-icon bg-dark\" aria-hidden=\"true\"></span>");
	    out.append("                  <span class=\"visually-hidden\">Previous</span>");
	    out.append("                </button>");
	    out.append("                <button class=\"carousel-control-next\" type=\"button\" data-bs-target=\"#carouselExampleControls\"");
	    out.append("                  data-bs-slide=\"next\">");
	    out.append("                  <span class=\"carousel-control-next-icon bg-dark\" aria-hidden=\"true\"></span>");
	    out.append("                  <span class=\"visually-hidden\">Next</span>");
	    out.append("                </button>");
	    out.append("");
	    out.append("              </div><!-- End Slides with controls -->");
	    out.append("");
	    out.append("              <div class=\"flex-grow-1\">");
	    out.append("                <p class=\"text-uppercase fw-semibold text-secondary small my-2\" style=\"letter-spacing: 0.1em;\">");
	    out.append("                  NỘI THẤT VĂN PHÒNG");
	    out.append("                </p>");
	    out.append("                <h3 class=\"fs-2 fw-semibold mb-4\">");
	    out.append("                  Bộ Bàn Ăn Scania (Màu Tự Nhiên, Mặt Vân Đá, 140)");
	    out.append("                </h3>");
	    out.append("                <div class=\"d-flex align-items-center gap-4 mb-4\">");
	    out.append("                  <span class=\"fs-3 fw-normal text-danger\">");
	    out.append("                    $119.00");
	    out.append("                  </span>");
	    out.append("                  <span");
	    out.append("                    class=\"fs-4 fw-normal text-secondary text-opacity-50 text-decoration-line-through user-select-none\">");
	    out.append("                    $246.00");
	    out.append("                  </span>");
	    out.append("                </div>");
	    out.append("                <hr class=\"custom-hr\" />");
	    out.append("                <div class=\"mb-4\">");
	    out.append("                  <p class=\"text-uppercase fw-semibold text-secondary small mb-3\" style=\"letter-spacing: 0.1em;\">");
	    out.append("                    MÀU SẮC");
	    out.append("                  </p>");
	    out.append("                  <div class=\"d-flex gap-3\">");
	    out.append("                    <span class=\"color-circle color-blue\" title=\"Blue\">");
	    out.append("                    </span>");
	    out.append("                    <span class=\"color-circle color-green\" title=\"Green\">");
	    out.append("                    </span>");
	    out.append("                    <span class=\"color-circle color-orange\" title=\"Orange\">");
	    out.append("                    </span>");
	    out.append("                    <span class=\"color-circle color-darkblue\" title=\"Dark Blue\">");
	    out.append("                    </span>");
	    out.append("                    <span class=\"color-circle color-pink\" title=\"Pink\">");
	    out.append("                    </span>");
	    out.append("                    <span class=\"color-circle color-cyan\" title=\"Cyan\">");
	    out.append("                    </span>");
	    out.append("                  </div>");
	    out.append("                </div>");
	    out.append("                <hr class=\"custom-hr\" />");
	    out.append("                <div class=\"mb-4 d-flex align-items-center gap-4\">");
	    out.append("                  <p class=\"text-uppercase fw-semibold text-secondary small mb-0\" style=\"letter-spacing: 0.1em;\">");
	    out.append("                    KÍCH CỠ");
	    out.append("                  </p>");
	    out.append("                  <div class=\"d-flex gap-3 fs-6 fw-normal\">");
	    out.append("                    <span class=\"size-option\">");
	    out.append("                      XXL");
	    out.append("                    </span>");
	    out.append("                    <span class=\"size-selected\">");
	    out.append("                      XL");
	    out.append("                    </span>");
	    out.append("                    <span class=\"size-option\">");
	    out.append("                      LG");
	    out.append("                    </span>");
	    out.append("                    <span class=\"size-option\">");
	    out.append("                      MD");
	    out.append("                    </span>");
	    out.append("                    <span class=\"size-option\">");
	    out.append("                      SM");
	    out.append("                    </span>");
	    out.append("                  </div>");
	    out.append("                </div>");
	    out.append("                <hr class=\"description-separator\" />");
	    out.append("                <div class=\"d-flex flex-column gap-2 small fw-semibold text-black\" style=\"max-width: 320px;\">");
	    out.append("                  <div class=\"d-flex justify-content-between\">");
	    out.append("                    <span>");
	    out.append("                      Nhãn hiệu:");
	    out.append("                    </span>");
	    out.append("                    <span class=\"fw-normal\">");
	    out.append("                      MOHO");
	    out.append("                    </span>");
	    out.append("                  </div>");
	    out.append("                  <div class=\"d-flex justify-content-between\">");
	    out.append("                    <span>");
	    out.append("                      Product Code:");
	    out.append("                    </span>");
	    out.append("                    <span class=\"fw-normal\">");
	    out.append("                      d12");
	    out.append("                    </span>");
	    out.append("                  </div>");
	    out.append("                  <div class=\"d-flex justify-content-between\">");
	    out.append("                    <span>");
	    out.append("                      Số Lượt Xem:");
	    out.append("                    </span>");
	    out.append("                    <span class=\"fw-normal\">");
	    out.append("                      100");
	    out.append("                    </span>");
	    out.append("                  </div>");
	    out.append("                </div>");
	    out.append("              </div>");
	    out.append("            </div>");
	    out.append("          </div>");
	    out.append("        </div>");
	    out.append("");
	    out.append("        <div class=\"col-xl-6\">");
	    out.append("          <div class=\"card\">");
	    out.append("            <div class=\"card-body\">");
	    out.append("              <h5 class=\"card-title fw-bold\">MÔ TẢ CHI TIẾT</h5>");
	    out.append("              <div class=\"d-flex flex-column\">");
	    out.append("                <div class=\"specification\">");
	    out.append("                  <h5 class=\"fw-bold\">1. THÔNG SỐ KỸ THUẬT</h5>");
	    out.append("                  <p>Khung chân : Gỗ cao su tự nhiên");
	    out.append("");
	    out.append("                    Bàn ăn gia đình làm từ chất liệu gỗ cao su tự nhiên đảm bảo độ chắc chắn cao, chống công vênh, mối");
	    out.append("                    mọt. Màu gỗ bắt mắt, đẹp tạo nét hiện đại.");
	    out.append("                    <br>");
	    out.append("                    Mặt bàn : Gỗ công nghiệp phủ Melamine vân đá tự nhiên");
	    out.append("                    <br>");
	    out.append("                    Lớp phủ Melamine có thể tái tạo chân thật vân đá tự nhiên mang lại vẻ đẹp sang trọng và hiện đại</p>");
	    out.append("                </div>");
	    out.append("                <div class=\"material\">");
	    out.append("                  <h5 class=\"fw-bold\">2. CHI TIẾT VẬT LIỆU</h5>");
	    out.append("                  <p>Khung chân : Gỗ cao su tự nhiên");
	    out.append("");
	    out.append("                    Bàn ăn gia đình làm từ chất liệu gỗ cao su tự nhiên đảm bảo độ chắc chắn cao, chống công vênh, mối");
	    out.append("                    mọt. Màu gỗ bắt mắt, đẹp tạo nét hiện đại.");
	    out.append("                    <br>");
	    out.append("                    Mặt bàn : Gỗ công nghiệp phủ Melamine vân đá tự nhiên");
	    out.append("                    <br>");
	    out.append("                    Lớp phủ Melamine có thể tái tạo chân thật vân đá tự nhiên mang lại vẻ đẹp sang trọng và hiện đại</p>");
	    out.append("                </div>");
	    out.append("                <div class=\"material\">");
	    out.append("                  <h5 class=\"fw-bold\">3. CHI TIẾT VẬT LIỆU</h5>");
	    out.append("                  <p>Khung chân : Gỗ cao su tự nhiên");
	    out.append("");
	    out.append("                    Bàn ăn gia đình làm từ chất liệu gỗ cao su tự nhiên đảm bảo độ chắc chắn cao, chống công vênh, mối");
	    out.append("                    mọt. Màu gỗ bắt mắt, đẹp tạo nét hiện đại.");
	    out.append("                    <br>");
	    out.append("                    Mặt bàn : Gỗ công nghiệp phủ Melamine vân đá tự nhiên");
	    out.append("                    <br>");
	    out.append("                    Lớp phủ Melamine có thể tái tạo chân thật vân đá tự nhiên mang lại vẻ đẹp sang trọng và hiện đại</p>");
	    out.append("                </div>");
	    out.append("                <div class=\"material\">");
	    out.append("                  <h5 class=\"fw-bold\">4. CHI TIẾT VẬT LIỆU</h5>");
	    out.append("                  <p>Khung chân : Gỗ cao su tự nhiên");
	    out.append("");
	    out.append("                    Bàn ăn gia đình làm từ chất liệu gỗ cao su tự nhiên đảm bảo độ chắc chắn cao, chống công vênh, mối");
	    out.append("                    mọt. Màu gỗ bắt mắt, đẹp tạo nét hiện đại.");
	    out.append("                    <br>");
	    out.append("                    Mặt bàn : Gỗ công nghiệp phủ Melamine vân đá tự nhiên");
	    out.append("                    <br>");
	    out.append("                    Lớp phủ Melamine có thể tái tạo chân thật vân đá tự nhiên mang lại vẻ đẹp sang trọng và hiện đại</p>");
	    out.append("                </div>");
	    out.append("              </div>");
	    out.append("            </div>");
	    out.append("          </div>");
	    out.append("        </div>");
	    out.append("      </div>");
	    out.append("    </section>");
	    out.append("");
	    out.append("  </main><!-- End #main -->");

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
