package servlet.user.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import servlet.dao.UserDAO;
import servlet.dao.impl.UserDAOImpl;
import servlet.models.User;

@WebServlet("/public/customer-signup")
public class UserSignup extends HttpServlet {

    private UserDAO userDAO;

    public UserSignup() {
        super();
        this.userDAO = new UserDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/Register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        if (fullname == null || fullname.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            confirmPassword == null || confirmPassword.trim().isEmpty()) {
            req.setAttribute("errorMessage", "Vui lòng điền đầy đủ thông tin.");
            req.getRequestDispatcher("/Register.jsp").forward(req, resp);
            return;
        }

        if (!password.equals(confirmPassword)) {
            req.setAttribute("errorMessage", "Mật khẩu xác nhận không khớp.");
            req.getRequestDispatcher("/Register.jsp").forward(req, resp);
            return;
        }

        User newUser = userDAO.signUp(fullname, email, password);

        if (newUser != null) {
            HttpSession session = req.getSession();
            session.setAttribute("userId", newUser.getUserId());
            session.setAttribute("user", newUser);
            session.setAttribute("successMessage", "Đăng ký tài khoản thành công!");
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("errorMessage", "Email đã tồn tại hoặc có lỗi xảy ra trong quá trình đăng ký.");
            req.getRequestDispatcher("/Register.jsp").forward(req, resp);
        }
    }
}
