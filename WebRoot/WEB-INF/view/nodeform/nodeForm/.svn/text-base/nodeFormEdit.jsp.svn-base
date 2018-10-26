<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 节点表单对应表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#nodeFormForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#nodeFormForm').submit();
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
						window.location.href = "${ctx}/nodeform/nodeForm/nodeForm/list.ht";
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
			    <c:when test="${not empty nodeFormItem.id}">
			        <span class="tbar-label"><span></span>编辑节点表单对应表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加节点表单对应表</span>
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
	<form id="nodeFormForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="6" class="formHead">节点表单对应表</td>
  </tr>
  <tr style="height:23px;">
   <td style="width:13%;" class="formTitle" nowrap="nowarp">查询条件1:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="condition1" lablename="查询条件1" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${nodeForm.condition1}" /></span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">查询条件2:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="condition2" lablename="查询条件2" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${nodeForm.condition2}" /></span></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">查询条件3:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="condition3" lablename="查询条件3" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${nodeForm.condition3}" /></span></td>
  </tr>
  <tr style="height:23px;">
   <td style="width:13%;" class="formTitle" nowrap="nowarp">表单id:</td>
   <td style="width:20%;" class="formInput"><input name="formID" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:18,maxDecimalLen:0,required:true}" type="text" value="${nodeForm.formID}" /></td>
   <td style="width:13%;" class="formTitle" nowrap="nowarp">表单名称:</td>
   <td style="width:20%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="formName" lablename="表单名称" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="${nodeForm.formName}" /></span></td>
   <td style="width:13%;" class="formTitle"></td>
   <td style="width:20%;" class="formInput"></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${nodeForm.id}"/>
	</form>
</body>
</html>
