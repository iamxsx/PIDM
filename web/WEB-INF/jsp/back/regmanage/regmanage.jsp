<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 16-9-19
  Time: 下午7:57
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
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap-table.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav.css">
    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <script src="${ctx}/dist/js/bootstrap.min.js"></script>
    <title>注册信息管理</title>
    <style>
        body{
            font-size: 1.4em;
        }
    </style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container-fluid">

    <div class="row">
        <jsp:include page="left.jsp"/>
        <div class="col-md-10 right-panel" style="font-family: 'Microsoft YaHei';color: black">
            <ol class="breadcrumb">
                <span>当前位置：</span>
                <li><a href="#">客户管理</a></li>
                <li class="active">注册信息管理</li>
            </ol>
            <div class="table-responsive">
                <div class="form-group">
                    <form class="navbar-form navbar-left" role="search">
                        <span style="line-height: 42px;margin-right: 10px">检索字段:</span>
                        <div class="form-group" style="margin-right: 10px">
                            <select id="table-type" class="select">
                                <option value="">请选择</option>
                                <option value="1">客户编号</option>
                                <option value="2">单位名称</option>、
                                <option value="3">加入协会</option>
                                <option value="4">加入分会</option>
                            </select>
                        </div>
                        <span style="line-height: 42px;margin-right: 10px">关键字</span>
                        <input id="regmanege-input" type="text" style="margin-right: 10px">
                        <button class="btn btn-primary" type="button" id="to-do-list-btn" onclick="tofind()">
                            <span class="glyphicon glyphicon-search"></span>查询
                        </button>
                    </form>
                </div>
                <br>
                <table id="to-do-table" class="table table-bordered"></table>
            </div>
        </div>
    </div>
</div>
</body>
<script src="${ctx}/dist/js/bootstrap-table.js"></script>
<script src="${ctx}/dist/js/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/dist/js/left_nav.js"></script>
<script src="${ctx}/dist/controller/staging/date.js"></script>
<script src="${ctx}/dist/controller/regmanage/to-regmanage.js"></script>
</html>

