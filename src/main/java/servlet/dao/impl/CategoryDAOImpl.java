package servlet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	public List<Category> findAllActiveCategories(int pageSize, int pageNumber, String orderBy, String sortBy) {
		List<Category> categories = new ArrayList<>();

		List<String> allowedSortColumns = List.of("keyword", "isActive");
		if (!allowedSortColumns.contains(orderBy)) {
			orderBy = "category_id";
		}

		sortBy = sortBy.equalsIgnoreCase("DESC") ? "DESC" : "ASC";

		String sql = "SELECT * FROM categories WHERE is_active > 0 ORDER BY " + orderBy + " " + sortBy + " LIMIT ? OFFSET ?";

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
	public List<Category> findAll(int pageSize, int pageNumber, String orderBy, String sortBy, String keyWord, int isActive) {
		List<Category> categories = new ArrayList<>();

		List<String> allowedSortColumns = List.of("category_name", "category_id", "is_active");
		if (!allowedSortColumns.contains(orderBy)) {
			orderBy = "category_id";
		}

		sortBy = sortBy.equalsIgnoreCase("DESC") ? "DESC" : "ASC";
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM categories ");
		if((keyWord != null && !keyWord.trim().isEmpty()) || isActive >= 0) {
			sql.append(" WHERE ");
		}
		if(keyWord != null && !keyWord.trim().isEmpty()) {
			sql.append(" (category_name LIKE ? OR category_description LIKE ?) ");
		}
		if (keyWord != null && !keyWord.trim().isEmpty() && isActive >=0) {
			sql.append(" AND ");
		}
		if(isActive >= 0) {
			sql.append(" (is_active = ?) ");
		}
		sql.append("ORDER BY " + orderBy + " "  + sortBy  + " " + "LIMIT ? OFFSET ?");

		try (Connection conn = DataSourceUtil.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
			int index = 1;
			if(keyWord != null && !keyWord.trim().isEmpty()) {
				stmt.setString(index++, "%"+keyWord+"%");
				stmt.setString(index++, "%"+keyWord+"%");
			}
			if(isActive >= 0) {
				stmt.setInt(index++, isActive);
			}
			stmt.setInt(index++, pageSize);
			stmt.setInt(index++, (pageNumber - 1) * pageSize);
			System.out.println(sql.toString());
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

	@Override
	public int categoryCounter() {
		String sql = "SELECT COUNT(*) as total FROM categories";
		int num = 0;
		try (
				Connection conn = DataSourceUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)
		) {
			ResultSet rs = stmt.executeQuery();
			if (rs.next()){
				num = rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
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
