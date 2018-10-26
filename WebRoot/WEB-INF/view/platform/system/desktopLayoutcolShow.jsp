<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>设置栏目</title>
	<%@include file="/commons/include/get.jsp" %>
    <%-- <link rel="stylesheet" href="${ctx}/js/jquery-ui-portlet/lib/themes/1.8/start/jquery-ui-1.8.5.custom.css" /> --%>
    <f:link href="jquery-ui-portlet/jquery-ui-1.8.5.custom.css"></f:link>
    <link rel="stylesheet" href="${ctx}/js/jquery-ui-portlet/css/jquery.portlet.css?v=1.1.3" />
    <script src="${ctx}/js/jquery-ui-portlet/lib/jquery-ui-1.8.14.custom.min.js" type="text/javascript"></script>
    <script src="${ctx}/js/jquery-ui-portlet/script/jquery.portlet.js"></script>
    <script type="text/javascript" src="${ctx}/js/util/easyTemplate.js "></script>
   	<script type="text/javascript" src="${ctx}/js/hotent/platform/desktop/desktopManage.js" ></script>   
	<script type="text/javascript">		
		var nonactivated = 'nonactivated';
		var active = 'active';	
		$(function(){
			var desktop=new DesktopManage();
			desktop.init();
			$(".ui-sortable").live("click", function() {
				desktop.changeClass(this);
			});
			$("#addColumn").click(function(){
				desktop.addColumn();
			});
		
			$("#save").click(function(){
				desktop.saveLayout();
			});
		});
	</script>
<style type="text/css">
.nonactivated {
	background-color: #FFFFFF;
	
}
.active {
	background-color: #FFA500;
}
</style>
</head>
<body>		<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">设置栏目</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" id="save" href="javascript:;" ><span></span>保存栏目</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link back" href="../desktopLayout/list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		</div>
		<div class="panel-detail">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td>
					<div class="row" style="margin-top: 10px; margin-left: 0px;">
						<ul>
							<li style="float: left;"><span class="label">请先选择下面栏目区域，再选择栏目:</span></li>
							<li style="float: left;">
							<select id="columnName" name="columnName">
								<c:forEach items="${desktopColumnList}" var="t">
									<option value="${t.id}" name="${t.columnName}" moreUrl="${t.columnUrl}">
										${t.columnName}
									</option>
								</c:forEach>
							</select>
							</li>
							<li style="float: left;">
								<a class="button" id="addColumn" ><span>添加到此列</span></a>						
							</li>
						</ul>
					</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="panel-body">
			<div id="myPage"></div>
			<input type="hidden" id="layoutId" value="${desktopLayout.id}"/>
		</div>
</body>
</html>
