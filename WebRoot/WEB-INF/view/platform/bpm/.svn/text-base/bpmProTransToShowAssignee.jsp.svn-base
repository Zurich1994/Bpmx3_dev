<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title>查看流转人</title>
</head>
<script type="text/javascript">
	function cancel(taskId, userId) {
		var params = {taskId:taskId, userId:userId};
		var url=__ctx + '/platform/bpm/bpmProTransTo/cancelDialog.ht';
		var winArgs="dialogWidth=550px;dialogHeight=300px;help=0;status=0;scroll=0;center=1";
		url=url.getNewUrl();
		/* var rtn=window.showModalDialog(url,params,winArgs);
		if(rtn=="ok"){
			location.href=location.href.getNewUrl();
		} */
		
		/*KILLDIALOG*/
		DialogUtil.open({
			height:300,
			width: 550,
			title : '关闭',
			url: url, 
			isResize: true,
			//自定义参数
			params: params,
			sucCall:function(rtn){
				if(rtn=="ok"){
					location.href=location.href.getNewUrl();
				}
			}
		});
	};
</script>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">查看流转人</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="${ctx}/platform/bpm/bpmProTransTo/mattersList.ht"><span></span>返回</a></div>
				</div>	
			</div>
		</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="bpmProTransToAssigneeList" id="bpmProTransToAssigneeItem" cellpadding="1" cellspacing="1"  class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${bpmProTransToAssigneeItem.userId}"/>
				</display:column>
				<display:column property="userName" title="流转人" style="text-align:center;"></display:column>
				<display:column title="审批状态" style="text-align:center;">
					<c:choose>
						<c:when test="${bpmProTransToAssigneeItem.status==1}">
							<span class="red">正在审批</span>
						</c:when>
						<c:otherwise>
							<span class="green">已审批</span>
						</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="管理" media="html" style="width:150px">
					<c:if test="${not empty bpmProTransToAssigneeItem.parentTaskId}">
						<a href="javascript:;" onclick="cancel('${bpmProTransToAssigneeItem.parentTaskId}','${bpmProTransToAssigneeItem.userId}')" class="link cancel">取消流转</a>
					</c:if>
				</display:column>
			</display:table>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


