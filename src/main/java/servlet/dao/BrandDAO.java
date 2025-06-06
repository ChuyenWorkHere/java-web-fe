package servlet.dao;

import servlet.models.Brand;

import java.util.List;

public interface BrandDAO {
    Brand findById(int id);
    List<Brand> findAll();
}
