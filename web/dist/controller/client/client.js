/**
 * Created by clouder on 9/17/16.
 */

$(function () {
    //删除帐号
    $('#delete-button').click(function () {
        $("input[name='radio']").each(function () {
            if(this.checked){
                var uid = $(this).val();
                if(confirm("确认要删除该帐号吗？")){
                    $.ajax({
                        url:'/back/user/deleteUserInfo?uid='+uid,
                        type:'get',
                        success:function (data) {
                            alert("删除成功");
                        },
                        error:function (err) {
                            alert('删除失败！')
                        }

                    });
                    $(this).parents('tr').remove();
                }

            }
        });
    });

    //查看详细信息
    $('#show-user').click(function () {
        $("input[name='radio']").each(function () {
            if(this.checked){
                var uid = $(this).val();
                window.location.href = "/back/user/clientRecordInfo?uid="+uid;
            }
        });
    });
    //修改信息
    $('#changebutton').click(function () {
        $("input[name='radio']").each(function () {
            if(this.checked){
                var uid = $(this).val();
                window.location.href = "/back/user/changeclient?uid="+uid;
            }
        });
    });

    //添加公司帐号
    $('#add-user').click(function () {
        window.location.href = "/back/user/getCompany";
    });

    //导出客户信息为excel
    $("#export-excel").click(function () {
        window.location.href='/back/user/getUserSimpleInfo?keyWord='+$('#Search').val()+'&&searchCondition='+$('#searchcon').val()
    });
});
