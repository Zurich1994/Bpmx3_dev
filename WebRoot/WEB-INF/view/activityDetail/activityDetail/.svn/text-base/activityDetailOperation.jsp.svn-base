<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>业务操作分析表-1</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
<iframe id="report" width="1200" height="500" src="http://192.168.3.114:8080/mas/ReportServer?reportlet=业务操作分析表-1.cpt"></iframe>
	<!--<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">业务操作分析表-1</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">活动名称:</span><input type="text" name="Q_activity_name_S"  class="inputText" />
						<span class="label">作业名:</span><input type="text" name="Q_work_name_S"  class="inputText" />
						<span class="label">资料来源:</span><input type="text" name="Q_data_source_S"  class="inputText" />
						
						<span class="label">作业类别:</span><input type="text" name="Q_data_source_S"  class="inputText" />
						
						<span class="label">作业子系统:</span><input type="text" name="Q_work_subsys_S"  class="inputText" />
						<span class="label">作业实例名:</span><input type="text" name="Q_job_ins_name_S"  class="inputText" />
						<span class="label">节点编号:</span><input type="text" name="Q_activity_num_S"  class="inputText" />
						<span class="label">操作实例名:</span><input type="text" name="Q_ope_ins_name_S"  class="inputText" />
						
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="activityDetail" id="activityDetailItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${activityDetailItem.id}">
				</display:column>
				<display:column property="activity_name" title="活动名称" sortable="true" sortName="F_ACTIVITY_NAME"></display:column>
				<display:column property="work_name" title="作业名" sortable="true" sortName="F_WORK_NAME"></display:column>
				<display:column property="data_source" title="资料来源" sortable="true" sortName="F_DATA_SOURCE"></display:column>
				
				<display:column property="data_source" title="作业类别" sortable="true" sortName="F_DATA_SOURCE"></display:column>
				
				<display:column property="work_subsys" title="作业子系统" sortable="true" sortName="F_WORK_SUBSYS"></display:column>
				<display:column property="job_ins_name" title="作业实例名" sortable="true" sortName="F_JOB_INS_NAME"></display:column>
				<display:column property="activity_num" title="节点编号" sortable="true" sortName="F_ACTIVITY_NUM"></display:column>
				<display:column property="ope_ins_name" title="操作实例名" sortable="true" sortName="F_OPE_INS_NAME"></display:column>
				
				
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${activityDetailItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${activityDetailItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${activityDetailItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="activityDetailItem"/>
		</div> end of panel-body 				
	</div>  end of panel 
--></body>
</html>


