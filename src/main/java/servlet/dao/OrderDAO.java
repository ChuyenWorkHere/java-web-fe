package servlet.dao;

import servlet.models.Order;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderDAO {

    // lấy tất cả đơn hàng theo phân trang
    List<Order> getAllOrders(int pageNo, int pageSize);

    //Đếm số lượng đơn hàng
    public int countAllOrders();

    Order getOrderDetailByOrderId(int orderId);

    // 3.1.3: Xóa đơn hàng
    boolean deleteOrder(int orderId);

    // 3.1.4: Cập nhật trạng thái đơn hàng
    boolean updateOrderStatus(int orderId, String newStatus);

    // Lọc đơn hàng theo khoảng giá
    List<Order> getOrdersByPriceRange(double minPrice, double maxPrice);

    // Lọc đơn hàng theo trạng thái
    List<Order> getOrdersByStatus(String status);

    // Lọc đơn hàng theo hình thức thanh toán
    List<Order> getOrdersByPaymentMethod(String paymentMethod);

    // Lọc đơn hàng theo khoảng thời gian (ví dụ đơn hàng mới, cũ)
    List<Order> getOrdersByDateRange(Date fromDate, Date toDate);

    // Tìm đơn hàng theo user (để xem đơn của khách cụ thể)
    List<Order> getOrdersByUserId(int userId);

    List<Map<String, Integer>> orderStatusCount();
}
