<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>系统错误日志管理</title>
<%@include file="/commons/include/get.jsp" %>

</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">系统错误日志管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
					<li>
						<span class="label">错误码:</span><input type="text" name="Q_id_L"  class="inputText" value="${param['Q_id_L']}"/>
					</li>
					<li>
						<span class="label">用户:</span><input type="text" name="Q_account_SL"  class="inputText"  value="${param['Q_account_SL']}" />
					</li>
					<li>
						<span class="label">IP地址:</span><input type="text" name="Q_ip_SL"  class="inputText" value="${param['Q_ip_SL']}" />
					</li>
					<li>					
						<span class="label">错误URL:</span><input type="text" name="Q_errorurl_SL"  class="inputText" value="${param['Q_errorurl_SL']}" />
					</li>
					<div class="row_date">
					<li>
						<span class="label">错误日期 从:</span><input  name="Q_beginerrordate_DL"  class="inputText date" value="${param['Q_beginerrordate_DL']}" />
					</li>
					<li>
						<span class="label">至: </span><input  name="Q_enderrordate_DG" class="inputText date"  value="${param['Q_enderrordate_DG']}" />
					</li>
					</div>
					</ul>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysErrorLogList" id="sysErrorLogItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${sysErrorLogItem.id}">
				</display:column>
				<display:column property="id" title="错误码" sortable="true" sortName="id" maxLength="80"></display:column>
				<display:column property="account" title="用户" sortable="true" sortName="account"></display:column>
				<display:column property="ip" title="IP地址" sortable="true" sortName="ip"></display:column>
				<display:column property="errorurl" title="错误URL" sortable="true" sortName="errorurl" maxLength="80"></display:column>
				<display:column  title="错误日期" sortable="true" sortName="errordate">
					<fmt:formatDate value="${sysErrorLogItem.errordate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</display:column>
				<display:column title="管理" media="html" style="width:100px;line-height:21px;">
					<a href="del.ht?id=${sysErrorLogItem.id}" class="link del">删除</a>
					<a href="get.ht?id=${sysErrorLogItem.id}" class="link detail">明细</a>
					
				</display:column>
			</display:table>
			<hotent:paging tableId="sysErrorLogItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


