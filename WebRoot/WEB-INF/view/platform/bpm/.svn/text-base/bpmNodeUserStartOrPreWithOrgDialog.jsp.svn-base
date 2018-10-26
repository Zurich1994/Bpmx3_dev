<%@page pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
	<head>
		<%@include file="/commons/include/form.jsp" %>
		<title>发起人或上个任务执行人的组织</title>
		<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
		<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
		<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js"></script>
		<script type="text/javascript">
			$(function(){
			
			});
			
			function save(){
				var objUserType=$("[name='userType']:checked");
				var objType=$("[name='type']:checked");
				if(objType.length==0){
					$.ligerDialog.warn("请选择人员类型!",'提示');
					return ;
				}
				if(objUserType.length==0){
					$.ligerDialog.warn("请选择组织类型!",'提示');
					return ;
				}
				var varType=objType.val();
				var varTypeName=objType.attr("memo");
				var varUserType=objUserType.val();
				var varUserTypeName=objUserType.attr("memo");
				
				var obj={};
			
				obj.json="{userType:\""+varUserType +"\",type:\""+varType+"\"}";
				obj.description="与"+varUserTypeName +"具有"+varTypeName;
				
				window.returnValue= obj;
				window.close();
			}
			
		</script>
	</head>
	
<body style="overflow: hidden;">
	<div class="panel">
		<div class="hide-panel">
			<div class="panel-top">
				<div class="tbar-title">
				    <span class="tbar-label">人员表单变量</span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a class="link save" id="dataFormSave" onclick="save();" href="javascript:;"><span></span>保存</a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link close" onclick="window.close()" href="javascript:;"><span></span>关闭</a></div>
					</div>
				</div>
			</div>
		</div>
		<div style="text-align: center;padding-top: 10px;">
			
				<table class="table-grid" width="90%">
				 
					<tr>
						<td>人员类型</td>
						<td align="left">
				            <input type="radio" name="userType" value="start" memo="发起人" <c:if test="${userType eq 'start'}">checked="checked"</c:if> />
				                                     发起人
				            <input type="radio" name="userType" value="prev"  memo="上一任务执行人" <c:if test="${userType eq 'prev'}">checked="checked"</c:if> />
				                                   上一任务执行人
						</td> 
					</tr>
					
					<tr>
						<td>组织类型</td>
						<td align="left">
				            <input type="radio" name="type" value="org" memo="相同部门" <c:if test="${type eq 'org'}">checked="checked"</c:if> />
                                                                                     相同部门
                            <input type="radio" name="type" value="job"  memo="相同职务" <c:if test="${type eq 'job'}">checked="checked"</c:if> />
                                                                                    相同职务
                            <input type="radio" name="type" value="pos"  memo="相同岗位" <c:if test="${type eq 'pos'}">checked="checked"</c:if> />
                                                                                   相同岗位
						</td> 
					</tr>
					
					
					
				</table>	
			
			
		</div>
		
	</div>
</body>
</html>