package servlet.dao.impl;

import servlet.dao.ReviewDAO;
import servlet.models.Product;
import servlet.models.Review;
import servlet.models.User;
import servlet.utils.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAOImpl implements ReviewDAO {

    @Override
    public boolean addReview(Review review) {
        String sql = "INSERT INTO reviews (rate, comment, created_at, user_id, product_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setShort(1, review.getRate());
            ps.setString(2, review.getComment());
            ps.setTimestamp(3, new Timestamp(review.getCreatedAt().getTime()));
            ps.setLong(4, review.getUser().getUserId());
            ps.setLong(5, review.getProduct().getProductId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Review> getReviewsByProductId(int productId) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT r.*, u.user_fullname, u.avatar FROM reviews r JOIN users u ON r.user_id = u.user_id WHERE r.product_id = ? ORDER BY r.created_at DESC";

        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Review review = new Review();
                review.setReviewId(rs.getLong("review_id"));
                review.setRate(rs.getShort("rate"));
                review.setComment(rs.getString("comment"));
                review.setCreatedAt(rs.getTimestamp("created_at"));

                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFullname(rs.getString("user_fullname"));
                user.setAvatar(rs.getString("avatar"));
                review.setUser(user);

                Product product = new Product();
                product.setProductId(productId);
                review.setProduct(product);

                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public double getAverageRatingByProductId(int productId) {
        String sql = "SELECT AVG(rate) FROM reviews WHERE product_id = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    @Override
    public boolean hasUserReviewedProduct(int userId, int productId) {
        String sql = "SELECT COUNT(*) FROM reviews WHERE user_id = ? AND product_id = ?";
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
