<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 10/7/16
  Time: 4:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>意见调查表</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/login.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav.css">
    <link rel="stylesheet" href="${ctx}/dist/css/survey/survey.css">

    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <script src="${ctx}/dist/js/index/jquery.login.js"></script>
    <script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
    <script src="${ctx}/dist/js/bootstrap.min.js"></script>
    <script src="${ctx}/dist/js/left_nav.js"></script>
    <script src="${ctx}/dist/js/index/amazeui.min.js"></script>
    <script src="${ctx}/dist/controller/index/index.js"></script>

    <style>
        .survey-paper-clip{
            position: absolute;
            right: 30px;
            top: -15px;
            background-image:url("${ctx}/imgs/survey/paper_clip.png");
            width: 33px;
            height: 81px;
            z-index: 1000;
        }
        .error{
            color: red;
            margin-left: 1em;
        }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/foreground/header.jsp"/>
    <div class="container-fluid">
        <a class="survey-paper-clip"></a>
        <p class="survey-title">${survey.questionnaireSurvey.title}</p>
        <hr size="3px" style="margin:0 auto;height: 4px; width:95%;">
        <div class="survey-content">
            <form action="/survey/statistics" id="formSurvey" method="post">
                <input type="hidden" name="userId" value="${userId}">
                <input type="hidden" name="questionnaireSurvey.id" value="${survey.questionnaireSurvey.id}">
                <c:forEach items="${survey.topic}" var="item" varStatus="status">
                    <div class="question-info">
                        <div class="row topic-content">
                            <span class="col-sm-12">
                                ${status.index+1}.&nbsp;&nbsp;&nbsp;${item.topicContent}
                                <span id="hiht" style="margin-left: 1em"></span>
                                <div class="error" style="display: none">提示:必填题目</div>
                            </span>
                        </div>
                            <c:choose>
                                <c:when test="${item.nature == 0}">
                                    <div class="option-content" name="radioCon">
                                        <input type="hidden" name="topic[${status.index}].nature" value="${item.nature}">
                                        <c:forEach items="${item.option}" var="option" varStatus="s">
                                        <c:if test="${option.nature == 'A'}">
                                            <div class="row">
                                                <input type="radio" name="topic[${status.index}].option[0].id" value="${option.id}"
                                                       id="topic${status.index}option0id"
                                                       onclick="checkValidateCanel('topic${status.index}option0id',1)">
                                                A.${option.optionContent}
                                            </div>
                                        </c:if>
                                        <c:if test="${option.nature == 'B'}">
                                            <div class="row" >
                                                <input type="radio" name="topic[${status.index}].option[0].id" value="${option.id}"
                                                       id="topic${status.index}option1id"
                                                       onclick="checkValidateCanel('topic${status.index}option1id',1)">
                                                B.${option.optionContent}
                                            </div>
                                        </c:if>
                                        <c:if test="${option.nature == 'C'}">
                                            <div class="row" >
                                                <input type="radio" name="topic[${status.index}].option[0].id" value="${option.id}"
                                                       id="topic${status.index}option2id"
                                                       onclick="checkValidateCanel('topic${status.index}option2id',1)">
                                                C.${option.optionContent}
                                            </div>
                                        </c:if>
                                        <c:if test="${option.nature == 'D'}">
                                            <div class="row" >
                                                <input type="radio" name="topic[${status.index}].option[0].id" value="${option.id}"
                                                       id="topic${status.index}option3id"
                                                       onclick="checkValidateCanel('topic${status.index}option3id',1)">
                                                D.${option.optionContent}
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                    </div>
                                </c:when>
                                <c:when test="${item.nature == 2}">
                                    <div class="option-content" name="checkCon">
                                        <input type="hidden" name="topic[${status.index}].nature" value="${item.nature}">
                                        <c:forEach items="${item.option}" var="option" varStatus="s">
                                        <c:if test="${option.nature == 'A'}">
                                            <div class="row" >
                                                <input type="checkbox" name="topic[${status.index}].option[0].id" id="topic${status.index}option0"
                                                       value="${option.id}" onclick="checkValidateCanel('topic${status.index}option0',2)">
                                                A.${option.optionContent}
                                            </div>
                                        </c:if>
                                        <c:if test="${option.nature == 'B'}">
                                            <div class="row" >
                                                <input type="checkbox" name="topic[${status.index}].option[1].id" id="topic${status.index}option1"
                                                       value="${option.id}" onclick="checkValidateCanel('topic${status.index}option1',2)">
                                                B.${option.optionContent}
                                            </div>
                                        </c:if>
                                        <c:if test="${option.nature == 'C'}">
                                            <div class="row" >
                                                <input type="checkbox" name="topic[${status.index}].option[2].id" id="topic${status.index}option2"
                                                       value="${option.id}" onclick="checkValidateCanel('topic${status.index}option2',2)">
                                                C.${option.optionContent}
                                            </div>
                                        </c:if>
                                        <c:if test="${option.nature == 'D'}">
                                            <div class="row" >
                                                <input type="checkbox" name="topic[${status.index}].option[3].id" id="topic${status.index}option3"
                                                       value="${option.id}" onclick="checkValidateCanel('topic${status.index}option3',2)">
                                                D.${option.optionContent}
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                    </div>
                                </c:when>
                                <c:when test="${item.nature == 1}">
                                    <div class="option-content">
                                        <input type="hidden" name="topic[${status.index}].nature" value="${item.nature}">
                                        <input type="hidden" name="topic[${status.index}].tid" value="${item.tid}">
                                        <div class="row">
                                            <input type="text" style='box-shadow:none;'  class="form-control question-content" name="topic[${status.index}].option[0].optionContent">
                                        </div>
                                    </div>
                                </c:when>
                            </c:choose>
                    </div>
                </c:forEach>
                <div class="btn-submit">
                    <input type="button" id="submitSurvey" class="btn btn-primary submit" value="提交">
                </div>
            </form>
        </div>

    </div>
    <jsp:include page="../../foreground/footer.jsp"></jsp:include>

</body>
<script>
    var userId =  ${userId};
    var surveyId = ${survey.questionnaireSurvey.id};

    $(function () {
        $('#zlight-nav').zlightMenu();
        generateNav(7);

        $('#submitSurvey').click(function () {
            $.ajax({
                url:"/survey/isWriteSurvey",
                type:"post",
                data:{
                    menu:"需求调查",
                    userId:${userId},
                    s_id:${survey.questionnaireSurvey.id}
                },
                success:function (data) {
                    if(checkValidate()){
                        if(data == "notWrite"){
                            $('#formSurvey').submit();
                        }else if(data == "isWrite"){
                            alert("您已填写过该调查表!");
                        }
                    }
                },
                error:function (error) {
                }
            });
        });
    });

</script>
<script src="${ctx}/dist/js/survey/SurveyValidate.js"></script>
</html>
