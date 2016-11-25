/**
 * Created by clouder on 16-9-27.
 */


(function ($) {

    $('#file-table').bootstrapTable({
        url: '/back/download-center/getFileList',
        method: 'get',
        sidePagination: 'server',
        pagination: true,
        pageNumber: 1,
        pageSize: 10,
        pageList: [20, 40, 60, 80],
        striped: true,
        queryParamsType: 'limit',
        columns: [{
            field: 'fileName',
            title: '文件名',
            width: 120,
            align:'center'
        }, {
            field: 'uploadTime',
            title: '上传时间',
            width: 120,
            align:'center'
        }, {
            field: 'description',
            title: '文件描述',
            width: 120,
            align:'center'
        },{
            field:'fileUrl',
            title:'操作',
            width:120,
            align:'center',
            formatter:function (value,row,index) {
                return '<a href="/back/download-center/download?fileUrl='+value+'">下载</a>';
            }
        }],

    });

}(jQuery));
