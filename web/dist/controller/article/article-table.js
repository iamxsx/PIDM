/**
 * Created by clouder on 16-9-11.
 */

$('#article-table').bootstrapTable({
    url: '/back/article/findArticlesByPage',
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
        title: '文章编号',
        width: 120
    }, {
        field: 'header',
        title: '标题',
        width: 240,
        formatter: function (value, row, index) {
            if (value.length > 20) {
                return value.substring(0, 20) + '...';
            } else {
                return value;
            }
        }
    }, {
        field: 'location.name',
        title: '发布位置',
        width: 120
    }, {
        field: 'author.realName',
        title: '申请人',
        width: 120
    }, {
        field: 'publishTime',
        title: '发布时间',
        width: 120
    }, {
        field: 'type',
        title: '申请类型',
        width: 120,
        formatter: function (value, row, index) {
            if (value == 0) {
                return '发布信息';
            } else if (value == 1) {
                return "删除信息";
            } else if (value == 2) {
                return "修改信息";
            }
        }
    }, {
        field: 'status',
        title: '状态',
        width: 120,
        formatter: function (value, row, index) {
            if (value == 0) {
                return '未发布';
            } else if (value == 1) {
                return "审核中";
            } else if (value == 2) {
                return "退回";
            } else {
                return "已发布";
            }
        }
    }, {
        field: 'id',
        title: '处理',
        width: 120,
        formatter: function (value, row, index) {
            return '' +
                '<a class="like" target="_blank" href="/back/article/article-preview?aid=' + value + '" title="Like">' +
                '预览' +
                '</a>' +
                '&nbsp;&nbsp;&nbsp;' +
                '<a class="like" href="javascript:void(0)" onclick="deleteArticle('+value+')" title="Like">' +
                '刪除' +
                '</a>';
        }
    }
    ]
});

function deleteArticle(ids) {
    $.ajax({
        url:'/back/article/deleteArticles?ids='+ids,
        success:function () {
            alert('删除成功');
            $('#article-table').bootstrapTable('refresh');
        },
        error:function () {
            alert('删除失败');
        }
    });
}

$('#article-published-table').bootstrapTable({
    url: '/back/article/findPublishedArticlesByPage',
    method: 'get',
    sidePagination: 'server',
    pagination: true,
    pageNumber: 1,
    pageSize: 10,
    pageList: [10, 20, 30, 40],
    striped: true,
    queryParamsType: 'limit',
    queryParams: function (params) {
        params.column = $('#select-column').children('option:selected').val();
        params.text = $('#input-text').val();
        return params;
    },
    columns: [{
        radio: true
    }, {
        field: 'id',
        title: '文章编号',
        width: 120
    }, {
        field: 'header',
        title: '标题',
        width: 240,
        formatter: function (value, row, index) {
            if (value.length > 20) {
                return value.substring(0, 20) + '...';
            } else {
                return value;
            }
        }
    }, {
        field: 'location.name',
        title: '发布位置',
        width: 120
    }, {
        field: 'publishTime',
        title: '发布时间',
        width: 120
    }, {
        field: 'author.userName',
        title: '发布人',
        width: 120
    }, {
        field: 'status',
        title: '状态',
        width: 120,
        formatter: function (value, row, index) {
            if (value == 0) {
                return '未发布';
            } else if (value == 1) {
                return "审核中";
            } else if (value == 2) {
                return "退回";
            } else {
                return "已发布";
            }
        }
    }, {
        field: 'approver',
        title: '审批人',
        width: 120
    }]
});

$('#span-search').click(function () {
    $('#article-published-table').bootstrapTable('refresh');
});

$('#btn-delete').click(function () {
    var selections = $('#article-published-table').bootstrapTable('getSelections');
    if (selections.length <= 0) {
        alert('请先选择要删除的数据！');
        return;
    }
    $('#delete-sure').modal('toggle');
});

$('#btn-delete-sure').click(function () {
    var selections = $('#article-published-table').bootstrapTable('getSelections');
    var ids = '';
    for (var i = 0; i < selections.length; i++) {
        ids += selections[i].id + ','
    }
    ids = ids.substring(0, ids.length - 1);
    console.log(ids);
    $.ajax({
        url: '/back/article/deleteArticles',
        type: 'get',
        data: {
            ids: ids
        },
        success: function (data) {
            console.log(data);
            $('#delete-sure').modal('toggle');
            $('#article-published-table').bootstrapTable('refresh');
        },
        error: function () {
            console.log("删除失败")
        }
    });

});

$('#btn-to-add').click(function () {
    window.location.href = '/back/article/to-publish';
});

$('#btn-to-preview').click(function () {
    var selections = $('#article-published-table').bootstrapTable('getSelections');
    if (selections.length != 1) {
        alert('请选择一个预览');
        return;
    }
    var id = selections[0].id;
    window.location.href = '/back/article/article-preview?aid=' + id;
});

$('#btn-to-edit').click(function () {
    var selections = $('#article-published-table').bootstrapTable('getSelections');
    if (selections.length != 1) {
        alert('请选择一个修改');
        return;
    }
    var id = selections[0].id;
    window.location.href = '/back/article/edit?aid=' + id;
});

$('#article-not-published-table').bootstrapTable({
    url: '/back/article/findNotpublishedArticle',
    method: 'get',
    sidePagination: 'server',
    pagination: true,
    pageNumber: 1,
    pageSize: 10,
    pageList: [20, 40, 60, 80],
    striped: true,
    columns: [{
        radio: true
    }, {
        field: 'id',
        title: '文章编号',
        width: 120
    }, {
        field: 'header',
        title: '标题',
        width: 240,
        formatter: function (value, row, index) {
            if (value.length > 20) {
                return value.substring(0, 20) + '...';
            } else {
                return value;
            }
        }
    }, {
        field: 'location.name',
        title: '发布位置',
        width: 120
    }, {
        field: 'publishTime',
        title: '发布时间',
        width: 120
    }, {
        field: 'author.userName',
        title: '发布人',
        width: 120
    }, {
        field: 'status',
        title: '状态',
        width: 120,
        formatter: function (value, row, index) {
            if (value == 0) {
                return '未发布';
            } else if (value == 1) {
                return "审核中";
            } else if (value == 2) {
                return "驳回";
            } else {
                return "已发布";
            }
        }
    }, {
        field: 'approver',
        title: '审批人',
        width: 120
    }]
});

$('#article-verify-table').bootstrapTable({
    url: '/back/article/findVerifiedArticle',
    method: 'get',
    sidePagination: 'server',
    pagination: true,
    pageNumber: 1,
    pageSize: 10,
    pageList: [20, 40, 60, 80],
    striped: true,
    columns: [{
        radio: true
    }, {
        field: 'id',
        title: '文章编号',
        width: 120
    }, {
        field: 'header',
        title: '标题',
        width: 240,
        formatter: function (value, row, index) {
            if (value.length > 20) {
                return value.substring(0, 20) + '...';
            } else {
                return value;
            }
        }
    }, {
        field: 'location.name',
        title: '发布位置',
        width: 120
    }, {
        field: 'publishTime',
        title: '发布时间',
        width: 120
    }, {
        field: 'author.userName',
        title: '发布人',
        width: 120
    }, {
        field: 'status',
        title: '状态',
        width: 120,
        formatter: function (value, row, index) {
            if (value == 0) {
                return '未发布';
            } else if (value == 1) {
                return "审核中";
            } else if (value == 2) {
                return "驳回";
            } else {
                return "已发布";
            }
        }
    }]
});

$('#article-rejected-table').bootstrapTable({
    url: '/back/article/findRejectedArticle',
    method: 'get',
    sidePagination: 'server',
    pagination: true,
    pageNumber: 1,
    pageSize: 10,
    pageList: [20, 40, 60, 80],
    striped: true,
    columns: [{
        radio: true
    }, {
        field: 'id',
        title: '文章编号',
        width: 120
    }, {
        field: 'header',
        title: '标题',
        width: 240,
        formatter: function (value, row, index) {
            if (value.length > 20) {
                return value.substring(0, 20) + '...';
            } else {
                return value;
            }
        }
    }, {
        field: 'location.name',
        title: '发布位置',
        width: 120
    }, {
        field: 'publishTime',
        title: '发布时间',
        width: 120
    }, {
        field: 'author.userName',
        title: '发布人',
        width: 120
    }, {
        field: 'status',
        title: '状态',
        width: 120,
        formatter: function (value, row, index) {
            if (value == 0) {
                return '未发布';
            } else if (value == 1) {
                return "审核中";
            } else if (value == 2) {
                return "驳回";
            } else {
                return "已发布";
            }
        }
    }, {
        field: 'approver',
        title: '审批人',
        width: 120
    }, {
        field: 'id',
        title: '操作',
        formatter: function (value, row, index) {
            return '<a href="/back/article/parseArticle?aid=' + value + '&taskId='+row.taskId+'">查看</a>';
        }
    }]
});