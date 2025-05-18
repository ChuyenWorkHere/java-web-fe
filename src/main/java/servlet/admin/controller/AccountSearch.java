package servlet.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.management.modelmbean.ModelMBean;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.dao.UserDAO;
import servlet.dao.impl.UserDAOImpl;
import servlet.models.User;


@WebServlet("/admin/search-user")
public class AccountSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AccountSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		
		UserDAO userDAO = new UserDAOImpl();
		List<User> users = userDAO.findByName(keyword, 1, 12);
		
		request.setAttribute("users", users);
		
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/accounts-view");
	    dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
