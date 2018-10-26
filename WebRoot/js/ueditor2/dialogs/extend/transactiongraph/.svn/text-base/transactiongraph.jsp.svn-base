<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/commons/include/form.jsp" %>
<link rel="stylesheet" type="text/css" href="../input.css">
<link rel="stylesheet" type="text/css" href="${ctx}/styles/default/css/form.css">
<script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
<style type="text/css">
	body{
		overflow:hidden;
	}
	a.extend{
	  display:inline;
	}
</style>
<script type="text/javascript"><!--
	var _element;
	$(".button-td").bind("mouseenter mouseleave",function(){
		$(this).toggleClass("button-td-hover");
	});
	$(function() {
		_element = null;
		_element = editor.curTransactiongraph;
		if (_element) {
			bindData();
		}			
	});
	//zl在线流程设计跳转
       function design(formData, jqForm, options) { 
			//var operatortype=$("#operatortype").val();
				/*if(operatortype=="0"){
					$.ligerDialog.warn("请选择操作类型",'提示信息');
					return false;
				}*/
				var  child = _element.childNodes[0];
				var label = $(child).text();
				var nurl =__ctx + "/platform/bpm/bpmDefinition/designBtn.ht?&btnname="+label;
						window.open(nurl);
			};
	function openIconDialog() {
		
		
		
		/*KILLDIALOG*/
		DialogUtil.open({
			height:conf.dialogHeight,
			width: conf.dialogWidth,
			title : '',
			url: url, 
			isResize: true,
			//自定义参数
			sucCall:function(rtn){
				$("#preview-buttonNew").attr("class",rtn.cla);
			}
		});
		
	};

	function bindData() {

		
			var child1 = _element.childNodes[0];
		if (child1) {
			var cla = $(child1).attr("class"), label = $(child1).text();
			$("#preview-buttonNew").attr("class", cla);
			$("#a-labelNew").val(label);

		var child = _element.childNodes[0];
		if (child) {
			var cla = $(child).attr("class"), label = $(child).text();
			$("#preview-buttonNew").attr("class", cla);
			$("#a-labelNew").val(label);

		}
            
	};

	dialog.onok = function() {
		var html = '<span name="editable-input" style="display:inline-block;">';
		var label = $("#a-labelNew").val(),
		cla = $("#preview-buttonNew").attr("class");
		html += '<a href="javascript:;" ';
		if(cla)html+='class="'+cla+'"';
		html +='>' + label + '</a>';
		html += '</span> ';
		if(_element){
			editor.curInput.outerHTML=html;
		}
		else{
			var child = utils.parseDomByString(html);
			var start = editor.selection.getStart();
			if(!start||!child)return;
			if(start.tagName=='TD'){
				start.appendChild(child);
			}
			else{
				start = domUtils.findEditableInput(start);
				utils.insertAfter(child, start);
			}
		}
	};

</script>
</head>
<body>
	<div id="inputPanel">
		<fieldset class="base">
			<legend><var id="lang_button_propNew"></var></legend>
			<table>
				<tr>
					<th><var id="lang_button_labelNew"></var>:</th>
					<td><input id="a-labelNew" type="text" /></td>
				</tr>
				<tr>
					<th><var id="lang_button_imgNew"></var>:</th>
					<td>
						<a href="javascript:;" id="preview-buttonNew"></a>				
						<div class="button-td" onclick="design()">
						   	<var id="lang_draw_buttonNew"></var>
						</div>
					</td>
				</tr>				
			</table>
		</fieldset>
	</div>
	
</body>
</html>
