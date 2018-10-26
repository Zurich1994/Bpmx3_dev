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
<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
	
	<tr>
		<th width="20%">名称:</th>
		<td>${sysDataSourceL.name}</td>
	</tr>
	
	<tr>
		<th width="20%">别名:</th>
		<td>${sysDataSourceL.alias}</td>
	</tr>
	
	<tr>
		<th width="20%">数据源的类型-mysql,oracle,sqlserver:</th>
		<td>${sysDataSourceL.dbType}</td>
	</tr>
	
	<tr>
		<th width="20%">设置字段:</th>
		<td>${sysDataSourceL.settingJson}</td>
	</tr>
	
	<tr>
		<th width="20%">开始服务器时启动:</th>
		<td>${sysDataSourceL.initOnStart}</td>
	</tr>
	
	<tr>
		<th width="20%">是否可用:</th>
		<td>${sysDataSourceL.enabled}</td>
	</tr>
	
	<tr>
		<th width="20%">类路径:</th>
		<td>${sysDataSourceL.classPath}</td>
	</tr>
	
	<tr>
		<th width="20%">初始化方法:</th>
		<td>${sysDataSourceL.initMethod}</td>
	</tr>
	
	<tr>
		<th width="20%">关闭方法:</th>
		<td>${sysDataSourceL.closeMethod}</td>
	</tr>
</table>
