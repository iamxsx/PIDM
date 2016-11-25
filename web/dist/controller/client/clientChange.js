/**
 * Created by clouder on 9/17/16.
 */
$('#save-button').click(function () {
        if ($('#account').val()!=''&&$('#password').val()!=''&&$('#realName').val()!=''&&$('#conpassword').val()!=''&&$('#password').val()!=''&&$('#password').val()!=''
        &&$('#phoneNum').val()!=''&&$('#IDcard').val()!=''&&$('#email').val()!=''
        &&$('#compayname').val()!=''&&$('#nature').val()!=''&&$('#address').val()!=''
        &&$('#lename').val()!=''&&$('#lejob').val()!=''&&$('#lecellphone').val()!=''
        &&$('#spname').val()!=''&&$('#spjob').val()!=''&&$('#spcellphone').val()!=''
        &&$('#spemail').val()!=''){
        if($('#password').val() == $('#conpassword').val()){
            reg = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
            if(!reg.test($('#spemail').val())||reg.test($('#email').val())){
                $(this).attr('data-content',"邮箱格式错误,请检查后重新输入!");
                $(this).popover('show');
            }else {
                $('#save-button').attr('data-content',"");
                $(this).popover('destroy');
                var uid;
                var str = location.href;
                var num = str.indexOf("=");
                uid = str.substr(num + 1);
                var InfoList = new Array();
                InfoList[0] = uid;
                InfoList[1] = $('#account').val();
                InfoList[2] = $('#password').val();
                InfoList[3] = $('#realName').val();
                InfoList[4] = $('#phoneNum').val();
                InfoList[5] = $('#IDcard').val();
                InfoList[6] = $('#email').val();
                InfoList[7] = $('#compayname').val();
                InfoList[8] = $('#nature').val();
                InfoList[9] = $('#address').val();
                InfoList[10] = $('#zip_code').val();
                InfoList[11] = $('#lename').val();
                InfoList[12] = $('#lejob').val();
                InfoList[13] = $('#leofficephone').val();
                InfoList[14] = $('#lecellphone').val();
                InfoList[15] = $('#spname').val();
                InfoList[16] = $('#spjob').val();
                InfoList[17] = $('#spofficephone').val();
                InfoList[18] = $('#spcellphone').val();
                InfoList[19] = $('#spemail').val();
                InfoList[20] = $('#spfaxnum').val();
                InfoList[21] = $('textarea[name="des1"]').val();
                InfoList[22] = $('textarea[name="des2"]').val();



                $.ajax({
                 url: "/back/user/changeUser?InfoList="+InfoList,
                 type: "get",
                 success: function () {
                 window.location.href = "/back/user/clientRecordInfo?uid="+uid;
                 }
                 });
            }
        }else{
            $('#save-button').attr('data-content',"两次输入的密码不一致！");
            $('#save-button').popover('show');
        }

    }else{
        $('#save-button').attr('data-content',"请填写带*的选项！");
        $('#save-button').popover('show');
    }

});

$(function () {
    $('#save-button').popover();
});



