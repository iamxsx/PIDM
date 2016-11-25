
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!doctype html>

<html>
<head>
    <title>交流详情</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>

    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav.css">
    <link rel="stylesheet" href="${ctx}/dist/css/communicate/back.css">

    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <script src="${ctx}/dist/js/bootstrap-table.js"></script>
    <script src="${ctx}/dist/js/communicate/back.js"></script>

    <script>
        var communicateId = "${communicateId}";
    </script>
</head>
<body>

<jsp:include page="../header.jsp"/>

<div class="container-fluid" style="padding: 16px 36px;">
    <div class="row">
        <jsp:include page="communicateLeft.jsp"/>

        <div class="col-md-10 right-panel" id="container">

            <span style="margin-top: 10px; ">当前位置 ： 交流园地管理 > 咨询、建议、意见管理</span>
            <hr>
            <%--标题内容--%>
            <div id="title-content-info"></div>
            <%--回复问答--%>
            <div>
                <table class="table table-striped table-bordered" id="reply-question-table"></table>
            </div>

            <shiro:hasPermission name="communicate:reply">
            <div>
                <div class="input-group input-group-lg">
                    <textarea id="textareaContent" class="form-control" placeholder="请输入回复的内容" maxlength="250"></textarea>
                </div>
                <div class="btn btn-primary" onclick="submitReply()">提交</div>
            </div>
            </shiro:hasPermission>
        </div>
    </div>
</div>
</body>
</html>
