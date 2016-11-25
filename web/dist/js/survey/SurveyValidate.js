/**
 * Created by clouder on 10/25/16.
 */
//提交按钮点击事件,验证单选题和多选题是否已选择
function checkValidate() {
    var judge = true;
    $("div[name='checkCon']").each(function () {
        var i = 0;
        $(this).find("input[type='checkbox']").each(function () {
            if(this.checked){
                i++;
            }
        });
        if(i<=0) {
            $(this).prev().children().find("div").css("display", "inline");
            judge = false;
        }
    });
    $("div[name='radioCon']").each(function () {
        var i = 0;
        $(this).find("input[type='radio']").each(function () {
            if(this.checked){
                i++;
            }
        });
        if(i<=0) {
            $(this).prev().children().find("div").css("display", "inline");
            judge = false;
        }
    });
    return judge;
}

//多选框点击事件,切换多选题的提示 1:单选验证 2:多选验证
function checkValidateCanel(id,natrue) {
    var j = 0;
    if(natrue ==  1){
        $('#'+id).parents(".option-content").find("input[type='radio']").each(function () {
            if(this.checked){
                j++;
            }
        });
    }else if(natrue == 2){
        $('#'+id).parents(".option-content").find("input[type='checkbox']").each(function () {
            if(this.checked){
                j++;
            }
        });
    }
    if(j>0){
        $('#'+id).parents(".option-content").prev().children().find("div").css("display","none");
    }else{
        $('#'+id).parents(".option-content").prev().children().find("div").css("display","inline");

    }
}

