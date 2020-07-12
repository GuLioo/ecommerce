﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>

<head>

	<!-- Meta
	============================================= -->
	<meta charset="utf-8">
	<%--<meta name="viewport" content="width=device-width, intial-scale=1, max-scale=1">
--%>
    <meta name="author" content="IT Geeks">
    <!-- description -->
    <meta name="description" content="">
    <!-- keywords -->
    <meta name="keywords" content="">

	<!-- Stylesheets
	============================================= -->
	<link href="${pageContext.request.contextPath}/resource/css/css-assets.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resource/css/style-en.css" rel="stylesheet">


	<!-- Favicon
	============================================= -->
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/general-elements/favicon/favicon.png">
	<link rel="apple-touch-icon" href="${pageContext.request.contextPath}/resource/images/general-elements/favicon/apple-touch-icon.png">
	<link rel="apple-touch-icon" sizes="72x72" href="${pageContext.request.contextPath}/resource/images/general-elements/favicon/apple-touch-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="114x114" href="${pageContext.request.contextPath}/resource/images/general-elements/favicon/apple-touch-icon-114x114.png">
	<!-- Title
	============================================= -->
	<title>SoqLina | Login</title>
	<script src="${pageContext.request.contextPath}/resource/js/jquery-3.3.1.js"></script>
	<%--<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>--%>
	<script type="text/javascript">
				function requestJson() {
					var userName =document.getElementById("loginEmail").value;
					var password =document.getElementById("loginPassword").value;
/*					var userName = $("#loginEmail").val();
					var password = $("#loginPassword").val();*/
					if(userName==""){
						alert("用户名不能为空！");
						return false;
					}
					else if(password==""){
						alert("密码不能为空！");
						return false;
					}
					else {
						console.log("准备post"),
						console.log("userName="+userName),
						console.log("password="+password),
						$.ajax({
							type: "post",
							url: "/ecommerce_war/entrance/loginRequest",
							data: {"userName": userName, "password": password},
							dataType: "json",
							success: function (result) {
								console.log("返回成功");
								var killResult=result['data'];
								var state=killResult['state'];
								var stateInfo=killResult['stateInfo'];
								if(state==1){
									location.href="/ecommerce_war/entrance/adminUser";
								}
								else if(state==-2){
									alert("密码错误");
									console.log(state);
								}
								else {
									alert("不存在用户");
									console.log(state);
								}
							},
							error: function (msg) {
								console.log("返回失败");
								alert("发生错误" + msg);
							}
						});
					}

				}
	</script>


</head>

<body>

	<!-- Document Full Container
	============================================= -->
	<div id="full-container">

		<!-- Header
		============================================= -->
		<header id="header">
		
			<div id="header-bar-1" class="header-bar">
		
				<div class="header-bar-wrap">
		
					<div class="container">
						<div class="row">
							<div class="col-md-12">
		
								<div class="hb-content">
									<div class="position-right">
										<ul class="list-info list-meta">
											<li><a href="register.html"><i class="fa fa-sign-in-alt"></i> Register</a></li>
											<li><a href="login.html"><i class="fa fa-user"></i> Login</a></li>
										</ul><!-- .list-meta end -->
										<ul class="list-info list-contact-info">
											<li><i class="fa fa-phone"></i><strong>Contact Us : </strong> (965) 55046994</li>
										</ul><!-- .list-contact-info end -->
									</div><!-- .position-right end -->
									<div class="position-left">
										<ul class="list-info list-meta">
											<li><a href="javascript:;"><i class="fa fa-question-circle"></i> Help</a></li>
										</ul><!-- .list-meta end -->
										<ul class="list-info list-language">
											<li class="dropdown-languages">
												<i class="fa fa-globe-americas"></i>English
												<ul class="select-language">
													
													<li><a href="index.html">English</a></li>
												</ul><!-- .select-language end -->
											</li>
										</ul><!-- .list-language end -->
									</div><!-- .position-left end -->
								</div><!-- .hb-content end -->
		
							</div><!-- .col-md-12 end -->
						</div><!-- .row end -->
					</div><!-- .container end -->
		
				</div><!-- .header-bar-wrap -->
		
			</div><!-- #header-bar-1 end -->
		<%--
			<div id="header-bar-2" class="header-bar">

				<div class="header-bar-wrap">

					<div class="container">
						<div class="row">
							<div class="col-md-12">

								<div class="hb-content">
									<a class="logo logo-header" href="index.html">
										<img src="images/files/logo-header-en.png" data-logo-alt="images/files/logo-header-en-alt.png" alt="">
										<h3><span class="colored">E-Commerce Store</span></h3>
										<span>HTML Template</span>
									</a><!-- .logo end -->
									<ul id="menu-main" class="menu-main">
										<li><a href="javascript:;"><span data-content="Mobiles & Tablets">Mobiles & Tablets</span></a></li>
										<li><a href="javascript:;"><span data-content="Home & Devices">Home & Devices</span></a></li>
										<li><a href="javascript:;"><span data-content="Health">Health</span></a></li>
										<li><a href="javascript:;"><span data-content="Electronics">Electronics</span></a></li>
										<li><a href="javascript:;"><span data-content="Childs & Games">Childs & Games</span></a></li>
									</ul><!-- #menu-main end -->
									<div class="cart-mini">
										<a href="javascript:;" class="btn-cart hidden-xs">
											<i class="fa fa-cart-arrow-down"></i><strong>KWD :</strong>0.00
											<span class="cart-count">2</span>
										</a>
										<div class="cart-box-wrapper">
											<div class="cart-box">
												<div class="count-total">
													<span>5 Products</span>
													<a class="btn small grey hover-colorful" href="javascript:;">Shop Cart</a>
												</div><!-- .count-total end -->
												<ul class="cart-dd-list">
													<li>
														<div class="item-img">
															<a class="img-bg" href="javascript:;"><img src="images/files/sliders/box-product/img-1.jpg" alt=""></a>
															<span class="close"><i class="fa fa-close"></i></span>
														</div><!-- .item-img end -->
														<div class="item-content">
															<h6 class="title">
																<a href="javascript:;">Product Name 1</a>
																<span class="quantity">x2</span>
															</h6>
															<span class="price">499 KWD</span>
														</div><!-- .item-content end -->
													</li>
													<li>
														<div class="item-img">
															<a class="img-bg" href="javascript:;"><img src="images/files/sliders/box-product/img-2.jpg" alt=""></a>
															<span class="close"><i class="fa fa-close"></i></span>
														</div><!-- .item-img end -->
														<div class="item-content">
															<h6 class="title">
																<a href="javascript:;">Product Name 2</a>
																<span class="quantity">x3</span>
															</h6>
															<span class="price">249 KWD</span>
														</div><!-- .item-content end -->
													</li>
												</ul><!-- .cart-dd-list end -->
												<div class="sub-total">
													<span class="title">Total</span>
													<span class="value">540 KWD</span>

												</div><!-- .sub-total end -->
												<a class="view-cart btn medium colorful hover-colorful-darken" href="javascript:;">View Cart</a>
											</div><!-- .cart-box end -->
										</div><!-- .cart-box-wrapper end -->
									</div><!-- .cart-mini end -->
								</div><!-- .hb-content end -->

							</div><!-- .col-md-12 end -->
						</div><!-- .row end -->
					</div><!-- .container end -->

				</div><!-- .header-bar-wrap -->

			</div><!-- #header-bar-2 end -->

			<div id="header-bar-3" class="header-bar sticky">

				<div class="header-bar-wrap">

					<div class="container">
						<div class="row">
							<div class="col-md-12">

								<div class="hb-content">
									<div class="list-full-categories">
										<a href="javascript:;" class="btn-dropdown-categories">
											<div class="hamburger hamburger--slider">
												<span class="hamburger-box">
													<span class="hamburger-inner"></span>
												</span>
											</div><!-- .hamburger-slider end -->
											All Categories
											<i class="fa fa-chevron-down"></i>
										</a><!-- .btn-dropdown-categories end -->
										<ul id="menu-categories" class="menu-categories">
											<li>
												<a href="javascript:;">Pages</a>
												<ul class="sub-menu">
													<li>
														<a href="javascript:;">Checkout Pages</a>
														<ul class="sub-menu">
															<li><a href="checkout-page-2.html">User State</a></li>
															<li><a href="checkout-page-3.html">User State Notification</a></li>
															<li><a href="checkout-page-4.html">Complete Order</a></li>
															<li><a href="checkout-page-5.html">Complete Order Notification</a></li>
															<li><a href="checkout-page-6.html">Complete Order Comment</a></li>
															<li><a href="checkout-page-7.html">Complete Order Details</a></li>
														</ul><!-- .sub-menu end -->
													</li>
													<li>
														<a href="javascript:;">Dashboard Pages</a>
														<ul class="sub-menu">
															<li><a href="dashboard-page-1.html">Control Panel</a></li>
															<li><a href="dashboard-page-2.html">My Address</a></li>
															<li><a href="dashboard-page-3.html">Update Address</a></li>
															<li><a href="dashboard-page-4.html">My Order</a></li>
															<li><a href="dashboard-page-5.html">Saved Items</a></li>
															<li><a href="dashboard-page-6.html">My Discounts</a></li>
															<li><a href="dashboard-page-7.html">Downloads</a></li>
															<li><a href="dashboard-page-8.html">Personal Profile</a></li>
														</ul><!-- .sub-menu end -->
													</li>
													<li><a href="index.html">Homepage</a></li>
													<li><a href="products.html">Products</a></li>
													<li><a href="products-fullwidth.html">Products Fullwidth</a></li>
													<li><a href="product-single.html">Product Single</a></li>
													<li><a href="login.html">Login</a></li>
													<li><a href="register.html">Register</a></li>
													<li><a href="shop-cart.html">Shop Cart</a></li>
													<li><a href="newsletter-popup.html">Newsletter Popup</a></li>
													<li><a href="products-comparison.html">Products Comparison</a></li>
													<li><a href="products-search.html">Product Search</a></li>
													<li><a href="wishlist.html">Wishlist</a></li>
													<li><a href="categories.html">Categories</a></li>
												</ul><!-- .sub-menu end -->
											</li>
											<li><a href="javascript:;">Mobiles & Tablets</a></li>
											<li><a href="javascript:;">Home Devices</a></li>
											<li>
												<a href="javascript:;">Computers & Networks</a>
												<ul class="sub-menu">
													<li><a href="javascript:;">Mobiles & Tablets</a></li>
													<li>
														<a href="javascript:;">Home Devices</a>
														<ul class="sub-menu">
															<li><a href="javascript:;">Mobiles & Tablets</a></li>
															<li><a href="javascript:;">Home Devices</a></li>
															<li><a href="javascript:;">Computers & Networks</a></li>
															<li><a href="javascript:;">Electronics</a></li>
															<li><a href="javascript:;">Home & Tools</a></li>
															<li><a href="javascript:;">Watches & Galleries</a></li>
														</ul><!-- .sub-menu end -->
													</li>
													<li><a href="javascript:;">Computers & Networks</a></li>
													<li><a href="javascript:;">Electronics</a></li>
													<li><a href="javascript:;">Home & Tools</a></li>
													<li><a href="javascript:;">Watches & Galleries</a></li>
												</ul><!-- .sub-menu end -->

											</li>
											<li><a href="javascript:;">Electronics</a></li>
											<li><a href="javascript:;">Home & Tools</a></li>
											<li><a href="javascript:;">Watches & Galleries</a></li>
											<li><a href="javascript:;">Fashion</a></li>
											<li><a href="javascript:;">Market</a></li>
											<li><a href="javascript:;">Books & Games</a></li>
											<li><a href="javascript:;">Health & Beauty</a></li>
											<li><a href="javascript:;">Childs Needs</a></li>
											<li><a href="javascript:;">Sports & Fitness</a></li>
										</ul><!-- #menu-categories end -->
									</div><!-- .list-full-categories end -->
									<div class="search-products">
										<form id="form-search-products" class="form-inline">
											<div class="form-group">
												<div class="list-select-category">
													<i class="fa fa-sort"></i>
													<a href="javascript:;" class="btn-select-category">All Categories</a>
													<ul class="menu-select-category">
														<li><a href="javascript:;">All Categories</a></li>
														<li><a href="javascript:;">Mobiles & Tablets</a></li>
														<li><a href="javascript:;">Home Devices</a></li>
														<li><a href="javascript:;">Computers & Networks</a></li>
														<li><a href="javascript:;">Home & Tools</a></li>
														<li><a href="javascript:;">Watches & Galleries</a></li>
														<li><a href="javascript:;">Fashion</a></li>
														<li><a href="javascript:;">Market</a></li>
													</ul><!-- .menu-select-category end -->
												</div><!-- .list-select-category end -->
											</div><!-- .form-group end -->
											<div class="form-group">
												<input type="text" name="productName" class="form-control" placeholder="Search for Products" data-alt-placeholder="Search for ...">
											</div><!-- .form-group end -->
											<div class="form-group">
												<button type="submit" class="form-control"><i class="fa fa-search"></i></button>
											</div><!-- .form-group end -->
										</form><!-- #form-search-products end -->
									</div><!-- .search-products end -->
									<ul class="icons-meta">
										<li><a href="javascript:;"><i class="fa fa-exchange-alt"></i></a></li>
										<li><a href="javascript:;"><i class="fa fa-heart"></i></a></li>
										<li class="hidden-lg hidden-md hidden-sm">
											<a class="mobile-btn-cart" href="javascript:;"><span class="cart-count">2</span><i class="fa fa-cart-arrow-down"></i></a>
											<div class="cart-box">
												<div class="count-total">
													<span>5 Products</span>
													<a class="btn small grey hover-colorful" href="javascript:;">Shop Cart</a>
												</div><!-- .count-total end -->
												<ul class="cart-dd-list">
													<li>
														<div class="item-img">
															<a class="img-bg" href="javascript:;"><img src="images/files/sliders/box-product/img-1.jpg" alt=""></a>
															<span class="close"><i class="fa fa-close"></i></span>
														</div><!-- .item-img end -->
														<div class="item-content">
															<h6 class="title">
																<a href="javascript:;">Product Name 1</a>
																<span class="quantity">x2</span>
															</h6>
															<span class="price">499 KWD</span>
														</div><!-- .item-content end -->
													</li>
													<li>
														<div class="item-img">
															<a class="img-bg" href="javascript:;"><img src="images/files/sliders/box-product/img-2.jpg" alt=""></a>
															<span class="close"><i class="fa fa-close"></i></span>
														</div><!-- .item-img end -->
														<div class="item-content">
															<h6 class="title">
																<a href="javascript:;">Product Name 2</a>
																<span class="quantity">x3</span>
															</h6>
															<span class="price">249 KWD</span>
														</div><!-- .item-content end -->
													</li>
												</ul><!-- .cart-dd-list end -->
												<div class="sub-total">
													<span class="title">Total</span>
													<span class="value">540 KWD</span>

												</div><!-- .sub-total end -->
												<a class="view-cart btn medium colorful hover-colorful-darken" href="javascript:;">View Cart</a>
											</div><!-- .cart-box end -->
										</li>
									</ul><!-- .icons-meta end -->
								</div><!-- .hb-content end -->

							</div><!-- .col-md-12 end -->
						</div><!-- .row end -->
					</div><!-- .container end -->

				</div><!-- .header-bar-wrap -->

			</div><!-- #header-bar-3 end -->--%>

		</header><!-- #header end -->
	
		<!-- Content
		============================================= -->
		<section id="content">

			<div id="content-wrap">

				<!-- Page Title
				============================================= -->
<%--				<div id="page-title">

					<div id="page-title-wrap">

						<div class="container">
							<div class="row">
								<div class="col-md-12">

									<ul class="breadcrumb">
										<li><a href="index.html"><i class="fa fa-home"></i>Home</a></li>
										<li class="active">Login</li>
									</ul><!-- .breadcrumb end -->

								</div><!-- .col-md-12 end -->
							</div><!-- .row end -->
						</div><!-- .container end -->

					</div><!-- #page-title-wrap end -->

				</div><!-- #page-title end -->--%>

				<!-- === Content Main =========== -->
				<div id="content-main" class="section-flat page-single page-login">

					<div class="section-content">

						<div class="container">
							<div class="row">
								<div class="col-md-12">

									<div class="page-single-content">

										<div class="row">
											<div class="col-md-12">

												<div class="content">
													<div class="box-login">
														<ul class="tabs">
															<li class="active"><a href="javascript:;">Login</a></li>
															<li><a href="javascript:;">New Account</a></li>
														</ul><!-- .tabs end -->
														<ul class="tabs-content">
															<li class="active">
																<form id="form-login">
																	<div class="form-group">
																		<input type="text" name="loginEmail" id="loginEmail" class="form-control" placeholder="Email Address">
																		<i class="fa fa-envelope"></i>
																	</div><!-- .form-group end -->
																	<div class="form-group">
																			<input type="password" name="loginPassword" id="loginPassword" class="form-control" placeholder="Password">
																			<i class="fa fa-pencil-alt"></i>
																	</div><!-- .form-group end -->
																	<div class="form-group">
																		<input type="submit" name="button" id="btn_login" class="form-control" value="Login"  onclick="requestJson()">
																		<a href="#">You forgot password?</a>
																	</div><!-- .form-group end -->
																</form><!-- #form-login end -->
															</li>
														</ul><!-- .tabs-content end -->
													</div><!-- .box-login end -->
												</div><!-- .content end -->

											</div><!-- .col-md-12 end -->
										</div><!-- .row end -->

									</div><!-- .page-single-content end -->

								</div><!-- .col-md-12 end -->
							</div><!-- .row end -->
						</div><!-- .container end -->

					</div><!-- .section-content end -->

				</div><!-- .section-flat end -->

			</div><!-- #content-wrap -->

		</section><!-- #content end -->

		<%--<!-- Footer
		============================================= -->
		<footer id="footer">

			<div id="footer-bar-1" class="footer-bar text-white">

				<div class="footer-bar-wrap">

					<div class="container">
						<div class="row">
							<div class="col-md-12">

								<div class="fb-row">

									<div class="row">
										<div class="col-sm-3 mb-sm-20">

											<img src="images/files/logo-header-en-alt.png" alt="">

										</div><!-- .col-sm-3 end -->
										<div class="col-sm-9">

											<div class="box-email-subscribe">
												<div class="box-title">
													<h5>Subscribe Newsletter</h5>
													<p>Register now and get the latest offers and products to your email</p>
												</div><!-- .box-title end -->
												<div class="box-content">
													<form id="form-email-subscribe" class="form-inline">
														<div class="form-group">
															<input type="text" name="email" class="form-control" placeholder="Enter Your Email">
														</div><!-- .form-group end -->
														<div class="form-group">
															<button type="submit" class="form-control">Subscribe</button>
														</div><!-- .form-group end -->
													</form><!-- #form-email-subscribe end -->
												</div><!-- .box-content end -->
											</div><!-- .box-email-subscribe end -->

										</div><!-- .col-sm-9 end -->
									</div><!-- .row end -->

								</div><!-- .fb-row end -->

							</div><!-- .col-md-12 end -->
						</div><!-- .row end -->
					</div><!-- .container end -->

				</div><!-- .footer-bar-wrap -->

			</div><!-- #footer-bar-1 end -->

			<div id="footer-bar-2" class="footer-bar text-white hidden-xs">

				<div class="footer-bar-wrap">

					<div class="container">
						<div class="row">
							<div class="col-md-12">

								<div class="fb-row">

									<div class="row">
										<div class="col-md-3 col-sm-6 mb-md-40">
											<h5>About ITGeeks</h5>
											<ul class="list-links">
												<li><a href="javascript:;">Help</a></li>
												<li><a href="javascript:;">FAQ</a></li>
												<li><a href="javascript:;">Sitemap</a></li>
												<li><a href="javascript:;">Contact Us</a></li>
												<li><a href="javascript:;">ITGeeks on Map</a></li>
												<li><a href="javascript:;">FAQ & Privacy Policy</a></li>
											</ul><!-- .list-links -->
										</div><!-- .col-md-3 end -->
										<div class="col-md-3 col-sm-6 mb-md-40">
											<h5>ITGeeks Services</h5>
											<ul class="list-links">
												<li><a href="javascript:;">Return & Exchange</a></li>
												<li><a href="javascript:;">Safe Marketing</a></li>
												<li><a href="javascript:;">Fast Delivery</a></li>
												<li><a href="javascript:;">Today Offers</a></li>
												<li><a href="javascript:;">ITGeeks App</a></li>
												<li><a href="javascript:;">Weekly Offers</a></li>
											</ul><!-- .list-links -->
										</div><!-- .col-md-3 end -->
										<div class="col-md-3 col-sm-6 mb-sm-40">
											<h5>Selling on ITGeeks</h5>
											<ul class="list-links">
												<li><a href="javascript:;">Start Selling</a></li>
												<li><a href="javascript:;">How to Sell</a></li>
												<li><a href="javascript:;">Selling Policy</a></li>
												<li><a href="javascript:;">Seller Privacy Policy</a></li>
												<li><a href="javascript:;">Delivered by ITGeeks</a></li>
												<li><a href="javascript:;">FAQ</a></li>
											</ul><!-- .list-links -->
										</div><!-- .col-md-3 end -->
										<div class="col-md-3 col-sm-6">
											<h5>Customers Services</h5>
											<ul class="list-info list-contact-info">
												<li><i class="far fa-envelope"></i><strong>Contact Us : </strong> <span>admin@itgeeks.info</span></li>
											</ul><!-- .list-contact-info end -->
											<h6 class="mt-20">Contact to Buy</h6>
											<ul class="list-info list-contact-info">
												<li><i class="far fa-envelope"></i><strong>Contact Us : </strong> <span>(965) 55046994</span></li>
											</ul><!-- .list-contact-info end -->
											<h6 class="mt-20">Follow Us</h6>
											<ul class="social-icons x4 white hover-white-bg icon-only">
												<li><a class="si-rss" href="javascript:;"><i class="fa fa-rss"></i><i class="fa fa-facebook"></i></a></li>
												<li><a class="si-tumblr" href="javascript:;"><i class="fa fa-tumblr"></i><i class="fa fa-facebook"></i></a></li>
												<li><a class="si-google" href="javascript:;"><i class="fa fa-google"></i><i class="fa fa-facebook"></i></a></li>
												<li><a class="si-youtube" href="javascript:;"><i class="fa fa-youtube"></i><i class="fa fa-facebook"></i></a></li>
												<li><a class="si-twitter" href="javascript:;"><i class="fa fa-twitter"></i><i class="fa fa-twitter"></i></a></li>
												<li><a class="si-instagramorange" href="javascript:;"><i class="fa fa-instagram"></i><i class="fa fa-instagram"></i></a></li>
											</ul><!-- .social-icons end -->

										</div><!-- .col-md-3 end -->
									</div><!-- .row end -->

								</div><!-- .fb-row end -->

							</div><!-- .col-md-12 end -->
						</div><!-- .row end -->
					</div><!-- .container end -->

				</div><!-- .footer-bar-wrap -->

			</div><!-- #footer-bar-2 end -->

			<div id="footer-bar-3" class="footer-bar text-white">

				<div class="footer-bar-wrap">

					<div class="container">
						<div class="row">
							<div class="col-md-12">

								<div class="fb-row">

									<div class="copyrights-message mb-md-20">All Right Reserved to <a href="http://www.bootstrapmb.com/" target="_blank">ITGeeks</a></div>
									<img class="img-payments" src="images/files/payment-methods.png" alt="">
									<div class="copyrights-message">Design & Development by <a href="http://www.bootstrapmb.com/" target="_blank">ITGeeks</a> for Information Technology</div>

								</div><!-- .fb-row end -->

							</div><!-- .col-md-12 end -->
						</div><!-- .row end -->
					</div><!-- .container end -->

				</div><!-- .footer-bar-wrap -->

			</div><!-- #footer-bar-3 end -->

		</footer><!-- #footer end -->

		<div class="side-full-categories">
			<div class="mobile-full-categories">
				<h5>All Categories</h5>
				<ul class="mobile-menu-categories">

				</ul><!-- .mobile-menu-categories end -->
			</div><!-- .mobile-full-categories end -->
		</div><!-- .side-full-categories end -->

		<div class="box-notification">
			<i class="fa fa-cart-arrow-down"></i>
			<div class="box-content">
				<span>Added to Cart</span>
				<h5>Elitebook 2019</h5>
				<a class="btn x-small grey hover-colorful" href="javascript:;">Shop Cart</a>
			</div><!-- .box-content end -->
			<a class="close" href="javascript:;">×</a>
		</div><!-- .box-notification end -->

	</div><!-- #full-container end -->

	<a class="scroll-top-icon scroll-top" href="javascript:;"><i class="fa fa-angle-up"></i></a>
--%>
	<!-- External JavaScripts
	============================================= -->


</body>
<script src="${pageContext.request.contextPath}/resource/js/jRespond.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/jquery.fitvids.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/superfish.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/owl.carousel.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/jquery.magnific-popup.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/price-range.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/functions.js"></script>
</html>
