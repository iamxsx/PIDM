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

    <script src="${ctx}/dist/js/jquery.min.js"></script>


    <title>文章发布</title>

    <style>


    </style>
</head>
<body>

<jsp:include page="../header.jsp"/>

<div class="container-fluid">

    <div class="row">

        <jsp:include page="../left.jsp"/>

        <div class="col-md-9 right-panel" id="right-content">

            <ol class="breadcrumb">
                <li><a href="#">信息发布</a></li>
                <li class="active">发布新文章</li>
            </ol>


            <form id="article-form" action="${ctx}/back/article/publish" method="post" role="form"
                  enctype="multipart/form-data">

                <div class="form-group">
                    <label for="header">标题</label>
                    <input type="header" name="header " class="form-control" id="header" placeholder="请输入标题">
                </div>
                <label for="header">是否发布到图片新闻</label>
                <div class="form-group">
                    <label class="radio-inline">
                        <input type="radio" name="isPicNews" value="1"> 是
                    </label>
                    <label class="checkbox-inline">
                        <input type="radio" name="isPicNews" value="0" checked> 否
                    </label>
                </div>

                <div class="form-group">
                    <label>发布位置</label>
                    <div class="row">
                        <div class="col-md-3">
                            <select class="form-control" id="menu1" name="location1">
                                <option value="">　--　选择一级菜单　--　</option>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <select class="form-control" id="menu2" name="location2">
                                <option value="">　--　选择二级菜单　--　</option>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <select class="form-control" id="menu3" name="location3">
                                <option value="">　--　选择三级菜单　--　</option>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <select class="form-control" id="menu4" name="location4">
                                <option value="">　--　选择四级菜单　--　</option>
                            </select>
                        </div>
                    </div>

                </div>


                <label style="margin-top: 24px;">展示图片</label>（建议轮播图片大小为 1170*400 像素, 建议图片新闻大小为 800*400 像素）
                <div class="row" style="margin-bottom: 24px;">
                    <div class="col-md-8">
                        <input id="poster" class="file" type="file" name="poster">
                    </div>
                    <div class="col-md-4">
                        <input name="posterDesc" type="text" class="form-control" placeholder="请输入图片描述">

                    </div>
                </div>

                <input id="key" type="hidden" name="key" value="new">

                <input type="hidden" name="location" id="location">

                <div id="editor" style="height: 500px;">
                    <p>
                        请在此编辑文章内容．．．．．．
                    </p>
                </div>

                <input type="hidden" name="content" id="content">


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
                    <%--<button type="button" class="btn btn-primary">预览</button>--%>
                    <button id="btn-save" type="button" class="btn btn-primary">保存</button>
                    <button id="btn-publish" type="button" class="btn btn-primary">送审</button>
                    <button type="button" class="btn btn-primary">取消</button>
                </div>
            </form>

        </div>


    </div>


</div>

<div style="height: 300px;"></div>


</body>
<script src="${ctx}/dist/js/fileinput.min.js"></script>
<script src="${ctx}/dist/js/zh.js"></script>
<script src="${ctx}/dist/js/left_nav.js"></script>
<script src="${ctx}/dist/wangEdit/js/wangEditor.min.js"></script>
<script src="${ctx}/dist/js/bootstrapValidator.min.js"></script>
<script src="${ctx}/dist/controller/article/article.js"></script>
<script>
    var editor = new wangEditor('editor');
    editor.config.pasteFilter = false;
    editor.create();

    $('#btn-save').click(function () {
        if ($('#header').val() == '') {
            alert('标题不能为空');
            return;
        }
        if ($('#menu2').val() == '' || $('#menu2').val() == null) {
            alert('请选择发布位置');
            return;
        }
        var html = editor.$txt.html();
        $('#key').val('save');
        $('#content').val(html);
        //将所选版块的值上传
        $('#location').val($('#menu1').children('option:selected').text());
        $('#article-form').submit();
    });

    $('#poster').fileinput({
        showUpload: false
    });

    $("#annex").fileinput({
        showUpload: false,
        fileType: "any",
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>"
    });


    $(function () {
        var lis = $('.left-nav .add-active');
        $(lis[0]).addClass('active');
    });


</script>
</html>