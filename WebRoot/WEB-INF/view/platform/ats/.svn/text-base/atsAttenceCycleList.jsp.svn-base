<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>考勤周期管理</title>
<%@include file="/commons/include/get.jsp" %>
<f:link href="listEdit.css"></f:link>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">考勤周期管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="${ctx}/platform/ats/atsBaseItem/list.ht"><span></span>返回</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<li>
						<span class="label">编码:</span><input type="text" name="Q_code_SL"  class="inputText" value="${param['Q_code_SL']}"/>
						</li>
						<li>
						<span class="label">名称:</span><input type="text" name="Q_name_SL"  class="inputText" value="${param['Q_name_SL']}"/>
						</li>
						<li>
						<span class="label">周期类型:</span>
						<select name="Q_type_S" value="${param['Q_type_S']}"  class="inputText">
							<option value="">请选择</option>
							<option value="1" <c:if test="${param['Q_type_S'] == '1'}">selected</c:if>>自然月</option>	
							<option value="0" <c:if test="${param['Q_type_S'] == '2'}">selected</c:if>>月(固定日期)</option>
						</select>
						</li>
						<li>
						<span class="label">是否默认:</span>
						<select name="Q_isDefault_S" value="${param['Q_isDefault_S']}"  class="inputText">
							<option value="">请选择</option>
							<option value="1" <c:if test="${param['Q_isDefault_S'] == '1'}">selected</c:if>>是</option>	
							<option value="0" <c:if test="${param['Q_isDefault_S'] == '0'}">selected</c:if>>否</option>
						</select>
						</li>
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="atsAttenceCycleList" id="atsAttenceCycleItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${atsAttenceCycleItem.id}">
				</display:column>
				<display:column property="code" title="编码" sortable="true" sortName="CODE" maxLength="80"></display:column>
				<display:column property="name" title="名称" sortable="true" sortName="NAME" maxLength="80"></display:column>
				<display:column  title="周期类型" sortable="true" sortName="TYPE" maxLength="80">
						<c:choose>
							<c:when test="${atsAttenceCycleItem.type==1}">
								<span class="red">自然月</span>
							</c:when>
							<c:otherwise>
								<span class="green">月(固定日期)</span>
							</c:otherwise>
						</c:choose>
				</display:column>
				<display:column title="开始周期" sortable="true" sortName="YEAR">
						${atsAttenceCycleItem.year }年${atsAttenceCycleItem.month}月
				</display:column>
				<display:column  title="周期开始日期" sortable="true" sortName="START_DAY">
						<c:choose>
							<c:when test="${atsAttenceCycleItem.type==1}">
								 1
							</c:when>
							<c:otherwise>
								${atsAttenceCycleItem.startDay}
							</c:otherwise>
						</c:choose>
				</display:column>
				
				<display:column  title="是否默认" sortable="true" sortName="IS_DEFAULT">
						<c:choose>
							<c:when test="${atsAttenceCycleItem.isDefault==1}">
								<span class="red">是</span>
							</c:when>
							<c:otherwise>
								<span class="green">否</span>
							</c:otherwise>
						</c:choose>
				</display:column>
				<display:column title="管理" media="html" class="rowOps">
					<a href="del.ht?id=${atsAttenceCycleItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${atsAttenceCycleItem.id}" class="link edit">编辑</a>
					<a href="get.ht?id=${atsAttenceCycleItem.id}" class="link detail">明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="atsAttenceCycleItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


