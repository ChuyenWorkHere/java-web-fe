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
    private CartDAO cartDAO;

    @Override
    public void init() throws ServletException {
        this.cartDAO = new CartDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("customer");

        if (user == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {
            int productId = Integer.parseInt(req.getParameter("productId"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));

            if (quantity > 0) {
                cartDAO.updateCartItemQuantity(productId, user.getUserId(), quantity);
                resp.sendRedirect(req.getContextPath() + "/customer/cart");
            } else {
                resp.sendRedirect(req.getContextPath() + "/customer/cart");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/customer/cart");
        }
    }
}