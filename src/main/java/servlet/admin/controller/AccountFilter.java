package servlet.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.dao.UserDAO;
import servlet.dao.impl.UserDAOImpl;
import servlet.models.User;


@WebServlet("/admin/filter")
public class AccountFilter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AccountFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");
		
		UserDAO userDAO = new UserDAOImpl();
		List<User> users = userDAO.findByStatus(status, 1, 12);
		
		request.setAttribute("users", users);
		request.setAttribute("status", status);
		
		request.getRequestDispatcher("../admin/accounts-view").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
