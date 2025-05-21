package servlet.dao.impl;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import servlet.dao.BrandDAO;
import servlet.dao.CategoryDAO;
import servlet.dao.ProductDAO;
import servlet.models.Brand;
import servlet.models.Category;
import servlet.models.Product;
import servlet.utils.DataSourceUtil;

@Data
public class ProductDAOImpl implements ProductDAO {

	private BrandDAO brandDAO = new BrandDAOImpl();
	private CategoryDAO categoryDAO = new CategoryDAOImpl();
	private Connection conn = null;
	private PreparedStatement stmt = null;

	@Override
	public Product findById(int id) {
		String sql = "SELECT * FROM products WHERE product_id = ?";
        try (
            Connection conn = DataSourceUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
            	return mapRowToProduct(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}

	

	@Override
	public boolean saveProduct(Product product) {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO products (");
		sql.append("product_name, product_total, product_code, product_price, ");
		sql.append("product_discount_price, product_description, product_visited, ");
		sql.append("product_image_url, product_size, product_enable, product_material, ");
		sql.append("brand_id, category_id, created_at, updated_at");
		sql.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		try {
			conn = DataSourceUtil.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql.toString());

			int index = 1;
			stmt.setString(index++, product.getProductName());
			stmt.setInt(index++, product.getProductTotal());
			stmt.setString(index++, product.getProductCode());
			stmt.setDouble(index++, product.getProductPrice());
			stmt.setDouble(index++, product.getProductDiscountPrice());
			stmt.setString(index++, product.getProductDescription());
			stmt.setInt(index++, 0);
			stmt.setString(index++, product.getProductImageUrl());
			stmt.setString(index++, product.getProductSize());
			stmt.setInt(index++, product.isProductEnable() ? 1 : 0);
			stmt.setString(index++, product.getProductMaterial());
			stmt.setInt(index++, 1);
			stmt.setInt(index++, 1);

			//created date
			LocalDate now = LocalDate.now();
			stmt.setDate(index++, Date.valueOf(now));

			//modified date
			stmt.setDate(index++, Date.valueOf(now));

			int rows = stmt.executeUpdate();

			if(rows < 0) {
				return false;
			}
			conn.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			if(conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					throw new RuntimeException(ex);
				}
			}
        } finally {
			try {
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
		return false;
	}

	@Override
	public boolean editProduct(int productId, Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProduct(int productId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Product> findAll(int pageSize, int pageNumber, String orderBy, String sortBy) {
		List<Product> products = new ArrayList<>();

		List<String> allowedSortColumns = List.of("product_name", "product_price", "product_discount_price", "product_id");
		if (!allowedSortColumns.contains(orderBy)) {
			orderBy = "product_id";
		}

		sortBy = sortBy.equalsIgnoreCase("DESC") ? "DESC" : "ASC";

		String sql = "SELECT * FROM products ORDER BY " + orderBy + " " + sortBy + " LIMIT ? OFFSET ?";

		try (Connection conn = DataSourceUtil.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, pageSize);
			stmt.setInt(2, (pageNumber - 1) * pageSize);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductPrice(rs.getDouble("product_price"));
				product.setProductDiscountPrice(rs.getDouble("product_discount_price"));
				product.setProductEnable(rs.getBoolean("product_enable"));
				product.setProductDescription(rs.getString("product_description"));
				product.setProductSize(rs.getString("product_size"));
				product.setProductMaterial(rs.getString("product_material"));
				product.setProductImageUrl(rs.getString("product_image_url"));
				product.setProductCode(rs.getString("product_code"));
				product.setProductVisited(rs.getInt("product_visited"));
				product.setCreatedAt(rs.getDate("created_at").toLocalDate());
				product.setUpdatedAt(rs.getDate("updated_at").toLocalDate());

				int brandId = rs.getInt("brand_id");
				product.setBrand(brandDAO.findById(brandId));

				int categoryId = rs.getInt("category_id");
				product.setCategory(categoryDAO.findById(categoryId));

				products.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}


	private Product mapRowToProduct(ResultSet rs) throws SQLException {
		Product product = new Product();

		product.setProductId(rs.getInt("product_id"));
		product.setProductName(rs.getString("product_name"));
		product.setProductTotal(rs.getInt("product_total"));
		product.setProductCode(rs.getString("product_code"));
		product.setProductPrice(rs.getFloat("product_price"));
		product.setProductDiscountPrice(rs.getFloat("product_discount_price"));
		product.setProductDescription(rs.getString("product_description"));
		product.setProductVisited(rs.getInt("product_visited"));
		product.setProductImageUrl(rs.getString("product_image_url"));
		product.setProductSize(rs.getString("product_size"));
		product.setProductMaterial(rs.getString("product_material"));
		product.setProductEnable(rs.getInt("product_enable") == 1);

		LocalDate createdAt = rs.getDate("created_at").toLocalDate();
		product.setCreatedAt(createdAt);
		LocalDate modifiedAt = rs.getDate("updated_at").toLocalDate();
		product.setUpdatedAt(modifiedAt);
		int brandId = rs.getInt("brand_id");

		Brand brandFromDb = brandDAO.findById(brandId);
		if(brandFromDb == null) {
			Brand emptyBrand = new Brand();
			product.setBrand(emptyBrand);
		} else
			product.setBrand(brandFromDb);

		int categoryId = rs.getInt("category_id");
		Category categoryFromDb = categoryDAO.findById(categoryId);
		if(categoryFromDb == null) {
			Category emptyCategory = new Category();
			product.setCategory(emptyCategory);
		} else
			product.setCategory(categoryFromDb);

		return product;
	}

}
