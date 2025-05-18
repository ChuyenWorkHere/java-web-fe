package servlet.admin.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/product/add")
public class AddProduct extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AddProduct() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String productName = request.getParameter("productName");
        String productSize = request.getParameter("productSize");
        String productMaterial = request.getParameter("productMaterial");
        String category = request.getParameter("category");
        String brand = request.getParameter("brand");
        String productStock = request.getParameter("productStock");
        String status = request.getParameter("status");
        String regularPrice = request.getParameter("regularPrice");
        String salePrice = request.getParameter("salePrice");
        String about = request.getParameter("about");

        // Bạn có thể ép kiểu hoặc xử lý thêm như sau
        int categoryId = Integer.parseInt(category);
        int brandId = Integer.parseInt(brand);
        int stock = Integer.parseInt(productStock);
        int statusValue = Integer.parseInt(status);
        double regular = Double.parseDouble(regularPrice);
        double sale = Double.parseDouble(salePrice);

        // In ra để kiểm tra
        System.out.println("Tên sản phẩm: " + productName);
        System.out.println("Kích thước: " + productSize);
        System.out.println("Chất liệu: " + productMaterial);
        System.out.println("Danh mục ID: " + categoryId);
        System.out.println("Thương hiệu ID: " + brandId);
        System.out.println("Số lượng: " + stock);
        System.out.println("Trạng thái: " + statusValue);
        System.out.println("Giá gốc: " + regular);
        System.out.println("Giá bán: " + sale);
        System.out.println("Mô tả: " + about);

        // TODO: Lưu vào DB, gọi service, forward,...
    }

}
