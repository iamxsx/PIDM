/**
 * Created by clouder on 16-9-27.
 */

/**
 * 设置二级菜单高亮
 */
function setUpActiveSecondNav(menuName) {
    var lis = $('.left-nav-item>a');
    $.each(lis,function (i,item) {
        if ($(item).text() === menuName){
            $(item).parent().parent().addClass('active');
            return false;
        }
    });
}

function getSecondMenuByMenuName(menuName,secondMenuName) {
    $.ajax({
        url:'/back/menu/getSecondMenuByMenuName',
        data:{
            menuName:menuName
        },
        success:function (menus) {
            var left_nav = $('#left-nav');
            var content = '';
            $.each(menus,function (i,menu) {
                content += '' +
                    '<li>' +
                        '<div class="left-nav-item">' +
                            '<a href="/'+menu.url+'&menuId='+menu.id+'">' +
                                 ''+menu.name+'' +
                            '</a>' +
                        '</div>' +
                    '</li>';
            });
            left_nav.append(content);
            setUpActiveSecondNav(secondMenuName);
        },
        error:function () {
            alert('获取次级菜单错误');
        }
    });
}

