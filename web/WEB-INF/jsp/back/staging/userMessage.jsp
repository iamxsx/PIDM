<%--
  Created by IntelliJ IDEA.
  User: clouder
  Date: 16-9-20
  Time: 下午2:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>客户信息档案</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
    <link rel="stylesheet" href="${ctx}/dist/css/left_nav.css">
    <link rel="stylesheet" href="${ctx}/dist/css/client/clienInfo.css">
    <script src="${ctx}/dist/js/jquery.min.js"></script>
</head>
<body>
<div class="container-fluid" style="padding: 16px 36px;color: black">
    <!-- Large modal -->
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg">Large modal</button>
    <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="myModalLabelThr"><span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;审核意见</h4>
                </div>
                <div class="modal-body">
                        <div id="user-message">mmmm</div>
                </div>
            </div>
        </div>
    </div>

</div>

</body>

<script src="${ctx}/dist/js/bootstrap.min.js"></script>
<script src="${ctx}/dist/js/bootstrap-table.js"></script>
<script>
    $(function (){
        function formatNull(s) {
            console.log(s);
            if( s== null){
                return '---';
            }
            else {
                return s;
            }
        }
        $.ajax({
            url:'../info/showInfo?t_id='+44,
            type:'get',
            success:function (data) {
                console.log(data);
                var module = $('#user-message');
                module.html('');
                module.append(
                        '<div class="row">'+
                        '<div id="container">'+
                        '<div class="container-fluid">'+
                        '<div>'+
                        '<div class="row">'+
                        '<div class="col-sm-2 text-right">帐号：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.user.account)+'</span>'+
                        '<div class="col-sm-2 text-right">申请人姓名：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.user.realName)+'</span>'+
                        '<div class="col-sm-2 text-right"> 加入协会：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.association.name)+'</span>'+
                        '</div>'+
                        '<div class="row">'+
                        '<div class="col-sm-2 text-right">申请人手机号码：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.user.phoneNum)+'</span>'+
                        '<div class="col-sm-2 text-right">申请人联系邮箱：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.user.email)+'</span>'+
                        '<div class="col-sm-2 text-right"> 申请人身份证：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.user.iDcard)+'</span>'+
                        '</div>'+
                        '<div class="row">'+
                        '<div class="col-sm-2 text-right">加入：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.associationUnit.name)+'</span>'+
                        '</div>'+'<hr>'+
                        '<div>'+
                        '<div class="row">'+
                        '<div class="col-sm-2 text-right"> 单位名称：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.company.name)+'</span>'+
                        '<div class="col-sm-2 text-right"> 注册性质： </div>'+
                        '<span class="col-sm-2">'+formatNull(data.company.registerNature)+'</span>'+
                        '<div class="col-sm-2 text-right">通讯地址： </div>'+
                        '<span class="col-sm-2">'+formatNull(data.company.address)+'</span>'+
                        '</div>'+
                        '<div class="row">'+
                        '<div class="col-sm-2 text-right">邮政编码： </div>'+
                        '<span class="col-sm-2">'+formatNull(data.company.zipCode)+'</span>'+
                        '<div class="col-sm-2 text-right">所在城市： </div>'+
                        '<span class="col-sm-2">'+formatNull(data.company.city)+'</span>'+
                        '<div class="col-sm-2 text-right">所在区县： </div>'+
                        '<span class="col-sm-2">'+formatNull(data.company.county)+'</span>'+
                        '</div>'+
                        '<div class="row">'+
                        '<div class="col-sm-2 text-right">单位网编： </div>'+
                        '<span class="col-sm-2">'+formatNull(data.company.internetSite)+'</span>'+
                        '<div class="col-sm-2 text-right">电子邮箱：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.company.email)+'</span>'+
                        '<div class="col-sm-2 text-right">传真：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.company.faxNum)+'</span>'+
                        '</div>'+
                        '</div>'+ '<hr>'+
                        '<div>'+
                        '<div class="row">'+
                        '<div class="col-sm-2 text-right">法人代表：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.companyEmployee_legal.name)+'</span>'+
                        '<div class="col-sm-2 text-right">职务：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.companyEmployee_legal.jobPosition)+'</span>'+
                        '<div class="col-sm-2 text-right">办公电话：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.companyEmployee_legal.officePhoneNum)+'</span>'+
                        '</div>'+
                        '<div class="row">'+
                        '<div class="col-sm-2 text-right">手机：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.companyEmployee_legal.cellPhoneNum)+'</span>'+
                        '</div>'+
                        '</div>'+
                        '</div>'+ '<hr>'+
                        '<div>'+
                        '<div class="row">'+
                        '<div class="col-sm-2 text-right">指定联系人： </div>'+
                        '<span class="col-sm-2">'+formatNull(data.designatedContact.name)+'</span>'+
                        '<div class="col-sm-2 text-right">职务： </div>'+
                        '<span class="col-sm-2">'+formatNull(data.designatedContact.jobPosition)+'</span>'+
                        '<div class="col-sm-2 text-right">办公电话： </div>'+
                        '<span class="col-sm-2">'+formatNull(data.designatedContact.officePhoneNum)+'</span>'+
                        '</div>'+
                        '<div class="row">'+
                        '<div class="col-sm-2 text-right">手机： </div>'+
                        '<span class="col-sm-2">'+formatNull(data.designatedContact.cellPhoneNum)+'</span>'+
                        '<div class="col-sm-2 text-right">邮箱： </div>'+
                        '<span class="col-sm-2">'+formatNull(data.designatedContact.email)+'</span>'+
                        '<div class="col-sm-2 text-right">传真号码：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.designatedContact.faxNum)+'</span>'+
                        '</div>'+
                        '</div>'+ '<hr>');
                //for开始
                for (var i = 0;i<data.introduced.length;i++){
                    module.append(
                            '<div>'+
                            '<div class="row">'+
                            '<div class="col-sm-2 text-right">协会职务人选： </div>'+
                            '<span class="col-sm-2">'+formatNull(data.introduced[i].name)+'</span>'+
                            '<div class="col-sm-2 text-right">职务： </div>'+
                            '<span class="col-sm-2">'+formatNull(data.introduced[i].jobPosition)+'</span>'+
                            '<div class="col-sm-2 text-right">办公电话：</div>'+
                            '<span class="col-sm-2">'+formatNull(data.introduced[i].cellPhoneNum)+'</span>'+
                            '</div>'+
                            '<div class="row">'+
                            '<div class="col-sm-2 text-right"> 手机：</div>'+
                            '<span class="col-sm-2">'+formatNull(data.introduced[i].officePhoneNum)+'</span>'+
                            '<div class="col-sm-2 text-right"> 邮箱：</div>'+
                            '<span class="col-sm-2">'+formatNull(data.introduced[i].email)+'</span>'+
                            '<div class="col-sm-2 text-right"> 传真号码：</div>'+
                            '<span class="col-sm-2">'+formatNull(data.introduced[i].faxNum)+'</span>'+
                            '</div>'+
                            '</div>'+'<hr>'
                    );
                }
                //for结束
                module.append(
                        '<div>'+
                        '<div class="row">'+
                        '<div class="col-sm-2 text-right"> 企业性质：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.company.nature)+'</span>'+
                        '<div class="col-sm-2 text-right"> 所属行业：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.company.industry)+'</span>'+
                        '<div class="col-sm-2 text-right">主管部门： </div>'+
                        '<span class="col-sm-2">'+formatNull(data.company.adminDepartment)+'</span>'+
                        '</div>'+
                        '<div class="row">'+
                        '<div class="col-sm-2 text-right"> 员工人数：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.company.employeeNum)+'</span>'+
                        '<div class="col-sm-2 text-right"> 邮箱：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.company.email)+'</span>'+
                        '</div>'+
                        '</div>'+ '<hr>'+
                        '<div>'+
                        '<div class="row">'+
                        '<div class="col-sm-2 text-right">单位代表：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.companyEmployee_rep.name)+'</span>'+
                        '<div class="col-sm-2 text-right"> 职务：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.companyEmployee_rep.jobPosition)+'</span>'+
                        '<div class="col-sm-2 text-right"> 电话：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.companyEmployee_rep.officePhoneNum)+'</span>'+
                        '</div>'+
                        '<div class="row">'+
                        '<div class="col-sm-2 text-right"> 手机：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.companyEmployee_rep.cellPhoneNum)+'</span>'+
                        '<div class="col-sm-2 text-right"> 传真：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.companyEmployee_rep.faxNum)+'</span>'+
                        '<div class="col-sm-2 text-right"> 邮箱：</div>'+
                        '<span class="col-sm-2">'+formatNull(data.companyEmployee_rep.email)+'</span>'+
                        '</div>'+
                        '</div>'+ '<hr>'+
                        '<div>'
                );
                //if开始
                //广东价格和产业发展协会
                if(data.description.associationId===1 || data.description.associationId===5 ) {
                    module.append(
                            '<div id="assoc1">' +
                            '<div class="row" >' +
                            '<div class="col-sm-2 text-right">科研成果：</div>' +
                            '<textarea class="col-sm-8" name="des1">' + formatNull(data.description.description1) + '</textarea>' +
                            '</div>' +
                            '<div class="row">' +
                            '<div class="col-sm-2 text-right">表彰与处分： </div>' +
                            '<textarea class="col-sm-8" name="des2">' + formatNull(data.description.description2) + '</textarea>' +
                            '</div>' +
                            '</div>'
                    );
                }
                //if结束
                //if start
                //电价分会
                if(data.description.associationId===2) {
                    module.append(
                            '<div id="assoc2">' +
                            '<div class="row">' +
                            '<div class="col-sm-2 text-right"> 主营业务：</div>' +
                            '<textarea class="col-sm-8" name="des1">' + formatNull(data.description.description1) + '</textarea>' +
                            '</div>' +
                            '<div class="row">' +
                            '<div class="col-sm-2 text-right">希望得到分会哪方面到支持： </div>' +
                            '<textarea class="col-sm-8" name="des2">' + formatNull(data.description.description2) + '</textarea>' +
                            '</div>' +
                            '</div>'
                    );
                }
                //if结束
                //if start
                if(data.description.associationId===3) {
                    module.append(
                            '<div id="assoc3" class="row">' +
                            '<div class="col-sm-2 text-right">对广东价格和产业发展协会燃气价格分会的建议： </div>' +
                            '<textarea class="col-sm-8" name="des1">' + formatNull(data.description.description1) + '</textarea>' +
                            '</div>'
                    );
                }
                //if结束
                //if start
                //农产品价格分会
                if(data.description.associationId===4) {
                    module.append(
                            '<div id="assoc4" class="row">' +
                            '<div class="col-sm-2 text-right">对广东价格和产业发展协会农产品价格分会的建议： </div>' +
                            '<textarea class="col-sm-8" name="des1">' + formatNull(data.description.description1) + '</textarea>' +
                            '</div>'
                    );
                }
                //if结束
                //if start
                module.append(
                        '</div>'+
                        '</div>'+
                        '</div>'+
                        '</div>'
                );
            },
            error:function (data) {
                console.log(data);
            }
        });
    })
</script>
</html>

