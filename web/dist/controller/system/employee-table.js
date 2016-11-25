/**
 * Created by clouder on 9/14/16.
 */
var deptId = null;
var jobStatusSH = null;
var realNameSH = null;
var userNameSH = null;

$('#employee-table').bootstrapTable({
    url:'employee/getEmployees',
    method:'post',
    sidePagination:'client',
    pagination:true,
    pageNumber:1,
    pageSize:10,
    pageList:[20,40,60,80],
    striped: true,
    queryParams:function queryParams(params) {
        params.deptId = deptId;
        params.jobStatusSH = jobStatusSH;
        params.realNameSH = realNameSH;
        params.userNameSH = userNameSH;
        console.log(params.deptId);
        return params;
    },

    queryParamsType:'limit',
    columns:[{
        checkbox: true
    },{
        field: 'id',
        visible:false
    },{
        field:'realName',
        title:'姓名',
        width:100,
    },{
        field:'userName',
        title:'工号',
        width:100,
    },{
        field:'jobStatus',
        title:'在职状态',
        width:80,
        formatter:function (value,row,index) {
            if (value == 1){
                return '在职';
            }else if(value == 2){
                return '离职';
            }else if(value == 3){
                return '停职';
            }else if(value == 4){
                return '退休';
            }
        }
    },{
        field:'gender',
        title:'性别',
        width:60,
        formatter:function (value,row,index) {
            if (value == 1){
                return '男';
            }else if(value == 0){
                return '女';
            }
        }
    },{
        field:'departmentName',
        title:'部门',
        width:180
    },{
        field:'jobPositionName',
        title:'岗位',
        width:180

    }
    ]
});

/**
 * 查询
 */
$('#btn-search').on('click', function () {
    jobStatusSH = $('#job-position-search').val();
    realNameSH = $('#real-name-search').val();
    userNameSH = $('#user-name-search').val();

    $('#employee-table').bootstrapTable('refresh');

    //查询完后重置参数为空
    jobStatusSH = null;
    realNameSH = "";
    userNameSH = "";
});

$('#btn-refresh').click(function () {
    $('#employee-table').bootstrapTable('refresh');
});
$('#btn-delete').click(function () {
    var selections = $('#employee-table').bootstrapTable('getSelections');
    if (selections.length <= 0){
        alert('请先选择要删除的数据！');
        return;
    }
    $('#delete-sure-modal').modal();
});

$('#btn-delete-sure').click(function () {
    var selections = $('#employee-table').bootstrapTable('getSelections');
    var ids ='';
    for (var i = 0; i<selections.length; i++){
        ids += selections[i].id + ','
    }
    ids = ids.substring(0,ids.length-1);
    console.log(ids);
    $.ajax({
        url:'employee/deleteEmployee',
        type:'get',
        data:{
            ids:ids
        },
        success:function (data) {
            console.log(data);
            $('#delete-success').css("display","block");
            $('#delete-msg').css("display","none");
        },
        error:function () {
            console.log("删除失败");
            $('#delete-error').css("display","block");
            $('#delete-msg').css("display","none");
        }
    });

    setTimeout(function () {
        $('#delete-sure-modal').modal('hide');
        $('#employee-table').bootstrapTable('refresh');
        $('#delete-msg').css("display","block");
        $('#delete-success').css("display","none");
        $('#delete-error').css("display","none");
    }, 500);

});

$('#btn-add').on('click', function () {
    //模态框内的标签页显示首页
    $('#add-employee-modal a:first').tab('show');
    //清空表单
    $('#registerForm').find(':input').not(':button, :submit, :reset').val('')
        .removeAttr('checked').removeAttr('selected');
    $('#registerForm').data('bootstrapValidator').resetForm();

    //清空角色表checked缓存
    var roleDate =  $('#role-table').bootstrapTable('getData');
    $.each(roleDate, function (i, item) {
        $('#role-table').bootstrapTable('uncheck', i);
    });
});

var oldPhoneNum = '';
var updateFormJsonInit = '';
var roleTableJsonInit = '';
/**
 * 点击修改按钮，从后台返回数据
 */
$('#btn-update').on('click', function () {
    //模态框内的标签页显示首页
    $('#update-employee-modal a:first').tab('show');
    $('#updatePhoneMsg').css('display','none');

    //清空表单
    $('#updateForm').find(':input').not(':button, :submit, :reset').val('')
        .removeAttr('checked').removeAttr('selected');
    $('#updateForm').data('bootstrapValidator').resetForm();


    var selections = $('#employee-table').bootstrapTable('getSelections');
    if (selections.length <= 0){
        alert('请先选择要修改的员工！');
        return false;
    }else if (selections.length>1){
        alert('请勿选择多条!');
        return false;
    }

    var id = selections[0].id;
    $.ajax({
        url:'employee/getEmployee',
        type:'get',
        async:false,
        data:{
            id:id
        },
        success:function (data) {
            if (data != null){
                $('#employeeId').val(data.id);
                $('#realName2').val(data.realName);
                $('#jobStatus2').val(data.jobStatus);
                $('#departmentName2').val(data.departmentName);
                $('#departmentId2').val(data.departmentId);
                getJobPosition2();
                $('#jobPosition2').val(data.jobPositionId);
                $('#userName2').val(data.userName);
                $('#password2').val('');

                if (data.gender ==1){
                    document.getElementById('male2').checked = true;
                }else if(data.gender == 0){
                    document.getElementById('female2').checked = true;
                }

                 var birthday = data.birthday
                birthday = birthday.split(' ')[0];
                $('#birthday2').val(birthday);
                $('#cellPhoneNum2').val(data.cellPhoneNum);
                oldPhoneNum = data.cellPhoneNum;
                $('#officePhoneNum2').val(data.officePhoneNum);
                $('#qq2').val(data.qq);
                $('#email2').val(data.email);
                $('#address2').val(data.address);


                //给员工已有的角色打钩
                var ids = data.roleIds.split(',');
                var roleDate =  $('#role-table2').bootstrapTable('getData');
                $.each(roleDate, function (i, item) {
                    $('#role-table2').bootstrapTable('uncheck', i);
                    for (var j= 0; j<ids.length; j++){
                        if (ids[j] == item.id){
                            $('#role-table2').bootstrapTable('check', i);
                        }
                    }
                });

                //将表单数据序列化
                var dataformInit = $("#updateForm").serializeArray();
                updateFormJsonInit = JSON.stringify({ dataform: dataformInit });

                // var dataformInit2 = $('#role-table2').bootstrapTable('getSelections').serializeArray();
                roleTableJsonInit = $('#role-table2').bootstrapTable('getSelections');

                return true;
            }else{
                alert('获取员工信息失败');
                return false;
            }
        },
        error:function (data) {
            alert('获取员工信息失败');
            return false;
        }
    });
});

/**
 * 根据部门id获取岗位
 */
function getJobPosition2(){
    var deptId = $('#departmentId2').val();
    $.ajax({
        url:'jobposition/getJobPositionByDept',
        type:'get',
        async:false,
        data:{
            deptId:deptId
        },
        success:function (data) {
            $('#jobPosition2').html("<option value=''>请选择</option>");
            $.each(data, function (i, item) {
                $('#jobPosition2').append(
                    '<option value="'+item.id+'">'+item.name+'</option>'
                );
            });
        },
        error:function (data) {

        }
    });
}


/**
 * 角色table初始化
 */
$('#role-table,#role-table2').bootstrapTable({
    url: 'role/findRoles',
    method: 'get',
    sidePagination: 'client',
    pagination: true,
    pageNumber: 1,
    pageSize: 5,
    pageList: [20, 40, 60, 80],
    striped: true,
    columns: [{
        checkbox: true
    }, {
        field: 'id',
        width: 120,
        visible:false
    },{
        field: 'name',
        title: '角色名称',
        width: 120
    }, {
        field:'location',
        title:'位置',
        formatter:function(value,row,index){
            if (value == 1){
                return "前台";
            }else {
                return "后台";
            }
        }
    }, {
        field: 'menus',
        title: '菜单权限',
        formatter: function (value, row, index) {
            var text = '';
            $.each(value, function (i, item) {
                text += '' +
                    '[ ' + item.name + ' ]' +
                    ' , ';
            });
            text = text.substring(0, text.length - 1);
            return text;
        }
    }]
});
