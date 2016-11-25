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
<div class="col-sm-3 col-md-2">
    <ul class="left-nav">
        <li class="head">
            <div class="left-nav-item">
                <span>下载管理</span>
            </div>
        </li>

        <c:forEach items="${sessionScope.downloadMenu}" var="menu">
            <li class="add-active">
                <div class="left-nav-item">
                    <a href="${ctx}/back${menu.url}">
                        <i class="${menu.icon}"></i>${menu.name}
                    </a>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>