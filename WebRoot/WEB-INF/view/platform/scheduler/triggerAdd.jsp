<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
	<head>
		<title>添加计划</title>
		<%@include file="/commons/include/form.jsp" %>
		<script type="text/javascript" src="${ctx }/js/lg/plugins/ligerWindow.js" ></script>
		<script type="text/javascript" src="${ctx }/js/hotent/platform/scheduler/JobDialog.js"></script>
		<script type="text/javascript" src="${ctx }/js/hotent/platform/scheduler/Trigger.js"></script>
		<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerDialog.js"></script>
 		<script  type="text/javascript">
 		$(function() {
 			
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			valid(showRequest,showResponse);
			$("a.save").click(function() {
				var str=getPlan();
				$("#planJson").val(str);
				$('#dataForm').submit(); 
			});
		});
 		function showResponse(responseText, statusText)  { 
			var obj=new com.hotent.form.ResultMessage(responseText);
			if(obj.isSuccess()){//成功
				$.ligerDialog.confirm(obj.getMessage()+',是否继续操作?','提示信息',function(rtn){
					if(!rtn){
						var returnUrl=$("a.back").attr("href");
						location.href=returnUrl;
					}
					else{
						valid.resetForm();
					}
				});
				
		    }else{//失败
		    	$.ligerDialog.err('出错信息',"添加计划任务失败",obj.getMessage());
		    }
		} 
		var valid;
		function valid(showRequest,showResponse){
		var options={};
		if(showRequest )
			options.beforeSubmit=showRequest;
		if(showResponse )
		   options.success=showResponse;
		valid=$("#dataForm").validate({
			rules: {
				name:{required:true,maxlength:128}
			},
			messages: {
				name:{required:"计划名称必填.",maxlength:"任务 最多 128 个字符."
				}
			},
			submitHandler:function(form){
				$(form).ajaxSubmit(options);
		    },
		    success: function(label) {
				label.html("&nbsp;").addClass("checked");
			}
			});
		}
		
 		</script>
	</head>
	<body>
		<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">添加定时计划:${jobName}</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="getTriggersByJob.ht?jobName=${jobName}"><span></span>返回</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<form id="dataForm" method="post" action="addTrigger2.ht">
					
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
						    
							<th width="20%">计划名称: </th>
							<td>
							<input type="text" id="name" name="name" class="inputText" size="40"/>
							<input id="planJson" name="planJson" type="hidden" />	
						    <input id="jobName" name="jobName" type="hidden" value="${jobName}" />
							</td>
						</tr>
						<tr>
							<th colspan="2"  style="text-align: left;padding-left: 5px;">
								执行计划的方式           
							</th>
						</tr>
						<tr>
							<th width="20%"><input type="radio" value="1"  name="rdoTimeType" />一次: </th>
							<td>
							开始:<input type="text" id="txtOnceDate" class="Wdate" size="10" onclick="WdatePicker({minDate:'%y-%M-{%d}'})" />
							<select id="txtOnceHour">
								<c:forEach begin="0" end="23" step="1" var="tmp">
											<option value="${tmp }">${tmp }时</option>
								</c:forEach>
							</select>
							<select id="txtOnceMinute">
								<c:forEach begin="0" end="55" step="5" var="tmp">
										<option value="${tmp }">${tmp }分</option>
									</c:forEach>
								<option value="59">59分</option>
							</select>
							<select id="txtOnceSecond">
								<c:forEach begin="0" end="55" step="5" var="tmp">
										<option value="${tmp }">${tmp }分</option>
								</c:forEach>
								
								<option value="59">59秒</option>
							</select>
						</td>
						</tr>
						<tr>
							<th width="20%"><input type="radio" checked="checked" value="2" name="rdoTimeType" />每天  </th>
							<td>
							<select id="selEveryDay">
							<option value="1">1分钟</option>
	               			<option value="5">5分钟</option>
	               			<option value="10">10分钟</option>
	               			<option value="15">15分钟</option>
	               			<option value="30">30分钟</option>
	               			<option value="60">1小时</option>
	               		</select>
							</td>
						</tr>
						<tr>
							<th width="20%"><input type="radio"  value="3" name="rdoTimeType" />每天  </th>
							<td>
								
								<select id="txtDayHour">
									<c:forEach begin="0" end="23" step="1" var="tmp">
										<option value="${tmp }">${tmp }时</option>
									</c:forEach>
								</select>
						
								<select id="txtDayMinute">
									<c:forEach begin="0" end="55" step="5" var="tmp">
												<option value="${tmp }">${tmp }分</option>
									</c:forEach>
									<option value="59">59分</option>
								</select>
							</td>
						</tr>
						<tr>
							<th width="20%"><input type="radio" value="4" name="rdoTimeType" />每周  </th>
							<td>
							     <input type="checkbox" name="chkWeek" value="MON"/>星期一
			               		 <input type="checkbox" name="chkWeek" value="TUE"/>星期二
			               		 <input type="checkbox" name="chkWeek" value="WED"/>星期三
			               		 <input type="checkbox" name="chkWeek" value="THU"/>星期四
			               		 <input type="checkbox" name="chkWeek" value="FRI"/>星期五
			               		 <input type="checkbox" name="chkWeek" value="SAT"/>星期六
			               		 <input type="checkbox" name="chkWeek" value="SUN"/>星期日	<br/>
	               				 <select id="txtWeekHour">
			               		 	<c:forEach begin="0" end="23" step="1" var="tmp">
										<option value="${tmp }">${tmp }时</option>
									</c:forEach>
								</select>
						
						
								<select id="txtWeekMinute">
									<c:forEach begin="0" end="55" step="5" var="tmp">
										<option value="${tmp }">${tmp }分</option>
									</c:forEach>
									<option value="59">59分</option>
								</select>
	               		 
							</td>
						</tr>
						<tr>
							<th width="20%"><input type="radio" value="5" name="rdoTimeType" />每月</th>
							<td>
								<c:forEach begin="1" end="31" var="mon">
									 <input type="checkbox" name="chkMon" value="${mon}"/>${mon}
								</c:forEach>
								     
		               			 <input type="checkbox" name="chkMon" value="L"/>最后一天<br/>
	               		 
			               		 <select id="txtMonHour">
			               		 	<c:forEach begin="0" end="23" step="1" var="tmp">
										<option value="${tmp }">${tmp }时</option>
									</c:forEach>
								</select>
								<select id="txtMonMinute">
									<c:forEach begin="0" end="55" step="5" var="tmp">
										<option value="${tmp }">${tmp }分</option>
									</c:forEach>
									<option value="59">59分</option>
								</select>
							</td>
						</tr>
						<tr>
						<th width="20%"><input type="radio" value="6" name="rdoTimeType" />Cron表达式:</th>
						<td><input type="text" id="txtCronExpression" name="txtCronExpression" /></td>
						</tr>
					</table>
					
				</form>
		</div>
</div>
	</body>
</html>