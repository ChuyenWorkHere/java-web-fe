package servlet.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    private int orderItemId;
    private int orderQuantity;
    private float orderPrice;
    private Order order;
    private Product product;
}
