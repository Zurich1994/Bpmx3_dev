<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>考勤档案导出模版</title>
<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/ats/AtsDialog.js"></script>
	
	<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)	
	$(function(){
		
		$("#btnExport").click(function(){
			var frm=$('#exportForm').form();
			if(frm.valid()){
				$("#exportForm").submit();
			}
		});
	});
	function selectAttencePolicy(){
		AtsAttencePolicyDialog({
			callback:function(data){
				$("#attencePolicys").val(data.name);
			}
		});
	}
	
	function selectHolidayPolicy(){
		AtsHolidayPolicyDialog({
			callback:function(data){
				$("#holidayPolicys").val(data.name);
			}});
	}
	function selectShiftInfo(){
		AtsShiftInfoDialog({
			callback:function(data){
				$("#shiftNames").val(data.name);
			}
		});
	}
	
	</script>
</head>
<body>
	<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">排班导出</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link export" id="btnExport"><span></span>导出排班模板</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del" onclick="javasrcipt:dialog.close()"><span></span>关闭</a></div>
				</div>	
			</div>
	</div>
	<div class="panel-body">
		<form id="exportForm" name="exportForm" method="post"  action="exportData.ht">
			<div class="row">
				 <table  class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%">考勤制度:</th>
						<td  colspan="3">
							<textarea rows="3" cols="5" id="attencePolicys" name="attencePolicys" validate="{required:true}"></textarea>
							<a href="javascript:;" onclick="selectAttencePolicy()" class="button"><span>选 择...</span></a>
						</td>
					</tr>
					<tr>
						<th width="20%">假期制度</th>
						<td  colspan="3">
							<textarea rows="3" cols="5" id="holidayPolicys" name="holidayPolicys" validate="{required:true}"></textarea>
							<a href="javascript:;" onclick="selectHolidayPolicy()" class="button"><span>选 择...</span></a>
						</td>
					</tr>
					<tr>
						<th width="20%">班次</th>
						<td  colspan="3">
							<textarea rows="3" cols="5" id="shiftNames" name="shiftNames" validate="{required:true}"></textarea>
							<a href="javascript:;" onclick="selectShiftInfo()" class="button"><span>选 择...</span></a>
						</td>
					</tr>
			</table>
			</div>
	    </form>
	</div><!-- end of panel-body -->	
	

		<input type="hidden" name="isTemplate" value="true"/>
	</form>	
</body>
</html>