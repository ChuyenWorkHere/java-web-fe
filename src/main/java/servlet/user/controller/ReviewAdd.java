package servlet.user.controller;

import servlet.dao.ReviewDAO;
import servlet.dao.impl.ReviewDAOImpl;
import servlet.models.Order;
import servlet.models.Product;
import servlet.models.Review;
import servlet.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/customer/review/add")
public class ReviewAdd extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReviewDAO reviewDAO;

    public ReviewAdd() {
        super();
        this.reviewDAO = new ReviewDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("customer");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/public/login");
            return;
        }

        String productIdStr = request.getParameter("productId");
        String ratingStr = request.getParameter("rating");
        String comment = request.getParameter("message");

        try {
            short rating = Short.parseShort(ratingStr);
            int productId = Integer.parseInt(productIdStr);

            Review review = new Review();
            review.setRate(rating);
            review.setComment(comment);
            review.setCreatedAt(new Date());

            review.setUser(user);

            Product product = new Product();
            product.setProductId(productId);
            review.setProduct(product);

            reviewDAO.addReview(review);

            response.sendRedirect(request.getContextPath() + "/public/product-detail?productId=" + productId + "&review_success=true");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/public/product-detail?productId=" + productIdStr + "&error=invalid_data");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/public/home");
    }
}
