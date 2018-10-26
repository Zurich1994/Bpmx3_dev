<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 发生规律信息表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#regularoccurrenceForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#regularoccurrenceForm').submit();
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
						window.location.href = "${ctx}/RegularOccurrence/lc/regularoccurrence/list.ht";
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
			    <c:when test="${not empty regularoccurrenceItem.id}">
			        <span class="tbar-label"><span></span>编辑发生规律信息表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加发生规律信息表</span>
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
	<form id="regularoccurrenceForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">发生规律信息表</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">流程编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="processId" lablename="流程编号" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="${regularoccurrence.processId}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">时间:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="time" lablename="时间" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="${regularoccurrence.time}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">发生规律值:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="regularValue" lablename="发生规律值" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="${regularoccurrence.regularValue}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${regularoccurrence.id}"/>
	</form>
</body>
</html>