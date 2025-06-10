package servlet.dao;

import servlet.models.Order;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderDAO {

    // lấy tất cả đơn hàng theo phân trang
//    List<Order> getAllOrders(int pageNo, int pageSize);
    List<Order> getAllOrders(int pageNo, int pageSize, String priceRange, String orderStatus,
                             String paymentStatus, String paymentMethod, String orderSort);

    //Đếm số lượng đơn hàng
    public int countAllOrders(String orderStatus, String paymentStatus, String paymentMethod);

    //lấy chi tiết đơn hàng theo orderId
    Order getOrderDetailByOrderId(int orderId);

    // Xóa đơn hàng
    boolean deleteOrder(int orderId);

    //lấy tất cả đơn hàng theo khoảng thời gian
    List<Order> getAllOrdersByDate(String startDate, String endDate);

    //Cập nhật trạng thái đơn hàng
    boolean updateOrderStatus(int orderId);

    List<Map<String, Integer>> orderStatusCount();

    Order saveOrder(Order order);

    List<Order> getOrdersByStatus(String status);
}
