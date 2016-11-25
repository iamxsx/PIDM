<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

		<link rel="stylesheet" href="${ctx}/dist/css/index/font-awesome.min.css" media="screen">
		<link rel="stylesheet" href="${ctx}/dist/css/index/amazeui.min.css"/>
		<link rel="stylesheet" href="${ctx}/dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="${ctx}/dist/css/font-awesome.min.css">
		<link rel="stylesheet" href="${ctx}/dist/css/index/zlight.menu.css" media="screen">
		<link rel="stylesheet" href="${ctx}/dist/css/index/index.css">
		<link rel="stylesheet" href="${ctx}/dist/css/back/index/index.css">
		<link rel="stylesheet" href="${ctx}/dist/css/left_nav_frontend.css">
		<link rel="stylesheet" href="${ctx}/dist/css/index/login.css">
		<link rel="stylesheet" href="${ctx}/dist/css/back/staging/register.css">


		<script src="${ctx}/dist/js/jquery.min.js"></script>
		<script src="${ctx}/dist/js/index/jquery.login.js"></script>
		<script src="${ctx}/dist/js/bootstrap.min.js"></script>
		<script src="${ctx}/dist/js/left_nav.js"></script>
		<script type="text/javascript" src="${ctx}/dist/js/index/amazeui.min.js"></script>
		<title>广东省价格和产业发展协会</title>
		<style>
			.register-box, .user-info{
				width: 1000px;
				min-width:1000px;
			}
			.my-modal-lg{
				width: 1050px;
			}
			.modal-body{
				padding: 0px;
			}
			.table{
				margin-bottom: 0px;
			}
		</style>
	</head>
	<body onload="createCode()">
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
				<div class="col-md-10" style="width: 988px;">
					<div class="row">
							<!-- Nav tabs -->
							<ul class="nav nav-tabs" role="tablist">
								<li role="presentation" class="active"><a href="#home" role="tab" data-toggle="tab">未处理</a></li>
								<li role="presentation"><a href="#profile" role="tab" data-toggle="tab">已处理</a></li>
								<li role="presentation"><a href="#communicate" role="tab" data-toggle="tab">经验交流、建议意见</a></li>
							</ul>
							<!-- Tab panes -->
							<div class="tab-content" style="margin-top: 5px">
								<div role="tabpanel" class="tab-pane fade in active" id="home">
									<div class="table-responsive">
										<table id="to-do-table" class="table table-bordered"></table>
									</div>
								</div>
								<div role="tabpanel" class="tab-pane fade" id="profile">
									<div class="table-responsive">
										<div class="form-group">
											<form class="navbar-form navbar-left" role="search">
												<span style="line-height: 42px;margin-right: 10px">所属业务:</span>
												<div class="form-group" style="margin-right: 10px">
													<select id="table-types" class="select">
														<option value="">请选择</option>
														<option value="User">用户信息审核</option>
														<!--<option value="Article">发布信息审核</option>-->
													</select>
												</div>
												<button class="btn btn-primary" type="button" id="to-do-list-btns" onclick="tofind()">
													<span class="glyphicon glyphicon-search"></span>查询
												</button>
											</form>
										</div>
										<br>
										<table id="to-historic-table" class="table table-bordered"></table>
									</div>
								</div>
								<div role="tabpanel" class="tab-pane fade" id="communicate">
									<div class="table-responsive">
										<table id="my-communicate" class="table table-bordered" style="text-align: center"></table>
									</div>
								</div>
							</div>
						<!-- Modal -->
						<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
										<h4 class="modal-title" id="myModalLabel">
											<span class="glyphicon glyphicon-book"></span>&nbsp;&nbsp;流程图</h4>
									</div>
									<div class="modal-body" style="padding: 0px">
										<img id="img_id">
										<div id="img_div"style="position: absolute; border: 2px solid red;" id="img_div"></div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-remove-sign"></span>&nbsp;&nbsp;关闭</button>
									</div>
								</div>
							</div>
						</div>
						<!-- Modal -->
						<div class="modal fade bs-example-modal-lg" id="myModalTwo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
										<h4 class="modal-title" id="myModalLabelTwo"><span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;审批流程轨迹</h4>
									</div>
									<div class="modal-body">
										<div class="table-responsive">
											<table class="table" style="width: 870px;margin:10px auto;border: 1px solid #DDDDDD;" id="comment-table">
											</table>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-chevron-left"></span>&nbsp;&nbsp;返回</button>
									</div>
								</div>
							</div>
						</div>
						<div class="modal fade bs-example-modal-lg" id="myModalThr" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-lg my-modal-lg">
								<div class="modal-content ">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
										<h4 class="modal-title" id="myModalLabelThr"><span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;审核意见</h4>
									</div>
									<div class="modal-body">
										<div id="user-message">
											<!--用户信息展示-->
											<div id="user-box" class="user-info">
												<table class="table table-bordered">
													<caption class="table-title">账号基本信息</caption>
													<tr>
														<th colspan="2"><span class="font-x">*</span>账号</th>
														<td colspan="2" id="user_account"></td>
													</tr>
													<tr>
														<th style="width: 145px"><span class="font-x">*</span>申请人姓名</th>
														<td id="user_realName" style="width: 319px"></td>
														<th style="width: 249px"><span class="font-x">*</span>申请人身份证</th>
														<td id="user_IDcard"></td>
													</tr>
													<tr>
														<th><span class="font-x">*</span>申请人手机号码</th>
														<td id="user_phoneNum"></td>
														<th><span class="font-x">*</span>申请人联系邮箱</th>
														<td id="user_email"></td>
													</tr>
												</table>
											</div>
											<div class="register-box">
												<div id="user-info" class="user page">
												</div>
												<table class="table table-bordered page" style="vertical-align: middle">
													<caption class="table-title">广东省价格和产业发展协会入会申请表</caption>
													<tr>
														<th  style="width:145px;"><span class="font-x">*</span>单位名称</th>
														<td  colspan="3" id="company_name"></td>
														<th><span class="font-x">*</span>单位性质</th>
														<td id="company_nature"></td>
													</tr>
													<tr>
														<th><span class="font-x">*</span>通讯地址</th>
														<td colspan="3" id="company_address"></td>
														<th><span class="font-x">*</span>邮政编码</th>
														<td id="company_zipCode"></td>
													</tr>
													<tr>
														<th rowspan="2"><span class="font-x">*</span>法人代表</th>
														<th style="width: 100px"><span class="font-x">*</span>姓名</th>
														<th style="width: 219px"><span class="font-x">*</span>单位职务</th>
														<th style="width: 249px"><span class="font-x">*</span>办公电话</th>
														<th colspan="2"><span class="font-x">*</span>手机</th>
													</tr>
													<tr>
														<td id="legalRep_name"></td>
														<td id="legalRep_jopPosition"></td>
														<td id="legalRep_officePhoneNum"></td>
														<td colspan="2" id="legalRep_cellPhoneNum"></td>
													</tr>
													<tr>
														<th rowspan="4"><span class="font-x">*</span>申请加入
														</th>
														<th colspan="2">协会</th>
														<th colspan="3">会员单位级别</th>
													</tr>
													<tr>
														<td colspan="2" id="company_associationName">
														</td>
														<td colspan="3" id="company_associationUnit">
														</td>
													</tr>
													<th colspan="2">分会</th>
													<th colspan="3">会员单位级别</th>
													<tr>
														<td colspan="2" id="company_chapterName">
														</td>
														<td colspan="3" id="company_chapterUnit">
														</td>
													</tr>
												</table>
												<table class="table table-bordered page" id="intr">
													<!--动态拼接 推荐人-->
												</table>
												<table class="table table-bordered page" id="Contact">
													<!--动态拼接 联系人-->
												</table>
												<table class="table table-bordered page">
													<tr style="border: solid 1px #DDDDDD">
														<th style="width: 145px;">单位简介<span class="tip">（300字以内）</span></th>
														<td colspan="7">
                                        <textarea class="intrud-sty" id="company_introduction">
                                        </textarea>
														</td>
													</tr>
													<tr style="border: solid 1px #DDDDDD">
														<th style="width: 145px;">希望得到协会或者分会服务和支持的主要内容</th>
														<td colspan="7">
                                        <textarea class="intrud-sty" id="company_demand">
                                        </textarea>
													</tr>
													<tr>
														<th>附件：</th>
														<td colspan="7" style="line-height: 2;text-indent: 2em; width: 3000px;">
														</td>
													</tr>
												</table>
												<div><span class="font-x">备注：各分会会员是广东省价格和产业发展协会的当然会员。</span></div>
											</div>
										</div>
										<div class="table-responsive register-box">
											<table class="table table-bordered page" id="comments-table"></table>
										</div>

									</div>
									<input type="hidden" id="taskId">
									<input type="hidden" id="entity">
									<input type="hidden" id="workType">
									<div class="modal-footer">
										<button type="button" id="agree-button" class="btn btn-primary btn-success"><span class="glyphicon glyphicon-ok-circle"></span>&nbsp;&nbsp;点击进行修改</button>
										<button type="button"  class="btn btn-default btn-danger" data-dismiss="modal"><span class=" glyphicon glyphicon-remove-circle"></span>&nbsp;&nbsp;取消</button>
									</div>
								</div>
							</div>
						</div>
						<!-- Modal -->
						<div class="modal fade bs-example-modal-lg" id="myModalFou" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
										<h4 class="modal-title">
											<span class="glyphicon glyphicon-book"></span>&nbsp;&nbsp;流程图</h4>
									</div>
									<div class="modal-body">
										<img id="img_ids">

									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-remove-sign"></span>&nbsp;&nbsp;关闭</button>
									</div>
								</div>
							</div>
						</div>
						<!-- Modal -->
						<div class="modal fade bs-example-modal-lg" id="myModalFive" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
										<h4 class="modal-title"><span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;审批流程轨迹</h4>
									</div>
									<div class="modal-body">
										<div class="table-responsive">
											<table class="table" style="width: 870px;margin:10px auto;border: 1px solid #DDDDDD;" id="his-comment-tables">
											</table>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-chevron-left"></span>&nbsp;&nbsp;返回</button>
									</div>
								</div>
							</div>
						</div>

						<div class="modal fade bs-example-modal-lg" id="myModalSix" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-lg my-modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
										<h4 class="modal-title"><span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;审核意见</h4>
									</div>
									<div class="modal-body">
										<!--用户详情展示-->
										<div id="user-messages">
											<!--用户信息展示-->
											<div id="user-boxs" class="user-info">
												<table class="table table-bordered">
													<caption class="table-title">账号基本信息</caption>
													<tr>
														<th colspan="2"><span class="font-x">*</span>账号</th>
														<td colspan="2" id="user_accounts"></td>
													</tr>
													<tr>
														<th style="width: 145px"><span class="font-x">*</span>申请人姓名</th>
														<td id="user_realNames" style="width: 319px"></td>
														<th style="width: 249px"><span class="font-x">*</span>申请人身份证</th>
														<td id="user_IDcards"></td>
													</tr>
													<tr>
														<th><span class="font-x">*</span>申请人手机号码</th>
														<td id="user_phoneNums"></td>
														<th><span class="font-x">*</span>申请人联系邮箱</th>
														<td id="user_emails"></td>
													</tr>
												</table>
											</div>
											<div class="register-box">
												<div id="user-infos" class="user page">
												</div>
												<table class="table table-bordered page" style="vertical-align: middle">
													<caption class="table-title">广东省价格和产业发展协会入会申请表</caption>
													<tr>
														<th  style="width:145px;"><span class="font-x">*</span>单位名称</th>
														<td  colspan="3" id="company_names"></td>
														<th><span class="font-x">*</span>单位性质</th>
														<td id="company_natures"></td>
													</tr>
													<tr>
														<th><span class="font-x">*</span>通讯地址</th>
														<td colspan="3" id="company_addresss"></td>
														<th><span class="font-x">*</span>邮政编码</th>
														<td id="company_zipCodes"></td>
													</tr>
													<tr>
														<th rowspan="2"><span class="font-x">*</span>法人代表</th>
														<th style="width: 100px"><span class="font-x">*</span>姓名</th>
														<th style="width: 219px"><span class="font-x">*</span>单位职务</th>
														<th style="width: 249px"><span class="font-x">*</span>办公电话</th>
														<th colspan="2"><span class="font-x">*</span>手机</th>
													</tr>
													<tr>
														<td id="legalRep_names"></td>
														<td id="legalRep_jopPositions"></td>
														<td id="legalRep_officePhoneNums"></td>
														<td colspan="2" id="legalRep_cellPhoneNums"></td>
													</tr>
													<tr>
														<th rowspan="4"><span class="font-x">*</span>申请加入
														</th>
														<th colspan="2">协会</th>
														<th colspan="3">会员单位级别</th>
													</tr>
													<tr>
														<td colspan="2" id="company_associationNames">
														</td>
														<td colspan="3" id="company_associationUnits">
														</td>
													</tr>
													<th colspan="2">分会</th>
													<th colspan="3">会员单位级别</th>
													<tr>
														<td colspan="2" id="company_chapterNames">
														</td>
														<td colspan="3" id="company_chapterUnits">
														</td>
													</tr>
												</table>
												<table class="table table-bordered page" id="intrs">
													<!--动态拼接 推荐人-->
												</table>
												<table class="table table-bordered page" id="Contacts">
													<!--动态拼接 联系人-->
												</table>
												<table class="table table-bordered page">
													<tr style="border: solid 1px #DDDDDD">
														<th style="width: 145px;">单位简介<span class="tip">（300字以内）</span></th>
														<td colspan="7">
                                        <textarea class="intrud-sty" id="company_introductions">
                                        </textarea>
														</td>
													</tr>
													<tr style="border: solid 1px #DDDDDD">
														<th style="width: 145px;">希望得到协会或者分会服务和支持的主要内容</th>
														<td colspan="7">
                                        <textarea class="intrud-sty" id="company_demands">
                                        </textarea>
													</tr>
													<tr>
														<th>附件：</th>
														<td colspan="7" style="line-height: 2;text-indent: 2em; width: 3000px;" id="filePath">
														</td>
													</tr>
												</table>
												<div><span class="font-x">备注：各分会会员是广东省价格和产业发展协会的当然会员。</span></div>
											</div>
										</div>
										<br>
										<div class="table-responsive register-box">
											<table class="table table-bordered page"  id="historic-comment-tables">
											</table>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary btn-success" data-dismiss="modal"><span class="glyphicon glyphicon-ok-circle"></span>&nbsp;&nbsp;确定</button>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
		<!-- container close -->
		<div style="height: 300px"></div>
	<jsp:include page="../../foreground/footer.jsp"></jsp:include>


	</body>
	<script src="${ctx}/dist/js/bootstrap-table.js"></script>
	<script src="${ctx}/dist/js/bootstrap-table-zh-CN.js"></script>
	<script src="${ctx}/dist/js/left_nav.js"></script>
	<script src="${ctx}/dist/controller/staging/date.js"></script>
	<script src="${ctx}/dist/controller/staging/to-personal-table.js"></script>
	<script src="${ctx}/dist/controller/staging/my-communicate.js"></script>
	<script src="${ctx}/dist/js/index/jquery.zlight.menu.1.0.min.js"></script>
	<script src="${ctx}/dist/controller/index/index.js"></script>
	<script src="${ctx}/dist/js/stickUp.min.js"></script>
	<script>
		$(function () {
			$('#zlight-nav').zlightMenu();
			generateNav(-1);
		});
	</script>
</html>
