package servlet.admin.controller;

import servlet.dao.MaterialDAO;
import servlet.dao.impl.MaterialDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/material/delete")
public class MaterialDelete extends HttpServlet {
    private MaterialDAO materialDAO;

    @Override
    public void init() throws ServletException {
        this.materialDAO = new MaterialDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int materialId = Integer.parseInt(req.getParameter("materialId"));

            if (materialDAO.deleteMaterial(materialId)) {
                resp.sendRedirect(req.getContextPath() + "/admin/materials-view?title=material&action=delete&noti=success");
            } else {
                resp.sendRedirect(req.getContextPath() + "/admin/materials-view?title=material&action=delete&noti=failed");
            }
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/materials-view?title=material&action=delete&noti=failed");
        }

    }
}
