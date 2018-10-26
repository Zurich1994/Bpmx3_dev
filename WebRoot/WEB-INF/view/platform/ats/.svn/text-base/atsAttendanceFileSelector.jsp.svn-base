
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>选择考勤档案</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">
	var isSingle="${isSingle}";
	$(function(){
		 $("#atsAttendanceFileItem>tbody").find("tr").bind('click', function() {
			 if(isSingle!='true'){
				var ch=$(this).find(":checkbox");
				window.parent.selectMulti(ch);
			}
		});	
		 $("#chkall").bind("click",function(){
		        var checkAll=false;
				if($(this).attr("checked")){
					checkAll=true;	
				}
				var checkboxs=$(":checkbox",$("#atsAttendanceFileItem>tbody"));
				checkboxs.each(function(){
					if(checkAll){
						window.parent.selectMulti(this);
					}
				});
		 });
	
	});
</script>
<style type="text/css">
	div.bottom{text-align: center;padding-top: 10px;}
	body {overflow: hidden;}
</style>
</head>
<body>

	<div  class="panel-top">
		<div class="panel-search">
			<form id="searchForm" method="post" action="${ctx}/platform/ats/atsAttendanceFile/selector.ht" >
				<ul class="row">
					<input type="hidden" name="isSingle" value="${isSingle }">
					<li><span class="label" >姓名:</span><input size="14" type="text" name="Q_fullname_SL"  class="inputText" style="width:60%;" value="${param['Q_fullname_SL']}"/>
					&nbsp;<a href="javascript:;" onclick="$('#searchForm').submit()" class='button'><span>查询</span></a></li>
				</ul>
			</form>
		</div>
	</div>
		<c:choose>
			<c:when test="${isSingle==false}">
				<c:set var="checkAll">
					<input type="checkbox" id="chkall" name="chkall" />
				</c:set>
			</c:when>
			<c:otherwise>
				<c:set var="checkAll" value="选择"/>
			</c:otherwise>
		</c:choose>
	    <display:table name="atsAttendanceFileList" id="atsAttendanceFileItem" requestURI="selector.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
			<display:column title="${checkAll}" media="html" style="width:30px;">
				<c:choose>
					<c:when test="${isSingle==false}">
						<input type="checkbox" class="pk" name="data" value="${atsAttendanceFileItem.id}#${atsAttendanceFileItem.userName}#${atsAttendanceFileItem.account}#${atsAttendanceFileItem.orgName}">
					</c:when>
					<c:otherwise>
						<input type="radio" class="pk" name="data" value="${atsAttendanceFileItem.id}#${atsAttendanceFileItem.userName}#${atsAttendanceFileItem.account}#${atsAttendanceFileItem.orgName}">
			
					</c:otherwise>
				</c:choose>
			
			</display:column>
			<display:column property="userName" title="姓名" sortable="true"  maxLength="80"></display:column>
			<display:column property="orgName" title="组织名称" sortable="true"  maxLength="80"></display:column>
			<display:column property="cardNumber" title="考勤卡号" sortable="true" sortName="CARD_NUMBER" maxLength="80"></display:column>
			<display:column property="attencePolicyName" title="考勤制度" sortable="true" sortName="ATTENCE_POLICY"></display:column>
			<display:column property="holidayPolicyName" title="假期制度" sortable="true" sortName="HOLIDAY_POLICY"></display:column>
			<display:column property="defaultShiftName" title="默认班次" sortable="true" sortName="DEFAULT_SHIFT"></display:column>
	
		</display:table>
		<hotent:paging tableId="atsAttendanceFileItem" showExplain="false"/>
</body>
</html>


