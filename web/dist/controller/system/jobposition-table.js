/**
 * Created by clouder on 9/18/16.
 */
var deptId = null;
var jpNameSH = null;

$('#jobposition-table').bootstrapTable({
    url:'jobposition/getJobPositionByPage',
    method:'post',
    sidePagination:'client',
    pagination:true,
    pageNumber:1,
    pageSize:10,
    pageList:[20,40,60,80],
    striped: true,
    queryParams:function queryParams(params) {
        params.deptId = deptId;
        params.jpName = jpNameSH;
        return params;
    },
    queryParamsType:'limit',
    columns:[{
        checkbox: true
    },{
        field: 'id',
        visible:false
    },{
        field:'name',
        title:'岗位名称',
        width:120
    },{
        field:'department.name',
        title:'所在部门',
        width:120,
    },{
        field:'description',
        title:'职位描述',
        width:160,
    }
    ]
});

/**
 * 查询
 */
$('#btn-search2').on('click', function () {
    jpNameSH = $('#jpname-search').val();

    $('#jobposition-table').bootstrapTable('refresh');

    //查询完后重置参数为空
    jpNameSH = "";
});

$('#btn-refresh').click(function () {
    $('#jobposition-table').bootstrapTable('refresh');
});

$('#btn-add').on('click', function () {
    //清空表单
    $('#saveJPForm').find(':input').not(':button, :submit, :reset').val('');
    $('#saveJPForm').data('bootstrapValidator').resetForm();
});

var updateFormJsonInit = '';
/**
 * 点击修改按钮，从后台返回数据
 */
$('#btn-update').on('click', function () {
    //清空表单
    $('#updateJPForm').find(':input').not(':button, :submit, :reset').val('');
    $('#updateJPForm').data('bootstrapValidator').resetForm();


    var selections = $('#jobposition-table').bootstrapTable('getSelections');
    if (selections.length <= 0){
        alert('请先选择要修改的岗位！');
        return false;
    }else if (selections.length>1){
        alert('请勿选择多条！');
        return false;
    }

    var id = selections[0].id;
    $.ajax({
        url:'jobposition/getJobPosition',
        type:'get',
        async:false,
        data:{
            id:id
        },
        success:function (data) {
            if (data != null){
                $('#jpId').val(data.id);
                $('#jpName2').val(data.name);
                $('#departmentName2').val(data.department.name);
                $('#departmentId2').val(data.department.id);
                $('#description2').val(data.description);

                //将表单数据序列化
                var dataformInit = $("#updateJPForm").serializeArray();
                updateFormJsonInit = JSON.stringify({ dataform: dataformInit });

                return true;
            }else{
                alert('获取岗位信息失败');
                return false;
            }
        },
        error:function (data) {
            alert('获取岗位信息失败');
            return false;
        }
    });
});

/**
 * 删除
 */
$('#btn-delete').click(function () {
    var selections = $('#jobposition-table').bootstrapTable('getSelections');
    if (selections.length <= 0){
        alert('请先选择要删除的数据！');
        return;
    }
    $('#delete-sure-modal').modal();
});

$('#btn-delete-sure').click(function () {
    var selections = $('#jobposition-table').bootstrapTable('getSelections');
    var ids ='';
    for (var i = 0; i<selections.length; i++){
        ids += selections[i].id + ','
    }
    ids = ids.substring(0,ids.length-1);
    console.log(ids);
    $.ajax({
        url:'jobposition/deleteJP',
        type:'get',
        data:{
            ids:ids
        },
        success:function (data) {
            console.log(data);
            $('#delete-msg').css("display","none");
            if (data == 'success'){
                $('#delete-success').css("display","block");
            }else if (data == 'reference'){
                $('#delete-error2').css("display","block");
            }else if (data == 'error'){
                $('#delete-error').css("display","block");
            }
        },
        error:function () {
            console.log("删除失败");
            $('#delete-error').css("display","block");
            $('#delete-msg').css("display","none");
        }
    });

    setTimeout(function () {
        $('#delete-sure-modal').modal('hide');
        $('#jobposition-table').bootstrapTable('refresh');

        $('#delete-msg').css("display","block");
        $('#delete-success').css("display","none");
        $('#delete-error2').css("display","none");
        $('#delete-error').css("display","none");
    }, 500);

});