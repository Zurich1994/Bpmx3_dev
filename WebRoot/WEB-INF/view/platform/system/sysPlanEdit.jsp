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
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/HtmlUploadDialog.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/FlexUploadDialog.js" ></script>

<script type="text/javascript">

	$(function(){
		//初始化选择器
		initData();
		
		//初始化返回提示
		$('#sysPlanEdit').ajaxForm({success:showResponse });
		
		//初始化保存按键
	    initSubmit();
		
		//初始化点击人员事件
	    openDetailEvent();

	    AttachMent.init("w");
	});
	
	
	//提交后返回内容
	function showResponse(r){
		var type = $("input[name='type']").val();
		var id = $("input[name='id']").val();
		var data = eval("("+r+")");
		var currentViweDate = $("input[name='currentViweDate']").val();
		if(data.result){
			$.ligerDialog.success(data.message,"消息提示",function(){
				var url = __ctx + '/platform/system/sysPlan/submit.ht?currentViweDate='+currentViweDate;
				if(id!=''&&type=='charge'){
					url = __ctx + '/platform/system/sysPlan/exchange.ht?id='+id+'&currentViweDate='+currentViweDate;
				}else if(type=='charge'){
					url = __ctx + '/platform/system/sysPlan/charge.ht?currentViweDate='+currentViweDate;
				}
				window.location.href = url;
			});
		}
		else{
			$.ligerDialog.warn(data.message,"消息提示");
		}
	}
	
	
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">编辑日程</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<c:if test="${sysPlan.rate!=100}">
					    <%-- <c:if test="${sysPlan.id==null||$sysPlan.id==''||type eq 'charge'}"> </c:if> --%>
						<div class="group">
							<a class="link save" ><span></span>保存</a>
						</div>
						<div class="l-bar-separator"></div>
					</c:if>
					
					<div class="group">
					    <c:choose>
							<c:when test="${type eq 'charge'}">
								<a class="link back" href="${ctx}/platform/system/sysPlan/charge.ht?currentViweDate=${currentViweDate}"><span></span>返回</a>
							</c:when>
							<c:when test="${type eq 'myPlan'}">
								<a class="link back" href="${ctx}/platform/system/sysPlan/myList.ht"><span></span>返回</a>
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
			<form id="sysPlanEdit" method="post" action="${ctx}/platform/system/sysPlan/save.ht">
				<input type="hidden" name="id" value="${sysPlan.id}"/>
				<input type="hidden" name="currentViweDate" value="${currentViweDate}"/>
				<input type="hidden" name="type" value="${type}"/>
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">				 
					
					<tr>
						<th width="20%">任务名称:</th>
						<td>
							<input type="text" name="taskName"  class="inputText" style="width: 250px;" value="${sysPlan.taskName}" validate="{'required':true}" />
						</td>
						<th width="20%">提交人:</th>
						<td>
							<input type="hidden" name="submitId" value="${sysPlan.submitId}"  />
							<input type="hidden" name="submitor" value="${sysPlan.submitor}"  />
							<div id='submitDiv'></div>
							<a class="link add" onclick="chooseUser(this,'submitDiv','submitId','submitor','yes');" ><span></span>选择</a>
							<a class="link reset" onclick="resetSelect('submitDiv','submitId','submitor');" ><span></span>重置</a>
						</td>
					</tr>
					
					<tr>
						<th width="20%">负责人:</th>
						<td>
							<input type="hidden" name="chargeId" value="${sysPlan.chargeId}" />
							<input type="hidden" name="charge" value="${sysPlan.charge}"  />
							<div id='chargeDiv'></div>
							<a class="link add" onclick="chooseUser(this,'chargeDiv','chargeId','charge','yes');" ><span></span>选择</a>
							<a class="link reset" onclick="resetSelect('chargeDiv','chargeId','charge');" ><span></span>重置</a>
						</td>
						<th width="20%">参与人:</th>
						<td>
							<input type="hidden" name="participantIds" value="${participantIds}"  />
							<input type="hidden" name="participants" value="${participants}"  />
							<div id='participantDiv'></div>
							<a class="link add" onclick="chooseUser(this,'participantDiv','participantIds','participants','no');" ><span></span>选择</a>
							<a class="link reset" onclick="resetSelect('participantDiv','participantIds','participants');" ><span></span>重置</a>
						</td>
					</tr>
					
					<tr>
						<th width="20%">开始时间:</th>
						<td>
							<input type="text" name="startTime"  class="inputText datetime" style="width: 150px;"  value="<fmt:formatDate value='${sysPlan.startTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" validate="{'required':true}"/>
						</td>
						<th width="20%">结束时间:</th>
						<td>
							<input type="text" name="endTime"  class="inputText datetime" style="width: 150px;"  value="<fmt:formatDate value='${sysPlan.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" validate="{'required':true}"/>
						</td>
					</tr>
					
					<c:choose>
						<c:when test="${type eq 'charge' or type eq 'myPlan'}">
							<tr>
								<th width="20%">项目名称:</th>
								<td>
									<input type="text" name="projectName"  class="inputText" style="width: 250px;" value="${sysPlan.projectName}" validate="{'required':true}" />
								</td>
								<th width="20%">日程进度:</th>
								<td>
									<input type="text" name="rate"  class="inputText" value="${sysPlan.rate}" validate="{'range':[0,100],'digits':true}" /> %
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<th width="20%">项目名称:</th>
								<td>
									<input type="text" name="projectName" class="inputText" style="width: 250px;" value="${sysPlan.projectName}" validate="{'required':true}" />
								</td>
								<th width="20%">日程进度:</th>
								<td>
									<c:choose>
										<c:when test="${sysPlan.rate==null ||sysPlan.rate==''}">
											0%
										</c:when>
										<c:otherwise>
											${sysPlan.rate}%
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
					
					<tr>
						<th width="20%">相关客户:</th>
						<td colspan="3">
							<input type="hidden" name="customerId" value="${sysPlan.customerId}" />
							<input type="hidden" name="customer" value="${sysPlan.customer}"  />
							<div id='customerDiv'></div>
							<a class="link add" onclick="chooseCustomer(this,'spxx','','customerDiv','customerId','customer');" ><span></span>选择</a>
							<a class="link reset" onclick="resetSelect('customerDiv','customerId','customer');" ><span></span>重置</a>
						</td>
					</tr>
					<tr>
						<th width="20%">相关文档: </th>
						<td colspan="3">
							<div name="div_attachment_container">
								<div class="attachement"></div>
								<textarea style="display: none" controltype="attachment"
									id="doc" name="doc" lablename="主表附件" validate="{}">${sysPlan.doc}</textarea>
								<a href="javascript:;" field="doc" class="link selectFile"
									type="select" onclick="AttachMent.addFile(this);">选择</a>
							</div> 
						</td>
					</tr>
					<tr>
						<th width="20%">工单:</th>
						<td colspan="3">
							<input type="hidden" name="runId" value="${sysPlan.runId}"  />
							<input type="hidden" name="runName" value="${sysPlan.runName}"  />
							<div id='runDiv'></div>
							<a class="link add" onclick="chooseProcessRun(this,'runDiv','runId','runName','yes');" ><span></span>选择</a>
							<a class="link reset" onclick="resetSelect('runDiv','runId','runName');" ><span></span>重置</a>
						</td>
					</tr>
					<tr>
						<th width="20%">内容:</th>
						<td colspan="3">
							<textarea name="description" rows="30" style="width: 98%;overflow:scroll;">${sysPlan.description}</textarea>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	
</body>
</html>

