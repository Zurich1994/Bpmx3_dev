<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>桌面浏览</title>
	<%@include file="/commons/include/get.jsp" %>
	<%@include file="/commons/include/getById.jsp" %>
<%--     <link href="${ctx}/js/desktop/inettuts.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/js/desktop/inettuts.js.css" rel="stylesheet" type="text/css" /> --%>
    <f:link href="desktop/inettuts.css"></f:link>
    <f:link href="desktop/inettuts.js.css"></f:link>
    <script type="text/javascript" src="${ctx }/js/desktop/inettutsnew.js" ></script>
    <script type="text/javascript" src="${ctx }/js/hotent/platform/desktop/desktopTemplete.js" ></script>
	<script type="text/javascript">
	 $(function () {  
		 var columnId=${desktopLayoutmap["id"]};//栏目ID
		 var cols=${desktopLayoutmap["cols"]};//栏目列数
		 var width='${desktopLayoutmap["widths"]}';//栏目宽度
		 new com.hotent.platform.desktop.myTemp(cols,columnId,width,0,"news");
	    }); 
	</script>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">浏览桌面</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				</div>
			</div>
			<div id="colshtml"></div>
		</div>
		</div>
	</div>
</body>
</html>
