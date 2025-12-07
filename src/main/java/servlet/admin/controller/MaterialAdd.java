package servlet.admin.controller;

import servlet.dao.MaterialDAO;
import servlet.dao.impl.MaterialDAOImpl;
import servlet.models.Material;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/material/add")
public class MaterialAdd extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private MaterialDAO materialDAO;
    
    public MaterialAdd() {
        super();
        materialDAO = new MaterialDAOImpl();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String materialName = req.getParameter("materialName");
        String materialDesc = req.getParameter("materialDesc");

        if (materialName == null || materialName.trim().isEmpty()
            || materialDesc == null || materialDesc.trim().isEmpty()) {
            resp.sendRedirect("../materials-view?title=material&action=add&noti=failed");
            return;
        }

        Material material = new Material();
        material.setMaterialName(materialName.trim());
        material.setDescription(materialDesc.trim());
        if (materialDAO.saveMaterial(material)) {
            resp.sendRedirect(req.getContextPath() + "/admin/materials-view?title=material&action=add&noti=success");
        } else {
            resp.sendRedirect(req.getContextPath() + "/admin/materials-view?title=material&action=add&noti=failed");
        }
    }
}
