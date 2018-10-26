<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/commons/include/form.jsp" %>
<link rel="stylesheet" type="text/css" href="../input.css">
<link rel="stylesheet" href="${ctx}/js/jquery/plugins/jquery.dragdiv.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/js/tree/zTreeStyle.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/hontent/platform/form/FormData.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.dragdiv.js"></script>
<script type="text/javascript">
	var _element; 
	
	$(function() {
		var el = editor.selection.getRange().getClosedNode(),
			 funcInstance={}, funcexp = el.getAttribute("funcexp");
			
		if (funcexp) {
			funcInstance.exp = funcexp;
		}
		funcInstance.targetEl=el;
		editor.funcInstance = funcInstance;
		$(".button-td").bind("mouseenter mouseleave", function() {
			$(this).toggleClass("button-td-hover");
		});
		_element = null;
		_element = funcInstance;
		if (_element) {
		    bindData();
		}
		getFileds(editor.tableId);
	});
	function removeOpt(e){
		 $("#selectedFields option[value='"+$(e).val()+"']").remove(); 
	}
	function bindData() {
		var type = FormData.FunctionType,exp=_element.exp;
		if (type) {
			for(var i=0,c;c=type[i++];){
				$("#func-type").append('<option title="'+c.des+'" value="'+c.name+'">'+c.des+'</option>');
			}
		}
		if(!$(editor.funcInstance.targetEl).attr("readonly")){
			$("#input_readonly").removeAttr("checked");
		}
		if(exp){
		   var expObj=eval("("+exp+")");
		   setTimeout(function(){
		 			$("#func-type").find("option[value='"+expObj.type+"']").each(function(){
						$(this).attr("selected","selected");
					}
				);
				for(var i=0,c;c=expObj.target[i++];){
					$("#selectedFields").append('<option title="'+c.des+'" value="'+c.name+'">'+c.des+'</option>');
				}
				if(expObj.capital){
					$("#display_capital").attr("checked","checked");
				}
				if(expObj.label){
					$("#display_label").attr("checked","checked");
				}
				if(expObj.percentage){
					$("#display_percentage").attr("checked","checked");
				}
				if(expObj.tofixed||expObj.tofixed==0){
					$("#display_tofixed").val(expObj.tofixed);
				}
		   },100);
		}
	};

	dialog.onok = function() {
		var type=$("#func-type").val(),
			target=[],
			readonly=$("#input_readonly").attr("checked"),
			capital=$("#display_capital").attr("checked"),
			label=$("#display_label").attr("checked"),
			percentage = $("#display_percentage").attr("checked"),
			tofixed = $("#display_tofixed").val();
		if(readonly){
			$(editor.funcInstance.targetEl).attr("readonly","readonly");
		}
		else{
			$(editor.funcInstance.targetEl).removeAttr("readonly");
		}
		$("#selectedFields").find("option").each(function(){
			var optObj = {name:$(this).val(),des:$(this).text()};
			target.push(optObj);
		});
		if(target.length>0){
			var exp = {type:type};
			if(capital)
				exp.capital = true;
			if(label)
				exp.label = true;
			if(percentage)
				exp.percentage = true;
			if(tofixed||tofixed==0)
				exp.tofixed = tofixed;
			exp.target = target;
			var expStr = JSON.stringify(exp);
			$(editor.funcInstance.targetEl).attr("funcexp",expStr);
			$(editor.funcInstance.targetEl).addClass("math-function-input");
		}
		else{
			$(editor.funcInstance.targetEl).removeAttr("funcexp");
			$(editor.funcInstance.targetEl).removeClass("math-function-input");
		}
	};
	function initFieldsTree(data){
		var mainTable = data.table,
			iconFolder = __ctx + '/styles/tree/',
			items = [];
		mainTable.name = mainTable.tableDesc;
		mainTable.icon = iconFolder + 'table.png';
		mainTable.open = true;			
		
		var mChildren=[];
		for ( var i = 0, c; c = mainTable.fieldList[i++];) {
			if(c.isHidden!=0)continue;
			c.name = c.fieldDesc;
			c.icon = iconFolder + c.fieldType + '.png';
			c.isMain=true;
			c.tableDesc=mainTable.tableDesc;
			c.tableName=mainTable.tableName;
			mChildren.push(c);
		}

		for ( var i = 0, c; c = mainTable.subTableList[i++];) {
			c.name = c.tableDesc;
			c.icon = iconFolder + 'table.png';
			c.open = true;
			
			mChildren.push(c);
			
			var sChildren = [];
			for ( var y = 0, t; t = c.fieldList[y++];) {
				if(t.isHidden!=0)continue;
				t.name = t.fieldDesc;
				t.icon = iconFolder + t.fieldType + '.png';	
				t.isMain=false;
				t.tableDesc=c.name;
				t.tableName=c.tableName;
				sChildren.push(t);
			}
			c.children = sChildren;
		}
		mainTable.children = mChildren;
		items.push(mainTable);
		
		var setting = {					
				callback : {						
					onDblClick:function(treeId,treeNode,clickFlag){
						addToSelected(clickFlag);
					}
				}
			};

		glTypeTree = $.fn.zTree.init($("#colstree"),setting, items);
	};
	function getFileds(tableId) {
		if(tableId){
			var url = __ctx
					+ '/platform/form/bpmFormTable/getTableById.ht?tableId='
					+ tableId;
			$.post(url, function(data) {
				initFieldsTree(data);
			});
		}
		else{
			var html = editor.getContent();		
			var params={};
			params.html=html;
			
			$.post(__ctx + '/platform/form/bpmFormDef/validDesign.ht', params, function(data){
				if(data.valid){
					initFieldsTree(data);
				}
				else{
					alert(data.errorMsg);
				}
			});
		}
	};
	function addToSelected(e){
		if(!e)return;
		if(e.fieldType!="number"){
			alert(editor.getLang("insertfunction.msg_not_num"));
			return;
		}
		var temp=true;
		$("#selectedFields").find("option").each(function(){
			if($(this).val()==e.fieldName){
				temp=false;
				return;
			}
		});
		if(temp){
			if(e.isMain){
				$("#selectedFields").append('<option title="'+e.fieldDesc+'['+e.tableDesc+']" value="m:'+e.tableName+':'+e.fieldName+'">'+e.fieldDesc+'['+e.tableDesc+']</option>');
			}else{
				$("#selectedFields").append('<option title="'+e.fieldDesc+'['+e.tableDesc+']" value="s:'+e.tableName+':'+e.fieldName+'">'+e.fieldDesc+'['+e.tableDesc+']</option>');
			}
		}
	};
</script>
</head>
<body>
	<div id="inputPanel">
		<fieldset class="base">
			<legend><var id="lang_math_prop"></var></legend>
			<table>
				<tr>
					<th><var id="lang_math_type"></var>:</th>
					<td><select id="func-type"></select></td>
				</tr>
				<tr>
					<th>
						<var id="lang_all_field"></var>
					</th>
					<th>
						<var id="lang_count_field"></var>
					</th>
				</tr>
				<tr>
					<td>
						<div id="field-div">
							<ul id="colstree" class="ztree fields-ul" style="height:198px;"></ul>
						</div>
					</td>
					<td><select id="selectedFields" style="height:198px;" ondblclick="removeOpt(this)" multiple="multiple" size="15"></select></td>
				</tr>
				<tr>					
					<td colspan="2">
						<label class="custom-label"><input id="input_readonly" type="checkbox" checked="checked"/>
							<var id="lang_input_readonly"></var>
						</label>
						<label class="custom-label"><input id="display_capital" type="checkbox" />
							<var id="lang_dis_rmb"></var>
						</label>
						<label class="custom-label"><input id="display_label" type="checkbox" />
							<var id="lang_dis_span"></var>
						</label>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="custom-label"><input id="display_percentage" type="checkbox" />
							<var id="lang_dis_persent"></var>
						</label>
						<label class="custom-label"><var id="lang_num_tofixed"></var><input id="display_tofixed" style="width:30px;height:18px;" type="text" /><var id="lang_num_bit"></var>
						</label>
					</td>
				</tr>
			</table>
		</fieldset>
	</div>
</body>
</html>