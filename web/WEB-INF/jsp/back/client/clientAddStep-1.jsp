<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 9/21/16
  Time: 9:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta charset="UTF-8">


    <link rel="stylesheet" href="${ctx}/dist/css/index/font-awesome.min.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/user/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/login.css">
    <script src="${ctx}/dist/js/jquery.min.js"></script>


    <script src="${ctx}/dist/js/bootstrap.min.js"></script>


    <link rel="stylesheet" href="${ctx}/dist/css/user/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/user/style.css">
    <title>帐号添加:第一步</title>
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="register1-box" style="margin: 10em auto;">
    <form class="form-horizontal" role="form" id="form1" action="${ctx}/back/user/exist-company" method="post">
        <div class="form-group">
            <div class="col-sm-4">
                <label for="select-company" class="control-label">公司名称</label>
            </div>
            <div class="col-sm-8">
                <select class="form-control" name="name" id="select-company">
                    <option value="0">请选择所属企业</option>
                    <c:forEach items="${companies}" var="company">
                        <option value="${company.name}">${company.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </form>
    <form class="form-horizontal" role="form" id="form2" action="${ctx}/back/user/exist-company" method="post">
        <div class="form-group">
            <div class="col-sm-7">
                <label style="line-height: 30px">如果所属企业不在列表中，请选择</label>
            </div>
            <div class="col-sm-5">
                <label class="radio-inline" id="add">
                    <input type="radio" name="add" value="add" onclick="showContent(this)"/>添加
                </label>
                <label class="radio-inline" id="cancle">
                    <input type="radio" name="add" value="cancle" onclick="showContent(this)" checked="checked"/>取消
                </label>
            </div>
        </div>
        <div class="form-group" id="new-company">
        </div>
    </form>
    <button type="button" class="btn btn-default" onclick="register1()">下一步</button>
</div>
<%--<footer class="footer">
    <p>© 2016 广东省价格和产业发展协会&nbsp;版权所有&nbsp;不得转载
        <a href="##">联系我们</a>
    <p>粤ICP备&nbsp;66666号&nbsp;广州亦云信息技术股份有限公司&nbsp;提供技术支持</p>
</footer>--%>
</body>
<script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
<script type="text/javascript" src="${ctx}/dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(function () {
        $('#zlight-nav').zlightMenu();
    });
    function showContent(node) {
        var oDivNode = document.getElementById("new-company");
        with (oDivNode.style) {
            if (node.value == 'add') {
                $("#child").remove();
                $("#new-company").append('<div id="child"><label class="col-sm-4 control-label">新增企业名称</label><div class="col-sm-8"><input type="text" class="form-control" id="input3" placeholder="请输入公司名称" name="name"></div></div>');
            } else {
                $("#child").remove();
            }
        }
    }
    function register1() {
        if($("#input3").val() == null) {
            if ($("#select-company").val() == "0") {
                alert("请填写所属公司");
                return;
            } else {
                alert("旧公司注册");
                $("#form1").submit();
                return;
            }
        } else {
            if ($("#select-company").val() == "0" && $("#input3").val().trim(" ").length == 0) {
                alert("请填写所属公司");
                return;
            }
            if ($("#select-company").val() != "0" && $("#input3").val().trim(" ").length != 0) {
                alert("不能可以同时填写两个公司哦");
                return;
            }
            if ($("#select-company").val() == "0") {
                alert("创建新公司");
                $("#form2").submit();
            } else {
                alert("创建旧公司");
                $("#form1").submit();
            }

        }
    }
</script>
