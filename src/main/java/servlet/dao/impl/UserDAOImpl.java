package servlet.dao.impl;

import servlet.dao.UserDAO;
import servlet.models.Role;
import servlet.models.User;
import servlet.utils.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import servlet.utils.PasswordUtils;

public class UserDAOImpl implements UserDAO {

    @Override
    public User findById(int userId) {
    	 String sql = "SELECT * FROM users WHERE user_id = ?";
         try (Connection conn = DataSourceUtil.getConnection();
              PreparedStatement ps = conn.prepareStatement(sql)) {
              
             ps.setInt(1, userId);
             try (ResultSet rs = ps.executeQuery()) {
                 if (rs.next()) {
                     return mapRowToUser(rs);
                 }
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return null;
    }


	@Override
	public boolean deleteById(int userId) {
		 String sql = "update users set user_isactive = ? WHERE user_id = ?";
	        try (Connection conn = DataSourceUtil.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            
	        	ps.setInt(1, 0);
	            ps.setInt(2, userId);
	            int rows = ps.executeUpdate();
	            return rows > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
        return false;
	}

	@Override
	public boolean updateLoginCount(int userId) {
		
		String sql = "Update users set login_count = ? where user_id = ?";
		User user = findById(userId);
		int loginCount = user.getLoginCount();
		
		try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
	              
			 ps.setInt(1, loginCount + 1);
             ps.setInt(2, userId);
             	      
             int row = ps.executeUpdate();
             return row > 0;
         } catch (SQLException e) {
             e.printStackTrace();
         }
		
		return false;
	}

	@Override
	public User checkLogin(String email, String password) {
	    String sql = "SELECT * FROM users inner join roles WHERE users.role_id = roles.role_id and user_email = ?";
	    try (Connection connection = DataSourceUtil.getConnection();
	         PreparedStatement ps = connection.prepareStatement(sql)) {

	        ps.setString(1, email);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                String hashedPassword = rs.getString("password");
	                if (PasswordUtils.checkPassword(password, hashedPassword)) {
	                	User user = mapRowToUser(rs);
	                	String roleName = rs.getString("role_name");
	                	user.getRole().setRoleName(roleName);
	                    return user;
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

    @Override
    public User signUp(String fullname, String email, String password) {
        if (existsByEmail(email)) {
            return null;
        }

        String hashedPassword = PasswordUtils.hashPassword(password);
        String sql = "INSERT INTO users (user_fullname, user_email, password, role_id, user_isactive, user_created_date, login_count) VALUES (?, ?, ?, ?, 1, NOW(), 0)";

        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, fullname);
            ps.setString(2, email);
            ps.setString(3, hashedPassword);
            ps.setInt(4, 2);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int newUserId = generatedKeys.getInt(1);
                        return findById(newUserId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAllPage(int page, int pageSize, String keyWord, String status, String dir, String orderBy, int roleId) {
        List<User> users = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM users WHERE 1 = 1 ");

        if(roleId != -1) {
            sql.append("AND role_id = ? ");
        }

        if (keyWord != null && !keyWord.trim().isEmpty()) {
            sql.append("AND user_fullname LIKE ? ");
        }

        if (status != null && !"all".equalsIgnoreCase(status)) {
            sql.append("AND user_isactive = ? ");
        }

        if("user_isactive".equalsIgnoreCase(orderBy)){
            sql.append("ORDER BY user_isactive ");
        }else {
            sql.append("ORDER BY user_fullname ");
        }

        if ("DESC".equalsIgnoreCase(dir)){
            sql.append("DESC ");
        }else {
            sql.append("ASC ");
        }

        sql.append("LIMIT ? OFFSET ?");

        try (
                Connection connection = DataSourceUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql.toString())
        ) {
            int index = 1;
            if(roleId != -1) {
                ps.setInt(index++, roleId);
            }
            if (keyWord != null && !keyWord.trim().isEmpty()) ps.setString(index++, "%" +keyWord.trim()+ "%");
            if (status != null && !"all".equalsIgnoreCase(status)) {
                int statusValue = "active".equals(status) ? 1 : 0;
                ps.setInt(index++, statusValue);
            }

            int offset = (page - 1) * pageSize;
            ps.setInt(index++, pageSize);
            ps.setInt(index, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = mapRowToUser(rs);
                    users.add(user);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public int countAllUsers(String keyWord, String status) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) FROM users WHERE role_id = 2 ");

        if (keyWord != null && !keyWord.trim().isEmpty()) {
            sql.append("AND user_fullname LIKE ? ");
        }

        if (status != null && !"all".equalsIgnoreCase(status)) {
            sql.append("AND user_isactive = ? ");
        }

        try (
                Connection connection = DataSourceUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql.toString())
        ) {
            int index = 1;

            if(keyWord != null && !keyWord.isEmpty()) ps.setString(index++, "%" + keyWord.trim() + "%");

            if (status != null && !"all".equalsIgnoreCase(status)) {
                int statusValue = "active".equals(status) ? 1 : 0;
                ps.setInt(index, statusValue);
            }

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next())
                    return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<User> findLatestRegisteredAccount() {
        String sql = "select * from users where user_isactive = 1 order by user_created_date desc limit 6";
        List<User> users = new ArrayList<>();

        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = mapRowToUser(rs);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean restoreById(int userId) {
        String sql = "update users set user_isactive = ? WHERE user_id = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, 1);
            ps.setInt(2, userId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getLoggedInUser(HttpSession session) {
        if(session.getAttribute("userId") != null) {
            int userId = (Integer) session.getAttribute("userId");
            User loggedInUser = findById(userId);
            return loggedInUser;
        } else {
            return null;
        }
    }

    @Override
    public Map<Integer, Integer> buildCustomerJoinChartData(String type) {
        Map<Integer, Integer> labelWithValue = new HashMap<>();
        String query = "";

        if ("MONTHLY".equalsIgnoreCase(type)) {
            query = buildMonthlyCustomerJoinQuery();
        } else if ("YEARLY".equalsIgnoreCase(type)) {
            query = buildYearlyCustomerJoinQuery();
        }

        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if ("MONTHLY".equalsIgnoreCase(type)) {
                while (rs.next()) {
                    Integer label = rs.getInt("day_of_month");
                    Integer value = rs.getInt("total_customers");
                    labelWithValue.put(label, value);
                }
            } else if ("YEARLY".equalsIgnoreCase(type)) {
                while (rs.next()) {
                    Integer label = rs.getInt("month_of_year");
                    Integer value = rs.getInt("total_customers");
                    labelWithValue.put(label, value);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if ("MONTHLY".equalsIgnoreCase(type)) {
            List<Integer> days = new ArrayList<>();
            for (int i = 1; i <= LocalDate.now().getDayOfMonth(); i++) {
                days.add(i);
            }
            for (Integer day : days) {
                labelWithValue.putIfAbsent(day, 0);
            }

        } else if ("YEARLY".equalsIgnoreCase(type)) {
            for (int i = 1; i <= 12; i++) {
                labelWithValue.putIfAbsent(i, 0);
            }
        }

        return labelWithValue;
    }

    @Override
    public Role findRoleById(int roleId) {
        Role role = null;
        String sql = "SELECT role_id, role_name FROM roles WHERE role_id = ?";

        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, roleId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    role = new Role();
                    role.setRoleId(rs.getInt("role_id"));
                    role.setRoleName(rs.getString("role_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }

    @Override
    public boolean updateUserProfile(User user) {
        String sql = "UPDATE users SET user_fullname=?, gender=?, user_address=?, user_phone_number=?, user_email=? WHERE user_id=?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getFullname());
            ps.setString(2, user.getGender());
            ps.setString(3, user.getAddress());
            ps.setString(4, user.getPhoneNumber());
            ps.setString(5, user.getEmail());
            ps.setInt(6, user.getUserId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT 1 FROM users WHERE user_email = ? LIMIT 1";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void updateAvatar(int userId, String avatar) {
        String sql = "UPDATE users SET avatar = ? WHERE user_id = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, avatar);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProfile(User currentUser) {
        String sql = "UPDATE users SET user_fullname = ?, gender = ?, user_phone_number = ?, user_address = ?, user_modified_date = NOW() WHERE user_id = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, currentUser.getFullname());
            ps.setString(2, currentUser.getGender());
            ps.setString(3, currentUser.getPhoneNumber());
            ps.setString(4, currentUser.getAddress());
            ps.setInt(5, currentUser.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRoleUser(int userId, int roleId) {
        String sql = "UPDATE users SET role_id = ? WHERE user_id = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, roleId); // Gán role_id mặc định là 3 (Mega Admin)
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String buildMonthlyCustomerJoinQuery() {
        return "         SELECT\n" +
                "            DAY(create_date) AS day_of_month,\n" +
                "            COUNT(user_id) AS total_customers\n" +
                "        FROM users\n" +
                "        WHERE MONTH(create_date) = MONTH(CURRENT_DATE())\n" +
                "          AND YEAR(create_date) = YEAR(CURRENT_DATE())\n" +
                "        GROUP BY DAY(create_date)\n" +
                "        ORDER BY day_of_month;";
    }

    private String buildYearlyCustomerJoinQuery() {
        return "         SELECT\n" +
                "            MONTH(create_date) AS month_of_year,\n" +
                "            COUNT(user_id) AS total_customers\n" +
                "        FROM users\n" +
                "        WHERE YEAR(create_date) = YEAR(CURRENT_DATE())\n" +
                "        GROUP BY MONTH(create_date)\n" +
                "        ORDER BY month_of_year";
    }

    private User mapRowToUser(ResultSet rs) throws SQLException{
		User user = new User();
		user.setUserId(rs.getInt("user_id"));
        user.setPassword(rs.getString("password"));
        user.setGender(rs.getString("gender"));
        user.setFullname(rs.getString("user_fullname"));
        user.setPhoneNumber(rs.getString("user_phone_number"));
        user.setEmail(rs.getString("user_email"));
        user.setCreateDate(rs.getTimestamp("user_created_date"));
        user.setModifiedDate(rs.getTimestamp("user_modified_date"));
        int userActive = rs.getInt("user_isactive");
        user.setActive(userActive == 1 ? true : false);
        user.setAddress(rs.getString("user_address"));
        user.setLoginCount(rs.getInt("login_count"));
        user.setAvatar(rs.getString("avatar"));
        
        int roleId = rs.getInt("role_id");
        Role role = findRoleById(roleId);
        user.setRole(role);
        
        return user;
	}

}
