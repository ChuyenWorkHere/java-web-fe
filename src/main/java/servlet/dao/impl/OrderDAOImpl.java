package servlet.dao.impl;

import servlet.dao.OrderDAO;
import servlet.models.*;
import servlet.utils.DataSourceUtil;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public List<Order> getAllOrders(int pageNo, int pageSize) {
        List<Order> orders = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o.order_id, o.created_at, o.total_price, o.order_status, ");
        sql.append("u.user_id, u.user_fullname ");
        sql.append("FROM orders o JOIN users u ON o.user_id = u.user_id ORDER BY o.created_at DESC ");
        sql.append(" LIMIT ? OFFSET ?");

        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())){

            int offset = (pageNo - 1) * pageSize;
            ps.setInt(1, pageSize);
            ps.setInt(2, offset);

             try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("order_id"));
                    order.setCreatedAt(rs.getTimestamp("created_at"));
                    order.setTotalPrice(rs.getFloat("total_price"));
                    order.setOrderStatus(rs.getString("order_status"));

                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setFullname(rs.getString("user_fullname"));
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
    public int countAllOrders() {
        String sql = "select count(*) from orders";
        try (
                Connection connection = DataSourceUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next())
                    return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Order getOrderDetailByOrderId(int orderId) {
        Order order = new Order();

       StringBuilder sql = new StringBuilder();
        sql.append("SELECT o.order_id, o.total_price, o.order_status, o.payment_status, o.payment_method, o.created_at, o.order_note, ");
        sql.append("sa.fullname, sa.phone_number, sa.address, sa.email, ");
        sql.append("u.user_id, u.user_fullname, u.user_phone_number, u.user_email, u.gender, u.user_isactive, u.user_created_date, u.user_modified_date, u.user_address, ");
        sql.append("oi.order_quantity, oi.order_price, oi.product_id, ");
        sql.append("p.product_name, p.product_image_url ");
        sql.append("FROM orders o ");
        sql.append("LEFT JOIN users u ON o.user_id = u.user_id ");
        sql.append("LEFT JOIN shipping_address sa ON sa.order_id = o.order_id AND sa.user_id = u.user_id ");
        sql.append("LEFT JOIN order_items oi ON oi.order_id = o.order_id ");
        sql.append("LEFT JOIN products p ON p.product_id = oi.product_id ");
        sql.append("WHERE o.order_id = ?");

       try(Connection conn  = DataSourceUtil.getConnection();
           PreparedStatement ps = conn.prepareStatement(sql.toString())){

           ps.setInt(1, orderId);

           try(ResultSet rs = ps.executeQuery()) {
               while (rs.next()){
                   if (order.getOrderId() == 0){
                       order.setOrderId(rs.getInt("order_id"));
                       order.setTotalPrice(rs.getFloat("total_price"));
                       order.setOrderStatus(rs.getString("order_status"));
                       order.setPaymentStatus(rs.getString("payment_status"));
                       order.setPaymentMethod(rs.getString("payment_method"));
                       order.setCreatedAt(rs.getDate("created_at"));
                       order.setOrderNote(rs.getString("order_note"));

                       ShippingAddress shippingAddress = new ShippingAddress();
                       shippingAddress.setFullname(rs.getString("fullname"));
                       shippingAddress.setPhoneNumber(rs.getString("phone_number"));
                       shippingAddress.setEmail(rs.getString("email"));
                       shippingAddress.setAddress(rs.getString("address"));
                       shippingAddress.setOrder(order);
                       order.setShippingAddress(shippingAddress);

                       User user = new User();
                       user.setUserId(rs.getInt("user_id"));
                       user.setFullname(rs.getString("user_fullname"));
                       user.setGender(rs.getString("gender"));
                       user.setPhoneNumber(rs.getString("user_phone_number"));
                       user.setEmail(rs.getString("user_email"));
                       user.setCreateDate(rs.getTimestamp("user_created_date"));
                       user.setModifiedDate(rs.getTimestamp("user_modified_date"));
                       int userActive = rs.getInt("user_isactive");
                       user.setActive(userActive == 1 ? true : false);
                       user.setAddress(rs.getString("user_address"));
                       order.setUser(user);
                   }


                   Product product = new Product();
                   product.setProductId(rs.getInt("product_id"));
                   product.setProductName(rs.getString("product_name"));
                   product.setProductImageUrl(rs.getString("product_image_url"));

                   OrderItem orderItem = new OrderItem();
                   orderItem.setOrderQuantity(rs.getInt("order_quantity"));
                   orderItem.setOrderPrice(rs.getFloat("order_price"));
                   orderItem.setProduct(product);

                   List<OrderItem> orderItems = new ArrayList<>();
                   orderItems.add(orderItem);
                   order.setOrderItems(orderItems);

               }
           }

       }catch (SQLException e){
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

    @Override
    public List<Map<String, Integer>> orderStatusCount() {
        List<Map<String, Integer>> result = new ArrayList<>();

        String sql = "select order_status, count(*) as total from orders group by order_status";
        try(Connection conn = DataSourceUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    Map<String, Integer> row = new HashMap<>();
                    row.put(rs.getString("order_status"), rs.getInt("total"));
                    result.add(row);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }


        return result;
    }


}