<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/commons/include/form.jsp" %>
<link rel="stylesheet" type="text/css" href="../input.css">
<script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.dragdiv.js"></script>
<link rel="stylesheet" href="${ctx}/js/jquery/plugins/jquery.dragdiv.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/js/tree/zTreeStyle.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/util/json2.js"></script>
<style type="text/css">
.field-ul {
	width: 95%;
	height: 95%; 
	margin: 0;
	padding: 0;
	overflow-y: auto;
	overflow-x: hidden;
}
.fields-div {
	float: left;
	border: 1px solid #828790;
	width: 160px;
	height: 260px;
	overflow: auto;
}
.domBtnDiv {
	display: block;
	margin-left:5px;
	float:left;
	width:380px;
	height:260px;
	background-color: powderblue;
	overflow-y: auto;
	overflow-x: hidden;
}
</style>
<script type="text/javascript">
	var obj = $(editor.selection.getStart());
	var curNode = obj;
	var fieldName=obj.attr("name");
	var parent=obj.parent();
	var dragDiv;
	if(fieldName && parent){
		var idFilter="input[name='"+fieldName+"ID']";
		var nameFilter="input[name='"+fieldName+"']";
		var inputId=$(idFilter,parent);
		if(inputId.length>0){
			curNode=inputId;
		}
	}
	var setting = {
			edit: {
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false,	
				drag:{}
			},				
			data: {
				keep: {
					parent: true,
					leaf: true
				},
				simpleData: {
					enable: true
				}
			},				
			view: {
				selectedMulti: false
			}
		};
	
	$(function() {
		$(".button-td").bind("mouseenter mouseleave", function() {
			$(this).toggleClass("button-td-hover");
		});
		getDialogs();
	});		
	//编辑时绑定数据
	function bindData(dialogStr) {
		var dialog = eval("("+dialogStr+")");
		var field;
		if(!dialog)return;
		$("#dialog-type").find("option[value='"+dialog.name+"']").each(function(){
				$(this).attr("selected","selected");
				dialogChange();
			}
		);
		while(field=dialog.fields.pop()){
			var src=field.src;
			var targets=field.target;
			var target;
			while(target=targets.pop()){
				var item = $("span.item-span[itemId='"+target+"']").toggleClass("item-span item-span-Disabled");
				if(item.length>0){
					var node = {id:target, name: item.text()};
					addNode(src,node);
				}
			}
		}
		setTimeout(function(){
			$("#condition-field").find("option[value='"+dialog.cond+"']").attr("selected","selected");
			$("#bind-event").find("option[value='"+dialog.evt.name+"']").attr("selected","selected");
		},10);
	};
	//添加树节点
	function addNode(id,node){
		var zTree = $.fn.zTree.getZTreeObj('fields-tree');
		if(!zTree)return;
		var parentNode = zTree.getNodeByParam("id",id,null);
		if(parentNode) zTree.addNodes(parentNode,node);
	};
	
	//获取自定义查询
	function getDialogs(){
		var url = __ctx + '/platform/bpm/bpmFormQuery/getAllQueries.ht';
		$.get(url,function(data){
			if (data) {
				for(var i=0,c;c=data[i++];){
					var opt = $('<option value="'+c.alias+'" fields="'+c.returnFields+'" conditions="'+c.conditionFields+'" >'+c.name+'</option>');
					opt.data("fields",c.resultfield);
					$("#dialog-type").append(opt);
					
				}
				getFileds(editor.tableId);
			}
		});
	};		
	//选择不同的查询
	function dialogChange(){
		var condition_field =$("#condition-field");
		condition_field.empty();
		var dia=$("#dialog-type").find("option:selected");
		var v = dia.data("fields");
		var condStr = dia.attr("conditions");
		if(v){				
			var nodes=[];
			var fields = eval("("+v+")");
			for(var i=0;i<fields.length;i++){
				var f=fields[i];
				nodes.push({id:f.field,pid:0,name:f.comment,isParent: true, open:true});
			}

			$("span.item-span-Disabled").each(function(){
					$(this).toggleClass("item-span-Disabled");
					$(this).toggleClass("item-span");
				}						
			);
			var tree = $.fn.zTree.init($("#fields-tree"), setting, nodes);
			//设置拖拽 树对象
			if(dragDiv)dragDiv.dragdiv('bind','fields-tree');
		}
		if(condStr){
			var options=[];
			var fields=condStr.split(",");
			
			for(var i=0,c;c=fields[i++];){
				if(c){
					var option = $("<option/>").val(c).text(c);
					options.push(option);
				}
			}
			
			$(options).each(function(){
				condition_field.append($(this));
			});
		}
	}

	dialog.onok = function() {
		var name=$("#dialog-type").val();
		var evtName = $("#bind-event").val();
		var evtKey = $("#bind-event").find('[value="'+evtName+'"]').attr("evtKey");
		var evtKeyCode = $("#bind-event").find('[value="'+evtName+'"]').attr("evtCode");
		var evt = {
				name:evtName,
				key:evtKey,
				code:evtKeyCode
		};
		var cond = $("#condition-field").val();;
		
		if(!name){
			curNode.removeAttr("query");
			return;
		}
		var zTree = $.fn.zTree.getZTreeObj("fields-tree"),
		nodes=zTree.getNodes(),fields=[];
		
		for(var i=0,c;c=nodes[i++];){
			if(!c.children)continue;
			var target=[],child=null;				
			while(child=c.children.pop()){
				target.push(child.id);
			}
			var sub={
					src:c.id,
					target:target
			};
			fields.push(sub);
		}
		var json={
				name:name,
				evt:evt,
				fields:fields,
				cond:cond
		};
		var jsonStr = JSON2.stringify(json).replaceAll('"',"'");
		curNode.get(0).setAttribute("query",jsonStr);
	};
	//初始化字段面板
	function initFieldsDiv(data){
		var mainTable = data.table, data = {};
		data.name = mainTable.tableDesc + '('+editor.getLang("customdialog.main")+')';
		data.id = mainTable.tableName;
		data.desc = mainTable.tableId;

		var items = [];
		for ( var i = 0, c; c = mainTable.fieldList[i++];) {
			items.push({
				name : c.fieldDesc,
				id : c.fieldName
			});
		}

		for ( var i = 0, c; c = mainTable.subTableList[i++];) {
			var sub = {};
			sub.name = c.tableDesc + '('+editor.getLang("customdialog.sub")+')';
			sub.id = c.tableName;
			sub.desc = c.tableId;

			var subItems = [];
			for ( var y = 0, t; t = c.fieldList[y++];) {
				subItems.push({
					name : t.fieldDesc,
					id : t.fieldName
				});
			}
			sub.items = subItems;
			items.push(sub);
		}
		data.items = items;
		dragDiv = $(".domBtnDiv").dragdiv('init',{data : data});
		var dialogStr = curNode.get(0).getAttribute("query");
		if (dialogStr) {
			bindData(dialogStr);
		}
	};

	//加载字段面板
	function getFileds(tableId) {
		if(tableId){
			var url = __ctx
					+ '/platform/form/bpmFormTable/getTableById.ht?tableId='
					+ tableId+'&incHide=true';
			$.post(url, function(data) {
				initFieldsDiv(data);
			});
		}else{	//编辑器设计表单时获取字段并验证字段
			var html = editor.getContent();		
			var params={};
			params.html=html;
			params.formDefId=editor.formDefId;
			
			
			$.post(__ctx + '/platform/form/bpmFormDef/validDesign.ht?incHide=true', params, function(data){
				if(data.valid){
					initFieldsDiv(data);
				}
				else{
					alert(data.errorMsg);
				}
			});
		}
	};
</script>
</head>
<body>
	<div id="inputPanel">
		<fieldset class="base">
			<legend><var id="lang_search_setting"></var></legend>
			<table>
				<tr>
					<th><var id="lang_choose_search"></var>:</th>
					<td>
						<select id="dialog-type" onchange="dialogChange()">
							<option value="0"></option>
						</select>
					</td>
				</tr>
				<tr>
					<th><var id="lang_condition_field"></var>:</th>
					<td>
						<select id="condition-field">
						</select>
					</td>
				</tr>
				<tr>
					<th><var id="lang_trigger_event"></var>:</th>
					<td>
						<select id="bind-event">
							<option value="carriage_return" evtKey="keyup" evtCode="13"></option>
							<option value="valueChange" evtKey="change" evtCode="0"></option>
						</select>
					</td>
				</tr>								
			</table>
		</fieldset>
		<fieldset class="base">
			<legend><var id="lang_return_setting"></var></legend>
				<div class="fields-div">
					<ul id="fields-tree" class="ztree field-ul"></ul>
				</div>
				<div class="domBtnDiv">
				</div>
		</fieldset>
	</div>
	
	
</body>
</html>