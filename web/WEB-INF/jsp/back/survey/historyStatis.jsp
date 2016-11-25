<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>调查表统计</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>


    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav.css">

</head>
<body>
<jsp:include page="../header.jsp"/>
<jsp:include page="surveyLeft.jsp"/>
<div class="col-md-9 right-panel">
    <span style="margin: 10px 0;">当前位置 ： 调查记录</span>
    <div style="margin: 20px 0">
        <div id="statisTable"></div>
    </div>
</div>
</body>

<script src="${ctx}/dist/js/jquery.min.js"></script>
<script src="${ctx}/dist/js/index/jquery.login.js"></script>
<script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
<script src="${ctx}/dist/js/bootstrap-table.js"></script>
<script src="${ctx}/dist/js/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/dist/js/left_nav.js"></script>
<script src="${ctx}/dist/js/index/amazeui.min.js"></script>
<script src="${ctx}/dist/controller/index/index.js"></script>

<script src="${ctx}/dist/js/survey/statisTable.js"></script>
<script>
    $(function () {
        var lis = $('.left-nav .add-active');
        $(lis[3]).addClass('active');
    });
</script>
</html>
