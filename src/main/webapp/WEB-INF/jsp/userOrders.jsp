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
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/images/pic/cat.png">
	<link rel="apple-touch-icon" href="${pageContext.request.contextPath}/resource/images/general-elements/favicon/apple-touch-icon.png">
	<link rel="apple-touch-icon" sizes="72x72" href="${pageContext.request.contextPath}/resource/images/general-elements/favicon/apple-touch-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="114x114" href="${pageContext.request.contextPath}/resource/images/general-elements/favicon/apple-touch-icon-114x114.png">

	<!-- Title
	============================================= -->
	<title>ecommerce | userOrders</title>
	<script src="${pageContext.request.contextPath}/resource/js/jquery-3.3.1.js"></script>
	<script>
		//查询全部订单分页的数据（抽取ajax查询方法）
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

		//查询全部订单分页的数据（抽取ajax查询方法）
		function to_page_search(pn){
			var orderId= $("#orderID").val();
			var startTime;
			console.log("datetime-start="+$("#datetime-start").val());
			if($("#datetime-start").val()==""){
				startTime=null;
			}
			else {
				startTime=$("#datetime-start").val();
			}
			var endTime;
			if($("#datetime-end").val()==""){
				endTime=null;
			}
			else {
				endTime=$("#datetime-end").val();
			}
			console.log(" orderId="+orderId+"  startTime="+startTime+" endTime="+endTime);
			$.ajax({
				url:"/ecommerce_war/user/searchOrdersInfo",
				data:{"pn":pn,"orderId": orderId,"startTime": startTime,"endTime": endTime,},
				type:"POST",
				success:function(result){
					console.log("获取商品信息成功");
					//1.解析并显示商品数据
					build_table(result);
					//2.解析显示（构建）分页条
					build_page_nav_search(result);
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

		//解析显示（构建）全部订单分页条，点击分页条要能去下一页。。。
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

		//解析显示（构建）查询订单分页条，点击分页条要能去下一页。。。
		function build_page_nav_search(result){
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
					to_page_search(1);
				});
				prePageLi.click(function(){
					to_page_search(result.extend.pageInfo.pageNum-1);
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
					to_page_search(result.extend.pageInfo.pageNum + 1);
				});
				lastPageLi.click(function(){
					to_page_search(result.extend.pageInfo.pages);
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
					to_page_search(item);
				});
				ul.append(numLi);

			});

			//添加下一页和末页的提示
			ul.append(nextPageLi).append(lastPageLi);
			//将ul添加到nav
			var navEle = $("<nav></nav>").append(ul);
			navEle.appendTo("#page_nav_area");
		}

		//根据orders数据创建内容
		function getDataJson(datas) {
			var table=document.getElementById("table");
			table.appendChild(getDataTh());
			for (var i = 0; i < datas.length; i++) {
				var trow = getDataRow(datas[i], i);
				table.appendChild(trow);
			}
		}

		//创建表头
		function getDataTh() {
			var tThread=document.createElement('thead');
			var row = document.createElement('tr'); //创建行
			//oid
			var id = document.createElement('th');
			id.innerHTML = "OrderId";
			row.appendChild(id);
			//price
			var price = document.createElement('th');
			price.innerHTML = "OrderPrice";
			row.appendChild(price);
			//Date
			var Date = document.createElement('th');
			Date.innerHTML = "OrderTime";
			row.appendChild(Date);
			tThread.appendChild(row);
			return tThread;
		}

		//具体创建内容
		function getDataRow(rowData, number) {
			var bo=document.createElement('tbody')
			var row = document.createElement('tr'); //创建行
			//oid
			var id = document.createElement('td');
			id.innerHTML = rowData.oid;
			row.appendChild(id);
			//price
			var price = document.createElement('td');
			price.innerHTML = rowData.orderPrice;
			row.appendChild(price);
			//date
			var date = document.createElement('td');
			var dataValue=new Date(rowData.orderTime);
			date.innerHTML = dataValue;
			row.appendChild(date);
			bo.append(row)
			return bo;
		}

		
		function search() {
			var orderId= $("#orderID").val();
			var startTime;
			console.log("datetime-start="+$("#datetime-start").val());
			if($("#datetime-start").val()==""){
				startTime=null;
			}
			else {
				startTime=$("#datetime-start").val();
			}
			var endTime;
			if($("#datetime-end").val()==""){
				endTime=null;
			}
			else {
				endTime=$("#datetime-end").val();
			}
			console.log(" orderId="+orderId+"  startTime="+startTime+" endTime="+endTime);
			$.ajax({
				url:"/ecommerce_war/user/searchOrdersInfo",
				data:{"pn":1,"orderId": orderId,"startTime": startTime,"endTime": endTime,},
				type:"POST",
				success:function(result){
					console.log("获取商品信息成功");
					//1.解析并显示商品数据
					build_table(result);
					//2.解析显示（构建）分页条
					build_page_nav_search(result);
				},
				error: function (msg) {
					console.log("返回失败");
					alert("发生错误" + msg);
				}
			});
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
								<ul class="list-info list-meta" >
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
								<li><a href="/ecommerce_war/user/userOrders"><span data-content="Orders">Orders</span></a></li>
							</ul><!-- #menu-main end -->

						</div><!-- .hb-content end -->

					</div><!-- .col-md-12 end -->
				</div><!-- .row end -->
			</div><!-- .container end -->

		</div><!-- .header-bar-wrap -->

	</div><!-- #header-bar-2 end -->


</header><!-- #header end -->

<!-- Document Full Container
============================================= -->
<div id="full-container">

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

											<div class="content" id="addTable">


												<div class="products-top-bar">
														<div class="form-group" width="20%">
															<label >订单编号</label>
															<input type="text" id="orderID" name="productName" class="form-control" placeholder="输入订单编号" data-alt-placeholder="Search for ...">
														</div><!-- .form-group end -->
														<div id="startTime" class="form-group" width="20%">
															<label for="datetime-start">起始时间</label>
															<input type="date" name="date" id="datetime-start" value="" />
															<%--<input type="datetime-local" name="date" id="datetime-start" value="" />--%>
														</div>
														<div id="endTime" class="form-group" width="20%">
															<label for="datetime-end">结束时间</label>
															<input type="date" name="date" id="datetime-end" value="" />
															<%--<input type="datetime-local" name="date" id="datetime-end" value="" />--%>
														</div>
														<div class="form-group" width="5%">
															<label >搜索</label>
															<button type="button" onclick=search() class="form-control"><i class="fa fa-search"></i></button>
														</div><!-- .form-group end -->

												</div><!-- .products-top-bar end -->


												<div class="block-content">
													<div id="addButton">
														<h5 class="block-title" id="proTitle"> 订单详情  </h5>
													</div>
													<div class="row">
														<div class="col-md-12">
															<div id="table-checkout-details1" class="table-1">
																<table id="table">
																	<thead>
																	<tr>
																		<th>OrderId</th>
																		<th>OrderPrice</th>
																		<th>OrderTime</th>
																	</tr>
																	</thead>
																	<tbody id="tbody">
																	</tbody>
																</table>
															</div>

															<div id = "page_nav_area">

															</div><!-- .pagination end -->

														</div><!-- .col-md-12 end -->
													</div><!-- .row end -->
												</div>
												<!--.block-content end -->
											</div><!-- .content end -->

										</div><!-- .col-md-9 end -->

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
