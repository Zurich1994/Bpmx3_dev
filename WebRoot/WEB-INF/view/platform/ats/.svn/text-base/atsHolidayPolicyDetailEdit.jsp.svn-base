<%--
	time:2015-05-28 14:25:03
	desc:edit the 假期制度明细
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 假期制度明细</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#atsHolidayPolicyDetailForm").attr("action","save.ht");
				$("#saveData").val(1);
				submitForm();
			});
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#atsHolidayPolicyDetailForm').form();
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
						window.location.href = "${ctx}/platform/ats/atsHolidayPolicyDetail/list.ht";
						//window.location.href = window.location.href;
					}else{
						window.location.href = "${ctx}/platform/ats/atsHolidayPolicyDetail/list.ht";
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
			    <c:when test="${atsHolidayPolicyDetail.id !=null}">
			        <span class="tbar-label"><span></span>编辑假期制度明细</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加假期制度明细</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="atsHolidayPolicyDetailForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">假期制度ID: </th>
					<td><input type="text" id="holidayId" name="holidayId" value="${atsHolidayPolicyDetail.holidayId}"  class="inputText" validate="{required:false,number:true,maxIntLen:19}"  /></td>
				</tr>
				<tr>
					<th width="20%">假期类型: </th>
					<td><input type="text" id="holidayType" name="holidayType" value="${atsHolidayPolicyDetail.holidayType}"  class="inputText" validate="{required:false,number:true,maxIntLen:19}"  /></td>
				</tr>
				<tr>
					<th width="20%">假期单位: </th>
					<td><input type="text" id="holidayUnit" name="holidayUnit" value="${atsHolidayPolicyDetail.holidayUnit}"  class="inputText" validate="{required:false}"  /></td>
				</tr>
				<tr>
					<th width="20%">启动周期: </th>
					<td><input type="text" id="enablePeriod" name="enablePeriod" value="${atsHolidayPolicyDetail.enablePeriod}"  class="inputText" validate="{required:false}"  /></td>
				</tr>
				<tr>
					<th width="20%">周期长度: </th>
					<td><input type="text" id="periodLength" name="periodLength" value="${atsHolidayPolicyDetail.periodLength}"  class="inputText" validate="{required:false}"  /></td>
				</tr>
				<tr>
					<th width="20%">周期单位: </th>
					<td><input type="text" id="periodUnit" name="periodUnit" value="${atsHolidayPolicyDetail.periodUnit}"  class="inputText" validate="{required:false}"  /></td>
				</tr>
				<tr>
					<th width="20%">控制单位额度: </th>
					<td><input type="text" id="enableMinAmt" name="enableMinAmt" value="${atsHolidayPolicyDetail.enableMinAmt}"  class="inputText" validate="{required:false}"  /></td>
				</tr>
				<tr>
					<th width="20%">单位额度: </th>
					<td><input type="text" id="minAmt" name="minAmt" value="${atsHolidayPolicyDetail.minAmt}"  class="inputText" validate="{required:false}"  /></td>
				</tr>
				<tr>
					<th width="20%">是否允许补请假: </th>
					<td><input type="text" id="isFillHoliday" name="isFillHoliday" value="${atsHolidayPolicyDetail.isFillHoliday}"  class="inputText" validate="{required:false}"  /></td>
				</tr>
				<tr>
					<th width="20%">补请假期限: </th>
					<td><input type="text" id="fillHoliday" name="fillHoliday" value="${atsHolidayPolicyDetail.fillHoliday}"  class="inputText" validate="{required:false}"  /></td>
				</tr>
				<tr>
					<th width="20%">补请假期限单位: </th>
					<td><input type="text" id="fillHolidayUnit" name="fillHolidayUnit" value="${atsHolidayPolicyDetail.fillHolidayUnit}"  class="inputText" validate="{required:false}"  /></td>
				</tr>
				<tr>
					<th width="20%">是否允许销假: </th>
					<td><input type="text" id="isCancelLeave" name="isCancelLeave" value="${atsHolidayPolicyDetail.isCancelLeave}"  class="inputText" validate="{required:false}"  /></td>
				</tr>
				<tr>
					<th width="20%">销假期限: </th>
					<td><input type="text" id="cancelLeave" name="cancelLeave" value="${atsHolidayPolicyDetail.cancelLeave}"  class="inputText" validate="{required:false}"  /></td>
				</tr>
				<tr>
					<th width="20%">销假期限单位: </th>
					<td><input type="text" id="cancelLeaveUnit" name="cancelLeaveUnit" value="${atsHolidayPolicyDetail.cancelLeaveUnit}"  class="inputText" validate="{required:false}"  /></td>
				</tr>
				<tr>
					<th width="20%">是否控制假期额度: </th>
					<td><input type="text" id="isCtrlLimit" name="isCtrlLimit" value="${atsHolidayPolicyDetail.isCtrlLimit}"  class="inputText" validate="{required:false}"  /></td>
				</tr>
				<tr>
					<th width="20%">假期额度规则: </th>
					<td><input type="text" id="holidayRule" name="holidayRule" value="${atsHolidayPolicyDetail.holidayRule}"  class="inputText" validate="{required:false,number:true,maxIntLen:19}"  /></td>
				</tr>
				<tr>
					<th width="20%">是否允许超额请假: </th>
					<td><input type="text" id="isOver" name="isOver" value="${atsHolidayPolicyDetail.isOver}"  class="inputText" validate="{required:false}"  /></td>
				</tr>
				<tr>
					<th width="20%">超出额度下期扣减: </th>
					<td><input type="text" id="isOverAutoSub" name="isOverAutoSub" value="${atsHolidayPolicyDetail.isOverAutoSub}"  class="inputText" validate="{required:false}"  /></td>
				</tr>
				<tr>
					<th width="20%">是否允许修改额度: </th>
					<td><input type="text" id="isCanModifyLimit" name="isCanModifyLimit" value="${atsHolidayPolicyDetail.isCanModifyLimit}"  class="inputText" validate="{required:false}"  /></td>
				</tr>
				<tr>
					<th width="20%">包括公休日: </th>
					<td><input type="text" id="isIncludeRest" name="isIncludeRest" value="${atsHolidayPolicyDetail.isIncludeRest}"  class="inputText" validate="{required:false}"  /></td>
				</tr>
				<tr>
					<th width="20%">包括法定假日: </th>
					<td><input type="text" id="isIncludeLegal" name="isIncludeLegal" value="${atsHolidayPolicyDetail.isIncludeLegal}"  class="inputText" validate="{required:false}"  /></td>
				</tr>
				<tr>
					<th width="20%">描述: </th>
					<td><input type="text" id="memo" name="memo" value="${atsHolidayPolicyDetail.memo}"  class="inputText" validate="{required:false,maxlength:500}"  /></td>
				</tr>
			</table>
			<input type="hidden" name="id" value="${atsHolidayPolicyDetail.id}" />
		    <input type="hidden" name="saveData" id="saveData" />
		    <input type="hidden" name="executeType"  value="start" />
		</form>
		
	</div>
</div>
</body>
</html>
