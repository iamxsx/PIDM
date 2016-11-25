<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 16-9-11
  Time: 下午9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav.css">

    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <title>文章列表</title>

    <style>




    </style>
</head>
<body>

<jsp:include page="../header.jsp"/>

<div class="container-fluid">

    <div class="row">

        <jsp:include page="../left.jsp"/>

        <div class="col-md-10 right-panel">

            <ol class="breadcrumb">
                <li><a href="#">信息发布</a></li>
                <li class="active">查看所有文章</li>
            </ol>

            <div class="table-responsive">
                 <table id="article-table" class="table table-bordered"  style="background-color: #ffffff"></table>
            </div>

        </div>

    </div>

</div>

</body>
<script src="${ctx}/dist/js/bootstrap-table.js"></script>
<script src="${ctx}/dist/js/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/dist/js/left_nav.js"></script>
<script src="${ctx}/dist/controller/article/article-table.js"></script>


</html>
