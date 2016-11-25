/**
 * Created by clouder on 16-9-14.
 */



$(function () {
    $.ajax({
        url:"show-detailed-communicate",
        data:{
            communicateId:communicateId
        },
        success:function (data) {
            if(data.title_info.type == 0){
                var type = "咨询";
            }else {
                var type = "建议与意见";
            }
            if(data.title_info.status == 0){
                var status = "未回复";
            }else{
                var status = "已回复";
            }
            $("#title-content-info").append(
                '<input type="hidden" id="communicateId" value="'+data.title_info.id+'">'+
                '<div class="text-center">'+
                '<div style="padding-top: 15px"><h4>标题：&nbsp;&nbsp;'+data.title_info.title+'</h4></div>'+
                '<div class="type-status-cs">类型：'+type+' &nbsp;&nbsp;&nbsp;状态：'+status+'</div>'+
                '</div>'+
                '<div style="margin:0 50px">'+
                '<div class="name-time-cs">'+data.title_info.name+'&nbsp;&nbsp;提问于：&nbsp;&nbsp;'+data.title_info.time+'</div>'+
                '<div class="fengexian"></div>'+
                '<div style="color: #000;font-size: 16px;min-height: 130px;">'+data.title_info.content+'</div>'+
                '</div>'
            )

            var rows = data.rows_reply_question;

            if(rows.length>0){
                rows.sort(function (a,b) {
                    return Date.parse(a.time) - Date.parse(b.time);
                });
                $.each(rows,function (i,item) {
                    $("#reply-question-table").append(
                    '<tr>'+
                    '<td>'+
                    '<div class="name-time-cs">'+item.name+'&nbsp;'+item.time+'</div>'+
                    '<div class="fengexian"></div>'+
                    '<div class="content-cs">'+item.content+'</div>'+
                    '</td>'+
                    '</tr>'
                    );
                })
            }
        }
    })
})

function submitReply() {
    var communicateId = $("#communicateId").val();
    var content = $("#textareaContent").val();
    // alert(communicateId);
    $.ajax({
        url:"submit-reply",
        data:{
            communicate_id:communicateId,
            content:content
        },
        success:function () {
            alert("提交回复成功");
            location.reload(true);
        }
    })
}

function submitQuestion() {
    var communicateId = $("#communicateId").val();
    var content = $("#textareaContent").val();
    // alert(communicateId);
    $.ajax({
        url:"submit-question",
        data:{
            communicate_id:communicateId,
            content:content
        },
        success:function () {
            alert("提交成功");
            location.reload(true);
        }
    })
}
