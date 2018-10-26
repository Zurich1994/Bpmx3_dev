<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>网络拓扑图表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">网络拓扑图表管理列表</span>
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
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="bpmnetworkmapList" id="bpmnetworkmapItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${bpmnetworkmapItem.id}">
				</display:column>
				<display:column property="defID" title="ID" sortable="true" sortName="F_DEFID"></display:column>
				<display:column property="typeID" title="类型ID" sortable="true" sortName="F_TYPEID"></display:column>
				<display:column property="subject" title="标题" sortable="true" sortName="F_SUBJECT"></display:column>
				<display:column property="defKey" title="拓扑图key" sortable="true" sortName="F_DEFKEY"></display:column>
				<display:column property="defXml" title="xml文件" sortable="true" sortName="F_DEFXML"></display:column>
				<display:column property="status" title="状态" sortable="true" sortName="F_STATUS"></display:column>
				<display:column  title="创建时间" sortable="true" sortName="F_CREATETIME">
					<fmt:formatDate value="${bpmnetworkmapItem.createTime}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column  title="更新时间" sortable="true" sortName="F_UPDATETIME">
					<fmt:formatDate value="${bpmnetworkmapItem.updateTime}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column property="actDefID" title="actDefID" sortable="true" sortName="F_ACTDEFID"></display:column>
				<display:column property="versionNo" title="版本号" sortable="true" sortName="F_VERSIONNO"></display:column>
				<display:column property="rest" title="预留字段" sortable="true" sortName="F_REST"></display:column>
				<display:column property="rest2" title="预留字段2" sortable="true" sortName="F_REST2"></display:column>
				<display:column property="rest3" title="预留字段3" sortable="true" sortName="F_REST3"></display:column>
				<display:column property="rest4" title="预留字段4" sortable="true" sortName="F_REST4"></display:column>
				<display:column property="rest5" title="预留字段5" sortable="true" sortName="F_REST5"></display:column>
				<display:column property="rest6" title="预留字段6" sortable="true" sortName="F_REST6"></display:column>
				<display:column property="rest7" title="预留字段7" sortable="true" sortName="F_REST7"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${bpmnetworkmapItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${bpmnetworkmapItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${bpmnetworkmapItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="bpmnetworkmapItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


