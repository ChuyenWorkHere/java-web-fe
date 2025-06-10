package servlet.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int orderId;
    private double totalPrice;
    private String orderStatus;
    private String paymentStatus;
    private String paymentMethod;
    private Date createdAt;
    private String orderNote;
    private User user;
    private ShippingAddress shippingAddress;
    private List<OrderItem> orderItems;

}
