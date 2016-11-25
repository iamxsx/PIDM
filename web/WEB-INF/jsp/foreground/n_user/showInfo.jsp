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
    <title>信息管理</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta charset="UTF-8">

    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/login.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav_frontend.css">

    <link rel="stylesheet" href="${ctx}/dist/css/n_user/register.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">

    <link rel="stylesheet" href="${ctx}/dist/css/client/clienInfo.css">

</head>
<body>
<jsp:include page="/WEB-INF/jsp/foreground/header.jsp"/>

<div class="container">
    <div class="row" style="margin-top: 24px;">
        <div class="col-md-2">
            <ul class="left-nav">
                <li class="head">
                    <div class="left-nav-item">
                        <span>个人中心</span>
                    </div>
                </li>
                <li>
                    <div class="left-nav-item">
                        <a href="../work/to-personal">
                            消息管理
                        </a>
                    </div>
                </li>
                <li>
                    <div class="left-nav-item">
                        <a href="${ctx}/nUser/showNUserInfo">
                            信息管理
                        </a>
                    </div>
                </li>
            </ul>
        </div>

        <div class="col-md-10 right-panel" id="container">
            <form id="register-form" method="post">
                <div id="user-box" class="">
                    <table class="table table-bordered">
                        <caption class="table-title">账号基本信息</caption>
                        <tr>
                            <th>账号</th>
                            <td>${clientInfo[0].account}</td>
                            <th>密码</th>
                            <td>********</td>
                        </tr>
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
                            <th rowspan="5"><input name="company.join" style="position: absolute; top: -999em;">加入协会
                            </th>
                            <th colspan="2">协会</th>
                            <th colspan="3">会员单位级别</th>
                        </tr>
                        <tr>
                            <td rowspan="2" colspan="2">${clientInfo[0].association_name}</td>
                            <td colspan="3">${clientInfo[0].association_unit}</td>
                        </tr>
                        <tr>
                        </tr>
                        <tr>
                            <th colspan="2">分会</th>
                            <th colspan="3">会员单位级别</th>
                        </tr>
                        <tr>
                            <td colspan="2">${clientInfo[0].chapter_name}</td>
                            <td colspan="3">${clientInfo[0].chapter_unit}</td>
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
                <div class="row" style="width: 100px;margin: 20px auto">
                    <button id="change-button" type="button" class="btn btn-primary" onclick="change()">修改信息</button>
                </div>
                <div style="min-height: 100px"></div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>


</body>
<script src="${ctx}/dist/js/jquery.min.js"></script>
<script src="${ctx}/dist/js/index/jquery.login.js"></script>
<script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
<script src="${ctx}/dist/js/bootstrap.min.js"></script>
<script src="${ctx}/dist/js/left_nav.js"></script>
<script src="${ctx}/dist/js/index/amazeui.min.js"></script>
<script src="${ctx}/dist/controller/index/index.js"></script>
<script>

    $(function () {
        $('#zlight-nav').zlightMenu();
        generateNav(-1);
    });

    function change() {
        if('${clientInfo[0].status}' == 0 ){
            alert("邮箱未验证,暂不可修改信息");
        }
        if('${clientInfo[0].status}' == 1 ){
            alert("帐号信息审核中,暂不可修改信息");
        }
        if('${clientInfo[0].status}' == 2 ){
            alert("帐号信息审核不通过,暂不可修改信息");
        }
        if('${clientInfo[0].status}' == 3 ){
            window.location.href = "${ctx}/nUser/changeNUserInfo";
        }
        if('${clientInfo[0].status}' == 4 ){
            alert("帐号已停用,暂不可修改信息");
        }
    };
</script>

</html>
