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
   <td colspan="6" class="formHead">用户ID</td>
  </tr>
  <tr style="height:23px;">
   <td style="width:13%;" class="formTitle" nowrap="nowarp">用户名:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${billPay.USERID}</span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">收款人:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${billPay.PAYEEID}</span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">日期:</td>
   <td style="width:20%;" class="formInput"><fmt:formatDate value='${billPay.DATA}' pattern='yyyy-MM-dd'/></td>
  </tr>
  <tr style="height:23px;">
   <td style="width:13%;" class="formTitle" nowrap="nowarp">付款金额:</td>
   <td style="width:20%;" class="formInput">${billPay.PAYMENT}</td>
   <td style="width:13%;" class="formTitle"></td>
   <td style="width:20%;" class="formInput"></td>
   <td style="width:13%;" class="formTitle"></td>
   <td style="width:20%;" class="formInput"></td>
  </tr>
 </tbody>
</table>
