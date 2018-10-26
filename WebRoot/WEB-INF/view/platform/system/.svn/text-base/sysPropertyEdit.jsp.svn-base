<%--
	time:2015-04-16 11:20:41
	desc:edit the 系统配置参数表
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 系统配置参数表</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#sysPropertyForm").attr("action","save.ht");
				$("#saveData").val(1);
				submitForm();
			});
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#sysPropertyForm').form();
			frm.ajaxForm(options);
			if(frm.valid()){
				frm.submit();
			}
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.href = "${ctx}/platform/system/sysProperty/list.ht";
					}else{
						window.location.href = "${ctx}/platform/system/sysProperty/list.ht";
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
        	<span class="tbar-label"><span></span>编辑系统配置参数表</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="sysPropertyForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">名称: </th>
					<td>${sysProperty.name}</td>
				</tr>
				<tr>
					<th width="20%">别名: </th>
					<td>${sysProperty.alias}</td>
				</tr>
				<tr>
					<th width="20%">分组: </th>
					<td>${sysProperty.group}</td>
				</tr>
				<tr>
					<th width="20%">值: </th>
					<td><input type="text" id="value" name="value" value='${sysProperty.value}' style="width: 400px" class="inputText" validate="{required:false}"  /></td>
				</tr>
				
				<tr>
					<th width="20%">排序: </th>
					<td><input type="text" id="sn" name="sn" value="${sysProperty.sn}"  class="inputText" validate="{required:false,number:true}"  /></td>
				</tr>
				<tr>
					<th width="20%">备注: </th>
					<td>
						<textarea rows="5" cols="60" name="memo">${sysProperty.memo}</textarea>
					</td>
				</tr>
			</table>
			<input type="hidden" name="id" value="${sysProperty.id}" />
		</form>
	</div>
</div>
</body>
</html>
