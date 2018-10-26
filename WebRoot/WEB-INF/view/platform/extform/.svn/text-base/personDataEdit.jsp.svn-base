<%--
	time:2013-01-10 09:44:06
	desc:edit the PERSON_DATA
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>

<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
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
			<th width="20%">名称:  <span class="required">*</span></th>
			<td><input type="text" id="username" name="username" value="${personData.username}" 
				class="inputText" validate="{required:true,maxlength:127}"  /></td>
		</tr>
		<tr>
			<th width="20%">身份证:  <span class="required">*</span></th>
			<td><input type="text" id="idcard" name="idcard" value="${personData.idcard}" 
				class="inputText" validate="{required:true,maxlength:40}"  /></td>
		</tr>
	</table>
	<input type="hidden" name="id" value="${personData.id}" />