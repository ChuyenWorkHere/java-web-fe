package servlet.dao.impl;

import servlet.dao.OrdersReportDAO;
import servlet.models.Order;
import servlet.response.OrderResponse;
import servlet.response.UserReportResponse;
import servlet.utils.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class OrdersReportDAOImpl implements OrdersReportDAO {

    @Override
    public List<Order> findByCreatedAt(int year, int month, int day, int pageNo, int pageSize) {

        List<Order> orders = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT order_id, order_status, created_at FROM orders WHERE YEAR(created_at) = ? ");

        if (month != 0) {
            sql.append("AND MONTH(created_at) = ? ");
        }
        if (day != 0) {
            sql.append("AND DAY(created_at) = ? ");
        }

        sql.append("ORDER BY created_at DESC LIMIT ? OFFSET ?");

        try(Connection conn = DataSourceUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString())){

            int offset = (pageNo - 1) * pageSize;
            int paramIndex = 1;
            ps.setInt(paramIndex++, year);

            if (month != 0) {
                ps.setInt(paramIndex++, month);
            }
            if (day != 0) {
                ps.setInt(paramIndex++, day);
            }

            ps.setInt(paramIndex++, pageSize);
            ps.setInt(paramIndex, offset);

            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Order order = new Order();
                    order.setOrderId(rs.getInt("order_id"));
                    order.setCreatedAt(rs.getTimestamp("created_at"));
                    order.setOrderStatus(rs.getString("order_status"));

                    orders.add(order);
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public int countCreatedAt(int year, int month, int day) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) FROM orders WHERE YEAR(created_at) = ? ");

        if (month != 0) {
            sql.append("AND MONTH(created_at) = ? ");
        }
        if (day != 0) {
            sql.append("AND DAY(created_at) = ? ");
        }

        try(Connection conn = DataSourceUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString())){

            int paramIndex = 1;
            ps.setInt(paramIndex++, year);

            if (month != 0) {
                ps.setInt(paramIndex++, month);
            }
            if (day != 0) {
                ps.setInt(paramIndex, day);
            }

            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    return rs.getInt(1);
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<OrderResponse> getMonthlyOrderStatus(int year) {

        List<OrderResponse> orderResponses = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT month(created_at) as Month, order_status, count(*) as Total ");
        sql.append("FROM orders WHERE year(created_at) = ? ");
        sql.append("GROUP BY Month, order_status ORDER BY Month");

        try(Connection conn = DataSourceUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString())){

            ps.setInt(1, year);

            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    OrderResponse orderResponse = new OrderResponse();

                    orderResponse.setMonth(rs.getInt("Month"));
                    orderResponse.setOrderStatus(rs.getString("order_status"));
                    orderResponse.setTotal(rs.getInt("Total"));

                    orderResponses.add(orderResponse);
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return orderResponses;
    }

    @Override
    public List<OrderResponse> getDailyOrderStatus(int year, int month) {
        List<OrderResponse> orderResponses = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT month(created_at) as Month, day(created_at) as Day, order_status, count(*) as Total ");
        sql.append("FROM orders WHERE year(created_at) = ? and month(created_at) = ? ");
        sql.append("GROUP BY Month, Day, order_status ORDER BY Day");

        try(Connection conn = DataSourceUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString())){

            ps.setInt(1, year);
            ps.setInt(2, month);

            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    OrderResponse orderResponse = new OrderResponse();

                    orderResponse.setMonth(rs.getInt("Month"));
                    orderResponse.setDay(rs.getInt("Day"));
                    orderResponse.setOrderStatus(rs.getString("order_status"));
                    orderResponse.setTotal(rs.getInt("Total"));

                    orderResponses.add(orderResponse);
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return orderResponses;
    }


    @Override
    public List<UserReportResponse> getTopBuyers(int year, int month, int limit) {
        List<UserReportResponse> result = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("u.user_id, u.user_fullname, u.gender, u.user_phone_number, u.user_email, ");
        sql.append("o.order_status, ");
        sql.append("COUNT(o.order_id) AS total_orders, ");
        sql.append("SUM(o.total_price) AS total_amount ");
        sql.append("FROM orders o ");
        sql.append("JOIN users u ON o.user_id = u.user_id ");
        sql.append("WHERE o.order_status = 'DELIVERED' ");
        sql.append("AND (? = 0 OR YEAR(o.created_at) = ?) ");
        sql.append("AND (? = 0 OR MONTH(o.created_at) = ?) ");
        sql.append("GROUP BY u.user_id, u.user_fullname, u.gender, u.user_phone_number, u.user_email ");
        sql.append("ORDER BY total_orders DESC ");
        sql.append("LIMIT ?");

        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            stmt.setInt(1, year);
            stmt.setInt(2, year);
            stmt.setInt(3, month);
            stmt.setInt(4, month);
            stmt.setInt(5, limit);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    UserReportResponse user = new UserReportResponse();
                    user.setUserId(rs.getInt("user_id"));
                    user.setFullname(rs.getString("user_fullname"));
                    user.setGender(rs.getString("gender"));
                    user.setPhoneNumber(rs.getString("user_phone_number"));
                    user.setEmail(rs.getString("user_email"));
                    user.setStatus(rs.getString("order_status"));
                    user.setTotalOrders(rs.getInt("total_orders"));
                    user.setTotalAmount(rs.getDouble("total_amount"));

                    result.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<UserReportResponse> getTopCancellers(int year, int month, int limit) {
        List<UserReportResponse> result = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("u.user_id, u.user_fullname, u.gender, u.user_phone_number, u.user_email, ");
        sql.append("o.order_status, ");
        sql.append("COUNT(o.order_id) AS total_orders, ");
        sql.append("SUM(o.total_price) AS total_amount ");
        sql.append("FROM orders o ");
        sql.append("JOIN users u ON o.user_id = u.user_id ");
        sql.append("WHERE o.order_status = 'CANCELLED' ");
        sql.append("AND (? = 0 OR YEAR(o.created_at) = ?) ");
        sql.append("AND (? = 0 OR MONTH(o.created_at) = ?) ");
        sql.append("GROUP BY u.user_id, u.user_fullname, u.gender, u.user_phone_number, u.user_email ");
        sql.append("ORDER BY total_orders DESC ");
        sql.append("LIMIT ?");

        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            stmt.setInt(1, year);
            stmt.setInt(2, year);
            stmt.setInt(3, month);
            stmt.setInt(4, month);
            stmt.setInt(5, limit);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    UserReportResponse user = new UserReportResponse();
                    user.setUserId(rs.getInt("user_id"));
                    user.setFullname(rs.getString("user_fullname"));
                    user.setGender(rs.getString("gender"));
                    user.setPhoneNumber(rs.getString("user_phone_number"));
                    user.setEmail(rs.getString("user_email"));
                    user.setStatus(rs.getString("order_status"));
                    user.setTotalOrders(rs.getInt("total_orders"));
                    user.setTotalAmount(rs.getDouble("total_amount"));

                    result.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Map<Integer, Integer> buildSalesChartData(String type) {

        Map<Integer, Integer> labelWithValue = new HashMap<>();

        String query = "";

        if("MONTHLY".equalsIgnoreCase(type)){
            query = buildMonthlySalesQuery();
        } else if ("YEARLY".equalsIgnoreCase(type)) {
            query = buildYearlySalesQuery();
        }

        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if("MONTHLY".equalsIgnoreCase(type)){
                    while (rs.next()) {
                        Integer label = rs.getInt("day_of_month");
                        Integer value = rs.getInt("total_sold");
                        labelWithValue.put(label, value);
                    }
                } else if ("YEARLY".equalsIgnoreCase(type)) {
                    while (rs.next()) {
                        Integer label = rs.getInt("month_of_year");
                        Integer value = rs.getInt("total_sold");
                        labelWithValue.put(label, value);
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if("MONTHLY".equalsIgnoreCase(type)) {
            List<Integer> dayOfMonth = new ArrayList<>();
            for(int i = 1;  i <= LocalDate.now().getDayOfMonth(); i++) {
                dayOfMonth.add(i);
            }
            Set<Integer> labels = labelWithValue.keySet();
            for(Integer day : dayOfMonth) {
                if(!labels.contains(day)) {
                    labelWithValue.put(day, 0);
                }
            }

        } else if ("YEARLY".equalsIgnoreCase(type)) {
            List<Integer> monthOfYear = new ArrayList<>();
            for(int i = 1;  i <= 12; i++) {
                monthOfYear.add(i);
            }
            Set<Integer> labels = labelWithValue.keySet();
            for(Integer month : monthOfYear) {
                if(!labels.contains(month)) {
                    labelWithValue.put(month, 0);
                }
            }
        }
        return labelWithValue;
    }

    @Override
    public Map<Integer, Integer> buildRevenueChartData(String type) {

        Map<Integer, Integer> labelWithValue = new HashMap<>();

        String query = "";

        if ("MONTHLY".equalsIgnoreCase(type)) {
            query = buildMonthlyRevenueQuery();
        } else if ("YEARLY".equalsIgnoreCase(type)) {
            query = buildYearlyRevenueQuery();
        }

        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            try (ResultSet rs = stmt.executeQuery()) {
                if ("MONTHLY".equalsIgnoreCase(type)) {
                    while (rs.next()) {
                        Integer label = rs.getInt("day_of_month");
                        Integer value = rs.getInt("total_revenue");
                        labelWithValue.put(label, value);
                    }
                } else if ("YEARLY".equalsIgnoreCase(type)) {
                    while (rs.next()) {
                        Integer label = rs.getInt("month_of_year");
                        Integer value = rs.getInt("total_revenue");
                        labelWithValue.put(label, value);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if ("MONTHLY".equalsIgnoreCase(type)) {
            List<Integer> dayOfMonth = new ArrayList<>();
            for (int i = 1; i <= LocalDate.now().getDayOfMonth(); i++) {
                dayOfMonth.add(i);
            }
            Set<Integer> labels = labelWithValue.keySet();
            for (Integer day : dayOfMonth) {
                if (!labels.contains(day)) {
                    labelWithValue.put(day, 0);
                }
            }

        } else if ("YEARLY".equalsIgnoreCase(type)) {
            List<Integer> monthOfYear = new ArrayList<>();
            for (int i = 1; i <= 12; i++) {
                monthOfYear.add(i);
            }
            Set<Integer> labels = labelWithValue.keySet();
            for (Integer month : monthOfYear) {
                if (!labels.contains(month)) {
                    labelWithValue.put(month, 0);
                }
            }
        }

        return labelWithValue;
    }


    private String buildMonthlySalesQuery() {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("    DAY(o.created_at) AS day_of_month,");
        sql.append("    SUM(oi.order_quantity) AS total_sold");
        sql.append("FROM ");
        sql.append("    orders o");
        sql.append("JOIN ");
        sql.append("    order_items oi ON o.order_id = oi.order_id");
        sql.append("WHERE ");
        sql.append("    MONTH(o.created_at) = MONTH(CURRENT_DATE())");
        sql.append("    AND YEAR(o.created_at) = YEAR(CURRENT_DATE())");
        sql.append("    AND o.order_status = 'DELIVERED'");
        sql.append("GROUP BY ");
        sql.append("    DAY(o.created_at)");
        sql.append("ORDER BY ");
        sql.append("    DAY(o.created_at);");

        return sql.toString();
    }

    private String buildYearlySalesQuery() {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("    MONTH(o.created_at) AS month_of_year,");
        sql.append("    SUM(oi.order_quantity) AS total_sold");
        sql.append("FROM ");
        sql.append("    orders o");
        sql.append("JOIN ");
        sql.append("    order_items oi ON o.order_id = oi.order_id");
        sql.append("WHERE ");
        sql.append("    YEAR(o.created_at) = YEAR(CURRENT_DATE())");
        sql.append("    AND o.order_status = 'DELIVERED'");
        sql.append("GROUP BY ");
        sql.append("    MONTH(o.created_at)");
        sql.append("ORDER BY ");
        sql.append("    MONTH(o.created_at);");

        return sql.toString();
    }

    private String buildMonthlyRevenueQuery() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("    DAY(o.created_at) AS day_of_month, ");
        sql.append("    SUM(oi.order_quantity * oi.order_price) AS total_revenue ");
        sql.append("FROM ");
        sql.append("    orders o ");
        sql.append("JOIN ");
        sql.append("    order_items oi ON o.order_id = oi.order_id ");
        sql.append("WHERE ");
        sql.append("    MONTH(o.created_at) = MONTH(CURRENT_DATE()) ");
        sql.append("    AND YEAR(o.created_at) = YEAR(CURRENT_DATE()) ");
        sql.append("    AND o.order_status = 'DELIVERED' ");
        sql.append("GROUP BY ");
        sql.append("    DAY(o.created_at) ");
        sql.append("ORDER BY ");
        sql.append("    DAY(o.created_at);");

        return sql.toString();
    }

    private String buildYearlyRevenueQuery() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("    MONTH(o.created_at) AS month_of_year, ");
        sql.append("    SUM(oi.order_quantity * oi.order_price) AS total_revenue ");
        sql.append("FROM ");
        sql.append("    orders o ");
        sql.append("JOIN ");
        sql.append("    order_items oi ON o.order_id = oi.order_id ");
        sql.append("WHERE ");
        sql.append("    YEAR(o.created_at) = YEAR(CURRENT_DATE()) ");
        sql.append("    AND o.order_status = 'DELIVERED' ");
        sql.append("GROUP BY ");
        sql.append("    MONTH(o.created_at) ");
        sql.append("ORDER BY ");
        sql.append("    MONTH(o.created_at);");

        return sql.toString();
    }

}