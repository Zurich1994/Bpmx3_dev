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
   <td colspan="2" class="formHead">用户ID</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">用户名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="USERID" lablename="用户名" class="inputText" validate="{maxlength:10}" isflag="tableflag" type="text" value="${quickpay.USERID}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">收款人:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="PAYEEID" lablename="收款人" class="inputText" validate="{maxlength:20,required:true}" isflag="tableflag" type="text" value="${quickpay.PAYEEID}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">日期:</td>
   <td class="formInput" style="width:80%;"><input name="DATA" class="Wdate" displaydate="0" validate="{empty:false,required:true}" type="text" value="<fmt:formatDate value='${quickpay.DATA}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">付款金额:</td>
   <td class="formInput" style="width:80%;"><input name="PAYMENT" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" type="text" value="${quickpay.PAYMENT}" /></td>
  </tr>
 </tbody>
</table>
