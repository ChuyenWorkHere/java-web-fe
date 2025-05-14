package servlet.admin.model;

import java.util.Date;

import lombok.Data;

@Data
public class Product {
	private int product_id; 
	private String product_name; 
	private int product_total; 
	private double product_price; 
	private double product_discount_price; 
	private String product_description; 
	private int product_visited; 
	private String product_image_url; 
	private String product_size; 
	private byte product_enable; 
	private Date product_created_date; 
	private Date product_modified_date; 
	private int brand_id; 
	private int category_id;

}
