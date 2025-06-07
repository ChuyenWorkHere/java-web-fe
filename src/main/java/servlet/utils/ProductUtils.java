package servlet.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
    public static String[] urlArray(String urlAndColors) {
        return toUrlStr(urlAndColors).split("\\|\\|");
    }

    public static String formatNumber (double number) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(number);
    }

}
