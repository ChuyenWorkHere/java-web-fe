package test;

import java.util.ArrayList;
import java.util.List;

import servlet.dao.CategoryDAO;
import servlet.dao.OrderDAO;
import servlet.dao.UserDAO;
import servlet.dao.impl.CategoryDAOImpl;
import servlet.dao.impl.OrderDAOImpl;
import servlet.dao.impl.UserDAOImpl;
import servlet.models.Category;
import servlet.models.Order;
import servlet.models.User;

public class Test {

	public static void main(String[] args) {
		CategoryDAO categoryDAO = new CategoryDAOImpl();

		List<Category> categories = new ArrayList<>();

		categories.add(new Category(
				"Sofas",
				"Ghế sofa hiện đại và cổ điển, phù hợp cho phòng khách",
				1
		));
		categories.add(new Category(
				"Dining Tables",
				"Bàn ăn với thiết kế sang trọng, đa dạng chất liệu",
				1
		));
		categories.add(new Category(
				"Chairs",
				"Ghế ăn, ghế văn phòng và ghế thư giãn cao cấp",
				1
		));
		categories.add(new Category(
				"Beds",
				"Giường ngủ đa phong cách, tối ưu sự thoải mái",
				1
		));
		categories.add(new Category(
				"Wardrobes",
				"Tủ quần áo với thiết kế hiện đại, tối ưu không gian",
				1
		));
		categories.add(new Category(
				"Coffee Tables",
				"Bàn cà phê nhỏ gọn, phong cách tối giản",
				1
		));
		categories.add(new Category(
				"Bookshelves",
				"Kệ sách gỗ và kim loại, đa dạng kích thước",
				1
		));
		categories.add(new Category(
				"TV Stands",
				"Kệ tivi hiện đại, tích hợp lưu trữ tiện lợi",
				1
		));
		categories.add(new Category(
				"Dining Chairs",
				"Ghế ăn bọc nệm, thiết kế thoải mái và thẩm mỹ",
				1
		));
		categories.add(new Category(
				"Mattresses",
				"Nệm cao cấp, hỗ trợ giấc ngủ tối ưu",
				1
		));
		categories.add(new Category(
				"Nightstands",
				"Tủ đầu giường nhỏ gọn, phong cách tinh tế",
				1
		));
		categories.add(new Category(
				"Desk",
				"Bàn làm việc đa năng, phù hợp cho văn phòng tại nhà",
				1
		));
		categories.add(new Category(
				"Recliners",
				"Ghế tựa thư giãn với cơ chế ngả lưng",
				1
		));
		categories.add(new Category(
				"Kitchen Cabinets",
				"Tủ bếp hiện đại, tối ưu hóa không gian lưu trữ",
				1
		));
		categories.add(new Category(
				"Decorative Lamps",
				"Đèn trang trí nội thất, đa dạng phong cách",
				0
		));
		categories.add(new Category(
				"Rugs",
				"Thảm trải sàn cao cấp, đa dạng màu sắc và kích thước",
				1
		));
		categories.add(new Category(
				"Mirrors",
				"Gương trang trí, phù hợp cho phòng khách và phòng ngủ",
				1
		));
		categories.add(new Category(
				"Outdoor Furniture",
				"Nội thất ngoài trời, bền bỉ với thời tiết",
				0
		));
		categories.add(new Category(
				"Bar Stools",
				"Ghế quầy bar hiện đại, phù hợp cho bếp và quầy",
				1
		));
		categories.add(new Category(
				"Storage Benches",
				"Ghế dài tích hợp ngăn lưu trữ, đa năng và tiện lợi",
				1
		));

		for(Category category : categories) {
			categoryDAO.addCategory(category);
		}
	}

}
