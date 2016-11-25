
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>

<html>
<head>
    <title>交流详情</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>

    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/amazeui.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/login.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav_frontend.css">
    <link rel="stylesheet" href="${ctx}/dist/css/communicate/back.css">
    <link rel="shortcut icon" href="${ctx}/imgs/favicon.ico"/>

    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <script src="${ctx}/dist/js/index/jquery.login.js"></script>
    <script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
    <script src="${ctx}/dist/js/bootstrap.min.js"></script>
    <script src="${ctx}/dist/js/left_nav.js"></script>
    <script src="${ctx}/dist/js/index/amazeui.min.js"></script>
    <script src="${ctx}/dist/controller/index/index.js"></script>
    <script src="${ctx}/dist/js/communicate/back.js"></script>

    <script>
        var communicateId = "${communicateId}";
    </script>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/foreground/header.jsp"/>

<div class="container">
    <div class="row" style="margin-top: 24px;">
        <div class="col-md-2">
            <ul class="left-nav">
                <li class="head">
                    <div class="left-nav-item">
                        <span>个人中心</span>
                    </div>
                </li>
                <li>
                    <div class="left-nav-item">
                        <a href="../work/to-personal">
                            消息管理
                        </a>
                    </div>
                </li>
                <li>
                    <div class="left-nav-item">
                        <a href="${ctx}/nUser/showNUserInfo">
                            信息管理
                        </a>
                    </div>
                </li>
            </ul>
        </div>

        <div class="col-md-10">

            <span style="margin-top: 10px; ">当前位置 ： 个人中心 > 咨询、建议、意见查看</span>
            <hr>
            <%--标题内容--%>
            <div id="title-content-info"></div>
            <%--回复问答--%>
            <div>
                <table class="table table-striped table-bordered" id="reply-question-table"></table>
            </div>

            <div style="margin: 10px 0">
                <textarea id="textareaContent" class="form-control" placeholder="请输入回复的内容" style="width: 100%"></textarea>
                <div class="btn btn-primary" onclick="submitQuestion()">提交</div>
            </div>
        </div>
    </div>
</div>
<div style="height: 100px"></div>
<jsp:include page="../footer.jsp"></jsp:include>

</body>
<script>
    $(function () {
        $('#zlight-nav').zlightMenu();
        generateNav(-1);
    });
</script>
</html>
