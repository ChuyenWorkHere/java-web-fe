package servlet.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel {
    private int productId;
    private String productName;
    private int productTotal;
    private float productPrice;
    private float productDiscountPrice;
    private String productDescription;
    private int productVisited;
    private String productImageUrl;
    private String productSize;
    private boolean productEnable;
    private Date createdDate;
    private Date modifiedDate;
    private Brand brand;
    private Category category;


}
