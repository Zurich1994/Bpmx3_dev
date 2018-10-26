<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>学生考勤表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">学生考勤表管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">日期 从:</span> <input  name="Q_beginrq_DL"  class="inputText date" />
						<span class="label">至: </span><input  name="Q_endrq_DG" class="inputText date" />
						<span class="label">出勤表:</span><input type="text" name="Q_kqby_S"  class="inputText" />
						<span class="label">加班表:</span><input type="text" name="Q_kqbe_S"  class="inputText" />
						<span class="label">备注:</span><input type="text" name="Q_bz_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="xskqbList" id="xskqbItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${xskqbItem.id}">
				</display:column>
				<display:column  title="日期" sortable="true" sortName="F_RQ">
					<fmt:formatDate value="${xskqbItem.rq}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column property="kqby" title="出勤表" sortable="true" sortName="F_KQBY"></display:column>
				<display:column property="kqbe" title="加班表 " sortable="true" sortName="F_KQBE"></display:column>
				<display:column property="bz" title="备注" sortable="true" sortName="F_BZ"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${xskqbItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${xskqbItem.id}" class="link edit"><span></span>合并</a>
					<a href="get.ht?id=${xskqbItem.id}" class="link detail"><span></span>考勤明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="xskqbItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


