
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<title>系统分类</title>
	<%@include file="/commons/include/get.jsp" %>
	<script type="text/javascript">
		function addNew(parentId,catKey,returnUrl,type)
		{
			if(parentId==0)
			{
				 $.ligerDialog.warn("请选择左边具体的分类!");
				 return;
			}
			else
			{
				location.href="add1.ht?parentId="+parentId+"&catKey="+catKey+"&type="+type+"&returnUrl="+returnUrl;
			}
		}
	</script>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">${parent.typeName }</span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link add" onclick="addNew('${parentId}','${catKey}','${returnUrl}','${type}')"  href="javascript:;"><span></span>添加分类</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
						</div>	
					</div>
				</div>
				</div>
				<div class="panel-body">
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
												<li><span class="label">名称:</span><input type="text" name="Q_typeName_S"  class="inputText" value="${param['Q_typeName_S']}"/></li>
											
												<li><span class="label" style="display: none;">路径</span><input type="hidden" name="Q_nodePath_S"  class="inputText" value="${Q_nodePath_S }" readonly="readonly"/></li>
									</ul>
							</form>
					</div>
					<br/>
					<div class="panel-data">
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="globalTypeList" id="globalTypeItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="typeId" value="${globalTypeItem.typeId}">
							</display:column>
							<display:column property="typeName" title="名称" sortable="true" sortName="typeName" style="text-align:left"></display:column>
							<display:column property="nodeKey" title="节点Key" sortable="true" sortName="nodeKey" style="text-align:left"></display:column>
							<display:column property="catKey" title="节点分类Key" sortable="true" sortName="catKey" style="text-align:left"></display:column>
							<display:column title="类型" sortable="true" sortName="type" >
								<c:choose>
									<c:when test="${globalTypeItem.type==0}">
										<div title="平铺结构" class="icon-list">&nbsp;</div>
								   	</c:when>
							       	<c:otherwise>
								        <div title="树型结构" class="icon-tree">&nbsp;</div>
								   	</c:otherwise>
								</c:choose>
							</display:column>
							<display:column title="管理" media="html" style="width:180px;text-align:center">
								<a href="del.ht?typeId=${globalTypeItem.typeId}" class="link del">删除</a>
								<a href="upd1.ht?typeId=${globalTypeItem.typeId}&returnUrl=${returnUrl}" class="link edit">编辑</a>
								<a href="get.ht?typeId=${globalTypeItem.typeId}" class="link detail">明细</a>
							</display:column>
						</display:table>
						<hotent:paging tableId="globalTypeItem"/>
					</div>
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


