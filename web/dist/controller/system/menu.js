/**
 * Created by clouder on 16-9-18.
 */

$(function () {

    function getTree() {
        $.ajax({
            url: 'menu/get-menu',
            type: 'get',
            success: function (data) {
                initTreeview(data);
            }
        });
    }

    function initTreeview(data) {
        $('#tree').treeview({
            data: data,
            onNodeSelected: function (event, data) {
                // console.log(event);
                $('#menu-id').val(data.id);
                $('#menu-name').val(data.text);
                $('#menu-url').val(data.url);
                $('#menu-icon').val(data.icon);
                //生成单选框
                var radio = $('#radio-open-way');
                radio.html('');
                var flag1 = '';
                var flag2 = '';
                if (data.openWay == 1) {
                    flag1 = 'checked';
                    flag2 = '';
                } else {
                    flag2 = 'checked';
                    flag1 = '';
                }
                radio.append('' +
                    '<label class="radio-inline">' +
                    '<input type="radio" name="open-way"  value="0" ' + flag1 + '> 当前菜单' +
                    '</label>' +
                    '<label class="radio-inline">' +
                    '<input type="radio" name="open-way"  value="1" ' + flag2 + '> 新建窗口' +
                    '</label>' +
                    '');
                //生成单选框
                var radio_canPublish = $('#radio-canPublish');
                //是否可以发布
                var flag3 = '';
                var flag4 = ''
                if (data.canPublish == 0){
                    flag3 = 'checked';
                    flag4 = '';
                }else {
                    flag3 = '';
                    flag4 = 'checked';
                }
                radio_canPublish.html('');
                radio_canPublish.append('' +
                    '<label class="radio-inline">' +
                    '<input type="radio" name="canPublish" value="0"' + flag3 + '> 允许' +
                    '</label>' +
                    '<label class="radio-inline">' +
                    '<input type="radio" name="canPublish" value="1"' + flag4 + '>  不允许' +
                    '</label>' +
                    '');
                //是否可以显示
                var radip_isHidden = $('#radio-isHidden');
                var flag5 = '';
                var flag6 = '';
                if (data.isHidden == 0){
                    flag5 = '';
                    flag6 = 'checked';
                }else {
                    flag5 = 'checked';
                    flag6 = '';
                }
                radip_isHidden.html('');
                radip_isHidden.append('' +
                    '<label class="radio-inline">' +
                    '<input type="radio" name="isHidden" value="1"' + flag5 + '> 显示' +
                    '</label>' +
                    '<label class="radio-inline">' +
                    '<input type="radio" name="isHidden" value="0"' + flag6 + '>  不显示' +
                    '</label>' +
                    '');
            }

        });
    }

    getTree();

    $('#btn-update-menu').click(function () {
        var form = $('#menu-form');
        form.bootstrapValidator('validate');
        if(!form.data('bootstrapValidator').isValid()){
            return;
        }
        $.ajax({
            url: 'menu/updateMenu',
            type: 'get',
            data: {
                id:$('#menu-id').val(),
                name: $('#menu-name').val(),
                url: $('#menu-url').val(),
                icon: $('#menu-icon').val(),
                canPublish:$('input[name= canPublish ]:checked').val(),
                isHidden:$('input[name = isHidden]:checked').val()
            },
            success:function () {
                window.location.reload();
            },
            error:function () {
                alert('更新菜单失败');
            }
        });

    });

    $('#menu-form').bootstrapValidator({
        fields: {
            menuName: {
                validators: {
                    notEmpty: {
                        message: '菜单名不可为空'
                    }
                }
            },
            menuUrl: {
                validators: {
                    notEmpty: {
                        message: '菜单的 url 不可为空'
                    }
                }
            },
        }
    });


});