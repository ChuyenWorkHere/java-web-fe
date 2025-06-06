package servlet.dao.impl;

import servlet.dao.OrdersReportDAO;
import servlet.models.Order;
import servlet.response.OrderResponse;
import servlet.utils.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}
