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
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/login.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav_frontend.css">
    <title>提交修改申请成功</title>
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
        <div class="col-md-10 right-panel" id="container">
            <div id="msg-box" style="width: 650px;height: 300px;margin: 50px auto;">
                <div style="height: 260px;line-height:260px;text-align: center;">
                    <span style="letter-spacing: 2px;"><span style="font-size: 18px;color: cornflowerblue">${msg}，</span><span>页面将在<i id="time">6</i>秒后自动跳转...</span></span>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script src="${ctx}/dist/js/jquery.min.js"></script>
<script src="${ctx}/dist/js/index/jquery.login.js"></script>
<script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
<script src="${ctx}/dist/js/bootstrap.min.js"></script>
<script src="${ctx}/dist/js/left_nav.js"></script>
<script src="${ctx}/dist/js/index/amazeui.min.js"></script>
<script src="${ctx}/dist/controller/index/index.js"></script>
<script>
    $(function () {
        $('#zlight-nav').zlightMenu();
        generateNav(-1);
        setTimeout(ChangeTime, 1000);
    });
    function ChangeTime() {
        var time;
        time = $("#time").text();
        time = parseInt(time);
        time--;
        if (time <= 0) {
            window.location.href="${ctx}/nUser/showNUserInfo";
        } else {
            $("#time").text(time);
            setTimeout(ChangeTime, 1000);
        }
    }
</script>
</html>