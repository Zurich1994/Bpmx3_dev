<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>流程批量审批列表</title>
<%@include file="/commons/include/get.jsp" %>
		<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayout.js"></script>
	<f:link href="tree/zTreeStyle.css"></f:link>
		<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
	
	<script type="text/javascript">
		var processTree = null;
		$(function (){
			//布局
		    $("#defLayout").ligerLayout({ leftWidth:210,height: '100%',allowLeftResize:false});
			initTree();
		});
		function initTree(){
			var setting = {
					data: {
						simpleData: {enable: true,pIdKey: "pid"}
					},
					callback:{onClick: function(event, treeId, treeNode){
					
						if( $.isEmpty(treeNode.pid) )
							return ;
						
						var url = __ctx+"/platform/bpm/bpmBatchApproval/lists.ht"+"?batchId="+treeNode.batchId;
							
						$("#defFrame").attr("src",url);
						
					}}
				};
	        var url = __ctx+"/platform/bpm/bpmBatchApproval/tree.ht";
	        $.post(url,{},function(result){
	            for(var i=0;i<result.length;i++){
	                var node=result[i];
	                node.open =true;
	            }
	            processTree = $.fn.zTree.init($("#processTree" ), setting,result);
	        });
		}
		
		function treeFresh(){
			initTree();
     	}

		//展开收起
		function treeExpandAll(type){
			processTree.expandAll(type);
		};
	</script>
</head>
<body>
     <div id="defLayout" >
          <div position="left" title="流程定义" style="overflow: auto;float:left;width:100%;height:96%;">
          	<div class="tree-toolbar">
				<span class="toolBar">
					<div class="group"><a class="link reload" id="treeFresh" href="javascript:treeFresh();"  title="刷新"></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link expand" id="treeExpandAll" href="javascript:treeExpandAll(true)"  title="展开"></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link collapse" id="treeCollapseAll" href="javascript:treeExpandAll(false)"  title="收起"></a></div>
				</span>
			</div>
			<ul id="processTree" class="ztree" ></ul>
          </div>
          <div position="center" >
        		<iframe id="defFrame" height="100%" width="100%" frameborder="0" src="${ctx}/platform/bpm/bpmBatchApproval/lists.ht"></iframe>
          </div>
      </div>
</body>
</html>


