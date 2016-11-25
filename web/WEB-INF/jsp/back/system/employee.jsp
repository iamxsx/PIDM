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
    <title>用户管理</title>
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <%--<link rel="stylesheet" href="${ctx}/dist/css/bootstrap-table.css">--%>
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav.css">
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap-datetimepicker.min.css">
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
                <li class="active">用户管理</li>
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
                        <select class="form-control select" id="job-position-search">
                            <option value="">在职状态</option>
                            <option value="1">在职</option>
                            <option value="2">离职</option>
                            <option value="3">停职</option>
                            <option value="4">退休</option>
                        </select>
                        <div class="form-group">
                            <input id="real-name-search" placeholder="输入姓名" class="form-control" type="text">
                        </div>
                        <div class="form-group">
                            <input id="user-name-search" placeholder="输入工号" class="form-control" type="text">
                        </div>
                        <a id="btn-search" class="btn btn-primary">
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
                            <shiro:hasPermission name="employee:add">
                            <button id="btn-add" class="btn btn-default" type="button"  data-toggle="modal" data-target="#add-employee-modal">
                                <span class="glyphicon glyphicon-plus"></span>&nbsp;添加
                            </button>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="employee:update">
                            <button id="btn-update" class="btn btn-default" type="button"  data-toggle="modal" data-target="#update-employee-modal">
                                <span class="glyphicon glyphicon-edit"></span>&nbsp;修改
                            </button>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="employee:delete">
                            <button id="btn-delete" class="btn btn-default" type="button">
                                <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
                            </button>
                            </shiro:hasPermission>
                        </div>
                    </div>
                </div>
                <table id="employee-table" class="table table-bordered"></table>
                <br><br><br>
            </div>
        </div>

    </div>
</div>

<%--添加员工模态框--%>
<div class="modal fade" id="add-employee-modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myLargeModalLabel">添加用户</h4>
            </div>
            <div class="modal-body">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active">
                        <a href="#home" aria-controls="home" role="tab" data-toggle="tab">基本资料</a>
                    </li>
                    <li role="presentation">
                        <a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">用户角色</a>
                    </li>
                </ul>

                <!-- Tab panes -->
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="home">
                        <div class="panel-heading">基本信息：
                            <div class="clearfix"></div>
                        </div>

                        <form id="registerForm" class="form-horizontal bv-form">
                            <div class="row space form-group">
                                <label class="col-md-2 right control-label" for="realName"><font color="red">*</font>姓名：</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" name="realName" id="realName" placeholder="">
                                </div>

                                <label class="col-md-2 right control-label" for="jobStatus"><font color="red">*</font>在职状态：</label>
                                <div class="col-md-3">
                                    <select class="form-control" id="jobStatus" name="jobStatus">
                                        <option value="">请选择</option>
                                        <option value="1">在职</option>
                                        <option value="2">离职</option>
                                        <option value="3">停职</option>
                                        <option value="4">退休</option>
                                    </select>
                                </div>
                            </div>

                            <div class="row form-group">
                                <label class="col-md-2 right control-label" for="departmentName"><font color="red">*</font>所在部门：</label>
                                <div class="col-md-3">
                                    <input type="hidden" name="departmentId" id="departmentId">
                                    <input style="cursor: pointer" type="text" class="form-control" name="departmentName" id="departmentName" placeholder="点击选择部门"
                                           readonly data-toggle="modal" data-target="#select-dept-modal">
                                </div>

                                <label class="col-md-2 right control-label" for="jobPosition"><font color="red">*</font>所在岗位：</label>
                                <div class="col-md-3">
                                    <select class="form-control" name="jobPosition" id="jobPosition"></select>
                                </div>
                            </div>

                            <div class="panel-heading">登录信息：
                                <div class="clearfix"></div>
                            </div>

                            <div class="row form-group">
                                <label class="col-md-2 right control-label" for="userName"><font color="red">*</font>工号/账号：</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" name="userName" id="userName" placeholder="">
                                </div>

                                <label class="col-md-2 right control-label" for="password"><font color="red">*</font>登录密码：</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control"  name="password" id="password" placeholder="">
                                </div>
                            </div>

                            <div class="panel-heading">其他信息：
                                <div class="clearfix"></div>
                            </div>

                            <div class="row form-group">
                                <label class="col-md-2 right control-label" for="male"><font color="red">*</font>性别：</label>
                                <div class="col-md-3">
                                    <div class="radio">
                                        <label class="radio-inline">
                                            <input type="radio" id="male" name="gender" value="1">
                                            男
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" id="female" name="gender" value="0">
                                            女
                                        </label>
                                    </div>
                                </div>

                                <label class="col-md-2 right control-label" for="birthday">出生日期：</label>
                                <div class="col-md-3">
                                    <input type="text" placeholder="1990-01-01" readonly class="form-control form_datetime" name="birthday" id="birthday" style="cursor: pointer">
                                </div>
                            </div>

                            <div class="row space form-group">
                                <label class="col-md-2 right control-label" for="cellPhoneNum"><font color="red">*</font>手机：</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" name="cellPhoneNum" id="cellPhoneNum" placeholder="">
                                </div>

                                <label class="col-md-2 right control-label" for="phoneMsg"><font color="red">*</font>验证码：</label>
                                <div class="col-md-2">
                                    <input type="text" class="form-control"  name="phoneMsg" id="phoneMsg" placeholder="">
                                </div>
                                <div class="col-md-3">
                                    <button class="btn btn-primary" id="getPhoneMsgBtn">获取验证码</button>
                                </div>
                            </div>

                            <div class="row space form-group">
                                <label class="col-md-2 right control-label" for="officePhoneNum">办公电话：</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control"  name="officePhoneNum" id="officePhoneNum" placeholder="">
                                </div>

                                <label class="col-md-2 right control-label" for="email"><font color="red">*</font>电子邮箱：</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" name="email" id="email" placeholder="">
                                </div>
                            </div>
                            <div class="row space form-group">
                                <label class="col-md-2 right control-label" for="qq">QQ：</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" name="qq" id="qq" placeholder="">
                                </div>
                                <label class="col-md-2 right control-label"></label>
                                <div class="col-md-3">
                                </div>
                            </div>
                            <div class="row space form-group">
                                <label class="col-md-2 right control-label" for="address">住址：</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="address" id="address" placeholder="">
                                </div>
                            </div>
                        </form>

                    </div>
                    <div role="tabpanel" class="tab-pane" id="profile">
                        <div class="panel-heading">角色信息：
                            <div class="clearfix"></div>
                        </div>
                        <table id="role-table" class="table table-bordered"></table>
                    </div>
                </div>

                <div class="modal-footer" style="text-align: center;">
                    <div id="save-success" class="alert alert-success alert-dismissible fade in" role="alert" style="display: none;">
                        <button type="button" class="close" data-dismiss="alert">
                            <span aria-hidden="true">×</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <strong>添加成功!</strong>
                    </div>
                    <div id="role-warning" class="alert alert-warning alert-dismissible fade in" role="alert" style="display: none;">
                        <button type="button" class="close" data-dismiss="alert">
                            <span aria-hidden="true">×</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <strong>请选择角色!</strong>
                    </div>
                    <div id="save-error" class="alert alert-danger alert-dismissible fade in" role="alert" style="display: none;">
                        <button type="button" class="close" data-dismiss="alert">
                            <span aria-hidden="true">×</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <strong>添加用户出错</strong> 请重试或检查网络！
                    </div>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="saveEmployeeBtn">保存</button>
                </div>

            </div>
        </div>
    </div>
</div>


<%--修改员工模态框--%>
<div class="modal fade" id="update-employee-modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myLargeModalLabel2">修改用户</h4>
            </div>
            <div class="modal-body">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active">
                        <a href="#home2" aria-controls="home2" role="tab" data-toggle="tab">基本资料</a>
                    </li>
                    <li role="presentation">
                        <a href="#profile2" aria-controls="profile2" role="tab" data-toggle="tab">用户角色</a>
                    </li>
                </ul>

                <!-- Tab panes -->
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="home2">
                        <div class="panel-heading">基本信息：
                            <div class="clearfix"></div>
                        </div>

                        <form id="updateForm" class="form-horizontal bv-form">
                            <input type="hidden" name="id" id="employeeId">
                            <div class="row space form-group">
                                <label class="col-md-2 right control-label" for="realName2"><font color="red">*</font>姓名：</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" name="realName" id="realName2" placeholder="">
                                </div>

                                <label class="col-md-2 right control-label" for="jobStatus2"><font color="red">*</font>在职状态：</label>
                                <div class="col-md-3">
                                    <select class="form-control" id="jobStatus2" name="jobStatus">
                                        <option value="">请选择</option>
                                        <option value="1">在职</option>
                                        <option value="2">离职</option>
                                        <option value="3">停职</option>
                                        <option value="4">退休</option>
                                    </select>
                                </div>
                            </div>

                            <div class="row form-group">
                                <label class="col-md-2 right control-label" for="departmentName2"><font color="red">*</font>所在部门：</label>
                                <div class="col-md-3">
                                    <input type="hidden" name="departmentId" id="departmentId2">
                                    <input style="cursor: pointer" type="text" class="form-control" name="departmentName" id="departmentName2" placeholder="点击选择部门"
                                           readonly data-toggle="modal" data-target="#select-dept-modal">
                                </div>

                                <label class="col-md-2 right control-label" for="jobPosition2"><font color="red">*</font>所在岗位：</label>
                                <div class="col-md-3">
                                    <select class="form-control" name="jobPosition" id="jobPosition2"></select>
                                </div>
                            </div>

                            <div class="panel-heading">登录信息：
                                <div class="clearfix"></div>
                            </div>

                            <div class="row form-group">
                                <label class="col-md-2 right control-label" for="userName2"><font color="red">*</font>工号/账号：</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" name="userName" id="userName2" placeholder="">
                                </div>

                                <label class="col-md-2 right control-label" for="password2"><font color="red">*</font>登录密码：</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control"  name="password" id="password2" placeholder="输入密码进行修改">
                                </div>
                            </div>

                            <div class="panel-heading">其他信息：
                                <div class="clearfix"></div>
                            </div>

                            <div class="row form-group">
                                <label class="col-md-2 right control-label" for="male2"><font color="red">*</font>性别：</label>
                                <div class="col-md-3">
                                    <div class="radio">
                                        <label class="radio-inline">
                                            <input type="radio" id="male2" name="gender2" value="1">
                                            男
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" id="female2" name="gender2" value="0">
                                            女
                                        </label>
                                    </div>
                                </div>

                                <label class="col-md-2 right control-label" for="birthday2">出生日期：</label>
                                <div class="col-md-3">
                                    <input type="text"
                                           placeholder="1990-01-01" readonly class="form-control form_datetime" name="birthday" id="birthday2" style="cursor: pointer">
                                </div>
                            </div>

                            <div class="row space form-group">
                                <label class="col-md-2 right control-label" for="cellPhoneNum2"><font color="red">*</font>手机：</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" name="cellPhoneNum" id="cellPhoneNum2" placeholder="">
                                </div>

                                <div id="updatePhoneMsg" style="display: none">
                                    <label class="col-md-2 right control-label" for="phoneMsg2">验证码：</label>
                                    <div class="col-md-2">
                                        <input type="text" class="form-control"  name="phoneMsg" id="phoneMsg2" placeholder="">
                                    </div>
                                    <div class="col-md-3">
                                        <button type="button" class="btn btn-primary" id="getPhoneMsgBtn2">获取验证码</button>
                                    </div>
                                </div>
                            </div>

                            <div class="row space form-group">
                                <label class="col-md-2 right control-label" for="officePhoneNum2">办公电话：</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control"  name="officePhoneNum" id="officePhoneNum2" placeholder="">
                                </div>

                                <label class="col-md-2 right control-label" for="email2"><font color="red">*</font>电子邮箱：</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" name="email" id="email2" placeholder="">
                                </div>
                            </div>
                            <div class="row space form-group">
                                <label class="col-md-2 right control-label" for="qq2">QQ：</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" name="qq" id="qq2" placeholder="">
                                </div>
                            </div>
                            <div class="row space form-group">
                                <label class="col-md-2 right control-label" for="address2">住址：</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="address" id="address2" placeholder="">
                                </div>
                            </div>
                        </form>

                    </div>
                    <div role="tabpanel" class="tab-pane" id="profile2">
                        <div class="panel-heading">角色信息：
                            <div class="clearfix"></div>
                        </div>
                        <div class="table-responsive">
                            <table id="role-table2" class="table table-bordered"></table>
                        </div>

                    </div>
                </div>

                <div class="modal-footer" style="text-align: center;">
                    <div id="update-success" class="alert alert-success alert-dismissible fade in" role="alert" style="display: none;">
                        <button type="button" class="close" data-dismiss="alert">
                            <span aria-hidden="true">×</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <strong>修改成功!</strong>
                    </div>
                    <div id="role-warning2" class="alert alert-warning alert-dismissible fade in" role="alert" style="display: none;">
                        <button type="button" class="close" data-dismiss="alert">
                            <span aria-hidden="true">×</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <strong>请选择角色!</strong>
                    </div>
                    <div id="update-error" class="alert alert-danger alert-dismissible fade in" role="alert" style="display: none;">
                        <button type="button" class="close" data-dismiss="alert">
                            <span aria-hidden="true">×</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <strong>修改用户出错</strong> 请重试或检查网络！
                    </div>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="updateEmployeeBtn">修改</button>
                </div>

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
                <p id="delete-msg">确认删除所选员工？</p>
                <div id="delete-success" class="alert alert-success alert-dismissible fade in" role="alert" style="display: none;">
                    <strong>删除成功!</strong>
                </div>

                <div id="delete-error" class="alert alert-danger alert-dismissible fade in" role="alert" style="display: none;">
                    <strong>删除失败！</strong>
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
<script src="${ctx}/dist/controller/system/employee-treeview.js"></script>
<script src="${ctx}/dist/controller/system/employee-table.js"></script>
<script src="${ctx}/dist/js/left_nav.js"></script>
<script src="${ctx}/dist/js/bootstrap-datetimepicker.min.js"></script>
<script src="${ctx}/dist/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${ctx}/dist/js/bootstrapValidator.min.js"></script>
<script>
    $(function () {
        var lis = $('.left-nav .add-active');
        $(lis[2]).addClass('active');
    });
</script>
</html>