<%--
	time:2012-11-27 10:37:13
	desc:edit the 通用表单查询
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 通用表单查询</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#viewForm').form();
			$("a.add").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					$('#viewForm').submit();
				}
			});
			
			$("#btnValid").click(function(){
				var sql=$('#sqlView').val();
				var dsalias=$('#dataSource').val();
				var params={sql:sql,dsalias:dsalias};
				$.post("validSql.ht",params,function(data){
					if (data){
						$.ligerDialog.success('<p><font color="green">验证通过!<br></font></p>');
					}else{
						$.ligerDialog.error('<p><font color="red">验证不通过!<br></font></p>');
					}
				});
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.success(obj.getMessage(),"提示信息", function() {
					this.window.location.href = "createView.ht";
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
		<div class="panel-toolbar">
			<div class="toolBar">
		   <div class="l-bar-separator"></div>
			<div class="group">
				<a class="link add" ><span></span>创建</a>
			</div>
		</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="viewForm" method="post" action="create.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="20%">视图别名: </th>
					<td><input type="text" id="name" name="viewName"  validate="{required:true,varirule:true}"  class="inputText" /></td>
					<th width="20%">数据源: </th>
					<td>
						<select id="dataSource" name="dsalias">
							<option value="LOCAL">本地数据源 </option>
							<c:forEach items="${dsList}" var="ds">
								<option value="${ds.alias}">${ds.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th width="20%">查询语句设置: </th>
					<td colspan="3" valign="top">
						<textarea  id="sqlView"  name="sqlView" validate="{required:true}" >
						</textarea>
					<a class="button" id="btnValid"   ><span class="icon valid"></span><span>验证查询语句</span></a></td>
				</tr>
			</table>
		</form>
		
	</div>
</div>
</body>
</html>
