/**
 * Created by clouder on 9/21/16.
 */
$(function () {
    /**
     * 判断值是否为空
     */
    $('#pForm').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        excluded: [],
        fields: {
            account: {
                message: '帐号非法',
                validators: {
                    notEmpty: {
                        message: '帐号不可为空'
                    },
                    stringLength: {
                        min: 4,
                        max: 16,
                        message: '帐号长度必须在4～16'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '账户只能包含数字或字母'
                    },
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: '邮箱不可为空'
                    },
                    emailAddress: {
                        message: '不合法的邮箱'
                    }
                }
            },
            password: {
                message: '密码非法',
                validators: {
                    notEmpty: {
                        message: '密码不可为空'
                    },
                    stringLength: {
                        min: 8,
                        max: 16,
                        message: '密码长度必须在8～16'
                    },
                    identical: {
                        field: 'repassword',
                        message: '新密码与确定密码不一致'
                    },
                }
            },
            repassword: {
                message: '密码非法',
                validators: {
                    notEmpty: {
                        message: '密码不可为空'
                    },
                    stringLength: {
                        min: 8,
                        max: 16,
                        message: '密码长度必须在8～16'
                    },
                    identical: {
                        field: 'password',
                        message: '新密码与确定密码不一致'
                    },
                }
            },
            /* code: {
             message: '验证码非法',
             validators: {
             notEmpty: {
             message: '验证码不可为空'
             },
             identical: {
             field: 'fcode',
             message: '验证码不正确'
             },
             },
             },*/
            verifycode: {
                message: '邮箱验证码非法',
                validators: {
                    notEmpty: {
                        message: '邮箱验证码不可为空'
                    },
                },
            }
        }
    });

    // $('#login-form').bootstrapValidator({
    //     feedbackIcons: {
    //         valid: 'glyphicon glyphicon-ok',
    //         invalid: 'glyphicon glyphicon-remove',
    //         validating: 'glyphicon glyphicon-refresh'
    //     },
    //     excluded: [],
    //     fields: {
    //         account: {
    //             message: '帐号非法',
    //             validators: {
    //                 notEmpty: {
    //                     message: '帐号不可为空'
    //                 },
    //                 stringLength: {
    //                     min: 4,
    //                     max: 16,
    //                     message: '帐号长度必须在4～16'
    //                 },
    //                 regexp: {
    //                     regexp: /^[a-zA-Z0-9_\.]+$/,
    //                     message: '账户只能包含数字或字母'
    //                 },
    //             }
    //         },
    //         password: {
    //             message: '密码非法',
    //             validators: {
    //                 notEmpty: {
    //                     message: '密码不可为空'
    //                 },
    //                 stringLength: {
    //                     min: 8,
    //                     max: 16,
    //                     message: '密码长度必须在8～16'
    //                 },
    //                 identical: {
    //                     field: 'repassword',
    //                     message: '新密码与确定密码不一致'
    //                 },
    //             }
    //         },
    //     }
    // });

})
