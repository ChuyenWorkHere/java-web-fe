package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import servlet.dao.OrdersReportDAO;
import servlet.dao.UserDAO;
import servlet.dao.impl.OrdersReportDAOImpl;
import servlet.dao.impl.UserDAOImpl;
import servlet.models.Order;
import servlet.response.OrderResponse;

public class Test {

	public static void main(String[] args) {
		UserDAO userDAO = new UserDAOImpl();
		
//		User user = userDAO.findById(2);
//		List<User> user2 = userDAO.findByName("E");
//		List<User> user2 = userDAO.findByStatus("Tạm ngừng");
//		for(User u : user2) {
//			System.out.println("thông tin user: " + u.toString());
//		}
		
//		boolean a = userDAO.deleteById(2);
//		System.out.println(a);
		
//		String pass = BCrypt.hashpw("123456", BCrypt.gensalt());
//		System.out.println(pass);
		
//		User user = userDAO.checkLogin("nguyenvana@example.com", "123456");
//		System.out.println(user.toString());
//		boolean a = userDAO.updateLoginCount(user.getUserId());
//		int b = userDAO.countAllUsers();
//		System.out.println("b: " + b);
//		List<User> users = userDAO.findByName("n", 1, 12);
//		int a = userDAO.countUserByStatus("active");
//		System.out.println("a: " + a);
//		OrderDAO orderDAO = new OrderDAOImpl();
//
//		List<Order> orders = orderDAO.getAllOrders();
//		for(Order o : orders){
//			System.out.println(o.toString());
//		}

//		Order order = new Order();
//		System.out.println("orderId = " + order.getOrderId());
//		Order order = orderDAO.getOrderDetailByOrderId(1);
//		System.out.println("order = " + order);

		OrdersReportDAO ordersReport = new OrdersReportDAOImpl();
//
//		LocalDate today = LocalDate.now();
//		int year = today.getYear();
//		int month = today.getMonthValue();
//		int month = 5;
//		int day = today.getDayOfMonth();
//		int day = 0;
//		System.out.println("year: " + year);
//		System.out.println("month: " + month);
//		System.out.println("day: " + day);


//		List<Order> orders = ordersReport.findByCreatedAt(year, month, day, 1, 5);
//		for(Order order : orders){
//			System.out.println("order: " + order);
//		}

	}

}
