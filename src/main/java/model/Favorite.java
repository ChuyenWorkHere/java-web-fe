package model;

import lombok.Data;

@Data
public class Favorite {
	private int favorite_id;
	private int user_id;
	private int product_id;
}
