<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
								console.log("返回成功========================");
								var killResult=result['data'];
								var state=killResult['state'];
								var adminUser=killResult['adminUser'];
								var uid=adminUser.uid;
								console.log("uid="+uid);
								if(state==1){
									console.log("用户管理员");
									location.href="/ecommerce_war/admin/adminUser";
								}
								else if(state==2){
									console.log("产品销售商");
									location.href="/ecommerce_war/saler/salerRefresh";
								}
								else if(state==3){
									alert("还没写");
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

		</header><!-- #header end -->
	
		<!-- Content
		============================================= -->
		<section id="content">

			<div id="content-wrap">


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
																		<input type="button" name="button" value="submit"   id="btn_login" href="javascript:;" onclick="requestJson() ">
																		<%--<a href="#">You forgot password?</a>--%>
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
