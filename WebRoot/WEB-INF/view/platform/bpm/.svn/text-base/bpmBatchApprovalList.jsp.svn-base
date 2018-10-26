<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>流程批量审批定义设置管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/BpmDefinitionDialog.js"></script>
<script type="text/javascript">

/**
* 选择流程
*/
function selectFlow(){
	BpmDefinitionDialog({isSingle:true,returnDefKey:true,callback:function(defIds,subjects,defKeys){
		$("#defKey").val(defKeys);
		$("#subject").val(subjects);

	}});
}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">流程批量审批定义设置管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">流程定义:</span>
						<input type="hidden" id="defKey" name="Q_defKey_SL"  class="inputText" />
							<input type="text" id="subject" name="subject"  class="inputText" />
							<a onclick="selectFlow()" class="button" href="javascript:void(0);"><span class="icon ok"></span><span>选择</span></a>
						</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="bpmBatchApprovalList" id="bpmBatchApprovalItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${bpmBatchApprovalItem.id}">
				</display:column>
				<display:column property="subject" title="流程定义" sortable="true" sortName="DEF_KEY" maxLength="80"></display:column>
				<display:column property="nodeName" title="节点名称" sortable="true" sortName="NODE_ID" maxLength="80"></display:column>
				<display:column title="管理" media="html" style="width:50px;text-align:center"  class="rowOps">
					<a href="del.ht?id=${bpmBatchApprovalItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${bpmBatchApprovalItem.id}" class="link edit">编辑</a>
					<a href="get.ht?id=${bpmBatchApprovalItem.id}" class="link detail">明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="bpmBatchApprovalItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


