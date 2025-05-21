package model;

import lombok.Data;

@Data
public class Cart {
	private int cart_id;
	private int quantity;
	private int user_id;
	private int product_id;
}
