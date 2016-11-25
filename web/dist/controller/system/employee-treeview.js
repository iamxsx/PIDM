/**
 * Created by PhychoLee on 9/14/16.
 * 树状结构
 */

$(function() {

    var jsonData;
    $.ajax({
        url:'department/getDepartments',
        type:'get',
        async:false,
        success:function (data) {
            jsonData = data;
        }
    });

    /**
     * 选择部门,根据部门查询员工
     */

    function selectDept(deptId) {
        console.log(deptId);
        $('#employee-table').bootstrapTable('refresh');
    }
    var initSelectableTree = function() {
        return $('#treeview-selectable').treeview({
            data: jsonData,
            multiSelect: $('#chk-select-multi').is(':checked'),
            onNodeSelected: function(event, node) {
                deptId = node.href;
                selectDept(node.href);
            },
            onNodeUnselected: function (event, node) {
            }
        });
    };
    var $selectableTree = initSelectableTree();

    var findSelectableNodes = function() {
        return $selectableTree.treeview('search', [ $('#input-select-node').val(), { ignoreCase: false, exactMatch: false } ]);
    };
    var selectableNodes = findSelectableNodes();

    $('#chk-select-multi:checkbox').on('change', function () {
        console.log('multi-select change');
        $selectableTree = initSelectableTree();
        selectableNodes = findSelectableNodes();
    });

    // Select/unselect/toggle nodes
    $('#input-select-node').on('keyup', function (e) {
        selectableNodes = findSelectableNodes();
        $('.select-node').prop('disabled', !(selectableNodes.length >= 1));
    });

    $selectableTree.treeview('expandAll', { levels: 3, silent: true });

    /**
     * 第二级模态框，弹出部门树状结构图
     *
     */
    var initSelectableTree2 = function() {
        return $('#treeview-selectable2').treeview({
            data: jsonData,
            multiSelect: $('#chk-select-multi').is(':checked'),
            onNodeSelected: function(event, node) {
                outData(node.text, node.href);
            },
            onNodeUnselected: function (event, node) {

            }
        });
    };
    var $selectableTree2 = initSelectableTree2();

    var findSelectableNodes2 = function() {
        return $selectableTree2.treeview('search', [ $('#input-select-node2').val(), { ignoreCase: false, exactMatch: false } ]);
    };
    var selectableNodes2 = findSelectableNodes2();

    $('#chk-select-multi:checkbox').on('change', function () {
        console.log('multi-select change');
        $selectableTree2 = initSelectableTree2();
        selectableNodes2 = findSelectableNodes2();
    });

    // Select/unselect/toggle nodes
    $('#input-select-node2').on('keyup', function (e) {
        selectableNodes2 = findSelectableNodes2();
        $('.select-node').prop('disabled', !(selectableNodes2.length >= 1));
    });

    $selectableTree2.treeview('expandAll', { levels: 3, silent: true });

    /**
     * 第二层模态框消失时，把焦点还给第一层
     */
    $('#select-dept-modal').on('hidden.bs.modal', function() {

        $('#add-employee-modal').css({'overflow-y':'scroll'});
    });

    /**
     * 选择部门查找
     */
    var outText = "";
    var outId = "";
    function outData(text, id){
        outText = text;
        outId = id;
    }

    if ($('#departmentName').val() != null && $('#departmentId').val() != null){
        getJobPosition();
    }

    $('#select-dept-confirm').on('click', function(){
        var isAdd = $('#add-employee-modal').is(':visible');
        var isUpdate = $('#update-employee-modal').is(':visible')

        if (isAdd){
            $('#departmentName').val(outText);
            $('#departmentId').val(outId);

            getJobPosition();
        } else if(isUpdate){
            $('#departmentName2').val(outText);
            $('#departmentId2').val(outId);

            getJobPosition2();
        }


        $('#select-dept-modal').modal('hide');
    });


    /**
     * 根据部门id获取岗位
     */
    function getJobPosition(){
        var deptId = $('#departmentId').val();
        $.ajax({
            url:'jobposition/getJobPositionByDept',
            type:'get',
            data:{
                deptId:deptId
            },
            success:function (data) {
                $('#jobPosition').html("<option value=''>请选择</option>");
                $.each(data, function (i, item) {
                    $('#jobPosition').append(
                        '<option value="'+item.id+'">'+item.name+'</option>'
                    );
                });
            },
            error:function (data) {

            }
        });
    }


    /**
     * 时间选择器
     */
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        startDate: '1940-01-01',
        format: 'yyyy-mm-dd',
        showMeridian: 1,
    });

    /**
     * 判断值是否为空（添加员工）
     */
    $('#registerForm').bootstrapValidator({
        feedbackIcons : {
            valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove',
            validating : 'glyphicon glyphicon-refresh'
        },
        excluded:[],
        fields:{
            realName:{
                message:'姓名非法',
                validators: {
                    notEmpty: {
                        message: '姓名不可为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 10,
                        message: '姓名长度必须在2～10'
                    }
                }
            },
            jobStatus:{
                message:'在职状态非法',
                validators: {
                    notEmpty: {
                        message: '在职状态不可为空'
                    }
                }
            },
            departmentName:{
                message:'所在部门非法',
                validators: {
                    notEmpty: {
                        message: '所在部门不可为空'
                    }
                }
            },
            jobPosition:{
                message:'所在岗位非法',
                validators: {
                    notEmpty: {
                        message: '所在岗位不可为空'
                    }
                }
            },
            userName:{
                message:'工号/账号非法',
                validators: {
                    notEmpty: {
                        message: '工号/账号不可为空'
                    },
                    stringLength: {
                        min: 4,
                        max: 30,
                        message: '工号/账号长度必须在4～30'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '工号/账号可以是字母，數字，点和下划线'
                    },
                    remote:{
                        message:'工号/账号已存在',
                        url:'employee/checkUserName'
                    }
                }
            },
            password:{
                message:'密码非法',
                validators:{
                    notEmpty: {
                        message: '密码不可为空'
                    },
                    stringLength: {
                        min: 4,
                        max: 30,
                        message: '密码长度必须在4～30'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '密码可以是字母，數字，点和下划线'
                    }
                }
            },
            gender:{
                message:'性别非法',
                validators: {
                    notEmpty: {
                        message: '性别不可为空'
                    }
                }
            },
            cellPhoneNum:{
                message:'手机号非法',
                validators: {
                    notEmpty: {
                        message: '手机号不可为空'
                    },
                    regexp: {
                        regexp: /^1\d{10}$/,
                        message: '手机号为数字11位'
                    },
                }
            },
            phoneMsg:{
                message:'验证码非法',
                validators: {
                    notEmpty: {
                        message: '验证码不可为空'
                    },
                    remote:{
                        message:'验证码不正确',
                        url:'code/checkPhoneMsg'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: '邮箱不可为空'
                    },
                    emailAddress: {
                        message: '不是有效的邮箱地址'
                    }
                }
            }
        }
    });

    $('#saveEmployeeBtn').on('click', function () {
        //判断属性是否为空
        var form = $('#registerForm');
        form.bootstrapValidator('validate');
        if(!form.data('bootstrapValidator').isValid()){
            return;
        }

        //获取勾选的角色
        var selections = $('#role-table').bootstrapTable('getSelections');
        var ids ='';
        for (var i = 0; i<selections.length; i++){
            ids += selections[i].id + ','
        }
        ids = ids.substring(0,ids.length-1);
        console.log(ids);
        if(ids == ""){
            $('#role-warning').css("display","block");
            return;
        }

        var gender1 = null;
        if(document.getElementById('male').checked){
            gender1 = 1;
        }else if(document.getElementById('female').checked){
            gender1 = 0;
        }

        var employee  = {
            realName:$('#realName').val().trim(),
            jobStatus:$('#jobStatus').val().trim(),
            jobPositionId:$('#jobPosition').val().trim(),
            userName:$('#userName').val().trim(),
            password:$('#password').val().trim(),
            gender:gender1,
            birthday:$('#birthday').val().trim(),
            cellPhoneNum:$('#cellPhoneNum').val().trim(),
            officePhoneNum:$('#officePhoneNum').val().trim(),
            qq:$('#qq').val(),
            email:$('#email').val().trim(),
            address:$('#address').val().trim(),
            roleIds:ids,
            phoneMsg:$('#phoneMsg').val().trim()
        };

        $.ajax({
            url:'employee/saveEmployee',
            type:'post',
            contentType:'application/json',
            async:false,
            data:JSON.stringify(employee),
            success:function (data) {
                console.log(data);
                $('#save-success').css("display","block");
            },
            error:function (data) {
                console.log(data);
                $('#save-error').css("display","block");
            }
        });


        setTimeout(function () {
            $('#add-employee-modal').modal('hide');
            $('#employee-table').bootstrapTable('refresh');
            $('#save-success').css("display","none");
            $('#save-error').css("display","none");
        }, 500);
    });

    /**
     * 判断值是否为空（修改员工）
     */
    $('#updateForm').bootstrapValidator({
        feedbackIcons : {
            valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove',
            validating : 'glyphicon glyphicon-refresh'
        },
        excluded:[],
        fields:{
            realName:{
                message:'姓名非法',
                validators: {
                    notEmpty: {
                        message: '姓名不可为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 10,
                        message: '姓名长度必须在2～10'
                    }
                }
            },
            jobStatus:{
                message:'在职状态非法',
                validators: {
                    notEmpty: {
                        message: '在职状态不可为空'
                    }
                }
            },
            departmentName:{
                message:'所在部门非法',
                validators: {
                    notEmpty: {
                        message: '所在部门不可为空'
                    }
                }
            },
            jobPosition:{
                message:'所在岗位非法',
                validators: {
                    notEmpty: {
                        message: '所在岗位不可为空'
                    }
                }
            },
            userName:{
                message:'工号/账号非法',
                validators: {
                    notEmpty: {
                        message: '工号/账号不可为空'
                    },
                    stringLength: {
                        min: 4,
                        max: 30,
                        message: '工号/账号长度必须在4～30'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '工号/账号可以是字母，數字，点和下划线'
                    },
                    remote:{
                        message:'工号/账号已存在',
                        url:'employee/checkUserName2'
                    }
                }
            },
            password:{
                message:'密码非法',
                validators:{

                    stringLength: {
                        min: 4,
                        max: 30,
                        message: '密码长度必须在4～30'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.*]+$/,
                        message: '密码可以是字母，數字，点和下划线'
                    }
                }
            },
            gender2:{
                message:'性别非法',
                validators: {
                    notEmpty: {
                        message: '性别不可为空'
                    }
                }
            },
            cellPhoneNum:{
                message:'手机号非法',
                validators: {
                    notEmpty: {
                        message: '手机号不可为空'
                    },
                    regexp: {
                        regexp: /^1\d{10}$/,
                        message: '手机号为数字11位'
                    }
                }
            },
            phoneMsg:{
                enabled:false,
                message:'验证码非法',
                validators: {
                    notEmpty: {
                        message: '验证码不可为空'
                    },
                    remote:{
                        message:'验证码不正确',
                        url:'code/checkPhoneMsg'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: '邮箱不可为空'
                    },
                    emailAddress: {
                        message: '不是有效的邮箱地址'
                    }
                }
            }
        }
    });

    $('#updateEmployeeBtn').on('click', function () {
        var form = $('#updateForm');
        var selections = $('#role-table2').bootstrapTable('getSelections');

        //数据没有修改弹出提示
        var dataform = form.serializeArray();
        var updateFormJson = JSON.stringify({ dataform: dataform });

        // var dataform2 = selections.serializeArray();
        // var roleTableJson = JSON.stringify({ dataform: dataform2 });
        if(updateFormJsonInit==updateFormJson && roleTableJsonInit == selections){
            alert('数据没有修改！');
            return;
        }

        //判断属性是否为空

        form.bootstrapValidator('validate');
        if (!form.data('bootstrapValidator').isValid()) {
            return;
        }

        //获取勾选的角色

        var ids ='';
        for (var i = 0; i<selections.length; i++){
            ids += selections[i].id + ','
        }
        ids = ids.substring(0,ids.length-1);
        console.log(ids);
        if(ids == ""){
            $('#role-warning2').css("display","block");
            return;
        }

        var gender2 = null;
        if(document.getElementById('male2').checked){
            gender2 = 1;
        }else if(document.getElementById('female2').checked){
            gender2 = 0;
        }

        var employee  = {
            id:$('#employeeId').val().trim(),
            realName:$('#realName2').val().trim(),
            jobStatus:$('#jobStatus2').val().trim(),
            jobPositionId:$('#jobPosition2').val().trim(),
            userName:$('#userName2').val().trim(),
            password:$('#password2').val().trim(),
            gender:gender2,
            birthday:$('#birthday2').val().trim(),
            cellPhoneNum:$('#cellPhoneNum2').val().trim(),
            officePhoneNum:$('#officePhoneNum2').val().trim(),
            qq:$('#qq2').val().trim(),
            email:$('#email2').val().trim(),
            address:$('#address2').val().trim(),
            roleIds:ids,
            phoneMsg:$('#phoneMsg').val().trim()
        };

        $.ajax({
            url:'employee/updateEmployee',
            type:'post',
            contentType:'application/json',
            async:false,
            data:JSON.stringify(employee),
            success:function (data) {
                console.log(data);
                $('#update-success').css("display","block");
            },
            error:function (data) {
                console.log(data);
                $('#update-error').css("display","block");
            }
        });

        setTimeout(function () {
            $('#update-employee-modal').modal('hide');
            $('#employee-table').bootstrapTable('refresh');
            $('#update-success').css("display","none");
            $('#update-error').css("display","none");
            $('#role-warning2').css("display","none");
        }, 500);
    });

    $(".add-active").on('click', function() {
        $(this).siblings().removeClass("active").end().addClass("active");
    });

    //获取手机短信验证码,新增员工
    $('#getPhoneMsgBtn').on('click', function () {
        var pattern = /^1\d{10}$/;
        var phoneNum = $('#cellPhoneNum').val();

        var result = pattern.test(phoneNum);

        if (!result){
            alert('请填写正确的手机号码!');
            return;
        }

        $.ajax({
            cache:false,
            url:'code/getphoneMsg',
            data:{
                phoneNum : phoneNum
            },
            success:function (data) {
                if (data == 'sent'){
                    alert('已获取过验证码！');
                }
            },
            error:function (data) {
                alert('获取验证码失败！');
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


    //监听电话号码改变
    $('#cellPhoneNum2').bind('input propertychange', function() {
        var changePhoneNum = $(this).val();
        if (changePhoneNum != oldPhoneNum){
            //当手机号码改变，需要短信验证码
            $('#updatePhoneMsg').css('display','block');
            $('#updateForm').data('bootstrapValidator').enableFieldValidators('phoneMsg', true);
        }else{
            $('#updatePhoneMsg').css('display','none');
            $('#updateForm').data('bootstrapValidator').enableFieldValidators('phoneMsg', false);
        }
    });


    //获取手机短信验证码,修改员工
    $('#getPhoneMsgBtn2').on('click', function () {
        var pattern = /^1\d{10}$/;
        var phoneNum = $('#cellPhoneNum2').val();

        var result = pattern.test(phoneNum);

        if (!result){
            alert('请填写正确的手机号码!');
            return;
        }

        $.ajax({
            cache:false,
            url:'code/getphoneMsg',
            data:{
                phoneNum : phoneNum
            },
            success:function (data) {
                if (data == 'sent'){
                    alert('已获取过验证码！');
                }
            },
            error:function (data) {
                alert('获取验证码失败！');
            }
        });

        $('#getPhoneMsgBtn2').attr("disabled", true);
        clock2();
    });

    var countNum2 = 60;

    function clock2(){
        var btn = $('#getPhoneMsgBtn2');
        if(countNum2 == 0){
            btn.attr("disabled", false);
            btn.text('获取验证码');
            countNum2 = 60;
            return;
        }else{
            btn.text(countNum2+'秒后重新获取');
            countNum2--;
        }
        setTimeout(function () {
            clock2()
        }, 1000);
    }

});