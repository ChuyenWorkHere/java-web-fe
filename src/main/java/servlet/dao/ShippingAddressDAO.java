package servlet.dao;

import servlet.models.ShippingAddress;

public interface ShippingAddressDAO {

    ShippingAddress findByOrderId(int orderId);
    ShippingAddress saveAddress(ShippingAddress shippingAddress);

}
