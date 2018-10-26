<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<title>脚本管理列表</title>
	<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel-toolbar">
		<div class="group"><a class="link search" id="btnSearch">查询</a></div>
	</div>
	<div position="center" id="framecenter"> 
          <div class="panel-search">
					<form id="searchForm" method="post" action="dialog.ht" target="win">
							<ul class="row">
								<li><span class="label">脚本分类:</span>
								<select name="Q_category_S">
									<option value="">全部</option>
									<c:forEach items="${categoryList }" var="catName">
										<option value="${catName}">${catName}</option>
									</c:forEach>
								</select>
								
								<input type="submit" value="查询">
								</li>
							</ul>
					</form>
			</div>
			<div class="panel-data">
			    <display:table name="scriptList" id="scriptItem" requestURI="list.ht"  cellpadding="1" cellspacing="1" class="table-grid">
					<display:column  title="名称" style="text-align:left" >
						${scriptItem.name}
						<textarea name="memo" style="display: none;">${scriptItem.memo}</textarea>
						<textarea name="script" style="display: none;">${scriptItem.script}</textarea>
					</display:column>
				</display:table>
				<hotent:paging tableId="scriptItem" showExplain="false" />
			</div>
      </div> 
</body>
</html>
</body>
</html>


