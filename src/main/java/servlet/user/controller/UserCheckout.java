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

        String paymentMethod = req.getParameter("payment");
        String fullName = req.getParameter("fullName");
        String address = req.getParameter("address");
        String city = req.getParameter("city");
        String district = req.getParameter("district");
        String ward = req.getParameter("ward");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        Long totalMoney = Long.parseLong(req.getParameter("amount"));

        //Tạo order
        Order order = new Order();
        ShippingAddress shippingAddress = new ShippingAddress();

        order.setTotalPrice(totalMoney);
        order.setOrderStatus(String.valueOf(OrderStatus.PENDING));
        order.setPaymentStatus(String.valueOf(PaymentStatus.UNPAID));
        order.setCreatedAt(new Date());
        order.setUser(loggedInUser);

        //Tạo shipping address
        shippingAddress.setFullname(fullName);
        shippingAddress.setAddress( city + ", " + district + ", " + ward + ", " + address);
        shippingAddress.setPhoneNumber(phone);
        shippingAddress.setEmail(email);


        switch (paymentMethod) {
            case "vnpay" -> {

                order.setPaymentMethod(String.valueOf(PaymentMethod.BANK_TRANSFER));
                Order newOrder = orderDAO.saveOrder(order);
                List<OrderItem> orderItems = createOrderItemsFromCart(loggedInUser, newOrder);
                shippingAddress.setOrder(newOrder);
                orderItemsDAO.saveOrderItems(orderItems);
                shippingAddressDAO.saveAddress(shippingAddress);

                String vnp_Version = "2.1.0";
                String vnp_Command = "pay";
                String orderType = "other";
                long amount = totalMoney * 100;
                String bankCode = req.getParameter("bankCode");

                String vnp_TxnRef = String.valueOf(newOrder.getOrderId());
                String vnp_IpAddr = VnPayConfig.getIpAddress(req);

                String vnp_TmnCode = VnPayConfig.vnp_TmnCode;

                Map<String, String> vnp_Params = new HashMap<>();
                vnp_Params.put("vnp_Version", vnp_Version);
                vnp_Params.put("vnp_Command", vnp_Command);
                vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
                vnp_Params.put("vnp_Amount", String.valueOf(amount));
                vnp_Params.put("vnp_CurrCode", "VND");

                if (bankCode != null && !bankCode.isEmpty()) {
                    vnp_Params.put("vnp_BankCode", bankCode);
                }
                vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
                vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
                vnp_Params.put("vnp_OrderType", orderType);

                String locate = req.getParameter("language");
                if (locate != null && !locate.isEmpty()) {
                    vnp_Params.put("vnp_Locale", locate);
                } else {
                    vnp_Params.put("vnp_Locale", "vn");
                }
                vnp_Params.put("vnp_ReturnUrl", VnPayConfig.vnp_ReturnUrl);
                vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

                Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                String vnp_CreateDate = formatter.format(cld.getTime());
                vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

                cld.add(Calendar.MINUTE, 15);
                String vnp_ExpireDate = formatter.format(cld.getTime());
                vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

                List fieldNames = new ArrayList(vnp_Params.keySet());
                Collections.sort(fieldNames);
                String hashData = VnPayConfig.buildHashData(vnp_Params);
                StringBuilder query = new StringBuilder();
                Iterator itr = fieldNames.iterator();
                while (itr.hasNext()) {
                    String fieldName = (String) itr.next();
                    String fieldValue = (String) vnp_Params.get(fieldName);
                    if ((fieldValue != null) && (!fieldValue.isEmpty())) {

                        //Build query
                        query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII));
                        query.append('=');
                        query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                        if (itr.hasNext()) {
                            query.append('&');
                        }
                    }
                }
                String queryUrl = query.toString();
                String vnp_SecureHash = VnPayConfig.hmacSHA512(VnPayConfig.secretKey, hashData);
                queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
                String paymentUrl = VnPayConfig.vnp_PayUrl + "?" + queryUrl;

                resp.sendRedirect(paymentUrl);
            }
            case "cod" -> {
                order.setPaymentMethod(String.valueOf(PaymentMethod.CASH_ON_DELIVERY));
                Order newOrder = orderDAO.saveOrder(order);
                List<OrderItem> orderItems = createOrderItemsFromCart(loggedInUser, newOrder);
                shippingAddress.setOrder(newOrder);
                orderItemsDAO.saveOrderItems(orderItems);
                shippingAddressDAO.saveAddress(shippingAddress);
                resp.sendRedirect(req.getContextPath() + "/customer/order-success");
            }
            default -> {
                resp.sendRedirect(req.getContextPath() + "/customer/order-failed");
            }
        }
    }

    private List<OrderItem> createOrderItemsFromCart(User loggedInUser, Order order) {

        List<OrderItem> orderItems = new ArrayList<>();

        List<Cart> cartItems = cartDAO.getCartByUserId(loggedInUser.getUserId());

        cartItems.forEach(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(item.getProduct());
            orderItem.setOrderPrice(item.getProduct().getProductDiscountPrice());
            orderItem.setOrderQuantity(item.getQuantity());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        });

        return orderItems;
    }
}
