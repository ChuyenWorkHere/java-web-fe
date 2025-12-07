package servlet.admin.controller;

import servlet.dao.CategoryDAO;
import servlet.dao.impl.CategoryDAOImpl;
import servlet.models.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/edit-category")
public class EditCategory extends HttpServlet {

    private CategoryDAO categoryDAO;

    public EditCategory() {
        categoryDAO = new CategoryDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        String categoryName = req.getParameter("categoryName");
        String categoryDescription = req.getParameter("categoryDescription");
        int isActive = Integer.parseInt(req.getParameter("isActive"));

        Category editedCategory = new Category();
        editedCategory.setCategoryName(categoryName);
        editedCategory.setCategoryDescription(categoryDescription);
        editedCategory.setIsActive(isActive);

        boolean isSuccess = categoryDAO.editCategory(categoryId, editedCategory);

        if(isSuccess) {
            resp.sendRedirect(req.getContextPath() + "/admin/categories-view?title=category&action=edit&noti=success");
        } else {
            resp.sendRedirect(req.getContextPath() + "/admin/categories-view?title=category&action=edit&noti=failed");
        }
    }

}
