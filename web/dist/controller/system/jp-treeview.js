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
     * 选择部门查找
     */
    function selectDept(deptId) {
        console.log(deptId);
        $('#jobposition-table').bootstrapTable('refresh');
    }

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

        $('#add-jp-modal').css({'overflow-y':'scroll'});
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

    $('#select-dept-confirm').on('click', function(){
        $('#departmentName').val(outText);
        $('#departmentId').val(outId);
        $('#select-dept-modal').modal('hide');
    });

    /**
     * 查询
     */
    $('#btn-search').on('click', function () {
        jpName = $('#jpname-search').val();

        $('#jobposition-table').bootstrapTable('refresh');

        //查询完后重置参数为空
        jpName = "";
    });


    /**
     * 判断值是否为空（添加岗位）
     */
    $('#saveJPForm').bootstrapValidator({
        feedbackIcons : {
            valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove',
            validating : 'glyphicon glyphicon-refresh'
        },
        excluded:[],
        fields:{
            name:{
                message:'岗位名称非法',
                validators: {
                    notEmpty: {
                        message: '岗位名称不可为空'
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
            }
        }
    });

    /**
     * 判断值是否为空（修改岗位）
     */
    $('#updateJPForm').bootstrapValidator({
        feedbackIcons : {
            valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove',
            validating : 'glyphicon glyphicon-refresh'
        },
        excluded:[],
        fields:{
            name:{
                message:'岗位名称非法',
                validators: {
                    notEmpty: {
                        message: '岗位名称不可为空'
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
            }
        }
    });

    /**
     * 保存岗位信息
     */
    $('#btn-save-jp').on('click', function () {
        //判断属性是否为空
        var form = $('#saveJPForm');
        form.bootstrapValidator('validate');
        if(!form.data('bootstrapValidator').isValid()){
            return;
        }

        var name = $('#jpName').val();
        var departmentId = outId;
        var description = $('#description').val()

        $.ajax({
            url:'jobposition/saveJP',
            type:'post',
            contentType:'application/json',
            async:false,
            data:JSON.stringify({
                name:name,
                departmentId:departmentId,
                description:description
            }),
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
            $('#add-jp-modal').modal('hide');
            $('#jobposition-table').bootstrapTable('refresh');
            $('#save-success').css("display","none");
            $('#save-error').css("display","none");
        }, 500);
    });

    /**
     * 修改岗位信息
     */
    $('#btn-update-jp').on('click', function () {
        var form = $('#updateJPForm');

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

        var id = $('#jpId').val();
        var name = $('#jpName2').val();
        var departmentId = $('#departmentId2').val();
        var description = $('#description2').val();

        $.ajax({
            url:'jobposition/updateJP',
            type:'post',
            contentType:'application/json',
            async:false,
            data:JSON.stringify({
                id:id,
                name:name,
                departmentId:departmentId,
                description:description
            }),
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
            $('#update-jp-modal').modal('hide');
            $('#jobposition-table').bootstrapTable('refresh');
            $('#update-success').css("display","none");
            $('#update-error').css("display","none");
        }, 500);
    });

    $(".add-active").on('click', function() {
        $(this).siblings().removeClass("active").end().addClass("active");
    });
});