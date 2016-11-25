<%--
  Created by IntelliJ IDEA.
  User: AngryFeng
  Date: 9/12/16
  Time: 9:00 AM
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
    <link rel="stylesheet" href="${ctx}/dist/css/index/login.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/article/article.css">
    <script src="${ctx}/dist/js/jquery.min.js"></script>

    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/user/style.css">
    <title>注册:第一步</title>
</head>
<body <%--onload="createCode()"--%>>
<jsp:include page="../header.jsp"/>
<div class="register1-box">
    <form class="form-horizontal" id="r-form1" action="${ctx}/register/existCompany" method="post">
        <div class="form-group">
            <div class="col-xs-4">
                <label for="select-company" class="control-label">公司名称</label>
            </div>
            <div class="col-xs-7">
                <select class="form-control" name="name" id="select-company">
                    <option value="0">请选择所属企业</option>
                    <c:forEach items="${companies}" var="company">
                        <option value="${company.name}">${company.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </form>

    <form class="form-horizontal" id="r-form2" action="${ctx}/register/existCompany" method="get">
        <div class="form-group">
            <div class="col-xs-7">
                <label style="line-height: 30px">如果所属企业不在列表中，请选择</label>
            </div>
            <div class="col-xs-4">
                <label class="radio-inline" id="add">
                    <input style="margin-top: 0" type="radio" name="add" value="add" onclick="showContent(this)"/>添加
                </label>
                <label class="radio-inline" id="cancle">
                    <input style="margin-top: 0" type="radio" name="add" value="cancle" onclick="showContent(this)"
                           checked="checked"/>取消
                </label>
            </div>
        </div>
        <div class="form-group" id="new-company">
        </div>
    </form>
    <button type="button" class="btn btn-default" id="r-btn">下一步</button>
</div>
<div style="height: 300px"></div>
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
<script type="text/javascript">
    $().ready(function () {
        generateNav(-1);
    });

    $(function () {
        $('#zlight-nav').zlightMenu();


        $("#r-btn").click(
                function register1() {
                    if ($("#input3").val() == null) {
                        if ($("#select-company").val() == "0") {
                            alert("请填写所属公司");
                            return false;
                        } else {
//                            alert("已存在公司注册");
                            $("#r-form1").submit();
                            <%--window.location.href = "${ctx}/register/existCompany?name=" + $("#select-company").val();--%>
                            return false;
                        }
                    } else {
                        if ($("#select-company").val() == "0" && $("#input3").val().trim(" ").length == 0) {
                            alert("请填写所属公司");
                            return false;
                        }
                        if ($("#select-company").val() != "0" && $("#input3").val().trim(" ").length != 0) {
                            alert("不能可以同时填写两个公司");
                            return false;
                        }
                        if ($("#select-company").val() == "0") {
//                            alert("创建新公司");
                            $("#r-form2").submit();
                            return false;
                        } else {
//                            alert("已存在公司注册");
                            $("#r-form1").submit();
                            return false;
                        }

                    }
                }
        );

    });

    function showContent(node) {
        var oDivNode = document.getElementById("new-company");
        with (oDivNode.style) {
            if (node.value == 'add') {
                $("#child").remove();
                $("#new-company").append('<div id="child"><label class="col-xs-4 control-label">新增企业名称</label><div class="col-xs-7"><input type="text" class="form-control" id="input3" placeholder="请输入公司名称" name="name"></div></div>');
            } else {
                $("#child").remove();
            }
        }
    }
</script>
</html>