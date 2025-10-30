package servlet.utils;

import servlet.models.Product;
import servlet.response.ProductResponse;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Arrays;

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

    public static String [] imageUrlArray(String urlAndColors) {
        return toUrlStr(urlAndColors).split("\\|\\|");
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

    public static void copyProductToProductResponse(Product product, ProductResponse productResponse) {
        // Sao chép các thuộc tính đơn giản
        productResponse.setProductId(product.getProductId());
        productResponse.setProductName(product.getProductName());
        productResponse.setProductDescription(product.getProductDescription());
        productResponse.setProductPrice(product.getProductPrice());
        productResponse.setProductTotal(product.getProductTotal());
        productResponse.setProductCode(product.getProductCode());
        productResponse.setProductDiscountPrice(product.getProductDiscountPrice());
        productResponse.setProductEnable(product.isProductEnable());
        productResponse.setCreatedAt(product.getCreatedAt());
        productResponse.setProductMaterial(product.getProductMaterial());
        productResponse.setProductSize(product.getProductSize());

        // Sao chép các đối tượng liên quan
        productResponse.setCategory(product.getCategory());
        productResponse.setBrand(product.getBrand());

        // Xử lý chuỗi hình ảnh và màu sắc
        if (product.getProductImageUrl() != null && !product.getProductImageUrl().isEmpty()) {
            productResponse.setProductImageUrls(Arrays.asList(imageUrlArray(product.getProductImageUrl())));
            productResponse.setProductColors(Arrays.asList(colorArray(product.getProductImageUrl())));
        }
    }
}
