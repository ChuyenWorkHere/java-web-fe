package servlet.user.view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/public/login")
public class UserLoginView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UserLoginView() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();


		String errorMessage = "";
		
		if(request.getParameter("errorMessage") != null){
			errorMessage = request.getParameter("errorMessage");
		}

		RequestDispatcher headerDispatcher = request.getRequestDispatcher("/public/header-view");
		headerDispatcher.include(request, response);
		out.append("	<main>");
		out.append("        <!-- breadcrumb-area-start -->");
		out.append("        <section class=\"breadcrumb-area\" data-background=\"../user/img/bg/page-title.png\">");
		out.append("            <div class=\"container\">");
		out.append("                <div class=\"row\">");
		out.append("                    <div class=\"col-xl-12\">");
		out.append("                        <div class=\"breadcrumb-text text-center\">");
		out.append("                            <h1>Login</h1>");
		out.append("                            <ul class=\"breadcrumb-menu\">");
		out.append("                                <li><a href=\"../public/home\">home</a></li>");
		out.append("                                <li><span>Login</span></li>");
		out.append("                            </ul>");
		out.append("                        </div>");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </div>");
		out.append("        </section>");
		out.append("        <!-- breadcrumb-area-end -->");
		out.append("");
		out.append("        <!-- login Area Strat-->");
		out.append("        <section class=\"login-area pt-100 pb-100\">");
		out.append("            <div class=\"container\">");
		out.append("                <div class=\"row\">");
		out.append("                    <div class=\"col-lg-8 offset-lg-2\">");
		out.append("                        <div class=\"basic-login\">");
		out.append("                            <h3 class=\"text-center mb-60\">ĐĂNG NHẬP</h3>");
		out.append("                            <form id=\"loginForm\" action=\"../public/customer-login\" method=\"post\">");
		out.append("                                <label for=\"email\">Email <span>**</span></label>");
		out.append("                                <input id=\"email\" name=\"username\" type=\"text\" placeholder=\"Nhập email...\" />");
		out.append("                                <label for=\"password\">Mật khẩu <span>**</span></label>");
		out.append("                                <input id=\"password\" name=\"password\" type=\"password\" placeholder=\"Nhập mật khẩu...\" />");
		out.append("                                <div class=\"login-action mb-20 fix\">");
		out.append("                                    <span class=\"log-rem f-left\">");
		out.append("                                        <input id=\"remember\" type=\"checkbox\" />");
		out.append("                                        <label for=\"remember\">Ghi nhớ đăng nhập!</label>");
		out.append("                                    </span>");
		out.append("                                    <span class=\"forgot-login f-right\">");
		out.append("                                        <a href=\"#\">Quên mật khẩu?</a>");
		out.append("                                    </span>");
		out.append("                                </div>");
		out.append("                                <div class=\"text-danger\" id=\"loginMessage\" class=\"mb-2\">"+errorMessage+"</div>");
		out.append("                                <button type=\"submit\" class=\"btn theme-btn-2 w-100\">Đăng Nhập</button>");
		out.append("                                <div class=\"or-divide\"><span>or</span></div>");
		out.append("                            </form>");
		out.append("                            <a href=\"../public/signup\" class=\"btn theme-btn w-100 text-white\">Đăng Ký Ngay</a>");
		out.append("                        </div>");
		out.append("                    </div>");
		out.append("                </div>");
		out.append("            </div>");
		out.append("        </section>");
		out.append("        <!-- login Area End-->");
		out.append("    </main>");

		RequestDispatcher footerDispatcher = request.getRequestDispatcher("/public/footer-view");
		footerDispatcher.include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
