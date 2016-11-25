<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 9/12/16
  Time: 9:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/back/article/article.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/login.css">
    <link rel="stylesheet" href="${ctx}/dist/css/user/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/user/style.css">
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrapValidator.min.css">
    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <%--<script src="${ctx}/dist/js/index/jquery.login.js"></script>--%>
    <style>
        .container {
            max-width: 1170px;
        }
    </style>
    <title>注册：第二步</title>
</head>
<body <%--onload="createCode()"--%>>
<jsp:include page="../header.jsp"/>
<div class="main clearfix">
    <!-- Nav tabs -->
    <div class="ul-div">
        <b><span class="font-x">*</span>请选择需要申请入会的协会：(带<span class="font-x">*</span>的表示为必填项目)</b>
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active">
                <a href="#home" role="tab" data-toggle="tab">广东省价格和产业发展协会</a>
            </li>
            <li role="presentation">
                <a href="#profile" role="tab" data-toggle="tab">电价分会</a>
            </li>
            <li role="presentation">
                <a href="#messages" role="tab" data-toggle="tab">燃气价格分会</a>
            </li>
            <li role="presentation">
                <a href="#setting" role="tab" data-toggle="tab">农产品价格分会</a>
            </li>
            <li role="presentation">
                <a href="#settings" role="tab" data-toggle="tab">评估鉴证分会</a>
            </li>
        </ul>
    </div>

    <!-- Tab panes -->
    <div class="tab-content form-box">
        <div role="tabpanel" class="tab-pane active" id="home">
            <form id="form1" class="form-inline" action="${ctx}/register/register-user" method="post">
                <div class="hidden">
                    <%--第一个标签页的协会是1--%>
                    <input type="hidden" name="associationId" value="1"><input type="hidden" name="token" value="${token}"/>
                </div>

                <div class="user clearfix">
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>帐号：</label><input type="text" id="f1-u-acc"
                                                                                  class="form-control"
                                                                                  placeholder="请输入4~16位的帐号"
                                                                                  name="user.account"/>
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>密码：</label><input id="f1-password"
                                type="password"
                                placeholder="请输入8~16位密码"
                                class="form-control" name="user.password">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>确定密码：</label><input
                                type="password"
                                placeholder="请输入确定密码"
                                class="form-control" name="rePassword">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>申请人姓名：</label><input
                                type="text"
                                placeholder=""
                                class="form-control" name="user.realName">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>申请人身份证：</label><input type="text"
                                                                                      placeholder=""
                                                                                      class="form-control"
                                                                                      name="user.IDcard">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>申请人手机号码：</label><input
                                type="text"
                                placeholder=""
                                class="form-control" name="user.phoneNum">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="clearfix">
                        <label><span class="font-x">*</span>申请人联系邮箱：</label><input type="text"
                                                                                   placeholder=""
                                                                                   class="form-control"
                                                                                   name="user.email">
                            </div>
                            <div class="error-info clearfix"></div>
                    </div>
                    </div>
                </div>

                <div class="company clearfix">
                    <div class="hidden">
                        <input type="hidden" name="company.id" value="${company.id}">
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <label><span class="font-x">*</span>单位名称：</label><input type="text"
                                                                                    class="form-control" name="company.name"
                                                                                    value="${company.name}" readonly="true"/>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>企业注册类型：</label><input
                                type="text"
                                class="form-control" name="company.registerNature" value="${company.registerNature}"
                                <%--<%="disabled=\"disabled\""%>--%>
                                <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        />
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field" style="height: 39px"></div>
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="city-linkage">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>所在城市：</label>
                                    <select class="form-control" onchange="showCity(this,$('#county1'))" name="company.city"
                                            <c:if test="${company.id != null}">disabled="disabled"</c:if>
                                    >
                                        <c:if test="${company.id == null}">
                                            <%=
                                            "<option>广东的城市</option>\n" +
                                                "                                <option value=\"广州市\">广州市</option>\n" +
                                                "                                <option value=\"佛山市\">佛山市</option>\n" +
                                                "                                <option value=\"中山市\">中山市</option>\n" +
                                                "                                <option value=\"东莞市\">东莞市</option>\n" +
                                                "                                <option value=\"深圳市\">深圳市</option>\n" +
                                                "                                <option value=\"珠海市\">珠海市</option>\n" +
                                                "                                <option value=\"韶关市\">韶关市</option>\n" +
                                                "                                <option value=\"河源市\">河源市</option>\n" +
                                                "                                <option value=\"梅州市\">梅州市</option>\n" +
                                                "                                <option value=\"惠州市\">惠州市</option>\n" +
                                                "                                <option value=\"汕尾市\">汕尾市</option>\n" +
                                                "                                <option value=\"揭阳市\">揭阳市</option>\n" +
                                                "                                <option value=\"汕头市\">汕头市</option>\n" +
                                                "                                <option value=\"潮州市\">潮州市</option>\n" +
                                                "                                <option value=\"清远市\">清远市</option>\n" +
                                                "                                <option value=\"肇庆市\">肇庆市</option>\n" +
                                                "                                <option value=\"云浮市\">云浮市</option>\n" +
                                                "                                <option value=\"江门市\">江门市</option>\n" +
                                                "                                <option value=\"阳江市\">阳江市</option>\n" +
                                                "                                <option value=\"茂名市\">茂名市</option>\n" +
                                                "                                <option value=\"湛江市\">湛江市</option>"
                                        %>
                                    </c:if>
                                    <c:if test="${company.id != null}">
                                        <option value="${company.city}">${company.city}</option>
                                    </c:if>
                                </select>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="city-linkage">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>所在区/县：</label>
                                <select class="form-control" id="county1" name="company.county"
                                <c:if test="${company.id != null}">disabled="disabled"</c:if>
                                >
                                    <c:if test="${company.id != null}">
                                        <option value="${company.county}">${company.county}</option>
                                    </c:if>
                                    <c:if test="${company.id == null}">
                                        <option>所在区/县</option>
                                    </c:if>
                                </select>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                                <label><span class="font-x">*</span>通讯地址：</label><input type="text"
                                                                                    class="form-control"
                                                                                    name="company.address"
                                                                                    value="${company.address}"
                                                                                    <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        />
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label>邮政编码：</label><input type="text" class="form-control" name="company.zipCode" placeholder="6位邮编"
                                                       value="${company.zipCode}"
                                                       <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        />
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <label>单位网址：</label><input type="text" placeholder=""
                                                       class="form-control" name="company.internetSite"
                                                       value="${company.internetSite}"
                                                       <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        >
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>电子邮箱：</label><input type="text"
                                                                                    placeholder=""
                                                                                    class="form-control"
                                                                                    name="company.email"
                                                                                    value="${company.email}"
                                                                                    <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        >
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label>传真：</label><input type="text" placeholder="区号-号码"
                                                     class="form-control" name="company.faxNum" value="${company.faxNum}"
                                                     <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        >
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                    </div>
                </div>

                <div class="comEmp clearfix">
                    <c:choose><c:when test="${company.id != null}"><div class="legalRep"></c:when><c:otherwise><div></c:otherwise></c:choose>
                        <div class="my-row clearfix">
                            <div class="form-sm-field">
                                <div class="clearfix">
                                    <label><span class="font-x">*</span>法人代表：</label><input
                                        type="text"
                                        class="form-control" name="legalRep.name" value="${company.id}"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>职务：</label><input
                                    type="text"
                                    class="form-control" name="legalRep.jobPosition" value="${company.id}"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label>办公电话：</label><input type="text" placeholder="区号-号码"
                                                           class="form-control" name="legalRep.officePhoneNum" value="${company.id}"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>手机：</label><input
                                    type="text"
                                    class="form-control" name="legalRep.cellPhoneNum" value="${company.id}"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                        </div>
                    </div>
                        <div class="my-row clearfix">
                            <div class="form-sm-field set-fs">
                                <label>担任协会职务人选：</label><input type="text" id="f1-introduced1"
                                                               class="form-control" name="introduceds[0].name"/>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                    <label><span class="font-x">*</span>职务：</label><input
                                        type="text"
                                        class="form-control" name="introduceds[0].jobPosition"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label>办公电话：</label><input type="text" placeholder="区号-号码"
                                                           class="form-control" name="introduceds[0].officePhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>手机：</label><input
                                    type="text"
                                    class="form-control" name="introduceds[0].cellPhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                                <button type="button" id="f1-add">+</button>
                            </div>
                        </div>

                        <div class="my-row clearfix" id="f1-addIntr" style="display:none">
                            <div class="form-sm-field set-fs">
                                <label>担任协会职务人选：</label><input type="text" id="f1-introduced2"
                                                               class="form-control" name="introduceds[1].name"/>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                    <label><span class="font-x">*</span>职务：</label><input
                                        type="text"
                                        class="form-control" name="introduceds[1].jobPosition"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label>办公电话：</label><input type="text" placeholder="区号-号码"
                                                           class="form-control" name="introduceds[1].officePhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                    <label><span class="font-x">*</span>手机：</label><input
                                        type="text"
                                        class="form-control" name="introduceds[1].cellPhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                        </div>

                        <div class="my-row clearfix">
                            <div class="form-sm-field set-fs">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>指定联系人：</label><input
                                    type="text"
                                    class="form-control" name="designatedContact.name"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                    <label><span class="font-x">*</span>职务：</label><input
                                        type="text"
                                        class="form-control" name="designatedContact.jobPosition"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label>办公电话：</label><input type="text" placeholder="区号-号码"
                                                           class="form-control" name="designatedContact.officePhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>手机：</label><input
                                    type="text"
                                    class="form-control" name="designatedContact.cellPhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                        </div>
                        <c:choose><c:when test="${company.id != null}"><div class="legalRep"></c:when><c:otherwise><div></c:otherwise></c:choose>
                            <div class="my-row clearfix">
                                <div class="form-sm-field">
                                    <div class="clearfix">
                                    <label><span class="font-x">*</span>企业性质：</label><input type="text"
                                                                                            class="form-control"
                                                                                            name="company.nature"
                                                                                            value="${company.nature}"/>
                                    </div>
                                    <div class="error-info clearfix"></div>
                                </div>
                                <div class="form-sm-field">
                                    <div class="clearfix">
                                    <label><span class="font-x">*</span>所属行业：</label><input type="text"
                                                                                            class="form-control"
                                                                                            name="company.industry"
                                                                                            value="${company.industry}"/>
                                    </div>
                                    <div class="error-info clearfix"></div>
                                </div>
                                <div class="form-sm-field">
                                    <div class="clearfix">
                                    <label><span class="font-x">*</span>主管部门：</label><input type="text"
                                                                                            class="form-control"
                                                                                            name="company.adminDepartment"
                                                                                            value="${company.adminDepartment}"/>
                                    </div>
                                    <div class="error-info clearfix"></div>
                                </div>
                                <div class="form-sm-field">
                                    <div class="clearfix">
                                    <label><span class="font-x">*</span>员工人数：</label><input type="text"
                                                                                            class="form-control"
                                                                                            name="company.employeeNum"
                                                                                            value="${company.employeeNum}"/>
                                    </div>
                                    <div class="error-info clearfix"></div>
                                </div>
                            </div>
                </div>
                        </div>
                <div class="desc clearfix">
                    <c:choose><c:when test="${company.id != null}"><div class="legalRep"></c:when><c:otherwise><div></c:otherwise></c:choose>
                        <label>科研成果：</label>
                        <textarea class="form-control ta-lag" rows="5" name="description.description1"
                                  <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        ></textarea>
                    </div>
                        <c:choose><c:when test="${company.id != null}"><div class="legalRep"></c:when><c:otherwise><div></c:otherwise></c:choose>
                        <label>表彰和处分：</label>
                        <textarea class="form-control ta-lag" rows="5" name="description.description2"
                                  <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        ></textarea>
                    </div>
                </div>

                <div class="readed">
                    <span><input type="checkbox" name="f1-agg"/></span>我已阅读并同意如下要求：<span style="color: red"></span><br/> 本单位研究协会章程后，决定承认并承诺遵守<a data-toggle="modal"
                                                                                     data-target="#myModal">《广东省价格和产业发展协会章程》</a>,自愿申请加入广东省价格协会电力价格分会（
                    <span><input type="radio" name="associationUnitId" value="1"/>副会长单位 <input type="radio"
                                                                                         name="associationUnitId"
                                                                                         value="2"/>常务理事单位 <input
                        type="radio"
                        name="associationUnitId" value="3"/>理事单位
                    <input type="radio" name="associationUnitId" value="4"/>普通会员单位</span><span style="color: red"></span>），在享有会员权利的同时，承诺履行下列会员义务：一、遵守协会章程，执行协会的决议；二、积极参加协会及分会组织的各项活动；三、承办协会及分会委托的事项；四、按时缴纳会费；五、及时向协会及分会反映情况，提供信息。

                </div>

            </form>
            <button id="btn1" type="button" class="btn btn-primary r-btn">完成注册</button>
        </div>


        <div role="tabpanel" class="tab-pane" id="profile">
            <form id="form2" class="form-inline" action="${ctx}/register/register-user" method="post">
                <div class="hidden">
                    <input type="hidden" name="associationId" value="2">
                    <input type="hidden" name="token" value="${token}"/>
                </div>
                <div class="user clearfix">
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>帐号：</label><input type="text" id="f2-u-acc" placeholder="请输入4~16位的帐号"
                                                                                  class="form-control" name="user.account"/>
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>密码：</label><input
                                type="password"
                                placeholder="请输入8~16位密码"
                                class="form-control" name="user.password"  id="f2-password">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>确定密码：</label><input
                                type="password"
                                placeholder="请输入确定密码"
                                class="form-control" name="rePassword">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>申请人姓名：</label><input
                                type="text"
                                placeholder=""
                                class="form-control" name="user.realName">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>申请人身份证：</label><input type="text"
                                                                                      placeholder=""
                                                                                      class="form-control"
                                                                                      name="user.IDcard">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>申请人手机号码：</label><input
                                type="text"
                                placeholder=""
                                class="form-control" name="user.phoneNum">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>申请人联系邮箱：</label><input type="text"
                                                                                       placeholder=""
                                                                                       class="form-control"
                                                                                       name="user.email">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                    </div>
                </div>

                <div class="company clearfix">
                    <div class="hidden">
                        <input type="hidden" name="company.id" value="${company.id}">
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>单位名称：</label><input type="text"
                                                                                    class="form-control" name="company.name"
                                                                                    value="${company.name}" readonly="true"/>
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>单位性质：</label><input
                                type="text"
                                class="form-control" name="company.nature" value="${company.nature}"
                                <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        />
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field" style="height: 39px"></div>
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="city-linkage">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>所在城市：</label>
                                <select class="form-control" onchange="showCity(this,$('#county2'))" name="company.city"
                                        <c:if test="${company.id != null}">disabled="disabled"</c:if>
                                >
                                    <c:if test="${company.id == null}">
                                        <%=
                                        "<option>广东的城市</option>\n" +
                                                "                                <option value=\"广州市\">广州市</option>\n" +
                                                "                                <option value=\"佛山市\">佛山市</option>\n" +
                                                "                                <option value=\"中山市\">中山市</option>\n" +
                                                "                                <option value=\"东莞市\">东莞市</option>\n" +
                                                "                                <option value=\"深圳市\">深圳市</option>\n" +
                                                "                                <option value=\"珠海市\">珠海市</option>\n" +
                                                "                                <option value=\"韶关市\">韶关市</option>\n" +
                                                "                                <option value=\"河源市\">河源市</option>\n" +
                                                "                                <option value=\"梅州市\">梅州市</option>\n" +
                                                "                                <option value=\"惠州市\">惠州市</option>\n" +
                                                "                                <option value=\"汕尾市\">汕尾市</option>\n" +
                                                "                                <option value=\"揭阳市\">揭阳市</option>\n" +
                                                "                                <option value=\"汕头市\">汕头市</option>\n" +
                                                "                                <option value=\"潮州市\">潮州市</option>\n" +
                                                "                                <option value=\"清远市\">清远市</option>\n" +
                                                "                                <option value=\"肇庆市\">肇庆市</option>\n" +
                                                "                                <option value=\"云浮市\">云浮市</option>\n" +
                                                "                                <option value=\"江门市\">江门市</option>\n" +
                                                "                                <option value=\"阳江市\">阳江市</option>\n" +
                                                "                                <option value=\"茂名市\">茂名市</option>\n" +
                                                "                                <option value=\"湛江市\">湛江市</option>"
                                        %>
                                    </c:if>
                                    <c:if test="${company.id != null}">
                                        <option value="${company.city}">${company.city}</option>
                                    </c:if>
                                </select>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="city-linkage">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>所在区/县：</label>
                                <select id="county2" class="form-control" name="company.county"
                                        <c:if test="${company.id != null}">disabled="disabled"</c:if>
                                >
                                    <c:if test="${company.id != null}">
                                        <option value="${company.county}">${company.county}</option>
                                    </c:if>
                                    <c:if test="${company.id == null}">
                                        <option>所在区/县</option>
                                    </c:if>
                                </select>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>通讯地址：</label><input type="text"
                                                                                    class="form-control"
                                                                                    name="company.address"
                                                                                    value="${company.address}"
                                                                                    <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        />
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label>邮政编码：</label><input type="text" class="form-control" name="company.zipCode" placeholder="6位邮编"
                                                       value="${company.zipCode}"
                                                       <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        />
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>电子邮箱：</label><input type="text"
                                                                                    placeholder=""
                                                                                    class="form-control"
                                                                                    name="company.email"
                                                                                    value="${company.email}"
                                                                                    <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        >
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label>传真：</label><input type="text" placeholder="区号-号码"
                                                     class="form-control" name="company.faxNum" value="${company.faxNum}"
                                                     <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        >
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                    </div>
                </div>

                <div class="comEmp clearfix">
                    <c:choose><c:when test="${company.id != null}"><div class="legalRep"></c:when><c:otherwise><div></c:otherwise></c:choose>
                        <div class="my-row clearfix">
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>法人代表：</label><input
                                    type="text"
                                    class="form-control" name="legalRep.name" value="${company.id}"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>职务：</label><input
                                    type="text"
                                    class="form-control" name="legalRep.jobPosition" value="${company.id}" />
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label>办公电话：</label><input type="text" placeholder="区号-号码"
                                                           class="form-control" name="legalRep.officePhoneNum" value="${company.id}"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>手机：</label><input
                                    type="text"
                                    class="form-control" name="legalRep.cellPhoneNum" value="${company.id}"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                        </div>
                    </div>
                        <div class="my-row clearfix">
                            <div class="form-sm-field set-fs">
                                <label>担任协会职务人选：</label><input type="text"
                                                               class="form-control" name="introduceds[0].name" id="f2-introduced1"/>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix a-position a-position1">
                                    <div class="clearfix">
                                    <label><span class="font-x">*</span>职务：</label><input
                                        type="text"
                                        class="form-control" name="introduceds[0].jobPosition"/>
                                    </div>
                                    <div class="error-info clearfix"></div>
                                </div>
                                <div class="clearfix a-position a-position2">
                                    <div class="clearfix">
                                    <label class="set-fs" style="line-height: 1.0"><span class="font-x">*</span>协会/分会职务：</label><input
                                        type="text"
                                        class="form-control" name="introduceds[0].asctJobPosition"/>
                                    </div>
                                    <div class="error-info clearfix"></div>
                                </div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label>办公电话：</label><input type="text" placeholder="区号-号码"
                                                           class="form-control" name="introduceds[0].officePhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>手机：</label><input
                                    type="text"
                                    class="form-control" name="introduceds[0].cellPhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                                <button type="button" id="f2-add">+</button>
                            </div>
                        </div>

                        <div class="my-row clearfix" id="f2-addIntr" style="display:none">
                            <div class="form-sm-field set-fs">
                                <label>担任协会职务人选：</label><input type="text"
                                                               class="form-control" name="introduceds[1].name" id="f2-introduced2"/>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix a-position a-position1">
                                    <div class="clearfix">
                                        <label><span class="font-x">*</span>职务：</label><input
                                            type="text"
                                            class="form-control" name="introduceds[1].jobPosition"/>
                                    </div>
                                    <div class="error-info clearfix"></div>
                                </div>
                                <div class="clearfix a-position a-position2">
                                    <div class="clearfix">
                                        <label class="set-fs" style="line-height: 1.0"><span class="font-x">*</span>协会/分会职务：</label><input
                                            type="text"
                                            class="form-control" name="introduceds[1].asctJobPosition"/>
                                    </div>
                                    <div class="error-info clearfix"></div>
                                </div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label>办公电话：</label><input type="text" placeholder="区号-号码"
                                                           class="form-control" name="introduceds[1].officePhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                    <label><span class="font-x">*</span>手机：</label><input
                                        type="text"
                                        class="form-control" name="introduceds[1].cellPhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                        </div>

                        <div class="my-row clearfix">
                            <div class="form-sm-field set-fs">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>指定联系人：</label><input
                                    type="text"
                                    class="form-control" name="designatedContact.name"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>职务：</label><input
                                    type="text"
                                    class="form-control" name="designatedContact.jobPosition"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label>办公电话：</label><input type="text" placeholder="区号-号码"
                                                           class="form-control" name="designatedContact.officePhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>手机：</label><input
                                    type="text"
                                    class="form-control" name="designatedContact.cellPhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                        </div>

                </div>

                <div class="desc clearfix">
                    <c:choose><c:when test="${company.id != null}"><div class="legalRep"></c:when><c:otherwise><div></c:otherwise></c:choose>
                        <label>主营业务：</label>
                        <textarea class="form-control ta-lag" rows="5" name="description.description1"
                                  <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        ></textarea>
                    </div>
                        <c:choose><c:when test="${company.id != null}"><div class="legalRep"></c:when><c:otherwise><div></c:otherwise></c:choose>
                        <label>希望得到分会哪方面的服务和支持：</label>
                        <textarea class="form-control ta-lag" rows="5" name="description.description2"
                                  <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        ></textarea>
                    </div>
                </div>

                <div class="readed">
                    <span><input type="checkbox" name="f1-agg"/></span>我已阅读并同意如下要求：<span style="color: red"></span><br/> 本单位研究协会章程后，决定承认并承诺遵守<a data-toggle="modal"
                                                                                     data-target="#myModal">《广东省价格和产业发展协会章程》</a>,自愿申请加入广东省价格协会电力价格分会（
                    <span><input type="radio" name="associationUnitId" value="5"/>副会长单位 <input type="radio"
                                                                                         name="associationUnitId"
                                                                                         value="6"/>常务理事单位 <input
                        type="radio"
                        name="associationUnitId" value="7"/>理事单位
                    <input type="radio" name="associationUnitId" value="8"/>普通会员单位</span><span style="color: red"></span>），在享有会员权利的同时，承诺履行下列会员义务：一、遵守协会章程，执行协会的决议；二、积极参加协会及分会组织的各项活动；三、承办协会及分会委托的事项；四、按时缴纳会费；五、及时向协会及分会反映情况，提供信息。

                </div>

            </form>
            <button id="btn2" type="button" class="btn btn-primary r-btn">完成注册</button>
        </div>


        <div role="tabpanel" class="tab-pane" id="messages">
            <form id="form3" class="form-inline" action="${ctx}/register/register-user" method="post">
                <div class="hidden">
                    <%--第一个标签页的协会是1--%>
                    <input type="hidden" name="associationId" value="3">
                        <input type="hidden" name="token" value="${token}"/>
                </div>
                <div class="user clearfix">
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>帐号：</label><input type="text" id="f3-u-acc" placeholder="请输入4~16位的帐号"
                                                                                  class="form-control" name="user.account"/>
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>密码：</label><input
                                type="password"
                                placeholder="请输入8~16位密码"
                                class="form-control" name="user.password" id="f3-password">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>确定密码：</label><input
                                type="password"
                                placeholder="请输入确定密码"
                                class="form-control" name="rePassword">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>申请人姓名：</label><input
                                type="text"
                                placeholder=""
                                class="form-control" name="user.realName">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>申请人身份证：</label><input type="text"
                                                                                      placeholder=""
                                                                                      class="form-control"
                                                                                      name="user.IDcard">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>申请人手机号码：</label><input
                                type="text"
                                placeholder=""
                                class="form-control" name="user.phoneNum">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>申请人联系邮箱：</label><input type="text"
                                                                                       placeholder=""
                                                                                       class="form-control"
                                                                                       name="user.email">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                    </div>
                </div>

                <div class="company clearfix">
                    <div class="hidden">
                        <input type="hidden" name="company.id" value="${company.id}">
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>单位名称：</label><input type="text"
                                                                                    class="form-control" name="company.name"
                                                                                    value="${company.name}" readonly="true"/>
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field" style="height: 39px"></div>
                        <div class="form-field" style="height: 39px"></div>
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="city-linkage">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>所在城市：</label>
                                <select class="form-control" onchange="showCity(this,$('#county3'))" name="company.city"
                                        <c:if test="${company.id != null}">disabled="disabled"</c:if>
                                >
                                    <c:if test="${company.id == null}">
                                        <%=
                                        "<option>广东的城市</option>\n" +
                                                "                                <option value=\"广州市\">广州市</option>\n" +
                                                "                                <option value=\"佛山市\">佛山市</option>\n" +
                                                "                                <option value=\"中山市\">中山市</option>\n" +
                                                "                                <option value=\"东莞市\">东莞市</option>\n" +
                                                "                                <option value=\"深圳市\">深圳市</option>\n" +
                                                "                                <option value=\"珠海市\">珠海市</option>\n" +
                                                "                                <option value=\"韶关市\">韶关市</option>\n" +
                                                "                                <option value=\"河源市\">河源市</option>\n" +
                                                "                                <option value=\"梅州市\">梅州市</option>\n" +
                                                "                                <option value=\"惠州市\">惠州市</option>\n" +
                                                "                                <option value=\"汕尾市\">汕尾市</option>\n" +
                                                "                                <option value=\"揭阳市\">揭阳市</option>\n" +
                                                "                                <option value=\"汕头市\">汕头市</option>\n" +
                                                "                                <option value=\"潮州市\">潮州市</option>\n" +
                                                "                                <option value=\"清远市\">清远市</option>\n" +
                                                "                                <option value=\"肇庆市\">肇庆市</option>\n" +
                                                "                                <option value=\"云浮市\">云浮市</option>\n" +
                                                "                                <option value=\"江门市\">江门市</option>\n" +
                                                "                                <option value=\"阳江市\">阳江市</option>\n" +
                                                "                                <option value=\"茂名市\">茂名市</option>\n" +
                                                "                                <option value=\"湛江市\">湛江市</option>"
                                        %>
                                    </c:if>
                                    <c:if test="${company.id != null}">
                                        <option value="${company.city}">${company.city}</option>
                                    </c:if>
                                </select>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="city-linkage">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>所在区/县：</label>
                                <select id="county3" class="form-control" name="company.county"
                                        <c:if test="${company.id != null}">disabled="disabled"</c:if>
                                >
                                    <c:if test="${company.id != null}">
                                        <option value="${company.county}">${company.county}</option>
                                    </c:if>
                                    <c:if test="${company.id == null}">
                                        <option>所在区/县</option>
                                    </c:if>
                                </select>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>通讯地址：</label><input type="text"
                                                                                    class="form-control"
                                                                                    name="company.address"
                                                                                    value="${company.address}"
                                                                                    <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        />
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label>邮政编码：</label><input type="text" class="form-control" name="company.zipCode" placeholder="6位邮编"
                                                       value="${company.zipCode}"
                                                       <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        />
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="clearfix">
                        <label><span class="font-x">*</span>电子邮箱：</label><input type="text"
                                                                                placeholder=""
                                                                                class="form-control"
                                                                                name="company.email"
                                                                                value="${company.email}"
                                                                                <c:if test="${company.id != null}">disabled="disabled"</c:if>
                    >
                            </div>
                            <div class="error-info clearfix"></div>
                    </div>
                    </div>
                </div>

                <div class="comEmp clearfix">
                    <c:choose><c:when test="${company.id != null}"><div class="legalRep"></c:when><c:otherwise><div></c:otherwise></c:choose>
                        <div class="my-row clearfix">
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label style="line-height: 1.0"><span class="font-x">*</span>法人代表：</label><input
                                    type="text"
                                    class="form-control" name="legalRep.name" value="${company.id}"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>职务：</label><input
                                    type="text"
                                    class="form-control" name="legalRep.jobPosition" value="${company.id}"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label>电话：</label><input type="text" placeholder="区号-号码"
                                                         class="form-control" name="legalRep.officePhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>手机：</label><input
                                    type="text"
                                    class="form-control" name="legalRep.cellPhoneNum" value="${company.id}"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>传真：</label><input
                                    type="text" placeholder="区号-号码"
                                    class="form-control" name="legalRep.faxNum" value="${company.id}"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>邮箱：</label><input
                                    type="text"
                                    class="form-control" name="legalRep.email" value="${company.id}"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                        </div>
                    </div>

                        <div class="my-row clearfix">
                            <div class="form-xm-field set-fs">
                                <div class="clearfix">
                                <label style="line-height: 1.0"><span class="font-x">*</span>指定联系人：</label><input
                                    type="text"
                                    class="form-control" name="designatedContact.name"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>职务：</label><input
                                    type="text"
                                    class="form-control" name="designatedContact.jobPosition"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label>电话：</label><input type="text" placeholder="区号-号码"
                                                         class="form-control" name="designatedContact.officePhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>手机：</label><input
                                    type="text"
                                    class="form-control" name="designatedContact.cellPhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>传真：</label><input
                                    type="text" placeholder="区号-号码"
                                    class="form-control" name="designatedContact.faxNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>邮箱：</label><input
                                    type="text"
                                    class="form-control" name="designatedContact.email"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                        </div>
                </div>

                <div class="desc clearfix">
                    <p>备注：广东省价格和产业发展协会燃气价格分会的会员是广东省价格和产业发展协会的当然会员。</p>
                    <c:choose><c:when test="${company.id != null}"><div class="legalRep"></c:when><c:otherwise><div></c:otherwise></c:choose>
                        <label>对广东省价格和产业发展协会燃气价格分会的建议：</label>
                        <textarea class="form-control ta-lag" rows="5" name="description.description1"
                                  <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        ></textarea>
                    </div>
                </div>

                <div class="readed">
                    <span><input type="checkbox" name="f1-agg"/></span>我已阅读并同意如下要求：<span style="color:red;"></span><br/> 本单位研究协会章程后，决定承认并承诺遵守<a data-toggle="modal"
                                                                                     data-target="#myModal">《广东省价格和产业发展协会章程》</a>,自愿申请加入广东省价格协会电力价格分会（
                    <span><input type="radio" name="associationUnitId" value="9"/>副会长单位 <input type="radio"
                                                                                         name="associationUnitId"
                                                                                         value="10"/>常务理事单位 <input
                        type="radio"
                        name="associationUnitId" value="11"/>理事单位
                    <input type="radio" name="associationUnitId" value="12"/>普通会员单位</span><span style="color: red"></span>），在享有会员权利的同时，承诺履行下列会员义务：一、遵守协会章程，执行协会的决议；二、积极参加协会及分会组织的各项活动；三、承办协会及分会委托的事项；四、按时缴纳会费；五、及时向协会及分会反映情况，提供信息。

                </div>

            </form>
            <button id="btn3" type="button" class="btn btn-primary r-btn">完成注册</button>
        </div>

        <div role="tabpanel" class="tab-pane" id="setting">
            <form id="form4" class="form-inline" action="${ctx}/register/register-user" method="post">
                <div class="hidden">
                    <%--第一个标签页的协会是1--%>
                    <input type="hidden" name="associationId" value="4">
                        <input type="hidden" name="token" value="${token}"/>
                </div>
                <div class="user clearfix">
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>帐号：</label><input type="text" id="f4-u-acc" placeholder="请输入4~16位的帐号"
                                                                                  class="form-control" name="user.account"/>
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>密码：</label><input
                                type="password"
                                placeholder="请输入8~16位密码"
                                class="form-control" name="user.password" id="f4-password">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>确定密码：</label><input
                                type="password"
                                placeholder="请输入确定密码"
                                class="form-control" name="rePassword">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>申请人姓名：</label><input
                                type="text"
                                placeholder=""
                                class="form-control" name="user.realName">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>申请人身份证：</label><input type="text"
                                                                                      placeholder=""
                                                                                      class="form-control"
                                                                                      name="user.IDcard">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>申请人手机号码：</label><input
                                type="text"
                                placeholder=""
                                class="form-control" name="user.phoneNum">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                    </div>
                    <div class="my-row clearfix">
                    <div class="form-field">
                        <div class="clearfix">
                        <label><span class="font-x">*</span>申请人联系邮箱：</label><input type="text"
                                                                                   placeholder=""
                                                                                   class="form-control"
                                                                                   name="user.email">
                        </div>
                        <div class="error-info clearfix"></div>
                    </div>
                    </div>
                </div>

                <div class="company clearfix">
                    <div class="hidden">
                        <input type="hidden" name="company.id" value="${company.id}">
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>单位名称：</label><input type="text"
                                                                                    class="form-control" name="company.name"
                                                                                    value="${company.name}" readonly="true"/>
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field" style="height: 39px"></div>
                        <div class="form-field" style="height: 39px"></div>
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="city-linkage">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>所在城市：</label>
                                <select class="form-control" onchange="showCity(this,$('#county4'))" name="company.city"
                                        <c:if test="${company.id != null}">disabled="disabled"</c:if>
                                >
                                    <c:if test="${company.id == null}">
                                        <%=
                                        "<option>广东的城市</option>\n" +
                                                "                                <option value=\"广州市\">广州市</option>\n" +
                                                "                                <option value=\"佛山市\">佛山市</option>\n" +
                                                "                                <option value=\"中山市\">中山市</option>\n" +
                                                "                                <option value=\"东莞市\">东莞市</option>\n" +
                                                "                                <option value=\"深圳市\">深圳市</option>\n" +
                                                "                                <option value=\"珠海市\">珠海市</option>\n" +
                                                "                                <option value=\"韶关市\">韶关市</option>\n" +
                                                "                                <option value=\"河源市\">河源市</option>\n" +
                                                "                                <option value=\"梅州市\">梅州市</option>\n" +
                                                "                                <option value=\"惠州市\">惠州市</option>\n" +
                                                "                                <option value=\"汕尾市\">汕尾市</option>\n" +
                                                "                                <option value=\"揭阳市\">揭阳市</option>\n" +
                                                "                                <option value=\"汕头市\">汕头市</option>\n" +
                                                "                                <option value=\"潮州市\">潮州市</option>\n" +
                                                "                                <option value=\"清远市\">清远市</option>\n" +
                                                "                                <option value=\"肇庆市\">肇庆市</option>\n" +
                                                "                                <option value=\"云浮市\">云浮市</option>\n" +
                                                "                                <option value=\"江门市\">江门市</option>\n" +
                                                "                                <option value=\"阳江市\">阳江市</option>\n" +
                                                "                                <option value=\"茂名市\">茂名市</option>\n" +
                                                "                                <option value=\"湛江市\">湛江市</option>"
                                        %>
                                    </c:if>
                                    <c:if test="${company.id != null}">
                                        <option value="${company.city}">${company.city}</option>
                                    </c:if>
                                </select>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="city-linkage">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>所在区/县：</label>
                                <select id="county4" class="form-control" name="company.county"
                                        <c:if test="${company.id != null}">disabled="disabled"</c:if>
                                >
                                    <c:if test="${company.id != null}">
                                        <option value="${company.county}">${company.county}</option>
                                    </c:if>
                                    <c:if test="${company.id == null}">
                                        <option>所在区/县</option>
                                    </c:if>
                                </select>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>通讯地址：</label><input type="text"
                                                                                    class="form-control"
                                                                                    name="company.address"
                                                                                    value="${company.address}"
                                                                                    <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        />
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label>邮政编码：</label><input type="text" class="form-control" name="company.zipCode" placeholder="6位邮编"
                                                       value="${company.zipCode}"
                                                       <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        />
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="clearfix">
                        <label><span class="font-x">*</span>电子邮箱：</label><input type="text"
                                                                                placeholder=""
                                                                                class="form-control"
                                                                                name="company.email"
                                                                                value="${company.email}"
                                                                                <c:if test="${company.id != null}">disabled="disabled"</c:if>
                    >
                            </div>
                            <div class="error-info clearfix"></div>
                    </div>
                    </div>
                </div>

                <div class="comEmp clearfix">
                    <c:choose><c:when test="${company.id != null}"><div class="legalRep"></c:when><c:otherwise><div></c:otherwise></c:choose>
                        <div class="my-row clearfix">
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label style="line-height: 1.0"><span class="font-x">*</span>法人代表：</label><input
                                    type="text"
                                    class="form-control" name="legalRep.name" value="${company.id}"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>职务：</label><input
                                    type="text"
                                    class="form-control" name="legalRep.jobPosition" value="${company.id}"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label>电话：</label><input type="text" placeholder="区号-号码"
                                                         class="form-control" name="legalRep.officePhoneNum" value="${company.id}"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>手机：</label><input
                                    type="text"
                                    class="form-control" name="legalRep.cellPhoneNum" value="${company.id}"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>传真：</label><input
                                    type="text" placeholder="区号-号码"
                                    class="form-control" name="legalRep.faxNum" value="${company.id}"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>邮箱：</label><input
                                    type="text"
                                    class="form-control" name="legalRep.email" value="${company.id}"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                        </div>
                        </div>

                        <div class="my-row clearfix">
                            <div class="form-xm-field set-fs">
                                <div class="clearfix">
                                <label style="line-height: 1.0"><span class="font-x">*</span>指定联系人：</label><input
                                    type="text"
                                    class="form-control" name="designatedContact.name"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>职务：</label><input
                                    type="text"
                                    class="form-control" name="designatedContact.jobPosition"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label>电话：</label><input type="text" placeholder="区号-号码"
                                                         class="form-control" name="designatedContact.officePhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>手机：</label><input
                                    type="text"
                                    class="form-control" name="designatedContact.cellPhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>传真：</label><input
                                    type="text" placeholder="区号-号码"
                                    class="form-control" name="designatedContact.faxNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>邮箱：</label><input
                                    type="text"
                                    class="form-control" name="designatedContact.email"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                        </div>

                        <div class="my-row clearfix">
                            <div class="form-xm-field">
                                <label style="line-height: 1.0">单位代表：</label><input
                                    type="text"
                                    class="form-control" name="cpnyAcstRep.name"/>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>职务：</label><input
                                    type="text"
                                    class="form-control" name="cpnyAcstRep.jobPosition"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label>电话：</label><input type="text" placeholder="区号-号码"
                                                         class="form-control" name="cpnyAcstRep.officePhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>手机：</label><input
                                    type="text"
                                    class="form-control" name="cpnyAcstRep.cellPhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>传真：</label><input
                                    type="text" placeholder="区号-号码"
                                    class="form-control" name="cpnyAcstRep.faxNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-xm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>邮箱：</label><input
                                    type="text"
                                    class="form-control" name="cpnyAcstRep.email"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                        </div>
                    </div>

                    <div class="desc clearfix">
                        <p>备注：广东省价格和产业发展协会燃气价格分会的会员是广东省价格和产业发展协会的当然会员。</p>
                        <c:choose><c:when test="${company.id != null}"><div class="legalRep"></c:when><c:otherwise><div></c:otherwise></c:choose>
                            <label>对广东省价格和产业发展协会燃气价格分会的建议：</label>
                            <textarea class="form-control ta-lag" rows="5" name="description.description1"
                                      <c:if test="${company.id != null}">disabled="disabled"</c:if>
                            ></textarea>
                        </div>
                    </div>

                    <div class="readed">
                        <span><input type="checkbox" name="f1-agg"/></span>我已阅读并同意如下要求：<span style="color:red;"></span><br/> 本单位研究协会章程后，决定承认并承诺遵守<a data-toggle="modal"
                                                                                         data-target="#myModal">《广东省价格和产业发展协会章程》</a>,自愿申请加入广东省价格协会电力价格分会（
                        <span><input type="radio" name="associationUnitId" value="13"/>副会长单位 <input type="radio"
                                                                                              name="associationUnitId"
                                                                                              value="14"/>常务理事单位 <input
                            type="radio"
                            name="associationUnitId" value="15"/>理事单位
                        <input type="radio" name="associationUnitId" value="16"/>普通会员单位</span><span style="color: red"></span>），在享有会员权利的同时，承诺履行下列会员义务：一、遵守协会章程，执行协会的决议；二、积极参加协会及分会组织的各项活动；三、承办协会及分会委托的事项；四、按时缴纳会费；五、及时向协会及分会反映情况，提供信息。

                    </div>

            </form>
            <button id="btn4" type="button" class="btn btn-primary r-btn">完成注册</button>
        </div>

        <div role="tabpanel" class="tab-pane" id="settings">
            <form id="form5" class="form-inline" action="${ctx}/register/register-user" method="post">
                <div class="hidden">
                    <%--第一个标签页的协会是1--%>
                    <input type="hidden" name="associationId" value="5">
                        <input type="hidden" name="token" value="${token}"/>
                </div>
                <div class="user clearfix">
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>帐号：</label><input type="text" id="f5-u-acc" placeholder="请输入4~16位的帐号"
                                                                                  class="form-control" name="user.account"/>
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>密码：</label><input
                                type="password" id="f5-password"
                                placeholder="请输入8~16位密码"
                                class="form-control" name="user.password">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>确定密码：</label><input
                                type="password"
                                placeholder="请输入确定密码"
                                class="form-control" name="rePassword">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>申请人姓名：</label><input
                                type="text"
                                placeholder=""
                                class="form-control" name="user.realName">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>申请人身份证：</label><input type="text"
                                                                                      placeholder=""
                                                                                      class="form-control"
                                                                                      name="user.IDcard">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>申请人手机号码：</label><input
                                type="text"
                                placeholder=""
                                class="form-control" name="user.phoneNum">
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="my-row clearfix">
                            <div class="form-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>申请人联系邮箱：</label><input type="text"
                                                                                           placeholder=""
                                                                                           class="form-control"
                                                                                           name="user.email">
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="company clearfix">
                    <div class="hidden">
                        <input type="hidden" name="company.id" value="${company.id}">
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>单位名称：</label><input type="text"
                                                                                    class="form-control" name="company.name"
                                                                                    value="${company.name}" readonly="true"/>
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>企业注册类型：</label><input
                                type="text"
                                class="form-control" name="company.registerNature" value="${company.registerNature}"
                                <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        />
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field" style="height: 39px"></div>
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <div class="city-linkage">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>所在城市：</label>
                                <select class="form-control" onchange="showCity(this,$('#county5'))" name="company.city"
                                        <c:if test="${company.id != null}">disabled="disabled"</c:if>
                                >
                                    <c:if test="${company.id == null}">
                                        <%=
                                        "<option>广东的城市</option>\n" +
                                                "                                <option value=\"广州市\">广州市</option>\n" +
                                                "                                <option value=\"佛山市\">佛山市</option>\n" +
                                                "                                <option value=\"中山市\">中山市</option>\n" +
                                                "                                <option value=\"东莞市\">东莞市</option>\n" +
                                                "                                <option value=\"深圳市\">深圳市</option>\n" +
                                                "                                <option value=\"珠海市\">珠海市</option>\n" +
                                                "                                <option value=\"韶关市\">韶关市</option>\n" +
                                                "                                <option value=\"河源市\">河源市</option>\n" +
                                                "                                <option value=\"梅州市\">梅州市</option>\n" +
                                                "                                <option value=\"惠州市\">惠州市</option>\n" +
                                                "                                <option value=\"汕尾市\">汕尾市</option>\n" +
                                                "                                <option value=\"揭阳市\">揭阳市</option>\n" +
                                                "                                <option value=\"汕头市\">汕头市</option>\n" +
                                                "                                <option value=\"潮州市\">潮州市</option>\n" +
                                                "                                <option value=\"清远市\">清远市</option>\n" +
                                                "                                <option value=\"肇庆市\">肇庆市</option>\n" +
                                                "                                <option value=\"云浮市\">云浮市</option>\n" +
                                                "                                <option value=\"江门市\">江门市</option>\n" +
                                                "                                <option value=\"阳江市\">阳江市</option>\n" +
                                                "                                <option value=\"茂名市\">茂名市</option>\n" +
                                                "                                <option value=\"湛江市\">湛江市</option>"
                                        %>
                                    </c:if>
                                    <c:if test="${company.id != null}">
                                        <option value="${company.city}">${company.city}</option>
                                    </c:if>
                                </select>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="city-linkage">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>所在区/县：</label>
                                <select id="county5" class="form-control" name="company.county"
                                        <c:if test="${company.id != null}">disabled="disabled"</c:if>
                                >
                                    <c:if test="${company.id != null}">
                                        <option value="${company.county}">${company.county}</option>
                                    </c:if>
                                    <c:if test="${company.id == null}">
                                        <option>所在区/县</option>
                                    </c:if>
                                </select>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>通讯地址：</label><input type="text"
                                                                                    class="form-control"
                                                                                    name="company.address"
                                                                                    value="${company.address}"
                                                                                    <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        />
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label>邮政编码：</label><input type="text" class="form-control" name="company.zipCode" placeholder="6位邮编"
                                                       value="${company.zipCode}"
                                                       <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        />
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                    </div>
                    <div class="my-row clearfix">
                        <div class="form-field">
                            <label>单位网址：</label><input type="text" placeholder=""
                                                       class="form-control" name="company.internetSite"
                                                       value="${company.internetSite}"
                                                       <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        >
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label><span class="font-x">*</span>电子邮箱：</label><input type="text"
                                                                                    placeholder=""
                                                                                    class="form-control"
                                                                                    name="company.email"
                                                                                    value="${company.email}"
                                                                                    <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        >
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                        <div class="form-field">
                            <div class="clearfix">
                            <label>传真：</label><input type="text" placeholder="区号-号码"
                                                     class="form-control" name="company.faxNum" value="${company.faxNum}"
                                                     <c:if test="${company.id != null}">disabled="disabled"</c:if>
                        >
                            </div>
                            <div class="error-info clearfix"></div>
                        </div>
                    </div>
                </div>

                <div class="comEmp clearfix">
                    <c:choose><c:when test="${company.id != null}"><div class="legalRep"></c:when><c:otherwise><div></c:otherwise></c:choose>
                        <div class="my-row clearfix">
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>法人代表：</label><input
                                    type="text"
                                    class="form-control" name="legalRep.name" value="${company.id}"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>职务：</label><input
                                    type="text"
                                    class="form-control" name="legalRep.jobPosition" value="${company.id}"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label>办公电话：</label><input type="text" placeholder="区号-号码"
                                                           class="form-control" name="legalRep.officePhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>手机：</label><input
                                    type="text"
                                    class="form-control" name="legalRep.cellPhoneNum" value="${company.id}"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                        </div>
                        </div>

                        <div class="my-row clearfix">
                            <div class="form-sm-field set-fs">
                                <label>担任协会职务人选：</label><input type="text" id="f5-introduced1"
                                                               class="form-control" name="introduceds[0].name"/>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>职务：</label><input
                                    type="text"
                                    class="form-control" name="introduceds[0].jobPosition"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label>办公电话：</label><input type="text" placeholder="区号-号码"
                                                           class="form-control" name="introduceds[0].officePhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>手机：</label><input
                                    type="text"
                                    class="form-control" name="introduceds[0].cellPhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                                <button type="button" id="f5-add">+</button>
                            </div>
                        </div>

                        <div class="my-row clearfix"  id="f5-addIntr" style="display:none">
                            <div class="form-sm-field set-fs">
                                <label>担任协会职务人选：</label><input type="text" id="f5-introduced2"
                                                               class="form-control" name="introduceds[1].name"/>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                    <label><span class="font-x">*</span>职务：</label><input
                                        type="text"
                                        class="form-control" name="introduceds[1].jobPosition"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label>办公电话：</label><input type="text" placeholder="区号-号码"
                                                           class="form-control" name="introduceds[1].officePhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                    <label><span class="font-x">*</span>手机：</label><input
                                        type="text"
                                        class="form-control" name="introduceds[1].cellPhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                        </div>


                        <div class="my-row clearfix">
                            <div class="form-sm-field set-fs">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>指定联系人：</label><input
                                    type="text"
                                    class="form-control" name="designatedContact.name"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>职务：</label><input
                                    type="text"
                                    class="form-control" name="designatedContact.jobPosition"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label>办公电话：</label><input type="text" placeholder="区号-号码"
                                                           class="form-control" name="designatedContact.officePhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                            <div class="form-sm-field">
                                <div class="clearfix">
                                <label><span class="font-x">*</span>手机：</label><input
                                    type="text"
                                    class="form-control" name="designatedContact.cellPhoneNum"/>
                                </div>
                                <div class="error-info clearfix"></div>
                            </div>
                        </div>

                            <c:choose><c:when test="${company.id != null}"><div class="legalRep"></c:when><c:otherwise><div></c:otherwise></c:choose>
                            <div class="my-row clearfix">
                                <div class="form-sm-field">
                                    <div class="clearfix">
                                <label><span class="font-x">*</span>企业性质：</label><input type="text"
                                                                                        class="form-control"
                                                                                        name="company.nature"
                                                                                        value="${company.nature}"/>
                                    </div>
                                    <div class="error-info clearfix"></div>
                            </div>
                                <div class="form-sm-field">
                                    <div class="clearfix">
                                    <label><span class="font-x">*</span>所属行业：</label><input type="text"
                                                                                            class="form-control"
                                                                                            name="company.industry"
                                                                                            value="${company.industry}"/>
                                    </div>
                                    <div class="error-info clearfix"></div>
                                </div>
                                <div class="form-sm-field">
                                    <div class="clearfix">
                                    <label><span class="font-x">*</span>主管部门：</label><input type="text"
                                                                                            class="form-control"
                                                                                            name="company.adminDepartment"
                                                                                            value="${company.adminDepartment}"/>
                                    </div>
                                    <div class="error-info clearfix"></div>
                                </div>
                                <div class="form-sm-field">
                                    <div class="clearfix">
                                    <label><span class="font-x">*</span>员工人数：</label><input type="text"
                                                                                            class="form-control"
                                                                                            name="company.employeeNum"
                                                                                            value="${company.employeeNum}"/>
                                    </div>
                                    <div class="error-info clearfix"></div>
                                </div>
                            </div>
                    </div>
                        </div>

                    <div class="desc clearfix">
                        <c:choose><c:when test="${company.id != null}"><div class="legalRep"></c:when><c:otherwise><div></c:otherwise></c:choose>
                            <label>科研成果：</label>
                            <textarea class="form-control ta-lag" rows="5" name="description.description1"
                                      <c:if test="${company.id != null}">disabled="disabled"</c:if>
                            ></textarea>
                        </div>
                            <c:choose><c:when test="${company.id != null}"><div class="legalRep"></c:when><c:otherwise><div></c:otherwise></c:choose>
                            <label>表彰和处分：</label>
                            <textarea class="form-control ta-lag" rows="5" name="description.description2"
                                      <c:if test="${company.id != null}">disabled="disabled"</c:if>
                            ></textarea>
                        </div>
                    </div>

                    <div class="readed">
                        <span><input type="checkbox" name="f1-agg"/></span>我已阅读并同意如下要求：<span style="color:red;"></span><br/> 本单位研究协会章程后，决定承认并承诺遵守<a data-toggle="modal"
                                                                                         data-target="#myModal">《广东省价格和产业发展协会章程》</a>,自愿申请加入广东省价格协会电力价格分会（
                        <span><input type="radio" name="associationUnitId" value="17"/>副会长单位 <input type="radio"
                                                                                              name="associationUnitId"
                                                                                              value="18"/>常务理事单位 <input
                            type="radio"
                            name="associationUnitId" value="19"/>理事单位
                        <input type="radio" name="associationUnitId" value="20"/>普通会员单位</span><span style="color:red;"></span>），在享有会员权利的同时，承诺履行下列会员义务：一、遵守协会章程，执行协会的决议；二、积极参加协会及分会组织的各项活动；三、承办协会及分会委托的事项；四、按时缴纳会费；五、及时向协会及分会反映情况，提供信息。

                    </div>

            </form>
            <button id="btn5" type="button" class="btn btn-primary r-btn">完成注册</button>
        </div>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h2 class="modal-title" id="myModalLabel">广东省价格协会章程</h2>
            </div>
            <div class="modal-body" style="line-height: 1.5">
                <p>
                    新中国从1949年到2003年走过了54年辉煌历程，在中国共产党的领导下，全国各族人民艰苦奋斗，奋发图强，不断探索适合中国国情的社会主义发展道路，努力克服前进中的艰难险阻，取得了综合国力明显增强、社会商品极大丰富，基础设施明显改善，人民生活总体水平显著提高，对外经济日益活跃，各项社会事业全面发展的辉煌成就。我们已经把一个半殖民地、半封建的贫穷落后、深受外国列强欺凌的旧中国改造建设成了屹立在世界东方、初步繁荣昌盛的社会主义新中国，初步实现了国家富强、人民安居乐业的百年来的梦想。
                    纵向比较，新中国比旧中国国力大大增强，今非昔比
                    国内生产总值从1949年408亿元增长到1978年3624.1亿元和2002年102398亿元，扣除物价因素，增长65.5倍，年平均增长8.3%，
                    其中改革前后分别年增长7.4%和9.35%；按当年汇率计算，GDP从1949年194.3亿美元增长到1978年2157亿美元和
                    2002年12371亿美元；将2002年与旧中国最高年（一般指1936年）相比较，比旧中国75.8亿美元高出162.2倍；据测算，
                    2002年一天创造GDP达280.5亿元，合33.89亿美元，相当于1949年全年GDP408亿元的68.8%，相当于1936年全年
                    GNP75.8亿美元的44.7%；一天产量超过1949年全年产量的还有钢（49.7 15.8万吨）、水泥（198.6 66万吨）、
                    硫酸（8.36 4万吨）、生铁（46.8 25万吨），原油一天产量45.8万吨，比1949年全年产量12万吨增长2.82倍，
                    比1943年全年产量32万吨增长43.1%等。2002年一年的固定资产投资为43201.6亿元，为改革开放前29年投资
                    总和7722.8亿元的5.59倍。现工业一年吃掉2.4亿吨钢材，超过美、日两国年产量的总和。现每年建筑工地浇灌的水泥相当
                    于全球年产量的40%，世界年产玻璃的30%镶在了中国的高楼大厦上。在1982年以前每年新增财政收入100亿元十分困难，
                    但近两年分别新增2990.8亿元和2527.9亿元。从以上统计数据可以看出，新中国所取得的经济建设成就，比国民党统治的旧
                    中国最好年份要高出上百倍，比历代封建王朝要强千百倍、上万倍。由此说，中国共产党执政54年，是中国历史上经济增长最
                    快、国力最强、人民生活改善最多的时期。新中国与旧中国相比较，发生了翻天覆地的变化，社会生产力获得飞速发展。旧貌
                    换新貌，真是今非昔比。
                    横向比较，中国比世界强国增速遥遥领先，强国富民
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>


</body>
<script type="text/javascript" src="${ctx}/dist/js/bootstrap.min.js"></script>
<script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
<script src="${ctx}/dist/controller/index/index.js"></script>
<%--<script src="${ctx}/dist/js/bootstrapValidator.min.js"></script>--%>
<script src="${ctx}/dist/js/user/jquery.validate.js"></script>
<script src="${ctx}/dist/js/user/form-validate.js"></script>
<script src="${ctx}/dist/js/user/messages_cn.js"></script>
<script src=""></script>
<script type="text/javascript">
    $().ready(function () {
        generateNav(-1);
    });

    $(function () {
        $('#zlight-nav').zlightMenu();
    });
    //维护一个二维数组，存放各个省份对应的城市
    var citys = [[], ["越秀区", "荔湾区", "海珠区", "天河区", "白云区", "黄埔区", "番禺区", "花都区", "南沙区", "萝岗区", "增城市", "从化市"],
        ["禅城区", "南海区", "顺德区", "三水区", "高明区"],
        ["石歧区", "东区", "西区", "南区", "五桂山区", "火炬开发区"],
        ["莞城区", "东城区", "南城区", "万江区"],
        ["福田区", "罗湖区", "南山区", "宝安区", "龙岗区", "盐田区"],
        ["香洲区", "斗门区", "金湾区"],
        ["浈江区", "武江区", "曲江区", "乐昌市", "南雄市", "始兴县", "仁化县", "翁源县", "新丰县", "乳源瑶族自治县"],
        ["源城区", "紫金县", "龙川县", "连平县", "和平县", "东源县"],
        ["梅江区", "兴宁市", "梅县", "大埔县", "丰顺县", "五华县", "平远县", "蕉岭县"],
        ["惠城区", "惠阳区", "博罗县", "惠东县", "龙门县"],
        ["城区", "陆丰市", "海丰县", "陆河县"],
        ["榕城区", "普宁市", "揭东县", "揭西县", "惠来县"],
        ["金平区", "濠江区", "龙湖区", "潮阳区", "潮南区", "澄海区", "南澳县"],
        ["湘桥区", "潮安县", "饶平县"],
        ["清城区", "英德市", "连州市", "佛冈县", "阳山县", "清新县", "连山壮族瑶族自治县", "连南瑶族自治县"],
        ["端州区", "鼎湖区", "高要市", "四会市", "广宁县", "怀集县", "封开县", "德庆县"],
        ["云城区", "罗定市", "云安县", "新兴县", "郁南县"],
        ["江海区", "蓬江区", "新会区", "恩平市", "台山市", "开平市", "鹤山市"],
        ["江城区", "阳春市", "阳西县", "阳东县"],
        ["茂南区", "茂港区", "化州市", "信宜市", "高州市", "电白县"],
        ["赤坎区", "霞山区", "坡头区", "麻章区", "吴川市", "廉江市", "雷州市", "遂溪县", "徐闻县"]];
    function showCity(node1, node2) {

        //获得第一个选择框的值
        var selectNode1 = node1;
        var selectIndex = selectNode1.selectedIndex;
        var cityData = citys[selectIndex];

        //获得城市选择框
        var countyNode = node2;
        //清空城市选择框的值
        /*
         var childNodes = cityNodes.childNodes;
         for(var i = 0; i < childNodes.length;){
         cityNodes.removeChild(childNodes[i]);
         }
         */
//        countyNode.length = 1;
        countyNode.empty();
        countyNode.append("<option>所在区/县</option>");
        //重新设置城市框
        for (var i = 0; i < cityData.length; i++) {
            var cityChild = document.createElement("option");
            cityChild.innerHTML = cityData[i];
            cityChild.setAttribute("value", cityData[i]);
            countyNode.append(cityChild);
        }
    }

    $("#f1-add").click(
            function () {
                if ( $("#f1-add").text() == "-") {
                    $("#f1-add").text("+");
                } else {
                    $("#f1-add").text("-");
                }
                $("#f1-addIntr").toggle();
            }
    );

    $("#f2-add").click(
            function () {
                if ( $("#f2-add").text() == "-") {
                    $("#f2-add").text("+");
                } else {
                    $("#f2-add").text("-");
                }
                $("#f2-addIntr").toggle();
            }
    );

    $("#f5-add").click(
            function () {
                if ( $("#f5-add").text() == "-") {
                    $("#f5-add").text("+");
                } else {
                    $("#f5-add").text("-");
                }
                $("#f5-addIntr").toggle();
            }
    );

    $("#btn1").click(function () {
        $("#form1").submit();
    })

    $("#btn2").click(function () {
        $("#form2").submit();
    })

    $("#btn3").click(function () {
        $("#form3").submit();
    })

    $("#btn4").click(function () {
        $("#form4").submit();
    })

    $("#btn5").click(function () {
        $("#form5").submit();
    })


</script>
</html>