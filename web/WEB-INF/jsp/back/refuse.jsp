<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 9/17/16
  Time: 9:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">

    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>组织结构</title>
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
</head>
<body>
    <jsp:include page="header.jsp"/>

    <div class="container-fluid">
        <div class="row">
            <br><br><br>
            <div class="col-md-6 col-md-offset-3">
                <h3>对不起，您没有权限访问此页面，请联系管理员!</h3>
            </div>
        </div>
    </div>

</body>

<script src="${ctx}/dist/js/jquery.min.js"></script>
<script src="${ctx}/dist/js/bootstrap.min.js"></script>
<script src="${ctx}/dist/js/bootstrap-table.js"></script>
<script src="${ctx}/dist/js/bootstrap-table-zh-CN.js"></script>
</html>
