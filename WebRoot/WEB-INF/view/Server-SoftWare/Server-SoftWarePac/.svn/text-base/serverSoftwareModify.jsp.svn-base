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
   <td colspan="2" class="formHead">服务器-软件表</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">序号:</td>
   <td class="formInput" style="width:80%;"><input name="ID" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:13,maxDecimalLen:0}" type="text" value="${serverSoftware.ID}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">服务器编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="Server_ID" lablename="服务器编号" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" type="text" value="${serverSoftware.Server_ID}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">软件编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="SoftWare_ID" lablename="软件编号" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" type="text" value="${serverSoftware.SoftWare_ID}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">软件部署路径:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="Software_Path" lablename="软件部署路径" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="${serverSoftware.Software_Path}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">软件监听端口:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="Software_Port" lablename="软件监听端口" class="inputText" validate="{maxlength:20}" isflag="tableflag" type="text" value="${serverSoftware.Software_Port}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">软件登陆方式:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="Login_Type" lablename="软件登陆方式" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${serverSoftware.Login_Type}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">数据库名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="DataBase" lablename="数据库名" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${serverSoftware.DataBase}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">软件登陆用户名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="Login_UserName" lablename="软件登陆用户名" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${serverSoftware.Login_UserName}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">软件登陆密码:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="Login_Password" lablename="软件登陆密码" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${serverSoftware.Login_Password}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">备注:</td>
   <td class="formInput" style="width:80%;"><textarea name="Remark" validate="{empty:false}">${serverSoftware.Remark}</textarea></td>
  </tr>
 </tbody>
</table>
