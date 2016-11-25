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


    <link rel="stylesheet" href="${ctx}/dist/css/index/amazeui.min.css"/>
    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/login.css">
    <link rel="shortcut icon" href="${ctx}/imgs/favicon.ico"/>
    <link rel="stylesheet" href="${ctx}/dist/css/fakeloader.css">

    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <%--<script src="${ctx}/dist/js/index/jquery.login.js"></script>--%>
    <script src="${ctx}/dist/js/bootstrap.min.js"></script>
    <script src="${ctx}/dist/js/vue.js"></script>
    <script src="${ctx}/dist/js/vue-resource.js"></script>
    <title>首页</title>

    <style>
        ．my-carousel {
            width: 100%;
            height: 400px;
        }

        .carousel-inner > .item > img {
            height: 260px;
        }

        .carousel-caption {
            width: 100%;
            /*background: rgba(83, 83, 83, 0.56);*/
            left: 0;
            bottom: 0;
        }
    </style>

</head>
<body <%--onload="createCode()"--%>>

<jsp:include page="header.jsp"/>

<div class="container">

    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="height: 400px;">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <c:forEach varStatus="status" var="article" items="${carouselNews}">
                <c:choose>
                    <c:when test="${status.index == 0}">
                        <li data-target="#carousel-example-generic" data-slide-to="${status.index}" class="active"></li>
                    </c:when>
                    <c:otherwise>
                        <li data-target="#carousel-example-generic" data-slide-to="${status.index}"></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <c:forEach varStatus="status" var="article" items="${carouselNews}">
                <c:choose>
                    <c:when test="${status.index == 0}">
                        <div class="item active">
                            <img style="width: 100%;height: 400px;" src="${article.poster}" alt="...">
                            <%--<div class="carousel-caption">--%>
                                    <%--${article.header}--%>
                            <%--</div>--%>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="item">
                            <img style="width: 100%;height: 400px;" src="${article.poster}" alt="...">
                            <%--<div class="carousel-caption">--%>
                                    <%--${article.header}--%>
                            <%--</div>--%>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>

    <%--<img src="${ctx}/imgs/banner.png" width="100%" height="300px" alt="">--%>
</div>


<div class="container">


    <div class="row">
        <div class="col-md-12">
            <div class="search-bar">
                <ul class="head-ul">
                    <li style="margin-right: 30px">
                        <h4 class="">欢迎入会</h4>
                    </li>
                    <li>
                        <h4 class="">诚邀您参加培训学习</h4>
                    </li>
                    <li style="margin-left: 350px">
                        <h4 class="">站内搜索：</h4>
                    </li>
                    <li>
                        <select class="form-control" id="select-which">
                            <option value="header" class="option">标题</option>
                            <option value="content" class="option">内容</option>
                        </select>
                    </li>
                    <li>
                        <input type="text" id="keywords" class="form-control" value="" placeholder="请输入关键字进行查询"/>
                    </li>
                    <li>
                        <button id="btn-search" type="button" class="btn btn-primary">
                            点击查询
                            <span class="glyphicon glyphicon-search"></span>
                        </button>
                    </li>
                </ul>
                <span class="clearfix"></span>
            </div>
        </div>
    </div>

    <div class="row" style="margin-top: 24px;">


        <div class="col-md-4 col-sm-4 col-xs-4">
            <div class="in-div">
                <div class="am-list-news-hd am-cf">
                    <h2 class="t-font"><strong>图片新闻</strong></h2>
                </div>
                <div id="my-carousel" class="carousel slide my-carousel" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li v-for="(index, item) in articles" data-target="#my-carousel"
                            data-slide-to="{{index}}"></li>
                    </ol>

                    <div class="carousel-inner" role="listbox">
                        <div class="item" v-for="(index,item) in articles" v-bind:class="{'active':index==0}">
                            <img src="{{item.poster}}" alt="...">
                            <a href="${ctx}/article/detail/{{item.id}}/协会新闻">
                                <div class="carousel-caption">
                                    {{item.header}}
                                </div>
                            </a>
                        </div>

                    </div>

                    <!-- Controls -->
                    <a class="left carousel-control" href="#my-carousel" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#my-carousel" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
        </div>

        <div class="col-md-4 new-div col-sm-4 col-xs-4">
            <div class="in-div">
                <div class="am-list-news-hd am-cf">
                    <!--带更多链接-->
                    <h2 class="t-font"><strong>协会新闻</strong></h2>
                    <a href="${ctx}/section/to-news-section?menu=协会新闻&menuId=2" class="">

                        <span class="am-list-news-more am-fr">更多 &raquo;</span>
                    </a>
                </div>
                <div data-am-widget="list_news" class="am-list-news am-list-news-default">
                    <!--列表标题-->

                    <div class="am-list-news-bd">
                        <ul class="am-list" id="association-ul"></ul>
                    </div>
                </div>
            </div>

        </div>
        <div class="col-md-4 col-sm-4 col-xs-4 new-div">
            <div class="in-div1">
                <!--列表标题-->
                <div class="am-list-news-hd am-cf">
                    <!--带更多链接-->
                    <h2 class="t-font"><strong>分会动态</strong></h2>
                    <a href="${ctx}/section/to-news-section?menu=分会动态&menuId=17" class="">
                        <span class="am-list-news-more am-fr">更多 &raquo;</span>
                    </a>
                </div>
                <div data-am-widget="list_news" class="am-list-news am-list-news-default">

                    <div class="am-list-news-bd">
                        <ul class="am-list" id="industry-news-ul"></ul>
                    </div>
                </div>
            </div>
            <div class="in-div1">
                <div class="am-list-news-hd am-cf">
                    <!--带更多链接-->
                    <h2 class="t-font"><strong>通知公告</strong></h2>
                    <a href="${ctx}/section/to-notices-section?menu=通知公告&menuId=3" class="">

                        <span class="am-list-news-more am-fr">更多 &raquo;</span>
                    </a>
                </div>
                <div data-am-widget="list_news" class="am-list-news am-list-news-default">
                    <!--列表标题-->

                    <div class="am-list-news-bd">
                        <ul class="am-list" id="notice-ul"></ul>
                    </div>
                </div>
            </div>

        </div>
        <div class="col-md-4 col-sm-4 col-xs-4 new-div">
            <div class="in-div">
                <!--列表标题-->
                <div class="am-list-news-hd am-cf">
                    <!--带更多链接-->
                    <h2 class="t-font"><strong>法律规章</strong></h2>
                    <a href="${ctx}/section/to-law-section?menu=法律规章&menuId=4" class="">

                        <span class="am-list-news-more am-fr">更多 &raquo;</span>
                    </a>
                </div>
                <div data-am-widget="list_news" class="am-list-news am-list-news-default">

                    <div class="am-list-news-bd">
                        <ul class="am-list" id="laws-regulations-ul"></ul>
                    </div>
                </div>
            </div>

        </div>
        <div class="col-md-4 col-sm-4 col-xs-4 new-div">
            <div class="in-div">
                <!--列表标题-->
                <div class="am-list-news-hd am-cf">
                    <!--带更多链接-->
                    <h2 class="t-font"><strong>价格政策</strong></h2>
                    <a href="${ctx}/section/to-law-section?menu=价格政策&menuId=22" class="">

                        <span class="am-list-news-more am-fr">更多 &raquo;</span>
                    </a>
                </div>
                <div data-am-widget="list_news" class="am-list-news am-list-news-default">

                    <div class="am-list-news-bd">
                        <ul class="am-list" id="price-policy-ul"></ul>
                    </div>
                </div>
            </div>

        </div>
        <div class="col-md-4 col-sm-4 col-xs-4 new-div">
            <div class="in-div">
                <!--列表标题-->
                <div class="am-list-news-hd am-cf">
                    <!--带更多链接-->
                    <h2 class="t-font"><strong>产业政策</strong></h2>
                    <a href="${ctx}/section/to-law-section?menu=产业政策&menuId=23" class="">

                        <span class="am-list-news-more am-fr">更多 &raquo;</span>
                    </a>
                </div>
                <div data-am-widget="list_news" class="am-list-news am-list-news-default">

                    <div class="am-list-news-bd">
                        <ul class="am-list" id="industrial-policy-ul"></ul>
                    </div>
                </div>
            </div>

        </div>
        <div class="col-md-4 col-sm-4 col-xs-4 new-div">
            <div class="in-div">
                <!--列表标题-->
                <div class="am-list-news-hd am-cf">
                    <!--带更多链接-->
                    <h2 class="t-font"><strong>价格数据</strong></h2>
                    <a href="${ctx}/section/to-price-section?menu=价格数据&menuId=6" class="">

                        <span class="am-list-news-more am-fr">更多 &raquo;</span>
                    </a>
                </div>
                <div data-am-widget="list_news" class="am-list-news am-list-news-default">

                    <div class="am-list-news-bd">
                        <ul class="am-list" id="price-data-ul">

                        </ul>
                    </div>
                </div>
            </div>

        </div>
        <div class="col-md-4 col-sm-4 col-xs-4 new-div">
            <div class="in-div">
                <!--列表标题-->
                <div class="am-list-news-hd am-cf">
                    <!--带更多链接-->
                    <h2 class="t-font"><strong>理论研究</strong></h2>
                    <a href="${ctx}/section/to-search-section?menu=理论研究&menuId=5" class="">

                        <span class="am-list-news-more am-fr">更多 &raquo;</span>
                    </a>
                </div>
                <div data-am-widget="list_news" class="am-list-news am-list-news-default">

                    <div class="am-list-news-bd">
                        <ul class="am-list" id="theoretical-research-ul"></ul>
                    </div>
                </div>
            </div>

        </div>
        <div class="col-md-4 col-sm-4 col-xs-4 new-div">
            <div class="in-div1">
                <!--列表标题-->
                <div class="am-list-news-hd am-cf">
                    <!--带更多链接-->
                    <h2 class="t-font"><strong>会员服务</strong></h2>
                    <a href="${ctx}/section/to-service-section?menu=会员服务&menuId=7" class="">

                        <span class="am-list-news-more am-fr">更多 &raquo;</span>
                    </a>
                </div>
                <div data-am-widget="list_news" class="am-list-news am-list-news-default">

                    <div class="am-list-news-bd">
                        <ul class="am-list" id="member-service-ul"></ul>
                    </div>
                </div>
            </div>
            <div class="in-div1">
                <!--列表标题-->
                <div class="am-list-news-hd am-cf">
                    <!--带更多链接-->
                    <h2 class="t-font"><strong>交流园地</strong></h2>
                    <a href="${ctx}/section/to-consult-section?menu=交流园地&menuId=9" class="">

                        <span class="am-list-news-more am-fr">更多 &raquo;</span>
                    </a>
                </div>
                <div data-am-widget="list_news" class="am-list-news am-list-news-default">

                    <div class="am-list-news-bd">
                        <ul class="am-list" id="exchange-field-ul"></ul>
                    </div>
                </div>
            </div>

        </div>

        <div class="row" style="margin-bottom: 24px;">
            <ul class="head-ul footlink">
                <li>
                    <h4 class="">友情链接</h4>
                </li>
                <li>
                    <select class="form-control">
                        <option value="all" class="option">xx网</option>
                        <option value="type1" class="option"> 类别一</option>
                        <option value="type2" class="option"> 类别一</option>
                    </select>
                </li>
                <li>
                    <select class="form-control">
                        <option value="all" class="option">政府网站</option>
                        <option value="type1" class="option"> 类别一</option>
                        <option value="type2" class="option"> 类别一</option>
                    </select>
                </li>
                <li>
                    <select class="form-control">
                        <option value="all" class="option">其他相关</option>
                        <option value="type1" class="option"> 类别一</option>
                        <option value="type2" class="option"> 类别一</option>
                    </select>
                </li>
                <li>
                    <select class="form-control">
                        <option value="all" class="option">其他行业</option>
                        <option value="type1" class="option">类别一</option>
                        <option value="type2" class="option">类别二</option>
                    </select>
                </li>
            </ul>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>


</body>
<script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
<script src="${ctx}/dist/js/fakeloader.min.js"></script>
<script src="${ctx}/dist/controller/index/index.js"></script>
<script>

    $().ready(function(){
        $(".fakeloader").fakeLoader({
            timeToHide:1200,
            bgColor:"#9b59b6",
            spinner:"spinner7"
        });
    });

    $().ready(function () {
        generateNav(0);
    });

    getArticleBySection("协会新闻", $('#association-ul'));
    getArticleBySection("分会动态", $('#industry-news-ul'));
    getArticleBySection("通知公告", $('#notice-ul'));
    getArticleBySection("法律规章", $('#laws-regulations-ul'));
    getArticleBySecondSection("价格政策", $('#price-policy-ul'));
    getArticleBySecondSection("产业政策", $('#industrial-policy-ul'));
    getArticleBySection("价格数据", $('#price-data-ul'));
    getArticleBySection("理论研究", $('#theoretical-research-ul'));
    getArticleBySection("会员服务", $('#member-service-ul'));
//    getArticleBySection("交流园地", $('#exchange-field-ul'));
    getExchangeSection();
</script>

<script src="${ctx}/dist/controller/index/photo-news.js"></script>
</html>

