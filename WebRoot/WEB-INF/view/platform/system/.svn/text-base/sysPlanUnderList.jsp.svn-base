<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title>日程管理列表</title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysPlanScript.js"></script>
</head>
<body>
	
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">>日程管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="${ctx}/platform/system/sysPlan/underList.ht">
					<input type="hidden" id="queryUserId" name="queryUserId" value="${queryUserId}" />
					<ul class="row">
						<li><span class="label">任务名称:</span><input type="text" name="Q_taskName_SL"  class="inputText" value="${param['Q_taskName_SL']}"/></<li>
						
					
							<li>
								<span class="label">开始时间 从:</span> <input  name="Q_beginStartTime_DL"  class="inputText date" value="${param['Q_beginStartTime_DL']}"/>
							</li>
							<li>
								<span class="label">至: </span><input  name="Q_endStartTime_DG" class="inputText date" value="${param['Q_endStartTime_DG']}">
							</li>
				
						
						<li><span class="label">项目名称:</span><input type="text" name="Q_projectName_SL"  class="inputText" value="${param['Q_projectName_SL']}"/></<li>
						
						
							<li>
								<span class="label">结束时间 从:</span> <input  name="Q_beginEndTime_DL"  class="inputText date" value="${param['Q_beginEndTime_DL']}"/>
							</li>
							<li>
								<span class="label">至: </span><input  name="Q_endEndTime_DG" class="inputText date" value="${param['Q_endEndTime_DG']}">
							</li>
					
						
						<li><span class="label">下属名称:</span><input type="text" name="Q_charge_SL"  class="inputText" value="${param['Q_charge_SL']}"/></<li>
						
					</ul>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysPlanList" id="sysPlan" requestURI="${ctx}/platform/system/sysPlan/underList.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${sysPlan.id}">
				</display:column>
				<display:column  title="任务名称" sortable="true" sortName="TASKNAME">
						<a class="link" href="${ctx}/platform/system/sysPlan/subscribeGet.ht?type=subscribe&id=${sysPlan.id}&subscribeId=${sysPlan.subscribeId}&queryUserId=${queryUserId}" style="color:blue;">${sysPlan.taskName}</a> 
				</display:column>
				<display:column property="projectName" title="项目名称" sortable="true" sortName="PROJECTNAME"></display:column>
				<display:column property="charge" title="负责人（下属）" sortable="true" sortName="CHARGE"></display:column>
				<display:column  title="开始时间" sortable="true" sortName="STARTTIME">
						<fmt:formatDate value='${sysPlan.startTime}' pattern='yyyy-MM-dd HH:mm:ss'/>
				</display:column>
				<display:column  title="结束时间" sortable="true" sortName="ENDTIME">
						<fmt:formatDate value='${sysPlan.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>
				</display:column>
				<display:column title="任务状态" sortable="true" sortName="RATE" >
					<c:choose>
						<c:when test="${sysPlan.rate == null || sysPlan.rate == ''|| sysPlan.rate == 0}">未开始</c:when>
						<c:when test="${sysPlan.rate == 100}">已完成</c:when>
						<c:otherwise>完成${sysPlan.rate}%</c:otherwise>
					</c:choose>
				</display:column>
				
				<display:column title="管理" media="html" style="width:100px;text-align:center" > <!--  class="rowOps" -->

					<a href="${ctx}/platform/system/sysPlan/subscribeGet.ht?type=subscribe&id=${sysPlan.id}&subscribeId=${sysPlan.subscribeId}&queryUserId=${queryUserId}" class="link detail">明细</a>
					
					<c:choose>
						<c:when test="${sysPlan.subscribeId == null||sysPlan.subscribeId == ''||sysPlan.subscribeId == '0'}">
							<a href="javascript:;" onclick="javascript:subscribeSysPlan('${sysPlan.id}')" class="link run">订阅</a>
						</c:when>
						<c:otherwise>
							<a href="javascript:;" onclick="javascript:cancelSysPlan('${sysPlan.subscribeId}')" class="link close">退订</a>
						</c:otherwise>
					</c:choose> 
				    
				</display:column>
			</display:table>
			<hotent:paging tableId="sysPlanItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


