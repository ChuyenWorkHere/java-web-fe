package servlet.dao;

import servlet.models.Order;
import servlet.response.OrderResponse;

import java.util.List;

public interface OrdersReportDAO {

    List<Order> findByCreatedAt(int year, int month, int day, int page, int pageSize);

    List<OrderResponse> getMonthlyOrderStatus(int year);

    List<OrderResponse> getDaylyOrderStatus(int year, int month);
}
