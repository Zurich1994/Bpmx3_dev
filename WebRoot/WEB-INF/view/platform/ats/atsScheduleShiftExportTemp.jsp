<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>排班导出模版</title>
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
	function selectFile(){
		AtsAttendanceFileDialog({callback:function(data){
			$("#accounts").val(data.account);
			$("#userNames").val(data.userName);
			$("#orgNames").val(data.orgName);
			}
		});
	}
	function selectShiftInfo(){
		AtsShiftInfoDialog({
			callback:function(data){
				$("#shiftIds").val(data.id);
				$("#shiftNames").val(data.name);
			}
		})
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
						<th width="20%">员工</th>
						<td colspan="3">
							<input id="accounts" name="accounts" type="hidden"/>
							<input id="orgNames" name="orgNames" type="hidden"/>
							<textarea rows="3" cols="5" id="userNames" name="userNames" ></textarea>
							<a href="javascript:;" onclick="selectFile()" class="button"><span>选 择...</span></a>
						</td>
					</tr>
					<tr>
						<th width="20%">班次</th>
						<td  colspan="3">
							<input id="shiftIds" name="shiftIds" type="hidden"/>
							<textarea rows="3" cols="5" id="shiftNames" name="shiftNames" validate="{required:true}"></textarea>
							<a href="javascript:;" onclick="selectShiftInfo()" class="button"><span>选 择...</span></a>
						</td>
					</tr>
					<tr>
						<th width="20%">开始时间: </th>
						<td>
							<input type="text" id="startTime" name="startTime"   class="inputText date"  readonly="readonly" validate="{required:true}"/>
						</td>
						<th width="20%">结束时间: </th>
						<td><input type="text" id="endTime" name="endTime"  class="inputText date"  readonly="readonly" validate="{required:true}"/>
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