<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hotent.switchSet.dao.switchSet.DeviceSwitchDao"%>
<html>
<head>
	<title>编辑 交换机配置表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#deviceSwitchForm').form();
			$("a.save").click(function() {
			location.href="${ctx}/deviceNodeSet/deviceNodeSet/deviceNodeSet/saveDeviceNode.ht?selecteIP=${selecteIP}&actdefid=${deviceSwitch.actdefid}&nodeid=${deviceSwitch.nodeid}&deviceTable=w_device_switch";
		    $.ligerDialog.success("保存成功！");
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.success("保存成功！");
			} else {
				$.ligerDialog.error(obj.getMessage());
			}
		}
		function surfto() {  

          var  myselect=document.getElementById("select");
          var index=myselect.selectedIndex; 
          var value=myselect.options[index].value;
		
           location.href="${ctx}/switchSet/switchSet/deviceSwitch/refresh.ht?value="+value+"&actdefid=${deviceSwitch.actdefid}&nodeid=${deviceSwitch.nodeid}";
            
} 
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty deviceSwitchItem.id}">
			        <span class="tbar-label"><span></span>编辑交换机配置表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加交换机配置表</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		

		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>	
				<div class="group" ><a class="link add"   href="newedit.ht?actdefid=${deviceSwitch.actdefid}&nodeid=${deviceSwitch.nodeid}"><span ></span>添加新设备</a></div>
			</div>
		</div>
	</div>
	<form id="deviceSwitchForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table cellpadding="2" cellspacing="0" border="1" class="formTable">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">交换机配置表</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">管理IP:</td>
  <td align="left"  style="width:80%;"><select id="select" 
   <%Object str = request.getParameter("selectIsDisabled");
   if(str!=null){%>
   disabled="disabled"
   <%}%>
   onchange="surfto()"><option value=0>
   <%if(request.getAttribute("selecteIP")!=null){%>
   <%=request.getAttribute("selecteIP")%>
   <%}else {%>
   <%="--请选择--" %>
   <%}%></option>
  <%
   DeviceSwitchDao dao=new DeviceSwitchDao();
   List<String> list=dao.getAllIP();
   int realNum = (list.size()-1)/2;
   for(int i=1;i<=realNum;i++)
  {
   %>
   <option value=<%=list.get(i+realNum)%>><%=list.get(i)%></option>
    <%}%>
    </select> </td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">网卡子网掩码:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="manage_NIC_subnetMas" lablename="网卡子网掩码" readonly="true" class="inputText" validate="{maxlength:50}" isflag="tableflag" value="${deviceSwitch.manage_NIC_subnetMas}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">网卡默认网关:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="manage_NIC_Default_g" lablename="网卡默认网关" readonly="true" class="inputText" validate="{maxlength:50}" isflag="tableflag" value="${deviceSwitch.manage_NIC_Default_g}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">网卡MAC地址:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="manage_NIC_MAC_addre" lablename="网卡MAC地址" readonly="true" class="inputText" validate="{maxlength:50}" isflag="tableflag" value="${deviceSwitch.manage_NIC_MAC_addre}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">设备编号:</td>
   <td class="formInput" style="width:80%;"><input lablename="设备编号"  validate="{empty:false,required:true}" name="dev_ID" height="200" width="125" readonly="true" value="${deviceSwitch.dev_ID}" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">配置信息:</td>
   <td class="formInput" style="width:80%;"><textarea name="config_info" readonly="true" validate="{empty:false}">${deviceSwitch.config_info}</textarea></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">备注:</td>
   <td class="formInput" style="width:80%;"><textarea name="remark" readonly="true" validate="{empty:false}">${deviceSwitch.remark}</textarea></td>
  </tr>
  <!--<tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">流程定义ID:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" readonly="true" name="actdefid" lablename="流程定义ID" class="inputText" validate="{maxlength:64}" isflag="tableflag" value="${deviceSwitch.actdefid}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">流程节点ID:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" readonly="true" name="nodeid" lablename="流程节点ID" class="inputText" validate="{maxlength:64}" isflag="tableflag" value="${deviceSwitch.nodeid}" /></span></td>
  </tr>
 --></tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${deviceSwitch.id}"/>
	</form>
</body>
</html>
