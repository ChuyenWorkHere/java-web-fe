package servlet.models;

import lombok.Data;

@Data
public class Cart {
	private int cartId;
	private int quantity;
	private User user;
	private Product product;

}
