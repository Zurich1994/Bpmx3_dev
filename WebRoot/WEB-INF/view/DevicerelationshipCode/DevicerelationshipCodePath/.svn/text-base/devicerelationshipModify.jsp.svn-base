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
<table class="formTable" cellspacing="0" cellpadding="2" border="1">
 <tbody>
  <tr>
   <td class="formHead" colspan="2">线路表</td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备编号:</td>
   <td class="formInput" style="width:80%"><input class="dicComboTree" height="200" width="125" name="dev_ID" validate="{empty:false,required:true}" nodekey="sbbhqz" lablename="设备编号" value="${devicerelationship.dev_ID}" /></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备端口:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="dev_Port" validate="{maxlength:50,required:true}" lablename="设备端口" isflag="tableflag" value="${devicerelationship.dev_Port}" /></span></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">对端设备编号:</td>
   <td class="formInput" style="width:80%"><input class="dicComboTree" height="200" width="125" name="opp_ID" validate="{empty:false,required:true}" nodekey="sbbhqz" lablename="对端设备编号" value="${devicerelationship.opp_ID}" /></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">对端设备端口类型:</td>
   <td class="formInput" style="width:80%"><input class="dicComboTree" height="200" width="125" name="opp_PortType" validate="{empty:false}" nodekey="ddsbdklx" lablename="对端设备端口类型" value="${devicerelationship.opp_PortType}" /></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">对端设备端口:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="opp_Port" validate="{maxlength:50,required:true}" lablename="对端设备端口" isflag="tableflag" value="${devicerelationship.opp_Port}" /></span></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">备注:</td>
   <td class="formInput" style="width:80%"><textarea name="remark" validate="{empty:false}">${devicerelationship.remark}</textarea></td>
  </tr>
 </tbody>
</table>
