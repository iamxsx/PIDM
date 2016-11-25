<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>信息修改</title>
    <meta charset="UTF-8">

    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
    <link rel="stylesheet" href="${ctx}/dist/css/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/index/login.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav_frontend.css">
    <link rel="stylesheet" href="${ctx}/dist/css/n_user/register.css">

    <style>
        .font-x{
            font-size: 15px;
        }
        .my-td-border{
            padding-left: 0 !important;
        }
        .my-td-tborder{
            padding-left: 0 !important;
        }
    </style>

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

        <div class="col-md-10 right-panel" id="container">
            <form id="register-form" method="post" enctype="multipart/form-data" action="${ctx}/nUser/saveNUserAllInfoToTemp" onsubmit="return ifSubmitConfirm()">
                <div id="user-box" class="">
                    <table class="table table-bordered">
                        <caption class="table-title">账号基本信息</caption>
                        <tr>
                            <th><span class="font-x">*</span>账号</th>
                            <td style="background-color: #f9f9f9">
                                <input type="hidden" name="user.account" value="${nUser.account}">
                                <div name="user.account">${nUser.account}</div>
                            </td>
                            <th><span class="font-x">*</span>密码</th>
                            <td style="background-color: #f9f9f9;font-size: 16px">
                                ********
                                <input type="hidden" name="user.password" value="${nUser.password}">
                                <a href="${ctx}/new-register/redirect?msg=goto-reset" style="float: right;font-size: 14px">修改密码</a>
                            </td>
                        </tr>
                        <tr>
                            <th><span class="font-x">*</span>申请人姓名</th>
                            <td><input type="text" name="user.realName" value="${nUser.realName}" id="userName" onblur="submitBtnCheck('userName','${nUser.realName}')"></td>
                            <th><span class="font-x">*</span>申请人身份证</th>
                            <td><input type="text" name="user.IDcard" value="${nUser.IDcard}" id="IDcard" onblur="submitBtnCheck('IDcard','${nUser.IDcard}')"></td>
                        </tr>
                        <tr>
                            <th><span class="font-x">*</span>申请人手机号码</th>
                            <td><input type="text" name="user.phoneNum" value="${nUser.phoneNum}" id="phoneNum" onblur="submitBtnCheck('phoneNum','${nUser.phoneNum}')"></td>
                            <th><span class="font-x">*</span>申请人联系邮箱</th>
                            <td><input type="text" name="user.email" value="${nUser.email}" id="email" onblur="submitBtnCheck('email','${nUser.email}')"></td>
                        </tr>
                    </table>
                </div>
                <div id="register-box" class="">
                    <table class="table table-bordered page" style="vertical-align: middle">
                        <caption class="table-title">广东省价格和产业发展协会入会申请信息</caption>
                        <tbody>
                            <tr>
                                <th><span class="font-x">*</span>单位名称</th>
                                <td colspan="3">
                                    <input type="text" name="company.name" id="com-name" value="${nCompany.name}" onblur="submitBtnCheck('com-name','${nCompany.name}')">
                                    <span id="companyName" style="display: none;font-size: 12px;color: red">提示&nbsp;&nbsp;:&nbsp;&nbsp;单位名称已存在!</span>
                                </td>
                                <th><span class="font-x">*</span>单位性质</th>
                                <td><input type="text" name="company.nature" value="${nCompany.nature}" id="companyNature" onblur="submitBtnCheck('companyNature','${nCompany.nature}')"></td>
                            </tr>
                            <tr>
                                <th><span class="font-x">*</span>通讯地址</th>
                                <td colspan="3"><input type="text" name="company.address" value="${nCompany.address}" id="companyAddress" onblur="submitBtnCheck('companyAddress','${nCompany.address}')"></td>
                                <th><span class="font-x">*</span>邮政编码</th>
                                <td><input type="text" name="company.zipCode" placeholder="六位邮编" value="${nCompany.zipCode}" id="companyZipCode" onblur="submitBtnCheck('companyZipCode','${nCompany.zipCode}')"></td>
                            </tr>
                            <tr>
                                <th rowspan="2"><span class="font-x">*</span>法人代表</th>
                                <th><span class="font-x">*</span>姓名</th>
                                <th><span class="font-x">*</span>单位职务</th>
                                <th><span class="font-x">*</span>办公电话</th>
                                <th colspan="2"><span class="font-x">*</span>手机</th>
                            </tr>
                            <tr>
                                <td>
                                    <input type="hidden" name="legalRep.nature" value="2">
                                    <input type="text" name="legalRep.name" value="${legalRep.name}" id="legalRepName" onblur="submitBtnCheck('legalRepName','${legalRep.name}')">
                                </td>
                                <td><input type="text" name="legalRep.jobPosition" value="${legalRep.jobPosition}" id="legalRepJob" onblur="submitBtnCheck('legalRepJob','${legalRep.jobPosition}')"></td>
                                <td><input type="text" name="legalRep.officePhoneNum" placeholder="区号-号码" value="${legalRep.officePhoneNum}" id="legalRepOff" onblur="submitBtnCheck('legalRepOff','${legalRep.officePhoneNum}')"></td>
                                <td colspan="2"><input type="text" name="legalRep.cellPhoneNum" value="${legalRep.cellPhoneNum}" id="legalRepCell" onblur="submitBtnCheck('legalRepCell','${legalRep.cellPhoneNum}')"></td>
                            </tr>
                            <tr>
                                <th rowspan="7"><input name="company.join" style="position: absolute; top: -999em;"><span
                                        class="font-x">*</span>申请加入
                                </th>
                                <th colspan="2">协会</th>
                                <th colspan="3">会员单位级别</th>
                            </tr>
                            <tr>
                                <td rowspan="2" colspan="2"><input type="checkbox" name="company.associationName"
                                                                   value="广东省价格和产业发展协会"
                                                                   id="association-name"><label
                                        for="association-name">广东省价格和产业发展协会</label>
                                </td>
                                <td class="my-td-border" style="width: 150px"><input type="radio" name="company.associationUnit" value="副会长单位"
                                                                id="asct-jp1"><label
                                        for="asct-jp1">副会长单位</label></td>
                                <td class="my-td-border"><input type="radio" name="company.associationUnit" value="监事长单位"
                                                                id="asct-jp2"><label
                                        for="asct-jp2">监事长单位</label></td>
                                <td class="my-td-rborder"><input type="radio" name="company.associationUnit" value="常务理事单位"
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
                                <td class="my-td-tborder"><input type="radio" name="company.chapterUnit" value="副会长单位"
                                                                 id="chapt-unit2"/><label
                                        for="chapt-unit2">副会长单位</label></td>
                                <td colspan="" class="my-td-rborder"><input type="radio" name="company.chapterUnit" value="常务理事单位"
                                                                           id="chapt-unit3"/><label
                                        for="chapt-unit3">常务理事单位</label>
                                </td>
                            </tr>
                            <tr>
                                <td class="my-td-border"><input type="radio" name="company.chapterName" value="农产品价格分会"
                                                                id="chapt-name3"/><label for="chapt-name3">农产品价格分会</label></td>
                                <td class="my-td-rborder"><input type="radio" name="company.chapterName" value="燃气价格分会"
                                                                 id="chapt-name4"/><label for="chapt-name4">燃气价格分会</label></td>

                                <td colspan="" class="my-td-border"><input type="radio" name="company.chapterUnit" value="理事单位"
                                                                           id="chapt-unit4"/><label
                                        for="chapt-unit4">理事单位</label></td>
                                <td class="my-td-border"><input type="radio" name="company.chapterUnit" value="会员单位"
                                                                id="chapt-unit5"/><label
                                        for="chapt-unit5">会员单位</label></td>
                                <td class="my-td-rborder" rowspan="2"></td>
                            </tr>
                            <tr>
                                <td class="my-td-border"><input type="radio" name="company.chapterName" value="价格与公平竞争分会"
                                                                id="chapt-name5"/><label
                                        for="chapt-name5">价格与公平竞争分会</label></td>
                                <td class="my-td-rborder">
                                    <div class="btn btn-default hidd" id="cancle-btn1" style="padding: 5px;width: 70px">撤销选择</div>
                                </td>
                            </tr>
                            <tr>
                                <th rowspan="4">推荐人选及拟任分会职务
                                    <sapn class="tip">（协会副会长单位、分会会长单位和副会长单位至少要有一人）</sapn>
                                </th>
                                <th>姓名</th>
                                <th>单位职务</th>
                                <th>协会职务</th>
                                <th colspan="2">分会职务</th>
                            </tr>
                            <tr>
                                <td rowspan="3">
                                    <input type="hidden" name="introduceds[0].nature" value="1">
                                    <textarea name="introduceds[0].name" class="textarea-sty" id="intr0-name" onblur="submitBtnCheck('intr0-name','${introduceds[0].name}')" >${introduceds[0].name}</textarea>
                                </td>
                                <td><input type="text" name="introduceds[0].jobPosition" value="${introduceds[0].jobPosition}" id="intrJobPosition0" onblur="submitBtnCheck('intrJobPosition0','${introduceds[0].jobPosition}')"></td>
                                <td><input type="text" name="introduceds[0].asctJobPosition" value="${introduceds[0].asctJobPosition}" id="intrAsctJobPosition0" onblur="submitBtnCheck('intrAsctJobPosition0','${introduceds[0].asctJobPosition}')"></td>
                                <td colspan="2"><input type="text" name="introduceds[0].chapterJobPosition" value="${introduceds[0].chapterJobPosition}" id="intrChapterJobPosition0" onblur="submitBtnCheck('intrChapterJobPosition0','${introduceds[0].chapterJobPosition}')"></td>
                            </tr>
                            <tr>
                                <th>电子邮箱</th>
                                <th>办公电话</th>
                                <th colspan="2">手机</th>
                            </tr>
                            <tr>
                                <td><input type="text" name="introduceds[0].email" value="${introduceds[0].email}" id="intrEmail0" onblur="submitBtnCheck('intrEmail0','${introduceds[0].email}')"></td>
                                <td><input type="text" name="introduceds[0].officePhoneNum" placeholder="区号-号码" value="${introduceds[0].officePhoneNum}" id="intrOff0" onblur="submitBtnCheck('intrOff0','${introduceds[0].officePhoneNum}')"></td>
                                <td colspan="2" class="re-positon">
                                    <input type="text" name="introduceds[0].cellPhoneNum" value="${introduceds[0].cellPhoneNum}" id="intrCell0" onblur="submitBtnCheck('intrCell0','${introduceds[0].cellPhoneNum}')">
                                    <button type="button" class="add-btn" id="add-in0" style="display: block">+</button>
                                </td>
                            </tr>
                        </tbody>

                        <tbody id="in-tb2" style="display: none">
                            <tr>
                                <th rowspan="4">推荐人</th>
                                <th>姓名</th>
                                <th>单位职务</th>
                                <th>协会职务</th>
                                <th colspan="2">分会职务</th>
                            </tr>
                            <tr>
                                <td rowspan="3">
                                    <input type="hidden" name="introduceds[1].nature" value="1">
                                    <textarea name="introduceds[1].name" class="textarea-sty" id="intr1-name" onblur="submitBtnCheck('intr1-name','${introduceds[1].name}')">${introduceds[1].name}</textarea>
                                </td>
                                <td><input type="text" name="introduceds[1].jobPosition" value="${introduceds[1].jobPosition}" id="intrjobPosition1" onblur="submitBtnCheck('intrjobPosition1','${introduceds[1].jobPosition}')"></td>
                                <td><input type="text" name="introduceds[1].asctJobPosition" value="${introduceds[1].asctJobPosition}" id="intrasctJobPosition1" onblur="submitBtnCheck('intrasctJobPosition1','${introduceds[1].asctJobPosition}')"></td>
                                <td colspan="2"><input type="text" name="introduceds[1].chapterJobPosition" value="${introduceds[1].chapterJobPosition}" id="intrchapterJobPosition1" onblur="submitBtnCheck('intrchapterJobPosition1','${introduceds[1].chapterJobPosition}')"></td>
                            </tr>
                            <tr>
                                <th>电子邮箱</th>
                                <th>办公电话</th>
                                <th colspan="2">手机</th>
                            </tr>
                            <tr>
                                <td><input type="text" name="introduceds[1].email" value="${introduceds[1].email}" id="intrEmail1" onblur="submitBtnCheck('intrEmail1','${introduceds[1].email}')"></td>
                                <td><input type="text" name="introduceds[1].officePhoneNum" placeholder="区号-号码" value="${introduceds[1].officePhoneNum}" id="intrOff1" onblur="submitBtnCheck('intrOff1','${introduceds[1].officePhoneNum}')"></td>
                                <td colspan="2"><input type="text" name="introduceds[1].cellPhoneNum" value="${introduceds[1].cellPhoneNum}" id="intrCell1" onblur="submitBtnCheck('intrCell1','${introduceds[1].cellPhoneNum}')"></td>
                            </tr>
                        </tbody>

                        <tbody>
                            <tr>
                                <th rowspan="4"><span class="font-x">*</span>单位指定联系人一
                                    <sapn class="tip">（至少一人）</sapn>
                                </th>
                                <th><span class="font-x">*</span>姓名</th>
                                <th><span class="font-x">*</span>单位职务</th>
                                <th><span class="font-x">*</span>办公电话</th>
                                <th colspan="2"><span class="font-x">*</span>手机</th>
                            </tr>
                            <tr>
                                <td rowspan="3">
                                    <input type="hidden" name="designatedContacts[0].nature" value="0">
                                    <textarea name="designatedContacts[0].name" class="textarea-sty" id="desName0" onblur="submitBtnCheck('desName0','${designatedContact[0].name}')">${designatedContact[0].name}</textarea>
                                </td>
                                <td><input type="text" name="designatedContacts[0].jobPosition" value="${designatedContact[0].jobPosition}" id="desJobPosition0" onblur="submitBtnCheck('','')"></td>
                                <td><input type="text" name="designatedContacts[0].officePhoneNum" placeholder="区号-号码" value="${designatedContact[0].officePhoneNum}" id="desOff0" onblur="submitBtnCheck('desOff0','${designatedContact[0].officePhoneNum}')"></td>
                                <td colspan="2"><input type="text" name="designatedContacts[0].cellPhoneNum" value="${designatedContact[0].cellPhoneNum}" id="desCell0" onblur="submitBtnCheck('desCell0','${designatedContact[0].cellPhoneNum}')"></td>
                            </tr>
                            <tr>
                                <th><span class="font-x">*</span>电子邮箱</th>
                                <th>传真号码</th>
                                <th colspan="2"><span class="font-x">*</span>微信号/QQ号</th>
                            </tr>
                            <tr>
                                <td><input type="text" name="designatedContacts[0].email" value="${designatedContact[0].email}" id="desEmail0" onblur="submitBtnCheck('desEmail0','${designatedContact[0].email}')"></td>
                                <td><input type="text" name="designatedContacts[0].faxNum" placeholder="区号-号码" value="${designatedContact[0].faxNum}" id="desFax0" onblur="submitBtnCheck('desFax0','${designatedContact[0].faxNum}')"></td>
                                <td colspan="2" class="re-positon">
                                    <input type="text" name="designatedContacts[0].onlineNum" value="${designatedContact[0].onlineNum}" id="desOnlineNum0" onblur="submitBtnCheck('desOnlineNum0','${designatedContact[0].onlineNum}')">
                                    <button type="button" class="add-btn" id="add-dis0">+</button></td>
                                </td>
                            </tr>
                        </tbody>

                        <tbody id="dis-tb2" style="display: none">
                            <tr>
                                <th rowspan="4">单位指定联系人二</th>
                                <th>姓名</th>
                                <th>单位职务</th>
                                <th>办公电话</th>
                                <th colspan="2">手机</th>
                            </tr>
                            <tr>
                                <td rowspan="3">
                                    <input type="hidden" name="designatedContacts[1].nature" value="0">
                                    <textarea name="designatedContacts[1].name" class="textarea-sty"
                                              id="desName1" onblur="submitBtnCheck('desName1','${designatedContact[1].name}')">${designatedContact[1].name}</textarea></td>
                                <td><input type="text" name="designatedContacts[1].jobPosition" value="${designatedContact[1].jobPosition}" id="desJobPosition1" onblur="submitBtnCheck('desJobPosition1','${designatedContact[1].jobPosition}')"></td>
                                <td><input type="text" name="designatedContacts[1].officePhoneNum" placeholder="区号-号码" value="${designatedContact[1].officePhoneNum}" id="desOff1" onblur="submitBtnCheck('desOff1','${designatedContact[1].officePhoneNum}')"></td>
                                <td colspan="2"><input type="text" name="designatedContacts[1].cellPhoneNum" value="${designatedContact[1].cellPhoneNum}" id="desCell1" onblur="submitBtnCheck('desCell1','${designatedContact[1].cellPhoneNum}')"></td>
                            </tr>
                            <tr>
                                <th>电子邮箱</th>
                                <th>传真号码</th>
                                <th colspan="2">微信号/QQ号</th>
                            </tr>
                            <tr>
                                <td><input type="text" name="designatedContacts[1].email" value="${designatedContact[1].email}" id="desEmail1" onblur="submitBtnCheck('desEmail1','${designatedContact[1].email}')"></td>
                                <td><input type="text" name="designatedContacts[1].faxNum" placeholder="区号-号码" value="${designatedContact[1].faxNum}" id="desFax1" onblur="submitBtnCheck('desFax1','${designatedContact[1].faxNum}')"></td>
                                <td colspan="2"><input type="text" name="designatedContacts[1].onlineNum" value="${designatedContact[1].onlineNum}" id="desOnlineNum1" onblur="submitBtnCheck('desOnlineNum1','${designatedContact[1].onlineNum}')"></td>
                            </tr>
                        </tbody>
                        <tbody>
                            <tr>
                                <th>单位简介<span class="tip">（300字以内）</span></th>
                                <td colspan="7"><textarea name="company.introduction" class="intrud-sty" style="text-indent: 2em;" id="introduction" onblur="submitBtnCheck('introduction','${nCompany.introduction}')">${nCompany.introduction}</textarea></td>
                            </tr>
                            <tr>
                                <th>希望得到协会或者分会服务和支持的主要内容</th>
                                <td colspan="7"><textarea name="company.demand" class="intrud-sty" style="text-indent: 2em;" id="demand" onblur="submitBtnCheck('demand','${nCompany.demand}')">${nCompany.demand}</textarea>
                            </tr>
                            <tr>
                                <th>入会申请确认</th>
                                <td colspan="7" style="line-height: 2;text-indent: 2em" height="180px">
                                    本单位研究协会章程后，决定承认《广东省价格和产业发展协会章程》，自愿申请加入<input type="checkbox" name="joinaname" disabled><span
                                        id="joinname-sp1">广东省价格和产业发展协会</span>（<input type="radio" name="joinaunit" disabled><span
                                        id="joinainit-sp1">副会长单位</span>
                                    <input type="radio" name="joinaunit" disabled><span
                                        id="joinainit-sp2">监事长单位</span><input type="radio" name="joinaunit" disabled><span
                                        id="joinainit-sp3">常务理事单位</span>
                                    <input type="radio" name="joinaunit" disabled><span
                                        id="joinainit-sp4">理事单位</span><input type="radio" name="joinaunit" disabled><span
                                        id="joinainit-sp5">监事单位</span>
                                    <input type="radio" name="joinaunit" disabled><span
                                        id="joinainit-sp6">会员单位</span>）<input type="checkbox" name="joinname" disabled><span
                                        id="joinname-sp2">评估鉴证分会</span>
                                    <input type="checkbox" name="joinname" disabled><span id="joinname-sp3">电力价格分会</span><input
                                        type="checkbox" name="joinname"
                                        disabled><span id="joinname-sp4">农产品价格分会</span><input
                                        type="checkbox" name="joinname" disabled><span id="joinname-sp5">燃气价格分会</span><input
                                        type="checkbox" name="joinname" disabled><span id="joinname-sp6">价格与公平竞争分会</span>（<input
                                        type="radio" name="joincunit" disabled><span id="joincuinit-sp1">会长单位</span><input
                                        type="radio" name="joincunit" disabled><span id="joincuinit-sp2">副会长单位</span><input
                                        type="radio"
                                        name="joincunit"
                                        disabled><span
                                        id="joincuinit-sp3">常务理事单位</span>
                                    <input type="radio" name="joincunit" disabled><span id="joincuinit-sp4">理事单位</span>
                                    <input type="radio" name="joincunit" disabled><span id="joincuinit-sp5">会员单位</span>），在享有会员权利的同时，承诺履行下列会员义务：一、遵守协会章程，执行协会的决议；二、积极参加协会及分会组织的各项活动；三、承办协会及分会委托的事项；四、按时缴纳会费；五、及时向协会及分会反映情况，提供信息。
                                </td>
                            </tr>
                            <tr>
                                <th>省协会意见</th>
                                <td colspan="7" style="line-height: 2;text-indent: 2em;" height="180px">
                                    <div>${nCompany.auditOpinion}</div>
                                </td>
                            </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <th width="150px">附件上传与下载</th>
                            <th width="205px">附件下载</th>
                            <td width="205px"><a
                                    href="${ctx}/new-register/downloadApplication?filePath=upload/doc/application.docx">申请表下载</a>
                            </td>
                            <th width="205px">附件上传</th>
                            <td colspan="2"><input type="file" name="fileName"></td>
                        </tr>
                        </tbody>
                    </table>
                    <div><span class="font-x" style="font-size: 15px;">备注：请下载附件打印成纸质文档，填写完毕加盖公章扫描后上传留作存档；各分会会员是广东省价格和产业发展协会的当然会员。</span></div>
                </div>
                <div class="row" style="width: 250px;margin: 20px auto">
                    <button id="submitBtn" type="submit" class="btn btn-primary"style="width: 100px;margin:10px" disabled="disabled">申请修改</button>
                    <a class="btn btn-primary" style="width: 100px;margin:10px" href="${ctx}/nUser/showNUserInfo">返回</a>
                </div>
                <div style="min-height: 100px"></div>
            </form>
        </div>
    </div>
</div>
<%--<footer class="footer">--%>
    <%--<div class="col-md-12" style="position: relative">--%>
        <%--<p>&copy; 2016 广东省价格和产业发展协会&nbsp;版权所有&nbsp;不得转载 <a href="##">联系我们</a></p>--%>
        <%--<p>粤ICP备05070829 </p>--%>
        <%--<p><a href="#">广州亦云信息技术股份有限公司&nbsp;提供技术支持</a></p>--%>
        <%--<img class="footcode" src="${ctx}/qrcode?text=202.104.231.132:8080/PIDM">--%>
    <%--</div>--%>
<%--</footer>--%>
<jsp:include page="../footer.jsp"></jsp:include>

</body>
<script src="${ctx}/dist/js/jquery.min.js"></script>
<script src="${ctx}/dist/js/user/jquery.validate.js"></script>
<script src="${ctx}/dist/js/index/jquery.login.js"></script>
<script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
<script src="${ctx}/dist/js/left_nav.js"></script>
<script src="${ctx}/dist/js/index/amazeui.min.js"></script>
<script src="${ctx}/dist/controller/index/index.js"></script>
<script src="${ctx}/dist/js/user/messages_cn.js"></script>
<script src="${ctx}/dist/js/n_user/foregroundChangeInfo.js"></script>
<script>
    $(function () {

        $('#zlight-nav').zlightMenu();
        generateNav(-1);

        var intrName = '${introduceds[1].name}';
        if(!(intrName == '')){
            $("#in-tb2").toggle();
            $("#add-in0").css("display","none");
        };

        var desName = '${designatedContact[1].name}';
        if(!(desName == '' )){
            $("#dis-tb2").toggle();
            $("#add-dis0").css("display","none");
        };


//        单选框多选框那块 的操作
        if($('#association-name').val() == "${nCompany.associationName}"){
            $('#association-name').get(0).checked = true;
        }

        var associationUnit = $("input[name='company.associationUnit']");
        associationUnit.each(function(){
            if($(this).val() == '${nCompany.associationUnit}'){
                $(this).get(0).checked = true;
            }
        })
        var chapterName = $("input[name='company.chapterName']");
        chapterName.each(function () {
            if($(this).val() == '${nCompany.chapterName}'){
                $(this).get(0).checked = true;
            }
        })
        var chapterUnit = $("input[name='company.chapterUnit']");
        chapterUnit.each(function () {
            if($(this).val() == '${nCompany.chapterUnit}'){
                $(this).get(0).checked = true;
            }
        })
        //检查该公司是否有加入协会
        if ($("input[name='company.associationName']").get(0).checked == true) {
            addJoinNameCheck();
            addJoinAUnitCheck();
        }else{
            $("input[name='company.associationUnit']").attr("disabled",true);
            $("input[name='company.associationUnit']").attr("checked",false);
        }
        addJoinCNameCheck();
        addJoinCUnitCheck();


        $("#add-in0").click(
                function () {
                    if ($("#add-in0").text() == "-") {
                        $("#add-in0").text("+");
                    } else {
                        $("#add-in0").text("-");
                    }
                    $("#in-tb2").toggle(1500);
                }
        );
        $("#add-dis0").click(
                function () {
                    if ($("#add-dis0").text() == "-") {
                        $("#add-dis0").text("+");
                    } else {
                        $("#add-dis0").text("-");
                    }
                    $("#dis-tb2").toggle(1500);
                }
        );



//###############################zjf 的JS
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

        $("input[name='company.associationName']").click(
                function () {
                    if ($("input[name='company.associationName']").get(0).checked == true) {
                        $("input[name='company.associationUnit']").get(0).checked = true;
                        $("input[name='company.associationUnit']").removeAttr("disabled");
                        addJoinNameCheck();
                        addJoinAUnitCheck();
                    } else {
                        $("input[name='company.associationUnit']").removeAttr("checked");
                        removeJoinNameCheck();
                        removeJoinAUnitCheck();
                        $("input[name='company.associationUnit']").attr("disabled", "disabled");
                    }
                    $("#submitBtn").removeAttr("disabled");
                    blurArray.push("广东省价格和产业发展协会");
                }
        )

        $("input[name='company.associationUnit']").click(
                function () {
                    addJoinAUnitCheck();
                }
        )

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

        $("input[name='company.chapterUnit']").click(
                function () {
                    addJoinCUnitCheck();
                }
        )
//          取消单选款按钮
        $("#cancle-btn1").click(
                function () {
                    $(this).addClass("hidd");
                    $("input[name='company.chapterName']").removeAttr("checked");
                    $("input[name='company.chapterUnit']").removeAttr("checked");
                    $("input[name='company.chapterUnit']").attr("disabled", "disabled");
//                    $("#cancle-btn2").addClass("hidd");
                    removeJoinCNameCheck();
                    removeJoinCUnitCheck();
                    $("#submitBtn").removeAttr("disabled");
                    return;
                }
        );
    })

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
        if(value != '${nCompany.associationUnit}'){
            $("#submitBtn").removeAttr("disabled");
            blurArray.push(value);
        }
        if(value != ''&& value != null){
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
        }
    }
    function removeJoinAUnitCheck() {
        $("input[name='joinaunit']:checked").next("span").removeClass("font-x");
        $("input[name='joinaunit']").removeAttr("checked");
    }

    function addJoinCNameCheck() {
        removeJoinCNameCheck();
        var value = $("input[name='company.chapterName']:checked").val();
        if(value != '${nCompany.chapterName}'){
            $("#submitBtn").removeAttr("disabled");
            blurArray.push(value);
        }
        if(value != ''&& value != null){
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
    }

    function removeJoinCNameCheck() {
        $("input[name='joinname']:checked").next("span").removeClass("font-x");
        $("input[name='joinname']").removeAttr("checked");
    }

    function addJoinCUnitCheck() {
        removeJoinCUnitCheck();
        var value = $("input[name='company.chapterUnit']:checked").val();
        if(value != '${nCompany.chapterUnit}'){
            $("#changeClient").removeAttr("disabled");
            blurArray.push(value);
        }
        if(value != ''&& value != null){
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
    }

    function removeJoinCUnitCheck() {
        $("input[name='joincunit']:checked").next("span").removeClass("font-x");
        $("input[name='joincunit']").removeAttr("checked");
    }

    //        -----------######------------zcb 的JS
    function ifSubmitConfirm() {
        return confirm("是否提交帐号信息修改申请？");
    }

    //  输入框验证是否有改动过
    var blurArray = new Array();
    function submitBtnCheck(id,val) {
        if($('#'+id).val() == val){
            if(blurArray.length != 0){
                for(var i=0;i<blurArray.length;i++){
                    if(blurArray[i] == id){
                        blurArray.splice(i,1);
                        break;
                    }
                }
                if(blurArray.length == 0){
                    $("#submitBtn").attr("disabled","disabled");
                }
            }else{
                $("#submitBtn").attr("disabled","disabled");
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
            $("#submitBtn").removeAttr("disabled");
        }
    }

    function existCompany(id) {
        if(id == "com-name"){
            $.ajax({
                url: "/PIDM/new-register/existCompany",
                type: "post",
                data: {
                    companyName: $('#' + id).val()
                },
                success: function (data) {
                    if (data == "123") {
                        $("#companyName").css("display","block");
                        $("#submitBtn").attr("disabled","disabled");
                    }
                }
            });
        }
    }

    $('#com-name').focus(function () {
        $("#companyName").css("display","none");
    });

</script>
</html>