<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 16-9-12
  Time: 上午8:32
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
    <link rel="stylesheet" href="${ctx}/dist/css/back/article/article.css">
    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <title>文章预览</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<div class="container">

    <div class="row">

        <div class="col-md-12 right-panel">

            <h4>${article.header}</h4>

                <span>发布时间：${article.publishTime}</span>

            <hr>


            <img src="${ctx}/back/article/showImage?path=${article.poster}" alt="" width="100%" >

            
            <div id = "content" style="margin-top: 24px;">
                ${article.content}
            </div>
            <c:if test="${not empty article.posterDesc}">
                <div class="annex-desc" id="perform-poster">
                    <span class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;
                    展示图片: <a href="${ctx}/back/article/showImage?path=${article.poster}"
                             target="_blank">${article.poster}</a>　&nbsp;&nbsp;&nbsp;&nbsp;
                    图片说明：${article.posterDesc}

                </div>
            </c:if>
            <c:if test="${not empty annex.filesurl}">
                <div class="annex-desc" id="perform-annex">
                    <span class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;
                    附件: <a href="${ctx}/back/article/download-annex?filePath=${annex.filesurl}">${annex.filesurl}</a>　&nbsp;&nbsp;&nbsp;&nbsp;
                    附件说明：${annex.annexDesc}
                </div>
            </c:if>


        </div>

    </div>
</div>
</body>
<script src="${ctx}/dist/js/left_nav.js"></script>
</html>
