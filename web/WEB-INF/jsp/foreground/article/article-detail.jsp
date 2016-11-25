<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 16-9-17
  Time: 上午10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/index/index.css">
    <%--<link rel="stylesheet" href="${ctx}/dist/css/back/article/article.css">--%>
    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <title>文章详情页</title>
    <style>
        .article-content{
            background-color: #FFFFFF;
            box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.16), 0 2px 4px 0 rgba(0, 0, 0, 0.12);
            padding: 24px;
            margin-top: 24px;
            margin-bottom: 24px;
        }

        .article-content h4 {
            font-size:24px;
            font-weight:800;
            text-align:center;
            margin:15px 0 30px;
        }

        .article-content .date {
            display:block;
            text-align:center;
        }
    </style>
</head>
<body>

<jsp:include page="../header.jsp"/>

<div class="container">

    <div class="row">

        <div class="article-content">

            <h4>${article.header}</h4>

            <span class="date">发布时间：${article.publishTime}</span>

            <hr>
            <c:if test="${not empty article.poster}">
                <img src="${ctx}/back/article/showImage?path=${article.poster}" alt="" width="100%" >
            </c:if>

            <div id = "content" style="margin-top: 24px;">
                ${article.content}
            </div>

            <c:if test="${not empty annex.filesurl}">
                <div class="annex-desc" id="perform-annex">
                    <span class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;
                    附件: <a href="${ctx}/back/article/download-annex?filePath=${annex.filesurl}">${annex.annexDesc}</a>　&nbsp;&nbsp;&nbsp;&nbsp;
                    附件说明：${annex.annexDesc}
                </div>
            </c:if>
        </div>

    </div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>
</body>
<script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
<script src="${ctx}/dist/controller/index/index.js"></script>
<script>
    $(function () {
        $('#zlight-nav').zlightMenu();
        generateNav(-1);
    });
</script>
</html>