<%--
	time:2015-05-26 10:07:59
	desc:edit the 考勤组明细
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 考勤组明细</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#atsAttenceGroupDetailForm").attr("action","save.ht");
				submitForm();
			});
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#atsAttenceGroupDetailForm').form();
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
						window.location.href = "${ctx}/platform/ats/atsAttenceGroupDetail/list.ht";
						//window.location.href = window.location.href;
					}else{
						window.location.href = "${ctx}/platform/ats/atsAttenceGroupDetail/list.ht";
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
			    <c:when test="${atsAttenceGroupDetail.id !=null}">
			        <span class="tbar-label"><span></span>编辑考勤组明细</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加考勤组明细</span>
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
		<form id="atsAttenceGroupDetailForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">考勤组: </th>
					<td><input type="text" id="groupId" name="groupId" value="${atsAttenceGroupDetail.groupId}"  class="inputText" validate="{required:false,number:true,maxIntLen:19}"  /></td>
				</tr>
				<tr>
					<th width="20%">考勤档案: </th>
					<td><input type="text" id="fileId" name="fileId" value="${atsAttenceGroupDetail.fileId}"  class="inputText" validate="{required:false,number:true,maxIntLen:19}"  /></td>
				</tr>
			</table>
			<input type="hidden" name="id" value="${atsAttenceGroupDetail.id}" />
		</form>
		
	</div>
</div>
</body>
</html>
