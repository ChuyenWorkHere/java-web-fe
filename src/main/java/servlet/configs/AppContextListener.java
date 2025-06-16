package servlet.configs;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    private ScheduledTasksConfig scheduledTasksConfig;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        scheduledTasksConfig = new ScheduledTasksConfig();
        System.out.println("[AppContextListener] Đã khởi tạo cấu hình tác vụ định kỳ.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (scheduledTasksConfig != null) {
            scheduledTasksConfig.shutdown();
            System.out.println("[AppContextListener] Đã kết thúc cấu hình tác vụ định kỳ.");
        }
    }
}
