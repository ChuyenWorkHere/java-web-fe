package servlet.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.dao.UserDAO;
import servlet.dao.impl.UserDAOImpl;

@WebServlet("/admin/account-restore")
public class AccountRestore extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public AccountRestore() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId")) ;
        System.out.println("UserId: " + userId);

        UserDAO userDAO = new UserDAOImpl();
        boolean success =  userDAO.restoreById(userId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"success\":" + success + "}");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
