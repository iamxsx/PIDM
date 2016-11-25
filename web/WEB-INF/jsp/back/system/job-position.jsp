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
    <title>岗位管理</title>
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <%--<link rel="stylesheet" href="${ctx}/dist/css/bootstrap-table.css">--%>
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

            <div class="col-md-10 right-panel">
                <ol class="breadcrumb">
                    <span>当前位置：</span>
                    <li><a href="javascript:void(0);">系统管理</a></li>
                    <li class="active">岗位管理</li>
                </ol>

                <div class="col-md-4">
                    <div class="form-group">
                        <label for="input-select-node" class="sr-only">Search Tree:</label>
                        <input type="input" class="form-control" id="input-select-node" placeholder="输入搜索">
                    </div>
                    <div id="treeview-selectable" class=""></div>

                </div>

                <div class="col-md-8 main">
                    <div class="operation">
                        <form class="navbar-form">
                            <div class="form-group">
                                <input id="jpname-search" placeholder="输入岗位名称" class="form-control" type="text">
                            </div>
                            <a id="btn-search2" class="btn btn-primary">
                                <span class="glyphicon glyphicon-search"></span>&nbsp;查询
                            </a>
                        </form>
                    </div>

                    <div class="row" style="margin-top: 20px;margin-bottom: 10px;">
                        <div class="col-md-12">
                            <div class="btn-group" >
                                <button id="btn-refresh" class="btn btn-default" type="button">
                                    <span class="glyphicon glyphicon-refresh"></span>&nbsp;刷新
                                </button>
                                <shiro:hasPermission name="jobposition:add">
                                <button id="btn-add" class="btn btn-default" type="button" data-toggle="modal" data-target="#add-jp-modal">
                                    <span class="glyphicon glyphicon-plus"></span>&nbsp;添加
                                </button>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="jobposition:update">
                                <button id="btn-update" class="btn btn-default" type="button" data-toggle="modal" data-target="#update-jp-modal">
                                    <span class="glyphicon glyphicon-edit"></span>&nbsp;修改
                                </button>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="jobposition:delete">
                                <button id="btn-delete" class="btn btn-default" type="button">
                                    <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
                                </button>
                                </shiro:hasPermission>
                            </div>
                        </div>
                    </div>
                    <table id="jobposition-table" class="table table-bordered"></table>
                    <br><br>
                </div>
            </div>
        </div>
    </div>

    <%--添加岗位模态框--%>
    <div class="modal fade" id="add-jp-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="ModalLabel">添加岗位</h4>
                </div>
                <div class="modal-body">
                    <div class="tab-content">
                        <div class="panel-heading">岗位信息：
                            <div class="clearfix"></div>
                        </div>

                        <form id="saveJPForm" class="form-horizontal bv-form">
                            <div class="row space form-group">
                                <label class="col-md-4 right control-label" for="jpName"><font color="red">*</font>岗位名称：</label>
                                <div class="col-md-6">
                                    <input type="text" class="form-control" name="name" id="jpName" placeholder="">
                                </div>
                            </div>
                            <div class="row space form-group">
                                <label class="col-md-4 right control-label" for="departmentName"><font color="red">*</font>所在部门：</label>
                                <div class="col-md-6">
                                    <input type="hidden" name="departmentId" id="departmentId">
                                    <input style="cursor: pointer" type="text" class="form-control" name="departmentName" id="departmentName" placeholder="点击选择部门"
                                           readonly data-toggle="modal" data-target="#select-dept-modal">
                                </div>
                            </div>
                            <div class="row space form-group">
                                <label class="col-md-4 right control-label" for="description">岗位描述：</label>
                                <div class="col-md-6">
                                    <textarea rows="3" class="form-control" name="description" id="description" style="resize: none"></textarea>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer" style="text-align: center">
                    <div id="save-success" class="alert alert-success alert-dismissible fade in" role="alert" style="display: none;">
                        <button type="button" class="close" data-dismiss="alert">
                            <span aria-hidden="true">×</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <strong>添加成功!</strong>
                    </div>
                    <div id="save-error" class="alert alert-danger alert-dismissible fade in" role="alert" style="display: none;">
                        <button type="button" class="close" data-dismiss="alert">
                            <span aria-hidden="true">×</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <strong>添加岗位出错</strong> 请重试或检查网络！
                    </div>
                    <button type="button" class="btn btn-default" data-dismiss="modal" >取消</button>
                    <button type="button" class="btn btn-primary" id="btn-save-jp">保存</button>
                </div>
            </div>
        </div>
    </div>

    <%--修改岗位模态框--%>
    <div class="modal fade" id="update-jp-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="ModalLabel2">添加岗位</h4>
                </div>
                <div class="modal-body">
                    <div class="tab-content">
                        <div class="panel-heading">岗位信息：
                            <div class="clearfix"></div>
                        </div>

                        <form id="updateJPForm" class="form-horizontal bv-form">
                            <input type="hidden" name="id" id="jpId">
                            <div class="row space form-group">
                                <label class="col-md-4 right control-label" for="jpName2"><font color="red">*</font>岗位名称：</label>
                                <div class="col-md-6">
                                    <input type="text" class="form-control" name="name" id="jpName2" placeholder="">
                                </div>
                            </div>
                            <div class="row space form-group">
                                <label class="col-md-4 right control-label" for="departmentName2"><font color="red">*</font>所在部门：</label>
                                <div class="col-md-6">
                                    <input type="hidden" name="departmentId" id="departmentId2">
                                    <input style="cursor: pointer" type="text" class="form-control" name="departmentName" id="departmentName2" placeholder="点击选择部门"
                                           readonly data-toggle="modal" data-target="#select-dept-modal">
                                </div>
                            </div>
                            <div class="row space form-group">
                                <label class="col-md-4 right control-label" for="description2">岗位描述：</label>
                                <div class="col-md-6">
                                    <textarea rows="3" class="form-control" name="description" id="description2" style="resize: none"></textarea>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer" style="text-align: center">
                    <div id="update-success" class="alert alert-success alert-dismissible fade in" role="alert" style="display: none;">
                        <button type="button" class="close" data-dismiss="alert">
                            <span aria-hidden="true">×</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <strong>添加成功!</strong>
                    </div>
                    <div id="update-error" class="alert alert-danger alert-dismissible fade in" role="alert" style="display: none;">
                        <button type="button" class="close" data-dismiss="alert">
                            <span aria-hidden="true">×</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <strong>修改岗位出错</strong> 请重试或检查网络！
                    </div>
                    <button type="button" class="btn btn-default" data-dismiss="modal" >取消</button>
                    <button type="button" class="btn btn-primary" id="btn-update-jp">保存</button>
                </div>
            </div>
        </div>
    </div>

    <%--二级模态框，选择岗位--%>
    <div class="modal fade" id="select-dept-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="myModalLabel">选择部门</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="input-select-node2" class="sr-only">Search Tree:</label>
                        <input type="text" class="form-control" id="input-select-node2" placeholder="输入搜索">
                    </div>
                    <div id="treeview-selectable2" class=""></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="select-dept-confirm">选择</button>
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
                    <p id="delete-msg">确认删除所选岗位？</p>
                    <div id="delete-success" class="alert alert-success alert-dismissible fade in" role="alert" style="display: none;">
                        <strong>删除成功!</strong>
                    </div>

                    <div id="delete-error" class="alert alert-danger alert-dismissible fade in" role="alert" style="display: none;">
                        <strong>删除失败！</strong>
                    </div>
                    <div id="delete-error2" class="alert alert-danger alert-dismissible fade in" role="alert" style="display: none;">
                        <strong>岗位下有员工，删除失败！</strong>
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
<script src="${ctx}/dist/controller/system/jp-treeview.js"></script>
<script src="${ctx}/dist/controller/system/jobposition-table.js"></script>
<script src="${ctx}/dist/js/left_nav.js"></script>
<script src="${ctx}/dist/js/bootstrapValidator.min.js"></script>
<script>
    $(function () {
        var lis = $('.left-nav .add-active');
        $(lis[1]).addClass('active');
    });
</script>
</html>
