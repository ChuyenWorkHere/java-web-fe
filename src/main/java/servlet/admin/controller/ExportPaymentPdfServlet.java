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
import java.util.Comparator;
import java.util.List;

@WebServlet("/admin/ExportPaymentPdfServlet")
public class ExportPaymentPdfServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        // Định dạng ngày cho tiêu đề: yyyy-MM-dd -> dd/MM/yyyy
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat monthYearFormat = new SimpleDateFormat("MM/yyyy");

        String formattedStartDate = startDate;
        String formattedEndDate = endDate;

        try {
            formattedStartDate = outputFormat.format(inputFormat.parse(startDate));
            formattedEndDate = outputFormat.format(inputFormat.parse(endDate));
        } catch (Exception e) {
            e.printStackTrace(); // Log lỗi nếu parse thất bại
        }

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"payment-method-report.pdf\"");

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Font
            String fontPath = getServletContext().getRealPath("/resources/fonts/Roboto-Regular.ttf");
            BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(baseFont, 12, Font.NORMAL);
            Font boldFont = new Font(baseFont, 14, Font.BOLD);

            // Tiêu đề
            document.add(new Paragraph("BÁO CÁO PHƯƠNG THỨC THANH TOÁN", boldFont));
            document.add(new Paragraph("Từ ngày " + formattedStartDate + " đến " + formattedEndDate, font));
            document.add(Chunk.NEWLINE);

            // Lấy và sắp xếp danh sách đơn hàng
            OrderDAO dao = new OrderDAOImpl();
            List<Order> orders = dao.getAllOrdersByDate(startDate, endDate);
            orders.sort(Comparator.comparing(Order::getCreatedAt)); // Sắp xếp tăng dần theo thời gian

            // Tạo bảng với 4 cột: STT, Mã đơn, Phương thức, Tháng/Năm
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1, 2.5f, 2.5f, 6});

            // Header
            addTableHeader(table, font, "STT", "Thời gian", "Mã đơn", "Phương thức thanh toán");

            // Dữ liệu bảng
            int index = 1;
            for (Order o : orders) {
                table.addCell(createCell(String.valueOf(index++), font));
                table.addCell(createCell(monthYearFormat.format(o.getCreatedAt()), font));
                table.addCell(createCell(String.valueOf(o.getOrderId()), font));
                table.addCell(createCell(convertPaymentMethod(o.getPaymentMethod()), font));
            }

            document.add(table);
            // Đếm số lượng theo phương thức
            int creditCardCount = 0;
            int codCount = 0;
            int bankTransferCount = 0;

            for (Order o : orders) {
                switch (o.getPaymentMethod()) {
                    case "CREDIT_CARD":
                        creditCardCount++;
                        break;
                    case "CASH_ON_DELIVERY":
                        codCount++;
                        break;
                    case "BANK_TRANSFER":
                        bankTransferCount++;
                        break;
                }
            }

// Thêm tổng kết vào cuối
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Tổng số đơn hàng theo phương thức thanh toán:", boldFont));
            document.add(new Paragraph("- Thẻ tín dụng: " + creditCardCount + " đơn", font));
            document.add(new Paragraph("- Tiền mặt khi nhận hàng: " + codCount + " đơn", font));
            document.add(new Paragraph("- Chuyển khoản ngân hàng: " + bankTransferCount + " đơn", font));


        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            document.close();
        }
    }

    private void addTableHeader(PdfPTable table, Font font, String... headers) {
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, font));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPaddingTop(4f);
            cell.setPaddingBottom(4f);
            table.addCell(cell);
        }
    }

    private String convertPaymentMethod(String code) {
        switch (code) {
            case "CREDIT_CARD": return "Thẻ tín dụng";
            case "CASH_ON_DELIVERY": return "Tiền mặt khi nhận hàng";
            case "BANK_TRANSFER": return "Chuyển khoản ngân hàng";
            default: return "Không xác định";
        }
    }

    private PdfPCell createCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPaddingTop(4f);
        cell.setPaddingBottom(4f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }
}
