<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 16-10-26
  Time: 上午11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <link rel="stylesheet" href="${ctx}/dist/css/refuse/demo.css">
    <link rel="stylesheet" href="${ctx}/dist/css/refuse/iconfont.css">
    <title>消息页</title>
</head>
<body>
<div class="" style="text-align: center">
    <img src="${ctx}/dist/css/refuse/forbid.png">
    <h1>
            <i class="icon iconfont">&#xe61a;</i>您没有权限访问该页面
    </h1>
    <font>请登录后访问</font><br>
    <font><span id="totalSecond" style="color: red">4</span>秒后回到首页</font>


</div>

</body>
<script type="text/javascript">
    var i = 3;
    var intervalid;
    intervalid = setInterval("fun()",1000);
    function fun() {
        if (i==0)
        {
            clearInterval(intervalid);
        }
        document.getElementById("totalSecond").innerHTML = i;
        i--;
    }
    function goback(){
        window.history.go(-1);
        //window.location.href='/PIDM';
    }
    setTimeout("goback()", 4000);
</script>
</html>
