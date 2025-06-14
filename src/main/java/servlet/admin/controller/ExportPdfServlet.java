package servlet.admin.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import servlet.dao.OrderDAO;
import servlet.dao.impl.OrderDAOImpl;
import servlet.models.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/admin/ExportPdfServlet")
public class ExportPdfServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"orders.pdf\"");

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Font
            String fontPath = getServletContext().getRealPath("/resources/fonts/Roboto-Regular.ttf");
            BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font titleFont = new Font(baseFont, 16, Font.BOLD);
            Font normalFont = new Font(baseFont, 12);
            Font boldFont = new Font(baseFont, 12, Font.BOLD);

            // Tiêu đề
            document.add(new Paragraph("BÁO CÁO ĐƠN HÀNG", titleFont));
            // Chuyển định dạng ngày từ yyyy-MM-dd -> dd/MM/yyyy
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

            String formattedStartDate = startDate;
            String formattedEndDate = endDate;

            try {
                formattedStartDate = outputFormat.format(inputFormat.parse(startDate));
                formattedEndDate = outputFormat.format(inputFormat.parse(endDate));
            } catch (Exception e) {
                e.printStackTrace(); // Log lỗi nếu có
            }
            document.add(new Paragraph("Từ " + formattedStartDate + " đến " + formattedEndDate, normalFont));
            document.add(Chunk.NEWLINE);

            // Lấy danh sách đơn hàng
            OrderDAO dao = new OrderDAOImpl();
            List<Order> orders = dao.getAllOrdersByDate(startDate, endDate);

            // Đếm trạng thái
            int delivered = 0, cancelled = 0, pending = 0, shipped = 0;
            for (Order o : orders) {
                switch (o.getOrderStatus()) {
                    case "DELIVERED": delivered++; break;
                    case "CANCELLED": cancelled++; break;
                    case "PENDING": pending++; break;
                    case "SHIPPED": shipped++; break;
                }
            }

            // Hiển thị tổng theo trạng thái
            document.add(new Paragraph("Tổng số đơn hàng theo trạng thái:", boldFont));
            document.add(new Paragraph("- Đã giao: " + delivered + " đơn", normalFont));
            document.add(new Paragraph("- Đã hủy: " + cancelled + " đơn", normalFont));
            document.add(new Paragraph("- Đang xử lý: " + pending + " đơn", normalFont));
            document.add(new Paragraph("- Đang giao: " + shipped + " đơn", normalFont));
            document.add(Chunk.NEWLINE);

            // Tạo bảng
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{2, 4, 4, 3, 4, 4});

            addTableHeader(table, baseFont, "Mã đơn", "Khách hàng", "Ngày tạo", "Tổng tiền", "Trạng thái ĐH", "Phương thức TT");

            // Định dạng ngày: dd/MM/yyyy
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (Order o : orders) {
                table.addCell(new Phrase(String.valueOf(o.getOrderId()), normalFont));
                table.addCell(new Phrase(o.getUser().getFullname(), normalFont));
                table.addCell(new Phrase(sdf.format(o.getCreatedAt()), normalFont));
                table.addCell(new Phrase(String.format("%.0f VND", o.getTotalPrice()), normalFont));
                table.addCell(new Phrase(getVietnameseOrderStatus(o.getOrderStatus()), normalFont));
                table.addCell(new Phrase(getVietnamesePaymentMethod(o.getPaymentMethod()), normalFont));
            }

            document.add(table);

        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            document.close();
        }
    }

    private void addTableHeader(PdfPTable table, BaseFont baseFont, String... headers) {
        Font headerFont = new Font(baseFont, 12, Font.BOLD);
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPaddingTop(4f);
            cell.setPaddingBottom(4f);
            table.addCell(cell);
        }
    }

    private String getVietnamesePaymentMethod(String method) {
        switch (method) {
            case "CREDIT_CARD":
                return "Thẻ tín dụng";
            case "CASH_ON_DELIVERY":
                return "Tiền mặt";
            case "BANK_TRANSFER":
                return "Chuyển khoản";
            default:
                return "Không xác định";
        }
    }

    private String getVietnameseOrderStatus(String status) {
        switch (status) {
            case "DELIVERED":
                return "Đã giao";
            case "CANCELLED":
                return "Đã hủy";
            case "SHIPPED":
                return "Đang giao";
            case "PENDING":
                return "Chờ xử lý";
            default:
                return "Không xác định";
        }
    }
}
