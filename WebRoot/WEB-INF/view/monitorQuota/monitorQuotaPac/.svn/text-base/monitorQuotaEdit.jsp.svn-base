<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 设备监控指标表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#monitorQuotaForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#monitorQuotaForm').submit();
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
						window.location.href = "${ctx}/monitorQuota/monitorQuotaPac/monitorQuota/list.ht";
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
			    <c:when test="${not empty monitorQuotaItem.id}">
			        <span class="tbar-label"><span></span>编辑设备监控指标表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加设备监控指标表</span>
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
	<form id="monitorQuotaForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">设备监控指标表</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">指标名称:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="name" lablename="指标名称" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="${monitorQuota.name}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">指标单位:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="unit" lablename="指标单位" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="${monitorQuota.unit}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">监控类型:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><select name="monitorType" lablename="监控类型" controltype="select" validate="{empty:false}"><option value="SNMP" <c:if test='${monitorQuota.monitorType=="SNMP"}'>selected='selected'</c:if>>SNMP</option><option value="IPMI" <c:if test='${monitorQuota.monitorType=="IPMI"}'>selected='selected'</c:if>>IPMI</option></select></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">对象标识符:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="OID" lablename="对象标识符" class="inputText" validate="{maxlength:100,required:true,OID:true}" isflag="tableflag" type="text" value="${monitorQuota.OID}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">监控类名:</td>
   <td class="formInput" style="width:80%;"><input name="className" readonly="readonly" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{maxlength:100,required:true}" type="text" value="${monitorQuota.className}" /></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${monitorQuota.id}"/>
	</form>
</body>
</html>
