package servlet.user.controller;

import servlet.dao.UserDAO;
import servlet.dao.impl.UserDAOImpl;
import servlet.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet("/customer/profile/update")
public class ProfileUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public ProfileUpdate() {
        super();
        this.userDAO = new UserDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("customer");

        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/public/login");
            return;
        }

        String fullName = request.getParameter("nameInput");
        String gender = request.getParameter("genderInput");
        String phone = request.getParameter("phoneInput");

        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String convince = request.getParameter("convince");
        String addressDetail = request.getParameter("addressInput");

        // 2. Validate
        boolean hasError = false;
        String errorMessage = "";

        if (fullName == null || fullName.trim().isEmpty()) {
            hasError = true;
            errorMessage = "Họ tên không được để trống.";
        }

        if (phone != null && !phone.trim().isEmpty()) {
            if (!Pattern.matches("^0\\d{9}$", phone.trim())) {
                hasError = true;
                errorMessage = "Số điện thoại không đúng định dạng.";
            }
        }

        if (hasError) {
            session.setAttribute("toast_message", errorMessage);
            session.setAttribute("toast_type", "error");
        } else {
            try {
                String fullAddress = "";
                if (city != null) fullAddress += city.trim();
                fullAddress += "##";
                if (district != null) fullAddress += district.trim();
                fullAddress += "##";
                if (convince != null) fullAddress += convince.trim();
                fullAddress += "##";
                if (addressDetail != null) fullAddress += addressDetail.trim();

                currentUser.setFullname(fullName.trim());
                currentUser.setGender(gender);
                currentUser.setPhoneNumber(phone.trim());
                currentUser.setAddress(fullAddress);

                // Gọi DAO
                boolean isUpdated = userDAO.updateUserProfile(currentUser);

                if (isUpdated) {
                    session.setAttribute("customer", currentUser);
                    session.setAttribute("toast_message", "Cập nhật hồ sơ thành công!");
                    session.setAttribute("toast_type", "success");
                } else {
                    session.setAttribute("toast_message", "Cập nhật thất bại. Vui lòng thử lại!");
                    session.setAttribute("toast_type", "error");
                }
            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute("toast_message", "Lỗi hệ thống: " + e.getMessage());
                session.setAttribute("toast_type", "error");
            }
        }

        response.sendRedirect(request.getContextPath() + "/customer/profile");
    }
}