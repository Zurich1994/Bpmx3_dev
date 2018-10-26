<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>子系统内部信息统计管理</title>
<%@include file="/js/lg/getLg.jsp"%>
<script type="text/javascript">
$(function(){
	//动态添加 columns
	var columns=[
		{
			display : '子系统名称',
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
			display : '新增与修改类操作非规范元素个数',
			name : 'nmopNonElemNum',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '新增与修改类操作元素总个数',
			name : 'nmopElemTotal',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '业务成熟度',
			name : 'workSubsysMaturity',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '信息规范程度',
			name : 'infoStandGrade',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '知识型操作个数',
			name : 'knowledgeOpnum',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '知识型操作自动实现的操作数量',
			name : 'knowledgeAutoOpnum',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '知识结构化比例',
			name : 'knowledStrucktGrade',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '本地信息量',
			name : 'local',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '利用外部信息量',
			name : 'outGov',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '外部利用信息量',
			name : 'inGov',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '利用公共信息量',
			name : 'outPub',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '公共利用信息量',
			name : 'inPub',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '本地服务量',
			name : 'flocal',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '利用外部服务量',
			name : 'foutGov',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '外部利用服务量',
			name : 'finGov',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '利用公共服务量',
			name : 'foutPub',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '公共利用服务量',
			name : 'finPub',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '业务架构开放程度',
			name : 'busFrameOpenGrade',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '节点数量',
			name : 'activityNum',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '马尔科夫节点数量',
			name : 'markActivityNum',
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
				<span class="tbar-label">子系统内部信息统计管理列表</span>
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
						<span class="label">子系统名称:</span><input type="text" name="Q_sysName_S"  class="inputText" />
						<span class="label">子系统id:</span><input type="text" name="Q_sysId_S"  class="inputText" />
						<span class="label">新增与修改类操作非规范元素个数:</span><input type="text" name="Q_nmopNonElemNum_S"  class="inputText" />
						<span class="label">新增与修改类操作元素总个数:</span><input type="text" name="Q_nmopElemTotal_S"  class="inputText" />
						<span class="label">业务成熟度:</span><input type="text" name="Q_workSubsysMaturity_S"  class="inputText" />
						<span class="label">信息规范程度:</span><input type="text" name="Q_infoStandGrade_S"  class="inputText" />
						<span class="label">知识型操作个数:</span><input type="text" name="Q_knowledgeOpnum_S"  class="inputText" />
						<span class="label">知识型操作自动实现的操作数量:</span><input type="text" name="Q_knowledgeAutoOpnum_S"  class="inputText" />
						<span class="label">知识结构化比例:</span><input type="text" name="Q_knowledStrucktGrade_S"  class="inputText" />
						<span class="label">本地信息量:</span><input type="text" name="Q_local_S"  class="inputText" />
						<span class="label">利用外部信息量:</span><input type="text" name="Q_outGov_S"  class="inputText" />
						<span class="label">外部利用信息量:</span><input type="text" name="Q_inGov_S"  class="inputText" />
						<span class="label">利用公共信息量:</span><input type="text" name="Q_outPub_S"  class="inputText" />
						<span class="label">公共利用信息量:</span><input type="text" name="Q_inPub_S"  class="inputText" />
						<span class="label">本地服务量:</span><input type="text" name="Q_flocal_S"  class="inputText" />
						<span class="label">利用外部服务量:</span><input type="text" name="Q_foutGov_S"  class="inputText" />
						<span class="label">外部利用服务量:</span><input type="text" name="Q_finGov_S"  class="inputText" />
						<span class="label">利用公共服务量:</span><input type="text" name="Q_foutPub_S"  class="inputText" />
						<span class="label">公共利用服务量:</span><input type="text" name="Q_finPub_S"  class="inputText" />
						<span class="label">业务架构开放程度:</span><input type="text" name="Q_busFrameOpenGrade_S"  class="inputText" />
						<span class="label">节点数量:</span><input type="text" name="Q_activityNum_S"  class="inputText" />
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


