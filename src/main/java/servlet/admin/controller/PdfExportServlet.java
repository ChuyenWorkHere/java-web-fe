package servlet.admin.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.json.JSONObject;
import servlet.dao.OrdersReportDAO;
import servlet.dao.impl.OrdersReportDAOImpl;
import servlet.response.UserReportResponse;
import servlet.utils.ProductUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.Base64;
import java.util.List;

@WebServlet("/admin/exportPdf")
public class PdfExportServlet extends HttpServlet {
    private OrdersReportDAO ordersReportDAO = new OrdersReportDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }

        JSONObject jsonObject = new JSONObject(jsonBuilder.toString());
        int year = jsonObject.getInt("year");
        int month = jsonObject.has("month") ? jsonObject.getInt("month") : 0;
        String chartImageData = jsonObject.getString("chartImage");

        List<UserReportResponse> completedResults = ordersReportDAO.getTopBuyers(year, month, 8);
        List<UserReportResponse> canceledResults = ordersReportDAO.getTopCancellers(year, month, 8);

        response.setContentType("application/pdf");
        String filename = "report_" + year + (month > 0 ? "_month_" + month : "") + ".pdf";
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // ✅ Load font Unicode (arial.ttf)
            String fontPath = getServletContext().getRealPath("/WEB-INF/arial.ttf");
            BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            Font titleFont = new Font(baseFont, 18, Font.BOLD);
            Font headerFont = new Font(baseFont, 14, Font.BOLD);
            Font tableHeaderFont = new Font(baseFont, 12, Font.BOLD);
            Font cellFont = new Font(baseFont, 12);

            Paragraph title = new Paragraph("Báo cáo Top Người Mua và Người Hủy - " +
                    (month > 0 ? "Tháng " + month + " " : "") + "Năm " + year, titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Chart image
            if (chartImageData != null && !chartImageData.isEmpty()) {
                String base64Image = chartImageData.split(",")[1];
                byte[] imageBytes = Base64.getDecoder().decode(base64Image);
                Image chartImage = Image.getInstance(imageBytes);
                chartImage.scaleToFit(500, 300);
                chartImage.setAlignment(Element.ALIGN_CENTER);
                document.add(chartImage);
            }

            // Buyers table
            document.add(new Paragraph("Top Người Mua", headerFont));
            document.add(new Paragraph(" ")); // thêm dòng trống
            PdfPTable buyersTable = createUserTable(completedResults, tableHeaderFont, cellFont);
            document.add(buyersTable);

            // Cancellers table
            document.add(new Paragraph("Top Người Hủy", headerFont));
            document.add(new Paragraph(" ")); // thêm dòng trống
            PdfPTable cancellersTable = createUserTable(canceledResults, tableHeaderFont, cellFont);
            document.add(cancellersTable);

            document.close();
        } catch (Exception e) {
            throw new ServletException("Lỗi khi tạo PDF", e);
        }
    }

    private PdfPTable createUserTable(List<UserReportResponse> users, Font headerFont, Font cellFont) {
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);

        table.addCell(createCell("Tên", headerFont, true));
        table.addCell(createCell("Giới tính", headerFont, true));
        table.addCell(createCell("Số điện thoại", headerFont, true));
        table.addCell(createCell("Email", headerFont, true));
        table.addCell(createCell("Số đơn", headerFont, true));
        table.addCell(createCell("Tổng tiền", headerFont, true));

        for (UserReportResponse user : users) {
            table.addCell(createCell(user.getFullname(), cellFont, false));
            table.addCell(createCell(user.getGender(), cellFont, false));
            table.addCell(createCell(user.getPhoneNumber(), cellFont, false));
            table.addCell(createCell(user.getEmail(), cellFont, false));
            table.addCell(createCell(String.valueOf(user.getTotalOrders()), cellFont, false));
            table.addCell(createCell(ProductUtils.formatNumber(user.getTotalAmount()) + "đ", cellFont, false));
        }

        return table;
    }

    private PdfPCell createCell(String content, Font font, boolean isHeader) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        if (isHeader) {
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        }
        return cell;
    }
}
