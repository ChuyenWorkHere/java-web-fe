package servlet.dao.impl;

import servlet.dao.OrderItemsDAO;
import servlet.models.OrderItem;
import servlet.utils.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
    public OrderItem getOrderItemByOrderId(Integer orderId) {
        return null;
    }

    @Override
    public List<OrderItem> getAllOrderItemsByOrderId(Integer orderId) {
        return List.of();
    }
}
