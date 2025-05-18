package servlet.dao.impl;

import servlet.dao.OrderDAO;
import servlet.models.Order;
import servlet.models.Product;
import servlet.models.User;
import servlet.utils.DataSourceUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.order_id, o.created_at, o.total_price, o.order_status, o.payment_status, o.payment_method, o.order_note, " +
                "u.user_id, u.username, u.email " +
                "FROM orders o JOIN users u ON o.user_id = u.user_id ORDER BY o.created_at DESC";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setCreatedAt(rs.getTimestamp("created_at"));
                order.setTotalPrice(rs.getFloat("total_price"));
                order.setOrderStatus(rs.getString("order_status"));
                order.setPaymentStatus(rs.getString("payment_status"));
                order.setPaymentMethod(rs.getString("payment_method"));
                order.setOrderNote(rs.getString("order_note"));

                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setEmail(rs.getString("email"));
                order.setUser(user);

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order getOrderDetailById(int orderId) {
        Order order = null;
        String sql = "SELECT o.*, u.user_id, u.username, u.email FROM orders o JOIN users u ON o.user_id = u.user_id WHERE o.order_id = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    order = new Order();
                    order.setOrderId(rs.getInt("order_id"));
                    order.setCreatedAt(rs.getTimestamp("created_at"));
                    order.setTotalPrice(rs.getFloat("total_price"));
                    order.setOrderStatus(rs.getString("order_status"));
                    order.setPaymentStatus(rs.getString("payment_status"));
                    order.setPaymentMethod(rs.getString("payment_method"));
                    order.setOrderNote(rs.getString("order_note"));

                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));

                    user.setEmail(rs.getString("email"));
                    order.setUser(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public boolean deleteOrder(int orderId) {
        String sql = "DELETE FROM orders WHERE order_id = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateOrderStatus(int orderId, String newStatus) {
        String sql = "UPDATE orders SET order_status = ? WHERE order_id = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, orderId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Order> getOrdersByPriceRange(double minPrice, double maxPrice) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.*, u.user_id, u.username, u.email FROM orders o JOIN users u ON o.user_id = u.user_id WHERE o.total_price BETWEEN ? AND ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, minPrice);
            ps.setDouble(2, maxPrice);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("order_id"));
                    order.setCreatedAt(rs.getTimestamp("created_at"));
                    order.setTotalPrice(rs.getFloat("total_price"));
                    order.setOrderStatus(rs.getString("order_status"));

                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setEmail(rs.getString("email"));
                    order.setUser(user);

                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersByStatus(String status) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.*, u.user_id, u.username, u.email FROM orders o JOIN users u ON o.user_id = u.user_id WHERE o.order_status = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("order_id"));
                    order.setCreatedAt(rs.getTimestamp("created_at"));
                    order.setTotalPrice(rs.getFloat("total_price"));
                    order.setOrderStatus(rs.getString("order_status"));

                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setEmail(rs.getString("email"));
                    order.setUser(user);

                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersByPaymentMethod(String paymentMethod) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.*, u.user_id, u.username, u.email FROM orders o JOIN users u ON o.user_id = u.user_id WHERE o.payment_method = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, paymentMethod);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("order_id"));
                    order.setCreatedAt(rs.getTimestamp("created_at"));
                    order.setTotalPrice(rs.getFloat("total_price"));
                    order.setOrderStatus(rs.getString("order_status"));

                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setEmail(rs.getString("email"));
                    order.setUser(user);

                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersByDateRange(Date fromDate, Date toDate) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.*, u.user_id, u.username, u.email FROM orders o JOIN users u ON o.user_id = u.user_id WHERE o.created_at BETWEEN ? AND ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, new Timestamp(fromDate.getTime()));
            ps.setTimestamp(2, new Timestamp(toDate.getTime()));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("order_id"));
                    order.setCreatedAt(rs.getTimestamp("created_at"));
                    order.setTotalPrice(rs.getFloat("total_price"));
                    order.setOrderStatus(rs.getString("order_status"));

                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));

                    user.setEmail(rs.getString("email"));
                    order.setUser(user);

                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.*, u.user_id, u.username, u.email FROM orders o JOIN users u ON o.user_id = u.user_id WHERE u.user_id = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("order_id"));
                    order.setCreatedAt(rs.getTimestamp("created_at"));
                    order.setTotalPrice(rs.getFloat("total_price"));
                    order.setOrderStatus(rs.getString("order_status"));

                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));

                    user.setEmail(rs.getString("email"));
                    order.setUser(user);

                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}