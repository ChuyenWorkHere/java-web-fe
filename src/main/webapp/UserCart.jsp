<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="UserHeader.jsp" %>
    <main>

        <!-- breadcrumb-area-start -->
        <section class="breadcrumb-area" data-background="img/bg/page-title.png">
            <div class="container">
                <div class="row">
                    <div class="col-xl-12">
                        <div class="breadcrumb-text text-center">
                            <h1>Giỏ hàng</h1>
                            <ul class="breadcrumb-menu">
                                <li><a href="../public/home">Trang chủ</a></li>
                                <li><span>Giỏ hàng</span></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- breadcrumb-area-end -->

        <!--notification-->
        <div id="notification" class="custom-alert">Đã xoá sản phẩm thành công!</div>
        <div id="notification-success" class="custom-alert">Cập nhật thành công!</div>
        <!--end notification-->

        <!-- Cart Area Strat-->
        <section class="cart-area pt-100 pb-100">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <form action="#">
                            <div class="table-content table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th class="product-thumbnail">Ảnh</th>
                                            <th class="cart-product-name">Tên sản phẩm</th>
                                            <th class="product-price">Giá bán</th>
                                            <th class="product-quantity">Số lượng</th>
                                            <th class="product-subtotal">Tổng giá</th>
                                            <th class="product-remove">Xoá</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td class="product-thumbnail"><a href="#"><img src="../user/img/product/pro1.jpg"
                                                        alt=""></a></td>
                                            <td class="product-name"><a href="#">Bakix Furniture</a></td>
                                            <td class="product-price"><span class="amount">$130.00</span></td>
                                            <td class="product-quantity">
                                                <div class="cart-plus-minus"><input type="text" value="1" /></div>
                                            </td>
                                            <td class="product-subtotal"><span class="amount">$130.00</span></td>
                                            <td class="product-remove"><a href="#"><i class="fa fa-times"></i></a></td>
                                        </tr>
                                        <tr>
                                            <td class="product-thumbnail"><a href="#"><img src="../user/img/product/pro2.jpg"
                                                        alt=""></a></td>
                                            <td class="product-name"><a href="#">Sujon Chair Set</a></td>
                                            <td class="product-price"><span class="amount">$120.00</span></td>
                                            <td class="product-quantity">
                                                <div class="cart-plus-minus"><input type="text" value="1" /></div>
                                            </td>
                                            <td class="product-subtotal"><span class="amount">$120.00</span></td>
                                            <td class="product-remove"><a href="#"><i class="fa fa-times"></i></a></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <div class="coupon-all">
                                        <div class="coupon">
                                            <a href="../public/shop" class="btn theme-btn-2" >
                                                Tiếp tục mua sắm
                                            </a>
                                        </div>
                                        <form>
                                            <div class="coupon2">
                                                <input class="btn theme-btn btn-update-cart" name="update_cart" value="Cập nhật">
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-5 ml-auto">
                                    <div class="cart-page-total">
                                        <h2>Tổng giỏ hàng</h2>
                                        <ul class="mb-20">
                                            <li>Tổng số sản phẩm <span>2</span></li>
                                            <li>Tổng tiền thanh toán <span>$250.00</span></li>
                                        </ul>
                                        <a class="btn theme-btn" href="../customer/checkout">Tiến hành thanh toán</a>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <!-- Cart Area End-->


        </main>
        <%@ include file="UserFooter.jsp" %>