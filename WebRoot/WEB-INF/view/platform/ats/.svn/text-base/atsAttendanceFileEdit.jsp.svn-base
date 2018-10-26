<%--
	time:2015-05-20 09:11:05
	desc:edit the 考勤档案
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 考勤档案</title>
	<%@include file="/commons/include/form.jsp" %>
<f:link href="listEdit.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/ats/AtsDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#atsAttendanceFileForm").attr("action","save.ht");
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
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.href = window.location.href;
					}else{
						window.location.href = "${ctx}/platform/ats/atsAttendanceFile/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
		function selectUser(){
			UserDialog({isSingle:true,callback:function(userId,userName,emails,mobiles,rtn){
				$('#userId').val(userId);
				$('#userName').val(userName);
				$('#orgName').val(rtn.orgNames);
				$('#cardNumber').val(rtn.accounts);
				
			}});
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
			AtsHolidayPolicyDialog({isSingle:true,callback:function(rtn){
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
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${atsAttendanceFile.id !=null}">
			        <span class="tbar-label"><span></span>编辑考勤档案</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加考勤档案</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:void(0);"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="atsAttendanceFileForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">用户: </th>
					<td>
						<input type="hidden" id="userId" name="userId" value="${atsAttendanceFile.userId}"  />
						<input type="text" id="userName"  value="${atsAttendanceFile.userName}"  readonly="readonly" validate="{required:true}"/>
						<a href="javascript:void(0);" onclick="selectUser()" class="button"><span>选 择...</span></a>
					</td>
					<th width="20%">组织: </th>
					<td>
						<input type="text" id="orgName"  value="${atsAttendanceFile.orgName}" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th width="20%">考勤卡号: </th>
					<td><input type="text" id="cardNumber" name="cardNumber" value="${atsAttendanceFile.cardNumber}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
					<th width="20%">打卡考勤: </th>
					<td>
						<select id="isAttendance" name="isAttendance" >
							<option value="1" <c:if test="${atsAttendanceFile.isAttendance == 1}">selected="selected"</c:if>>是</option>
							<option value="0" <c:if test="${atsAttendanceFile.isAttendance == 0}">selected="selected"</c:if>>否</option>
						</select>
					</td>
				</tr>
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
				</tr>
				<tr>
					<th width="20%">默认班次: </th>
					<td colspan="3">
						<input type="hidden" id="defaultShift" name="defaultShift" value="${atsAttendanceFile.defaultShift}"  />
						<input type="text" id="defaultShiftName" value="${atsAttendanceFile.defaultShiftName}"  readonly="readonly" validate="{required:true}"/>
						<a href="javascript:void(0);" onclick="selectShift()" class="button"><span>选 择...</span></a>
					</td>
				</tr>
			</table>
			<input type="hidden" name="id" value="${atsAttendanceFile.id}" />
		</form>
		
	</div>
</div>
</body>
</html>
