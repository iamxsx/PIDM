<%--
  Created by IntelliJ IDEA.
  User: AngryFeng
  Date: 16-10-16
  Time: 下午10:25
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>

    <meta charset="UTF-8">
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/n_user/register.css">
    <link rel="stylesheet" href="${ctx}/dist/css/n_user/msg.css">
    <title>跳转页面</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<%--<header class="header-box">
    <a href="#"><img src="${ctx}/imgs/logo.png"></a>
    <a class="g-index" href="${ctx}/index">回到首页</a>
</header>
<div class="msg-titel"></div>--%>
<div id="msg-box" class="msg-sty">
    <div class="msg-header"></div>
    <div class="msg-tip">
        <span class="info">${msg}，页面将在<i id="time">5</i>秒后自动跳转...</span>
    </div>
</div>
<form action="${ctx}/new-register/redirect" id="redirectForm" method="post">
    <input type="hidden" name="msg" value="${msg}">
</form>
<jsp:include page="../footer.jsp"></jsp:include>

</body>
<script src="${ctx}/dist/js/jquery.min.js"></script>
<script src="${ctx}/dist/controller/index/index.js"></script>
<script src="${ctx}/dist/controller/section/section.js"></script>
<script>
    $().ready(function () {
        generateNav(-1);
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