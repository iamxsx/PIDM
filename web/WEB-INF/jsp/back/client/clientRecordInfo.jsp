<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 9/14/16
  Time: 11:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>客户信息档案</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav.css">
    <link rel="stylesheet" href="${ctx}/dist/css/client/clienInfo.css">

</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container-fluid" style="padding: 16px 36px;">
    <div class="row">
        <jsp:include page="../regmanage/left.jsp"/>
        <div class="col-md-10 right-panel" id="container">

            <ol class="breadcrumb">
                <span>当前位置：&nbsp&nbsp&nbsp</span>
                <li><a href="/back/user/clientList">客户管理</a></li>
                <li><a href="#" class="active">客户信息管理</a></li>
            </ol>
            <a type="button" class="btn btn-default" href="/back/user/clientList" style="padding: 0.2em 0.8em"><</a>
            <span id="company-name">
                ${user.company.name}
            </span>
            <div class="Info">
                <div class="conclass">
                    <div class="row">
                        <div class="col-sm-2 text-right">
                            帐号：
                        </div>
                        <div class="col-sm-2 text-left">
                            ${user.account}
                        </div>
                        <div class="col-sm-2 text-right">
                            申请人姓名：
                        </div>
                        <div class="col-sm-2 text-left">
                            ${user.realName}
                        </div>
                        <div class="col-sm-2 text-right">
                            申请人身份证：
                        </div>
                        <div class="col-sm-2 text-left">
                            ${user.IDcard}
                        </div>
                        <%--<div class="col-sm-2 text-right">
                            密码：
                        </div>
                        <div class="col-sm-2 text-left"></div>
                        <div class="col-sm-2 text-right">
                            确认密码：
                        </div>
                        <div class="col-sm-2 text-left"></div>--%>
                    </div>
                    <div class="row">
                        <div class="col-sm-2 text-right">
                            申请人手机号码：
                        </div>
                        <div class="col-sm-2 text-left">
                            ${user.phoneNum}
                        </div>
                        <div class="col-sm-2 text-right">
                            申请人联系邮箱：
                        </div>
                        <div class="col-sm-2 text-left">
                            ${user.email}
                        </div>
                    </div>
                </div>
                <hr>
                <div class="conclass">
                    <div class="row">
                        <div class="col-sm-2 text-right">
                            单位名称：
                        </div>
                        <div class="col-sm-2 text-left">
                            ${user.company.name}
                        </div>
                        <div class="col-sm-2 text-right">
                            单位性质：
                        </div>
                        <div class="col-sm-2 text-left">
                            ${user.company.nature}
                        </div>
                        <div class="col-sm-2 text-right">
                            通讯地址：
                        </div>
                        <div class="col-sm-2 text-left">
                            ${user.company.address}
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-2 text-right">
                            邮政编码：
                        </div>
                        <div class="col-sm-2 text-left">
                            ${user.company.zipCode}
                        </div>
                    </div>
                </div>
                <hr>
                <div class="conclass">
                    <div class="row">
                        <div class="col-sm-2 text-right">
                            法人代表：
                        </div>
                        <div class="col-sm-2 text-left">
                            ${companyEmployee.name}
                        </div>
                        <div class="col-sm-2 text-right">
                            职务：
                        </div>
                        <div class="col-sm-2 text-left">
                            ${companyEmployee.jobPosition}
                        </div>
                        <div class="col-sm-2 text-right">
                            办公电话：
                        </div>
                        <div class="col-sm-2 text-left">
                            ${companyEmployee.officePhoneNum}
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-2 text-right">
                            手机：
                        </div>
                        <div class="col-sm-2 text-left">
                        </div>
                    </div>
                </div>
                <hr>
                <div class="conclass">
                    <div class="row">
                        <div class="col-sm-2 text-right">
                            指定联系人：
                        </div>
                        <div class="col-sm-2 text-left">
                            ${user.designatedContact.name}
                        </div>
                        <div class="col-sm-2 text-right">
                            职务：
                        </div>
                        <div class="col-sm-2 text-left">
                            ${user.designatedContact.jobPosition}
                        </div>
                        <div class="col-sm-2 text-right">
                            办公电话：
                        </div>
                        <div class="col-sm-2 text-left">
                            ${user.designatedContact.officePhoneNum}
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-2 text-right">
                            手机：
                        </div>
                        <div class="col-sm-2 text-left">
                            ${user.designatedContact.cellPhoneNum}
                        </div>
                        <div class="col-sm-2 text-right">
                            邮箱：
                        </div>
                        <div class="col-sm-2 text-left">
                            ${user.designatedContact.email}
                        </div>
                        <div class="col-sm-2 text-right">
                            传真号码：
                        </div>
                        <div class="col-sm-2 text-left">
                            ${user.designatedContact.faxNum}
                        </div>
                    </div>
                </div>
                <hr>
            <div class="conclass">

                <c:choose>
                    <c:when test="${user.associationId == 1 || user.associationId == 5}">
                        <%--广东价格和产业发展协会--%>
                        <div id="assoc1">
                            <div class="row" >
                                <div class="col-sm-2 text-right">
                                    科研成果：
                                </div>
                                <div class="col-sm-8" id="des1">
                                        ${description.description1}
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-2 text-right">
                                    表彰与处分：
                                </div>
                                <div class="col-sm-8" id="des2">
                                        ${description.description2}
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${user.associationId == 2}">
                        <%--电价分会--%>
                        <div id="assoc2">
                            <div class="row">
                                <div class="col-sm-2 text-right">
                                    主营业务：
                                </div>
                                <div class="col-sm-8" id="des3">
                                        ${description.description1}
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-2 text-right">
                                    希望得到分会哪方面到支持：
                                </div>
                                <div class="col-sm-8" id="des4">${description.description2}
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${user.associationId == 3}">
                        <%--燃气价格分会--%>
                        <div id="assoc3" class="row">
                            <div class="col-sm-2 text-right">
                                对广东价格和产业发展协会燃气价格分会的建议：
                            </div>
                            <div class="col-sm-8" id="des5">
                                    ${description.description1}
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${user.associationId == 4}">
                        <%--农产品价格分会--%>
                        <div id="assoc4" class="row">
                            <div class="col-sm-2 text-right">
                                对广东价格和产业发展协会农产品价格分会的建议：
                            </div>
                            <div class="col-sm-8" id="des6">
                                    ${description.description1}
                            </div>
                        </div>
                    </c:when>
                </c:choose>
            </div>
                <div class="buttonList">
                    <button type="button" class="btn btn-primary" onclick="exportPDF(${user.id});">导出</button>
                    <button id="change-button" type="button" class="btn btn-primary" onclick="change(${user.id});">修改</button>
                    <a type="button" class="btn btn-primary" href="/back/user/clientList">返回</a>
                </div>
            </div>

        </div>
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
