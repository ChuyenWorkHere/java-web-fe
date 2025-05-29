package servlet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import servlet.dao.CategoryDAO;
import servlet.models.Category;
import servlet.models.Product;
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
		List<Category> categories = new ArrayList<>();

		List<String> allowedSortColumns = List.of("keyword", "isActive");
		if (!allowedSortColumns.contains(orderBy)) {
			orderBy = "category_id";
		}

		sortBy = sortBy.equalsIgnoreCase("DESC") ? "DESC" : "ASC";

		String sql = "SELECT * FROM categories ORDER BY " + orderBy + " " + sortBy + " LIMIT ? OFFSET ?";

		try (Connection conn = DataSourceUtil.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, pageSize);
			stmt.setInt(2, (pageNumber - 1) * pageSize);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setCategoryId(rs.getInt("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				category.setCategoryDescription(rs.getString("category_description"));
				category.setIsActive(rs.getInt("is_active"));

				categories.add(category);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categories;
	}

	@Override
	public boolean addCategory(Category category) {
		String sql = "INSERT INTO categories (category_name, category_description, is_active) VALUES (?,?,?) ";
		try (Connection conn = DataSourceUtil.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, category.getCategoryName());
			ps.setString(2, category.getCategoryDescription());
			ps.setInt(3, category.getIsActive());
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
		String sql = "UPDATE categories SET category_name = ?, category_description = ?, is_active = ? WHERE category_id = ?";

		try (
				Connection conn = DataSourceUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)
		) {
			stmt.setString(1, category.getCategoryName());
			stmt.setString(2, category.getCategoryDescription());
			stmt.setInt(3, category.getIsActive());
			stmt.setInt(4, categoryId);

			int rowsUpdated = stmt.executeUpdate();
			return rowsUpdated > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteCategory(int categoryId) {
		String sql = "DELETE FROM categories WHERE category_id = ?";
		try (
				Connection conn = DataSourceUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)
		) {
			stmt.setInt(1, categoryId);
			int rows = stmt.executeUpdate();
			return rows > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
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
