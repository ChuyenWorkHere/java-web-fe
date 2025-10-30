package servlet.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import servlet.models.Brand;
import servlet.models.Category;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private int productId;
    private String productName;
    private int productTotal;
    private String productCode;
    private String productMaterial;
    private double productPrice;
    private double productDiscountPrice;
    private String productDescription;
    private int productVisited;
    private List<String> productImageUrls;
    private List<String> productColors;
    private String productSize;
    private boolean productEnable;
    private Brand brand;
    private Category category;
    private LocalDate createdAt;

}
