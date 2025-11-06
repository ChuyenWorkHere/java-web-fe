package servlet.dao.impl;

import servlet.dao.OrderItemsDAO;
import servlet.models.OrderItem;
import servlet.models.Product;
import servlet.utils.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderItemsDAOImpl implements OrderItemsDAO {


    @Override
    public boolean saveOrderItems(List<OrderItem> items) {
        String sql = "INSERT INTO order_items (order_quantity, order_price, order_id, product_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Bắt đầu transaction
            conn.setAutoCommit(false);

            for (OrderItem item : items) {
                ps.setInt(1, item.getOrderQuantity());
                ps.setDouble(2, item.getOrderPrice());
                ps.setInt(3, item.getOrder().getOrderId());
                ps.setInt(4, item.getProduct().getProductId());
                ps.addBatch();
            }

            int[] result = ps.executeBatch();
            conn.commit();

            for (int r : result) {
                if (r == Statement.EXECUTE_FAILED) {
                    return false;
                }
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                DataSourceUtil.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public boolean editOrderItem(OrderItem orderItem, Integer id) {
        return false;
    }
    

    @Override
    public List<OrderItem> getAllOrderItemsByOrderId(Integer orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT oi.order_item_id, oi.order_quantity, oi.order_price, " +
                     "p.product_id, p.product_name, p.product_image_url " +
                     "FROM order_items oi " +
                     "JOIN products p ON oi.product_id = p.product_id " +
                     "WHERE oi.order_id = ?";

        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("product_id"));
                    product.setProductName(rs.getString("product_name"));
                    product.setProductImageUrl(rs.getString("product_image_url"));

                    OrderItem item = new OrderItem();
                    item.setOrderItemId(rs.getInt("order_item_id"));
                    item.setOrderQuantity(rs.getInt("order_quantity"));
                    item.setOrderPrice(rs.getDouble("order_price"));
                    item.setProduct(product);

                    orderItems.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItems;
    }
}
