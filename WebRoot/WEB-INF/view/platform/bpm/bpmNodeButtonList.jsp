<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>节点按钮管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">

$(function(){
	$(".table-detail").width($(".table-grid").width());
	$(".tbar-title").width($(".table-grid").width());
	$("a.del").unbind("click");
});
function initAll(defId){
	var url =  __ctx+'/platform/bpm/bpmNodeButton/initAll.ht?defId='+defId;
	$.ligerDialog.confirm('确认初始化按钮吗？','提示',function(rtn) {
		if(rtn){
			gotoUrl(url);
		}
	});	
}
function init(defId,nodeId){
	if($.isEmpty(nodeId)) nodeId ='';
	var url =  __ctx+'/platform/bpm/bpmNodeButton/init.ht?defId='+defId+'&nodeId='+nodeId;
	$.ligerDialog.confirm('确认初始化按钮吗？','提示',function(rtn) {
		if(rtn){
			gotoUrl(url);
		}
	});	
}
function clearAll(defId){
	var url =  __ctx+'/platform/bpm/bpmNodeButton/delByDefId.ht?defId='+defId;
	$.ligerDialog.confirm('确认清除该流程定义的所有按钮吗？','提示',function(rtn) {
		if(rtn){
			
			gotoUrl(url);
		}
	});	
}

function del(defId,nodeId){
	if($.isEmpty(nodeId)) nodeId ='';
	var url =  __ctx+'/platform/bpm/bpmNodeButton/deByDefNodeId.ht?defId='+defId+'&nodeId='+nodeId;
	$.ligerDialog.confirm('确认清除该流程定义的所有按钮吗？','提示',function(rtn) {
		if(rtn){
			gotoUrl(url);
		}
	});	
}
function gotoUrl(url){
	if($.browser.msie){
		$.gotoDialogPage(url);
	}
	else{
		location.href=url;
	}
}
</script>

</head>
<body>
    
    <jsp:include page="incDefinitionHead.jsp">
   		<jsp:param value="节点操作按钮" name="title"/>
	</jsp:include>
<div class="panel-container">
    <f:tab curTab="button" tabName="flow"/>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">流程任务审批按钮设置</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link init"  href="javascript:;" onclick="initAll('${bpmDefinition.defId}');"><span></span>初始化全部按钮</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del" href="javascript:;" onclick="clearAll('${bpmDefinition.defId}')"><span></span>清除按钮配置</a></div>
				</div>
			</div>
		</div>
		</div>
		<div class="panel-body" >
			
				<form action="save.ht" method="post" id="dataForm">
				    <input type="hidden" name="defId" value="${bpmDefinition.defId}"/>
							<!-- 节点对应表 -->
					    <table cellpadding="1" cellspacing="1" class="table-grid table-list">
					    <thead>
							<tr>
								<th>序号</th>
								<th>节点名</th>
								<th>类型</th>
								<th>
									操作按钮
								</th>
								<th>
									管理
								</th>
							</tr>
							</thead>
							<tr>
									<td>0</td>
									<td>启动流程</td>
									<td style="color:red">起始节点	</td>
									<td>
										<c:set var="btnList" value="${btnMap.start}"></c:set>
										<c:set var="flag" value="0"></c:set>
										<!-- 启动流程 -->
										<c:choose>
											<c:when test="${empty btnList }">
												<c:forEach items="${startBtnList}" var="btn" varStatus="status" >
													${btn.text}<c:if test="${!status.last}">,</c:if>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<c:forEach items="${btnList}" var="btn" varStatus="status" >
															${btn.btnname}<c:if test="${!status.last}">,</c:if>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</td>
                                    <td >
										<a class="link edit" href="getByNode.ht?defId=${bpmDefinition.defId}">编辑</a>
										
										<a class="link init" href="init.ht?defId=${bpmDefinition.defId}">初始化</a>
										
										<a  class="link del"  href="deByDefNodeId.ht?defId=${bpmDefinition.defId}">删除</a>
									</td>
							</tr>
							
							
							<c:forEach items="${bpmNodeSetList}" var="item" varStatus="status">
							<tr>
								<td>
									${status.index +1}
								</td>
								<td>
									<input type="hidden" name="nodeId" value="${item.nodeId}"/>
									<input type="hidden" name="nodeName" value="${item.nodeName}"/>${item.nodeName}
								</td>
								<td>
									<c:choose>
										<c:when test="${taskMap[item.nodeId].isSignNode }"><span class="red">会签节点</span></c:when>
										<c:when test="${taskMap[item.nodeId].isSubProcess or taskMap[item.nodeId].isCallActivity}"><span class="red">子流程</span></c:when>
										<c:otherwise><span class="green">普通节点</span></c:otherwise>
									</c:choose>
								</td>
								<td nowrap="nowrap">
									<c:set var="btnList" value="${btnMap[item.nodeId] }"></c:set>
									<c:choose>
										<c:when test="${empty btnList }">
											<c:choose>
												<c:when test="${taskMap[item.nodeId].isSignNode}"><!-- 表示会签节点 -->
													<c:forEach items="${signBtnList}" var="btn" varStatus="status" >
														${btn.text}<c:if test="${!status.last}">,</c:if>
													</c:forEach>
												</c:when>
												<c:when test="${taskMap[item.nodeId].isFirstNode }"><!-- 表示第一个节点 -->
													<c:forEach items="${firstNodeBtnList}" var="btn" varStatus="status" >
														${btn.text}<c:if test="${!status.last}">,</c:if>
													</c:forEach>
												</c:when>
												<c:when test="${taskMap[item.nodeId].isSubProcess or taskMap[item.nodeId].isCallActivity}" ><!-- 子流程-->
													&nbsp;
												</c:when>
												<c:otherwise>
													<c:forEach items="${commonBtnList}" var="btn" varStatus="status" >
														${btn.text}<c:if test="${!status.last}">,</c:if>
													</c:forEach>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<c:forEach items="${btnList}" var="btn" varStatus="status" >
												${btn.btnname}<c:if test="${!status.last}">,</c:if>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									<c:choose>
										<c:when test="${taskMap[item.nodeId].isSubProcess or taskMap[item.nodeId].isCallActivity}" >
												&nbsp;	
										</c:when>
										<c:otherwise>
											<a class="link edit" href="getByNode.ht?defId=${bpmDefinition.defId}&nodeId=${item.nodeId}">编辑</a>
											<a class="link init" href="javascript:;" onclick="init('${bpmDefinition.defId}','${item.nodeId}')">初始化</a>
											<a  class="link del" href="javascript:;" onclick="del('${bpmDefinition.defId}','${item.nodeId}')">删除</a>
										</c:otherwise>
									</c:choose>
								</td>
								
							</tr>
							</c:forEach>
						</table>
						
				</form>
			
		</div>				
</div>	
</div>
</body>
</html>





