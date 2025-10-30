//
//package servlet.admin.view;
//
//import servlet.admin.controller.AINotificationService;
//import servlet.utils.DataSourceUtil;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDate;
//
//@WebServlet("/admin/payment-report")
//public class PaymentReport extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        int cashCount = 0;
//        int transferCount = 0;
//        int creditCardCount = 0;
//
//        String month = request.getParameter("month");
//        String year = request.getParameter("year");
//        String sql = "SELECT payment_method, COUNT(*) as count FROM orders";
//
//        if (month != null && !month.isEmpty() && year != null && !year.isEmpty()) {
//            sql += " WHERE MONTH(created_at) = ? AND YEAR(created_at) = ?";
//        } else if (year != null && !year.isEmpty()) {
//            sql += " WHERE YEAR(created_at) = ?";
//        }
//
//        sql += " GROUP BY payment_method";
//
//        try (Connection connection = DataSourceUtil.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(sql);
//
//            if (month != null && !month.isEmpty() && year != null && !year.isEmpty()) {
//                statement.setInt(1, Integer.parseInt(month));
//                statement.setInt(2, Integer.parseInt(year));
//            } else if (year != null && !year.isEmpty()) {
//                statement.setInt(1, Integer.parseInt(year));
//            }
//
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                String paymentMethod = resultSet.getString("payment_method");
//                int count = resultSet.getInt("count");
//
//                switch (paymentMethod) {
//                    case "CASH_ON_DELIVERY":
//                        cashCount = count;
//                        break;
//                    case "BANK_TRANSFER":
//                        transferCount = count;
//                        break;
//                    case "CREDIT_CARD":
//                        creditCardCount = count;
//                        break;
//                }
//            }
//
//            request.setAttribute("cashCount", cashCount);
//            request.setAttribute("transferCount", transferCount);
//            request.setAttribute("creditCardCount", creditCardCount);
//            request.setAttribute("selectedMonth", month); // Pass month to JSP
//            request.setAttribute("selectedYear", year);   // Pass year to JSP
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
//            return;
//        }
//
//        response.setContentType("text/html; charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter out = response.getWriter();
//        request.setAttribute("view", "report-payment");
//
//        RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
//        headerDispatcher.include(request, response);
//
//        out.println("<main id=\"main\" class=\"main\">");
//        out.println("  <div class=\"pagetitle d-flex justify-content-between\">");
//        out.println("    <h1>Thống kê phương thức thanh toán</h1>");
//        out.println("    <nav class=\"d-flex align-items-center\">");
//        out.println("      <ol class=\"breadcrumb mb-0\">");
//        out.println("        <li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-fill\"></i></a></li>");
//        out.println("        <li class=\"breadcrumb-item active\">Thống kê</li>");
//        out.println("      </ol>");
//        out.println("    </nav>");
//        out.println("  </div>");
//
//        out.println("  <div class=\"container\">");
//        out.println("    <div class=\"row justify-content-center align-items-stretch align-items-center g-5\">");
//
//        out.println("      <div class=\"col-12 col-md-6 d-flex chart-container\">");
//        out.println("        <div class=\"card p-5 w-100 h-100 bg-white\">");
//        out.append("  <div class=\"filter dropdown\">");
//        out.append("    <a class=\"icon float-end\" data-bs-toggle=\"dropdown\">");
//        out.append("      <i class=\"bi bi-three-dots\"></i>");
//        out.append("    </a>");
//
//        out.append("    <div class=\"dropdown-menu dropdown-menu-end p-4 shadow\" data-bs-auto-close=\"outside\" style=\"min-width: 350px;\">");
//
//        out.append("      <h6 class=\"fw-bold mb-3\">Lọc theo</h6>");
//
//        out.append("      <div class=\"d-flex align-items-center mb-3 gap-3\">");
//        out.append("        <div class=\"form-check\">");
//        out.append("          <input class=\"form-check-input\" type=\"radio\" name=\"filterMode\" id=\"filterYear\" value=\"year\" checked onclick=\"toggleFilterMode()\">");
//        out.append("          <label class=\"form-check-label\" for=\"filterYear\">Năm</label>");
//        out.append("        </div>");
//        out.append("        <div class=\"form-check\">");
//        out.append("          <input class=\"form-check-input\" type=\"radio\" name=\"filterMode\" id=\"filterMonth\" value=\"month\" onclick=\"toggleFilterMode()\">");
//        out.append("          <label class=\"form-check-label\" for=\"filterMonth\">Tháng</label>");
//        out.append("        </div>");
//        out.append("      </div>");
//
//        LocalDate localDate = LocalDate.now();
//        int currentYear = localDate.getYear();
//
//        out.append("      <div id=\"yearFilterGroup\" class=\"mb-3\">");
//        out.append("        <select class=\"form-select\" id=\"yearSelectOnly\">");
//        for (int i = currentYear; i >= 2015; i--) {
//            out.append("          <option value=\"" + i + "\">" + i + "</option>");
//        }
//        out.append("        </select>");
//        out.append("        <a id=\"yearSubmit\" class=\"btn btn-primary w-100 mt-2\" href=\"#\" onclick=\"submitYear()\">Xem kết quả</a>");
//        out.append("      </div>");
//
//        out.append("      <div id=\"monthFilterGroup\" class=\"mb-3\" style=\"display: none;\">");
//        out.append("        <div class=\"d-flex gap-2 mb-2\">");
//        out.append("          <select class=\"form-select\" id=\"monthYearSelect\">");
//        for (int i = currentYear; i >= 2015; i--) {
//            out.append("            <option value=\"" + i + "\">" + i + "</option>");
//        }
//        out.append("          </select>");
//        out.append("          <select class=\"form-select\" id=\"monthSelect\" style=\"min-width: 102px;\">");
//        for (int m = 1; m <= 12; m++) {
//            out.append("            <option value=\"" + m + "\">Tháng " + m + "</option>");
//        }
//        out.append("          </select>");
//        out.append("        </div>");
//        out.append("        <a id=\"monthSubmit\" class=\"btn btn-primary w-100 mt-2\" href=\"#\" onclick=\"submitMonth()\">Xem kết quả</a>");
//        out.append("      </div>");
//
//        out.append("    </div>");
//        out.append("  </div>");
//
//        out.println("          <canvas id=\"paymentChart\" class=\"mt-4\" aria-label=\"Biểu đồ phương thức thanh toán\" role=\"img\"></canvas>");
//        out.println("        </div>");
//        out.println("      </div>");
//
//        out.println("      <div class=\"col-12 col-md-6 d-flex\">");
//        out.println("        <div class=\"card p-5 w-100 h-100 bg-white\">");
//        // Dynamic date display using scriptlet
//        out.println("          <h5>Thông tin thanh toán <span class=\"show-date badge bg-secondary float-end\">");
//        String displayDate = "Tất cả";
//        if (month != null && !month.isEmpty() && year != null && !year.isEmpty()) {
//            displayDate = month + "/" + year;
//        } else if (year != null && !year.isEmpty()) {
//            displayDate = year;
//        }
//        out.println(displayDate);
//        out.println("</span></h5>");
//        out.println("          <ul class=\"list-group list-group-flush mt-3\">");
//
//        out.println("            <li class=\"list-group-item py-3 d-flex justify-content-between align-items-center\">");
//        out.println("              <span class=\"d-flex align-items-center\">Tiền mặt</span>");
//        out.println("              <span class=\"badge px-4 py-2 bg-primary fs-5 rounded-pill\" id=\"cashCount\">" + cashCount + "</span>");
//        out.println("            </li>");
//
//        out.println("            <li class=\"list-group-item py-3 d-flex justify-content-between align-items-center\">");
//        out.println("              <span class=\"d-flex align-items-center\">Chuyển khoản</span>");
//        out.println("              <span class=\"badge px-4 py-2 bg-success fs-5 rounded-pill\" id=\"transferCount\">" + transferCount + "</span>");
//        out.println("            </li>");
//
//        out.println("            <li class=\"list-group-item py-3 d-flex justify-content-between align-items-center\">");
//        out.println("              <span class=\"d-flex align-items-center\">Thẻ tín dụng</span>");
//        out.println("              <span class=\"badge px-4 py-2 bg-warning fs-5 text-dark rounded-pill\" id=\"creditCardCount\">" + creditCardCount + "</span>");
//        out.println("            </li>");
//
//        out.println("          </ul>");
//        out.println("          <p class=\"mt-4\">Biểu đồ tròn bên cạnh thể hiện tỷ lệ phần trăm các phương thức thanh toán được sử dụng trong hệ thống.</p>");
//
//
//
//        // Notifications based on the payment counts
//        String aiMessage = AINotificationService.generateSmartMessage(cashCount, transferCount, creditCardCount);
//        out.println("<div class='alert alert-info mt-3'><strong>" + aiMessage + "</strong></div>");
//
//
//
//
//
//        out.println("        </div>");
//        out.println("      </div>");
//
//        out.println("    </div>");
//        out.println("  </div>");
//
//        // Thêm Chart.js + Plugin Datalabels để hiển thị %
//        out.println("  <script src=\"https://cdn.jsdelivr.net/npm/chart.js\"></script>");
//        out.println("  <script src=\"https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2\"></script>");
//        out.println("  <script>");
//        out.println("    const cashCount = " + cashCount + ";");
//        out.println("    const transferCount = " + transferCount + ";");
//        out.println("    const creditCardCount = " + creditCardCount + ";");
//        out.println("    const ctx = document.getElementById('paymentChart').getContext('2d');");
//        out.println("    const total = cashCount + transferCount + creditCardCount;");
//        out.println("    const paymentChart = new Chart(ctx, {");
//        out.println("      type: 'pie',");
//        out.println("      data: {");
//        out.println("        labels: ['Tiền mặt', 'Chuyển khoản', 'Thẻ tín dụng'],");
//        out.println("        datasets: [{");
//        out.println("          data: [cashCount, transferCount, creditCardCount],");
//        out.println("          backgroundColor: ['#0d6efd', '#198754', '#ffc107'],");
//        out.println("        }]");
//        out.println("      },");
//        out.println("      options: {");
//        out.println("        responsive: true,");
//        out.println("        plugins: {");
//        out.println("          legend: {");
//        out.println("            position: 'bottom',");
//        out.println("            labels: { padding: 20, font: {size:16}}");
//        out.println("          },");
//        out.println("          datalabels: {");
//        out.println("            color: '#000',");
//        out.println("            font: {");
//        out.println("              weight: 'bold',");
//        out.println("              size: 18");
//        out.println("            },");
//        out.println("            formatter: function(value, context) {");
//        out.println("              let percentage = (value / total * 100).toFixed(1);");
//        out.println("              return percentage + '%';");
//        out.println("            }");
//        out.println("          }");
//        out.println("        }");
//        out.println("      },");
//        out.println("      plugins: [ChartDataLabels]");
//        out.println("    });");
//
//        // Mã JavaScript cho các chức năng lọc
//        out.println("    function toggleFilterMode() {");
//        out.println("      const isYear = document.getElementById('filterYear').checked;");
//        out.println("      document.getElementById('yearFilterGroup').style.display = isYear ? 'block' : 'none';");
//        out.println("      document.getElementById('monthFilterGroup').style.display = isYear ? 'none' : 'block';");
//        out.println("    }");
//
//        out.println("    function submitYear() {");
//        out.println("      const year = document.getElementById('yearSelectOnly').value;");
//        out.println("      window.location.href = `../admin/payment-report?year=${year}`;");
//        out.println("    }");
//
//        out.println("    function submitMonth() {");
//        out.println("      const year = document.getElementById('monthYearSelect').value;");
//        out.println("      const month = document.getElementById('monthSelect').value;");
//        out.println("      window.location.href = `../admin/payment-report?year=${year}&month=${month}`;");
//        out.println("    }");
//
//        out.println("    document.querySelectorAll('.dropdown-menu').forEach(dropdown => {");
//        out.println("      dropdown.addEventListener('click', function (e) {");
//        out.println("        e.stopPropagation();");
//        out.println("      });");
//        out.println("    });");
//
//        out.println("    function getQueryParams() {");
//        out.println("      const params = {};");
//        out.println("      window.location.search.substring(1).split('&').forEach(pair => {");
//        out.println("        if (pair) {");
//        out.println("          const [key, value] = pair.split('=');");
//        out.println("          params[decodeURIComponent(key)] = decodeURIComponent(value || '');");
//        out.println("        }");
//        out.println("      });");
//        out.println("      return params;");
//        out.println("    }");
//
//        out.println("    window.addEventListener('DOMContentLoaded', () => {");
//        out.println("      const params = getQueryParams();");
//        out.println("      const filterYearRadio = document.getElementById('filterYear');");
//        out.println("      const filterMonthRadio = document.getElementById('filterMonth');");
//        out.println("      const yearSelectOnly = document.getElementById('yearSelectOnly');");
//        out.println("      const monthYearSelect = document.getElementById('monthYearSelect');");
//        out.println("      const monthSelect = document.getElementById('monthSelect');");
//
//        out.println("      if (params.month) {");
//        out.println("        filterMonthRadio.checked = true;");
//        out.println("        toggleFilterMode();");
//        out.println("        if (params.year) monthYearSelect.value = params.year;");
//        out.println("        monthSelect.value = params.month;");
//        out.println("      } else if (params.year) {");
//        out.println("        filterYearRadio.checked = true;");
//        out.println("        toggleFilterMode();");
//        out.println("        yearSelectOnly.value = params.year;");
//        out.println("      } else {");
//        out.println("        filterYearRadio.checked = true;");
//        out.println("        toggleFilterMode();");
//        out.println("      }");
//        out.println("    });");
//        out.println("  </script>");
//        out.println("</main>");
//
//        RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
//        footerDispatcher.include(request, response);
//    }
//}


package servlet.admin.view;

import servlet.utils.DataSourceUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/admin/payment-report")
public class PaymentReportView extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int cashCount = 0;
        int transferCount = 0;
        int creditCardCount = 0;

        String month = request.getParameter("month");
        String year = request.getParameter("year");
        String sql = "SELECT payment_method, COUNT(*) as count FROM orders";

        if (month != null && !month.isEmpty() && year != null && !year.isEmpty()) {
            sql += " WHERE MONTH(created_at) = ? AND YEAR(created_at) = ?";
        } else if (year != null && !year.isEmpty()) {
            sql += " WHERE YEAR(created_at) = ?";
        }

        sql += " GROUP BY payment_method";

        try (Connection connection = DataSourceUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);

            if (month != null && !month.isEmpty() && year != null && !year.isEmpty()) {
                statement.setInt(1, Integer.parseInt(month));
                statement.setInt(2, Integer.parseInt(year));
            } else if (year != null && !year.isEmpty()) {
                statement.setInt(1, Integer.parseInt(year));
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String paymentMethod = resultSet.getString("payment_method");
                int count = resultSet.getInt("count");

                switch (paymentMethod) {
                    case "CASH_ON_DELIVERY":
                        cashCount = count;
                        break;
                    case "BANK_TRANSFER":
                        transferCount = count;
                        break;
                    case "CREDIT_CARD":
                        creditCardCount = count;
                        break;
                }
            }

            request.setAttribute("cashCount", cashCount);
            request.setAttribute("transferCount", transferCount);
            request.setAttribute("creditCardCount", creditCardCount);
            request.setAttribute("selectedMonth", month); // Pass month to JSP
            request.setAttribute("selectedYear", year);   // Pass year to JSP

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
            return;
        }

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        request.setAttribute("view", "report-payment");

        RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
        headerDispatcher.include(request, response);

        out.println("<main id=\"main\" class=\"main\">");
        out.println("  <div class=\"pagetitle d-flex justify-content-between\">");
        out.println("    <h1>Thống kê phương thức thanh toán</h1>");
        out.println("    <nav class=\"d-flex align-items-center\">");
        out.println("      <ol class=\"breadcrumb mb-0\">");
        out.println("        <li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-fill\"></i></a></li>");
        out.println("        <li class=\"breadcrumb-item active\">Thống kê</li>");
        out.println("      </ol>");
        out.println("    </nav>");
        out.println("  </div>");

        out.println("  <div class=\"container\">");
        out.println("    <div class=\"row justify-content-center align-items-stretch align-items-center g-5\">");

        out.println("      <div class=\"col-12 col-md-6 d-flex chart-container\">");
        out.println("        <div class=\"card p-5 w-100 h-100 bg-white\">");
        out.append("  <div class=\"filter dropdown\">");
        out.append("    <a class=\"icon float-end\" data-bs-toggle=\"dropdown\">");
        out.append("      <i class=\"bi bi-three-dots\"></i>");
        out.append("    </a>");

        out.append("    <div class=\"dropdown-menu dropdown-menu-end p-4 shadow\" data-bs-auto-close=\"outside\" style=\"min-width: 350px;\">");

        out.append("      <h6 class=\"fw-bold mb-3\">Lọc theo</h6>");

        out.append("      <div class=\"d-flex align-items-center mb-3 gap-3\">");
        out.append("        <div class=\"form-check\">");
        out.append("          <input class=\"form-check-input\" type=\"radio\" name=\"filterMode\" id=\"filterYear\" value=\"year\" checked onclick=\"toggleFilterMode()\">");
        out.append("          <label class=\"form-check-label\" for=\"filterYear\">Năm</label>");
        out.append("        </div>");
        out.append("        <div class=\"form-check\">");
        out.append("          <input class=\"form-check-input\" type=\"radio\" name=\"filterMode\" id=\"filterMonth\" value=\"month\" onclick=\"toggleFilterMode()\">");
        out.append("          <label class=\"form-check-label\" for=\"filterMonth\">Tháng</label>");
        out.append("        </div>");
        out.append("      </div>");

        LocalDate localDate = LocalDate.now();
        int currentMonth = localDate.getMonthValue();
        int currentYear = localDate.getYear();

        out.append("      <div id=\"yearFilterGroup\" class=\"mb-3\">");
        out.append("        <select class=\"form-select\" id=\"yearSelectOnly\">");
        for (int i = currentYear; i >= 2015; i--) {
            out.append("          <option value=\"" + i + "\">" + i + "</option>");
        }
        out.append("        </select>");
        out.append("        <a id=\"yearSubmit\" class=\"btn btn-primary w-100 mt-2\" href=\"#\" onclick=\"submitYear()\">Xem kết quả</a>");
        out.append("      </div>");

        out.append("      <div id=\"monthFilterGroup\" class=\"mb-3\" style=\"display: none;\">");
        out.append("        <div class=\"d-flex gap-2 mb-2\">");
        out.append("          <select class=\"form-select\" id=\"monthYearSelect\">");
        for (int i = currentYear; i >= 2015; i--) {
            out.append("            <option value=\"" + i + "\">" + i + "</option>");
        }
        out.append("          </select>");
        out.append("          <select class=\"form-select\" id=\"monthSelect\" style=\"min-width: 102px;\">");
        for (int m = 1; m <= 12; m++) {
            out.append("            <option value=\"" + m + "\">Tháng " + m + "</option>");
        }
        out.append("          </select>");
        out.append("        </div>");
        out.append("        <a id=\"monthSubmit\" class=\"btn btn-primary w-100 mt-2\" href=\"#\" onclick=\"submitMonth()\">Xem kết quả</a>");
        out.append("      </div>");

        out.append("    </div>");
        out.append("  </div>");

        out.println("          <canvas id=\"paymentChart\" class=\"mt-4\" aria-label=\"Biểu đồ phương thức thanh toán\" role=\"img\"></canvas>");
        out.println("        </div>");
        out.println("      </div>");

        out.println("      <div class=\"col-12 col-md-6 d-flex\">");
        out.println("        <div class=\"card p-5 w-100 h-100 bg-white\">");
        // Dynamic date display using scriptlet
        out.println("          <h5>Thông tin thanh toán <span class=\"show-date badge bg-secondary float-end\">");
        String displayDate = "Tất cả";
        if (month != null && !month.isEmpty() && year != null && !year.isEmpty()) {
            displayDate = month + "/" + year;
        } else if (year != null && !year.isEmpty()) {
            displayDate = year;
        }
        out.println(displayDate);
        out.println("</span></h5>");
        out.println("          <ul class=\"list-group list-group-flush mt-3\">");

        out.println("            <li class=\"list-group-item py-2 d-flex justify-content-between align-items-center\">");
        out.println("              <span class=\"d-flex align-items-center\">Tiền mặt</span>");
        out.println("              <span class=\"badge px-4 py-2 bg-primary fs-5 rounded-pill\" id=\"cashCount\">" + cashCount + "</span>");
        out.println("            </li>");

        out.println("            <li class=\"list-group-item py-2 d-flex justify-content-between align-items-center\">");
        out.println("              <span class=\"d-flex align-items-center\">Chuyển khoản</span>");
        out.println("              <span class=\"badge px-4 py-2 bg-success fs-5 rounded-pill\" id=\"transferCount\">" + transferCount + "</span>");
        out.println("            </li>");

        out.println("            <li class=\"list-group-item py-2 d-flex justify-content-between align-items-center\">");
        out.println("              <span class=\"d-flex align-items-center\">Thẻ tín dụng</span>");
        out.println("              <span class=\"badge px-4 py-2 bg-warning fs-5 text-dark rounded-pill\" id=\"creditCardCount\">" + creditCardCount + "</span>");
        out.println("            </li>");

        out.println("          </ul>");
        out.println("          <p class=\"mt-4 fst-italic\">Biểu đồ tròn bên cạnh thể hiện tỷ lệ phần trăm các phương thức thanh toán được sử dụng trong hệ thống.</p>");


        out.println("<!-- Nút Xuất dữ liệu PDF phương thức thanh toán -->");
        out.println("<div class=\"filter dropdown mt-3\">");
        out.println("  <a class=\"icon btn btn-success\" data-bs-toggle=\"dropdown\">");
        out.println("    <i class=\"bi bi-download\"></i> Xuất PDF");
        out.println("  </a>");
        out.println("  <div class=\"dropdown-menu dropdown-menu-end p-4 shadow\" data-bs-auto-close=\"outside\" style=\"min-width: 350px;\">");
        out.println("    <h6 class=\"fw-bold mb-3\">Chọn thời gian</h6>");
        out.println("");
        out.println("    <div id=\"monthFilterGroupPayment\" class=\"mb-3\">");
        out.println("      <div class=\"mb-3\">");
        out.println("        <label for=\"startDatePdfPayment\" class=\"form-label\">Ngày bắt đầu</label>");
        out.println("        <input type=\"date\" class=\"form-control\" id=\"startDatePdfPayment\">");
        out.println("      </div>");
        out.println("");
        out.println("      <div class=\"mb-3\">");
        out.println("        <label for=\"endDatePdfPayment\" class=\"form-label\">Ngày kết thúc</label>");
        out.println("        <input type=\"date\" class=\"form-control\" id=\"endDatePdfPayment\">");
        out.println("      </div>");
        out.println("");
        out.println("      <a id=\"dateSubmitPdfPayment\" class=\"btn btn-success w-100 mt-2\" href=\"#\" onclick=\"submitDateRangePdfPayment()\">Xuất PDF</a>");
        out.println("    </div>");
        out.println("  </div>");
        out.println("</div>");
        out.println("");

        out.println("        </div>");
        out.println("      </div>");

        out.println("    </div>");
        out.println("  </div>");

        // Thêm Chart.js + Plugin Datalabels để hiển thị %
        out.println("  <script src=\"https://cdn.jsdelivr.net/npm/chart.js\"></script>");
        out.println("  <script src=\"https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2\"></script>");
        out.println("  <script>");
        out.println("    const cashCount = " + cashCount + ";");
        out.println("    const transferCount = " + transferCount + ";");
        out.println("    const creditCardCount = " + creditCardCount + ";");
        out.println("    const ctx = document.getElementById('paymentChart').getContext('2d');");
        out.println("    const total = cashCount + transferCount + creditCardCount;");
        out.println("    const paymentChart = new Chart(ctx, {");
        out.println("      type: 'pie',");
        out.println("      data: {");
        out.println("        labels: ['Tiền mặt', 'Chuyển khoản', 'Thẻ tín dụng'],");
        out.println("        datasets: [{");
        out.println("          data: [cashCount, transferCount, creditCardCount],");
        out.println("          backgroundColor: ['#0d6efd', '#198754', '#ffc107'],");
        out.println("        }]");
        out.println("      },");
        out.println("      options: {");
        out.println("        responsive: true,");
        out.println("        plugins: {");
        out.println("          legend: {");
        out.println("            position: 'bottom',");
        out.println("            labels: { padding: 20, font: {size:16}}");
        out.println("          },");
        out.println("          datalabels: {");
        out.println("            color: '#000',");
        out.println("            font: {");
        out.println("              weight: 'bold',");
        out.println("              size: 18");
        out.println("            },");
        out.println("            formatter: function(value, context) {");
        out.println("              let percentage = (value / total * 100).toFixed(1);");
        out.println("              return percentage + '%';");
        out.println("            }");
        out.println("          }");
        out.println("        }");
        out.println("      },");
        out.println("      plugins: [ChartDataLabels]");
        out.println("    });");

        // Mã JavaScript cho các chức năng lọc
        out.println("    function toggleFilterMode() {");
        out.println("      const isYear = document.getElementById('filterYear').checked;");
        out.println("      document.getElementById('yearFilterGroup').style.display = isYear ? 'block' : 'none';");
        out.println("      document.getElementById('monthFilterGroup').style.display = isYear ? 'none' : 'block';");
        out.println("    }");

        out.println("    function submitYear() {");
        out.println("      const year = document.getElementById('yearSelectOnly').value;");
        out.println("      window.location.href = `../admin/payment-report?year=${year}`;");
        out.println("    }");

        out.println("    function submitMonth() {");
        out.println("      const year = document.getElementById('monthYearSelect').value;");
        out.println("      const month = document.getElementById('monthSelect').value;");
        out.println("      window.location.href = `../admin/payment-report?year=${year}&month=${month}`;");
        out.println("    }");

        out.println("    document.querySelectorAll('.dropdown-menu').forEach(dropdown => {");
        out.println("      dropdown.addEventListener('click', function (e) {");
        out.println("        e.stopPropagation();");
        out.println("      });");
        out.println("    });");

        out.println("    function getQueryParams() {");
        out.println("      const params = {};");
        out.println("      window.location.search.substring(1).split('&').forEach(pair => {");
        out.println("        if (pair) {");
        out.println("          const [key, value] = pair.split('=');");
        out.println("          params[decodeURIComponent(key)] = decodeURIComponent(value || '');");
        out.println("        }");
        out.println("      });");
        out.println("      return params;");
        out.println("    }");

        out.println("    window.addEventListener('DOMContentLoaded', () => {");
        out.println("      const params = getQueryParams();");
        out.println("      const filterYearRadio = document.getElementById('filterYear');");
        out.println("      const filterMonthRadio = document.getElementById('filterMonth');");
        out.println("      const yearSelectOnly = document.getElementById('yearSelectOnly');");
        out.println("      const monthYearSelect = document.getElementById('monthYearSelect');");
        out.println("      const monthSelect = document.getElementById('monthSelect');");

        out.println("      if (params.month) {");
        out.println("        filterMonthRadio.checked = true;");
        out.println("        toggleFilterMode();");
        out.println("        if (params.year) monthYearSelect.value = params.year;");
        out.println("        monthSelect.value = params.month;");
        out.println("      } else if (params.year) {");
        out.println("        filterYearRadio.checked = true;");
        out.println("        toggleFilterMode();");
        out.println("        yearSelectOnly.value = params.year;");
        out.println("      } else {");
        out.println("        filterYearRadio.checked = true;");
        out.println("        toggleFilterMode();");
        out.println("      }");
        out.println("    });");




        out.println("function submitDateRangePdfPayment() {");
        out.println("    const startDate = document.getElementById(\"startDatePdfPayment\").value;");
        out.println("    const endDate = document.getElementById(\"endDatePdfPayment\").value;");
        out.println("");
        out.println("    if (startDate && endDate) {");
        out.println("        const url = `/Furniture/admin/ExportPaymentPdfServlet?startDate=${startDate}&endDate=${endDate}`;");
        out.println("        window.open(url, '_blank');");
        out.println("    } else {");
        out.println("        alert(\"Vui lòng chọn đủ ngày bắt đầu và kết thúc.\");");
        out.println("    }");
        out.println("}");
        out.println("");
        out.println("  </script>");
        out.println("</main>");

        RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
        footerDispatcher.include(request, response);
    }


}
