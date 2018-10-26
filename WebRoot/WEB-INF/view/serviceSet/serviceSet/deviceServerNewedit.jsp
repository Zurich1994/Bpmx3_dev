<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 服务器配置表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#deviceServerForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#deviceServerForm').submit();
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty deviceServerItem.id}">
			        <span class="tbar-label"><span></span>编辑服务器配置表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加服务器配置表</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="${ctx}/devicein/deviceinpack/deviceInfomation/newedit.ht?type=server"><span></span>下一步</a></div>
				<div class="l-bar-separator"></div>

			</div>
		</div>
	</div>
	<form id="deviceServerForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table cellpadding="2" cellspacing="0" border="1" class="formTable">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">服务器配置表</td>
  </tr>
  <!--<tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">设备编号:</td>
   <td class="formInput" style="width:80%;"><input lablename="设备编号" class="dicComboTree" nodekey="sbbhqz" validate="{empty:false,required:true}" name="dev_ID" height="200" width="125" value="${deviceServer.dev_ID}" /></td>
  </tr>
  --><tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">服务器类型:</td>
   <td class="formInput" style="width:80%;"><input lablename="服务器类型" class="dicComboTree" nodekey="fwqlx" validate="{empty:false,required:true}" name="server_type" height="200" width="125" value="${deviceServer.server_type}" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">操作系统:</td>
   <td class="formInput" style="width:80%;"><input lablename="操作系统" class="dicComboTree" nodekey="czxt" validate="{empty:false,required:true}" name="os_name" height="200" width="125" value="${deviceServer.os_name}" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">管理IP:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="manage_IP" lablename="管理IP" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" value="${deviceServer.manage_IP}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">管理IP网卡子网掩码:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="manage_NIC_subnetMas" lablename="管理IP网卡子网掩码" class="inputText" validate="{maxlength:50}" isflag="tableflag" value="${deviceServer.manage_NIC_subnetMas}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">管理IP网卡默认网关:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="manage_NIC_Default_g" lablename="管理IP网卡默认网关" class="inputText" validate="{maxlength:50}" isflag="tableflag" value="${deviceServer.manage_NIC_Default_g}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">管理IP网卡MAC地址:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="manage_NIC_MAC_addre" lablename="管理IP网卡MAC地址" class="inputText" validate="{maxlength:50}" isflag="tableflag" value="${deviceServer.manage_NIC_MAC_addre}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">业务IP:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="bussiness_IP" lablename="业务IP" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" value="${deviceServer.bussiness_IP}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">业务IP网卡子网掩码:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="bussiness_NIC_subnet" lablename="业务IP网卡子网掩码" class="inputText" validate="{maxlength:50}" isflag="tableflag" value="${deviceServer.bussiness_NIC_subnet}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">业务IP网卡默认网关:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="bussiness_NIC_Defaul" lablename="业务IP网卡默认网关" class="inputText" validate="{maxlength:50}" isflag="tableflag" value="${deviceServer.bussiness_NIC_Defaul}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">业务IP网卡MAC地址:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="bussiness_NIC_MAC_ad" lablename="业务IP网卡MAC地址" class="inputText" validate="{maxlength:50}" isflag="tableflag" value="${deviceServer.bussiness_NIC_MAC_ad}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">业务类型:</td>
   <td class="formInput" style="width:80%;"><input lablename="业务类型" class="dicComboTree" nodekey="ywlx" validate="{empty:false}" name="type" height="200" width="125" value="${deviceServer.type}" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">城市编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="city_id" lablename="城市编号" class="inputText" validate="{maxlength:20,required:true}" isflag="tableflag" value="${deviceServer.city_id}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">功能名称:</td>
   <td class="formInput" style="width:80%;"><input lablename="功能名称" class="dicComboTree" nodekey="glmc" validate="{empty:false,required:true}" name="function_name" height="200" width="125" value="${deviceServer.function_name}" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">备注:</td>
   <td class="formInput" style="width:80%;"><textarea name="remark" validate="{empty:false}">${deviceServer.remark}</textarea></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">流程定义ID:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="actdefid" lablename="流程定义ID" class="inputText" validate="{maxlength:64}" isflag="tableflag" value="${deviceServer.actdefid}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">流程节点ID:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="nodeid" lablename="流程节点ID" class="inputText" validate="{maxlength:64}" isflag="tableflag" value="${deviceServer.nodeid}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${deviceServer.id}"/>
	</form>
</body>
</html>
