package servlet.dao.impl;

import servlet.dao.UserDAO;
import servlet.models.Roles;
import servlet.models.User;
import servlet.utils.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import org.mindrot.jbcrypt.BCrypt;

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
    public List<User> findByName(String name, int page, int pageSize) {
        String sql = "SELECT * FROM users WHERE user_fullname LIKE ? LIMIT ? OFFSET ?";
        List<User> users = new ArrayList<>();

        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            int offset = (page - 1) * pageSize;

            ps.setString(1, "%" + name + "%");
            ps.setInt(2, pageSize);
            ps.setInt(3, offset);

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
    public List<User> findByStatus(String status, int page, int pageSize) {
        List<User> users = new ArrayList<>();
        String sql;

        boolean isAll = "all".equals(status);
        if (isAll) {
            sql = "SELECT * FROM users WHERE role_id = ? LIMIT ? OFFSET ?";
        } else {
            sql = "SELECT * FROM users WHERE role_id = ? AND user_isactive = ? LIMIT ? OFFSET ?";
        }

        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            int offset = (page - 1) * pageSize;
            ps.setInt(1, 2); 

            if (isAll) {
                ps.setInt(2, pageSize);
                ps.setInt(3, offset);
            } else {
                int statusValue = "active".equals(status) ? 1 : 0;
                ps.setInt(2, statusValue);
                ps.setInt(3, pageSize);
                ps.setInt(4, offset);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    users.add(mapRowToUser(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
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
	                if (BCrypt.checkpw(password, hashedPassword)) {
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
	
	public List<User> findAllPage(int page, int pageSize) {
	    List<User> users = new ArrayList<>();
	    String sql = "SELECT * FROM users WHERE role_id = ? LIMIT ? OFFSET ?";
	    
	    try (
	        Connection connection = DataSourceUtil.getConnection();
	        PreparedStatement ps = connection.prepareStatement(sql)
	    ) {
	        int offset = (page - 1) * pageSize;
	        
	        ps.setInt(1, 2); 
	        ps.setInt(2, pageSize); 
	        ps.setInt(3, offset);   
	        
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
    public int countAllUsers() {
        String sql = "select count(*) from users";
        try (
                Connection connection = DataSourceUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
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
    public int countUserByName(String name) {
        String sql = "select count(*) from users where user_fullname like ?";
        try (
                Connection connection = DataSourceUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, "%" + name + "%");
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
    public int countUserByStatus(String status) {
        String sql = "select count(*) from users where user_isactive = ?";
        try (
                Connection connection = DataSourceUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            int isActive = status.equals("active") ? 1 : 0;
            ps.setInt(1, isActive);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next())
                    return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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
        
        int roleId = rs.getInt("role_id");      
        Roles role = new Roles();
        role.setRoleId(roleId);     
        user.setRole(role);
        
        return user;
	}

	


	
}
