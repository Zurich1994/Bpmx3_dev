<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/commons/include/form.jsp" %>
<link rel="stylesheet" type="text/css" href="../input.css">
<style type="text/css">
	.resVal{
		width:240px;
	}
</style>
<script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
<script type="text/javascript">
	var curNode = null,
		curNodeParent = null;
	$(function(){
		curNode =  editor.$curTextNode;
		curNodeParent = domUtils.findParents(curNode,false,null,true)[0];
		getLanguages();
	});

	function getLanguages(){
		var url = __ctx + '/platform/system/sysLanguage/getAllLanguages.ht';
		$.get(url,function(data){
			data = $.parseJSON(data);
			var table = $("#table");
			for(var i=0,c;c=data[i++];){
				var input = $('<input type="text">').attr("id",c.language).addClass("resVal"),
					td = $('<td>').append(input),
					th = $('<th>').html(c.memo),
					tr = $('<tr>').append(th).append(td);
				table.append(tr);
			}
			init();
		});
	};

	function init(){
		if(!curNode)return;
		if(curNodeParent&&curNodeParent.getAttribute("i18n")){
			var i18n = curNodeParent.getAttribute("i18n");
			i18n = $.parseJSON(i18n);
			if(!i18n)return;
			for(var i=0,c;c=i18n.resval[i++];){
				$("#"+c.lantype).val(c.value).attr("lanid",c.lanid);
			}
		}
		else{
			var zh_cn_value = curNode.nodeValue.trim();
			$("#zh_cn").val(zh_cn_value);
		}
	};

	dialog.onok = function() {
		if(!curNode)return;
		
		var i18n = {reskey:''},
			resval = [];

		$("input.resVal").each(function(){
			var me = $(this),
				lanid = me.attr("lanid"),
				lantype = me.attr("id"),
				val = me.val();
			if(!lanid)
				lanid = 0;

			resval.push({lanid:lanid,lantype:lantype,value:val});
		});
		i18n.resval =resval;
		
		if(curNodeParent&&curNodeParent.getAttribute("i18n")){
			var oldI18n = curNodeParent.getAttribute("i18n");
			oldI18n = $.parseJSON(i18n);
			i18n.reskey = oldI18n.reskey;
			curNodeParent.attr("i18n",JSON.stringify(i18n));
		}
		else{
			var p = $("<p>"),
			next = domUtils.getNextDomNode(curNode);
			
			p.attr("i18n",JSON.stringify(i18n));
			$(curNode).before(p);
			p.append(curNode);
			if($(next).is('br'))
				p.append($(next));
		}
	};
</script>
</head>
<body>
	<div id="inputPanel">
		<fieldset class="base">
			<legend><var id="lang_dialog_setting"></var></legend>
			<table id="table">
				<tbody>
				</tbody>
			</table>
		</fieldset>
	</div>
</body>
</html>