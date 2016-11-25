
function clickSaveChangeBtn() {

    // var designatedContactId;
    var spname = $('#spname').val();
    var spjob = $('#spjob').val();
    var spofficephone = $('#spofficephone').val();
    var spcellphone = $('#spcellphone').val();
    var spemail = $('#spemail').val();
    var spafaxnum = $('#spfaxnum').val();

    if(spname !=spname2 || spjob!= spjob2 ||spofficephone !=spofficephone2 ||
        spcellphone != spcellphone2|| spemail!=spemail2 || spafaxnum !=spafaxnum2){
        // alert("指定人 改动");
        var name = $('#spname').val();
        var jobPosition = $('#spjob').val();
        var officePhoneNum = $('#spofficephone').val();
        var cellPhoneNum = $('#spcellphone').val();
        var email = $('#spemail').val();
        var faxNum = $('#spfaxnum').val();
        $.ajax({
            url:"saveCompanyEmployee",
            dataType:"json",
            data:{
                "name":name,
                "jobPosition":jobPosition,
                "officePhoneNum":officePhoneNum,
                "cellPhoneNum":cellPhoneNum,
                "email":email,
                "faxNum":faxNum,

                "companyId":companyId
            },
            success:function (data) {
                // alert(data.companyEmployeeId);
                designatedContactId = data.companyEmployeeId;
                savaUserTemporary(designatedContactId);
            }
        })
    } else {
        // alert("指定人 没修改");
        //指定人ID
        // alert(designatedContactId);
        savaUserTemporary(designatedContactId);
    }
}

//保存修改用户信息到 临时表
function savaUserTemporary(designatedContactId) {
    var realName = $('#realName').val();
    var phoneNum= $('#phoneNum').val();
    var IDcard= $('#IDcard').val();
    var email = $('#email').val();
    $.ajax({
        url:"savaUserTemporary",
        data:{
            "designatedContactId":designatedContactId,
            "uId":uId,
            "realName":realName,
            "phoneNum":phoneNum,
            "IDcard":IDcard,
            "email":email
        },
        success:function (data) {
            var userTemporary = data.userTemporaryId;
            if(userTemporary != ""){
                alert("修改申请提交成功，请等待审核完成，谢谢！");
                window.location.href="showUserInfo";
            }else {
                alert("修改信息提交失败，请重试！");
            }
        }
    })
}

//改变按钮状态
function changeBtn() {
    var realName = $('#realName').val();
    var phoneNum= $('#phoneNum').val();
    var IDcard= $('#IDcard').val();
    var email = $('#email').val();
    var spname = $('#spname').val();
    var spjob = $('#spjob').val();
    var spofficephone = $('#spofficephone').val();
    var spcellphone = $('#spcellphone').val();
    var spemail = $('#spemail').val();
    var spafaxnum = $('#spfaxnum').val();

    // alert(realName2);alert(realName);
    // alert(phoneNum2);alert(phoneNum);
    // alert(IDcard2);alert(IDcard);
    // alert(email2);alert(email);
    // alert(spname2);alert(spname);
    // alert(spjob2);alert(spjob);
    // alert(spofficephone2);alert(spofficephone);
    // alert(spcellphone2);alert(spcellphone);
    // alert(spemail2);alert(spemail);
    // alert(spafaxnum2);alert(spafaxnum);

    if(realName !=realName2 || phoneNum !=phoneNum2 || IDcard !=IDcard2 ||email !=email2 ||
                spname !=spname2 || spjob!= spjob2 ||spofficephone !=spofficephone2 ||
                        spcellphone != spcellphone2|| spemail!=spemail2 || spafaxnum !=spafaxnum2){

        $("#save-button").attr("disabled",false);
    }else {
        $("#save-button").attr("disabled",true);
    }
}







// function changeBtn() {
//     $("#save-button").attr("disabled",false);
// }