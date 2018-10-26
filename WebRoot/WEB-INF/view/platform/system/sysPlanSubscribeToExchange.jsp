<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title>日程交流--我的订阅</title>
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
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/FlexUploadDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
<script type="text/javascript" src="${ctx}/codegen/js/AttachMent.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysPlanScript.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysPlan"></script>
<script type="text/javascript">

	$(function(){
		//初始化选择器
		initData();
		
		//初始化点击人员事件
	    openDetailEvent();
		

	});
	
	
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">日程交流--我的订阅</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="${ctx}/platform/system/sysPlan/subscribe.ht?currentViweDate=${currentViweDate}"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="sysPlanExchange" method="post" action="">
				<div class="panel-detail">
				<input type="hidden" name="id" value="${sysPlan.id}"/>
				<input type="hidden" name="currentViweDate" value="${currentViweDate}"/>
				<input type="hidden" name="type" value="subscribe"/>
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
						<td>
							<input type="hidden" name="customerId" value="${sysPlan.chargeId}" />
							<input type="hidden" name="customer" value="${sysPlan.charge}"  />
							<div id='customerDiv'></div>
						</td>
						<th width="20%">相关文档:</th>
						<td>
							<div  name="div_attachment_container"  >
							<div  class="attachement" ></div>
							<textarea style="display:none" controltype="attachment" name="doc" lablename="相关文档">${sysPlan.doc}</textarea>
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
					
					<tr>
						<th width="20%">交流信息:</th>
						<td colspan="3">

						    <div>
								<table id="exchangeTable" style="float: left;"  class="table-grid table-list"  cellpadding="0" cellspacing="0" border="0">
									
									<thead>
										<tr>
											<th width="10%;" style="text-align: center;"><input type="checkbox" id="all_exchangeId" onclick="selectCheckbox('all_exchangeId')"/></th>
											<th width="35%;" style="text-align: center;">提交内容</th>
											<th width="20%;" style="text-align: center;">提交人</th>
											<th width="20%;" style="text-align: center;">提交时间</th>
											<th width="15%;" style="text-align: center;">管理</th>
										</tr> 
									</thead>
									
								    <c:choose>
										
										<c:when test="${fn:length(sysPlanExchangeList)>0 }">
											<c:forEach items="${sysPlanExchangeList}" var="exchange">
												<tr>
													<td style="text-align: center;">
													     <input type="checkbox" name="exchangeId" value="${exchange.id}" parentId="${exchange.planId}">
													</td>
													<td style="text-align: center;" >
													    <c:choose>
															<c:when test="${fn:length(exchange.content)>21}">
																 <c:out value="${fn:substring(exchange.content, 0, 20)}..." /> 
															</c:when>
															<c:otherwise>
																<c:out value="${exchange.content}" /> 
															</c:otherwise>
														</c:choose>	
													</td>
													<td>
														<c:if test="${fn:length(exchange.submitor)>0}">
															<span class="owner-span">
																<a class="moreinfo" ownerid="${exchange.submitId}" hrefstr="${ctx}/platform/system/sysUser/get.ht?openType=detail&userId=${exchange.submitId}" href="#">
																	${exchange.submitor}
																</a>
															</span>
														</c:if>
													</td>
													<td style="text-align: center;" >
													    <fmt:formatDate value='${exchange.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>
													</td>
													<td style="text-align: center;" >
													    <%-- <a class="link" onclick="editSysPlanExchange('${exchange.id}','${exchange.planId}')" href="#">编辑</a> --%>
													    <a class="link" onclick="openSysPlanExchange('${exchange.id}','${exchange.planId}')" href="#">查看</a>
													</td>
												</tr>
											</c:forEach>
										</c:when>
										
										<c:otherwise>
											<tr class="empty-div" >
												<td colspan="5" style="text-align: center;"> 
													暂没有交流信息
												</td>
											</tr>
										</c:otherwise>
										
									</c:choose>							 
							   			
								</table>
							</div>
						</td>

					</tr>
					
				</table>
				</div>
			</form>
		</div>
	</div>
	
</body>
</html>

