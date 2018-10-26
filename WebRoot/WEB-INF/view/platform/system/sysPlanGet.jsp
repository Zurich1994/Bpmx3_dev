<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title>编辑日程</title>
<style type="text/css">
	.owner-span{
		font-size: 13px;
		background-color: #EFF2F7;
	    border: 1px solid #CCD5E4;
	    border-radius: 5px 5px 5px 5px;
	    cursor: default;
	    float: left;
	    height: auto !important;
	    margin: 3px;
	    overflow: hidden;
	    padding: 2px 4px;
	    white-space: nowrap;
	}
</style>
<f:link href="form.css" ></f:link>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysPlanScript.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysPlan"></script>

<script type="text/javascript" src="${ctx}/js/hotent/platform/form/AttachMent.js" ></script>
<script type="text/javascript">

	$(function(){
		//初始化选择器
		initData();
		
		//初始化点击人员事件
	    openDetailEvent();

	    AttachMent.init("r");
	});

	
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">日程详细</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<c:choose>
							<c:when test="${type eq 'charge'}">
								<a class="link back" href="${ctx}/platform/system/sysPlan/charge.ht?currentViweDate=${currentViweDate}"><span></span>返回</a>
							</c:when>
							<c:otherwise>
								<a class="link back" href="${ctx}/platform/system/sysPlan/submit.ht?currentViweDate=${currentViweDate}"><span></span>返回</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="sysPlanEdit" method="post" action="save.ht">
				<input type="hidden" name="id" value="${sysPlan.id}"/>
				<input type="hidden" name="currentViweDate" value="${currentViweDate}"/>
				<input type="hidden" name="type" value="${type}"/>
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">				 
					
					<tr>
						<th width="20%">任务名称:</th>
						<td>
							${sysPlan.taskName}
						</td>
						<th width="20%">提交人:</th>
						<td>
							<input type="hidden" name="submitId" value="${sysPlan.submitId}"  />
							<input type="hidden" name="submitor" value="${sysPlan.submitor}"  />
							<div id='submitDiv'></div>
						</td>
					</tr>
					
					<tr>
						<th width="20%">负责人:</th>
						<td>
							<input type="hidden" name="chargeId" value="${sysPlan.chargeId}" />
							<input type="hidden" name="charge" value="${sysPlan.charge}"  />
							<div id='chargeDiv'></div>
						</td>
						<th width="20%">参与人:</th>
						<td>
							<input type="hidden" name="participantIds" value="${participantIds}"  />
							<input type="hidden" name="participants" value="${participants}"  />
							<div id='participantDiv'></div>
						</td>
					</tr>
					
					<tr>
						<th width="20%">开始时间:</th>
						<td>
							<fmt:formatDate value='${sysPlan.startTime}' pattern='yyyy-MM-dd HH:mm:ss'/>
						</td>
						<th width="20%">结束时间:</th>
						<td>
							<fmt:formatDate value='${sysPlan.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>
						</td>
					</tr>
					
					<tr>
						<th width="20%">项目名称:</th>
						<td>
							${sysPlan.projectName}
						</td>
						<th width="20%">日程进度:</th>
						<td>
							${sysPlan.rate}%
						</td>

					</tr>
					
					<tr>
						<th width="20%">相关客户:</th>
						<td colspan="3">
							<input type="hidden" name="customerId" value="${sysPlan.chargeId}" />
							<input type="hidden" name="customer" value="${sysPlan.charge}"  />
							<div id='customerDiv'></div>
						</td>
					</tr>
					
					<tr>
						<th width="20%">相关文档: </th>
						<td colspan="3">
							<div name="div_attachment_container">
								<div class="attachement"></div>
								<textarea style="display: none" controltype="attachment"
									id="doc" name="doc" lablename="主表附件" validate="{}">${sysPlan.doc}</textarea>
							</div> 
						</td>
					</tr>
					
					<tr>
						<th width="20%">工单:</th>
						<td colspan="3">
							<input type="hidden" name="runId" value="${sysPlan.runId}"  />
							<input type="hidden" name="runName" value="${sysPlan.runName}"  />
							<div id='runDiv'></div>
						</td>

					</tr>
					
					<tr>
						<th width="20%">内容:</th>
						<td colspan="3">
							${sysPlan.description}
						</td>

					</tr>
					
				</table>
			</form>
		</div>
	</div>
	
	
</body>
</html>

