package servlet.admin.view;

import servlet.dao.OrderDAO;
import servlet.dao.impl.OrderDAOImpl;
import servlet.models.Order;
import servlet.utils.ProductUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


@WebServlet("/admin/orders-view")
public class OrdersView extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public OrdersView() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        request.setAttribute("view", "order");
        RequestDispatcher headerDispatcher = request.getRequestDispatcher("/admin/header-view");
        headerDispatcher.include(request, response);

        String pageNo = request.getParameter("pageNo");
        int currentPage = pageNo != null ? Integer.parseInt(pageNo) : 1;

        String priceRange = request.getParameter("priceRange");
        String ordersStatus = request.getParameter("ordersStatus");
        String paymentStatus = request.getParameter("paymentStatus");
        String paymentMethod = request.getParameter("paymentMethod");
        String orderSort = request.getParameter("orderSort");

        OrderDAO orderDAO = new OrderDAOImpl();

        List<Order> orders = orderDAO.getAllOrders(currentPage, 12, priceRange,
                ordersStatus, paymentStatus, paymentMethod, orderSort);
        List<Map<String, Integer>> orderStatusCount = orderDAO.orderStatusCount();
        int pageNumbers = (int) Math.ceil((double) orderDAO.countAllOrders(
                ordersStatus, paymentStatus, paymentMethod) / 12);

        out.append("\"<main id=\"main\" class=\"main\">");

        out.append("    <div class=\"pagetitle d-flex justify-content-between m-0 p-0 py-2\">");
        out.append("      <h1>Đơn hàng</h1>");
        out.append("      <nav class=\"d-flex align-items-center\">");
        out.append("        <ol class=\"breadcrumb\">");
        out.append("          <li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-fill\"></i></a></li>");
        out.append("          <li class=\"breadcrumb-item active\">Đơn hàng</li>");
        out.append("        </ol>");
        out.append("      </nav>");
        out.append("    </div><!-- End Page Title -->");

        out.append("    <section class=\"section\">");
        out.append("      <!-- Container để chứa alert -->");
        out.append("      <div id=\"alert-container\" class = \"z-2 position-absolute\"></div>");
        out.append("      <div class=\"row\">");
        out.append("        <div class=\"col-lg-12\">");

        out.append("          <div class=\"card mb-3\">");
        out.append("            <div class=\"card-body\">");
        out.append("              <div class=\"d-flex align-items-center mt-2 mb-3 filter-label\">");
        out.append("                <i class=\"bi bi-funnel-fill me-2\"></i>");
        out.append("                <span>BỘ LỌC</span>");
        out.append("              </div>");

        out.append("              <form id=\"orderFilterForm\" class=\"container\">");
        out.append("                <div class=\"row g-3 align-items-end\">");
        out.append("                  <!-- Khoảng giá -->");
        out.append("                  <div class=\"col-md-2\">");
        out.append("                    <label for=\"priceRange\" class=\"form-label\">Giá</label>");
        out.append("                    <select class=\"form-select\" id=\"priceRange\">");
        out.append("                      <option value=\"\">Tuỳ chọn</option>");
        out.append("                      <option value=\"ASC\">Tăng dần</option>");
        out.append("                      <option value=\"DESC\">Giảm dần</option>");
        out.append("                    </select>");
        out.append("                  </div>");

        out.append("                  <!-- Trạng thái -->");
        out.append("                  <div class=\"col-md-2\">");
        out.append("                    <label for=\"ordersStatus\" class=\"form-label\">Trạng thái</label>");
        out.append("                    <select class=\"form-select\" id=\"orderStatus\">");
        out.append("                      <option value=\"all\">Tất cả</option>");
        out.append("                      <option value=\"PENDING\">Chờ xác nhận</option>");
        out.append("                      <option value=\"SHIPPED\">Đang giao</option>");
        out.append("                      <option value=\"DELIVERED\">Thành công</option>");
        out.append("                      <option value=\"CANCELLED\">Đã hủy</option>");
        out.append("                    </select>");
        out.append("                  </div>");

        out.append("                  <!-- Trạng thái thanh toán -->");
        out.append("                  <div class=\"col-md-2\">");
        out.append("                    <label for=\"paymentMethod\" class=\"form-label\">Trạng thái TT</label>");
        out.append("                    <select class=\"form-select\" id=\"paymentMethod\">");
        out.append("                      <option value=\"all\">Tất cả</option>");
        out.append("                      <option value=\"PAID\">Đã thanh toán</option>");
        out.append("                      <option value=\"UNPAID\">Chưa thanh toán</option>");
        out.append("                    </select>");
        out.append("                  </div>");

        out.append("                  <!-- Phương thức thanh toán -->");
        out.append("                  <div class=\"col-md-2\">");
        out.append("                    <label for=\"paymentMethod\" class=\"form-label\">Phương Thức</label>");
        out.append("                    <select class=\"form-select\" id=\"paymentMethod\">");
        out.append("                      <option value=\"all\">Tất cả</option>");
        out.append("                      <option value=\"CASH_ON_DELIVERY\">COD</option>");
        out.append("                      <option value=\"BANK_TRANSFER\">Chuyển khoản</option>");
        out.append("                      <option value=\"CREDIT_CARD\">Thẻ tín dụng</option>");
        out.append("                    </select>");
        out.append("                  </div>");

        out.append("                  <!-- Sắp xếp -->");
        out.append("                  <div class=\"col-md-2\">");
        out.append("                    <label for=\"orderSort\" class=\"form-label\">Đơn đặt hàng</label>");
        out.append("                    <select class=\"form-select\" id=\"orderSort\">");
        out.append("                      <option value=\"newest\">Mới Nhất</option>");
        out.append("                      <option value=\"oldest\">Cũ nhất</option>");
        out.append("                    </select>");
        out.append("                  </div>");

        out.append("                  <!-- Nút lọc -->");
        out.append("                  <div class=\"col-md-1\">");
        out.append("                    <button type=\"button\" class=\"btn btn-primary mt-3 w-100\" onclick=\"applyOrderFilter()\">Lọc</button>");
        out.append("                  </div>");

        out.append("                </div>");
        out.append("              </form>");

        out.append("              <!-- Tổng quan số lượng đơn hàng -->");
        out.append("              <div class=\"my-4\">");
        out.append("                <h5 class=\"my-3 fw-bold\">Tổng quan đơn hàng</h5>");
        out.append("                <div class=\"row text-center g-4\">");

        for (Map<String, Integer> statusCount : orderStatusCount) {
            String status = statusCount.keySet().iterator().next();
            int total = statusCount.get(status);

            String label = "";
            String textColor = "";
            String bgColor = "";
            String idAttr = "";

            switch (status) {
                case "DELIVERED":
                    label = "Thành công";
                    bgColor = "bg-success bg-opacity-25";
                    idAttr = " id=\"successful-orders\"";
                    break;
                case "PENDING":
                    label = "Đang xử lý";
                    bgColor = "bg-warning bg-opacity-25";
                    idAttr = " id=\"processing-orders\"";
                    break;
                case "SHIPPED":
                    label = "Đang giao";
                    bgColor = "bg-primary bg-opacity-25";
                    idAttr = "";
                    break;
                case "CANCELLED":
                    label = "Đã huỷ";
                    bgColor = "bg-danger bg-opacity-25";
                    idAttr = " id=\"cancelled-orders\"";
                    break;
            }

            out.append("  <div class=\"col-md-3 col-6\">");
            out.append("    <div class=\"border rounded p-3 " + bgColor + "\">");
            out.append("      <h6 class=\"fs-5 fw-bold mt-2\">" + label + "</h6>");
            out.append("      <p class=\"fs-4 fw-bold text-dark\"" + idAttr + ">" + total + "</p>");
            out.append("    </div>");
            out.append("  </div>");
        }

        out.append("                </div>");
        out.append("              </div>");
        out.append("            </div>");

        out.append("          </div>");

        out.append("          <div class=\"card\">");
        out.append("            <div class=\"card-body\">");

        out.append("              <div class=\"table-responsive\">");

        out.append("                <!-- Table with stripped rows -->");
        out.append("                <table class=\"table table-striped datatable table-hover\">");
        out.append("                  <thead>");
        out.append("                    <tr>");
        out.append("                      <th>");
        out.append("                        Mã ĐH");
        out.append("                      </th>");
        out.append("                      <th>Khách hàng</th>");
        out.append("                      <th data-type=\"date\" data-format=\"YYYY/DD/MM\">Thời gian</th>");
        out.append("                      <th>Tổng tiền</th>");
        out.append("                      <th>Phương thức TT</th>");
        out.append("                      <th>Trạng thái TT</th>");
        out.append("                      <th>Trạng thái</th>");
        out.append("                      <th>Chức năng</th>");
        out.append("                    </tr>");
        out.append("                  </thead>");
        out.append("                  <tbody>");

        for (Order order : orders) {
            out.append("                    <tr>");
            out.append("                      <td class=\"align-middle\">DH#" + order.getOrderId() + "</td>");
            out.append("                      <td class=\"align-middle\">" + order.getUser().getFullname() + "</td>");
            out.append("                      <td class=\"align-middle\">" + ProductUtils.formatDate(order.getCreatedAt()) + "</td>");
            out.append("                      <td class=\"align-middle\">" + ProductUtils.formatNumber(order.getTotalPrice()) + "đ</td>");
            out.append("                      <td class=\"align-middle\">" +
                    ("CREDIT_CARD".equals(order.getPaymentMethod()) ? "Thẻ tín dụng"
                            : ("CASH_ON_DELIVERY".equals(order.getPaymentMethod())
                            ? "Thanh toán khi giao hàng" : "Chuyển khoản"))
                    + "</td>");
            out.append("                      <td class=\"align-middle\">" +
                    ("PAID".equals(order.getPaymentStatus()) ? "Đã thanh toán" : "Chưa thanh toán")
                    + "</td>");
            String className = "";
            String orderStatus = "";
            switch (order.getOrderStatus()) {
                case "PENDING":
                    className = "btn bg-secondary";
                    orderStatus = "Chờ xác nhận";
                    break;
                case "SHIPPED":
                    className = "bg-warning text-dark";
                    orderStatus = "Đang giao";
                    break;
                case "DELIVERED":
                    className = "bg-success";
                    orderStatus = "Thành công";
                    break;
                case "CANCELLED":
                    className = "bg-danger";
                    orderStatus = "Đã huỷ";
                    break;
            }
            out.append("                      <td class=\"align-middle\">");
            out.append("                        <span class=\"p-2 badge ");
            out.append(className + "\">" + (order.getOrderStatus().equals("PENDING")
                    ? "<a class=\"text-white\" href=\"../admin/single-order-view?orderId=" + order.getOrderId() + "\">" + orderStatus + "</a>" : orderStatus) + "</span>");
            out.append("                      </td>");
            out.append("                      <td class=\"align-middle\">");
            out.append("                           <a href=\"../admin/single-order-view?orderId=" + order.getOrderId() + "\">");
            out.append("                                <i class=\"bi bi-eye fs-6 text-secondary ms-2 pointer\"></i>");
            out.append("                           </a>");
            out.append("                           <form action=\"../admin/order-delete\" method=\"post\" style=\"display:inline;\">");
            out.append("                                <input type=\"hidden\" name=\"orderId\" value=\"" + order.getOrderId() + "\">");
            out.append("                                <button type=\"submit\" class=\"btn p-0 border-0 bg-transparent\">");
            out.append("                                    <i class=\"bi bi-trash fs-6 text-danger ms-2 pointer\"></i>");
            out.append("                                </button>");
            out.append("                            </form>");
            out.append("                        </td>");
            out.append("                    </tr>");
        }
        out.append("                  </tbody>");
        out.append("                </table>");
        out.append("                <!-- End Table with stripped rows -->");

        out.append("              </div>");

        out.append("              <div class=\"d-flex align-items-center mt-3\">");

        out.append("                <!-- Nút Xuất dữ liệu -->");

        out.append("  <div class=\"filter dropdown\">");
        out.append("    <a class=\"icon btn btn-primary\" data-bs-toggle=\"dropdown\">");
        out.append("      <i class=\"bi bi-download\"></i> Xuất Excel");
        out.append("    </a>");
        out.append("    <div class=\"dropdown-menu dropdown-menu-end p-4 shadow\" data-bs-auto-close=\"outside\" style=\"min-width: 350px;\">");

        out.append("      <h6 class=\"fw-bold mb-3\">Chọn thời gian</h6>");

        out.append("      <div id=\"monthFilterGroup\" class=\"mb-3\">");

        out.append("        <div class=\"mb-3\">");
        out.append("          <label for=\"startDate\" class=\"form-label\">Ngày bắt đầu</label>");
        out.append("          <input type=\"date\" class=\"form-control\" id=\"startDate\">");
        out.append("        </div>");

        out.append("        <div class=\"mb-3\">");
        out.append("          <label for=\"endDate\" class=\"form-label\">Ngày kết thúc</label>");
        out.append("          <input type=\"date\" class=\"form-control\" id=\"endDate\">");
        out.append("        </div>");

        out.append("        <a id=\"dateSubmit\" class=\"btn btn-primary w-100 mt-2\" href=\"#\" onclick=\"submitDateRange()\">Xuất Excel</a>");


        out.append("      </div>");

        out.append("    </div>");
        out.append("  </div>");

        out.append("                <!-- Nút Xuất dữ liệu PDF -->");

        out.append("  <div class=\"filter dropdown ms-3\">");
        out.append("    <a class=\"icon btn btn-primary\" data-bs-toggle=\"dropdown\">");
        out.append("      <i class=\"bi bi-download\"></i> Xuất PDF");
        out.append("    </a>");
        out.append("    <div class=\"dropdown-menu dropdown-menu-end p-4 shadow\" data-bs-auto-close=\"outside\" style=\"min-width: 350px;\">");

        out.append("      <h6 class=\"fw-bold mb-3\">Chọn thời gian</h6>");

        out.append("      <div id=\"monthFilterGroup\" class=\"mb-3\">");

        out.append("        <div class=\"mb-3\">");
        out.append("          <label for=\"startDatePdf\" class=\"form-label\">Ngày bắt đầu</label>");
        out.append("          <input type=\"date\" class=\"form-control\" id=\"startDatePdf\">");
        out.append("        </div>");

        out.append("        <div class=\"mb-3\">");
        out.append("          <label for=\"endDatePdf\" class=\"form-label\">Ngày kết thúc</label>");
        out.append("          <input type=\"date\" class=\"form-control\" id=\"endDatePdf\">");
        out.append("        </div>");

        out.append("        <a id=\"dateSubmitPdf\" class=\"btn btn-primary w-100 mt-2\" href=\"#\" onclick=\"submitDateRangePdf()\">Xuất PDF</a>");

        out.append("      </div>");

        out.append("    </div>");
        out.append("  </div>");

        out.append("                <!-- Pagination with icons -->");
        out.append("<nav aria-label=\"Page navigation example\" class=\"ms-auto mt-4 d-flex justify-content-center order-3 order-md-3\">");
        out.append("  <ul class=\"pagination\">");

        if (currentPage > 1) {
            out.append("    <li class=\"page-item\">");
            out.append("      <a class=\"page-link\" href=\"" +
                    getPageUrl(currentPage - 1, priceRange, ordersStatus, paymentStatus, paymentMethod, orderSort)
                    + "\" aria-label=\"Previous\">");
            out.append("        <span aria-hidden=\"true\">&laquo;</span>");
            out.append("      </a>");
            out.append("    </li>");
        }

        out.append(createPageItem(1, currentPage, priceRange, ordersStatus, paymentStatus, paymentMethod, orderSort));
        if (currentPage > 3) {
            out.append("    <li class=\"page-item disabled\"><a class=\"page-link\">...</a></li>");
        }
        if (currentPage != 1 && currentPage != pageNumbers) {
            out.append(createPageItem(currentPage, currentPage, priceRange, ordersStatus,
                    paymentStatus, paymentMethod, orderSort));
        }
        if (currentPage < pageNumbers - 2) {
            out.append("    <li class=\"page-item disabled\"><a class=\"page-link\">...</a></li>");
        }
        if (pageNumbers > 1) {
            out.append(createPageItem(pageNumbers, currentPage, priceRange, ordersStatus,
                    paymentStatus, paymentMethod, orderSort));
        }
        if (currentPage < pageNumbers) {
            out.append("    <li class=\"page-item\">");
            out.append("      <a class=\"page-link\" href=\"" +
                    getPageUrl(currentPage + 1, priceRange, ordersStatus, paymentStatus, paymentMethod, orderSort)
                    + "\" aria-label=\"Next\">");
            out.append("        <span aria-hidden=\"true\">&raquo;</span>");
            out.append("      </a>");
            out.append("    </li>");
        }

        out.append("  </ul>");
        out.append("</nav>");

        out.append("              </div>");
        out.append("            </div>");
        out.append("          </div>");
        out.append("        </div>");
        out.append("      </div>");
        out.append("    </section>");
        out.append("  </main><!-- End #main -->");
        out.append("  <script src=\"../admin/js/jquery.js\"></script>");
        out.append("  <script src=\"../admin/js/Order.js\"></script>");
        out.append("  <script src=\"../admin/js/showAlert.js\"></script>");
        out.append("  <script src=\"https://code.jquery.com/jquery-3.6.0.min.js\"></script>");
        out.append("  <script>");
        out.println("function submitDateRangePdf() {");
        out.println("    var startDate = document.getElementById(\"startDatePdf\").value;");
        out.println("    var endDate = document.getElementById(\"endDatePdf\").value;");
        out.println("");
        out.println("    // Kiểm tra xem người dùng đã nhập ngày chưa");
        out.println("    if (!startDate || !endDate) {");
        out.println("        alert(\"Vui lòng chọn cả ngày bắt đầu và ngày kết thúc.\");");
        out.println("        return;");
        out.println("    }");
        out.println("");
        out.println("    // Tạo URL để gửi yêu cầu đến servlet");
        out.println("    var url = '" + request.getContextPath() + "/admin/ExportPdfServlet?startDate=' + startDate + '&endDate=' + endDate;");
        out.println("");
        out.println("    // Chuyển hướng đến URL để xuất PDF");
        out.println("    window.location.href = url;");
        out.println("}");
        out.println("");
        out.append("  </script>");


        RequestDispatcher footerDispatcher = request.getRequestDispatcher("/admin/footer-view");
        footerDispatcher.include(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    private String createPageItem(int page, int currentPage, String priceRange, String orderStatus,
                                  String paymentStatus, String paymentMethod, String orderSort) {
        StringBuilder item = new StringBuilder();
        item.append("    <li class=\"page-item " + (page == currentPage ? "active" : "") + "\">");
        item.append("      <a class=\"page-link\" href=\"" +
                getPageUrl(page, priceRange, orderStatus, paymentStatus, paymentMethod, orderSort) + "\">"
                + page + "</a>");

        item.append("    </li>");
        return item.toString();
    }

    private String getPageUrl(int page, String priceRange, String orderStatus,
                              String paymentStatus, String paymentMethod, String orderSort) {
        String url = "../admin/orders-view?pageNo=" + page;

        if (priceRange != null && !priceRange.isEmpty()) {
            url += "&priceRange=" + priceRange;
        }
        if (orderStatus != null && !orderStatus.isEmpty()) {
            url += "&orderStatus=" + orderStatus;
        }
        if (paymentStatus != null && !paymentStatus.isEmpty()) {
            url += "&paymentStatus=" + paymentStatus;
        }
        if (paymentMethod != null && !paymentMethod.isEmpty()) {
            url += "&paymentMethod=" + paymentMethod;
        }
        if (orderSort != null && !orderSort.isEmpty()) {
            url += "&orderSort=" + orderSort;
        }

        return url;
    }

}

