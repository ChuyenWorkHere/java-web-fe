package servlet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import servlet.dao.ProductDAO;
import servlet.models.Product;
import servlet.utils.DataSourceUtil;

public class ProductDAOImpl implements ProductDAO {

	@Override
	public Product findById(int id) {
		String sql = "SELECT * FROM products WHERE productId = ?";
        try (
            Connection conn = DataSourceUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	//Get Product
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
		return null;
	}

	

	@Override
	public boolean addProduct(Product product) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}
	
}
