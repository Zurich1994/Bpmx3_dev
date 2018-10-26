<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>excel 数据临时表管理</title>
<%@include file="/commons/include/get.jsp" %>
<%@include file="/commons/include/customForm.jsp" %>
<script type="text/javascript">
function upload(a){
//alert("this:"+a)
	AttachMent.directUpLoadFile(a);}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">excel 数据临时表管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link selectFile"  href="javascript:;" onclick="upload(this)">上传Excel</a>
						</div>				
					<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link selectFile"  href="listCal2.ht" >加载Excel表数据</a>
						</div>
					<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link back" href="list.ht"><span></span>返回</a>
						</div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body"><!--
		
		
	    <c:set var="checkAll">
				<input type="checkbox" id="chkall"  />
			</c:set>
		    <display:table name="excelsjlsbLcList" id="excelsjlsbLcItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" id="chk" class="pk" name="id" value="${excelsjlsbLcItem.sj}">
				</display:column>
				<display:column property="sj" title="时间" sortable="true" sortName="F_SJ">
			    </display:column>
				<display:column property="fsl" title="发生量" sortable="true" sortName="F_FSL">
							</display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${excelsjlsbLcItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${excelsjlsbLcItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${excelsjlsbLcItem.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
		--><hotent:paging tableId="excelsjlsbLcItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


