<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>考勤档案管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/platform/ats/AtsDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<f:link href="listEdit.css"></f:link>
<script>
function selectUser(){
	UserDialog({isSingle:true,callback:function(userId,userName,emails,mobiles,rtn){
		$('#userName').val(userName);
	}});
}
function importData(){
	AtsImport({
		url:__ctx + '/platform/ats/atsAttendanceFile/import.ht',
		title:'考勤档案导入'
	});
}

</script>
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
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del" href="javascript:void(0);" action="del.ht"><span></span>删除</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link primary" href="disUser.ht"><span></span>未建档案人员</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link import" href="javascript:importData();"><span></span>导入</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">用户:</span>
						<input type="text" id="userName" name="Q_userName_SL" value="${param['Q_userName_SL']}"   readonly="readonly" />
						<a href="javascript:void(0);" onclick="selectUser()" class="button inputText"><span>选 择...</span></a>
						<span class="label">考勤卡号:</span>
						<input type="text" name="Q_cardNumber_SL"  class="inputText" value="${param['Q_cardNumber_SL']}"/>
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="atsAttendanceFileList" id="atsAttendanceFileItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${atsAttendanceFileItem.id}">
				</display:column>
				<display:column property="userName" title="用户" sortable="true" sortName="USER_ID"></display:column>
				<display:column property="cardNumber" title="考勤卡号" sortable="true" sortName="CARD_NUMBER" maxLength="80"></display:column>
				<display:column  title="打卡考勤" sortable="true" sortName="IS_ATTENDANCE">
					<c:choose>
							<c:when test="${atsAttendanceFileItem.isAttendance==1}">
								<span class="red">是</span>
							</c:when>
							<c:otherwise>
								<span class="green">否</span>
							</c:otherwise>
						</c:choose>
				</display:column>
				<display:column property="attencePolicyName" title="考勤制度" sortable="true" sortName="ATTENCE_POLICY"></display:column>
				<display:column property="holidayPolicyName" title="假期制度" sortable="true" sortName="HOLIDAY_POLICY"></display:column>
				<display:column property="defaultShiftName" title="默认班次" sortable="true" sortName="DEFAULT_SHIFT"></display:column>
				<display:column title="管理" media="html"  class="rowOps">
					<a href="del.ht?id=${atsAttendanceFileItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${atsAttendanceFileItem.id}" class="link edit">编辑</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="atsAttendanceFileItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


