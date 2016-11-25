/**
 * Created by clouder on 16-9-16.
 */
$('#to-do-table').bootstrapTable({
    url:'/work/personal',
    method:'post',
    sidePagination:'server',
    pagination:true,
    showRefresh: true,
    striped:true,
    pageNumber:1,
    pageSize:5,
    pageList:[20,40,60,80],
    striped: true,
    queryParamsType:'limit',
    queryParams:function queryParams(params) {
        if (params.offset<0){
            params.offset = 0;
        }
        return params;
    },
    columns:[{
        field:'workType',
        title:'所属业务',
        width:100,
        formatter:function (value,row,index) {
            if(value=='UserInsert'){
                return '客户帐号注册';
            }
            else if(value=='ArticleInsert'){
                return '发布新的信息';
            }
            else if (value=="UserUpdate"){
                return "客户信息更改"
            }
            else if (value=="ArticleUpdate"){
                return "发布信息更改"
            }
            else if (value=="UserDelete"){
                return "帐号修改审核"
            }
            else if (value=="ArticleDelete"){
                return "发布更改审核"
            }
        }
    },{
        field:'workType',
        title:'提醒内容',
        width:100,
        formatter:function (value,row,index) {
            if(value=='UserInsert'){
                return '帐号注册审核';
            }
            else if(value=='ArticleInsert'){
                return '发布信息审核';
            }
            else if (value=="UserUpdate"){
                return "帐号修改审核"
            }
            else if (value=="ArticleUpdate"){
                return "发布更改审核"
            }
            else if (value=="UserDelete"){
                return "帐号修改审核"
            }
            else if (value=="ArticleDelete"){
                return "发布更改审核"
            }
        }
    },{
        field:'actName',
        title:'流程环节',
        width:100
    },{
        field:'startTime',
        title:'接收时间',
        width:180,
        formatter:function (value,row,index) {
            return format(value,'yyyy-MM-dd HH:mm:ss');
        }
    },{
        field:'taskId',
        title:'操作',
        width:120,
        formatter:function (value,row,index) {
            entityId = row.entityId;
            taskId = row.taskId;
            workType = row.workType;
            return '<a data-toggle="modal" data-target="#myModal" ata-target=".bs-example-modal-lg" onclick="View('+value+')">流程图</a>&nbsp;&nbsp;<a style="cursor: pointer" data-toggle="modal" data-target="#myModalTwo" ata-target=".bs-example-modal-lg" onclick="hiscomenet('+value+')">流程轨迹</a>&nbsp;&nbsp;'+
                '<a onclick="shenpi(event)" tId="'+taskId+'" eId="'+entityId+'" wT="'+workType+'" style="cursor: pointer" data-toggle="modal" data-target="#myModalThr" ata-target=".bs-example-modal-lg" >查看</a>&nbsp;&nbsp;';
        }
    }
    ]
});

function tofind() {
    //$('#to-do-table').bootstrapTable('refresh');
    $('#to-historic-table').bootstrapTable('selectPage', 1);//直接会请求接口数据，不需要再refresh
};


$('#to-historic-table').bootstrapTable({
    url:'/work/completeActivity',
    method:'post',
    sidePagination:'server',
    pagination:true,
    showRefresh: true,
    striped:true,
    pageNumber:1,
    pageSize:5,
    pageList:[20,40,60,80],
    striped: true,
    showToggle:true,
    queryParamsType:'limit',
    queryParams:function queryParams(params) {
        if (params.offset<0){
            params.offset = 0;
        }
        params.condition = $('#table-types option:selected').val();
        params.front = 'front';
        console.log(params.condition);
        return params;
    },
    columns:[{
        field:'workType',
        title:'所属业务',
        width:100,
        formatter:function (value,row,index) {
            if(value=='UserInsert'){
                return '帐号注册审核';
            }
            else if(value=='ArticleInsert'){
                return '发布信息审核';
            }
            else if (value=="UserUpdate"){
                return "帐号修改审核"
            }
            else if (value=="ArticleUpdate"){
                return "发布更改审核"
            }
            else if (value=="UserDelete"){
                return "帐号修改审核"
            }
            else if (value=="ArticleDelete"){
                return "发布更改审核"
            }
        }
    },{
        field:'workType',
        title:'提醒内容',
        width:100,
        formatter:function (value,row,index) {
            if(value=='UserInsert'){
                return '帐号注册审核';
            }
            else if(value=='ArticleInsert'){
                return '发布信息审核';
            }
            else if (value=="UserUpdate"){
                return "帐号修改审核"
            }
            else if (value=="ArticleUpdate"){
                return "发布更改审核"
            }
            else if (value=="UserDelete"){
                return "帐号修改审核"
            }
            else if (value=="ArticleDelete"){
                return "发布更改审核"
            }
        }
    },{
        field:'taskname',
        title:'流程环节',
        width:80
    },{
        field:'startTime',
        title:'接收时间',
        width:160,
        formatter:function (value,row,index) {
            return format(value,'yyyy-MM-dd HH:mm:ss');
        }
    },{
        field:'endTime',
        title:'处理时间',
        width:160,
        formatter:function (value,row,index) {
            return format(value,'yyyy-MM-dd HH:mm:ss');
        }
    },{
        field:'user',
        title:'申请人',
        width:80
    },{
        field:'processInstanceId',
        title:'操作',
        width:160,
        formatter:function (value,row,index) {
            processInstanceId = row.processInstanceId;
            entityId = row.entityId;
            workType = row.workType;
            return '<a data-toggle="modal" data-target="#myModalFou" ata-target=".bs-example-modal-lg" onclick="historicView('+value+')">流程图</a>&nbsp;&nbsp;'+
                '<a style="cursor: pointer" data-toggle="modal" data-target="#myModalFive" ata-target=".bs-example-modal-lg" onclick="hiscomenets('+value+')">流程轨迹</a>&nbsp;&nbsp;'+
                '<a onclick="shenpis(event)" pId="'+processInstanceId+'" eId="'+entityId+'" wT="'+workType+'" style="cursor: pointer" data-toggle="modal" data-target="#myModalSix" ata-target=".bs-example-modal-lg">查看</a>&nbsp;&nbsp;';
        }
    }
    ]
});

function formatNull(s) {
    console.log(s);
    if( s== null){
        return '---';
    }
    else {
        return s;
    }
}

function shenpi(e) {
    var target = e.target;
    var taskId = $(target).attr('tId');
    var entityId = $(target).attr('eId');
    var workType = $(target).attr('wT');
    console.log(entityId);
    console.log(taskId);
    console.log(workType);
    //修改用户详情展示
    $.ajax({
        url:'/work/showInfo?entityId='+entityId,
        type:'get',
        success:function (data) {
            console.log(data);
            $('#user_account').text(formatNull(data.user.account));
            $('#user_realName').text(formatNull(data.user.realName));
            $('#user_IDcard').text(formatNull(data.user.iDcard));
            $('#user_phoneNum').text(formatNull(data.user.phoneNum));
            $('#user_email').text(formatNull(data.user.email));
            $('#company_name').text(formatNull(data.company.name));
            $('#company_nature').text(formatNull(data.company.nature));
            $('#company_address').text(formatNull(data.company.address));
            $('#company_zipCode').text(formatNull(data.company.zipCode));
            $('#legalRep_name').text(formatNull(data.legalRep.name));
            $('#legalRep_jopPosition').text(formatNull(data.legalRep.jobPosition));
            $('#legalRep_officePhoneNum').text(formatNull(data.legalRep.officePhoneNum));
            $('#legalRep_cellPhoneNum').text(formatNull(data.legalRep.cellPhoneNum));
            $('#company_associationName').text(formatNull(data.company.associationName));
            $('#company_associationUnit').text(formatNull(data.company.associationUnit));
            $('#company_chapterName').text(formatNull(data.company.chapterName));
            $('#company_chapterUnit').text(formatNull(data.company.chapterUnit));
            $('#company_introduction').text(formatNull(data.company.introduction));
            $('#company_demand').text(formatNull(data.company.demand));

            var module = $('#intr');
            module.html('');
            for (var i = 0;i<data.introduceds.length;i++) {
                module.append(
                    <!--动态拼接 推荐人-->
                    '<tr>'+
                    '<th rowspan="4" style="width: 145px;">推荐人选及拟任分会职务<sapn>（协会副会长单位、分会会长单位和副会长单位至少要有一人）</sapn></th>' +
                    '<th style="width: 100px;">姓名</th>' +
                    '<th style="width: 219px;">单位职务</th>' +
                    '<th>协会职务</th>' +
                    '<th colspan="2">分会职务</th></tr>' +
                    '<tr>' +
                    '<td rowspan = "3">'+formatNull(data.introduceds[i].name)+'</td>'+
                    '<td>'+formatNull(data.introduceds[i].jobPosition)+'</td>'+
                    '<td>'+formatNull(data.introduceds[i].asctJobPosition)+'</td>'+
                    '<td colspan = "2">'+formatNull(data.introduceds[i].chapterJobPosition)+'</td>'+
                    '</tr> <tr> <th>电子邮箱</th> <th>办公电话</th> <th colspan="2">手机</th> </tr><tr>' +
                    '<td>'+formatNull(data.introduceds[i].email)+'</td>'+
                    '<td>'+formatNull(data.introduceds[i].officePhoneNum)+'</td>'+
                    '<td colspan = "2">'+formatNull(data.introduceds[i].cellPhoneNum)+'</td>'+
                    '</tr>'
                );
            }
            var module = $('#Contact');
            module.html('');
            for (var i = 0;i<data.designatedContacts.length;i++) {
                module.append(
                    <!--动态拼接 联系人-->
                    '<tr>'+
                    '<th rowspan="4" style="width: 145px;"><span class="font-x">*</span>单位指定联系人'+
                    '<sapn class="tip">（至少一人）</sapn>'+
                    '</th>'+
                    '<th style="width: 100px;"><span class="font-x">*</span>姓名</th>'+
                    '<th style="width: 219px;"><span class="font-x">*</span>单位职务</th>'+
                    '<th style="width: 249px;"><span class="font-x">*</span>办公电话</th>'+
                    '<th colspan="2"><span class="font-x">*</span>手机</th>'+
                    '</tr>'+
                    '<tr>'+
                    '<td rowspan="3" id="designatedContact0_name">'+formatNull(data.designatedContacts[i].name)+'</td>'+
                    '<td id="designatedContact0_jopPosition">'+formatNull(data.designatedContacts[i].jobPosition)+'</td>'+
                    '<td id="designatedContact0_officePhoneNum">'+formatNull(data.designatedContacts[i].officePhoneNum)+'</td>'+
                    '<td colspan="2" id="designatedContact0_cellPhoneNum">'+formatNull(data.designatedContacts[i].cellPhoneNum)+'</td>'+
                    '</tr>'+
                    '<tr>'+
                    '<th><span class="font-x">*</span>电子邮箱</th>'+
                    '<th>传真号码</th>'+
                    '<th colspan="2"><span class="font-x">*</span>微信号/QQ号</th>'+
                    '</tr>'+
                    '<tr>'+
                    '<td id="designatedContact0_email">'+formatNull(data.designatedContacts[i].email)+'</td>'+
                    '<td id="designatedContact0_faxNum">'+formatNull(data.designatedContacts[i].faxNum)+'</td>'+
                    '<td colspan="2" id="designatedContact0_onlineNum">'+formatNull(data.designatedContacts[i].onlineNum)+'</td>'+
                    '</tr>'
                );
            }


        },
        error:function (data) {
            console.log(data);
        }
    });
    $('#taskId').val(taskId);
    $('#entity').val(entityId);
    $('workType').val(workType);
    hiscomenet(taskId);
}

function shenpis(e) {
    var target = e.target;
    var processInstanceId = $(target).attr('pId');
    var entityId = $(target).attr('eId');
    var workType = $(target).attr('wT');
    console.log(entityId);
    console.log(taskId);
    console.log(workType);
    //修改用户详情展示
    $.ajax({
        url:'/work/showInfo?entityId='+entityId,
        type:'get',
        success:function (data) {
            console.log(data);
            $('#user_accounts').text(formatNull(data.user.account));
            $('#user_realNames').text(formatNull(data.user.realName));
            $('#user_IDcards').text(formatNull(data.user.iDcard));
            $('#user_phoneNums').text(formatNull(data.user.phoneNum));
            $('#user_emails').text(formatNull(data.user.email));
            $('#company_names').text(formatNull(data.company.name));
            $('#company_natures').text(formatNull(data.company.nature));
            $('#company_addresss').text(formatNull(data.company.address));
            $('#company_zipCodes').text(formatNull(data.company.zipCode));
            $('#legalRep_names').text(formatNull(data.legalRep.name));
            $('#legalRep_jopPositions').text(formatNull(data.legalRep.jobPosition));
            $('#legalRep_officePhoneNums').text(formatNull(data.legalRep.officePhoneNum));
            $('#legalRep_cellPhoneNums').text(formatNull(data.legalRep.cellPhoneNum));
            $('#company_associationNames').text(formatNull(data.company.associationName));
            $('#company_associationUnits').text(formatNull(data.company.associationUnit));
            $('#company_chapterNames').text(formatNull(data.company.chapterName));
            $('#company_chapterUnits').text(formatNull(data.company.chapterUnit));
            $('#company_introductions').text(formatNull(data.company.introduction));
            $('#company_demands').text(formatNull(data.company.demand));

            var module = $('#intrs');
            module.html('');
            for (var i = 0;i<data.introduceds.length;i++) {
                module.append(
                    <!--动态拼接 推荐人-->
                    '<tr>'+
                    '<th rowspan="4" style="width: 145px;">推荐人选及拟任分会职务<sapn>（协会副会长单位、分会会长单位和副会长单位至少要有一人）</sapn></th>' +
                    '<th style="width: 100px;">姓名</th>' +
                    '<th style="width: 219px;">单位职务</th>' +
                    '<th>协会职务</th>' +
                    '<th colspan="2">分会职务</th></tr>' +
                    '<tr>' +
                    '<td rowspan = "3">'+formatNull(data.introduceds[i].name)+'</td>'+
                    '<td>'+formatNull(data.introduceds[i].jobPosition)+'</td>'+
                    '<td>'+formatNull(data.introduceds[i].asctJobPosition)+'</td>'+
                    '<td colspan = "2">'+formatNull(data.introduceds[i].chapterJobPosition)+'</td>'+
                    '</tr> <tr> <th>电子邮箱</th> <th>办公电话</th> <th colspan="2">手机</th> </tr><tr>' +
                    '<td>'+formatNull(data.introduceds[i].email)+'</td>'+
                    '<td>'+formatNull(data.introduceds[i].officePhoneNum)+'</td>'+
                    '<td colspan = "2">'+formatNull(data.introduceds[i].cellPhoneNum)+'</td>'+
                    '</tr>'
                );
            }
            var module = $('#Contacts');
            module.html('');
            for (var i = 0;i<data.designatedContacts.length;i++) {
                module.append(
                    <!--动态拼接 联系人-->
                    '<tr>'+
                    '<th rowspan="4" style="width: 145px;"><span class="font-x">*</span>单位指定联系人'+
                    '<sapn class="tip">（至少一人）</sapn>'+
                    '</th>'+
                    '<th style="width: 100px;"><span class="font-x">*</span>姓名</th>'+
                    '<th style="width: 219px;"><span class="font-x">*</span>单位职务</th>'+
                    '<th style="width: 249px;"><span class="font-x">*</span>办公电话</th>'+
                    '<th colspan="2"><span class="font-x">*</span>手机</th>'+
                    '</tr>'+
                    '<tr>'+
                    '<td rowspan="3" id="designatedContact0_name">'+formatNull(data.designatedContacts[i].name)+'</td>'+
                    '<td id="designatedContact0_jopPosition">'+formatNull(data.designatedContacts[i].jobPosition)+'</td>'+
                    '<td id="designatedContact0_officePhoneNum">'+formatNull(data.designatedContacts[i].officePhoneNum)+'</td>'+
                    '<td colspan="2" id="designatedContact0_cellPhoneNum">'+formatNull(data.designatedContacts[i].cellPhoneNum)+'</td>'+
                    '</tr>'+
                    '<tr>'+
                    '<th><span class="font-x">*</span>电子邮箱</th>'+
                    '<th>传真号码</th>'+
                    '<th colspan="2"><span class="font-x">*</span>微信号/QQ号</th>'+
                    '</tr>'+
                    '<tr>'+
                    '<td id="designatedContact0_email">'+formatNull(data.designatedContacts[i].email)+'</td>'+
                    '<td id="designatedContact0_faxNum">'+formatNull(data.designatedContacts[i].faxNum)+'</td>'+
                    '<td colspan="2" id="designatedContact0_onlineNum">'+formatNull(data.designatedContacts[i].onlineNum)+'</td>'+
                    '</tr>'
                );
            }


        },
        error:function (data) {
            console.log(data);
        }
    });
    hiscomenets(processInstanceId);
}

function hiscomenets(processInstanceId) {
    $.ajax({
        url:'/work/historicComment?processInstanceId='+processInstanceId,
        type:'get',
        success:function (data) {
            var module = $('#his-comment-tables');
            var modules = $('#historic-comment-tables');
            module.html('');
            modules.html('');
            module.append(
                '<thead><tr><th>办理人</th> <th>意见</th> <th>开始时间</th> <th>结束时间</th> <th>流程环节</th></tr> </thead>'
            );
            modules.append(
                '<thead><tr><th>办理人</th> <th>意见</th> <th>开始时间</th> <th>结束时间</th> <th>流程环节</th></tr> </thead>'
            );
            $.each(data,function (i,item) {
                var username = item.username;
                var message = item.message;
                var startTime = format(item.startTime,'yyyy-MM-dd HH:mm:ss');
                var endTime = format(item.endTime,'yyyy-MM-dd HH:mm:ss');
                var taskName = item.taskName;
                module.append(
                    '<tr> <td>'+ username +
                    '</td> <td>'+ message +
                    '</td> <td>'+ startTime +
                    '</td> <td>'+ endTime +
                    '</td> <td>'+ taskName +
                    '</td> </tr>'
                );
                modules.append(
                    '<tr> <td>'+ username +
                    '</td> <td>'+ message +
                    '</td> <td>'+ startTime +
                    '</td> <td>'+ endTime +
                    '</td> <td>'+ taskName +
                    '</td> </tr>'
                );
            })
        }
    });

}

function historicView(processInstanceId) {
    var s = '/work/historicViewImage?processInstanceId='+processInstanceId;
    $("#img_ids").attr("src",s);
}

function View(taskId) {
    var s = '/work/flowsheet?taskId='+taskId;
    $("#img_id").attr("src",s);
    $.ajax({
        url:'/work/coordinate?taskId='+taskId,
        type:'get',
        success:function (data) {
            console.log(data.x);
            var x = data.x + 'px';
            var y = data.y + 'px';
            var width =data.width + 'px';
            var height = data.height + 'px';
            console.log(x);
            $("#img_div").css("width",width);
            $("#img_div").css("height",height);
            $("#img_div").css("left",x);
            $("#img_div").css("top",y);
        },
        error:function () {
            console.log("aaa");
        }
    });
}


function hiscomenet(taskId) {
    $.ajax({
        url:'/work/comment?taskId='+taskId,
        type:'get',
        success:function (data) {
            var module = $('#comment-table');
            var modules = $('#comments-table');
            module.html('');
            modules.html('');
            module.append(
                '<thead><tr><th>办理人</th> <th>意见</th> <th>开始时间</th> <th>结束时间</th> <th>流程环节</th></tr> </thead>'
            );
            modules.append(
                '<thead><tr><th>办理人</th> <th>意见</th> <th>开始时间</th> <th>结束时间</th> <th>流程环节</th></tr> </thead>'
            );
            $.each(data,function (i,item) {
                var username = item.username;
                var message = item.message;
                var startTime = format(item.startTime,'yyyy-MM-dd HH:mm:ss');
                var endTime = format(item.endTime,'yyyy-MM-dd HH:mm:ss');
                var taskName = item.taskName;
                module.append(
                    '<tr> <td>'+ username +
                    '</td> <td>'+ message +
                    '</td> <td>'+ startTime +
                    '</td> <td>'+ endTime +
                    '</td> <td>'+ taskName +
                    '</td> </tr>'
                );
                modules.append(
                    '<tr> <td>'+ username +
                    '</td> <td>'+ message +
                    '</td> <td>'+ startTime +
                    '</td> <td>'+ endTime +
                    '</td> <td>'+ taskName +
                    '</td> </tr>'
                );
            })
        }
    });
}

$('#agree-button').click(function () {
    var entityId = $('#entity').val();
    var workType = $('#workType').val();
    // if(workType =='UserInsert'){
        window.location.href='/nUser/CheckUserInfo?uid='+entityId;
    // }
    // else if (workType == 'UserUpdate'){
    //     window.location.href='../user/checkInfo?t_id='+entityId;
    // }
});