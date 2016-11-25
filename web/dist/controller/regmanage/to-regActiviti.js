/**
 * Created by clouder on 16-9-19.
 */
var url = document.location.toString();
var companyId = url.substring(url.lastIndexOf('=')+1,url.length);

$(function () {
    var lis = $('.left-nav .add-active');
    $(lis[1]).addClass('active');
});

$('#to-do-tables').bootstrapTable({
    url:'/back/regmanage/findAllCount',
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
        params.companyId = companyId;
        console.log(params.companyId);
        return params;
    },
    columns:[{
        field:'account',
        title:'帐号',
        width:120
    },{
        field:'realName',
        title:'姓名',
        width:120
    },{
        field:'phoneNum',
        title:'手机号码',
        width:120
    },{
        field:'email',
        title:'邮箱',
        width:120
    },{
        field:'iDcard',
        title:'身份证号码',
        width:120
    },{
        field:'status',
        title:'是否正常使用',
        width:120,
        formatter:function (value,row,index) {
            if( value === 4 ){
                return '否';
            }else{
                return '是';
            }
        }
    },{
        field:'id',
        title:'操作',
        width:60,
        formatter:function (value,row,index) {
            return '<a data-toggle="modal" data-target="#myModal" onclick="findEntity('+value+')">修改</a>&nbsp;&nbsp;';
        }
    }
    ]
});

$('#to-do-table').bootstrapTable({
    url:'/back/regmanage/findRegActiviti?companyId='+companyId,
    method:'get',
    sidePagination:'client',
    pagination:true,
    showRefresh: true,
    striped:true,
    pageSize:5,
    pageList:[20,40,60,80],
    striped: true,
    columns:[{
        field:'realName',
        title:'申请人',
        width:120
    },{
        field:'startTime',
        title:'申请时间',
        width:120,
        formatter:function (value,row,index) {
            return format(value,'yyyy-MM-dd HH:mm:ss');
        }
    },{
        field:'workType',
        title:'申请类型',
        width:120,
        formatter:function (value,row,index) {
            if (value == 'UserInsert') {
                return '客户帐号注册';
            }
            else if (value == 'UserUpdate') {
                return '修改用户信息';
            }
        }
    },{
        field:'status',
        title:'审批状态',
        width:120,
        formatter:function (value,row,index) {
            if(value ==1){
                return "审批中";
            }else if(value==2){
                return "不通过";
            }else if (value==3){
                return "通过";
            }
        }
    },{
        field:'endTime',
        title:'审批时间',
        width:120,
        formatter:function (value,row,index) {
            if(value == null){
                return '';
            }else {
                return format(value,'yyyy-MM-dd HH:mm:ss');
            }
        }
    },{
        field:'entityId',
        title:'操作',
        width:60,
        formatter:function (value,row,index) {
            entityId = row.entityId;
            pId = row.pId;
            sId = row.status;
            return '<a onclick="shenpi(event)" tId="'+pId+'" sId="'+sId+'" eId="'+entityId+'" style="cursor: pointer" data-toggle="modal" data-target="#myModalThr" ata-target=".bs-example-modal-lg">查看</a>&nbsp;&nbsp;';
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
    var status = $(target).attr('sId');
    $.ajax({
            url:'/back/work/showInfo?entityId='+entityId,
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
    $('#taskId').val(pId);
    if(status == 2){
        hiscomenets(taskId);
    }else {
        hiscomenet(taskId);
    }
}

function hiscomenets(taskId) {
    $.ajax({
        url:'/work/comment?taskId='+taskId,
        type:'get',
        success:function (data) {
            var module = $('#comment-table');
            module.html('');
            module.append(
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
            })
        }
    });
}

function hiscomenet(taskId) {
    $.ajax({
        url:'/work/historicComment?processInstanceId='+taskId,
        type:'get',
        success:function (data) {
            var module = $('#comment-table');
            module.html('');
            module.append(
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
            })
        }
    });
}

function findEntity(id) {
    $.ajax({
        url:'/back/regmanage/show-account-info?id='+id,
        type:'get',
        success:function (data) {
            $('#account').text(data.usermessage.account);
            $('#id').val(data.usermessage.id);
            $('#name').val(data.usermessage.realName);
            $('#phone').val(data.usermessage.phoneNum);
            $('#email').val(data.usermessage.email);
            $('#idCard').val(data.usermessage.iDcard);
            if(data.usermessage.status===4){
                $('#isCont').prop("checked", true);
            }else {
                $('#isConts').prop("checked", true);
            }
        }
    });
}

function updateEntity() {
    var id = $('#id').val();
    var usermessage  = {
        account:$('#account').text(),
        id:$('#id').val(),
        realName:$('#name').val(),
        phoneNum:$('#phone').val(),
        email:$('#email').val(),
        iDcard:$('#idCard').val(),
        status:$('input[name="isCont"]:checked').val()
    };

    $.ajax({
        url:'/back/regmanage/change-account-info',
        type:'post',
        contentType:'application/json',
        data:JSON.stringify(usermessage),
        success:function (data) {
            console.log("成功");
            $('#moal-close').click();
            $('#to-do-tables').bootstrapTable('refresh');
           // $('#myModal').modal('toggle');
        },
        error:function (data) {
            console.log(data);
        }
    });
}

function deleteId(id) {
    $('#deleteid').val(id);
}
function DeleteEntity() {
    var id = $('#deleteid').val();
    console.log(id);
    $.ajax({
        url:'/deleteId?id='+id,
        type:'get',
        success:function (data) {
            console.log("成功");
            //$('#Modaltwo').modal('toggle');
            $('#modal-close2').click();
            $('#to-do-tables').bootstrapTable('refresh');
        },
        error:function (data) {
            console.log(data);
        }
    });
}