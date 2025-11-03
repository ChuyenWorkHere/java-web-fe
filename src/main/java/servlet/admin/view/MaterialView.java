package servlet.admin.view;

import servlet.dao.MaterialDAO;
import servlet.dao.impl.MaterialDAOImpl;
import servlet.models.Material;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/admin/materials-view")
public class MaterialView extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private MaterialDAO materialDAO;

    public MaterialView() {
        materialDAO = new MaterialDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Material> materials = materialDAO.findAll();

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        request.setAttribute("view", "material");
        RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
        headerDispatcher.include(request, response);

        out.append("<main id=\"main\" class=\"main\">");
        out.append("");
        out.append("    <div class=\"pagetitle d-flex justify-content-between align-items-center\">");
        out.append("      <div class=\"pagetitle\">");
        out.append("        <h1>Chất liệu</h1>");
        out.append("        <nav>");
        out.append("          <ol class=\"breadcrumb\">");
        out.append("            <li class=\"breadcrumb-item\"><a href=\"../admin/home-view\">Home</a></li>");
        out.append("            <li class=\"breadcrumb-item\">Sản phẩm</li>");
        out.append("            <li class=\"breadcrumb-item active\">Chất liệu</li>");
        out.append("          </ol>");
        out.append("        </nav>");
        out.append("      </div>");
        out.append("      <button class=\"btn btn-add bg-dark text-white\" data-bs-toggle=\"modal\" data-bs-target=\"#materialModalAdd\">");
        out.append("        + Thêm mới");
        out.append("      </button>");
        out.append("    </div>");
        out.append("");
        out.append("    <section class=\"section mt-3\">");
        out.append("      <!-- Container để chứa alert -->");
        out.append("      <div id=\"alert-container\" class = \"z-2 position-absolute\"></div>");
        out.append("      <div class=\"card\">");
        out.append("        <div class=\"card-body\">");
        out.append("          <div class=\"table-responsive mt-4\">");
        out.append("            <table class=\"table table-bordered align-middle\">");
        out.append("              <thead class=\"table-light\">");
        out.append("                <tr>");
        out.append("                  <th style=\"width: 70px;\">ID</th>");
        out.append("                  <th>Tên chất liệu</th>");
        out.append("                  <th>Mô tả</th>");
        out.append("                  <th style=\"width: 120px;\">Thao tác</th>");
        out.append("                </tr>");
        out.append("");
        out.append("              </thead>");
        out.append("              <tbody id=\"materialsBody\">");
        materials.forEach(material ->  {
            out.append("                <tr>");
            out.append("                  <td>"+material.getMaterialId()+"</td>");
            out.append("                  <td>"+material.getMaterialName()+"</td>");
            out.append("                  <td>"+material.getDescription()+"</td>");
            out.append("                  <td>");
            out.append("                    <button class=\"btn btn-link action-btn edit\" data-bs-toggle=\"modal\"");
            out.append("                      data-bs-target=\"#materialModalEdit-"+material.getMaterialId()+"\">");
            out.append("                      <i class=\"bi bi-pencil-square text-warning\"></i>");
            out.append("                    </button>");
            out.append("                    <button class=\"btn btn-link action-btn delete\" data-bs-toggle=\"modal\"");
            out.append("                      data-bs-target=\"#materialModalDelete-"+material.getMaterialId()+"\">");
            out.append("                      <i class=\"bi bi-trash text-danger\"></i>");
            out.append("                    </button>");
            out.append("                  </td>");
            out.append("                </tr>");
        });

        out.append("              </tbody>");
        out.append("            </table>");
        out.append("          </div>");
        out.append("        </div>");
        out.append("      </div>");
        out.append("    </section>");
        out.append("");
        out.append("    <!-- Modal Thêm-->");
        out.append("    <div class=\"modal fade\" id=\"materialModalAdd\" tabindex=\"-1\">");
        out.append("      <div class=\"modal-dialog modal-dialog-centered\">");
        out.append("        <div class=\"modal-content\">");
        out.append("          <form id=\"materialForm\" action=\"../admin/material/add\" method=\"post\">");
        out.append("            <div class=\"modal-header\">");
        out.append("              <h5 class=\"modal-title\" id=\"modalTitle\">Thêm chất liệu</h5>");
        out.append("              <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\"></button>");
        out.append("            </div>");
        out.append("            <div class=\"modal-body\">");
        out.append("              <input type=\"hidden\" id=\"materialId\">");
        out.append("              <div class=\"mb-3\">");
        out.append("                <label class=\"form-label\">Tên chất liệu</label>");
        out.append("                <input type=\"text\" class=\"form-control\" name=\"materialName\" required>");
        out.append("              </div>");
        out.append("              <div class=\"mb-3\">");
        out.append("                <label class=\"form-label\">Mô tả</label>");
        out.append("                <textarea class=\"form-control\" name=\"materialDesc\" rows=\"3\"></textarea>");
        out.append("              </div>");
        out.append("            </div>");
        out.append("            <div class=\"modal-footer\">");
        out.append("              <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Hủy</button>");
        out.append("              <button type=\"submit\" class=\"btn btn-primary\">Lưu</button>");
        out.append("            </div>");
        out.append("          </form>");
        out.append("        </div>");
        out.append("      </div>");
        out.append("    </div>");
        out.append("");
        materials.forEach(material -> {
            out.append("    <!-- Modal Sửa -->");
            out.append("    <div class=\"modal fade\" id=\"materialModalEdit-"+material.getMaterialId()+"\" tabindex=\"-1\">");
            out.append("      <div class=\"modal-dialog modal-dialog-centered\">");
            out.append("        <div class=\"modal-content\">");
            out.append("          <form action=\"../admin/material/update\" method=\"post\">");
            out.append("            <div class=\"modal-header\">");
            out.append("              <h5 class=\"modal-title\" id=\"modalTitle\">Cập nhật</h5>");
            out.append("              <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\"></button>");
            out.append("            </div>");
            out.append("            <div class=\"modal-body\">");
            out.append("              <input type=\"hidden\" name=\"materialId\" value=\""+material.getMaterialId()+"\">");
            out.append("              <div class=\"mb-3\">");
            out.append("                <label class=\"form-label\">Tên chất liệu</label>");
            out.append("                <input type=\"text\" class=\"form-control\" name=\"materialName\" value=\""+material.getMaterialName()+"\" required>");
            out.append("              </div>");
            out.append("              <div class=\"mb-3\">");
            out.append("                <label class=\"form-label\">Mô tả</label>");
            out.append("                <textarea class=\"form-control\" name=\"materialDesc\" rows=\"3\">"+material.getDescription()+"</textarea>");
            out.append("              </div>");
            out.append("            </div>");
            out.append("            <div class=\"modal-footer\">");
            out.append("              <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Hủy</button>");
            out.append("              <button type=\"submit\" class=\"btn btn-primary\">Lưu</button>");
            out.append("            </div>");
            out.append("          </form>");
            out.append("        </div>");
            out.append("      </div>");
            out.append("    </div>");
        });

        out.append("");
        materials.forEach(material -> {
            out.append("    <!-- Modal Xóa -->");
            out.append("    <div class=\"modal fade\" id=\"materialModalDelete-"+material.getMaterialId()+"\" tabindex=\"-1\">");
            out.append("      <div class=\"modal-dialog modal-dialog-centered\">");

            out.append("        <div class=\"modal-content\">");
            out.append("          <div class=\"modal-header\">");
            out.append("            <h5 class=\"modal-title\" id=\"modalTitle\">Xác nhận xóa</h5>");
            out.append("            <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\"></button>");
            out.append("          </div>");
            out.append("          <form id=\"materialForm\" action=\"../admin/material/delete\" method=\"post\">");
            out.append("              <input type=\"hidden\" name=\"materialId\" value=\""+material.getMaterialId()+"\">");
            out.append("            <p class=\"pt-2 text-center\">Bạn có chắc chăn muốn xóa chất liệu "+material.getMaterialName()+" ?</p>");
            out.append("            <div class=\"modal-footer\">");
            out.append("              <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Hủy</button>");
            out.append("              <button type=\"submit\" class=\"btn btn-danger\">Xóa</button>");
            out.append("            </div>");
            out.append("          </form>");
            out.append("        </div>");
            out.append("      </div>");
            out.append("    </div>");
        });
        out.append("  <script src=\"../admin/js/showAlert.js\"></script>");
        out.append("  </main>");

        RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
        footerDispatcher.include(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
