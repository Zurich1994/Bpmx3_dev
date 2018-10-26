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
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag">${deviceInfomation.dev_Name}</span></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">机房编号:</td>
   <td class="formInput" style="width:80%">${deviceInfomation.room_ID}</td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">机柜编号:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag">${deviceInfomation.cabinet_num}</span></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备归属:</td>
   <td class="formInput" style="width:80%">${deviceInfomation.sbgs}</td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备类型:</td>
   <td class="formInput" style="width:80%">${deviceInfomation.dev_type}</td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备品牌:</td>
   <td class="formInput" style="width:80%">${deviceInfomation.dev_brand}</td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备型号:</td>
   <td class="formInput" style="width:80%">${deviceInfomation.dev_model}</td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备序列号:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag">${deviceInfomation.dev_sequence}</span></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备配置:</td>
   <td class="formInput" style="width:80%"><textarea name="dev_config" validate="{empty:false}" readonly="readonly">${deviceInfomation.dev_config}</textarea></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">功能描述:</td>
   <td class="formInput" style="width:80%"><textarea name="fun_Info" validate="{empty:false}" readonly="readonly">${deviceInfomation.fun_Info}</textarea></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备使用状态:</td>
   <td class="formInput" style="width:80%">${deviceInfomation.state}</td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备出厂日期:</td>
   <td class="formInput" style="width:80%"><fmt:formatDate value='${deviceInfomation.exFactory_Date}' pattern='yyyy-MM-dd'/></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备投入使用日期:</td>
   <td class="formInput" style="width:80%"><fmt:formatDate value='${deviceInfomation.using_Date}' pattern='yyyy-MM-dd'/></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备报废日期:</td>
   <td class="formInput" style="width:80%"><fmt:formatDate value='${deviceInfomation.retirement_Date}' pattern='yyyy-MM-dd'/></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">使用人:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag">${deviceInfomation.responsible_Person}</span></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">使用人联系方式:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag">${deviceInfomation.contact}</span></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">项目名称:</td>
   <td class="formInput" style="width:80%">${deviceInfomation.project_Name}</td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">录入时间:</td>
   <td class="formInput" style="width:80%"><fmt:formatDate value='${deviceInfomation.insert_Time}' pattern='yyyy-MM-dd'/></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">备注:</td>
   <td class="formInput" style="width:80%"><textarea name="remark" validate="{empty:false}" readonly="readonly">${deviceInfomation.remark}</textarea></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">流程定义ID:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag">${deviceInfomation.actdefid}</span></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">流程节点ID:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag">${deviceInfomation.nodeid}</span></td>
  </tr>
 </tbody>
</table>
