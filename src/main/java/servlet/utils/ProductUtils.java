package servlet.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProductUtils {

    public static String toUrlStr(String urlAndColor) {
        return urlAndColor.split("\\*\\*")[0];
    }
    public static String toColorStr(String urlAndColor) {
        return urlAndColor.split("\\*\\*")[1];
    }

    public static String[] colorArray(String urlAndColors) {
        return toColorStr(urlAndColors).split("\\|\\|");
    }

    public static List<String> allColorsArray(List<String> urlAndColors) {
        List<String> colors = new ArrayList<>();
        for(String temp: urlAndColors) {
            String[] hexColors = colorArray(temp);
            for (int i = 0; i < hexColors.length; i++) {
                if(!colors.contains(hexColors[i])){
                    colors.add(hexColors[i]);
                }
            }
        }
        return colors;
    }

    public static String[] urlArray(String urlAndColors) {
        return toUrlStr(urlAndColors).split("\\|\\|");
    }

    public static String formatNumber (double number) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(number);
    }

    // Hàm định dạng tùy theo giờ
    public static String formatDate(Date date) {
        // Tách giờ, phút, giây ra để kiểm tra
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int millisecond = calendar.get(Calendar.MILLISECOND);

        // Nếu đúng 00:00:00.0 thì chỉ hiển thị ngày
        if (hour == 0 && minute == 0 && second == 0 && millisecond == 0) {
            SimpleDateFormat dateOnlyFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateOnlyFormat.format(date);
        } else {
            SimpleDateFormat fullFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return fullFormat.format(date);
        }
    }
}
