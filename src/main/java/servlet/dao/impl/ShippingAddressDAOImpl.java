package servlet.dao.impl;

import servlet.dao.ShippingAddressDAO;
import servlet.models.ShippingAddress;
import servlet.utils.DataSourceUtil;

import java.sql.*;

public class ShippingAddressDAOImpl implements ShippingAddressDAO {

    @Override
    public ShippingAddress findByOrderId(int orderId) {
        String sql = "SELECT * FROM shipping_address WHERE order_id = ?";
        try (Connection conn = servlet.utils.DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ShippingAddress address = new ShippingAddress();
                    address.setShippingAddressId(rs.getInt("shipping_address_id"));
                    address.setFullname(rs.getString("fullname"));
                    address.setPhoneNumber(rs.getString("phone_number"));
                    address.setEmail(rs.getString("email"));
                    address.setAddress(rs.getString("address"));
                    return address;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ShippingAddress saveAddress(ShippingAddress shippingAddress) {
        String sql = "INSERT INTO shipping_address (fullname, phone_number, email, address, order_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, shippingAddress.getFullname());
            ps.setString(2, shippingAddress.getPhoneNumber());
            ps.setString(3, shippingAddress.getEmail());
            ps.setString(4, shippingAddress.getAddress());
            ps.setInt(5, shippingAddress.getOrder().getOrderId());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        shippingAddress.setShippingAddressId(generatedKeys.getInt(1));
                        return shippingAddress;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
