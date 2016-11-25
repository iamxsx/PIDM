/**
 * Created By IntelliJ IDEA
 * @Author: AngryFeng
 * @Date: 16-10-14 下午10:03
 */

$("#register-form").validate({
    rules: {
        "user.password": {
            required: true,
            minlength: 8,
            maxlength: 16,
        },
        rePassword: {
            required: true,
            equalTo: "#user-pw",
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
        "company.name": {
            required: true,
        },
        "company.nature": {
            required: true,
        },
        "company.address": {
            required: true,
        },
        "company.zipCode": {
            required: true,
            zipCode: true,
        },
        "legalRep.name": {
            required: true,
        },
        "legalRep.jobPosition": {
            required: true,
        },
        "legalRep.officePhoneNum": {
            required: true,
            telephone: true,
        },
        "legalRep.cellPhoneNum": {
            required: true,
            mobile: true,
        },
        "company.join": {
            join: true,
        },
        "introduceds[0].name": {
            dependentUnit: true,
        },
        "introduceds[0].jobPosition": {
            dependentName0: true,
        },
        "introduceds[0].asctJobPosition": {
            dependentName0: true,
        },
        "introduceds[0].chapterJobPosition": {
            dependentName0: true,
        },
        "introduceds[0].email": {
            dependentName0: true,
            email: true,
        },
        "introduceds[0].officePhoneNum": {
            dependentName0: true,
            telephone: true,
        },
        "introduceds[0].cellPhoneNum": {
            dependentName0: true,
            mobile: true,
        },
        "introduceds[1].name": {},
        "introduceds[1].jobPosition": {
            dependentName1: true,
        },
        "introduceds[1].asctJobPosition": {
            dependentName1: true,
        },
        "introduceds[1].chapterJobPosition": {
            dependentName1: true,
        },
        "introduceds[1].email": {
            dependentName1: true,
            email: true,
        },
        "introduceds[1].officePhoneNum": {
            dependentName1: true,
            telephone: true,
        },
        "introduceds[1].cellPhoneNum": {
            dependentName1: true,
            mobile: true,
        },
        "designatedContacts[0].name": {
            required: true,
        },
        "designatedContacts[0].jobPosition": {
            required: true,
        },
        "designatedContacts[0].officePhoneNum": {
            required: true,
            telephone: true,
        },
        "designatedContacts[0].cellPhoneNum": {
            required: true,
            mobile: true,
        },
        "designatedContacts[0].email": {
            required: true,
            email: true,
        },
        "designatedContacts[0].faxNum": {
            faxNum: true,
        },
        "designatedContacts[0].onlineNum": {
            required: true,
            checkLineNum: true,
        },
        "designatedContacts[1].jobPosition": {
            dependentName2: true,
        },
        "designatedContacts[1].officePhoneNum": {
            dependentName2: true,
            telephone: true,
        },
        "designatedContacts[1].cellPhoneNum": {
            dependentName2: true,
            mobile: true,
        },
        "designatedContacts[1].email": {
            dependentName2: true,
            email: true,
        },
        "designatedContacts[1].faxNum": {
            faxNum: true,
        },
        "designatedContacts[1].onlineNum": {
            dependentName2: true,
            checkLineNum: true,
        },
        "company.introduction": {
            maxlength: 300,
        },
        file: {
            required: true,
        }
    },
    messages: {
        "user.password": {
            minlength: "密码长度不可小于8位",
            maxlength: "密码长度不可大于16位",
        },
        "company.zipCode": {
            zipCode: "邮编应为6位数字组成",
        },
        "company.introduction": {
            maxlength: "单位简介不可300字",
        },
        file: {
            required: "请上传附件",
        }
    },
    errorElement: "h6",
    errorPlacement: function (error, element) { //错误信息位置设置方法
        error.appendTo(element.parent()); //这里的element是录入数据的对象
    },
})

jQuery.validator.addMethod("zipCode", function (value, element) {
    return this.optional(element) || ( /^[1-9][0-9]{5}$/.test(value));
}, "提示：邮编格式错误");

jQuery.validator.addMethod("mobile", function (value, element) {
    return this.optional(element) || (/^1[34578]\d{9}$/.test(value));
}, "提示：手机格式错误");

jQuery.validator.addMethod("telephone", function (value, element) {
    return this.optional(element) || (/^((0\d{2,3})(-)?)(\d{7,8})((-)?(\d{3,}))?$/.test(value));
}, "提示：电话格式错误");

jQuery.validator.addMethod("faxNum", function (value, element) {
    return this.optional(element) || (/^((0\d{2,3})(-)?)(\d{7,8})((-)?(\d{3,}))?$/.test(value));
}, "提示：传真格式错误");

jQuery.validator.addMethod("IDcard", function (value, element) {
    return this.optional(element) || (/^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/.test(value));
}, "提示：身份证格式错误");

jQuery.validator.addMethod("dependentUnit", function (value, element) {
    if ($("input[name='company.associationUnit']").get(0).checked == true || $("input[name='company.chapterUnit']").get(0).checked == true || $("input[name='company.chapterUnit']").get(1).checked == true) {
        if (value == null || value.trim() == "") {
            return false;
        }
    }
    return true;
}, "提示：至少要一名推荐人");

jQuery.validator.addMethod("dependentName1", function (value, element) {
    if ($("#intr1-name").val().trim() != "") {
        if (value == null || value.trim() == "") {
            return false;
        } else {
            return true;
        }
    } else {
        return true;
    }

}, "提示：不可为空");

jQuery.validator.addMethod("dependentName0", function (value, element) {
    if ($("#intr0-name").val().trim() != "") {
        if (value == null || value.trim() == "") {
            return false;
        } else {
            return true;
        }
    } else {
        return true;
    }

}, "提示：不可为空");

jQuery.validator.addMethod("dependentName2", function (value, element) {
    if ($("#desiCont-name").val().trim() != "") {
        if (value == null || value.trim() == "") {
            return false;
        } else {
            return true;
        }
    } else {
        return true;
    }

}, "提示：不可为空");

jQuery.validator.addMethod("checkLineNum", function (value, element) {
    return this.optional(element) || (/^[a-zA-Z0-9_]+$/.test(value));
}, "提示：QQ号/微信号格式错误");
