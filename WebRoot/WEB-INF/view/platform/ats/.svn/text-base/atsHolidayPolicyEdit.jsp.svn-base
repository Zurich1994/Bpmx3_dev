<%--
	time:2015-05-19 17:42:20
	desc:edit the 假期制度
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>编辑 假期制度</title>
<%@include file="/commons/include/form.jsp"%>
<f:link href="listEdit.css"></f:link>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
		<script type="text/javascript" src="${ctx}/js/hotent/platform/ats/AtsDialog.js"></script>
<script type="text/javascript"
	src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<style>
.tools {
	display: none;
}

table:hover .tools {
	display: block;
}
</style>
<script type="text/javascript">
	var tempSub = ""; 
	var atsHolidayTypeMap = ${atsHolidayTypeMap};
	$(function() {
		$("a.save").click(function() {
			$("#atsHolidayPolicyForm").attr("action", "save.ht");
			$("[type='checkbox']").each(function() {
				if ($(this).is(":checked")) {
					$(this).val(1);
				} else {
					$(this).val(0);
				}
			})
			submitForm();
		});
		setChecked();
		$("#holidayDetail").delegate("[type='checkbox']", 'click', function() {
			if ($(this).is(":checked")) {
				$(this).val(1);
			} else {
				$(this).val(0);
			}
		})
		tempSub = $("#holidayDetail").children().first().clone();
		$('input,textarea',tempSub).val('').removeAttr('checked').removeAttr('selected')
		$(".holidayType").each(function(){
			var me = $(this),id = me.val();
			if(id){
				me.next().val(atsHolidayTypeMap[id]);
			}
		})
	});
	function setChecked() {
		$("[type='checkbox']").each(function() {
			if ($(this).val() == 1) {
				this.checked = true;
			} else {
				this.checked = false;
			}
		})
	}
	//提交表单
	function submitForm() {
		var options = {};
		if (showResponse) {
			options.success = showResponse;
		}
		var frm = $('#atsHolidayPolicyForm').form();
		var sub = $('#holidayDetail').form();
		frm.ajaxForm(options);
		if (frm.valid()&&sub.valid()) {
			frm.submit();
		}else{
			$.ligerDialog.error("表单验证失败", "提示信息");
		}
	}

	function showResponse(responseText) {
		var obj = new com.hotent.form.ResultMessage(responseText);
		if (obj.isSuccess()) {
			$.ligerDialog.confirm(
							obj.getMessage() + ",是否继续操作",
							"提示信息",
							function(rtn) {
								if (rtn) {
									window.location.href = window.location.href;
								} else {
									window.location.href = "${ctx}/platform/ats/atsHolidayPolicy/list.ht";
								}
							});
		} else {
			$.ligerDialog.error(obj.getMessage(), "提示信息");
		}
	}
	function selectOrg() {
		OrgDialog({
			isSingle : true,
			callback : function(orgId, orgName) {
				$('#orgId').val(orgId);
				$('#orgName').val(orgName);
			}
		});
	}
	function addRow() {
		var el = $("#holidayDetail"), pid = parseInt(el.children().last().attr("pid")) + 1, tableHTML = tempSub[0].outerHTML;
		tableHTML = $(tableHTML.replaceAll("\\\[0\\\]", "\[" + pid + "\]"));
		el.append(tableHTML.attr("pid", pid));
	}
	function delRow(el) {
		$(el).closest("table").remove();
	}
	function selectAtsHolidayType(el){
		AtsHolidayTypeDialog({callback:function(rtn){
			$(el).prev().val(rtn.name);
			$(el).prev().prev().val(rtn.id);
		}});
	}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<c:choose>
					<c:when test="${atsHolidayPolicy.id !=null}">
						<span class="tbar-label"><span></span>编辑假期制度</span>
					</c:when>
					<c:otherwise>
						<span class="tbar-label"><span></span>添加假期制度</span>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" id="dataFormSave" href="javaScript:void(0)"><span></span>保存</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="atsHolidayPolicyForm" method="post" action="save.ht">
				<table class="table-detail" cellpadding="0" cellspacing="0"
					border="0" type="main">
					<tr>
						<th width="20%">编码:</th>
						<td><input type="text" id="code" name="code"
							value="${atsHolidayPolicy.code}" class="inputText"
							validate="{required:true,maxlength:384}" /></td>
						<th width="20%">名称:</th>
						<td><input type="text" id="name" name="name"
							value="${atsHolidayPolicy.name}" class="inputText"
							validate="{required:true,maxlength:384}" /></td>

					</tr>
					<tr>
						<th width="20%">所属组织:</th>
						<td colspan="3"><input type="hidden" id="orgId" name="orgId"
							value="${atsHolidayPolicy.orgId}" /> <input type="text"
							id="orgName" value="${atsHolidayPolicy.orgName}" validate="{required:true}" readonly="readonly" /> <a href="javascript:;"
							onclick="selectOrg()" class="button"><span>选 择...</span></a></td>
					</tr>
					<tr>
						<th width="20%">是否默认:</th>
						<td><input type="checkbox" id="isDefault" name="isDefault"
							value="1"
							<c:if test="${atsHolidayPolicy.isDefault ==1}"> checked="checked"</c:if> />
						</td>
						<th width="20%">是否启动半天假:</th>
						<td><input type="checkbox" id="isHalfDayOff"
							name="isHalfDayOff" value="1"
							<c:if test="${atsHolidayPolicy.isHalfDayOff ==1}"> checked="checked"</c:if> />
						</td>
					</tr>
					<tr>
						<th width="20%">描述:</th>
						<td><textarea rows="3" cols="5" id="memo" name="memo"
								class="inputText">${atsHolidayPolicy.memo}</textarea></td>
					</tr>
				</table>
				<input type="hidden" name="id" value="${atsHolidayPolicy.id}" />
				<div class="panel-detail">

					<div class="panel-toolbar">
						<div class="group">
							<a onclick="addRow()" class="link add"><span></span>添加</a>
						</div>
					</div>
				</div>
				<div id="holidayDetail" class="panel-body">
					<c:forEach items="${atsHolidayPolicy.atsHolidayPolicyDetailList}"
						var="atsHolidayPolicyDetail" varStatus="status">
						<table cellpadding="1" cellspacing="1" class="table-detail"
							pid="${status.index}">
							<tbody>
								<tr>
									<th width="20%">假期类型:</th>
									<td><input type="hidden"
										name="atsHolidayPolicyDetailList[${status.index}].holidayId"
										value="${atsHolidayPolicyDetail.holidayId}" class="inputText" />
										<input type="hidden" class="holidayType"
										name="atsHolidayPolicyDetailList[${status.index}].holidayType"
										value="${atsHolidayPolicyDetail.holidayType}"/>
										<input type="text"
										readonly="readonly"
										validate="{required:true}" />
										<a href="javascript:;" onclick="selectAtsHolidayType(this)" class="button"><span>选 择...</span></a>
										</td>
									<th width="20%">假期单位:</th>
									<td><select
										name="atsHolidayPolicyDetailList[${status.index}].holidayUnit">
											<option value="1"
												<c:if test="${atsHolidayPolicyDetail.holidayUnit==1}"> selected="selected" </c:if>>天</option>
											<option value="2"
												<c:if test="${atsHolidayPolicyDetail.holidayUnit==2}"> selected="selected" </c:if>>小时</option>
									</select></td>
									<th width="20%"></th>
									<td></td>
								</tr>
								<tr>
									<th width="20%">启动周期:</th>
									<td><input type="checkbox"
										name="atsHolidayPolicyDetailList[${status.index}].enablePeriod"
										value="${atsHolidayPolicyDetail.enablePeriod}" /></td>
									<th width="20%">周期长度:</th>
									<td><input type="text" id="periodLength"
										name="atsHolidayPolicyDetailList[${status.index}].periodLength"
										value="${atsHolidayPolicyDetail.periodLength}"
										class="inputText" /></td>
									<th width="20%">周期单位:</th>
									<td><select
										name="atsHolidayPolicyDetailList[${status.index}].periodUnit">
											<option value="1"
												<c:if test="${atsHolidayPolicyDetail.periodUnit==1}"> selected="selected" </c:if>>年</option>
											<option value="2"
												<c:if test="${atsHolidayPolicyDetail.periodUnit==2}"> selected="selected" </c:if>>月</option>
									</select></td>
								</tr>
								<tr>
									<th width="20%">控制单位额度:</th>
									<td><input type="checkbox"
										name="atsHolidayPolicyDetailList[${status.index}].enableMinAmt"
										value="${atsHolidayPolicyDetail.enableMinAmt}" /></td>
									<th width="20%">单位额度:</th>
									<td><input type="text"
										name="atsHolidayPolicyDetailList[${status.index}].minAmt"
										value="${atsHolidayPolicyDetail.minAmt}" class="inputText" /></td>
									<th width="20%"></th>
									<td></td>
								</tr>
								<tr>
									<th width="20%">是否允许补请假:</th>
									<td><input type="checkbox"
										name="atsHolidayPolicyDetailList[${status.index}].isFillHoliday"
										value="${atsHolidayPolicyDetail.isFillHoliday}" /></td>
									<th width="20%">补请假期限:</th>
									<td><input type="text"
										name="atsHolidayPolicyDetailList[${status.index}].fillHoliday"
										value="${atsHolidayPolicyDetail.fillHoliday}"
										class="inputText" /></td>
									<th width="20%">补请假期限单位:</th>
									<td><select
										name="atsHolidayPolicyDetailList[${status.index}].fillHolidayUnit">
											<option value="1"
												<c:if test="${atsHolidayPolicyDetail.fillHolidayUnit==1}"> selected="selected" </c:if>>年</option>
											<option value="2"
												<c:if test="${atsHolidayPolicyDetail.fillHolidayUnit==2}"> selected="selected" </c:if>>月</option>
											<option value="3"
												<c:if test="${atsHolidayPolicyDetail.fillHolidayUnit==3}"> selected="selected" </c:if>>天</option>
									</select></td>

								</tr>
								<tr>
									<th width="20%">是否允许销假:</th>
									<td><input type="checkbox"
										name="atsHolidayPolicyDetailList[${status.index}].isCancelLeave"
										value="${atsHolidayPolicyDetail.isCancelLeave}" /></td>
									<th width="20%">销假期限:</th>
									<td><input type="text"
										name="atsHolidayPolicyDetailList[${status.index}].cancelLeave"
										value="${atsHolidayPolicyDetail.cancelLeave}"
										class="inputText" /></td>
									<th width="20%">销假期限单位:</th>
									<td><select
										name="atsHolidayPolicyDetailList[${status.index}].cancelLeaveUnit">
											<option value="1"
												<c:if test="${atsHolidayPolicyDetail.cancelLeaveUnit==1}"> selected="selected" </c:if>>年</option>
											<option value="2"
												<c:if test="${atsHolidayPolicyDetail.cancelLeaveUnit==2}"> selected="selected" </c:if>>月</option>
											<option value="3"
												<c:if test="${atsHolidayPolicyDetail.cancelLeaveUnit==3}"> selected="selected" </c:if>>天</option>
									</select></td>
								</tr>
								<tr>
									<th width="20%">是否控制假期额度:</th>
									<td><input type="checkbox"
										name="atsHolidayPolicyDetailList[${status.index}].isCtrlLimit"
										value="${atsHolidayPolicyDetail.isCtrlLimit}" /></td>
									<th width="20%">假期额度规则:</th>
									<td><input type="text"
										name="atsHolidayPolicyDetailList[${status.index}].holidayRule"
										value="${atsHolidayPolicyDetail.holidayRule}"
										class="inputText"
										validate="{required:false,number:true,maxIntLen:19}" /></td>
									<th width="20%"></th>
									<td></td>
								</tr>
								<tr>
									<th width="20%">是否允许超额请假:</th>
									<td><input type="checkbox"
										name="atsHolidayPolicyDetailList[${status.index}].isOver"
										value="${atsHolidayPolicyDetail.isOver}" /></td>
									<th width="20%">超出额度下期扣减:</th>
									<td><input type="checkbox"
										name="atsHolidayPolicyDetailList[${status.index}].isOverAutoSub"
										value="${atsHolidayPolicyDetail.isOverAutoSub}" /></td>
									<th width="20%">是否允许修改额度:</th>
									<td><input type="checkbox"
										name="atsHolidayPolicyDetailList[${status.index}].isCanModifyLimit"
										value="${atsHolidayPolicyDetail.isCanModifyLimit}" /></td>
								</tr>
								<tr>
									<th width="20%">包括公休日:</th>
									<td><input type="checkbox"
										name="atsHolidayPolicyDetailList[${status.index}].isIncludeRest"
										value="${atsHolidayPolicyDetail.isIncludeRest}" /></td>
									<th width="20%">包括法定假日:</th>
									<td><input type="checkbox"
										name="atsHolidayPolicyDetailList[${status.index}].isIncludeLegal"
										value="${atsHolidayPolicyDetail.isIncludeLegal}" /></td>
									<th width="20%"></th>
									<td></td>
								</tr>
								<tr>
									<th width="20%">描述:</th>
									<td><input type="text"
										name="atsHolidayPolicyDetailList[${status.index}].memo"
										value="${atsHolidayPolicyDetail.memo}" class="inputText"
										validate="{required:false,maxlength:500}" /></td>
									<th width="20%"></th>
									<td></td>
									<th width="20%"></th>
									<td class="tools">
										<div class="toolBar">
											<div class="group">
												<a onclick="delRow(this)" class="link del"><span></span>删除</a>
											</div>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</c:forEach>
				</div>
			</form>
		</div>
</body>
</html>
