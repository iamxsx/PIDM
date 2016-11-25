var code ; //在全局 定义验证码
     function createCode()
     {
       code = "";
       var codeLength = 6;//验证码的长度
       var checkCode = document.getElementById("checkCode");
       var selectChar = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');//所有候选组成验证码的字符，当然也可以用中文的

       for(var i=0;i<codeLength;i++)
       {


       var charIndex = Math.floor(Math.random()*36);
       code +=selectChar[charIndex];


       }
//       alert(code);
       if(checkCode)
       {
         checkCode.className="code";
         checkCode.value = code;
       }

     }

      function validate ()
     {
       var inputCode = document.getElementById("InputCode").value;
       if(inputCode.length <=0)
       {
           alert("请输入验证码！");
       }
       else if(inputCode.toLowerCase()  != code.toLowerCase()  )
       {
          alert("验证码输入错误！");
          createCode();//刷新验证码
       }
       else
       {
         alert("^-^ OK");
       }

       }
          
(function ($) {
    $.extend({
        "login": function (loginBtn) {
            // alert("进来了吗");
            if ($(loginBtn).length === 0) { alert('找不到登录按钮，请检查按钮是否填写正确。'); return false; }
            var $win = $(window), $loginDiv = $('#_login_div_quick_'), $login = $('#login_button'), $errTip = $('#error_tips'), $capsLock = $('#caps_lock_tips'), showLogin = function () {
                $loginDiv.css({
                    top: 40 + 'px',
                    right: 0 + 'px',
                    display: 'block'
                });
            }, $userTip = $('#uin_tips'), $passTip = $('#pwd_tips'), $operateTip = $('#operate_tips'), $delUserInput = $('#uin_del'), $u = $('#u'), $p = $('#p'), $switch = $('#qrswitch_logo'), $qr = $('#web_qr_login_show');
            $(loginBtn).click(function () { showLogin(); });
            $win.resize(function () { if ($loginDiv.is(':visible')) { showLogin(); } });
            $('#close').click(function () { $loginDiv.hide(); });
            $u.add($p).bind({
                'focus': function () {
                    var $this = $(this), $currTip = $this.attr('id') === 'u' ? ($operateTip.show(), $userTip) : $passTip;
                    $this.parent().css('background-position-y', '-45px');
                    $currTip.css('color', '#ddd');
                },
                'blur': function () {
                    var $this = $(this), $currTip = $this.attr('id') === 'u' ? ($operateTip.hide(), $userTip) : $passTip;
                    $this.parent().css('background-position-y', '-1px');
                    $currTip.css('color', '#aaa');
                    if ($currTip === $passTip && $capsLock.is(':visible')) { $capsLock.hide(); }
                },
                'input': function (e) {
                    var $this = $(this), $currTip = $this.attr('id') === 'u' ? $userTip : $passTip;
                    if ($this.val()) {
                        if ($currTip.is(':visible')) {
                            $currTip.hide();
                            if ($currTip === $userTip) { $delUserInput.show(); }
                        }
                    } else {
                        $currTip.show();
                        if ($currTip === $userTip) { $delUserInput.hide(); }
                    }
                }
            });
            $p.keypress(function (e) {
                if (((e.keyCode >= 65 && e.keyCode <= 90) && !e.shiftKey) || ((e.keyCode >= 97 && e.keyCode <= 122) && e.shiftKey)) { $capsLock.show(); } else { $capsLock.hide(); }
            });
            $delUserInput.click(function () { $u.val('').focus(); $userTip.show(); $delUserInput.hide(); });
            $switch.click(function () {
                if ($switch.attr('class') === 'qrswitch_logo') {
                    $switch.attr('class', 'qrswitch_logo_qr');
                    $qr.animate({ top: '-265px' }, 200);
                } else {
                    $switch.attr('class', 'qrswitch_logo');
                    $qr.animate({ top: '0px' }, 200);
                }
            });
            $login.click(function () {
                if (!/^\d{5,11}$/.test($u.val())) {
                    $errTip.find('#err_m').text('请输入正确的账号！').end().show();
                    setTimeout(function () { $errTip.hide(); }, 5000);
                    return false;
                } else if (!$p.val()) {
                    $errTip.find('#err_m').text('您还没有输入密码！').end().show();
                    setTimeout(function () { $errTip.hide(); }, 5000);
                    return false;
                } else {
                    $('#loading_tips').show();
                }
            });
        }
    });
}(jQuery));