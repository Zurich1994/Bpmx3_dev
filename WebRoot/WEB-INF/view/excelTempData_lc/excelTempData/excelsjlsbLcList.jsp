<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@include file="/commons/include/customForm.jsp" %>

<html>
	<head>
		<title>excel 数据临时表管理</title>
		<%@include file="/commons/include/get.jsp"%>
		<script type="text/javascript"
			src="${ctx}/js/jquery/plugins/jquery.attach.js"></script>
		<script type="text/javascript"><!--
		window.name="win";
		var service_name="";
		
		$(function(){
			$("#layoutMain").ligerLayout({ rightWidth: 350, height: '100%'});
		});
		
		function selectServiceName(a)
		{
			service_name=a;
			window.returnValue= {service_name:service_name};	
			window.close();
		}
		
		function selectExcel(){
		var exp = document.getElementsByName("unknownExp")[0].value;
		var rtnType = document.getElementsByName("rtnType")[0].value;
		//alert("exp="+exp);
		 window.location.href ='${ctx}/excelTempData_lc/excelTempData/excelsjlsbLc/list.ht?exp='+exp+'&rtnType='+rtnType;
	
		}
		
				
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
						<div class="group">
							<a class="link search" id="btnSearch"><span></span>查询</a>
						</div>
						<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link add" href="edit.ht"><span></span>用户手动添加方式</a>
						</div>
						<!--<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a>
						</div>
						<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link del" action="del.ht"><span></span>删除</a>
						</div>
						-->
						
						<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link selectFile" href="listCal1.ht?redir=${redirType}"/> 用户读取Excel方式</a>
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
		<div class="panel-body">
			<c:set var="checkAll">
				<input type="checkbox" id="chkall" />
			</c:set>
			
			<display:table name="excelsjlsbLcList" id="excelsjlsbLcItem"
				requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"
				class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
					<input type="checkbox" class="pk" name="id" 
						value="${excelsjlsbLcItem.id}">
				</display:column>
				<display:column property="sj" title="时间" sortable="true"
					sortName="F_SJ"></display:column>
				<display:column property="fsl" title="发生量" sortable="true"
					sortName="F_FSL"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${excelsjlsbLcItem.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${excelsjlsbLcItem.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${excelsjlsbLcItem.id}" class="link detail"><span></span>明细</a>
					<!--<a href='#' class='button'
						onclick="selectServiceName('${excelsjlsbLcItem.sj}')"><span
						class="icon ok"></span><span>选择</span>
					</a>

				--></display:column>
			</display:table>
			<hotent:paging tableId="excelsjlsbLcItem" />
		</div>
		<input type="hidden" name="redirType" value="${redirType}"/>
		<!-- end of panel-body -->
		</div>
		<!-- end of panel -->
	</body>
</html>


