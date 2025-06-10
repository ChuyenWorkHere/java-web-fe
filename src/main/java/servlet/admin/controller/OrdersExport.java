package servlet.admin.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import servlet.dao.OrderDAO;
import servlet.dao.UserDAO;
import servlet.dao.impl.OrderDAOImpl;
import servlet.dao.impl.UserDAOImpl;
import servlet.models.Order;
import servlet.utils.ProductUtils;

@WebServlet("/admin/export-order-excel")
public class OrdersExport extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public OrdersExport() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        if (startDate == null || endDate == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing date parameters");
            return;
        }

        OrderDAO orderDAO = new OrderDAOImpl();
        List<Order> orders = null;

        try {
            orders = orderDAO.getAllOrdersByDate(startDate, endDate);

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Orders");

            // Tạo header
            Row header = sheet.createRow(0);
            String[] columns = {"Mã hoá đơn", "Ngày tạo", "Tên khách hàng", "Tổng tiền", "Trạng thái đơn", "Trạng thái thanh toán", "Phương thức thanh toán"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(columns[i]);
                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }

            // Đổ dữ liệu từng dòng
            int rowNum = 1;
            for (Order order : orders) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue("DH#" + order.getOrderId());

                // Format ngày tạo
                Cell dateCell = row.createCell(1);
                dateCell.setCellValue(order.getCreatedAt());
                CellStyle dateCellStyle = workbook.createCellStyle();
                CreationHelper createHelper = workbook.getCreationHelper();
                dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss"));
                dateCell.setCellStyle(dateCellStyle);

                row.createCell(2).setCellValue(order.getUser().getFullname());
                row.createCell(3).setCellValue(ProductUtils.formatNumber(order.getTotalPrice()));
                row.createCell(4).setCellValue(orderStatus(order.getOrderStatus()));
                row.createCell(5).setCellValue(paymentStatus(order.getPaymentStatus()));
                row.createCell(6).setCellValue(paymentMethod(order.getPaymentMethod()));
            }

            // Tự động chỉnh cột vừa dữ liệu
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Thiết lập response headers để trình duyệt tải file
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=orders.xlsx");

            // Ghi workbook vào OutputStream
            try (OutputStream out = response.getOutputStream()) {
                workbook.write(out);
            } finally {
                workbook.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi xuất file Excel");
        }

//        response.sendRedirect("../admin/orders-view");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    private String orderStatus(String orderStatus){
        switch(orderStatus){
            case "PENDING":
                return "Chờ xác nhận";
            case "SHIPPED":
                return  "Đang giao";
            case "DELIVERED":
                return "Thành công";
            case "CANCELLED":
                return "Đã huỷ";
        }
        return "";
    }

    private String paymentMethod(String paymentMethod){
        switch(paymentMethod){
            case "CREDIT_CARD":
                return "Thẻ tín dụng";
            case "CASH_ON_DELIVERY":
                return  "Thanh toán khi giao hàng";
            case "BANK_TRANSFER":
                return "Chuyển khoản";
        }
        return "";
    }

    private String paymentStatus(String paymentStatus){
        switch(paymentStatus){
            case "PAID":
                return "Đã chuyển khoản";
            case "UNPAID":
                return  "Chưa chuyển khoản";
        }
        return "";
    }
}
