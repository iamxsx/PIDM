<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 9/17/16
  Time: 9:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">

    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>组织结构</title>
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap-table.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav.css">
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrapValidator.min.css">

    <link rel="stylesheet" href="${ctx}/dist/css/back/system/bootstrap-treeview.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/system/system-manage.css">
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
                    <li class="active">组织结构</li>
                </ol>

                <div class="col-md-4">
                    <div class="form-group">
                        <label for="input-select-node" class="sr-only">Search Tree:</label>
                        <input type="input" class="form-control" id="input-select-node" placeholder="输入搜索">
                    </div>
                    <div id="treeview-selectable" class=""></div>
                </div>

                <div class="col-md-8 main update">
                    <%--main--%>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">部门信息</h3>
                        </div>
                        <div class="btn-group pull-right" >
                            <shiro:hasPermission name="department:add">
                            <button id="btn-add" class="btn btn-default" type="button">
                                <span class="glyphicon glyphicon-plus"></span>&nbsp;添加
                            </button>
                            </shiro:hasPermission>

                            <shiro:hasPermission name="department:delete">
                            <button id="btn-delete" class="btn btn-default" type="button">
                                <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
                            </button>
                            </shiro:hasPermission>
                        </div>
                        <div class="panel-body dept-info">
                            <ul>
                                <form id="updateForm" class="form-horizontal bv-form">
                                    <li class="row form-group">
                                        <input type="hidden" id="id" name="id">
                                        <div class="col-md-4">
                                            <label for="code"><font color="red">*</font>编码：</label>
                                        </div>
                                        <div class="col-md-5">
                                            <input type="text" class="form-control" id="code" name="code" placeholder="">
                                        </div>
                                    </li>
                                    <li class="row form-group">
                                        <div class="col-md-4">
                                            <label for="name"><font color="red">*</font>部门名称：</label>
                                        </div>
                                        <div class="col-md-5" id="add-note">
                                            <input type="text" class="form-control" id="name" name="name" placeholder="">
                                        </div>
                                    </li>
                                    <li class="row form-group">
                                        <div class="col-md-4">
                                            <label for="parent"><font color="red">*</font>所在部门：</label>
                                        </div>
                                        <div class="col-md-5">
                                            <input type="hidden" id="parentId">
                                            <input type="text" class="form-control" id="parent" name="parent" readonly>
                                        </div>
                                    </li>
                                    <li class="row">
                                        <div class="col-md-4">
                                            <label for="description">部门描述：</label>
                                        </div>
                                        <div class="col-md-5">
                                            <textarea style="resize: none;" class="form-control" rows="3" name="description" id="description"></textarea>
                                        </div>
                                    </li>
                                </form>
                                <li class="row form-group">
                                    <div class="col-md-5 col-md-offset-4">
                                        <shiro:hasPermission name="department:update">
                                        <button type="button" class="btn btn-info" id="update-department">修改</button>
                                        </shiro:hasPermission>
                                    </div>
                                </li>
                                <li>
                                    <div id="update-success" class="alert alert-success alert-dismissible fade in" role="alert" style="display: none;text-align: center;">
                                        <strong>修改成功!</strong>
                                    </div>
                                </li>
                                <li>
                                    <div id="update-error" class="alert alert-danger alert-dismissible fade in" role="alert" style="display: none;text-align: center;">
                                        <strong>修改部门出错</strong> 请重试或检查网络！
                                    </div>
                                </li>
                                <li>
                                    <div id="conflict-error2" class="alert alert-danger alert-dismissible fade in" role="alert" style="display: none;text-align: center;">
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="col-md-8 main save" style="display: none;">
                    <%--main--%>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">添加部门</h3>
                        </div>
                        <div class="panel-body dept-info">
                            <ul>
                                <form id="saveForm" class="form-horizontal bv-form">
                                    <input type="hidden" id="id2">
                                    <li class="row form-group">
                                        <div class="col-md-4">
                                            <label for="code2"><font color="red">*</font>编码：</label>
                                        </div>
                                        <div class="col-md-5">
                                            <input type="text" class="form-control" id="code2" name="code" placeholder="">
                                        </div>
                                    </li>
                                    <li class="row form-group">
                                        <div class="col-md-4">
                                            <label for="name2"><font color="red">*</font>部门名称：</label>
                                        </div>
                                        <div class="col-md-5">
                                            <input type="text" class="form-control" id="name2" name="name" placeholder="">
                                        </div>
                                    </li>
                                    <li class="row form-group">
                                        <div class="col-md-4">
                                            <label for="parent2"><font color="red">*</font>所在部门：</label>
                                        </div>
                                        <div class="col-md-5">
                                            <input type="hidden" id="parentId2">
                                            <input type="text" class="form-control" id="parent2" name="parent" readonly>
                                        </div>
                                    </li>
                                    <li class="row">
                                        <div class="col-md-4">
                                            <label for="description2">部门描述：</label>
                                        </div>
                                        <div class="col-md-5">
                                            <textarea style="resize: none;" class="form-control" rows="3" name="description" id="description2"></textarea>
                                        </div>
                                    </li>
                                </form>
                                <li class="row form-group">
                                    <div class="col-md-5 col-md-offset-4">
                                        <button type="button" class="btn btn-info" id="save-department">保存</button>
                                    </div>
                                </li>
                                <li>
                                    <div id="save-success" class="alert alert-success alert-dismissible fade in" role="alert" style="display: none;text-align: center;">
                                        <strong>添加成功!</strong>
                                    </div>
                                </li>
                                <li>
                                    <div id="save-error" class="alert alert-danger alert-dismissible fade in" role="alert" style="display: none;text-align: center;">
                                        <strong>添加部门出错</strong> 请重试或检查网络！
                                    </div>
                                </li>
                                <li>
                                    <div id="conflict-error" class="alert alert-danger alert-dismissible fade in" role="alert" style="display: none;text-align: center;">
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%--删除确认模态框--%>
    <div class="modal fade" id="delete-sure-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="">提示</h4>
                </div>
                <div class="modal-body">
                    <p id="delete-msg">确认删除所选部门？</p>
                    <div id="delete-success" class="alert alert-success alert-dismissible fade in" role="alert" style="display: none;">
                        删除成功!
                    </div>

                    <div id="delete-error" class="alert alert-danger alert-dismissible fade in" role="alert" style="display: none;">
                        删除失败！
                    </div>
                    <div id="reference-error" class="alert alert-danger alert-dismissible fade in" role="alert" style="display: none;">

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="btn-delete-sure">确定</button>
                </div>
            </div>
        </div>
    </div>
</body>

<script src="${ctx}/dist/js/jquery.min.js"></script>
<script src="${ctx}/dist/js/bootstrap.min.js"></script>
<script src="${ctx}/dist/js/bootstrap-table.js"></script>
<script src="${ctx}/dist/js/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/dist/controller/system/bootstrap-treeview.min.js"></script>
<script src="${ctx}/dist/controller/system/department-treeview.js"></script>
<script src="${ctx}/dist/js/left_nav.js"></script>
<script src="${ctx}/dist/js/bootstrapValidator.min.js"></script>
<script>
    $(function () {
        var lis = $('.left-nav .add-active');
        $(lis[0]).addClass('active');
    });
</script>
</html>
