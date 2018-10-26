<%--
	time:2012-02-20 09:25:49
	desc:edit the 法定假期设置
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>编辑 法定假期设置</title>
<%@include file="/commons/include/form.jsp"%>
<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#vacationForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					$('#vacationForm').submit();
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm( obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						this.close();
					}else{
						window.location.href = "list.ht";
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
					<c:when test="${vacation.id !=null}">
						<span class="tbar-label">编辑法定假期设置</span>
					</c:when>
					<c:otherwise>
						<span class="tbar-label">添加法定假期设置</span>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="vacationForm" method="post" action="save.ht">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%">假日名称:</th>
						<td><input type="text" id="name" name="name"
							value="${vacation.name}" validate="{required:true}" class="inputText" /></td>
					</tr>
					<tr>
						<th width="20%">年份:</th>
						<td>
						<c:if test="${vacation.years==null}">
								<input type="text" id="years" name="years" value="${year}"
									class="inputText Wdate" onfocus="WdatePicker({dateFmt:'yyyy'})" />
						</c:if> <c:if test="${vacation.years!=null}">
								<input type="text" id="years" name="years"
									value="${vacation.years}" class="Wdate"
									onfocus="WdatePicker({dateFmt:'yyyy'})" />
							</c:if></td>
					</tr>
					<tr>
						<th width="20%">开始时间:</th>
						<td><input type="text" id="startTime" name="statTime"
							value="<fmt:formatDate value='${vacation.statTime}' pattern='yyyy-MM-dd'/>"
							class="inputText date" validate="{date:true,dateRangeStart:'endTime'}" /></td>
					</tr>
					<tr>
						<th width="20%">结束时间:</th>
						<td><input type="text" id="endTime" name="endTime"
							value="<fmt:formatDate value='${vacation.endTime}' pattern='yyyy-MM-dd'/>"
							class="inputText date" validate="{date:true,dateRangeEnd:'startTime'}"/></td>
					</tr>
				</table>
				<input type="hidden" name="id" value="${vacation.id}" />
			</form>

		</div>
	</div>
</body>
</html>
