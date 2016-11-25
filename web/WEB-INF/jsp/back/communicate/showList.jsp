<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>经验交流、建议意见管理</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>

    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <%--<link rel="stylesheet" href="${ctx}/dist/css/flat-ui.min.css">--%>
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav.css">
    <link rel="stylesheet" href="${ctx}/dist/css/communicate/showList.css">


</head>
<body>

<jsp:include page="../header.jsp"/>


<div class="container-fluid">
    <div class="row">

        <jsp:include page="communicateLeft.jsp"/>

        <div class="col-md-10 right-panel" id="container">
            <span style="margin: 10px 0;">当前位置 ： 交流园地管理 > 经验交流、建议意见管理</span>
            <div class="row" style="margin-top: 10px">
                <div class="col-md-2">
                    <label>检索字段:</label>
                    <select class="form-control" name="" id="select-column" style="width: 150px">
                        <option value="title">标题</option>
                        <option value="type">类型</option>
                        <option value="companyName">客户名称</option>
                        <option value="askName">提起人</option>
                        <option value="askTime">提起时间</option>
                        <option value="replyName">回复人</option>
                        <option value="replyTime">回复时间</option>
                        <option value="status">状态</option>
                    </select>
                </div>

                <div class="col-md-3">
                    <label>关键字:</label>
                    <div class="input-group">
                        <input type="text" class="form-control" id="input-text">
                        <span id="span-search" class="input-group-addon btn btn-primary" onclick="tofindByKey()" >
                    <span class="glyphicon glyphicon-search"></span>
                </span>
                    </div>
                </div>
            </div>


            <div style="margin-top: 15px">
                <div class="btn btn-primary" onclick="showDetail()" disabled="disabled" id="showDetailBtn">回复/查看</div>
                <div class="table-responsive" style="margin-top: 15px;">
                    <table id="showList-table" class="table table-bordered" style="font-size: 14px;"></table>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
<script src="${ctx}/dist/js/jquery.min.js"></script>
<script src="${ctx}/dist/js/bootstrap-table.js"></script>
<script src="${ctx}/dist/js/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/dist/js/communicate/communicate-table.js"></script>
<script>
    $(function () {
        var lis = $('.left-nav .add-active');
        $(lis[0]).addClass('active');
    });
</script>
</html>
