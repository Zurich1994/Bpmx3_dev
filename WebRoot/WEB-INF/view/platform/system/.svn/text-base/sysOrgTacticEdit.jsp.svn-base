<%--
	time:2015-03-31 10:21:18
	desc:edit the 组织策略
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 组织策略</title>
	<%@include file="/commons/include/form.jsp" %>
	<link type="text/css" rel="stylesheet"  href="${ctx}/js/jquery/plugins/token-input-facebook.css"></link>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.tokeninput.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#sysOrgTacticForm").attr("action","save.ht");
				submitForm();
			});
			$('#orgTactic').trigger("change");
			
			$(".token-input").tokenInput('',{
				theme:"facebook",
				propertyToSearch:"name",
				queryParam:"name"
				 });
			addOrgId('${sysOrgTactic.orgSelectId}');
			
			$("#orgTactic").click(function() {
				$("#sysOrgTacticForm").attr("action","${ctx}/platform/system/sysOrg/updCompany.ht");
				submitForm();
			});
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#sysOrgTacticForm').form();
			frm.ajaxForm(options);
			if(frm.valid()){
				frm.submit();
			}
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.success(obj.getMessage(),"提示信息");
			} else {
				$.ligerDialog.err(obj.getMessage(),"提示信息");
			}
		}
		
		
		function addOrgId(json){
			if($.isEmpty(json)) return;
			if(!$.isArray(json))
				json= eval('(' + json + ')');
	 		for(var i=0,c;c=json[i++];){
				$("#select-orgs").tokenInput("add",c);
			}
		}
		function cleanOrgId(){
			$("#select-orgs").tokenInput("clear");	
		}
		function getOrgId(){
			return $("#select-orgs").tokenInput("get");
		}
		
		function changeOrgTactic(obj){
			var me = $(obj),
				val= me.val(),
				orgTypeTr = $('#orgTypeTr'),
				orgSelectIdTr = $('#orgSelectIdTr');
			if(val == 0){
				orgTypeTr.hide();
				orgSelectIdTr.hide();
			}else if(val == 1){
				orgTypeTr.show();
				orgSelectIdTr.hide();
			}else if(val == 2){
				orgTypeTr.hide();
				orgSelectIdTr.show();
			}else if(val == 3){
				orgTypeTr.show();
				orgSelectIdTr.show();
			}
		}

		function selectOrg(){
			var json =getOrgId();
			OrgDialog({isSingle:false,arguments:json,callback:function(orgIds,orgIdNames,orgJson){
				addOrgId(orgJson);
				$('#orgSelectId').val(JSON2.stringify(orgJson));
			}});
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${sysOrgTactic.id !=null}">
			        <span class="tbar-label"><span></span>编辑组织策略</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加组织策略</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:void(0)"><span></span>保存</a></div>
				<div class="group"><a class="link run" id="orgTactic" href="javascript:void(0)"><span></span>生成分公司</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="sysOrgTacticForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">策略: </th>
					<td>
						<select id="orgTactic" name="orgTactic" onchange="changeOrgTactic(this)" class="inputText">
							<option value="0" <c:if test="${sysOrgTactic == null ||sysOrgTactic.orgTactic == '0'}">selected</c:if>>无策略</option>
							<option value="1" <c:if test="${sysOrgTactic.orgTactic == '1'}">selected</c:if>>按照级别</option>
							<option value="2" <c:if test="${sysOrgTactic.orgTactic == '2'}">selected</c:if>>手工选择</option>
							<option value="3" <c:if test="${sysOrgTactic.orgTactic == '3'}">selected</c:if>>按照级别+手工选择</option>
						</select>
					</td>
				</tr>
				<tr id="orgTypeTr">
					<th width="20%">组织级别: </th>
					<td>
						<select id="orgType" name="orgType">
								<option value="" >-请选择-</option>
							<c:forEach items="${orgTypelist}" var="orgType">
								<option value="${orgType.id}" <c:if test="${sysOrgTactic.orgType == orgType.id}">selected</c:if>>${orgType.name}</option>
							</c:forEach>
						</select>
					
				</tr>
				
					<tr id ="orgSelectIdTr" <c:if test="${sysOrgTactic.orgTactic == '1'}">style="display:none"</c:if>>
						<th width="20%">组织选择: </th>
						<td>
							<textarea class="token-input" id="select-orgs" readonly="readonly"></textarea>
							
							<a href='#' class='button' onclick="selectOrg()" style="margin-right:10px;" ><span class="icon ok"></span><span >选择</span></a>
							<a href="javascript:;" class="button"  onclick="cleanOrgId()"><span class="icon cancel" ></span><span class="cance" >清空</span></a>
						</td>
					</tr>
				
			</table>
			<textarea style="display: none;" id="orgSelectId" name="orgSelectId" >${fn:escapeXml(sysOrgTactic.orgSelectId)}</textarea>
			<input type="hidden" name="id" value="${sysOrgTactic.id}" />
			<input type="hidden" name="demId" value="${sysOrgTactic.demId}" />
		</form>
		
	</div>
</div>
</body>
</html>
