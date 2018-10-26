<#import "function.ftl" as func>
<#assign comment=model.tabComment>
<#assign class=model.variables.class>
<#assign package=model.variables.package>
<#assign comment=model.tabComment>
<#assign classVar=model.variables.classVar>
<#assign system=vars.system>
<#assign commonList=model.commonList>
<#assign pkModel=model.pkModel>
<#assign pk=func.getPk(model) >
<#assign pkVar=func.convertUnderLine(pk) >
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${comment }管理</title>
<%@ include file="/commons/include/easyuiGet.jsp"%>
<script type="text/javascript">
function OperationRow(value, row, index) {
	var editBtn = "<a href='edit.ht?${pkVar}="+row.${pkVar}+"'>编辑</a>";
	var delBtn = "<a href='#' onclick=del('"+row.${pkVar}+"')>删除</a>";
	var seperator = " | ";
	return editBtn + seperator + delBtn ;
}
function del(id){
	$.ajax({
		url: '<#noparse>${ctx}</#noparse>/${system}/${package}/${classVar}/del.ht?${pkVar}='+id,
		type: 'POST',
		success: function(result){
			$('#easyList').datagrid('reload'); 
		}
	});
}

function add(){
	window.location.href='edit.ht';
}
</script>
</head>

<body class="pannel-common easyui-layout" >
	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">新增</a>
	</div>
	
	<table id="easyList" class="easyui-datagrid" title="${comment }列表"
					data-options="cache:false,rownumbers:true,singleSelect:true,fit:true,fitColumns:true,pagination:true,method:'post',url:'getList.ht',toolbar:'#tb'">
		<thead >
			<tr>
				<th data-options="field:'${pkVar}Id',checkbox:true,fixed:true"></th>
				<#list model.commonList as col>
				<#assign colName=func.convertUnderLine(col.columnName)>
				<th data-options="field:'${colName}',width:70<#if (col.colType=="java.util.Date") >,formatter:dateFormater</#if>">${col.getComment()}</th>
				</#list>
				<th data-options="field:'operation',width:120,formatter:OperationRow,align:'center'">操作</th>
			</tr>
		</thead>
	</table>
	
</body>
</html>