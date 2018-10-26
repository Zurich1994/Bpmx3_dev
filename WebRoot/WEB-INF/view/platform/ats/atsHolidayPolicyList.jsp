<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>假期制度管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript">
function selectOrg(){
	OrgDialog({isSingle:true,callback:function(orgId,orgName){
		$('#orgId').val(orgId);
		$('#orgName').val(orgName);
	}});
}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">假期制度管理列表</span>
			</div>
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
					<ul class="row">
						<li>
							<span class="label">编码:</span><input type="text" name="Q_code_SL"  class="inputText" value="${param['Q_code_SL']}"/>
						</li>
						<li>
							<span class="label">名称:</span><input type="text" name="Q_name_SL"  class="inputText" value="${param['Q_name_SL']}"/>
						</li>
						<li>
							<span class="label">所属组织:</span>
							<input type="hidden" id="orgId" name="orgId" value="${atsHolidayPolicy.orgId}" value="${param['Q_orgId_S']}"/>
							<input type="text" id="orgName"  value="${atsHolidayPolicy.orgName}"   readonly="readonly" value="${param['Q_orgName_SL']}"/>
							<a href="javascript:;" onclick="selectOrg()" class="button inputText"><span>选 择...</span></a>
						</li>
					</ul>
					<ul class="row">
						<li>
							<span class="label">是否默认:</span>
							<select name="Q_isDefault_S" value="${param['Q_isDefault_S']}"  class="inputText">
								<option value="">请选择</option>
								<option value="1" <c:if test="${param['Q_isDefault_S'] == '1'}">selected</c:if>>是</option>	
								<option value="0" <c:if test="${param['Q_isDefault_S'] == '0'}">selected</c:if>>否</option>
							</select>
						</li>
						<li>
							<span class="label">是否启动半天假:</span>
							<select name="Q_isHalfDayOff_S" value="${param['Q_isHalfDayOff_S']}"  class="inputText">
								<option value="">请选择</option>
								<option value="1" <c:if test="${param['Q_isHalfDayOff_S'] == '1'}">selected</c:if>>是</option>	
								<option value="0" <c:if test="${param['Q_isHalfDayOff_S'] == '0'}">selected</c:if>>否</option>
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
		    <display:table name="atsHolidayPolicyList" id="atsHolidayPolicyItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${atsHolidayPolicyItem.id}">
				</display:column>
				<display:column property="code" title="编码" sortable="true" sortName="CODE" maxLength="80"></display:column>
				<display:column property="name" title="名称" sortable="true" sortName="NAME" maxLength="80"></display:column>
				<display:column property="orgName" title="所属组织" sortable="true" sortName="ORG_ID">
				</display:column>
				<display:column  title="是否默认" sortable="true" sortName="IS_DEFAULT">
						<c:choose>
							<c:when test="${atsHolidayPolicyItem.isDefault==1}">
								<span class="red">是</span>
							</c:when>
							<c:otherwise>
								<span class="green">否</span>
							</c:otherwise>
						</c:choose>
				</display:column>
				<display:column  title="是否启动半天假" sortable="true" sortName="IS_HALF_DAY_OFF">
					<c:choose>
							<c:when test="${atsHolidayPolicyItem.isHalfDayOff==1}">
								<span class="red">是</span>
							</c:when>
							<c:otherwise>
								<span class="green">否</span>
							</c:otherwise>
						</c:choose>
				</display:column>
				<display:column title="管理" media="html"  class="rowOps">
					<a href="del.ht?id=${atsHolidayPolicyItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${atsHolidayPolicyItem.id}" class="link edit">编辑</a>
					<a href="get.ht?id=${atsHolidayPolicyItem.id}" class="link detail">明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="atsHolidayPolicyItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


