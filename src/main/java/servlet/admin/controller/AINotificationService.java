package servlet.admin.controller;

public class AINotificationService {

    public static String generateSmartMessage(int cashCount, int transferCount, int creditCardCount) {
        int total = cashCount + transferCount + creditCardCount;

        if (total == 0) {
            return "🤖 Không có dữ liệu giao dịch để phân tích.";
        }

        double creditPercent = creditCardCount * 100.0 / total;
        double cashPercent = cashCount * 100.0 / total;
        double transferPercent = transferCount * 100.0 / total;

        if (creditCardCount > transferCount && creditCardCount > cashCount) {
            return "🤖 Thẻ tín dụng là phương thức phổ biến nhất tháng này (" + String.format("%.1f", creditPercent) + "%).";
        } else if (cashCount < transferCount && cashCount < creditCardCount) {
            return "🤖 Tiền mặt giảm nhẹ so với tháng trước.";
        } else {
            return "🤖 Đề xuất: Tăng ưu đãi cho chuyển khoản để cân bằng kênh thanh toán.";
        }
    }

    public static String generateYearlyMessage(int year, int cashCount, int transferCount, int creditCardCount) {
        int total = cashCount + transferCount + creditCardCount;

        if (total == 0) {
            return "🤖 Năm " + year + " không có dữ liệu giao dịch để phân tích.";
        }

        double creditPercent = creditCardCount * 100.0 / total;
        double cashPercent = cashCount * 100.0 / total;
        double transferPercent = transferCount * 100.0 / total;

        if (creditCardCount > transferCount && creditCardCount > cashCount) {
            return "🤖 Trong năm " + year + ", thẻ tín dụng chiếm ưu thế với " + String.format("%.1f", creditPercent) + "% tổng giao dịch.";
        } else if (cashCount < transferCount && cashCount < creditCardCount) {
            return "🤖 Năm " + year + " ghi nhận sự suy giảm rõ rệt của giao dịch tiền mặt.";
        } else {
            return "🤖 Phân tích năm " + year + ": Cân nhắc tăng ưu đãi cho chuyển khoản để cải thiện tỷ lệ thanh toán.";
        }
    }
    public static String generateAllTimeMessage(int cashCount, int transferCount, int creditCardCount) {
        int total = cashCount + transferCount + creditCardCount;

        if (total == 0) {
            return "🤖 Không có dữ liệu giao dịch trong toàn bộ hệ thống để phân tích.";
        }

        double creditPercent = creditCardCount * 100.0 / total;
        double cashPercent = cashCount * 100.0 / total;
        double transferPercent = transferCount * 100.0 / total;

        if (creditCardCount > transferCount && creditCardCount > cashCount) {
            return "🤖 Trong toàn bộ dữ liệu, <strong>thẻ tín dụng</strong> là phương thức phổ biến nhất ("
                    + String.format("%.1f", creditPercent) + "%). Nên tiếp tục khai thác kênh này.";
        } else if (cashCount < transferCount && cashCount < creditCardCount) {
            return "🤖 Giao dịch <strong>tiền mặt</strong> có xu hướng thấp nhất ("
                    + String.format("%.1f", cashPercent) + "%). Nên cân nhắc cắt giảm ưu đãi liên quan.";
        } else {
            return "🤖 Phân tích toàn bộ dữ liệu cho thấy sự <strong>cân bằng chưa rõ rệt</strong>. Đề xuất tăng ưu đãi cho <strong>chuyển khoản</strong> để khuyến khích người dùng sử dụng kênh này.";
        }
    }

}
