package servlet.dao.impl;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import servlet.dao.BrandDAO;
import servlet.dao.CategoryDAO;
import servlet.dao.MaterialDAO;
import servlet.dao.ProductDAO;
import servlet.models.Brand;
import servlet.models.Material;
import servlet.models.Category;
import servlet.models.Product;
import servlet.utils.DataSourceUtil;

@Data
public class ProductDAOImpl implements ProductDAO {

	private BrandDAO brandDAO = new BrandDAOImpl();
	private CategoryDAO categoryDAO = new CategoryDAOImpl();
	private MaterialDAO materialDAO = new MaterialDAOImpl();
	private Connection conn = null;
	private PreparedStatement stmt = null;

	@Override
	public Product findById(int id) {
		String sql = "SELECT * FROM products WHERE product_id = ? AND product_enable = 1 ";
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
		sql.append("product_image_url, product_size, product_enable, ");
		sql.append("brand_id, category_id, created_at, updated_at, material_id");
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
			stmt.setInt(index++, product.getBrand().getBrandId());
			stmt.setInt(index++, product.getCategory().getCategoryId());

			//created date
			LocalDate now = LocalDate.now();
			stmt.setDate(index++, Date.valueOf(now));

			//modified date
			stmt.setDate(index++, Date.valueOf(now));
			stmt.setInt(index++, product.getMaterial().getMaterialId());

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
		Product existedProduct = findById(productId);
		if (existedProduct == null && existedProduct.getProductName() == null){
			return false;
		}

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE products SET ");
		sql.append("product_name=?, product_total=?, product_code=?, product_price=?, ");
		sql.append("product_discount_price=?, product_description=?, product_visited=?, ");
		sql.append("product_image_url=?, product_size=?, product_enable=?, ");
		sql.append("brand_id=?, category_id=?, updated_at=?, material_id=? ");
		sql.append(" WHERE product_id = ?");

		try (Connection conn = DataSourceUtil.getConnection();
			 PreparedStatement updateStmt = conn.prepareStatement(sql.toString())) {

			int index = 1;
			updateStmt.setString(index++, product.getProductName());
			updateStmt.setInt(index++, product.getProductTotal());
			updateStmt.setString(index++, product.getProductCode());
			updateStmt.setDouble(index++, product.getProductPrice());
			updateStmt.setDouble(index++, product.getProductDiscountPrice());
			updateStmt.setString(index++, product.getProductDescription());
			updateStmt.setInt(index++, product.getProductVisited());
			updateStmt.setString(index++, product.getProductImageUrl());
			updateStmt.setString(index++, product.getProductSize());
			updateStmt.setInt(index++, product.isProductEnable()? 1 : 0);
			updateStmt.setInt(index++, product.getBrand().getBrandId());
			updateStmt.setInt(index++, product.getCategory().getCategoryId());
			updateStmt.setDate(index++, java.sql.Date.valueOf(LocalDate.now()));
			updateStmt.setInt(index++, product.getMaterial().getMaterialId());
			updateStmt.setInt(index++, productId); // WHERE

			int rowsAffected = updateStmt.executeUpdate();
			return rowsAffected > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean softDeleteProduct(int productId) {
		String sql = "UPDATE products SET product_enable = 0 WHERE product_id = ?";

		try (
				Connection connection = DataSourceUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
		) {
			statement.setInt(1, productId);
			int rows = statement.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public int productCounter() {
		String sql = "SELECT COUNT(*) as total FROM products";
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

	@Override
	public List<Product> findAllBySearchConditions(int size, int page, String sortBy, String orderBy, String keyWord, int categoryId, int brandId, int materialId, String price) {
		List<Product> products = new ArrayList<>();

		StringBuilder sqlSearch = productQuerySearchBuilder(size, page, sortBy, orderBy, keyWord, categoryId, brandId, materialId, price);

		try (Connection conn = DataSourceUtil.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sqlSearch.toString())) {
			int index = 1;
			if(keyWord != null && !"".equalsIgnoreCase(keyWord)) {
				stmt.setString(index++, "%"+keyWord+"%");
			}
			if(categoryId != -1) {
				stmt.setInt(index++, categoryId);
			}
			if(brandId != -1) {
				stmt.setInt(index++, brandId);
			}
			if(materialId != -1) {
				stmt.setInt(index++, materialId);
			}

			if(price != null && !"".equalsIgnoreCase(price)) {
				int min = -1, max = -1;
				switch (price) {
					case "sm" -> { min = -1; max = 1_000_000;}
					case "md"->	{ min = 1_000_000; max = 3_000_000;}
					case "lg" -> { min = 3_000_000; max = 5_000_000;}
					case "xl" -> { min = 5_000_000; max = 9_000_000;}
					case "xxl"-> { min = 9_000_000; max = -1;}
				}

				if(min >= 0 ) {
					stmt.setInt(index++, min);
				}
				if(max >= 0) {
					stmt.setInt(index++, max);
				}
			}
			stmt.setInt(index++, size);
			stmt.setInt(index++, (page - 1) * size);
			System.out.println(sqlSearch.toString());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				products.add(mapRowToProduct(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public List<Product> findNewestProducts(Integer limit) {
		List<Product> products = new ArrayList<>();
		String sql = "SELECT * FROM products WHERE product_enable = 1 ORDER BY created_at DESC LIMIT ?";

		try (Connection conn = DataSourceUtil.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, limit);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				products.add(mapRowToProduct(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public List<Product> findBestSellProducts(Integer limit) {
		List<Product> products = new ArrayList<>();
		String sql = "SELECT p.*, SUM(od.order_quantity) as total_sold "+
				"FROM products p " +
				"JOIN order_items od ON p.product_id = od.product_id " +
				"WHERE p.product_enable = 1 " +
				"GROUP BY p.product_id " +
				"ORDER BY total_sold DESC " +
				"LIMIT ?";

		try (Connection conn = DataSourceUtil.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, limit);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				products.add(mapRowToProduct(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;

	}

	@Override
	public List<Product> findAll(int pageSize, int pageNumber, String orderBy, String sortBy) {
		List<Product> products = new ArrayList<>();

		List<String> allowedSortColumns = List.of("product_name", "product_price", "product_discount_price", "product_id", "created_at");
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
				product.setProductImageUrl(rs.getString("product_image_url"));
				product.setProductCode(rs.getString("product_code"));
				product.setProductVisited(rs.getInt("product_visited"));
				product.setCreatedAt(rs.getDate("created_at").toLocalDate());
				product.setUpdatedAt(rs.getDate("updated_at").toLocalDate());

				int brandId = rs.getInt("brand_id");
				product.setBrand(brandDAO.findById(brandId));

				int categoryId = rs.getInt("category_id");
				product.setCategory(categoryDAO.findById(categoryId));

				int materialId = rs.getInt("material_id");
				product.setMaterial(materialDAO.findById(materialId));

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

		int materialId = rs.getInt("material_id");
		Material materialFromDb = materialDAO.findById(materialId);
		if(materialFromDb == null) {
			Material emptyMaterial = new Material();
			product.setMaterial(emptyMaterial);
		} else
			product.setMaterial(materialFromDb);

		return product;
	}

	public StringBuilder productQuerySearchBuilder(int size, int page, String sortBy, String orderBy, String keyWord, int categoryId, int brandId, int materialId, String price){
		StringBuilder sqlQuerySearch = new StringBuilder();

		List<String> allowedSortColumns = List.of("product_name", "product_price", "product_discount_price", "product_id", "created_at");
		if (!allowedSortColumns.contains(orderBy)) {
			orderBy = "product_id";
		}
		sortBy = sortBy.equalsIgnoreCase("DESC") ? "DESC" : "ASC";

		sqlQuerySearch.append("SELECT * FROM products WHERE (1 = 1) AND product_enable = 1 ");

		if(keyWord!= null && !"".equalsIgnoreCase(keyWord)) {
			sqlQuerySearch.append(" AND ");
			sqlQuerySearch.append(" product_name LIKE ? ");
		}
		if(categoryId != -1) {
			sqlQuerySearch.append(" AND ");
			sqlQuerySearch.append(" category_id = ? ");
		}
		if(brandId != -1) {
			sqlQuerySearch.append(" AND ");
			sqlQuerySearch.append(" brand_id = ? ");
		}
		if(materialId != -1 ){
			sqlQuerySearch.append(" AND ");
			sqlQuerySearch.append(" material_id = ? ");
		}

		if(price != null && !"".equalsIgnoreCase(price)) {
			int min = -1, max = -1;
			switch (price) {
				case "sm" -> { min = -1; max = 1_000_000;}
				case "md"->	{ min = 1_000_000; max = 3_000_000;}
				case "lg" -> { min = 3_000_000; max = 5_000_000;}
				case "xl" -> { min = 5_000_000; max = 9_000_000;}
				case "xxl"-> { min = 9_000_000; max = -1;}
			}

			if(min >= 0 ) {
				sqlQuerySearch.append(" AND ");
				sqlQuerySearch.append(" product_discount_price >= ? ");
			}
			if(max >= 0) {
				sqlQuerySearch.append(" AND ");
				sqlQuerySearch.append(" product_discount_price <= ? ");
			}
		}
		sqlQuerySearch.append("ORDER BY " + orderBy + " "  + sortBy  + " " + "LIMIT ? OFFSET ?");

		return sqlQuerySearch;
	}

}
