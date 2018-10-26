<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>马尔科夫链表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">马尔科夫链表管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="recommended.ht?defId=${defId}&typeName=${typeName}"><span></span>推荐链路</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="editbyprocess.ht?defId=${defId}&typeName=${typeName}&status=${status}"><span></span>添加</a></div>
					<!--<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加old</a></div>
					--><div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="editbyprocess.ht?defId=${defId}&typeName=${typeName}"><span></span>修改</a></div>
					<!--<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改old</a></div>-->
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update"  action="copy.ht"><span></span>复制</a></div>
					<!--  
				<div class="group"><a class="link add" href="${ctx}/platform/bpm/bpmDefinition/design.ht?defId=${defId}" target="_blank"><span></span>绘图</a></div>	-->
					
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="listbydefid.ht">
				<input type="hidden"  name="defId" value="${defId}">
				<input type="hidden" name="typeName" value="${typeName}">
				<input type="hidden" name="status" value="${status}">
					<div class="row">
						<span class="label">马尔科夫链名称:</span><input type="text" name="Q_markovchainNAME_S"  class="inputText" />
						<span class="label">是否参与仿真:</span><input type="text" name="Q_isSimulation_S"  class="inputText" />
						<span class="label">所属流程:</span><input type="text" name="Q_belongto_S"  class="inputText" />
						<span class="label">依赖马尔科夫链:</span><input type="text" name="Q_dependmark_S"  class="inputText" />
						<span class="label">依赖马尔科夫链id:</span><input type="text" name="Q_dependId_L"  class="inputText" />
						<span class="label">所属流程id:</span><input type="text" name="Q_defid_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="markovchainList" id="markovchainItem" requestURI="listbydefid.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${markovchainItem.id}">
				</display:column>
				<display:column property="markovchainNAME" title="马尔科夫链名称" sortable="true" sortName="F_MARKOVCHAINNAME"></display:column>
				<display:column property="isSimulation" title="是否参与仿真" sortable="true" sortName="F_ISSIMULATION"></display:column>
				<display:column property="belongto" title="所属流程" sortable="true" sortName="F_BELONGTO"></display:column>
				<display:column property="dependmark" title="依赖马尔科夫链" sortable="true" sortName="F_DEPENDMARK"></display:column>
				<display:column property="frequency" title="马尔科夫链发生次数" sortable="true" sortName="F_FREQUENCY"></display:column>
				<display:column property="markovchainDes" title="马尔科夫链详细备注" sortable="true" sortName="F_MARKOVCHAINDES"></display:column>
				<display:column property="probability" title="马尔科夫链的概率" sortable="true" sortName="F_PROBABILITY"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${markovchainItem.id}" class="link del"><span></span>删除</a>
					<!--<a href="deploy.ht?id=${markovchainItem.id}" class="link deploy"><span></span>发布</a>
					--><a href="editbyprocess.ht?defId=${defId}&&id=${markovchainItem.id}&typeName=${typeName}&status=${status}" class="link update"><span></span>设置</a>
					<!--<a href="edit.ht?id=${markovchainItem.id}" class="link edit"><span></span>编辑</a>
					--><a href="get.ht?id=${markovchainItem.id}" class="link detail"><span></span>明细</a>
					<a href="${ctx}/platform/bpm/bpmDefinition/design.ht?defId=${defId}&markid=${markovchainItem.id}&typeName=${typeName}&status=${status}" target="_blank" class="link detail"><span></span>设计</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="markovchainItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
		<input type="hidden" id="typeName" value="${typeName}">
		<input type="hidden" id="status" value="${status}">
</body>
</html>


