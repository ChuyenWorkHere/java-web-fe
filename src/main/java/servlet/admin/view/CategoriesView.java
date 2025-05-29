package servlet.admin.view;

import servlet.dao.CategoryDAO;
import servlet.dao.impl.CategoryDAOImpl;
import servlet.models.Category;
import servlet.models.Product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/admin/categories-view")
public class CategoriesView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CategoryDAO categoryDAO;
    
    public CategoriesView() {
        super();
		categoryDAO = new CategoryDAOImpl();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		List<Category> categories = categoryDAO.findAll(100, 1, "ASC", "product_id");


		PrintWriter out = response.getWriter();
		request.setAttribute("view", "cate");
		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
	    headerDispatcher.include(request, response);

	    out.append("<main id=\"main\" class=\"main\">");
	    out.append("");
	    out.append("    <div class=\"pagetitle d-flex justify-content-between\">");
	    out.append("      <h1>Danh mục</h1>");
	    out.append("      <nav class=\"d-flex align-items-center\">");
	    out.append("        <ol class=\"breadcrumb  mb-0\">");
	    out.append("          <li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-fill\"></i></a></li>");
	    out.append("          <li class=\"breadcrumb-item active\">Danh mục</li>");
	    out.append("        </ol>");
	    out.append("      </nav>");
	    out.append("    </div><!-- End Page Title -->");
	    out.append("");
	    out.append("    <section class=\"section dashboard\">");
	    out.append("      <div id=\"alert-container\" class=\"z-2 position-absolute\"></div>");
	    out.append("      <div class=\"row product\">");
	    out.append("        <div class=\"col-xl-9 order-2 order-xl-1 mt-4\">");
	    out.append("          <div class=\"card\">");
	    out.append("            <div class=\"card-body\">");
	    out.append("              <div class=\"table-responsive\">");
	    out.append("                <table class=\"table table-bordered mt-4 container-md\" style=\"overflow: scroll;\">");
	    out.append("                  <thead>");
	    out.append("                    <tr class=\"text-center\">");
	    out.append("                      <th scope=\"col\" class=\"col-1\">ID</th>");
	    out.append("                      <th scope=\"col\" class=\"col-2\">Danh Mục</th>");
	    out.append("                      <th scope=\"col\" class=\"col-5\">Mô Tả</th>");
	    out.append("                      <th scope=\"col\" class=\"col-2\">Trạng Thái</th>");
	    out.append("                      <th scope=\"col\" class=\"col-2\">Thao Tác</th>");
	    out.append("                    </tr>");
	    out.append("                  </thead>");
	    out.append("                  <tbody>");

		for (int i = 0; i < categories.size(); i++) {
			out.append("                    <tr class=\"align-middle text-center\">");
			out.append("                      <td>"+categories.get(i).getCategoryId()+"</td>");
			out.append("                      <td>"+categories.get(i).getCategoryName()+"</td>");
			out.append("                      <td>"+categories.get(i).getCategoryDescription()+"</td>");
			out.append("                      <td>");
			out.append("                        <div class=\"dropdown\">");
			out.append("                          <button class=\"py-2 btn btn-sm badge "+ (categories.get(i).getIsActive() > 0 ? "bg-success" : "bg-danger")+" text-white\" type=\"button\"");
			out.append("                            data-bs-toggle=\"dropdown\">");
			out.append("                            <i class=\"bi bi-check-circle\"></i> "+ (categories.get(i).getIsActive() > 0 ? "Hoạt động" : "Bảo trì") +"");
			out.append("                          </button>");
			out.append("                        </div>");
			out.append("                      </td>");
			out.append("                      <td>");
			out.append("                        <button type=\"button\" class=\"btn\" data-bs-toggle=\"modal\" data-bs-target=\"#modalEdit"+categories.get(i).getCategoryId()+"\">");
			out.append("                          <i class=\"bi bi-pencil-square fs-5 text-primary pointer\"></i>");
			out.append("                        </button>");
			out.append("                        <button type=\"button\" class=\"btn\" data-bs-toggle=\"modal\" data-bs-target=\"#modalDel"+categories.get(i).getCategoryId()+"\">");
			out.append("                          <i class=\"bi bi-trash fs-5 text-danger pointer\"></i>");
			out.append("                        </button>");
			out.append("                      </td>");
			out.append("                    </tr>");

			out.append("        <!--Modal Delete Category-->");
			out.append("        <div class=\"modal fade\" id=\"modalDel"+categories.get(i).getCategoryId()+"\" tabindex=\"-1\">");
			out.append("          <div class=\"modal-dialog modal-dialog-centered\">");
			out.append("            <div class=\"modal-content\">");
			out.append("              <div class=\"modal-body text-center py-3\">");
			out.append("                <div class=\"my-3\"><i class=\"bi bi-exclamation-octagon me-1\"></i>Xác nhận xóa danh mục "+categories.get(i).getCategoryName()+"?</div>");
			out.append("                <form id=\"form"+categories.get(i).getCategoryId()+"\" method=\"post\" action=\"../admin/delete-category\">");
			out.append("                  <input type=\"hidden\" name=\"categoryId\" value=\"" + categories.get(i).getCategoryId() + "\">");
			out.append("                  <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Đóng</button>");
			out.append("                  <button type=\"submit\" class=\"btn btn-danger del-confirm\">Xóa</button>");
			out.append("                </form>");
			out.append("              </div>");
			out.append("            </div>");
			out.append("          </div>");
			out.append("        </div><!-- End Modal Delete Category-->");

			out.append("        <!--Modal Edit Category-->");
			out.append("        <div class=\"modal fade\" id=\"modalEdit"+categories.get(i).getCategoryId()+"\" tabindex=\"-1\">");
			out.append("          <div class=\"modal-dialog modal-dialog-centered\">");
			out.append("            <div class=\"modal-content\">");
			out.append("              <div class=\"modal-header\">");
			out.append("                <h5 class=\"modal-title\">Danh Mục</h5>");
			out.append("                <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
			out.append("              </div>");
			out.append("              <div class=\"modal-body\">");
			out.append("                <form action=\"../admin/edit-category\" method=\"POST\">");
			out.append("                  <div class=\"row mb-3 bg-white\">");
			out.append("                    <label for=\"inputText\" class=\"col-sm-3 col-form-label\">ID</label>");
			out.append("                    <div class=\"col-sm-9\">");
			out.append("                      <input type=\"text\" name=\"categoryId\" class=\"form-control\" readonly value=\""+categories.get(i).getCategoryId()+"\">");
			out.append("                    </div>");
			out.append("                  </div>");
			out.append("                  <div class=\"row mb-3 bg-white\">");
			out.append("                    <label for=\"inputText\" class=\"col-sm-3 col-form-label\">Danh mục</label>");
			out.append("                    <div class=\"col-sm-9\">");
			out.append("                      <input id=\"editNameCategory\" name=\"categoryName\" type=\"text\" class=\"form-control\" value=\""+categories.get(i).getCategoryName()+"\">");
			out.append("                      <p class=\"text-danger\" id=\"editNameMessage\"></p>");
			out.append("                    </div>");
			out.append("                  </div>");
			out.append("                  <div class=\"row mb-3 bg-white\">");
			out.append("                    <label for=\"inputText\" class=\"col-sm-3 col-form-label\">Mô tả</label>");
			out.append("                    <div class=\"col-sm-9\">");
			out.append("                      <textarea name=\"categoryDescription\" id=\"editDetailCategory\" class=\"form-control\" style=\"height: 150px\">"+categories.get(i).getCategoryDescription()+"</textarea>");
			out.append("                      <p class=\"text-danger\" id=\"editDetailMessage\"></p>");
			out.append("                    </div>");
			out.append("                  </div>");
			out.append("                  <div class=\"row mb-3 bg-white\">");
			out.append("                    <label class=\"col-sm-3 col-form-label\">Trạng Thái</label>");
			out.append("                    <div class=\"col-sm-9\">");
			out.append("                      <select name=\"isActive\" class=\"form-select\" aria-label=\"Default select example\">");
			out.append("                        <option value=\"1\" "+ (categories.get(i).getIsActive() > 0 ? "" : "selected")+">Hoạt động</option>");
			out.append("                        <option value=\"0\" "+(categories.get(i).getIsActive() <= 0 ? "selected" : "")+">Bảo trì</option>");
			out.append("                      </select>");
			out.append("                    </div>");
			out.append("                  </div>");
			out.append("                </form>");
			out.append("              </div>");
			out.append("              <div class=\"modal-footer\">");
			out.append("                <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Đóng</button>");
			out.append("                <button type=\"button\" onClick=\"validFormEditCategory(this)\" class=\"btn btn-primary edit-confirm\">Lưu</button>");
			out.append("              </div>");
			out.append("            </div>");
			out.append("          </div>");
			out.append("        </div><!-- End Modal Edit Category-->");

		}
	    out.append("                  </tbody>");
	    out.append("                </table>");
	    out.append("              </div>");
	    out.append("              <!-- End Default Table Example -->");
	    out.append("");
	    out.append("              <!-- Pagination with icons -->");
	    out.append("              <nav aria-label=\"Page navigation example \">");
	    out.append("                <div class=\"d-flex flex-end justify-content-end\">");
	    out.append("                  <ul class=\"pagination\">");
	    out.append("                  <li class=\"page-item\">");
	    out.append("                    <a class=\"page-link\" href=\"#\" aria-label=\"Previous\">");
	    out.append("                      <span aria-hidden=\"true\">&laquo;</span>");
	    out.append("                    </a>");
	    out.append("                  </li>");
	    out.append("                  <li class=\"page-item\"><a class=\"page-link\" href=\"#\">1</a></li>");
	    out.append("                  <li class=\"page-item\"><a class=\"page-link\" href=\"#\">2</a></li>");
	    out.append("                  <li class=\"page-item\"><a class=\"page-link\" href=\"#\">3</a></li>");
	    out.append("                  <li class=\"page-item\">");
	    out.append("                    <a class=\"page-link\" href=\"#\" aria-label=\"Next\">");
	    out.append("                      <span aria-hidden=\"true\">&raquo;</span>");
	    out.append("                    </a>");
	    out.append("                  </li>");
	    out.append("                </ul>");
	    out.append("                </div>");
	    out.append("              </nav><!-- End Pagination with icons -->");
	    out.append("            </div>");
	    out.append("");
	    out.append("          </div>");
	    out.append("        </div>");
	    out.append("");
	    out.append("");
	    out.append("        <!--Modal Add Category-->");
	    out.append("        <div class=\"modal fade\" id=\"modalAdd\" tabindex=\"-1\">");
	    out.append("          <div class=\"modal-dialog modal-dialog-centered\">");
	    out.append("            <div class=\"modal-content\">");
	    out.append("              <div class=\"modal-header\">");
	    out.append("                <h5 class=\"modal-title\">Danh Mục</h5>");
	    out.append("                <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
	    out.append("              </div>");
	    out.append("              <div class=\"modal-body\">");
	    out.append("                <form id=\"formAddCate\" action=\"../admin/category/add\" method=\"POST\"> ");
	    out.append("                  <div class=\"row mb-3 bg-white\">");
	    out.append("                    <label for=\"inputText\" class=\"col-sm-3 col-form-label\">Danh mục</label>");
	    out.append("                    <div class=\"col-sm-9\">");
	    out.append("                      <input name=\"categoryName\" id=\"addNameCategory\" type=\"text\" class=\"form-control\" value=\"\">");
	    out.append("                      <p class=\"text-danger\" id=\"addNameMessage\"></p>");
	    out.append("                    </div>");
	    out.append("                  </div>");
	    out.append("                  <div class=\"row mb-3 bg-white\">");
	    out.append("                    <label for=\"inputText\" class=\"col-sm-3 col-form-label\">Mô tả</label>");
	    out.append("                    <div class=\"col-sm-9\">");
	    out.append("                      <textarea name=\"categoryDescription\" id=\"addDetailCategory\" class=\"form-control\" style=\"height: 150px\"></textarea>");
	    out.append("                      <p class=\"text-danger\" id=\"addDetailMessage\"></p>");
	    out.append("                    </div>");
	    out.append("                  </div>");
	    out.append("                  <div class=\"row mb-3 bg-white\">");
	    out.append("                    <label class=\"col-sm-3 col-form-label\">Trạng Thái</label>");
	    out.append("                    <div class=\"col-sm-9\">");
	    out.append("                      <select name=\"isActive\" class=\"form-select\" aria-label=\"Default select example\">");
	    out.append("                        <option value=\"1\">Hoạt động</option>");
	    out.append("                        <option value=\"0\">Bảo trì</option>");
	    out.append("                      </select>");
	    out.append("                    </div>");
	    out.append("                  </div>");
	    out.append("                </form>");
	    out.append("              </div>");
	    out.append("              <div class=\"modal-footer\">");
	    out.append("                <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Đóng</button>");
	    out.append("                <button type=\"button\" id=\"addCategory\" class=\"btn btn-primary confirm\">Lưu</button>");
	    out.append("              </div>");
	    out.append("            </div>");
	    out.append("          </div>");
	    out.append("        </div><!-- End Modal Add Category-->");
	    out.append("");
	    out.append("");
	    out.append("        <div class=\"col-xl-3 order-1 order-xl-2 mt-4\">");
	    out.append("          <div class=\"row card\" style=\"background-color: #f5f8fe; position: sticky; top: 100px;\">");
	    out.append("              <button type=\"button\" class=\"btn-search w-100\" data-bs-toggle=\"modal\" data-bs-target=\"#modalAdd\"><i class=\"bi bi-plus fs-5\"></i>THÊM MỚI</button>");
	    out.append("          </div>");
	    out.append("          <div class=\"row card py-2\" style=\"background-color: #f5f8fe; position: sticky; top: 150px;\">");
	    out.append("            <div class=\"container py-4 d-flex flex-column align-items-start gap-3\" style=\"max-width: 320px;\">");
	    out.append("              <div class=\"d-flex align-items-center gap-2 filter-label\">");
	    out.append("                <i class=\"bi bi-funnel-fill\"></i>");
	    out.append("                <span>BỘ LỌC</span>");
	    out.append("              </div>");
	    out.append("              <input type=\"text\" placeholder=\"Tìm kiếm...\" class=\"w-100 py-2 px-3 form-control\" style=\"outline: none;\">");
	    out.append("              <select class=\"form-select\" aria-label=\"Danh mục\">");
	    out.append("                <option selected>TRẠNG THÁI</option>");
	    out.append("                <option value=\"1\">HOẠT ĐỘNG</option>");
	    out.append("                <option value=\"2\">BẢO TRÌ</option>");
	    out.append("              </select>");
	    out.append("              <button type=\"button\" class=\"btn-search\">TÌM KIẾM</button>");
	    out.append("            </div>");
	    out.append("          </div>");
	    out.append("        </div>");
	    out.append("    </section>");
	    out.append("");
	    out.append("  </main><!-- End #main -->");
	    out.append("  <script src=\"../admin/js/mainCategory.js\"></script>");
		out.append("  <script src=\"../admin/js/showAlert.js\"></script>");
	    
		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
		footerDispatcher.include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
