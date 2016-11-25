/**
 * Created by clouder on 16-9-21.
 */
$(function () {
    $("#my-communicate").bootstrapTable({
        url:'../communicate/myCommunicate',
        method:'get',
        sidePagination:'server',
        pagination:true,
        pageNumber:1,
        pageSize:5,
        pageList:[20,40,60,80],
        striped: true,
        queryParamsType:'limit',
        columns:[{
            field: "type",
            title: "类型",
            width: 80,
            formatter:function(value,row,index){
                if (value == 0){
                    return '经验交流';
                }else if(value == 1){
                    return "建议意见";
                }
            }
        },{
            field:"title",
            title:"标题",
            width: 150,
            formatter:function (value,row,index) {
                if (value.length > 20){
                    return value.substring(0,20) + '...';
                }else {
                    return value;
                }
            }
        },{
            field:"time",
            title:"时间",
            width: 80
        },{
            field:"status",
            title:"状态",
            width: 80,
            formatter:function (value,row,index) {
                if (value == 0){
                    return '未回复';
                }else if(value == 1){
                    return "已回复";
                }
            }
        },{
            field:"id",
            title:"操作",
            width: 80,
            formatter:function (value,row,index) {
                return '<a href="../communicate/userDetail?communicateId='+value+'">查看</a>'
            }
        }]



    })


})