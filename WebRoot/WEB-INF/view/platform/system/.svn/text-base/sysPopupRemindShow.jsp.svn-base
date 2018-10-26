<%--
	time:2015-03-18 11:36:25
	desc:edit the sys_popup_remind
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@ taglib prefix="ht" tagdir="/WEB-INF/tags/wf"%>
<html>
<head>
<title>弹框演示</title>

<%@include file="/commons/include/get.jsp"%>
<style>
body {
	font-family: Tahoma;
	font-size: 12px;
}

.leftmenu {
	width: 100%;
}

.leftmenu li {
	display: inline;
	white-space: nowrap;
}

.leftmenu span,.leftmenu a:active,.leftmenu a:visited,.leftmenu a:link {
	display: block;
	text-decoration: none;
	margin: 6px 10px 6px 0px;
	padding: 2px 6px 2px 6px;
	color: #00527f;
	background-color: #d9e8f3;
	border: 1px solid #004266;
}

.leftmenu a:hover {
	color: red;
	background-color: #8cbbda;
}

.leftmenu span {
	color: #a13100;
}
</style>
<script type="text/javascript">
	$(function() {
		//处理定时刷新页面
		var refreshTime = "${param.refreshTime}";
		if (refreshTime != "") {
			window.setInterval(function() {
				window.location.reload();
			}, Number(refreshTime) * 1000);
		}
	});

	function openerLinkTo(url, text) {
		if (!startWith(url, "http://") && !startWith(url, "www.")) {
			url = __ctx + url;
		}
		window.top.tab.addTabItem({
			url : url,
			text : text,
			tabid : text,
			icon : ""
		});
	}

	function startWith(source, str) {
		if (str == null || str == "" || source.length == 0
				|| str.length > source.length)
			return false;
		return source.substr(0, str.length) == str;
	}
</script>
</head>
<body>
	<ul class="leftmenu">
		<c:forEach var="item" items="${jsonList}">
			<li><a
				href="javaScript:openerLinkTo('${item.url}','${item.subject}')">${item.msg}
			</a>
			</li>
		</c:forEach>

	</ul>
</body>
</html>
