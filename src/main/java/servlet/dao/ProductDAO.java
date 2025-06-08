package servlet.dao;

import java.util.List;

import lombok.Data;
import servlet.models.Product;


public interface ProductDAO {
	Product findById(int id);
	List<Product> findAll(int pageSize, int pageNumber, String orderBy, String sortBy);
	boolean saveProduct(Product product);
	boolean editProduct(int productId, Product product);
	boolean deleteProduct(int productId);
	int productCounter();
	List<Product> findAllBySearchConditions(int size, int page, String sortBy, String orderBy, String keyWord, int categoryId, int brandId, String color, String price);
}
