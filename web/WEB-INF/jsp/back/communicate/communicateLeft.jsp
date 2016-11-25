<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 9/18/16
  Time: 8:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="col-md-2">
    <ul class="left-nav" style="font-size: 1.1em">
        <li class="head">
            <div class="left-nav-item">
                <span>交流园地管理</span>
            </div>
        </li>
        <c:forEach items="${sessionScope.communicateMenu}" var="menu">
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
                <%--<a href="showList">--%>
                    <%--<i class="icon-comments-alt icon-large"></i> 咨询与建议--%>
                <%--</a>--%>
            <%--</div>--%>
        <%--</li>--%>
    </ul>
</div>

