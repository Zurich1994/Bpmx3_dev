<%--
	time:2014-08-20 11:10:07
	desc:edit the SYS_DATA_SOURCE_DEF
	tip:这个页面用了ngjs by liyj
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑数据源模板</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/angular.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/service/baseServices.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/service/arrayToolService.js"></script>
	<script type="text/javascript" src="${ctx}/js/angular/service/commonListService.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/sysDataSourceDef/SysDataSourceDefService.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/sysDataSourceDef/EditController.js"></script>
	<style type="text/css">
		.inputText{
			width:300px;
		}
	</style>
	<script type="text/javascript">
		var id="${param.id}";
	</script>
</head>
<body ng-app="app" ng-controller="EditController">
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${sysDataSourceDef.id !=null}">
			        <span class="tbar-label">编辑数据源模板</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label">编辑数据源模板</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" ng-click="save(prop)"><span></span>保存</a></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
			<tr>
				<th width="20%">名称: </th>
				<td><input type="text" ng-model="prop.name" validate="{required:true,maxlength:64}"  class="inputText"/></td>
			</tr>
			<tr>
				<th width="20%">初始化方法:  </th>
				<td><input type="text" ng-model="prop.initMethod" validate="{required:false,maxlength:64}"  class="inputText"/></td>
			</tr>
			<tr>
				<th width="20%">是否是系统默认: </th>
				<td>
					<select ng-model="prop.isSystem" ng-options="m.value as m.key for m in CommonList.yesOrNoList">
					</select>
				</td>
			</tr>
			<tr>
				<th width="20%">关闭方法: </th>
				<td>
					<input type="text" ng-model="prop.closeMethod" validate="{required:false,maxlength:128}"  class="inputText"/>
					<!-- (格式：classPath|method--》eg:org.logicalcobwebs.proxool.ProxoolFacade|shutDown) -->
					<div class="tipbox"><a href="javascript:;" class="tipinfo">
						<span>格式：classPath|method<br>例：org.logicalcobwebs.proxool.ProxoolFacade|shutDown</span>
					</a></div>
				</td>
			</tr>
			<tr>
				<th width="20%">类路径: </th>
				<td>
					<input type="text" ng-model="prop.classPath" validate="{required:false,maxlength:64}"  class="inputText"/>
					<input ng-if="prop.id==null" type="button" value="获取属性" ng-click="getAttr(prop.classPath);"/>
				</td>
			</tr>
			
			<!-- 字段信息 -->
			<table ng-if="prop.settingJson!=null" class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main" style="width: 100%; ">
				<tr>
					<th colspan=5><p align="center">字段属性: </p></th>
				</tr>
				<tr>
					<th>名称</td>
					<th>描述</td>
					<th>参数类型</td>
					<th>是否必填</td>
					<c:if test="${param.id==null}"><th>管理</th></c:if><!-- 新建的模板才能改变字段的东西 -->
				</tr>
				<tr ng-repeat="field in prop.settingJson">
					<th>{{field.name}}</th>
					<td><input type="text" value="{{field.comment}}" ng-model="field.comment" class="inputText" style="width: 90%;"/></td>
					<th>{{field.type}}</th>
					<td>
						<select ng-model="field.baseAttr" ng-options="m.value as m.key for m in CommonList.yesOrNoList">
						</select>
					</td>
					<c:if test="${param.id==null}"><td>
						<a class="link moveup" href="javascript:;" ng-click="ArrayTool.up($index,prop.settingJson)" title="上移"></a>
						<a class="link movedown" href="javascript:;" ng-click="ArrayTool.down($index,prop.settingJson)" title="下移"></a>
						<a class="link del" href="javascript:;" ng-click="ArrayTool.del($index,prop.settingJson)" title="删除"></a>
					</td></c:if>
				</tr>
			</table>
			
			
		</table>
		<input type="hidden" name="id" value="${sysDataSourceDef.id}" />					
	</div>
</div>
</body>
</html>
