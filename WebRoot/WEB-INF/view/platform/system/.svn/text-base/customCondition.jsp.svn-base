<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>条件自定义</title>
	<%@include file="/commons/include/form.jsp" %>
	<f:link href="Aqua/css/ligerui-all.css"></f:link>
    <f:link href="tree/zTreeStyle.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ConditionExpression.js"></script>
	<script type="text/javascript">
		var zTree;
		var conditionExpress;
		var setting = {
			edit: {
				enable: true,
				showRemoveBtn: true,
				showRenameBtn: false,
				drag:{
					isCopy:true,
					isMove:true,
					prev:true,
					inner:true,
					next:true
				}
			},
			callback: {
				beforeDrag: beforeDrag,
				beforeDrop: beforeDrop,
				onRemove: onTreeRemove,
				onClick: onTreeClick,
				onDrop: onTreeOnDrop
			}
		};
		
		function onTreeRemove(event, treeId, treeNode) {
			zTree.refresh();
			calc();
		}
		
		function onTreeClick(event, treeId, treeNode) {
			var type=treeNode.type;
		    switch(type){
		    	case "1":
		    		break;
		    	case "2":
		    		break;
		    	case "3":
		    		$("#txtExpression").val(treeNode.expression);
		    		$("input[name=action][value='3']").attr("checked",true);
		    		break;
		    }
		 
		};
		
		function beforeDrag(treeId, treeNodes) {
			for (var i=0,l=treeNodes.length; i<l; i++) {
				if (treeNodes[i].drag === false) {
					return false;
				}
			}
			return true;
		}
		function beforeDrop(treeId, treeNodes, targetNode, moveType) {
			return targetNode ? targetNode.drop !== false : true;
		}
		
		function onTreeOnDrop(event, treeId, treeNodes, targetNode, moveType) {
		    calc();
		};
		
		
		$(document).ready(function(){
			$("a.save").click(function(){
				save();
			});
			zTree=$.fn.zTree.init($("#treeCondition"), setting);
			
			$("#txtExpression").change( txtExpressionChangeHandler);
			//表达式计算JS。
			conditionExpress=new com.hotent.platform.system.ConditionExpression();
			
			$("a.no").click(cancelSelect);
		});
		
		function cancelSelect(){
			if(zTree){
				zTree.cancelSelectedNode();
			}
		}
		
		function txtExpressionChangeHandler(e)
		{
			var nodes=zTree.getSelectedNodes();
			var selectNode;
			if(!nodes) return;
			
			selectNode=nodes[0];
			if(selectNode){
				if(selectNode.type=="3"){
					selectNode.expression=this.value;
					calc();
				}
			}
		}
		function add(){
			
			var isRoot=$("#chkRoot").attr("checked");
			if(isRoot)
				blnChecked=true;
			var type=$("input[name=action]:checked").val();
			var txt=$("#txtExpression").val();
			var node=conditionExpress.genNode(type,txt);
			var nodes=zTree.getSelectedNodes();
			var selectNode;
			if(nodes){
				selectNode=nodes[0];
			}
			if(selectNode){
				if(selectNode.type!="3"){
					zTree.addNodes(selectNode, node);
				}
				else{
					$.ligerDialog.warn('条件表达式下不能添加条件!');
				}
			}
			else{
				zTree.addNodes(null, node);
			}
			
			calc();
		}
		
		function calc(){
			conditionExpress.reset();
			
			var nodes = zTree.getNodes();
		
			for(var i=0;i<nodes.length;i++){
				var node=nodes[i];
				conditionExpress.evaluate(node);
				if(conditionExpress.hasError) break;
			}
			var result=conditionExpress.getResult();
			$("#txtCondition").val(result);
		}
	</script>
</head>
<body>
<div class="panel-top">
	<div class="tbar-title">
		<span class="tbar-label">构造条件</span>
	</div>
	<div class="panel-toolbar">
		<div class="toolBar">
			<div class="group"><a class="link save"><span></span>保存</a></div>
			<div class="l-bar-separator"></div>
		
			<div class="group"><a onclick="add();" class="link add"><span></span>添加</a></div>
			<div class="l-bar-separator"></div>
			<div class="group"><a onclick="cancelSelect();" class="link no"><span></span>取消选中</a></div>
		</div>	
	</div>
</div>
<div class="panel-body">
	<div class="panel-detail">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width="15%">条件:</th>
				<td><input type="radio" value="2" name="action" checked="checked" />并&nbsp;<input type="radio" value="1"  name="action"/>或</td>
			</tr>
			<tr>
				<th width="15%">表达式:</th>
				<td><input type="radio" value="3"  name="action"/> <input type="text" id="txtExpression" class="inputText" size="40"/></td>
			</tr>
			
			<tr height="150px">
				<th width="15%">表达式树:</th>
				<td  valign="top">
					<ul id="treeCondition" class="ztree" style="height:150px;overflow: auto;"></ul>
				</td>
			</tr>
			<tr height="150px">
				<th width="15%">条件:</th>
				<td  valign="top">
					<textarea rows="6" cols="80" id="txtCondition"></textarea>
				</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>