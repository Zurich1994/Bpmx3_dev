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
<table class="formTable" border="1" cellspacing="0" cellpadding="2">
 <tbody>
  <tr>
   <td class="formHead" colspan="6">newjsprelation</td>
  </tr>
  <tr style="height:23px;">
   <td class="formTitle" nowrap="nowarp" style="width:13%;">FORMDEFID:</td>
   <td class="formInput" style="width:20%;">${newjsprelation.FORMDEFID}</td>
   <td class="formTitle" nowrap="nowarp" style="width:13%;">nameandvalue:</td>
   <td class="formInput" style="width:20%;"><textarea name="nameandvalue" validate="{empty:false}" readonly="readonly">${newjsprelation.nameandvalue}</textarea></td>
   <td class="formTitle" nowrap="nowarp" style="width:13%;">name:</td>
   <td class="formInput" style="width:20%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag">${newjsprelation.name}</span></td>
  </tr>
 </tbody>
</table>
