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
<table cellpadding="2" cellspacing="0" border="1" class="formTable">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">硬件登录信息表</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">序号:</td>
   <td class="formInput" style="width:80%;"><input name="id" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:18,maxDecimalLen:0}" value="${deviceLoginInfo.id}" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">设备编号:</td>
   <td class="formInput" style="width:80%;"><input lablename="设备编号" class="dicComboTree" nodekey="sbbhqz" validate="{empty:false,required:true}" name="dev_ID" height="200" width="125" value="${deviceLoginInfo.dev_ID}" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">登录方式:</td>
   <td class="formInput" style="width:80%;"><input lablename="登录方式" class="dicComboTree" nodekey="dlfs" validate="{empty:false}" name="login_manner" height="200" width="125" value="${deviceLoginInfo.login_manner}" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">IP地址:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="ip_address" lablename="IP地址" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" value="${deviceLoginInfo.ip_address}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">端口号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="port" lablename="端口号" class="inputText" validate="{maxlength:50}" isflag="tableflag" value="${deviceLoginInfo.port}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">用户名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="username" lablename="用户名" class="inputText" validate="{maxlength:200}" isflag="tableflag" value="${deviceLoginInfo.username}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">密码:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="password" lablename="密码" class="inputText" validate="{maxlength:200}" isflag="tableflag" value="${deviceLoginInfo.password}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">备注:</td>
   <td class="formInput" style="width:80%;"><textarea name="remark" validate="{empty:false}">${deviceLoginInfo.remark}</textarea></td>
  </tr>
 </tbody>
</table>
