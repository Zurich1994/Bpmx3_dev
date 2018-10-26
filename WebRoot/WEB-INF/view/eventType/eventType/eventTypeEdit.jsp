<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 事件类型表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#eventTypeForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#eventTypeForm').submit();
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
						window.location.href = "${ctx}/eventType/eventType/eventType/list.ht";
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
			    <c:when test="${not empty eventTypeItem.id}">
			        <span class="tbar-label"><span></span>编辑事件类型表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加事件类型表</span>
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
	<form id="eventTypeForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellspacing="0" cellpadding="2">
 <tbody>
  <tr>
   <td class="formHead" colspan="2">事件类型表</td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">事件名称:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="eventName" class="inputText" type="text" isflag="tableflag" validate="{maxlength:50}" lablename="事件名称" value="${eventType.eventName}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">备用字段1:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="spare1" class="inputText" type="text" isflag="tableflag" validate="{maxlength:100}" lablename="备用字段1" value="${eventType.spare1}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">备用字段2:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="spare2" class="inputText" type="text" isflag="tableflag" validate="{maxlength:100}" lablename="备用字段2" value="${eventType.spare2}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">备用字段3:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="spare3" class="inputText" type="text" isflag="tableflag" validate="{maxlength:100}" lablename="备用字段3" value="${eventType.spare3}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">备用字段4:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="spare4" class="inputText" type="text" isflag="tableflag" validate="{maxlength:100}" lablename="备用字段4" value="${eventType.spare4}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">备用字段5:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="spare5" class="inputText" type="text" isflag="tableflag" validate="{maxlength:100}" lablename="备用字段5" value="${eventType.spare5}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${eventType.id}"/>
	</form>
</body>
</html>
