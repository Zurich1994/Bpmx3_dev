<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 固定指标参数表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#fixParamForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#fixParamForm').submit();
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
						window.location.href = "${ctx}/FixParam/lc/fixParam/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
		function fixParamSave(){
			var processId = $("#processId").val();
			window.location.href = "${ctx}/FixParam/lc/fixParam/list.ht?processId="+processId;
		}
		
		function goBackImportData(){
			var processId = $("#processId").val();
			window.location.href = "${ctx}/ImportData/lc/importData/edit.ht?processId="+processId;
		}
		
		
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty fixParamItem.id}">
			        <span class="tbar-label"><span></span>编辑固定指标参数表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加固定指标参数表</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;" onclick="fixParamSave()"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="javascript:;" onclick="goBackImportData()"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<form id="fixParamForm" method="post" action="save.ht">
		<input type="text" id="processId" name="processId" value="<%=request.getParameter("processId") %>" readonly="readonly" />
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">固定指标参数表</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">固定指标类型:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="fix_param_type" lablename="固定指标类型" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="<%=request.getParameter("paramType") %>" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">固定指标名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="fix_param_name" lablename="固定指标名" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="${fixParam.fix_param_name}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">固定指标值:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="fix_param_value" lablename="固定指标值" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="${fixParam.fix_param_value}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${fixParam.id}"/>
	</form>
</body>
</html>
