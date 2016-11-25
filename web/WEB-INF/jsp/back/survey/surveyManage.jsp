<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 10/11/16
  Time: 12:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>调查表管理</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav.css">

    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <script src="${ctx}/dist/js/index/jquery.login.js"></script>
    <script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
    <script src="${ctx}/dist/js/bootstrap.min.js"></script>
    <script src="${ctx}/dist/js/left_nav.js"></script>
    <script src="${ctx}/dist/js/index/amazeui.min.js"></script>
    <script src="${ctx}/dist/controller/index/index.js"></script>
    <style>
        .rightPanel{
            background-color: #ffffff;
            box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.16), 0 2px 4px 0 rgba(0, 0, 0, 0.12);
            padding-top: 16px;
            padding-bottom: 32px;
            margin-bottom: 32px;
        }
        .fontPanel{
            width: 90%;
            margin: 15em auto;
            text-align: center;
        }
        .fontPanel a{
            font-size: 18px;

        }
    </style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div>
    <jsp:include page="surveyLeft.jsp"/>
    <div class="col-md-9 rightPanel">
        <div class="fontPanel">
            <a href="${ctx}/back/survey/questionSurvey">${mess}</a>
        </div>
    </div>
</div>
</body>
<%--<script>--%>
    <%--$(function () {--%>
        <%--var lis = $('.left-nav .add-active');--%>
        <%--$(lis[0]).addClass('active');--%>
    <%--});--%>
<%--</script>--%>
</html>
