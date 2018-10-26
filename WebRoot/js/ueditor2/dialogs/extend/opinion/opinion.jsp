<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@include file="/commons/include/form.jsp" %>
	<link rel="stylesheet" type="text/css" href="../input.css">
	<script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>
	<style type="text/css">
		body{
			overflow:hidden;
		}
	</style>
	<script type="text/javascript">
		var _element;
		$(".button-td").bind("mouseenter mouseleave",function(){
			$(this).toggleClass("button-td-hover");
		});
		$(function() {
			_element = null;
			_element = editor.curOpinion;
			if (_element) {
				bindData();
			}			
		}); 

		function bindData() {
			var child = _element.childNodes[0];
			if (child) {
				var name = $(child).attr("name"), opinionName = $(child).attr("opinionname");
				$("#input-name").val(name.replace("opinion:",""));
				$("#input-opinion-name").val(opinionName);
				$("#input-width").val($(child).attr('ewidth')); 
				$("#input-height").val( $(child).attr('eheight')); 
				$("#widthUnit").val($(child).attr('widthUnit'));
				$("#heightUnit").val($(child).attr('heightUnit'));
			}
		};
		
		function validata(n,o){
			//匹配不含空格的字符串
			var reg=/^\S+$/gi;
			var match=reg.exec(n);
			if(match){
				return true;
			}
			return false;
		};

		dialog.onok = function() {
			var name=$("#input-name").val(),opinionName=$("#input-opinion-name").val();
			if(!/^[a-zA-Z]\w*$/.test(name.trim())) return false;
			var objwidth="150px",objheight="55px";
			if($('#input-width').val().length>0 ){
				objwidth=$('#input-width').val()+$('#widthUnit').val();
			}
			if($('#input-height').val().length>0 ){
				objheight=$('#input-height').val()+$('#heightUnit').val();
			}

			var result=validata(name,opinionName);
			if(!result){
				alert(editor.getLang("opinion.msg_form_field"));
				return false;
			}
			name="opinion:"+name;
			var html = '<span name="editable-input" style="display:inline-block;">';			
			html += '<textarea name="'+name+'" opinionname="'+opinionName+'" ewidth="'+$('#input-width').val()+'" eheight="'+$('#input-height').val()+'" widthUnit="'+$('#widthUnit').val()+'" heightUnit="'+$('#heightUnit').val()+'" ' ;
			html +='style="width:'+objwidth+';height:'+objheight+'"></textarea>';
			html += '</span> ';
			if(_element){
				var child=editor.curOpinion.childNodes[0];
				$(child).attr("name",name);
				$(child).attr("opinionname",opinionName);
				$(child).width(objwidth); 
				$(child).height(objheight); 
			}
			else{
				if(!parent.__Permission__){
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
					//editor.execCommand('insertHtml', html);
					return;
				}
				//添加意见权限
				var rtn=parent.__Permission__.addOpinion(name,opinionName);
				if(rtn){
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
					//editor.execCommand('insertHtml', html);
				}
				else{
					alert(editor.getLang("opinion.msg_opinion_exist"));
					return false;
				}
			}
			editor.curOpinion = null;
		};
		
		//验证
		function validWord(obj){
			var me = $(obj);
			var val = me.val();
			 if(!/^[a-zA-Z]\w*$/.test(val.trim())){
				$("#validSpan").show();
			 }else{
				$("#validSpan").hide();
			 }
		};
		
		//自动生成Key
		function autoGetKey(obj){
			var subject=$(obj).val();
			if($.trim(subject).length<1) return;
			var py =Share.getPingyin({input:subject});
			if($.trim($('#input-name').val()).length>1) return;
			$('#input-name').val(py);
			
		};
	</script>
</head>
<body >
	<div id="inputPanel">
		<fieldset class="base">
			<legend><var id="lang_box_prop"></var></legend>
			<table>
				<tr>
					<th>意见描述:</th>
					<td>
						<input id="input-opinion-name" type="text"  onblur="autoGetKey(this)"/>
					</td>
				</tr>
				<tr>
					<th>意见名称:</th>
					<td>
						<input id="input-name" type="text" onblur="validWord(this)"/>
						<span style='color:red;display:none' id="validSpan" >只能为字母开头,允许字母、数字和下划线</span>
					</td>
				</tr>		
				<tr class="style_tr">
					<th><var id="lang_control_width"></var>:</th>
					<td>
						<input type="text" id="input-width"  />
						<select style="width:40px;" id="widthUnit">
							<option value="px">px</option>
							<option value="%">%</option>
						</select>
					</td>
				</tr>
				<tr>
					<th><var id="lang_control_height"></var>:</th>
					<td>
						<input type="text" id="input-height"/>
						<select style="width:40px;" id="heightUnit">
							<option value="px">px</option>
							<option value="%">%</option>
						</select>
					</td>
				</tr>
			</table>
		</fieldset>
	</div>
</body>
</html>