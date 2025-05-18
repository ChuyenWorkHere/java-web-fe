package servlet.dao.impl;

import servlet.dao.CartDAO;
import servlet.models.Cart;
import servlet.models.Product;
import servlet.utils.DataSourceUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImpl implements CartDAO {

	@Override
	public List<Cart> getCartByUserId(int userId) {
		List<Cart> carts = new ArrayList<Cart>();
	    
	    String sql = "SELECT p.product_id, p.product_name, p.product_image_url , p.product_discount_price, c.quantity " +
	                 "FROM cart c " +
	                 "JOIN products p ON c.product_id = p.product_id " +
	                 "WHERE c.user_id = ?";
	    
	    try (
	        Connection conn = DataSourceUtil.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql)
	    ) {
	        ps.setInt(1, userId);

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Product product = new Product();
	                product.setProductId(rs.getInt("product_id"));
	                product.setProductName(rs.getString("product_name"));	
	                product.setProductImageUrl(rs.getString("product_image_url"));
	                product.setProductDiscountPrice(rs.getDouble("product_discount_price"));
	                
	                Cart cart = new Cart();
	                cart.setQuantity(rs.getInt("quantity"));
	                cart.setProduct(product);
	                
	                carts.add(cart);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return carts;
	}


    @Override
    public boolean addProductToCart(int userId, int productId) {
    	
    	int quantity = 1;
    	if(isProductInCart(userId, productId)) {
    		 quantity += getQuantity(userId, productId);
    	}
        String sql = "INSERT INTO cart (user_id, product_id, quantity) VALUES (?, ?, ?)";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateProductToCart(int userId, int productId, int quantity) {
    	
    	String sql = "UPDATE cart SET quantity = ? WHERE user_id = ? AND product_id = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, userId);
            ps.setInt(3, productId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteProductToCartById(int userId, int productId) {
        String sql = "DELETE FROM cart WHERE user_id = ? AND product_id = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, productId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isProductInCart(int userId, int productId) {
        String sql = "SELECT 1 FROM cart WHERE user_id = ? AND product_id = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getQuantity(int userId, int productId) {
        String sql = "SELECT quantity FROM cart WHERE user_id = ? AND product_id = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantity");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
