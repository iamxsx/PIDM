/**
 * Created by clouder on 16-10-8.
 */
$('#log-table').bootstrapTable({
    url: '/log/findExceptionLogsByPage',
    method: 'get',
    sidePagination: 'server',
    pagination: true,
    pageNumber: 1,
    pageSize: 10,
    pageList: [20, 40, 60, 80],
    striped: true,
    queryParamsType: 'limit',
    columns: [{
        field: 'id',
        title: '异常编号',
        width: 40
    },{
        field: 'exceptionName',
        title: '异常名',
        width: 120
    },{
        field:'exceptionMethod',
        title:'发生异常的方法',
        width:120
    },{
        field:'methodArgs',
        title:'参数',
        width:120
    },{
        field:'time',
        title:'发送时间',
        width:120
    },{
        field:'exceptionDesc',
        title:'描述',
        width:200,
        formatter:function (value, row, index) {
            return '<a href="javascript:void(0)" desc="'+value+'" onclick="showExceptionDesc(event)">详情</a>';
        }
    }]
});

function showExceptionDesc(event) {
    $('#exception-modal #content').html($(event.target).attr('desc'));
    $('#exception-modal').modal('toggle');
}