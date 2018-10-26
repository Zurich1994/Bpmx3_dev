<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 业务部署绑定</title>
	<%@include file="/codegen/include/customForm.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/subform.js"></script>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#ywbsbdForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				$("#saveData").val(1);
				if(frm.valid()){
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					if(OfficePlugin.officeObjs.length>0){
						OfficePlugin.submit(function(){
							frm.handleFieldName();
							$('#ywbsbdForm').submit();
						});
					}else{
						frm.handleFieldName();
						$('#ywbsbdForm').submit();
					}
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.href = window.location.href;
					}else{
					    window.location.href = window.location.href;
						//window.location.href = "${ctx}/Ywbsbd/Ywbsbd/ywbsbd/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
	</script>
</head>
<body>
<div class="panel" style="height:100%;overflow:auto;">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty ywbsbdItem.id}">
			        <span class="tbar-label"><span></span>编辑业务部署绑定</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加业务部署绑定</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht?defId=${ywbsbd.defId}&nodeId=${ywbsbd.nodeId}"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<form id="ywbsbdForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="6" class="formHead">业务部署绑定</td>
  </tr>
  <tr style="height:23px;">
   <td style="width:13%;" class="formTitle" nowrap="nowarp">子系统id:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:ywbubs:systemid" lablename="子系统id" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${ywbsbd.systemId}" /></span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">流程id:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:ywbubs:lcid" lablename="流程id" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${ywbsbd.defId}" /></span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">子系统名称:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:ywbubs:sysname" lablename="子系统名称" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${ywbsbd.sysname}" /></span></td>
  </tr>
  <tr style="height:23px;">
   <td style="width:13%;" class="formTitle" nowrap="nowarp">子系统别名:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:ywbubs:sysalias" lablename="子系统别名" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${ywbsbd.sysalias}" /></span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">备注:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:ywbubs:sysmeno" lablename="备注" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${ywbsbd.sysmeno}" /></span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">服务:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:ywbubs:service" lablename="服务" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${ywbsbd.service}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${ywbsbd.id}"/>
		<input type="hidden" id="saveData" name="saveData"/>
		<input type="hidden" name="executeType"  value="start" />
	</form>
</body>
</html>
