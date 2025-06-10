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

@WebServlet("/admin/category/add")
public class AddCategory extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CategoryDAO categoryDAO;

    public AddCategory() {
        categoryDAO= new CategoryDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Đặt encoding cho request
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String categoryName = req.getParameter("categoryName");
        String categoryDescription = req.getParameter("categoryDescription");
        int isActive = Integer.parseInt(req.getParameter("isActive"));

        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setCategoryDescription(categoryDescription);
        category.setIsActive(isActive);


        boolean isSucess = categoryDAO.saveCategory(category);
        if(isSucess) {
            resp.sendRedirect("../categories-view?title=category&action=add&noti=success");
        } else {
            resp.sendRedirect("../categories-view?title=category&action=add&noti=failed");
        }

    }
}
