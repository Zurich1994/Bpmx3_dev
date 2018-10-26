<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>桌面栏目管理表明细</title>
	<%@include file="/commons/include/get.jsp"%>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerMessageBox.js"></script>
	<f:link href="jquery-ui-portlet/jquery-ui-1.8.5.custom.css"></f:link>
	<f:link href="jquery-ui-portlet/jquery.portlet.css"></f:link>
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
		$("#changeLayout").click(function(){
			desktop.changLayout();
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
.row select {
margin-top: 0;
}
</style>
</head>
<body>
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">桌面布局设置</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
					<a class="link save" id="save" href="javascript:;" ><span></span>保存布局</a>
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
							<li style="float: left;"><select id="columnName"
								name="columnName">
									<c:forEach items="${desktopColumnList}" var="t">
										<option value="${t.id}" name="${t.columnName}" moreUrl="${t.columnUrl}">${t.columnName}</option>
									</c:forEach>
							</select>&nbsp;&nbsp;
							<a class="button" id="addColumn"><span>添加到此列</span></a></li> 							
							<li style="float: left;">&nbsp;&nbsp;<span class="label">选择布局:</span>								
								<select id="colsSelect" name="cols">
									<c:forEach items="${desktopLayout}" var="t">
										<option value="${t.id}" cols="${t.cols}" widthStr="${t.width}"<c:if test="${desktopLayoutmap.cols==t.cols}">selected="selected"</c:if>  >${t.name}</option>
									</c:forEach>
							</select>
							</li>
							<li style="float: left;"><a class="button" id="changeLayout"><span>更换布局</span></a></li>
						</ul>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div class="panel-body">
		<div id="myPage"></div>
	</div>
</body>
</html>