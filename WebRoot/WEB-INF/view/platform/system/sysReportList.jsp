<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>报表管理</title>
<style type="text/css">
.file_name{
	text-decoration:none;
	color:black;
}
</style>
<%@include file="/commons/include/get.jsp" %>
<link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.qtip.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ImageQtip.js" ></script>
<script type="text/javascript">
	$(function() {
		$("a.file_name").each(function() {
			var path = $(this).attr("path");			
			//图片类型
			if (/\w+.(png|gif|jpg)/gi.test(path)) {
				ImageQtip.drawImg(this,"${ctx}/"+path,{maxWidth:265});
			}
		});
	});
	
	//处理预览
	function previewHandle(reportId){
		window.open('${ctx}/platform/system/sysReport/show.ht?reportId='+reportId,null,'height=700,width=900,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,status=no') ;
	}
</script>
<style type="text/css">
	table th, table td{text-align: center;}
</style>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">报表管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link add" id="btnAdd" href='edit.ht<c:if test="${typeId!=0}">?typeId=${typeId}</c:if>'><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del" action="${ctx}/platform/system/sysReport/del.ht"><span></span>删除</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a href="javascript:;" class="link reset" onclick="$.clearQueryForm();"><span></span>重置</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<c:if test="${not empty param.typeId}">
							<input type="hidden" name="typeId" value="${param.typeId}"/>
						</c:if>
						<li><span class="label">标题:</span><input type="text" name="Q_title_SL"  class="inputText"  value="${param['Q_title_SL']}" />	</li>											
					</ul>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysReportList" id="sysReportItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
				  	<input type="checkbox" class="pk" name="reportId" value="${sysReportItem.reportId}">
				</display:column>
				<display:column property="title" title="标题"></display:column>
				<display:column property="descp" title="描述"></display:column>
				<display:column property="dsName" title="数据源"></display:column>
				<display:column media="html" title="文件名" sortable="true" sortName="fileName">
					<a href="javascript:;" class="file_name" path="${sysReportItem.filePath}">${sysReportItem.fileName}</a>
				</display:column>
				<display:column title="创建时间" sortable="true" sortName="createtime">
					<fmt:formatDate value="${sysReportItem.createtime}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column media="html" title="状态">
				<c:choose>
					<c:when test="${sysReportItem.status=='1' }">已处理</c:when>
					<c:otherwise>未处理</c:otherwise>
				</c:choose>
				</display:column>
				<display:column title="管理" media="html" style="width:180px;text-align:center" class="rowOps">
					<a href="del.ht?reportId=${sysReportItem.reportId}" class="link del">删除</a>
		       		<c:choose>
						<c:when test="${sysReportItem.status=='1' }">
							<a href="javascript:previewHandle('${sysReportItem.reportId}')" class="link detail">预览</a>
							<a href="edit.ht?reportId=${sysReportItem.reportId}" class="link detail">编辑</a>
						</c:when>
						<c:otherwise>
							<a href="edit.ht?reportId=${sysReportItem.reportId}" class="link detail">配置</a>
						</c:otherwise>
					</c:choose>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysReportItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


