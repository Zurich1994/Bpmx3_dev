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
   <td colspan="4" class="formHead">支付者信息表</td>
  </tr>
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">用户名:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${payee.USERID}</span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">别名:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${payee.PAYEEID}</span></td>
  </tr>
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">名字:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${payee.NAME}</span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">街道:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${payee.STREET}</span></td>
  </tr>
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">城市:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${payee.CITY}</span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">州:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${payee.STATE}</span></td>
  </tr>
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">区号:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${payee.ZIP}</span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">电话:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${payee.PHONE}</span></td>
  </tr>
 </tbody>
</table>
