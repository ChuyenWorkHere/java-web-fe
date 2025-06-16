package servlet.dao;

import servlet.models.Order;
import servlet.models.User;
import servlet.response.OrderReportFeedback;
import servlet.response.OrderResponse;
import servlet.response.UserReportResponse;

import java.util.List;

public interface OrdersReportDAO {

    List<Order> findByCreatedAt(int year, int month, int day, int page, int pageSize);

    int countCreatedAt(int year, int month, int day);

    List<OrderResponse> getMonthlyOrderStatus(int year);

    List<OrderResponse> getDaylyOrderStatus(int year, int month);

    public List<UserReportResponse> getTopBuyers(int year, int month, int limit);

    public List<UserReportResponse> getTopCancellers(int year, int month, int limit);
}
