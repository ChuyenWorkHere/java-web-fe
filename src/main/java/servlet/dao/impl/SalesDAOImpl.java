package servlet.dao.impl;

import servlet.dao.SalesDAO;
import servlet.utils.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Map;
import java.util.TreeMap;

public class SalesDAOImpl implements SalesDAO {
    @Override
    public double getDailySale(int day, int month, int year) {
        StringBuilder dailySalesSQL = new StringBuilder();
        dailySalesSQL.append("SELECT SUM(total_price) AS revenue");
        dailySalesSQL.append(" FROM orders");
        dailySalesSQL.append(" WHERE order_status = 'DELIVERED' and created_at = ?");
        double sales = 0;

        try(Connection connection = DataSourceUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement(dailySalesSQL.toString())) {

            // Chuẩn bị ngày từ tham số
            LocalDate date = LocalDate.of(year, month, day);
            java.sql.Date sqlDate = java.sql.Date.valueOf(date);

            stmt.setDate(1, sqlDate);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next() && rs.getObject("revenue") != null) {
                    sales = rs.getDouble("revenue");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sales;
    }

    @Override
    public double getMonthlySale(int month, int year) {
        StringBuilder sqlMonthlySales = new StringBuilder();
        sqlMonthlySales.append("SELECT SUM(total_price) AS revenue");
        sqlMonthlySales.append(" FROM orders");
        sqlMonthlySales.append(" WHERE order_status = 'DELIVERED' and MONTH(created_at) = ? and YEAR(created_at) = ?");
        sqlMonthlySales.append(" GROUP BY MONTH(created_at)");
        double sales = 0;

        try(Connection connection = DataSourceUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sqlMonthlySales.toString())) {


            stmt.setInt(1, month);
            stmt.setInt(2, year);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next() && rs.getObject("revenue") != null) {
                    sales = rs.getDouble("revenue");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sales;
    }

    @Override
    public double getYearlySale(int year) {
        StringBuilder sqlMonthlySales = new StringBuilder();
        sqlMonthlySales.append("SELECT SUM(total_price) AS revenue");
        sqlMonthlySales.append(" FROM orders");
        sqlMonthlySales.append(" WHERE order_status = 'DELIVERED' and YEAR(created_at) = ?");
        sqlMonthlySales.append(" GROUP BY YEAR(created_at)");
        double sales = 0;

        try(Connection connection = DataSourceUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sqlMonthlySales.toString())) {



            stmt.setInt(1, year);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next() && rs.getObject("revenue") != null) {
                    sales = rs.getDouble("revenue");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sales;
    }

    @Override
    public Map<Integer, Double> reportChartBuilder(int month, int year) {

        Map<Integer, Double> salesMap = new TreeMap<>();

        StringBuilder querySale = new StringBuilder();

        if(month > 0 && year > 0){
            querySale = getSalesInMonth();
        } else {
            querySale = getSalesInYear();
        }

        try (Connection connection = DataSourceUtil.getConnection();
        PreparedStatement stmt = connection.prepareStatement(querySale.toString())) {

            if(month > 0 && year > 0){
                stmt.setInt(1, year);
                stmt.setInt(2, month);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    salesMap.put(rs.getInt("sale_date"), rs.getDouble("daily_revenue"));
                }
            } else {
                stmt.setInt(1, year);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    salesMap.put(rs.getInt("sale_month"), rs.getDouble("monthly_revenue"));
                }
            }

            return foreCastSales(month, year, salesMap);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    private StringBuilder getSalesInMonth() {
        StringBuilder salesInMonth = new StringBuilder();
        salesInMonth.append(" SELECT day(created_at) AS sale_date, SUM(total_price) AS daily_revenue");
        salesInMonth.append(" FROM orders");
        salesInMonth.append(" WHERE order_status = \"DELIVERED\" AND YEAR(created_at) = ? AND MONTH(created_at) = ? ");
        salesInMonth.append(" GROUP BY day(created_at)");
        salesInMonth.append(" ORDER BY sale_date ASC;");

        return salesInMonth;
    }

    private StringBuilder getSalesInYear() {
        StringBuilder salesInYear = new StringBuilder();

        salesInYear.append(" SELECT month(created_at) AS sale_month, SUM(total_price) AS monthly_revenue");
        salesInYear.append(" FROM orders");
        salesInYear.append(" WHERE order_status = \"DELIVERED\" AND YEAR(created_at) = ?");
        salesInYear.append(" GROUP BY month(created_at)");
        salesInYear.append(" ORDER BY sale_month ASC;");

        return salesInYear;
    }

    private Map<Integer, Double> foreCastSales(int month, int year, Map<Integer, Double> salesMap) {
        Map<Integer, Double> forecastMap = new TreeMap<>(salesMap);

        LocalDate today = LocalDate.now();

        if (month > 0 && year > 0) {
            YearMonth yearMonthObject = YearMonth.of(year, month);
            int daysInMonth = yearMonthObject.lengthOfMonth();


            int cutoffDate;
            if (year < today.getYear() || (year == today.getYear() && month < today.getMonthValue())) {
                cutoffDate = daysInMonth + 1; // Đã qua hết tháng
            } else if (year == today.getYear() && month == today.getMonthValue()) {
                cutoffDate = today.getDayOfMonth();
            } else {
                cutoffDate = 1;
            }

            for (int i = 1; i < cutoffDate; i++) {
                forecastMap.putIfAbsent(i, 0.0);
            }

            for (int i = cutoffDate; i <= daysInMonth; i++) {
                if (!forecastMap.containsKey(i)) {
                    double sumSales = 0;
                    int count = 0;
                    for (int j = i - 5; j < i; j++) {
                        if (forecastMap.containsKey(j)) {
                            sumSales += forecastMap.get(j);
                            count++;
                        }
                    }
                    forecastMap.put(i, count > 0 ? sumSales / 5.0 : 0.0);
                }
            }

        } else {
            int currentMonth = today.getMonthValue();

            int cutoffMonth;
            if (year < today.getYear()) {
                cutoffMonth = 13;
            } else if (year == today.getYear()) {
                cutoffMonth = currentMonth;
            } else {
                cutoffMonth = 1;
            }

            for (int i = 1; i < cutoffMonth; i++) {
                forecastMap.putIfAbsent(i, 0.0);
            }

            for (int i = cutoffMonth; i <= 12; i++) {
                if (!forecastMap.containsKey(i)) {
                    double sumSales = 0;
                    for (int j = i - 3; j < i; j++) {
                        if (forecastMap.containsKey(j)) {
                            sumSales += forecastMap.get(j);
                        }
                    }
                    forecastMap.put(i, sumSales / 3.0);
                }
            }
        }

        return forecastMap;
    }
}
