<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp" %>
<title>已办事宜</title>
<script type="text/javascript" src="${ctx}/js/hrbeu/platform/tabOperator.js" ></script>
<script type="text/javascript" src="${ctx}/js/hrbeu/platform/bpm/FlowRightDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hrbeu/platform/bpm/FlowUtil.js" ></script>
<script type="text/javascript" src="${ctx}/js/hrbeu/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hrbeu/platform/bpm/SelectUtil.js" ></script>
<script type="text/javascript" src="${ctx}/js/hrbeu/platform/system/UserInfo.js"></script>
<script type="text/javascript">
	function showDetail(obj){
		var url = $(obj).attr("action");
		var url1="${ctx}/platform/bpm/processRun/"+url;
		jQuery.openFullWindow(url1);
	};
	
</script>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">已办事宜</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a href="#" class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
				</div>	
			</div>
			<div class="panel-search">
					<form id="searchForm" method="post" action="alreadyMattersListWithBus.ht">
							<ul class="row">
							<li>
								<span class="label">请求标题</span><input  type="text" name="Q_subject_SUPL"  class="inputText" value="${param['Q_subject_SUPL']}" />						
							</li>
							<li>
								<span class="label">流程名称:</span><input  type="text" name="Q_processName_SUPL"  class="inputText" value="${param['Q_processName_SUPL']}"  />
							
							</li>
							<li>	
								<span class="label">创 建 人:</span>
								<input type="hidden" id="creatorId" name="Q_creatorId_L"  value="${param['Q_creatorId_L']}" />
								<input type="text"   id="creator" name="Q_creator_SL" class="inputText"  value="${param['Q_creator_SL']}" onclick="selectUser('creatorId','creator');" readonly="readonly"  />
								<input type="button" value="..." onclick="selectUser('creatorId','creator');"/>
							</li>
							<div class="row_date">
							<li>
								<span class="label">创建时间&nbsp;从:</span> <input  name="Q_begincreatetime_DL"  class="inputText datePicker" datetype="1"  value="${param['Q_begincreatetime_DL']}" />
							</li>
							<li>
								<span class="label">至: </span><input  name="Q_endcreatetime_DG" class="inputText datePicker" datetype="2" value="${param['Q_endcreatetime_DG']}"/>
							</li>
							</div>
							<li>
								<span class="label">状态:</span><select name="Q_status_SN">
									<option value="">所有</option>
									<option value="1" <c:if test="${param['Q_status_SN']==1}">selected</c:if>>审批中</option>
									<option value="4" <c:if test="${param['Q_status_SN']==4}">selected</c:if>>已撤销</option>
									<option value="5" <c:if test="${param['Q_status_SN']==5}">selected</c:if>>已驳回</option>
									<option value="11" <c:if test="${param['Q_status_SN']==11}">selected</c:if>>重启任务</option>				
								</select>
							</li>
							</ul>
					</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
				<div class='panel-table'>
						<table id="bpmFormDialogTable" cellpadding="1" class="table-grid table-list" cellspacing="1">
							<thead>
							<tr>
							<th>
								<input type="checkbox" id="chkall"/>
							</th>
							<th>请求标题</th>
							<th>状态</th>
							<c:forEach items="${bpmFormDialog.displayList}" var="field">
								<c:set var="sortMark">
									<c:if test="${sortField==field.fieldName}">
										<c:choose>
											<c:when test="${orderSeq=='ASC'}">↑</c:when>
											<c:otherwise>↓</c:otherwise>
										</c:choose>
									</c:if>
								</c:set>
								<th><a href="${baseHref}&newSortField=${field.fieldName}">${field.comment }${sortMark}</a></th>
							</c:forEach>
							</thead>
							<tbody>
								<c:forEach items="${processRunDataList}" var="processRunData" varStatus="status">
									<c:set var="clsName" ><c:choose><c:when test="${status.index %2==0 }">even</c:when><c:otherwise>odd</c:otherwise></c:choose></c:set>
									<tr class="${clsName}">
										<td>
											<input type="checkbox" name="id" class="pk" value="${processRunData.runId}"/>
										</td>
										<td>
												<a name="processDetail" onclick="showDetail(this)" href="#${processRunData.runId}"  action="info.ht?link=1&runId=${processRunData.runId}" title='${processRunData.subject}'>${processRunData.subject}</a>
										</td>
										<td>
										
											<f:processStatus status="${processRunData.status}"></f:processStatus>
										</td>
									
										<c:forEach items="${bpmFormDialog.displayList}" var="field">
											<td>${processRunData.data[field.fieldName] }</td>	
										</c:forEach>
									</tr>
								</c:forEach>
							</tbody>
							</table>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


