<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 网络配置表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#nicForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#nicForm').submit();
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						this.close();
					}else{
						window.location.href = "${ctx}/NIC/NICPac/nic/list.ht";
					}
				});
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
			    <c:when test="${not empty nicItem.id}">
			        <span class="tbar-label"><span></span>编辑网络配置表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加网络配置表</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<form id="nicForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">网络配置表</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">序号:</td>
   <td class="formInput" style="width:80%;"><input name="ID" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:13,maxDecimalLen:0}" type="text" value="${nic.ID}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">网卡编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="NIC_ID" lablename="网卡编号" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" type="text" value="${nic.NIC_ID}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">设备编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="Server_ID" lablename="设备编号" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" type="text" value="${nic.Server_ID}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">网卡IP:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="NIC_IP" lablename="网卡IP" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="${nic.NIC_IP}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">网卡子网掩码:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="NIC_subnetMask" lablename="网卡子网掩码" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${nic.NIC_subnetMask}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">网卡默认网关:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="NIC_Default_gateway" lablename="网卡默认网关" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${nic.NIC_Default_gateway}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">网卡MAC地址:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="NIC_MAC_address" lablename="网卡MAC地址" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${nic.NIC_MAC_address}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">网卡序号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="NIC_Net_sequence" lablename="网卡序号" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" type="text" value="${nic.NIC_Net_sequence}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">备注:</td>
   <td class="formInput" style="width:80%;"><textarea name="Remark" validate="{empty:false}">${nic.Remark}</textarea></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${nic.id}"/>
	</form>
</body>
</html>
