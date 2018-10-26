<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>子系统流程节点遍历管理</title>
<%@include file="/js/lg/getLg.jsp"%>
<script type="text/javascript">
$(function(){
	//动态添加 columns
	var columns=[
		{
			display : '主键',
			name : 'zj',
	        type : 'int',
	        isSort : true,
			editor: {
	            type: 'int'
	        }
	        
		},
		{
			display : '子系统名字',
			name : 'sysName',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '子系统id',
			name : 'sysId',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '流程名字',
			name : 'defName',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '流程id',
			name : 'defId',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '流程版本id',
			name : 'actDefId',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '节点名字',
			name : 'nodeName',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '节点作业名',
			name : 'nodeWorkName',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		}
	]
	initData({"columns":columns,"innerEdit":false,"needToolbar":true});

})
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
					<div class="groupUI"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="groupUI"><a class="link add" action="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="groupUI"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="groupUI"><a class="link del"  action="del.ht"><span></span>删除</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="getList.ht">
					<div class="row">
						<span class="label">主键:</span><input type="text" name="Q_zj_L"  class="inputText" />
						<span class="label">子系统名字:</span><input type="text" name="Q_sysName_S"  class="inputText" />
						<span class="label">子系统id:</span><input type="text" name="Q_sysId_S"  class="inputText" />
						<span class="label">流程名字:</span><input type="text" name="Q_defName_S"  class="inputText" />
						<span class="label">流程id:</span><input type="text" name="Q_defId_S"  class="inputText" />
						<span class="label">流程版本id:</span><input type="text" name="Q_actDefId_S"  class="inputText" />
						<span class="label">节点名字:</span><input type="text" name="Q_nodeName_S"  class="inputText" />
						<span class="label">节点作业名:</span><input type="text" name="Q_nodeWorkName_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<div id="grid" style="PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px"></div>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


