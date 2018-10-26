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
		<td><input type="text" id="name" name="name" value="${sysDataSourceDef.name}" validate="{required:false,maxlength:64}" class="inputText"/></td>
	</tr>
	<tr>
		<th width="20%">类路径: </th>
		<td><input type="text" id="classPath" name="classPath" value="${sysDataSourceDef.classPath}" validate="{required:false,maxlength:128}" class="inputText"/></td>
	</tr>
	<tr>
		<th width="20%">设置字段JSON: </th>
		<td><input type="text" id="settingJson" name="settingJson" value="${sysDataSourceDef.settingJson}" validate="{required:false}" class="inputText"/></td>
	</tr>
	<tr>
		<th width="20%">初始化方法，可为空: </th>
		<td><input type="text" id="initMethod" name="initMethod" value="${sysDataSourceDef.initMethod}" validate="{required:false,maxlength:64}" class="inputText"/></td>
	</tr>
	<tr>
		<th width="20%">是否是系统内部，是的话生成了的数据源在服务器开启时就自动加载到服务器: </th>
		<td><input type="text" id="isSystem" name="isSystem" value="${sysDataSourceDef.isSystem}" validate="{required:false,number:true }" class="inputText"/></td>
	</tr>
	<tr>
		<th width="20%">在关闭数据源还需要调用的方法，可为空: </th>
		<td><input type="text" id="closeMethod" name="closeMethod" value="${sysDataSourceDef.closeMethod}" validate="{required:false,maxlength:64}" class="inputText"/></td>
	</tr>
</table>
<input type="hidden" name="id" value="${sysDataSourceDef.id}" />