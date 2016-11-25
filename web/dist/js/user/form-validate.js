/**
 * Created by clouder on 9/25/16.
 */

$("#login-form").validate({
    rules: {
        account:{
            required: true,
            minlength: 4,
            maxlength: 16,
        },
        password: {
            required: true,
            minlength: 8,
            maxlength: 16,
        },
        verifycode: {
            required: true,
            // checkLoginCode: true,
            remote: {
                type: "post",
                url: "/login/testVerifycode",
                data: {
                    verifycode: function() {
                        return  $('#verifycode').val();
                    }
                },
                dataFilter: function(data) {
                    if (data)
                        return true;
                    else {
                        return false;
                    }
                }
            }
        }
    },
    messages: {
        verifycode: {
            remote: "验证码不正确"
        },
        account: {
            minlength: "账号长度不可小于4位",
            maxlength: "账号不得多余16位字符",
        },
        password: {
            minlength: "密码长度不可小于8位",
            maxlength: "密码长度不可大于16位",
        }
    },
    errorElement: "h6",
    errorPlacement: function(error, element) { //错误信息位置设置方法
        error.appendTo( element.parent().parent().next() ); //这里的element是录入数据的对象
    },
})

$("#pForm").validate({
    rules: {
        code:{
            required: true,
            checkCode:true,
        }
    },
    messages: {
        code: {
            checkCode: "验证码不正确"
        }
    },
    errorElement: "em",
    errorPlacement: function(error, element) { //错误信息位置设置方法
        error.appendTo( element.parent().next() ); //这里的element是录入数据的对象
    },
})

$("#form1").validate({
    rules: {
        "user.account": {
            required: true,
            minlength: 4,
            maxlength: 16,
            noChinese: true,
            remote: {
                type: "post",
                url: "/register/noExistAccount",
                data: {
                    account: function() {
                        return  $('#f1-u-acc').val();
                    }
                },
                dataFilter: function(data) {
                    if (data)
                        return false;
                    else
                        return true;
                }
            }
        },
        "user.password": {
            required: true,
            minlength: 8,
            maxlength: 16,
        },
        "rePassword": {
            required: true,
            equalTo: "#f1-password",
        },
        "user.realName": {
            required: true,
        },
        "user.IDcard": {
            required: true,
            IDcard:true,
        },
        "user.phoneNum": {
            required: true,
            mobile: true,
        },
        "user.email": {
            required: true,
            email:true,
        },
        "company.registerNature": {
            required: true,
        },
        "company.city": {
            xiala:true,
        },
        "company.county": {
            xiala:true,
        },
        "company.address": {
            required: true,
        },
        "company.email": {
            required: true,
            email:true,
        },
        "company.zipCode": {
            zipCode: true,
        },
        "company.faxNum": {
            faxNum: true,
        },
        "legalRep.name": {
            required: true,
        },
        "legalRep.jobPosition": {
            required: true,
        },
        "legalRep.officePhoneNum": {
            telephone: true,
        },
        "legalRep.cellPhoneNum": {
            required: true,
            mobile: true,
        },
        "introduceds[0].jobPosition": {
            dependent: true,
        },
        "introduceds[0].officePhoneNum": {
            telephone: true,
        },
        "introduceds[0].cellPhoneNum": {
            dependent: true,
            mobile: true,
        },
        "introduceds[1].jobPosition": {
            dependent: true,
        },
        "introduceds[1].officePhoneNum": {
            telephone: true,
        },
        "introduceds[1].cellPhoneNum": {
            dependent: true,
            mobile: true,
        },
        "designatedContact.name": {
            required: true,
        },
        "designatedContact.jobPosition": {
            required: true,
        },
        "designatedContact.officePhoneNum": {
            telephone: true,
        },
        "designatedContact.cellPhoneNum": {
            required: true,
            mobile: true,
        },
        "company.nature": {
            required: true,
        },
        "company.industry": {
            required: true,
        },
        "company.adminDepartment": {
            required: true,
        },
        "company.employeeNum": {
            required: true,
        },
        "f1-agg": {
            required: true,
        },
        "associationUnitId": {
            required: true,
        },

    },
    messages: {
        "user.account": {
            required: "请输入用户名4--16个英文/数字字符",
            minlength: "请输入用户名4--16个英文/数字字符",
            maxlength: "请输入用户名4--16个英文/数字字符",
            noChinese:"请输入用户名4--16个英文/数字字符",
            remote : "帐号已存在",
        },
       /* "company.city": {
            xiala: "必选"
        }*/
        "company.city": {
            xiala: "必选"
        },
        "company.county": {
            xiala: "必选"
        },
       "introduceds[0].jobPosition": {
           dependent:"填写职务"
       },
        "introduceds[0].cellPhoneNum": {
            dependent:"填写手机"
        },
        "introduceds[1].jobPosition": {
            dependent:"填写职务"
        },
        "introduceds[1].cellPhoneNum": {
            dependent:"填写手机"
        },
    },
    errorElement: "em",
    errorPlacement: function(error, element) { //错误信息位置设置方法
        error.appendTo( element.parent().next() ); //这里的element是录入数据的对象
    },
   /* success: function(li) {
        li.addClass("error checked");
    }*/
})

$("#form2").validate({
    rules: {
        "user.account": {
            required: true,
            minlength: 4,
            maxlength: 16,
            noChinese: true,
            remote: {
                type: "post",
                url: "/register/noExistAccount",
                data: {
                    account: function() {
                        return  $('#f2-u-acc').val();
                    }
                },
                dataFilter: function(data) {
                    if (data)
                        return false;
                    else
                        return true;
                }
            }
        },
        "user.password": {
            required: true,
            minlength: 8,
            maxlength: 16,
        },
        "rePassword": {
            required: true,
            equalTo: "#f2-password",
        },
        "user.realName": {
            required: true,
        },
        "user.IDcard": {
            required: true,
            IDcard:true,
        },
        "user.phoneNum": {
            required: true,
            mobile: true,
        },
        "user.email": {
            required: true,
            email:true,
        },
        "company.registerNature": {
            required: true,
        },
         "company.city": {
         xiala:true,
         },
         "company.county": {
         xiala:true,
         },
        "company.address": {
            required: true,
        },
        "company.zipCode": {
            zipCode: true,
        },
        "company.email": {
            required: true,
            email:true,
        },
        "company.faxNum": {
            faxNum: true,
        },
        "legalRep.name": {
            required: true,
        },
        "legalRep.jobPosition": {
            required: true,
        },
        "legalRep.officePhoneNum": {
            telephone: true,
        },
        "legalRep.cellPhoneNum": {
            required: true,
            mobile: true,
        },
        "introduceds[0].jobPosition": {
            dependent: true,
        },
        "introduceds[0].officePhoneNum": {
            telephone: true,
        },
        "introduceds[0].cellPhoneNum": {
            dependent: true,
            mobile: true,
        },
        "introduceds[0].asctJobPosition": {
            dependent: true,
        },
        "introduceds[1].jobPosition": {
            dependent: true,
        },
        "introduceds[1].officePhoneNum": {
            telephone: true,
        },
        "introduceds[1].cellPhoneNum": {
            dependent: true,
            mobile: true,
        },
        "introduceds[1].asctJobPosition": {
            dependent: true,
        },
        "designatedContact.name": {
            required: true,
        },
        "designatedContact.jobPosition": {
            required: true,
        },
        "designatedContact.officePhoneNum": {
            telephone: true,
        },
        "designatedContact.cellPhoneNum": {
            required: true,
            mobile: true,
        },
        "f1-agg": {
            required: true,
        },
        "associationUnitId": {
            required: true,
        },

    },
    /*remote: {
        url: "register/noExistAccount",     //后台处理程序
        type: "post",               //数据发送方式
        dataType: "json",           //接受数据格式
        data: {                     //要传递的数据
            "user.account": function() {
                return $("#username").val();
            }
        }
    },*/
    messages: {
        "user.account": {
            required: "请输入用户名4--16个英文/数字字符",
            minlength: "请输入用户名4--16个英文/数字字符",
            maxlength: "请输入用户名4--16个英文/数字字符",
            noChinese:"请输入用户名4--16个英文/数字字符",
            remote : "帐号已存在",
        },
         "company.city": {
         xiala: "必选"
         },
        "company.county": {
            xiala: "必选"
        },
        "introduceds[0].jobPosition": {
            dependent:"填写职务"
        },
        "introduceds[0].cellPhoneNum": {
            dependent:"填写手机"
        },
        "introduceds[0].asctJobPosition": {
            dependent:"填写协会职务"
        },
        "introduceds[1].jobPosition": {
            dependent:"填写职务"
        },
        "introduceds[1].cellPhoneNum": {
            dependent:"填写手机"
        },
        "introduceds[1].asctJobPosition": {
            dependent:"填写协会职务"
        },
    },
    errorElement: "em",
    errorPlacement: function(error, element) { //错误信息位置设置方法
        error.appendTo( element.parent().next() ); //这里的element是录入数据的对象
    },
    /* success: function(li) {
     li.addClass("error checked");
     }*/
})

$("#form3").validate({
    rules: {
        "user.account": {
            required: true,
            minlength: 4,
            maxlength: 16,
            noChinese: true,
            remote: {
                type: "post",
                url: "/register/noExistAccount",
                data: {
                    account: function() {
                        return  $('#f3-u-acc').val();
                    }
                },
                dataFilter: function(data) {
                    if (data)
                        return false;
                    else
                        return true;
                }
            }
        },
        "user.password": {
            required: true,
            minlength: 8,
            maxlength: 16,
        },
        "rePassword": {
            required: true,
            equalTo: "#f3-password",
        },
        "user.realName": {
            required: true,
        },
        "user.IDcard": {
            required: true,
            IDcard:true,
        },
        "user.phoneNum": {
            required: true,
            mobile: true,
        },
        "user.email": {
            required: true,
            email:true,
        },
        "company.registerNature": {
            required: true,
        },
        "company.city": {
            xiala:true,
        },
        "company.county": {
            xiala:true,
        },
        "company.address": {
            required: true,
        },
        "company.zipCode": {
            zipCode: true,
        },
        "company.email": {
            required: true,
            email:true,
        },
        "legalRep.name": {
            required: true,
        },
        "legalRep.jobPosition": {
            required: true,
        },
        "legalRep.officePhoneNum": {
            telephone: true,
        },
        "legalRep.cellPhoneNum": {
            required: true,
            mobile: true,
        },
        "legalRep.faxNum": {
            required: true,
            faxNum: true,
        },
        "legalRep.email": {
            required: true,
            email:true,
        },
        "designatedContact.name": {
            required: true,
        },
        "designatedContact.jobPosition": {
            required: true,
        },
        "designatedContact.officePhoneNum": {
            telephone: true,
        },
        "designatedContact.cellPhoneNum": {
            required: true,
            mobile: true,
        },
        "designatedContact.faxNum": {
            required: true,
            faxNum: true,
        },
        "designatedContact.email": {
            required: true,
            email:true,
        },
        "f1-agg": {
            required: true,
        },
        "associationUnitId": {
            required: true,
        },

    },
    /*remote: {
     url: "register/noExistAccount",     //后台处理程序
     type: "post",               //数据发送方式
     dataType: "json",           //接受数据格式
     data: {                     //要传递的数据
     "user.account": function() {
     return $("#username").val();
     }
     }
     },*/
    messages: {
        "user.account": {
            required: "请输入用户名4--16个英文/数字字符",
            minlength: "请输入用户名4--16个英文/数字字符",
            maxlength: "请输入用户名4--16个英文/数字字符",
            noChinese:"请输入用户名4--16个英文/数字字符",
            remote : "帐号已存在",
        },
        "company.city": {
            xiala: "必选"
        },
        "company.county": {
            xiala: "必选"
        },
    },
    errorElement: "em",
    errorPlacement: function(error, element) { //错误信息位置设置方法
        error.appendTo( element.parent().next() ); //这里的element是录入数据的对象
    },
    /* success: function(li) {
     li.addClass("error checked");
     }*/
})

$("#form4").validate({
    rules: {
        "user.account": {
            required: true,
            minlength: 4,
            maxlength: 16,
            noChinese: true,
            remote: {
                type: "post",
                url: "/register/noExistAccount",
                data: {
                    account: function() {
                        return  $('#f4-u-acc').val();
                    }
                },
                dataFilter: function(data) {
                    if (data)
                        return false;
                    else
                        return true;
                }
            }
        },
        "user.password": {
            required: true,
            minlength: 8,
            maxlength: 16,
        },
        "rePassword": {
            required: true,
            equalTo: "#f4-password",
        },
        "user.realName": {
            required: true,
        },
        "user.IDcard": {
            required: true,
            IDcard:true,
        },
        "user.phoneNum": {
            required: true,
            mobile: true,
        },
        "user.email": {
            required: true,
            email:true,
        },
        "company.registerNature": {
            required: true,
        },
        "company.city": {
            xiala:true,
        },
        "company.county": {
            xiala:true,
        },
        "company.address": {
            required: true,
        },
        "company.zipCode": {
            zipCode: true,
        },
        "company.email": {
            required: true,
            email:true,
        },
        "legalRep.name": {
            required: true,
        },
        "legalRep.jobPosition": {
            required: true,
        },
        "legalRep.officePhoneNum": {
            telephone: true,
        },
        "legalRep.cellPhoneNum": {
            required: true,
            mobile: true,
        },
        "legalRep.faxNum": {
            required: true,
            faxNum: true,
        },
        "legalRep.email": {
            required: true,
            email:true,
        },
        "designatedContact.name": {
            required: true,
        },
        "designatedContact.jobPosition": {
            required: true,
        },
        "designatedContact.officePhoneNum": {
            telephone: true,
        },
        "designatedContact.cellPhoneNum": {
            required: true,
            mobile: true,
        },
        "designatedContact.faxNum": {
            required: true,
            faxNum: true,
        },
        "designatedContact.email": {
            required: true,
            email:true,
        },
        "cpnyAcstRep.jobPosition": {
            dependent: true,
        },
        "cpnyAcstRep.officePhoneNum": {
            telephone: true,
        },
        "cpnyAcstRep.cellPhoneNum": {
            dependent: true,
            mobile: true,
        },
        "cpnyAcstRep.faxNum": {
            dependent: true,
            faxNum: true,
        },
        "cpnyAcstRep.email": {
            dependent: true,
            email: true,
        },
        "f1-agg": {
            required: true,
        },
        "associationUnitId": {
            required: true,
        },

    },
    /*remote: {
     url: "register/noExistAccount",     //后台处理程序
     type: "post",               //数据发送方式
     dataType: "json",           //接受数据格式
     data: {                     //要传递的数据
     "user.account": function() {
     return $("#username").val();
     }
     }
     },*/
    messages: {
        "user.account": {
            required: "请输入用户名4--16个英文/数字字符",
            minlength: "请输入用户名4--16个英文/数字字符",
            maxlength: "请输入用户名4--16个英文/数字字符",
            noChinese:"请输入用户名4--16个英文/数字字符",
            remote : "帐号已存在",
        },
        "company.city": {
            xiala: "必选"
        },
        "company.county": {
            xiala: "必选"
        },
        "cpnyAcstRep.jobPosition": {
            dependent:"填写职务"
        },
        "cpnyAcstRep.cellPhoneNum": {
            dependent:"填写手机"
        },
        "cpnyAcstRep.faxNum": {
            dependent:"填写传真"
        },
        "cpnyAcstRep.email": {
            dependent:"填写邮箱"
        },
    },
    errorElement: "em",
    errorPlacement: function(error, element) { //错误信息位置设置方法
        error.appendTo( element.parent().next() ); //这里的element是录入数据的对象
    },
    /* success: function(li) {
     li.addClass("error checked");
     }*/
})

$("#form5").validate({
    rules: {
        "user.account": {
            required: true,
            minlength: 4,
            maxlength: 16,
            noChinese: true,
            remote: {
                type: "post",
                url: "/register/noExistAccount",
                data: {
                    account: function() {
                        return  $('#f5-u-acc').val();
                    }
                },
                dataFilter: function(data) {
                    if (data)
                        return false;
                    else
                        return true;
                 }
            }
        },
        "user.password": {
            required: true,
            minlength: 8,
            maxlength: 16,
        },
        "rePassword": {
            required: true,
            equalTo: "#f5-password",
        },
        "user.realName": {
            required: true,
        },
        "user.IDcard": {
            required: true,
            IDcard:true,
        },
        "user.phoneNum": {
            required: true,
            mobile: true,
        },
        "user.email": {
            required: true,
            email:true,
        },
        "company.registerNature": {
            required: true,
        },
        "company.city": {
            xiala:true,
        },
        "company.county": {
            xiala:true,
        },
        "company.address": {
            required: true,
        },
        "company.zipCode": {
            zipCode: true,
        },
        "company.email": {
            required: true,
            email:true,
        },
        "legalRep.name": {
            required: true,
        },
        "legalRep.jobPosition": {
            required: true,
        },
        "legalRep.officePhoneNum": {
            telephone: true,
        },
        "legalRep.cellPhoneNum": {
            required: true,
            mobile: true,
        },
        "introduceds[0].jobPosition": {
            dependent: true,
        },
        "introduceds[0].officePhoneNum": {
            telephone: true,
        },
        "introduceds[0].cellPhoneNum": {
            dependent: true,
            mobile: true,
        },
        "introduceds[1].jobPosition": {
            dependent: true,
        },
        "introduceds[1].officePhoneNum": {
            telephone: true,
        },
        "introduceds[1].cellPhoneNum": {
            dependent: true,
            mobile: true,
        },
        "designatedContact.name": {
            required: true,
        },
        "designatedContact.jobPosition": {
            required: true,
        },
        "designatedContact.officePhoneNum": {
            telephone: true,
        },
        "designatedContact.cellPhoneNum": {
            required: true,
            mobile: true,
        },
        "company.nature": {
            required: true,
        },
        "company.industry": {
            required: true,
        },
        "company.adminDepartment": {
            required: true,
        },
        "company.employeeNum": {
            required: true,
        },
        "f1-agg": {
            required: true,
        },
        "associationUnitId": {
            required: true,
        },

    },
    /* remote: {
     url: "../register/noExistAccount",     //后台处理程序
     type: "post",               //数据发送方式
     dataType: "json",           //接受数据格式
     data: {                     //要传递的数据
     "account": function() {
     return $('#f-u-acc').val()
     }
     },
     dataType: "html",
     dataFilter: function(data, type) {
     if (data == "true")
     return true;
     else
     return false;
     }
     },*/
    messages: {
        "user.account": {
            required: "请输入用户名4--16个英文/数字字符",
            minlength: "请输入用户名4--16个英文/数字字符",
            maxlength: "请输入用户名4--16个英文/数字字符",
            noChinese:"请输入用户名4--16个英文/数字字符",
            remote : "帐号已存在",
        },
        /* "company.city": {
         xiala: "必选"
         }*/
        "company.city": {
            xiala: "必选"
        },
        "company.county": {
            xiala: "必选"
        },
        "introduceds[0].jobPosition": {
            dependent:"填写职务"
        },
        "introduceds[0].cellPhoneNum": {
            dependent:"填写手机"
        },
        "introduceds[1].jobPosition": {
            dependent:"填写职务"
        },
        "introduceds[1].cellPhoneNum": {
            dependent:"填写手机"
        },
    },
    errorElement: "em",
    errorPlacement: function(error, element) { //错误信息位置设置方法
        error.appendTo( element.parent().next() ); //这里的element是录入数据的对象
    },
     /*success: function(em) {
         em.addClass("error checked");
     }*/
})

/*
jQuery.validator.addMethod("checkLoginCode", function(value, element) {
    $.ajax({
        type: "post",
        url: "../login/testVerifycode",
        data:{
            verifycode: function() {
                return  $('#verifycode').val();
            }
        },
        success:function (data) {
            if (data) {
                return true;
            } else {
                $('#kaptchaImage').attr('src', '../kaptcha.jpg?' + Math.floor(Math.random() * 100));
                return false;
            }
        }
    });
}, "验证码错误");
*/

jQuery.validator.addMethod("mobile", function(value, element) {
    return this.optional(element) || (/^1[34578]\d{9}$/.test(value));
}, "手机格式错误");

jQuery.validator.addMethod("telephone", function(value, element) {
    return this.optional(element) || (/^((0\d{2,3})(-)?)(\d{7,8})((-)?(\d{3,}))?$/.test(value));
}, "电话格式错误");

jQuery.validator.addMethod("faxNum", function(value, element) {
    return this.optional(element) || (/^((0\d{2,3})(-)?)(\d{7,8})((-)?(\d{3,}))?$/.test(value));
}, "传真格式错误");

jQuery.validator.addMethod("IDcard", function(value, element) {
    return this.optional(element) || (/^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/.test(value));
}, "身份证格式错误");

jQuery.validator.addMethod("zipCode", function(value, element) {
    return this.optional(element) || ( /^[1-9][0-9]{5}$/.test(value));
}, "邮编格式错误");


jQuery.validator.addMethod("checkCode",function(value,element,param){
        var code = $("#checkFormCode").val();
        if (value.toLowerCase() != code.toLowerCase()) {
            /*$("#fcodediv").css("color","#b94a48")
            $("#pCode").css("border-color","#b94a48");*/
            return false;
        }
        return true;
    }
    //,$.validator.format("请输入数字{0}位以内")
);

jQuery.validator.addMethod("noChinese",function(value,element,param){
        return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
    }
    //,$.validator.format("请输入数字{0}位以内")
);

jQuery.validator.addMethod("xiala",function(value,element,param){
        if (value == "广东的城市" || value == "所在区/县") {
            // alert("hello");
            return false;
        }
        return true;
    }
    //,$.validator.format("请输入数字{0}位以内")
);

jQuery.validator.addMethod("dependent",function(value,element,param){
        if ($("#f1-introduced1").val() != "" || $("#f1-introduced1").val().length > 0) {
            if(value == null || value.length == 0) {
                return false;
            }
        }
        if ($("#f1-introduced2").val() != "" || $("#f1-introduced2").val().length > 0) {
            if(value == null || value.length == 0) {
                return false;
            }
        }
        if ($("#f2-introduced1").val() != "" || $("#f2-introduced1").val().length > 0) {
            if(value == null || value.length == 0) {
                return false;
            }
        }
        if ($("#f2-introduced2").val() != "" || $("#f2-introduced2").val().length > 0) {
            if(value == null || value.length == 0) {
                return false;
            }
        }
        /*if ($("#f1-introduced3").val() != "" || $("#f1-introduced3").val().length > 0) {
            if(value == null || value.length == 0) {
                return false;
            }
        }
        if ($("#f1-introduced4").val() != "" || $("#f1-introduced4").val().length > 0) {
            if(value == null || value.length == 0) {
                return false;
            }
        }*/
        if ($("#f5-introduced1").val() != "" || $("#f1-introduced1").val().length > 0) {
            if(value == null || value.length == 0) {
                return false;
            }
        }
        if ($("#f5-introduced2").val() != "" || $("#f1-introduced2").val().length > 0) {
            if(value == null || value.length == 0) {
                return false;
            }
        }
        return true;
    }
    //,$.validator.format("请输入数字{0}位以内")


);