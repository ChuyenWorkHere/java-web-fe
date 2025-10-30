package servlet.admin.controller;

import servlet.dao.ProductDAO;
import servlet.dao.impl.ProductDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/product-delete")
public class ProductDelete extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductDAO productDAO;

    public ProductDelete() {
        productDAO = new ProductDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));

        boolean isSuccess = productDAO.softDeleteProduct(productId);

        if(isSuccess) {
            resp.sendRedirect("../admin/products-view?title=product&action=del&noti=success");
        } else {
            resp.sendRedirect("../admin/products-view?title=product&action=del&noti=failed");
        }
    }
}
