package servlet.dao.impl;

import servlet.dao.HomeDAO;
import servlet.dao.SalesDAO;
import servlet.response.ReportResponse;
import servlet.utils.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeDAOImpl implements HomeDAO {

    private SalesDAO salesDAO = new SalesDAOImpl();

    @Override
    public Map<String, Map<String, Double>> loadCard(Map<String, String> filterInfo) {

        Map<String, Map<String, Double>> responseMap = new HashMap<>();

        StringBuilder multiQueries = new StringBuilder();
        multiQueries.append(salesCardQuery(filterInfo));
        multiQueries.append(customerCardQuery(filterInfo));
        multiQueries.append(revenueCardQuery(filterInfo));

        try(Connection connection = DataSourceUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement(multiQueries.toString())) {

            boolean hasResults = stmt.execute();

            int resultSetCount = 1;
            do {
                if (hasResults) {
                    ResultSet rs = stmt.getResultSet();

                    //Xử lý sales
                    if (resultSetCount == 1) {
                        if (rs.next()) {
                            Map<String, Double> dataMap = new HashMap<>();
                            dataMap.put("now", rs.getDouble("now"));
                            dataMap.put("before", rs.getDouble("before"));
                            responseMap.put("sales", dataMap);
                        }
                    }
                    // Xử lý customers
                    else if (resultSetCount == 2) {
                        if (rs.next()) {
                            Map<String, Double> dataMap = new HashMap<>();
                            dataMap.put("now", rs.getDouble("now"));
                            dataMap.put("before", rs.getDouble("before"));
                            responseMap.put("customers", dataMap);
                        }
                    }
                    // Xử lý revenue
                    else if (resultSetCount == 3) {
                        if (rs.next()) {
                            Map<String, Double> dataMap = new HashMap<>();
                            dataMap.put("now", rs.getDouble("now"));
                            dataMap.put("before", rs.getDouble("before"));
                            responseMap.put("revenue", dataMap);
                        }
                    }
                    rs.close();
                    resultSetCount++;
                }

                hasResults = stmt.getMoreResults();
            } while (hasResults || stmt.getUpdateCount() != -1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseMap;
    }

    @Override
    public ReportResponse<String> loadChart(Map<String, String> filterInfo) {

        ReportResponse<String> chartResponse = new ReportResponse<>();
        Map<String, List<Integer>> dataMap = new HashMap<>();
        List<String> labels = new ArrayList<>();
        LocalDate now = LocalDate.now();

        boolean isMonthView = false;
        if(filterInfo.containsKey("chart") && "month".equalsIgnoreCase(filterInfo.get("chart"))) {
            isMonthView = true;
            for ( int i = 1; i <= now.getDayOfMonth(); i++) {
                labels.add(String.valueOf(i));
            }
        } else {
            for ( int i = 1; i <= 12; i++) {
                labels.add(String.valueOf(i));
            }
        }
        chartResponse.setLabels(labels);

        try (Connection connection = DataSourceUtil.getConnection()) {

            Map<Integer, Integer> salesTempMap = new HashMap<>();
            String salesQuery = salesChartQuery(filterInfo).toString();

            try (PreparedStatement stmt = connection.prepareStatement(salesQuery);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    salesTempMap.put(rs.getInt("date"), rs.getInt("total_sales"));
                }
            }
            dataMap.put("sales", mapDataToLabels(salesTempMap, labels));


            Map<Integer, Integer> customersTempMap = new HashMap<>();
            String customersQuery = customersChartQuery(filterInfo).toString();

            try (PreparedStatement stmt = connection.prepareStatement(customersQuery);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    customersTempMap.put(rs.getInt("date"), rs.getInt("total_customers"));
                }
            }
            dataMap.put("customers", mapDataToLabels(customersTempMap, labels));


            Map<Integer, Integer> revenueTempMap = new HashMap<>();
            String revenueQuery = revenueChartQuery(filterInfo).toString();

            try (PreparedStatement stmt = connection.prepareStatement(revenueQuery);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    revenueTempMap.put(rs.getInt("date"), rs.getInt("total_revenue"));
                }
            }
            dataMap.put("revenue", mapDataToLabels(revenueTempMap, labels));

        } catch (Exception e) {
            e.printStackTrace();

            dataMap.put("sales", createEmptyList(labels.size()));
            dataMap.put("customers", createEmptyList(labels.size()));
            dataMap.put("revenue", createEmptyList(labels.size()));
        }

        chartResponse.setObject(dataMap);
        return chartResponse;
    }

    private List<Integer> mapDataToLabels(Map<Integer, Integer> dataMap, List<String> labels) {
        List<Integer> finalDataList = new ArrayList<>();
        for (String label : labels) {
            int key = Integer.parseInt(label); // Lấy ngày/tháng từ label (ví dụ: "4")
            // Lấy giá trị từ Map, nếu không có (null) thì trả về 0
            int value = dataMap.getOrDefault(key, 0);
            finalDataList.add(value);
        }
        return finalDataList;
    }

    private List<Integer> createEmptyList(int size) {
        List<Integer> emptyList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            emptyList.add(0);
        }
        return emptyList;
    }

    private StringBuilder salesChartQuery(Map<String, String> filterInfo) {
        StringBuilder salesChartQuery = new StringBuilder();

        if(filterInfo.containsKey("chart") && filterInfo.get("chart") != null) {
            String byTime = filterInfo.get("chart");
            switch (byTime.toLowerCase()) {
                case "month" -> {
                    salesChartQuery.append(" SELECT day(o.created_at) as `date`, count(*) as total_sales");
                    salesChartQuery.append(" FROM orders o");
                    salesChartQuery.append(" WHERE YEAR(o.created_at) = YEAR(curdate())");
                    salesChartQuery.append(" AND MONTH(o.created_at) = MONTH(curdate())");
                    salesChartQuery.append(" AND o.order_status = 'DELIVERED'");
                    salesChartQuery.append(" GROUP BY day(o.created_at)");
                    salesChartQuery.append(" order by `date`;");
                }
                default -> {
                    salesChartQuery.append(" SELECT month(o.created_at) as `date`, count(*) as total_sales");
                    salesChartQuery.append(" FROM orders o");
                    salesChartQuery.append(" WHERE YEAR(o.created_at) = YEAR(curdate())");
                    salesChartQuery.append(" AND o.order_status = 'DELIVERED'");
                    salesChartQuery.append(" GROUP BY `date`");
                    salesChartQuery.append(" order by `date`;");
                }
            }
        }


        return salesChartQuery;
    }
    private StringBuilder customersChartQuery(Map<String, String> filterInfo) {
        StringBuilder customersChartQuery = new StringBuilder();

        if(filterInfo.containsKey("chart") && filterInfo.get("chart") != null) {
            String byTime = filterInfo.get("chart");
            switch (byTime.toLowerCase()) {
                case "month" -> {
                    customersChartQuery.append(" SELECT day(o.user_created_date) as `date`, count(*) as total_customers");
                    customersChartQuery.append(" FROM users o");
                    customersChartQuery.append(" WHERE YEAR(o.user_created_date) = YEAR(curdate())");
                    customersChartQuery.append(" AND MONTH(o.user_created_date) = MONTH(curdate())");
                    customersChartQuery.append(" AND o.role_id = 2 ");
                    customersChartQuery.append(" GROUP BY `date`");
                    customersChartQuery.append(" order by `date`;");
                }
                default -> {
                    customersChartQuery.append(" SELECT month(o.user_created_date) as `date`, count(*) as total_customers");
                    customersChartQuery.append(" FROM users o");
                    customersChartQuery.append(" WHERE YEAR(o.user_created_date) = YEAR(curdate())");
                    customersChartQuery.append(" AND o.role_id = 2 ");
                    customersChartQuery.append(" GROUP BY `date`");
                    customersChartQuery.append(" order by `date`;");
                }
            }
        }
        return customersChartQuery;
    }
    private StringBuilder revenueChartQuery(Map<String, String> filterInfo) {
        StringBuilder revenueChartQuery = new StringBuilder();

        if(filterInfo.containsKey("chart") && filterInfo.get("chart") != null) {
            String byTime = filterInfo.get("chart");
            switch (byTime.toLowerCase()) {
                case "month" -> {
                    revenueChartQuery.append(" SELECT day(o.created_at) as `date`, sum(total_price) as total_revenue");
                    revenueChartQuery.append(" FROM orders o");
                    revenueChartQuery.append(" WHERE YEAR(o.created_at) = YEAR(curdate())");
                    revenueChartQuery.append(" AND MONTH(o.created_at) = MONTH(curdate())");
                    revenueChartQuery.append(" GROUP BY `date`");
                    revenueChartQuery.append(" order by `date`;");
                }
                default -> {
                    revenueChartQuery.append(" SELECT month(o.created_at) as `date`, sum(total_price) as total_revenue");
                    revenueChartQuery.append(" FROM orders o");
                    revenueChartQuery.append(" WHERE YEAR(o.created_at) = YEAR(curdate())");
                    revenueChartQuery.append(" GROUP BY `date`");
                    revenueChartQuery.append(" order by `date`;");
                }
            }
        }
        return revenueChartQuery;
    }

    private StringBuilder salesCardQuery(Map<String, String> filterInfo) {

        StringBuilder queryBuilder = new StringBuilder();

        LocalDate now = LocalDate.now();

        if(filterInfo.containsKey("sales") && filterInfo.get("sales") != null) {
            switch (filterInfo.get("sales")) {
                case "day" -> {
                    queryBuilder.append("SELECT ");
                    queryBuilder.append("    (SELECT COUNT(*) ");
                    queryBuilder.append("     FROM orders ");
                    queryBuilder.append("     WHERE order_status = 'DELIVERED' ");
                    queryBuilder.append("       AND DATE(created_at) = CURDATE())  AS `now`,");
                    queryBuilder.append("    (SELECT COUNT(*) ");
                    queryBuilder.append("     FROM orders ");
                    queryBuilder.append("     WHERE order_status = 'DELIVERED' ");
                    queryBuilder.append("       AND DATE(created_at) = CURDATE() - INTERVAL 1 DAY) AS `before`;");
                }
                case "month" -> {
                    queryBuilder.append("SELECT ");
                    queryBuilder.append("    (SELECT COUNT(*) ");
                    queryBuilder.append("     FROM orders ");
                    queryBuilder.append("     WHERE order_status = 'DELIVERED' ");
                    queryBuilder.append("       AND YEAR(created_at) = YEAR(CURDATE()) ");
                    queryBuilder.append("       AND MONTH(created_at) = MONTH(CURDATE())) AS `now`,");
                    queryBuilder.append("    (SELECT COUNT(*) ");
                    queryBuilder.append("     FROM orders ");
                    queryBuilder.append("     WHERE order_status = 'DELIVERED' ");
                    queryBuilder.append("       AND YEAR(created_at) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 MONTH)) ");
                    queryBuilder.append("       AND MONTH(created_at) = MONTH(DATE_SUB(CURDATE(), INTERVAL 1 MONTH))) AS `before`;");
                }
                default -> {
                    queryBuilder.append("SELECT ");
                    queryBuilder.append("    (SELECT COUNT(*) ");
                    queryBuilder.append("     FROM orders ");
                    queryBuilder.append("     WHERE order_status = 'DELIVERED' ");
                    queryBuilder.append("       AND YEAR(created_at) = YEAR(CURDATE()))  AS `now`,");
                    queryBuilder.append("    (SELECT COUNT(*) ");
                    queryBuilder.append("     FROM orders ");
                    queryBuilder.append("     WHERE order_status = 'DELIVERED' ");
                    queryBuilder.append("       AND YEAR(created_at) = YEAR(CURDATE() - INTERVAL 1 YEAR)) AS `before`;");
                }
            }
        }
        return queryBuilder;
    }

    private StringBuilder customerCardQuery(Map<String, String> filterInfo) {

        StringBuilder queryBuilder = new StringBuilder();

        LocalDate now = LocalDate.now();

        if(filterInfo.containsKey("customers") && filterInfo.get("customers") != null) {
            switch (filterInfo.get("customers")) {
                case "day" -> {
                    queryBuilder.append("SELECT ");
                    queryBuilder.append("    (SELECT COUNT(*) ");
                    queryBuilder.append("     FROM users ");
                    queryBuilder.append("     WHERE role_id = 2");
                    queryBuilder.append("       AND DATE(user_created_date) = CURDATE())  AS `now`,");
                    queryBuilder.append("    (SELECT COUNT(*) ");
                    queryBuilder.append("     FROM users ");
                    queryBuilder.append("     WHERE role_id = 2 ");
                    queryBuilder.append("	   AND DATE(user_created_date) = DATE(CURDATE() - INTERVAL 1 DAY)) AS `before`;");
                }
                case "month" -> {
                    queryBuilder.append("SELECT ");
                    queryBuilder.append("    (SELECT COUNT(*) ");
                    queryBuilder.append("     FROM users ");
                    queryBuilder.append("     WHERE role_id = 2");
                    queryBuilder.append("	   AND MONTH(user_created_date) = MONTH(CURDATE())");
                    queryBuilder.append("       AND YEAR(user_created_date) = YEAR(CURDATE()))  AS `now`,");
                    queryBuilder.append("    (SELECT COUNT(*) ");
                    queryBuilder.append("     FROM users ");
                    queryBuilder.append("     WHERE role_id = 2 ");
                    queryBuilder.append("	   AND MONTH(user_created_date) = MONTH(CURDATE() - INTERVAL 1 MONTH)");
                    queryBuilder.append("       AND YEAR(user_created_date) = YEAR(CURDATE())) AS `before`;");
                }
                default -> {
                    queryBuilder.append("SELECT ");
                    queryBuilder.append("    (SELECT COUNT(*) ");
                    queryBuilder.append("     FROM users ");
                    queryBuilder.append("     WHERE role_id = 2 ");
                    queryBuilder.append("       AND YEAR(user_created_date) = YEAR(CURDATE()))  AS `now`,");
                    queryBuilder.append("    (SELECT COUNT(*) ");
                    queryBuilder.append("     FROM users ");
                    queryBuilder.append("     WHERE role_id = 2 ");
                    queryBuilder.append("       AND YEAR(user_created_date) = YEAR(CURDATE() - INTERVAL 1 YEAR)) AS `before`;");
                }
            }
        }
        return queryBuilder;
    }
    private StringBuilder revenueCardQuery(Map<String, String> filterInfo) {

        StringBuilder queryBuilder = new StringBuilder();

        LocalDate now = LocalDate.now();

        if(filterInfo.containsKey("revenue") && filterInfo.get("revenue") != null) {
            switch (filterInfo.get("revenue")) {
                case "day" -> {
                    queryBuilder.append("SELECT ");
                    queryBuilder.append("    (SELECT COALESCE(SUM(total_price), 0) AS revenue ");
                    queryBuilder.append("     FROM orders ");
                    queryBuilder.append("     WHERE order_status = 'DELIVERED'");
                    queryBuilder.append("       AND DATE(created_at) = CURDATE())  AS `now`,");
                    queryBuilder.append("    (SELECT COALESCE(SUM(total_price), 0) AS revenue ");
                    queryBuilder.append("     FROM orders ");
                    queryBuilder.append("     WHERE order_status = 'DELIVERED' ");
                    queryBuilder.append("	   AND DATE(created_at) = DATE(CURDATE() - INTERVAL 1 DAY)) AS `before`;");
                }
                case "month" -> {
                    queryBuilder.append("SELECT ");
                    queryBuilder.append("    (SELECT COALESCE(SUM(total_price), 0) AS revenue ");
                    queryBuilder.append("     FROM orders ");
                    queryBuilder.append("     WHERE order_status = 'DELIVERED'");
                    queryBuilder.append("       AND MONTH(created_at) = MONTH(CURDATE())");
                    queryBuilder.append("       AND YEAR(created_at) = year(CURDATE()))  AS `now`,");
                    queryBuilder.append("    (SELECT COALESCE(SUM(total_price), 0) AS revenue ");
                    queryBuilder.append("     FROM orders ");
                    queryBuilder.append("     WHERE order_status = 'DELIVERED' ");
                    queryBuilder.append("       AND MONTH(created_at) = MONTH(CURDATE() - INTERVAL 1 MONTH)");
                    queryBuilder.append("	    AND YEAR(created_at) = year(CURDATE())) AS `before`;");
                }
                default -> {
                    queryBuilder.append("SELECT ");
                    queryBuilder.append("    (SELECT COALESCE(SUM(total_price), 0) AS revenue ");
                    queryBuilder.append("     FROM orders ");
                    queryBuilder.append("     WHERE order_status = 'DELIVERED'");
                    queryBuilder.append("       AND YEAR(created_at) = year(CURDATE()))  AS `now`,");
                    queryBuilder.append("    (SELECT COALESCE(SUM(total_price), 0) AS revenue ");
                    queryBuilder.append("     FROM orders ");
                    queryBuilder.append("     WHERE order_status = 'DELIVERED' ");
                    queryBuilder.append("	   AND YEAR(created_at) = year(CURDATE() - INTERVAL 1 YEAR)) AS `before`;");
                }
            }
        }
        return queryBuilder;
    }
}
