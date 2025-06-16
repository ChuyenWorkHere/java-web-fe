package servlet.dao.impl;

import servlet.dao.HomeDAO;
import servlet.dao.SalesDAO;
import servlet.response.ReportChartResponse;
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
    public ReportChartResponse<String> loadChart(Map<String, String> filterInfo) {

        ReportChartResponse<String> chartResponse = new ReportChartResponse<>();
        Map<String, List<Integer>> dataMap = new HashMap<>();
        List<String> labels = new ArrayList<>();
        LocalDate now = LocalDate.now();

        //Xử lý labels
        if(filterInfo.containsKey("chart") && filterInfo.get("chart") != null) {
            String byTime = filterInfo.get("chart");
            switch (byTime.toLowerCase()) {
                case "month" -> {
                    for ( int i = 1; i <= now.getDayOfMonth(); i++) {
                        labels.add(i+"");
                    }
                }
                default -> {
                    for ( int i = 1; i <= 12; i++) {
                        labels.add(i+"");
                    }
                }
            }
        }

        StringBuilder querySearch = new StringBuilder();
        querySearch.append(salesChartQuery(filterInfo));
        querySearch.append(customersChartQuery(filterInfo));
        querySearch.append(revenueChartQuery(filterInfo));


        try(Connection connection = DataSourceUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement(querySearch.toString())) {

            boolean hasResults = stmt.execute();

            int resultSetCount = 1;
            do {
                if (hasResults) {
                    ResultSet rs = stmt.getResultSet();

                    //Xử lý sales
                    if (resultSetCount == 1) {
                        while (rs.next()) {
                            dataMap.computeIfAbsent("sales", k -> new ArrayList<>()).add(rs.getInt("total_sales"));
                        }
                    }
                    // Xử lý customers
                    else if (resultSetCount == 2) {
                        while (rs.next()) {
                            dataMap.computeIfAbsent("customers", k -> new ArrayList<>()).add(rs.getInt("total_customers"));
                        }
                    }
                    // Xử lý revenue
                    else if (resultSetCount == 3) {
                        while (rs.next()) {
                            dataMap.computeIfAbsent("revenue", k -> new ArrayList<>()).add(rs.getInt("total_revenue"));
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
        chartResponse.setLabels(labels);
        chartResponse.setObject(dataMap);
        return chartResponse;
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
                    salesChartQuery.append(" GROUP BY day(o.created_at)");
                    salesChartQuery.append(" order by `date`;");
                }
                default -> {
                    salesChartQuery.append(" SELECT month(o.created_at) as `date`, count(*) as total_sales");
                    salesChartQuery.append(" FROM orders o");
                    salesChartQuery.append(" WHERE YEAR(o.created_at) = YEAR(curdate())");
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
                    customersChartQuery.append(" AND o.role_id = 2)");
                    customersChartQuery.append(" GROUP BY day(o.user_created_date)");
                    customersChartQuery.append(" order by `date`;");
                }
                default -> {
                    customersChartQuery.append(" SELECT day(o.user_created_date) as `date`, count(*) as total_customers");
                    customersChartQuery.append(" FROM users o");
                    customersChartQuery.append(" WHERE YEAR(o.user_created_date) = YEAR(curdate())");
                    customersChartQuery.append(" AND o.role_id = 2");
                    customersChartQuery.append(" GROUP BY day(o.user_created_date)");
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
                    revenueChartQuery.append(" GROUP BY day(o.created_at)");
                    revenueChartQuery.append(" order by `date`;");
                }
                default -> {
                    revenueChartQuery.append(" SELECT month(o.created_at) as `date`, sum(total_price) as total_revenue");
                    revenueChartQuery.append(" FROM orders o");
                    revenueChartQuery.append(" WHERE YEAR(o.created_at) = YEAR(curdate())");
                    revenueChartQuery.append(" GROUP BY month(o.created_at)");
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
