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
<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">参数依赖</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">依赖方式:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><c:if test='${paramRely.relyMethod==流程依赖}'>流程依赖</c:if><c:if test='${paramRely.relyMethod==固定指标参数依赖}'>固定指标参数依赖</c:if></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">依赖Key:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">依赖参数:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${paramRely.relyValue}</span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">脚本:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><textarea name="script" lablename="脚本" class="l-textarea" rows="5" cols="40" validate="{maxlength:2000}" isflag="tableflag" readonly="readonly">${paramRely.script}</textarea></span></td>
  </tr>
 </tbody>
</table>
