<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.jee-soft.cn/functions" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<%@ include file="/commons/include/html_doctype.html"%>
<%
	request.setAttribute("toReplace", "\"");
	request.setAttribute("replaceBy", "\\\"");
%>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
	<title><spr:message code="processCenter.pendingMatters"/></title>
	<script type="text/javascript" src="${ctx}/js/hrbeu/platform/system/SysDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hrbeu/platform/form/FormUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/hrbeu/platform/form/SelectorInit.js"></script>
	<script type="text/javascript" src="${ctx}/js/hrbeu/platform/form/CommonDialog.js"></script>
	<f:link href="form.css" ></f:link>
	<script type="text/javascript">
	$(function(){
		$("[name='subject']").click(function(){
			$("#treeFresh",window.parent.document).trigger("click");
		});
	});

	function executeTask(taskId){
		 var url="${ctx}/platform/bpm/task/toStart.ht?taskId="+taskId;
		var rtn = jQuery.openFullWindow(url);
		
	}

	//重启任务
	function restartTask(taskId){
		var url="${ctx}/platform/bpm/task/restartTask.ht?taskId="+taskId;
		var rtn = jQuery.openFullWindow(url);
	}


	function reload(){
		location.href=location.href.getNewUrl();
		parent.globalType.loadGlobalTree();
	}



	function batOperator(operator){
		if(operator=="approve"){
			if ($("#btnApprove").attr('class').indexOf('disabled')>0){return false;}	
		}
		else{
			if ($("#btnBatDelegate").attr('class').indexOf('disabled')>0){return false;}
		}
		
		var aryId = $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");
		var len=aryId.length;
		if(len==0){
			$.ligerDialog.warn($lang.operateTip.selectRecord,$lang.tip.msg);
			return;
		}
		
		var taskIds=new Array();
		$("input[name='id']:checked").each(function(){
			taskIds.push($(this).val());
		});
		var ids=taskIds.join(",");
		
		
		var url=__ctx + "/platform/bpm/task/pendingMattersListBatchApprovalCfm.ht?taskIds="+ids;
		if(operator=="delegate"){
			url=__ctx + "/platform/bpm/task/delegateDialog.ht?taskIds="+ids;
		}
		
		var winArgs="dialogWidth=500px;dialogHeight=250px;help=0;status=0;scroll=0;center=1";
		url=url.getNewUrl();
		var rtn=window.showModalDialog(url,"",winArgs);
		if (rtn=='ok'){
			location.href=location.href.getNewUrl();
		}
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
							<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><f:a alias="batchApproval" css="link save" id="btnApprove" href="#"   onclick="batOperator('approve');"><span></span>批量审批</f:a></div>
						</div>
					</div>
					<div class="panel-search">
						<form id="searchForm" method="post" action="getMyTaskView.ht?typeId=${typeId}">
							<ul class="row">
								<li>
								<span class="label"><spr:message code="processCenter.subjectName"/>:</span>
								<input type="text" name="Q_subject_SUPL"  class="inputText" value="${param['Q_subject_SUPL']}"/>
								</li>
								<c:if test="${fn:length(bpmFormDialog.conditionList)>0}">
									<c:forEach items="${bpmFormDialog.conditionList}" var="col" >
										<c:if test="${col.defaultType==4}">
											<c:forEach items="${paramsMap}" var="pm">
												<c:if test="${pm.key==col.fieldName }">
													<input type="hidden" name="${col.fieldName}" value="${pm.value }" />
												</c:if>
											</c:forEach>
										</c:if>
										<c:if test="${col.fieldType=='isAfferent' and col.defaultType==5}">
											<c:forEach items="${paramsMap}" var="pm">
												<c:if test="${pm.key==col.fieldName }">
													<input type="hidden" name="${col.fieldName}" value="${pm.value }" />
												</c:if>
											</c:forEach>
										</c:if>
										<c:if test="${col.defaultType==1 or (col.defaultType==5 and col.fieldName!='' and col.fieldType!='isAfferent')}">
										<li><span class="label">${col.comment }:</span>
										<c:choose>
											<c:when test="${col.fieldType=='date'}">
												<c:choose>
													<c:when test="${col.condition=='='}">
														<input type="text" name="Q_${col.fieldName}_DL" class="date inputText"  />
													</c:when>
													<c:otherwise>
														从:<input type="text" name="Q_start${col.fieldName}_DL" class="date inputText" />
														</li>
														<li><span class="label"><spr:message code="bpmFormDialog.to"/>:</span>
														<input type="text" name="Q_end${col.fieldName}_DG" class="date inputText" />
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${col.fieldType=='number'}">
														<input type="hidden" name="${col.fieldName}" class="inputText" disabled="true"/>
														<input type="hidden" name="${col.fieldName}ID" class="inputText" disabled="true" onchange="fieldIDChange(this)"/>
														<input type="text" name="Q_${col.fieldName}_DB"  class="inputText" />
													</c:when>
													<c:otherwise>
														<input type="hidden" name="${col.fieldName}" class="inputText" disabled="true"/>
														<input type="hidden" name="${col.fieldName}ID" class="inputText" disabled="true" onchange="fieldIDChange(this)"/>
														<input type="text" name="Q_${col.fieldName }_S"  class="inputText" />
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${col.paraCt=='0'}">
														<input type="button" value="…" id="dialog" dialog="${col.dialog}" param="${col.param}"/>
													</c:when>
													<c:when test="${col.paraCt=='4'}">
														<a href="#" class="link user" name="${col.fieldName}">用户</a>
													</c:when>
													<c:when test="${col.paraCt=='17'}">
														<a href="#" class="link role" name="${col.fieldName}">角色</a>
													</c:when>
													<c:when test="${col.paraCt=='18'}">
														<a href="#" class="link org" name="${col.fieldName}">组织</a>
													</c:when>
													<c:when test="${col.paraCt=='19'}">
														<a href="#" class="link pos" name="${col.fieldName}">岗位</a>
													</c:when>
												</c:choose>
											</c:otherwise>
										</c:choose>
										</li>
										</c:if>
									</c:forEach>
								</c:if>
							</ul>
						</form>
					</div>
				</div>
				<div class="panel-body">
						<div class='panel-table'>
						<table id="bpmFormDialogTable" cellpadding="1" class="table-grid table-list" cellspacing="1">
							<thead>
							<tr>
							<th>
								<input type="checkbox" id="chkall"/>
							</th>
							<th><spr:message code="processCenter.subjectName"/></th>
							<th><spr:message code="processCenter.pendingType"/></th>
							<th><spr:message code="processCenter.status"/></th>
							<c:forEach items="${bpmFormDialog.displayList}" var="field">
								<c:set var="sortMark">
									<c:if test="${sortField==field.fieldName}">
										<c:choose>
											<c:when test="${orderSeq=='ASC'}">↑</c:when>
											<c:otherwise>↓</c:otherwise>
										</c:choose>
									</c:if>
								</c:set>
								<th><a href="${baseHref}&newSortField=${field.fieldName}">${field.comment }${sortMark}</a></th>
							</c:forEach>
							</thead>
							<tbody>
								<c:forEach items="${taskDataList}" var="taskData" varStatus="status">
									<c:set var="clsName" ><c:choose><c:when test="${status.index %2==0 }">even</c:when><c:otherwise>odd</c:otherwise></c:choose></c:set>
									<tr class="${clsName}">
										<td>
											<input type="checkbox" name="id" class="pk" value="${taskData.taskId}"/>
										</td>
										<td>
										<c:choose>
											<c:when test="${taskData.type eq '36'}">
												<a name="subject"  href="#${taskData.taskId}" onclick="javascript:restartTask(${taskData.taskId})" title='${taskData.taskName}'  <c:if test="${taskData.hasRead == 0}"> class='message close-message'</c:if><c:if test="${taskItem.hasRead != 0}"> class='message open-message'</c:if> >${f:subString(taskData.taskName)}</a>
											</c:when>
											<c:otherwise>
												<a name="subject"  href="#${taskData.taskId}" onclick="javascript:executeTask(${taskData.taskId})" title='${taskData.taskName}' <c:if test="${taskData.hasRead == 0}"> class='message close-message'</c:if><c:if test="${taskItem.hasRead != 0}"> class='message open-message'</c:if> >${f:subString(taskData.taskName)}</a>
											</c:otherwise>
										</c:choose>
										</td>
										<td>
											<c:choose>
												<c:when test="${taskData.status==1}"><spr:message code="processRun.status.approval"/></c:when>
												<c:when test="${taskData.status==5}"><spr:message code="processRun.status.recover"/></c:when>
												<c:when test="${taskData.status==6}"><spr:message code="processRun.status.reject"/></c:when>
												<c:when test="${taskData.status==7}"><spr:message code="processRun.status.retrieved"/></c:when>
											</c:choose>
										</td>
										<td>
										<c:choose>
											<c:when test="${taskData.type=='-1'}">
												<span class="green"><spr:message code="processCenter.pendingType.pending"/></span>
											</c:when>
											<c:when test="${taskData.type eq '21' }" >
												<span class="brown"><spr:message code="processCenter.pendingType.delegate"/></span>
											</c:when>
											<c:when test="${taskData.type eq '15' }" >
												<span class="orange"><spr:message code="processCenter.pendingType.commu"/></span>
											</c:when>
											<c:when test="${taskData.type eq '26' }" >
												<span class="brown"><spr:message code="processCenter.pendingType.agent"/></span>
											</c:when>
											<c:when test="${taskData.type eq '38' }" >
												<span class="red"><spr:message code="processCenter.pendingType.trans"/></span>
											</c:when>
										</c:choose>
										</td>
										<c:forEach items="${bpmFormDialog.displayList}" var="field">
											<td>${taskData.data[field.fieldName] }</td>	
										</c:forEach>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						</div>
						<c:if test="${bpmFormDialog.needpage==1 }">
						${pageHtml}
						</c:if>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>
