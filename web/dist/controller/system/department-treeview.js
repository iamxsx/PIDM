/**
 * Created by PhychoLee on 9/14/16.
 * 树状结构
 */

$(function() {
    document.getElementById('updateForm').reset();

    var jsonData;
    function getDept() {
        $.ajax({
            url:'department/getDepartments',
            type:'get',
            async:false,
            success:function (data) {
                jsonData = data;
            }
        });
    }

    var currentNode = null;

    function initTreeView() {
        getDept();
        var initSelectableTree = function() {
            return $('#treeview-selectable').treeview({
                data: jsonData,
                multiSelect: $('#chk-select-multi').is(':checked'),
                onNodeSelected: function(event, node) {
                    $('#selectable-output').prepend('<p>' + node.text + ' was selected</p>');
                    selectDept(node.href);
                    currentNode = node;
                },
                onNodeUnselected: function (event, node) {
                    $('#selectable-output').prepend('<p>' + node.text + ' was unselected</p>');
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
    }
    initTreeView();

    var updateFormJsonInit = '';
    /**
     * 选择部门查找
     */
    function selectDept(deptId) {
        $('.save').css('display','none');
        $('.update').css('display','block');
        $.ajax({
            url:'department/getDepartment',
            type:'get',
            data:{
                deptId:deptId,
                mark:33
            },
            success:function (data) {
                $('#id').val(data.id);
                $('#code').val(data.code);
                $('#name').val(data.name);
                $('#parent').val(data.parent);
                $('#parentId').val(data.parentId);
                $('#description').val(data.description);

                //将表单数据序列化
                var dataformInit = $("#updateForm").serializeArray();
                updateFormJsonInit = JSON.stringify({ dataform: dataformInit });
            },
            error:function (data) {
                if (data.error != ""){
                    alert("查找部门信息失败");
                }
            }
        });
    }

    /**
     * 判断值是否为空(添加)
     */
    $('#saveForm').bootstrapValidator({
        feedbackIcons : {
            valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove',
            validating : 'glyphicon glyphicon-refresh'
        },
        excluded:[],
        fields:{
            code:{
                message:'部门编号非法',
                validators: {
                    notEmpty: {
                        message: '部门编号不可为空'
                    },
                    stringLength: {
                        min: 4,
                        max: 30,
                        message: '部门编号长度必须在4～30'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9]+$/,
                        message: '部门编号可以是字母，數字'
                    },
                    remote:{
                        message:'部门编号已存在',
                        url:'department/checkCode'
                    }
                }
            },
            name:{
                message:'部门名称非法',
                validators: {
                    notEmpty: {
                        message: '部门名称不可为空'
                    }
                }
            },
            parent:{
                message:'所在部门非法',
                validators: {
                    notEmpty: {
                        message: '所在部门不可为空'
                    }
                }
            }
        }
    });

    /**
     * 判断值是否为空(修改)
     */
    $('#updateForm').bootstrapValidator({
        feedbackIcons : {
            valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove',
            validating : 'glyphicon glyphicon-refresh'
        },
        excluded:[],
        fields:{
            code:{
                message:'部门编号非法',
                validators: {
                    notEmpty: {
                        message: '部门编号不可为空'
                    },
                    stringLength: {
                        min: 4,
                        max: 30,
                        message: '部门编号长度必须在4～30'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9]+$/,
                        message: '部门编号可以是字母，數字'
                    },
                    remote:{
                        message:'部门编号已存在',
                        url:'department/checkCode2'
                    }
                }
            },
            name:{
                message:'部门名称非法',
                validators: {
                    notEmpty: {
                        message: '部门名称不可为空'
                    }
                }
            },
            parent:{
                message:'所在部门非法',
                validators: {
                    notEmpty: {
                        message: '所在部门不可为空'
                    }
                }
            }
        }
    });

    /**
     * 删除按钮
     */
    $('#btn-delete').on('click', function () {
       var id = $('#id').val();
        if (id == ""){
            return;
        }
        $('#delete-sure-modal').modal();
    });

    /**
     * 确认删除
     */
    $('#btn-delete-sure').on('click', function () {
        var id = $('#id').val();
        var parentId = $('#parentId').val();

        var isSuccess = false;
        $.ajax({
            url:'department/deleteDept',
            type:'get',
            data:{
                id:id
            },
            success:function (data) {
                console.log(data);
                $('#delete-msg').css("display","none");
                if (data.success == 'success'){
                    initTreeView();
                    $(this).attr("disabled", true);
                    $('#delete-success').css("display","block");
                    isSuccess = true;
                }else if (data.error == 'error'){
                    $('#delete-error').css("display","block");
                }else if (data.child == 'notNull'){
                    $('#reference-error').text('此部门下有子部门!');
                    $('#reference-error').css("display","block");
                }else if (data.JP == 'notNull'){
                    $('#reference-error').text('此部门下有岗位!');
                    $('#reference-error').css("display","block");
                }
            },
            error:function () {
                console.log("删除失败");
                $('#delete-error').css("display","block");
                $('#delete-msg').css("display","none");
            }
        });

        setTimeout(function () {
            if (isSuccess && parentId != ''){
                selectDept(parentId);
            }
            $('#delete-sure-modal').modal('hide');
            $('#delete-msg').css("display","block");
            $('#delete-success').css("display","none");
            $('#reference-error').css("display","none");
            $('#delete-error').css("display","none");
        }, 600);
    });

    /**
     * 点击新增后表单改变
     */
    $('#btn-add').on('click', function () {
        $('#parent2').val($('#name').val());
        $('#parentId2').val($('#id').val());
        $('#code2').val('');
        $('#name2').val('');
        $('#description2').val('');

        $('.save').css('display','block');
        $('.update').css('display','none');
    });

    /**
     * 新增操作
     */
    $('#save-department').on('click', function () {
        //判断属性是否为空
        var form = $('#saveForm');
        form.bootstrapValidator('validate');
        if(!form.data('bootstrapValidator').isValid()){
            return;
        }

        var parentName = $('#parent2').val();
        var parentId = $('#parentId2').val();
        var code = $('#code2').val();
        var name = $('#name2').val();
        var description = $('#description2').val();

        var savedId = '';
        $.ajax({
            url: 'department/saveDept',
            type: 'post',
            contentType: 'application/json',
            async: false,
            data: JSON.stringify({
                parentId: parentId,
                code: code,
                name: name,
                description: description
            }),
            success: function (data) {
                console.log(data);
                if (data.success == 'success') {
                    initTreeView();
                    savedId = data.id;
                    $('#save-success').css("display", "block");
                } else if (data.error == 'error') {
                    $('#save-error').css("display", "block");
                } else if (data.name = 'conflict'){
                    $('#conflict-error').text(parentName+'下已存在'+name);
                    $('#conflict-error').css("display", "block");
                }
            },
            error: function (data) {
                console.log(data);
            }
        });

        setTimeout(function () {
            if(savedId != ''){
                selectDept(savedId);
            }
            $('#saveForm').data('bootstrapValidator').resetForm();
            $('#save-success').css("display","none");
            $('#save-error').css("display","none");
            $('#conflict-error').css("display", "none");
        }, 600);
    });


    /**
     * 更新操作
     */
    $('#update-department').on('click', function () {
        var form = $('#updateForm');
        //数据没有修改弹出提示
        var dataform = form.serializeArray();
        var updateFormJson = JSON.stringify({ dataform: dataform });
        if(updateFormJsonInit==updateFormJson){
            alert('数据没有修改！');
            return;
        }

        //判断属性是否为空
        form.bootstrapValidator('validate');
        if(!form.data('bootstrapValidator').isValid()){
            return;
        }

        var id = $('#id').val();
        var code = $('#code').val();
        var name = $('#name').val();
        var description = $('#description').val();
        var parentId = $('#parentId').val();
        var parentName = $('#parent').val();

        $.ajax({
            url:'department/updateDept',
            type:'post',
            contentType:'application/json',
            async:false,
            data:JSON.stringify({
                id:id,
                code:code,
                name:name,
                description:description,
                parentId:parentId
            }),
            success:function (data) {
                console.log(data);
                initTreeView();
                if (data.success == 'success'){
                    $('#update-success').css("display","block");
                }else if(data.error == 'error'){
                    $('#update-error').css("display","block");
                } else if (data.name == 'conflict'){
                    $('#conflict-error2').text(parentName+'下已存在'+name);
                    $('#conflict-error2').css("display", "block");
                } else if(data.code == 'conflict'){
                    $('#conflict-error2').text('编号 '+code+' 下已被其他部门使用');
                    $('#conflict-error2').css("display", "block");
                }
            },
            error:function (data) {
                console.log(data);
            }
        });

        setTimeout(function () {
            if (id!=''){
                selectDept(id)
            }
            $('#updateForm').data('bootstrapValidator').resetForm();
            $('#update-success').css("display","none");
            $('#update-error').css("display","none");
            $('#conflict-error2').css("display", "none");
        }, 600);
    });

    $(".add-active").on('click', function() {
        $(this).siblings().removeClass("active").end().addClass("active");
    });
});

