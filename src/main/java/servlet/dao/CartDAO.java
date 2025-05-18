package servlet.dao;

import servlet.models.Cart;

import java.util.List;

public interface CartDAO {
    List<Cart> getCartByUserId(int userId);
    boolean addProductToCart(int userId, int productId);
    boolean updateProductToCart(int userId, int productId, int quantity);
    boolean deleteProductToCartById(int userId, int productId);
    boolean isProductInCart(int userId, int productId);
    int getQuantity(int userId, int productId);
}
