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
        <div class="step2-icn"><em>2</em></div>
        <div class="step3-icn"><em>3</em></div>
        <div class="step4-icn"><em>4</em></div>
        <div class="progress">
            <div class="progress-bar" role="progressbar" style="width: 25%;">
            </div>
        </div>
        <ul>
            <li>填写账户名</li>
            <li>验证身份</li>
            <li>设置新密码</li>
            <li>完成</li>
        </ul>
    </div>
    <form action="${ctx}/new-login/reset-step1" id="f-step1" method="post">
        <input type="hidden" name="token" value="${token}"/>
        <c:if test="${msg != null}">
            <div class="my-row msg font-x">
                <span>温馨提示：${msg}</span>
            </div>
        </c:if>
        <div class="form-box">
            <div class="my-row">
                <div class="left field">
                    <label>账号:</label>
                </div>
                <div class="right field-value">
                    <input type="text" name="account" class="my-form-control">
                </div>
            </div>
            <div class="my-sm-row font-x">
            </div>
            <div class="my-row">
                <div class="left field">
                    <label>验证码:</label>
                </div>
                <div class="left field-value vcode">
                    <input type="text" name="verifycode" id="verifycode">
                    <img src="${ctx}/kaptcha.jpg" alt="验证码" id="kaptchaImage">
                </div>
            </div>
            <div class="my-sm-row font-x">
            </div>
            <button type="submit" class="btn btn-primary form-btn">下一步</button>
        </div>
    </form>
</div>
<div class="full"></div>
<jsp:include page="../footer.jsp"></jsp:include>

<script src="${ctx}/dist/js/jquery.min.js"></script>
<script src="${ctx}/dist/controller/index/index.js"></script>
<script src="${ctx}/dist/controller/section/section.js"></script>
<script src="${ctx}/dist/js/user/jquery.validate.js"></script>
<script src="${ctx}/dist/js/n_user/form-validate.js"></script>
<script src="${ctx}/dist/js/user/messages_cn.js"></script>
<script>
    $().ready(function () {
        generateNav(-1);
    });

    $(function () {
        $('#kaptchaImage').click(function () {
//            $("codeTip").children().remove('h6');
            $(this).attr('src', '../kaptcha.jpg?' + Math.floor(Math.random() * 100));
        })
    })
</script>
</body>
</html>