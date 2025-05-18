package servlet.dao;

import java.util.List;

import servlet.models.Category;

public interface CategoryDAO {
	Category findById(int id);
	List<Category> findAll(int pageSize, int pageNumber, String orderBy, String sortBy);
	boolean addCategory(Category category);
	boolean editCategory(int categoryId, Category category);
	boolean deleteCategory(int categoryId);
}
