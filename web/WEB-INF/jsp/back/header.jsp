<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link rel="shortcut icon" href="${ctx}/imgs/favicon.ico"/>
<div class="topbar">
    <div class="logo">
        <span>广东价格和产业发展协会管理后台</span>
    </div>
    <div class="user-option">
        <ul id="user-ul" class="pull-right">
            <li>欢迎您，<span id="employeeName"></span></li>
            <li style="cursor: pointer;"><a id="logout" href="/back/logout">退出</a></li>
        </ul>
    </div>
</div>

<!--导航条-->
<nav class="navbar navbar-default my-nav" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-01">
            <span class="sr-only">Toggle navigation</span>
        </button>
    </div>
    <div class="collapse navbar-collapse" id="navbar-collapse-01">
        <ul class="nav navbar-nav my-navbar-nav">

            <c:forEach items="${sessionScope.backTopMenu}" var="menu">
                <li>
                    <a href="${ctx}/back${menu.url}/${menu.id}">
                        <i class="${menu.icon}"></i>
                        <span>${menu.name}</span>
                    </a>
                </li>
            </c:forEach>
        </ul>

    </div>
</nav>

<script src="/dist/js/jquery.min.js"></script>
<script>
    $(function () {
        $.ajax({
            url:'/back/getCurrentEmployee',
            type:'get',
            success:function(data){
                $("#employeeName").text(data);
            },
            error:function(err){

            }
        });

        $("#logout").click(function () {
            var isLogout = window.confirm("是否退出当前账号？")
            if (isLogout){
                return true;
            }else {
                return false;
            }
        });
    });
</script>
