package servlet.user.controller;

import servlet.configs.VnPayConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

@WebServlet("/public/ipn")
public class VnPayIpn extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. VNPAY GỌI TRỰC TIẾP VÀO ĐÂY VÀ ĐƯỢC XỬ LÝ NGAY LẬP TỨC
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter(); // Đảm bảo import java.io.PrintWriter
        try {
            // DÙNG TreeMap ĐỂ TỰ ĐỘNG SẮP XẾP KEY
            Map<String, String> fields = new TreeMap<>();
            for (Enumeration<String> e = req.getParameterNames(); e.hasMoreElements(); ) {
                String fieldName = e.nextElement();
                String fieldValue = req.getParameter(fieldName);
                if (fieldValue != null && !fieldValue.isEmpty()) {
                    fields.put(fieldName, fieldValue);
                }
            }

            String vnp_SecureHash = fields.remove("vnp_SecureHash");

            if (vnp_SecureHash == null) {
                out.print("{\"RspCode\":\"97\", \"Message\":\"Invalid Checksum\"}");
                out.flush();
                return;
            }

            // TẠO LẠI CHUỖI HASH ĐỂ KIỂM TRA
            StringBuilder hashData = new StringBuilder();
            for (Map.Entry<String, String> entry : fields.entrySet()) {
                if (hashData.length() > 0) {
                    hashData.append('&');
                }
                hashData.append(entry.getKey()).append('=').append(entry.getValue());
            }

            String signValue = VnPayConfig.hmacSHA512(VnPayConfig.secretKey, hashData.toString());

            if (!signValue.equals(vnp_SecureHash)) {
                out.print("{\"RspCode\":\"97\", \"Message\":\"Invalid Checksum\"}");
                out.flush();
                return;
            }

            // XỬ LÝ LOGIC (NẾU CHỮ KÝ ĐÚNG)
            String responseCode = fields.get("vnp_ResponseCode");

            // KỂ CẢ THÀNH CÔNG HAY THẤT BẠI, BẠN VẪN PHẢI TRẢ VỀ "00"
            // ĐỂ VNPAY BIẾT BẠN ĐÃ NHẬN ĐƯỢC
            if ("00".equals(responseCode)) {
                System.out.println("Thanh toan thanh cong (IPN Test)");
            } else {
                System.out.println("Thanh toan that bai (IPN Test)");
            }

            // TRẢ VỀ THÀNH CÔNG CHO VNPAY
            out.print("{\"RspCode\":\"00\", \"Message\":\"Confirm Success\"}");

        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"RspCode\":\"99\", \"Message\":\"Unknown error\"}");
        } finally {
            out.flush();
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Cho doPost gọi doGet (phòng trường hợp sau này có POST)
        try {
            doGet(req, resp);
        } catch (ServletException e) {
            throw new IOException(e);
        }
    }
}