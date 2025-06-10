package servlet.dao;

import servlet.models.Product;
import servlet.response.ReportChartResponse;
import servlet.response.TopProductSold;

import java.util.List;

public interface ProductReportDAO {

    List<TopProductSold> getTopSoldProduct(int month, int year, int top);

    ReportChartResponse<Integer> buildChartData(int month, int year);
}
