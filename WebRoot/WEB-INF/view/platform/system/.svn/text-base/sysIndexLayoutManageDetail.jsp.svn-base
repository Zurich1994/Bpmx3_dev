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
		<th width="20%">布局名称:</th>
		<td>${sysIndexLayoutManage.name}</td>
	</tr>
	
	<tr>
		<th width="20%">布局描述:</th>
		<td>${sysIndexLayoutManage.memo}</td>
	</tr>
	
	<tr>
		<th width="20%">组织ID:</th>
		<td>${sysIndexLayoutManage.orgId}</td>
	</tr>
	
	<tr>
		<th width="20%">模版内容:</th>
		<td>${sysIndexLayoutManage.templateHtml}</td>
	</tr>
	
	<tr>
		<th width="20%">设计模版:</th>
		<td>${sysIndexLayoutManage.designHtml}</td>
	</tr>
	
	<tr>
		<th width="20%">是否是默认:</th>
		<td>${sysIndexLayoutManage.isDef}</td>
	</tr>
</table>
