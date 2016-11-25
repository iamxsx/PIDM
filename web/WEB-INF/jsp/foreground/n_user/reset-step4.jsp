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
    <link rel="stylesheet" href="${ctx}/dist/css/n_user/reset.css">
    <title>找回密码-step1</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="re-main">
    <div id="process-box" class="progress-box">
        <div class="step1-icn curr-index"><em>1</em></div>
        <div class="step2-icn curr-index"><em>2</em></div>
        <div class="step3-icn curr-index"><em>3</em></div>
        <div class="step4-icn curr-index"><em>4</em></div>
        <div class="progress">
            <div class="progress-bar" role="progressbar" style="width: 100%;">
            </div>
        </div>
        <ul>
            <li>填写账户名</li><li>验证身份</li><li>设置新密码</li><li>完成</li>
        </ul>
    </div>
    <form id="redirectForm" action="${ctx}/new-register/redirect" method="post">
        <input type="hidden" name="msg" value="${msg}">
        <div class="form-box center">
            <h4 class="">密码修改成功</h4>
            <span><i id="time">5</i>秒后自动跳转到登录页面</span>
        </div>
    </form>
</div>
<div class="full"></div>
<jsp:include page="../footer.jsp"></jsp:include>

<script src="${ctx}/dist/js/jquery.min.js"></script>
<script src="${ctx}/dist/controller/index/index.js"></script>
<script src="${ctx}/dist/controller/section/section.js"></script>
<script src="${ctx}/dist/js/user/jquery.validate.js"></script>
<script src="${ctx}/dist/js/user/form-validate.js"></script>
<script src="${ctx}/dist/js/user/messages_cn.js"></script>
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
</body>
</html>