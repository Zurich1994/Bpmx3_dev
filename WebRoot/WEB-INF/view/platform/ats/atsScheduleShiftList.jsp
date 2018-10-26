<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>排班列表管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/platform/ats/AtsDialog.js"></script>
<script type="text/javascript">
	function importData(){
		AtsImport({
			url:__ctx + '/platform/ats/atsScheduleShift/import.ht',
			title:'排班列表导入'
		});
	}

	function selectAttendanceFile(){
		
		AtsAttendanceFileDialog({
			isSingle:true,
			callback:function(data){
				$("#fileId").val(data.id);
				$("#fileName").val(data.userName);
			}
		});
	}
	
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">排班列表管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="group"><a class="link add" href="${ctx}/platform/ats/atsTurnShift/wizard.ht" id="btnWizard"><span></span>排班向导</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link import" href="javascript:importData();"><span></span>导入</a></div>
						<div class="l-bar-separator"></div>
					<div class="group"><a class="link del" href="javascript:void(0);" action="del.ht"><span></span>删除</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<li>
							<span class="label">考勤用户:</span>
							<input type="hidden" id="fileId" name="Q_fileId_SL"  class="inputText" />
							<input type="text" id="fileName" name="Q_fileName_S" value="${param['Q_fileName_S']}"   readonly="readonly" />
							<a href="javascript:void(0);" onclick="selectAttendanceFile()" class="button inputText"><span>选 择...</span></a>
						</li>
						<li>
							<span class="label">考勤日期 从:</span> <input  name="Q_beginattenceTime_DL"  class="inputText date" />
							<span class="label">至: </span><input  name="Q_endattenceTime_DG" class="inputText date" />
						</li>
					</ul>
					<ul class="row">
						<li>
							<span class="label">日期类型:</span>
							<select name="Q_dateType_SN" value="${param['Q_dateType_SN']}">
								<option value="">请选择</option>
								<option value="1" <c:if test="${param['Q_dateType_SN'] == '1'}">selected</c:if>>工作日</option>	
								<option value="2" <c:if test="${param['Q_dateType_SN'] == '0'}">selected</c:if>>休息日</option>
								<option value="3" <c:if test="${param['Q_dateType_SN'] == '0'}">selected</c:if>>法定假日</option>
							</select>
						</li>
					</ul>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="atsScheduleShiftList" id="atsScheduleShiftItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${atsScheduleShiftItem.id}">
				</display:column>
				<display:column property="userName" title="姓名" sortable="true" sortName="userId"></display:column>
				<display:column property="orgName" title="组织" ></display:column>
				<display:column title="考勤日期" sortable="true" sortName="attenceTime">
					<fmt:formatDate value="${atsScheduleShiftItem.attenceTime}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column title="日期类型" sortable="true" sortName="dateType">
					<c:choose>
						<c:when test="${atsScheduleShiftItem.dateType==1}">
							工作日
						</c:when>
						<c:when test="${atsScheduleShiftItem.dateType==2}">
							<span class="red">休息日</span>
						</c:when>
						<c:otherwise>
							<span class="green">
							<c:choose>
								<c:when test="${empty atsScheduleShiftItem.title}">
									法定假日
								</c:when>
								<c:otherwise>
									${atsScheduleShiftItem.title}
								</c:otherwise>
							</c:choose>	
							</span>
						</c:otherwise>
					</c:choose>
				</display:column>
				<display:column property="shiftName" title="班次名称" sortable="true" sortName="shiftId"></display:column>
				<display:column property="cardNumber" title="考勤卡号" sortable="true" sortName="cardNumber"></display:column>
				<display:column property="attencePolicyName" title="考勤制度" sortable="true" sortName="attencePolicy"></display:column>
				<display:column property="cardRuleName" title="取卡规则" sortable="true" sortName="cardRule"></display:column>
			</display:table>
			<hotent:paging tableId="atsScheduleShiftItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


