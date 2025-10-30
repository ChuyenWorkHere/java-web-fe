<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="UserHeader.jsp" %>

<main>

    <!-- breadcrumb-area-start -->
    <section class="breadcrumb-area" data-background="img/bg/page-title.png">
        <div class="container">
            <div class="row">
                <div class="col-xl-12">
                    <div class="breadcrumb-text text-center">
                        <h1>Register</h1>
                        <ul class="breadcrumb-menu">
                            <li><a href="/Furniture/public/home">home</a></li>
                            <li><span>Register</span></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- breadcrumb-area-end -->

    <!-- login Area Strat-->
    <section class="login-area pt-100 pb-100">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 offset-lg-2">
                    <div class="basic-login">
                        <h3 class="text-center mb-60">ĐĂNG KÝ</h3>
                        <form id="registerForm" action="/Furniture/public/customer-signup" method="post">
                            <label for="fullname">Họ và tên <span>**</span></label>
                            <input id="fullname" name="fullname" type="text" placeholder="Nhập họ và tên..." required />
                            <label for="email">Email <span>**</span></label>
                            <input id="email" name="email" type="email" placeholder="Nhập email..." required />
                            <label for="password">Mật khẩu <span>**</span></label>
                            <input id="password" name="password" type="password" placeholder="Nhập mật khẩu..." required />
                            <label for="confirm_password">Xác nhận mật khẩu <span>**</span></label>
                            <input id="confirm_password" name="confirm_password" type="password" placeholder="Xác nhận mật khẩu..." required />
                            <div class="mb-2 text-center text-danger">${errorMessage}</div>
                            <button type="submit" class="btn theme-btn-2 w-100">Đăng Ký</button>
                            <div class="mt-20 text-center">
                                Đã có tài khoản? <a href="/Furniture/public/login">Đăng nhập ngay</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- login Area End-->
</main>
<script src="../admin/js/validAuth.js"></script>
<%@ include file="UserFooter.jsp" %>