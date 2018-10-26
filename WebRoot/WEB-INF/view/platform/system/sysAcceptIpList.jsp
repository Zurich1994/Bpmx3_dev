
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<title>可访问地址管理</title>
	<%@include file="/commons/include/get.jsp" %>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">可访问地址管理列表</span>
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
							<div class="l-bar-separator"></div>
			                <div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
						</div>	
					</div>
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
												<li><span class="label">标题:</span><input type="text" name="Q_title_SL"  class="inputText" value="${param['Q_title_SL']}"/>	</li>											
									</ul>
							</form>
					</div>
				</div>
				</div>
				<div class="panel-body">
					
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="sysAcceptIpList" id="sysAcceptIpItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="acceptId" value="${sysAcceptIpItem.acceptId}">
							</display:column>
							<display:column property="title" title="标题" sortable="true" sortName="title"></display:column>
							<display:column property="startIp" title="开始地址" sortable="true" sortName="startIp"></display:column>
							<display:column property="endIp" title="结束地址" sortable="true" sortName="endIp"></display:column>						
							<display:column title="管理" media="html"  style="width:180px;text-align:center" >
								<f:a alias="delAddress" href="del.ht?acceptId=${sysAcceptIpItem.acceptId}" css="link del">删除</f:a>
								<a href="edit.ht?acceptId=${sysAcceptIpItem.acceptId}" class="link edit">编辑</a>
								<a href="get.ht?acceptId=${sysAcceptIpItem.acceptId}" class="link detail">明细</a>
							</display:column>
						</display:table>
						<hotent:paging tableId="sysAcceptIpItem"/>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


