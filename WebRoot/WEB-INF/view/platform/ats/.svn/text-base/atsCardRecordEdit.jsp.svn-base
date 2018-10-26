<%--
	time:2015-05-26 11:21:22
	desc:edit the 打卡记录
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 打卡记录</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#atsCardRecordForm").attr("action","save.ht");
				submitForm();
			});
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#atsCardRecordForm').form();
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
						window.location.href = "${ctx}/platform/ats/atsCardRecord/list.ht";
						//window.location.href = window.location.href;
					}else{
						window.location.href = "${ctx}/platform/ats/atsCardRecord/list.ht";
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
			    <c:when test="${atsCardRecord.id !=null}">
			        <span class="tbar-label"><span></span>编辑打卡记录</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加打卡记录</span>
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
		<form id="atsCardRecordForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">考勤卡号: </th>
					<td><input type="text" id="cardNumber" name="cardNumber" value="${atsCardRecord.cardNumber}"  class="inputText" validate="{required:false,maxlength:384}"  /></td>
				</tr>
				<tr>
					<th width="20%">打卡日期: </th>
					<td><input type="text" id="cardDate" name="cardDate" value="<fmt:formatDate value='${atsCardRecord.cardDate}' pattern='yyyy-MM-dd'/>" class="inputText date" validate="{date:true}" /></td>
				</tr>
				<tr>
					<th width="20%">打卡时间: </th>
					<td><input type="text" id="cardTime" name="cardTime" value="${atsCardRecord.cardTime}"  class="inputText" validate="{required:false}"  /></td>
				</tr>
				<tr>
					<th width="20%">打卡来源: </th>
					<td><input type="text" id="cardSource" name="cardSource" value="${atsCardRecord.cardSource}"  class="inputText" validate="{required:false,maxlength:384}"  /></td>
				</tr>
				<tr>
					<th width="20%">打卡位置: </th>
					<td><input type="text" id="cardPlace" name="cardPlace" value="${atsCardRecord.cardPlace}"  class="inputText" validate="{required:false,maxlength:384}"  /></td>
				</tr>
			</table>
			<input type="hidden" name="id" value="${atsCardRecord.id}" />
		</form>
		
	</div>
</div>
</body>
</html>
