package servlet.dao;

import servlet.response.ReportChartResponse;

import java.util.Map;

public interface HomeDAO {

    Map<String, Map<String,Double>> loadCard(Map<String, String> filterInfo);

    ReportChartResponse<String> loadChart(Map<String, String> filterInfo);

}
