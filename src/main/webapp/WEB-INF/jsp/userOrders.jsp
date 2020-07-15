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
		//查询分页的数据（抽取ajax查询方法）
		function to_page(pn){
			$.ajax({
				url:"/ecommerce_war/user/userOrdersInfo",
				data:{"pn": pn},
				type:"POST",
				success:function(result){
					console.log("获取商品信息成功");
					//1.解析并显示商品数据
					build_table(result);
					//2.解析显示（构建）分页条
					build_page_nav(result);

				},
				error: function (msg) {
					console.log("返回失败");
					alert("发生错误" + msg);
				}
			});
		}

		//根据得到的orders信息解析显示(构建)商品图片
		function build_table(result){
			/***************
			 查询数据之前，必须清空table表
			 */
			$("#table").empty();
			var data= result.extend.pageInfo.list;
			console.log("显示orders信息="+data);
			getDataJson(data);
		}

		//根据orders数据创建内容
		function getDataJson(datas) {
			for (var i = 0; i < datas.length; i++) {
				var trow = getDataRow(datas[i], i);
				var productArea=document.getElementById("table");
				productArea.appendChild(trow);

			}
		}

		//具体创建内容
		function getDataRow(rowData) {
			var row = document.createElement('tr'); //创建行
			//oid
			var id = document.createElement('td');
			id.style.width="20%";
			id.innerHTML = rowData.oid;
			row.appendChild(id);
			//price
			var price = document.createElement('td');
			price.style.width="20%";
			price.innerHTML = rowData.orderPrice;
			row.appendChild(price);
			//Date
			var Date = document.createElement('td');
			Date.style.width="20%";
			Date.innerHTML = rowData.orderTime;
			row.appendChild(Date);
		}


		//解析显示（构建）分页条，点击分页条要能去下一页。。。
		function build_page_nav(result){
			/***************
			 查询数据之前，必须清空分页条信息
			 */
			$("#page_nav_area").empty();

			//page_info_area
			var ul= $("<ul></ul>").addClass("pagination");

			//构建元素
			var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
			var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
			if(result.extend.pageInfo.hasPreviousPage==false){
				firstPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
			}else{
				//为元素添加点击翻页事件
				firstPageLi.click(function(){
					to_page(1);
				});
				prePageLi.click(function(){
					to_page(result.extend.pageInfo.pageNum-1);
				});
			}

			var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
			var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
			if(result.extend.pageInfo.hasNextPage==false){
				nextPageLi.addClass("disabled");
				lastPageLi.addClass("disabled");
			}else{
				//为元素添加点击翻页事件
				nextPageLi.click(function(){
					to_page(result.extend.pageInfo.pageNum + 1);
				});
				lastPageLi.click(function(){
					to_page(result.extend.pageInfo.pages);
				});
			}


			//添加首页和前一页的提示
			ul.append(firstPageLi).append(prePageLi);

			//遍历页码号 1.2.3.
			//将页码号添加到ul中
			$.each(result.extend.pageInfo.navigatepageNums,function(index,item){
				var numLi= $("<li></li>").append($("<a></a>").append(item));
				if(result.extend.pageInfo.pageNum == item){
					numLi.addClass("active");
				}
				//添加点击事件，通过ajax查询选择的页面
				numLi.click(function(){
					to_page(item);
				});
				ul.append(numLi);

			});

			//添加下一页和末页的提示
			ul.append(nextPageLi).append(lastPageLi);
			//将ul添加到nav
			var navEle = $("<nav></nav>").append(ul);
			navEle.appendTo("#page_nav_area");
		}



		function refresh(){
			to_page(1);
		}

		//页面请求
		$(function (){
			refresh();
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
										<li><a href="javascript:;" href="/ecommerce_war/user/products"><span data-content="Products" >Products</span></a></li>
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
				<div id="content-main" class="section-flat page-single page-checkout">

					<div class="section-content">

						<div class="container">
							<div class="row">
								<div class="col-md-12">

									<div class="page-single-content">

										<div class="row">
											<div class="col-md-12">

												<div class="content">
													<div class="products-top-bar">
														<div class="form-group">
															<input type="text" name="productName" class="form-control" placeholder="Search for Products" data-alt-placeholder="Search for ...">
														</div><!-- .form-group end -->
														<div class="form-group">
															<button type="submit" class="form-control"><i class="fa fa-search"></i></button>
														</div><!-- .form-group end -->
													</div><!-- .products-top-bar end -->
													<div class="block-content">
														<div class="row">
															<div class="col-md-12">


																<h5 class="block-title color-theme">Order Details</h5>

																<div id="table-shop-cart" class="mb-70">
																	<table id="table">
																		<thead>
																		<tr>
																			<th>OrderId</th>
																			<th>Price</th>
																			<th>Date</th>
																		</tr>
																		</thead>
																		<tbody id="tbody">

																		</tbody>
																	</table>
																</div><!-- #table-shop-cart end -->
																<div id = "page_nav_area">

																</div><!-- .pagination end -->

															</div><!-- .col-md-12 end -->

															<div class="col-md-6">

																<div class="box-checkout">

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
