<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 9/20/16
  Time: 9:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="col-md-2">
    <ul class="left-nav">
        <li class="head">
            <div class="left-nav-item">
                <span>系统管理</span>
            </div>
        </li>

        <c:forEach items="${sessionScope.systemMenu}" var="menu">
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
                <%--<a href="${ctx}/department">--%>
                    <%--<i class="icon-sitemap"></i>组织结构--%>
                <%--</a>--%>
            <%--</div>--%>
        <%--</li>--%>
        <%--<li>--%>
            <%--<div class="left-nav-item">--%>
                <%--<a href="${ctx}/jobposition">--%>
                    <%--<i class="icon-desktop"></i>岗位管理--%>
                <%--</a>--%>
            <%--</div>--%>
        <%--</li>--%>
        <%--<li>--%>
            <%--<div class="left-nav-item">--%>
                <%--<a href="${ctx}/employee">--%>
                    <%--<i class="icon-user"></i>用户管理--%>
                <%--</a>--%>
            <%--</div>--%>
        <%--</li>--%>
        <%--<li>--%>
            <%--<div class="left-nav-item">--%>
                <%--<a href="${ctx}/menu/to-menu-manage">--%>
                    <%--<i class="icon-reorder"></i>菜单管理--%>
                <%--</a>--%>
            <%--</div>--%>
        <%--</li>--%>
        <%--<li>--%>
            <%--<div class="left-nav-item">--%>
                <%--<a href="${ctx}/role/to-role-manage">--%>
                    <%--<i class="icon-group"></i>角色管理--%>
                <%--</a>--%>
            <%--</div>--%>
        <%--</li>--%>
        <%--<li>--%>
            <%--<div class="left-nav-item">--%>
                <%--<a href="">--%>
                    <%--<i class="icon-info-sign"></i> 运行日志--%>
                <%--</a>--%>
            <%--</div>--%>
        <%--</li>--%>
    </ul>
</div>