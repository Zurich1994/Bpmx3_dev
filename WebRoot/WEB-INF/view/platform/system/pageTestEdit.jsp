<%--
	time:2015-06-02 22:29:22
	desc:edit the PageTest
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@ taglib prefix="ht" tagdir="/WEB-INF/tags/wf"%>
<html>
<head>
	<title>编辑 PageTest</title>
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
			    <c:when test="${pageTest.id !=null}">
			        <span class="tbar-label">编辑PageTest</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label">添加PageTest</span>
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
						<th width="20%">category: </th>
						<td><input type="text" id="category" name="category" value="${pageTest.category}" validate="{required:false,maxlength:150}" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">name: </th>
						<td><input type="text" id="name" name="name" value="${pageTest.name}" validate="{required:false,maxlength:240}" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%">amount: </th>
						<td><input type="text" id="amount" name="amount" value="${pageTest.amount}" validate="{required:false,number:true,maxIntLen:10 }" class="inputText"/></td>
					</tr>
			</table>
			<input type="hidden" name="id" value="${pageTest.id}" />					
	</div>
</div>
</form>
</body>
</html>
