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
<script type="text/javascript">
	var obj = $(editor.curInput);
	var curNode = obj;
	var parent = obj.parent();
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
		$(".button-td").bind("mouseenter mouseleave", function() {
			$(this).toggleClass("button-td-hover");
		});
		getDialogs();
		
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
		if (!dialog)
			return;
		$("#dialog-type").find("option[value='" + dialog.name + "']").each(
				function() {
					$(this).attr("selected", "selected");
					dialogChange();
				});
		var key = dialog.binding.key;
		var value = dialog.binding.value;

		var target = [ {
			id : 'key',
			value : key
		}, {
			id : 'value',
			value : value
		} ];
		for ( var i = 0, t; t = target[i++];) {
			var item = $("span.item-span[itemId='" + t.id + "']").toggleClass(
					"item-span item-span-Disabled");
			if (item.length > 0) {
				var node = {
					id : t.id,
					name : item.text()
				};
				addNode(t.value, node);
			}
		}
		
		var dialogQueryArr = dialog.query;
		if(dialogQueryArr && dialogQueryArr.length>0){
			for(var i=0;i<dialogQueryArr.length;i++){
				var isMain = dialogQueryArr[i].isMain ;
				$(":text#"+dialogQueryArr[i].condition,".fieldTr").each(function(){
					$(this).siblings(".fieldCheck").click() ;
					$(this).siblings('div.fielddiv').children('select').find("option[value='"+dialogQueryArr[i].trigger+"']").each(function(){
						$(this).attr("selected","selected");
					});
					if(dialogQueryArr[i].initValue!=''){
						$(this).siblings('div.fielddiv').children(':text').val(dialogQueryArr[i].initValue);
					}else{
						$(this).siblings('div.fielddiv').children(':text').val("--预设值--");
					}
				});
			}
		}
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
	function getDialogs() {
		var url = __ctx + '/platform/bpm/bpmFormQuery/getAllQueries.ht';
		$.get(url,function(data) {
							if (data) {
								for ( var i = 0, c; c = data[i++];) {
									var opt = $('<option value="'+c.alias+'" fields="'+c.returnFields+'" conditionfield="'+c.conditionfield+'" conditions="'+
											c.conditionFields+'" istable="'+c.istable+'" objname="'+c.objname+'" dsname="'+c.dsalias+'" >'+ c.name + '</option>');
									opt.data("fields", c.resultfield);
									opt.data("conditionfield",c.conditionfield);
									$("#dialog-type").append(opt);
								}
								getFileds(editor.tableId);
							}
						});

	};
	//选择不同的查询
	function dialogChange() {
		var condition_field = $("#condition-field");
		condition_field.empty();
		var dia = $("#dialog-type").find("option:selected");
		var v = dia.data("fields");
		var condStr = dia.data("conditionfield");
		if (v) {
			var nodes = [];
			var fields = $.parseJSON(v);
			for ( var i = 0; i < fields.length; i++) {
				var f = fields[i];
				nodes.push({
					id : f.field,
					pid : 0,
					name : f.comment,
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
			var jsonObj = $.parseJSON(condStr), html="";
			var conditionfield= [];
			for(var i=0; i<jsonObj.length;i++){
				if(jsonObj[i].defaultType=="1"){
					conditionfield.push(jsonObj[i]);
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
		if (!name) {
			curNode.removeAttr("selectquery");
			return;
		};
		var bindingKey = '';
		var bindingValue = '';

		var zTree = $.fn.zTree.getZTreeObj("fields-tree"), 
			nodes = zTree.getNodes();
		for ( var i = 0, c; c = nodes[i++];) {
			if (!c.children)
				continue;
			while (child = c.children.pop()) {
				if (child.id == 'key') {
					bindingKey = c.id;
				} else if (child.id == 'value') {
					bindingValue = c.id;
				}
			}
		};
		if (!bindingKey) {
			alert('请选择Key');
			return false;
		};
		if (!bindingValue) {
			alert('请选择值');
			return false;
		};
		
		var binding = {
				key : bindingKey,
				value : bindingValue
			}
		//处理类型是输入框
		var queryArr = [] ;
		
		$(':checkbox:checked','table .fieldTr').each(function(){
			var queryObj = {} ;
			queryObj.condition = $(this).siblings(':text').attr('id') ;
			var selectobj=$(this).siblings('div.fielddiv').children('select').find("option:selected");
			queryObj.trigger = selectobj.val();
			queryObj.controlType=selectobj.attr('controlType');
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
				binding:binding,
				query:queryArr};
		var json2 = JSON2.stringify(json).replaceAll("\"","'") ;
		curNode.get(0).setAttribute("selectquery", json2);
	};
	//初始化字段面板
	function initFieldsDiv(data) {
		var mainTable = data.table, data = {};
		var item = [];
		for ( var i = 0, c; c = mainTable.fieldList[i++];){
			if (c.isHidden != 0)
				continue;
			//字段的name与当前需要绑定的字段的id相同这不添加进去
			if(removeObj(curNode,c.fieldName))
				continue;
			var type=c.controlType;
			item.push({
				name : c.fieldDesc,
				id : c.fieldName,
				ctype : type
			});
		}
		//子表字段，返回一个全称作为id
		var subItems = [];
		for ( var i = 0, c; c = mainTable.subTableList[i++];) {
			var sub = {};
			tableName ="s:"+c.tableName+":";

			for ( var y = 0, t; t = c.fieldList[y++];) {
				if(removeObj(curNode,t.fieldName))
					continue;
				var type=t.controlType;
				subItems.push({
					name : t.fieldDesc,
					id : tableName+t.fieldName,
					ctype : type
				});
			}
		}
		//生成“绑定参数”行
		if(item){
			opt = '<optgroup label="主表字段"></optgroup>';
			selectOpt.push(opt);
			for(var i=0, c;c=item[i++];){
				 opt='<option value="'+c.id+'"  controlType="'+c.ctype+'" ismain=true >' + c.name+ '</option>';
				 selectOpt.push(opt);
			}
				
		}

		 var parentTableClass = $(editor.curInput).closest('div[type="subtable"]');
		if (!parentTableClass || parentTableClass.length > 0) {
			//若为空，表示主表，则隐藏子表字段
			opt = '<optgroup label="子表字段"></optgroup>';
			selectOpt.push(opt);
			for(var i=0, c;c=subItems[i++];){
				 opt='<option value="'+c.id+'" controlType="'+c.ctype+'" ismain=false >' + c.name+ '</option>';
				 selectOpt.push(opt);
			}
			
		} 

		var items = [ {
			name : '值',
			id : 'value'
		}, {
			name : 'key',
			id : 'key'
		} ];
		data.items = items;
		dragDiv = $(".domBtnDiv").dragdiv('init', {
			data : data
		});
		var dialogStr = curNode.get(0).getAttribute("selectquery");
		if (dialogStr) {
			bindData(dialogStr);
		}
	};

	//加载字段面板
	function getFileds(tableId) {
		if (tableId) {
			var url = __ctx
					+ '/platform/form/bpmFormTable/getTableById.ht?tableId='
					+ tableId;
			$.post(url, function(data) {
				initFieldsDiv(data);
			});
		} else { //编辑器设计表单时获取字段并验证字段
			var html = editor.getContent();
			var params = {};
			params.html = html;
			params.formDefId = editor.formDefId;

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
	//解码单引号和双引号
	function htmlDecode(str) {
		return str.replace(/\&quot\;/g, '\"').replace(/\&\#39\;/g, '\'');
	};
	
	//如果字段的name与当前做级联设置的字段id一样，就不添加进去
	function removeObj(obj,id){
		var external = obj.parent("span").attr("external");
		if(external){
			external=htmlDecode(external).replaceAll("'","\"");
			var queryJson = JSON2.parse(external);
			return queryJson.name==id;
		}else{
			return false;
		}
	}
	
	function showSearch(obj,type){
		if(type){
			if(obj.value=="")
				obj.value="--预设值--";
		}else{
			if(obj.value=="--预设值--")
				obj.value="";
		}
	}
	
</script>
</head>
<body>
	<div id="inputPanel">
		<fieldset class="base">
			<legend>
				<var id="lang_search_setting"></var>
			</legend>
			<table  width="90%">
				<tr>
					<th><var id="lang_choose_search"></var>:</th>
					<td colspan="2">
						<select id="dialog-type" onchange="dialogChange()">
								<option value="0"></option>
						</select>
					</td>
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
				<var id="lang_return_setting"></var>
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
	


</body>
</html>