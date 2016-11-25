<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 16-9-19
  Time: 上午9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
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
    <title>角色管理</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<div class="container-fluid">
    <div class="row">
        <jsp:include page="left.jsp"/>

        <div class="col-md-10 right-panel">
            <ol class="breadcrumb">
                <span>当前位置：</span>
                <li><a href="javascript:void(0);">系统管理</a></li>
                <li class="active">角色管理</li>
            </ol>

            <div class="row" style="margin-top: 0px;margin-bottom: 24px;">
                <div class="col-md-12">
                    <div class="btn-group">
                        <button id="btn-refresh" class="btn btn-default" type="button">
                            <span class="glyphicon glyphicon-refresh"></span>　刷新
                        </button>
                        <shiro:hasPermission name="role:add">
                        <button id="btn-to-add-role" class="btn btn-default" type="button" data-toggle="modal" data-target="#add-role-modal">
                            <span class="glyphicon glyphicon-plus"></span>　添加
                        </button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="role:update">
                        <button id="btn-to-update" class="btn btn-default" type="button">
                            <span class="glyphicon glyphicon-edit"></span>　修改
                        </button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="role:delete">
                        <button id="btn-to-delete" class="btn btn-default" type="button">
                            <span class="glyphicon glyphicon-remove"></span>　删除
                        </button>
                        </shiro:hasPermission>
                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">角色管理</div>
                <div class="table-responsive">
                    <table id="role-table" class="table table-bordered"></table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<div class="modal fade" id="add-role-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加角色</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">

                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">角色名</label>
                                <div class="col-sm-10">
                                    <input id="roleName" type="text" class="form-control"  placeholder="请输入角色名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">角色归属</label>
                                <div class="col-sm-10">
                                    <label class="radio-inline">
                                        <input type="radio" name="location" id="front" value="1"> 前台
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="location" id="back" value="2"> 后台管理系统
                                    </label>
                                </div>

                            </div>

                            <div id="treeview-role-menu" style="display: none;"></div>
                            <div id="treeview-back-role-menu" style="display: none;"></div>

                        </form>

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button id="btn-add-role" type="button" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="update-role-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">修改角色</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">

                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">角色名</label>
                                <div class="col-sm-10">
                                    <input id="old-role-name" type="text" class="form-control"  placeholder="请输入角色名">
                                </div>
                            </div>


                            <div id="treeview-update-role-menu"></div>

                        </form>

                    </div>
                </div>
            </div>
            <span style="color: #ff0000;">&nbsp;&nbsp;&nbsp;&nbsp;注意：更新完角色的菜单和权限信息后，相关的用户需要重新登陆才能生效！</span>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button id="btn-update-role" type="button" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="delete-sure" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="">提示</h4>
            </div>
            <div class="modal-body">
                <p>确认删除所选角色，该操作不可恢复，确定执行 ？</p>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="btn-delete-sure">确定</button>
            </div>
        </div>
    </div>
</div>

<script src="${ctx}/dist/js/jquery.min.js"></script>
<script src="${ctx}/dist/js/bootstrap.min.js"></script>
<script src="${ctx}/dist/js/bootstrap-table.js"></script>
<script src="${ctx}/dist/js/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/dist/controller/system/bootstrap-treeview.js"></script>
<script src="${ctx}/dist/controller/system/role.js"></script>
<script>
    $(function () {
        var lis = $('.left-nav .add-active');
        $(lis[4]).addClass('active');
    });
</script>
</html>