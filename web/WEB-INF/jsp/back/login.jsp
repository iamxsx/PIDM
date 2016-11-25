<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 9/17/16
  Time: 9:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">

    <title>广东省价格和产业发展协会管理后台</title>
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrapValidator.min.css">

    <link  rel="stylesheet" href="${ctx}/dist/css/back/index/login.css">
    <link rel="stylesheet" href="${ctx}/dist/css/welcome/style.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="shortcut icon" href="${ctx}/imgs/favicon.ico"/>

</head>
<body class="login-page" id="particles">
    <div class="intro">
        <main>
        <div class="login-block">
            <h2>后台管理登陆</h2>
            <form method="post" id="loginForm">
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input type="text" class="form-control" name="username" placeholder="请输入工号" id="username"
                               value="<shiro:principal/>">
                    </div>
                </div>
                <span class="resultMsg" id="username-msg"></span>
                <%--<c:if test="${'org.apache.shiro.authc.UnknownAccountException' eq shiroLoginFailure}">--%>
                    <%--<span class="resultMsg" id="username-msg">账号不存在！</span>--%>
                <%--</c:if>--%>

                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input type="password" class="form-control" name="password" placeholder="请输入密码" id="password">
                    </div>
                </div>
                <span class="resultMsg" id="password-msg"></span>
                <c:if test="${'org.apache.shiro.authc.IncorrectCredentialsException' eq shiroLoginFailure
                              || 'org.apache.shiro.authc.UnknownAccountException' eq shiroLoginFailure}">
                    <span class="resultMsg">账号或密码错误！</span>
                </c:if>

                <%--验证码--%>
                <%--<div class="form-group">--%>
                    <%--<div class="input-group">--%>
                        <%--<input id="captcha-input" type="text" class="form-control" name="kaptchafield" value="" placeholder="请输入验证码"/>--%>
                        <%--<img src="${ctx}/back/code/captcha-image" mce_src="Kaptcha.jpg" id="kaptchaImage" title="点击换一张"/>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<span class="resultMsg" id="captcha-msg"></span>--%>
                <%--<c:if test="${'IncorrectCaptchaCode' eq shiroLoginFailure}">--%>
                    <%--<span class="resultMsg">验证码错误！</span>--%>
                <%--</c:if>--%>

                <div class="form-group">
                    <div class="input-group">
                        <input id="code-input" type="text" class="form-control" name="phoneMsg" value="" placeholder="请输入验证码"/>
                        <button type="button" class="btn" id="getPhoneMsgBtn">获取验证码</button>
                    </div>
                </div>
                <span class="resultMsg" id="code-msg"></span>
                <c:if test="${'IncorrectMsgCode' eq shiroLoginFailure}">
                    <span class="resultMsg">验证码错误！</span>
                </c:if>

                <button type="button" class="btn btn-block" id="loginButton">登录</button>

                <c:if test="${not empty shiroLoginFailure
                            && shiroLoginFailure != 'org.apache.shiro.authc.IncorrectCredentialsException'
                            && shiroLoginFailure != 'org.apache.shiro.authc.UnknownAccountException'
                            && shiroLoginFailure != 'IncorrectCaptchaCode'
                            && shiroLoginFailure != 'IncorrectMsgCode'
                            && shiroLoginFailure != 'KickoutSessionMessage'}">
                    <span class="resultMsg">未知错误！</span>
                </c:if>
                <c:if test="${sessionScope.shiroLoginFailure eq 'KickoutSessionMessage'}">
                    <span class="resultMsg">此账号已在别处登陆!如果不是本人操作，请尽快修改密码！</span>
                </c:if>
            </form>
        </div>
        </main>
    </div>

</body>

<script src="${ctx}/dist/js/jquery.min.js"></script>
<script src="${ctx}/dist/js/bootstrapValidator.min.js"></script>
<script src="${ctx}/dist/js/welcome/jquery.particleground.js"></script>
<script src="${ctx}/dist/js/welcome/demo.js"></script>
<script src="${ctx}/dist/controller/back-login.js"></script>

</html>
