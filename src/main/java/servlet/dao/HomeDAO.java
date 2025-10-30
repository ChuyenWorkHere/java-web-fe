package servlet.dao;

import servlet.response.ReportResponse;

import java.util.Map;

public interface HomeDAO {

    Map<String, Map<String,Double>> loadCard(Map<String, String> filterInfo);

    ReportResponse<String> loadChart(Map<String, String> filterInfo);

}
