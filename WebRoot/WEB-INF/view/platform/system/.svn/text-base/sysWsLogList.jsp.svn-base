<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>cxf调用日志记录管理</title>
<%@include file="/commons/include/get.jsp" %>

</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">cxf调用日志管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<li><span class="label">路径全名:</span><input type="text" name="Q_clsName_SL"  class="inputText" value="${param['Q_clsName_SL']}" /></li>
						<li><span class="label">方法名称:</span><input type="text" name="Q_callName_SL"  class="inputText" value="${param['Q_callName_SL']}" /></li>
						<li><span class="label">创建时间 从:</span> <input  name="Q_begincreatetime_DL"  class="inputText date"  value="${param['Q_begincreatetime_DL']}" /><span class="label">至: </span><input  name="Q_endcreatetime_DG" class="inputText date"  value="${param['Q_endcreatetime_DG']}"/></li>
						<li><span class="label">是否成功:</span><select name="Q_isSuccess_S">
							<option value="">-- 请选择  --</option>
							<option value="1">是</option>
							<option value="0">否</option> 
							</select>
						</li>
					</ul>
				</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysWsLogList" id="sysWsLogItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="true"  class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="logId" value="${sysWsLogItem.logId}">
				</display:column>
				<display:column property="sourceMsg" title="访问来源" sortable="true" sortName="SOURCE_MSG"></display:column>
				<display:column property="clsName" title="访问路径" sortable="true" sortName="CLS_NAME"></display:column>
				<display:column property="callName" title="方法名称" sortable="true" sortName="CALL_NAME"></display:column>
				<display:column property="callDesc" title="方法描述" sortable="true" sortName="CALL_DESC"></display:column>
				<display:column property="impDesc" title="传入参数" sortable="true" sortName="IMP_DESC" maxLength="80"></display:column>
				<display:column property="expDesc" title="返回值" sortable="true" sortName="EXP_DESC" maxLength="80"></display:column>
				<display:column title="创建时间" sortable="true" sortName="createtime">
					<fmt:formatDate value="${sysWsLogItem.createtime}" pattern="yyyy-MM-dd hh:mm:ss" />
				</display:column>
				<display:column title="是否成功" sortable="true" sortName="IS_SUCCESS">
					<c:if test="${sysWsLogItem.isSuccess==1}">
						成功
					</c:if>
					<c:if test="${sysWsLogItem.isSuccess==0}">
						失败
					</c:if>
				</display:column>
				<display:column property="errDetail" title="错误描述" sortable="true" sortName="ERR_DETAIL" maxLength="80"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?logId=${sysWsLogItem.logId}" class="link del">删除</a>
					<a href="get.ht?logId=${sysWsLogItem.logId}" class="link detail">明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysWsLogItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


