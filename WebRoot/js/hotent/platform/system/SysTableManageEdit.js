/**
*双击不选中文本
*/
var clearSelection = function () { 
    if(document.selection && document.selection.empty) { 
        document.selection.empty(); 
    } 
    else if(window.getSelection) { 
        var sel = window.getSelection(); 
        sel.removeAllRanges(); 
    } 
};
/**
*切换不同过滤条件的界面
*/
function switchConditionType(type){
	if(type==1){
		$(".condition-conds-build").show();
		$(".condition-conds-script").hide();
		$("#condition-columnsTbl th:nth-child(1),#condition-columnsTbl td:nth-child(1)").show();
		$("#condition-columnsTbl tbody tr").unbind("dblclick");
		$("#condition-columnsTbl tbody tr").qtip("destroy");
	}else{
		$(".condition-conds-build").hide();
		$(".condition-conds-script").show();
		$("#condition-columnsTbl th:nth-child(1),#condition-columnsTbl td:nth-child(1)").hide();
		$("#condition-columnsTbl tbody tr").dblclick(function(){
			var text=$(this).find("[name='naTd']").text();
			var pos=conditonScriptEditor.getCursor();
			conditonScriptEditor.replaceRange(text,pos);
		});
		if(conditonScriptEditor){
			setTimeout(function(){
				conditonScriptEditor.refresh();	
			},0);
		}
		$("#condition-columnsTbl tbody tr").qtip({
			content:"双击插入到脚本中",
			position: {
				target: 'mouse',
				viewport: $(window), // Keep it on-screen at all times if possible
				adjust: {
					x: 10,  y: 10
				}
			}
		});
	}
}

/**
 * 选中行或反选
 * @return void
 **/
function selectTr(){
	$("tr.odd,tr.even").each(function(){
		$(this).bind("mousedown",function(event){
			if(event.target.tagName=="TD"){
				var strFilter='input:checkbox[class="pk"],input:radio[class="pk"]';
				var obj=$(this).find(strFilter);
				if(obj.length==1){
					var state=obj.attr("checked");
					obj.attr("checked",!state);
				}
			}
		});    
	});
}
/**
*验证设置
*@return void
*/
function validate(){
	
	var tabid = tabcomp.getSelectedTabItemID();
	//validate base setting
	var valid = validateBaseSetting();
	if(valid.status!=0){
		if(tabid!="baseSetting"){
			pgmswitchta=true;
			tabcomp.selectTabItem("baseSetting");
		}
		if(valid.message){
			$.ligerDialog.error(valid.message,"Error");
		}
		return false;
	}
	//validate display setting
	valid = validateDisplaySetting();
	if(valid.status!=0){
		if(tabid!="displaySetting"){
			pgmswitchta=true;
			tabcomp.selectTabItem("displaySetting");
		}
		if(valid.message){
			$.ligerDialog.error(valid.message,"Error");
		}
		return false;
	}
	var valid = validateParameterSetting();
	if(valid.status!=0){
		if(tabid!="parameterSetting"){
			pgmswitchta=true;
			tabcomp.selectTabItem("parameterSetting");
		}
		if(valid.message){
			$.ligerDialog.error(valid.message,"Error");
		}
		return false;
	}
	valid = validateConditionSetting();
	if(valid.status!=0){
		if(tabid!="conditionSetting"){
			pgmswitchta=true;
			tabcomp.selectTabItem("conditionSetting");
		}
		if(valid.message){
			$.ligerDialog.error(valid.message,"Error");
		}
		return false;
	}
	return true;
}
/**
* 验证基本设置
* @return Object resultMsg={status,message}
* 		  resultMsg.status, 0-无错；1-有错
*		  resultMsg.message, 出错消息
*/
function validateBaseSetting(){
	var resultMsg={
			status:0,
			message:""
	};
	var frm=$('#sysTableManageForm').form();
	if(!frm.valid()){
		resultMsg.status=1;
		return resultMsg;
	}
	var dsAlias=$("#dsAlias").val();
	if(!dsAlias){
		resultMsg.status=2;
		resultMsg.message="请选择数据源！";
		return resultMsg;
	}
	var objName=$("#objName").val();
	if(!objName){
		resultMsg.status=3;
		resultMsg.message="请选择表/视图！";
		return resultMsg;
	}
	
	var objName=$("#templateId").val();
	if(!objName){
		resultMsg.status=4;
		resultMsg.message="请选择数据模板！";
		return resultMsg;
	}
	return resultMsg;
}
/**
* 验证基本设置
* @return Object resultMsg={status,message}
* 		  resultMsg.status, 0-无错；1-有错
*		  resultMsg.message, 出错消息
*/
function validateDisplaySetting(){
	var resultMsg={
			status:0,
			message:""
	};
	//取得参数
	var templen =$("#columnsTbl tbody [name='ds']:checked").length;
	if(templen<1){
		resultMsg.status=1;
		resultMsg.message="请选择显示字段！";
	}
	return resultMsg;
}
/**
* 验证基本设置
* @return Object resultMsg={status,message}
* 		  resultMsg.status, 0-无错；1-有错
*		  resultMsg.message, 出错消息
*/
function validateParameterSetting(){
	var resultMsg={
			status:0,
			message:""
	};
	var nas=[];
	var valid=0;
	var msg;
	$("#parameters-table tbody tr").each(function(){
		var tr=$(this);
		var na=tr.find("[name='na']").val();
		var cm=tr.find("[name='cm']").val()||cm;
		var ty=tr.find("[name='ty']").val();
		var vf=tr.find("[name='vf']").val();
		var va=tr.find("[name='va']").val();
		if (!/^[a-zA-Z_]{1}[\w\d_]*$/.test(na)) {
			resultMsg.status=1;
			resultMsg.message="变量名称必须由字母、下划线和数字组成，并由字母或下划线开头！";
			return false;
		}
		if($.inArray(na,nas)!=-1){
			resultMsg.status=2;
			resultMsg.message="变量名称不能重复！";
			return false;
		}
		nas.push(na);
		if(vf!=4&&vf!=1){
			if(!va){
				resultMsg.status=3;
				resultMsg.message="变量["+na+"<"+cm+">]的值不能为空！";
				return false;
			}
		}
	});
	return resultMsg;
}

/**
* 验证基本设置
* @return Object resultMsg={status,message}
* 		  resultMsg.status, 0-无错；1-有错
*		  resultMsg.message, 出错消息
*/
function validateConditionSetting(){
	var resultMsg={
			status:0,
			message:""
	};
	
	var conditionType=$("[name=conditionType]").val();
	if(conditionType==2){
		return resultMsg;
	}
	
	$("#conditionTbl tbody tr").each(function(){
		var tr=$(this);
		var field=$.parseJSON(tr.find("input[type='hidden']").val());
		var vf=tr.find("[name='vf']").val();
		var cm=tr.find("[name='cm']").val();
		var va=tr.find("[name='va']").val();
		if(vf!=1){
			if(!va){
				resultMsg.status=1;
				resultMsg.message="条件["+field.na+"<"+cm+">]的值不能为空！";
				return false;
			}
		}
	});
	return resultMsg;
}

/**
 * 自定义外部表单并提交
 * @return void
 */
function customFormSubmit(options){
	var id=$("#id").val();
	var name=$("#name").val();
	var alias=$("#alias").val();
	var needPage=$("input[name='needPage']:checked").val();
	var pageSize=$("#pageSize").val();
	var isTable=$("#isTable").val();
	var objName=$("#objName").val();
	var displayField=$("#displayField").val();
	var conditionField=$("#conditionField").val();
	var dsName=$("#dsName").val();
	var dsAlias=$("#dsAlias").val();
	var dspTemplate="";
	var editable=$("input[name='editable']:checked").val();
	var conditionType=$("input[name='conditionType']:checked").val();
	var parameters=$("#parameters").val();
	var templateId=$("#templateId").val();
	var json={
		id:id,
		name:name,
		alias:alias,
		needPage:needPage,
		pageSize:pageSize,
		isTable:isTable,
		objName :objName,
		displayField:displayField ,
		conditionField:conditionField ,
		dsName:dsName ,
		dsAlias:dsAlias,
		dspTemplate:dspTemplate,
		editable:editable,
		conditionType:conditionType,
		parameters:parameters,
		templateId:templateId
	};
	
	var form = $('<form method="post" action="save.ht"></form>');
	var input = $("<input type='hidden' name='json'/>");
	input.val(JSON2.stringify(json));
	form.append(input);
	form.ajaxForm(options);
	form.submit();
}



/**
 *选择表或视图
 */
function selectTable(){
	var dsName=$("#dsAlias").val();
	if(typeof(dsName)=='undefine' || dsName==""){
		$.ligerDialog.warn("请选择数据源！","提示信息");
		return false;
	}
	var url=__ctx +"/platform/system/sysTableManage/selectTable.ht?dsName="+dsName;
	var winArgs="dialogWidth=800px;dialogHeight=600px;help=0;status=0;scroll=1;center=1;resizable=1;";
	url=url.getNewUrl();
	//TODO 找不到页面
	var rtn=window.showModalDialog(url,{},winArgs);
	if(rtn){
		$("#objName").val(rtn.tableName);
		$("#isTable").val(rtn.tableType=='table'?1:0);
		resetParameterSetting();
		var fields = getTableColumns(dsName,rtn.tableName,rtn.tableType);
		initSetting({fields:fields});
	}
}

/**
 *获取表或视图的列
 */
function getTableColumns(dsName,tableName,tableType){
	var params={
		dsName:dsName,
		tableName:tableName,
		tableType:tableType
	};
	var columns;
	var url=__ctx +"/platform/system/sysTableManage/getTableColumns.ht";
	$.ajax({
		url:url,
		data:params,
		async:false
	}).done(function(data){
		columns=data.columns;
	});
	return columns;
}
/**
 *初始化自定义表管理字段设置
 */
function initSetting(settings,conditionType){
	var fields=settings.fields;
	var conds=settings.conds;
	var params=settings.params;
	initColumns(fields);
	initConditionSelCols(fields);
	$("#conditionTbl tbody").html("");
	
	if(params){
		initParameters(params);				
	}
	
	if(conds){
		if(conditionType==1){
			initConditionFields(conds);
		}
	}
	jQuery.highlightTableRows();
	//选中行或反选
		selectTr();
	}
	
	
   /**
*分页 不分页 切换
*/
function switchNeedPage(){
	var isNeedPage=$("input:radio[name='needPage']:checked").val();
	if(isNeedPage>0){
		$("#spanPageSize").show();
	}
	else{
		$("#spanPageSize").hide();
	}
}

/**
 *从左边选择列作为条件
 */
function selectCondition(){
	var conditionType=$("input[name='conditionType']:checked").val();
	$("#condition-columnsTbl input:[name='select']:checked").each(function(){
		var field=$.parseJSON($(this).closest("tr").find("input[type='hidden']").val());
		var na = field.na;
		var ty = field.ty;
		var cm = field.cm;
		var condition={
			jt:"AND",
			na:na,
			ty:ty,
			op:'=',
			cm:cm,
			va:"",
			vf:2
		};
		var tr = constructConditionTr(condition);
		$("#conditionTbl tbody").append(tr);
	});
}

function showResponse(responseText) {
	var obj = new com.hotent.form.ResultMessage(responseText);
	if (obj.isSuccess()) {
		$.ligerDialog.confirm( obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
			if(rtn){
				this.close();
			}else{
				window.location.href =  __ctx+"/platform/system/sysTableManage/list.ht";
			}
		});
	} else {
		$.ligerDialog.error(obj.getMessage(),"提示信息");
	}
}

/**
*数据源改变事件处理
*/
function dsAliasOnChangeHandler(obj){
	$("#dsName").val('');
	var dsAlias = $(obj).val();
	if(dsAlias){
		var dsName = $("#dsAlias option[value='"+dsAlias+"']").text();
		$("#dsName").val(dsName);
	}
	
	resetSelectTable();
}
/**
 *重置选择的表
 */
function resetSelectTable(){
	$("#objName").val('');
	$("#isTable").val('');
	resetParameterSetting();
}
/**
 *重置参数设置
 */
function resetParameterSetting(){
	$("#conditionTbl tbody").html("");
	$("#columnsTbl tbody").html("");
}


/**
 *初始化条件字段
 */
function initConditionFields(confitionFields){
	$("#conditionTbl tbody").html("");
	for(var i=0;i<confitionFields.length;i++){
		var field=confitionFields[i];
		var jt=field.jt;
		var na = field.na;
		var ty=field.ty;
		var cm=field.cm;
		var op=field.op;
		var vf=field.vf;
		var va=field.va;
		
		var condition={
			jt:jt,
			na:na,
			ty:ty,
			cm:cm,
			op:op,
			vf:vf,
			va:va
		};						
		var tr = constructConditionTr(condition);
		$("#conditionTbl tbody").append(tr);
	}
}
/**
 *构造条件的列
 */
function constructConditionTr(condition){
	var jt=condition.jt;
	var ty=condition.ty;
	var na=condition.na;
	var cm=condition.cm;
	var op=condition.op;
	var va=condition.va;
	var vf=condition.vf;
	var constructOption=function(cond1,cond2,text){
		if(!text){
			text=cond2;
		}
		var option=constructTag("option",{value:cond2},text);
		if(cond1==cond2){
			option.attr("selected","selected");
		}
		return option;
	};
	//联合类型
	var jtTd =constructTag("td");
	var jtSelect =constructTag("select",{name:"jt"}); //$("<select name='conditionJoinType'></select>");
	jtSelect.append(constructOption(jt,"AND"));
	jtSelect.append(constructOption(jt,"OR"));
	jtTd.append(jtSelect);
	
	var hiddenInput=constructTag("input",{type:"hidden",value:JSON2.stringify(condition)});
	jtTd.append(hiddenInput);
	
	//名称
	var naTd = constructTag("td",{},na);
	//条件
	var opTd=constructTag("td");
	var opSelect =constructTag("select",{name:"op",style:"width:70px;"});
	switch(ty){
		case 'varchar':
			opSelect.append(constructOption(op,"="));
			opSelect.append(constructOption(op,"likeEnd"));
			opSelect.append(constructOption(op,"like"));
// 					opSelect.append(constructOption(op,"in"));
			break;
		case 'number':
			opSelect.append(constructOption(op,"="));
			opSelect.append(constructOption(op,">"));
			opSelect.append(constructOption(op,"<"));
			opSelect.append(constructOption(op,">="));
			opSelect.append(constructOption(op,"<="));
// 					opSelect.append(constructOption(op,"in"));
			break;
		case 'int':
			opSelect.append(constructOption(op,"="));
			opSelect.append(constructOption(op,">"));
			opSelect.append(constructOption(op,"<"));
			opSelect.append(constructOption(op,">="));
			opSelect.append(constructOption(op,"<="));
// 					opSelect.append(constructOption(op,"in"));
			break;
		default:
			opSelect.append(constructOption(op,"="));
			opSelect.append(constructOption(op,">="));
			opSelect.append(constructOption(op,"<="));
// 					opSelect.append(constructOption(op,"in"));
			opSelect.append(constructOption(op,"between"));
	}
	opTd.append(opSelect);
	//注解
	var cmTd=constructTag("td");
	var cmInput=constructTag("input",{name:"cm",value:cm,type:"text",'class':"inputText"});
	cmTd.append(cmInput);
	
	//值来源
	var vfTd=constructTag("td");
	var vfSelect =constructTag("select",{name:"vf",onchange:"selectValueFrom(this)"});
// 			vfSelect.append(constructOption(vf,1,"输入"));
	vfSelect.append(constructOption(vf,2,"固定值"));
	vfSelect.append(constructOption(vf,3,"脚本"));
	vfSelect.append(constructOption(vf,4,"变量"));
	vfTd.append(vfSelect);
	//值
	var vaTd=constructTag("td");
	var a=constructTag("a",{href:'#',name:'btnScript','class':'hide link var',title:'常用脚本',onclick:"selectScript(this)"},"常用脚本");
	var vaInput;
	if(vf==1){
		vaInput=constructTag("input",{name:"va",type:"text",'class':"hide",readonly:"readonly"});
	}else if(vf==2){
		vaInput=constructTag("input",{name:'va',type:'text'});
	}else if(vf==3){
		a.show();
		vaInput=constructTag("textarea",{name:'va'});
	}else{
		vaInput=constructTag("select",{name:"va"});
		var parameters=getParameters();
		for(var i=0;i<parameters.length;i++){
			p=parameters[i];
			vaInput.append(constructTag("option",{value:p.na},p.cm+"("+p.na+")"));
		}
	}
	vaInput.val(va);
	vaTd.append(a);
	vaTd.append(vaInput);
	//管理
	var manageTd=constructTag("td");
	var deleteA=constructTag("a",{href:"#",'class':"link del",onclick:'delConditionTr(this)'},"删除");
	manageTd.append(deleteA);
	
	tr=constructTag("tr");
	tr.append(jtTd);
	tr.append(naTd);
	tr.append(opTd);
	tr.append(cmTd);
	tr.append(vfTd);
	tr.append(vaTd);
	tr.append(manageTd);
	return tr;
}
/**
 *初始化数据列的表
 */
function initColumns(fields){
	var columnsTbl = $("#columnsTbl tbody").html("");
	for(var i=0;i<fields.length;i++){
		var field=fields[i];
		var tr = constructColumnTr(field);
		if(i%2==0){
			tr.addClass("even");
		}else{
			tr.addClass("odd");
		}
		columnsTbl.append(tr);
	}
}
/**
* 初始化供条件过滤页面选择的数据/视图数据列信息
*/
function initConditionSelCols(fields){
	var columnsTbl = $("#condition-columnsTbl tbody").html("");
	for(var i=0;i<fields.length;i++){
		var field=fields[i];
		var tr = constructConditionSelColTr(field);
		if(i%2==0){
			tr.addClass("even");
		}else{
			tr.addClass("odd");
		}
		columnsTbl.append(tr);
	}
}
/**
 *构造字段设置的列的<tr>
 */
function constructColumnTr(field){
	var ty = field.ty;
	var na = field.na;
	var cm = field.cm||na;
	var ds=false;
	if(typeof(field.ds)!='undefined'){
		ds = field.ds;
	}
	// td field name
	var nameTd = constructTag("td",{},na);
	var hiddenInput=constructTag("input",{type:"hidden"});
	hiddenInput.val(JSON2.stringify(field));
	nameTd.append(hiddenInput);
	
	var typeTd=constructTag("td",{},(ty||"").toUpperCase());
	
	
	// td field comment
	var commentTd=constructTag("td");
	var commentInput=constructTag("input",{name:"cm",value:cm,'class':"inputText"});
	commentTd.append(commentInput);
	// td column select checkedbox
	var displayTd =constructTag("td");
	var dsInput = constructTag("input",{name:"ds",type:"checkbox",'class':"pk"}); 
	if(ds){
		dsInput.attr("checked","checked");
	}
	displayTd.append(dsInput);
	//Tr
	var tr=constructTag("tr");
	tr.append(displayTd);
	tr.append(nameTd);
	tr.append(typeTd);
	tr.append(commentTd);
	
	return tr;
}
 
 
/**	
 *构造过滤条件的字段设置的列的<tr>
 */
function constructConditionSelColTr(field){
	var ty = field.ty;
	var na = field.na;
	var cm = field.cm||na;
	//td checkbox
	var selectTd = constructTag("td");
	var selectInput = constructTag("input",{name:"select",type:"checkbox",'class':"pk"});
	selectTd.append(selectInput);
	// td field name
	var nameTd = constructTag("td",{name:"naTd"},na);
	var hiddenInput=constructTag("input",{type:"hidden"});
	hiddenInput.val(JSON2.stringify(field));
	nameTd.append(hiddenInput);
	// td field type
	var typeTd=constructTag("td",{},(ty||"").toUpperCase());
	
	//Tr
	var tr=constructTag("tr");
	tr.append(selectTd);
	tr.append(nameTd);
	tr.append(typeTd);
	return tr;
}
 
/**
 *删除选择的条件所有的表格的行
 */
function delConditionTr(obj){
	$(obj).closest("tr").remove();
}

/**
 * 取得参数设置
 */
function getParameterSetting(){
	var setting={
			fieldSetting:new Array(),
			conditionField:new Array(),
			parameters:new Array()
	};
	//get field setting
	setting.fieldSetting=getFields();
	
	//取条件字段
	var conditions=getConditions();
	setting.conditionField=conditions;
	//取自定义变量
	var parameters=getParameters();
	setting.parameters=parameters;
	return setting;
}

/**
*取显示字段设置
*/
function getFields(){
	var fields=[];
	$("#columnsTbl tbody tr").each(function(){
		var tr=$(this);
		var fieldSetting=$.parseJSON(tr.find("input:[type='hidden']").val());
		var cm = tr.find("[name='cm']").val();
		var ds = tr.find("[name='ds']").is(":checked");
		fieldSetting.cm=cm;
		fieldSetting.ds=ds;
		fields.push(fieldSetting);
	});
	return fields;
}

/**
*取条件设置
*/
function getConditions(){
	var conditions=new Array();
	//取条件类型
	var conditonType=$("input[name='conditionType']:checked").val();
	//如果为建立类型
	if(conditonType==1){
		$("#conditionTbl tbody tr").each(function(){
			var tr=$(this);
			var field=$.parseJSON(tr.find("input[type='hidden']").val());
			var jt=tr.find("[name='jt']").val();
			var op =tr.find("[name='op']").val();
			var vf =parseInt(tr.find("[name='vf']").val());
			var va =tr.find("[name='va']").val();
			var cm =tr.find("[name='cm']").val();
			field.jt=jt;
			field.op=op;
			field.vf=vf;
			field.va=va;
			field.cm=cm;
			conditions.push(field);
		});
		return conditions;
	}else{//如果是脚本类型
		var script=$("#conditionScript").val();
		return script;
	}
}

/**
*取自定义变量设置
*/
function getParameters(){
	var parameters=[];
	$("#parameters-table tbody tr").each(function(){
		var tr=$(this);
		var na=tr.find("[name='na']").val();
		var cm=tr.find("[name='cm']").val()||na;
		var ty=tr.find("[name='ty']").val();
		var vf=tr.find("[name='vf']").val();
		var va=tr.find("[name='va']").val();
		var parameter={
				na:na,
				cm:cm,
				ty:ty,
				vf:vf,
				va:va
		};
		parameters.push(parameter);
	});
	return parameters; 
}

/**
 *值来源更改事件处理
 */
function selectValueFrom(obj){
	var tr = $(obj).closest("tr");
	var a = tr.find("a:[name='btnScript']");
	a.hide();
	var vf = $(obj).val();
	var valueInput;
	if(vf==1){
		valueInput=constructTag("input",{name:'a',type:"text",'class':"hide",readonly:"readonly"});
	}else if(vf==2){
		valueInput=constructTag("input",{name:'va',type:"text"});
	}else if(vf==3){
		a.show();
		valueInput=constructTag("textarea",{name:"va"});
	}else {
		valueInput=constructTag("select",{name:"va"});
		var parameters=getParameters();
		for(var i=0;i<parameters.length;i++){
			p=parameters[i];
			valueInput.append(constructTag("option",{value:p.na},p.cm+"("+p.na+")"));
		}
	}
	tr.find("[name='va']").replaceWith(valueInput);
}
/**
 *脚本选择
 */
function selectScript(obj) {
	var linkObj=$(obj);
	var txtObj=linkObj.next()[0];
	ScriptDialog({
		callback : function(script) {
			$.insertText(txtObj,script);
		}
	});
}

/**
*添加自定义参数Tr
*/
function addParameter(){
	var parameter={
			na:"",
			cm:"",
			ty:"varchar",
			vf:1,
			va:""
	};
	var tr = constructParameterTr(parameter);
	$("#parameters-table").append(tr);
}

/**
*添加自定义参数Tr
*/
function constructParameterTr(parameter){
	var na=parameter.na;
	var cm=parameter.cm;
	var ty=parameter.ty;
	var vf=parameter.vf;
	var va=parameter.va;
	
	var constructOption=function(cond1,cond2,text){
		if(!text){
			text=cond2;
		}
		var option=constructTag("option",{value:cond2},text);
		if(cond1==cond2){
			option.attr("selected","selected");
		}
		return option;
	};
	
	var tr=constructTag("tr");
	
	var nameTd=constructTag("td");
	var nameInput=constructTag("input",{name:"na",type:"text",'class':"inputText",value:na});
	nameTd.append(nameInput);
	
	var commentTd=constructTag("td");
	var commentInput=constructTag("input",{name:"cm",type:"text",'class':"inputText",value:cm});
	commentTd.append(commentInput);
	
	var typeTd=constructTag("td");
	var typeSelect=constructTag("select",{name:"ty"});
	typeSelect.append(constructOption(ty,"S","字符类型"));
	typeSelect.append(constructOption(ty,"L","整数类型"));
//	typeSelect.append(constructOption(ty,"N","Integer"));
	typeSelect.append(constructOption(ty,"DB","数字类型"));
//	typeSelect.append(constructOption(ty,"BD","BigDecimal"));
//	typeSelect.append(constructOption(ty,"FT","Float"));
//	typeSelect.append(constructOption(ty,"SN","Short"));
	typeSelect.append(constructOption(ty,"DL","日期类型"));
	typeTd.append(typeSelect);
	
	var valueFromTd=constructTag("td");
	var valueFromSelect=constructTag("select",{name:"vf",onchange:"paramValueFromChange(this)"});
	valueFromSelect.append(constructOption(vf,1,"输入"));
	valueFromSelect.append(constructOption(vf,2,"固定值"));
	valueFromSelect.append(constructOption(vf,3,"脚本"));
	valueFromTd.append(valueFromSelect);
	
	var valueTd=constructTag("td");
	var scriptBtn=$("<a style='display:none;' href='#' name='btnScript' class='link var' title='常用脚本' onclick='selectScript(this)'>常用脚本</a>");
	var valueInput;
	if(vf==1){
		valueInput=constructTag("input",{name:"va",type:"text",value:va,readonly:"readonly"});
		valueInput.hide();
	}else if(vf==2){
		valueInput=constructTag("input",{name:"va",type:"text",value:va,style:"width:96%"});
	}else{
		scriptBtn.show();
		valueInput=constructTag("textarea",{name:"va",value:va,style:"width:96%"});
	}
	valueTd.append(scriptBtn);
	valueTd.append(valueInput);
	
	//管理
	var manageTd=constructTag("td");
	var deleteA=constructTag("a",{onclick:"delConditionTr(this)",href:"#",'class':"link del"},"删除");
	manageTd.append(deleteA);
	
	tr.append(nameTd);
	tr.append(commentTd);
	tr.append(typeTd);
	tr.append(valueFromTd);
	tr.append(valueTd);
	tr.append(manageTd);
	
	return tr;
}

/**
 *值来源更改事件处理
 */
function paramValueFromChange(obj){
	var tr = $(obj).closest("tr");
	var scriptBtn = tr.find("a:[name='btnScript']");
	scriptBtn.hide();
	var vf = $(obj).val();
	var valueInput;
	if(vf==1){
		valueInput=constructTag("input",{name:"va",type:"text",readonly:"readonly"});
		valueInput.hide();
	}else if(vf==2){
		valueInput=constructTag("input",{name:"va",type:"text",style:"width:96%"});
	}else{
		scriptBtn.show();
		valueInput=constructTag("textarea",{name:"va",style:"width:96%"});
	}
	tr.find("[name='va']").replaceWith(valueInput);
}

/**
 *构造标签
 */
function constructTag(name,props,text){
	var tag = $("<"+name+"></"+name+">");
	if(props){
		for(var key in props){
			tag.attr(key,props[key]);
		}
	}
	if(text){
		tag.text(text);
	}
	return tag;
}

/**
*Before Select Tab item event handler
*@param tabid ligertab tab id
*@return 
*	false 阻止操作,否则继续
*/
function onBeforeSelectTabItem(newtabid){
	if(pgmswitchta){
		pgmswitchta=false;
		return true;
	}
	var tabid = tabcomp.getSelectedTabItemID();
	
	if(newtabid=="baseSetting"){
		return true;
	}else if(newtabid=="displaySetting"){
		var valid = validateBaseSetting();
		if(valid.status!=0){
			if(tabid!="baseSetting"){
				pgmswitchta=true;
				tabcomp.selectTabItem("baseSetting");
			}
			if(valid.message){
				$.ligerDialog.error(valid.message,"Error");
			}
			return false;
		}
	}else if(newtabid=="parameterSetting"){
		return	true;
	}else if(newtabid=="conditionSetting"){
		//没有使用setTimeout，不起会用，应该是CodeMirror与LigerUI有冲突
		setTimeout(function(){
			conditonScriptEditor.refresh();
		},0);
		
		var valid = validateBaseSetting();
		if(valid.status!=0){
			if(tabid!="baseSetting"){
				pgmswitchta=true;
				tabcomp.selectTabItem("baseSetting");
			}
			if(valid.message){
				$.ligerDialog.error(valid.message,"Error");
			}
			return false;
		}
		
		
		valid = validateParameterSetting();
		if(tabid!="parameterSetting"){
			pgmswitchta=true;
			tabcomp.selectTabItem("parameterSetting");
		}
		if(valid.status!=0){
			if(valid.message){
				$.ligerDialog.error(valid.message,"Error");
			}
			return false;
		}
		
		//更新条件中的值来源是变量的变量选择列表
		var parameters=getParameters();
		var vaInput=constructTag("select",{name:"va"});
		for(var i=0;i<parameters.length;i++){
			p=parameters[i];
			vaInput.append(constructTag("option",{value:p.na},p.cm+"("+p.na+")"));
		}
		$("#conditionTbl tbody select[name='vf']:[value='4']").each(function(){
			var tr=$(this).closest("tr");
			var oldVaInput = tr.find("[name='va']");
			var va=oldVaInput.val();
			vaInput.val(va);
			oldVaInput.replaceWith(vaInput);
		});
		
		//更新脚本条件编辑器上方的自变量列表
		initConditionSelParams();
	
	}
	return true;
}
/**
*@param checkbox object
*@return void
*/
function selectAll(obj){
	if($(obj).attr("checked")){
		$(obj).closest("table").find("[name='ds']").each(function(){
			$(this).attr("checked","checked");
		});
	}else{
		$(obj).closest("table").find("[name='ds']").each(function(){
			$(this).removeAttr("checked");
		});
	}
}


/**
*初始化自定义参数
*@param 参数Object
*@return void
*/
function initParameters(params){
	for(var i=0;i<params.length;i++){
		var parameter=params[i];
		var tr = constructParameterTr(parameter);
		$("#parameters-table tbody").append(tr);
	}
}
/**
* 设置帮助信息
*/
function helpInfoShow(obj,target){
	var info = $("#"+target).html();
	$.ligerDialog.open({
		content:info,
		width:500,
		height:400,
		modal:false,
		showMin: false
	});
}

/**
 * 初始化条件过滤脚本编辑器上方的自定义参数选项选择
 */
function initConditionSelParams(){
	var parameters=getParameters();
	var parametersDiv=$("#condition-script-div-parameters-list");
	parametersDiv.html("");
	for(var i=0;i<parameters.length;i++){
		p=parameters[i];
		var a=constructTag("a",{name:p.na,'class':'button',style:"margin:5px 10px",href:"#",onclick:"insertParameterToScript(this)"});
		var content=""
			+"<table class='table-detail'>"
			+"<tr>"
			+"<th colspan='2'><span class='green'>点击插入到脚本中</span></th>"
			+"</tr>"
			+"<tr>"
			+"<th>名称</th>"
			+"<td>"+p.na+"</td>"
			+"</tr>"
			+"<tr>"
			+"<th>注释</th>"
			+"<td>"+p.cm+"</td>"
			+"</tr>"
			+"<tr>"
			+"<th>类型</th>"
			+"<td>"+getType(p.ty)+"</td>"
			+"</tr>"
			+"</table>";
		a.qtip({
			content:content
		});
		var span=constructTag("span",{},p.cm);
		a.append(span);
		parametersDiv.append(a);
	}
}
function insertParameterToScript(obj){
	var text = $(obj).attr("name");
	var pos=conditonScriptEditor.getCursor();
	conditonScriptEditor.replaceRange(text,pos);
}

/**
 * 类型简写到完全的表示形式
 * @param type
 * @returns 类型的完全表示
 */
function getType(type)
{
	var lType=type;
	switch(type){
	case "S":
		lType="String";
		break;
	case "L":
		lType="Long";
		break;
	case "N":
		lType="Integer";
		break;
	case "DB":
		lType="Double";
		break;
	case "BD":
		lType="BigDecimal";
		break;
	case "FT":
		lType="Float";
		break;
	case "SN":
		lType="Short";
		break;
	case "DL":
		lType="Date";
		break;
	}
	return lType;
}
/**
 * 验证输入的条件脚本
 * @param obj
 */
function validateConditionScript(obj){
	var url=__ctx+"/platform/system/sysTableManage/parseConditionScript.ht";
	var parameters=getParameters();
	var inputs=[];
	var inputnas=[];
	for(var i=0;i<parameters.length;i++){
		var p=parameters[i];
		if(p.vf!=1){
			continue;
		}
		var input;
		if(p.ty=='DL'){
			input = constructTag("input",{name:"Q_"+p.na+"_"+p.ty,onfocus:"datePicker(this)","class":"Wdate inputText"});
		}else{
			input = constructTag("input",{name:"Q_"+p.na+"_"+p.ty,"class":"inputText"});
		}
		
		var content=""
			+"<table class='table-detail'>"
			+"<tr>"
			+"<th>名称</th>"
			+"<td>"+p.na+"</td>"
			+"</tr>"
			+"<tr>"
			+"<th>注释</th>"
			+"<td>"+p.cm+"</td>"
			+"</tr>"
			+"<tr>"
			+"<th>类型</th>"
			+"<td>"+getType(p.ty)+"</td>"
			+"</tr>"
			+"</table>";
		input.qtip({
			show: {
	            event: 'focus'
	        },
	        hide: {
	           event: 'blur'
	        },
			content:content
		});
		
		var tr=constructTag("tr");
		var naTd=constructTag("td");
		naTd.text(p.na);
		var vaTd=constructTag("td");
		vaTd.append(input);
		tr.append(naTd);
		tr.append(vaTd);
		inputs.push(tr);
		inputnas.push('');
	}
	
	
	var validate=function(vars){
		vars=vars||{};
		var script=conditonScriptEditor.getValue();
		var params={
				script:script,
				parameters:JSON2.stringify(parameters)
		};
		$.extend(params,vars);
		$.post(url,params,function(data){
			// console.dir(JSON2.stringify(data));
			if(data.status){
				$.ligerDialog.success("结果:"+data.result,"在当前上下文，脚本执行成功");
			}else{
				$.ligerDialog.error(data.message,"当前上下文，解析条件脚本失败：");
			}
		});
	};
	
	
	if(inputs.length>0){
		var table=$("<table class='table-detail'><tr><th style='text-align: center;'>变量名</th><th style='text-align: center;'>变量值</th></tr></table>");
		for(var i=0;i<inputs.length;i++){
			table.append(inputs[i]);
		}
		var form=$("<form id='dialog-input-parameter-form'>").append(table);
		$.ligerDialog.open({
			width:300,
			height:350,
			target:form,
			buttons:[{
				text:'确定',
				onclick:function(item, dialog, index){
					dialog.close();
					var vars = serializeObject(form);
					validate(vars);
				}
			}]
		});
	}else{
		validate();
	}
}
/**
 * 从表单中取得表单输入的JSON
 * @param form
 * @returns Object
 */
function serializeObject(form){
	var o = {};
	var a = $(form).serializeArray();
	
	$.each(a, function() {
	    if (o[this.name]) {
	        if (!o[this.name].push) {
	            o[this.name] = [o[this.name]];
	        }
	        o[this.name].push(this.value || '');
	    } else {
	        o[this.name] = this.value || '';
	    }
	});
	return o;
}