<%--
	time:2015-05-16 21:44:00
	desc:edit the 班次类型
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 班次类型</title>
	<%@include file="/commons/include/form.jsp" %>
	<f:link href="listEdit.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#atsShiftTypeForm").attr("action","save.ht");
				submitForm();
			});
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#atsShiftTypeForm').form();
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
						window.location.href = "${ctx}/platform/ats/atsShiftType/list.ht";
						//window.location.href = window.location.href;
					}else{
						window.location.href = "${ctx}/platform/ats/atsShiftType/list.ht";
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
			    <c:when test="${atsShiftType.id !=null}">
			        <span class="tbar-label"><span></span>编辑班次类型</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加班次类型</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:void(0);"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="atsShiftTypeForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">编码: </th>
					<td><input type="text" id="code" name="code" value="${atsShiftType.code}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
				</tr>
				<tr>
					<th width="20%">名称: </th>
					<td><input type="text" id="name" name="name" value="${atsShiftType.name}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
				</tr>
				<tr>
					<th width="20%">是否启用: </th>
					<td>
						<select name="status" id="" value="${atsShiftType.status}" validate="{required:true,maxlength:384}"  >
							<option value="1" <c:if test="${atsShiftType.status==1}">selected </c:if>  >启用</option>
							<option value="0" <c:if test="${atsShiftType.status==0}">selected </c:if>  > 禁用</option>
						</select>
					</td>
				</tr>
				<tr>
					<th width="20%">描述: </th>
					<td>
						<textarea rows="3" cols="5" id="memo" name="memo"  class="inputText">${atsShiftType.memo}</textarea>
					</td>
				</tr>
			</table>
			<input type="hidden" name="id" value="${atsShiftType.id}" />
			<input type="hidden" id="isSys" name="isSys" value="${atsShiftType.isSys}" />
		</form>
		
	</div>
</div>
</body>
</html>
