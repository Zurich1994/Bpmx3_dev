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
   <td colspan="4" class="formHead">更新账户信息</td>
  </tr>
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">用户名，由英文及数字组合而成:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="USERID" lablename="用户名，由英文及数字组合而成" class="inputText" validate="{maxlength:10,required:true}" isflag="tableflag" type="text" value="${update.USERID}" /></span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">密码:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="PASSWORD" lablename="密码" class="inputText" validate="{maxlength:32,required:true}" isflag="tableflag" type="text" value="${update.PASSWORD}" /></span></td>
  </tr>
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">地址:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="ADDRESS" lablename="地址" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="${update.ADDRESS}" /></span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">城市:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="CITY" lablename="城市" class="inputText" validate="{maxlength:20,required:true}" isflag="tableflag" type="text" value="${update.CITY}" /></span></td>
  </tr>
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">州,2位州代码:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="STATE" lablename="州,2位州代码" class="inputText" validate="{maxlength:2,required:true}" isflag="tableflag" type="text" value="${update.STATE}" /></span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">区号:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="ZIP" lablename="区号" class="inputText" validate="{maxlength:5,required:true}" isflag="tableflag" type="text" value="${update.ZIP}" /></span></td>
  </tr>
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">电话:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="PHONE" lablename="电话" class="inputText" validate="{maxlength:20,required:true}" isflag="tableflag" type="text" value="${update.PHONE}" /></span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">邮件地址:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="EMAIL" lablename="邮件地址" class="inputText" validate="{maxlength:30,required:true}" isflag="tableflag" type="text" value="${update.EMAIL}" /></span></td>
  </tr>
 </tbody>
</table>
