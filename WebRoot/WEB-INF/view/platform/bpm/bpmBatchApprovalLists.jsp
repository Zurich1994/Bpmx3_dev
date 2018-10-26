<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">
	
	

	// 执行任务
	function executeTask(taskId){
		 var url="${ctx}/platform/bpm/task/toStart.ht?taskId="+taskId;
		var rtn = jQuery.openFullWindow(url);
		
	}

	//重启任务
	function restartTask(taskId){
		var url="${ctx}/platform/bpm/task/restartTask.ht?taskId="+taskId;
		var rtn = jQuery.openFullWindow(url);
	}
	
	// 批量审批
	function batOperator(){
		var aryId = $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");
		var len=aryId.length;
		if(len==0){
			$.ligerDialog.warn("请选择记录！");
			return;
		}
		var taskIds=new Array();
		$("input[name='id']:checked").each(function(){
			taskIds.push($(this).val());
		});
		var ids=taskIds.join(",");
		var url=__ctx + "/platform/bpm/bpmBatchApproval/dialog.ht?taskIds="+ids;
		url=url.getNewUrl();
		
		DialogUtil.open({
			height:350,
			width: 450,
			title : '批量审批',
			url: url, 
			isResize: true,
			//自定义参数
			sucCall:function(rtn){
				if (rtn){
					parent.location.href=parent.location.href.getNewUrl();
				}
			}
		});
		
	}
	
	// 返回待办审批界面	
	function back(){
		parent.location = "${ctx}/platform/bpm/task/pendingMatters.ht";
	}
	
	
	
</script>
</head>
<body>
	<div class="panel">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><f:a alias="batchApproval" css="link save" id="btnApprove" href="javascript:;" showNoRight="false" onclick="batOperator()"><span></span>批量审批</f:a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" onclick="javascript:back();"><span></span>返回</a></div>
				</div>	
			</div>
		<div class="panel-body" >
			<c:choose>
				<c:when test="${fn:length(dataList)<=0}">
						<c:choose>
							<c:when test="${isDataError}">
								<div style="text-align: center;margin-top: 10%;">数据异常</div>
							</c:when>
							<c:otherwise>
								<div style="text-align: center;margin-top: 10%;">暂无数据</div>
							</c:otherwise>
						</c:choose>
				</c:when>
				<c:otherwise>
					<table cellpadding="1" cellspacing="1" class="table-grid table-list">
					<thead>
						<tr>
							<th width="5%">
								<input type="checkbox" id="chkall">
							</th>
							<th width="20%">流程名称</th>
							<c:forEach items="${fieldList}" var="field">
								<th > ${field }</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${dataList}" var="data" varStatus="status">
								<c:choose>
							    	<c:when test="${status.index%2==0}" >
							            <tr class="odd">  
							        </c:when>  
							        <c:when test="${status.index%2==1}" >  
							            <tr class="even">  
							       </c:when>  
	    						</c:choose> 
								<td>
									<input type="checkbox" class="pk" name="id" value="${data['actTaskId']}">
								</td>
								<td >
									<a name="subject"  href="#" onclick="javascript:executeTask(${data['actTaskId']})" title="${data['actSubject'] }" > ${data['actSubject'] }</a>
								</td>
								<c:forEach items="${fieldNameList}" var="fieldName">
									<td> ${data[fieldName] }</td>
								</c:forEach>
							</tr>
						</c:forEach>
					</tbody>
				</table>
					
				</c:otherwise>
			</c:choose>
		</div>			
	</div> 
</body>
</html>


