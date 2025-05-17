package servlet.dao;

import java.util.List;

import servlet.models.Product;

public interface ProductDAO {
	Product findById(int id);
	List<Product> findAll();
	boolean addProduct(Product product);
	boolean editProduct(int productId, Product product);
	boolean deleteProduct(int productId);
}
