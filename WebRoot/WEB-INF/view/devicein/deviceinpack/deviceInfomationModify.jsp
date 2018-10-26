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
   <td class="formHead" colspan="2">设备基本信息表</td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备名称:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="dev_Name" validate="{maxlength:50,required:true}" lablename="设备名称" isflag="tableflag" value="${deviceInfomation.dev_Name}" /></span></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">机房编号:</td>
   <td class="formInput" style="width:80%"><input class="dicComboTree" height="200" width="125" name="room_ID" validate="{empty:false,required:true}" nodekey="jfbh" lablename="机房编号" value="${deviceInfomation.room_ID}" /></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">机柜编号:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="cabinet_num" validate="{maxlength:50}" lablename="机柜编号" isflag="tableflag" value="${deviceInfomation.cabinet_num}" /></span></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备归属:</td>
   <td class="formInput" style="width:80%"><input class="dicComboTree" height="200" width="125" name="sbgs" validate="{empty:false}" nodekey="sbgs" lablename="设备归属" value="${deviceInfomation.sbgs}" /></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备类型:</td>
   <td class="formInput" style="width:80%"><input class="dicComboTree" height="200" width="125" name="dev_type" validate="{empty:false}" nodekey="shebeileixing" lablename="设备类型" value="${deviceInfomation.dev_type}" /></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备品牌:</td>
   <td class="formInput" style="width:80%"><input class="dicComboTree" height="200" width="125" name="dev_brand" validate="{empty:false,required:true}" nodekey="sbpp" lablename="设备品牌" value="${deviceInfomation.dev_brand}" /></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备型号:</td>
   <td class="formInput" style="width:80%"><input class="dicComboTree" height="200" width="125" name="dev_model" validate="{empty:false,required:true}" nodekey="sbxh" lablename="设备型号" value="${deviceInfomation.dev_model}" /></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备序列号:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="dev_sequence" validate="{maxlength:50}" lablename="设备序列号" isflag="tableflag" value="${deviceInfomation.dev_sequence}" /></span></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备配置:</td>
   <td class="formInput" style="width:80%"><textarea name="dev_config" validate="{empty:false}">${deviceInfomation.dev_config}</textarea></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">功能描述:</td>
   <td class="formInput" style="width:80%"><textarea name="fun_Info" validate="{empty:false}">${deviceInfomation.fun_Info}</textarea></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备使用状态:</td>
   <td class="formInput" style="width:80%"><input class="dicComboTree" height="200" width="125" name="state" validate="{empty:false,required:true}" nodekey="sbsyzt" lablename="设备使用状态" value="${deviceInfomation.state}" /></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备出厂日期:</td>
   <td class="formInput" style="width:80%"><input class="Wdate" name="exFactory_Date" validate="{empty:false,required:true}" displaydate="0" value="<fmt:formatDate value='${deviceInfomation.exFactory_Date}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备投入使用日期:</td>
   <td class="formInput" style="width:80%"><input class="Wdate" name="using_Date" validate="{empty:false,required:true}" displaydate="0" value="<fmt:formatDate value='${deviceInfomation.using_Date}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备报废日期:</td>
   <td class="formInput" style="width:80%"><input class="Wdate" name="retirement_Date" validate="{empty:false,required:true}" displaydate="0" value="<fmt:formatDate value='${deviceInfomation.retirement_Date}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">使用人:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="responsible_Person" validate="{maxlength:50}" lablename="使用人" isflag="tableflag" value="${deviceInfomation.responsible_Person}" /></span></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">使用人联系方式:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="contact" validate="{maxlength:50}" lablename="使用人联系方式" isflag="tableflag" value="${deviceInfomation.contact}" /></span></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">项目名称:</td>
   <td class="formInput" style="width:80%"><input class="dicComboTree" height="200" width="125" name="project_Name" validate="{empty:false}" nodekey="xmmc" lablename="项目名称" value="${deviceInfomation.project_Name}" /></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">录入时间:</td>
   <td class="formInput" style="width:80%"><input class="Wdate" name="insert_Time" validate="{empty:false,required:true}" displaydate="0" value="<fmt:formatDate value='${deviceInfomation.insert_Time}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">备注:</td>
   <td class="formInput" style="width:80%"><textarea name="remark" validate="{empty:false}">${deviceInfomation.remark}</textarea></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">流程定义ID:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="actdefid" validate="{maxlength:64}" lablename="流程定义ID" isflag="tableflag" value="${deviceInfomation.actdefid}" /></span></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">流程节点ID:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="nodeid" validate="{maxlength:64}" lablename="流程节点ID" isflag="tableflag" value="${deviceInfomation.nodeid}" /></span></td>
  </tr>
 </tbody>
</table>
