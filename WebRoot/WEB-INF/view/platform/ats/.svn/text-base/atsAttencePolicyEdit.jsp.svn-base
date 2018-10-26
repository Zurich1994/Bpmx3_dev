<%--
	time:2015-05-17 20:54:19
	desc:edit the 考勤制度
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 考勤制度</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/ats/AtsDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#atsAttencePolicyForm").attr("action","save.ht");
				submitForm();
			});
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#atsAttencePolicyForm').form();
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
						window.location.href = "${ctx}/platform/ats/atsAttencePolicy/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		function selectWorkCalendar(){
			AtsWorkCalendarDialog({callback:function(rtn){
				$('#workCalendar').val(rtn.id);
				$('#workCalendarName').val(rtn.name);
			}});
		}
		
		function selectAttenceCycle(){
			AtsAttenceCycleDialog({callback:function(rtn){
				$('#attenceCycle').val(rtn.id);
				$('#attenceCycleName').val(rtn.name);
			}});
		}
		
		function selectOrg(){
			OrgDialog({isSingle:true,callback:function(orgId,orgName){
				$('#orgId').val(orgId);
				$('#orgName').val(orgName);
			}});
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${atsAttencePolicy.id !=null}">
			        <span class="tbar-label"><span></span>编辑考勤制度</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加考勤制度</span>
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
		<form id="atsAttencePolicyForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">编码: </th>
					<td><input type="text" id="code" name="code" value="${atsAttencePolicy.code}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
					<th width="20%">名称: </th>
					<td><input type="text" id="name" name="name" value="${atsAttencePolicy.name}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>	
				</tr>
				<tr>
					<th width="20%">工作日历: </th>
					<td>
						<input type="hidden" id="workCalendar" name="workCalendar" value="${atsAttencePolicy.workCalendar}" />
						<input type="text" id="workCalendarName"  value="${atsAttencePolicy.workCalendarName}"  validate="{required:true}"  readonly="readonly"/>
						<a href="javascript:;" onclick="selectWorkCalendar()" class="button"><span>选 择...</span></a>
					</td>
					<th width="20%">考勤周期: </th>
					<td>
						<input type="hidden" id="attenceCycle" name="attenceCycle" value="${atsAttencePolicy.attenceCycle}" />
						<input type="text" id="attenceCycleName"  value="${atsAttencePolicy.attenceCycleName}" validate="{required:true}"  readonly="readonly"/>
						<a href="javascript:;" onclick="selectAttenceCycle()" class="button"><span>选 择...</span></a>
					</td>
				</tr>
				<tr>
					<th width="20%">所属组织: </th>
					<td>
						<input type="hidden" id="orgId" name="orgId" value="${atsAttencePolicy.orgId}" />
						<input type="text" id="orgName"  value="${atsAttencePolicy.orgName}"  validate="{required:true}"  readonly="readonly"/>
						<a href="javascript:;" onclick="selectOrg()" class="button"><span>选 择...</span></a>
					</td>
					<th width="20%">是否默认: </th>
					<td>
						<input type="checkbox" id="isDefault" name="isDefault" value="1"   <c:if test="${atsAttencePolicy.isDefault ==1}"> checked="checked"</c:if>  />
					</td>
				</tr>
				<tr>
					<th width="20%">描述: </th>
					<td colspan="3">
						<textarea rows="3" cols="5" id="memo" name="memo"  class="inputText">${atsAttencePolicy.memo}</textarea>
					</td>
				</tr>
				<tr>
					<th width="20%">每周工作时数(时): </th>
					<td><input type="text" id="weekHour" name="weekHour" value="${atsAttencePolicy.weekHour}"  class="inputText" validate="{required:true}"  /></td>
					<th width="20%">每天工作时数(时): </th>
					<td><input type="text" id="daysHour" name="daysHour" value="${atsAttencePolicy.daysHour}"  class="inputText" validate="{required:true}"  /></td>
				
				</tr>
				<tr>
					<th width="20%">月标准工作天数(天): </th>
					<td colspan="3"><input type="text" id="monthDay" name="monthDay" value="${atsAttencePolicy.monthDay}"  class="inputText" validate="{required:true}"  /></td>
				</tr>
				<tr>
			
					<th width="20%">每段迟到允许值(分): </th>
					<td><input type="text" id="lateAllow" name="lateAllow" value="${atsAttencePolicy.lateAllow}"  class="inputText" validate="{required:true,number:true,maxIntLen:10}"  /></td>
					<th width="20%">每段早退允许值(分): </th>
					<td><input type="text" id="leaveAllow" name="leaveAllow" value="${atsAttencePolicy.leaveAllow}"  class="inputText" validate="{required:true,number:true,maxIntLen:10}"  /></td>
			
				</tr>
				<tr>
					<th width="20%">旷工起始值(分): </th>
					<td><input type="text" id="absentAllow" name="absentAllow" value="${atsAttencePolicy.absentAllow}"  class="inputText" validate="{required:true,number:true,maxIntLen:10}"  /></td>
					<th width="20%">每段早退起始值(分): </th>
					<td><input type="text" id="leaveStart" name="leaveStart" value="${atsAttencePolicy.leaveStart}"  class="inputText" validate="{required:true,number:true,maxIntLen:10}"  /></td>
				</tr>
				<tr>
					<th width="20%">加班起始值(分): </th>
					<td><input type="text" id="otStart" name="otStart" value="${atsAttencePolicy.otStart}"  class="inputText" validate="{required:true,number:true,maxIntLen:10}"  /></td>
				</tr>
				<tr>
					<th width="20%">班前无需加班单: </th>
					<td>
						<input type="checkbox" id="preNotBill" name="preNotBill" value="1"   <c:if test="${atsAttencePolicy.preNotBill ==1}"> checked="checked"</c:if>  />
					</td>
			
					<th width="20%">班后无需加班单: </th>
					<td>
						<input type="checkbox" id="afterNotBill" name="afterNotBill" value="1"   <c:if test="${atsAttencePolicy.afterNotBill ==1}"> checked="checked"</c:if>  />
					</td>
				</tr>
			</table>
			<input type="hidden" name="id" value="${atsAttencePolicy.id}" />
		</form>
		
	</div>
</div>
</body>
</html>
