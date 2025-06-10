package servlet.dao;

import servlet.models.OrderItem;

import java.util.List;

public interface OrderItemsDAO {
    boolean saveOrderItems(List<OrderItem> items);
    boolean editOrderItem(OrderItem orderItem, Integer id);
    OrderItem getOrderItemByOrderId(Integer orderId);
    List<OrderItem> getAllOrderItemsByOrderId(Integer orderId);
}
