<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 10/16/16
  Time: 12:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>客户信息查看</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta charset="UTF-8">

    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/n_user/register.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">

    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">

    <link rel="stylesheet" href="${ctx}/dist/css/left_nav.css">

    <link rel="stylesheet" href="${ctx}/dist/css/client/clienInfo.css">


</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container-fluid" style="padding-left: 0">
    <jsp:include page="../regmanage/left.jsp"/>
    <div class="col-md-10 right-panel">
        <form id="register-form" method="post">
            <%--<div class="progress-box">
                <h2>Step1：填写账号信息</h2>
            </div>--%>



            <div id="user-box" class="">
                <table class="table table-bordered">
                    <caption class="table-title">账号基本信息</caption>
                    <tr>
                        <th>账号</th>
                        <td>${clientInfo[0].account}</td>
                        <th>账号昵称</th>
                        <td></td>
                    </tr>
                    <%--<tr>
                        <th><span class="font-x">*</span>密码</th>
                        <td><input type="password" name="user.password" id="user-pw" placeholder="请输入8~16位的密码"></td>
                        <th><span class="font-x">*</span>确认密码</th>
                        <td><input type="password" name="rePassword"></td>
                    </tr>--%>
                    <tr>
                        <th>客户姓名</th>
                        <td>${clientInfo[0].real_name}</td>
                        <th>客户身份证</th>
                        <td>${clientInfo[0].IDcard}</td>
                    </tr>
                    <tr>
                        <th>客户手机号码</th>
                        <td>${clientInfo[0].phone_num}</td>
                        <th>客户联系邮箱</th>
                        <td>${clientInfo[0].uemail}</td>
                    </tr>
                </table>
            </div>
            <%--<div class="progress-box">
                <h2>Step2：填写入会申请表信息</h2>
            </div>--%>
            <div id="">
                <div id="user-info" class="user page">
                </div>
                <table class="table table-bordered page" style="vertical-align: middle">
                    <caption class="table-title">广东省价格和产业发展协会帐号详细信息</caption>
                    <tbody>
                    <tr>
                        <th>单位名称</th>
                        <td colspan="3">${clientInfo[0].companyName}</td>
                        <th>单位性质</th>
                        <td>${clientInfo[0].mnature}</td>
                    </tr>
                    <tr>
                        <th>通讯地址</th>
                        <td colspan="3">${clientInfo[0].address}</td>
                        <th>邮政编码</th>
                        <td>${clientInfo[0].zip_code}</td>
                    </tr>
                    <tr>
                        <th rowspan="2">法人代表</th>
                        <th>姓名</th>
                        <th>单位职务</th>
                        <th>办公电话</th>
                        <th colspan="2">手机</th>
                    </tr>
                    <tr>
                        <c:forEach items="${clientInfo}" var="item" varStatus="status">
                            <c:choose>
                                <c:when test="${item.emnature == 2}">
                                    <td>${item.desName}</td>
                                    <td>${item.job_position}</td>
                                    <td>${item.office_phone_num}</td>
                                    <td colspan="2">${item.cell_phone_num}</td>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </tr>
                    <tr>
                        <th rowspan="4"><input name="company.join" style="position: absolute; top: -999em;">加入协会
                        </th>
                        <th colspan="2">协会</th>
                        <th colspan="3">会员单位级别</th>
                    </tr>
                    <tr>
                        <td colspan="2">${clientInfo[0].association_name}</td>
                        <td colspan="3" class="my-td-rborder">${clientInfo[0].association_unit}</td>
                    </tr>
                    <tr>
                        <th colspan="2">分会</th>
                        <th colspan="3">会员单位级别</th>
                    </tr>
                    <tr>
                        <td colspan="2">${clientInfo[0].chapter_name}</td>
                        <td colspan="3" class="my-td-rborder">${clientInfo[0].chapter_unit}</td>
                    </tr>

                    <c:forEach items="${clientInfo}" var="item" varStatus="status">
                    <c:choose>
                        <c:when test="${item.emnature == 1}">
                            <th rowspan="5">推荐人选及拟任分会职务
                            </th>
                            <tr>
                                <th>姓名</th>
                                <th>单位职务</th>
                                <th>协会职务</th>
                                <th colspan="2">分会职务</th>
                            </tr>
                            <tr>
                                <td rowspan="3">${item.desName}</td>
                                <td>${item.job_position}</td>
                                <td>${item.asct_job_position}</td>
                                <td colspan="2">${item.chapter_job_position}</td>
                            </tr>
                            <tr>
                                <th>电子邮箱</th>
                                <th>办公电话</th>
                                <th colspan="2">手机</th>
                            </tr>
                            <tr>
                                <td>${item.ememail}</td>
                                <td>${item.office_phone_num}</td>
                                <td colspan="2">${item.cell_phone_num}</td>
                            </tr>
                        </c:when>
                    </c:choose>
                </c:forEach>

                    <c:forEach items="${clientInfo}" var="item" varStatus="status">
                        <c:choose>
                            <c:when test="${item.emnature == 0}">
                                <tr>
                                    <th rowspan="4">单位指定联系人</th>
                                    <th>姓名</th>
                                    <th>单位职务</th>
                                    <th>办公电话</th>
                                    <th colspan="2">手机</th>
                                </tr>
                                <tr>
                                    <td rowspan="3">${item.desName}</td>
                                    <td>${item.job_position}</td>
                                    <td>${item.office_phone_num}</td>
                                    <td colspan="2">${item.cell_phone_num}</td>
                                </tr>
                                <tr>
                                    <th>电子邮箱</th>
                                    <th>传真号码</th>
                                    <th colspan="2">微信号/QQ号</th>
                                </tr>
                                <tr>
                                    <td>${item.ememail}</td>
                                    <td>${item.fax_num}</td>
                                    <td colspan="2">${item.online_num}</td>
                                </tr>
                                <tr>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                    <tr>
                        <th>单位简介<span class="tip">（300字以内）</span></th>
                        <td colspan="7" style="line-height: 2;text-indent: 2em" height="150px">${clientInfo[0].introduction}</td>
                    </tr>
                    <tr>
                        <th>希望得到协会或者分会服务和支持的主要内容</th>
                        <td colspan="7" style="line-height: 2;text-indent: 2em" height="150px">${clientInfo[0].demand}</td>
                    </tr>
                    <tr>
                        <th>省协会意见</th>
                        <td colspan="7" style="line-height: 2;text-indent: 2em" width="87%" height="150px">${clientInfo[0].audit_opinion}</td>
                    </tr>
                    </tbody>
                </table>
                <div><span class="font-x" style="font-size: 15px;">备注：各分会会员是广东省价格和产业发展协会的当然会员。</span></div>
                </div>
            <div class="buttonList">
                <button type="button" class="btn btn-primary" onclick="exportPDF(${clientInfo[0].id});">导出</button>
                <button id="change-button" type="button" class="btn btn-primary" onclick="change(${clientInfo[0].id});">修改</button>
                <a type="button" class="btn btn-primary" href="/back/user/clientList">返回</a>
            </div>
        </form>
    </div>
</div>

</body>
<script src="${ctx}/dist/js/jquery.min.js"></script>
<script src="${ctx}/dist/js/bootstrap.min.js"></script>
<script src="${ctx}/dist/js/bootstrap-table.js"></script>
<script>
    $(function () {
        var lis = $('.left-nav .add-active');
        $(lis[0]).addClass('active');
    });
    function change(uid) {
        window.location.href = "/back/user/changeclient?uid="+uid;
    };
    function exportPDF(uid) {
        window.location.href = "/back/user/exportPDF?uid="+uid;
    }
</script>
</html>
