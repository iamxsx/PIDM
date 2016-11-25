<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>个人信息</title>
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
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav_frontend.css">
    <link rel="stylesheet" href="${ctx}/dist/css/client/clienInfo.css">

    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <script src="${ctx}/dist/js/index/jquery.login.js"></script>
    <script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
    <script src="${ctx}/dist/js/bootstrap.min.js"></script>
    <script src="${ctx}/dist/js/bootstrap-table.js"></script>
    <script src="${ctx}/dist/js/left_nav.js"></script>
    <script src="${ctx}/dist/js/index/amazeui.min.js"></script>
    <script src="${ctx}/dist/controller/index/index.js"></script>
    <script src="${ctx}/dist/controller/changeUserInfo.js"></script>

    <script>
        var companyId = "${user.company.id}";
        var uId = "${user.id}";

        var designatedContactId = "${user.designatedContact.id}"

        var realName2 ="${user.realName}";
        var phoneNum2 ="${user.phoneNum}";
        var IDcard2 = "${user.IDcard}";
        var email2 = "${user.email}";
        var spname2 = "${user.designatedContact.name}";
        var spjob2 = "${user.designatedContact.jobPosition}";
        var spofficephone2 ="${user.designatedContact.officePhoneNum}";
        var spcellphone2 = "${user.designatedContact.cellPhoneNum}";
        var spemail2 ="${user.designatedContact.email}";
        var spafaxnum2 ="${user.designatedContact.faxNum}";

    </script>

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
                        <a href="../user/showUserInfo">
                            信息管理
                        </a>
                    </div>
                </li>
            </ul>
        </div>

        <div class="col-md-10 right-panel" id="container">

            <div class="container-fluid">
                <div class="conclass">
                    <div class="row">
                        <div class="col-sm-2 text-right">
                            帐号：
                        </div>
                        <input id="account" type="text" class="col-sm-2" value="${user.account}" disabled="disabled">
                        <div class="col-sm-2 text-right">
                            密码：
                        </div>
                        <input id="password" type="password" class="col-sm-2" value="123456" disabled="disabled">
                        <a href="../register/redirect?msg=forget">修改密码</a>
                        <%--<div class="col-sm-2 text-right">--%>
                        <%--确认密码：--%>
                        <%--</div>--%>
                        <%--<input id="conpassword" type="password" class="col-sm-2">--%>
                        <%--</input>--%>
                    </div>
                    <div class="row">
                        <div class="col-sm-2 text-right">
                            申请人姓名：
                        </div>
                        <input id="realName" type="text" class="col-sm-2" value="${user.realName}" onblur="changeBtn()">
                        <div class="col-sm-2 text-right">
                            申请人身份证：
                        </div>
                        <input id="IDcard" type="text" class="col-sm-2" value="${user.IDcard}" onblur="changeBtn()">
                        <div class="col-sm-2 text-right">
                            申请人手机号码：
                        </div>
                        <input id="phoneNum" type="text" class="col-sm-2" value="${user.phoneNum}" onblur="changeBtn()">
                    </div>
                    <div class="row">
                        <div class="col-sm-2 text-right">
                            申请人联系邮箱：
                        </div>
                        <input id="email" type="text" class="col-sm-2" value="${user.email}" onblur="changeBtn()">
                    </div>
                </div>
                <hr>
                <div class="conclass">

                    <c:choose>
                        <c:when test="${user.associationId == 1 || user.associationId == 5}">
                            <div class="conclass">
                                <div class="row">
                                    <div class="col-sm-2 text-right">
                                        单位名称：
                                    </div>
                                    <input type="text" disabled="disabled" class="col-sm-2" value="${user.company.name}">
                                    <div class="col-sm-2 text-right">
                                        企业注册类型：
                                    </div>
                                    <input type="text" disabled="disabled" class="col-sm-2" value="${user.company.registerNature}">

                                </div>
                                <div class="row">
                                    <div class="col-sm-2 text-right">
                                        所在城市：
                                    </div>
                                    <input type="text" disabled="disabled" class="col-sm-2" value="${user.company.city}">
                                    <div class="col-sm-2 text-right">
                                        所在区/县：
                                    </div>
                                    <input type="text" disabled="disabled" class="col-sm-2" value="${user.company.county}">
                                    <div class="col-sm-2 text-right">
                                        通讯地址：
                                    </div>
                                    <input type="text" disabled="disabled" class="col-sm-2" value="${user.company.address}">
                                </div>
                                <div class="row">
                                    <div class="col-sm-2 text-right">
                                        邮政编码：
                                    </div>
                                    <input type="text" disabled="disabled" class="col-sm-2" value="${user.company.zipCode}">
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${user.associationId == 2}">
                            <div class="conclass">
                                <div class="row">
                                    <div class="col-sm-2 text-right">
                                        单位名称：
                                    </div>
                                    <input type="text" disabled="disabled" class="col-sm-2" value="${user.company.name}">
                                    <div class="col-sm-2 text-right">
                                        单位性质：
                                    </div>
                                    <input type="text" disabled="disabled" class="col-sm-2" value="${user.company.nature}">

                                </div>
                                <div class="row">
                                    <div class="col-sm-2 text-right">
                                        所在城市：
                                    </div>
                                    <input type="text" disabled="disabled" class="col-sm-2" value="${user.company.city}">
                                    <div class="col-sm-2 text-right">
                                        所在区/县：
                                    </div>
                                    <input type="text" disabled="disabled" class="col-sm-2" value="${user.company.county}">
                                    <div class="col-sm-2 text-right">
                                        通讯地址：
                                    </div>
                                    <input type="text" disabled="disabled" class="col-sm-2" value="${user.company.address}">
                                </div>
                                <div class="row">
                                    <div class="col-sm-2 text-right">
                                        邮政编码：
                                    </div>
                                    <input type="text" disabled="disabled" class="col-sm-2" value="${user.company.zipCode}">
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${user.associationId == 3 || user.associationId == 4 }">
                            <div class="conclass">
                                <div class="row">
                                    <div class="col-sm-2 text-right">
                                        单位名称：
                                    </div>
                                    <input type="text" disabled="disabled" class="col-sm-2" value="${user.company.name}">
                                </div>
                                <div class="row">
                                    <div class="col-sm-2 text-right">
                                        所在城市：
                                    </div>
                                    <input type="text" disabled="disabled" class="col-sm-2" value="${user.company.city}">
                                    <div class="col-sm-2 text-right">
                                        所在区/县：
                                    </div>
                                    <input type="text" disabled="disabled" class="col-sm-2" value="${user.company.county}">
                                    <div class="col-sm-2 text-right">
                                        通讯地址：
                                    </div>
                                    <input type="text" disabled="disabled" class="col-sm-2" value="${user.company.address}">
                                </div>
                                <div class="row">
                                    <div class="col-sm-2 text-right">
                                        邮政编码：
                                    </div>
                                    <input type="text" disabled="disabled" class="col-sm-2" value="${user.company.zipCode}">
                                    <div class="col-sm-2 text-right">
                                        电子邮箱：
                                    </div>
                                    <input type="text" disabled="disabled" class="col-sm-2" value="${user.company.email}">
                                </div>
                            </div>
                        </c:when>
                    </c:choose>

                </div>
                <hr>
                <div class="conclass">
                    <div class="row">
                        <div class="col-sm-2 text-right">
                            法人代表：
                        </div>
                        <input id="lename" type="text" class="col-sm-2" value="${companyEmployee.name}" disabled="disabled">
                        <div class="col-sm-2 text-right">
                            职务：
                        </div>
                        <input id="lejob" type="text" class="col-sm-2" value="${companyEmployee.jobPosition}" disabled="disabled">
                        <div class="col-sm-2 text-right">
                            办公电话：
                        </div>
                        <input id="leofficephone" type="text" class="col-sm-2" value="${companyEmployee.officePhoneNum}" disabled="disabled">
                    </div>
                    <div class="row">
                        <div class="col-sm-2 text-right">
                            手机：
                        </div>
                        <input id="lecellphone" type="text" class="col-sm-2" value="${companyEmployee.cellPhoneNum}" disabled="disabled">
                    </div>
                </div>
            </div>
            <hr>
            <div class="container-fluid conclass">
                <div class="row">
                    <div class="col-sm-2 text-right">
                        指定联系人：
                    </div>
                    <input id="spname" type="text" class="col-sm-2" value="${user.designatedContact.name}" onblur="changeBtn()">
                    <div class="col-sm-2 text-right">
                        职务：
                    </div>
                    <input id="spjob" type="text" class="col-sm-2" value="${user.designatedContact.jobPosition}" onblur="changeBtn()">
                    <div class="col-sm-2 text-right">
                        办公电话：
                    </div>
                    <input id="spofficephone" type="text" class="col-sm-2" value="${user.designatedContact.officePhoneNum}" onblur="changeBtn()">
                </div>
                <div class="row">
                    <div class="col-sm-2 text-right">
                        手机：
                    </div>
                    <input id="spcellphone" type="text" class="col-sm-2" value="${user.designatedContact.cellPhoneNum}" onblur="changeBtn()">
                    <div class="col-sm-2 text-right">
                        邮箱：
                    </div>
                    <input id="spemail" type="text" class="col-sm-2" value="${user.designatedContact.email}" onblur="changeBtn()">
                    <div class="col-sm-2 text-right">
                        传真号码：
                    </div>
                    <input id="spfaxnum" type="text" class="col-sm-2" value="${user.designatedContact.faxNum}" onblur="changeBtn()">
                </div>
            </div>
            <hr>
            <div class="container-fluid conclass">
                <c:choose>
                    <c:when test="${user.associationId == 1 || user.associationId == 5}">
                        <%--广东价格和产业发展协会--%>
                        <div id="assoc1">
                            <div class="row" >
                                <div class="col-sm-2 text-right">
                                    科研成果：
                                </div>
                                <textarea class="col-sm-8" name="des1" disabled="disabled">${description.description1}</textarea>
                            </div>
                            <div class="row">
                                <div class="col-sm-2 text-right">
                                    表彰与处分：
                                </div>
                                <textarea class="col-sm-8" name="des2" disabled="disabled">${description.description2}</textarea>
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
                                <textarea class="col-sm-8" name="des1" disabled="disabled">${description.description1}</textarea>
                            </div>
                            <div class="row">
                                <div class="col-sm-2 text-right">
                                    希望得到分会哪方面到支持：
                                </div>
                                <textarea class="col-sm-8" name="des2" disabled="disabled">${description.description2}</textarea>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${user.associationId == 3}">
                        <%--燃气价格分会--%>
                        <div id="assoc3" class="row">
                            <div class="col-sm-2 text-right">
                                对广东价格和产业发展协会燃气价格分会的建议：
                            </div>
                            <textarea class="col-sm-8" name="des1" disabled="disabled">${description.description1}</textarea>
                        </div>
                    </c:when>
                    <c:when test="${user.associationId == 4}">
                        <%--农产品价格分会--%>
                        <div id="assoc4" class="row">
                            <div class="col-sm-2 text-right">
                                对广东价格和产业发展协会农产品价格分会的建议：
                            </div>
                            <textarea class="col-sm-8" name="des1" disabled="disabled">${description.description1}</textarea>
                        </div>
                    </c:when>
                </c:choose>
            </div>


            <div class="buttonList">
                <button type="button" class="btn btn-primary" id="save-button" disabled="disabled" style="width: 100px" onclick="clickSaveChangeBtn()">保存修改</button>
                <%--<a type="button" class="btn btn-primary" style="width: 100px" href="#">返回</a>--%>
            </div>
        </div>
    </div>
</div>


</body>

<script>

    $(function () {
        $('#zlight-nav').zlightMenu();
        generateNav(-1);
    });
</script>
</html>
