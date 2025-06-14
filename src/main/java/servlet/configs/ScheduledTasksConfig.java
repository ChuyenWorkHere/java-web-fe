package servlet.configs;

import servlet.dao.OrderDAO;
import servlet.dao.impl.OrderDAOImpl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledTasksConfig {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final OrderDAO orderDAO;

    public ScheduledTasksConfig() {
        this.orderDAO = new OrderDAOImpl();
        startDeleteOldOrdersTask();
    }

    private void startDeleteOldOrdersTask() {
        // Lên lịch chạy task xóa đơn hàng đã xóa trên 30 ngày hàng ngày
        scheduler.scheduleAtFixedRate(() -> {
            try {
                int deletedCount = orderDAO.deleteOldDeletedOrders();
                System.out.println("Đã xóa " + deletedCount + " đơn hàng trong thùng rác trên 30 ngày.");
            } catch (Exception e) {
                System.err.println("Lỗi khi xóa đơn hàng cũ:");
                e.printStackTrace();
            }
        }, 0, 24, TimeUnit.HOURS);
    }

    public void shutdown() {
        scheduler.shutdown();
        System.out.println(" Đã dừng scheduler xóa đơn hàng cũ.");
    }
}

