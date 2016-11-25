<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 9/20/16
  Time: 10:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta charset="UTF-8">
    <meta name="viewport"
    content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/back/article/article.css">
    <script src="${ctx}/dist/js/jquery.min.js"></script>

    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/user/style.css">
    <title>跳转页面</title>
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="jump-box">
    ${msg},<span id="time">5</span>秒钟页面自动跳转<a href="javascript:void(0);"></a>
    <%--<c:if test="${msg == '验证码错误' || msg == '此用户邮箱已验证，请勿重复验证' || msg == '验证成功' || msg == '注册成功，请注意邮箱查收，已激活帐号'}" ></c:if>--%>
        <form action="${ctx}/register/redirect" id="redirectForm" method="post">
            <input type="hidden" name="msg" value="${mag}">
        </form>
</div>

</body>
<script type="text/javascript" src="${ctx}/dist/js/bootstrap.min.js"></script>
<script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
<script src="${ctx}/dist/controller/index/index.js"></script>
<script>
    $().ready(function () {
        generateNav(-1);
    });

    $(function () {
        $('#zlight-nav').zlightMenu();
    });


    $(function () {
        setTimeout(ChangeTime, 1000);
    });

    function ChangeTime() {
        var time;
        time = $("#time").text();
        time = parseInt(time);
        time--;
        if (time <= 0) {
            $("#redirectForm").submit();
        } else {
            $("#time").text(time);
            setTimeout(ChangeTime, 1000);
        }
    }
</script>
</html>
