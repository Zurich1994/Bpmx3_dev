<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html ng-app="shareRightsApp">
<head>
<title>编辑 数据功能权限分享</title>
<%@include file="/codegen/include/customForm.jsp"%>
<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/angular/angular.min.js"></script>
<script type="text/javascript"	src="${ctx}/js/hotent/platform/system/ScriptDialog.js"></script>
<script type="text/javascript"	src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormDialog.js"></script>
<script type="text/javascript"	src="${ctx}/js/angular/service/baseServices.js"></script>
<script type="text/javascript" src="${ctx}/js/angular/controller/sysShareRightsController.js"></script>
<script type="text/javascript">
	var sr = '${sysShareRights}';
	var allTypes = '${allTypes}';
</script>
</head>
<body ng-controller="shareRightsCtrl">
	<div class="panel" style="height: 100%; overflow: auto;">
		<div class="panel-top">
			<div class="tbar-title">
				<c:choose>
					<c:when test="${not empty sysShareRightsItem.id}">
						<span class="tbar-label"><span></span>编辑数据功能权限分享</span>
					</c:when>
					<c:otherwise>
						<span class="tbar-label"><span></span>添加数据功能权限分享</span>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" id="dataFormSave" ng-click="save();"><span></span>保存</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<form id="sysShareRightsForm" method="post" action="save.ht">
			<div type="custform">
				<div class="panel-detail">
					<table cellpadding="2" cellspacing="0" border="1" class="formTable">
						<tbody>
							<tr>
								<td colspan="4" class="formHead">数据功能权限分享</td>
							</tr>
							<tr>
								<td align="right" style="width: 15%;" class="formTitle"
									nowrap="nowarp">名称:</td>
								<td style="width: 35%;" class="formInput">
									<input type="text" lablename="名称" class="inputText ht-input" validate="{maxlength:50,required:true}" 
											ng-model="shareRights.name" />
								</td>
								<td align="right" style="width: 15%;" class="formTitle"
									nowrap="nowarp">共享原:</td>
								<td style="width: 35%;" class="formInput">
									<div right="shareRights.source" choose-target op-list="{no:'none,everyone,orgMgr,script'}"></div>
								</td>
							</tr>
							<tr>
								<td align="right" style="width: 15%;" class="formTitle"
									nowrap="nowarp">共享类型:</td>
								<td style="width: 35%;" class="formInput">
									<select class="ht-input" ng-model="shareRights.shareItem.type" validate="{required:true}" ng-options="o.type as o.desc for o in allTypes" ng-change="resetShareItem()">
										<option value="">请选择</option>
									</select>
								</td>
								<td align="right" style="width: 15%;" class="formTitle"
									nowrap="nowarp" >共享条目:</td>
								<td style="width: 35%;" class="formInput" > 
										<div class="ht-input" >
											<span class="owner-span hover-pointer" title="单击设置分享规则" ng-repeat="item in shareRights.shareItem.names" ng-click="setShareRule($index)">{{item}}
												<a class="flootbutton" title="移除该项" ng-click="remove($index,$event)">x</a>
											</span>
				 						</div>
				 						<span class="bt-select" ng-click="chooseShareObject()">选择</span>
								</td>
							</tr>
							<tr>
								<td align="right" style="width: 15%;" class="formTitle"
									nowrap="nowarp">是否全部:</td>
								<td style="width: 35%;" class="formInput">
										<select lablename="是否全部" controltype="select" validate="{empty:false}" class="ht-input"
											ng-model="shareRights.isAll" ng-change="shareRights.isAll==0?(shareRights.sync=0):''">
											<option value="1">是</option>
											<option value="0">否</option>
										</select>
								</td>
								<td align="right" style="width: 15%;" class="formTitle"
									nowrap="nowarp" >是否同步:</td>
								<td style="width: 35%;" class="formInput" >
										<select lablename="是否同步" controltype="select" validate="{empty:false}"  class="ht-input"
											ng-model="shareRights.sync" title="在选择【全部共享】的时候，才会出现同步的选项"  ng-if="shareRights.isAll==1">
											<option value="1" >是</option>
											<option value="0">否</option>
										</select>
										<select lablename="是否同步" controltype="select" validate="{empty:false}"  class="ht-input"
											ng-model="shareRights.sync" title="在选择【全部共享】的时候，才会出现同步的选项"  ng-if="shareRights.isAll==0">
											<option value="0">否</option>
										</select>
								</td>
											
							</tr>
							<tr>
								<td align="right" style="width: 15%;" class="formTitle"
									nowrap="nowarp">共享目标:</td>
								<td style="width: 35%;" class="formInput">
									<div right="shareRights.target" choose-target op-list="{no:'none'}"></div>
								</td>
								<td align="right" style="width: 15%;" class="formTitle"
									nowrap="nowarp">是否启用:</td>
								<td style="width: 35%;" class="formInput">
										<select lablename="是否启用" controltype="select" validate="{empty:false}"  class="ht-input"
												ng-model="shareRights.enable">
												<option value="1">是</option>
												<option value="0">否</option>
										</select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
