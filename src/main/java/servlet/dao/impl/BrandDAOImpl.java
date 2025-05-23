package servlet.dao.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import servlet.dao.BrandDAO;
import servlet.models.Brand;
import servlet.utils.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BrandDAOImpl implements BrandDAO {

    @Override
    public Brand findById(int id) {
        String sql = "SELECT * FROM brands WHERE brand_id = ?";
        try (
                Connection conn = DataSourceUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
               return mapRowToBrand(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Brand mapRowToBrand(ResultSet rs) throws SQLException {
        Brand brand = new Brand();

        brand.setBrandId(rs.getInt("brand_id"));
        brand.setBrandName(rs.getString("brand_name"));

        return brand;
    }

}
