<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 16-9-27
  Time: 上午8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>下载中心管理</title>
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/fileinput.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav.css">
    <script src="${ctx}/dist/js/jquery.min.js"></script>
</head>
<body>

<jsp:include page="../header.jsp"/>

<div class="container-fluid">

    <div class="row">
        <jsp:include page="left.jsp"/>
        <div class="col-md-9 col-md-offset-0">
            <form id="form-upload" action="${ctx}/back/download-center/fileUpload" method="post" enctype="multipart/form-data">
                <br>
                <br>
                <input id="file" class="file" type="file" name="file">

                <br>

                <textarea style="resize: none;" name="description" type="text" class="form-control" placeholder="请输入文件描述"></textarea>

                <br>


                <div class="col-sm-2 pull-right">
                    <button type="submit" class="btn btn-primary form-control">上传</button>
                </div>
            </form>
        </div>
    </div>

    <div class="row" style="margin-top: 48px">
        <div class="col-md-9 col-md-offset-2">
            <div class="table-responsive">
                <table id="file-table" class="table table-bordered"  style="background-color: #ffffff"></table>
            </div>
        </div>
    </div>

    <div class="row" style="height: 100px;"></div>


</div>


</body>
<script src="${ctx}/dist/js/bootstrap-table.js"></script>
<script src="${ctx}/dist/js/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/dist/js/fileinput.min.js"></script>
<script src="${ctx}/dist/js/bootstrapValidator.min.js"></script>
<script src="${ctx}/dist/controller/download-center/file-table.js"></script>

<script>

    $('#file').fileinput({
        showUpload: false
    });
    $(function () {
        var lis = $('.left-nav .add-active');
        $(lis[0]).addClass('active');
    });

</script>
</html>
