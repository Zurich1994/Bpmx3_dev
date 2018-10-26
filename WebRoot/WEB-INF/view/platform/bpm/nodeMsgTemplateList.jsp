<%
	//作用:查看某一流程所有版本历史
	//作者:csx
%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>流程定义扩展管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">
	function openEditPage(nodeId,defId,parentDefId){
		DialogUtil.open({
			height:800,
			width: 1000,
			title : '节点消息模板',
			url: __ctx+"/platform/bpm/nodeMsgTemplate/edit.ht?defId="+defId+"&nodeId="+nodeId+"&parentDefId="+parentDefId, 
			isResize: true,
			reset:reset
		});
	}
	
	//充值
	function reset(){
		window.location.reload();
	}
</script>
</head>
<body>
     <jsp:include page="incDefinitionHead.jsp">
	 	<jsp:param value="节点消息模板" name="title"/>
	 </jsp:include>
	 <div class="panel-container">
     	<f:tab curTab="nodeMsgTemp" tabName="flow"/>
		<div class="panel">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link edit" onclick="openEditPage('','${param.defId}','')"><span></span>全局模板</a></div>
					<div class="group"><a class="link reset" id="resetBtn" onclick="reset()"><span></span>刷新</a></div>
				</div>	
			</div>
			<display:table name="nodeMsgTemplateList" id="nodeMsgTemplateItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${nodeMsgTemplateItem.id}">
				</display:column>
				<display:column property="title" title="主题" sortable="true" sortName="TITLE" maxLength="80"></display:column>
				<display:column property="nodeName" title="节点ID" sortable="true" sortName="NODEID" maxLength="80"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${nodeMsgTemplateItem.id}" class="link del">删除</a>
					<a href="javaScript:openEditPage('${nodeMsgTemplateItem.nodeId}','${nodeMsgTemplateItem.defId}','${nodeMsgTemplateItem.parentDefId}')" class="link edit">编辑</a>
				</display:column>
			</display:table>
		</div>	
	</div> <!-- end of panel -->
</body>
</html>





