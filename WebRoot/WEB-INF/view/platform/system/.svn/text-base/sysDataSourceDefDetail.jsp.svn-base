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
		<td>${sysDataSourceDef.name}</td>
	</tr>
	
	<tr>
		<th width="20%">类路径:</th>
		<td>${sysDataSourceDef.classPath}</td>
	</tr>
	
	<tr>
		<th width="20%">设置字段JSON:</th>
		<td>${sysDataSourceDef.settingJson}</td>
	</tr>
	
	<tr>
		<th width="20%">初始化方法，可为空:</th>
		<td>${sysDataSourceDef.initMethod}</td>
	</tr>
	
	<tr>
		<th width="20%">是否是系统内部，是的话生成了的数据源在服务器开启时就自动加载到服务器:</th>
		<td>${sysDataSourceDef.isSystem}</td>
	</tr>
	
	<tr>
		<th width="20%">在关闭数据源还需要调用的方法，可为空:</th>
		<td>${sysDataSourceDef.closeMethod}</td>
	</tr>
</table>
