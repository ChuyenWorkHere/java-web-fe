package servlet.user;

import servlet.dao.UserDAO;
import servlet.dao.impl.UserDAOImpl;
import servlet.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/public/customer-login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public LoginController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.checkLogin(username, password);

        if (user != null){
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getFullname());
            session.setAttribute("role", user.getRole().getRoleName());
            if("CUSTOMER".equals(user.getRole().getRoleName())) {
                response.sendRedirect("../public/home");
            }
        }else {
            request.setAttribute("errorMessage", "Tài khoản hoặc mật khẩu không hợp lệ");
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
        }
    }
}
