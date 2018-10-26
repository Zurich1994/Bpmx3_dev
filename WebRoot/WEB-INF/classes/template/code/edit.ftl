<#assign class=table.variable.class>
<#assign classVar=table.variable.classVar>
<#assign package=table.variable.package>
<#assign fieldList=table.fieldList>
<#assign subtables=table.subTableList>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 ${table.tableDesc}</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript" src="<#noparse>${ctx}</#noparse>/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="<#noparse>${ctx}</#noparse>/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="<#noparse>${ctx}</#noparse>/js/hotent/subform.js"></script>
	<#if flowKey?exists>
	<script type="text/javascript" src="<#noparse>${ctx}</#noparse>/js/hotent/platform/bpm/FlowDetailWindow.js"></script>
	</#if>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=<#noparse>$('#</#noparse>${classVar}Form').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				$("#saveData").val(1);
				if(frm.valid()){
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					if(OfficePlugin.officeObjs.length>0){
						OfficePlugin.submit(function(){
							frm.handleFieldName();
							<#if subtables?size!=0>
							frm.sortList();
							</#if>
							<#noparse>$('#</#noparse>${classVar}Form').submit();
						});
					}else{
						frm.handleFieldName();
						<#if subtables?size!=0>
						frm.sortList();
						</#if>
						<#noparse>$('#</#noparse>${classVar}Form').submit();
					}
				}
			});
			<#if flowKey?exists>
			$("a.run").click(function() {
				frm.ajaxForm(options);
				$("#saveData").val(0);
				<#noparse>$('#</#noparse>${classVar}Form').attr("action","run.ht");
				if(frm.valid()){
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					frm.handleFieldName();
					OfficePlugin.submit();
					<#if subtables?size!=0>
					frm.sortList();
					</#if>
					<#noparse>$('#</#noparse>${classVar}Form').submit();
				}
			});
			</#if>
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.href = window.location.href;
					}else{
					   //window.location.href = window.location.href;
						window.location.href = "<#noparse>${ctx}</#noparse>/${system}/${package}/${classVar}/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
	</script>
</head>
<body>
<div class="panel" style="height:100%;overflow:auto;">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="<#noparse>${not empty </#noparse>${classVar}Item.id}">
			        <span class="tbar-label"><span></span>编辑${table.tableDesc}</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加${table.tableDesc}</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<#if flowKey?exists>
				<c:if test="<#noparse>${</#noparse>runId==0}">
				<div class="group"><a class="link run"  href="javascript:;"><span></span>提交</a></div>
				<div class="l-bar-separator"></div>
				</c:if>
				<c:if test="<#noparse>${</#noparse>runId!=0}">
					<div class="group"><a class="link detail"  onclick="FlowDetailWindow({runId:<#noparse>${</#noparse>runId}})" href="javascript:;" ><span></span>流程明细</a></div>
					<div class="l-bar-separator"></div>
				</c:if>
				</#if>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<form id="${classVar}Form" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				${html}
			</div>
		</div>
		<input type="hidden" name="${table.pkField?lower_case}" value="<#noparse>${</#noparse>${classVar}.${table.pkField?lower_case}}"/>
		<input type="hidden" id="saveData" name="saveData"/>
		<input type="hidden" name="executeType"  value="start" />
	</form>
</body>
</html>
