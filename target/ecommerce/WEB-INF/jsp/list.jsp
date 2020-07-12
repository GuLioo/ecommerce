<%--
  Created by IntelliJ IDEA.
  User: 谷粒
  Date: 2020/2/11
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>秒杀列表页</title>
    <%@include file="common/head.jsp"%>
    <%@include file="common/tag.jsp"%>
</head>
<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>秒杀列表</h2>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>userID</th>
                    <th>userName</th>
                    <th>userType</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="sk" items="${adminUser}">
                    <tr>
                        <td>${sk.auid}</td>
                        <td>${sk.username}</td>
                        <td>
                                ${sk.uid}
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
