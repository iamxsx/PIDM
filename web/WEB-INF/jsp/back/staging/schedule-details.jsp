<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 16-9-14
  Time: 下午9:52
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
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap-table.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <script src="${ctx}/dist/js/bootstrap.min.js"></script>
    <title>待办事项</title>
    <style>
        body{
            font-size: 1.4em;
        }
    </style>
</head>
<body>
    <jsp:include page="../header.jsp"/>
    <div class="container" style="font-family: 'Microsoft YaHei';color: black">
        <ol class="breadcrumb">
            <li><a href="#">我的工作台</a></li>
            <li><a href="#">待办事项</a></li>
            <li class="active">审批</li>
        </ol>
        <div class="col-md-2">
            <button type="button" style="margin-bottom: 18px" class="btn blockquote-reverse btn-lg" data-toggle="modal" data-target="#myModal">
                <span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;填写审核意见
            </button>
            <button type="button" id="comment-button" style="margin-bottom: 18px" class="btn blockquote-reverse btn-info btn-lg" data-toggle="modal" data-target="#myModalTwo">
                <span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;查看流程轨迹
            </button>
            <button type="button" style="margin-bottom: 18px" class="btn blockquote-reverse btn-danger btn-lg" data-toggle="modal" data-target="#myModalThr">
                <span class="glyphicon glyphicon-paperclip"></span>&nbsp;&nbsp;暂不进行审核
            </button>
            <!-- Modal -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title" id="myModalLabel"><span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;审核意见</h4>
                        </div>
                        <div class="modal-body">
                            <textarea id="comment" class="form-control" rows="5" style="resize:none"></textarea>
                            <label class="radio-inline">
                                <input type="radio" name="message" id="inlineRadio1" value="通过">同意
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="message" id="inlineRadio2" value="驳回">驳回
                            </label>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default btn-danger" data-dismiss="modal"><span class=" glyphicon glyphicon-remove-circle"></span>&nbsp;&nbsp;取消</button>
                            <button type="button" id="agree-button" class="btn btn-primary btn-success"><span class="glyphicon glyphicon-ok-circle"></span>&nbsp;&nbsp;确定</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Modal -->
            <div class="modal fade" id="myModalTwo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title" id="myModalLabelTwo"><span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;审批流程轨迹</h4>
                        </div>
                        <div class="modal-body">
                            <div class="table-responsive">
                                <table class="table"  id="comment-table">
                                    <thead>
                                    <tr>
                                        <th>
                                            编号
                                        </th>
                                        <th>
                                            产品
                                        </th>
                                        <th>
                                            交付时间
                                        </th>
                                        <th>
                                            状态
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>
                                            1
                                        </td>
                                        <td>
                                            TB - Monthly
                                        </td>
                                        <td>
                                            01/04/2012
                                        </td>
                                        <td>
                                            Default
                                        </td>
                                    </tr>
                                    <tr class="success">
                                        <td>
                                            1
                                        </td>
                                        <td>
                                            TB - Monthly
                                        </td>
                                        <td>
                                            01/04/2012
                                        </td>
                                        <td>
                                            Approved
                                        </td>
                                    </tr>
                                    <tr class="error">
                                        <td>
                                            2
                                        </td>
                                        <td>
                                            TB - Monthly
                                        </td>
                                        <td>
                                            02/04/2012
                                        </td>
                                        <td>
                                            Declined
                                        </td>
                                    </tr>
                                    <tr class="warning">
                                        <td>
                                            3
                                        </td>
                                        <td>
                                            TB - Monthly
                                        </td>
                                        <td>
                                            03/04/2012
                                        </td>
                                        <td>
                                            Pending
                                        </td>
                                    </tr>
                                    <tr class="info">
                                        <td>
                                            4
                                        </td>
                                        <td>
                                            TB - Monthly
                                        </td>
                                        <td>
                                            04/04/2012
                                        </td>
                                        <td>
                                            Call in to confirm
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-chevron-left"></span>&nbsp;&nbsp;返回</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="myModalThr" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title" id="myModalLabelThr"><span class="glyphicon glyphicon-paperclip"></span>&nbsp;&nbsp;暂时不进行审核</h4>
                        </div>
                        <div class="modal-body">
                            暂时不对此消息进行审核！返回我的工作台
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;取消</button>
                            <button type="button" id ="reject-button" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;确定</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">信息详细展示</div>
                <div class="table-responsive">
                    <table class="table">
                        <tbody>
                        <tr>
                            <td>
                                1
                            </td>
                            <td>
                                TB - Monthly
                            </td>
                            <td>
                                01/04/2012
                            </td>
                            <td>
                                Default
                            </td>
                        </tr>
                        <tr class="success">
                            <td>
                                1
                            </td>
                            <td>
                                TB - Monthly
                            </td>
                            <td>
                                01/04/2012
                            </td>
                            <td>
                                Approved
                            </td>
                        </tr>
                        <tr class="error">
                            <td>
                                2
                            </td>
                            <td>
                                TB - Monthly
                            </td>
                            <td>
                                02/04/2012
                            </td>
                            <td>
                                Declined
                            </td>
                        </tr>
                        <tr class="warning">
                            <td>
                                3
                            </td>
                            <td>
                                TB - Monthly
                            </td>
                            <td>
                                03/04/2012
                            </td>
                            <td>
                                Pending
                            </td>
                        </tr>
                        <tr class="info">
                            <td>
                                4
                            </td>
                            <td>
                                TB - Monthly
                            </td>
                            <td>
                                04/04/2012
                            </td>
                            <td>
                                Call in to confirm
                            </td>
                        </tr>
                        <tr>
                            <td>
                                1
                            </td>
                            <td>
                                TB - Monthly
                            </td>
                            <td>
                                01/04/2012
                            </td>
                            <td>
                                Default
                            </td>
                        </tr>
                        <tr class="success">
                            <td>
                                1
                            </td>
                            <td>
                                TB - Monthly
                            </td>
                            <td>
                                01/04/2012
                            </td>
                            <td>
                                Approved
                            </td>
                        </tr>
                        <tr class="error">
                            <td>
                                2
                            </td>
                            <td>
                                TB - Monthly
                            </td>
                            <td>
                                02/04/2012
                            </td>
                            <td>
                                Declined
                            </td>
                        </tr>
                        <tr class="warning">
                            <td>
                                3
                            </td>
                            <td>
                                TB - Monthly
                            </td>
                            <td>
                                03/04/2012
                            </td>
                            <td>
                                Pending
                            </td>
                        </tr>
                        <tr class="info">
                            <td>
                                4
                            </td>
                            <td>
                                TB - Monthly
                            </td>
                            <td>
                                04/04/2012
                            </td>
                            <td>
                                Call in to confirm
                            </td>
                        </tr>
                        <tr>
                            <td>
                                1
                            </td>
                            <td>
                                TB - Monthly
                            </td>
                            <td>
                                01/04/2012
                            </td>
                            <td>
                                Default
                            </td>
                        </tr>
                        <tr class="success">
                            <td>
                                1
                            </td>
                            <td>
                                TB - Monthly
                            </td>
                            <td>
                                01/04/2012
                            </td>
                            <td>
                                Approved
                            </td>
                        </tr>
                        <tr class="error">
                            <td>
                                2
                            </td>
                            <td>
                                TB - Monthly
                            </td>
                            <td>
                                02/04/2012
                            </td>
                            <td>
                                Declined
                            </td>
                        </tr>
                        <tr class="warning">
                            <td>
                                3
                            </td>
                            <td>
                                TB - Monthly
                            </td>
                            <td>
                                03/04/2012
                            </td>
                            <td>
                                Pending
                            </td>
                        </tr>
                        <tr class="info">
                            <td>
                                4
                            </td>
                            <td>
                                TB - Monthly
                            </td>
                            <td>
                                04/04/2012
                            </td>
                            <td>
                                Call in to confirm
                            </td>
                        </tr>
                        </tbody>
                    </table>
            </div>
        </div>
    </div>
    </div>
</body>
<script src="${ctx}/dist/controller/staging/date.js"></script>
<script src="${ctx}/dist/controller/staging/schedule-details.js"></script>
</html>

