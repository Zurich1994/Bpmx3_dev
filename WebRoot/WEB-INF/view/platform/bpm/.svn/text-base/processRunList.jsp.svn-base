<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程实例管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowDetailWindow.js"></script>
<script type="text/javascript">
	function addUser(){
		UserDialog({isSingle:true,callback:function(userIds,fullnames,emails,mobiles){
			$('#cmpNames').val(fullnames);
		}});
	}
	
	function addOrg(){
		OrgDialog({isSingle:true,callback:function(userIds,fullnames,emails,mobiles){
			$('#orgNames').val(fullnames);
		}});
	}
</script>
</head>
<body>
			<div class="panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">流程实例管理列表</span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
							<div class="l-bar-separator"></div>
							<%-- <a class="link del"  action="del.ht">删除</a>--%>
							<%-- 能够控制按钮权限的写法，注意别名不要写错--%>
							<div class="group"><f:a alias="delRun" css="link del" action="del.ht"><span></span>删除</f:a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
						</div>	
					</div>
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
										<li><span class="label">流程定义名称:</span><input type="text" name="Q_processName_SL"  class="inputText" value="${param['Q_processName_SL']}"/></li>
										<li><span class="label">流程实例标题:</span><input type="text" name="Q_subject_SL"  class="inputText" value="${param['Q_subject_SL']}"/></li>
										<li><span class="label">流程运行ID:</span><input type="text" name="Q_runId_S"  class="inputText" value="${param['Q_runId_S']}"/></li>
										<li><span class="label">流程实例ID:</span><input type="text" name="Q_actInstId_SL"  class="inputText" value="${param['Q_actInstId_SL']}"/></li>
										<li><span class="label">流程业务ID:</span><input type="text" name="Q_businessKey_SL"  class="inputText" value="${param['Q_businessKey_SL']}"/></li>
										<div class="row_date">
											<li><span class="label">创建时间 从:</span><input  name="Q_begincreatetime_DL"  class="inputText date" value="${param['Q_begincreatetime_DL']}"/></li>
											<li><span class="label">至: </span><input  name="Q_endcreatetime_DG" class="inputText date" value="${param['Q_endcreatetime_DG']}"></li>
										</div>
										<li><span class="label">状态:</span>
										<select name="Q_status_SN" value="${param['Q_status_SN']}">
											<option value="">所有</option>
											<option value="1" <c:if test="${param['Q_status_SN'] == '1'}">selected</c:if>>正在运行</option>
											<option value="2" <c:if test="${param['Q_status_SN'] == '2'}">selected</c:if>>结束</option>
											<option value="3" <c:if test="${param['Q_status_SN'] == '3'}">selected</c:if>>人工结束</option>
											<option value="4" <c:if test="${param['Q_status_SN'] == '4'}">selected</c:if>>草稿</option>
											<option value="5" <c:if test="${param['Q_status_SN'] == '5'}">selected</c:if>>撤销</option>
											<option value="6" <c:if test="${param['Q_status_SN'] == '6'}">selected</c:if>>驳回</option>
											<option value="10" <c:if test="${param['Q_status_SN'] == '10'}">selected</c:if>>逻辑删除</option>
										</select>
										</li>
										<li>
											<input type="hidden" id="cmpIds" name="Q_creatorId_L" value="" />
											<span class="label">创建人:</span>
											<input type="text" id="cmpNames" name="Q_creator_SL" class="inputText" value="${param['Q_creator_SL'] }"/>
											<input type="button" onclick="addUser();" value="..." />
										</li>
										<li>
											<input type="hidden" id="orgIds" name="Q_startOrgId_L" value="" />
											<span class="label">创建部门:</span>
											<input type="text" id="orgNames" name="Q_startOrgName_SL"  class="inputText" value="${param['Q_startOrgName_SL'] }"/>
											<input type="button" onclick="addOrg();" value="..." />
										</li>
									</ul>
							</form>
					</div>		
				</div>
				</div>
				<div class="panel-body">
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="processRunList" id="processRunItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="runId" value="${processRunItem.runId}">
							</display:column>
							<display:column  title="流程实例标题" media="html" maxLength="18" sortable="true" sortName="subject" style="text-align:left">
								<a href="#${processRunItem.runId}" onclick="FlowDetailWindow({runId:${processRunItem.runId}})" style="text-align:left;" >${processRunItem.subject}</a>
							</display:column>
							<display:column property="processName" title="流程定义名称" sortable="true" sortName="processName" style="text-align:left"></display:column>
							<display:column property="creator" title="创建人" sortable="true" sortName="creator" style="text-align:left"></display:column>
							<display:column  title="创建时间" sortable="true" sortName="createtime">
								<fmt:formatDate value="${processRunItem.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</display:column>
							<display:column  title="结束时间" sortable="true" sortName="endTime">
								<fmt:formatDate value="${processRunItem.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</display:column>
							<display:column title="持续时间" sortable="true" sortName="duration">
								${f:getTime(processRunItem.duration)}
							</display:column>
							<display:column title="状态" sortable="true" sortName="status">
								<c:choose>
									<c:when test="${processRunItem.status==1}">
										<span class='green'>正在运行</span>
									</c:when>
									<c:when test="${processRunItem.status==2}">
										<span class="red">结束</span>
									</c:when>
									<c:when test="${processRunItem.status==3}">
										<span class="brown">人工结束</span>
									</c:when>
									<c:when test="${processRunItem.status==4}">
										<span class="green">试运行</span>
									</c:when>
								</c:choose>
							</display:column>
							<display:column title="管理" media="html" style="width:140px">
								<c:if test="${processRunItem.authorizeRight.instanceDel=='Y'}">
									<f:a alias="delRun" href="del.ht?runId=${processRunItem.runId}" css="link del">删除</f:a>
								</c:if>
								<c:if test="${processRunItem.authorizeRight.instanceLog=='Y'}">
									<a href="${ctx}/platform/bpm/bpmRunLog/list.ht?runId=${processRunItem.runId}" class="link log">操作日志</a>
								</c:if>
							</display:column>
						</display:table>
						<hotent:paging tableId="processRunItem"/>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


