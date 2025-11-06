package servlet.dao.impl;

import servlet.dao.CartDAO;
import servlet.dao.ProductDAO;
import servlet.models.Cart;
import servlet.models.Product;
import servlet.utils.DataSourceUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImpl implements CartDAO {

    private ProductDAO productDAO;

    public CartDAOImpl() {
        productDAO = new ProductDAOImpl();
    }


	@Override
	public List<Cart> getCartByUserId(int userId) {
		List<Cart> carts = new ArrayList<Cart>();
	    
	    String sql = "SELECT p.product_id, p.product_name, p.product_image_url , p.product_discount_price, c.quantity, c.cart_id " +
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
	                product.setProductDiscountPrice((float) rs.getDouble("product_discount_price"));
	                
	                Cart cart = new Cart();
	                cart.setQuantity(rs.getInt("quantity"));
	                cart.setCartId(rs.getInt("cart_id"));
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

        String sql = "INSERT INTO cart (user_id, product_id, quantity) VALUES (?, ?, ?)";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.setInt(3, 1);
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
    public boolean updateUserCart(int userId, int productId, int quantity) {

        Product originalProduct = productDAO.findById(productId);
        int productTotal = originalProduct.getProductTotal();

        String sql = "SELECT quantity FROM cart WHERE user_id = ? AND product_id = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int quantityInCart = rs.getInt("quantity");
                if(quantityInCart >= productTotal) {
                    updateProductToCart(userId, productId, quantityInCart);
                } else if (quantityInCart + quantity > productTotal) {
                    updateProductToCart(userId, productId, productTotal);
                } else {
                    updateProductToCart(userId, productId, quantityInCart + quantity);
                }

            } else {
                addProductToCart(userId, productId);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCartItem(int productId, int userId) {
        String sql = "DELETE FROM cart WHERE product_id = ? AND user_id = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void updateCartItemQuantity(int productId, int userId, int quantity) {
        String sql = "UPDATE cart SET quantity = ? WHERE product_id = ? AND user_id = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, productId);
            ps.setInt(3, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
