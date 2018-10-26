<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>服务产品表管理</title>
<%@include file="/js/lg/getLg.jsp"%>
<script type="text/javascript">
$(function(){
	//动态添加 columns
	var columns=[
		{
			display : '服务产品名',
			name : 'name',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '服务编号',
			name : 'serviceID',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '服务地址',
			name : 'serviceaddress',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '服务端口号',
			name : 'serviceport',
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
				<span class="tbar-label">服务产品表管理列表</span>
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
						<span class="label">服务产品名:</span><input type="text" name="Q_name_S"  class="inputText" />
						<span class="label">服务编号:</span><input type="text" name="Q_serviceID_S"  class="inputText" />
						<span class="label">服务地址:</span><input type="text" name="Q_serviceaddress_S"  class="inputText" />
						<span class="label">服务端口号:</span><input type="text" name="Q_serviceport_S"  class="inputText" />
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


