package servlet.dao;

import servlet.models.Order;
import servlet.response.OrderResponse;
import servlet.response.ReportResponse;
import servlet.response.UserReportResponse;

import java.util.List;
import java.util.Map;

public interface OrdersReportDAO {

    List<Order> findByCreatedAt(int year, int month, int day, int page, int pageSize);

    int countCreatedAt(int year, int month, int day);

    List<OrderResponse> getMonthlyOrderStatus(int year);

    List<OrderResponse> getDailyOrderStatus(int year, int month);

    List<UserReportResponse> getTopBuyers(int year, int month, int limit);

    List<UserReportResponse> getTopCancellers(int year, int month, int limit);

    Map<Integer, Integer> buildSalesChartData(String type);

    Map<Integer, Integer> buildRevenueChartData(String type);
}
