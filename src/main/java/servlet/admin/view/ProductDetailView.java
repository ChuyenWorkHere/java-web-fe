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


@WebServlet("/admin/product-detail-view")
public class ProductDetailView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDAO productDAO;

    public ProductDetailView() {
        super();
		productDAO = new ProductDAOImpl();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");		 

		int productId = Integer.parseInt(request.getParameter("pId"));
		Product product = productDAO.findById(productId);


		PrintWriter out = response.getWriter();
		request.setAttribute("view", "product");
		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
	    headerDispatcher.include(request, response);

		List<String> urlArray = List.of(ProductUtils.urlArray(product.getProductImageUrl()));
		List<String> colorArray = List.of(ProductUtils.colorArray(product.getProductImageUrl()));

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
	    out.append("        <div class=\"col-xl-6 border-end\">");
	    out.append("          <div class=\"card shadow-none\">");
	    out.append("            <div class=\"card-body\">");
	    out.append("              <h5 class=\"card-title fw-semibold\">THÔNG TIN CHUNG</h5>");
	    out.append("              <!-- Slides with controls -->");
	    out.append("              <div id=\""+product.getProductId()+"\" class=\"carousel carousel-fade slide\" data-bs-ride=\"carousel\">");
	    out.append("                <div class=\"carousel-inner\">");

		for (int j = 0; j < urlArray.size(); j++) {
			out.append("                    <div class=\"carousel-item " + (j == 0 ? "active" : "")  +"\" data-color=\"\">");
			out.append("                      <img");
			out.append("                        src=\".."+urlArray.get(j)+"\"");
			out.append("                        class=\"d-block w-100\" alt=\"...\">");
			out.append("                    </div>");
		}
	    out.append("");
	    out.append("                <button class=\"carousel-control-prev\" type=\"button\" data-bs-target=\"#"+product.getProductId()+"\"");
	    out.append("                  data-bs-slide=\"prev\">");
	    out.append("                  <span class=\"carousel-control-prev-icon bg-dark\" aria-hidden=\"true\"></span>");
	    out.append("                  <span class=\"visually-hidden\">Previous</span>");
	    out.append("                </button>");
	    out.append("                <button class=\"carousel-control-next\" type=\"button\" data-bs-target=\"#"+product.getProductId()+"\"");
	    out.append("                  data-bs-slide=\"next\">");
	    out.append("                  <span class=\"carousel-control-next-icon bg-dark\" aria-hidden=\"true\"></span>");
	    out.append("                  <span class=\"visually-hidden\">Next</span>");
	    out.append("                </button>");
	    out.append("");
	    out.append("              </div><!-- End Slides with controls -->");
	    out.append("");
	    out.append("              <div class=\"flex-grow-1\">");
	    out.append("                <p class=\"text-uppercase fw-semibold text-secondary small my-2\" style=\"letter-spacing: 0.1em;\">");
	    out.append("                  "+product.getCategory().getCategoryName()+"");
	    out.append("                </p>");
	    out.append("                <h3 class=\"fs-2 fw-semibold mb-4\">");
	    out.append("                  "+product.getProductName()+"");
	    out.append("                </h3>");
	    out.append("                <div class=\"d-flex align-items-center gap-4 mb-4\">");
	    out.append("                  <span class=\"fs-3 fw-normal text-danger\">");
	    out.append("                    "+ProductUtils.formatNumber(product.getProductDiscountPrice())+"đ");
	    out.append("                  </span>");
	    out.append("                  <span");
	    out.append("                    class=\"fs-4 fw-normal text-secondary text-opacity-50 text-decoration-line-through user-select-none\">");
	    out.append("                    "+ProductUtils.formatNumber(product.getProductPrice())+"đ");
	    out.append("                  </span>");
	    out.append("                </div>");
	    out.append("                <div class=\"mb-4\">");
	    out.append("                  <p class=\"text-uppercase fw-semibold text-secondary small mb-3\" style=\"letter-spacing: 0.1em;\">");
	    out.append("                    MÀU SẮC");
	    out.append("                  </p>");
	    out.append("                  <div class=\"d-flex gap-3\">");
		for (int j = 0; j < colorArray.size(); j++) {
			out.append("                    <span style=\"background-color: "+colorArray.get(j)+"\" class=\"color-circle\">");
			out.append("                    </span>");
		}
	    out.append("                  </div>");
	    out.append("                </div>");
	    out.append("                <div class=\"mb-4 d-flex align-items-center gap-4\">");
	    out.append("                  <p class=\"text-uppercase fw-semibold text-secondary small mb-0\" style=\"letter-spacing: 0.1em;\">");
	    out.append("                    KÍCH CỠ");
	    out.append("                  </p>");
	    out.append("                  <div class=\"d-flex gap-3 fs-6 fw-normal\">");
	    out.append("                    <span class=\"size-option\">");
	    out.append("                      "+product.getProductSize()+"");
	    out.append("                    </span>");
	    out.append("                  </div>");
	    out.append("                </div>");
		out.append("                <div class=\"mb-4 d-flex align-items-center gap-4\">");
		out.append("                  <p class=\"text-uppercase fw-semibold text-secondary small mb-0\" style=\"letter-spacing: 0.1em;\">");
		out.append("                    CHẤT LIỆU");
		out.append("                  </p>");
		out.append("                  <div class=\"d-flex gap-3 fs-6 fw-normal\">");
		out.append("                    <span class=\"size-option\">");
		out.append("                      "+product.getProductMaterial()+"");
		out.append("                    </span>");
		out.append("                  </div>");
		out.append("                </div>");
	    out.append("                <div class=\"d-flex flex-column gap-2 small fw-semibold text-black\" style=\"max-width: 320px;\">");
	    out.append("                  <div class=\"d-flex justify-content-between\">");
	    out.append("                    <span>");
	    out.append("                      Nhãn hiệu:");
	    out.append("                    </span>");
	    out.append("                    <span class=\"fw-normal\">");
	    out.append("                      "+product.getBrand().getBrandName()+"");
	    out.append("                    </span>");
	    out.append("                  </div>");
	    out.append("                  <div class=\"d-flex justify-content-between\">");
	    out.append("                    <span>");
	    out.append("                      Product Code:");
	    out.append("                    </span>");
	    out.append("                    <span class=\"fw-normal\">");
	    out.append("                      "+product.getProductCode()+"");
	    out.append("                    </span>");
	    out.append("                  </div>");
	    out.append("                  <div class=\"d-flex justify-content-between\">");
	    out.append("                    <span>");
	    out.append("                      Số lượng:");
	    out.append("                    </span>");
	    out.append("                    <span class=\"fw-normal\">");
	    out.append("                      "+product.getProductTotal()+"");
	    out.append("                    </span>");
	    out.append("                  </div>");
		out.append("                  <div class=\"d-flex justify-content-between\">");
		out.append("                    <span>");
		out.append("                      Trạng thái:");
		out.append("                    </span>");
		out.append("                    <span class=\"fw-medium "+ (product.isProductEnable() ? "text-success" : "text-danger")+"\">");
		out.append("                      "+(product.isProductEnable() ? "Hoạt động" : "Ngừng bán")+"");
		out.append("                    </span>");
		out.append("                  </div>");
	    out.append("                </div>");
	    out.append("              </div>");
	    out.append("            </div>");
	    out.append("          </div>");
	    out.append("        </div>");
		out.append("      </div>");
	    out.append("");
	    out.append("        <div class=\"col-xl-6\">");
	    out.append("          <div class=\"card shadow-none\">");
	    out.append("            <div class=\"card-body\">");
	    out.append("              <h5 class=\"card-title fw-semibold\">MÔ TẢ CHI TIẾT</h5>");
	    out.append("              <div class=\"d-flex flex-column\">");
	    out.append("                <div style=\"max-height: 1100px; overflow: auto;\" class=\"specification\">");
	    out.append(product.getProductDescription());
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
