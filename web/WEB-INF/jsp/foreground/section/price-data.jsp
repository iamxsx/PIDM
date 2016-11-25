<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <link rel="stylesheet" href="${ctx}/dist/css/index/font-awesome.min.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/index/index.css">
    <%--<link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">--%>
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav_frontend.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/login.css">
    <link rel="shortcut icon" href="${ctx}/imgs/favicon.ico"/>

    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <%--<script src="${ctx}/dist/js/index/jquery.login.js"></script>--%>
    <script src="${ctx}/dist/js/bootstrap.min.js"></script>
    <script src="${ctx}/dist/js/left_nav.js"></script>
    <script type="text/javascript" src="${ctx}/dist/js/index/amazeui.min.js"></script>
    <style>
        .left-nav-item {
            height: 100%;
            text-align: center;
            margin-left: 0;
        }
    </style>

    <title>广东省价格和产业发展协会</title>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/foreground/header.jsp"/>


<div class="container">
    <div class="row" style="margin-top: 24px;">
        <div class="col-md-2">
            <ul class="left-nav" id="left-nav">
                <li class="head">
                    <div class="left-nav-item">
                        <span>价格数据</span>
                    </div>
                </li>

            </ul>
        </div>
        <div class="col-md-10 right-panel">
            <h4>
                <ol class="breadcrumb">
                    <li>
                        ${menuName}
                    </li>
                </ol>
            </h4>
            <div class="list-tab">
                <table id="article-table"></table>
            </div>
            <div class="col-md-8 col-md-offset-2" style="margin-top: 48px;text-align: center">
                <ul id="pagn"></ul>
            </div>
        </div>
    </div>
    <div style="height:260px;"></div>


</div>


<jsp:include page="../footer.jsp"></jsp:include>


<!-- container close -->

</body>
<script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
<script src="${ctx}/dist/controller/index/index.js"></script>
<script src="${ctx}/dist/js/bootstrap-paginator.min.js"></script>
<script src="${ctx}/dist/controller/index/pagination.js"></script>
<script src="${ctx}/dist/controller/section/section.js"></script>
<script>
    $(function () {
//        $('#zlight-nav').zlightMenu();
        generateNav(5);
        getArticleCount('${menuId}','${menuName}');
        findArticleByMenuName('${menuId}','${menuName}');
        getSecondMenuByMenuName('价格数据','${menuName}');
    });
</script>
</html>
