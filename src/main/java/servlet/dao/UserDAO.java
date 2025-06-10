package servlet.dao;

import servlet.models.User;

import java.util.List;

public interface UserDAO {

    //đếm số lần đăng nhập
    boolean updateLoginCount(int userId);

    // tìm bằng id
    User findById(int userId);

    //xoá bằng id
    boolean deleteById(int userId);

    User checkLogin(String email, String password);

    //lấy tất cả
    List<User> findAllPage(int page, int pageSize, String keyWord, String status, String dir, String orderBy);

    //đếm số lượng
    int countAllUsers(String keyWord, String status);

    // lấy 6 tài khoản mới tạo
    List<User> findLatestRegisteredAccount();
}
