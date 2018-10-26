<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>马尔科夫链表管理</title>
<%@include file="/js/lg/getLg.jsp"%>
<script type="text/javascript">
$(function(){
	//动态添加 columns
	var columns=[
		{
			display : '马尔科夫链名称',
			name : 'markovchainNAME',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '是否参与仿真',
			name : 'isSimulation',
			type : 'text',
			isSort : true,
			editor: {
	            type: 'select',
	            data:getSelectData('[{"key":"是","value":"是"},{"key":"否","value":"否"}]','isSimulation'),
	            valueField : 'isSimulation'
	        },
            render: function (item){
            	var datas=getSelectData('[{"key":"是","value":"是"},{"key":"否","value":"否"}]','isSimulation');
            	for(var data in datas){
            		if(item.isSimulation==datas[data]['isSimulation']){
            			return datas[data]['text'];
            		}
            	}
            }
		},
		{
			display : '所属流程',
			name : 'belongto',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '依赖马尔科夫链',
			name : 'dependmark',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '马尔科夫链发生次数',
			name : 'frequency',
	        type : 'int',
	        isSort : true,
			editor: {
	            type: 'int'
	        }
	        
		},
		{
			display : '马尔科夫链详细备注',
			name : 'markovchainDes',
			 type : 'text',
	         isSort : false,
			 editor: {
	            type: 'textarea',
	            height:100
	         } 
		},
		{
			display : '马尔科夫链的概率',
			name : 'probability',
	        type : 'int',
	        isSort : true,
			editor: {
	            type: 'int'
	        }
	        
		},
		{
			display : '依赖马尔科夫链id',
			name : 'dependId',
	        type : 'int',
	        isSort : true,
			editor: {
	            type: 'int'
	        }
	        
		},
		{
			display : '所属流程id',
			name : 'defid',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '马尔科夫链xml文件',
			name : 'markovchainXML',
			 type : 'text',
	         isSort : false,
			 editor: {
	            type: 'textarea',
	            height:100
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
				<span class="tbar-label">马尔科夫链表管理列表</span>
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
						<span class="label">马尔科夫链名称:</span><input type="text" name="Q_markovchainNAME_S"  class="inputText" />
						<span class="label">是否参与仿真:</span><input type="text" name="Q_isSimulation_S"  class="inputText" />
						<span class="label">所属流程:</span><input type="text" name="Q_belongto_S"  class="inputText" />
						<span class="label">依赖马尔科夫链:</span><input type="text" name="Q_dependmark_S"  class="inputText" />
						<span class="label">依赖马尔科夫链id:</span><input type="text" name="Q_dependId_L"  class="inputText" />
						<span class="label">所属流程id:</span><input type="text" name="Q_defid_S"  class="inputText" />
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


