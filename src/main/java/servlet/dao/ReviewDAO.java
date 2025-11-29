package servlet.dao;

import servlet.models.Review;
import java.util.List;

public interface ReviewDAO {

    /**
     * Thêm một bài đánh giá mới vào cơ sở dữ liệu.
     * @param review Đối tượng Review cần thêm.
     * @return true nếu thêm thành công, false nếu thất bại.
     */
    boolean addReview(Review review);

    /**
     * Lấy danh sách tất cả các bài đánh giá cho một sản phẩm cụ thể.
     * @param productId ID của sản phẩm.
     * @return Danh sách các đối tượng Review.
     */
    List<Review> getReviewsByProductId(int productId);

    /**
     * Tính toán xếp hạng trung bình cho một sản phẩm cụ thể.
     * @param productId ID của sản phẩm.
     * @return Xếp hạng trung bình (kiểu double).
     */
    double getAverageRatingByProductId(int productId);

    /**
     * Kiểm tra xem một người dùng đã đánh giá một sản phẩm cụ thể hay chưa.
     * @param userId ID của người dùng.
     * @param productId ID của sản phẩm.
     * @return true nếu người dùng đã đánh giá, false nếu chưa.
     */
    boolean hasUserReviewedProduct(int userId, int productId);
}
