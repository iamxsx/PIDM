/**
 * Created by clouder on 16-9-17.
 */

$(function () {

    $('#showList-table').bootstrapTable({
        url:'/back/communicate/show-communicate-list',
        method:'get',
        sidePagination:'server',
        pagination:true,
        pageNumber:1,
        pageSize:10,
        pageList:[5,10,25,50],
        striped: true,
        queryParamsType:'limit',
        queryParams:function(params) {
            params.column = $('#select-column').children('option:selected').val();
            params.text = $('#input-text').val();
            return params;
        },
        columns:[{
            field:'communicateId',
            // radio: true,
            formatter:function (value,row,index) {
                return '<input type="radio" onclick="radioCheck()" name="radio" value='+value+'>';
            }
        },{
            title:'序号',
            width:50,
            formatter:function (value,row,index) {
                return index+1;
            }
        },{
            field:'title',
            title:'标题',
            width:300,
            formatter:function (value,row,index) {
                if (value.length > 20){
                    return value.substring(0,20) + '...';
                }else {
                    return value;
                }
            }
        },{
            field:'type',
            title:'类型',
            width:120,
            formatter:function(value,row,index){
                if (value == 0){
                    return '经验交流';
                }else if(value == 1){
                    return "建议意见";
                }
            }
        },{
            field:'companyName',
            title:'客户名称',
            width:120
        },{
            field:'askName',
            title:'提起人',
            width:120
        },{
            field:'askTime',
            title:'提起时间',
            width:120
        },{
            field:'employeeName',
            title:'回复人',
            width:120
        },{
            field:'replyTime',
            title:'回复时间',
            width:120
        },{
            field:'status',
            title:'状态',
            width:120,
            formatter:function(value,row,index){
                if (value == 0){
                    return '未回复';
                }else if(value == 1){
                    return "已回复";
                }
            }
        }]
    });

});

function tofindByKey() {
    // $('#to-do-table').bootstrapTable('refresh');
    $('#showList-table').bootstrapTable('selectPage', 1);//直接会请求接口数据，不需要再refresh
};

function showDetail() {
    var communicateId = $("#showList-table input[name='radio']:checked").val();
    // alert(communicateId);
    window.location.href = 'detail?communicateId='+communicateId;
}

function radioCheck() {
    $("#showDetailBtn").attr("disabled",false);
}
