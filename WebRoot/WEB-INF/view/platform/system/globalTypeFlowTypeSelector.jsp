<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title>选择流程分类</title>
<style type="text/css">
	html,body{
		overflow:auto;
	}
	.para-info-table thead tr th{
		text-align:center;
	}
	.para-info-table tbody tr td{
		padding:5px;
	}
	input{
		width:180px;
		height:21px;
	}
</style>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerDialog.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/ProcessUrgeDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/foldBox.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/displaytag.js" ></script>
<script type="text/javascript">

	
	
</script>
</head>
<body>
	<div position="center" >
		<div class="panel">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link search" id="btnSearch"><span></span>查询</a>  
					</div>
				</div>
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="flowTypeSelector">
					<input type="hidden" name="defId" id="bpmDefId" value="${defId }"/>
					<div class="row">
						<span class="label">分类名称:</span><input type="text" name="Q_typeName_SL"  class="inputText" style="width:120px;"/>
					</div>
				</form>
			</div>
			<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
				</c:set>
				
			    <display:table name="globalTypeList" id="globalTypeItem" requestURI="flowTypeSelector.ht" sort="external" 
			    	cellpadding="1" cellspacing="1" export="false"  class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
						  	<input type="checkbox" class="pk" name="typeId" value="${globalTypeItem.typeId}" defSubject="${globalTypeItem.typeName}">
					</display:column>
					<display:column property="typeName"  titleKey="分类名称" sortable="true" sortName="typeName" style="text-align:center"></display:column>
					
				</display:table>
				<hotent:paging tableId="globalTypeItem"></hotent:paging>
			</div>		
		</div>
	</div>
</body>
</html>

