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
		    <display:table name="markovchainList" id="markovchainItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
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
					<a href="edit.ht?id=${markovchainItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${markovchainItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="markovchainItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


