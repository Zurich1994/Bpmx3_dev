<%--
	time:2015-05-21 09:06:10
	desc:edit the 轮班规则
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 轮班规则</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/ats/AtsDialog.js"></script>
	<script type="text/javascript">
		/*KILLDIALOG*/
		var  dialog= frameElement.dialog; //调用页面的dialog对象(ligerui对象)
		$(function() {
			var params = dialog.get("params"),
				startDate = params.startTime;
				endDate  =  params.endTime;
			$("a.save").click(function() {
				$.ajax({
					type : "POST",
					url : __ctx+"/platform/ats/atsTurnShift/calculate.ht",
					data : {
						ruleId:$('#ruleId').val(),
						startNum:$('#startNum').val(),
						startDate:startDate,
						endDate:endDate,
						startTime:$('#startTime').val(),
						endTime:$('#endTime').val(),
						holidayHandle:$('#holidayHandle').val(),
						attencePolicy:params.attencePolicy
					},
					success : function(data) {
						var rtn = $.parseJSON(data);
						dialog.get('sucCall')(rtn.data,rtn.beginCol);
						dialog.close();
					}
				});
			});

			$('#startTime').val(startDate);
			$('#endTime').val(endDate);
		});
	
		function selectShiftRule(obj){
			AtsShiftRuleDialog({callback:function(rtn){
			
				$.ajax({
					type : "POST",
					url : __ctx+"/platform/ats/atsShiftRule/detail.ht?id="+rtn.id,
					data : {},
					success : function(data) {
						$("#ruleId").val(rtn.id);
						$("#shiftRule").val(rtn.name);
						$('#detailList').val(data);
						var detailLists = $.parseJSON(data);
						var tr = $($("#templ .table-detail tr")[0]).clone(true, true);
						for (var i = 0, c; c = detailLists[i++];) {
							$("[var='sn']", tr).html(c.sn);
							var dateName =  (c.dateType ==1?'工作日':(c.dateType ==2?'休息日':'法定假日'));
							$("[var='dateType']", tr).html(dateName);
							$("[var='shiftCode']", tr).html(c.shiftCode);
							$("[var='shiftName']", tr).html(c.shiftName);
							$("[var='shiftTime']", tr).html(c.shiftTime);
							var tr1 = tr.clone(true, true);
							$("#trContainer").append(tr1);
						}
					}
				});
			}});
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${atsShiftRule.id !=null}">
			        <span class="tbar-label"><span></span>编辑轮班规则</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加轮班规则</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javaScript:void(0);"><span></span>确定</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link close" href="javaScript:dialog.close();"><span></span>关闭</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">轮班规则: </th>
					<td>
						<input type="hidden" id="ruleId" />
						<input type="text" id="shiftRule"  class="inputText"  readonly="readonly" />
						<a href="javascript:;" onclick="selectShiftRule()" class="button"><span>选 择...</span></a>
					</td>
					<th width="20%">轮班开始于: </th>
					<td><input type="text" id="startNum" value="1"  /></td>				
				</tr>
				<tr>
					<th width="20%">开始时间: </th>
					<td>
						<input type="text" id="startTime"    class="inputText date"  readonly="readonly"/>
					</td>
					<th width="20%">结束时间: </th>
					<td><input type="text" id="endTime"  class="inputText date"  readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th width="20%">节假日处理</th>
					<td>
						<select id="holidayHandle">
							<option value="1" >替换</option>
							<option value="2" >不替换</option>
							<option value="3" >顺延</option>
						</select>
					</td>
				</tr>
			</table>	
			<textarea style="display: none" id="detailList" name="detailList"></textarea>	
		<div class="panel-detail">
			<div class="panel-toolbar">
				 <span class="tbar-label"><span></span>排班信息</span>
			</div>
			<table cellpadding="1" cellspacing="1"  class="table-grid">
				<tr>
				  <th align="center" width="50px">序号</th>
				  <th align="center">日期类型</th>
				  <th align="center">班次编码</th>
				  <th align="center">班次名称</th>
				  <th align="center">上下班时间</th>
				</tr>
				<tbody id="trContainer">
			    </tbody>
			</table>
		</div>
		
		<div  id="templ"  style="display: none;">
			<table cellpadding="1" cellspacing="1"  class="table-detail">
				<tbody>
				<tr var="templTr">
					<td var="sn"></td>
					<td var="dateType" ></td>
					<td var="shiftCode"></td>
					<td var="shiftName"></td>
					<td var="shiftTime"></td>
				</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>
