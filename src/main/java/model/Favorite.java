package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {
	private int favorite_id;
	private int user_id;
	private int product_id;
}
