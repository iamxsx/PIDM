<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 16-9-11
  Time: 下午9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav.css">

    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <script src="${ctx}/dist/js/bootstrap.min.js"></script>
    <title>已发布列表</title>
    <style>
        body{
            font-size: 14px;
        }
    </style>
</head>
<body>

<jsp:include page="../header.jsp"/>

<div class="container-fluid">

    <div class="row">

        <jsp:include page="../left.jsp"/>

        <div class="col-md-10 right-panel">

            <ol class="breadcrumb">
                <li><a href="#">信息发布</a></li>
                <li class="active">编辑未发布</li>
            </ol>

            <div class="row" style="margin-top: 40px;margin-bottom: 24px;">
                <div class="col-md-12">
                    <div class="btn-group pull-right" >
                        <button id="btn-to-preview-2" class="btn btn-default" type="button">
                            <span class="glyphicon glyphicon-eye-open"></span>　预览
                        </button>
                        <button id="btn-to-add-2" class="btn btn-default" type="button">
                            <span class="glyphicon glyphicon-plus"></span>　添加
                        </button>
                        <button id="btn-to-edit-2" class="btn btn-default" type="button">
                            <span class="glyphicon glyphicon-edit"></span>　修改
                        </button>
                        <button id="btn-delete-2" class="btn btn-default" type="button">
                            <span class="glyphicon glyphicon-remove"></span>　删除
                        </button>
                    </div>
                </div>
            </div>

            <div class="table-responsive">
                 <table id="article-not-published-table" class="table table-bordered"></table>
            </div>

        </div>

    </div>

</div>

</body>


<script src="${ctx}/dist/js/bootstrap-table.js"></script>
<script src="${ctx}/dist/js/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/dist/js/left_nav.js"></script>
<script src="${ctx}/dist/controller/article/article-table.js"></script>
<script>
    $('#btn-to-preview-2').click(function () {
        var selections = $('#article-not-published-table').bootstrapTable('getSelections');
        if (selections.length != 1){
            alert('请选择一个预览');
            return;
        }
        var id = selections[0].id;
        window.location.href = '/back/article/article-preview?aid='+id;
    });
    $('#btn-to-edit-2').click(function () {
        var selections = $('#article-not-published-table').bootstrapTable('getSelections');
        if (selections.length != 1){
            alert('请选择一个修改');
            return;
        }
        var id = selections[0].id;
        window.location.href = '/back/article/edit?aid='+id;
    });

    $('#btn-delete-2').click(function () {
        var selections = $('#article-not-published-table').bootstrapTable('getSelections');
        if (selections.length <= 0) {
            alert('请先选择要删除的数据！');
            return;
        }
        var ids = '';
        for (var i = 0; i < selections.length; i++) {
            ids += selections[i].id + ','
        }
        deleteArticle2(ids);
    });

    function deleteArticle2(ids) {
        $.ajax({
            url:'/back/article/deleteArticles?ids='+ids,
            success:function () {
                alert('删除成功');
                $('#article-not-published-table').bootstrapTable('refresh');
            },
            error:function () {
                alert('删除失败');
            }
        });
    }

    $(function () {
        var lis = $('.left-nav .add-active');
        $(lis[4]).addClass('active');
    });


</script>

</html>
