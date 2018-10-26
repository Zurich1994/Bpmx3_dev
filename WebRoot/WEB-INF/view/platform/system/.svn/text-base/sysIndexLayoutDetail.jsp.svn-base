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
		<td>${sysIndexLayout.name}</td>
	</tr>
	
	<tr>
		<th width="20%">布局描述:</th>
		<td>${sysIndexLayout.memo}</td>
	</tr>
	
	<tr>
		<th width="20%">模版内容:</th>
		<td>${sysIndexLayout.templateHtml}</td>
	</tr>
	
	<tr>
		<th width="20%">排序:</th>
		<td>${sysIndexLayout.sn}</td>
	</tr>
</table>
