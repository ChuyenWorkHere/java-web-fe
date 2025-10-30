package servlet.dao;

import java.util.Map;

public interface SalesDAO {
    double getDailySale(int day, int month, int year);
    double getMonthlySale(int month, int year);
    double getYearlySale(int year);
    Map<Integer, Double> reportChartBuilder(int month, int year);
}
