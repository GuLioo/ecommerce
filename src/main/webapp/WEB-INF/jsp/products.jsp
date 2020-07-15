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
		function to_page(name,pn){
			/*			alert("到达to_page");*/
			console.log("To_Page"+"  name==="+name);
			var sendName=encodeURI(encodeURI(name));
			console.log("sendName==="+sendName);
			$.ajax({
				url:"/ecommerce_war/user/productInfo",
				data:{"name":name ,"pn": pn},
				type:"POST",
				success:function(result){
					console.log("获取商品信息成功");
					//1.解析并显示商品数据
					build_table(result);
					//2.解析显示（构建）分页条
					build_page_nav(name,result);

				},
				error: function (msg) {
					console.log("返回失败");
					alert("发生错误" + msg);
				}
			});
		}

		//根据得到的product信息解析显示(构建)商品图片
		function build_table(result){
			/***************
			 查询数据之前，必须清空table表
			 */
			$("#table").empty();
			var data= result.extend.pageInfo.list;
			console.log("显示adminUser信息="+data);
			getDataJson(data);
		}

		//根据products数据创建内容
		function getDataJson(datas) {
			for (var i = 0; i < datas.length; i++) {
				var trow = getDataRow(datas[i], i);
				var productArea=document.getElementById("table");
				productArea.appendChild(trow);
				btnApply(datas[i]);
			}
		}

		//具体创建内容
		function getDataRow(rowData, number) {
			var li=document.createElement('li');
			var pdesc=rowData.pdesc;
			var price=rowData.marketPrice;
			var url="${pageContext.request.contextPath}/resource/images/pic/"+rowData.image;
			li.innerHTML='<li>\n'+'<div class="box-preview box-product box-product-3">\n'+'<div class="box-img">\n'+'<a href="javascript:;">\n'+'<img src='+url+' alt="">'+'<\a>\n'+
			'</div><!-- .box-img end -->\n'+'<div class="box-content">\n'+'<h5><a href="javascript:;">'+pdesc+'</a></h5>\n'+
			'<h4 class="product-price">\n'+'<span class="new">'+price+'</span>\n'+'</h4><!-- .product-price end -->\n'+'<a class="btn btn-add-to-cart medium rounded with-icon colorful hover-dark mt-30" href="javascript:;"'+' id='+rowData.pid+'>\n'+
			'<i class="fa fa-shopping-cart"></i>\n'+"BUY"+'</a>\n'+'</div><!-- .box-content end -->\n'+'</div><!-- .box-preview end -->\n'+'</li>\n';
			return li;
		}

		//更改按钮
		function btnApply(rowData){
			$("#"+rowData.pid).click(function() {
				location.href="/ecommerce_war/user/"+rowData.pid+"/productDetail";
			});
		}

		//解析显示（构建）分页条，点击分页条要能去下一页。。。
		function build_page_nav(name,result){
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
					to_page(name,1);
				});
				prePageLi.click(function(){
					to_page(name,result.extend.pageInfo.pageNum-1);
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
					to_page(name,result.extend.pageInfo.pageNum + 1);
				});
				lastPageLi.click(function(){
					to_page(name,result.extend.pageInfo.pages);
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
					to_page(name,item);
				});
				ul.append(numLi);

			});

			//添加下一页和末页的提示
			ul.append(nextPageLi).append(lastPageLi);
			//将ul添加到nav
			var navEle = $("<nav></nav>").append(ul);
			navEle.appendTo("#page_nav_area");
		}

		//根据数据创建目录内容
		function createCate(datas) {
			/***************
			 查询数据之前，必须清空分页条信息
			 */
			$("#category").empty();
			//目录area
			var ul=document.getElementById("category");
			//构建元素
			for (var i = 0; i < datas.length; i++) {
				var name=datas[i].cname;
				console.log("categeryName==="+name);
				var li=document.createElement("li");
				li.innerHTML='<li>\n'+'<a onclick="to_page(\''+name+'\',\''+1+'\')">'+name+'</a>\n'+'</li>';
				ul.append(li);
			}
		}

		function refresh(){
			$.ajax({
				url : "/ecommerce_war/user/categoryInfo",
				type : "GET",
				success : function (result){
					console.log("目录查询成功");
					createCate(result);
					to_page(result[0].cname,2);

				},
				error: function (msg) {
					console.log("返回失败");
					alert("发生错误" + msg);
				}
			});
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
				<div id="content-main" class="section-flat page-single page-products with-sidebar">

					<div class="section-content">

						<div class="container">
							<div class="row">
								<div class="col-md-12">
								
									<div class="page-single-content">

										<div class="row">
											<div class="col-md-9 col-md-push-3">

												<div class="content">
													<div class="products-top-bar">
														<ul class="layout-style lp-layout-tabs">
															<li class="active"><a href="javascript:;"><i class="fa fa-th"></i></a></li>
															<li><a href="javascript:;"><i class="fa fa-list"></i></a></li>
														</ul><!-- .layout-style end -->
														<div class="sort-by">
															<span>Sort by</span>
															<select name="list-sort-by" class="list-sort-by">
																<option selected>All</option>
																<option>Option 1</option>
																<option>Option 2</option>
																<option>Option 3</option>
																<option>Option 4</option>
																<option>Option 5</option>
															</select>
															<i class="fa fa-sort"></i>
														</div><!-- .sort-by end -->
													</div><!-- .products-top-bar end -->
													<div class="block-content">
														<h5 class="block-title">Products you searched for</h5>
														<div class="row">
															<div class="col-md-12">
												
																<ul class="lp-layout-tabs-content">
																	<%--<li class="active">--%>
																		<ul class="list-products row-items-3" id="table">
										<%--									<li>
																				<div class="box-preview box-product box-product-3">
																					<div class="box-img">
																						<a href="javascript:;"><img src="images/files/sliders/box-product/img-4.jpg" alt=""></a>
																						<div class="overlay">
																							<div class="overlay-inner">
																								<ul class="product-icons-meta">
																									<li><a href="javascript:;"><i class="fa fa-exchange-alt"></i></a></li>
																									<li><a href="javascript:;"><i class="fa fa-heart"></i></a></li>
																								</ul><!-- .product-icons-meta end -->
																							</div><!-- .overlay-inner end -->
																						</div><!-- .overlay end -->
																					</div><!-- .box-img end -->
																					<div class="box-content">
																						<h5><a href="javascript:;">Waterproof 4k Camera</a></h5>
																						<h4 class="product-price">
																							<span class="new">499 KWD</span>
																							<span class="old">999 KWD</span>
																						</h4><!-- .product-price end -->
																						<a class="btn btn-add-to-cart medium rounded with-icon colorful hover-dark mt-30" href="javascript:;">
																							<i class="fa fa-shopping-cart"></i>
																							BUY
																						</a>
																					</div><!-- .box-content end -->
																				</div><!-- .box-preview end -->
																			</li>--%>

																		</ul><!-- .list-products end -->
																	<%--</li>--%>
																</ul><!-- .lp-layout-tabs-content end -->

																<div id = "page_nav_area">

																</div><!-- .pagination end -->

															</div><!-- .col-md-12 end -->
														</div><!-- .row end -->
													</div><!-- .block-content end -->
												</div><!-- .content end -->

											</div><!-- .col-md-9 end -->
											<div class="col-md-3 col-md-pull-9">

												<div class="sidebar">
													<div class="box-widget">
														<h5 class="box-title">Category</h5>
														<div class="box-content">
															<ul class="sidebar-list-links list-brands" id="category">

															</ul><!-- .sidebar-list-links -->
														</div><!-- .box-content end -->
													</div><!-- .box-widget end -->

												</div><!-- .sidebar end -->
												
											</div><!-- .col-md-3 end -->
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
