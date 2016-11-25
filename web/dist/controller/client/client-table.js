/**
 * Created by clouder on 9/14/16.
 */
$(function () {
    var oTable = new TableInit();
    $("#searchButton").click(function () {
        $('#client-table').bootstrapTable('selectPage', 1);

    });

    //导出客户信息为excel
    $("#export-excel").click(function () {
        window.location.href='/back/user/getUserSimpleInfo?keyWord='+$('#Search').val()+'&&searchCondition='+$('#searchcon').val()
    });
});
var TableInit = function () {
    $('#client-table').bootstrapTable({
        url:'/back/user/getUserSimpleInfoLimit',
        method:'get',
        sidePagination:'server',
        pagination:true,
        pageNumber:1,
        pageSize:10,
        pageList:[10,25,50,100],
        striped: true,
        queryParamsType:'limit',
        queryParams:function(params){
            params.searchCondition = $('#searchcon').val();
            params.keyWord = $('#Search').val();
            return params;
        },
        columns:[{
            field:'id',
            formatter:function (value,row,index) {
                return "<input type='radio' name='radio' value="+value+">";
            },
            width:30
        },{
            title:'序号',
            formatter:function (value,row,index) {
                return index+1;
            },
            width:50
        },{
            field:'account',
            title:'帐号',
            width:120
        },{
            field:'name',
            title:'单位名称',
            width:250
        },{
            field:'address',
            title:'单位地址',
            width:350
        },{
            field:'desContactName',
            title:'指定联系人',
            width:120
        },{
            field:'cell_phone_num',
            title:'指定联系人手机',
            width:160
        },{
            field:'fax_num',
            title:'传真',
            width:120
        },{
            field:'email',
            title:'邮箱',
            width:120
        }]
    });
}
