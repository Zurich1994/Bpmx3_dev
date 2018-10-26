
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title>报表参数管理</title>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">报表参数管理列表</span>
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
		</div>
		</div>
		<div class="panel-body">
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
						<ul class="row">
							<li><span class="label">所属报表:</span><input type="text" name="Q_REPORTID_SL"  class="inputText" value="${param['Q_REPORTID_SL']}"/></li>
							<li><span class="label">参数名称:</span><input type="text" name="Q_PARAMNAME_SL"  class="inputText" value="${param['Q_PARAMNAME_SL']}"/></li>
							<li><span class="label">参数Key:</span><input type="text" name="Q_PARAMKEY_SL"  class="inputText" value="${param['Q_PARAMKEY_SL']}"/></li>
							<li><span class="label">缺省值:</span><input type="text" name="Q_DEFAULTVAL_SL"  class="inputText" value="${param['Q_DEFAULTVAL_SL']}"/></li>
							<li><span class="label">类型:</span><input type="text" name="Q_PARAMTYPE_SL"  class="inputText" value="${param['Q_PARAMTYPE_SL']}"/></li>
							<li><span class="label">系列号:</span><input type="text" name="Q_SN_SL"  class="inputText" value="${param['Q_SN_SL']}"/></li>
							<li><span class="label">PARAMTYPESTR:</span><input type="text" name="Q_PARAMTYPESTR_SL"  class="inputText" value="${param['Q_PARAMTYPESTR_SL']}"/></li>
						</ul>
				</form>
			</div>
			<br/>
			<div class="panel-data">
		    	<c:set var="checkAll"><input type="checkbox" id="chkall"/></c:set>
				<display:table name="reportParamList" id="reportParamItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="true"  class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
	 					<input type="checkbox" class="pk" name="PARAMID" value="${reportParamItem.PARAMID}">
					</display:column>
					<display:column property="REPORTID" title="所属报表" sortable="true" sortName="REPORTID"></display:column>
					<display:column property="PARAMNAME" title="参数名称" sortable="true" sortName="PARAMNAME"></display:column>
					<display:column property="PARAMKEY" title="参数Key" sortable="true" sortName="PARAMKEY"></display:column>
					<display:column property="DEFAULTVAL" title="缺省值" sortable="true" sortName="DEFAULTVAL"></display:column>
					<display:column property="PARAMTYPE" title="类型" sortable="true" sortName="PARAMTYPE"></display:column>
					<display:column property="SN" title="系列号" sortable="true" sortName="SN"></display:column>
					<display:column property="PARAMTYPESTR" title="PARAMTYPESTR" sortable="true" sortName="PARAMTYPESTR" maxLength="80"></display:column>
					<display:column title="管理" media="html" style="width:180px;text-align:center">
						<a href="del.ht?PARAMID=${reportParamItem.PARAMID}" class="link del">删除</a>
						<a href="edit.ht?PARAMID=${reportParamItem.PARAMID}" class="link edit">编辑</a>
						<a href="get.ht?PARAMID=${reportParamItem.PARAMID}" class="linkdetail">明细</a>
					</display:column>
				</display:table>
				<hotent:paging tableId="reportParamItem"/>
			</div>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


