package servlet.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReportResponse {
    private int userId;
    private String gender;
    private String fullname;
    private String phoneNumber;
    private String email;
    private String status;
    private int totalOrders;
    private double totalAmount;
}
