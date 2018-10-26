<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>打卡记录管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/platform/ats/AtsDialog.js"></script>
<script type="text/javascript">
	function importData(){
		AtsImport({
			url:__ctx + '/platform/ats/atsCardRecord/import.ht',
			title:'打卡记录导入'
		});
	}

</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">打卡记录管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link import" href="javascript:importData();"><span></span>导入</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link export" href="javascript:exportData();"><span></span>导出</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">考勤卡号:</span><input type="text" name="Q_cardNumber_SL"  class="inputText" />
						<span class="label">打卡日期 从:</span> <input  name="Q_begincardDate_DL"  class="inputText date" />
						<span class="label">至: </span><input  name="Q_endcardDate_DG" class="inputText date" />
						<span class="label">打卡来源:</span><input type="text" name="Q_cardSource_SL"  class="inputText" />
						<span class="label">打卡位置:</span><input type="text" name="Q_cardPlace_SL"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="atsCardRecordList" id="atsCardRecordItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${atsCardRecordItem.id}">
				</display:column>
				<display:column property="cardNumber" title="考勤卡号" sortable="true" sortName="CARD_NUMBER" maxLength="80"></display:column>
				<display:column  title="打卡日期" sortable="true" sortName="CARD_DATE">
					<fmt:formatDate value="${atsCardRecordItem.cardDate}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column title="打卡时间" sortable="true" sortName="CARD_DATE">
					<fmt:formatDate value="${atsCardRecordItem.cardDate}" pattern="HH:mm:ss"/>
				</display:column>
				<display:column  title="打卡来源" sortable="true" sortName="CARD_SOURCE" maxLength="80">
					<c:choose>
						<c:when test="${atsCardRecordItem.cardSource==1}">补打卡</c:when>
						<c:when test="${atsCardRecordItem.cardSource==2}">Excel</c:when>
						<c:when test="${atsCardRecordItem.cardSource==3}"><span class="red">Access</span></c:when>
						<c:when test="${atsCardRecordItem.cardSource==4}">TEXT</c:when>
						<c:otherwise>
							未知来源
						</c:otherwise>
					</c:choose>
				</display:column>
				<display:column property="cardPlace" title="打卡位置" sortable="true" sortName="CARD_PLACE" maxLength="80"></display:column>
			</display:table>
			<hotent:paging tableId="atsCardRecordItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


