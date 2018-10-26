<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>账户概要管理</title>
<%@include file="/commons/include/get.jsp" %>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">账户概要管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
					<div class="l-bar-separator"></div>
					
					<div class="group"><a class="link back" href="/mas/banking/banking/userTab/success.ht?name=<%=request.getParameter("name")%>"><span></span>返回</a></div>
				</div>	
			</div>
		
					
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="account1List" id="account1Item" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${account1Item.id}"></display:column>
				
				<display:column property="USERID" title="用户名" sortable="true" sortName="F_USERID"></display:column>
				<display:column property="ACCOUNTNO" title="账号" sortable="true" sortName="F_ACCOUNTNO"></display:column>
				
				<display:column property="TYPE" title="类型：1-Checking、2-Saving、其它-Other" sortable="true" sortName="F_TYPE"></display:column>
				<display:column property="BALANCE" title="金额" sortable="true" sortName="F_BALANCE"></display:column>
				<display:column property="TOTALDEPOSIT" title="总存款数" sortable="true" sortName="F_TOTALDEPOSIT"></display:column>
				<display:column property="AVGDEPOSIT" title="平均存款数" sortable="true" sortName="F_AVGDEPOSIT"></display:column>
				<display:column property="TOTALWITHDRAWL" title="总取款数" sortable="true" sortName="F_TOTALWITHDRAWL"></display:column>
				<display:column property="AVGWITHDRAWAL" title="平均取款数" sortable="true" sortName="F_AVGWITHDRAWAL"></display:column>
				<!--<display:column title="管理" media="html" style="width:220px">
					--><a href="del.ht?id=${account1Item.id}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${account1Item.id}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${account1Item.id}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="account1Item"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


