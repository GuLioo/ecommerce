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
				url:"/ecommerce_war/saler/productInfo",
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

		//根据得到的product信息解析显示(构建)员工表格
		function build_table(result){
			/***************
			 查询数据之前，必须清空table表
			 */
			$("#table tbody").empty();
			var data= result.extend.pageInfo.list;
			console.log("显示adminUser信息="+data);
			getDataJson(data);
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

		//根据products数据创建内容
		function getDataJson(datas) {
			for (var i = 0; i < datas.length; i++) {
				var trow = getDataRow(datas[i], i);
				tbody.appendChild(trow);
				btnApply(datas[i]);
				btnDel(datas[i]);
				var title=document.getElementById("proTitle");
				title.textContent=datas[i].pname;
			}
		}

		//具体创建内容
		function getDataRow(rowData, number) {
/*			var butArea=document.getElementById("addButton");
			var createAddButton=document.createElement('input');
			createAddButton.style.width="150px";
			createAddButton.setAttribute('type','button');
			createAddButton.setAttribute("id","buttonAdd"+rowData.pid);
			createAddButton.setAttribute("value", "增加用户");
			createAddButton.setAttribute("name", "add");
			createAddButton.setAttribute("class", "btn btn-primary btn-lg");
			butArea.append(createAddButton);*/

			var row = document.createElement('tr'); //创建行
			//pid
			var id = document.createElement('td');
			id.style.width="5%";
			id.innerHTML = rowData.pid;
			row.appendChild(id);
			//pname
			var name = document.createElement('td');
			name.style.width="20%";
			name.innerHTML = rowData.pname;
			row.appendChild(name);
			//market_Price
			var market_Price = document.createElement('td');
			market_Price.style.width="20%";
			market_Price.innerHTML = rowData.marketPrice;
			row.appendChild(market_Price);
			//image
			var image = document.createElement('td');
			image.style.width="20%";
			var pImage = document.createElement('img');
			var url="${pageContext.request.contextPath}/resource/images/pic/"+rowData.image;
			console.log("图片的url="+url);
			pImage.src=url;
			row.appendChild(image);
			image.append(pImage);
			//pdesc
			var pdesc = document.createElement('td');
			pdesc.style.width="20%";
			pdesc.innerHTML = rowData.pdesc;
			row.appendChild(pdesc);
			//pnum
			var pnum = document.createElement('td');
			pnum.style.width="20%";
			pnum.innerHTML = rowData.pnum;
			row.appendChild(pnum);
			//button修改
			var but = document.createElement('td');//创建列
			row.appendChild(but);
			var button = document.createElement('input');
			button.style.width="50px";
			button.setAttribute('type','button');
			button.setAttribute("id", "buttonApply"+rowData.pid);
			button.setAttribute("value", "改");
			button.setAttribute("name", "apply");
			button.setAttribute("class", "apply");
			but.appendChild(button);

			//button删除
			var butDele = document.createElement('td');//创建列
			row.appendChild(butDele);
			var buttonDel = document.createElement('input');
			buttonDel.style.width="20%";
			buttonDel.setAttribute('type','button');
			buttonDel.setAttribute("id","buttonDel"+rowData.pid);
			buttonDel.setAttribute("value", "删");
			buttonDel.setAttribute("name", "del");
			buttonDel.setAttribute("class", "del");
			butDele.appendChild(buttonDel);
			return row;
		}

		//增加按钮
		function show(){
			var killPhoneModal=$('#addProduct');
			//显示弹出层
			killPhoneModal.modal({
				//显示弹出层
				show:true,
/*				//禁止位置关闭
				backdrop:'static',
				//关闭键盘事件
				keyboard:false*/
			});
		}

		//删除按钮
		function btnDel(rowData){
			$("#buttonDel"+rowData.pid).click(function() {
						console.log("点了" + "buttonDel")
						if (rowData != null) {
							$.ajax({
								type: "post",
								url: "/ecommerce_war/saler/salerDelete",
								data: {"pid": rowData.pid},
								dataType: "text",
								success: function (result) {
									console.log("更改成功");
									refresh();
									location.href="/ecommerce_war/saler/productSaler";
								},
								error: function (msg) {
									console.log("返回失败");
									alert("发生错误" + msg);
								}
							});
						}
					}
			)}

		//更改按钮
		function btnApply(rowData){
			$("#buttonApply"+rowData.pid).click(function(){
				console.log("点了btnApply");
				var killPhoneModal=$('#killPhoneModal');
				//显示弹出层
				killPhoneModal.modal({
					//显示弹出层
					show:true,
		/*								//禁止位置关闭
                                        backdrop:'static',
										//关闭键盘事件
                                        keyboard:false*/
				});
				var cateName =document.getElementById("cateName");
				cateName.textContent=rowData.pname;
				//绑定点击事件
				$('#updateData').click(function () {
					var marketPrice =document.getElementById("price").value;
					var pdesc =document.getElementById("description").value;
					var pnum =document.getElementById("num").value;
					var pid=rowData.pid;
					var pname=rowData.pname;
					var image=rowData.image;
					if(marketPrice==""){
						marketPrice=rowData.marketPrice;
					}
					if(pdesc==""){
						pdesc=rowData.pdesc;
					}
					if(pnum==""){
						pnum=rowData.pnum;
					}
					if(pnum>100){
						alert("添加数目不可超过100");
						return false;
					}
					console.log("marketprice="+marketPrice+" pdesc="+pdesc+" pnum="+pnum);
					$.ajax({
						type: "post",
						url: "/ecommerce_war/saler/salerChange",
						data: {"pid": pid,"pname": pname,"marketPrice": marketPrice,"image": image,"pdesc": pdesc,"pnum": pnum},
						dataType: "text",
						success: function (result) {
							alert("更改成功");
							refresh();
							window.location.href="/ecommerce_war/saler/productSaler";
						},
						error: function (msg) {
							console.log("返回失败");
							alert("发生错误" + msg);
						}
					});
				});

			})
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
				url : "/ecommerce_war/saler/categoryInfo",
				type : "GET",
				success : function (result){
					console.log("目录查询成功");
					createCate(result);
					to_page(result[0].cname,1);

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


</header><!-- #header end -->

<!-- Document Full Container
============================================= -->
	<div id="full-container">
	
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

												<div class="content" id="addTable">
													<div class="block-content">
														<div id="addButton">
														<h5 class="block-title" id="proTitle">   <button  class="btn btn-primary btn-lg" id="addBtn" width="10px">增加用户</button></h5>
														</div>
														<div class="row">
															<div class="col-md-12">
																<div id="table-shop-cart">
																	<table id="table">
																		<thead>
																		<tr>
																			<th>ID</th>
																			<th>NAME</th>
																			<th>PRICE</th>
																			<th>IMAGE</th>
																			<th>DESCRIPTION</th>
																			<th>NUM</th>
																		</tr>
																		</thead>
																		<tbody id="tbody">
																		</tbody>
																	</table>
																</div>
																<div class="table-btns">
																	<a class="btn medium colorful hover-grey" id="butAdd" onclick=show()>ADD</a>
																</div><!-- .table-btns end -->
																<div id = "page_nav_area">

																</div><!-- .pagination end -->

															</div><!-- .col-md-12 end -->
														</div><!-- .row end -->
													</div>
													<!--.block-content end -->
												</div><!-- .content end -->

											</div><!-- .col-md-9 end -->
											<div class="col-md-3 col-md-pull-9" >

												<div class="sidebar">
													<div class="box-widget">
														<h5 class="box-title">category</h5>
														<div class="box-content" >
															<ul class="sidebar-list-links list-brands" id="category">
																<%--<li><a href="javascript:;">Optics</a></li>
																<li><a href="javascript:;">Shoes</a></li>
																<li><a href="javascript:;">Watches and Accessories</a></li>
																<li><a href="javascript:;">Child</a></li>
																<li><a href="javascript:;">Household Appliance</a></li>--%>
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
	<!--弹出层-->
	<div id="killPhoneModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title text-center" id="cateName">

					</h4>
				</div>
				<div class="input-group">
					<span class="input-group-addon">price</span>
					<input type="text" name="price" id="price"
						   placeholder="填价格" class="form-control"/>
				</div>
				<div class="input-group">
					<span class="input-group-addon">description</span>
					<input type="text" name="description" id="description"
						   placeholder="填描述" class="form-control"/>
				</div>
				<div class="input-group">
					<span class="input-group-addon">num</span>
					<input type="text" name="num" id="num"
						   placeholder="填数量" class="form-control"/>
				</div>

				<div class="modal-footer">
					<!-- 验证信息-->
					<span id="killPhoneMessage" class="glyphicon"></span>
					<button type="button" id="updateData" class="btn btn-success">
						<span class="glyphicon glyphicon-phone"></span>
						Submit
					</button>
				</div>
			</div>
		</div>
	</div>

	<!--弹出层-->
	<div id="addProduct" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title text-center" id="cateNameAddPro">
						商品信息填写^0^
					</h4>
				</div>
				<form action="addProduct.do"  method="post" enctype="multipart/form-data" target="ifm">
				<div class="input-group">
					<span class="input-group-addon">price</span>
					<input type="text" name="price" id="priceAddPro"
						   placeholder="填价格" class="form-control"/>
				</div>
				<div class="input-group">
					<span class="input-group-addon">description</span>
					<input type="text" name="description" id="descriptionAddPro"
						   placeholder="填描述" class="form-control"/>
				</div>
				<div class="input-group">
					<span class="input-group-addon">num</span>
					<input type="text" name="num" id="numAddPro"
						   placeholder="填数量" class="form-control"/>
				</div>
				<div class="input-group">

				图片：<input type="file" name="file">
				<input type="submit" value="提交" id="imageSub">

				</div>
				</form>
<%--				<div class="modal-footer">
					<!-- 验证信息-->
					<span id="AddPro" class="glyphicon"></span>
					<button type="button" id="submitAddPro" class="btn btn-success">
						<span class="glyphicon glyphicon-phone"></span>
						Submit
					</button>
				</div>--%>
			</div>
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/resource/js/jquery-3.3.1.js"></script>
	<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
