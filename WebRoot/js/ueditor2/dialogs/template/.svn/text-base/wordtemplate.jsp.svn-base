<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/commons/include/form.jsp" %>
<link rel="stylesheet" type="text/css" href="../input.css">
<script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js"></script>
<script type="text/javascript" src="${ctx}/js/util/json2.js"></script>
</head>
<body>
	<div id="inputPanel">
		<fieldset class="base">
			<legend>word套打设置</legend>
			<table>
				<tr>
					<th>按钮名称</th>
					<td><input type="text" name="btnName" id="btnName" value="打印"></td>
				</tr>
				<tr>
					<th>模板</th>
					<td>
					<select id="templateSelect">
					</select>
					</td>
				</tr>
			</table>
		</fieldset>
	</div>
	
	<textarea id="template" class="hidden">
		<#list data as obj>
		<option value="\${obj.alias}">\${obj.name}</option>
		</#list>
	</textarea>
	<span class="hidden" id="printBtnTemplate">
	<a class="link print" href="javascript:;" templateAlias=""><span></span>打印</a>
	</span>
	<script type="text/javascript">
	var _element;
	$(function() {
		_element = null;
		_element = editor.curInput;
		var url = __ctx + '/platform/system/sysWordTemplate/getAllTemplate.ht';
		$.post(url,function(data){
			if (!data) return;
			var templateHtml = $('#template').text();
			var optionHtml = easyTemplate(templateHtml, data).toString();
			$('#templateSelect').append(optionHtml);
			if (_element) {
				bindData();
			}
		});
	});		
	function bindData(){
		var templateAlias = $(_element).attr("templateAlias");
		if(!templateAlias) return ;
		$('#templateSelect').val(templateAlias);
		var btnName = $(_element).text();
		$('#btnName').val(btnName);
	}
	dialog.onok = function() {
		var templateAlias=$("#templateSelect").val();
		if(!templateAlias){
			$(editor.curInput).setAttr("templateAlias", "");
			return;
		}
		var btn = $('#printBtnTemplate>a');
		btn.attr('templateAlias', templateAlias);
		var btnName = $('#btnName').val();
		btn.text(btnName);
		var html = $('#printBtnTemplate').html().trim();
		if(_element){
			editor.curInput.outerHTML=html;
		} else{
			var child = utils.parseDomByString(html);
			var start = editor.selection.getStart();
			if(!start||!child)return;
			if(start.tagName=='TD'){
				start.appendChild(child);
			} else{
				start = domUtils.findEditableInput(start);
				utils.insertAfter(child, start);
			}
		}
	};
	</script>
</body>
</html>