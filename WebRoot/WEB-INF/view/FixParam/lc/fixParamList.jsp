<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>固定指标参数表管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">
	function goBackImportData(){
		alert("goBack");
		var processId = $("#processId").val();
		alert(processId);
		window.location.href = "${ctx}/ImportData/lc/importData/edit.ht?processId="+processId;
	}
</script>

</head>
<body>
	<param name="movie" value = "lineshow.swf" />
	<object type ="application/x-shockwave-flash" data="lineshow.swf" width="500px" height="500px"></object>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">固定指标参数表管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a style="display: none;" class="link search" id="btnSearch" href="javascript:void(0);" onclick="goBackImportData()"><span></span>返回</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a  class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a  class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a  class="link del"  action="del.ht"><span></span>删除</a></div>
					<div class="group"><a  class="link back" href="javascript:void(0);" onclick="goBackImportData()"><span></span>返回</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" >
					
					<div class="row">
					</div>
				</form>
			</div>
			<input type="text" id="processId" name="processId" value="<%=request.getParameter("processId") %>" readonly="readonly" />
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="fixParamList"  id="fixParamItem"  sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${fixParamItem.id}">
				</display:column>
				<display:column property="fix_param_type" title="固定指标类型" sortable="true" sortName="F_FIX_PARAM_TYPE"></display:column>
				<display:column property="fix_param_name" title="固定指标名" sortable="true" sortName="F_FIX_PARAM_NAME"></display:column>
				<display:column property="fix_param_value" title="固定指标值" sortable="true" sortName="F_FIX_PARAM_VALUE"></display:column>
				
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${fixParamItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${fixParamItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${fixParamItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="fixParamItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


