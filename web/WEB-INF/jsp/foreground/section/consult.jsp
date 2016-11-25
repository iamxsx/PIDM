<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <link rel="stylesheet" href="${ctx}/dist/css/index/font-awesome.min.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav_frontend.css">
    <link rel="stylesheet" href="${ctx}/dist/css/communicate/foreground.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/login.css">
    <link rel="shortcut icon" href="${ctx}/imgs/favicon.ico"/>

    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <script src="${ctx}/dist/js/bootstrap.min.js"></script>
    <script src="${ctx}/dist/js/left_nav.js"></script>
    <script src="${ctx}/dist/js/communicate/foreground.js"></script>
    <script src="${ctx}/dist/controller/index/index.js"></script>


    <title>广东省价格和产业发展协会</title>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/foreground/header.jsp"/>

<div class="container">

    <div class="row" style="margin-top: 24px;">
        <div class="col-md-2">
            <ul class="left-nav" id="left-nav">
                <li class="head">
                    <div class="left-nav-item">
                        <span>交流园地</span>
                    </div>
                </li>
                <%--<li>--%>
                <%--<div class="left-nav-item">--%>
                <%--<a href="to-consult-section?menu=consult">--%>
                <%--咨询--%>
                <%--</a>--%>
                <%--</div>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<div class="left-nav-item">--%>
                <%--<a href="to-consult-section?menu=suggest">--%>
                <%--建议与意见--%>
                <%--</a>--%>
                <%--</div>--%>
                <%--</li>--%>
            </ul>
        </div>
        <div class="col-md-10 right-panel">
            <div>
                <h4>
                    <ol class="breadcrumb">
                        <li>
                            ${menuName}
                        </li>
                    </ol>
                </h4>
                <div class="input-group input-group-lg">
                    <span class="input-group-addon" id="titleCss">标题</span>
                    <input id="questionTitle" type="text" class="form-control" placeholder="请输入标题" maxlength="25"
                           onkeyup="javascript:setShowLength(this, 25, 'title_length');" onblur="changeBtn()">
                    <span class="remind" id="title_length">25/25</span>
                </div>
            </div>
            <div>
                <div class="input-group input-group-lg">
                    <textarea id="textareaContent" class="form-control" placeholder="请输入内容"
                              onblur="changeBtn()"></textarea>
                </div>
            </div>
            <div class="row">
                <div class="btn btn-primary" disabled="disabled"
                     style="width:70px;float: left;margin-left:15px;margin-top: 6px" onclick="submitQuestion()"
                     id="submitQuestionBtn">提交
                </div>
            </div>

            <div class="panel panel-info" style="width: 800px;margin-top: 20px">
                <div class="panel-heading" style="height: 50px;background-color: rgba(217, 237, 247, 0.71);">
                    <h4>其他人的询问</h4>
                </div>
                <div class="panel-footer othersCommunicateCss">

                    <div id="othersCommunicateTip"></div>

                    <div class="btn btn-primary" style="width:100%;" id="showMoreBtn" onclick="showMore()">点击加载更多
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div style="height:260px;"></div>


<jsp:include page="../footer.jsp"></jsp:include>

<!-- container close -->

</body>
<script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
<script src="${ctx}/dist/js/stickUp.min.js"></script>
<script src="${ctx}/dist/controller/section/section.js"></script>
<script>
    $(function () {
//        $('#zlight-nav').zlightMenu();
        generateNav(10);
    });

    getSecondMenuByMenuName('交流园地','${menuName}');
</script>
</html>
