package servlet.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import servlet.models.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

    private Integer cartId;
    private Product product;
    private Integer quantity;

}
