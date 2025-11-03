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

@WebServlet("/admin/material/update")
public class MaterialUpdate extends HttpServlet {
    private MaterialDAO materialDAO;

    @Override
    public void init() throws ServletException {
        this.materialDAO = new MaterialDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        try {
            int materialId = Integer.parseInt(req.getParameter("materialId"));
            String materialName = req.getParameter("materialName");
            String materialDesc = req.getParameter("materialDesc");

            if (materialName == null || materialName.trim().isEmpty()) {
                resp.sendRedirect("../materials-view?title=material&action=edit&noti=failed");
            } else {
                Material material = new Material(materialId, materialName.trim(), materialDesc.trim());
                if (materialDAO.editMaterial(material)) {
                    resp.sendRedirect("../materials-view?title=material&action=edit&noti=success");
                } else {
                    resp.sendRedirect("../materials-view?title=material&action=edit&noti=failed");
                }
            }
        } catch (NumberFormatException e) {
            resp.sendRedirect("../materials-view?title=material&action=edit&noti=failed");
        }
    }
}
