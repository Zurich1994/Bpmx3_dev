<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/commons/include/form.jsp" %>
<link rel="stylesheet" type="text/css" href="../input.css">
<f:link href="tree/zTreeStyle.css"></f:link>
<script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.dragdiv1.js"></script>
<link rel="stylesheet" href="${ctx}/js/jquery/plugins/jquery.dragdiv.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/js/tree/zTreeStyle.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js"></script>
<script type="text/javascript" src="${ctx}/js/util/json2.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerTab.js"></script>
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
	width:340px;
	height:260px;
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
	<div class="panel-top">
			<a href="#" class="link add" onclick="addToTab(this);">添加子查询</a>
			<input type="text" size="10" class="inputText">
			<div class="l-bar-separator"></div>
			</div>
	</div>
	<div id="inputPanel">
	<div tabid="dialog" title="自定义对话框">
		<fieldset class="base">
			<legend><var id="lang_dialog_setting"></var></legend>
			<table width="90%" style="border: thin;">
				<tr hidden="true">
					<th>权限继承</th>
					<td>
					<select id="permissionExtend">
					</select>
					</td>
					<td>弹出框显示权限将继承该字段的权限</td>
				</tr>
				<tr>
					<th>选择对话框</th>
					<td>
					<select id="dialog-type" onchange="dialogChange()">
						<option value="0"></option>
					</select>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<th>查询条件:</th>
					<td colspan="2">
						<div id="fieldContainer" style="height:60px;">
						<table id="fieldTable"   width="90%">
						</table>
						</div>
					</td>
				</tr>
				
			</table>
		</fieldset>
		<fieldset class="base">
			<legend><var id="lang_return_setting"></var></legend>
				<div class="fields-div">
					<ul id="fields-tree-dialog" class="ztree field-ul"></ul>
				</div>
				<div class="domBtnDiv">
				</div>
		</fieldset>
	</div>
	</div>
	
	<!-- <textarea id="templateTr" style="display:none;">
	<#list data as a>
		<#if (!a.items)>
		<tr class="fieldTr">
			<th>
			<#if (a_index == 0)>主表参数</#if>
			</th>
			<td>
				<input type="checkbox" style="width:10px;" class="fieldCheck" onclick="fieldCheckToggle(this)"/>&nbsp;
				主表字段
				<input type="text" class="mainField" style="width:60px" value="\${a.name}" readonly id="\${a.id}"/>&nbsp;
				<select class="fieldSelect" style="width:95px;display:none;">
					<option></option>
				</select>
			</td>
		</tr>
		<#else>
			<#list a.items as sub>
			<tr class="fieldTr">
				<th><#if (sub_index == 0)>子表参数</#if></th>
				<td>
					<input type="checkbox" style="width:10px;" class="fieldCheck" />&nbsp;
					<input type="text" class="subField" style="width:60px" value="\${sub.name}" readonly id="\${sub.id}"/>&nbsp;
					<select class="fieldSelect" style="width:95px;display:none;">
						<option></option>
					</select>
				</td>
			</tr>
			</#list>
		</#if>
	</#list>
	</textarea> -->
	<textarea id="templateOption" style="display:none;">
	<#list data as o>
		<option value="\${o.field}">\${o.field}</option>
	</#list>
	</textarea>
	<textarea id="subtableTemplateOption" style="display:none;">
	<#list data as o>
		<option value="\${o.tableName}">\${o.tableDesc}</option>
	</#list>
	</textarea>
	<textarea id="queryTemplateTr" style="display:none;">
	<#list data as a>
		<tr class="fieldTr" style="height: 30px;">
			<td >
				<input type="checkbox" style="width:10%;float:left;" class="fieldCheck" onclick="fieldCheckToggle(this)"/>
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
	<div id="inputPanelTemplate" class="hidden">
		<fieldset class="base">
			<legend>
				查询设置
			</legend>
			<table  width="90%">
				 <tr style="height: 30px;">
					<th>联动查询设置:</th>
					<td colspan="2">
					<select id="linkageType" style="width:60px;">
						<option value="0">不联动</option>
						<option value="1">回车联动</option>
						<option value="2">值改变联动</option>
					</select>
					对象选择：
					<select id="linkageField" style="width:30%;">
						<option value="0">--请选择--</option>
					</select>
					</td>
				</tr> 
				<tr style="height: 30px;">
					<th>查询类型:</th>
					<td colspan="2">
						<select id="query-type" class="query-type">
							<option value="0">--请选择--</option>
							<option value="querybtn">自定义查询</option>
							<option value="aliasbtn">别名脚本</option>
						</select>
					</td>
				</tr>
				<tr style="height: 30px;">
					<th>选择查询:</th>
					<td colspan="2">
						<select id="dialog-type" class="dialog-type" onchange="queryDialogChange()">
								<option value="0"></option>
						</select>
					</td>
				</tr>
				
				<tr>
					<th>查询条件:</th>
					<td colspan="2">
						<div id="fieldContainer" style="height:60px;">
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
	<script type="text/javascript">
		var obj = $(editor.curInput);
		var bodyObj=obj.parents('body');
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
		var dragDivCache={};
		var currentTabId = 'dialog';
		var tab;
		var selectOptCache=[];
		
		var isMainTable;
		var currentTableName;
		$(function() {
			// 当前操作表是否为主表，表名字
			 getCurrentTableType();
			
			$('div').delegate('.query-type', 'change', function(){
				var qyerType = $(this).val();
				getDialogByType(qyerType);
			});
			$("#inputPanel").ligerTab({
				height:'570',
				onBeforeSelectTabItem:function(tabid){
					currentTabId=tabid;
				},
				onAfterRemoveTabItem:function(tabid){
					currentTabId=tab.getSelectedTabItemID();
					tab.selectTabItem(currentTabId);
				},
				onAfterAddTabItem:function(tabid){
					var currentDiv = getCurrentTab();
					var divContent = currentDiv.html();
					if(!divContent){
						var content = $('#inputPanelTemplate').clone();
						content.find('#fields-tree').attr('id', 'fields-tree-'+tabid);
						currentDiv.html(content.html());
					}
				}
			});
			
			//获取tab的引用
            tab = $("#inputPanel").ligerGetTabManager();
			
			getDialogs();
		});	
		function fieldCheckToggle(obj){
			$(obj).siblings('select.fieldSelect, div.fielddiv').toggle();
		}
		function getCurrentTab(){
			return $('#inputPanel div[tabid="'+currentTabId+'"]');
		}
		//添加到tab或者刷新
        function addToTab(obj){
			//回显时为直接title，获取则从input中获取
			if($(obj).attr("class")) 
				var text = $(obj).next().val();
			else var text = obj; 
			
			if(text=='')text = null; 
        	tab.addTabItem({text:text});
        };
		//编辑时绑定数据
		function bindData(dialog, tabId) {
			if(!dialog)return;
			var currentDiv = $('#inputPanel div[tabid="'+tabId+'"]');
			var dialogType = dialog.type;
			if(dialogType != 'dialog'){
				$('.query-type', currentDiv).val(dialogType);
				getDialogByType(dialogType);
				var linkage = dialog.linkage;
				if(linkage){
					$('#linkage', currentDiv).attr('checked', 'checked');
					$('#linkage', currentDiv).trigger('click');
				}
			}
			$("#dialog-type", currentDiv).val(dialog.name);
			$("#dialog-type", currentDiv).trigger('change');
			var field;
			while(field=dialog.fields.pop()){
				var src=field.src,
					targets=field.target,target;
				if(targets)
				while(target=targets.pop()){
					var item = $("span.item-span[itemId='"+target+"']", currentDiv).toggleClass("item-span item-span-Disabled");
					if(item.length>0){
						item.each(function(){
							var node = {id:target, name: $(this).text()};
							addNode(src,node);
						});
					}
				}
			}
			var dialogQueryArr = dialog.query;
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
			//{linkType:linkType,linkTarget:linkTarget.val(),isMain:linkTarget.attr("ismain")}
			var linkMessage	= dialog.linkMessage ;
			if(linkMessage){
				$("#linkageType",currentDiv).val(linkMessage.linkType);
				$("#linkageField",currentDiv).val(linkMessage.linkTarget); 
			}
			var subtableName = dialog.subtableName;
			if(dialogType == 'dialog'){
				var isAddRow = dialog.isAddRow;
			}
			
		};
		//添加树节点
		function addNode(id,node){
			var zTree = $.fn.zTree.getZTreeObj('fields-tree-'+currentTabId);
			if(!zTree)return;
			var parentNode = zTree.getNodeByParam("id",id,null);
			if(parentNode) zTree.addNodes(parentNode,node);
		};
		
		//获取自定义查询
		var queryCache = {};
		function getDialogByType(qyerType) {
			var targetObj = getCurrentTab();
			var cache = queryCache[qyerType];
			var dialogType = $("#dialog-type", targetObj);
			dialogType.empty();
			dialogType.append('<option value="0">--请选择--</option>');
			if(cache){
				for(var i=0;i<cache.length;i++){
					dialogType.append(cache[i].clone());
				}
				dialogType.trigger('change');
				var domBtnDiv = dragDivCache[currentTabId];
				if(!domBtnDiv) getFileds();
				return;
			}
			cache = [];
			var url = '';
			if(qyerType == 'querybtn'){
				url = '/platform/bpm/bpmFormQuery/getAllQueries.ht';
			}else if(qyerType == 'aliasbtn'){
				url = '/platform/system/aliasScript/getAllAliasScripts.ht?enable=0&isReturnValue=1&scriptType=default';
			}
			if(!url) return;
			$.ajaxSetup({async:false});  //同步
			$.get(__ctx + url,function(data) {
				if (!data) return;
				if(qyerType == 'querybtn'){
					for ( var i = 0, c; c = data[i++];) {
						var opt = $('<option value="'+c.alias+'" fields="'+c.returnFields
								+'" istable="'+c.istable+'" objname="'+c.objname+'" dsname="'+c.dsalias+'" >'+ c.name + '</option>');
						opt.attr("resultfield", c.resultfield.replaceAll('\"', "'"));
						opt.attr("conditionfield",c.conditionfield.replaceAll('\"', "'"));
						dialogType.append(opt);
						cache.push(opt);
					}
				}else if(qyerType == 'aliasbtn'){
					for ( var i = 0, c; c = data[i++];) {
						var opt = $('<option value="'+c.aliasName+'" fields="'+c.returnFields
								+'">'+ c.aliasDesc + '</option>');
						var argument = c.argument;
						if(!argument) argument = '{}';
						var returnParamJson = c.returnParamJson;
						if(!returnParamJson) returnParamJson = '{}';
						opt.attr("conditionfield", argument.replaceAll('\"', "'"));
						opt.attr("resultfield",returnParamJson.replaceAll('\"', "'"));
						dialogType.append(opt);
						cache.push(opt);
					}
				}
				getFileds(editor.tableId);
				queryCache[qyerType] = cache;
			});
			$.ajaxSetup({async:true}); //异步
		};
		
		//获取自定义对话框 //增加获取树形对话框
		function getDialogs(){
			var url = __ctx + '/platform/form/bpmFormDialog/getAllDialogs.ht';
			$.get(url,function(data){
				if (data) {
					var currentDiv = getCurrentTab();
					for(var i=0,c;c=data[i++];){
						var opt = $('<option value="'+c.alias+'" fields="'+c.returnFields
								+'" istable="'+c.istable+'" objname="'+c.objname+'" dsname="'+c.dsalias+'" >'+c.name+'</option>');
						opt.data("fields",c.resultfield); 
						opt.data("conditionfield",c.conditionfield);
						$("#dialog-type", currentDiv).append(opt);
					}
				}
			});
			//组合对话框
			var combiDialogUrl = __ctx + '/platform/form/bpmFormDialogCombinate/getAllCombDialog.ht';
			$.get(combiDialogUrl,function(data){ 
				if (data) {
					var currentDiv = getCurrentTab();
					for(var i=0,c;c=data[i++];){
						var opt = $('<option value="'+c.alias+'" type="combi" '
								+'" istable="'+c.listDialog.istable+'" objname="'+c.listDialog.objname+'" dsname="'+c.listDialog.dsalias+'" >'+c.name+'</option>');
						opt.data("fields",c.listDialog.resultfield);
						opt.data("conditionfield",c.listDialog.conditionfield);
						$("#dialog-type", currentDiv).append(opt);
					}
					getFileds(editor.tableId);
				}
			});
		};		
		//选择不同的对话框
		function dialogChange(){
			var currentDiv = getCurrentTab();
			var dia=$("#dialog-type", currentDiv).find("option:selected");
			var v = dia.data("fields");
			var c = dia.data("conditionfield");

			if(v){
				var nodes=[];
				var fields = $.parseJSON(v);
				for(var i=0;i<fields.length;i++){
					var f=fields[i];
					nodes.push({id:f.field,pid:0,name:f.comment,isParent: true, open:true});
				}
				bindFieldsTree(setting, nodes);
			}
			if(c){
				var conditionfield = $.parseJSON(c);
				//生成查询参数设置框
				changeConditionParam(conditionfield,currentDiv);
			}
			
		}
		
		function bindFieldsTree(setting, nodes){
			var currentDiv = getCurrentTab();
			$("span.item-span-Disabled", currentDiv).each(function(){
				$(this).toggleClass("item-span-Disabled");
				$(this).toggleClass("item-span");
			});
			var tree = $.fn.zTree.init($("#fields-tree-"+currentTabId, currentDiv), setting, nodes);
			//设置拖拽 树对象
			var dragDiv = dragDivCache[currentTabId];
			if(dragDiv)dragDiv.dragdiv('bind','fields-tree-'+currentTabId);
		}
		//选择不同的查询
		function queryDialogChange(){
			var currentDiv = getCurrentTab();
			var condition_field = $("#condition-field", currentDiv);
			condition_field.empty();
			var dia = $("#dialog-type", currentDiv).find(":selected");
			var v = dia.attr("resultfield");
			var condStr = dia.attr("conditionfield");
			var type = $("#query-type", currentDiv).val();
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
				
				bindFieldsTree(setting, nodes);
			}
			
			if (condStr) {
				var jsonObj = eval("("+condStr+")");
				changeConditionParam(jsonObj,currentDiv,type);
			}
		}
		//根据参数绑定字段 
		function changeConditionParam(jsonObj,currentDiv,type){
			if (jsonObj) {
				$('#fieldTable', currentDiv).empty();
				var conditionfield= [];
				for(var i=0,obj;obj=jsonObj[i++];){
					if(obj.defaultType=="1" || obj.defaultType=="4"){
						conditionfield.push(obj);
					}else if(type == 'aliasbtn'){
						obj.field = obj.paraName;
						conditionfield.push(obj);
					}
				}
				//对于值是输入框的，生成“绑定参数”下拉框选项
				if(typeof(conditionfield)!="undefined" && conditionfield!=""){
					var template = $('textarea#queryTemplateTr').val();
					var templateTrHtml = easyTemplate(template,conditionfield).toString();
					$('#fieldTable', currentDiv).append(templateTrHtml);
					if(selectOptCache.length!=0){
						var fieldSelect = $('td .fieldSelect, #linkageField', currentDiv);
						fieldSelect.empty();
						for(var j=0;j<selectOptCache.length;j++){
							fieldSelect.append(selectOptCache[j]);
						}
					}
				}
			}
			
			
		}

		dialog.onok = function() {
			//循环构建TAB页数据
			var jsonArr = [];
			$('div[tabid]').each(function(){
				jsonArr = buildJsonResult(this, jsonArr);
			});
			$(editor.curInput).removeAttr("eventBtn");
			if(jsonArr.length<1) return;
			var json = JSON2.stringify(jsonArr).replaceAll("\"","'") ;
			editor.curInput.setAttribute("eventBtn",json);
			//设置该弹出框权限继承自那个字段
			var permissionExtend = $("#permissionExtend").val();
			if(permissionExtend){
				//主表绑定主表权限，后台处理
				//子表前端js 控制
				if(isMainTable) editor.curInput.setAttribute("name",permissionExtend);
				else editor.curInput.setAttribute("permissionName",permissionExtend);
			}
				
			editor.curInput = null;
		};
		function buildJsonResult(obj, jsonArr){
			var currentTab = $(obj);
			var tabId = currentTab.attr('tabid');
			var tabName = $($("li[tabid="+tabId+"]").children()[0]).text();
			
			var dialogType=$("#dialog-type", currentTab).val();
			if(!dialogType || dialogType == '0') return jsonArr;
			
			var zTree = $.fn.zTree.getZTreeObj("fields-tree-"+tabId);
			var nodes=zTree.getNodes(),fields=[];
			
			for(var i=0,c;c=nodes[i++];){
				if(!c.children)continue;
				var target=[],child=null, sub = {};
				while(child=c.children.pop()){
					target.push(child.id);
				}
				if(target.length>0){
					sub.src=c.id;
					sub.target=target;
				}
				fields.push(sub);
			}
			var queryArr = [] ;
			$('table .fieldTr :checkbox:checked',currentTab).each(function(){
				var queryObj = {} ;
				
				//获取查询参数
				queryObj.condition = $(this).siblings(':text').attr('id');
				var selectobj=$(this).siblings('div.fielddiv').children('select').find(":selected");
				queryObj.trigger = selectobj.val();
				queryObj.controlType = selectobj.attr('controlType');
				queryObj.isMain = selectobj.attr('isMain');
				
				var initvalue=$(this).siblings('div.fielddiv').children(':text').val();
				if(initvalue=="--预设值--"){
					initvalue="";
				}
				queryObj.initValue=initvalue;
				queryArr.push(queryObj);
			});
			var resultData = {'name': dialogType, 'fields':fields, 'query':queryArr,tabName:tabName};
			
			//如果是子表内按钮，不可以添加行
			var isAddRow = isMainTable; //$('#addSubTableRow', currentTab).is(':checked');
			resultData.isAddRow = isAddRow;
			
			if('dialog' == tabId){
				resultData.type = 'dialog';
				//如果为组合对话框，则修改类型
				var queryType = $("#dialog-type option:selected'",currentTab).attr("type");
				if("combi" ==queryType) resultData.type = "combiDialog"; 
			}else {
				resultData.type = $("#query-type", currentTab).val();
				//添加联动设置
				var linkType = $("#linkageType",currentTab).val();
				var linkTarget = $("#linkageField",currentTab);
				if(linkTarget)
				 var isMain =  linkTarget.find("option:selected").attr("ismain");
				resultData.linkMessage = {linkType:linkType,linkTarget:linkTarget.val(),isMain:isMain};
			}
			jsonArr.push(resultData);
			return jsonArr;
		}
		// eventBtn数组缓存
		var eventBtnCache = [];
		//初始化字段面板
		function initFieldsDiv(data){
			var mainTable = data.table, data = {};
			data.name = mainTable.tableDesc + '('+editor.getLang("customdialog.main")+')';
			data.id = mainTable.tableName;
			data.desc = mainTable.tableId;
			var formMainField=mainTable.tableName?$(bodyObj).find('[name*="'+mainTable.tableName+'"]'):[];
			
			var items = [];
			for ( var i = 0, c; c = mainTable.fieldList[i++];) {
				//if(c.isHidden!=0) continue ;
				
				//判断当前字段是否在表单上显示，如果存在这显示，
				if(formMainField.length != 0 && removeObj(formMainField,c.fieldName))continue;
				var itemObj = {name : c.fieldDesc,id : c.fieldName};
				var type=c.controlType;
				if(typeof type != 'undefined' && type != '') itemObj.ctype = type;
				items.push(itemObj);
			}
			var currentDiv = getCurrentTab();
			var selectOpt = [];
			//并且有字段信息，并且selectOptCache缓存还不存在，则产生缓存    ----  取消 当前不是自定义对话框模式，
			if(items && selectOptCache.length<=0){
				var opt = '<optgroup label="主表字段"></optgroup>';
				selectOpt.push(opt);
				for(var i=0, c;c=items[i++];){
					 opt='<option value="'+c.id+'"  controlType="'+c.ctype+'" ismain=true >' + c.name+ '</option>';
					 selectOpt.push(opt);
				}
			}
			if(!isMainTable) items=[];
				
			var allSubItems = [];
			for ( var i = 0, c; c = mainTable.subTableList[i++];) {
				//如果当前按钮为子表，去除其他子表 
				if(!isMainTable && c.tableName != currentTableName) continue;
				var sub = {};
				sub.name = c.tableDesc + '('+editor.getLang("customdialog.sub")+')';
				sub.id = c.tableName;
				sub.desc = c.tableId;
				var currSubField=$(bodyObj).find('[name*="'+c.tableName+'"]');
				var subItems = [];
				for ( var y = 0, t; t = c.fieldList[y++];) {
					//判断当前字段是否在表单上显示，如果存在这显示，不存在这不显示
					if(formMainField.length != 0 && removeObj(currSubField,t.fieldName))continue;
					var itemObj = {name : t.fieldDesc,id : t.fieldName,tableName: c.tableName};
					var type=t.controlType;
					if(typeof type != 'undefined' && type != '') itemObj.ctype = type;
					subItems.push(itemObj);
				}
				sub.items = subItems;
				items.push(sub);
				allSubItems = allSubItems.concat(subItems);
			}
			
			var dialogType = $('#dialog-type', currentDiv).val();
			
			var parentTableClass = $(editor.curInput).closest('div[type="subtable"]');
			if((!parentTableClass || parentTableClass.length<=0) && currentTabId == 'dialog'){
				//若为空，表示主表，则隐藏子表字段
				$('.subField', currentDiv).each(function(){
					$(this).closest('.fieldTr').hide() ;
				})
			}
			else if(!isMainTable || parentTableClass.length>0 && selectOptCache.length<=0){
				opt = '<optgroup label="子表字段"></optgroup>';
				selectOpt.push(opt);
				for(var i=0, c;c=allSubItems[i++];){
					//如果是子表情况，将其他子表数据排除
					if(c.tableName == currentTableName){
					 opt='<option value="'+c.id+'" controlType="'+c.ctype+'" ismain=false >' + c.name+ '</option>';
					 selectOpt.push(opt);
					}
				}
			}
			if(selectOpt.length>0) {
				selectOptCache = selectOpt;
			
				//联动框 
				var linkAgeSelect = $("#linkageField",currentDiv);
				if(linkAgeSelect)
				for(var j=0;j<selectOptCache.length;j++){
					linkAgeSelect.append(selectOptCache[j]);
				}
					
				//弹出框权限追随框 
				var permissionExtend = $("#permissionExtend",currentDiv);
				if(permissionExtend){
					$(permissionExtend).parent().parent()[0].hidden = false;
					for(var j=0;j<selectOptCache.length;j++){
						var isMain = $(selectOptCache[j]).attr("ismain");
						//子表显示子表字段，主表显示主表字段
						if(!isMain||(isMainTable && "true" == isMain)||
								(!isMainTable && "false" == isMain)){
							permissionExtend.append(selectOptCache[j]);
						}
					}
				}
				var permissionName="";
				if(isMainTable)permissionName = obj.attr("name");
				else permissionName = obj.attr("permissionName");
				$(permissionExtend).val(permissionName);
			}
			
			data.items = items;
			//初始化字段面板
			var dragDiv = $(".domBtnDiv", currentDiv).dragdiv('init',{data : data});
			dragDivCache[currentTabId] = dragDiv;
			if(eventBtnCache.length<1){// 编辑时第一次循环
				var dialogStr = $(editor.curInput).get(0).getAttribute('eventBtn');
				if (dialogStr) {
					var jsonArr = eval("("+dialogStr+")");
					if(!jsonArr) return;
					eventBtnCache = jsonArr;
					for(var i=0;i<jsonArr.length;i++){
						var jsonObj = jsonArr[i];
						var dialogType = jsonObj.type;
						var tabId = 'dialog';
						if(dialogType != 'dialog'){
							addToTab(jsonObj.tabName);
							tabId = currentTabId;
						}
						bindData(jsonObj, tabId);
					}
				}
			}
		};

		//加载字段面板
		var filedDataCache;
		function getFileds(tableId) {
			if(filedDataCache){
				initFieldsDiv(filedDataCache);
				return;
			}
			if(tableId){
				var url = __ctx
						+ '/platform/form/bpmFormTable/getTableById.ht?tableId='
						+ tableId;
				$.post(url, function(data) {
					filedDataCache = data;
					initFieldsDiv(data);
				});
			}
			else{	//编辑器设计表单时获取字段并验证字段
				var html = editor.getContent();		
				var params={};
				params.html=html;
				params.formDefId=editor.formDefId;
				
				
				$.post(__ctx + '/platform/form/bpmFormDef/validDesign.ht', params, function(data){
					if(data.valid){
						filedDataCache = data;
						initFieldsDiv(data);
					}
					else{
						alert(data.errorMsg);
					}
				});
			}
		};
		
		//判断当前字段是否在表单上显示，如果存在这显示，不存在这不显示
		function removeObj(obj,fieldName){
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
		};
		//获取当前操作对象
		function getCurrentTableType(){
			var tbody = obj.parents('tbody'); 
			if(!tbody) return;
			var input = $('input',tbody)[0];
			if(!input) return;
			var name = input.name.split(":");
			if(name.length < 2){
				alert("尚未生成物理表，暂不支持。");
				isMainTable = true; return; 
			}
			isMainTable = name[0] == 'm';
			currentTableName = name[1];
		} 
		
	</script>
</body>
</html>