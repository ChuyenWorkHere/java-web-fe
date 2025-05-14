package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
	private int cart_id;
	private int quantity;
	private int user_id;
	private int product_id;
}
