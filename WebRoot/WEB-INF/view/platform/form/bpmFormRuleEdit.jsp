<%--
	time:2012-01-11 10:53:15
	desc:edit the 表单验证规则
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 表单验证规则</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerDialog.js"></script>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=bpmFormRule"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			if(${bpmFormRule.id==null }){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			$("a.save").click(function() {
				$('#bpmFormRuleForm').submit(); 
			});
			
			handvVlidRule();
		});
		
		function testReg(rule,toValid){
			var reg= new RegExp(rule);
			var rtn= reg.test(toValid);
			return rtn;
		}
		
		function handvVlidRule(){
			$("#btnValid").click(function(){
				var rule=$('#rule').val();
				var toValid=$('#toValid').val();
				var rtn=testReg(rule,toValid);
				if (rtn){
					$.ligerDialog.success('<p><font color="green">验证通过!<br></font></p>');
				}else{
					$.ligerDialog.error('<p><font color="red">验证不通过!<br></font></p>');
				}
			});
		}
		
		
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">
				<c:if test="${bpmFormRule.id==null }">添加表单验证规则</c:if>
				<c:if test="${bpmFormRule.id!=null }">编辑表单验证规则</c:if>
			    </span>
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
				<form id="bpmFormRuleForm" method="post" action="save.ht">
				
						<table class="table-detail"  width="60%" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%">规则名: </th>
								<td><input size="60%" type="text" id="name" name="name" value="${bpmFormRule.name}"  class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%">规则: </th>
								<td >
								<input size="60%" id="rule" name="rule" value="${bpmFormRule.rule}" class="inputText"/>
								</tr><tr>
								<th>请输入验证值:</th>
								<td><input size="60%"  type="text"  id="toValid"  name="toValid" class="inputText" >
								<a class="button" id="btnValid"   ><span class="icon valid"></span><span>验证规则</span></a></td>
							</tr>
							<tr>
								<th width="20%">错误消息提示: </th>
								<td>
								<input size="60%" id="tipInfo" name="tipInfo" value="${bpmFormRule.tipInfo}" class="inputText"/>
								</td>
							</tr>
							<tr>
								<th width="20%">描述: </th>
								<td>
								<textarea rows="4" cols="30" id="memo" name="memo" style="width:60%">${bpmFormRule.memo}</textarea>
							</tr>
						</table>
						<input type="hidden" name="id" value="${bpmFormRule.id}" />
					
				</form>
		</div>
</div>
</body>
</html>
