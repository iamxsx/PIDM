<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 16-9-15
  Time: 上午9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <title>Document</title>
    <style>

        .ok{
            color: #3498db;
            font-size: 100px;
        }

        .p-ok{
            color: #3498db;
            font-size: 40px;
        }

        .success{
            text-align: center;
            margin-top: 120px;
        }

        .message{
            color: #3498db;
        }

    </style>


</head>
<body>

<div class="container">

    <div class="row success">

        <div class="col-md-8 col-md-offset-2 ">
            <span class="glyphicon glyphicon-ok-circle ok"></span><br><br>
            <p class="p-ok">上传成功,正在跳转</p>
        </div>

    </div>

</div>

</body>
<script type="text/javascript">
    function goback(){
        window.history.go(-1);
    }
    setTimeout("goback()", 2000);
</script>
</html>