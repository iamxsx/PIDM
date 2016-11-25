/**
 * Created by clouder on 16-9-19.
 */

$('#role-table').bootstrapTable({
    url: 'role/findRoles',
    method: 'get',
    sidePagination: 'client',
    pagination: true,
    pageNumber: 1,
    pageSize: 20,
    pageList: [20, 40, 60, 80],
    striped: true,
    columns: [{
        radio: true
    }, {
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

$('#btn-refresh').click(function () {
    $('#role-table').bootstrapTable('refresh');
});

$('input[name = location]').change(function () {
    var flag = $('#front').prop('checked');
    if (flag){
        console.log('前台');
        $('#treeview-role-menu').css('display','block');
        $('#treeview-back-role-menu').css('display','none');
    }else {
        console.log('后台');
        $('#treeview-role-menu').css('display','none');
        $('#treeview-back-role-menu').css('display','block');
    }
});
getTree();

function getTree() {
    $.ajax({
        url: 'menu/get-front-menu',
        type: 'get',
        success: function (data) {
            var treeview = $('#treeview-role-menu');
            initTreeview(treeview,data);
        }
    });
    $.ajax({
        url: 'menu/get-back-menu',
        type: 'get',
        success: function (data) {
            var treeview = $('#treeview-back-role-menu');
            initTreeview(treeview,data);
        }
    });
}


function initTreeview(treeview,data) {
    treeview.treeview({
        data: data,
        showIcon: false,
        showCheckbox: true,
        onNodeChecked: function (event, node) {

            checkChildNode(treeview,node);
            checkParentNode(treeview,node.parentId);
        },
        onNodeUnchecked: function (event, node) {
            //unCheckParentNode(treeview,node.parentId);
            unCheckChildNode(treeview,node);
        }
    });
}


/**
 * 选择所有的子节点
 * @param treeview
 * @param node
 */
var checkChildNode = function (treeview,node) {
    var nodes = node.nodes;
    if (nodes == null || nodes.length == 0){
        return;
    }
    for (var i = 0; i < nodes.length; i++) {
        treeview.treeview('checkNode', [ nodes[i].nodeId, { silent: true } ]);
        checkChildNode(treeview,nodes[i]);
    }
}
/**
 * 取消选择所有的子节点
 * @param treeview
 * @param node
 */
var unCheckChildNode = function (treeview,node) {
    var nodes = node.nodes;
    if (nodes == null || nodes.length == 0){
        return;
    }
    for (var i = 0; i < nodes.length; i++) {
        treeview.treeview('uncheckNode', [ nodes[i].nodeId, { silent: true } ]);
        unCheckChildNode(treeview,nodes[i]);
    }
}
/**
 * 选择相应的父节点
 * @param treeview
 * @param parentId
 */
var checkParentNode = function (treeview,parentId) {
    if (parentId == null){
        return;
    }
    treeview.treeview('checkNode', [ parentId, { silent: true } ]);
    var node = treeview.treeview('getNode', parentId);
    checkParentNode(treeview,node.parentId);
}

var unCheckParentNode = function (treeview,parentId) {
    if (parentId == null){
        return;
    }
    treeview.treeview('uncheckNode', [ parentId, { silent: true } ]);
    var node = treeview.treeview('getNode', parentId);
    unCheckParentNode(treeview,node.parentId);
}

$('#btn-add-role').click(function () {
    var location = $('input[name = location]:checked').val();
    var treeview;
    if (location == 1){
        treeview = $('#treeview-role-menu');
    }else if(location == 2){
        treeview = $('#treeview-back-role-menu');
    }
    var node = treeview.treeview('getNode', '0');
    var nodes = treeview.treeview('getChecked', node.nodeId);
    var ids = '';
    for (var i=0;i<nodes.length;i++){
        ids += nodes[i].id + ',';
    }
    $.ajax({
        url:'role/bindRoleMenus',
        type:'get',
        data:{
            ids:ids,
            location:location,
            roleName:$('#roleName').val()
        },
        success:function () {
            $('#add-role-modal').modal('hide');
            $('#role-table').bootstrapTable('refresh');
        },
        error:function () {
            alert('添加角色失败');
            $('#add-role-modal').modal('hide');
        }
    });
});

$('#btn-to-delete').click(function(){
    var selections = $('#role-table').bootstrapTable('getSelections');
    if (selections.length != 1) {
        alert('请选择一条要删除的数据！');
        return;
    }
    $('#delete-sure').modal();
});

$('#btn-delete-sure').click(function(){
    var selections = $('#role-table').bootstrapTable('getSelections');
    var id = selections[0].id;
    $.ajax({
        url:'role/delete-role',
        type:'get',
        data:{
            id:id
        },
        success:function () {
            $('#delete-sure').modal('hide');
            $('#role-table').bootstrapTable('refresh');
        },
        error:function () {
            alert('删除失败');
            $('#delete-sure').modal('hide');
        }
    });
});

$('#btn-to-update').click(function () {
    var selections = $('#role-table').bootstrapTable('getSelections');
    if (selections.length != 1) {
        alert('请选择一条要更新的数据！');
        return;
    }
    var selection = selections[0];
    $.ajax({
        url:'menu/get-role-menu',
        type:'get',
        data:{
            roleId:selection.id,
            location:selection.location

        },
        success:function (data) {
            initTreeview($('#treeview-update-role-menu'),data);
            $('#old-role-name').val(selection.name);
            $('#update-role-modal').modal();
        },
        error:function () {
            alert('获取角色权限失败');
        }
    });

});

$('#btn-update-role').click(function () {
    var treeview = $('#treeview-update-role-menu');
    var node = treeview.treeview('getNode', '0');
    var nodes = treeview.treeview('getChecked', node.nodeId);
    var ids = '';
    for (var i=0;i<nodes.length;i++){
        ids += nodes[i].id + ',';
    }
    var selections = $('#role-table').bootstrapTable('getSelections');
    var roleId = selections[0].id;
    $.ajax({
        url:'role/updateRoleMenus',
        type:'get',
        data:{
            ids:ids,
            roleId:roleId,
            roleName:$('#old-role-name').val()
        },
        success:function () {
            $('#update-role-modal').modal('hide');
            $('#role-table').bootstrapTable('refresh');
        },
        error:function () {
            alert('修改角色失败');
            $('#update-role-modal').modal('hide');
        }
    });
});
