<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/pic/cat.png">
	<link rel="apple-touch-icon" href="${pageContext.request.contextPath}/resource/images/general-elements/favicon/apple-touch-icon.png">
	<link rel="apple-touch-icon" sizes="72x72" href="${pageContext.request.contextPath}/resource/images/general-elements/favicon/apple-touch-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="114x114" href="${pageContext.request.contextPath}/resource/images/general-elements/favicon/apple-touch-icon-114x114.png">

	<!-- Title
	============================================= -->
	<title>ecommerce | productDetail</title>
	<script src="${pageContext.request.contextPath}/resource/js/jquery-3.3.1.js"></script>
	<script>
		var id;
		//具体创建内容
		function refreshImage() {
			$.ajax({
				url : "/ecommerce_war/user/productDetailRefresh",
				type : "GET",
				data: {"pid": ${productDetail.pid}},
				success : function (result){
					id=result.pid;
					console.log("返回商品详情成功");
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

		function requestJson() {
			var url="/ecommerce_war/user/"+id+"/orderDetail";
			console.log("url="+url);
			location.href=url;
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

									</div><!-- .position-right end -->

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
	
		<!-- Content
		============================================= -->
		<section id="content">

			<div id="content-wrap">


				<!-- === Content Main =========== -->
				<div id="content-main" class="section-flat page-single page-product-single">

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
															<div class="col-md-5">
																<div class="slider-product-single">
																	<ul class="owl-carousel">
																		<li>
																			<div class="slide">
																				<div class="img-bg" >
																					<img id="image" <%--src="${pageContext.request.contextPath}/resource/images/pic/"+ String(${productDetail.image})--%> alt="">
																				</div><!-- .img-bg end -->
																			</div><!-- .slide end -->
																		</li>
																	</ul>
																</div><!-- .slider-product-single end -->

															</div><!-- .col-md-5 end -->
															<div class="col-md-7">
																<ul class="list-product-single-details">
																	<li>
																		<h5 id="titile">
																			${productDetail.pdesc}
																		</h5>

																		<div class="ps-price">
																			<span class="price-current">You Pay<span id="price">${productDetail.marketPrice} ¥</span></span>

																		</div><!-- .ps-price end -->
																	</li>
																	<li>
																		<div class="ps-line-info">
																			<i class="fa fa-archive"></i>
																			The category of product is <span class="color-dark" id="category">${productDetail.pname} </span>
																		</div><!-- .ps-line-info end -->
																	</li>
																	<li>
																		<div class="ps-line-info">
																			<i class="fa fa-calculator"></i>
																			The rest number of product is <span class="color-dark" id="num"> ${productDetail.pnum} 件</span>
																		</div><!-- .ps-line-info end -->
																	</li>

																	<li>
																		<ul class="ps-list-features" id="description">
																			${productDetail.pdesc}
																		</ul><!-- .ps-list-features end -->
																	</li>
																	<li>
																		<div class="ps-btns">
																			<input type="button" class="btn large colorful hover-grey" value="Buy Now"  id="toOrder" onclick="requestJson()">
																		</div><!-- .ps-btns end -->
																	</li>
																</ul><!-- .list-product-single-details end -->
															</div><!-- .col-md-7 end -->

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
