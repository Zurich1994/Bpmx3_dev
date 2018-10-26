<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>流程模板管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">流程模板管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
				</div>	
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="codeTemplateList" id="codeTemplateItem" requestURI="getMyDraftJson.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${codeTemplateItem.id}">
				</display:column>
				<display:column property="templatename" title="模版名称" sortable="true" sortName="F_TEMPLATENAME"></display:column>
				<display:column property="bm" title="别名" sortable="true" sortName="F_BM"></display:column>
				<display:column property="templatetype" title="模板类别" sortable="true" sortName="F_TEMPLATETYPE"></display:column>
				<display:column property="subjecttype" title="流程类别" sortable="true" sortName="F_SUBJECTTYPE"></display:column>
				<display:column property="subjectname" title="流程名称" sortable="true" sortName="F_SUBJECTNAME"></display:column>
				<display:column property="bz" title="备注" sortable="true" sortName="F_BZ"></display:column>
				<display:column property="issign" title="是否标记为模板" sortable="true" sortName="F_ISSIGN"></display:column>
				<display:column  title="创建时间" sortable="true" sortName="F_CREATETIME">
					<fmt:formatDate value="${codeTemplateItem.createtime}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column property="mbscdwjlj" title="模板生成的文件路径" sortable="true" sortName="F_MBSCDWJLJ"></display:column>
				<display:column property="mbwjscdwjmc" title="模板文件生成的文件名称" sortable="true" sortName="F_MBWJSCDWJMC"></display:column>
				<display:column property="templateXML" title="模板XML" sortable="true" sortName="F_TEMPLATEXML"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${codeTemplateItem.id}" class="link del"><span></span>删除</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="codeTemplateItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


