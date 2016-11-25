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
                <li class="active">已发布</li>
            </ol>

            <div class="row">

                <div class="col-md-4">
                    <label>检索字段:</label>
                    <select class="form-control" name="" id="select-column">
                        <option value="">--　　索引字段　　--</option>
                        <option value="header">标题</option>
                        <option value="location">发布位置</option>
                        <option value="author">发布人</option>
                        <option value="publishTime">发布时间</option>
                        <option value="status">发布状态</option>
                        <option value="approver">审批人</option>
                    </select>
                </div>

                <div class="col-md-3">
                    <label>关键字:</label>
                    <div class="input-group">
                        <input type="text" class="form-control" id="input-text">
                        <span id="span-search" class="input-group-addon  btn-primary" >
                            <span class="glyphicon glyphicon-search"></span>
                        </span>
                    </div>
                </div>

                <%--<div class="col-md-2">--%>
                    <%--<button class="btn btn-default" style="margin-top: 26px;" data-toggle="modal" data-target="#advanced-query">高级搜索</button>--%>
                <%--</div>--%>
            </div>

            <div class="row" style="margin-top: 40px;margin-bottom: 24px;">
                <div class="col-md-12">
                    <div class="btn-group pull-right" >
                        <button id="btn-to-preview" class="btn btn-default" type="button">
                            <span class="glyphicon glyphicon-eye-open"></span>　预览
                        </button>
                        <button id="btn-to-add" class="btn btn-default" type="button">
                            <span class="glyphicon glyphicon-plus"></span>　添加
                        </button>
                        <button id="btn-to-edit" class="btn btn-default disabled" type="button">
                            <span class="glyphicon glyphicon-edit"></span>　修改
                        </button>
                        <button id="btn-delete" class="btn btn-default" type="button">
                            <span class="glyphicon glyphicon-remove"></span>　删除
                        </button>
                    </div>
                </div>
            </div>


            <div class="table-responsive">
                 <table id="article-published-table" class="table table-bordered"></table>
            </div>

        </div>

    </div>

</div>

</body>
<%--高级查询模态框--%>
<div class="modal fade" id="advanced-query" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">高级查询</h4>
            </div>
            <div class="modal-body">

                <div class="row">
                    <div class="col-sm-4 col-sm-offset-1">
                        <label>标题 </label>
                        <input type="text" class="form-control" >
                    </div>
                    <div class="col-sm-4 col-sm-offset-1">
                        <label>内容 </label>
                        <input type="text" class="form-control" >
                    </div>
                    <div class="col-sm-4 col-sm-offset-1">
                        <label>发布位置 </label>
                        <input type="text" class="form-control" >
                    </div>
                    <div class="col-sm-4 col-sm-offset-1">
                        <label>发布时间 </label>
                        <input type="text" class="form-control" >
                    </div>
                    <div class="col-sm-4 col-sm-offset-1">
                        <label>状态 </label>
                        <div>
                        <label class="radio-inline">
                            <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"> 显示
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"> 隐藏
                        </label>
                        </div>
                    </div>
                    <div class="col-sm-4 col-sm-offset-1">
                        <label>起草人 </label>
                        <input type="text" class="form-control" >
                    </div>
                    <div class="col-sm-4 col-sm-offset-1">
                        <label>审批人 </label>
                        <input type="text" class="form-control" >
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary">查询</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="delete-sure" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="">提示</h4>
            </div>
            <div class="modal-body">
                <p>确认删除所选记录 ？</p>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="btn-delete-sure">确定</button>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/dist/js/bootstrap.min.js"></script>
<script src="${ctx}/dist/js/bootstrap-table.js"></script>
<script src="${ctx}/dist/js/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/dist/js/left_nav.js"></script>
<script src="${ctx}/dist/controller/article/article-table.js"></script>
<script>
    $(function () {
        var lis = $('.left-nav .add-active');
        $(lis[1]).addClass('active');
    });
</script>

</html>
