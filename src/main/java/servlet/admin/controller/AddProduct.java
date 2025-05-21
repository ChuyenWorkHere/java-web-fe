package servlet.admin.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import servlet.dao.ProductDAO;
import servlet.dao.impl.ProductDAOImpl;
import servlet.models.Brand;
import servlet.models.Category;
import servlet.models.Product;
import servlet.utils.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@WebServlet("/admin/product/add")
@MultipartConfig
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
        // Đặt encoding cho request
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String uploadPath = getServletContext().getRealPath("/uploads");

        // Khởi tạo StringBuilder để nối các URL ảnh và mã màu
        StringBuilder imgUrlsConcat = new StringBuilder();
        List<String> imgUrlAndColor = new ArrayList<>();

        // Khởi tạo DiskFileItemFactory và ServletFileUpload
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(10 * 1024 * 1024); // Giới hạn kích thước file: 10MB

        // Lấy các tham số từ form
        String productName = null;
        String productSize = null;
        String productMaterial = null;
        int categoryId = 0;
        int brandId = 0;
        int stock = 0;
        int statusValue = 0;
        double regular = 0.0;
        double sale = 0.0;
        String about = null;
        String[] colors = new String[100]; // Lưu mã màu theo index
        Map<Integer, List<String>> indexAndUrls = new HashMap<>();

        try {
            // Parse request để lấy các field và file
            List<FileItem> items = upload.parseRequest(request);

            for (FileItem item : items) {
                String fieldName = item.getFieldName();
                if (item.isFormField()) {
                    // Xử lý các trường văn bản
                    String value = item.getString("UTF-8");
                    switch (fieldName) {
                        case "productName" -> productName = value;
                        case "productSize" -> productSize = value;
                        case "productMaterial" -> productMaterial = value;
                        case "category" -> categoryId = Integer.parseInt(value);
                        case "brand" -> brandId = Integer.parseInt(value);
                        case "productStock" -> stock = Integer.parseInt(value);
                        case "status" -> statusValue = Integer.parseInt(value);
                        case "regularPrice" -> regular = Double.parseDouble(value);
                        case "salePrice" -> sale = Double.parseDouble(value);
                        case "about" -> about = value;
                        default -> {
                            if (fieldName.startsWith("productColor[")) {
                                int index = extractIndex(fieldName);
                                if (index >= 0 && index < 100) {
                                    colors[index] = value;
                                }
                            }
                        }
                    }
                } else if (fieldName.startsWith("productImg[")) {
                    // Xử lý file ảnh
                    int index = extractIndex(fieldName);
                    if (item.getSize() > 0) {
                        String fileName = FileUtil.uploadImage(uploadPath, item);
                        String fileUrl = "/uploads/" + fileName; // lưu đường dẫn tương đối
                        indexAndUrls.computeIfAbsent(index, k -> new ArrayList<>()).add(fileUrl);
                    }
                }
            }

            // Tạo danh sách các cặp URL ảnh và mã màu
            for (Map.Entry<Integer, List<String>> indexAndUrl:  indexAndUrls.entrySet()) {
                Integer index = indexAndUrl.getKey();
                List<String> urls = indexAndUrl.getValue();

//                urls.forEach(url -> imgUrlAndColor.add(url + "**" + colors[index]));
                //Cho 1 ảnh đại diện cho 1 màu
                for (int i = 0; i < urls.size(); i++) {
                    if(i==0)
                        imgUrlAndColor.add(urls.get(i) + "**" + colors[index]);
                    else
                        imgUrlAndColor.add(urls.get(i));
                }

            }

            // Nối các url bằng ký tự "||"
            if (!imgUrlAndColor.isEmpty()) {
                imgUrlsConcat.append(String.join("||", imgUrlAndColor));
            }

            Product product = new Product();
            product.setProductName(productName);
            product.setProductTotal(stock);
            product.setProductCode("X123");
            product.setProductEnable(statusValue > 0);
            product.setProductDescription(about);
            product.setProductPrice(regular);
            product.setProductDiscountPrice(sale);
            product.setProductSize(productSize);
            product.setProductMaterial(productMaterial);
            product.setProductImageUrl(imgUrlsConcat.toString());

            // Tạo đối tượng Category và Brand
            Category category = new Category(categoryId, "", "", 1);
            product.setCategory(category);

            Brand brand = new Brand(brandId, "");
            product.setBrand(brand);

            // Lưu sản phẩm vào cơ sở dữ liệu
            ProductDAO productDAO = new ProductDAOImpl();
            boolean isSuccess = productDAO.saveProduct(product);


        } catch (NumberFormatException e) {
            request.setAttribute("message", "Dữ liệu số không hợp lệ: " + e.getMessage());
        } catch (Exception e) {
            request.setAttribute("message", "Lỗi xử lý: " + e.getMessage());
        }

    }

    // Lấy index của fieldName
    private int extractIndex(String fieldName) {
        try {
            String indexStr = fieldName.substring(fieldName.indexOf('[') + 1, fieldName.indexOf(']'));
            return Integer.parseInt(indexStr);
        } catch (Exception e) {
            return -1;
        }
    }
}
