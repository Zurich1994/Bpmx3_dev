<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>excel 数据临时表管理</title>
<%@include file="/commons/include/get.jsp" %>
<%@include file="/commons/include/customForm.jsp" %>


		<script type="text/javascript"
			src="${ctx}/js/jquery/plugins/jquery.attach.js"></script>
		<script type="text/javascript">
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
				
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">excel 数据临时表管理列表</span>
			</div>
			<div class="panel-toolbar">
				
					<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link back" href="listCal2.ht"><span></span>返回</a>
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
	
			<input type="text" id = "res" >
		<a href='#' class='button'
						onclick="selectServiceName('${id}')"><span
						class="icon ok"></span><span>选择</span>
					</a>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


