<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>support产品表管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">
	function startFlow(obj,id){
		var linkObj=$(obj);
		if(!linkObj.hasClass('disabled')) {
			linkObj.addClass('disabled');
			$.post("run.ht?isList=1&id="+id,function(responseText){
				
				var obj = new com.hotent.form.ResultMessage(responseText);
				if (obj.isSuccess()) {
					$.ligerDialog.success("启动流程成功！", "成功", function(rtn) {
						this.close();
						window.location.href = "${ctx}/support/catelog/products/list.ht";
					});
				} else {
					$.ligerDialog.error(obj.getMessage(),"提示信息");
					linkObj.removeClass("disabled");
				}
				
			});
		}
		
	}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">support产品表管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
					<span class="label">搜索关键字:</span><input type="text" name="Q_KEYWORDS_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="productsList" id="productsItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${productsItem.id}">
				</display:column>
				<display:column property="PRODUCTID" title="产品ID" sortable="true" sortName="F_PRODUCTID"></display:column>
				
				<display:column property="PRODUCT" title="产品" sortable="true" sortName="F_PRODUCT"></display:column>
				
				<display:column title="操作" media="html" style="width:220px">
					<c:if test="${productsItem.runId==0}">
						<!-- <a href="del.ht?id=${productsItem.id}" class="link del"><span></span>删除</a>
						<a href="javascript:;" onclick="startFlow(this,'${productsItem.id}')" class="link run"><span></span>提交</a>
					</c:if>
					<a href="edit.ht?id=${productsItem.id}" class="link edit"><span></span>编辑</a> -->
					<a href="/mas/support/catelog/fileCatalog/edit.ht?PRODUCTID=${productsItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="productsItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


