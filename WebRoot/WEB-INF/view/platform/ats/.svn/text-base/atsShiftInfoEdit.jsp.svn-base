<%--
	time:2015-05-18 17:21:46
	desc:edit the 班次设置
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 班次设置</title>
	<%@include file="/commons/include/form.jsp" %>
<f:link href="listEdit.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/ats/AtsDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#atsShiftInfoForm").attr("action","save.ht");
				submitForm();
			});
			initDetailList();
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#atsShiftInfoForm').form();
			var sub=$('#trContainer').form();
			frm.ajaxForm(options);
			if(frm.valid()&&sub.valid()){
				$('#detailList').val(getDetailList());
				frm.submit();
			}
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.href = window.location.href;
					}else{
						window.location.href = "${ctx}/platform/ats/atsShiftInfo/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
		
		function getDetailList(){
			var json =  [];
			$("#trContainer tr[var='templTr']").each(function(){
				var me = $(this),obj={};
				obj.segment =$("select[var='segment']",me).val();
				obj.attendanceType =$("select[var='attendanceType']",me).val();
				obj.onTime =$("input[var='onTime']",me).val();
				obj.onPunchCard =$("select[var='onPunchCard']",me).val();
				obj.onFloatAdjust =$("input[var='onFloatAdjust']",me).val();
				obj.segmentRest =$("input[var='segmentRest']",me).val();
				obj.offTime =$("input[var='offTime']",me).val();
				obj.offPunchCard =$("select[var='offPunchCard']",me).val();
				obj.offFloatAdjust =$("input[var='offFloatAdjust']",me).val();
				json.push(obj);
			});
			return JSON2.stringify(json)
		}
		
		function initDetailList() {
			var detailList = $('#detailList').val();
			if ($.isEmpty(detailList))
				return;
			var detailLists = $.parseJSON(detailList),
				temp = $($("#templ .table-detail tr")[0]);
			for (var i = 0, c; c = detailLists[i++];) {
				var tr = temp.clone(true, true);
				$("select[var='segment']", tr).val(c.segment);
				$("select[var='attendanceType']", tr).val(c.attendanceType);
				$("input[var='onTime']", tr).val(c.onTime);
				$("select[var='onPunchCard']", tr).val(c.onPunchCard);
				$("input[var='onFloatAdjust']", tr).val(c.onFloatAdjust);
				$("input[var='segmentRest']", tr).val(c.segmentRest);
				$("input[var='offTime']", tr).val(c.offTime);
				$("select[var='offPunchCard']", tr).val(c.offPunchCard);
				$("input[var='offFloatAdjust']", tr).val(c.offFloatAdjust);
				$("#trContainer").append(tr);
			}
		}
		
		function addRow(i){
			var tr = $($("#templ .table-detail tr")[0]).clone(true, true);
			$('[var="segment"]' ,tr).val(i);
			$("#trContainer").append(tr);
		}
		function delAllRow(){
			$("#trContainer").empty();
		}
		
		function delTr(obj) {
			$(obj).closest("tr").remove();
		}
		
		function selectOrg(){
			OrgDialog({isSingle:true,callback:function(orgId,orgName){
				$('#orgId').val(orgId);
				$('#orgName').val(orgName);
			}});
		}
		
		function selectCardRule(){
			AtsCardRuleDialog({callback:function(rtn){
				$('#cardRule').val(rtn.id);
				$('#cardRuleName').val(rtn.name);
				delAllRow();
				if(rtn.segmentNum == '1'){
					addRow(1);
				}
				if(rtn.segmentNum == '2'){
					addRow(1);
					addRow(2);
				}
				if(rtn.segmentNum == '3'){
					addRow(1);
					addRow(2);
					addRow(3);
				}
			}});
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${atsShiftInfo.id !=null}">
			        <span class="tbar-label"><span></span>编辑班次设置</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加班次设置</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javaScript:void(0)"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="atsShiftInfoForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">编码: </th>
					<td><input type="text" id="code" name="code" value="${atsShiftInfo.code}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
					<th width="20%">名称: </th>
					<td><input type="text" id="name" name="name" value="${atsShiftInfo.name}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
			
				</tr>
				<tr>
					<th width="20%">班次类型: </th>
					<td>
						<select id="shiftType" name="shiftType">
							<option value="">--请选择--</option>
							<c:forEach items="${shiftTypeList}" var="shiftType">
								<option value="${shiftType.id}" <c:if test="${shiftType.id==atsShiftInfo.shiftType}">selected="selected" </c:if>>${shiftType.name}</option>
							</c:forEach>
						</select>
					</td>
					<th width="20%">加班补偿方式: </th>
					<td><input type="text" id="otCompens" name="otCompens" value="${atsShiftInfo.otCompens}"  class="inputText" validate="{number:true,maxIntLen:19}" readonly="readonly" /></td>
				
				</tr>
				<tr>
					<th width="20%">所属组织: </th>
					<td>
						<input type="hidden" id="orgId" name="orgId" value="${atsShiftInfo.orgId}" />
						<input type="text" id="orgName"  validate="{required:true}"  value="${atsShiftInfo.orgName}"   readonly="readonly"/>
						<a href="javascript:;" onclick="selectOrg()" class="button"><span>选 择...</span></a>
					</td>
					<th width="20%">取卡规则: </th>
					<td>
						<input type="hidden" id="cardRule" name="cardRule" value="${atsShiftInfo.cardRule}" />
						<input type="text" id="cardRuleName"  value="${atsShiftInfo.cardRuleName}"    readonly="readonly"/>
						<a href="javascript:;" onclick="selectCardRule()" class="button"><span>选 择...</span></a>
					</td>
				</tr>
				<tr>
					<th width="20%">标准工时: </th>
					<td><input type="text" id="standardHour" name="standardHour" value="${atsShiftInfo.standardHour}"  class="inputText" validate="{required:true}"  /></td>
					<th width="20%">默认班次: </th>
					<td>
						<input type="checkbox" id="isDefault" name="isDefault" value="1"   <c:if test="${atsShiftInfo.isDefault ==1}"> checked="checked"</c:if>  />
					</td>
				</tr>
				<tr>
					<th width="20%">描述: </th>
					<td colspan="3">
							<textarea rows="3" cols="5" id="memo" name="memo"  class="inputText">${atsShiftInfo.memo}</textarea>
					</td>
				</tr>
			</table>
			<input type="hidden" name="id" value="${atsShiftInfo.id}" />
			<input type="hidden" id="status" name="status" value="${atsShiftInfo.status}"  />
			<textarea style="display: none" id="detailList" name="detailList">${fn:escapeXml(atsShiftInfo.detailList)}</textarea>
		</form>
			<div class="panel-body" >
					<table cellpadding="1" cellspacing="1"  class="table-grid">
						<tr>
						  <th align="center">段次</th>
						  <th align="center">出勤类型</th>
						  <th align="center">上班时间</th>
						  <th align="center">上班是否打卡</th>
						  <th align="center">浮动调整值(分)</th>
						  <th align="center">段内休息(分)</th>
						    <th align="center">下班时间</th>
						  <th align="center">下班是否打卡</th>
						  <th align="center">浮动调整值(分)</th>
						</tr>
						<tbody id="trContainer">
					    </tbody>
					</table>
			</div>
		</div>
		<div  id="templ"  style="display: none;">
			<table cellpadding="1" cellspacing="1"  class="table-detail">
				<tbody>
				<tr var="templTr">
					<td><select var="segment" disabled="disabled">
							<option value="1">第一段</option>
							<option value="2">第二段</option>
							<option value="3">第三段</option>
						</select>
					</td>
					<td>
						<select var="attendanceType" >
							<option value="0">正常出勤</option>
							<option value="1">固定加班</option>
							<option value="2">正常出勤不计异常</option>
							<option value="3">固定加班不计异常</option>
						</select>
					</td>
					<td>
						<input type="text" var="onTime" class="wdateTime" datefmt="HH:mm" style="width: 80px;" validate="{required:true}">
					</td>
					<td>
						<select  var="onPunchCard">
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</td>
					<td>
						<input type="text" var="onFloatAdjust" style="width: 80px;">
					</td>
					<td>
						<input type="text" var="segmentRest" style="width: 80px;">
					</td>
					<td>
						<input type="text" var="offTime" class="wdateTime" datefmt="HH:mm" style="width: 80px;" validate="{required:true}">
					</td>
					<td>
						<select  var="offPunchCard">
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</td>
					<td>
						<input type="text" var="offFloatAdjust" style="width: 80px;">
					</td>
				</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>
