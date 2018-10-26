<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>子系统服务表管理</title>
<%@include file="/js/lg/getLg.jsp"%>
<script type="text/javascript">
$(function(){
	//动态添加 columns
	var columns=[
		{
			display : 'id',
			name : 'id',
	        type : 'int',
	        isSort : true,
			editor: {
	            type: 'int'
	        }
	        
		},
		{
			display : '子系统id',
			name : 'systemId',
	        type : 'int',
	        isSort : true,
			editor: {
	            type: 'int'
	        }
	        
		},
		{
			display : '服务类别',
			name : 'service',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '服务数量',
			name : 'serviceNum',
	        type : 'int',
	        isSort : true,
			editor: {
	            type: 'int'
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
				<span class="tbar-label">子系统服务表管理列表</span>
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
						<span class="label">id:</span><input type="text" name="Q_id_L"  class="inputText" />
						<span class="label">子系统id:</span><input type="text" name="Q_systemId_L"  class="inputText" />
						<span class="label">服务类别:</span><input type="text" name="Q_service_S"  class="inputText" />
						<span class="label">服务数量:</span><input type="text" name="Q_serviceNum_L"  class="inputText" />
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


