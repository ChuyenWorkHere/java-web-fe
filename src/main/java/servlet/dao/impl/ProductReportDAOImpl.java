package servlet.dao.impl;

import servlet.dao.ProductDAO;
import servlet.dao.ProductReportDAO;
import servlet.models.Product;
import servlet.response.ReportChartResponse;
import servlet.response.TopProductSold;
import servlet.utils.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductReportDAOImpl implements ProductReportDAO {

    private final ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public List<TopProductSold> getTopSoldProduct(int month, int year, int top) {
        List<TopProductSold> productsReports = new ArrayList<>();

        StringBuilder sqlSearch = new StringBuilder();
        sqlSearch.append("SELECT oi.product_id, SUM(oi.order_quantity) AS total_sold");
        sqlSearch.append(" FROM orders o");
        sqlSearch.append(" JOIN order_items oi ON o.order_id = oi.order_id ");
        sqlSearch.append(" WHERE  (o.order_status = 'DELIVERED' OR  o.payment_status = 'PAID') ");
        if (month > 0) {
            sqlSearch.append(" AND MONTH(o.created_at) = ? ");
        }
        if (year > 0) {
            sqlSearch.append(" AND YEAR(o.created_at) = ? ");
        }
        sqlSearch.append(" GROUP BY oi.product_id");
        sqlSearch.append(" ORDER BY total_sold DESC");
        sqlSearch.append(" LIMIT ?");
        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlSearch.toString())) {
            int index = 1;

            if (month > 0) {
                stmt.setInt(index++, month);
            }
            if (year > 0) {
                stmt.setInt(index++, year);
            }
            if (top > 0) {
                stmt.setInt(index++, top);
            }

            System.out.println(sqlSearch);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                int totalSold = rs.getInt("total_sold");

                Product product = productDAO.findById(productId);

                productsReports.add(new TopProductSold(product, totalSold));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productsReports;
    }

    @Override
    public ReportChartResponse<Integer> buildChartData(int month, int year) {
        ReportChartResponse<Integer> reportChartResponse = new ReportChartResponse<>();

        List<String> labels = new ArrayList<>();

        StringBuilder query = new StringBuilder();
        if (month > 0 && year > 0) {
            query = queryDailyProductSoldBuilder();
        }
        if (month <= 0 && year > 0) {
            query = queryMonthlyProductSoldBuilder();
        }

        try (Connection conn = DataSourceUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {
            int index = 1;

            if (month > 0 && year > 0) {
                stmt.setInt(index++, year);
                stmt.setInt(index++, month);
                stmt.setInt(index++, year);
                stmt.setInt(index++, month);
                stmt.setInt(index++, year);
                stmt.setInt(index++, month);
            }
            if (month <= 0 && year > 0) {
                stmt.setInt(index++, year);
                stmt.setInt(index++, year);
            }

            System.out.println(query);
            ResultSet rs = stmt.executeQuery();

            if (month > 0 && year > 0) {
                for (int i = 1; i <= 31; i++) {
                    labels.add(i + "");
                }
                while (rs.next()) {
                    int productId = rs.getInt("product_id");
                    reportChartResponse.getObject().computeIfAbsent(productId, k -> new ArrayList<>()).add(rs.getInt("total_quantity"));
                }
                reportChartResponse.setLabels(labels);
            }
            if (month <= 0 && year > 0) {
                for (int i = 1; i <= 12; i++) {
                    labels.add("Tháng " + i);
                }
                while (rs.next()) {
                    int productId = rs.getInt("product_id");
                    reportChartResponse.getObject().computeIfAbsent(productId, k -> new ArrayList<>()).add(rs.getInt("total_quantity"));
                }
                reportChartResponse.setLabels(labels);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportChartResponse;
    }

    public StringBuilder queryMonthlyProductSoldBuilder() {
        StringBuilder querySelectMonth = new StringBuilder();

        querySelectMonth.append("WITH TopProducts AS (");
        querySelectMonth.append("    SELECT p.product_id");
        querySelectMonth.append("    FROM order_items oi");
        querySelectMonth.append("    JOIN orders o ON oi.order_id = o.order_id");
        querySelectMonth.append("    JOIN products p ON oi.product_id = p.product_id");
        querySelectMonth.append("    WHERE YEAR(o.created_at) = ? ");
        querySelectMonth.append("    AND (o.order_status = 'DELIVERED' OR o.payment_status = 'PAID')");
        querySelectMonth.append("    GROUP BY p.product_id");
        querySelectMonth.append("    ORDER BY SUM(oi.order_quantity) DESC");
        querySelectMonth.append("    LIMIT 3");
        querySelectMonth.append("),");
        querySelectMonth.append("Months AS (");
        querySelectMonth.append("    SELECT 1 AS month UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5");
        querySelectMonth.append("    UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10");
        querySelectMonth.append("    UNION SELECT 11 UNION SELECT 12");
        querySelectMonth.append(")");
        querySelectMonth.append("SELECT");
        querySelectMonth.append("    tp.product_id,");
        querySelectMonth.append("    m.month,");
        querySelectMonth.append("    COALESCE(SUM(CASE WHEN MONTH(o.created_at) = m.month THEN oi.order_quantity ELSE 0 END), 0) AS total_quantity ");
        querySelectMonth.append("FROM TopProducts tp");
        querySelectMonth.append(" CROSS JOIN Months m");
        querySelectMonth.append(" LEFT JOIN order_items oi ON tp.product_id = oi.product_id");
        querySelectMonth.append(" LEFT JOIN orders o ON oi.order_id = o.order_id");
        querySelectMonth.append("    AND YEAR(o.created_at) = ?");
        querySelectMonth.append("    AND (o.order_status = 'DELIVERED' OR o.payment_status = 'PAID')");
        querySelectMonth.append(" GROUP BY tp.product_id, m.month");
        querySelectMonth.append(" ORDER BY tp.product_id, m.month;");

        return querySelectMonth;
    }

    public StringBuilder queryDailyProductSoldBuilder() {
        StringBuilder querySelectDay = new StringBuilder();

        querySelectDay.append("WITH TopProducts AS (");
        querySelectDay.append("    SELECT p.product_id");
        querySelectDay.append("    FROM order_items oi");
        querySelectDay.append("    JOIN orders o ON oi.order_id = o.order_id");
        querySelectDay.append("    JOIN products p ON oi.product_id = p.product_id");
        querySelectDay.append("    WHERE YEAR(o.created_at) = ? ");
        querySelectDay.append("    AND MONTH(o.created_at) = ? ");
        querySelectDay.append("    AND (o.order_status = 'DELIVERED' OR o.payment_status = 'PAID')");
        querySelectDay.append("    GROUP BY p.product_id");
        querySelectDay.append("    ORDER BY SUM(oi.order_quantity) DESC");
        querySelectDay.append("    LIMIT 3");
        querySelectDay.append("),");
        querySelectDay.append("DaysInMonth AS (");
        querySelectDay.append("    SELECT n AS day");
        querySelectDay.append("    FROM (");
        querySelectDay.append("        SELECT a.N + b.N * 10 + 1 AS n");
        querySelectDay.append("        FROM ");
        querySelectDay.append("            (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a,");
        querySelectDay.append("            (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3) b");
        querySelectDay.append("        ORDER BY n");
        querySelectDay.append("    ) numbers");
        querySelectDay.append("    WHERE n <= DAY(LAST_DAY(CONCAT(?, '-', ?, '-01'))) ");
        querySelectDay.append(")");
        querySelectDay.append("SELECT ");
        querySelectDay.append("    tp.product_id,");
        querySelectDay.append("    d.day,");
        querySelectDay.append("    COALESCE(SUM(CASE WHEN DAY(o.created_at) = d.day THEN oi.order_quantity ELSE 0 END), 0) AS total_quantity");
        querySelectDay.append(" FROM TopProducts tp");
        querySelectDay.append(" CROSS JOIN DaysInMonth d");
        querySelectDay.append(" LEFT JOIN order_items oi ON tp.product_id = oi.product_id");
        querySelectDay.append(" LEFT JOIN orders o ON oi.order_id = o.order_id ");
        querySelectDay.append("    AND YEAR(o.created_at) = ?");
        querySelectDay.append("    AND MONTH(o.created_at) = ?");
        querySelectDay.append("    AND (o.order_status = 'DELIVERED' OR o.payment_status = 'PAID')");
        querySelectDay.append(" GROUP BY tp.product_id, d.day");
        querySelectDay.append(" ORDER BY tp.product_id, d.day;");

        return querySelectDay;
    }

}
