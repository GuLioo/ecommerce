
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
	<title>SoqLina | Shop Cart</title>
	<script src="${pageContext.request.contextPath}/resource/js/jquery-3.3.1.js"></script>
	<%--<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>--%>
	<script type="text/javascript">
		//查询分页的数据（抽取ajax查询方法）
		function to_page(uid,pn){
			$.ajax({
				url:"/ecommerce_war/admin/adminUserGet",
				data:{"pn": pn,"uid":uid},
				type:"GET",
				success:function(result){
					console.log("分页查询成功了");
					//1.解析并显示员工数据
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

		//解析显示(构建)员工表格
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

		//根据数据创建内容
		function getDataJson(datas) {
			for (var i = 0; i < datas.length; i++) {
				var trow = getDataRow(datas[i], i);
				tbody.appendChild(trow);
				btnApply(datas[i]);
				btnDel(datas[i]);
				var title=document.getElementById("proTitle");
				if(datas[i].uid==0){
					title.textContent="普通用户";
				}
				else if(datas[i].uid==1){
					title.textContent="产品销售商";
				}
				else {
					title.textContent="用户管理员";
				}

				btnAdd(datas[i]);
			}
		}

		//具体创建内容
		function getDataRow(rowData, number) {
			var row = document.createElement('tr'); //创建行
			//auid
			var id = document.createElement('td');
			id.style.width="20%";
			id.innerHTML = rowData.auid;
			row.appendChild(id);
			//username
			var name = document.createElement('td');
			name.style.width="20%";
			name.innerHTML = rowData.username;
			row.appendChild(name);
			//uid
			var select = document.createElement('td');//创建列
			row.appendChild(select);
			var  mySelect = document.createElement( "select" );
			name.style.width="20%";
			mySelect.id = "select"+rowData.auid ;
			mySelect.add( new  Option( "普通用户" , "0" ));
			mySelect.add( new  Option( "产品销售商" , "1" ));
			mySelect.add( new  Option( "网站管理员" , "2" ));
			mySelect.setAttribute("class", "select");
			select.appendChild(mySelect);
			if(rowData.uid!=null){
				if(rowData.uid==0){
					mySelect[0].selected=true;
				}
				else if(rowData.uid==1){
					mySelect[1].selected=true;
				}
				else {
					mySelect[2].selected=true;
				}
			}
			//document.getElementById( rowData.auid)[initSelectedValue(mySelect,rowData.uid)].selected=true;

			//button应用
			var but = document.createElement('td');//创建列
			row.appendChild(but);
			var button = document.createElement('input');
			button.style.width="50px";
			button.setAttribute('type','button');
			button.setAttribute("id", "buttonApply"+rowData.auid);
			button.setAttribute("value", "use");
			button.setAttribute("name", "apply");
			button.setAttribute("class", "apply");
			but.appendChild(button);

			//button删除
			var butDele = document.createElement('td');//创建列
			row.appendChild(butDele);
			var buttonDel = document.createElement('input');
			buttonDel.style.width="20%";
			buttonDel.setAttribute('type','button');
			buttonDel.setAttribute("id","buttonDel"+rowData.auid);
			buttonDel.setAttribute("value", "del");
			buttonDel.setAttribute("name", "del");
			buttonDel.setAttribute("class", "del");
			butDele.appendChild(buttonDel);
			return row;
		}

		function btnAdd(){
			$("#butAdd").click(function() {
				var killPhoneModal=$('#killPhoneModal');
				//显示弹出层
				killPhoneModal.modal({
					//显示弹出层
					show:true,
					/*					//禁止位置关闭
                                        backdrop:'static',*/
					/*					//关闭键盘事件
                                        keyboard:false*/
				});
				//绑定点击事件
				$('#submit').click(function () {
					var userName =document.getElementById("name").value;
					var password =document.getElementById("password").value;
					var uidName =document.getElementById("uid").value;
					var uid;
					if(userName==""){
						alert("用户名不能为空！");
						return false;
					}
					if(password==""){
						alert("密码不能为空！");
						return false;
					}
					if((uidName!="普通用户")&&(uidName!="产品销售商")&&(uidName!="网站管理员")){
						alert("只可填写提供选项");
						return false;
					}
					else if(uidName=="普通用户"){
						uid=0;
					}
					else  if(uidName=="产品销售商"){
						uid=1;
					}
					else {
						uid=2;
					}
					$.ajax({
						type: "post",
						url: "/ecommerce_war/admin/adminAdd",
						data: {"userName": userName,"password": password,"uid": uid},
						dataType: "text",
						success: function (result) {
							console.log(result);
							alert("添加成功");
							refresh();
						},
						error: function (msg) {
							console.log("返回失败");
							alert("发生错误" + msg);
						}
					});
				});
			});
		}

		function btnDel(rowData){
			$("#buttonDel"+rowData.auid).click(function() {
				console.log("点了" + "buttonDel" + rowData.auid)
				if (rowData != null) {
					$.ajax({
						type: "post",
						url: "/ecommerce_war/admin/adminDelete",
						data: {"auid": rowData.auid},
						dataType: "text",
						success: function (result) {
							console.log(result);
							refresh();
							alert("更改成功");
						},
						error: function (msg) {
							console.log("返回失败");
							alert("发生错误" + msg);
						}
					});
				}
			}
			)
		}


		function btnApply(rowData){
			$("#buttonApply"+rowData.auid).click(function(){
				if(rowData!=null){
					var select=document.getElementById("select"+rowData.auid) ;
					if(select!=null){
						var selectId;
						var uid=rowData.uid;
						var  index=select.selectedIndex;  //序号，取当前选中选项的序号
						var  val = select.options[index].value;
						console.log("select="+"  index="+index+" val="+val);
						if(select[0].selected==true){
							selectId=0;
						}
						else if(select[1].selected==true){
							selectId=1;
						}
						else {
							selectId=2;
						}
						if(uid==selectId){
							alert("权限未修改，无需应用");
						}
						else {
							$.ajax({
								type: "post",
								url: "/ecommerce_war/admin/adminUserChange",
								data: {"auid": rowData.auid, "uid": selectId},
								dataType: "text",
								success: function (result) {
									console.log(result);
									alert("更改成功");
/*									if(result=="更改成功")
										alert("更改成功");
									else
										alert("更改失败");*/
									refresh();
								},
								error: function (msg) {
									console.log("返回失败");
									alert("发生错误" + msg);
								}
							});
						}
					}
				}
			})
		}


		function refresh(){
			$.ajax({
				url : "/ecommerce_war/admin/adminUser",
				type : "GET",
				success : function (){
					console.log("目录查询成功");
					to_page(0,1);
				},
				error: function (msg) {
					console.log("返回失败");
					alert("发生错误" + msg);
				}
			});
		}

		$(function (){
			refresh();
		});

	</script>
</head>
<style>
	.host_style{
		width: 250px;/*给td定一个宽度,在这里有3个td，
其他3个td都没有设置宽度，所以这3个td的宽度之和是
table的定义的宽度-td(定义了宽度)*/
	}
</style>


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


</header><!-- #header end -->


	<!-- Document Full Container
	============================================= -->
	<div id="full-container">


		<!-- Content
		============================================= -->
		<section id="content">

			<div id="content-wrap">


				<!-- === Content Main =========== -->
				<div id="content-main" class="section-flat page-single page-shop-cart">

					<div class="section-content">

						<div class="container">
							<div class="row">
								<div class="col-md-12">
								
									<div class="page-single-content">

										<div class="row">
											<div class="col-md-9 col-md-push-3">

												<div class="content">
													<div class="block-content">
														<div id="addButton">
														<h5 class="block-title" id="proTitle"> <button  class="btn btn-primary btn-lg" id="addBtn" width="10px">增加用户</button></h5>
														</div>
														<div class="row">
															<div class="col-md-12">
																<div id="table-shop-cart">
																	<table id="table">
																		<thead>
																			<tr>
																				<th>ID</th>
																				<th>NAME</th>
																				<th>TYPE</th>
																			</tr>
																		</thead>
																		<tbody id="tbody">
																		</tbody>
																	</table>																	
																</div>
																<div class="table-btns">
																	<a class="btn medium colorful hover-grey" id="butAdd">ADD</a>
																</div><!-- .table-btns end -->
																<div id = "page_nav_area">

																</div><!-- .pagination end -->

															</div><!-- .col-md-12 end -->
														</div><!-- .row end -->
													</div>
													<!--.block-content end -->
												</div><!-- .content end -->

											</div><!-- .col-md-12 end -->
											<div class="col-md-3 col-md-pull-9" >
											<div class="sidebar">
												<div class="box-widget">
													<h5 class="box-title">user</h5>
													<div class="box-content" >
														<ul class="sidebar-list-links list-brands" id="category">
															<li><a id="cate0" onclick="to_page(0,1)">普通用户</a></li>
                                                            <li><a id="cate1" onclick="to_page(1,1)">产品销售商</a></li>
                                                            <li><a id="cate2" onclick="to_page(2,1)">用户管理员</a></li>
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
		<!--弹出层-->
		<div id="killPhoneModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title text-center">
							用户信息填写^0^
						</h4>
					</div>
					<div class="input-group">
						<span class="input-group-addon">userName</span>
						<input type="text" name="name" id="name"
							   placeholder="填姓名" class="form-control"/>
					</div>
					<div class="input-group">
						<span class="input-group-addon">password</span>
						<input type="password" name="password" id="password"
							   placeholder="填密码" class="form-control"/>
					</div>
					<div class="input-group">
						<span class="input-group-addon">userState</span>
						<input type="text" name="uid" id="uid"
							   placeholder="普通用户/产品销售商/用户管理员" class="form-control"/>
					</div>

					<div class="modal-footer">
						<!-- 验证信息-->
						<span id="killPhoneMessage" class="glyphicon"></span>
						<button type="button" id="submit" class="btn btn-success">
							<span class="glyphicon glyphicon-phone"></span>
							Submit
						</button>
					</div>
				</div>
			</div>
		</div>

		<script src="${pageContext.request.contextPath}/resource/js/jquery-3.3.1.js"></script>
		<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
		<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
		<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
		<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<%--<script src="${pageContext.request.contextPath}/resource/js/jquery.js"></script>--%>
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
