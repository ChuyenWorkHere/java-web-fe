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
    private String productCode;
    private String productMaterial;
    private double productPrice;
    private double productDiscountPrice;
    private String productDescription;
    private int productVisited;
    private String productImageUrl;
    private String productSize;
    private boolean productEnable;
    private Brand brand;
    private Category category;
    private Material material;
}
