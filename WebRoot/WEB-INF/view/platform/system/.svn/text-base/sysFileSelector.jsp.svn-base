<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/get.jsp"%>
<title>文件选择</title>
<style type="text/css">
.file_name{
	text-decoration:none;
	color:black;
}
</style>
<link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.qtip.js" ></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerWindow.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ImageQtip.js" ></script>
<script type="text/javascript">
	$(function() {

		$("#sysFileItem  tr.even,#sysFileItem tr.odd").bind('click', function() {
			var trObj=$(this);
			var obj=$(":checkbox[name='fileId']",trObj);
			if(obj.length>0){
				window.parent.selectMulti(obj);
			}
			
		});
	});
</script>
</head>
<body >
	<div class="panel">
		<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">文件选择列表</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link search" ><span></span>查询</a></div>
			</div>	
		</div>
		</div>
		<div class="panel-search" >
			<form action="selector.ht?isSingle=${param.isSingle }" id="searchForm" method="POST">
				<input type="hidden" name="typeId" id="typeId" /> 
				<span class="label">文件名:</span> 
				<input type="text" id="fileName" name="Q_fileName_SL" style="width:80px;" maxlength="128" class="inputText" value="${param['Q_fileName_SL']}"/> 
				<span class="label">扩展名:</span> 
				<input type="text" id=ext name="Q_ext_SL" style="width:80px;" maxlength="128" class="inputText" value="${param['Q_ext_SL']}"/>
			</form>
		</div>
	</div>
	<div class="panel-body">
		<c:if test="${isSingle==0}">
			<c:set var="checkAll">
				<input onclick="window.parent.selectAll(this);" type="checkbox" />
			</c:set>
		</c:if>
		
		<display:table name="sysFileList" id="sysFileItem" requestURI="selector.ht" sort="external" cellpadding="1"
			cellspacing="1" export="false" class="table-grid">
			<display:column title="${checkAll}" media="html" style="width:30px;">
				<c:choose>
					<c:when test="${isSingle==0}">
						<input onchange="window.parent.selectMulti(this);" type="checkbox" class="pk" name="fileId" value="${sysFileItem.fileId}">
					</c:when>
					<c:otherwise>
						<input type="radio" class="pk" name="fileId" value="${sysFileItem.fileId}">
					</c:otherwise>
				</c:choose>
				<textarea name="fileData" style="display: none;">{fileId:'${sysFileItem.fileId}',fileName:'${sysFileItem.fileName}.${sysFileItem.ext}'}</textarea>
			</display:column>
			<display:column title="文件名" sortable="true" sortName="fileName">
				${sysFileItem.fileName}.${sysFileItem.ext}
			</display:column>
			<display:column property="ext" title="扩展名" sortable="true" sortName="ext" />
		</display:table>
		<hotent:paging tableId="sysFileItem" showExplain="false"/>
		
	</div>
</body>
</html>


