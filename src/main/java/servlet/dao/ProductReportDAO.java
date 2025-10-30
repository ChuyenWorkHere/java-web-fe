package servlet.dao;

import servlet.response.ReportResponse;
import servlet.response.TopProductSold;

import java.util.List;

public interface ProductReportDAO {

    List<TopProductSold> getTopSoldProduct(int month, int year, int top);

    ReportResponse<Integer> buildChartData(int month, int year);
}
