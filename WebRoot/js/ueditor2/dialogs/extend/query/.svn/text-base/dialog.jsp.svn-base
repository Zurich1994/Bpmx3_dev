<%@page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/commons/include/form.jsp"%>
<link rel="stylesheet" type="text/css" href="../input.css">
<script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.dragdiv.js"></script>
<link rel="stylesheet" href="${ctx}/js/jquery/plugins/jquery.dragdiv.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/js/tree/zTreeStyle.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js"></script>
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
	margin-left: 5px;
	float: left;
	width: 380px;
	height: 260px;
	background-color: powderblue;
	overflow-y: auto;
	overflow-x: hidden;
}
#fieldContainer{
	height:70px;
	overflow-y:auto;
	overflow-x:hidden;
}
#fieldTable{
	margin:0;
}
</style>

</head>
<body>
	<div id="inputPanel">
		<fieldset class="base">
			<legend>
				查询设置
			</legend>
			<table  width="90%">
				<tr>
					<th>查询类型:</th>
					<td colspan="2">
						<select id="query-type">
							<option value="0">--请选择--</option>
							<option value="querybtn">自定义查询</option>
							<option value="aliasbtn">别名脚本</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>选择查询:</th>
					<td colspan="2">
						<select id="dialog-type" onchange="dialogChange()">
								<option value="0"></option>
						</select>
					</td>
				</tr>
				<tr>
					<th>选择子表:</th>
					<td>
					<select id="table-type">
					</select>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">
						<div id="fieldContainer">
						<table id="fieldTable"   width="90%">
						</table>
						</div>
					</td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="base">
			<legend>
				回填值设置
			</legend>
			<div class="fields-div">
				<ul id="fields-tree" class="ztree field-ul"></ul>
			</div>
			<div class="domBtnDiv"></div>
		</fieldset>
	</div>
	
	<textarea id="templateTr" style="display:none;">
	<#list data as a>
		<tr class="fieldTr">
			<td >
				<input type="checkbox" style="width:10%;float:left;" class="fieldCheck" />
				<!-- 条件字段 -->
				<input type="text" class="conditionField" style="width:25%;float:left;margin-right: 5px;" value="\${a.field}" readonly id="\${a.field}"/>
				<div class="fielddiv" style="display:none;">
				<select class="fieldSelect" style="width:30%;margin-right: 5px;">
					<option value="-1" controlType="-1" >--请选择--</option>
				</select>
				<input type="text" class="fieldValue" style="width:25%" value="--预设值--" onfocus="if (value==defaultValue)value=''" onblur="if(!value)value=defaultValue"/>
				</div>
			</td>
		</tr>
	</#list>
	</textarea>
	<textarea id="subtableTemplateOption" style="display:none;">
	<#list data as o>
		<option value="\${o.tableName}">\${o.tableDesc}</option>
	</#list>
	</textarea>

</body>
<script type="text/javascript">
	var obj = $(editor.curInput);
	var curNode = obj;
	var parent = obj.parent();
	var bodyObj=obj.parents('body');
	if(!curNode.is('select'))
		curNode=parent;
	var dragDiv;
	var selectOpt=[];
	var setting = {
		edit : {
			enable : true,
			showRemoveBtn : false,
			showRenameBtn : false,
			drag : {}
		},
		data : {
			keep : {
				parent : true,
				leaf : true
			},
			simpleData : {
				enable : true
			}
		},
		view : {
			selectedMulti : false
		}
	};

	$(function() {
		$('#query-type').change(function(){
			var type = $(this).val();
			getDialogs(type);
		});
		type = $(editor.curInput).get(0).getAttribute('queryType');
		if(type){
			$('#query-type').val(type);
			$('#query-type').trigger('change');
		}
		$('.fieldCheck','.fieldTr').live("click",function(){
			$(this).siblings('.fielddiv').each(function(){
				
				if($(this).css('display')!='none'){
					$(this).hide() ;
				}else{
					$(this).show() ;
				}
			});
		});
	});
	//编辑时绑定数据
	function bindData(dialogStr) {
		var dialogObj=dialogStr.replaceAll("'","\"");
		var dialog = $.parseJSON(dialogObj);
		if (!dialog) return;
		$("#dialog-type").find("option[value='" + dialog.name + "']").each(function() {
			$(this).attr("selected", "selected");
			dialogChange();
		});
		while(field=dialog.fields.pop()){
			var src=field.src,
				targets=field.target,target;
			while(target=targets.pop()){
				var item = $("span.item-span[itemId='"+target+"']").toggleClass("item-span item-span-Disabled");
				if(item.length>0){
					var node = {id:target, name: item.text()};
					addNode(src,node);
				}
			}
		}
		var dialogQueryArr = dialog.query;
		if(dialogQueryArr && dialogQueryArr.length>0){
			for(var i=0;i<dialogQueryArr.length;i++){
				var dialogQueryObj = dialogQueryArr[i];
				var isMain = dialogQueryObj.isMain ;
				$(":text#"+dialogQueryObj.condition,".fieldTr").each(function(){
					$(this).siblings(".fieldCheck").click() ;
					$(this).siblings('div.fielddiv').children('select').find("option[value='"+dialogQueryObj.trigger+"']").each(function(){
						var self = $(this);
						self.attr("selected","selected");
						var value = self.val();
					});
					if(dialogQueryObj.initValue!=''){
						$(this).siblings('div.fielddiv').children(':text').val(dialogQueryObj.initValue);
					}else{
						$(this).siblings('div.fielddiv').children(':text').val("--预设值--");
					}
				});
			}
		}
		var subtableName = dialog.subtableName;
		if(subtableName) $('#table-type').val(subtableName);
	};

	//添加树节点
	function addNode(id, node) {
		var zTree = $.fn.zTree.getZTreeObj('fields-tree');
		if (!zTree)
			return;
		var parentNode = zTree.getNodeByParam("id", id, null);
		if (parentNode)
			zTree.addNodes(parentNode, node);
	};

	//获取自定义查询
	var queryCache = {};
	function getDialogs(type) {
		var cache = queryCache[type];
		$("#dialog-type").empty();
		$("#dialog-type").append('<option value="0">--请选择--</option>');
		if(cache){
			for(var i=0;i<cache.length;i++){
				$("#dialog-type").append(cache[i]);
			}
			$("#dialog-type").trigger('change');
			return;
		}else {
			cache = [];
		}
		var url = '';
		if(type == 'querybtn'){
			url = '/platform/bpm/bpmFormQuery/getAllQueries.ht';
		}else if(type == 'aliasbtn'){
			url = '/platform/system/aliasScript/getAllAliasScripts.ht?enable=0&isReturnValue=1&scriptType=default';
		}
		if(!url) return;
		$.get(__ctx + url,function(data) {
			if (!data) return;
			if(type == 'querybtn'){
				for ( var i = 0, c; c = data[i++];) {
					var opt = $('<option value="'+c.alias+'" fields="'+c.returnFields
							+'" istable="'+c.istable+'" objname="'+c.objname+'" dsname="'+c.dsalias+'" >'+ c.name + '</option>');
					opt.attr("resultfield", c.resultfield.replaceAll('\"', "'"));
					opt.attr("conditionfield",c.conditionfield.replaceAll('\"', "'"));
					$("#dialog-type").append(opt);
					cache.push(opt);
				}
			}else if(type == 'aliasbtn'){
				for ( var i = 0, c; c = data[i++];) {
					var opt = $('<option value="'+c.aliasName+'" fields="'+c.returnFields
							+'">'+ c.aliasDesc + '</option>');
					var argument = c.argument;
					if(!argument) argument = '{}';
					var returnParamJson = c.returnParamJson;
					if(!returnParamJson) returnParamJson = '{}';
					opt.attr("conditionfield", argument.replaceAll('\"', "'"));
					opt.attr("resultfield",returnParamJson.replaceAll('\"', "'"));
					$("#dialog-type").append(opt);
					cache.push(opt);
				}
			}
			if(utils.isEmptyObject(queryCache)) getFileds(editor.tableId);
			queryCache[type] = cache;
		});

	};
	//选择不同的查询
	function dialogChange() {
		var condition_field = $("#condition-field");
		condition_field.empty();
		var dia = $("#dialog-type").find(":selected");
		var v = dia.attr("resultfield");
		var condStr = dia.attr("conditionfield");
		var type = $("#query-type").val();
		if (v) {
			var nodes = [];
			var fields = eval("("+v+")");
			var idName = 'field';
			var name = 'comment';
			if(type == 'aliasbtn'){
				idName = 'fieldName';
				name = 'fieldDesc';
			}
			for ( var i = 0; i < fields.length; i++) {
				var f = fields[i];
				nodes.push({
					id : f[idName],
					pid : 0,
					name : f[name],
					isParent : true,
					open : true
				});
			}

			$("span.item-span-Disabled").each(function() {
				$(this).toggleClass("item-span-Disabled");
				$(this).toggleClass("item-span");
			});
			var tree = $.fn.zTree.init($("#fields-tree"), setting, nodes);
			//设置拖拽 树对象
			if (dragDiv)
				dragDiv.dragdiv('bind', 'fields-tree');
		}
		
		if (condStr) {
			$('#fieldTable').empty();
			var jsonObj = eval("("+condStr+")"), html="";
			var conditionfield= [];
			for(var i=0; i<jsonObj.length;i++){
				var obj = jsonObj[i];
				if(obj.defaultType=="1"){
					conditionfield.push(obj);
				}else if(type == 'aliasbtn'){
					obj.field = obj.paraName;
					conditionfield.push(obj);
				}
			}
			//对于值是输入框的，生成“绑定参数”下拉框选项
			if(typeof(conditionfield)!="undefined" && conditionfield!=""){
				var template = $('textarea#templateTr').val() ;
				var templateTrHtml = easyTemplate(template,conditionfield).toString();
				$('#fieldTable').append(templateTrHtml);
				if(selectOpt.length!=0){
					for(var j=0;j<selectOpt.length;j++){
						$('#inputPanel table').find('.fieldSelect').append(selectOpt[j]);
					}
				}
			}
		}
	}

	dialog.onok = function() {
		var name = $("#dialog-type").val();
		var queryType = $('#query-type').val();
		if (!name) {
			curNode.removeAttr("querybtn");
			curNode.removeAttr("aliasbtn");
			curNode.removeAttr("dialog");
			return;
		};

		var zTree = $.fn.zTree.getZTreeObj("fields-tree"), 
			nodes = zTree.getNodes(),fields=[];
		
		for(var i=0,c;c=nodes[i++];){
			if(!c.children)continue;
			var target=[],child=null;				
			while(child=c.children.pop()){
				target.push(child.id);
			}
			if(target.length>0){
				var sub = {};
				sub.src = c.id;
				sub.target = target;
			}
			fields.push(sub);
		}
		//处理类型是输入框
		var queryArr = [] ;
		
		$(':checkbox:checked','table .fieldTr').each(function(){
			var queryObj = {} ;
			queryObj.condition = $(this).siblings(':text').attr('id') ;
			var selectobj=$(this).siblings('div.fielddiv').children('select').find(":selected");
			queryObj.trigger = selectobj.val();
			var controlType=selectobj.attr('controlType');
			queryObj.controlType=controlType;
			queryObj.isMain = selectobj.attr('isMain') ;
			var initvalue=$(this).siblings('div.fielddiv').children(':text').val();
			if(initvalue=="--预设值--"){
				initvalue="";
			}
			queryObj.initValue=initvalue;
			queryArr.push(queryObj);
		});
		
		var json={
				name:name,
				query:queryArr,
				fields:fields};
		json.subtableName = $('#table-type').val();
		var json2 = JSON2.stringify(json).replaceAll("\"","'") ;
		editor.curInput.setAttribute(queryType,json2);
		editor.curInput.setAttribute('queryType',queryType);
		editor.curInput = null;
	};
	//初始化字段面板
	function initFieldsDiv(data) {
		var mainTable = data.table, data = {};
		data.name = mainTable.tableDesc + '('+editor.getLang("customdialog.main")+')';
		data.id = mainTable.tableName;
		data.desc = mainTable.tableId;
		var formMainField=mainTable.tableName?$(bodyObj).find('[name*="'+mainTable.tableName+'"]'):[];
		
		var items = [];
		for ( var i = 0, c; c = mainTable.fieldList[i++];) {
			//判断当前字段是否在表单上显示，如果存在这显示，
			if(formMainField.length != 0 && removeObj(formMainField,c.fieldName))continue;
			var type=c.controlType;
			items.push({
				name : c.fieldDesc,
				id : c.fieldName,
				ctype : type
			});
		}
		
		//生成“绑定参数”行
		var opt;
		if(items){
			opt = '<optgroup label="主表字段"></optgroup>';
			selectOpt.push(opt);
			for(var i=0, c;c=items[i++];){
				 opt='<option value="'+c.id+'"  controlType="'+c.ctype+'" ismain=true >' + c.name+ '</option>';
				 selectOpt.push(opt);
			}
				
		}

		var allSubItems = [];
		for ( var i = 0, c; c = mainTable.subTableList[i++];) {
			var sub = {};
			sub.name = c.tableDesc + '('+editor.getLang("customdialog.sub")+')';
			sub.id = c.tableName;
			sub.desc = c.tableId;
			var currSubField=$(bodyObj).find('[name*="'+c.tableName+'"]');
			var subItems = [];
			for ( var y = 0, t; t = c.fieldList[y++];) {
				//判断当前字段是否在表单上显示，如果存在这显示，不存在这不显示
				if(formMainField.length != 0 && removeObj(currSubField,t.fieldName))continue;
				var type=t.controlType;
				subItems.push({
					name : t.fieldDesc,
					id : t.fieldName,
					ctype : type
				});
			}
			sub.items = subItems;
			items.push(sub);
			allSubItems = allSubItems.concat(subItems);
		}
		//生成“绑定子表”行
		if($('#table-type').children().length>0) return;
		if($('#table-type').children().length<1) {
			template = $('#subtableTemplateOption').val() ;
			templateTrHtml = easyTemplate(template,mainTable.subTableList).toString();
			$('#table-type').append(templateTrHtml);
		}
		var parentTableClass = $(editor.curInput).closest('div[type="subtable"]') ;
		if(!parentTableClass || parentTableClass.length>0){
			//若为空，表示主表，则隐藏子表字段
			opt = '<optgroup label="子表字段"></optgroup>';
			selectOpt.push(opt);
			for(var i=0, c;c=allSubItems[i++];){
				 opt='<option value="'+c.id+'" controlType="'+c.ctype+'" ismain=false >' + c.name+ '</option>';
				 selectOpt.push(opt);
			}
		}
		data.items = items;
		//初始化字段面板
		dragDiv = $(".domBtnDiv").dragdiv('init',{data : data});
		var queryType = $('#query-type').val();
		var dialogStr = $(editor.curInput).get(0).getAttribute(queryType);
		if (dialogStr) {
			bindData(dialogStr);
		}
	};

	//加载字段面板
	function getFileds(tableId) {
		if (tableId) {
			var url = __ctx
					+ '/platform/form/bpmFormTable/getTableById.ht';
			$.post(url, {incHide:true,tableId:tableId}, function(data) {
				initFieldsDiv(data);
			});
		} else { //编辑器设计表单时获取字段并验证字段
			var html = editor.getContent();
			var params = {};
			params.html = html;
			params.formDefId = editor.formDefId;
			params.incHide = true;

			$.post(__ctx + '/platform/form/bpmFormDef/validDesign.ht', params,
			function(data) {
				if (data.valid) {
					initFieldsDiv(data);
				} else {
					alert(data.errorMsg);
				}
			});
		}
	};
	
	//判断当前字段是否在表单上显示，如果存在这显示，不存在这不显示
	function removeObj(obj,id){
		//当前表单所有字段
		var AllField=$(obj);
		for( var i=0 ;i<AllField.length; i++){
			var formName=$(AllField[i]).attr("name");
			if(!formName)continue;
			var name=formName.split(":");
			if(name[2]==fieldName){
				return false;
			}else{
				continue;
			}
		}
		return true;
	}
	
</script>
</html>