package servlet.dao.impl;

import java.util.List;

import servlet.dao.CategoryDAO;
import servlet.models.Category;

public class CategoryDAOImpl implements CategoryDAO {

	@Override
	public Category findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> findAll(int pageSize, int pageNumber, String orderBy, String sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addCategory(Category category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editCategory(int categoryId, Category category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCategory(int categoryId) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
