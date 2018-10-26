<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 绑定表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#bpmFormBangForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#bpmFormBangForm').submit();
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
						window.location.href = "${ctx}/BpmFormBang/bpmFormBang/bpmFormBang/list.ht";
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
			    <c:when test="${not empty bpmFormBangItem.id}">
			        <span class="tbar-label"><span></span>编辑绑定表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加绑定表</span>
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
	<form id="bpmFormBangForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="8" class="formHead">绑定表</td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">按钮名称:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="btn_name" lablename="按钮名称" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${bpmFormBang.btn_name}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">按钮类型:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="btn_type" lablename="按钮类型" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${bpmFormBang.btn_type}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">按钮概率:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="btn_probability" lablename="按钮概率" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${bpmFormBang.btn_probability}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">在线流程流程定义Id:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="defbId" lablename="在线流程流程定义Id" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${bpmFormBang.defbId}" /></span></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">表单Id:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="formId" lablename="表单Id" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${bpmFormBang.formId}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">流程定义Id:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="defId" lablename="流程定义Id" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${bpmFormBang.defId}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">节点Id:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="nodeId" lablename="节点Id" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${bpmFormBang.nodeId}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">自定义按钮Id:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="xId" lablename="自定义按钮Id" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${bpmFormBang.xId}" /></span></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">表单定义Id:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="formDefId" lablename="表单定义Id" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${bpmFormBang.formDefId}" /></span></td>
   <td style="width:10%;" class="formTitle"></td>
   <td style="width:15%;" class="formInput"></td>
   <td style="width:10%;" class="formTitle"></td>
   <td style="width:15%;" class="formInput"></td>
   <td style="width:10%;" class="formTitle"></td>
   <td style="width:15%;" class="formInput"></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${bpmFormBang.id}"/>
	</form>
</body>
</html>
