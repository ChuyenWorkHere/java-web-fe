package servlet.dao;

import java.util.List;

import servlet.models.Product;

public interface ProductDAO {
	Product findById(int id);
	List<Product> findAll(int pageSize, int pageNumber, String orderBy, String sortBy);
	boolean saveProduct(Product product);
	boolean editProduct(int productId, Product product);
	boolean deleteProduct(int productId);
}
