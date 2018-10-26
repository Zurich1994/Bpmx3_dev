<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>信息规范化程度</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>


   <iframe id="report" width="1200" height="500" src="http://192.168.3.114:8080/mas/ReportServer?reportlet=业务应用发展能力1.cpt"></iframe>
   <!--
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">信息规范化程度</span>
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
					     <span class="label">子系统名称:</span><input type="text" name="Q_sysName_S"  class="inputText" />
						<span class="label">新增与修改类操作非规范元素个数:</span><input type="text" name="Q_nmopNonElemNum_S"  class="inputText" />
						<span class="label">新增与修改类操作元素总个数:</span><input type="text" name="Q_nmopElemTotal_S"  class="inputText" />
						<span class="label">业务成熟度:</span><input type="text" name="Q_workSubsysMaturity_S"  class="inputText" />
						<span class="label">信息规范程度:</span><input type="text" name="Q_infoStandGrade_S"  class="inputText" />
						
						
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysinfomationList" id="sysinfomationItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${sysinfomationItem.sysId}">
			  		
				</display:column>      
				<display:column property="sysName" title="子系统名称" sortable="true" sortName="F_SYSNAME"></display:column>
				<display:column property="nmopNonElemNum" title="新增与修改类操作非规范元素个数" sortable="true" sortName="F_NMOPNONELEMNUM"></display:column>
				<display:column property="nmopElemTotal" title="新增与修改类操作元素总个数" sortable="true" sortName="F_NMOPELEMTOTAL"></display:column>
				<display:column property="workSubsysMaturity" title="业务成熟度" sortable="true" sortName="F_WORKSUBSYSMATURITY"></display:column>
				<display:column property="infoStandGrade" title="信息规范程度" sortable="true" sortName="F_INFOSTANDGRADE"></display:column>
				
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${sysinfomationItem.sysId}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${sysinfomationItem.sysId}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${sysinfomationItem.sysId}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysinfomationItem"/>
			
		
		</div> end of panel-body 				
	</div>  end of panel 
     -->
</body>
</html>


