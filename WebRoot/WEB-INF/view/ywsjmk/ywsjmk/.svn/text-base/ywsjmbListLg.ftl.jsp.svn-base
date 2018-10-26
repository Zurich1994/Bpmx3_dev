<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>业务数据模板管理</title>
<%@include file="/js/lg/getLg.jsp"%>
<script type="text/javascript">
$(function(){
	//动态添加 columns
	var columns=[
		{
			display : '表单标题',
			name : 'subject',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '表单别名',
			name : 'formkey',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '对应表',
			name : 'tablename',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '表单分类',
			name : 'categoryid',
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
				<span class="tbar-label">业务数据模板管理列表</span>
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
						<span class="label">表单标题:</span><input type="text" name="Q_subject_S"  class="inputText" />
						<span class="label">表单别名:</span><input type="text" name="Q_formkey_S"  class="inputText" />
						<span class="label">对应表:</span><input type="text" name="Q_tablename_S"  class="inputText" />
						<span class="label">表单分类:</span><input type="text" name="Q_categoryid_S"  class="inputText" />
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


