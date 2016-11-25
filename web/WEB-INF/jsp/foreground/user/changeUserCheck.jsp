<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>个人审核信息修改</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/login.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav.css">
    <link rel="stylesheet" href="${ctx}/dist/css/client/clienInfo.css">

    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <script src="${ctx}/dist/js/index/jquery.login.js"></script>
    <script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
    <script src="${ctx}/dist/js/bootstrap.min.js"></script>
    <script src="${ctx}/dist/js/bootstrap-table.js"></script>
    <script src="${ctx}/dist/js/left_nav.js"></script>
    <script src="${ctx}/dist/js/index/amazeui.min.js"></script>
    <script src="${ctx}/dist/controller/index/index.js"></script>
    <%--<script src="${ctx}/dist/controller/changeUserInfo.js"></script>--%>

</head>
<body>

<jsp:include page="/WEB-INF/jsp/foreground/header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <ul class="left-nav">
                <li class="head">
                    <div class="left-nav-item">
                        <span>个人中心</span>
                    </div>
                </li>
                <li>
                    <div class="left-nav-item">
                        <a href="../work/to-personal">
                            <i class="icon-bell-alt"></i> 消息管理
                        </a>
                    </div>
                </li>
                <li>
                    <div class="left-nav-item">
                        <a href="../user/showUserInfo">
                            <i class="icon-th-large"></i> 信息管理
                        </a>
                    </div>
                </li>
            </ul>
        </div>

        <div class="col-md-9 right-panel" id="container">

            <div class="container-fluid">
                <form id="check-form" action="${ctx}/user/updateCheckInfo" method="post">
                    <div class="conclass">
                        <div class="row">
                            <input type="hidden" name="user.id" value="${user.id}">
                            <input type="hidden" name="association.id" value="${association.id}">
                            <input type="hidden" name="user.associationUnit.id" value="${associationUnit.id}">
                            <input type="hidden" name="user.account" value="${user.account}">
                            <div class="col-sm-2 text-right">
                                帐号：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="account" type="text" class="form-control" value="${user.account}" disabled="disabled">
                            </div>

                            <div class="col-sm-2 text-right">
                                申请人姓名：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="user-realName" type="text" class="form-control" name="user.realName" value="${user.realName}" onblur="changeCheck('#user-realName','${user.realName}')">
                            </div>
                            <div class="col-sm-2 text-right">
                                加入协会：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input disabled="disabled" type="text" class="form-control" name="association.name" value="${association.name}">
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-sm-2 text-right">
                                申请人手机号码：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="user-phoneNum" type="text" class="form-control" name="user.phoneNum" value="${user.phoneNum}" onblur="changeCheck('#user-phoneNum','${user.phoneNum}')">
                            </div>

                            <div class="col-sm-2 text-right">
                                申请人联系邮箱：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input  id="user-email" type="text" class="form-control"name="user.email" value="${user.email}" onblur="changeCheck('#user-email','${user.email}')">
                            </div>

                            <div class="col-sm-2 text-right">
                                申请人身份证：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="user-IDcard" type="text" class="form-control" name="user.IDcard" value="${user.IDcard}" onblur="changeCheck('#user-IDcard','${user.IDcard}')">
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-sm-2 text-right">
                                职位：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input  id="user-jobPosition" type="text" class="form-control" name="user.jobPosition" value="${user.jobPosition}" onblur="changeCheck('#user-jobPosition','${user.jobPosition}')">
                            </div>

                            <div class="col-sm-2 text-right">
                                加入协会单位：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="associationUnit-name" type="text" class="form-control" name="associationUnit.name" value="${associationUnit.name}" onblur="changeCheck('#associationUnit-name','${associationUnit.name}')">
                            </div>

                        </div>
                    </div>
                    <hr>
                    <div id="companyInfo">
                        <div class="conclass" >
                            <div class="row">
                                <input id="company-id" type="hidden" name="company.id" value="${company.id}">
                                <div class="col-sm-2 text-right">
                                    单位名称：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input id="company" type="text" class="form-control" name="company.name" value="${company.name}"  onblur="changeCheck('#company','${company.name}')">
                                </div>

                                <div class="col-sm-2 text-right">
                                    企业注册类型：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input id="company-registerNature" type="text" class="form-control" name="company.registerNature" value="${company.registerNature}" onblur="changeCheck('#company-registerNature','${company.registerNature}')">
                                </div>

                                <div class="col-sm-2 text-right">
                                    通讯地址：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input id="company-address" type="text" class="form-control" name="company.address" value="${company.address}" onblur="changeCheck('#company-address','${company.address}')">
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-sm-2 text-right">
                                    所在城市：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input id="company-city" type="text" class="form-control" name="company.city" value="${company.city}" onblur="changeCheck('#company-city','${company.city}')">
                                </div>

                                <div class="col-sm-2 text-right">
                                    所在区/县：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input id="company-county" type="text" class="form-control" name="company.county" value="${company.county}" onblur="changeCheck('#company-county','${company.county}')">
                                </div>

                                <div class="col-sm-2 text-right">
                                    电子邮箱：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input id="company-email" type="text" class="form-control" name="company.email" value="${company.email}" onblur="changeCheck('#company-email','${company.email}')">
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-sm-2 text-right">
                                    邮政编码：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input id="company-zipCode" type="text" class="form-control" name="company.zipCode" value="${company.zipCode}" onblur="changeCheck('#company-zipCode','${company.zipCode}')" >
                                </div>

                                <div class="col-sm-2 text-right">
                                    单位网编：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input id="company-internetSite" type="text" class="form-control" name="company.internetSite" value="${company.internetSite}" onblur="changeCheck('#company-internetSite','${company.internetSite}')" >
                                </div>

                                <div class="col-sm-2 text-right">
                                    传真：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input id="company-faxNum" type="text" class="form-control" name="company.faxNum" value="${company.faxNum}" onblur="changeCheck('#company-faxNum','${company.faxNum}')">
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="conclass">
                            <div class="row">
                                <input type="hidden" name="companyEmployee_legal.id" value="${companyEmployee_legal.id}">
                                <div class="col-sm-2 text-right">
                                    法人代表：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input id="companyEmployee_legal-name" type="text" class="form-control" name="companyEmployee_legal.name" value="${companyEmployee_legal.name}" onblur="changeCheck('#companyEmployee_legal-name','${companyEmployee_legal.name}')">
                                </div>

                                <div class="col-sm-2 text-right">
                                    职务：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input id="companyEmployee_legal-jobPosition" type="text" class="form-control" name="companyEmployee_legal.jobPosition" value="${companyEmployee_legal.jobPosition}" onblur="changeCheck('#companyEmployee_legal-jobPosition','${companyEmployee_legal.jobPosition}')">
                                </div>

                                <div class="col-sm-2 text-right">
                                    办公电话：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input id="companyEmployee_legal-officePhoneNum" type="text" class="form-control" name="companyEmployee_legal.officePhoneNum" value="${companyEmployee_legal.officePhoneNum}" onblur="changeCheck('#companyEmployee_legal-officePhoneNum','${companyEmployee_legal.officePhoneNum}')" >
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-sm-2 text-right">
                                    手机：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input id="companyEmployee_legal-cellPhoneNum" type="text" class="form-control" name="companyEmployee_legal.cellPhoneNum" value="${companyEmployee_legal.cellPhoneNum}"  onblur="changeCheck('#companyEmployee_legal-cellPhoneNum','${companyEmployee_legal.cellPhoneNum}')">
                                </div>

                                <div class="col-sm-2 text-right">
                                    传真：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input  id="companyEmployee_legal-faxNum" type="text" class="form-control" name="companyEmployee_legal.faxNum" value="${companyEmployee_legal.faxNum}" onblur="changeCheck('#companyEmployee_legal-faxNum','${companyEmployee_legal.faxNum}')">
                                </div>

                                <div class="col-sm-2 text-right">
                                    邮箱：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input  id="companyEmployee_legal-email" type="text" class="form-control" name="companyEmployee_legal.email" value="${companyEmployee_legal.email}" onblur="changeCheck('#companyEmployee_legal-email','${companyEmployee_legal.email}')">
                                </div>

                            </div>
                        </div>
                        <hr>
                        <div class="conclass">
                            <div class="row">
                                <div class="col-sm-2 text-right">
                                    企业性质：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input  id="company-nature" type="text" class="form-control" name="company.nature" value="${company.nature}" onblur="changeCheck('#company-nature','${company.nature}')">
                                </div>

                                <div class="col-sm-2 text-right">
                                    所属行业：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input  id="company-industry" type="text" class="form-control" name="company.industry" value="${company.industry}" onblur="changeCheck('#company-industry','${company.industry}')">
                                </div>

                                <div class="col-sm-2 text-right">
                                    主管部门：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input  id="company-adminDepartment" type="text" class="form-control" name="company.adminDepartment" value="${company.adminDepartment}" onblur="changeCheck('#company-adminDepartment','${company.adminDepartment}')">
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-sm-2 text-right">
                                    员工人数：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input id="company-employeeNum" type="text" class="form-control" name="company.employeeNum" value="${company.employeeNum}" onblur="changeCheck('#company-employeeNum','${company.employeeNum}')" >
                                </div>

                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="conclass">
                        <div class="row">
                            <input type="hidden" name="designatedContact.id" value="${designatedContact.id}">
                            <div class="col-sm-2 text-right">
                                指定联系人：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="designatedContact-name" type="text" class="form-control" name="designatedContact.name" value="${designatedContact.name}" onblur="changeCheck('#designatedContact-name','${designatedContact.name}')">
                            </div>

                            <div class="col-sm-2 text-right">
                                职务：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="designatedContact-jobPosition" type="text" class="form-control" name="designatedContact.jobPosition" value="${designatedContact.jobPosition}" onblur="changeCheck('#designatedContact-jobPosition','${designatedContact.jobPosition}')">
                            </div>

                            <div class="col-sm-2 text-right">
                                办公电话：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="designatedContact-officePhoneNum" type="text" class="form-control" name="designatedContact.officePhoneNum" value="${designatedContact.officePhoneNum}"  onblur="changeCheck('#designatedContact-officePhoneNum','${designatedContact.officePhoneNum}')">
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-sm-2 text-right">
                                手机：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="designatedContact-cellPhoneNum" type="text" class="form-control" name="designatedContact.cellPhoneNum" value="${designatedContact.cellPhoneNum}"  onblur="changeCheck('#designatedContact-cellPhoneNum','${designatedContact.cellPhoneNum}')">
                            </div>

                            <div class="col-sm-2 text-right">
                                邮箱：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="designatedContact-email" type="text" class="form-control" name="designatedContact.email" value="${designatedContact.email}"  onblur="changeCheck('#designatedContact-email','${designatedContact.email}')">
                            </div>

                            <div class="col-sm-2 text-right">
                                传真号码：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="designatedContact-faxNum" type="text" class="form-control" name="designatedContact.faxNum" value="${designatedContact.faxNum}"  onblur="changeCheck('#designatedContact-faxNum','${designatedContact.faxNum}')">
                            </div>

                        </div>
                    </div>
                    <hr>
                    <div class="conclass">
                        <c:forEach items="${introduced}" var="item" varStatus="status">
                            <div class="row">
                                <input type="hidden" name="introduced[${status.index}].id" value="${item.id}">
                                <div class="col-sm-2 text-right">
                                    协会职务人选：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input id="introduced[${status.index}]-name" type="text" class="form-control" name="introduced[${status.index}].name" value="${item.name}"  onblur="changeCheck('#introduced[${status.index}]-name','${item.name}')">
                                </div>

                                <div class="col-sm-2 text-right">
                                    职务：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input id="introduced[${status.index}]-jobPostion" type="text" class="form-control" name="introduced[${status.index}].jobPosition" value="${item.jobPosition}"  onblur="changeCheck('#introduced[${status.index}]-jobPostion','${item.jobPosition}')">
                                </div>

                                <div class="col-sm-2 text-right">
                                    办公电话：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input id="introduced[${status.index}]-officePhoneNum" type="text" class="form-control" name="introduced[${status.index}].officePhoneNum" value="${item.cellPhoneNum}"  onblur="changeCheck('#introduced[${status.index}]-officePhoneNum','${item.officePhoneNum}')">
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-sm-2 text-right">
                                    手机：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input id="introduced[${status.index}].cellPhoneNum" type="text" class="form-control" name="introduced[${status.index}].cellPhoneNum" value="${item.officePhoneNum}"  onblur="changeCheck('#introduced[${status.index}]-cellPhoneNum','${item.cellPhoneNum}')">
                                </div>

                                <div class="col-sm-2 text-right">
                                    邮箱：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input id="introduced[${status.index}]-email" type="text" class="form-control" name="introduced[${status.index}].email" value="${item.email}"  onblur="changeCheck('#introduced[${status.index}]-email','${item.email}')">
                                </div>

                                <div class="col-sm-2 text-right">
                                    传真号码：
                                </div>
                                <div class="col-sm-2 coltext">
                                    <input id="introduced[${status.index}]-faxNum" type="text" class="form-control" name="introduced[${status.index}].faxNum" value="${item.faxNum}"  onblur="changeCheck('#introduced[${status.index}]-faxNum','${item.faxNum}')">
                                </div>

                            </div>
                        </c:forEach>
                    </div>
                    <hr>
                    <c:choose>
                        <c:when test="${association.id == 4}">
                            <div class="conclass">
                                <input type="hidden" name="companyEmployee_rep.id" value="${companyEmployee_rep.id}">
                                <div class="row">
                                    <div class="col-sm-2 text-right">
                                        单位代表：
                                    </div>
                                    <div class="col-sm-2 coltext">
                                        <input type="text" class="form-control" name="companyEmployee_rep.name" value="${companyEmployee_rep.name}" id="companyEmployee_rep-name" onblur="changeCheck('#companyEmployee_rep-name','${companyEmployee_rep.name}')">
                                    </div>

                                    <div class="col-sm-2 text-right">
                                        职务：
                                    </div>
                                    <div class="col-sm-2 coltext">
                                        <input type="text" class="form-control" name="companyEmployee_rep.jobPosition" value="${companyEmployee_rep.jobPosition}" id="companyEmployee_rep-jobPosition" onblur="changeCheck('#companyEmployee_rep-jobPosition','${companyEmployee_rep.jobPosition}')">
                                    </div>

                                    <div class="col-sm-2 text-right">
                                        电话：
                                    </div>
                                    <div class="col-sm-2 coltext">
                                        <input type="text" class="form-control" name="companyEmployee_rep.officePhoneNum" value="${companyEmployee_rep.officePhoneNum}" id="companyEmployee_rep-officePhoneNum" onblur="changeCheck('#companyEmployee_rep-officePhoneNum','${companyEmployee_rep.officePhoneNum}')">
                                    </div>

                                </div>
                                <div class="row">
                                    <div class="col-sm-2 text-right">
                                        手机：
                                    </div>
                                    <input type="text" class="form-control" name="companyEmployee_rep.cellPhoneNum" value="${companyEmployee_rep.cellPhoneNum}" id="companyEmployee_rep-cellPhoneNum" onblur="changeCheck('#companyEmployee_rep-cellPhoneNum','${companyEmployee_rep.cellPhoneNum}')">
                                    <div class="col-sm-2 text-right">
                                        传真：
                                    </div>
                                    <div class="col-sm-2 coltext">
                                        <input type="text" class="form-control" name="companyEmployee_rep.faxNum" value="${companyEmployee_rep.faxNum}" id="companyEmployee_rep-faxNum" onblur="changeCheck('#companyEmployee_rep-faxNum','${companyEmployee_rep.faxNum}')">
                                    </div>

                                    <div class="col-sm-2 text-right">
                                        电子邮箱：
                                    </div>
                                    <div class="col-sm-2 coltext">
                                        <input type="text" class="form-control" name="companyEmployee_rep.email" value="${companyEmployee_rep.email}" id="companyEmployee_rep-email" onblur="changeCheck('#companyEmployee_rep-email','${companyEmployee_rep.email}')">
                                    </div>

                                </div>
                            </div>
                            <hr>
                        </c:when>
                    </c:choose>

                    <div id="judgeCompany">
                        <div class="conclass">
                            <input id="description-id" type="hidden" name="description.id" value="${description.id}">
                            <c:choose>
                                <c:when test="${user.associationId == 1 || user.associationId == 5}">
                                    <%--广东价格和产业发展协会--%>
                                    <div id="assoc1">
                                        <div class="row" >
                                            <div class="col-sm-2 text-right">
                                                科研成果：
                                            </div>
                                            <div class="col-sm-8">
                                                <textarea id="description1" class="form-control" name="description.description1"  onblur="changeCheck('#description1','${description.description1}')">${description.description1}</textarea>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-2 text-right">
                                                表彰与处分：
                                            </div>
                                            <div class="col-sm-8">
                                                <textarea id="description2" class="form-control" name="description.description2"  onblur="changeCheck('#description2','${description.description2}')">${description.description2}</textarea>
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
                                            <div class="col-sm-8">
                                                <textarea id="description3" class="form-control" name="description.description1"  onblur="changeCheck('#description3','${description.description1}')">${description.description1}</textarea>
                                            </div>

                                        </div>
                                        <div class="row">
                                            <div class="col-sm-2 text-right">
                                                希望得到分会哪方面到支持：
                                            </div>
                                            <div class="col-sm-8">
                                                <textarea id="description4" class="form-control" name="description.description2"  onblur="changeCheck('#description4','${description.description2}')">${description.description2}</textarea>
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
                                        <div class="col-sm-8">
                                            <textarea id="description5" class="form-control" name="description.description1"  onblur="changeCheck('#description5','${description.description1}')">${description.description1}</textarea>
                                        </div>

                                    </div>
                                </c:when>
                                <c:when test="${user.associationId == 4}">
                                    <%--农产品价格分会--%>
                                    <div id="assoc4" class="row">
                                        <div class="col-sm-2 text-right">
                                            对广东价格和产业发展协会农产品价格分会的建议：
                                        </div>
                                        <div class="col-sm-8">
                                            <textarea id="description6" class="form-control" name="description.description1"  onblur="changeCheck('#description6','${description.description1}')">${description.description1}</textarea>
                                        </div>

                                    </div>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>

                    <div class="buttonList">
                        <button disabled="disabled" type="submit" class="btn btn-primary" id="save-button" style="width: 100px">保存修改</button>
                        <%--<a type="button" class="btn btn-primary" style="width: 100px" href="#">返回</a>--%>
                    </div>
                </form>


        </div>
    </div>
</div>
</div>
</body>

<script>

    $(function () {


        $('#zlight-nav').zlightMenu();
        generateNav(-1);
        if(${judgeNewCompany != 0}){
            $('#companyInfo input[type="text"]').attr("disabled","disabled");
            $('#judgeCompany textarea').attr("disabled","disabled");

        }else{

        }
        /*$('#save-button').click(function () {

            $('#check-form').submit();
        });*/
        /*$('#check-form').submit(function () {
            var realName = "${user.realName}";
            if($('#realName').val()!= realName){
                $('#save-button').remove('disabled');
                return true;
            }else {
                $('#save-button').attr('disabled',true);
                return false;
            }
        });*/

    });
    function changeCheck(id,val) {
        if($(id).val() == val){
            $("#save-button").attr("disabled",true);
        }else {
            $("#save-button").attr("disabled",false);
        }

        if(id == "#user-email"||id == "#company-email"||id == "#companyEmployee_legal-email"||id == "#designatedContact-email"||id == "companyEmployee_rep-email"){
             reg = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
             if(!reg.test($(id).val())){
                alert("邮箱格式错误!");
                $("#save-button").attr("disabled",true);
             }else{
                $("#save-button").attr("disabled",false);
             }
         }

    }

</script>
</html>