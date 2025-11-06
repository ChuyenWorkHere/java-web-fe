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

@WebServlet("/customer/cart/delete")
public class UserCartDelete extends HttpServlet {
    private CartDAO cartDAO;

    @Override
    public void init() throws ServletException {
        this.cartDAO = new CartDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("customer");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/public/login");
            return;
        }

        try {
            int productId = Integer.parseInt(req.getParameter("productId"));
            cartDAO.deleteCartItem(productId, user.getUserId());
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/customer/cart");
        }

        resp.sendRedirect(req.getContextPath() + "/customer/cart");
    }
}
