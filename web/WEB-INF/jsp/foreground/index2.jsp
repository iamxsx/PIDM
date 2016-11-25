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

    <!-- SCRIPTS -->
    <script src="${ctx}/dist/js/jquery.min.js"></script>
    <script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
    <script type="text/javascript" src="${ctx}/dist/js/index/amazeui.min.js"></script>
    <!-- STYLES -->
    <link rel="alternate icon" type="image/png" href="${ctx}/imgs/index/i/favicon.png">
    <link rel="stylesheet" href="${ctx}/dist/css/index/amazeui.min.css"/>
    <link rel="stylesheet" href="${ctx}/dist/css/index/index.css"/>
    <link href="${ctx}/dist/css/index/bootstrap.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/index/font-awesome.min.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/index/style.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
    <link rel="shortcut icon" href="${ctx}/imgs/favicon.ico"/>

    <title>广东省价格和产业发展协会</title>
</head>

<body>
<!--<header class="am-topbar am-topbar-fixed-top">

</header>-->

<div class="get">
    <div class="am-container">
        <div class="am-collapse am-topbar-collapse" id="collapse-head">
            <div class="am-topbar-right">
                <a href="#" class="link">注册</a>
            </div>
            <div class="am-topbar-right">
                <a href="#" class="link">登录</a>
            </div>
        </div>
    </div>
    <div class="am-g">
        <div class="am-u-lg-12">
            <h1 class="get-title">广东省价格和产业发展协会</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <h5>The Price and Industrial Development Association of Guangdong</h5>
            <h5>业务监督指导单位：广东省发展和改革委员会</h5>
        </div>
    </div>

    <div class="row" style="margin-top: 20px;">
        <div class="col-lg-12">
            <nav id="zlight-nav">
                <ul id="zlight-main-nav" style="">
                    <li class="zlight-active">
                        <a href="首页.html">首页</a>
                    </li>
                    <li class="zlight-dropdown">
                        <a href="协会概况/协会简介.jsp">协会概况</a>
                        <ul class="zlight-submenu">
                            <li>
                                <a href="协会概况/协会简介.jsp">协会简介</a>
                            </li>
                            <li>
                                <a href="协会概况/协会章程.jsp">协会章程</a>
                            </li>
                            <li>
                                <a href="协会概况/领导成员.jsp">领导成员</a>
                            </li>
                            <li>
                                <a href="协会概况/组织结构.jsp">组织结构</a>
                            </li>
                            <li class="zlight-dropdown">
                                <a href="协会概况/分会介绍.jsp">专业分会<b>&nbsp;&gt;</b></a>
                                <ul class="zlight-submenu">
                                    <li>
                                        <a href="协会概况/分会介绍.jsp">分会介绍</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="协会概况/专家智库.jsp">专家智库</a>
                            </li>
                        </ul>
                    </li>
                    <li class="zlight-dropdown">
                        <a href="协会新闻/协会动态.jsp">协会新闻</a>
                        <ul class="zlight-submenu">
                            <li>
                                <a href="协会新闻/协会动态.jsp">协会动态</a>
                            </li>
                            <li>
                                <a href="协会新闻/分会动态.jsp">分会动态</a>
                            </li>
                        </ul>
                    </li>
                    <li class="zlight-dropdown">
                        <a href="通知公告/重要通告.jsp">通知公告</a>
                        <ul class="zlight-submenu">
                            <li>
                                <a href="通知公告/重要通告.jsp">重要通告</a>
                            </li>
                            <li class="zlight-dropdown">
                                <a href="通知公告/参会报名.jsp">会议通知<b>&nbsp;&gt;</b></a>
                                <ul class="zlight-submenu">
                                    <li>
                                        <a href="通知公告/参会报名.jsp">参会报名</a>
                                    </li>
                                </ul>
                            </li>
                            <li class="zlight-dropdown">
                                <a href="#">培训通知<b>&nbsp;&gt;</b></a>
                                <ul class="zlight-submenu">
                                    <li>
                                        <a href="通知公告/网上报名.jsp">网上报名</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li class="zlight-dropdown">
                        <a href="法律规章/法律法规.jsp">法律规章</a>
                        <ul class="zlight-submenu">
                            <li>
                                <a href="法律规章/法律法规.jsp">法律法规</a>
                            </li>
                            <li>
                                <a href="法律规章/价格政策.jsp">价格政策</a>
                            </li>
                            <li>
                                <a href="法律规章/产业法规.jsp">产业法规</a>
                            </li>
                            <li>
                                <a href="法律规章/政策解读.jsp">政策解读</a>
                            </li>
                            <li>
                                <a href="法律规章/行业自律.jsp">行业自律</a>
                            </li>
                        </ul>
                    </li>
                    <li class="zlight-dropdown">
                        <a href="行业动态/电力行业动态.jsp">行业动态</a>
                        <ul class="zlight-submenu">
                            <li>
                                <a href="行业动态/电力行业动态.jsp">电力</a>
                            </li>
                            <li>
                                <a href="行业动态/燃气行业动态.jsp">燃气</a>
                            </li>
                            <li>
                                <a href="行业动态/农产品行业动态.jsp">农产品</a>
                            </li>
                        </ul>
                    </li>
                    <li class="zlight-dropdown">
                        <a href="价格数据/电力数据.jsp">价格数据</a>
                        <ul class="zlight-submenu">
                            <li>
                                <a href="价格数据/电力数据.jsp">电力</a>
                            </li>
                            <li>
                                <a href="价格数据/燃气数据.jsp">燃气</a>
                            </li>
                            <li>
                                <a href="价格数据/农产品数据.jsp">农产品</a>
                            </li>
                        </ul>
                    </li>
                    <li class="zlight-dropdown">
                        <a href="会员服务/协会会员服务方法.jsp">会员服务</a>
                        <ul class="zlight-submenu">
                            <li class="zlight-dropdown">
                                <a href="会员服务/协会会员服务方法.jsp">服务方法<b>&nbsp;&gt;</b></a>
                                <ul class="zlight-submenu">
                                    <li>
                                        <a href="会员服务/协会会员服务方法.jsp">协会会员服务方法</a>
                                    </li>
                                    <li>
                                        <a href="会员服务/分会工作方法.jsp">分会工作办法</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="会员服务/需求调查.jsp">入会申请</a>
                            </li>
                            <li>
                                <a href="会员服务/需求调查.jsp">意见调查表</a>
                            </li>
                            <li>
                                <a href="会员服务/会员风采.jsp">会员风彩</a>
                            </li>
                        </ul>
                    </li>
                    <li class="">
                        <a href="下载中心/下载中心.jsp">下载中心</a>
                    </li>
                    <li class="zlight-dropdown">
                        <a href="交流园地/咨询.jsp">交流园地</a>
                        <ul class="zlight-submenu">
                            <li>
                                <a href="交流园地/咨询.jsp">咨询</a>
                            </li>
                            <li>
                                <a href="交流园地/建议与意见.jsp">建议与意见</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <!-- MOBILE NAV -->
                <div id="zlight-mobile-nav">
                    <span>Menu</span>
                    <i class="icon-reorder zlight-icon"></i>
                    <select></select>
                </div>
            </nav>
            <!-- nav close -->

        </div>
    </div>
</div>
<div class="container" id="main">
    <div class="row zlight-hhh">
        <ul class="head-ul">
            <li>
                <h4 class="">欢迎入会</h4>
            </li>
            <li>
                <h4 class="">诚邀您参加培训学习</h4>
            </li>
            <li>
                <h4 class="">站内搜索：</h4>
            </li>
            <li>
                <select class="select" id="select-which">
                    <option value="all" class="option">--全部类别--</option>
                    <option value="header" class="option">标题</option>
                    <option value="content" class="option">内容</option>
                </select>
            </li>
            <li>
                <input type="text" id="keywords" class="s-text" value="" placeholder="请输入关键字进行查询"/>
            </li>
            <li>
                <button id="btn-search" type="button" class="s-btn">点击查询</button>
            </li>
        </ul>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="col-md-4 new-div">
                <div class="in-div">
                    <div class="am-list-news-hd am-cf">
                        <!--带更多链接-->
                        <h2 class="t-font"><strong>图片新闻</strong></h2>
                        <a href="##" class="">
                            <span class="am-list-news-more am-fr">更多 &raquo;</span>
                        </a>
                    </div>
                    <div data-am-widget="slider" class="am-slider am-slider-default"
                         data-am-slider='{"animation":"slide","slideshow":false}'>
                        <ul class="am-slides">
                            <li>
                                <img src="http://s.amazeui.org/media/i/demos/bing-1.jpg">
                                <div class="am-slider-desc">这是标题标题标题标题标题标题标题0</div>

                            </li>
                            <li>
                                <img src="http://s.amazeui.org/media/i/demos/bing-2.jpg">
                                <div class="am-slider-desc">这是标题标题标题标题标题标题标题1</div>

                            </li>
                            <li>
                                <img src="http://s.amazeui.org/media/i/demos/bing-3.jpg">
                                <div class="am-slider-desc">这是标题标题标题标题标题标题标题2</div>

                            </li>
                            <li>
                                <img src="http://s.amazeui.org/media/i/demos/bing-4.jpg">
                                <div class="am-slider-desc">这是标题标题标题标题标题标题标题3</div>

                            </li>
                        </ul>
                    </div>

                </div>

            </div>
            <div class="col-md-4 new-div">
                <div class="in-div">
                    <div class="am-list-news-hd am-cf">
                        <!--带更多链接-->
                        <h2 class="t-font"><strong>协会新闻</strong></h2>
                        <a href="##" class="">

                            <span class="am-list-news-more am-fr">更多 &raquo;</span>
                        </a>
                    </div>
                    <div data-am-widget="list_news" class="am-list-news am-list-news-default">
                        <!--列表标题-->

                        <div class="am-list-news-bd">
                            <ul class="am-list">

                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">2016年5月3日，。。。。。。。！</a>

                                    <span class="am-list-date">2013-09-18</span>

                                </li>
                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">哈哈哈哈哈哈哈哈</a>

                                    <span class="am-list-date">2013-10-14</span>

                                </li>
                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">嘻嘻我信息我嘻嘻嘻嘻嘻嘻</a>

                                    <span class="am-list-date">2013-11-18</span>
                                </li>
                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">嘻嘻我信息我嘻嘻嘻嘻嘻嘻</a>

                                    <span class="am-list-date">2013-11-18</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
            <div class="col-md-4 new-div">
                <div class="in-div">
                    <!--列表标题-->
                    <div class="am-list-news-hd am-cf">
                        <!--带更多链接-->
                        <h2 class="t-font"><strong>行业动态</strong></h2>
                        <a href="##" class="">
                            <span class="am-list-news-more am-fr">更多 &raquo;</span>
                        </a>
                    </div>
                    <div data-am-widget="list_news" class="am-list-news am-list-news-default">

                        <div class="am-list-news-bd">
                            <ul class="am-list">
                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">2016年5月3日，。。。。。。。！</a>

                                    <span class="am-list-date">2013-09-18</span>
                                </li>
                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">哈哈哈哈哈哈哈哈</a>

                                    <span class="am-list-date">2013-10-14</span>

                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="in-div">
                    <div class="am-list-news-hd am-cf">
                        <!--带更多链接-->
                        <h2 class="t-font"><strong>通知公告</strong></h2>
                        <a href="##" class="">

                            <span class="am-list-news-more am-fr">更多 &raquo;</span>
                        </a>
                    </div>
                    <div data-am-widget="list_news" class="am-list-news am-list-news-default">
                        <!--列表标题-->

                        <div class="am-list-news-bd">
                            <ul class="am-list">

                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">2016年5月3日，。。。。。。。！</a>

                                    <span class="am-list-date">2013-09-18</span>

                                </li>
                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">嘻嘻我信息我嘻嘻嘻嘻嘻嘻</a>

                                    <span class="am-list-date">2013-11-18</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
            <div class="col-md-4 new-div">
                <div class="in-div">
                    <!--列表标题-->
                    <div class="am-list-news-hd am-cf">
                        <!--带更多链接-->
                        <h2 class="t-font"><strong>法律规章</strong></h2>
                        <a href="##" class="">

                            <span class="am-list-news-more am-fr">更多 &raquo;</span>
                        </a>
                    </div>
                    <div data-am-widget="list_news" class="am-list-news am-list-news-default">

                        <div class="am-list-news-bd">
                            <ul class="am-list">

                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">2016年5月3日，。。。。。。。！</a>

                                    <span class="am-list-date">2013-09-18</span>

                                </li>
                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">哈哈哈哈哈哈哈哈</a>

                                    <span class="am-list-date">2013-10-14</span>

                                </li>
                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">嘻嘻我信息我嘻嘻嘻嘻嘻嘻</a>

                                    <span class="am-list-date">2013-11-18</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
            <div class="col-md-4 new-div">
                <div class="in-div">
                    <!--列表标题-->
                    <div class="am-list-news-hd am-cf">
                        <!--带更多链接-->
                        <h2 class="t-font"><strong>价格政策</strong></h2>
                        <a href="##" class="">

                            <span class="am-list-news-more am-fr">更多 &raquo;</span>
                        </a>
                    </div>
                    <div data-am-widget="list_news" class="am-list-news am-list-news-default">

                        <div class="am-list-news-bd">
                            <ul class="am-list">

                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">2016年5月3日，。。。。。。。！</a>

                                    <span class="am-list-date">2013-09-18</span>

                                </li>
                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">哈哈哈哈哈哈哈哈</a>

                                    <span class="am-list-date">2013-10-14</span>

                                </li>
                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">嘻嘻我信息我嘻嘻嘻嘻嘻嘻</a>

                                    <span class="am-list-date">2013-11-18</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
            <div class="col-md-4 new-div">
                <div class="in-div">
                    <!--列表标题-->
                    <div class="am-list-news-hd am-cf">
                        <!--带更多链接-->
                        <h2 class="t-font"><strong>产业政策</strong></h2>
                        <a href="##" class="">

                            <span class="am-list-news-more am-fr">更多 &raquo;</span>
                        </a>
                    </div>
                    <div data-am-widget="list_news" class="am-list-news am-list-news-default">

                        <div class="am-list-news-bd">
                            <ul class="am-list">

                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">2016年5月3日，。。。。。。。！</a>

                                    <span class="am-list-date">2013-09-18</span>

                                </li>
                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">哈哈哈哈哈哈哈哈</a>

                                    <span class="am-list-date">2013-10-14</span>

                                </li>
                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">嘻嘻我信息我嘻嘻嘻嘻嘻嘻</a>

                                    <span class="am-list-date">2013-11-18</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
            <div class="col-md-4 new-div">
                <div class="in-div">
                    <!--列表标题-->
                    <div class="am-list-news-hd am-cf">
                        <!--带更多链接-->
                        <h2 class="t-font"><strong>价格数据</strong></h2>
                        <a href="##" class="">

                            <span class="am-list-news-more am-fr">更多 &raquo;</span>
                        </a>
                    </div>
                    <div data-am-widget="list_news" class="am-list-news am-list-news-default">

                        <div class="am-list-news-bd">
                            <ul class="am-list">

                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">2016年5月3日，。。。。。。。！</a>

                                    <span class="am-list-date">2013-09-18</span>

                                </li>
                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">哈哈哈哈哈哈哈哈</a>

                                    <span class="am-list-date">2013-10-14</span>

                                </li>
                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">嘻嘻我信息我嘻嘻嘻嘻嘻嘻</a>

                                    <span class="am-list-date">2013-11-18</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
            <div class="col-md-4 new-div">
                <div class="in-div">
                    <!--列表标题-->
                    <div class="am-list-news-hd am-cf">
                        <!--带更多链接-->
                        <h2 class="t-font"><strong>理论研究</strong></h2>
                        <a href="##" class="">

                            <span class="am-list-news-more am-fr">更多 &raquo;</span>
                        </a>
                    </div>
                    <div data-am-widget="list_news" class="am-list-news am-list-news-default">

                        <div class="am-list-news-bd">
                            <ul class="am-list">

                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">2016年5月3日，。。。。。。。！</a>

                                    <span class="am-list-date">2013-09-18</span>

                                </li>
                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">哈哈哈哈哈哈哈哈</a>

                                    <span class="am-list-date">2013-10-14</span>

                                </li>
                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">嘻嘻我信息我嘻嘻嘻嘻嘻嘻</a>

                                    <span class="am-list-date">2013-11-18</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
            <div class="col-md-4 new-div">
                <div class="in-div">
                    <!--列表标题-->
                    <div class="am-list-news-hd am-cf">
                        <!--带更多链接-->
                        <h2 class="t-font"><strong>会员服务</strong></h2>
                        <a href="##" class="">

                            <span class="am-list-news-more am-fr">更多 &raquo;</span>
                        </a>
                    </div>
                    <div data-am-widget="list_news" class="am-list-news am-list-news-default">

                        <div class="am-list-news-bd">
                            <ul class="am-list">
                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">2016年5月3日，。。。。。。。！</a>

                                    <span class="am-list-date">2013-09-18</span>
                                </li>
                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">哈哈哈哈哈哈哈哈</a>

                                    <span class="am-list-date">2013-10-14</span>

                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="in-div">
                    <!--列表标题-->
                    <div class="am-list-news-hd am-cf">
                        <!--带更多链接-->
                        <h2 class="t-font"><strong>交流园地</strong></h2>
                        <a href="##" class="">

                            <span class="am-list-news-more am-fr">更多 &raquo;</span>
                        </a>
                    </div>
                    <div data-am-widget="list_news" class="am-list-news am-list-news-default">

                        <div class="am-list-news-bd">
                            <ul class="am-list">

                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">2016年5月3日，。。。。。。。！</a>

                                    <span class="am-list-date">2013-09-18</span>

                                </li>
                                <li class="am-g am-list-item-dated">
                                    <a href="##" class="am-list-item-hd ">嘻嘻我信息我嘻嘻嘻嘻嘻嘻</a>

                                    <span class="am-list-date">2013-11-18</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="row zlight-hhh">
        <ul class="head-ul">
            <li>
                <h4 class="">友情链接</h4>
            </li>
            <li>
                <select class="select1">
                    <option value="all" class="option">xx网</option>
                    <option value="type1" class="option">
                        <a href="#">类别一</a>
                    </option>
                    <option value="type2" class="option">
                        <a href="#">类别一</a>
                    </option>
                </select>
            </li>
            <li>
                <select class="select1">
                    <option value="all" class="option">政府网站</option>
                    <option value="type1" class="option">
                        <a href="#">类别一</a>
                    </option>
                    <option value="type2" class="option">
                        <a href="#">类别一</a>
                    </option>
                </select>
            </li>
            <li>
                <select class="select1">
                    <option value="all" class="option">其他相关</option>
                    <option value="type1" class="option">
                        <a href="#">类别一</a>
                    </option>
                    <option value="type2" class="option">
                        <a href="#">类别一</a>
                    </option>
                </select>
            </li>
            <li>
                <select class="select1">
                    <option value="all" class="option">其他行业</option>
                    <option value="type1" class="option">类别一</option>
                    <option value="type2" class="option">类别二</option>
                </select>
            </li>
        </ul>
    </div>
</div>
<!-- main close -->

</div>
<%--<footer class="footer">--%>
    <%--<p>© 2016 广东省价格和产业发展协会&nbsp;版权所有&nbsp;不得转载--%>
        <%--<a href="##">联系我们</a>--%>
    <%--<p>粤ICP备&nbsp;66666号&nbsp;广州亦云信息技术股份有限公司&nbsp;提供技术支持</p>--%>
<%--</footer>--%>
<jsp:include page="footer.jsp"></jsp:include>


<!-- container close -->
<script src="${ctx}/dist/js/bootstrap.min.js"></script>
<script src="${ctx}/dist/js/index/respond.min.js"></script>
<div data-am-widget="gotop" class="am-gotop am-gotop-fixed">
    <a href="#top" title="">
        <img class="am-gotop-icon-custom" src="http://amazeui.b0.upaiyun.com/assets/i/cpts/gotop/goTop.gif"/>
    </a>
</div>
<span id="111">
		<button class="btn btn-sm btn-default fk1" type="button">
		收起/展开
		</button>
		</span>
<div class="tab">
    关注公众号<br/> 获取更多相关信息
    <img src="${ctx}/imgs/index/code.PNG"/>
</div>
</body>
<script>


    $(document).ready(function () {
        $('#zlight-nav').zlightMenu();
    });
    $().ready(function () {
        $("#111").click(function (event) {
            $(".tab").toggle();

        });
    });
    $(function () {
        $('#btn-search').click(function () {
            console.log('adfau9j');
            var which = $('#select-which').children('option:selected').val();
            var keywords = $('#keywords').val();
            window.location.href = '/back/article/article-search?which=' + which + '&keywords=' + keywords + '&limit=10';
        });
    });
</script>
</html>