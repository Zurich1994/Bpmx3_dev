
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp" %>
<title>选择模板</title>
<style type="text/css">
	html,body{height:100%;width:100%; overflow: auto;overflow-x:hidden;}
</style>
<script type="text/javascript">
/*KILLDIALOG*/
var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)

var templatesId = "${templatesId}";
$(function(){
	$("#dataFormSave").click(function(){
		openEdit();
	});	
	if(!$.isEmpty(templatesId)){
		$("select[templateId='templateId']").each(function() {
			var tableId= $(this).attr("tableid");
			var templateAlias = parseTemplateAlias(templatesId,tableId);
			$(this).val(templateAlias);
		});
	}
});

//返回解析模板的别名
var parseTemplateAlias = function(templatesId,tableId){
	if($.isEmpty(templatesId)) return ;
	var t = templatesId.split(";");
	for(var i=0,c;c=t[i++];){
		var s =c.split(",");
		if(s[0] == tableId){
			return s[1];
		}
	}
};

function openEdit(){
	var aliasObj=$("#templateAlias");
	var tableObj=$("#templateTableId");
	
	var aryAlias=new Array();
	var aryTableId=new Array();
	$("select[templateId='templateId']").each(function(i){
		aryTableId.push($(this).attr("tableid"));
		aryAlias.push($(this).val());
	});
	
	tableObj.val(aryTableId.join(","));
	aliasObj.val(aryAlias.join(","));
	
	var params=$("#frmDefInfo").serialize();
	var url='edit.ht?' + params;
	alert("进入tablettt");
	jQuery.openFullWindow(url);
	
	//parent.closeWin();
	//dialog.close();//如果在这里关闭了，打开页面又怎么调用window.opener呢？！！！
};
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">选择表单模版</span>
			</div>
			<c:if test="${isSimple==0}">
				<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link run" id="dataFormSave" href="javascript:;"><span></span>下一步</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" onclick="history.back()"><span></span>返回</a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
			<form id="frmDefInfo">
				
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<c:if test="${mainTable != null}">
						<tr>
							<th width="30%">主表模板:</th>
							<td>
								<select id="main" templateId="templateId" tableId="${mainTable.tableId }">
									<c:forEach items="${mainTableTemplates}" var="bpmFormTemplate">
										<option value="${bpmFormTemplate.alias}">${bpmFormTemplate.templateName}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						</c:if>
						<c:forEach items="${subTables}" var="subTable">
						<tr>
							<th width="30%">${subTable.tableDesc }模板:</th>
							<td>
								<select templateId="templateId" tableId="${subTable.tableId }">
									<c:forEach items="${subTableTemplates}" var="bpmFormTemplate">
										<option value="${bpmFormTemplate.alias}">${bpmFormTemplate.templateName}</option>
									</c:forEach>
								</select>
							</td>
						</tr>					
						</c:forEach>					
					</table>
				</div>
				<input type="hidden" name="templateAlias" id="templateAlias" />
				<input type="hidden" name="templateTableId" id="templateTableId" />
				<input type="hidden" name="categoryId" value="${categoryId}"/>
				<input type="hidden" name="tableId" value="${tableId}"/>
				<input type="hidden" name="subject" value="${subject}"/>
				<input type="hidden" name="formDesc" value="${formDesc}"/>
				<input type="hidden" name="formKey" value="${formKey}"/>
			</form>
				
	</div> <!-- end of panel -->
</body>
</html>


