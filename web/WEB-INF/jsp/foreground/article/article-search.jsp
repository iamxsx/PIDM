<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 16-9-16
  Time: 上午8:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link rel="stylesheet" href="${ctx}/dist/css/back/article/article.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/index/index.css">
    <link rel="shortcut icon" href="${ctx}/imgs/favicon.ico"/>
    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <title>搜索结果</title>
    <style>
        body{
            background-color: #f6f6f6;
        }
    </style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">

            <span> 共检索到 ${result.total} 条数据</span>

            <div class="article-list">

                <c:forEach items="${result.articles}" var="article">
                    <div class="article">
                        <div class="header">
                            <h3>
                                <a href="/article/detail/${article.id}">${article.header}</a>
                            </h3>
                        </div>
                        <c:out value="${article.content}" escapeXml="false"/>
                        <hr>

                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
<script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
<script src="${ctx}/dist/controller/index/index.js"></script>
<script>
    $(function () {
//        $('#zlight-nav').zlightMenu();
        generateNav(-1);
    });

</script>
</html>