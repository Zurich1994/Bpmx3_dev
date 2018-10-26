<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.jee-soft.cn/functions"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Druid执行状态监控设置</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#resetAll").click(function(){
			if($(this).hasClass('disabled')) return false;
			var ele=this;
			$.ligerDialog.confirm('确认重置所有状态吗？','提示信息',function(rtn){
				if(rtn){
					if(ele.click){
						$(ele).unbind('click');
						ele.click();
					}else{
						var url=ele.href;
						$.post(url);
					}
				}
			});
		});
		
// 		$("table.table-grid td a.link.run,.close").click(function(){
// 			if($(this).hasClass('disabled')) return false;
			
// 			var ele = this;
// 			$.ligerDialog.confirm('确认开启吗？','提示信息',function(rtn) {
// 				if(rtn) {
// 					if(ele.click) {
// 						$(ele).unbind('click');
// 						ele.click();
// 					} else {
// 						location.href=ele.href;
// 					}
// 				}
// 			});
// 			return false;
// 		});
	});
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="panel-toolbar">
				<div class="toolBar">
					<a class="link update" id="resetAll" href="resetAll.ht"><span></span>重置所有监控信息</a>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="panel-data" cellpadding="1" cellspacing="1">
				<table id="tblFilterList" class="table-grid">
					<thead>
						<tr>
							<th>过滤器名</th>
							<th>过滤器类名</th>
							<th>状态</th>
							<th>描述</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
<%-- 						<c:forEach items="${totalFilters }" var="filter"> --%>
<!-- 							<tr> -->
<%-- 								<td>${filter['name'] }</td> --%>
<%-- 								<td>${filter['class'] }</td> --%>
<!-- 								<td> -->
<%-- 									<c:choose> --%>
<%-- 										<c:when test="${filter['using']}"> --%>
<%-- 											<font color="blue">正在使用</font> --%>
<%-- 										</c:when> --%>
<%-- 										<c:otherwise> --%>
<%-- 											<font color="red">未开启</font> --%>
<%-- 										</c:otherwise> --%>
<%-- 									</c:choose> --%>
<!-- 								</td> -->
<%-- 								<td>${filter['desc'] }</td> --%>
<!-- 								<td> -->
<%-- 									<c:choose> --%>
<%-- 										<c:when test="${filter['using']}"> --%>
<%-- 											<a  href="saveConfig.ht?id=${filter['id'] }&operateType=close" class="link run" >关闭</a> --%>
<%-- 										</c:when> --%>
<%-- 										<c:otherwise> --%>
<%-- 											<a href="saveConfig.ht?id=${filter['id'] }&operateType=open" class="link close" status="open" address="${filter['id']}">开启</a> --%>
<%-- 										</c:otherwise> --%>
<%-- 									</c:choose> --%>
<!-- 								</td> -->
<!-- 							</tr> -->
<%-- 						</c:forEach> --%>
					</tbody>
				</table>
			</div>
		</div>
		<!-- end of panel-body -->
	</div>
	<!-- end of panel -->
</body>
</html>