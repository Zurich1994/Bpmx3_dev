<%--update 2013-1-1 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title>表单打印</title>
<script type="text/javascript"  >
	function preview(id){
		var url="get.ht?id="+id;
		jQuery.openFullWindow(url);
	}
	
	function editForm(formKey,id){
		var url="edit.ht?formKey="+formKey ;
		if(id){
			url+="&id=" +id;
		}
		jQuery.openFullWindow(url);
	}
	
	
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">表单打印管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="javascript:;" onclick="editForm('${formKey}')"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="${ctx}/platform/form/bpmFormDef/list.ht"><span></span>返回</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<input type="hidden" name="formKey"  class="inputText" value="${formKey}" />
					<div class="row">
						<span class="label">模版名称:</span><input type="text" name="Q_temapalteName_SL"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="bpmPrintTemplateList" id="bpmPrintTemplateItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column property="temapalteName" titleKey="模版名称" sortable="true" sortName="temapalteName"></display:column>
				<display:column  titleKey="是否默认" sortable="true" sortName="isDefault">
					<c:choose>
						<c:when test="${bpmPrintTemplateItem.isDefault==1}">
							<span class="green">是</span>
						</c:when>
						<c:when test="${bpmPrintTemplateItem.isDefault==0}">
							<span class="red">否</span>
						</c:when>
					</c:choose>
				</display:column>
				<display:column titleKey="管理" media="html" style="width:220px">
					<a href="javascript:;" onclick="editForm('${formKey}','${bpmPrintTemplateItem.id}')" class="link edit"><span></span>编辑</a>
					<a href="javascript:;" onclick="preview(${bpmPrintTemplateItem.id});" class="link preview"><span></span>预览</a>
					<c:if test="${bpmPrintTemplateItem.isDefault==0}">
						
						<a href="setDefault.ht?id=${bpmPrintTemplateItem.id}&formKey=${formKey}" class="link detail"><span></span>设置默认</a>
					</c:if>
					<a href="del.ht?id=${bpmPrintTemplateItem.id}" class="link del">删除</a>
				</display:column>
			</display:table>
			<sf:paging tableId="bpmPrintTemplateItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


