/**
 * Created by clouder on 16-9-22.
 */


var offset = 1;
var limit = 10;

/**
 * 根据具体的菜单项获取文章数量
 * @param menuName
 */
function getArticleCount(menuId,menuName) {
    $.ajax({
        url: '/getArticleCountByMenuName',
        data: {
            menuId: menuId
        },
        success: function (total) {
            initPaginator(total,menuId,menuName);
        },
        error: function () {
            alert('获取数量失败');
        }
    });
}

function initPaginator(total,menuId,menuName) {
    var totalPages = Math.ceil(total / limit);
    var options = {
        bootstrapMajorVersion: 3,
        currentPage: offset,
        totalPages: totalPages,
        onPageClicked: function (e, originalEvent, type, page) {
            offset = page;
            findArticleByMenuName(menuId,menuName);
        }
    }
    $('#pagn').bootstrapPaginator(options);
}


/**
 * 根据具体的菜单项获取文章
 * @param menuName
 */
function findArticleByMenuName(menuId,menuName) {
    $.ajax({
        url: '/findArticleByMenuName',
        data: {
            menuId: menuId,
            offset: offset,
            limit: limit
        },
        success: function (data) {
            console.log(data);
            var table = $('#article-table');
            table.html('');
            var content = '';
            $.each(data, function (i, item) {
                content += '' +
                    '<tr><td>' +
                    '<p style="width: 500px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">' +
                    '<a class="list" href="/article/detail/'+item.id+'/'+menuName+'">' +
                    '' + item.header +
                    '</a>' +
                    '</p></td>' +
                    '<td>' +
                    '<span class="time">' +
                    '' + item.publishTime +
                    '</span>' +
                    '</td></tr>';
            });
            table.append(content);
        },
        error: function () {
            alert('获取文章列表失败');
        }
    });
}




