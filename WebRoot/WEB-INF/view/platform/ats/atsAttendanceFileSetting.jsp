<%--
	time:2015-05-20 09:11:05
	desc:edit the 考勤档案
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>新增 考勤档案</title>
	<%@include file="/commons/include/form.jsp" %>
<f:link href="listEdit.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/ats/AtsDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript">
	/*KILLDIALOG*/
		var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
		$(function() {
			$("a.save").click(function() {
				$("#atsAttendanceFileForm").attr("action","saveAddSetting.ht");
				var aryId=dialog.get("params");
				$("#aryId").val(aryId);
				submitForm();
			});
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#atsAttendanceFileForm').form();
			frm.ajaxForm(options);
			if(frm.valid()){
				frm.submit();
			}
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.alert("保存成功！","提示信息", function(rtn) {
					dialog.get('sucCall')(true);
					dialog.close();
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
		function selectAttencePolicy(){
			AtsAttencePolicyDialog({
				isSingle:true,
				callback:function(rtn){
					$('#attencePolicy').val(rtn.id);
					$('#attencePolicyName').val(rtn.name);
			}});
		}
		
		function selectHolidayPolicy(){
			AtsHolidayPolicyDialog({
				isSingle:true,
				callback:function(rtn){
				$('#holidayPolicy').val(rtn.id);
				$('#holidayPolicyName').val(rtn.name);
			}});
		}
		
		function selectShift(){
			AtsShiftInfoDialog({
				isSingle:true,
				callback:function(rtn){
				$('#defaultShift').val(rtn.id);
				$('#defaultShiftName').val(rtn.name);
			}});
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:void(0);"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link close" href="javascript:dialog.close();"><span></span>关闭</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="atsAttendanceFileForm" method="post" action="saveAddSetting.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
			
				<tr>
					<th width="20%">考勤制度: </th>
					<td>
						<input type="hidden" id="attencePolicy" name="attencePolicy" value="${atsAttendanceFile.attencePolicy}"  />
						<input type="text" id="attencePolicyName" value="${atsAttendanceFile.attencePolicyName}"  readonly="readonly" validate="{required:true}" />
						<a href="javascript:void(0);" onclick="selectAttencePolicy()" class="button"><span>选 择...</span></a>
					</td>
					<th width="20%">假期制度: </th>
					<td>
						<input type="hidden" id="holidayPolicy" name="holidayPolicy" value="${atsAttendanceFile.holidayPolicy}"  />
						<input type="text" id="holidayPolicyName" value="${atsAttendanceFile.holidayPolicyName}"   readonly="readonly" validate="{required:true}"/>
						<a href="javascript:void(0);" onclick="selectHolidayPolicy()" class="button"><span>选 择...</span></a>
					</td>
				</tr>
				<tr>
					<th width="20%">默认班次: </th>
					<td>
						<input type="hidden" id="defaultShift" name="defaultShift" value="${atsAttendanceFile.defaultShift}"  />
						<input type="text" id="defaultShiftName" value="${atsAttendanceFile.defaultShiftName}"  readonly="readonly" validate="{required:true}"/>
						<a href="javascript:void(0);" onclick="selectShift()" class="button"><span>选 择...</span></a>
					</td>
						<th width="20%">打卡考勤: </th>
					<td>
						<select id="isAttendance" name="isAttendance" >
							<option value="1" <c:if test="${atsAttendanceFile.isAttendance == 1}">selected="selected"</c:if>>是</option>
							<option value="0" <c:if test="${atsAttendanceFile.isAttendance == 0}">selected="selected"</c:if>>否</option>
						</select>
					</td>
				</tr>
			</table>
			<input type="hidden" id="aryId" name="aryId"  />
		</form>
		
	</div>
</div>
</body>
</html>
