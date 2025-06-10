package servlet.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import servlet.models.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopProductSold {

    private Product product;
    private int totalSold;

}
