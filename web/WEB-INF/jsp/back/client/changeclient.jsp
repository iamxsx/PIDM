<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 9/18/16
  Time: 2:10 PM
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
    <script src="${ctx}/dist/js/jquery.min.js"></script>
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


            <div class="container-fluid">
                    <div class="conclass">
                        <div class="row">
                            <div class="col-sm-2 text-right">
                                <span>*</span>&nbsp;帐号：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="account" type="text" class="form-control" value="${user.account}">
                            </div>

                            <div class="col-sm-2 text-right">
                                <span>*</span>&nbsp;密码：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="password" type="password" class="form-control">
                            </div>

                            <div class="col-sm-2 text-right">
                                <span>*</span>&nbsp;确认密码：
                            </div>

                            <div class="col-sm-2 coltext">
                                <input id="conpassword" type="password" class="form-control">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2 text-right">
                                <span>*</span>&nbsp;申请人姓名：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="realName" type="text" class="form-control" value="${user.realName}">
                            </div>
                            <div class="col-sm-2 text-right">
                                <span>*</span>&nbsp;申请人身份证：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="IDcard" type="text" class="form-control value="${user.IDcard}">
                            </div>
                            <div class="col-sm-2 text-right">
                                <span>*</span>&nbsp;申请人手机号码：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="phoneNum" type="text" class="form-control" value="${user.phoneNum}">
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-sm-2 text-right">
                                <span>*</span>&nbsp;申请人联系邮箱：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="email" type="text" class="form-control" value="${user.email}">
                            </div>

                        </div>
                    </div>
                    <hr>
                    <div class="conclass">
                        <div class="row">
                            <div class="col-sm-2 text-right">
                                <span>*</span>&nbsp;单位名称：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="compayname" type="text" class="form-control" value="${user.company.name}">
                            </div>

                            <div class="col-sm-2 text-right">
                                <span>*</span>&nbsp;单位性质：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="nature" type="text" class="form-control" value="${user.company.nature}">
                            </div>
                            <div class="col-sm-2 text-right">
                                <span>*</span>&nbsp;通讯地址：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="address" type="text" class="form-control" value="${user.company.address}">
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-sm-2 text-right">
                                邮政编码：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="zip_code" type="text" class="form-control" value="${user.company.zipCode}">
                            </div>

                        </div>
                    </div>
                    <hr>
                    <div class="conclass">
                        <div class="row">
                            <div class="col-sm-2 text-right">
                                <span>*</span>&nbsp;法人代表：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="lename" type="text" class="form-control" value="${companyEmployee.name}">
                            </div>

                            <div class="col-sm-2 text-right">
                                <span>*</span>&nbsp;职务：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="lejob" type="text" class="form-control" value="${companyEmployee.jobPosition}">
                            </div>

                            <div class="col-sm-2 text-right">
                                办公电话：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="leofficephone" type="text" class="form-control" value="${companyEmployee.officePhoneNum}">
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-sm-2 text-right">
                                <span>*</span>&nbsp;手机：
                            </div>
                            <div class="col-sm-2 coltext">
                                <input id="lecellphone" type="text" class="form-control" value="${companyEmployee.cellPhoneNum}">
                            </div>

                        </div>
                    </div>
                </div>
                <hr>
                <div class="conclass">
                    <div class="row">
                        <div class="col-sm-2 text-right">
                            <span>*</span>&nbsp;指定联系人：
                        </div>
                        <div class="col-sm-2 coltext">
                            <input id="spname" type="text" class="form-control" value="${user.designatedContact.name}">
                        </div>

                        <div class="col-sm-2 text-right">
                            <span>*</span>&nbsp;职务：
                        </div>
                        <div class="col-sm-2 coltext">
                            <input id="spjob" type="text" class="form-control" value="${user.designatedContact.jobPosition}">
                        </div>

                        <div class="col-sm-2 text-right">
                            办公电话：
                        </div>
                        <div class="col-sm-2 coltext">
                            <input id="spofficephone" type="text" class="form-control" value="${user.designatedContact.officePhoneNum}">
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-sm-2 text-right">
                            <span>*</span>&nbsp;手机：
                        </div>
                        <div class="col-sm-2 coltext">
                            <input id="spcellphone" type="text" class="form-control" value="${user.designatedContact.cellPhoneNum}">
                        </div>

                        <div class="col-sm-2 text-right">
                            <span>*</span>&nbsp;邮箱：
                        </div>
                        <div class="col-sm-2 coltext">
                            <input id="spemail" type="text" class="form-control has-error" value="${user.designatedContact.email}">
                        </div>

                        <div class="col-sm-2 text-right">
                            传真号码：
                        </div>
                        <div class="col-sm-2 coltext">
                            <input id="spfaxnum" type="text" class="form-control" value="${user.designatedContact.faxNum}">
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
                                    <div class="col-sm-8">
                                        <textarea class="form-control" name="des1">${description.description1}</textarea>
                                    </div>

                                </div>
                                <div class="row">
                                    <div class="col-sm-2 text-right">
                                        表彰与处分：
                                    </div>
                                    <div class="col-sm-8">
                                         <textarea class="form-control" name="des2">${description.description2}</textarea>
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
                                        <textarea class="form-control" name="des1">${description.description1}</textarea>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2 text-right">
                                        希望得到分会哪方面到支持：
                                    </div>
                                    <div class="col-sm-8">
                                        <textarea class="form-control" name="des2">${description.description2}</textarea>
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
                                    <textarea class="form-control" name="des1">${description.description1}</textarea>
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
                                    <textarea class="col-sm-8" name="des1">${description.description1}</textarea>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>
                </div>


                <a type="button" class="btn btn-primary" id="save-button"  tabindex="0" data-toggle="popover" data-trigger="focus">保存修改</a>

            </div>

        </div>
    </div>
</div>

</body>

<script src="${ctx}/dist/js/bootstrap.min.js"></script>
<script src="${ctx}/dist/js/bootstrap-table.js"></script>
<script src="${ctx}/dist/controller/client/clientChange.js"></script>
</html>
