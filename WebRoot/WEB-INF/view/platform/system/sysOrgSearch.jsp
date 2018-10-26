<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>组织架构查询</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="search.ht">
					<ul class="row">
						<li><span class="label">组织名称:</span><input type="text" name="Q_orgName_SL"  class="inputText" value="${param['Q_orgName_SL']}"/></li>
					</ul>
				</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
		    <display:table name="sysOrgList" id="sysOrgItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column property="orgName" title="组织名称" sortable="true" sortName="orgName"></display:column>
				<display:column property="orgDesc" title="备注" sortable="true" sortName="orgDesc"></display:column>
				<display:column title="管理" media="html" style="width:150px">
					<a href="javascript:;" class="link show" onclick="window.parent.setLocation('${sysOrgItem.orgId}')">定位</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysOrgItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


