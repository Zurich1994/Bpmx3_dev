<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<title>业务数据模板预览</title>
	<%@include file="/commons/include/getJqGrid.jsp" %>
</head>
<body>
<div class="panel">
		<!-- 导航TAB -->
		<c:if test=" ${hasTab &&  fn:length(viewList)>1}">
			<div class='panel-nav'>
				<div class="l-tab-links">
					<ul style="left: 0px; ">
						<c:forEach items="${viewList}" var="item">
							<li tabid="${item.alias}" <#if item.alias ==currentView> class="l-selected"</#if>>
								<a href="${ctx }/platform/system/sysQueryView/${sqlAlias}.ht?view=${item.alias}" title="${item.alias}">${item.name}</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</c:if>
		${queryView.template}
	</div>
</body>
</html>
