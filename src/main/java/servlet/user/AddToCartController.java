package servlet.user;

import servlet.dao.CartDAO;
import servlet.dao.impl.CartDAOImpl;
import servlet.models.Cart;
import servlet.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/customer/add-to-cart")
public class AddToCartController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AddToCartController() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        CartDAO cartDAO = new CartDAOImpl();

        int quantity = cartDAO.getQuantity(user.getUserId(), productId);
        if(quantity > 0) {
            cartDAO.updateProductToCart(user.getUserId(), productId, quantity + 1);
        }else {
            boolean check = cartDAO.addProductToCart(user.getUserId(), productId);
            System.out.println("check: " + check);
        }

        response.sendRedirect("../customer/cart");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}

