package servlet.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseModel {
	private int productId;
	private String productName;
	private String productImageUrl;
	private int productTotal;
	private double productPrice;
	private double productDiscountPrice;
}
