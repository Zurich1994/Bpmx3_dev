<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>流程模板管理</title>
<%@include file="/js/lg/getLg.jsp"%>
<script type="text/javascript">
$(function(){
	//动态添加 columns
	var columns=[
		{
			display : '模版名称',
			name : 'templatename',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '别名',
			name : 'bm',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '模板类别',
			name : 'templatetype',
			type : 'text',
			isSort : true,
			editor: {
	            type: 'select',
	            data:getSelectData('[{"key":"1","value":"流程图"},{"key":"2","value":"操作图"},{"key":"3","value":"事务图"},{"key":"4","value":"word文档"}]','templatetype'),
	            valueField : 'templatetype'
	        },
            render: function (item){
            	var datas=getSelectData('[{"key":"1","value":"流程图"},{"key":"2","value":"操作图"},{"key":"3","value":"事务图"},{"key":"4","value":"word文档"}]','templatetype');
            	for(var data in datas){
            		if(item.templatetype==datas[data]['templatetype']){
            			return datas[data]['text'];
            		}
            	}
            }
		},
		{
			display : '流程类别',
			name : 'subjecttype',
			type : 'text',
			isSort : true,
			editor: {
	            type: 'select',
	            data:getSelectData('[{"key":"1","value":"行政执法"},{"key":"2","value":"政务公开"}]','subjecttype'),
	            valueField : 'subjecttype'
	        },
            render: function (item){
            	var datas=getSelectData('[{"key":"1","value":"行政执法"},{"key":"2","value":"政务公开"}]','subjecttype');
            	for(var data in datas){
            		if(item.subjecttype==datas[data]['subjecttype']){
            			return datas[data]['text'];
            		}
            	}
            }
		},
		{
			display : '流程名称',
			name : 'subjectname',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '备注',
			name : 'bz',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '是否标记为模板',
			name : 'issign',
			type : 'text',
			isSort : true,
			editor: {
	            type: 'select',
	            data:getSelectData('[{"key":"1","value":"是"},{"key":"2","value":"fou"}]','issign'),
	            valueField : 'issign'
	        },
            render: function (item){
            	var datas=getSelectData('[{"key":"1","value":"是"},{"key":"2","value":"fou"}]','issign');
            	for(var data in datas){
            		if(item.issign==datas[data]['issign']){
            			return datas[data]['text'];
            		}
            	}
            }
		},
		{
			display : '创建时间',
			name : 'createtime',
			type : 'date',
			format : getFormat('{"format":"yyyy-MM-dd","displayDate":1}'),
			isSort : true,
			editor: {
	            type: 'date'
	        } 
		},
		{
			display : '模板生成的文件路径',
			name : 'mbscdwjlj',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '模板文件生成的文件名称',
			name : 'mbwjscdwjmc',
	         type : 'text',
	         isSort : true,
			 editor: {
	            type: 'text'
	         } 
		},
		{
			display : '模板XML',
			name : 'templateXML',
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
				<span class="tbar-label">流程模板管理列表</span>
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


