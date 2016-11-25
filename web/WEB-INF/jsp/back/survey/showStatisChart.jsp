<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>调查问卷统计</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>

    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">


    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <script src="${ctx}/dist/js/index/jquery.login.js"></script>
    <script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
    <script src="${ctx}/dist/js/bootstrap.min.js"></script>
    <script src="${ctx}/dist/js/left_nav.js"></script>
    <script src="${ctx}/dist/js/index/amazeui.min.js"></script>
    <script src="${ctx}/dist/controller/index/index.js"></script>
    <script src="${ctx}/dist/js/echarts.min.js"></script>

    <script src="${ctx}/dist/js/survey/showStatisChart.js"></script>

    <script>
        var id = ${id};
    </script>
    <style>
        .survey-paper-clip{
            position: absolute;
            right: 30px;
            top: -15px;
            background-image:url("${ctx}/imgs/survey/paper_clip.png");
            width: 33px;
            height: 81px;
            z-index: 1000;
        }
    </style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<jsp:include page="surveyLeft.jsp"/>
<div class="col-md-9 right-panel">
        <a class="survey-paper-clip"></a>
        <div style="padding: 20px">
            <div style="text-align: center">
                <h3 id="surveyTitle"></h3>
                <hr>
            </div>
            <div id="questionList">
            </div>
        </div>
</div>
</body>
<script>
    $(function () {
        var lis = $('.left-nav .add-active');
        $(lis[3]).addClass('active');
    });
</script>
</html>
