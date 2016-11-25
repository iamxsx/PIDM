<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 10/11/16
  Time: 10:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="col-md-2">

    <ul class="left-nav">
        <li class="head">
            <div class="left-nav-item">
                <span>调查表管理</span>
            </div>
        </li>
        <c:forEach items="${sessionScope.surveyMenu}" var="menu">
            <li class="add-active">
                <div class="left-nav-item">
                    <a  id="${menu.name}" onclick="changeSurvey('${menu.id}','/back${menu.url}')">
                        <i class="${menu.icon}"></i>${menu.name}
                    </a>
                </div>
            </li>
        </c:forEach>
    </ul>

</div>

<script>
    function changeSurvey(id,url) {
        if(id == 129){
            location.href="${ctx}"+url;
        }else if(id == 130){
            $.ajax({
                url:"${ctx}"+url,
                type:"post",
                success:function (data) {
                    if(data == "" || data == "2"){
                        location.href = "${ctx}/back/survey/surveyManage?sta=2";
                    }else if (data == "3"){
                        if(confirm("修改操作会撤回调查表并清除调查到的数据\n\n\t\t确定修改调查表吗?")){
                            location.href = "${ctx}/back/survey/changeStatus";
                        }
                    }else if(data == "1"){
                        location.href = "${ctx}/back/survey/changeSurvey";
                    }
                },
                error:function () {
                    <%--location.href = "${ctx}/back/survey/surveyManage?sta=2";--%>
                    alert("操作失败!");
                }

            });

        }else if(id == 131){
            location.href="${ctx}"+url;


        }else if(id == 132){
            location.href="${ctx}"+url;


        }
    }
</script>