package servlet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import servlet.dao.CategoryDAO;
import servlet.models.Category;
import servlet.utils.DataSourceUtil;

public class CategoryDAOImpl implements CategoryDAO {

	@Override
	public Category findById(int id) {
		String sql = "SELECT * FROM categories WHERE category_id = ?";
		try (
				Connection conn = DataSourceUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)
		) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return mapRowToCategory(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Category> findAll(int pageSize, int pageNumber, String orderBy, String sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addCategory(Category category) {
		String sql = "INSERT INTO categories (category_name, category_description) VALUES (?,?) ";
		try (Connection conn = DataSourceUtil.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, category.getCategoryName());
			ps.setString(2, category.getCategoryDescription());
			try {
				int row = ps.executeUpdate();
				return row > 0;
			}
		 	catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
	}

	@Override
	public boolean editCategory(int categoryId, Category category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCategory(int categoryId) {
		// TODO Auto-generated method stub
		return false;
	}


	private Category mapRowToCategory(ResultSet rs) throws SQLException {
		Category category = new Category();

		category.setCategoryId(rs.getInt("category_id"));
		category.setCategoryName(rs.getString("category_name"));
		category.setCategoryDescription(rs.getString("category_description"));
		category.setIsActive(rs.getInt("is_active"));

		return category;
	}

}
