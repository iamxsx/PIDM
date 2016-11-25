<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 10/18/16
  Time: 11:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: AngryFeng
  Date: 16-10-14
  Time: 下午10:00
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>驳回信息修改</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta charset="UTF-8">

    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/n_user/register.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/login.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav_frontend.css">
    <%--<link rel="stylesheet" href="${ctx}/dist/css/fileinput.min.css">--%>
    <link rel="stylesheet" href="${ctx}/dist/css/client/clienInfo.css">

</head>
<body>
<jsp:include page="/WEB-INF/jsp/foreground/header.jsp"/>
<div class="container">
    <div class="row" style="margin-top: 24px;">
        <div class="col-md-2">
            <ul class="left-nav">
                <li class="head">
                    <div class="left-nav-item">
                        <span>个人中心</span>
                    </div>
                </li>
                <li>
                    <div class="left-nav-item">
                        <a href="../work/to-personal">
                            消息管理
                        </a>
                    </div>
                </li>
                <li>
                    <div class="left-nav-item">
                        <a href="${ctx}/nUser/showNUserInfo">
                            信息管理
                        </a>
                    </div>
                </li>
            </ul>
        </div>
        <div class="col-md-10 right-panel">
            <form id="register-form" action="${ctx}/nUser/changeUserCheck" method="post" enctype="multipart/form-data">
            <input type="hidden" name="user.id" value="${clientInfo[0].id}">
            <input type="hidden" name="user.account" value="${clientInfo[0].account}">
            <input type="hidden" name="user.password" value="${clientInfo[0].password}">
            <input type="hidden" name="user.salt" value="${clientInfo[0].salt}">
            <input type="hidden" name="user.verifycode" value="${clientInfo[0].verifycode}">
            <input type="hidden" name="user.userRole" value="${clientInfo[0].user_role}">
            <input type="hidden" name="company.id" value="${clientInfo[0].company_id}">

            <div id="user-box" class="">
                <table class="table table-bordered">
                    <caption class="table-title">账号基本信息</caption>
                    <tr>
                        <th colspan="2"><span class="font-x">*</span>账号</th>
                        <td colspan="2">${clientInfo[0].account}</td>
                    </tr>
                    <%--<tr>
                        <th><span class="font-x">*</span>密码</th>
                        <td><input type="password" name="user.password" id="user-pw" placeholder="请输入8~16位的密码"></td>
                        <th><span class="font-x">*</span>确认密码</th>
                        <td><input type="password" name="rePassword"></td>
                    </tr>--%>
                    <tr>
                        <th><span class="font-x">*</span>申请人姓名</th>
                        <td><input type="text" id="userName" name="user.realName" value="${clientInfo[0].real_name}" onblur="changeblur('userName','${clientInfo[0].real_name}')"></td>
                        <th><span class="font-x">*</span>申请人身份证</th>
                        <td><input type="text" id="userIDcard" name="user.IDcard" value="${clientInfo[0].IDcard}" onblur="changeblur('userIDcard','${clientInfo[0].IDcard}')"></td>
                    </tr>
                    <tr>
                        <th><span class="font-x">*</span>申请人手机号码</th>
                        <td><input type="text" id="userPhoneNum" name="user.phoneNum" value="${clientInfo[0].phone_num}" onblur="changeblur('userPhoneNum','${clientInfo[0].phone_num}')"></td>
                        <th><span class="font-x">*</span>申请人联系邮箱</th>
                        <td><input type="text" id="userEmail" name="user.email" value="${clientInfo[0].uemail}" onblur="changeblur('userEmail','${clientInfo[0].uemail}')"></td>
                    </tr>
                </table>
            </div>
            <%--<div class="progress-box">
                <h2>Step2：填写入会申请表信息</h2>
            </div>--%>
            <div id="">
                <div id="user-info" class="user page">
                </div>
                <table class="table table-bordered page" style="vertical-align: middle">
                    <caption class="table-title">广东省价格和产业发展协会帐号详细信息</caption>
                    <tbody>
                    <tr>
                        <th><span class="font-x">*</span>单位名称</th>
                        <td colspan="3">
                            <input type="text" name="company.name" id="com-name" value="${clientInfo[0].companyName}" onblur="changeblur('com-name','${clientInfo[0].companyName}')">
                            <span id="companyName" style="display: none;font-size: 12px;color: red">提示&nbsp;&nbsp;:&nbsp;&nbsp;单位名称已存在!</span>
                        </td>
                        <th><span class="font-x">*</span>单位性质</th>
                        <td><input type="text" id="companyNature" name="company.nature" value="${clientInfo[0].mnature}" onblur="changeblur('companyNature','${clientInfo[0].mnature}')"></td>
                    </tr>
                    <tr>
                        <th><span class="font-x">*</span>通讯地址</th>
                        <td colspan="3"><input type="text" id="companyAddress" name="company.address" value="${clientInfo[0].address}" onblur="changeblur('companyAddress','${clientInfo[0].address}')"></td>
                        <th><span class="font-x">*</span>邮政编码</th>
                        <td><input type="text" id="companyZipCode" name="company.zipCode" value="${clientInfo[0].zip_code}" onblur="changeblur('companyZipCode','${clientInfo[0].zip_code}')"></td>
                    </tr>
                    <tr>
                        <th rowspan="2"><span class="font-x">*</span>法人代表</th>
                        <th><span class="font-x">*</span>姓名</th>
                        <th><span class="font-x">*</span>单位职务</th>
                        <th><span class="font-x">*</span>办公电话</th>
                        <th colspan="2"><span class="font-x">*</span>手机</th>
                    </tr>
                    <tr>
                        <c:forEach items="${clientInfo}" var="item" varStatus="status">
                            <c:choose>
                                <c:when test="${item.emnature == 2}">
                                    <input type="hidden" name="legalRep.id" value="${item.EmployeeId}" onblur="changeblur('userIDcard','${clientInfo[0].IDcard}')">
                                    <td><input type="text" id="legalRepName" name="legalRep.name" value="${item.desName}" onblur="changeblur('legalRepName','${item.desName}')"></td>
                                    <td><input type="text" id="legalRepJob" name="legalRep.jobPosition" value="${item.job_position}" onblur="changeblur('legalRepJob','${item.job_position}')"></td>
                                    <td><input type="text" id="legalRepOff" name="legalRep.officePhoneNum" value="${item.office_phone_num}" onblur="changeblur('legalRepOff','${item.office_phone_num}')"></td>
                                    <td colspan="2"><input id="legalRepCell" type="text" name="legalRep.cellPhoneNum" value="${item.cell_phone_num}" onblur="changeblur('legalRepCell','${item.cell_phone_num}')"></td>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </tr>
                    <tr>
                        <th rowspan="7"><span class="font-x">*</span>申请加入</th>
                        <th colspan="2">协会</th>
                        <th colspan="3">会员单位级别</th>
                    </tr>
                    <tr>
                        <td rowspan="2" colspan="2"><input type="checkbox" name="company.associationName"
                                                           value="广东省价格和产业发展协会"
                                                           id="association-name"><label
                                for="association-name">广东省价格和产业发展协会</label>
                        </td>
                        <td class="my-td-border"><input type="radio" name="company.associationUnit" value="副会长单位"
                                                        id="asct-jp1"><label
                                for="asct-jp1">副会长单位</label></td>
                        <td class="my-td-border"><input type="radio" name="company.associationUnit" value="监事长单位"
                                                        id="asct-jp2"><label
                                for="asct-jp2">监事长单位</label></td>
                        <td class="my-td-rborder" width="150px"><input type="radio" name="company.associationUnit" value="常务理事单位"
                                                        id="asct-jp3"><label
                                for="asct-jp3">常务理事单位</label>
                        </td>
                    </tr>
                    <tr>
                        <td class="my-td-border"><input type="radio" name="company.associationUnit" value="理事单位"
                                                        id="asct-jp4"><label for="asct-jp4">理事单位</label>
                        </td>
                        <td class="my-td-border"><input type="radio" name="company.associationUnit" value="监事单位"
                                                        id="asct-jp5"><label for="asct-jp5">监事单位</label>
                        </td>
                        <td class="my-td-rborder"><input type="radio" name="company.associationUnit" value="会员单位"
                                                        id="asct-jp6"><label for="asct-jp6">会员单位</label>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">分会</th>
                        <th colspan="3">会员单位级别</th>
                    </tr>
                    <tr>
                        <td class="my-td-tborder"><input type="radio" name="company.chapterName" value="评估鉴定分会"
                                                         id="chapt-name1"/><label for="chapt-name1">评估鉴定分会</label></td>
                        <td class="my-td-trborder"><input type="radio" name="company.chapterName" value="电力价格分会"
                                                          id="chapt-name2"/><label for="chapt-name2">电力价格分会</label></td>
                        <td class="my-td-tborder"><input type="radio" name="company.chapterUnit" value="会长单位"
                                                         id="chapt-unit1"/><label
                                for="chapt-unit1">会长单位</label></td>
                        <td class="my-td-rborder" colspan="2"><input type="radio" name="company.chapterUnit" value="副会长单位"
                                                         id="chapt-unit2"/><label
                                for="chapt-unit2">副会长单位</label></td>
                    </tr>
                    <tr>
                        <td class="my-td-border"><input type="radio" name="company.chapterName" value="农产品价格分会"
                                                        id="chapt-name3"/><label for="chapt-name3">农产品价格分会</label></td>
                        <td class="my-td-rborder"><input type="radio" name="company.chapterName" value="燃气价格分会"
                                                         id="chapt-name4"/><label for="chapt-name4">燃气价格分会</label></td>
                        <td colspan="" class="my-td-border"><input type="radio" name="company.chapterUnit" value="常务理事单位"
                                                                   id="chapt-unit3"/><label
                                for="chapt-unit3">常务理事单位</label>
                        </td>
                        <td colspan="2" class="my-td-rborder"><input type="radio" name="company.chapterUnit" value="理事单位"
                                                                   id="chapt-unit4"/><label
                                for="chapt-unit4">理事单位</label></td>
                    </tr>
                    <tr>
                        <td class="my-td-border"><input type="radio" name="company.chapterName" value="价格与公平竞争分会"
                                                        id="chapt-name5"/><label
                                for="chapt-name5">价格与公平竞争分会</label></td>
                        <td class="my-td-rborder">
                            <button type="button" class="btn btn-default hidd" id="cancle-btn1">撤销选择</button>
                        </td>
                        <td colspan="3" class="my-td-rborder"><input type="radio" name="company.chapterUnit" value="会员单位"
                                                        id="chapt-unit5"/><label
                                for="chapt-unit5">会员单位</label></td>
                        <!--<td class="my-td-rborder">
                            <button type="button" class="btn btn-default hidd" id="cancle-btn2">撤销选择</button>
                        </td>-->
                    </tr>
                    <c:forEach items="${clientInfo}" var="item" varStatus="status">
                        <c:choose>
                            <c:when test="${item.emnature == 1}">
                                <input type="hidden" name="introduceds[${status.index}].id" value="${item.EmployeeId}">
                                <th rowspan="5">推荐人选及拟任分会职务
                                </th>
                                <tr>
                                    <th>姓名</th>
                                    <th>单位职务</th>
                                    <th>协会职务</th>
                                    <th colspan="2">分会职务</th>
                                </tr>
                                <tr>
                                    <td rowspan="3"><textarea name="introduceds[${status.index}].name" class="textarea-sty"
                                                              id="intr${status.index}-name" onblur="changeblur('intr${status.index}-name','${item.desName}')">${item.desName}</textarea></td>
                                    <td><input type="text" id="intr${status.index}-jobPosition" name="introduceds[${status.index}].jobPosition" value="${item.job_position}" onblur="changeblur('intr${status.index}-jobPosition','${item.job_position}')"></td>
                                    <td><input type="text" id="intr${status.index}-asctJob" name="introduceds[${status.index}].asctJobPosition" value="${item.asct_job_position}" onblur="changeblur('introduceds[${status.index}].asctJobPosition','${item.asct_job_position}')"></td>
                                    <td colspan="2"><input  type="text" id="intr${status.index}-chapterJob" name="introduceds[${status.index}].chapterJobPosition" value="${item.chapter_job_position}" onblur="changeblur('intr${status.index}-chapterJob','${item.chapter_job_position}')"></td>
                                </tr>
                                <tr>
                                    <th>电子邮箱</th>
                                    <th>办公电话</th>
                                    <th colspan="2">手机</th>
                                </tr>
                                <tr>
                                    <td><input type="text" id="intr${status.index}-email" name="introduceds[${status.index}].email" value="${item.ememail}" onblur="changeblur('intr${status.index}-email','${item.ememail}')"></td>
                                    <td><input type="text" id="intr${status.index}-office" name="introduceds[${status.index}].officePhoneNum" value="${item.office_phone_num}" onblur="changeblur('userIDcard','${item.office_phone_num}')"></td>
                                    <td colspan="2"><input type="text" id="intr${status.index}-cell" name="introduceds[${status.index}].cellPhoneNum" value="${item.cell_phone_num}" onblur="changeblur('intr${status.index}-cell','${item.cell_phone_num}')"></td>
                                </tr>
                            </c:when>
                        </c:choose>
                    </c:forEach>

                    <c:forEach items="${clientInfo}" var="item" varStatus="status">
                    <c:choose>
                    <c:when test="${item.emnature == 0}">
                    <input type="hidden" name="designatedContacts[${status.index}].id" value="${item.EmployeeId}">

                    <tr>
                        <th rowspan="4"><span class="font-x">*</span>单位指定联系人</th>
                        <th><span class="font-x">*</span>姓名</th>
                        <th><span class="font-x">*</span>单位职务</th>
                        <th><span class="font-x">*</span>办公电话</th>
                        <th colspan="2"><span class="font-x">*</span>手机</th>
                    </tr>
                    <tr>
                        <td rowspan="3"><textarea id="des${status.index}-Name" name="designatedContacts[${status.index}].name" class="textarea-sty" onblur="changeblur('des${status.index}-Name','${item.desName}')">${item.desName}</textarea></td>
                        <td><input type="text" id="des${status.index}-job" name="designatedContacts[${status.index}].jobPosition" value="${item.job_position}" onblur="changeblur('des${status.index}-job','${item.job_position}')"></td>
                        <td><input type="text" id="des${status.index}-office" name="designatedContacts[${status.index}].officePhoneNum"value="${item.office_phone_num}" onblur="changeblur('des${status.index}-office','${item.office_phone_num}')"></td>
                        <td colspan="2"><input type="text" id="des${status.index}-cell" name="designatedContacts[${status.index}].cellPhoneNum" value="${item.cell_phone_num}" onblur="changeblur('des${status.index}-cell','${item.cell_phone_num}')"></td>
                    </tr>
                    <tr>
                        <th><span class="font-x">*</span>电子邮箱</th>
                        <th><span class="font-x">*</span>传真号码</th>
                        <th colspan="2"><span class="font-x">*</span>微信号/QQ号</th>
                    </tr>
                    <tr>
                        <td><input type="text" id="des${status.index}-email" name="designatedContacts[${status.index}].email" value="${item.ememail}" onblur="changeblur('des${status.index}-email','${item.ememail}')"></td>
                        <td><input type="text" id="des${status.index}-faxNum" name="designatedContacts[${status.index}].faxNum" value="${item.fax_num}" onblur="changeblur('des${status.index}-faxNum','${item.fax_num}')"></td>
                        <td colspan="2"><input type="text" id="des${status.index}-onlineNum" name="designatedContacts[${status.index}].onlineNum" value="${item.online_num}" onblur="changeblur('des${status.index}-onlineNum','${item.online_num}')"></td>
                    </tr>
                    <tr>
                        </c:when>
                        </c:choose>
                        </c:forEach>
                    <tr>
                        <th>单位简介<span class="tip">（300字以内）</span></th>
                        <td colspan="7" style="line-height: 2;text-indent: 2em" height="150px">
                            <textarea name="company.introduction" id="companyIntroduction" class="intrud-sty" style="width: 95%;" onblur="changeblur('companyIntroduction','${clientInfo[0].introduction}')">${clientInfo[0].introduction}</textarea></td>
                    </tr>
                    <tr>
                        <th>希望得到协会或者分会服务和支持的主要内容</th>
                        <td colspan="7" style="line-height: 2;text-indent: 2em" height="150px" width="87%">
                            <textarea name="company.demand" id="companyDemand" class="intrud-sty" style="width: 95%;" onblur="changeblur('companyDemand','${clientInfo[0].demand}')">${clientInfo[0].demand}</textarea></td>
                    </tr>

                    <tr>
                        <th rowspan="2">附件上传和下载</th>
                        <th>下载</th>
                        <td colspan="6"><a href="${ctx}/new-register/downloadApplication?filePath=upload/doc/application.docx">申请表下载</a></td>
                    </tr>
                    <tr>
                        <th>上传</th>
                        <td colspan="6">
                            <input id="fileupload" name="file" class="file" type="file">

                        </td>
                    </tr>
                    </tbody>
                </table>
                <div><span class="font-x" style="font-size: 15px;">备注：请下载附件打印成纸质文档，填写完毕加盖公章扫描后上传留作存档；各分会会员是广东省价格和产业发展协会的当然会员。</span></div>
            </div>
            <input type="submit" id="changeClient" class="btn btn-primary" style="width:8em;display:block;margin: 3em auto;" value="保存修改" disabled>
        </form>
        </div>
        </div>
</div>

</body>
<script src="${ctx}/dist/js/jquery.min.js"></script>
<script src="${ctx}/dist/js/index/jquery.login.js"></script>
<script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
<%--<script src="${ctx}/dist/js/fileinput.min.js"></script>--%>

<script src="${ctx}/dist/js/left_nav.js"></script>
<script src="${ctx}/dist/js/index/amazeui.min.js"></script>
<script src="${ctx}/dist/controller/index/index.js"></script>

<script src="${ctx}/dist/js/user/jquery.validate.js"></script>
<script src="${ctx}/dist/js/n_user/CheckUserInfo.js"></script>
<script src="${ctx}/dist/js/user/messages_cn.js"></script>
<script>
    $(function () {
//        $('#zlight-nav').zlightMenu();
        generateNav(-1);

    });

//    $('#fileupload').fileinput({
//        showUpload: false
//    });

    $(function () {
        if($('#association-name').val() == "${clientInfo[0].association_name}"){
            $('#association-name').get(0).checked = true;
        }

        $("input[name='company.associationUnit']").each(function () {
            if($(this).val() == "${clientInfo[0].association_unit}") {
                $(this).get(0).checked = true;
            }
        });

        $("input[name='company.chapterName']").each(function () {
            if($(this).val() == "${clientInfo[0].chapter_name}"){
                $(this).get(0).checked = true;
            }
        });

        $("input[name='company.chapterUnit']").each(function () {
            if($(this).val() == "${clientInfo[0].chapter_unit}"){
                $(this).get(0).checked = true;
            }
        });

        $("input:text").focus(function () {
            $(this).parent().addClass("my-form-control");
        });
        $("input:text").blur(function () {
            $(this).parent().removeClass("my-form-control")
        });
        $("input:password").focus(function () {
            $(this).parent().addClass("my-form-control");
        });
        $("input:password").blur(function () {
            $(this).parent().removeClass("my-form-control")
        });
        $("textarea").focus(function () {
            $(this).parent().addClass("my-form-control");
        });
        $("textarea").blur(function () {
            $(this).parent().removeClass("my-form-control")
        });

        //点击“广东省×××协会”的js
        $("input[name='company.associationName']").click(
                function () {
                    if ($("input[name='company.associationName']").get(0).checked == true) {
                        $("input[name='company.associationUnit']").get(0).checked = true;
                        $("input[name='company.associationUnit']").removeAttr("disabled");
//                        addJoinNameCheck();
//                        addJoinAUnitCheck();

                    } else {
                        $("input[name='company.associationUnit']").removeAttr("checked");
//                        removeJoinNameCheck();
//                        removeJoinAUnitCheck();
                        $("input[name='company.associationUnit']").attr("disabled", "disabled");
                    }
                    $("#changeClient").removeAttr("disabled");
                    blurArray.push("广东省价格和产业发展协会");
                }
        );

        //点击“广东省×××协会”后的会员单位级别 触发的js
        $("input[name='company.associationUnit']").click(
            function () {
                addJoinAUnitCheck();
            }
        );

        //点击“分会”的js
        $("input[name='company.chapterName']").click(
                function () {
                    $("#cancle-btn1").removeClass("hidd");
                    $("input[name='company.chapterUnit']").removeAttr("disabled");
                    /*for (var i = 0; i < $("input[name='company.chapterUnit']").length; i++) {
                     if ($("input[name='company.chapterUnit']").get(i).checked == true) {
                     return;
                     }
                     }*/
                    if ($("input[name='company.chapterUnit']:checked").length == 0) {
                        $("input[name='company.chapterUnit']").get(0).checked = true;
                        addJoinCUnitCheck();
                    }
                    /*$("#cancle-btn2").removeClass("hidd");*/
                    addJoinCNameCheck();
                    return;
                }
        );

        //点击“分会”后的会员单位级别 触发的js
        $("input[name='company.chapterUnit']").click(
                function () {
                    addJoinCUnitCheck();
                }
        )

        //点击“撤销选择”触发的js
        $("#cancle-btn1").click(
                function () {
                    $(this).addClass("hidd");
                    $("input[name='company.chapterName']").removeAttr("checked");
                    $("input[name='company.chapterUnit']").removeAttr("checked");
                    $("input[name='company.chapterUnit']").attr("disabled", "disabled");
//                    $("#cancle-btn2").addClass("hidd");
                    removeJoinCNameCheck();
                    removeJoinCUnitCheck();
                    $("#changeClient").removeAttr("disabled");
                    return;
                }
        );

    });

    function addJoinNameCheck() {
        $("input[name='joinaname']").get(0).checked = true;
        $("#joinname-sp1").addClass("font-x");
    }
    function removeJoinNameCheck() {
        $("input[name='joinaname']").removeAttr("checked");
        $("#joinname-sp1").removeClass("font-x");
    }
    function addJoinAUnitCheck() {
        removeJoinAUnitCheck();
        var value = $("input[name='company.associationUnit']:checked").val()
        if(value != '${clientInfo[0].association_unit}'){
            $("#changeClient").removeAttr("disabled");
            blurArray.push(value);
        }
        switch (value.toString()) {
            case "副会长单位":
                $("input[name='joinaunit']").get(0).checked = true;
                $("#joinainit-sp1").addClass("font-x");
                break;
            case "监事长单位":
                $("input[name='joinaunit']").get(1).checked = true;
                $("#joinainit-sp2").addClass("font-x");
                break;
            case "常务理事单位":
                $("input[name='joinaunit']").get(2).checked = true;
                $("#joinainit-sp3").addClass("font-x");
                break;
            case "理事单位":
                $("input[name='joinaunit']").get(3).checked = true;
                $("#joinainit-sp4").addClass("font-x");
                break;
            case "监事单位":
                $("input[name='joinaunit']").get(4).checked = true;
                $("#joinainit-sp5").addClass("font-x");
                break;
            case "会员单位":
                $("input[name='joinaunit']").get(5).checked = true;
                $("#joinainit-sp6").addClass("font-x");
                break;
        }
        /*for(var i = 0; i < $("input[name='company.associationUnit']").length; i++) {

         }*/
    }
    function removeJoinAUnitCheck() {
        $("input[name='joinaunit']:checked").next("span").removeClass("font-x");
        $("input[name='joinaunit']").removeAttr("checked");
    }

    function addJoinCNameCheck() {
        removeJoinCNameCheck();
        var value = $("input[name='company.chapterName']:checked").val();
        if(value != '${clientInfo[0].chapter_name}'){
            $("#changeClient").removeAttr("disabled");
            blurArray.push(value);
        }
        switch (value.toString()) {
            case "评估鉴定分会":
                $("input[name='joinname']").get(0).checked = true;
                $("#joinname-sp2").addClass("font-x");
                break;
            case "电力价格分会":
                $("input[name='joinname']").get(1).checked = true;
                $("#joinname-sp3").addClass("font-x");
                break;
            case "农产品价格分会":
                $("input[name='joinname']").get(2).checked = true;
                $("#joinname-sp4").addClass("font-x");
                break;
            case "燃气价格分会":
                $("input[name='joinname']").get(3).checked = true;
                $("#joinname-sp5").addClass("font-x");
                break;
            case "价格与公平竞争分会":
                $("input[name='joinname']").get(4).checked = true;
                $("#joinname-sp6").addClass("font-x");
                break;
        }
    }

    function removeJoinCNameCheck() {
        $("input[name='joinname']:checked").next("span").removeClass("font-x");
        $("input[name='joinname']").removeAttr("checked");
    }

    function addJoinCUnitCheck() {
        removeJoinCUnitCheck();
        var value = $("input[name='company.chapterUnit']:checked").val();
        if(value != '${clientInfo[0].chapter_unit}'){
            $("#changeClient").removeAttr("disabled");
            blurArray.push(value);
        }
        switch (value.toString()) {
            case "会长单位":
                $("input[name='joincunit']").get(0).checked = true;
                $("#joincuinit-sp1").addClass("font-x");
                break;
            case "副会长单位":
                $("input[name='joincunit']").get(1).checked = true;
                $("#joincuinit-sp2").addClass("font-x");
                break;
            case "常务理事单位":
                $("input[name='joincunit']").get(2).checked = true;
                $("#joincuinit-sp3").addClass("font-x");
                break;
            case "理事单位":
                $("input[name='joincunit']").get(3).checked = true;
                $("#joincuinit-sp4").addClass("font-x");
                break;
            case "会员单位":
                $("input[name='joincunit']").get(4).checked = true;
                $("#joincuinit-sp5").addClass("font-x");
                break;
        }
    }

    function removeJoinCUnitCheck() {
        $("input[name='joincunit']:checked").next("span").removeClass("font-x");
        $("input[name='joincunit']").removeAttr("checked");
    }

    var blurArray = new Array();
    function changeblur(id,val) {
        if($('#'+id).val() == val){
            if(blurArray.length != 0){
                for(var i=0;i<blurArray.length;i++){
                    if(blurArray[i] == id){
                        blurArray.splice(i,1);
                        break;
                    }
                }
                if(blurArray.length == 0){
                    $("#changeClient").attr("disabled","disabled");
                }
            }else{
                $("#changeClient").attr("disabled","disabled");
            }
        }else{
            existCompany(id);
            if(blurArray.length != 0){
                var j = 0;
                for(var i=0;i<blurArray.length;i++){
                    if(blurArray[i] == id){
                        j++;
                        break;
                    }
                }
                if(j==0){
                    blurArray.push(id)
                }
            }else{
                blurArray.push(id);
            }
            $("#changeClient").removeAttr("disabled");
        }
    }

    function existCompany(id) {
        if(id == "com-name"){
            $.ajax({
                url: "/new-register/existCompany",
                type: "post",
                data: {
                    companyName: $('#' + id).val()
                },
                success: function (data) {
                    if (data == "123") {
                        $("#companyName").css("display","block");
                        $("#changeClient").attr("disabled","disabled");
                    }
                }
            });
        }
    }
    $('#com-name').focus(function () {
        $("#companyName").css("display","none");
    });

    /*$('#fileupload').click(function () {
        $("#changeClient").removeAttr("disabled");
    });*/

</script>
</html>
