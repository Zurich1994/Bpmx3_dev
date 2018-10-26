<%--
	time:2011-11-09 11:20:13
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>添加组织架构</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysOrg"></script>
	<script type="text/javascript" src="${ctx }/js/lg/plugins/htButtons.js" ></script>
	<script type="text/javascript" src="${ctx }/js/lg/plugins/ligerWindow.js" ></script>
	<script type="text/javascript" src="${ctx }/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript">
		$(function() {
			
			var scope="${scope}";
			
			var url="${ctx}/platform/system/sysOrg/get.ht?orgId={0}";
			if(scope=="grade"){
				url="${ctx}/platform/system/sysOrg/getGrade.ht?orgId={0}";
			}
			
			function showRequest(formData, jqForm, options){ 
				return true;
			} 
	
			valid(showRequest,showResp);
			
			
			
			$("a.save").click(function() {
				$('#sysOrgForm').submit();
			});
			
			function showResp(responseText, statusText){
				
				var obj=new com.hotent.form.ResultMessage(responseText);
				
				if(obj.isSuccess()){//成功
				
					var objMsg=eval("(" + obj.getMessage() +")" );
					var orgId=objMsg.orgId;
					var action=objMsg.action;
					var msg=(action=="add")?"添加组织信息成功!":"编辑组织信息成功!";
					$.ligerDialog.success(msg,'提示信息',function(){
						var redirectUrl=String.format(url,orgId);
						parent.$("#viewFrame").attr("src",redirectUrl);
						var selectNode = parent.getSelectNode();
						parent.refreshNode();
					});
			    }else{//失败
			    	$.ligerDialog.err('出错信息',"添加组织信息失败",obj.getMessage());
			    }
			}
			
		});
		
		
		//在sysOrgEdit.jsp调用，为了弹出页面的拖动范围大些，所以写在父页面了
		function addClick()
		{
			UserDialog({callback:function(userIds,fullnames){
				$("#ownUser").val(userIds);
				$("#ownUserName").val(fullnames);	
			},isSingle:false});
		};
		
		//清空
		function reSet(){
			$("#ownUser").val("");
			$("#ownUserName").val("");	
		}
	
	</script>
</head>
<body>
<div class="panel">
      <div class="panel-top">
			<div class="tbar-title" style="height:17px !important">
				<c:choose>
					<c:when test="${sysOrg.orgId==null}">添加组织信息</c:when>
					<c:otherwise>编辑组织信息</c:otherwise>  
				</c:choose>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="sysOrgForm" method="post" action="save.ht">
				
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">维度名称: </th>
							<td>
			         			<input type="hidden" name="demId" value="${demension.demId}"/>
		         				${demension.demName}
			         		</td>
						</tr>
							<tr>
							<th width="20%">上级组织: </th>
							<td>${sysOrg.orgSupName}<input type="hidden" id="orgSupName" value="${sysOrg.orgSupName}" readonly="readonly" style="width:255px !important" class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">组织名称: </th>
							<td><input type="text" id="orgName" name="orgName" value="${sysOrg.orgName}" style="width:255px !important" class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">组织代码: </th>
							<td>
								<c:if test="${sysOrg.code!=null}"><input type="text" id="code" name="code" disabled="disabled" value="${sysOrg.code}" class="inputText"/></c:if>
								<c:if test="${sysOrg.code==null}"><input type="text" id="code" name="code" value="${parentCode}" class="inputText"/></c:if>
							</td>
						</tr>
						<tr>
							<th width="20%">组织类型: </th>
							<td>
								<select id="orgType" name="orgType" class="select">  
									<c:forEach items="${sysOrgTypelist}" var="org" >							 				
					 					 <option value ="${org.id}" <c:if test="${sysOrg.orgType==org.id}">selected="selected"</c:if> >${org.name}</option>												
									</c:forEach> 
			                    </select>
		                    </td>
						</tr>
						<tr>
							<th width="20%">编制人数: </th>
							<td><input  type="text" id="orgStaff" name="orgStaff" value="${sysOrg.orgStaff}" style="width:50px !important" class="inputText"/></td>
						</tr>
						<%--
						<tr>
							<th width="20%">主要负责人:</th>
							<td>
	                        <input type="text" class="inputText" readonly="readonly" style="width:300px" id="ownUserName" value="${sysOrg.ownUserName}" >
						    <a href="javascript:;" onclick="addClick()" class="link get">选择</a>
						    <a href="javascript:;" onclick="reSet()" class="link clean">清空</a>
						    <input  type="hidden" name="ownUser" id="ownUser" value="${sysOrg.ownUser}">
							</td>
						</tr>	
						 --%>
						<tr>
							<th width="20%">组织描述: </th>
							<td><textarea id="orgDesc" name="orgDesc" cols="30" rows="4"  style="width:258px !important">${sysOrg.orgDesc}</textarea></td>
						</tr>		
					</table>
						
				<input type="hidden" id="orgId" name="orgId" value="${sysOrg.orgId}" />
				<input type="hidden" id="orgSupId" name="orgSupId" value="${sysOrg.orgSupId}"/>
		  </form>
	</div>

</div>

</body>
</html>
