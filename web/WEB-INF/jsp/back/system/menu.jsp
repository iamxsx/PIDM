<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 16-9-18
  Time: 下午2:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap-table.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/system/bootstrap-treeview.min.css">
    <title>菜单管理</title>
    <style>
        .menu-option{
            background-color: #FFFFFF;
            padding: 24px;
            box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.16), 0 2px 4px 0 rgba(0, 0, 0, 0.12);
        }
    </style>
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="container-fluid">
    <div class="row">
        <jsp:include page="left.jsp"/>


        <div class="col-md-9 right-panel">
            <ol class="breadcrumb">
                <span>当前位置：</span>
                <li><a href="javascript:void(0);">系统管理</a></li>
                <li class="active">菜单管理</li>
            </ol>

            <div class="col-md-4">

                <div id="tree"></div>
            </div>

            <div class="col-md-8">
                <form id="menu-form" class="form-horizontal" role="form">
                    <input type="hidden" id="menu-id">
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">父菜单</label>--%>
                        <%--<div class="col-sm-10">--%>
                            <%--<input type="text" class="form-control">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">菜单名字</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="menuName" id="menu-name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">菜单链接</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="menuUrl" id="menu-url">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">菜单图标</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="menu-icon">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">打开方式</label>
                        <div class="col-sm-10" id="radio-open-way">
                            <%--<label class="radio-inline">--%>
                                <%--<input type="radio" name="open-way"  value="option1" checked> 当前菜单--%>
                            <%--</label>--%>
                            <%--<label class="radio-inline">--%>
                                <%--<input type="radio" name="open-way"  value="option2"> 新建窗口--%>
                            <%--</label>--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">菜单状态</label>
                        <div class="col-sm-10" id="radio-isHidden">
                            <%--<label class="radio-inline">--%>
                                <%--<input type="radio" name="isHidden" value="option1"> 显示--%>
                            <%--</label>--%>
                            <%--<label class="radio-inline">--%>
                                <%--<input type="radio" name="isHidden" value="option2"> 隐藏--%>
                            <%--</label>--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">信息发布</label>
                        <div class="col-sm-10" id="radio-canPublish">
                            <%--<label class="radio-inline">--%>
                                <%--<input type="radio" name="open-way" value="0"> 允许--%>
                            <%--</label>--%>
                            <%--<label class="radio-inline">--%>
                                <%--<input type="radio" name="open-way" value="1">  不允许--%>
                            <%--</label>--%>
                        </div>
                    </div>

                </form>

                <div class="col-md-4 col-md-offset-2" style="margin-top: 24px;">
                    <shiro:hasPermission name="menu:update">
                    <button id="btn-update-menu" class="btn btn-primary form-control">确定</button>
                    </shiro:hasPermission>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="${ctx}/dist/js/jquery.min.js"></script>
<script src="${ctx}/dist/js/bootstrap.min.js"></script>
<script src="${ctx}/dist/controller/system/bootstrap-treeview.min.js"></script>
<script src="${ctx}/dist/js/bootstrapValidator.min.js"></script>
<script src="${ctx}/dist/controller/system/menu.js"></script>
<script>
    $(function () {
        var lis = $('.left-nav .add-active');
        $(lis[3]).addClass('active');
    });
</script>
</html>
