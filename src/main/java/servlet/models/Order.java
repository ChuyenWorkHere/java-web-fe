package servlet.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int orderId;
    private float totalPrice;
    private String orderStatus;
    private String paymentStatus;
    private String paymentMethod;
    private Date createdAt;
    private String orderNote;
    private User user;

}
