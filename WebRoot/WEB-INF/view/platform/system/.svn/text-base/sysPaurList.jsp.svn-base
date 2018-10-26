<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>个性设置管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
	
		<div class="panel-body">
	    	
		    <display:table name="sysPaurList" id="sysPaurItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
				
				<display:column property="paurname" title="名称" sortable="true" sortName="paurname"></display:column>
				<display:column property="aliasname" title="别名" sortable="true" sortName="aliasname"></display:column>
				<display:column property="paurvalue" title="值" sortable="true" sortName="paurvalue"></display:column>
				<display:column title="管理" media="html" style="width:180px">					
					<a href="edit.ht?paurid=${sysPaurItem.paurid}" class="link edit">编辑</a>
				</display:column>
			</display:table>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


