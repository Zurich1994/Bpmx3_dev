<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>数据库方法参数表管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">数据库方法参数表管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">表名:</span><input type="text" name="Q_table_name_S"  value="${tablename} class="inputText" />
						<span class="label">方法类型:</span><input type="text" name="Q_func_type_L"  class="inputText" />
						<span class="label">方法名称:</span><input type="text" name="Q_func_name_S"  class="inputText" />
						<span class="label">方法别名:</span><input type="text" name="Q_func_alias_S"  class="inputText" />
						<span class="label">传参类型:</span><input type="text" name="Q_parameterType_S"  class="inputText" />
						<span class="label">返回值类型:</span><input type="text" name="Q_resultType_S"  class="inputText" />
						<span class="label">返回字段:</span><input type="text" name="Q_resultField_S"  class="inputText" />
						<span class="label">条件字段:</span><input type="text" name="Q_conditionField_S"  class="inputText" />
						<span class="label">排序字段:</span><input type="text" name="Q_sortField_S"  class="inputText" />
						<span class="label">设置字段:</span><input type="text" name="Q_settingField_S"  class="inputText" />
						<span class="label">显示字段:</span><input type="text" name="Q_displayField_S"  class="inputText" />
						<span class="label">数据源名称:</span><input type="text" name="Q_dsName_S"  class="inputText" />
						<span class="label">数据源别名:</span><input type="text" name="Q_ds_Alias_S"  class="inputText" />
						<span class="label">是否为表 :</span><input type="text" name="Q_isTable_L"  class="inputText" />
						<span class="label">是否正在使用:</span><input type="text" name="Q_is_Using_L"  class="inputText" />
						<span class="label">objname:</span><input type="text" name="Q_objname_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="dbFuncList" id="dbFuncItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${dbFuncItem.id}">
				</display:column>
				<display:column property="table_name" title="表名" sortable="true" sortName="F_TABLE_NAME"></display:column>
				<display:column property="func_type" title="方法类型" sortable="true" sortName="F_FUNC_TYPE"></display:column>
				<display:column property="func_name" title="方法名称" sortable="true" sortName="F_FUNC_NAME"></display:column>
				<display:column property="func_alias" title="方法别名" sortable="true" sortName="F_FUNC_ALIAS"></display:column>
				<display:column property="remarks" title="备注" sortable="true" sortName="F_REMARKS"></display:column>
				<display:column property="parameterType" title="传参类型" sortable="true" sortName="F_PARAMETERTYPE"></display:column>
				<display:column property="resultType" title="返回值类型" sortable="true" sortName="F_RESULTTYPE"></display:column>
				<display:column property="resultField" title="返回字段" sortable="true" sortName="F_RESULTFIELD"></display:column>
				<display:column property="conditionField" title="条件字段" sortable="true" sortName="F_CONDITIONFIELD"></display:column>
				<display:column property="sortField" title="排序字段" sortable="true" sortName="F_SORTFIELD"></display:column>
				<display:column property="settingField" title="设置字段" sortable="true" sortName="F_SETTINGFIELD"></display:column>
				<display:column property="displayField" title="显示字段" sortable="true" sortName="F_DISPLAYFIELD"></display:column>
				<display:column property="dsName" title="数据源名称" sortable="true" sortName="F_DSNAME"></display:column>
				<display:column property="ds_Alias" title="数据源别名" sortable="true" sortName="F_DS_ALIAS"></display:column>
				<display:column property="isTable" title="是否为表 " sortable="true" sortName="F_ISTABLE"></display:column>
				<display:column property="is_Using" title="是否正在使用" sortable="true" sortName="F_IS_USING"></display:column>
				<display:column property="objname" title="objname" sortable="true" sortName="F_OBJNAME"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${dbFuncItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${dbFuncItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${dbFuncItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="dbFuncItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


