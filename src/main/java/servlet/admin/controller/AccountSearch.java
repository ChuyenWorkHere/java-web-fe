package servlet.admin.controller;

import java.io.IOException;
import java.util.List;

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
		String pageNumber = request.getParameter("pageNo");

		int pageNo = pageNumber != null ? Integer.parseInt(pageNumber) : 1;

		UserDAO userDAO = new UserDAOImpl();
		List<User> users = userDAO.findByName(keyword, pageNo, 12);
		int pageNumbers = userDAO.countUserByName(keyword) / 12 + 1;
		
		request.setAttribute("users", users);
		request.setAttribute("keyword", keyword);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageNumbers", pageNumbers);
		request.setAttribute("statusLink", "/admin/search-user");

	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/accounts-view");
	    dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
