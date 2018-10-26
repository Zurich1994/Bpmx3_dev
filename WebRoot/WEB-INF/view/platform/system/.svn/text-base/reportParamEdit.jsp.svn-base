<%--
	time:2012-04-12 11:08:13
	desc:edit the 报表参数
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 报表参数</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=reportParam"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			valid(showRequest,showResponse);
			$("a.save").click(function() {
				$('#reportParamForm').submit(); 
			});
		});
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    <c:choose>
			        <c:when test="${reportParam.PARAMID !=null }">
			            <span class="tbar-label">编辑报表参数</span>
			        </c:when>
			        <c:otherwise>
			            <span class="tbar-label">添加报表参数</span>
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
				<form id="reportParamForm" method="post" action="save.ht">
					<div class="panel-detail">
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%">所属报表:  <span class="required">*</span></th>
								<td><input type="text" id="REPORTID" name="REPORTID" value="${reportParam.REPORTID}"  class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%">参数名称:  <span class="required">*</span></th>
								<td><input type="text" id="PARAMNAME" name="PARAMNAME" value="${reportParam.PARAMNAME}"  class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%">参数Key:  <span class="required">*</span></th>
								<td><input type="text" id="PARAMKEY" name="PARAMKEY" value="${reportParam.PARAMKEY}"  class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%">缺省值: </th>
								<td><input type="text" id="DEFAULTVAL" name="DEFAULTVAL" value="${reportParam.DEFAULTVAL}"  class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%">类型:  <span class="required">*</span></th>
								<td><input type="text" id="PARAMTYPE" name="PARAMTYPE" value="${reportParam.PARAMTYPE}"  class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%">系列号:  <span class="required">*</span></th>
								<td><input type="text" id="SN" name="SN" value="${reportParam.SN}"  class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%">PARAMTYPESTR: </th>
								<td><input type="text" id="PARAMTYPESTR" name="PARAMTYPESTR" value="${reportParam.PARAMTYPESTR}"  class="inputText"/></td>
							</tr>
						</table>
					</div>
					<input type="hidden" name="PARAMID" value="${reportParam.PARAMID}" />
				</form>
		</div>
</div>
</body>
</html>
