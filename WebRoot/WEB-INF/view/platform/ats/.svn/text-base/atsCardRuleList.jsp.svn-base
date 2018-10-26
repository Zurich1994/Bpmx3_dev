<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>取卡规则管理</title>
<%@include file="/commons/include/get.jsp" %>
<f:link href="listEdit.css"></f:link>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">编码:</span><input type="text" name="Q_code_SL"  class="inputText" value="${param['Q_code_SL']}"/>
						<span class="label">名称:</span><input type="text" name="Q_name_SL"  class="inputText" value="${param['Q_name_SL']}"/>
						<span class="label">是否默认:</span>
						<select name="Q_isDefault_S" value="${param['Q_isDefault_S']}"  class="inputText">
							<option value="">请选择</option>
							<option value="1" <c:if test="${param['Q_isDefault_S'] == '1'}">selected</c:if>>是</option>	
							<option value="0" <c:if test="${param['Q_isDefault_S'] == '0'}">selected</c:if>>否</option>
						</select>
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="atsCardRuleList" id="atsCardRuleItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${atsCardRuleItem.id}">
				</display:column>
				<display:column property="code" title="编码" sortable="true" sortName="CODE" maxLength="80"></display:column>
				<display:column property="name" title="名称" sortable="true" sortName="NAME" maxLength="80"></display:column>

				
				<display:column title="段次" sortable="true" sortName="SEGMENT_NUM">
						<c:choose>
							<c:when test="${atsCardRuleItem.segmentNum==1}">
								一段
							</c:when>
							<c:when test="${atsCardRuleItem.segmentNum==2}">
								二段
							</c:when>
							<c:otherwise>
								三段
							</c:otherwise>
						</c:choose>
				</display:column>
				<display:column property="startNum" title="上班取卡提前(小时)" sortable="true" sortName="START_NUM"></display:column>
				<display:column property="endNum" title="下班取卡延后(小时)" sortable="true" sortName="END_NUM"></display:column>
				<display:column property="timeInterval" title="最短取卡间隔(分钟）" sortable="true" sortName="TIME_INTERVAL"></display:column>
				<display:column title="是否默认" sortable="true" sortName="IS_DEFAULT">
						<c:choose>
							<c:when test="${atsCardRuleItem.isDefault==1}">
								<span class="red">是</span>
							</c:when>
							<c:otherwise>
								<span class="green">否</span>
							</c:otherwise>
						</c:choose>
				</display:column>
				<display:column title="管理" media="html"  class="rowOps">
					<a href="del.ht?id=${atsCardRuleItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${atsCardRuleItem.id}" class="link edit">编辑</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="atsCardRuleItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


