/**
 * Created by clouder on 16-10-26.
 */

$(function () {

    $("#statisTable").bootstrapTable({
        url:'/back/survey/historyResponse',
        method:'get',
        sidePagination:'server',
        pagination:true,
        pageNumber:1,
        pageSize:10,
        pageList:[5,10,15,20],
        striped:true,
        queryParamsType:'limit',
        columns:[
            {
            title:'序号',
            width:50,
            formatter:function (value,row,index) {
                return index+1;
                }
            },{
            field:'questionnaireSurvey.title',
            title:'问卷标题',
            width:350,
            formatter:function (value,row,index) {
                if (value.length > 25){
                    return value.substring(0,25) + '...';
                }else {
                    return value;
                }
            }
            },{
                field:'questionnaireSurvey.startTime',
                title:'开始时间',
                width:180
            },{
                field:'questionnaireSurvey.endTime',
                title:'结束时间',
                width:180
            },{
                field:'questionnaireSurvey.joinNum',
                title:'参与调查人数',
                width:150
            },{
                field:'questionnaireSurvey.id',
                title:'操作',
                width:150,
                formatter:function (value,row,index) {
                    return '<a href="showStatisChart?id='+value+'">查看统计情况</a>'
                }
            }
        ]
    })

})