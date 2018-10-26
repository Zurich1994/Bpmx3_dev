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
<script type="text/javascript">
	var _element;
	$(".button-td").bind("mouseenter mouseleave",function(){
		$(this).toggleClass("button-td-hover");
	});
	$(function() {
		_element = null;
		_element = editor.curInput;
		if (_element) {
			bindData();
		}			
	});
	
	function openIconDialog() {
		var url= __ctx+"/js/ueditor2/dialogs/extend/input/icons.jsp";			
		var dialogWidth=500;
		var dialogHeight=400;
		conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1});

		var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
			+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;			
		/* var rtn=window.showModalDialog(url,"",winArgs);
		if(rtn!=undefined){				
			$("#preview-button").attr("class",rtn.cla);
		} */
		
		/*KILLDIALOG*/
		DialogUtil.open({
			height:conf.dialogHeight,
			width: conf.dialogWidth,
			title : '',
			url: url, 
			isResize: true,
			//自定义参数
			sucCall:function(rtn){
				$("#preview-button").attr("class",rtn.cla);
			}
		});
		
	};

	function bindData() {
		var child = _element.childNodes[0];
		if (child) {
			var cla = $(child).attr("class"), label = $(child).text();
			$("#preview-button").attr("class", cla);
			$("#a-label").val(label);
		}
	};

	dialog.onok = function() {
		var html = '<span name="editable-input" style="display:inline-block;">';
		var label = $("#a-label").val(),
		name = $("#a-label").val(),
		cla = $("#preview-button").attr("class");
		html += '<a href="javascript:;" name="m:b:';
		html += name;
		html += '" ';
		if(cla)html+='class="'+cla+'"';
		html +='>' + label + '</a>';
		html +='</span>';
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
			<legend><var id="lang_button_prop"></var></legend>
			<table>
				<tr>
					<th><var id="lang_button_label"></var>:</th>
					<td><input id="a-label" type="text" /></td>
				</tr>
				<tr>
					<th><var id="lang_button_img"></var>:</th>
					<td>
						<a href="javascript:;" id="preview-button"></a>				
						<div class="button-td" onclick="openIconDialog()">
						   	<var id="lang_choose_img"></var>
						</div>
					</td>
				</tr>				
			</table>
		</fieldset>
	</div>
	
</body>
</html>