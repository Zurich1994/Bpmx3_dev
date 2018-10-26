<%--
	time:2015-05-17 15:46:19
	desc:edit the 工作日历
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 工作日历</title>
	<%@include file="/commons/include/form.jsp" %>
<f:link href="listEdit.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/ats/AtsDialog.js"></script>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#atsWorkCalendarForm").attr("action","save.ht");
				submitForm();
			});
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#atsWorkCalendarForm').form();
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
						window.location.href = window.location.href;
					}else{
						window.location.href = "${ctx}/platform/ats/atsWorkCalendar/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
		function selectCalendarTempl(){
			AtsCalendarTemplDialog({callback:function(rtn){
				$('#calendarTempl').val(rtn.id);
				$('#calendarTemplName').val(rtn.name);
			}});
		}
		
		function selectLegalHoliday(){
			AtsLegalHolidayDialog({callback:function(rtn){
				$('#legalHoliday').val(rtn.id);
				$('#legalHolidayName').val(rtn.name);
			}});
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${atsWorkCalendar.id !=null}">
			        <span class="tbar-label"><span></span>编辑工作日历</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加工作日历</span>
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
		<form id="atsWorkCalendarForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">编码: </th>
					<td><input type="text" id="code" name="code" value="${atsWorkCalendar.code}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
					<th width="20%">名称: </th>
					<td><input type="text" id="name" name="name" value="${atsWorkCalendar.name}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
		
				</tr>
				<tr>
					<th width="20%">开始时间: </th>
					<td><input type="text" id="startTime" name="startTime" value="<fmt:formatDate value='${atsWorkCalendar.startTime}' pattern='yyyy-MM-dd'/>" class="inputText datePicker" dateType="1" validate="{date:true,required:true}" /></td>
					<th width="20%">结束时间: </th>
					<td><input type="text" id="endTime" name="endTime" value="<fmt:formatDate value='${atsWorkCalendar.endTime}' pattern='yyyy-MM-dd'/>" class="inputText datePicker"  dateType="2" validate="{date:true,required:true}"  /></td>
				
				</tr>
				<tr>
					<th width="20%">日历模版: </th>
					<td>
						<input type="hidden" id="calendarTempl" name="calendarTempl" value="${atsWorkCalendar.calendarTempl}" />
						<input type="text" id="calendarTemplName"  value="${atsWorkCalendar.calendarTemplName}"  class="inputText" validate="{required:true}" readonly="readonly" validate="{required:true}"  />
						<a href="javascript:;" onclick="selectCalendarTempl()" class="button"><span>选 择...</span></a>
					</td>
					<th width="20%">法定假日: </th>
					<td>
						<input type="hidden" id="legalHoliday" name="legalHoliday" value="${atsWorkCalendar.legalHoliday}" />
						<input type="text" id="legalHolidayName"  value="${atsWorkCalendar.legalHolidayName}"  class="inputText"  readonly="readonly" validate="{required:true}" />
						<a href="javascript:;" onclick="selectLegalHoliday()" class="button"><span>选 择...</span></a>
					</td>
		
				</tr>
				<tr>
					<th width="20%">是否默认: </th>
					<td>
						<input type="checkbox" id="isDefault" name="isDefault" value="1"   <c:if test="${atsWorkCalendar.isDefault ==1}"> checked="checked"</c:if>  />
					</td>
					<th width="20%">描述: </th>
					<td>
						<textarea rows="3" cols="5" id="memo" name="memo"  class="inputText">${atsWorkCalendar.memo}</textarea>
					</td>
				
				</tr>
			</table>
			<input type="hidden" name="id" value="${atsWorkCalendar.id}" />
		</form>
		
	</div>
</div>
</body>
</html>
