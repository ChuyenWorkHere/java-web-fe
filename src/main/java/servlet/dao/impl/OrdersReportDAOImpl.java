package servlet.dao.impl;

import servlet.dao.OrdersReportDAO;
import servlet.models.Order;
import servlet.response.OrderReportFeedback;
import servlet.response.OrderResponse;
import servlet.response.UserReportResponse;
import servlet.utils.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<OrderResponse> getDaylyOrderStatus(int year, int month) {
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

}