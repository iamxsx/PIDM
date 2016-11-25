<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 16-9-11
  Time: 下午9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="col-md-2">
    <%--<ul id="accordion" class="accordion">--%>
    <%--<li class="open">--%>
    <%--<div class="link">--%>
    <%--<i class="icon-caret-down"></i>信息管理--%>
    <%--<i class="fa fa-chevron-down"></i>--%>
    <%--</div>--%>
    <%--<ul class="submenu" style="display: block">--%>
    <%--<li>--%>
    <%--<a href="/PIDM/article/to-publish">--%>
    <%--新文章--%>
    <%--</a>--%>
    <%--</li>--%>
    <%--<li><a href="/PIDM/article/article-not-published-list">未发布</a></li>--%>
    <%--<li><a href="/PIDM/article/article-verify-list">审核中</a></li>--%>
    <%--<li><a href="/PIDM/article/article-published-list">已发布</a></li>--%>
    <%--</ul>--%>
    <%--</li>--%>
    <%--</ul>--%>

    <ul class="left-nav">
        <li class="head">
            <div class="left-nav-item">
                <span>客户管理</span>
            </div>
        </li>
        <c:forEach items="${sessionScope.clientMenu}" var="menu">
            <li class="add-active">
                <div class="left-nav-item">
                    <a href="${ctx}/back${menu.url}">
                        <i class="${menu.icon}"></i>${menu.name}
                    </a>
                </div>
            </li>
        </c:forEach>
        <%--<li>--%>
            <%--<div class="left-nav-item">--%>
                <%--<a href="/PIDM/regmanage/to-regmanage">--%>
                    <%--<i class="icon-edit"></i>注册信息--%>
                <%--</a>--%>
            <%--</div>--%>
        <%--</li>--%>
        <%--<li>--%>
            <%--<div class="left-nav-item">--%>
                <%--<a href="/PIDM/user/clientList">--%>
                    <%--<i class="icon-paste"></i>客户信息--%>
                <%--</a>--%>
            <%--</div>--%>
        <%--</li>--%>
    </ul>

</div>
