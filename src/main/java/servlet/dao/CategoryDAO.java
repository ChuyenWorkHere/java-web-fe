package servlet.dao;

import java.util.List;

import servlet.models.Category;

public interface CategoryDAO {
	Category findById(int id);
	List<Category> findAllActiveCategories(int pageSize, int pageNumber, String orderBy, String sortBy);
	List<Category> findAll(int pageSize, int pageNumber, String orderBy, String sortBy, String keyWord, int active);
	boolean saveCategory(Category category);
	boolean editCategory(int categoryId, Category category);
	boolean deleteCategory(int categoryId);
	int categoryCounter();
}
