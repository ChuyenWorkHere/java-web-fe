package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import servlet.dao.OrdersReportDAO;
import servlet.dao.UserDAO;
import servlet.dao.impl.OrdersReportDAOImpl;
import servlet.dao.impl.UserDAOImpl;
import servlet.models.Category;
import servlet.models.Order;
import servlet.models.User;
import servlet.response.OrderResponse;
import servlet.utils.PasswordUtils;

public class Test {

	public static void main(String[] args) {
		UserDAO userDAO = new UserDAOImpl();

		User user = userDAO.checkLogin("nguyenvana@example.com", "Admin123!");
		System.out.println("user: " + user);

		String hashCode = PasswordUtils.hashPassword("Admin123!");
		System.out.println("hashCode: " + hashCode);
	}

}
