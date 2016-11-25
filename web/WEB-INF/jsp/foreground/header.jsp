<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 16-9-17
  Time: 上午10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%--<link rel="stylesheet" href="${ctx}/dist/css/user/style.css">--%>
<link rel="shortcut icon" href="${ctx}/imgs/favicon.ico"/>

<div style="width:100%;background-color: #FFFFFF">

<div class="my-header container">
    <div class="row">
        <div class="col-md-12">
            <div class="top-bar">
                <c:if test="${userInfo == null}">
                    <a id="loginStart" href="${ctx}/new-register/redirect?msg=goto-login">登录</a><a href="${ctx}/new-register/redirect?msg=goto-register">会员注册</a>
                </c:if>
                <c:if test="${userInfo != null}">
                    <a href="${ctx}/new-login/logout">退出</a>&nbsp;
                    <a href="${ctx}/work/to-personal">会员中心</a>&nbsp;
                    <a href="#" id="loginIdMaster">你好，${userInfo.realName}</a>&nbsp;
                </c:if>
                <a href="${ctx}/index" style="float:left;margin:0 ">
                    <img src="${ctx}/imgs/logo.png" height="110px" style="margin-left: -20px;" alt="">
                </a>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="col-md-12">
            <nav id="zlight-nav" class="my-navbar">
                <ul id="zlight-main-nav" style="">
                    <li>
                        <a href="${ctx}/index">
                            首页
                            <%--<i class="icon-home"></i>--%>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>
</div>
