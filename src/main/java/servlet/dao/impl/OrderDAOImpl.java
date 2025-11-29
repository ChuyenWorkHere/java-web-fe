package servlet.dao.impl;

import servlet.constants.OrderStatus;
import servlet.constants.PaymentStatus;
import servlet.dao.OrderDAO;
import servlet.dao.UserDAO;
import servlet.models.*;
import servlet.utils.DataSourceUtil;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class OrderDAOImpl implements OrderDAO {


    private UserDAO userDAO = new UserDAOImpl();
    private OrderItemsDAOImpl orderItemsDAO = new OrderItemsDAOImpl();

    @Override
    public List<Order> getAllOrders(int pageNo, int pageSize, String priceRange, String orderStatus,
                                    String paymentStatus, String paymentMethod, String orderSort) {
        List<Order> orders = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o.order_id, o.created_at, o.total_price, o.order_status, o.payment_status, o.payment_method, ");
        sql.append("u.user_id, u.user_fullname ");
        sql.append("FROM orders o JOIN users u ON o.user_id = u.user_id ");

        // Xây dựng điều kiện WHERE động
        List<String> conditions = new ArrayList<>();

        if (orderStatus != null) conditions.add("o.order_status = ?");
        if (paymentStatus != null) conditions.add("o.payment_status = ?");
        if (paymentMethod != null) conditions.add("o.payment_method = ?");

        if (!conditions.isEmpty()) {
            sql.append("WHERE ");
            sql.append(String.join(" AND ", conditions));
            sql.append(" ");
        }

        // Sắp xếp
        sql.append("ORDER BY ");
        if ("asc".equalsIgnoreCase(priceRange)) {
            sql.append("o.total_price ASC, ");
        } else if ("desc".equalsIgnoreCase(priceRange)) {
            sql.append("o.total_price DESC, ");
        }
        // Mặc định sắp xếp theo created_at
        sql.append("o.created_at ");
        sql.append("oldest".equalsIgnoreCase(orderSort) ? "ASC " : "DESC ");

        // Phân trang
        sql.append("LIMIT ? OFFSET ?");

        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {


            int index = 1;
            // Gán giá trị cho các điều kiện lọc
            if (orderStatus != null) ps.setString(index++, orderStatus);
            if (paymentStatus != null) ps.setString(index++, paymentStatus);
            if (paymentMethod != null) ps.setString(index++, paymentMethod);

            // Gán LIMIT và OFFSET
            int offset = (pageNo - 1) * pageSize;
            ps.setInt(index++, pageSize);
            ps.setInt(index, offset);

            // Thực thi truy vấn và lấy kết quả
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                   Order order = new Order();
                    order.setOrderId(rs.getInt("order_id"));
                    order.setCreatedAt(rs.getTimestamp("created_at"));
                    order.setTotalPrice(rs.getFloat("total_price"));
                    order.setOrderStatus(rs.getString("order_status"));
                    order.setPaymentStatus(rs.getString("payment_status"));
                    order.setPaymentMethod(rs.getString("payment_method"));

                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setFullname(rs.getString("user_fullname"));
                    order.setUser (user);

                    orders.add(order);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }



    @Override
    public int countAllOrders(String orderStatus, String paymentStatus, String paymentMethod) {
        StringBuilder sql = new StringBuilder();
        sql.append("select count(*) from orders");
        List<String> conditions = new ArrayList<>();
        if (orderStatus != null) conditions.add("o.order_status = ?");
        if (paymentStatus != null) conditions.add("o.payment_status = ?");
        if (paymentMethod != null) conditions.add("o.payment_method = ?");

        if (!conditions.isEmpty()) {
            sql.append("WHERE ");
            sql.append(String.join(" AND ", conditions));
            sql.append(" ");
        }
        try (
                Connection connection = DataSourceUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql.toString())
        ) {
            int index = 1;
            if (orderStatus != null) ps.setString(index++, orderStatus);
            if (paymentStatus != null) ps.setString(index++, paymentStatus);
            if (paymentMethod != null) ps.setString(index, paymentMethod);

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
        Order order = null;
        List<OrderItem> orderItems = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o.order_id, o.total_price, o.order_status, o.payment_status, o.payment_method, o.created_at, o.order_note, ");
        sql.append("sa.fullname, sa.phone_number, sa.address, sa.email, ");
        sql.append("u.user_id, u.user_fullname, u.user_phone_number, u.user_email, u.gender, u.user_isactive, u.user_created_date, u.user_modified_date, u.user_address, ");
        sql.append("oi.order_quantity, oi.order_price, oi.product_id, ");
        sql.append("p.product_name, p.product_image_url ");
        sql.append("FROM orders o ");
        sql.append("LEFT JOIN users u ON o.user_id = u.user_id ");
        sql.append("LEFT JOIN shipping_address sa ON sa.order_id = o.order_id ");
        sql.append("LEFT JOIN order_items oi ON oi.order_id = o.order_id ");
        sql.append("LEFT JOIN products p ON p.product_id = oi.product_id ");
        sql.append("WHERE o.order_id = ?");

       try(Connection conn  = DataSourceUtil.getConnection();
           PreparedStatement ps = conn.prepareStatement(sql.toString())){

           ps.setInt(1, orderId);

           try(ResultSet rs = ps.executeQuery()) {
               while (rs.next()){
                   if (order == null) {
                       order = new Order();
                       order.setOrderId(rs.getInt("order_id"));
                       order.setTotalPrice(rs.getFloat("total_price"));
                       order.setOrderStatus(rs.getString("order_status"));
                       order.setPaymentStatus(rs.getString("payment_status"));
                       order.setPaymentMethod(rs.getString("payment_method"));
                       order.setCreatedAt(rs.getTimestamp("created_at"));
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
                       user.setActive(userActive == 1);
                       user.setAddress(rs.getString("user_address"));
                       order.setUser(user);
                   }

                   if (rs.getInt("product_id") != 0) {
                       Product product = new Product();
                       product.setProductId(rs.getInt("product_id"));
                       product.setProductName(rs.getString("product_name"));
                       product.setProductImageUrl(rs.getString("product_image_url"));

                       OrderItem orderItem = new OrderItem();
                       orderItem.setOrderQuantity(rs.getInt("order_quantity"));
                       orderItem.setOrderPrice(rs.getFloat("order_price"));
                       orderItem.setProduct(product);
                       orderItems.add(orderItem);
                   }
               }
               if (order != null) {
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

        String deleteOrderItemsSql = "DELETE FROM order_items WHERE order_id = ?";
        String deleteOrderSql = "DELETE FROM orders WHERE order_id = ?";

        try (Connection conn = DataSourceUtil.getConnection()) {
            conn.setAutoCommit(false);
            try (
                    PreparedStatement psItems = conn.prepareStatement(deleteOrderItemsSql);
                    PreparedStatement psOrder = conn.prepareStatement(deleteOrderSql)
            ) {
                psItems.setInt(1, orderId);
                psItems.executeUpdate();

                psOrder.setInt(1, orderId);
                int affectedRows = psOrder.executeUpdate();

                if (affectedRows > 0) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                }
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public List<Order> getAllOrdersByDate(String startDate, String endDate) {
        List<Order> orders = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o.order_id, o.created_at, o.total_price, o.order_status, o.payment_status, o.payment_method, ");
        sql.append("u.user_id, u.user_fullname ");
        sql.append("FROM orders o JOIN users u ON o.user_id = u.user_id ");
        sql.append("WHERE o.created_at BETWEEN ? AND ? ");
        sql.append("ORDER BY o.created_at DESC");

        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())){

            ps.setString(1, startDate);
            ps.setString(2, endDate);

            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("order_id"));
                    order.setCreatedAt(rs.getTimestamp("created_at"));
                    order.setTotalPrice(rs.getFloat("total_price"));
                    order.setOrderStatus(rs.getString("order_status"));
                    order.setPaymentStatus(rs.getString("payment_status"));
                    order.setPaymentMethod(rs.getString("payment_method"));

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

    //Đã sửa
    @Override
    public List<Order> getOrdersByStatus(String status) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.* FROM orders o WHERE o.order_status = ?";
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
                    order.setPaymentStatus(rs.getString("payment_status"));
                    order.setPaymentMethod(rs.getString("payment_method"));
                    order.setOrderNote(rs.getString("order_note"));


                    int userId  = rs.getInt("user_id");
                    User user = userDAO.findById(userId);

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
    public Order findOrderByOrderId(int orderId) {
        Order order = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o.order_id, o.total_price, o.order_status, o.payment_status, o.payment_method, o.created_at, o.order_note, ");
        sql.append("sa.fullname, sa.phone_number, sa.address, sa.email, ");
        sql.append("u.user_id, u.user_fullname, u.user_phone_number, u.user_email, u.gender, u.user_isactive, u.user_created_date, u.user_modified_date, u.user_address ");
        sql.append("FROM orders o ");
        sql.append("LEFT JOIN users u ON o.user_id = u.user_id ");
        sql.append("LEFT JOIN shipping_address sa ON sa.order_id = o.order_id ");
        sql.append("WHERE o.order_id = ?");

        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    order = new Order();
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
                    user.setActive(userActive == 1);
                    user.setAddress(rs.getString("user_address"));
                    order.setUser(user);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> findAllByUser(int userId) {
        List<Order> orders = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o.order_id, o.total_price, o.order_status, o.payment_status, o.payment_method, o.created_at, o.order_note, ");
        sql.append("sa.fullname, sa.phone_number, sa.address, sa.email ");
        sql.append("FROM orders o ");
        sql.append("LEFT JOIN shipping_address sa ON sa.order_id = o.order_id ");
        sql.append("WHERE o.user_id = ?");

        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
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

                    List<OrderItem> orderItems = orderItemsDAO.getAllOrderItemsByOrderId(order.getOrderId());
                    order.setOrderItems(orderItems);

                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public boolean updateOrderStatus(int orderId, OrderStatus status) {
        String sql = "UPDATE orders SET order_status = ? WHERE order_id = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status.toString());
            ps.setInt(2, orderId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatePaymentStatus(int orderId, PaymentStatus status) {
        String sql = "UPDATE orders SET payment_status = ? WHERE order_id = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status.toString());
            ps.setInt(2, orderId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<Map<String, Integer>> orderStatusCount() {
        List<Map<String, Integer>> result = new ArrayList<>();

        String sql = "SELECT order_status, COUNT(*) AS total FROM orders GROUP BY order_status";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Integer> row = new HashMap<>();
                    row.put(rs.getString("order_status"), rs.getInt("total"));
                    result.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    @Override
    public Order saveOrder(Order order) {
        String sql = "INSERT INTO orders (total_price, order_status, payment_status, payment_method, created_at, order_note, user_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDouble(1, order.getTotalPrice());
            ps.setString(2, order.getOrderStatus());
            ps.setString(3, order.getPaymentStatus());
            ps.setString(4, order.getPaymentMethod());
            ps.setDate(5, new java.sql.Date(order.getCreatedAt().getTime()));
            ps.setString(6, order.getOrderNote());
            ps.setInt(7, order.getUser().getUserId());

            try {
                int row = ps.executeUpdate();

                if (row > 0) {
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            int generatedId = rs.getInt(1);
                            order.setOrderId(generatedId);
                            return order;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}