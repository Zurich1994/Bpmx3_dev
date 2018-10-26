<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 线路表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#devicerelationshipForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#devicerelationshipForm').submit();
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
						window.location.href = "${ctx}/DevicerelationshipCode/DevicerelationshipCodePath/devicerelationship/list.ht";
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
			    <c:when test="${not empty devicerelationshipItem.id}">
			        <span class="tbar-label"><span></span>编辑线路表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加线路表</span>
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
	<form id="devicerelationshipForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
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
			</div>
		</div>
		<input type="hidden" name="id" value="${devicerelationship.id}"/>
	</form>
</body>
</html>
