package servlet.admin.controller;

import servlet.dao.CategoryDAO;
import servlet.dao.impl.CategoryDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delete-category")
public class DeleteCategory extends HttpServlet {

    private CategoryDAO categoryDAO;

    public DeleteCategory() {
        categoryDAO = new CategoryDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int categoryId = Integer.parseInt(req.getParameter("categoryId"));

        boolean isSuccess = categoryDAO.deleteCategory(categoryId);

        if(isSuccess) {
            resp.sendRedirect("../admin/categories-view?title=category&action=del&noti=success");
        } else {
            resp.sendRedirect("../admin/categories-view?title=category&action=del&noti=failed");
        }
    }
}
