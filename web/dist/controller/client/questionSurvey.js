/**
 * Created by clouder on 9/29/16.
 */
var i = 1;
var count = 1;
var singselection = 1;
var mulselect = 0;
var comp = 0;
$('#radio-btn').click(function () {
    var a = "<div class='question'>"+
        "<div class='row'>" +
        "<a type='button' class='remove-btn' id='remove-btn"+i+"' onclick='removeQuestion("+i+",1);'>" +
        "<img src='/imgs/survey/delete.png' class='deleteButton'>" +
        "</a>"+
        "[单选]&nbsp;题目:<input class='form-control question-content' style='box-shadow:none;' type='text' name='topic["+i+"].topicContent' required>"+
        "<input type='hidden' name='topic["+i+"].nature' value='0'>"+
        "</div>"+
        "<span>选项:</span>" +

        "<div class='row'>"+
        "<div class='col-sm-7 answer'>"+
        "<input type='radio' class='optionradio' name='"+i+"'>&nbsp;A.&nbsp;&nbsp;&nbsp;"+
        "<input class='form-control optionContent' type='text' name='topic["+i+"].option[0].optionContent' required>" +
        "<input type='hidden' name='topic["+i+"].option[0].nature' value='A'>"+
        "</div>"+
        "</div>"+
        "<div class='row'>"+
        "<div class='col-sm-7 answer'>"+
        "<input type='radio' class='optionradio' name='"+i+"'>&nbsp;B.&nbsp;&nbsp;&nbsp;"+
        "<input class='form-control optionContent' type='text' name='topic["+i+"].option[1].optionContent' required>" +
        "<input type='hidden' name='topic["+i+"].option[1].nature' value='B'>"+
        "</div>"+
        "</div>"+
        "<div class='row'>"+
        "<div class='col-sm-7 answer'>"+
        "<input type='radio' class='optionradio' name='"+i+"'>&nbsp;C.&nbsp;&nbsp;&nbsp;"+
        "<input class='form-control optionContent' type='text' name='topic["+i+"].option[2].optionContent' required>" +
        "<input type='hidden' name='topic["+i+"].option[2].nature' value='C'>"+
        "</div>"+
        "</div>"+
        "<div class='row'>"+
        "<div class='col-sm-7 answer'>"+
        "<input type='radio' class='optionradio' name='"+i+"'>&nbsp;D.&nbsp;&nbsp;&nbsp;"+
        "<input class='form-control optionContent' type='text' name='topic["+i+"].option[3].optionContent' required>" +
        "<input type='hidden' name='topic["+i+"].option[3].nature' value='D'>"+
        "</div>"+
        "</div>"+
        "</div>";
    $('#con').append(a);
    i++;
    count++;
    singselection++;
    statisNum();
    /*buttondisplay();*/
});
$('#checkbox-btn').click(function () {
    var a = "<div class='question'>"+
        "<div class='row'>" +
        "<a type='button' class='remove-btn' id='remove-btn"+i+"' onclick='removeQuestion("+i+",2);'>" +
        "<img src='/imgs/survey/delete.png' class='deleteButton'>" +
        "</a>"+
        "[多选]&nbsp;题目:<input class='form-control question-content' style='box-shadow:none;' type='text' name='topic["+i+"].topicContent' required>"+
        "<input type='hidden' name='topic["+i+"].nature' value='2'>"+
        "</div>"+
        "<span>选项:</span>" +

        "<div class='row'>"+
        "<div class='col-sm-7 answer'>"+
        "<input type='checkbox' class='optionradio' name='"+i+"'>&nbsp;A.&nbsp;&nbsp;&nbsp;"+
        "<input class='form-control optionContent' type='text' name='topic["+i+"].option[0].optionContent' required>" +
        "<input type='hidden' name='topic["+i+"].option[0].nature' value='A'>"+
        "</div>"+
        "</div>"+
        "<div class='row'>"+
        "<div class='col-sm-7 answer'>"+
        "<input type='checkbox' class='optionradio' name='"+i+"'>&nbsp;B.&nbsp;&nbsp;&nbsp;"+
        "<input class='form-control optionContent' type='text' name='topic["+i+"].option[1].optionContent' required>" +
        "<input type='hidden' name='topic["+i+"].option[1].nature' value='B'>"+
        "</div>"+
        "</div>"+
        "<div class='row'>"+
        "<div class='col-sm-7 answer'>"+
        "<input type='checkbox' class='optionradio' name='"+i+"'>&nbsp;C.&nbsp;&nbsp;&nbsp;"+
        "<input class='form-control optionContent' type='text' name='topic["+i+"].option[2].optionContent' required>" +
        "<input type='hidden' name='topic["+i+"].option[2].nature' value='C'>"+
        "</div>"+
        "</div>"+
        "<div class='row'>"+
        "<div class='col-sm-7 answer'>"+
        "<input type='checkbox' class='optionradio' name='"+i+"'>&nbsp;D.&nbsp;&nbsp;&nbsp;"+
        "<input class='form-control optionContent' type='text' name='topic["+i+"].option[3].optionContent' required>" +
        "<input type='hidden' name='topic["+i+"].option[3].nature' value='D'>"+
        "</div>"+
        "</div>"+
        "</div>";
        "</div>";
    $('#con').append(a);
    i++;
    count++;
    mulselect++;
    statisNum();
    /*buttondisplay();*/
});
$('#completion').click(function () {
    var a = "<div class='question'>"+
        "<div class='row'>"+
        "<a type='button' class='remove-btn' id='remove-btn"+i+"' onclick='removeQuestion("+i+",3);'>" +
        "<img src='/imgs/survey/delete.png' class='deleteButton'>" +
        "</a>"+
        "[填空]&nbsp;题目:<input class='form-control question-content' style='box-shadow:none;' type='text' name='topic["+i+"].topicContent' required>"+
        "<input type='hidden' name='topic["+i+"].nature' value='1'>"+
        "</div>"+
        "</div>";
    $('#con').append(a);
    i++;
    count++;
    comp++;
    statisNum();
    /* buttondisplay();*/
});

/*//导航条拖动时,按钮位置变换
$(function () {
    var scrolltop = $('#button-list').offset().top;
    $(window).scroll(function () {
        var scrollValue = $(window).scrollTop();
        if (scrollValue >= scrolltop){
            $('#button-list').addClass('fix');
            $('#button-list li').addClass('btn-li');
            $('#button-list li div').addClass('button');

        } else {
            $('#button-list').removeClass('fix');
            $('#button-list li').removeClass('btn-li');
            $('#button-list li div').removeClass('button');
        }
    });
});*/

/**
 * 删除
 * @param idnum
 * @param nature 1:单选 2:多选 3:填空
 */
function removeQuestion(idnum,nature) {
    /*alert(idnum);
    alert($('#remove-btn'+idnum).parents('.question').length);*/
    $('#remove-btn'+idnum).parents('.question').remove();
    count--;
    if(nature == 1){
        singselection--;
    }else if(nature == 2){
        mulselect--;
    }else if(nature == 3){
        comp--;
    }
    statisNum();
    buttondisplay();
}

//显示创建的题目数
function statisNum() {
    $("#count").html(count);
    $("#singselection").html(singselection);
    $("#mulselect").html(mulselect);
    $("#comp").html(comp);
}

//保存 发布按钮显示和隐藏
function buttondisplay() {
    if ($('#con').find('div').length){
        $('#btn-list').css('display','block');
    }else{
        $('#btn-list').css('display','none');
    }
}
