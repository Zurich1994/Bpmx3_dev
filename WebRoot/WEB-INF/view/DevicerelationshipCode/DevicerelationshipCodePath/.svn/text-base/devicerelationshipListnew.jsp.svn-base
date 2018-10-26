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
				$.ligerDialog.success('保存成功',function(rtn) {
					if(rtn){
						this.close();
					}else{
						this.close();
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"保存失败");
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
   <td class="formInput" style="width:80%">
   <input lablename="设备编号" name="dev_ID" height="200" width="125"  readonly="readonly" value="${f_dev_id}" /></td>
  </tr>
   
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备端口:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag">
   <input name="dev_Port"  lablename="设备端口" value="${dev_Port}" readonly="readonly"/></span></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">对端设备编号:</td>
   <td class="formInput" style="width:80%">
   <input height="200" width="125" name="opp_ID" lablename="对端设备编号" value="${f_opp_id}"  readonly="readonly" /></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">对端设备端口类型:</td>
   <td class="formInput" style="width:80%">
   <input height="200" width="125" name="opp_PortType" lablename="对端设备端口类型" value="${opp_PortType}" readonly="readonly"/></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">对端设备端口:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag">
   <input name="opp_Port" lablename="对端设备端口" value="${opp_Port}" readonly="readonly"/></span></td>
  </tr>
  <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">备注:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag">
   <input name="opp_Port" lablename="备注" value="${remark}" readonly="readonly"/></span></td>
  </tr>

 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${devicerelationship.id}"/>
	</form>
</body>
</html>
