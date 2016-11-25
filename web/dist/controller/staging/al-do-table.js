// var processInstanceId ;
// var entityId ;
// var workType ;
$(function () {
    var lis = $('.left-nav .add-active');
    $(lis[1]).addClass('active');
});

$('#to-do-table').bootstrapTable({
    url:'/back/work/completeActivity',
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
        params.condition = $('#table-type option:selected').val();
        console.log(params.condition);
        return params;
    },
    columns:[{
        field:'workType',
        title:'所属业务',
        width:120,
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
        width:120,
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
        width:120
    },{
        field:'startTime',
        title:'接收时间',
        width:120,
        formatter:function (value,row,index) {
            return format(value,'yyyy-MM-dd HH:mm:ss');
        }
    },{
        field:'endTime',
        title:'处理时间',
        width:120,
        formatter:function (value,row,index) {
            return format(value,'yyyy-MM-dd HH:mm:ss');
        }
    },{
        field:'user',
        title:'审核人',
        width:100
    },{
        field:'processInstanceId',
        title:'操作',
        width:140,
        formatter:function (value,row,index) {
            var processInstanceId = row.processInstanceId;
            var entityId = row.entityId;
            var workType = row.workType;

//            return '<a href ="/PIDM/work/to-hiflowsheet?processInstanceId='+value+'">流程图</a>&nbsp;&nbsp;<a href ="/PIDM/work/to-pe-datails?processInstanceId='+value+'">查看</a>';
            return '<a data-toggle="modal" data-target="#myModal" ata-target=".bs-example-modal-lg" onclick="historicView('+value+')">流程图</a>&nbsp;&nbsp;' +
                '<a style="cursor: pointer" data-toggle="modal" data-target="#myModalTwo" ata-target=".bs-example-modal-lg" onclick="hiscomenet('+value+')">流程轨迹</a>&nbsp;&nbsp;' +
                '<a  onclick="shenpi(event)" pId="'+processInstanceId+'" eId="'+entityId+'" wT="'+workType+'" style="cursor: pointer" data-toggle="modal" data-target="#myModalThr" ata-target=".bs-example-modal-lg" >查看</a>&nbsp;&nbsp;';
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
    var processInstanceId = $(target).attr('pId');
    var entityId = $(target).attr('eId');
    var workType = $(target).attr('wT');
    if(workType=='UserInsert'){
        $("#article-message").hide();
        $("#user-message").show();
        //修改用户详情展示
        $.ajax({
            url:'/back/work/showInfo?entityId='+entityId,
            type:'get',
            success:function (data) {
                console.log(data);
                if(data.filePath){
                    var filePath = '<a href="/new-register/downloadApplication?filePath='+data.filePath+'">点击下载</a>';
                    $('#filePath').append(filePath);
                }
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
    }
    else if(workType=='ArticleInsert'){
        $("#user-message").hide();
        $("#article-message").show();
        $.ajax({
            url:'/back/work/article-preview?aid='+entityId,
            type:'get',
            success:function (data) {
                console.log(data);
                console.log(data[0].header);
                var module = $('#article-message');
                module.html('');
                if (data.length == 1) {
                    module.append(
                        '<h4>' + data[0].header + '</h4><span>发布时间：' + data[0].publishTime + '</span><hr>');
                    if (data[0].poster != null) {
                        module.append('<img src="/back/article/showImage?path=' + data[0].poster + '" alt="" width="100%" >');
                    }
                    module.append('<div>' + data[0].content + '</div>' + '<div class="annex-desc" id="perform-poster">');
                    if (data[0].poster != null) {
                        module.append(
                            '<span class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;' +
                            '展示图片: <a href="/back/article/showImage?path=' + formatNull(data[0].poster) + '"' +
                            'target="_blank">' + formatNull(data[0].poster)+ '</a>　&nbsp;&nbsp;&nbsp;&nbsp;' +
                            '图片说明：' + formatNull(data[0].posterDesc) + '<span id="span-delete-poster" class="span-delete pull-right">'
                        );
                    }
                }
                if (data.length == 2) {
                    module.append(
                        '<h4>' + data[0].header + '</h4><span>发布时间：' + data[0].publishTime + '</span><hr>');
                    if (data[0].poster != null) {
                        module.append('<img src="/back/article/showImage?path=' + data[0].poster + '" alt="" width="100%" >');
                    }
                    module.append('<div>' + data[0].content + '</div>' + '<div class="annex-desc" id="perform-poster">');
                    if (data[0].poster != null) {
                        module.append(
                            '<span class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;' +
                            '展示图片: <a href="/back/article/showImage?path=' + data[0].poster + '"' +
                            'target="_blank">' + formatNull(data[0].poster) + '</a>　&nbsp;&nbsp;&nbsp;&nbsp;' +
                            '图片说明：' + formatNull(data[0].posterDesc) + '<span id="span-delete-poster" class="span-delete pull-right">'
                        );
                    }
                    if (data[1].filesurl != null) {
                        module.append('<div class="annex-desc" id="perform-annex">' + '<span class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;' +
                            '附件: <a href="/back/article/download-annex?filePath=' + formatNull(data[1].filesurl) + '">' + data[1].filesurl + '</a>　&nbsp;&nbsp;&nbsp;&nbsp;' +
                            '附件说明：' + formatNull(data[1].annexDesc) + '<span id="span-delete-annex" class="span-delete pull-right">' +
                            '</div>');
                    }
                }
            },
            error:function(data) {
            }
        });
    }
    else if (workType=="UserUpdate"){
        $("#article-message").hide();
        $("#user-message").show();
        //修改用户详情展示
        $.ajax({
            url:'/back/work/showInfo?entityId='+entityId,
            type:'get',
            success:function (data) {
                console.log(data);
                if(data.filePath){
                    var filePath = '<a href="/new-register/downloadApplication?filePath='+data.filePath+'">点击下载</a>';
                    $('#filePath').append(filePath);
                }
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
    }
    else if (workType=="ArticleUpdate"){
        $("#user-message").hide();
        $("#article-message").show();
        $.ajax({
            url:'/back/work/article-preview?aid='+entityId,
            type:'get',
            success:function (data) {
                console.log(data);
                console.log(data[0].header);
                var module = $('#article-message');
                module.html('');
                if (data.length == 1) {
                    module.append(
                        '<h4>' + data[0].header + '</h4><span>发布时间：' + data[0].publishTime + '</span><hr>');
                    if (data[0].poster != null) {
                        module.append('<img src="/back/article/showImage?path=' + data[0].poster + '" alt="" width="100%" >');
                    }
                    module.append('<div>' + data[0].content + '</div>' + '<div class="annex-desc" id="perform-poster">');
                    if (data[0].poster != null) {
                        module.append(
                            '<span class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;' +
                            '展示图片: <a href="/back/article/showImage?path=' + formatNull(data[0].poster) + '"' +
                            'target="_blank">' + formatNull(data[0].poster)+ '</a>　&nbsp;&nbsp;&nbsp;&nbsp;' +
                            '图片说明：' + formatNull(data[0].posterDesc) + '<span id="span-delete-poster" class="span-delete pull-right">'
                        );
                    }
                }
                if (data.length == 2) {
                    module.append(
                        '<h4>' + data[0].header + '</h4><span>发布时间：' + data[0].publishTime + '</span><hr>');
                    if (data[0].poster != null) {
                        module.append('<img src="/back/article/showImage?path=' + data[0].poster + '" alt="" width="100%" >');
                    }
                    module.append('<div>' + data[0].content + '</div>' + '<div class="annex-desc" id="perform-poster">');
                    if (data[0].poster != null) {
                        module.append(
                            '<span class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;' +
                            '展示图片: <a href="/back/article/showImage?path=' + data[0].poster + '"' +
                            'target="_blank">' + formatNull(data[0].poster) + '</a>　&nbsp;&nbsp;&nbsp;&nbsp;' +
                            '图片说明：' + formatNull(data[0].posterDesc) + '<span id="span-delete-poster" class="span-delete pull-right">'
                        );
                    }
                    if (data[1].filesurl != null) {
                        module.append('<div class="annex-desc" id="perform-annex">' + '<span class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;' +
                            '附件: <a href="/back/article/download-annex?filePath=' + formatNull(data[1].filesurl) + '">' + data[1].filesurl + '</a>　&nbsp;&nbsp;&nbsp;&nbsp;' +
                            '附件说明：' + formatNull(data[1].annexDesc) + '<span id="span-delete-annex" class="span-delete pull-right">' +
                            '</div>');
                    }
                }
            },
            error:function(data) {
            }
        });
    }
    hiscomenet(processInstanceId);
}

function tofind() {
    //$('#to-do-table').bootstrapTable('refresh');
    $('#to-do-table').bootstrapTable('selectPage', 1);//直接会请求接口数据，不需要再refresh
};

function historicView(processInstanceId) {
    var s = 'historicViewImage?processInstanceId='+processInstanceId;
    $("#img_id").attr("src",s);
}

function hiscomenet(processInstanceId) {
    $.ajax({
        url:'/back/work/historicComment?processInstanceId='+processInstanceId,
        type:'get',
        success:function (data) {
            var module = $('#comment-table');
            var modules = $('#comment-tables');
            modules.html('');
            module.html('');
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