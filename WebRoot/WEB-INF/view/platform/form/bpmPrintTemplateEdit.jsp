<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<title>编辑表单模板</title>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormDef.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor2/editor_config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor2/editor_api.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/ueditor2/themes/default/css/ueditor.css"/>
	<script type="text/javascript">
		var locale='zh_cn';
		var tableId='${bpmPrintTemplate.tableId}';
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			$("a.save").click(save);
			FormDef.getEditor({
				height:200,
				width:10,
				lang:locale
			});
			//ueditor渲染textarea
			editor.render("html");
			editor.tableId = tableId;
		});
		function save() {
			
			if (FormDef.isSourceMode == 1) {
				$.ligerDialog.warn('不能在源代码模式下保存表单','提示');
				return;
			}
			var rtn = $("#bpmPrintTemplateForm").valid();
			if (!rtn)
				return;
			var data = {};
			var arr = $('#bpmPrintTemplateForm').serializeArray();
			$.each(arr, function(i, d) {
				data[d.name] = d.value;
			});
			var formData = editor.getContent();
			data['html'] = formData;
			var url="${ctx}/platform/form/bpmPrintTemplate/save.ht";
			$.post(url, {data : JSON2.stringify(data)}, showResponse);
		}
		
		function showResponse(data) {
			
			var obj = new com.hotent.form.ResultMessage(data);
			if (obj.isSuccess()) {// 成功
				$.ligerDialog.success('保存成功!','提示', function() {
					window.onbeforeunload = null;
					if(window.opener){
						var url='${ctx}/platform/form/bpmPrintTemplate/list.ht?formKey=${bpmPrintTemplate.formKey}';
						window.opener.location.href=url.getNewUrl();
					}
					window.close();
				});
			} else {// 失败
				$.ligerDialog.error(obj.getMessage(),'提示');
			}
		};		
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${bpmPrintTemplate.id !=null}">
			        <span class="tbar-label">编辑打印模板</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label">添加打印模板</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link close" href="javascript:;" onclick="window.close()"><span></span>关闭</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="bpmPrintTemplateForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main" >
				<tr>
					<th width="20%">模板名: </th>
					<td><input type="text" id="temapalteName" name="temapalteName" value="${bpmPrintTemplate.temapalteName}"   class="inputText"   /></td>
				</tr>
				<tr>
					<td colspan="2">
						<div id="editor" position="center"  style="overflow:auto;height:100%;">
							<textarea id="html" name="html">${fn:escapeXml(bpmPrintTemplate.html)}</textarea>
						</div>
					</td>
				</tr>
			</table>
			<input type="hidden" name="isDefault" value="${bpmPrintTemplate.isDefault}">
			<input type="hidden" name="formKey" value="${bpmPrintTemplate.formKey}">
			<input type="hidden" name="tableId" value="${bpmPrintTemplate.tableId}">
			<input type="hidden" name="id" value="${bpmPrintTemplate.id}" />					
		</form>
		
	</div>
</div>
</body>
</html>
