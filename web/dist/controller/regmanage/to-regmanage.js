/**
 * Created by clouder on 16-9-19.
 */
$(function () {
    var lis = $('.left-nav .add-active');
    $(lis[1]).addClass('active');
});
$('#to-do-table').bootstrapTable({
    url:'/back/regmanage/findRegMessage',
    method:'post',
    sidePagination:'server',
    pagination:true,
    showRefresh: true,
    striped:true,
    pageNumber:1,
    pageSize:5,
    pageList:[20,40,60,80],
    striped: true,
    queryParamsType:'limit',
    queryParams:function queryParams(params) {
        if (params.offset<0){
            params.offset = 0;
        }
        params.type = $('#table-type option:selected').val();
        params.condition = $('#regmanege-input').val();
        console.log(params.type);
        console.log(params.condition);
        return params;
    },
    columns:[{
        field:'identifier',
        title:'客户编号',
        width:80
    },{
        field:'companyName',
        title:'单位名称',
        width:120
    },{
        field:'associationName',
        title:'加入协会',
        width:120
    },{
        field:'branchName',
        title:'加入分会',
        width:100,
        formatter:function (value,row,index) {
            if(value == ''){
                return '';
            }else {
                return value;
            }
        }
    },{
        field:'city',
        title:'所在地区',
        width:120
    },{
        field:'associationId',
        title:'操作',
        width:60,
        formatter:function (value,row,index) {
            var associationId = row.associationId;
            var companyId = row.companyId;
            return '<a href="to-regActivity?companyId='+companyId+'" >注册管理</a>'
        }
    }
    ]
});


    function tofind() {
        $('#to-do-table').bootstrapTable('selectPage', 1);//直接会请求接口数据，不需要再refresh
    };
