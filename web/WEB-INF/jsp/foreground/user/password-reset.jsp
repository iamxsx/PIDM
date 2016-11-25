<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 9/21/16
  Time: 4:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/back/article/article.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/login.css">
    <script src="${ctx}/dist/js/jquery.min.js"></script>

    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/user/style.css">
    <meta charset="UTF-8">
    <title>找回密码</title>
</head>
<body onload="/*createCode(),*/createFormCode()">
<jsp:include page="../header.jsp"/>

<div class="password-reset">
    <div class="col-md-12 p-titel">
        <label>找回密码</label><br/>
        <label style="color: red;font-size: 16px;">${msg}</label>
    </div>
    <form action="${ctx}/login/reset-password" method="post" id="pForm" class="form-horizontal" role="form">
        <div class="form-group">
            <label for="pAccount" class="col-sm-2 control-label"><span class="font-x">*</span>帐号：</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="pAccount" placeholder="请输入帐号" name="account">
            </div>
        </div>
        <div class="form-group">
            <label for="pEmail" class="col-sm-2 control-label"><span class="font-x">*</span>申请人邮箱:</label>
            <div class="col-sm-8">
                <input type="text" id="pEmail" class="form-control" placeholder="请输入邮箱" name="email">
            </div>
        </div>
        <div class="form-group">
            <label for="pPassword" class="col-sm-2 control-label"><span class="font-x">*</span>新密码:</label>
            <div class="col-sm-8">
                <input type="password" id="pPassword" class="form-control" placeholder="请输入8~16位密码" name="password">
            </div>
        </div>
        <div class="form-group">
            <label for="pRPassword" class="col-sm-2 control-label"><span class="font-x">*</span>确认密码:</label>
            <div class="col-sm-8">
                <input type="password" id="pRPassword" class="form-control" placeholder="请输入确定密码" name="repassword">
            </div>
        </div>
        <div class="form-group" id="fcodediv">
            <label for="pCode" class="col-sm-2 control-label"><span class="font-x">*</span>验证码:</label>
            <div class="col-sm-6">
                <span><input type="text" id="pCode" class="form-control" placeholder="验证码" name="code"></span>
                <small style="color: #b94a48;font-style: normal" class="help-block"></small>
            </div>
            <div class="col-md-3" style="text-align: left">
                <input type="text" name="fcode" onclick="createFormCode()" readonly="readonly" id="checkFormCode"
                       class="unchanged" style="width: 80%"/>
            </div>
        </div>
        <div class="form-group">
            <label for="pVerifycode" class="col-sm-2 control-label"><span class="font-x">*</span>邮箱验证码:</label>
            <div class="col-sm-6">
                <input type="text" id="pVerifycode" class="form-control" placeholder="邮箱验证码" name="verifycode">
            </div>
            <div class="col-md-3">
                <a href="javascript:void(0)" id="pA">获取验证码</a>
            </div>
        </div>
        <div class="pBtn">
            <button id="pBtn" class="btn btn-primary" type="submit">重置密码</button>
        </div>
    </form>
</div>

<div style="height: 80px"></div>

<footer class="footer">
    <div class="col-md-12">
        <p>&copy; 2016 广东省价格和产业发展协会&nbsp;版权所有&nbsp;不得转载 <a href="##">联系我们</a></p>
        <p>粤ICP备05070829 </p>
        <p><a href="#">广州亦云信息技术股份有限公司&nbsp;提供技术支持</a></p>
        <img class="footcode" src="${ctx}/qrcode?text=202.104.231.132:8080/PIDM">
    </div>
</footer>

</body>
<script type="text/javascript" src="${ctx}/dist/js/bootstrap.min.js"></script>
<script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
<script src="${ctx}/dist/controller/index/index.js"></script>
<script src="${ctx}/dist/js/bootstrapValidator.min.js"></script>
<script src="${ctx}/dist/js/user/user.js"></script>
<script src="${ctx}/dist/js/user/jquery.validate.js"></script>
<script src="${ctx}/dist/js/user/form-validate.js"></script>
<script src="${ctx}/dist/js/user/messages_cn.js"></script>
<script>
    $().ready(function () {
        generateNav(-1);
    });

    $(function () {

        $('#zlight-nav').zlightMenu();

        //获取短信验证码
        $("#pA").click(function () {
            if ($("#pAccount").val().trim().length > 0 && $("#pEmail").val().trim().length > 0) {
                var validCode = true;
                var time = 30;
                var code = $(this);
                if (validCode) {
                    validCode = false;
                    code.addClass("msgs1");
                    var t = setInterval(function () {
                        time--;
                        code.html(time + "秒");
                        if (time == 0) {
                            clearInterval(t);
                            code.html("重新获取");
                            validCode = true;
                            code.removeClass("msgs1");

                        }
                    }, 1000)
                }

                $.ajax({
                    url: "../login/get-verifycode",
                    type: "post",
                    data: {
                        account: $("#pAccount").val(),
                        email: $("#pEmail").val(),
                    },
                    success: function (data) {
                        code.html("请查收邮箱");
                    }
                })
            } else {
                alert("邮箱和账号必须填写");
//                    $("#pAccount").parent().parent().addClass('has-error');
            }
        })

        /*$("#pBtn").click(function () {
         $("#pForm").submit();
         })*/
    })
    /*
     $("#pA").click(function () {
     var time=30;
     var code=$(this);
     if (validCode) {
     validCode=false;
     code.addClass("msgs1");
     var t=setInterval(function  () {
     time--;
     code.html(time+"秒");
     if (time==0) {
     clearInterval(t);
     code.html("重新获取");
     validCode=true;
     code.removeClass("msgs1");

     }
     },1000)
     }
     $.ajax({
     url:"login/get-verifycode",
     data:{
     account:$(""),
     email:email
     },
     success:function (data) {

     }
     })
     })
     */
    var fcode;
    function createFormCode() {
        fcode = "";
        var codeLength = 6;//验证码的长度
        var checkCode = document.getElementById("checkFormCode");
        var selectChar = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');//所有候选组成验证码的字符，当然也可以用中文的

        for (var i = 0; i < codeLength; i++) {


            var charIndex = Math.floor(Math.random() * 36);
            fcode += selectChar[charIndex];


        }
//       alert(code);
        if (checkCode) {
            checkCode.className = "code";
            checkCode.value = fcode;
        }

    }
</script>
</html>