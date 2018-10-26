<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>

<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${ctx}/js/hotent/scriptMgr.js"></script>
<script type="text/javascript">
	function afterOnload(){
		var afterLoadJs=[
		     			'${ctx}/js/hotent/formdata.js',
		     			'${ctx}/js/hotent/subform.js'
		 ];
		ScriptMgr.load({
			scripts : afterLoadJs
		});
	}
</script>

<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
	<tr>
		<th width="20%">名称: </th>
		<td><input type="text" id="name" name="name" value="${sysDataSourceL.name}" validate="{required:false,maxlength:64}" class="inputText"/></td>
	</tr>
	<tr>
		<th width="20%">别名: </th>
		<td><input type="text" id="alias" name="alias" value="${sysDataSourceL.alias}" validate="{required:false,maxlength:64}" class="inputText"/></td>
	</tr>
	<tr>
		<th width="20%">数据源的类型-mysql,oracle,sqlserver: </th>
		<td><input type="text" id="dbType" name="dbType" value="${sysDataSourceL.dbType}" validate="{required:false,maxlength:64}" class="inputText"/></td>
	</tr>
	<tr>
		<th width="20%">设置字段: </th>
		<td><input type="text" id="settingJson" name="settingJson" value="${sysDataSourceL.settingJson}" validate="{required:false}" class="inputText"/></td>
	</tr>
	<tr>
		<th width="20%">开始服务器时启动: </th>
		<td><input type="text" id="initOnStart" name="initOnStart" value="${sysDataSourceL.initOnStart}" validate="{required:false,number:true }" class="inputText"/></td>
	</tr>
	<tr>
		<th width="20%">是否可用: </th>
		<td><input type="text" id="enabled" name="enabled" value="${sysDataSourceL.enabled}" validate="{required:false,number:true }" class="inputText"/></td>
	</tr>
	<tr>
		<th width="20%">类路径: </th>
		<td><input type="text" id="classPath" name="classPath" value="${sysDataSourceL.classPath}" validate="{required:false,maxlength:128}" class="inputText"/></td>
	</tr>
	<tr>
		<th width="20%">初始化方法: </th>
		<td><input type="text" id="initMethod" name="initMethod" value="${sysDataSourceL.initMethod}" validate="{required:false,maxlength:128}" class="inputText"/></td>
	</tr>
	<tr>
		<th width="20%">关闭方法: </th>
		<td><input type="text" id="closeMethod" name="closeMethod" value="${sysDataSourceL.closeMethod}" validate="{required:false,maxlength:128}" class="inputText"/></td>
	</tr>
</table>
<input type="hidden" name="id" value="${sysDataSourceL.id}" />