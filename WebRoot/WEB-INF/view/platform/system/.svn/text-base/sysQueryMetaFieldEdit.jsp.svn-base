<%--
	time:2015-06-08 16:02:04
	desc:edit the 自定义SQL字段定义
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@ taglib prefix="ht" tagdir="/WEB-INF/tags/wf"%>
<html>
<head>
	<title>编辑 自定义SQL字段定义</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript">
		var returnUrl="${returnUrl}";	
		
		$(function() {
				$("a.save").click(function() {
					submitForm();
				});
		});
		
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#frmSubmit').form();
			frm.ajaxForm(options);
			if(frm.valid()){
				frm.submit();
			}
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.topCall.success(obj.getMessage(),"提示信息", function(rtn) {
					if(rtn){
						if(window.opener){
							window.opener.location.reload();
							window.close();
						}else{
							this.close();
							window.location.href="list.ht";
						}
					}
				});
			} else {
				$.topCall.error(obj.getMessage(),"提示信息");
			}
		}
	</script>
</head>
<body>
<form id="frmSubmit" method="post" action="save.ht">
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${sysQueryMetaField.id !=null}">
			        <span class="tbar-label">编辑自定义SQL字段定义</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label">添加自定义SQL字段定义</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<ht:incToolBar ></ht:incToolBar>
				<div class="group"><a class="link back" href="history.back(-1);"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
					<tr>
						<th width="20%">SQLID: </th>
						<td><input type="text" id="sqlId" name="sqlId" value="${sysQueryMetaField.sqlId}" validate="{required:false,number:true,maxIntLen:19 }" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">字段名: </th>
						<td><input type="text" id="name" name="name" value="${sysQueryMetaField.name}" validate="{required:false,maxlength:150}" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">实际字段名: </th>
						<td><input type="text" id="fieldName" name="fieldName" value="${sysQueryMetaField.fieldName}" validate="{required:false,maxlength:150}" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">字段备注: </th>
						<td><input type="text" id="fieldDesc" name="fieldDesc" value="${sysQueryMetaField.fieldDesc}" validate="{required:false,maxlength:300}" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">是否可见: </th>
						<td><input type="text" id="isShow" name="isShow" value="${sysQueryMetaField.isShow}" validate="{required:false}" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">是否搜索: </th>
						<td><input type="text" id="isSearch" name="isSearch" value="${sysQueryMetaField.isSearch}" validate="{required:false}" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">控件类型: </th>
						<td><input type="text" id="controlType" name="controlType" value="${sysQueryMetaField.controlType}" validate="{required:false}" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">数据类型: </th>
						<td><input type="text" id="dataType" name="dataType" value="${sysQueryMetaField.dataType}" validate="{required:false,maxlength:60}" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">是否衍生列: </th>
						<td><input type="text" id="isVirtual" name="isVirtual" value="${sysQueryMetaField.isVirtual}" validate="{required:false}" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">衍生列来自列: </th>
						<td><input type="text" id="virtualFrom" name="virtualFrom" value="${sysQueryMetaField.virtualFrom}" validate="{required:false,maxlength:150}" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">来自类型: </th>
						<td><input type="text" id="resultFromType" name="resultFromType" value="${sysQueryMetaField.resultFromType}" validate="{required:false,maxlength:30}" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">衍生列配置: </th>
						<td><input type="text" id="resultFrom" name="resultFrom" value="${sysQueryMetaField.resultFrom}" validate="{required:false}" class="inputText"/></td>
					</tr>
			</table>
			<input type="hidden" name="id" value="${sysQueryMetaField.id}" />					
	</div>
</div>
</form>
</body>
</html>
