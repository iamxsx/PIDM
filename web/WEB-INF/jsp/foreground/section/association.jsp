<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

	<link rel="stylesheet" href="${ctx}/dist/css/index/font-awesome.min.css" media="screen">
	<link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
	<link rel="stylesheet" href="${ctx}/dist/css/index/index.css">
	<%--<link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">--%>
	<link rel="stylesheet" href="${ctx}/dist/css/left_nav_frontend.css">
	<link rel="stylesheet" href="${ctx}/dist/css/index/login.css">
	<link rel="shortcut icon" href="${ctx}/imgs/favicon.ico"/>

	<script src="${ctx}/dist/js/jquery.min.js"></script>
	<script src="${ctx}/dist/js/bootstrap.min.js"></script>
	<script src="${ctx}/dist/js/left_nav.js"></script>


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
						<span>协会概况</span>
					</div>
				</li>
			</ul>
		</div>
		<div class="col-md-10 right-panel">

			<h4>${article.header}</h4>

			<c:if test="${not empty article.publishTime}">
				<span>发布时间：${article.publishTime}</span>
			</c:if>
			<hr>

			<c:if test="${not empty article.poster}">
				<img src="${ctx}/back/article/showImage?path=${article.poster}" alt="" width="100%" >
			</c:if>

			<div id = "content" style="margin-top: 24px;">
				${article.content}
			</div>

			<c:if test="${not empty article.posterDesc}">
				<div class="annex-desc" id="perform-poster">
					<span class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;
					展示图片: <a href="${ctx}/back/article/showImage?path=${article.poster}"
							 target="_blank">${article.poster}</a>　&nbsp;&nbsp;&nbsp;&nbsp;
					图片说明：${article.posterDesc}

				</div>
			</c:if>

			<c:if test="${not empty annex.filesurl}">
				<div class="annex-desc" id="perform-annex">
					<span class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;
					附件: <a href="${ctx}/back/article/download-annex?filePath=${annex.filesurl}">${annex.filesurl}</a>　&nbsp;&nbsp;&nbsp;&nbsp;
					附件说明：${annex.annexDesc}

				</div>
			</c:if>


		</div>
	</div>

</div>
<div style="height:200px;"></div>

<jsp:include page="../footer.jsp"></jsp:include>

<!-- container close -->

</body>
<script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
<script src="${ctx}/dist/controller/index/index.js"></script>
<script src="${ctx}/dist/js/vue.js"></script>
<script src="${ctx}/dist/controller/section/section.js"></script>
<script>

	$().ready(function () {
		getSecondMenuByMenuName('协会概况','${menuName}');
		console.log('${menuName}');

	});
	generateNav(1);
</script>
</html>