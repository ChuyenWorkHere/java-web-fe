package servlet.dao;

import servlet.models.User;

import java.util.List;

public interface UserDAO {
	boolean updateLoginCount(int userId);
    User findById(int userId);
    List<User> findByName(String name, int page, int pageSize);
    List<User> findByStatus(String status, int page, int pageSize);
    boolean deleteById(int userId);
    User checkLogin(String email, String password);
    List<User> findAllPage(int page, int pageSize);
}
