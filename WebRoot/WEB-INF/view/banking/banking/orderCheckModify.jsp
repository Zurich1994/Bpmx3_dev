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
   <td colspan="2" class="formHead">银行订单表</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">用户id:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="userid" lablename="用户id" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" type="text" value="${orderCheck.userid}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">账号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="accountno" lablename="账号" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="${orderCheck.accountno}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">总价格:</td>
   <td class="formInput" style="width:80%;"><input name="totalprice" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:13,maxDecimalLen:2,required:true}" type="text" value="${orderCheck.totalprice}" /></td>
  </tr>
 </tbody>
</table>
