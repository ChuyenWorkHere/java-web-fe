package servlet.admin.controller;

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

@WebServlet("/admin/update-profile")
public class AccountUpdate extends HttpServlet {

    private UserDAO userDAO;

    public AccountUpdate() {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/admin/profile");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            resp.sendRedirect(req.getContextPath() + "/admin/signin");
            return;
        }

        String fullname = req.getParameter("fullName");
        String gender = req.getParameter("gender");
        String address = req.getParameter("address");
        String phone = req.getParameter("phoneNumber");
        String email = req.getParameter("email");

        loggedInUser.setFullname(fullname);
        loggedInUser.setGender(gender);
        loggedInUser.setAddress(address);
        loggedInUser.setPhoneNumber(phone);
        loggedInUser.setEmail(email);

        boolean isUpdated = userDAO.updateUserProfile(loggedInUser);

        if (isUpdated) {
            session.setAttribute("loggedInUser", loggedInUser);
            resp.sendRedirect(req.getContextPath() + "/admin/profile?title=profile&action=edit&noti=success");
        } else {
            resp.sendRedirect(req.getContextPath() + "/admin/profile?title=profile&action=edit&noti=failed");
        }
    }
}
