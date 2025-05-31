package servlet.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingAddress {

    private int shippingAddressId;
    private String fullname;
    private String phoneNumber;
    private String email;
    private String address;
    private boolean isDefault;

    @ToString.Exclude
    private Order order;
//    private User user;

}
