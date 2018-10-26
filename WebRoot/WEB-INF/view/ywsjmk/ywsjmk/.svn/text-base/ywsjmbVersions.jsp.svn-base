
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看版本</title>
<%@include file="/commons/include/get.jsp" %>

<style type="text/css">
	.panel-data {
		margin-top: 5px ;
		margin-bottom: 5px ;
	}
	
	.floatBtn {
		font-weight: bold;
		color: red !important;
	}
	
</style>
<script type="text/javascript">
	$(function(){
		handPublish();
		handNewVersion();
	});
	
	function handPublish(){
		$.confirm("a.link.deploy",'确认发布吗？');
	}
	
	function handNewVersion(){
		$.confirm("a.link.newVersion",'确认新建版本吗？');
	}
	
	
	
</script>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">${formName}--版本管理</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back " href="bdlist.ht"><span></span>返回</a></div>
				</div>
			</div>
		</div>
		</div>
		<div class="panel-body">
			<display:table name="versions" id="bpmFormDefItem" requestURI="versions.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
				<display:column property="versionNo" title="版本号" sortable="true" sortName="versionNo"></display:column>
				<display:column property="publishedBy" title="发布人" sortable="true" sortName="publishedBy"></display:column>
				<display:column title="发布时间" sortable="true" sortName="publishTime">
					<fmt:formatDate value="${bpmFormDefItem.publishTime}"/>
				</display:column>
				<display:column title="是否默认" sortable="true" sortName="isDefault">
					<c:choose>
					 	<c:when test="${bpmFormDefItem.isDefault==1}"><span class="green">是</span></c:when>
					 	<c:otherwise>
					 		<span class="red">否</span>
					 	</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="是否发布" sortable="true" sortName="isDefault">
					<c:choose>
					 	<c:when test="${bpmFormDefItem.isPublished==1}"><span class="green">已发布</span></c:when>
					 	<c:otherwise>
					 		<span class="red">未发布</span>
					 	</c:otherwise>
					</c:choose>
				</display:column>
			
				<display:column title="管理" media="html" >
					<c:choose>
						<c:when test="${bpmFormDefItem.isDefault==1}">
							<a class="link setting " style="color:gray;">设置默认</a>
						</c:when>
						<c:otherwise>
							<a  class="link setting" href="setDefaultVersion.ht?formDefId=${bpmFormDefItem.formDefId }&formKey=${bpmFormDefItem.formKey}"><span >设置默认</span></a>
						</c:otherwise>
					</c:choose>
					<c:if test="${bpmFormDefItem.isPublished==0&&bpmFormDefItem.designType==0}">
							<a  class="link deploy" href="publish.ht?formDefId=${bpmFormDefItem.formDefId }">发布</a>
					</c:if>
					<c:if test="${bpmFormDefItem.isPublished== 1}">
						<a href="newVersion.ht?formDefId=${bpmFormDefItem.formDefId}"  class="link newVersion">新建版本</a>
					</c:if>	
					
					<c:choose>
						<c:when test="${bpmFormDefItem.designType==0 }">
							<a href="javascript:;" onclick="javascript:jQuery.openFullWindow('edit.ht?formDefId=${bpmFormDefItem.formDefId}');" class="link edit">编辑</a>
						</c:when>
						<c:otherwise >
							<a href="javascript:;" onclick="javascript:jQuery.openFullWindow('designEdit.ht?formDefId=${bpmFormDefItem.formDefId}');" class="link edit">编辑</a>
						</c:otherwise>
					</c:choose>
					
					<a href="get.ht?formDefId=${bpmFormDefItem.formDefId}"  class="link detail">查看</a>
					
					<c:choose>
							<c:when test="${bpmFormDefItem.designType==0 }">
								<a target="_blank" href="${ctx}/platform/form/bpmFormHandler/edit.ht?formDefId=${bpmFormDefItem.formDefId}" class="link preview">预览</a>
							</c:when>
							<c:when test="${bpmFormDefItem.designType==1 }">
								<a href="javascript:;" onclick="javascript:jQuery.openFullWindow('preview.ht?formDefId=${bpmFormDefItem.formDefId}');" class="link edit">预览</a>
							</c:when>
					</c:choose>
					
					<input type="hidden" id="bpmFormDefId"value="${bpmFormDefItem.formDefId}">
				</display:column>
			</display:table>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


