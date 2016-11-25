<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 9/14/16
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>客户列表</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav.css">
    <link rel="stylesheet" href="${ctx}/dist/css/client/clientList.css">
    <script src="${ctx}/dist/js/jquery.min.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container-fluid" id="container">
    <div class="row">
        <jsp:include page="../regmanage/left.jsp"/>

        <div class="col-md-10 right-panel" >

            <ol class="breadcrumb">
                <span>当前位置：&nbsp&nbsp&nbsp</span>
                <li><a href="#">客户管理</a></li>
                <li><a href="#" class="active">客户信息管理</a></li>
            </ol>

            <div class="container-fluid">
                <form class="navbar-form navbar-left" role="search">
                    <label for="searchcon">检索字段：</label>
                    <div class="btn-group"  id="select-button">
                        <select id="searchcon" class="form-control">
                            <option value="0">帐号</option>
                            <option value="1">单位名称</option>
                            <option value="2">单位地址</option>
                            <option value="3">指定联系人</option>
                            <option value="4">指定联系人手机号码</option>
                            <option value="5">传真号码</option>
                            <option value="6">邮箱</option>
                        </select>
                    </div>

                    <label for="Search">关键字：</label>
                    <div class="form-group">
                        <input id="Search" type="text" class="form-control" placeholder="Search">
                    </div>
                    <button id="searchButton" type="button" class="btn btn-primary">查询
                        <span class="glyphicon glyphicon-search"></span>
                    </button>
                </form>
            </div>

            <div id="buttonList">
                <button id="show-user" type="button" class="btn btn-default">查看</button>
                <shiro:hasPermission name="client:add">
                <button id="add-user" type="button" class="btn btn-default">添加</button>
                </shiro:hasPermission>
                <shiro:hasPermission name="client:query">
                <button id="changebutton" type="button" class="btn btn-default">修改</button>
                </shiro:hasPermission>
                <shiro:hasPermission name="client:delete">
                <button id="delete-button" type="button" class="btn btn-default">删除</button>
                </shiro:hasPermission>
                <shiro:hasPermission name="client:export">
                <button id="export-excel" type="button" class="btn btn-default">导出</button>
                </shiro:hasPermission>
            </div>

            <div class="table-responsive">
                <table id="client-table" class="table table-bordered"></table>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        var lis = $('.left-nav .add-active');
        $(lis[0]).addClass('active');
    });
</script>
<script src="${ctx}/dist/js/bootstrap.min.js"></script>
<script src="${ctx}/dist/js/bootstrap-table.js"></script>
<script src="${ctx}/dist/js/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/dist/controller/client/client-table.js"></script>
<script src="${ctx}/dist/controller/client/client.js"></script>
</html>
