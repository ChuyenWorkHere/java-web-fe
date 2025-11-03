package servlet.dao.impl;

import servlet.dao.MaterialDAO;
import servlet.models.Material;
import servlet.utils.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAOImpl implements MaterialDAO {

    @Override
    public List<Material> findAll() {
        List<Material> materials = new ArrayList<>();
        String sql = "SELECT * FROM material ORDER BY material_id ASC";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Material material = new Material();
                material.setMaterialId(rs.getInt("material_id"));
                material.setMaterialName(rs.getString("material_name"));
                material.setDescription(rs.getString("description"));
                materials.add(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materials;
    }

    @Override
    public boolean saveMaterial(Material material) {
        String sql = "INSERT INTO material (material_name, description) VALUES (?, ?)";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, material.getMaterialName());
            stmt.setString(2, material.getDescription());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean editMaterial(Material material) {
        String sql = "UPDATE material SET material_name = ?, description = ? WHERE material_id = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, material.getMaterialName());
            stmt.setString(2, material.getDescription());
            stmt.setInt(3, material.getMaterialId());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteMaterial(int materialId) {
        String sql = "DELETE FROM material WHERE material_id = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, materialId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Material findById(int id) {
        String sql = "SELECT * FROM material WHERE material_id = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Material material = new Material();
                    material.setMaterialId(rs.getInt("material_id"));
                    material.setMaterialName(rs.getString("material_name"));
                    material.setDescription(rs.getString("description"));
                    return material;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
