package servlet.user.controller;

import servlet.dao.CartDAO;
import servlet.dao.ProductDAO;
import servlet.dao.impl.CartDAOImpl;
import servlet.dao.impl.ProductDAOImpl;
import servlet.models.Product;
import servlet.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/customer/cart/update")
public class CartUpdate extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CartDAO cartDAO;
    private ProductDAO productDAO;

    public CartUpdate() {
        super();
        cartDAO = new CartDAOImpl();
        productDAO = new ProductDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        String referer = request.getHeader("Referer");
        String redirectURL = (referer != null && !referer.isEmpty()) ? referer : request.getContextPath() + "/customer/cart";

        try {
            User user = (User) session.getAttribute("customer");
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/public/login");
                return;
            }

            int productId = Integer.parseInt(request.getParameter("productId"));
            int quantity = 1;
            if(request.getParameter("quantity") != null) {
                quantity = Integer.parseInt(request.getParameter("quantity"));
            }

            Product originalProduct = productDAO.findById(productId);
            if (originalProduct == null) {
                session.setAttribute("toast_message", "Sản phẩm không tồn tại!");
                session.setAttribute("toast_type", "error");
                response.sendRedirect(redirectURL);
                return;
            }

            if(!originalProduct.isProductEnable()) {
                session.setAttribute("toast_message", "Sản phẩm này hiện đang ngừng kinh doanh!");
                session.setAttribute("toast_type", "warning");
                response.sendRedirect(redirectURL);
                return;
            }

            if (quantity > originalProduct.getProductTotal()) {
                 session.setAttribute("toast_message", "Số lượng vượt quá tồn kho!");
                 session.setAttribute("toast_type", "warning");
                 response.sendRedirect(redirectURL);
                 return;
            }

            // Thực hiện cập nhật
            boolean isSuccess = cartDAO.updateUserCart(user.getUserId(), productId, quantity);

            if (isSuccess) {
                session.setAttribute("toast_message", "Cập nhật giỏ hàng thành công!");
                session.setAttribute("toast_type", "success");
            } else {
                session.setAttribute("toast_message", "Không thể cập nhật giỏ hàng. Vui lòng thử lại!");
                session.setAttribute("toast_type", "error");
            }

            response.sendRedirect(redirectURL);

        } catch (NumberFormatException e) {
            session.setAttribute("toast_message", "Lỗi dữ liệu! Mã sản phẩm hoặc số lượng không hợp lệ.");
            session.setAttribute("toast_type", "error");
            response.sendRedirect(redirectURL);
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("toast_message", "Đã xảy ra lỗi hệ thống!");
            session.setAttribute("toast_type", "error");
            response.sendRedirect(redirectURL);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}