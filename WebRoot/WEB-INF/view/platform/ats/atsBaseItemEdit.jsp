<%--
	time:2015-05-16 18:08:43
	desc:edit the 基础数据
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 基础数据</title>
	<%@include file="/commons/include/form.jsp" %>
	<f:link href="listEdit.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#atsBaseItemForm").attr("action","save.ht");
				submitForm();
			});
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#atsBaseItemForm').form();
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
						window.location.href = window.location.href;
					}else{
						window.location.href = "${ctx}/platform/ats/atsBaseItem/list.ht";
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
			    <c:when test="${atsBaseItem.id !=null}">
			        <span class="tbar-label"><span></span>编辑基础数据</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加基础数据</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javaScript:void(0)"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="atsBaseItemForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" >
				<tr>
					<th width="20%">编码: </th>
					<td><input type="text" id="code" name="code" value="${atsBaseItem.code}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
				</tr>
				<tr>
					<th width="20%">名称: </th>
					<td><input type="text" id="name" name="name" value="${atsBaseItem.name}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
		
				</tr>
				<tr>
					<th width="20%">地址: </th>
					<td>
					<input type="text" id="url" name="url" value="${atsBaseItem.url}" style="width: 50%"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
				</tr>
				<tr>
					<th width="20%">描述: </th>
					<td>
						<textarea rows="3" cols="5" id="memo" name="memo"  class="inputText">${atsBaseItem.memo}</textarea>
					</td>
				</tr>
			</table>
			<input type="hidden" name="id" value="${atsBaseItem.id}" />
			<input type="hidden" id="isSys" name="isSys" value="${atsBaseItem.isSys}"  />
		</form>
		
	</div>
</div>
</body>
</html>
