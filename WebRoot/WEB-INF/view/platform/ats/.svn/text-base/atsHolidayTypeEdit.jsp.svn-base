<%--
	time:2015-05-16 20:47:17
	desc:edit the 假期类型
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 假期类型</title>
	<%@include file="/commons/include/form.jsp" %>
	<f:link href="listEdit.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#atsHolidayTypeForm").attr("action","save.ht");
				submitForm();
			});
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#atsHolidayTypeForm').form();
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
						window.location.href = "${ctx}/platform/ats/atsHolidayType/list.ht";
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
			    <c:when test="${atsHolidayType.id !=null}">
			        <span class="tbar-label"><span></span>编辑假期类型</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加假期类型</span>
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
		<form id="atsHolidayTypeForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">编码: </th>
					<td><input type="text" id="code" name="code" value="${atsHolidayType.code}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
				</tr>
				<tr>
					<th width="20%">名称: </th>
					<td><input type="text" id="name" name="name" value="${atsHolidayType.name}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
				</tr>
				
				<tr>
					<th width="20%">是否启用: </th>
					<td>
						<select name="status" id="" value="${atsHolidayType.status}" validate="{required:true,maxlength:384}"  >
							<option value="1" <c:if test="${atsHolidayType.status==1}">selected </c:if>  >启用</option>
							<option value="0" <c:if test="${atsHolidayType.status==0}">selected </c:if>  > 禁用</option>
						</select>
					</td>
				</tr>
			
				<tr>
					<th width="20%">描述: </th>
					<td>
						<textarea rows="3" cols="5" id="memo" name="memo"  class="inputText">${atsHolidayType.memo}</textarea>
					</td>
				</tr>
			</table>
			
			</div>
			<input type="hidden" name="id" value="${atsHolidayType.id}" />
			<input type="hidden" id="isSys" name="isSys" value="${atsHolidayType.isSys}" />
		</form>
		
	</div>
</div>
</body>
</html>
