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
   <td style="word-break:break-all;" colspan="2" class="formHead">添加支付者信息</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">用户名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="USERID" lablename="用户名" class="inputText" validate="{maxlength:10,required:true,英数字:true}" isflag="tableflag" type="text" value="${add_payee.USERID}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">别名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="PAYEEID" lablename="别名" class="inputText" validate="{maxlength:20,required:true,英数字:true}" isflag="tableflag" type="text" value="${add_payee.PAYEEID}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">名字:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="NAME" lablename="名字" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="${add_payee.NAME}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">街道:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="STREET" lablename="街道" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="${add_payee.STREET}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">城市:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="CITY" lablename="城市" class="inputText" validate="{maxlength:20,required:true}" isflag="tableflag" type="text" value="${add_payee.CITY}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">州:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="STATE" lablename="州" class="inputText" validate="{maxlength:2,required:true}" isflag="tableflag" type="text" value="${add_payee.STATE}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">区号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="ZIP" lablename="区号" class="inputText" validate="{maxlength:5,required:true,正整数:true}" isflag="tableflag" type="text" value="${add_payee.ZIP}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">电话:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="PHONE" lablename="电话" class="inputText" validate="{maxlength:20,required:true,手机号码:true}" isflag="tableflag" type="text" value="${add_payee.PHONE}" /></span></td>
  </tr>
 </tbody>
</table>
