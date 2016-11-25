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
    <ul class="left-nav" >
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
                    <%--<i class="icon-edit"></i> 注册信息--%>
                <%--</a>--%>
            <%--</div>--%>
        <%--</li>--%>
        <%--<li>--%>
            <%--<div class="left-nav-item">--%>
                <%--<a href="">--%>
                    <%--<i class="icon-paste"></i> 客户信息--%>
                <%--</a>--%>
            <%--</div>--%>
        <%--</li>--%>
    </ul>
</div>

