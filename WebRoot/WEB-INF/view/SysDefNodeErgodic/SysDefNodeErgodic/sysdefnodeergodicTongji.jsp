<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>业务逻辑校验</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/ImportExportXmlUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/ActivityInformWindow.js" ></script>
<script type="text/javascript">
	function setting(){
			//var id=$("#id").val();//有时候好事有时候不好使
		var id=ImportExportXml.getChkValue('pk');
		//alert(id);
		if (id=="")
		{
			$.ligerDialog.warn('还没有选择流程,请选择一项流程定义!','提示信息');
			return ;
		}
		var url=__ctx + "/platform/bpm/bpmDefinition/tongji.ht?id="+id;
		window.open(url);//跳转到下一个页面
		//window.location.href = "${ctx}/platform/bpm/bpmDefinition/tongji.ht?id="+id;	//再笨页面跳转
		//window.location.href = "${ctx}/platform/system/subSystem/tongji.ht?id="+id;
		}
		
		function nodeSetWindow(nodeId,defId,actDefId)
		{
		//var id=ImportExportXml.getChkValue('pk');
		//alert(nodeId+","+defId+","+actDefId);
		ActivityInformWindow({nodeId:nodeId,defId:defId,actDefId:actDefId});
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
				<div class="group"><a class="link import" onclick="setting()"><span></span>进行更多操作</a></div>
					<div class="l-bar-separator"></div>
					
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">子系统编号:</span><input type="text" name="Q_zj_L"  class="inputText" />
						<span class="label">子系统名字:</span><input type="text" name="Q_sysName_S"  class="inputText" />
						<span class="label">流程名字:</span><input type="text" name="Q_defName_S"  class="inputText" />
						<span class="label">节点名字:</span><input type="text" name="Q_nodeName_S"  class="inputText" />
						<span class="label">校验:</span><input type="text" name="Q_setJudge_S"  class="inputText" />
						<span class="label">设置</span><input type="text" name="Q_setUp_S"  class="inputText" />
						<span class="label">节点作业名:</span><input type="text" name="Q_nodeWorkName_S"  class="inputText" />
						<span class="label">所绑定操作图:</span><input type="text" name="Q_operateName_S"  class="inputText" />
						<span class="label">操作图节点名字:</span><input type="text" name="Q_operateNodeName_S"  class="inputText" />
						<span class="label">事务图名或表单名:</span><input type="text" name="Q_transactionName_S"  class="inputText" />
						<span class="label">事务图节点或表单或数据包:</span><input type="text" name="Q_transactionNodeName_S"  class="inputText" />
						<span class="label">生成数据包的表:</span><input type="text" name="Q_tableParcelName_S"  class="inputText" />
						<span class="label">生成数据包的表的列:</span><input type="text" name="Q_tableParcelNodeName_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysdefnodeergodicList" id="sysdefnodeergodicItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<c:if test="${sysdefnodeergodicItem.defId!=''&&sysdefnodeergodicItem.defName!=''}">
			  			<input type="checkbox" class="pk" name="defId" value="${sysdefnodeergodicItem.defId}">
			  		</c:if>
				</display:column>			
				<display:column property="zj" title="子系统编号" sortName="F_ZJ"></display:column>
				<display:column property="sysName" title="子系统名字" sortName="F_SYSNAME"></display:column>
				<display:column title="流程名字" sortName="F_DEFNAME">
				<a href onclick="window.open('/mas/platform/bpm/bpmDefinition/design.ht?defId=${sysdefnodeergodicItem.defId}&typeName=flowChart&status=${sysdefnodeergodicItem.status}')">${sysdefnodeergodicItem.defName}</a>
				</display:column>				
				<display:column property="nodeName" title="节点名字" sortName="F_NODENAME"></display:column>
				<display:column property="setJudge" title="校验" sortName="F_SETJUDGE"></display:column>
				<display:column sortName="F_SETUP">
				<a href="javascript:nodeSetWindow('${sysdefnodeergodicItem.nodeId}','${sysdefnodeergodicItem.defId}','${sysdefnodeergodicItem.actDefId}')">${sysdefnodeergodicItem.setUp}</a>
				</display:column>			
				<display:column property="nodeWorkName" title="节点作业名" sortName="F_NODEWORKNAME"></display:column>		
				<display:column title="所绑定操作图" sortName="F_OPERATENAME">
				<a href onclick="window.open('/mas/platform/bpm/bpmDefinition/design.ht?defId=${sysdefnodeergodicItem.operateDefId}&typeName=operationChart&status=${sysdefnodeergodicItem.operateStatus}')">${sysdefnodeergodicItem.operateName}</a>				
				</display:column>			
				<display:column property="operateNodeName" title="操作图节点名字" sortName="F_OPERATENODENAME"></display:column>
				
				
			
				<display:column title="事务图名或表单名" sortName="F_TRANSACTIONNAME">
			     <c:if test="${sysdefnodeergodicItem.transactionStatus!=null}">
			  	     
				<a href onclick="window.open('/mas/platform/bpm/bpmDefinition/design.ht?defId=${sysdefnodeergodicItem.transactionDefId}&typeName=transactionChart&status=${sysdefnodeergodicItem.transactionStatus}')">${sysdefnodeergodicItem.transactionName}</a>				
				
				</c:if>
				   <c:if test="${sysdefnodeergodicItem.transactionStatus==null}">
			  	     
				<a>${sysdefnodeergodicItem.transactionName}</a>				
				
				</c:if>
				</display:column>
				
				
				
						
				<display:column property="transactionNodeName" title="事务图节点或表单或数据包" sortName="F_TRANSACTIONNODENAME"></display:column>	
				<display:column property="tableParcelName" title="生成数据包的表" sortName="F_TABLEPARCELNAME"></display:column>	
				<display:column property="tableParcelNodeName" title="生成数据包的表的列" sortName="F_TABLEPARCELNODENAME"></display:column>						
			</display:table>
			<hotent:paging tableId="sysdefnodeergodicItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


