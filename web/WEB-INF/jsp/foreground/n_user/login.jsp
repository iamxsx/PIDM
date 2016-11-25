<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta charset="UTF-8">
    <%--<meta name="viewport" content="width=device-width,initial-scale=1.0">--%>
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/n_user/login.css">
    <link rel="shortcut icon" href="${ctx}/imgs/favicon.ico"/>

    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <title>登录</title>
</head>
<body>
<div class="l-header clearfix">
    <a href="${ctx}/index"><img src="${ctx}/imgs/logo.png"></a>
    <a class="g-index" href="${ctx}/index">回到首页</a>
</div>
<div class="login-main">
    <%--<div class="qr-code">
        <h4>微信二维码即将登陆，敬请期待</h4>
        <img src="${ctx}/qrcode?text=202.104.231.132:8080/PIDM" alt="二维码">
    </div>--%>
    <div class="login-box">
        <div class="login-title">
            <span style="float:left;">登录</span>
            <span style="float: right;color: #357ebd"><a href="${ctx}/new-register/redirect?msg=goto-register">免费注册</a></span>
        </div>
        <form id="login-form" method="post" action="${ctx}/new-login/login">
            <input type="hidden" name="token" value="${token}"/>
            <div class="in-account f-field clearfix">
                <div class="icon-img f-left">
                    <img src="${ctx}/imgs/user/dl_yh.png">
                </div>
                <div class="f-in f-right">
                    <input type="text" placeholder="请输入账号" name="account">
                </div>
            </div>
            <div class="error"></div>
            <div class="in-password f-field clearfix">
                <div class="icon-img f-left">
                    <img src="${ctx}/imgs/user/dl_mm.png">
                </div>
                <div class="f-in f-right">
                    <input type="password" placeholder="请输入密码" name="password">
                </div>
            </div>
            <div class="error"></div>
            <div class="in-verifycode f-field clearfix">
                <div class="icon-img f-left">
                    <img src="${ctx}/imgs/user/dl_yzm.png">
                </div>
                <div class="f-in-code f-left">
                    <input id="verifycode" type="text" placeholder="请输入验证码" name="verifycode"/>
                </div>
                <div class="f-code-img f-right">
                    <img alt="验证码" src="${ctx}/kaptcha.jpg" id="kaptchaImage"/>
                </div>
            </div>
            <div class="error" id="codeTip"></div>
            <div class="l-row clearfix">
                <div class="f-left">
                    <input id="ifSave" type="checkbox" name="isRemember"/>&nbsp;记住密码
                </div>
                <div class="f-right">
                    <a href="${ctx}/new-register/redirect?msg=goto-reset">忘记密码</a>
                </div>
            </div>
            <c:if test="${msg != null}">
                <h6 class="e-info" id="codeTip">${msg}</h6>
            </c:if>
            <div class="l-row clearfix" style="height: auto">
                <button type="submit" id="login" class="btn btn-primary btn-lg btn-block r-btn">登录</button>
            </div>
        </form>
    </div>
</div>

<%--<footer class="footer">--%>
    <%--<div class="col-md-12" style="position: relative">--%>
        <%--<p>&copy; 2016 广东省价格和产业发展协会&nbsp;版权所有&nbsp;不得转载 <a href="##">联系我们</a></p>--%>
        <%--<p>粤ICP备05070829 </p>--%>
        <%--<p><a href="#">广州亦云信息技术股份有限公司&nbsp;提供技术支持</a></p>--%>
        <%--<span class="foot-tip">微信二维码即将登陆，敬请期待</span>--%>
        <%--<img class="footcode" src="${ctx}/qrcode?text=202.104.231.132:8080/PIDM">--%>
    <%--</div>--%>
<%--</footer>--%>
<jsp:include page="../footer.jsp"></jsp:include>
</body>
<script src="${ctx}/dist/js/user/jquery.validate.js"></script>
<script src="${ctx}/dist/js/user/form-validate.js"></script>
<script src="${ctx}/dist/js/user/messages_cn.js"></script>
<script>
    $(function () {
        $('#kaptchaImage').click(function () {
            $("#codeTip").children().remove('h6');
            $(this).attr('src', '../kaptcha.jpg?' + Math.floor(Math.random() * 100));
        })

        /*$('#codeTip').bind('DOMNodeInserted', function (e) {
         if ($('#codeTip').children().text() == "验证码不正确") {
         alert("hello");
         $('#kaptchaImage').attr('src', '../kaptcha.jpg?' + Math.floor(Math.random() * 100));
         return;
         }
         });*/

        /* $('#codeTip').keyup(function () {
         alert("hello");
         $('#kaptchaImage').attr('src', '../kaptcha.jpg?' + Math.floor(Math.random()*100) );
         })*/

        /* if($('#codeTip').children().text().trim() != "") {
         $('#kaptchaImage').attr('src', '../kaptcha.jpg?' + Math.floor(Math.random()*100) );
         }*/
    });
</script>
</html>