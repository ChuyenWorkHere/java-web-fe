package servlet.admin.controller;

import servlet.dao.UserDAO;
import servlet.dao.impl.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mega-admin/account/update")
public class MegaAdmin extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    public MegaAdmin() {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int userId = Integer.parseInt(req.getParameter("userId"));
            int roleId = Integer.parseInt(req.getParameter("roleId"));
            userDAO.updateRoleUser(userId, roleId);
            resp.sendRedirect(req.getContextPath() + "/mega-admin/manage-account");
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/mega-admin/manage-account");
        }

    }
}
