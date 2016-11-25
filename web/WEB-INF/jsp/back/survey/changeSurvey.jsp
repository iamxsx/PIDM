<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 10/9/16
  Time: 8:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>意见调查表修改</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/survey/survey.css">
    <link rel="stylesheet" href="${ctx}/dist/css/client/questionSurvey.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav.css">
    <link rel="stylesheet" href="${ctx}/dist/css/survey/materSurvey.css">

    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <%--<script src="${ctx}/dist/js/materialize.min.js"></script>--%>
    <script src="${ctx}/dist/js/index/jquery.login.js"></script>
    <script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
    <script src="${ctx}/dist/js/bootstrap.min.js"></script>
    <script src="${ctx}/dist/js/left_nav.js"></script>
    <script src="${ctx}/dist/js/index/amazeui.min.js"></script>
    <script src="${ctx}/dist/controller/index/index.js"></script>

    <style>
        .surveyTitle{
            width: 85%;
            margin: 1em auto;
        }
        .ques{
            margin: 3em 3em;
        }
        .topicPanel{
            clear: both;
            margin: 1em;
        }
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
<jsp:include page="../header.jsp"/>
<div>
    <jsp:include page="surveyLeft.jsp"/>
    <div class="col-md-9 right-panel">
        <%--<a class="survey-paper-clip"></a>--%>
        <div class="conContent">
            <form action="change" method="post" id="changeSurvey">
                <div class="row  surveyTitle">
                    <div class="col-sm-2 text-right" style="line-height: 4em">
                        <span class="title">标题修改:</span>
                    </div>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="questionnaireSurvey.title" value="${survey.questionnaireSurvey.title}"
                               id="questionnaireSurvey" onblur="blurChange(${survey.questionnaireSurvey.id},'questionnaireSurvey',0,'${survey.questionnaireSurvey.title}')"
                                required>
                        <input type="hidden" name="id" id="questionnaireSurveyId" value="${survey.questionnaireSurvey.id}">
                    </div>
                </div>
                <hr size="3px" style="margin:0 auto;height: 4px; width:95%;clear: both">
                <ul id="buttonlist" class="fix">
                    <%--<li class="btn-li"><div id="rad" class="btn btn-default button" onclick="addTopic(0,${survey.questionnaireSurvey.id})">单选</div></li>
                    &lt;%&ndash;<li><div id="checkbox-btn" class="btn btn-primary">多选</div></li>&ndash;%&gt;
                    <li class="btn-li"><div id="com" class="btn btn-default button" onclick="addTopic(1,${survey.questionnaireSurvey.id})">填空</div></li>
                --%></ul>

                <div class="topicPanel">
                    <div id="topicCon">
                        <c:forEach items="${survey.topic}" var="item" varStatus="status">
                            <div class="ques">
                                <c:choose>
                                    <c:when test="${item.nature == 0}">
                                        <div class="topic">
                                            <div class="row topic-content">
                                                <a type='button' class='remove-btn' id='${item.tid}' onclick='removeQues(${item.tid},1)' >
                                                    <img src="${ctx}/imgs/survey/delete.png" class="deleteButton">
                                                </a>
                                                [单选]&nbsp;题目:
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-10">
                                                    <input class='form-control question-content' style='box-shadow:none;'
                                                           id="topicContent${item.tid}" type='text'  value="${item.topicContent}"
                                                           onblur="blurChange(${item.tid},'topicContent${item.tid}',1,'${item.topicContent}')"
                                                           name="topicContent${item.tid}" required>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="options">
                                            <input type="hidden" name="topic[${status.index}].nature" value="${item.nature}" id="">
                                            <c:forEach items="${item.option}" var="option" varStatus="s">
                                                <c:if test="${option.nature == 'A'}">
                                                    <div class="row">
                                                            <%--<span class='col-sm-2 text-right answ'>
                                                            </span>--%>
                                                        <div class="col-sm-7 answer">
                                                            <input type="radio" class="optionradio" name="topic[${status.index}].option[0].id" value="${option.id}">A.&nbsp;&nbsp;&nbsp;
                                                            <input type="text" value="${option.optionContent}" class="form-control optionContent"
                                                                   id="option${option.id}" onblur="blurChange(${option.id},'option${option.id}',2,'${option.optionContent}')"
                                                                   name="option${option.id}" required>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <c:if test="${option.nature == 'B'}">
                                                    <div class="row">
                                                        <div class="col-sm-7 answer">
                                                            <input type="radio" class="optionradio" name="topic[${status.index}].option[0].id" value="${option.id}">B.&nbsp;&nbsp;&nbsp;
                                                            <input type="text" value="${option.optionContent}" class="form-control optionContent"
                                                                   id="option${option.id}" onblur="blurChange(${option.id},'option${option.id}',2,'${option.optionContent}')"
                                                                   name="option${option.id}" required>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <c:if test="${option.nature == 'C'}">
                                                    <div class="row">
                                                        <div class="col-sm-7 answer">
                                                            <input type="radio" class="optionradio" name="topic[${status.index}].option[0].id" value="${option.id}">C.&nbsp;&nbsp;&nbsp;
                                                            <input type="text" value="${option.optionContent}" class="form-control optionContent"
                                                                   id="option${option.id}" onblur="blurChange(${option.id},'option${option.id}',2,'${option.optionContent}')"
                                                                   name="option${option.id}" required>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <c:if test="${option.nature == 'D'}">
                                                    <div class="row">
                                                        <div class="col-sm-7 answer">
                                                            <input type="radio" class="optionradio" name="topic[${status.index}].option[0].id" value="${option.id}">D.&nbsp;&nbsp;&nbsp;
                                                            <input type="text" value="${option.optionContent}" class="form-control optionContent"
                                                                   id="option${option.id}" onblur="blurChange(${option.id},'option${option.id}',2,'${option.optionContent}')"
                                                                   name="option${option.id}" required>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </c:when>
                                    <c:when test="${item.nature == 2}">
                                        <div class="topic">
                                            <div class="row topic-content">
                                                <a type='button' class='remove-btn' id='${item.tid}' onclick='removeQues(${item.tid},2)'>
                                                    <img src='/imgs/survey/delete.png' class='deleteButton'>
                                                </a>
                                                [多选]&nbsp;题目:
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-10">
                                                    <input class='form-control question-content' style='box-shadow:none;'
                                                           id="topicContent${item.tid}" type='text'  value="${item.topicContent}" onblur="blurChange(${item.tid},'topicContent${item.tid}',1,'${item.topicContent}')"
                                                           name="topicContent${item.tid}" required>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="options">
                                            <input type="hidden" name="topic[${status.index}].nature" value="${item.nature}" id="">
                                            <c:forEach items="${item.option}" var="option" varStatus="s">
                                                <c:if test="${option.nature == 'A'}">
                                                    <div class="row">
                                                            <%--<span class='col-sm-2 text-right answ'>
                                                            </span>--%>
                                                        <div class="col-sm-7 answer">
                                                            <input type="checkbox" class="optionradio" name="topic[${status.index}].option[0].id" value="${option.id}">A.&nbsp;&nbsp;&nbsp;
                                                            <input type="text" value="${option.optionContent}" class="form-control optionContent"
                                                                   id="option${option.id}" onblur="blurChange(${option.id},'option${option.id}',2,'${option.optionContent}')"
                                                                   name="option${option.id}" required>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <c:if test="${option.nature == 'B'}">
                                                    <div class="row">
                                                        <div class="col-sm-7 answer">
                                                            <input type="checkbox" class="optionradio" name="topic[${status.index}].option[1].id" value="${option.id}">B.&nbsp;&nbsp;&nbsp;
                                                            <input type="text" value="${option.optionContent}" class="form-control optionContent"
                                                                   id="option${option.id}" onblur="blurChange(${option.id},'option${option.id}',2,'${option.optionContent}')"
                                                                   name="option${option.id}" required>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <c:if test="${option.nature == 'C'}">
                                                    <div class="row">
                                                        <div class="col-sm-7 answer">
                                                            <input type="checkbox" class="optionradio" name="topic[${status.index}].option[2].id" value="${option.id}">C.&nbsp;&nbsp;&nbsp;
                                                            <input type="text" value="${option.optionContent}" class="form-control optionContent"
                                                                   id="option${option.id}" onblur="blurChange(${option.id},'option${option.id}',2,'${option.optionContent}')"
                                                                   name="option${option.id}" required>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <c:if test="${option.nature == 'D'}">
                                                    <div class="row">
                                                        <div class="col-sm-7 answer">
                                                            <input type="checkbox" class="optionradio" name="topic[${status.index}].option[3].id" value="${option.id}">D.&nbsp;&nbsp;&nbsp;
                                                            <input type="text" value="${option.optionContent}" class="form-control optionContent"
                                                                   id="option${option.id}" onblur="blurChange(${option.id},'option${option.id}',2,'${option.optionContent}')"
                                                                   name="option${option.id}" required>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </c:when>
                                    <c:when test="${item.nature == 1}">
                                        <div class="topic">
                                            <div class="row topic-content">
                                                <a type='button' class='remove-btn' id='${item.tid}' onclick='removeQues(${item.tid},3)'>
                                                    <img src='/imgs/survey/delete.png' class='deleteButton'>
                                                </a>
                                                [填空]&nbsp;题目:
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-10">
                                                    <input class='form-control question-content' style='box-shadow:none;' type='text' id="topicContent${item.tid}" value="${item.topicContent}" onblur="blurChange(${item.tid},'topicContent${item.tid}',1,'${item.topicContent}')"
                                                           name="topicContent${item.tid}" required>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                        </div>
                                        <input type="hidden" name="topic[${status.index}].tid" value="${item.tid}">
                                    </c:when>
                                </c:choose>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="btn-submit">
                        <input type="submit" class="btn btn-primary submit"  id="submit" value="发布">
                    </div>
                    <div class="statisNum">
                        本意见调查表共&nbsp;&nbsp;<span id="count">${survey.questionnaireSurvey.joinNum}</span>&nbsp;&nbsp;道题目&nbsp;(
                        单选题&nbsp;&nbsp;<span id="singselection">${survey.singselection}</span>&nbsp;&nbsp;道题目&nbsp;,&nbsp;
                        多选题&nbsp;&nbsp;<span id="mulselect">${survey.mulselect}</span>&nbsp;&nbsp;道题目&nbsp;,&nbsp;
                        填空题&nbsp;&nbsp;<span id="comp">${survey.comp}</span>&nbsp;&nbsp;道题目&nbsp;)
                        <%--<hr width="50%" style="margin-left: 15em">--%>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="fixed-action-btn" style="bottom: 45px; right: 24px;">
        <a class="btn-floating btn-large blue">
            <i class="material-icons">添加题目</i>
        </a>
        <ul>
            <li><a class="btn-floating blue" onclick="addTopic(0,${survey.questionnaireSurvey.id})"><i class="material-icons">单选</i></a></li>
            <li><a class="btn-floating blue" onclick="addTopic(2,${survey.questionnaireSurvey.id})"><i class="material-icons">多选</i></a></li>
            <li><a class="btn-floating blue" onclick="addTopic(1,${survey.questionnaireSurvey.id})"><i class="material-icons">填空</i></a></li>
            <%--<li><a class="btn-floating green"><i class="material-icons">publish</i></a></li>
            <li><a class="btn-floating blue"><i class="material-icons">attach_file</i></a></li>--%>
        </ul>
    </div>

</div>
</body>
<script src="${ctx}/dist/controller/client/changeSurvey.js"></script>
<script src="${ctx}/dist/js/user/jquery.validate.js"></script>
<script src="${ctx}/dist/js/user/messages_cn.js"></script>
<script>
    var count = ${survey.questionnaireSurvey.joinNum};
    var singselection = ${survey.singselection};
    var mulselect = ${survey.mulselect};
    var comp = ${survey.comp};
    $("#changeSurvey").validate({
        errorPlacement:function (error,element) {
            $(element).parent().append(error);
        },
        errorElement: "span",
    });
    $(function () {
        var lis = $('.left-nav .add-active');
        $(lis[1]).addClass('active');
    });
</script>
</html>
