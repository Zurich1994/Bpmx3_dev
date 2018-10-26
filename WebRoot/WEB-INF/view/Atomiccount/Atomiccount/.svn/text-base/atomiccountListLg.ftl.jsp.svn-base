<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>原子运行次数管理</title>
<%@include file="/js/lg/getLg.jsp"%>
<script type="text/javascript">
$(function(){
	//动态添加 columns
	var columns=[
		{
			display : '流程id',
			name : 'bpmid',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '操作图id',
			name : 'operaterid',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '事务图id',
			name : 'transactionid',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '原子操作id',
			name : 'atomicid',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '运行次数',
			name : 'runs',
	        type : 'int',
	        isSort : true,
			editor: {
	            type: 'int'
	        }
	        
		},
		{
			display : '时间',
			name : 'time',
			type : 'date',
			format : getFormat('{"format":"yyyy-MM-dd HH:mm:ss","displayDate":0}'),
			isSort : true,
			editor: {
	            type: 'date'
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
				<span class="tbar-label">原子运行次数管理列表</span>
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


