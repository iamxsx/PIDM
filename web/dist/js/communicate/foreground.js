/**
 * Created by clouder on 16-9-13.
 */

var limit = 3;
var offset = 0;

$(function () {

    $.ajax({
        url:"/communicate/othersCommunicate",
        data:{
            "limit":limit,
            "offset":offset
        },
        success:function (data) {
            if(data.rows.length>0){
                $.each(data.rows,function (i,item) {
                    $("#othersCommunicateTip").append(
                        '<span class="tipcss">问题:</span>'+
                        '<div class="othersTitle">'+item.title+'</div>'+
                        '<div class="othersContent">'+item.content+'</div>'+
                        '<div  class="small questionTime">'+item.askTime+'</div>'+
                        '<span class="tipcss">回复:</span>'+
                        '<div class="replyContent" style="font-size:15px">'+item.replyContent+'</div>'+
                        '<div class="small replyTime">'+item.replyTime+'</div>'+
                        '<hr>'
                    );
                })
            }else{
                $("#showMoreBtn").hide();
                $(".othersCommunicateCss").append('<div style="font-size: 18px;color: rgb(155, 155, 170);width: 770px;height: 100%;padding: 120px 300px">交流园地暂无交流项</div>');
            }


        }
    })

})

// 设置标题输入长度
function setShowLength(obj, maxlength, id)
{
    var rem = maxlength - obj.value.length;
    var wid = id;
    if (rem < 0){
        rem = 0;
    }
    document.getElementById(wid).innerHTML = rem+"/25";
}

//修改按钮 是否可用
function changeBtn() {
    var title = $("#questionTitle").val();
    var content = $("#textareaContent").val();
    if(title !="" && content != ""){
        $("#submitQuestionBtn").attr("disabled",false);
    }else {
        $("#submitQuestionBtn").attr("disabled",true);
    }
}
function changeBtn2() {
    var title = $("#suggestTitle").val();
    var content = $("#textareaContent").val();
    if(title !="" && content != ""){
        $("#submitQuestionBtn").attr("disabled",false)
    }else {
        $("#submitQuestionBtn").attr("disabled",true);
    }
}

//提交建议意见
function submitSuggest(){
    if($("#loginIdMaster").length == 0){
        alert("请先登录，谢谢!");
    }else {
        //type为1 建议意见
        var type =1;
        var title = $("#suggestTitle").val();
        var content =$("#textareaContent").val();
        $.ajax({
            url:"/communicate/submit-communicate",
            data:{
                title:title,
                content:content,
                type:type,
                },
            success:function (data) {
                alert("提交成功，感谢您的建议意见！");
                location.reload(true);
            }
        })
    }
}

function submitQuestion() {
    if($("#loginIdMaster").length == 0){
        alert("请先登录，谢谢!");
    }else {
        //type为0 经验交流 问题
        var type =0;
        var title = $("#questionTitle").val();
        var content = $("#textareaContent").val();
        $.ajax({
            url:"/communicate/submit-communicate",
            data:{
                title:title,
                content:content,
                type:type,
            },
            success:function (data) {
                alert("提交成功，感谢您的经验交流！");
                location.reload(true);
            }
        })
    }
}

function showMore(){

    offset = offset + limit ;
    // alert(offset);
    $.ajax({
        url:"/communicate/othersCommunicate",
        data:{
            "limit":limit,
            "offset":offset
        },
        success:function (data) {
            if(data.rows.length>0){
                $.each(data.rows,function (i,item) {
                    $("#othersCommunicateTip").append(
                        '<span class="tipcss">问题:</span>'+
                        '<div class="othersTitle">'+item.title+'</div>'+
                        '<div class="othersContent">'+item.content+'</div>'+
                        '<div  class="small questionTime">'+item.askTime+'</div>'+
                        '<span class="tipcss">回复:</span>'+
                        '<div class="replyContent" style="font-size:15px">'+item.replyContent+'</div>'+
                        '<div class="small replyTime">'+item.replyTime+'</div>'+
                        '<hr>'
                    );
                })
            }else {
                $("#showMoreBtn").text("全部加载完毕");
            }
        }
    })
}