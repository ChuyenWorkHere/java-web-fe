package servlet.admin.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/admin/account-modal")
public class AccountModalView extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public AccountModalView() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        out.append("    <!-- start large modal -->");
        out.append("    <div class=\"modal fade\" id=\"largeModal\" tabindex=\"-1\">");
        out.append("      <div class=\"modal-dialog modal-lg modal-dialog-centered\">");
        out.append("        <div class=\"modal-content\">");
        out.append("          <div class=\"modal-header\">");
        out.append("            <h5 class=\"modal-title\">Thông tin tài khoản</h5>");
        out.append("            <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
        out.append("          </div>");
        out.append("          <div class=\"modal-body\">");
        out.append("            <div class=\"row\">");
        out.append("              <div class=\"card-body profile-card col-md-6 pt-4 d-flex flex-column align-items-center\">");
        out.append("                <img id=\"modalProfileImage\" src=\"\" alt=\"Profile\" class=\"rounded-circle\" style=\"width: 120px; height: 120px;\">" );

        out.append("              </div>");

        out.append("              <div class=\"col-md-6 tab-pane fade show active profile-overview\" id=\"profile-overview\">");
        out.append("                <div class=\"row mb-1\">");
        out.append("                  <div class=\"col-lg-5 col-md-4 label \">Họ & Tên</div>");
        out.append("                  <div class=\"col-lg-7 col-md-8\" id=\"modalName\"></div>");
        out.append("                </div>");

        out.append("                <div class=\"row mb-1\">");
        out.append("                  <div class=\"col-lg-5 col-md-4 label\">Giới tính</div>");
        out.append("                  <div class=\"col-lg-7 col-md-8\" id=\"modalGender\"></div>");
        out.append("                </div>");

        out.append("                <div class=\"row mb-1\">");
        out.append("                  <div class=\"col-lg-5 col-md-4 label\">Số điện thoại</div>");
        out.append("                  <div class=\"col-lg-7 col-md-8\" id=\"modalPhone\"></div>");
        out.append("                </div>");

        out.append("                <div class=\"row mb-1\">");
        out.append("                  <div class=\"col-lg-5 col-md-4 label\">Email</div>");
        out.append("                  <div class=\"col-lg-7 col-md-8\" id=\"modalEmail\"></div>");
        out.append("                </div>");

        out.append("                <div class=\"row mb-1\">");
        out.append("                  <div class=\"col-lg-5 col-md-4 label\">Địa chỉ</div>");
        out.append("                  <div class=\"col-lg-7 col-md-8\" id=\"modalAddress\"></div>");
        out.append("                </div>");

        out.append("                <div class=\"row mb-1\">");
        out.append("                  <div class=\"col-lg-5 col-md-4 label\">Ngày tạo tài khoản</div>");
        out.append("                  <div class=\"col-lg-7 col-md-8\" id=\"modalCreated\"></div>");
        out.append("                </div>");

        out.append("                <div class=\"row mb-1\">");
        out.append("                  <div class=\"col-lg-5 col-md-4 label\">Ngày sửa thông tin</div>");
        out.append("                  <div class=\"col-lg-7 col-md-8\" id=\"modalUpdated\"></div>");
        out.append("                </div>");

        out.append("                <div class=\"row\">");
        out.append("                  <div class=\"col-lg-5 col-md-4 label\">Trạng thái</div>");
        out.append("                  <div class=\"col-lg-7 col-md-8\" id=\"modalStatus\"></div>");
        out.append("                </div>");
        out.append("              </div>");
        out.append("            </div>");

        out.append("          </div>");

        out.append("        </div>");
        out.append("      </div>");
        out.append("    </div><!-- End large Modal-->");

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
