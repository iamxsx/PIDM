<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 9/28/16
  Time: 3:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>创建意见调查表</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav.css">
    <link rel="stylesheet" href="${ctx}/dist/css/client/questionSurvey.css">
    <link rel="stylesheet" href="${ctx}/dist/css/survey/survey.css">
    <link rel="stylesheet" href="${ctx}/dist/css/survey/materSurvey.css">
    <style>
        .error{
            color: red;
            margin-left: 1em;
        }
        a:hover{
            cursor:pointer
        }
    </style>


</head>
<body>
    <jsp:include page="../header.jsp"/>
    <div>
        <jsp:include page="surveyLeft.jsp"/>
        <div class="col-md-9 right-panel">
            <form id="surveyForm" action="saveSurveyInfo" method="post" class="conContent">
                <div class="question-title">
                    <span class="title">标题:</span>
                    <input type="text" class="form-control" id="questionnaireSurvey.title" name="questionnaireSurvey.title" required>
                </div>

                <%--<ul id="button-list">
                    <li><div id="radio-btn" class="btn btn-primary">单选</div></li>
                    &lt;%&ndash;<li><div id="checkbox-btn" class="btn btn-primary">多选</div></li>&ndash;%&gt;
                    <li><div id="completion" class="btn btn-primary">填空</div></li>
                </ul>--%>
                <div id="con" class="container">
                    <div class='question'>
                    <div class='row'>
                        <a type='button' class='remove-btn' id='remove-btn0' onclick='removeQuestion(0,1);'>
                            <img src='/imgs/survey/delete.png' class='deleteButton'>
                        </a>
                        [单选]&nbsp;题目:<input class='form-control question-content' style='box-shadow:none;' type='text' name='topic[0].topicContent' required>
                        <input type='hidden' name='topic[0].nature' value='0'>
                        </div>
                    <span>选项:</span>

                    <div class='row'>
                        <div class='col-sm-7 answer'>
                            <input type='radio' class='optionradio' name='0'>&nbsp;A.&nbsp;&nbsp;&nbsp;
                            <input class='form-control optionContent' type='text' name='topic[0].option[0].optionContent' required>
                            <input type='hidden' name='topic[0].option[0].nature' value='A'>
                            </div>
                        </div>
                    <div class='row'>
                        <div class='col-sm-7 answer'>
                            <input type='radio' class='optionradio' name='0'>&nbsp;B.&nbsp;&nbsp;&nbsp;
                            <input class='form-control optionContent' type='text' name='topic[0].option[1].optionContent' required>
                            <input type='hidden' name='topic[0].option[1].nature' value='B'>
                            </div>
                        </div>
                    <div class='row'>
                        <div class='col-sm-7 answer'>
                            <input type='radio' class='optionradio' name='0'>&nbsp;C.&nbsp;&nbsp;&nbsp;
                            <input class='form-control optionContent' type='text' name='topic[0].option[2].optionContent' required>
                            <input type='hidden' name='topic[0].option[2].nature' value='C'>
                            </div>
                        </div>
                    <div class='row'>
                        <div class='col-sm-7 answer'>
                            <input type='radio' class='optionradio' name='0'>&nbsp;D.&nbsp;&nbsp;&nbsp;
                            <input class='form-control optionContent' type='text' name='topic[0].option[3].optionContent' required>
                            <input type='hidden' name='topic[0].option[3].nature' value='D'>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="btn-list">
                    <%--                <button class="btn btn-primary">保存</button>--%>
                    <button type="submit" class="btn btn-primary">发布</button>
                </div>
                <div class="statisNum">
                    共创建&nbsp;&nbsp;<span id="count">1</span>&nbsp;&nbsp;道题目&nbsp;(
                    单选题&nbsp;&nbsp;<span id="singselection">1</span>&nbsp;&nbsp;道题目&nbsp;,&nbsp;
                    多选题&nbsp;&nbsp;<span id="mulselect">0</span>&nbsp;&nbsp;道题目&nbsp;,&nbsp;
                    填空题&nbsp;&nbsp;<span id="comp">0</span>&nbsp;&nbsp;道题目&nbsp;)
                </div>
            </form>

        </div>
        <div class="fixed-action-btn" style="bottom: 45px; right: 24px;">
            <a class="btn-floating btn-large blue">
                <i class="material-icons">添加题目</i>
            </a>
            <ul>
                <li><a id="radio-btn" class="btn-floating blue"><i class="material-icons">单选</i></a></li>
                <li><a id="checkbox-btn" class="btn-floating blue"><i class="material-icons">多选</i></a></li>
                <li><a id="completion" class="btn-floating blue"><i class="material-icons">填空</i></a></li>
                <%--<li><a class="btn-floating green"><i class="material-icons">publish</i></a></li>
                <li><a class="btn-floating blue"><i class="material-icons">attach_file</i></a></li>--%>
            </ul>
        </div>
    </div>
</body>
<script src="${ctx}/dist/js/jquery.min.js"></script>
<%--<script src="${ctx}/dist/js/materialize.min.js"></script>--%>
<script src="${ctx}/dist/js/bootstrap.min.js"></script>
<script src="${ctx}/dist/js/user/jquery.validate.js"></script>
<script src="${ctx}/dist/js/user/messages_cn.js"></script>
<script src="${ctx}/dist/controller/client/questionSurvey.js"></script>
<script>
    $("#surveyForm").validate({
        errorPlacement:function (error,element) {
            $(element).parent().append(error);
        },
        errorElement: "span",
    });
    $(function () {
        var lis = $('.left-nav .add-active');
        $(lis[0]).addClass('active');
    });
</script>
</html>
