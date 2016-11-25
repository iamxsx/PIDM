/**
 * Created by clouder on 16-9-27.
 */

function deleteFile(event) {
    var target = event.target;
    var id = $(target).attr('fid');
    var fileUrl = $(target).attr('fileUrl');
    $.ajax({
        url:'/back/download-center/delete?id='+id+'&fileUrl='+fileUrl,
        success:function () {
            alert('删除成功');
            $('#file-table').bootstrapTable('refresh');
        },
        error:function () {
            alert('删除失败')
        }
    });
}

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
            field: 'id',
            title: '文件编号',
            width: 120
        }, {
            field: 'fileName',
            title: '文件名',
            width: 120,
        }, {
            field: 'fileUrl',
            title: '文件路径',
            width: 120
        }, {
            field: 'uploadTime',
            title: '上传时间',
            width: 120
        }, {
            field: 'description',
            title: '文件描述',
            width: 120
        },{
            field:'fileUrl',
            title:'操作',
            width:120,
            formatter:function (value,row,index) {
                var r = '<a href="/back/download-center/download?fileUrl='+value+'">下载</a>' +
                    '&nbsp;&nbsp;' +
                    '<a href="javascript:void(0)" fid="'+row.id+'" fileUrl="'+value+'" onclick="deleteFile(event)">删除</a>';
                return r;
            }
        }],

    });



    $('#form-upload').bootstrapValidator({
        fields: {
            file: {
                validators: {
                    notEmpty: {
                        message: '请选择要上传的文件'
                    }
                }
            },
            description: {
                validators: {
                    notEmpty: {
                        message: '文件描述不可为空'
                    }
                }
            },
        }
    });

}(jQuery));
