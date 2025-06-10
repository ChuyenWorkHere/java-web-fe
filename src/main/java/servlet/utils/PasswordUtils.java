package servlet.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    // Tạo hash từ mật khẩu người dùng
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt()); // độ mạnh mặc định là 10, có thể thay đổi giá trị
    }

    // Kiểm tra mật khẩu nhập vào có khớp với hash không
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
