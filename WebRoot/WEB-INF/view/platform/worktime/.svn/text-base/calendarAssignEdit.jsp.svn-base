<%--
	time:2012-02-20 09:25:51
	desc:edit the 日历分配
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>编辑 日历分配</title>
<%@include file="/commons/include/form.jsp"%>
<script type="text/javascript" src="${ctx }/js/lg/plugins/htButtons.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerWindow.js"></script>
<script type="text/javascript"
	src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript"
	src="${ctx }/js/lg/plugins/ligerComboBox.js"></script>
<script type="text/javascript"
	src="${ctx}/servlet/ValidJs?form=calendarAssign"></script>
<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			
			if(${calendarAssign.id==0}){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			
			$("a.save").click(function() {
				$('#calendarAssignForm').submit(); 
				
				$("#calendarAssignForm").resetForm();
			});
			
		});
		
        var __ctx='${ctx}';
        // 用户选择器
        function showUserDlg()
        {
        	var flag = $('#assignType').attr('value');
        	var ids = $('#assignUid').attr('value');
			var names = $('#assignUserName').attr('value');
        	if(flag==1){
    	        UserDialog({
    	        	selectUserIds:ids,
    	        	selectUserNames:names,
    	        	callback:function(userIds,userNames){
    		        	$('#assUserName').attr('value',userNames);
    		        	$('#assignUid').attr('value',userIds);
    		        	$('#assignUserName').attr('value',userNames);
    		        }
    	        });
        	}else{
    	        OrgDialog({
    	        	ids:ids,
    	        	names:names,
    		        callback:function(userIds,userNames){
    		        	$('#assUserName').attr('value',userNames);
    		        	$('#assignUid').attr('value',userIds);
    		        	$('#assignUserName').attr('value',userNames);
    		        }
    	        });
        	}
        }
        
        // 判断选择组织或用户
        function changeOrgOrUser(obj){
        		$('#assUserName').attr('value','');
        		$('#assignUid').attr('value','');
        		$('#assignUserName').attr('value','');
        }
        
	</script>
</head>
<body>

	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">添加日历分配</span>
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

			<form id="calendarAssignForm" method="post" action="save.ht">
				<table class="table-detail" cellpadding="0" cellspacing="0"
					border="0">
					<tr>
						<th width="20%">工作日历:</th>
						<td><select id="canlendarId" name="canlendarId"
							style="width: 10%;">
								<c:forEach var="calItem" items="${callist}">
									<c:choose>
										<c:when test="${calItem.id==calendarAssign.canlendarId}">
											<option value="${calItem.id}" selected="selected">${calItem.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${calItem.id}">${calItem.name}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<th width="20%">被分配者类型:</th>
						<td><select id="assignType" name="assignType"
							style="width: 10%;" onchange="changeOrgOrUser(this)">
								<c:forEach var="typeItem" items="${typelist}">
									<c:choose>
										<c:when test="${typeItem.id==calendarAssign.assignType}">
											<option value="${typeItem.id}" selected="selected">${typeItem.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${typeItem.id}">${typeItem.name}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<th width="20%">被分配的组织或用户名称:</th>
						<td>
							<ul>
								<li style="float: left;"><textarea rows="5" cols="60"
										id="assUserName" name="assUserName" readonly="readonly"
										class="inputText">
										${calendarAssign.assignUserName}
									</textarea> <input type="hidden" id="assignUserName" name="assignUserName"
									value="${calendarAssign.assignUserName}" /> <input
									type="hidden" id="assignUid" name="assignUid"
									value="${calendarAssign.assignId}" /></li>
								<li style="float: left;">&nbsp;</li>
								<li style="float: left;"><a href='#' class='button'
									onclick="showUserDlg()"><span>...</span></a></li>
							</ul>
						</td>
					</tr>
				</table>
				<input type="hidden" name="id" value="${calendarAssign.id}" />
			</form>

		</div>
	</div>
</body>
</html>
