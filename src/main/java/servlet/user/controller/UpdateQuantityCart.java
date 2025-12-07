package servlet.user.controller;

import servlet.dao.CartDAO;
import servlet.dao.impl.CartDAOImpl;
import servlet.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/customer/cart/update-quantity")
public class UpdateQuantityCart extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CartDAO cartDAO;

    @Override
    public void init() throws ServletException {
        this.cartDAO = new CartDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("customer");

        // 1. Kiểm tra đăng nhập
        if (user == null) {
            session.setAttribute("toast_message", "Vui lòng đăng nhập để thực hiện chức năng này!");
            session.setAttribute("toast_type", "warning");

            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        try {
            int productId = Integer.parseInt(req.getParameter("productId"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));

            if (quantity > 0) {
                cartDAO.updateCartItemQuantity(productId, user.getUserId(), quantity);

                session.setAttribute("toast_message", "Cập nhật số lượng thành công!");
                session.setAttribute("toast_type", "success");
            } else {
                session.setAttribute("toast_message", "Số lượng sản phẩm không hợp lệ!");
                session.setAttribute("toast_type", "warning");
            }

            resp.setStatus(HttpServletResponse.SC_OK);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            session.setAttribute("toast_message", "Dữ liệu không hợp lệ!");
            session.setAttribute("toast_type", "error");

            resp.setStatus(HttpServletResponse.SC_OK);

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("toast_message", "Lỗi hệ thống: " + e.getMessage());
            session.setAttribute("toast_type", "error");

            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}