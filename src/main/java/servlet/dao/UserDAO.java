package servlet.dao;

import servlet.models.Role;
import servlet.models.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface UserDAO {

    //đếm số lần đăng nhập
    boolean updateLoginCount(int userId);

    // tìm bằng id
    User findById(int userId);

    //xoá bằng id
    boolean deleteById(int userId);

    User checkLogin(String email, String password);

    User signUp(String fullname, String email, String password);

    //lấy tất cả
    List<User> findAllPage(int page, int pageSize, String keyWord, String status, String dir, String orderBy);

    //đếm số lượng
    int countAllUsers(String keyWord, String status);

    // lấy 6 tài khoản mới tạo
    List<User> findLatestRegisteredAccount();

    //kích hoạt lại tài khoản
    boolean restoreById(int userId);

    User getLoggedInUser(HttpSession session);

    Map<Integer, Integer> buildCustomerJoinChartData(String type);

    Role findRoleById(int roleId);

    boolean updateUserProfile(User loggedInUser);

    boolean existsByEmail(String email);
}
