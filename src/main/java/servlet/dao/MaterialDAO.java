package servlet.dao;

import servlet.models.Material;

import java.util.List;

public interface MaterialDAO {

    List<Material> findAll();

    boolean saveMaterial(Material material);

    boolean editMaterial(Material material);

    boolean deleteMaterial(int materialId);

    Material findById(int id);

}
