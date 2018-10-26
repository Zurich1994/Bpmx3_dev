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
		<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.fix.clone.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/ats/AtsDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#atsShiftRuleForm").attr("action","save.ht");
				submitForm();
			});
			
			initDetailList();
		});
		function changeHide(el){
			if(el){
				var me = $(el);
				if(me.val()!=1)
					me.closest("[var='templTr']").find(".needHide").hide();
				else
					me.closest("[var='templTr']").find(".needHide").show();
			}else{
				$("[var='templTr']").each(function(){
					var dateType = $(this).find("[var='dateType']").val();
					if(dateType!=1)
						$(this).find(".needHide").hide();
				})
			}
			
		}
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#atsShiftRuleForm').form();
			var sub=$('#trContainer').form();
			frm.ajaxForm(options);
			if(frm.valid()&&sub.valid()){//数据是否有效
				
				$('#detailList').val(getDetailList());
				frm.submit();
			}
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.href = "${ctx}/platform/ats/atsShiftRule/list.ht";
						//window.location.href = window.location.href;
					}else{
						window.location.href = "${ctx}/platform/ats/atsShiftRule/list.ht";
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
				obj.dateType =$("[var='dateType']",me).val();
				obj.shiftId =$("[var='shiftId']",me).val();
				json.push(obj);
			});
			return JSON2.stringify(json)
		}
		
		function initDetailList() {
			var detailList = $('#detailList').val();
			if ($.isEmpty(detailList))
				return;
			var tr = $($("#templ .table-detail tr")[0]).clone(true, true);
			var detailLists = $.parseJSON(detailList);
			for (var i = 0, c; c = detailLists[i++];) {
				$("select[var='dateType']", tr).val(c.dateType);
				$("input[var='shiftId']", tr).val(c.shiftId);
				$("input[var='shiftCode']", tr).val(c.shiftCode);
				$("input[var='shiftName']", tr).val(c.shiftName);
				$("input[var='shiftTime']", tr).val(c.shiftTime);
				var tr1 = tr.clone(true, true);
				$("#trContainer").append(tr1);
			}
		}
		
		function addRow(){
			var tr = $($("#templ .table-detail tr")[0]).clone(true, true);
		//	$("[var='order']",tr).html(1);
			$("#trContainer").append(tr);
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
		
		function selectShiftInfo(obj){
			AtsShiftInfoDialog({
				isSingle:true,
				callback:function(rtn){
				var tr =  $(obj).closest("tr");
				$("input[var='shiftId']", tr).val(rtn.id);
				$("input[var='shiftCode']", tr).val(rtn.code);
				$("input[var='shiftName']", tr).val(rtn.name);
				$("input[var='shiftTime']", tr).val(rtn.shiftTime);
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
				<div class="group"><a class="link save" id="dataFormSave" href="javaScript:void(0)"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="atsShiftRuleForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">编码: </th>
					<td><input type="text" id="code" name="code" value="${atsShiftRule.code}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>
					<th width="20%">名称: </th>
					<td><input type="text" id="name" name="name" value="${atsShiftRule.name}"  class="inputText" validate="{required:true,maxlength:384}"  /></td>				
				</tr>
				<tr>
					<th width="20%">所属组织: </th>
					<td>
						<input type="hidden" id="orgId" name="orgId" value="${atsShiftRule.orgId}" />
						<input type="text" id="orgName"  value="${atsShiftRule.orgName}"   readonly="readonly" validate="{required:true}" />
						<a href="javascript:;" onclick="selectOrg()" class="button"><span>选 择...</span></a>
					</td>
					<th width="20%">描述: </th>
					<td><input type="text" id="memo" name="memo" value="${atsShiftRule.memo}"  class="inputText"  /></td>
				</tr>
			</table>
			<input type="hidden" name="id" value="${atsShiftRule.id}" />
			<input type="hidden" id="status" name="status" value="${atsShiftRule.status}"  class="inputText" />
			<textarea style="display: none" id="detailList" name="detailList">${fn:escapeXml(atsShiftRule.detailList)}</textarea>	
		</form>
		<div class="panel-detail">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a onclick="addRow()" class="link add"><span></span>添加</a></div>
					<div class="group"><a onclick="delSubRow()" class="link del"><span></span>删除</a></div>
				</div>	
			</div>
		</div>
			<div class="panel-body">
					<table cellpadding="1" cellspacing="1"  class="table-grid">
						<tr>
						  <th align="center" width="50px">选择</th>
						  <th align="center">日期类型</th>
						  <th align="center">班次编码</th>
						  <th align="center">班次名称</th>
						  <th align="center">上下班时间</th>
						  <th align="center">删除</th>
						</tr>
						<tbody id="trContainer">
					    </tbody>
					</table>
			</div>
		
		<div  id="templ"  style="display: none;">
			<table cellpadding="1" cellspacing="1"  class="table-detail">
				<tbody>
				<tr var="templTr" onclick="checkTr(this)">
					<td ><input class="pk" type="checkbox" name="select"/><span var="order"></span>
						<input type="hidden" var="shiftId"/>
					<td>
						<select var="dateType" validate="{required:true}">
							<option value="1">工作日</option>
							<option value="2">休息日</option>
							<option value="3">法定假日</option>
						</select>
					</td>
					<td>
						<input class="inputText " var="shiftCode" readonly="readonly" >
					</td>
					<td>
						<input  var="shiftName" readonly="readonly"  >
						<a href="javascript:void(0);" onclick="selectShiftInfo(this)" class="button "><span>选 择...</span></a>
					</td>
					<td>
						<input class="inputText " var="shiftTime" readonly="readonly">
					</td>
					<td>
						<a class="link del " href="javascript:;"  title="删除" onclick="delTr(this)"></a>
					</td>
				</tr>
				</tbody>
			</table>
		</div>
		</div>
	</div>
</div>
</body>
</html>
