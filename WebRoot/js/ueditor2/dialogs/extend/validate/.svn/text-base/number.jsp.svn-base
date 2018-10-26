<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@include file="/commons/include/form.jsp" %>
	<link rel="stylesheet" type="text/css" href="../input.css">
	<script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
	<script type="text/javascript">
		var curElement = null,
			validate = '';
		$(function(){
			
			//JSON 没有兼容IE时 就用到json2.js
			if(typeof JSON == 'undefined'){
                $('head').append($("<script type='text/javascript' src='${ctx}/js/util/json2.js'>"));
            }
	
			curElement = editor.selection.getRange().getClosedNode();
			validate = curElement.getAttribute("validate");		

			if(!validate){
				validate = '{}';
			}

			validate = eval("("+validate+")");

			if(isNum(validate.maxvalue)){
				$("#maxvalue").val(validate.maxvalue);
			}
			if(isNum(validate.minvalue)){
				$("#minvalue").val(validate.minvalue);
			}
			if(validate.range){
				$("#minvalue").val(validate.range[0]);
				$("#maxvalue").val(validate.range[1]);
			}
		});
	
		dialog.onok = function() {
			if(!curElement||!validate)return;
			
			var maxval = $("#maxvalue").val(),
				minval = $("#minvalue").val();

			delete validate['maxvalue'];
			delete validate['minvalue'];
			delete validate['range'];

			if(isNum(maxval)&&isNum(minval)){
				validate['range'] = [parseFloat(minval),parseFloat(maxval)];
			}
			else if(isNum(minval)){
				validate['minvalue'] = parseFloat(minval);
			}
			else if(isNum(maxval)){
				validate['maxvalue'] = parseFloat(maxval);
			}
			var str = '';
			if(typeof validate != 'undefined' && validate!=""){
				var str = JSON2.stringify(validate).replace(/\"/g,"'");    //JS 把所有的双引号 替换成单引号
			}
			$(curElement).attr("validate",str);
		}; 

		function isNum(d){
			if(d===null||d===undefined||d==='')return false;
			return !isNaN(d);
		};
	</script>
</head>
<body >
	<div id="inputPanel">
		<fieldset class="base">
			<legend><var id="lang_num_range"></var></legend>
			<table>
				<tr>
					<th><var id="lang_less_equal"></var>:</th>
					<td>
						<input id="maxvalue" type="text" />
					</td>
				</tr>
				<tr>
					<th><var id="lang_more_equal"></var>:</th>
					<td>
						<input id="minvalue" type="text" />
					</td>
				</tr>
			</table>
		</fieldset>
	</div>
</body>
</html>