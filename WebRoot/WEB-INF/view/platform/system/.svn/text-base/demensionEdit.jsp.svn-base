<%--
	time:2011-12-01 14:23:26
	desc:edit the SYS_DEMENSION
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑维度</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=demension"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
						
			if(${demension.demId ==null}){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			$("a.save").click(function() {
				$('#demensionForm').submit(); 
			});
		});
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    <c:choose>
			        <c:when test="${demension.demId !=null}">
			            <span class="tbar-label">编辑维度</span>
			        </c:when>
			        <c:otherwise>
			            <span class="tbar-label">添加维度</span>
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
				<form id="demensionForm" method="post" action="save.ht">
					
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="25%">维度名称:</th>
							<td><input type="text" id="demName" name="demName" value="${demension.demName}"  class="inputText" style="width:220px !important" /></td>
						</tr>
						<tr height="40px">
							<th width="20%">维度描述:</th>
							<td>
								<textarea id="demDesc" name="demDesc" rows="3" cols="50">${demension.demDesc}</textarea>
							</td>
						</tr>
					</table>
					
					<input type="hidden" name="demId" value="${demension.demId}" />
				</form>
		</div>
</div>
</body>
</html>
