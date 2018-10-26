<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>子系统流程节点遍历管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/ImportExportXmlUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/ActivityInformWindow.js" ></script>
<script type="text/javascript">
	
		function nodeSetWindow(nodeId,defId,actDefId)
		{
		//var id=ImportExportXml.getChkValue('pk');
		//alert(nodeId+","+defId+","+actDefId);
		ActivityInformWindow({nodeId:nodeId,defId:defId,actDefId:actDefId});
		}
		function setting()
		{
		var url=__ctx + "/platform/bpm/bpmDefinition/design.ht?defId="+${sysdefnodeergodicList[0].operateDefId}+"&typeName=operationChart1&status="+${sysdefnodeergodicList[0].operateStatus};
		window.open(url);//跳转到下一个页面
		///mas/platform/bpm/bpmDefinition/design.ht?defId=${sysdefnodeergodicItem.defId}&typeName=flowChart&status=${sysdefnodeergodicItem.status}')"${sysdefnodeergodicItem.defName}
		//<a href onclick="window.open('/mas/platform/bpm/bpmDefinition/design.ht?defId=${sysdefnodeergodicItem.operateDefId}&typeName=operationChart&status=${sysdefnodeergodicItem.operateStatus}')">${sysdefnodeergodicItem.operateName}</a>				
						
		}
		
		function ordering(sort)  //排序触发ordering
		{  
		   alert(sort);
		   alert(${sysdefnodeergodicList[0].nodeIdNum});
		   window.location.href = "${ctx}/SysDefNodeErgodic/SysDefNodeErgodic/sysdefnodeergodic/nodetongji.ht?sort="+sort+"&id="+${sysdefnodeergodicList[0].nodeIdNum};
		   
		}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">子系统流程节点遍历管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
				
					<div class="group"><a class="link import" onclick="setting()"><span></span>设计该操作图</a></div>
					<div class="l-bar-separator"></div>
				
					<div class="group"><a class="link refresh" onclick="ordering(3)"><span></span>操作标准排序</a></div>
					<div class="l-bar-separator"></div>
				    <div class="group"><a class="link refresh" onclick="ordering(4)"><span></span>操作依赖排序</a></div>
					<div class="l-bar-separator"></div>
				    
				    
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" >
					<div class="row">
						<span class="label">操作图节点名字:</span><input type="text" name="Q_operateNodeName_S"  class="inputText" />
						
						<span class="label">操作标准:</span><input type="text" name="Q_operateNodeExenum_S"  class="inputText" />
						<span class="label">操作依赖:</span><input type="text" name="Q_operateNodeDependExenum_S"  class="inputText" />
						
						<span class="label">事务图名或表单名:</span><input type="text" name="Q_transactionName_S"  class="inputText" />
						<span class="label">事务图节点或表单或数据包:</span><input type="text" name="Q_transactionNodeName_S"  class="inputText" />
						<span class="label">生成数据包的表:</span><input type="text" name="Q_tableParcelName_S"  class="inputText" />
						<span class="label">生成数据包的表:</span><input type="text" name="Q_tableParcelNodeName_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysdefnodeergodicList" id="sysdefnodeergodicItem" requestURI="nodetongji.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">				
			<display:column title="操作图节点名字" sortName="F_OPERATENODENAME">
					<c:if test="${sysdefnodeergodicItem.operateNodeName!=''}">
						<c:if test="${fn:contains(sysdefnodeergodicItem.operateNodeId, 'ScriptTask')}">
							<a>(脚本任务)${sysdefnodeergodicItem.operateNodeName}</a>
						</c:if>
						<c:if test="${fn:contains(sysdefnodeergodicItem.operateNodeId, 'UserTask')}">
							<a>(用户任务)${sysdefnodeergodicItem.operateNodeName}</a>
						</c:if>
					</c:if>		
				</display:column>
				
				<display:column title="操作标准" sortName="F_OPERATENODEEXENUM">	
					<c:if test="${sysdefnodeergodicItem.operateNodeName!=''}">
						<c:if test="${sort=='3'}">
							<span class="green">${sysdefnodeergodicItem.operateNodeExenum}</span>
						</c:if>					
						<c:if test="${sort!='3'}">
					${sysdefnodeergodicItem.operateNodeExenum}
						</c:if>
					</c:if>
					</display:column>	
					<display:column title="操作依赖" sortName="F_OPERATENODEDEPENDEXENUM">	
					<c:if test="${sysdefnodeergodicItem.operateNodeName!=''}">
						<c:if test="${sort=='4'}">
							<span class="green">${sysdefnodeergodicItem.operateNodeDependExenum}</span>
						</c:if>					
						<c:if test="${sort!='4'}">
					${sysdefnodeergodicItem.operateNodeDependExenum}
						</c:if>	
					</c:if>
					</display:column>	
								
				<display:column title="事务图名或表单" sortName="F_TRANSACTIONNAME">
				<c:if test="${sysdefnodeergodicItem.operateNodeName!=''}">
					<c:if test="${fn:contains(sysdefnodeergodicItem.operateNodeId, 'ScriptTask')}">
	   					
	   						<c:if test="${fn:endsWith(sysdefnodeergodicItem.transactionName, '(0)')}">				  	     
							<a href onclick="window.open('/mas/platform/bpm/bpmDefinition/design.ht?defId=${sysdefnodeergodicItem.transactionDefId}&typeName=transactionChart1&status=${sysdefnodeergodicItem.transactionStatus}')"><span class="red">${sysdefnodeergodicItem.transactionName}(无事务图节点)</span></a>							
							</c:if>
	   						<c:if test="${!fn:endsWith(sysdefnodeergodicItem.transactionName, '(0)')}">				  	     
							<a href onclick="window.open('/mas/platform/bpm/bpmDefinition/design.ht?defId=${sysdefnodeergodicItem.transactionDefId}&typeName=transactionChart1&status=${sysdefnodeergodicItem.transactionStatus}')">${sysdefnodeergodicItem.transactionName}</a>							
							</c:if>
						
						
					</c:if>
					<c:if test="${fn:contains(sysdefnodeergodicItem.operateNodeId, 'UserTask')}">  	     
							<a>${sysdefnodeergodicItem.transactionName}</a>													
					</c:if>	
				
					<c:if test="${sysdefnodeergodicItem.transactionName==''}">	
					  	<c:if test="${fn:contains(sysdefnodeergodicItem.operateNodeId, 'UserTask')}">					     	     
								<span class="red">(未绑定页面)</span>													
						</c:if>	
						<c:if test="${fn:contains(sysdefnodeergodicItem.operateNodeId, 'ScriptTask')}">
								<span class="red">(未绑定事务图)</span>		
						</c:if>	
					</c:if>
				</c:if>
				</display:column>	
				<display:column title="事务图节点或表名或数据包" sortName="F_TRANSACTIONNODENAME">							
					<c:if test="${fn:startsWith(sysdefnodeergodicItem.transactionName, '(表单页面)')}">
							<c:if test="${sysdefnodeergodicItem.transactionNodeName==''}">
								<span class="red">(该页面未绑定表)</span>
							</c:if>
							<c:if test="${sysdefnodeergodicItem.transactionNodeName!=''}">								
								<c:if test="${fn:endsWith(sysdefnodeergodicItem.transactionNodeName, '(0)')}">
									<a><span class="red">${sysdefnodeergodicItem.transactionNodeName}(无字段)</span></a>
								</c:if>
								<c:if test="${!fn:endsWith(sysdefnodeergodicItem.transactionNodeName, '(0)')}">
									<a>${sysdefnodeergodicItem.transactionNodeName}</a>
								</c:if>							
							</c:if>
					</c:if>	
					<c:if test="${sysdefnodeergodicItem.transactionNodeName!=''}">
							<c:if test="${fn:startsWith(sysdefnodeergodicItem.transactionNodeName, '(数据包)')}">
								<a>${sysdefnodeergodicItem.transactionNodeName}</a>
							</c:if>
							<c:if test="${fn:startsWith(sysdefnodeergodicItem.transactionNodeName, '(事务图节点)')}">
								<c:if test="${fn:endsWith(sysdefnodeergodicItem.transactionNodeName, '(0)')}">
								<a><span class="red">${sysdefnodeergodicItem.transactionNodeName}(无方法)</span></a>
								</c:if>
								<c:if test="${!fn:endsWith(sysdefnodeergodicItem.transactionNodeName, '(0)')}">
								<a>${sysdefnodeergodicItem.transactionNodeName}</a>
								</c:if>
							</c:if>
					</c:if>							
				</display:column>
								
				<display:column title="事务图节点表名或表单字段或数据包表名" sortName="F_TABLEPARCELNAME">
				<c:if test="${fn:startsWith(sysdefnodeergodicItem.transactionNodeName, '(数据包)')&&sysdefnodeergodicItem.tableParcelName==''}">
				<span class="red">(该数据未绑定表)</span>
				</c:if>
				<c:if test="${sysdefnodeergodicItem.tableParcelName!=''}">
					<c:if test="${fn:startsWith(sysdefnodeergodicItem.tableParcelName, '(表名)')}">
						<c:if test="${fn:endsWith(sysdefnodeergodicItem.tableParcelName, '(0)')}">	
							<a><span class="red">${sysdefnodeergodicItem.tableParcelName}(无字段)</span></a>
						</c:if>
						<c:if test="${!fn:endsWith(sysdefnodeergodicItem.tableParcelName, '(0)')}">	
							<a>${sysdefnodeergodicItem.tableParcelName}</a>
						</c:if>
					</c:if>
					<c:if test="${fn:startsWith(sysdefnodeergodicItem.tableParcelName, '(字段)')}">
						<a>${sysdefnodeergodicItem.tableParcelName}</a>
					</c:if>
					<c:if test="${fn:startsWith(sysdefnodeergodicItem.tableParcelName, '表名：')}">
						<a>${sysdefnodeergodicItem.tableParcelName}</a>
					</c:if>
				</c:if>
				</display:column>	
				
				<display:column property="tableParcelNodeName" title="字段" sortName="F_TABLEPARCELNODENAME"></display:column>				
			</display:table>
			<hotent:paging tableId="sysdefnodeergodicItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


