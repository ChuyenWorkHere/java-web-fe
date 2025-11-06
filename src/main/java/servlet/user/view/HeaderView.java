package servlet.user.view;

import servlet.constants.SearchConstants;
import servlet.dao.*;
import servlet.dao.impl.*;
import servlet.models.*;
import servlet.response.CartResponse;
import servlet.utils.ProductUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/public/header-view")
public class HeaderView extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final int PRODUCT_NAME_MAX_LENGTH = 20;
    private CategoryDAO categoryDAO;
    private BrandDAO brandDAO;
    private MaterialDAO materialDAO;
    private CartDAO cartDAO;
    private ProductDAO productDAO;

    public HeaderView() {
        super();
        categoryDAO = new CategoryDAOImpl();
        materialDAO = new MaterialDAOImpl();
        brandDAO = new BrandDAOImpl();
        cartDAO = new CartDAOImpl();
        productDAO = new ProductDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();

        User loggedInUser = (User) session.getAttribute("customer");

        PrintWriter out = resp.getWriter();

        List<Category> categories = categoryDAO.findAll(1000, 1, "category_id", SearchConstants.DEFAULT_DIR, null, 1);
        List<Material> materials = materialDAO.findAll();
        List<Brand> brands = brandDAO.findAll();

        List<CartResponse> cartResponses = new ArrayList<>();
        double totalPrice = 0;
        int cartCount = 0;

        if (loggedInUser != null) {
            List<Cart> userCarts = cartDAO.getCartByUserId(loggedInUser.getUserId());
            cartCount = userCarts.size();

            for (Cart cart : userCarts) {
                Product product = cart.getProduct();
                Integer quantity = cart.getQuantity();

                double price = product.getProductDiscountPrice() > 0 ? product.getProductDiscountPrice() : product.getProductPrice();
                totalPrice += price * quantity;

                CartResponse cartResponse = new CartResponse();
                cartResponse.setProduct(product);
                cartResponse.setQuantity(quantity);
                cartResponse.setCartId(cart.getCartId());
                cartResponses.add(cartResponse);
            }
        }

        out.append("<!doctype html>");
        out.append("<html class=\"no-js\" lang=\"zxx\">");
        out.append("    ");
        out.append("<head>");
        out.append("        <meta charset=\"utf-8\">");
        out.append("        <meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">");
        out.append("        <title>Vue - Clean Minimal eCommerce HTML Template</title>");
        out.append("        <meta name=\"description\" content=\"\">");
        out.append("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        out.append("");
        out.append("		<link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"" + req.getContextPath() + "/user/img/favicon.png\">");
        out.append("        <!-- Place favicon.ico in the root directory -->");
        out.append("");
        out.append("		<!-- CSS here -->");
        out.append("        <link rel=\"stylesheet\" href=\"" + req.getContextPath() + "/user/css/bootstrap.min.css\">");
        out.append("        <link rel=\"stylesheet\" href=\"" + req.getContextPath() + "/user/css/owl.carousel.min.css\">");
        out.append("        <link rel=\"stylesheet\" href=\"" + req.getContextPath() + "/user/css/animate.min.css\">");
        out.append("        <link rel=\"stylesheet\" href=\"" + req.getContextPath() + "/user/css/magnific-popup.css\">");
        out.append("        <link rel=\"stylesheet\" href=\"" + req.getContextPath() + "/user/css/fontawesome-all.min.css\">");
        out.append("        <link rel=\"stylesheet\" href=\"" + req.getContextPath() + "/user/css/flaticon.css\">");
        out.append("        <link rel=\"stylesheet\" href=\"" + req.getContextPath() + "/user/css/meanmenu.css\">");
        out.append("        <link rel=\"stylesheet\" href=\"" + req.getContextPath() + "/user/css/slick.css\">");
        out.append("        <link rel=\"stylesheet\" href=\"" + req.getContextPath() + "/user/css/default.css\">");
        out.append("        <link rel=\"stylesheet\" href=\"" + req.getContextPath() + "/user/css/style.css\">");
        out.append("        <link rel=\"stylesheet\" href=\"" + req.getContextPath() + "/user/css/responsive.css\">");
        out.append("        <link rel=\"stylesheet\" href=\"" + req.getContextPath() + "/user/css/profile.css\">");
        out.append("    </head>");
        out.append("    <body>");
        out.append("");
        out.append("");
        out.append("        <!-- preloader -->");
        out.append("        <div id=\"preloader\">");
        out.append("            <div class=\"preloader\">");
        out.append("                <span></span>");
        out.append("                <span></span>");
        out.append("            </div>");
        out.append("        </div>");
        out.append("        <!-- preloader end  -->");
        out.append("");
        out.append("        <!-- header start -->");
        out.append("        <header>");
        out.append("            <div id=\"header-sticky\" class=\"header-area box-90\">");
        out.append("                <div class=\"container-fluid\">");
        out.append("                    <div class=\"row align-items-center\">");
        out.append("                        <div class=\"col-xl-2 col-lg-6 col-md-6 col-7 col-sm-5 d-flex align-items-center pos-relative\">");
        out.append("                            <div class=\"basic-bar cat-toggle\">");
        out.append("                                <span class=\"bar1\"></span>");
        out.append("                                <span class=\"bar2\"></span>");
        out.append("                                <span class=\"bar3\"></span>");
        out.append("                            </div>");
        out.append("                            <div class=\"logo\">");
        out.append("                                <a href=\"" + req.getContextPath() + "/public/home\"><img src=\"" + req.getContextPath() + "/user/img/logo/logo.png\" alt=\"\"></a>");
        out.append("                            </div>");
        out.append("");
        out.append("                            <div class=\"category-menu\">");
        out.append("                                <h4>Category</h4>");
        out.append("                                <ul>");
        out.append("                                    <li><a href=\"" + req.getContextPath() + "/public/shop\"><i class=\"flaticon-shopping-cart-1\"></i> Table lamp</a></li>");
        out.append("                                    <li><a href=\"" + req.getContextPath() + "/public/shop\"><i class=\"flaticon-shopping-cart-1\"></i> Furniture</a></li>");
        out.append("                                    <li><a href=\"" + req.getContextPath() + "/public/shop\"><i class=\"flaticon-shopping-cart-1\"></i> Chair</a></li>");
        out.append("                                    <li><a href=\"" + req.getContextPath() + "/public/shop\"><i class=\"flaticon-shopping-cart-1\"></i> Men</a></li>");
        out.append("                                    <li><a href=\"" + req.getContextPath() + "/public/shop\"><i class=\"flaticon-shopping-cart-1\"></i> Women</a></li>");
        out.append("                                    <li><a href=\"" + req.getContextPath() + "/public/shop\"><i class=\"flaticon-shopping-cart-1\"></i> Cloth</a></li>");
        out.append("                                    <li><a href=\"" + req.getContextPath() + "/public/shop\"><i class=\"flaticon-shopping-cart-1\"></i> Trend</a></li>");
        out.append("                                </ul>");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("                        <div class=\"col-xl-8 col-lg-6 col-md-8 col-8 d-none d-xl-block\">");
        out.append("                            <div class=\"main-menu text-center\">");
        out.append("                                <nav id=\"mobile-menu\">");
        out.append("                                    <ul>");
        out.append("                                        <li>");
        out.append("                                            <a href=\"" + req.getContextPath() + "/public/home\">Trang chủ</a>");
        out.append("                                        </li>");
        out.append("                                        <li>");
        out.append("                                            <a href=\"" + req.getContextPath() + "/public/shop\">Cửa hàng</a>");
        out.append("                                        </li>");
        out.append("                                        <li class=\"mega-menu\">");
        out.append("                                            <a href=\"#\">Sản phẩm </a>");
        out.append("											<ul class=\"submenu\">");
        out.append("                                                <li>");
        out.append("                                                    <a href=\"#\">Danh mục sản phẩm</a>");
        out.append("                                                    <ul class=\"submenu level-1\">");
        categories.forEach(category -> {
            out.append("                                                        <li>");
            out.append("                                                            <a href=\"" + req.getContextPath() + "/public/shop?category=" + category.getCategoryId() + "\">" + category.getCategoryName() + "</a>");
            out.append("                                                        </li>");
        });
        out.append("                                                    </ul>");
        out.append("                                                </li>");
        out.append("                                                <li>");
        out.append("                                                    <a href=\"#\">Thương hiệu</a>");
        out.append("                                                    <ul class=\"submenu\">");
        brands.forEach(brand -> {
            out.append("                                                        <li>");
            out.append("                                                            <a href=\"" + req.getContextPath() + "/public/shop?brand=" + brand.getBrandId() + "\">" + brand.getBrandName() + "</a>");
            out.append("                                                        </li>");
        });
        out.append("                                                    </ul>");
        out.append("                                                </li>");
        out.append("                                                <li>");
        out.append("                                                    <a href=\"#\">Chất liệu</a>");
        out.append("                                                    <ul class=\"submenu\">");
        materials.forEach(material -> {
            out.append("                                                        <li>");
            out.append("                                                            <a href=\"" + req.getContextPath() + "/public/shop?material=" + material.getMaterialId() + "\">" + material.getMaterialName() + "</a>");
            out.append("                                                        </li>");
        });
        out.append("                                                    </ul>");
        out.append("                                                </li>");
        out.append("                                            </ul>");
        out.append("                                        </li>");
        out.append("                                        <li>");
        out.append("                                            <a href=\"" + req.getContextPath() + "/public/blog\">Tin tức</a>");
        out.append("                                        </li>");
        out.append("                                        <li>");
        out.append("                                            <a href=\"" + req.getContextPath() + "/public/introduction\">Giới thiệu</a>");
        out.append("                                        </li>");
        out.append("                                        <li>");
        out.append("                                            <a href=\"" + req.getContextPath() + "/public/contact\">Liên hệ</a>");
        out.append("                                        </li>");
        if (loggedInUser != null) {
            out.append("                                        <li class=\"mega-menu d-xl-none\">");
            out.append("                                            <a href=\"#\">Tài khoản</a>");
            out.append("                                            <ul class=\"submenu\">");
            out.append("                                                <li>");
            out.append("                                                    <a href=\"" + req.getContextPath() + "/customer/profile\">Thông tin</a>");
            out.append("                                                </li>");
            out.append("                                                <li>");
            out.append("                                                    <a href=\"" + req.getContextPath() + "/customer/orders\">Đơn hàng</a>");
            out.append("                                                </li>");
            out.append("                                                <li>");
            out.append("                                                    <a href=\"" + req.getContextPath() + "/customer/customer-logout\">Đăng xuất</a>");
            out.append("                                                </li>");
            out.append("                                        </li>");
        }
        out.append("                                    </ul>");
        out.append("                                </nav>");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("                        <div class=\"col-xl-2 col-lg-6 col-md-6 col-5 col-sm-7 pl-0\">");
        out.append("                            <div class=\"header-right f-right\">");
        out.append("                                <ul>");
        out.append("                                    <li class=\"search-btn\">");
        out.append("                                        <a class=\"search-btn nav-search search-trigger\" href=\"#\"><i class=\"fas fa-search\"></i></a>");
        out.append("                                    </li>");
        if (loggedInUser == null) {
            out.append("                                    <li class=\"login-btn\">");
            out.append("                                        <a href=\"" + req.getContextPath() + "/public/login\"><i class=\"far fa-user\"></i></a>");
            out.append("                                    </li>");
        } else {
            out.append("									<li class=\"search-btn p-0\">");
            out.append("                                        <a href=\"#\" >");
            out.append("                                            <img class=\"rounded-circle\" style=\"border: 3px solid tomato;\" src=\" "+ (loggedInUser.getAvatar() == null ? "https://static.vecteezy.com/system/resources/previews/009/292/244/original/default-avatar-icon-of-social-media-user-vector.jpg" : (req.getContextPath() + loggedInUser.getAvatar())) +"\" alt=\"\">");
            out.append("                                        </a>");
            out.append("                                        <ul class=\"miniuser p-0\" style=\"width: 200px;\">");
            out.append("                                            <li>");
            out.append("                                                <div class=\"d-flex flex-column p-0\">");
            out.append("                                                    <a class=\"p-3\" href=\"../customer/profile\">Tài khoản</a>");
            out.append("                                                    <a class=\"p-3\" href=\"../customer/orders\">Đơn hàng</a>");
            out.append("                                                    <a class=\"p-3\" href=\"../public/customer-logout\">Đăng xuất</a>");
            out.append("                                                </div>");
            out.append("                                            </li>");
            out.append("                                        </ul>");
            out.append("                                    </li>");
        }
        out.append("                                    <li class=\"d-shop-cart\"><a href=\"" + req.getContextPath() + "/customer/cart\"><i class=\"flaticon-shopping-cart\"></i> <span class=\"cart-count\">"+ cartCount +"</span></a>");
        out.append("                                        <ul class=\"minicart\">");

        if (cartResponses.isEmpty()) {
            out.append("                                            <li class=\"p-3 text-center\">Giỏ hàng của bạn đang trống.</li>");
        } else {
            for (CartResponse item : cartResponses) {
                String productImgUrl = ProductUtils.urlArray(item.getProduct().getProductImageUrl())[0];
                String productName = ProductUtils.truncate(item.getProduct().getProductName(), PRODUCT_NAME_MAX_LENGTH);
                out.append("                                            <li>");
                out.append("                                                <div class=\"cart-img\">");
                out.append("                                                    <a href=\"" + req.getContextPath() + "/public/product-detail?productId=" + item.getProduct().getProductId() + "\">");
                out.append("                                                        <img src=\"" + req.getContextPath() + productImgUrl + "\" alt=\"" + productName + "\" />");
                out.append("                                                    </a>");
                out.append("                                                </div>");
                out.append("                                                <div class=\"cart-content\">");
                out.append("                                                    <h3><a href=\"" + req.getContextPath() + "/public/product-detail?productId=" + item.getProduct().getProductId() + "\">" + productName + "</a></h3>");
                out.append("                                                    <div class=\"cart-price\">");
                double itemPrice = item.getProduct().getProductDiscountPrice() > 0 ? item.getProduct().getProductDiscountPrice() : item.getProduct().getProductPrice();
                out.append("                                                        <span class=\"new\">" + item.getQuantity() + " x " + ProductUtils.formatNumber(itemPrice) + "</span>");
                out.append("                                                    </div>");
                out.append("                                                </div>");
                out.append("                                                <div class=\"del-icon\">");
                out.append("                                                    <a href=\""+ (req.getContextPath() + "/customer/cart/delete?productId=" + item.getProduct().getProductId()) +"\"><i class=\"far fa-trash-alt\"></i></a>");
                out.append("                                                </div>");
                out.append("                                            </li>");
            }
        }

        out.append("                                            <li>");
        out.append("                                                <div class=\"total-price\">");
        out.append("                                                    <span class=\"f-left\">Total:</span>");
        out.append("                                                    <span class=\"f-right\">" + ProductUtils.formatNumber(totalPrice) + "</span>");
        out.append("                                                </div>");
        out.append("                                            </li>");
        out.append("                                            <li>");
        out.append("                                                <div class=\"checkout-link\">");
        out.append("                                                    <a href=\"" + req.getContextPath() + "/customer/cart\">Giỏ hàng</a>");
        out.append("                                                    <a class=\"red-color\" href=\"" + req.getContextPath() + "/customer/checkout-view\">Thanh toán</a>");
        out.append("                                                </div>");
        out.append("                                            </li>");
        out.append("                                        </ul>");
        out.append("                                    </li>");
        out.append("                                </ul>");
        out.append("                            </div>");
        out.append("                        </div>");
        out.append("                        <div class=\"col-12 d-xl-none\">");
        out.append("                            <div class=\"mobile-menu\"></div>");
        out.append("                        </div>");
        out.append("                    </div>");
        out.append("                </div>");
        out.append("            </div>");
        out.append("        </header>");
        out.append("        <!-- header end -->");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
}
