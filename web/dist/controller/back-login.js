/**
 * Created by phycholee on 10/19/16.
 */
$(function () {


    function validate(isLogin){
        $('.resultMsg').text('');

        var username = $('#username').val();
        var password = $('#password').val();

        if (username.trim() == ''){
            $('#username-msg').text('账号不能为空！');
        }
        if (password.trim() == ''){
            $('#password-msg').text('密码不能为空！');
        }

        if (isLogin){
            var codeInput = $('#code-input').val();
            if (codeInput.trim() == ''){
                $('#code-msg').text('验证码不能为空！');
            }
            return !(username.trim() == '' || password.trim() == '' || codeInput == '');
        }

        return !(username.trim() == '' || password.trim() == '');

    }

    $('#loginButton').on('click', function () {
        var form = $('#loginForm');
        if (!validate(true)){
            return;
        }
        form.attr('action', '/back/login');
        form.submit();
    });

    //获取手机短信验证码,登陆
    $('#getPhoneMsgBtn').on('click', function () {
        if (!validate(false)){
            return;
        }

        var username = $('#username').val().trim();
        var password = $('#password').val().trim();

        $.ajax({
            cache:false,
            url:'code/getLoginPhoneMsg',
            type: 'post',
            data:{
                username : username,
                password : password
            },
            success:function (data) {
                if (data == 'sent'){
                    $('#code-msg').text('重复获取验证码过快！');
                }else if(data == 'errorAccount'){
                    //用户名密码错误
                    $('#code-msg').text('请填写正确的账号密码！');
                }
            },
            error:function (data) {
                $('#code-msg').text('获取验证码失败！');
            }
        });

        $('#getPhoneMsgBtn').attr("disabled", true);
        clock();
    });
    var countNum = 60;

    function clock(){
        var btn = $('#getPhoneMsgBtn');
        if(countNum == 0){
            btn.attr("disabled", false);
            btn.text('获取验证码');
            countNum = 60;
            return;
        }else{
            btn.text(countNum+'秒后重新获取');
            countNum--;
        }
        setTimeout(function () {
            clock()
        }, 1000);
    }

});