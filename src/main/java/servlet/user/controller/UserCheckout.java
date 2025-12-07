package servlet.user.controller;

import servlet.configs.VnPayConfig;
import servlet.constants.OrderStatus;
import servlet.constants.PaymentMethod;
import servlet.constants.PaymentStatus;
import servlet.dao.*;
import servlet.dao.impl.*;
import servlet.models.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/customer/checkout")
public class UserCheckout extends HttpServlet {

    private ProductDAO productDAO;
    private OrderDAO orderDAO;
    private OrderItemsDAO orderItemsDAO;
    private CartDAO cartDAO;
    private ShippingAddressDAO shippingAddressDAO;

    public UserCheckout() {
        productDAO = new ProductDAOImpl();
        orderDAO = new OrderDAOImpl();
        orderItemsDAO = new OrderItemsDAOImpl();
        cartDAO = new CartDAOImpl();
        shippingAddressDAO = new ShippingAddressDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        User loggedInUser = (User) session.getAttribute("customer");

        if (loggedInUser == null) {
            resp.sendRedirect(req.getContextPath() + "/public/login");
            return;
        }

        try {
            String paymentMethod = req.getParameter("payment");
            String fullName = req.getParameter("fullName");
            String addressDetail = req.getParameter("address");
            String city = req.getParameter("city");
            String district = req.getParameter("district");
            String ward = req.getParameter("ward");
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");

            String amountStr = req.getParameter("amount");
            long totalMoney = (amountStr != null && !amountStr.isEmpty()) ? Double.valueOf(amountStr).longValue() : 0;

            if (totalMoney <= 0) {
                session.setAttribute("toast_message", "Giỏ hàng trống hoặc lỗi giá tiền!");
                session.setAttribute("toast_type", "error");
                resp.sendRedirect(req.getContextPath() + "/customer/cart");
                return;
            }

            // Tạo Order
            Order order = new Order();
            ShippingAddress shippingAddress = new ShippingAddress();

            order.setTotalPrice(totalMoney);
            order.setOrderStatus(String.valueOf(OrderStatus.PENDING));
            order.setPaymentStatus(String.valueOf(PaymentStatus.UNPAID));
            order.setCreatedAt(new Date());
            order.setUser(loggedInUser);

            String fullAddress = city + ", " + district + ", " + ward + ", " + addressDetail;
            shippingAddress.setFullname(fullName);
            shippingAddress.setAddress(fullAddress);
            shippingAddress.setPhoneNumber(phone);
            shippingAddress.setEmail(email);

            Order newOrder = orderDAO.saveOrder(order);

            List<OrderItem> orderItems = createOrderItemsFromCart(loggedInUser, newOrder);
            orderItemsDAO.saveOrderItems(orderItems);

            shippingAddress.setOrder(newOrder);
            shippingAddressDAO.saveAddress(shippingAddress);

            for (OrderItem orderItem : orderItems) {
                Product product = productDAO.findById(orderItem.getProduct().getProductId());

                product.setProductTotal(product.getProductTotal() - orderItem.getOrderQuantity());

                productDAO.editProduct(product.getProductId(), product);

                cartDAO.deleteCartItem(orderItem.getProduct().getProductId(), loggedInUser.getUserId());
            }

            // Xử lý Payment Method
            if ("vnpay".equals(paymentMethod)) {
                orderDAO.updatePaymentMethod(newOrder.getOrderId(), PaymentMethod.BANK_TRANSFER);

                String vnp_Version = "2.1.0";
                String vnp_Command = "pay";
                String orderType = "other";
                long amount = totalMoney * 100;

                String vnp_TxnRef = String.valueOf(newOrder.getOrderId());
                String vnp_IpAddr = VnPayConfig.getIpAddress(req);
                String vnp_TmnCode = VnPayConfig.vnp_TmnCode;

                Map<String, String> vnp_Params = new HashMap<>();
                vnp_Params.put("vnp_Version", vnp_Version);
                vnp_Params.put("vnp_Command", vnp_Command);
                vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
                vnp_Params.put("vnp_Amount", String.valueOf(amount));
                vnp_Params.put("vnp_CurrCode", "VND");
                vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
                vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang #" + vnp_TxnRef);
                vnp_Params.put("vnp_OrderType", orderType);
                vnp_Params.put("vnp_Locale", "vn");

                // Quan trọng: Cần cấu hình đúng ReturnUrl để hứng kết quả trả về từ VNPAY
                vnp_Params.put("vnp_ReturnUrl", VnPayConfig.vnp_ReturnUrl);
                vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

                Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                String vnp_CreateDate = formatter.format(cld.getTime());
                vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

                cld.add(Calendar.MINUTE, 15);
                String vnp_ExpireDate = formatter.format(cld.getTime());
                vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

                // Build Query URL
                List fieldNames = new ArrayList(vnp_Params.keySet());
                Collections.sort(fieldNames);
                StringBuilder hashData = new StringBuilder();
                StringBuilder query = new StringBuilder();
                Iterator itr = fieldNames.iterator();
                while (itr.hasNext()) {
                    String fieldName = (String) itr.next();
                    String fieldValue = (String) vnp_Params.get(fieldName);
                    if ((fieldValue != null) && (fieldValue.length() > 0)) {
                        // Build hash data
                        hashData.append(fieldName);
                        hashData.append('=');
                        hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                        // Build query
                        query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                        query.append('=');
                        query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                        if (itr.hasNext()) {
                            query.append('&');
                            hashData.append('&');
                        }
                    }
                }
                String queryUrl = query.toString();
                String vnp_SecureHash = VnPayConfig.hmacSHA512(VnPayConfig.secretKey, hashData.toString());
                queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
                String paymentUrl = VnPayConfig.vnp_PayUrl + "?" + queryUrl;

                resp.sendRedirect(paymentUrl);

            } else {
                orderDAO.updatePaymentMethod(newOrder.getOrderId(), PaymentMethod.CASH_ON_DELIVERY);

                // Thông báo thành công
                session.setAttribute("toast_message", "Đặt hàng thành công!");
                session.setAttribute("toast_type", "success");
                resp.sendRedirect(req.getContextPath() + "/public/home");
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("toast_message", "Lỗi đặt hàng: " + e.getMessage());
            session.setAttribute("toast_type", "error");
            resp.sendRedirect(req.getContextPath() + "/customer/checkout-view");
        }
    }

    private List<OrderItem> createOrderItemsFromCart(User loggedInUser, Order order) {
        List<OrderItem> orderItems = new ArrayList<>();
        List<Cart> cartItems = cartDAO.getCartByUserId(loggedInUser.getUserId());

        cartItems.forEach(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(item.getProduct());

            double sellingPrice = item.getProduct().getProductDiscountPrice() > 0
                    ? item.getProduct().getProductDiscountPrice()
                    : item.getProduct().getProductPrice();

            orderItem.setOrderPrice(sellingPrice);
            orderItem.setOrderQuantity(item.getQuantity());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        });

        return orderItems;
    }
}