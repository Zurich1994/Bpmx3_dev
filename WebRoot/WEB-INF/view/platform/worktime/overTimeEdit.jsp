<%--
	time:2012-02-20 09:25:52
	desc:edit the 加班情况
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 加班情况</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx }/js/lg/plugins/htButtons.js" ></script>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerWindow.js" ></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js" ></script>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#overTimeForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					$('#overTimeForm').submit();
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm( obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						this.close();
					}else{
						window.location.href = "${ctx}/platform/worktime/overTime/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
        var __ctx='${ctx}';
     // 用户选择器
        function showUserDlg()
        {
 	        UserDialog({
 		        callback:function(userIds,userNames){
 		        	$('#userId').attr('value',userIds);
 		        	$('#userName').attr('value',userNames);
 		        },
 		       isSingle:true
 	        });
        }
		
        
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
		        <c:when test="${overTime.id !=null}">
		            <span class="tbar-label">编辑加班情况</span>
		        </c:when>
		        <c:otherwise>
		            <span class="tbar-label">添加加班情况</span>
		        </c:otherwise>
		    </c:choose>
			
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
			
				<form id="overTimeForm" method="post" action="save.ht">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">标题: </th>
							<td>
								<input type="text" id="subject" name="subject" 
									value="${overTime.subject}" class="inputText"  size="40"/>
							</td>
						</tr>
						<tr>
							<th width="20%">用户名称: </th>
							<td>
								<ul>
									<li style="float: left;">
										<input type="text" id="userName" name="userName" readonly="readonly"
											value="${overTime.userName}" validate="{required:true}" class="inputText"/>
										<input type="hidden" id="userId" name="userId" value="${overTime.userId}" />
									</li>
									<li style="float: left;">&nbsp;</li>
									<li style="float: left;">
										
										<a href='#' class='button'  onclick="showUserDlg()" ><span >...</span></a>
									</li>
								</ul>
							</td>
						</tr>
						<tr>
							<th width="20%">开始时间: </th>
							<td><input type="text" id="startTime" name="startTime" 
								value="<fmt:formatDate value='${overTime.startTime}' pattern='yyyy-MM-dd HH:mm:ss' type='both' />" 
								class="inputText datetime" validate="{date:true,dateRangeStart:'endTime'}" datetype="datetime"/></td>
						</tr>
						<tr>
							<th width="20%">结束时间: </th>
							<td><input type="text" id="endTime" name="endTime" validate="{date:true,dateRangeEnd:'startTime'}"
								value="<fmt:formatDate value='${overTime.endTime}' pattern='yyyy-MM-dd HH:mm:ss' type='both' />" 
								class="inputText datetime" datetype="datetime" /></td>
						</tr>
						<tr>
							<th width="20%">工作日类型变更: </th>
							<td>
								<select id="workType" name="workType">
									<c:forEach var="type" items="${typelist}">
										<c:if test="${overTime.workType==type.typeId}">
											<option selected="selected" value="${type.typeId}">${type.typeName}</option>
										</c:if>
										<c:if test="${overTime.workType!=type.typeId}">
											<option value="${type.typeId}">${type.typeName}</option>
										</c:if>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th width="20%">描述:</th>
							<td>
								<textarea rows="3" cols="60" name="memo">${overTime.memo}</textarea>
							</td>
						</tr>
						
					</table>
					<input type="hidden" name="id" value="${overTime.id}" />
				</form>
			
	</div>
</div>
</body>
</html>
