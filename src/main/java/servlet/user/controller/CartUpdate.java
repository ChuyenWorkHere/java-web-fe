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
        HttpSession session = request.getSession();
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            int quantity = 1;
            if(request.getParameter("quantity") != null) {
                quantity = Integer.parseInt(request.getParameter("quantity"));
            }
            User user = (User) session.getAttribute("customer");

            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/public/login");
                return;
            }
            String referer = request.getHeader("Referer");
            String redirectURL = (referer != null && !referer.isEmpty()) ? referer : request.getContextPath() + "/customer/cart";
            Product originalProduct = productDAO.findById(productId);
            if (originalProduct == null) {
                response.sendRedirect(request.getContextPath() + "/public/home");
                return;
            }
            if(!originalProduct.isProductEnable()) {
                response.sendRedirect(redirectURL+ "?title=cart&action=edit&noti=failed");
                return;
            }

            boolean isSuccess = cartDAO.updateUserCart(user.getUserId(), productId, quantity);

            if (isSuccess) {
                response.sendRedirect(redirectURL+ "?title=cart&action=edit&noti=success");
            } else {
                response.sendRedirect(redirectURL+ "?title=cart&action=edit&noti=failed");
            }
        } catch (NumberFormatException e) {
            String referer = request.getHeader("Referer");
            String redirectURL = (referer != null && !referer.isEmpty()) ? referer : request.getContextPath() + "/customer/cart";
            response.sendRedirect(redirectURL+ "?title=cart&action=edit&noti=failed");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
