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
                            <h1>Login</h1>
                            <ul class="breadcrumb-menu">
                                <li><a href="index.html">home</a></li>
                                <li><span>Login</span></li>
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
                            <h3 class="text-center mb-60">ĐĂNG NHẬP</h3>
                            <form id="loginForm" action="/Furniture/public/customer-login" method="post">
                                <label for="email">Email <span>**</span></label>
                                <input id="email" name="username" type="text" placeholder="Nhập email..." />
                                <label for="password">Mật khẩu <span>**</span></label>
                                <input id="password" name="password" type="password" placeholder="Nhập mật khẩu..." />
                                <div class="login-action mb-20 fix">
                                    <span class="log-rem f-left">
                                        <input id="remember" type="checkbox" />
                                        <label for="remember">Ghi nhớ đăng nhập!</label>
                                    </span>
                                    <span class="forgot-login f-right">
                                        <a href="#">Quên mật khẩu?</a>
                                    </span>
                                </div>
                                <div class="text-danger" id="loginMessage" class="mb-2">${errorMessage}</div>
                                <button type="submit" class="btn theme-btn-2 w-100">Đăng Nhập</button>
                                <div class="or-divide"><span>or</span></div>
                            </form>
                            <a href="/Furniture/public/signup" class="btn theme-btn w-100 text-white">Đăng Ký Ngay</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- login Area End-->
        </main>
        <script src="../admin/js/validAuth.js"></script>
        <%@ include file="UserFooter.jsp" %>