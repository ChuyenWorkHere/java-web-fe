package servlet.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductUtils {
    public static List<String> toUrlAndColor(String urlAndColor) {
        return List.of(urlAndColor.split("\\|\\|"));
    }

    public static String toUrl(String urlAndColor) {
        return urlAndColor.split("\\*\\*")[0];
    }
    public static String toColor(String urlAndColor) {
        if(urlAndColor.split("\\*\\*").length > 1)
            return urlAndColor.split("\\*\\*")[1];
        return "";
    }

    public static List<String> colorArray(String urlAndColors) {
        List<String > colorArray = new ArrayList<>();

        String[] temp = urlAndColors.split("\\|\\|");
        for (int i = 0; i < temp.length; i++) {
            if(temp[i].split("\\*\\*").length > 1)
                colorArray.add(temp[i].split("\\*\\*")[1]);
        }
        return colorArray;
    }

    public static String formatNumber (double number) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(number);
    }
}
