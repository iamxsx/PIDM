/**
 * Created by clouder on 16-9-16.
 */

var _PageHeight = document.documentElement.clientHeight,
    _PageWidth = document.documentElement.clientWidth;
//计算loading框距离顶部和左部的距离（loading框的宽度为215px，高度为61px）
var _LoadingTop = _PageHeight > 61 ? (_PageHeight - 61) / 2 : 0,
    _LoadingLeft = _PageWidth > 300 ? (_PageWidth - 300) / 2 : 0;
//在页面未加载完毕之前显示的loading Html自定义内容
var _LoadingHtml = '<div id="loadingDiv" style="position:absolute;left:0;width:100%;height:2000px;top:0;background:#f3f8ff;filter:alpha(opacity=100);z-index:10000;">' +
    '<div style="position: absolute; cursor1: wait; left: ' + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width: auto; height: 57px; line-height: 57px; padding-left: 50px; padding-right: 5px;   color: #696969; font-family:\'Microsoft YaHei\'; font-size: 24px">' +
    '<i class="icon-spinner icon-spin icon-large"></i>&nbsp;&nbsp; &nbsp;页面加载中，请稍等...' +
    '</div></div>';
//呈现loading效果
document.write(_LoadingHtml);
document.onreadystatechange = completeLoading;
//加载状态为complete时移除loading效果
function completeLoading() {
    if (document.readyState == "complete") {
        var loadingMask = document.getElementById('loadingDiv');
        loadingMask.parentNode.removeChild(loadingMask);
    }
}


$(function () {
    $('#btn-search').click(function () {
        var which = $('#select-which').children('option:selected').val();
        var keywords = $('#keywords').val();
        window.location.href = '/back/article/article-search?which=' + which + '&keywords=' + keywords + '&limit=20';
    });
});

/**
 * 设置一级菜单高亮
 * @param index
 */
function setUpActiveNav(index) {
    var lis = $('#zlight-main-nav>li>a');
    $.each(lis, function (i, item) {
        if (i == index) {
            $(item).addClass('active');
            return false;
        }
    });
}

function xhr(url, method, data, success, error) {
    $.ajax({
        url: url,
        type: method,
        data: data,
        success: function (data) {
            success(data);
        },
        error: function () {
            error();
        }
    });
}

function getCarouselNews() {
    xhr(
        '/article/getCarouselNews',
        'get',
        {},
        function (data) {

        },
        function () {

        }
    );
}

/**
 * 获取各版块文章
 * @param sectionName
 * @param section
 */
function getArticleBySection(sectionName, section) {
    section.html('');
    var content = '';
    $.ajax({
        url: '/article/findArticleBySection',
        data: {
            sectionName: sectionName
        },
        type: 'post',
        success: function (data) {
            $.each(data, function (i, item) {
                content += '' +
                    '<li class="am-g am-list-item-dated">';
                content +=
                '<a title="'+item.header+'" href="/article/detail/' + item.id + '/'+sectionName+'" class="am-list-item-hd ">' + item.header ;
                content  += '</a>';
                if (i == 0 || i == 1 || i == 2) {
                    content += '<img class="new" src="/imgs/new.gif">';
                }
                content += '<span class="am-list-date">' + item.publishTime + '</span>';
                '</li>';
            });
            section.append(content);
        },
        error: function () {
            // alert('获取失败');
        }
    });
}

/**
 *
 * @param sectionName
 * @param section
 */
function getArticleBySecondSection(sectionName, section) {
    var content = '';
    $.ajax({
        url: '/article/findArticlesBySecondSection?sectionName=' + sectionName,
        type: 'get',
        success: function (data) {
            $.each(data, function (i, item) {
                content += '' +
                    '<li class="am-g am-list-item-dated">' +
                    '<a href="/article/detail/' + item.id + '/'+sectionName+'" class="am-list-item-hd ">' + item.header + '' +
                    '</a>' +
                    '<span class="am-list-date">' + item.publishTime + '</span>' +
                    '</li>';
            });
            section.append(content);
        },
        error: function () {
            alert('获取失败');
        }
    });
}


/**
 * 生成导航条
 * @param index 代表当前页
 */
function generateNav(index) {
    $.ajax({
        url: '/menu/get-front-menu',
        type: 'get',
        success: function (data) {
            var nav = $('#zlight-main-nav');
            var content = '';
            $.each(data, function (i, item) {
                //console.log(item);
                content += '' +
                    '<li class="zlight-dropdown">' +
                    '<a href="/' + item.url + '&menuId='+item.id+'">' + item.text + '' +
                    // '<i class="' + item.icon + '"></i>' +
                    '</a>';
                content = showNav(item.nodes, content);
                content += '</li>';
            });
            nav.append(content);
            setUpActiveNav(index);
            pingjie();
        },
        error: function () {
            alert('获取菜单失败');
        }
    });
}

/**
 * 拼接
 * @param data
 * @param content
 */
function showNav(data, content) {
    if (data == null || data.length <= 0) {
        return content;
    }
    content += '<ul class="zlight-submenu">';
    $.each(data, function (i, item) {
        content += '' +
            '<li class="zlight-dropdown">' +
            '<a href="/' + item.url + '&menuId=' + item.id + '">' + item.text + '' +
            '<i class="' + item.icon + '"></i>' +
            '</a>';
        //递归输出次级菜单
        content = showNav(item.nodes, content);
        content += '</li>';
    });
    content += '</ul>'
    return content;
}

/**
 * 获得交流园地板块
 */
function getExchangeSection() {
    $.ajax({
        url:'communicate/othersCommunicate',
        data:{
            offset:0,
            limit:3,
        },
        success:function (data) {
            $.each(data.rows,function (i,item) {
                $("#exchange-field-ul").append(
                        '<li class="am-g am-list-item-dated">'+
                            '<a href="/section/to-consult-section?menu=交流园地&menuId=9" class="am-list-item-hd ">' + item.title + '</a>'+
                            '<span class="am-list-date">' + item.askTime + '</span>'+
                        '</li>'
                );
            })
        }
    })
}


