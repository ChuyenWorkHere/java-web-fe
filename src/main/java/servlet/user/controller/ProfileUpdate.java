package servlet.user.controller;

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

@WebServlet("/customer/profile/update")
public class ProfileUpdate extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        this.userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("customer");

        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/public/login");
            return;
        }

        String fullname = req.getParameter("nameInput");
        String gender = req.getParameter("genderInput");
        String phone = req.getParameter("phoneInput");
        String address = req.getParameter("addressInput");

        if (fullname == null || fullname.trim().isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"status\": \"error\", \"message\": \"Họ tên không được để trống.\"}");
            return;
        }

        currentUser.setFullname(fullname);
        currentUser.setGender(gender);
        currentUser.setPhoneNumber(phone);
        currentUser.setAddress(address);

        userDAO.updateProfile(currentUser);
        session.setAttribute("customer", currentUser);

        resp.sendRedirect(req.getContextPath() + "/customer/profile");
    }
}
