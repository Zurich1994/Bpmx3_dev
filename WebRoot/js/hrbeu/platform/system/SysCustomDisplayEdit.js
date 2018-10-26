var validateData=null;

/**
 * 初始化第一步的页面
 * @param isAdd
 * @returns
 */
function initPage(isAdd){
	initFirstStepLayout();
	initFirstStepTablesTree();
	if(!isAdd){
		$("a.sbutton").hide();
	}
	$("#first-next").click(function(){
		if(scriptEditor){
			scriptEditor.save();
		}
		var resultMsg = validateFirstStepSetting(isAdd);
		if(resultMsg.status){
			if(resultMsg.message){
				$.ligerDialog.error(resultMsg.message);
			}
			return;
		}
		if(!isAdd){
			initSecondStepUpdate();
		}else{
			initSecondStepAdd();
		}
	});
	
	$("#second-prev").click(function(){
		$(".step-first").show();
		$(".step-second").hide();
	});	
}

/**
*第一步页面的布局
*/
function initFirstStepLayout(){
	$("#firstLayout").ligerLayout( {
		leftWidth : 350,
		height: 500,
		onHeightChanged: heightChanged,
		allowLeftResize:false
	});
}

/**
*加载表/视图 树
*@return void
*/
function initFirstStepTablesTree(){
	var dsName=$("#dsName").val();
	if(!dsName){
		return;
	}
	var setting = {
		async: {enable: false},
		data: {
			key:{name:"name"},
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pid",
				rootPId: "0"
			}
		},
		callback:{
			onDblClick: zTreeOnDblClick
		}
	};
	var url=__ctx+"/platform/system/jdbcDb/getTables.ht?dsName="+dsName;
	url=url.getNewUrl();
	$.ligerDialog.waitting('正在加载数据，请等待...');
	$.post(url,{},function(data){
		var ztData =transDataToZtreeFormat(data);
		tablesTree=$.fn.zTree.init($("#tablesTree"), setting,ztData);
		//tablesTree.expandAll(true);
		$.ligerDialog.closeWaitting();
	});
}

/**
 * 第一步页面
 * @returns void
 */
function initFirstStep(isAdd){
	$("#first-next").click(function(){
		if(!isAdd){
			initStepSecondUpdate();
		}else{
			initStepSecondAdd();
		}
	});
}

/**
 * Validate first step setting
 * @param isAdd
 * @returns object:{status,message}
 * 			status:error code,non-zero	error occurred
 * 			message:error message.
 */
function validateFirstStepSetting(isAdd){
	var resultMsg={
		status:0,
		message:""
	};
	var frm=$('#sysCustomDisplayForm').form();
	if(!frm.handValidResult("#name")){
		resultMsg.status=1;
	}
	if(isAdd){
		if(!frm.handValidResult("#script")){
			resultMsg.status=2;
		}
		if($("[name='dsName']").val()==""){
			resultMsg.status=3;
			resultMsg.message="请选择数据源！";
			return resultMsg;
		}
		validateData = validateScript();
		if(!validateData.status){
			resultMsg.status=4;
			resultMsg.message=validateData.resultMsg;
			return resultMsg;
		}
		if(!validateData.supportSubSelect){
			resultMsg.status=5;
			resultMsg.message="脚本执行的结果必须能够作为子查询！";
			return resultMsg;
		}	
	}
	return resultMsg;
}

/**
 * Validate second step setting
 * @param isAdd
 * @returns object:{status,message}
 * 			status:error code,non-zero	error occurred
 * 			message:error message.
 */
function validateSecondStepSetting(isAdd){
	var resultMsg = {
		status : 0,
		message : ""
	};
	var template = $("#template").val();
	if (!template) {
		resultMsg.status = 1;
		resultMsg.message = "请设置模板！";
		return resultMsg;
	}
	var setting = $("#setting").val();
	if (!setting) {
		resultMsg.status = 2;
		resultMsg.message = "请设置模板参数！";
		return resultMsg;
	}
	return resultMsg;
		
}

/**初始化第二步页面----更新
 * @returns
 */
function initSecondStepUpdate(){
	//initial selected columns
	var fields = $("#fieldSetting").val();
	if(!fields){
		fields=$("#fields").val();
	}
	initColumns($.parseJSON(fields));
	//initial condition setting
	var conditions = $("#conditionField").val();
	initConditions($.parseJSON(conditions));
	
	$(".step-first").hide();
	$(".step-second").show();
}
/**
 * 始化第二步页面----添加
 */
function initSecondStepAdd(){
	var fields=validateData.sqlFields;
	//initial selected columns
	initColumns(fields);
	initConditions([]);
	
	$(".step-first").hide();
	$(".step-second").show();
	$("#fields").val(JSON2.stringify(fields));
};


/**
 *验证Script的有效性
 */
function validateScript(){
	var dsName=$("[name='dsName']").val();
	var script=$("[name='script']").val();
	var url=__ctx+ "/platform/system/sysCustomDisplay/validateScript.ht";
	var params={
		dsName:dsName,
		script:script
	};
	var rtn;
	$.ajax({
		url:url,
		data:params,
		async:false
	}).done(function(data){
		rtn=data;
	});
	return rtn;
}

/**
*更改模板
*/
function templateChangeHandler(obj){
	$("#conditionField").val('');
	$("#setting").val('');
}
   
/**
*设置模板参数
*/
function settingTemplate(){
    var file=$("#template").val();
    if(!file){
    	$.ligerDialog.error("请先选择一个模板！");
    	return;
    }
    
    var settings = getAllSetting();
    $("#fieldSetting").val(JSON2.stringify(settings.fieldSetting));
    $("#conditionField").val(JSON2.stringify(settings.conditionField));
	var setting = $.parseJSON($("#setting").val());
	var fields = settings.fieldSetting;
	
	if(!fields){
		fields=$.parseJSON($("#fields").val());
	}
	var params={
		fields:fields,
		setting:setting
	};
	openSettingTemplateDialog(params)
	
}
	
/**
*打开设置模板对话框
*/
function openSettingTemplateDialog(params){
	var setting = $("#template").find("option:selected").attr("setting");
	var winArgs="dialogWidth=900px;dialogHeight=600px;help=0;status=0;scroll=0;center=1";
	var url=__ctx+setting;
	url=url.getNewUrl();
	var rtn = window.showModalDialog(url,params,winArgs);
	
	if(rtn){
// 	$("#fieldSetting").val(JSON2.stringify(rtn.fieldSetting));
	$("#setting").val(JSON2.stringify(rtn.setting));
	}
}
   
	/*
 *自定义外部表单并提交
 */
function customFormSubmit(options){
	var id=$("#id").val();
	var name=$("#name").val();
	var script=$("#script").val();
	var scriptType=$("#scirptType").val();
	var description=$("#description").val();
	var fieldSetting=$("#fieldSetting").val();
	var setting=$("#setting").val();
	var conditionField=$("#conditionField").val();
	var dsName=$("#dsName").val();
	var dsAlias=$("#dsAlias").val();
	var template=$("#template").val();
// 			var dspTemplate=$("#dspTemplate").val();
	var paged=$("input[name='paged']:checked").val();
	var pageSize=$("#pageSize").val();
	var json={
		id:id,
		name:name,
		description:description,
		script:script,
		scriptType:scriptType,
		setting:setting ,
		fieldSetting:fieldSetting,
		conditionField:conditionField ,
		dsName:dsName ,
		dsAlias:dsAlias,
		template:template,
// 				dspTemplate:dspTemplate,
		paged:paged,
		pageSize:pageSize
	};
	
	var form = $('<form method="post" action="save.ht"></form>');
	var input = $("<input type='hidden' name='json'/>");
	input.val(JSON2.stringify(json));
	form.append(input);
	form.ajaxForm(options);
	form.submit();
}

/**布局大小改变的时候通知tab，面板改变大小
 * @param options
 * @returns
 */
function heightChanged(options){
 	$("#tablesTree").height(options.middleHeight -90);
};
/**
*TablesTree 树节点双击元事件
*/
function zTreeOnDblClick(event, treeId, treeNode) {
    if(treeNode.id=='table'||treeNode.id=='view'||treeNode.id=='0'){
    	return;
    }
    if(treeNode.isParent){
    	return;
    }
    var text=treeNode.name;
    var pos=scriptEditor.getCursor();
	scriptEditor.replaceRange(text,pos);
//    var textArea=document.getElementById("script");
//    $.insertText(textArea,treeNode.name);
};

/**
*将从__ctx+"/platform/system/jdbcDb/getTables.ht返回的数据转换成ztree SimpleData接受的数据格式
*/
function transDataToZtreeFormat(data){
	//节点的Type字段: 1-表父节点;2-视图的父节点；3-表；4-视图；5-表列；6-视图列
	var tables = data.tables;
	var views=data.views;
	var ztData=[
		{name:"数据表",id:"table",pid:"0",type:1},
		{name:"视图",id:"view",pid:"0",type:2}
	];
	for(var i=0;i<tables.length;i++){
		var table=tables[i];
		var tableObj={
			id:table.name,
			pid:"table",
			name:table.name,
			comment:table.comment,
			type:3
		};
		ztData.push(tableObj);
		var columns=table.columnList;
		for(var j=0;j<columns.length;j++){
			var column=columns[j];
    		var columnObj={
    			id:column.tableName+"."+column.name,
    			pid:column.tableName,
    			name:column.name,
    			comment:column.comment,
    			type:5
    		};
    		ztData.push(columnObj);
		}
	}
	
	for(var i=0;i<views.length;i++){
		var view=views[i];
		var viewObj={
			id:view.name,
			pid:"view",
			name:view.name,
			comment:view.comment,
			type:4
		};
		ztData.push(viewObj);
		var columns=view.columnList;
		for(var j=0;j<columns.length;j++){
			var column=columns[j];
    		var columnObj={
    			id:column.tableName+"."+column.name,
    			pid:column.tableName,
    			name:column.name,
    			comment:column.comment,
    			type:5
    		};
    		ztData.push(columnObj);
		}
	}
	return ztData;
}

/**
*更改数据源响应事件
*/
function changeDataSource(){
	initFirstStepTablesTree();
}

/**
*树节点选择操作：选择
*/
function treeSelect(type){
	if(!tablesTree){
		return;
	}
	var nodes = tablesTree.getSelectedNodes();
	var text="";
	
	switch(type){
   		case 1:
			for(var i=0;i<nodes.length;i++){
				//Skip None Table ,None Column Nodes
				if(nodes[i].id=='table'||nodes[i].id=='view'||nodes[i].id=='0'){
					continue;
				}
				text+=" ,"+nodes[i].name;
			}
			if(text){
				text=text.substring(2);
//				$.insertText(document.getElementById("script"),text);
				var pos=scriptEditor.getCursor();
				scriptEditor.replaceRange(text,pos);
			}
			break;
   		case 2:
   			var selectItems=[];
   			var fromItems=[];
   			for(var k=0;k<nodes.length;k++){
   				var node = nodes[k]
				//Skip None Table ,None Column Nodes
				if(node.id=='table'||node.id=='view'||node.id=='0'){
					continue;
				}
				//get fromItems
			
				if(node.type==3||node.type==4){
					fromItems.push(node.name);
					var children = node.children;
					//if one of table/view's columns, skip;else,push all table/view's columns to selectItem
					var flag2=false;
					for(var i=0;i<children.length;i++){
						var childId=children[i].id;
						var flag=false;
						for(var j=0;j<nodes.length;j++){
							if(nodes[j].id==childId){
								flag=true;
								break;
							}
						}
						if(flag==true){
							flag2=true;
							break;
						}
					}
					if(flag2==false){
						for(var i=0;i<children.length;i++){
							selectItems.push(children[i].name);									
						}
					}
					continue;
				}
				//get selectItems
				if(node.type==5||node.type==6){
					selectItems.push(node.name);
					var parent = node.getParentNode();
					var flag = false;
					for(var j=0;j<nodes.length;j++){
						if(nodes[j].id==parent.id){
							flag=true;
							break;
						}
					}
					if(flag==false){
						fromItems.push(parent.name);
					}
					continue;
				}
			}
   			
   			//insert text into script textarea
   			text="SELECT\n\t";
   			var select =selectItems.join(",\n\t")||"<选择的列>\n\t";
   			text+=select;

   			text+="\nFROM\n\t";
   			var from =fromItems.join(",\n\t")||"<选择的表>\n\t";
   			text+=from;
   			text+="\nWHERE\n\t<条件>";
//   			$.insertText(document.getElementById("script"),text);
   			var pos=scriptEditor.getCursor();
			scriptEditor.replaceRange(text,pos);
   			break;
	}
}

/**
 *初始化数据列的表
 */
function initColumns(fields){
	var columnsTbl = $("#columnsTbl tbody").html("");
	for(var i=0;i<fields.length;i++){
		var field=fields[i];
		var tr = constructColumnTr(field);
		columnsTbl.append(tr);
	}
}

/**
*初始化条件设置
*/
function initConditions(confitionFields){
	if(!confitionFields){
		return false;
	}
	$("#conditionTbl tbody").html("");
	for(var i=0;i<confitionFields.length;i++){
		var field=confitionFields[i];
		var joinType=field.joinType;
		var name = field.name;
		var qualifiedName = field.qualifiedName;
		var type=field.type;
		var comment=field.comment;
		var operate=field.cond;
		var value=field.value;
		var valueFrom=field.valueFrom;
		
		var condition={
			joinType:joinType,
			name:name,
			qualifiedName:qualifiedName,
			type:type,
			comment:comment,
			operate:operate,
			value:value,
			valueFrom:valueFrom
		};						
		var tr = constructConditionTr(condition);
		$("#conditionTbl tbody").append(tr);
	}			
}

/*
 *构造SQL语句选择的列的<tr>///////////////
 */
function constructColumnTr(field){
	var index = field.index;
	var type = field.type;
	var name = field.name;
	var qualifiedName=field.qualifiedName;
	var label=field.label;
	var comment = field.comment;
	if(!comment){
		comment=label;
	}
// 			var display=false;
// 			if(typeof(field.display)!='undefined'){
// 				display = field.display;
// 			}
	//first td checkbox
	var $tdChk = $("<td></td>");
	var $inputChk = $("<input></input>")
	$inputChk.attr("name","fieldCheckBox");
	$inputChk.attr("type","checkbox");
	$tdChk.append($inputChk);
	
	$inputHidden=$("<input type='hidden'/>")
	$inputHidden.val(JSON2.stringify(field));
	$tdChk.append($inputHidden);
	//second td field name
	var $tdName = $("<td></td>");
	$tdName.append(name);
	//third td field comment
	var $tdComment = $("<td style='width:80px;'></td>");
	var $inputComment=$("<input style='width:80px;'></input>");
	$inputComment.attr("name","fieldComment");
	$inputComment.val(comment);
	$tdComment.append($inputComment);
	//four td column select checkedbox
// 			var $tdSelect = $("<td></td>");
// 			var $checkbox=$("<input></input>");
// 			$checkbox.attr("name","fieldDisplay");
// 			$checkbox.attr("type","checkbox");
// 			$checkbox.attr("fieldName",name);
// 			$tdSelect.append($checkbox);
	//Tr
	var $tr=$("<tr></tr>");
	$tr.append($tdChk);
	$tr.append($tdName);
	$tr.append($tdComment);
// 			$tr.append($tdSelect);
	return $tr;
}
 
 
 
/**
 *绑定选择条件点击事件
 */
function selectCondition(){
	$("#columnsTbl input:[name='fieldCheckBox']:checked").each(function(){
		var field=$.parseJSON($(this).closest("tr").find("input[type='hidden']").val());
		var $fieldComment = $(this).closest("tr").find("input[name='fieldComment']");
		var name = field.name;
		var type = field.type;
		var label = field.label;
		var index = field.index;
		var qualifiedName=field.qualifiedName;
		var comment = $fieldComment.val();
//					var qualifiedName = $fieldComment.attr("fieldQualifiedName");
		var condition={
			joinType:"AND",
			name:name,
			qualifiedName:qualifiedName,
			label:label,
			type:type,
			operate:'=',
			comment:comment,
			value:"",
			valueFrom:1
		};
		var tr = constructConditionTr(condition);
		$("#conditionTbl tbody").append(tr);
	});
};

/*
 *构造条件列的<tr>
 */

function constructConditionTr(condition) {
	var joinType = condition.joinType;
	var type = condition.type;
	var name = condition.name;
	var qualifiedName = condition.qualifiedName;
	var label = condition.lebel;
	var comment = condition.comment;
	var cond = condition.operate;
	var value = condition.value;
	var valueFrom = condition.valueFrom;
	var constructOption = function(cond1, cond2, text) {
		if (!text) {
			text = cond2;
		}
		var $option = $("<option></option");
		$option.text(text);
		$option.val(cond2);
		if (cond1 == cond2) {
			$option.attr("selected", "selected");
		}
		return $option;
	};
	//联合类型
	var $tdJoinType = $("<td></td>");
	var $selectJoinType = $("<select name='conditionJoinType'></select>");
	$selectJoinType.append(constructOption(joinType, "AND"));
	$selectJoinType.append(constructOption(joinType, "OR"));
	$tdJoinType.append($selectJoinType);

	$hiddenInput = $("<input type='hidden'/>");
	$hiddenInput.val(JSON2.stringify(condition));
	$tdJoinType.append($hiddenInput);

	//名称
	var $tdName = $("<td></td>");
	$tdName.append(name);
	//条件
	var $tdCond = $("<td></td>");
	var $select = $("<select name='conditionOperate'></select>");
	$select.css("width", "100%");
	switch (type) {
	case 'varchar':
		$select.append(constructOption(cond, "="));
		$select.append(constructOption(cond, "likeEnd"));
		$select.append(constructOption(cond, "like"));
		break;
	case 'number':
		$select.append(constructOption(cond, "="));
		$select.append(constructOption(cond, ">"));
		$select.append(constructOption(cond, "<"));
		$select.append(constructOption(cond, ">="));
		$select.append(constructOption(cond, "<="));
		break;
	case 'int':
		$select.append(constructOption(cond, "="));
		$select.append(constructOption(cond, ">"));
		$select.append(constructOption(cond, "<"));
		$select.append(constructOption(cond, ">="));
		$select.append(constructOption(cond, "<="));
		break;
	default:
		$select.append(constructOption(cond, "="));
		$select.append(constructOption(cond, ">="));
		$select.append(constructOption(cond, "<="));
		$select.append(constructOption(cond, "between"));
	}
	$tdCond.append($select);
	//注解
	var $tdComment = $("<td style='width:80px;'></td>");
	var $inputComment = $("<input style='width:80px;' name='conditionComment' type='text'/>");
	$inputComment.val(comment);
	$tdComment.append($inputComment);

	//值来源
	var $tdValueFrom = $("<td></td>");
	var $valueFrom = $("<select name='conditionValueFrom' onchange='selectValueFrom(this)'></select>");
	$valueFrom.append(constructOption(valueFrom, 1, "输入"));
	$valueFrom.append(constructOption(valueFrom, 2, "固定值"));
	$valueFrom.append(constructOption(valueFrom, 3, "脚本"));
	$tdValueFrom.append($valueFrom);
	//值
	var $tdValue = $("<td style='width:150px;'></td>");
	$a = $("<a style='display:none;' href='#' name='btnScript' class='link var' title='常用脚本' onclick='selectScript(this)'>常用脚本</a>");
	var $value;
	if (valueFrom == 1) {
		$value = $("<input class='hide' name='conditionValue' type='text'/>");
		$value.attr("readonly", "readonly")
	} else if (valueFrom == 2) {
		$value = $("<input style='width:150px;' name='conditionValue' type='text'/>");
	} else {
		$a.show();
		$value = $("<textarea style='width:150px;' name='conditionValue'></textarea>");
	}
	$value.val(value);
	$tdValue.append($a);
	$tdValue.append($value);
	//管理
	var $tdManage = $("<td></td>");
	var $aDelete = $("<a onclick='delConditionTr(this)'>删除</a>");
	$aDelete.attr("href", "#");
	$aDelete.addClass("link del");
	$tdManage.append($aDelete);

	$tr = $("<tr></tr>");
	$tr.append($tdJoinType);
	$tr.append($tdName);
	$tr.append($tdCond);
	$tr.append($tdComment);
	$tr.append($tdValueFrom);
	$tr.append($tdValue);
	$tr.append($tdManage);
	return $tr;
}
 
 
/**删除选择的条件所有的表格的行
 * @param obj
 * @returns
 */
function delConditionTr(obj){
	$(obj).closest("tr").remove();
}
/**
 * 取得参数设置
 */
function getAllSetting(){
	var settings={
			fieldSetting:new Array(),
			conditionField:new Array()
	};
	//get field setting
	var fields = getFieldSetting();
	settings.fieldSetting=fields;
	//取条件字段
	var conditions=getConditionSetting();
	settings.conditionField=conditions;
	return settings;
}

/**
*获取Select 字段的设置
*/
function getFieldSetting(){
	var setting=[];
	$("#columnsTbl input:[type='hidden']").each(function(){
		$this=$(this);
		var fieldSetting=$.parseJSON($this.val());
		var comment = $this.closest("tr").find("input[name='fieldComment']").val();
		fieldSetting.comment=comment;
		setting.push(fieldSetting);
	});
	return setting;
}

/**
*获取条件 字段设置
*/
function getConditionSetting(){
	var setting=[];
	$("#conditionTbl input[type='hidden']").each(function(){
		var $this=$(this);
		var $tr=$this.closest('tr');
		var field=$.parseJSON($this.val());
		var joinType=$tr.find("select:[name='conditionJoinType']").val();
		var operate =$tr.find("select:[name='conditionOperate']").val();
		var valueFrom =parseInt($tr.find("select:[name='conditionValueFrom']").val());
		var value =$tr.find("[name='conditionValue']").val();
		var comment =$tr.find("input:[name='conditionComment']").val();
		field.joinType=joinType;
		field.operate=operate;
		field.valueFrom=valueFrom;
		field.value=value;
		field.comment=comment;
		field.valueFrom=valueFrom;
		setting.push(field);
	});
	return setting;
}

/**
*值来源更改事件处理
*/
function selectValueFrom(obj){
	var $tr = $(obj).closest("tr");
	var $a = $tr.find("a:[name='btnScript']");
	$a.hide();
	var valueFrom = $(obj).val();
	var $value;
	if(valueFrom==1){
		$value=$("<input class='hide' type='text'/>");
		$value.attr("readonly","readonly")
	}else if(valueFrom==2){
		$value=$("<input style='width:150px;' type='text'/>");
	}else{
		$a.show();
		$value=$("<textarea></textarea>");
		$value.css("width","150px");
	}
	$value.attr("name","conditionValue");
	$tr.find("[name='conditionValue']").replaceWith($value);
}
/**选择脚本
 * @param obj
 * @returns
 */
function selectScript(obj) {
	var linkObj=$(obj);
	var txtObj=linkObj.next()[0];
	ScriptDialog({
		callback : function(script) {
			$.insertText(txtObj,script);
		}
	});
};