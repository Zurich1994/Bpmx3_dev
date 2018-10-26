<%--
	time:2013-11-27 10:19:23
	desc:edit the SYS_USER_POS
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 SYS_USER_POS</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#userPositionForm").attr("action","save.ht");
				submitForm();
			});
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#userPositionForm').form();
			frm.ajaxForm(options);
			if(frm.valid()){
				frm.submit();
			}
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.success(obj.getMessage(),"提示信息", function(rtn) {
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
			    <c:when test="${userPosition.userposid !=null}">
			        <span class="tbar-label">编辑SYS_USER_POS</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label">添加SYS_USER_POS</span>
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
	<div class="panel-body">
		<form id="userPositionForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">ORGID: </th>
					<td><input type="text" id="orgid" name="orgid" value="${userPosition.orgid}" validate="{required:false,number:true,maxIntLen:18 }" class="inputText"/></td>
				</tr>
				<tr>
					<th width="20%">POSID: </th>
					<td><input type="text" id="posid" name="posid" value="${userPosition.posid}" validate="{required:false,number:true,maxIntLen:18 }" class="inputText"/></td>
				</tr>
				<tr>
					<th width="20%">USERID: </th>
					<td><input type="text" id="userid" name="userid" value="${userPosition.userid}" validate="{required:false,number:true,maxIntLen:18 }" class="inputText"/></td>
				</tr>
				<tr>
					<th width="20%">ISPRIMARY: </th>
					<td><input type="text" id="isprimary" name="isprimary" value="${userPosition.isprimary}" validate="{required:false,number:true }" class="inputText"/></td>
				</tr>
				<tr>
					<th width="20%">ISCHARGE: </th>
					<td><input type="text" id="ischarge" name="ischarge" value="${userPosition.ischarge}" validate="{required:false,number:true }" class="inputText"/></td>
				</tr>
				<tr>
					<th width="20%">ISDELETE: </th>
					<td><input type="text" id="isdelete" name="isdelete" value="${userPosition.isdelete}" validate="{required:false,number:true }" class="inputText"/></td>
				</tr>
			</table>
			<input type="hidden" name="userposid" value="${userPosition.userposid}" />					
		</form>
	</div>
</div>
</body>
</html>
