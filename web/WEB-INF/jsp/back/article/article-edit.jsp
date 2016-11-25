<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 16-9-9
  Time: 上午11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav.css">
    <link rel="stylesheet" href="${ctx}/dist/wangEdit/css/wangEditor.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/fileinput.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/article/article.css">

    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <title>文章发布</title>
    
</head>
<body>

<jsp:include page="../header.jsp"/>

<div class="container-fluid">

    <div class="row">

        <jsp:include page="../left.jsp"/>

        <div class="col-md-9 right-panel" id="right-content">

            <ol class="breadcrumb">
                <li><a href="#">信息发布</a></li>
                <li class="active">文章修改</li>
            </ol>

            <form id="article-form" action="${ctx}/back/article/publish" method="post" role="form"
                  enctype="multipart/form-data">

                <input type="hidden" name="aid" value="${article.id}">

                <div class="form-group">
                    <label for="header">标题</label>
                    <input type="header" name="header " class="form-control" id="header"
                           value="${article.header}">
                </div>
                <label for="header">是否发布到图片新闻</label>
                <div class="form-group">
                    <c:if test="${article.isPicNews == 1}">
                        <label class="radio-inline">
                            <input type="radio" name="isPicNews" value="1" checked> 是
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="isPicNews" value="0"> 否
                        </label>
                    </c:if>
                    <c:if test="${article.isPicNews == 0}">
                        <label class="radio-inline">
                            <input type="radio" name="isPicNews" value="1"> 是
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="isPicNews" value="0" checked> 否
                        </label>
                    </c:if>
                </div>

                <div class="form-group">
                    <label>发布位置</label>
                    <div class="row">
                        <div class="col-md-3">
                            <select class="form-control" id="menu1" name="location1">
                            </select>
                        </div>
                        <div class="col-md-3">
                            <select class="form-control" id="menu2" name="location2">
                            </select>
                        </div>
                        <div class="col-md-3">
                            <select class="form-control" id="menu3" name="location3">
                            </select>
                        </div>
                        <div class="col-md-3">
                            <select class="form-control" id="menu4" name="location4">
                            </select>
                        </div>
                    </div>
                </div>

                <input type="hidden" name="location" id="location">

                <div id="editor" style="height: 500px;margin-bottom: 24px;">
                    ${article.content}
                </div>

                <%--表明是已经存在的文章--%>
                <input type="hidden" name="key" value="update">

                <%--接受富文本框内容的输入框--%>
                <input type="hidden" name="content" id="content">

                <c:if test="${not empty article.posterDesc}">
                    <div class="annex-desc" id="perform-poster">
                        <span class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;
                        展示图片: <a href="${ctx}/back/article/showImage?path=${article.poster}"
                                 target="_blank">${article.poster}</a>　&nbsp;&nbsp;&nbsp;&nbsp;
                        图片说明：${article.posterDesc}

                        <span id="span-delete-poster" class="span-delete pull-right">
                            <span class="glyphicon glyphicon-remove-sign"></span> &nbsp;&nbsp;  删除
                        </span>
                    </div>
                </c:if>


                <label style="margin-top: 24px;">展示图片</label>
                <div class="row">
                    <div class="col-md-8">
                        <input id="poster" class="file" type="file" name="poster">
                    </div>
                    <div class="col-md-4">
                        <input name="posterDesc" type="text" class="form-control" placeholder="请输入图片描述">

                    </div>
                </div>

                <c:if test="${not empty annex.filesurl}">
                    <div class="annex-desc" id="perform-annex">
                        <span class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;
                        原附件: <a href="${ctx}/back/article/download-annex?filePath=${annex.filesurl}">${annex.filesurl}</a>　&nbsp;&nbsp;&nbsp;&nbsp;
                        原附件说明：${annex.annexDesc}

                        <span id="span-delete-annex" class="span-delete pull-right">
                        <span class="glyphicon glyphicon-remove-sign"></span> &nbsp;&nbsp;  删除
                    </span>
                    </div>
                </c:if>


                <label>附件</label>
                <div class="row">
                    <div class="col-md-8">
                        <input id="annex" class="file" type="file" name="annex">
                    </div>
                    <div class="col-md-4">
                        <input name="annexDesc" type="text" class="form-control" placeholder="请输入附件描述">

                    </div>
                </div>

                <div class="btn-group pull-right" style="margin-top: 24px;">
                    <button type="button" class="btn btn-primary">预览</button>
                    <button id="btn-publish" type="button" class="btn btn-primary">送审</button>
                    <button type="button" class="btn btn-primary">取消</button>
                </div>
            </form>

        </div>


    </div>


</div>

<div style="height: 300px;"></div>


</body>
<script src="${ctx}/dist/js/left_nav.js"></script>
<script src="${ctx}/dist/wangEdit/js/wangEditor.min.js"></script>
<script src="${ctx}/dist/controller/article/article.js"></script>
<script src="${ctx}/dist/js/fileinput.min.js"></script>
<script>
    var editor = new wangEditor('editor');
    editor.config.pasteFilter = false;
    editor.create();

    $('#poster').fileinput({
        showUpload: false
    });

    $("#annex").fileinput({
        showUpload: false,
        fileType: "any",
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>"
    });


    $('#span-delete-poster').click(function () {
        if (confirm('删除后无法还原，是否删除？')) {
            //删除已有图片，显示重新上传图片的框
            $.ajax({
                url: '/back/article/deletePoster?aid=' +${article.id},
                type: 'get',
                success: function (data) {
                    console.log(data);
                    if (data == '删除成功') {
                        $('#perform-poster').remove();
                    } else {
                        alert('删除图片失败');
                    }
                },
                error: function () {
                    alert("未知错误");
                }
            });
        }
    });

    $('#span-delete-annex').click(function () {
        if (confirm('删除后无法还原，是否删除？')) {
            //删除已有图片，显示重新上传图片的框
            $.ajax({
                url: '/back/article/deleteAnnex?aid=' +${article.id},
                type: 'get',
                success: function (data) {
                    console.log(data);
                    if (data == '删除成功') {
                        $('#perform-annex').remove();
                    } else {
                        alert('删除图片失败');
                    }
                },
                error: function () {
                    alert("未知错误");
                }
            });
        }
    });

    $('#btn-publish').click(function () {
        console.log($('#header').val());
        if ($('#header').val() == ''){
            alert('标题不能为空');
            return;
        }
        console.log($('#menu2').val());
        if ($('#menu2').val() == '' || $('#menu2').val() == null){
            alert('请选择发布位置');
            return;
        }
        //在这里提交的文章需要去更新数据库
        var html = editor.$txt.html();
        $('#content').val(html);
        //将所选版块的值上传
        $('#location').val($('#menu1').children('option:selected').text());
        $('#article-form').submit();
    });


</script>
</html>