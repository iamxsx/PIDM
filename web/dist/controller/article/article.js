/**
 * Created by clouder on 10/09/16.
 */
function queryLevel1Menu(){
    var menu1 = $('#menu1');
    var content = '';
    $.ajax({
        url:'/back/article/queryLevel1Menu',
        type:'get',
        success:function (data) {
            $.each(data,function(i,item){
                content += '' +
                    '<option value="'+item.id+'" >'+item.name+'</option>' +
                    '';
            });
            menu1.append(content);
            bindSelectListener(1);
        },
        error:function (err) {
            alert(err);
        }
    });
}

function querySecondMenu(parentId,which) {
    var menu;
    var content = '';
    if(which == 1){
        menu = $('#menu2');
    }else if (which == 2){
        menu = $('#menu3');
    } else if (which == 3) {
        menu = $('#menu4');
    }
    $.ajax({
        url:'/back/article/querySecondMenu',
        type:'get',
        data:{
            parentId:parentId
        },
        success:function (data) {
            if (data.length != 0){
                content += '<option>-----请选择-----</option>';
                $.each(data,function(i,item){
                    content += '' +
                        '<option value="'+item.id+'">'+item.name+'</option>' +
                        '';
                });
                menu.html('');
                menu.append(content);
                bindSelectListener(which + 1);
            }
        }
    });
}

function bindSelectListener(which) {
    var menu;
    if (which == 1){
        menu  = $('#menu1');
    }else if(which == 2){
        menu = $('#menu2');
    } else if(which == 3){
        menu = $('#menu3');
    }
    menu.change(function () {
        var parentId = $(this).children('option:selected').val();
        querySecondMenu(parentId,which);
    });
}

queryLevel1Menu();

$('#btn-publish').click(function () {
    if ($('#header').val() == ''){
        alert('标题不能为空');
        return;
    }
    if ($('#menu2').val() == ''){
        alert('请选择发布位置');
        return;
    }
    //在这里提交的文章需要去更新数据库
    var html = editor.$txt.html();
    $('#content').val(html);
    //将所选版块的值上传
    $('#location').val($('#menu1').children('option:selected').text());
    console.log($('#menu1').children('option:selected').text());
    $('#article-form').submit();


});
