﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html lang="en-US">

<head>

	<!-- Meta
	============================================= -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, intial-scale=1, max-scale=1">

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
	<title>SoqLina | Products</title>
	<script src="${pageContext.request.contextPath}/resource/js/jquery-3.3.1.js"></script>
	<script>
		//具体创建内容
		function refreshImage() {
			$.ajax({
				url : "/ecommerce_war/user/orderDetailRefresh",
				type : "GET",
				data: {"pid": ${orderDetail.productId}},
				success : function (result){
					console.log("返回订单详情成功");
					$("#image").empty();
					var imageArea=document.getElementById('image');
					var imageURL="${pageContext.request.contextPath}/resource/images/pic/"+result.image;
					imageArea.src=imageURL;
					console.log("imageURL="+imageURL);
				},
				error: function (msg) {
					console.log("返回失败");
					alert("发生错误" + msg);
				}
			});

		}


		//页面请求
		$(function (){
			refreshImage();
		});

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
											<li><a href="http://localhost:8080/ecommerce_war/entrance/logOut" <%--onclick="logOut()"--%>><i class="fa fa-sign-in-alt"></i> Logout</a></li>
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
			<div id="header-bar-2" class="header-bar">

				<div class="header-bar-wrap">

					<div class="container">
						<div class="row">
							<div class="col-md-12">

								<div class="hb-content">
									<a class="logo logo-header" >
										<a href="/ecommerce_war/user/products"><i class="fa fa-home"></i>Home</a>
										<%--<img src="${pageContext.request.contextPath}/resource/images/pic/小招喵欢迎你.jpg" data-logo-alt="images/files/logo-header-en-alt.png" alt="">
										<h3><span class="colored">E-Commerce Store</span></h3>
										<span>HTML Template</span>--%>
									</a><!-- .logo end -->
									<ul id="menu-main" class="menu-main">
										<li><a href="/ecommerce_war/user/products"><span data-content="Products">Products</span></a></li>
										<li><a href="javascript:;"><span data-content="Orders">Orders</span></a></li>
										<li><a href="javascript:;"><span data-content="UserInfo">UserInfo</span></a></li>
									</ul><!-- #menu-main end -->

								</div><!-- .hb-content end -->

							</div><!-- .col-md-12 end -->
						</div><!-- .row end -->
					</div><!-- .container end -->

				</div><!-- .header-bar-wrap -->

			</div><!-- #header-bar-2 end -->


		</header><!-- #header end -->
		<!-- === Content Main =========== -->
		<div id="content-main" class="section-flat page-single page-checkout">

			<div class="section-content">

				<div class="container">
					<div class="row">
						<div class="col-md-12">

							<div class="page-single-content">

								<div class="row">
									<div class="col-md-12">

										<div class="content">
											<div class="block-content">
												<div class="row">
													<div class="col-md-12">

														<div id="table-checkout-details1" class="table-1">
															<table>
																<thead>
																<tr>
																	<th>OrderId</th>
																	<th>Price</th>
																	<th>Date</th>
																</tr>
																</thead>
																<tbody>
																<tr>
																	<td>${orderDetail.oid}</td>
																	<td><span class="color-theme">${orderDetail.orderPrice}</span></td>
																	<td><span class="color-theme">${orderDetail.orderTime}</span> </td>
																</tr>
																</tbody>
															</table>
														</div><!-- #table-checkout-details-1 end -->

														<h5 class="block-title color-theme">Order Details</h5>

														<div id="table-shop-cart" class="mb-70">
															<table>
																<thead>
																<tr>
																	<th>Product Cate</th>
																	<th>Product Image</th>
																	<th>Product Name</th>
																	<th>Product Price</th>
																	<th>User Discount</th>
																</tr>
																</thead>
																<tbody>
																<tr>
																	<td>
																		<h6>${orderDetail.productCate}</h6>
																	</td>
																	<td><a href="javascript:;"><img id="image" alt=""></a></td>
																	<td>
																		<h6>${orderDetail.productName}</h6>
																	</td>
																	<td>
																		<h6>${orderDetail.productPrice}</h6>
																	</td>
																	<td>
																		<h6>${orderDetail.userDiscount}</h6>
																	</td>
																</tr>
																</tbody>
															</table>
														</div><!-- #table-shop-cart end -->


													</div><!-- .col-md-12 end -->

													<div class="col-md-6">

														<div class="box-checkout">
					<%--										<h5 class="box-title">Delivery Address</h5>
															<ul class="checkout-list-details">
																<li><span class="strong">Name:</span> Mohammed Hassan</li>
																<li><span class="strong">Company Name:</span> Web Design Company</li>
																<li><span class="strong">First Address:</span> Address 1</li>
																<li><span class="strong">Second Address:</span> Address 2</li>
																<li><span class="strong">City:</span> Mansoura</li>
																<li><span class="strong">Postal Code:</span> 7633</li>
															</ul><!-- .checkout-list-details end -->--%>
															<div class="ps-btns">
																<a class="btn large colorful hover-grey" href="javascript:;">Pay Now</a>
															</div><!-- .ps-btns end -->
														</div><!-- .box-checkout end -->

													</div><!-- .col-md-6 end -->
												</div><!-- .row end -->
											</div><!-- .block-content end -->
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


	</div><!-- #full-container end -->

	<a class="scroll-top-icon scroll-top" href="javascript:;"><i class="fa fa-angle-up"></i></a>

	<!-- External JavaScripts
	============================================= -->
	<script src="${pageContext.request.contextPath}/resource/js/jRespond.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/js/jquery.fitvids.js"></script>
	<script src="${pageContext.request.contextPath}/resource/js/superfish.js"></script>
	<script src="${pageContext.request.contextPath}/resource/js/owl.carousel.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/js/jquery.magnific-popup.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/js/price-range.js"></script>
	<script src="${pageContext.request.contextPath}/resource/js/select2.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/js/functions.js"></script>

</body>
</html>