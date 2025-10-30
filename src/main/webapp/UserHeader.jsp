<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html class="no-js" lang="zxx">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>LuxeHome - Nội Thất Gia Đình Việt</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <link rel="shortcut icon" type="image/x-icon"
        href="../user/img/favicon.png">
    <!-- Place favicon.ico in the root directory -->

    <!-- CSS here -->
    <link rel="stylesheet" href="../user/css/bootstrap.min.css">
    <link rel="stylesheet" href="../user/css/owl.carousel.min.css">
    <link rel="stylesheet" href="../user/css/animate.min.css">
    <link rel="stylesheet" href="../user/css/magnific-popup.css">
    <link rel="stylesheet" href="../user/css/fontawesome-all.min.css">
    <link rel="stylesheet" href="../user/css/flaticon.css">
    <link rel="stylesheet" href="../user/css/meanmenu.css">
    <link rel="stylesheet" href="../user/css/jquery-ui.css">
    <link rel="stylesheet" href="../user/css/slick.css">
    <link rel="stylesheet" href="../user/css/default.css">
    <link rel="stylesheet" href="../user/css/style.css">
    <link rel="stylesheet" href="../user/css/responsive.css">
    <link rel="stylesheet" href="../user/css/cart.css">
    <style>
        .fixed-alert {
          position: fixed;
          bottom: 20px;
          left: 20px;
          z-index: 1055;
        }
    </style>
</head>
<body>


	<!-- preloader -->
	<div id="preloader">
		<div class="preloader">
			<span></span> <span></span>
		</div>
	</div>
	<!-- preloader end  -->

        <!-- header start -->
        <header>
            <div id="header-sticky" class="header-area box-90">
                <div class="container-fluid">
                    <div class="row align-items-center">
                        <div class="col-xl-2 col-lg-6 col-md-6 col-7 col-sm-5 d-flex align-items-center pos-relative">
                            <div class="basic-bar cat-toggle">
                                <span class="bar1"></span>
                                <span class="bar2"></span>
                                <span class="bar3"></span>
                            </div>
                            <div class="logo">
                                <a href="../public/home"><img src="../user/img/logo/logo.png" alt=""></a>
                            </div>

                            <div class="category-menu">
                                <h4>Category</h4>
                                <ul>
                                    <li><a href="shop.html"><i class="flaticon-shopping-cart-1"></i> Table lamp</a></li>
                                    <li><a href="shop.html"><i class="flaticon-shopping-cart-1"></i> Furniture</a></li>
                                    <li><a href="shop.html"><i class="flaticon-shopping-cart-1"></i> Chair</a></li>
                                    <li><a href="shop.html"><i class="flaticon-shopping-cart-1"></i> Men</a></li>
                                    <li><a href="shop.html"><i class="flaticon-shopping-cart-1"></i> Women</a></li>
                                    <li><a href="shop.html"><i class="flaticon-shopping-cart-1"></i> Cloth</a></li>
                                    <li><a href="shop.html"><i class="flaticon-shopping-cart-1"></i> Trend</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-xl-7 col-lg-6 col-md-8 col-8 d-none d-xl-block">
                            <div class="main-menu text-center">
                                <nav id="mobile-menu">
                                    <ul>
                                        <li>
                                            <a href="../public/home">Trang Chủ</a>
                                        </li>
                                        <li class="mega-menu">
                                            <a href="../public/shop">Cửa Hàng</a>
                                        </li>
                                        <li>
                                            <a href="blog.html">Tin Tức</a>
                                            <ul class="submenu">
                                                <li>
                                                    <a href="blog-2-col.html">Blog 2 Column</a>
                                                </li>
                                                <li>
                                                    <a href="blog-2-col-mas.html">Blog 2 Col Masonry</a>
                                                </li>
                                                <li>
                                                    <a href="blog-3-col.html">Blog 3 Column</a>
                                                </li>
                                                <li>
                                                    <a href="blog-3-col-mas.html">Blog 3 Col Masonry</a>
                                                </li>
                                                <li>
                                                    <a href="blog-details.html">Blog Details</a>
                                                </li>
                                                <li>
                                                    <a href="blog-details-audio.html">Blog Details Audio</a>
                                                </li>
                                                <li>
                                                    <a href="blog-details-video.html">Blog Details Video</a>
                                                </li>
                                                <li>
                                                    <a href="blog-details-gallery.html">Blog Details Gallery</a>
                                                </li>
                                                <li>
                                                    <a href="blog-details-left-sidebar.html">Details Left Sidebar</a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li>
                                            <a href="#">Trang</a>
                                            <ul class="submenu">
                                                <li>
                                                    <a href="about.html">About Us</a>
                                                </li>

                                                <li>
                                                    <a href="contact.html">Contact Us</a>
                                                </li>
                                                <li>
                                                    <a href="login.html">login</a>
                                                </li>
                                                <li>
                                                    <a href="register.html">Register</a>
                                                </li>
                                                <li>
                                                    <a href="cart.html">Shoping Cart</a>
                                                </li>
                                                <li>
                                                    <a href="checkout.html">Checkout</a>
                                                </li>
                                                <li>
                                                    <a href="wishlist.html">Wishlist</a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li>
                                            <a href="about.html">Giới thiệu</a>
                                        </li>
                                        <li>
                                            <a href="contact.html">Liên Hệ</a>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                        <div class="col-xl-3 col-lg-6 col-md-6 col-5 col-sm-7 pl-0">
                            <div class="header-right f-right d-flex">
                                <ul>
                                    <li class="search-btn">
                                        <a class="search-btn nav-search search-trigger" href="#"><i class="fas fa-search"></i></a>
                                    </li>

                                    <li class="login-btn">
                                        <c:choose>
                                            <c:when test="${not empty sessionScope.user}">
                                                <a href="${pageContext.request.contextPath}/user/profile">
                                                    <img src="https://static.vecteezy.com/system/resources/previews/036/280/651/original/default-avatar-profile-icon-social-media-user-image-gray-avatar-icon-blank-profile-silhouette-illustration-vector.jpg"
                                                         alt="Avatar"
                                                         class="rounded-circle"
                                                         style="width:100%; height:100%; object-fit:cover; border:1px solid #fe4536;">
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="${pageContext.request.contextPath}/public/login">
                                                    <i class="far fa-user fs-5"></i>
                                                </a>
                                            </c:otherwise>
                                        </c:choose>
                                    </li>
                                    <li class="d-shop-cart">
                                            <c:choose>
                                                <c:when test="${not empty sessionScope.user}">
                                                    <a href="../customer/cart">
                                                        <i class="flaticon-shopping-cart"></i>
                                                        <span class="cart-count">3</span>
                                                    </a>
                                                    <ul class="minicart">
                                                            <li>
                                                                <div class="cart-img">
                                                                    <a href="product-details.html">
                                                                        <img src="../user/img/product/pro1.jpg" alt="" />
                                                                    </a>
                                                                </div>
                                                                <div class="cart-content">
                                                                    <h3>
                                                                        <a href="product-details.html">Black & White Shoes</a>
                                                                    </h3>
                                                                    <div class="cart-price">
                                                                        <span class="new">$ 229.9</span>
                                                                        <span>
                                                                            <del>$239.9</del>
                                                                        </span>
                                                                    </div>
                                                                </div>
                                                                <div class="del-icon">
                                                                    <a href="#">
                                                                        <i class="far fa-trash-alt"></i>
                                                                    </a>
                                                                </div>
                                                            </li>
                                                            <li>
                                                                <div class="cart-img">
                                                                    <a href="product-details.html">
                                                                        <img src="../user/img/product/pro2.jpg" alt="" />
                                                                    </a>
                                                                </div>
                                                                <div class="cart-content">
                                                                    <h3>
                                                                        <a href="product-details.html">Black & White Shoes</a>
                                                                    </h3>
                                                                    <div class="cart-price">
                                                                        <span class="new">$ 229.9</span>
                                                                        <span>
                                                                            <del>$239.9</del>
                                                                        </span>
                                                                    </div>
                                                                </div>
                                                                <div class="del-icon">
                                                                    <a href="#">
                                                                        <i class="far fa-trash-alt"></i>
                                                                    </a>
                                                                </div>
                                                            </li>
                                                            <li>
                                                                <div class="cart-img">
                                                                    <a href="product-details.html">
                                                                        <img src="../user/img/product/pro3.jpg" alt="" />
                                                                    </a>
                                                                </div>
                                                                <div class="cart-content">
                                                                    <h3>
                                                                        <a href="product-details.html">Black & White Shoes</a>
                                                                    </h3>
                                                                    <div class="cart-price">
                                                                        <span class="new">$ 229.9</span>
                                                                        <span>
                                                                            <del>$239.9</del>
                                                                        </span>
                                                                    </div>
                                                                </div>
                                                                <div class="del-icon">
                                                                    <a href="#">
                                                                        <i class="far fa-trash-alt"></i>
                                                                    </a>
                                                                </div>
                                                            </li>
                                                            <li>
                                                                <div class="total-price">
                                                                    <span class="f-left">Tổng:</span>
                                                                    <span class="f-right">$300.0</span>
                                                                </div>
                                                            </li>
                                                            <li>
                                                                <div class="checkout-link">
                                                                    <a href="../customer/cart">Giỏ Hàng</a>
                                                                    <a class="red-color" href="../customer/checkout">Thanh Toán</a>
                                                                </div>
                                                            </li>
                                                        </ul>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="${pageContext.request.contextPath}/public/login">
                                                        <i class="flaticon-shopping-cart"></i>
                                                        <span class="cart-count">0</span>
                                                    </a>
                                                </c:otherwise>
                                            </c:choose>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-12 d-xl-none">
                            <div class="mobile-menu"></div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- header end -->