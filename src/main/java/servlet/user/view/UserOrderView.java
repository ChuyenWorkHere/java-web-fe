package servlet.user.view;

import servlet.constants.OrderStatus;
import servlet.constants.PaymentMethod;
import servlet.constants.PaymentStatus;
import servlet.dao.OrderDAO;
import servlet.dao.ReviewDAO;
import servlet.dao.impl.OrderDAOImpl;
import servlet.dao.impl.ReviewDAOImpl;
import servlet.models.Order;
import servlet.models.OrderItem;
import servlet.models.ShippingAddress;
import servlet.models.User;
import servlet.utils.ProductUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/customer/orders")
public class UserOrderView extends HttpServlet {

    private OrderDAO orderDAO;
    private ReviewDAO reviewDAO;

    public UserOrderView() {
        orderDAO = new OrderDAOImpl();
        reviewDAO = new ReviewDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        User loggedInUser = (User) session.getAttribute("customer");
        if(loggedInUser == null) {
            response.sendRedirect("../public/login");
            return;
        }

        List<Order> orders = orderDAO.findAllByUser(loggedInUser.getUserId());

        PrintWriter out = response.getWriter();
        RequestDispatcher headerDispatcher = request.getRequestDispatcher("/public/header-view");
        headerDispatcher.include(request, response);

        out.append("<main>");
        out.append("        <!-- breadcrumb-area-start -->");
        out.append("        <section class=\"breadcrumb-area\" data-background=\"../user/img/bg/page-title.png\">");
        out.append("            <div class=\"container\">");
        out.append("                <div class=\"row\">");
        out.append("                    <div class=\"col-xl-12\">");
        out.append("                        <div class=\"breadcrumb-text text-center\">");
        out.append("                            <h1>Đơn hàng</h1>");
        out.append("                            <ul class=\"breadcrumb-menu\">");
        out.append("                                <li><a href=\"../public/home\">home</a></li>");
        out.append("                                <li><span>orders</span></li>");
        out.append("                            </ul>");
        out.append("                        </div>");
        out.append("                    </div>");
        out.append("                </div>");
        out.append("            </div>");
        out.append("        </section>");
        out.append("        <!-- breadcrumb-area-end -->");
        out.append("");
        out.append("        <!-- Order History Area Start -->");
        out.append("        <section class=\"cart-area pt-100 pb-100\">");
        out.append("            <div class=\"container\">");
        out.append("                <div class=\"row\">");
        out.append("                    <div class=\"col-12\">");

        if (orders.isEmpty()) {
            out.append("                        <div class=\"text-center p-5 border rounded bg-light\">");
            out.append("                            <h4 class=\"mb-3\">Bạn chưa có đơn hàng nào</h4>");
            out.append("                            <p class=\"text-muted mb-4\">Hãy khám phá các sản phẩm tuyệt vời của chúng tôi và bắt đầu mua sắm ngay hôm nay!</p>");
            out.append("                            <a href=\"" + request.getContextPath() + "/public/shop\" class=\"btn theme-btn\">Bắt đầu mua sắm</a>");
            out.append("                        </div>");
        } else {

            for (int i = 0; i < orders.size(); i++) {

                Order order = orders.get(i);
                List<OrderItem> orderItems = order.getOrderItems();
                ShippingAddress address = order.getShippingAddress();

                out.append("                        <div class=\"order-box mb-4 p-4 border rounded\">");
                out.append("                            <div class=\"d-flex justify-content-between align-items-center mb-3\">");
                out.append("                                <h5 class=\"fw-bold mb-0\">Mã đơn hàng: #"+order.getOrderId()+"</h5>");
                out.append("                                <span class=\"fw-semibold\">");
                if (order.getOrderStatus().equals(String.valueOf(OrderStatus.PENDING))) {
                    out.append("                                    <i class=\"fas fa-clock text-warning\"></i> Chờ xác nhận");
                } else if (order.getOrderStatus().equals(String.valueOf(OrderStatus.DELIVERED))) {
                    out.append("                                    <i class=\"fas fa-check text-success\"></i> Giao hàng thành công");
                } else if (order.getOrderStatus().equals(String.valueOf(OrderStatus.SHIPPING))){
                    out.append("                                    <i class=\"fas fa-truck text-warning\"></i> Đang giao hàng");
                } else {
                    out.append("                                    <i class=\"fas fa-times-circle text-danger\"></i> Đơn hàng bị hủy");
                }
                out.append("                                </span>");
                out.append("                            </div>");
                out.append("                            <hr>");
                out.append("                            <div class=\"d-flex flex-column gap-2 w-100\">");
                out.append("                                <div class=\"d-flex justify-content-between align-items-start\">");
                out.append("                                    <div>");
                out.append("                                        <p><strong>Tên người nhận:</strong> "+address.getFullname()+"</p>");
                out.append("                                        <p><strong>Số điện thoại:</strong> "+address.getPhoneNumber()+"</p>");
                out.append("                                        <p><strong>Địa chỉ nhận hàng:</strong> "+address.getAddress()+"</p>");
                out.append("                                        <p><strong>Ngày mua:</strong> "+order.getCreatedAt()+"</p>");
                out.append("                                    </div>");
                out.append("                                    <div>");
                if (order.getOrderStatus().equals(String.valueOf(OrderStatus.PENDING))) {
                    out.append("                                    <p><strong>Trạng thái đơn hàng:</strong> Chờ xác nhận</p>");
                } else if (order.getOrderStatus().equals(String.valueOf(OrderStatus.DELIVERED))) {
                    out.append("                                    <p><strong>Trạng thái đơn hàng:</strong> Giao hàng thành công</p>");
                } else if (order.getOrderStatus().equals(String.valueOf(OrderStatus.SHIPPING))){
                    out.append("                                    <p><strong>Trạng thái đơn hàng:</strong> Đang giao hàng</p>");
                } else {
                    out.append("                                    <p><strong>Trạng thái đơn hàng:</strong> Đơn hàng bị hủy</p>");
                }

                if (order.getPaymentStatus().equals(String.valueOf(PaymentStatus.UNPAID))){
                    out.append("                                        <p><strong>Thanh toán:</strong> Chưa thanh toán</p>");
                } else {
                    out.append("                                        <p><strong>Thanh toán:</strong> Đã thanh toán</p>");
                }

                if (order.getPaymentMethod().equals(String.valueOf(PaymentMethod.BANK_TRANSFER))){
                    out.append("                                        <p><strong>Phương thức thanh toán:</strong> Chuyển khoản ngân hàng</p>");
                } else  {
                    out.append("                                        <p><strong>Phương thức thanh toán:</strong> Thanh toán khi nhận hàng</p>");
                }

                out.append("                                    </div>");
                out.append("                                </div>");
                out.append("                                <hr>");
                orderItems.forEach(item -> {
                    out.append("                                <div class=\"d-flex justify-content-between align-items-center\">");
                    out.append("                                    <div class=\"d-flex gap-4\">");
                    out.append("                                        <div>");
                    out.append("                                            <img src=\""+request.getContextPath() + ProductUtils.urlArray(item.getProduct().getProductImageUrl())[0] +"\" alt=\"product\" width=\"120\" class=\"rounded\">");
                    out.append("                                        </div>");
                    out.append("                                        <div class=\"flex-grow-1 d-flex flex-column ml-3\">");
                    out.append("                                            <h5 class=\"mb-1\">"+item.getProduct().getProductName()+"</h5>");
                    out.append("                                            <p class=\"text-muted\">Số lượng: "+item.getOrderQuantity()+"</p>");
                    if(OrderStatus.DELIVERED.toString().equals(order.getOrderStatus())){
                        if(reviewDAO.hasUserReviewedProduct(loggedInUser.getUserId(), item.getProduct().getProductId())) {
                            out.append("                                            <span class=\"bg-secondary text-center px-3 py-2 text-white pe-none align-self-start\">Đã đánh giá</span>");
                        } else {
                            out.append("                                            <a href=\""+ (request.getContextPath() + "/public/product-detail?mode=review&&productId="+item.getProduct().getProductId()) +"\" class=\"bg-primary text-center px-3 py-2 text-white align-self-start\">Đánh giá</a>");
                        }
                    }
                    out.append("                                        </div>");
                    out.append("                                    </div>");
                    out.append("                                    <div class=\"text-end\">");
                    out.append("                                        <p class=\"mb-0 text-danger fw-bold\">"+ProductUtils.formatNumber(item.getOrderPrice())+"</p>");
                    out.append("                                    </div>");
                    out.append("                                </div>");
                    out.append("                                <hr>");
                });
                out.append("                                <p class=\"text-black mb-0 text-right\">");
                out.append("                                    Thành tiền: <span class=\"text-danger fw-bold ml-1\"");
                out.append("                                        style=\"font-size: 1.2rem;\">"+ProductUtils.formatNumber(order.getTotalPrice())+"");
                out.append("                                        đ</span>");
                out.append("                                </p>");
                out.append("                            </div>");
                out.append("                        </div>");
            }
        }
        out.append("                    </div>");
        out.append("                </div>");
        out.append("            </div>");
        out.append("        </section>");
        out.append("        <!-- Order History Area End -->");
        out.append("</main>");

        RequestDispatcher footerDispatcher = request.getRequestDispatcher("/public/footer-view");
        footerDispatcher.include(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
