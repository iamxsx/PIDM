
//删除题目 nature 1:单选 2:多选 3:填空
function removeQues(idnum,nature) {
    /*alert(idnum);
     alert($('#remove-btn'+idnum).parents('.question').length);*/
    $.ajax({
        url:"/back/survey/deleteTopic",
        type:"post",
        data:{deleteId:idnum},
        success:function () {
            if(nature == 1){
                singselection--;
            }else if(nature == 2){
                mulselect--;
            }else if(nature == 3){
                comp--;
            }
            count--;
            statisNum();
            $('#'+idnum).parents('.ques').remove();
        },
        error:function (data) {
            alert('删除失败!');
        }
    });

}

//显示创建的题目数
function statisNum() {
    $("#count").html(count);
    $("#singselection").html(singselection);
    $("#mulselect").html(mulselect);
    $("#comp").html(comp);
}

// //重新发布
// $('#submit').click(function () {
//     $.ajax({
//         url:"/back/survey/change",
//         type:"post",
//         data:{id:$('#questionnaireSurveyId').val()},
//         success:function () {
//             location.href="surveyManage";
//         },
//         error:function () {
//             alert('发布失败!');
//         }
//     });
// });

//修改
function blurChange(id,content,nature,beContent) {
    var c = '#'+content;
    var a = $('#'+content).val();
    if($('#'+content).val() != beContent){

        $.ajax({
            url:"/back/survey/changeContent",
            type:"post",
            data:{
                id:id,
                content:$('#'+content).val(),
                junature:nature
            },
            error:function () {
                alert("修改失败!");
            }
        });
    }
}

//新增空题目
function addTopic(topicNature,sid) {
    $.ajax({
        url:"/back/survey/addTopic",
        type:"post",
        data:{
            topicNature:topicNature,
            sid:sid,
            optionNum:4
        },
        success:function (data) {
            if (topicNature == 0){
                var option = "";
                var con = "<div class='ques'>"+
                        "<div class='topic'>"+
                        "<div class='row topic-content'>"+
                        "<a type='button' class='remove-btn' id='"+data.tid+"' onclick='removeQues("+data.tid+",1)'>" +
                        "<img src='/imgs/survey/delete.png' class='deleteButton'>"+
                        "</a>"+
                        "[单选]&nbsp;题目:"+
                        "</div>"+
                        "<div class='row'>" +
                        "<div class='col-sm-10'>" +
                        "<input name='topicContent" + data.tid + "' class='form-control question-content' style='box-shadow:none;' type='text' id='topicContent" + data.tid + "' value='' onblur='blurChange(" + data.tid + ",\"topicContent" + data.tid + '"' + ",1,null)' required>" +
                        "</div></div></div>" +
                        "<div class='options'>";
                        $.each(data.optionList,function (i,item) {
                            option +="<div class='row'>"+
                                "<div class='col-sm-7 answer'>"+
                                "<input type='radio' class='optionradio' name='topic[${status.index}].option[0].id' value='"+item.id+"'>"+item.nature+".&nbsp;&nbsp;&nbsp;"+
                                "<input name='option"+item.id+"' type='text' value='' class='form-control optionContent' id='option"+item.id+"' onblur='blurChange("+item.id+","+'"option'+item.id+'"'+",2,null)' required>"+
                                "</div>"+
                                "</div>"

                        });

                        option +="</div></div>";
                        con +=option;
                singselection++;
            }else if (topicNature == 2){
                var option = "";
                var con = "<div class='ques'>"+
                    "<div class='topic'>"+
                    "<div class='row topic-content'>"+
                    "<a type='button' class='remove-btn' id='"+data.tid+"' onclick='removeQues("+data.tid+",2)'>" +
                    "<img src='/imgs/survey/delete.png' class='deleteButton'>"+
                    "</a>"+
                    "[多选]&nbsp;题目:"+
                    "</div>"+
                    "<div class='row'>" +
                    "<div class='col-sm-10'>" +
                    "<input name='topicContent" + data.tid + "' class='form-control question-content' style='box-shadow:none;' type='text' id='topicContent" + data.tid + "' value='' onblur='blurChange(" + data.tid + ",\"topicContent" + data.tid + '"' + ",1,null)' required>" +
                    "</div></div></div>" +
                    "<div class='options'>";
                    $.each(data.optionList,function (i,item) {
                        option +="<div class='row'>"+
                            "<div class='col-sm-7 answer'>"+
                            "<input type='checkbox' class='optionradio' name='topic[${status.index}].option["+i+"].id' value='"+item.id+"'>"+item.nature+".&nbsp;&nbsp;&nbsp;"+
                            "<input name='option"+item.id+"' type='text' value='' class='form-control optionContent' id='option"+item.id+"' onblur='blurChange("+item.id+","+'"option'+item.id+'"'+",2,null)' required>"+
                            "</div>"+
                            "</div>"

                    });

                option +="</div></div>";
                con +=option;
                mulselect++;
            }else {
                if (topicNature == 1) {
                    var con = "<div class='ques'>" +
                        "<div class='topic'>" +
                        "<div class='row topic-content'>" +
                        "<a type='button' class='remove-btn' id='" + data.tid + "' onclick='removeQues(" + data.tid + ",3)'>" +
                        "<img src='/imgs/survey/delete.png' class='deleteButton'>"+
                        "</a>" +
                        "[填空]&nbsp;题目:" +
                        "</div>" +
                        "<div class='row'>" +
                        "<div class='col-sm-10'>" +
                        "<input name='topicContent" + data.tid + "' class='form-control question-content' style='box-shadow:none;' type='text' id='topicContent" + data.tid + "' value='' onblur='blurChange(" + data.tid + "," + '"topicContent' + data.tid + '"' + ",1,null)' required>" +
                        "</div>" +
                        "</div>" +
                        "</div>" +
                        "</div>"
                    comp++;
                }
            }
            $('#topicCon').append(con);
            count++;
            statisNum();
        },
        error:function () {
            alert("添加失败!");
        }
    })
}