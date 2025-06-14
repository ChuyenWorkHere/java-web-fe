package test;

import servlet.dao.*;
import servlet.dao.impl.*;
import servlet.models.Order;
import servlet.models.OrderItem;
import servlet.models.Product;
import servlet.models.User;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Test {

	public static void main(String[] args) {
//		UserDAO userDAO = new UserDAOImpl();
//
//		User user = userDAO.checkLogin("admin@gmail.com", "Admin123!");
//		System.out.println("user: " + user);
//
//		String hashCode = PasswordUtils.hashPassword("Admin123!");
//		System.out.println("hashCode: " + hashCode);
		OrderDAO orderDAO = new OrderDAOImpl();
		OrderItemsDAO orderItemsDAO = new OrderItemsDAOImpl();

		//Giả lập user id
		List<Integer> userId = new ArrayList<>();
		for(int i=1; i <= 42; i++){
			userId.add(i);
		}

		//Gỉa lập product id
		List<Integer> productId = new ArrayList<>();
		for(int i=6; i<=29; i++){
			productId.add(i);
		}

		//Gỉa lập price
		List<Integer> price = new ArrayList<>();
		for(int i=10; i<=20; i++){
			price.add(i);
		}

		//Gải lập status
		List<String> orderStatus = List.of("CANCELLED");

		//Gỉa lập payment status
		List<String> paymentStatus = List.of("UNPAID");

		//Gỉa lập payment method
		List<String> paymentMethod = List.of("CREDIT_CARD","CASH_ON_DELIVERY", "BANK_TRANSFER");

		//Order note
		List<String> orderNote = List.of("Giao hàng nhanh","Gói cẩn thận", "Giao trong giờ hành chính", "Giao hàng thành công");

		//Random date
		LocalDate startDate = LocalDate.of(2025, 1, 1);
		LocalDate endDate = LocalDate.of(2025,5 , 30);


		for(int i = 100; i< 1500; i++) {
			Order order = new Order();

			order.setTotalPrice(price.get(ThreadLocalRandom.current().nextInt(0,  price.size())) * 1_000_000);
			order.setOrderStatus(orderStatus.get(ThreadLocalRandom.current().nextInt(0,  orderStatus.size())));
			order.setPaymentMethod(paymentMethod.get(ThreadLocalRandom.current().nextInt(0,  paymentMethod.size())));
			order.setPaymentStatus(paymentStatus.get(ThreadLocalRandom.current().nextInt(0,  paymentStatus.size())));
			order.setCreatedAt(getRandomDate(startDate, endDate));
			order.setOrderNote(orderNote.get(ThreadLocalRandom.current().nextInt(0,  orderNote.size())));
			User user = new User();
			user.setUserId( userId.get(ThreadLocalRandom.current().nextInt(0,  userId.size())));
			order.setUser(user);

			order = orderDAO.saveOrder(order);

			List<OrderItem> orderItems = new ArrayList<>();
			for(int j = 0; j< 10; j++) {
				OrderItem item = new OrderItem();
				item.setOrderQuantity(price.get(ThreadLocalRandom.current().nextInt(0,  price.size())));
				item.setOrderPrice(price.get(ThreadLocalRandom.current().nextInt(0,  price.size())) * 50_000);
				item.setOrder(order);
				Product product = new Product();
				product.setProductId(productId.get(ThreadLocalRandom.current().nextInt(0, productId.size())));
				item.setProduct(product);

				orderItems.add(item);
			}
			orderItemsDAO.saveOrderItems(orderItems);
		}
//		SalesDAO salesDAO = new SalesDAOImpl();
//		System.out.println(salesDAO.reportChartBuilder(6, 2025));
	}

	public static java.util.Date getRandomDate(LocalDate start, LocalDate end) {
		long days = ChronoUnit.DAYS.between(start, end);
		long randomDays = ThreadLocalRandom.current().nextLong(days + 1);
		return Date.valueOf(start.plusDays(randomDays));
	}

}
