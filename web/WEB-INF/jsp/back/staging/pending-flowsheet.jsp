<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 16-9-14
  Time: 下午7:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <title>流程图</title>
    <style>
        body{
            font-size: 1.4em;
        }
    </style>
</head>
<body>
<img style="position: absolute;top: 0px;left: 0px;" id="img_id" src="historicViewImage?processInstanceId=${processInstanceId}">
</body>
</html>
