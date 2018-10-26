//更新规则内容
function updateContent(item,data){
	var nobr = $('<div class="nobr" style="float:left;margin-right:-999em;"></div>'),
		flowVarsSelect = $("#flowVarsSelect").clone(true).removeAttr("id"),
		paramKey = $("#paramKey").clone(true).removeAttr("id"),
		paramCondition = $("#paramCondition").clone(true).removeAttr("id"),
		paramValue = $("#paramValue").clone(true).removeAttr("id"),
		ruleType;
	if(!data){
		ruleType= "1";
	}
	else{
		ruleType = data.ruleType;
	}
	//普通规则
	if(ruleType=='1'){
		labelSpan = $('<span class="label-span left"></span>').attr("ruleType",ruleType).text('普通规则');
		nobr.append(labelSpan).append(flowVarsSelect).append($('<div class="judge left margin-set"></div>'));
		$("div.nobr",item).remove();
		item.append(nobr);
		if(data){
			flowVarsSelect.val(data.flowvarKey).trigger("change");
			var judgeExp = $("div.judgeExp",item),
				judgeExp2 = $("div.judgeExp2",item);
			if(judgeExp){
				setJudgeValue(judgeExp,data.judgeCon1,true);
				setJudgeValue(judgeExp,data.judgeVal1,false);
			}
			if(judgeExp2){
				setJudgeValue(judgeExp2,data.judgeCon2,true);
				setJudgeValue(judgeExp2,data.judgeVal2,false);
			}
		}
	}
	//脚本规则
	else if(ruleType=='2'){
		labelSpan = $('<span class="label-span left"></span>').attr("ruleType",ruleType).text('脚本规则');
		var judge = $('<div class="judge left margin-set"></div>').append($('<a name="script" href="###" onclick="editConditionScript(this)">脚本</a>'));
		nobr.append(labelSpan).append(judge);
		$("div.nobr",item).remove();
		item.append(nobr);
		if(data.script){
			item.data("script",data.script);
		}
		else if(typeof data.script==='undefined'){
			addConditionScript(item);
		}
	}
	else {//组织属性、用户属性
		if(ruleType=='3'){
			labelSpan = $('<span class="label-span left"></span>').attr("ruleType",ruleType).text('用户属性');
		}
		else {
			labelSpan = $('<span class="label-span left"></span>').attr("ruleType",ruleType).text('组织属性');
		}
		nobr.append(labelSpan).append(paramKey).append(paramCondition).append(paramValue);
		$("div.nobr",item).remove();
		item.append(nobr);
		if(data) {
			paramKey.val(data.paramKey);
			paramCondition.val(data.paramCondition);
			paramValue.val(data.paramValue);
		}
	}
};
/**
 * 添加条件脚本规则
 * @param obj
 */
function addConditionScript(item){
	var defId = $("#defId").val();
	ConditionScriptEditDialog({
		data:{
			defId:defId
		},
		callback:function(data){
			item.data("script",data.script);
		}
	});
};

/**
 * 编辑条件脚本规则
 * @param obj
 */
function editConditionScript(t){
	var me=$(t),
		defId = $("#defId").val(),
		item = me.parent().parent().parent(),
		script = item.data("script");
	ConditionScriptEditDialog({
		data:{
			defId:defId,
			script:script
		},
		callback:function(data){
			item.data("script",data.script);
		}
	});
};
//获取流程变量的类型识别码
function getFlowVarType(opt){
	var value = opt.val(),
		ctltype = opt.attr("ctltype"),
		ftype = opt.attr("ftype");
	
	//流程变量
	if(ctltype){
		switch(ctltype.toString()){
			//用户、角色、岗位、组织选择器
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "17":
			case "18":
			case "19":
				return 5;
			//字典
			case "3":
			case "11":
			case "13":
			case "14":
				return 4;
			//日期
			case "15":
				return 3;
		}
	}		
	//发起人
	if(value=="startUser")
		return 5;
	if(ftype){
		//根据变量类型识别
		switch(ftype.toLowerCase()){
			case "int":
			case "number":
				return 1;
			case "varchar":
			case "string":
				return 2;
			case "date":
				return 3;
		}
	}
	return 2;
};

//获取字典类型的 条件值 控件
function getDicControl(option){
	var value = option.val(),
		ctltype = option.attr("ctltype"),
		chosenopt = option.attr("chosenopt"),
		opts = eval("("+chosenopt+")"),
		html = '',
		data = [],
		type = "";
	var tempHtml = $("#dic-radio-checkbox").val();
	
	if(ctltype.toString()=='13')
		type = "checkbox";
	else if(ctltype.toString()=='14')
		type = "radio";
	else{
		tempHtml = $("#dic-select").val();
	}
	
	for(var i=0,c;c=opts[i++];){
		data.push({type:type,name:value,option:c.key,memo:c.value});
	}
	
	html = easyTemplate(tempHtml,data).toString();
	return $(html);
};

//获取不同类型的选择器
function getSelector(option){
	var ctltype = option.attr("ctltype"),
		value = option.val(),
		str = "user-div";
	switch(ctltype.toString()){
		case "4"://用户单选
		case "8"://用户多选
			str = "user-div";
			break;
		case "5"://角色
		case "17"://角色
			str = "role-div";
			break;
		case "6"://组织
		case "18"://组织
			str = "org-div";
			break;
		case "7"://岗位
		case "19"://岗位
			str = "position-div";
			break;
	}
	var control = $("#"+str).clone(true,true).removeAttr("id");
 	$("input[type='text']",control).attr("name",value);
 	$("input[type='hidden']",control).attr("name",value+"ID");
 	$("a.link",control).attr("name",value);
	return control;
};

//获取判定值表达式
function getJudgeExp(optType,option){
	var	datefmt = option.attr("datefmt"),
	 	expDiv = $('<div class="judgeExp left"></div>'),
		judgeCon = $("#judgeCon-"+optType).clone(true).removeAttr("id"),
		input;
	if(!judgeCon||judgeCon.length==0){
		judgeCon = $("#judgeCon-1").clone(true).removeAttr("id");
	}
	expDiv.attr("optType",optType).append(judgeCon);
	switch(optType){
		case 1:
		case 2:
			input = $("#normal-input").clone(true).removeAttr("id");
			break;
		case 3:
			input = $("#date-input").clone(true).removeAttr("id").attr("datefmt",datefmt);
			break;
		case 4:
			input = getDicControl(option);
			break;
		case 5:
			input = getSelector(option).children();
			break;
	}
	expDiv.append(input);
	return expDiv;
};

//流程变量选择事件
function flowVarChange(){
	var me = $(this),
		nobr = me.parents("div.nobr"),
		option = me.find("option:selected");
	
	if(!option.val())return;		
	
	var	optType = getFlowVarType(option),
		datefmt = option.attr("datefmt"),
		judgeCon = null;
	
	var judgeDiv = $("div.judge",nobr).empty(),
		judgeExp = getJudgeExp(optType,option);
	
	judgeDiv.append(judgeExp);
	if(optType==1||optType==3){
		var judgeExp2 = judgeExp.clone(true).removeClass("judgeExp").addClass("judgeExp2");
		judgeDiv.append(judgeExp2);
	}
};

//获取判定值
function getJudgeValue(p){
	if(!p)return false;
	var val = [];
	p.find("input").each(function(){
		var me = $(this),
			type = me.attr("type");
		if(type=="checkbox"||type=="radio"){
			if(me.attr("checked"))
				val.push(me.val());	
		}
		else
			val.push(me.val());
	});
	p.find("select").each(function(){
		var me = $(this),
			name = me.attr("name");
		if(name=="judgeCondition")return true;
		val.push(me.val());
	});
	return val.join('&&');
};

//获取判定text
function getJudgeText(p){
	if(!p)return '';
	var val = [];
	p.find("input:visible").each(function(){
		var me = $(this),
			type = me.attr("type");
		if(type=="checkbox"||type=="radio"){
			if(me.attr("checked")){
				val.push(me.parent().text());
			}
		}
		else
			val.push(me.val());
	});
	p.find("select").each(function(){
		var me = $(this),
			name = me.attr("name");
		if(name=="judgeCondition")return true;
		val.push(me.find("option:selected").text());
	});
	return val.join('&&');
};

//设置判定值
function setJudgeValue(p,val,isJudgeCon){
	if(!p)return;
	if(!isJudgeCon){
		p.find("input").each(function(){
			var me = $(this),
				value = me.val(),
				type = me.attr("type");
			if(type=="checkbox"||type=="radio"){
				if(val.indexOf(value)>-1){
					me.attr("checked","checked");
				}
			}
			else{
				if(/\&\&/.test(val)){
					var vals = val.split(/\&\&/);
					if(me.attr("type")=="hidden")					
						me.val(vals[0]);
					else
						me.val(vals[1]);
				}
				else
					me.val(val);
			}
		});
	}
	p.find("select").each(function(){
		var me = $(this),
			name = me.attr("name");
		if((name=="judgeCondition")==isJudgeCon){
				me.val(val);
		}
	});
};

/**
 * rule生成json
 * @params item
 * @returns {Object}
 */
function rule2json(item){
	var jobject = {},
		ruleType = $("span.label-span",item).attr("ruleType");
	
	//普通规则
	if(ruleType=="1"){
		var	flowvarKey = $("select[name='flowVars']",item).val(),
			flowvarText = $("select[name='flowVars']",item).find("option:selected").text(),
			judgeExp = $("div.judgeExp",item),
			judgeExp2 = $("div.judgeExp2",item),
			conDesc = [];
		
		if(!judgeExp||judgeExp.length==0)return null;
		conDesc.push(flowvarText);
		var optType = judgeExp.attr("optType");
		jobject.optType = optType;
		jobject.flowvarKey = flowvarKey;
		jobject.judgeCon1 = $("select[name='judgeCondition']",judgeExp).val();
		conDesc.push($("select[name='judgeCondition']",judgeExp).find("option:selected").text());
		jobject.judgeVal1 = getJudgeValue(judgeExp);
		conDesc.push(getJudgeText(judgeExp));
		if(judgeExp2&&judgeExp2.length>0){
			jobject.judgeCon2 = $("select[name='judgeCondition']",judgeExp2).val();
			conDesc.push('并且');
			conDesc.push($("select[name='judgeCondition']",judgeExp2).find("option:selected").text());
			jobject.judgeVal2 = getJudgeValue(judgeExp2);
			conDesc.push(getJudgeText(judgeExp2));
		}
		jobject.conDesc = conDesc.join(' ');
	}
	//脚本规则
	else if(ruleType=='2'){
		var script = item.data("script");
		jobject.script = script;
		jobject.conDesc = ' 脚本 ';
	}
	else{//ruleType=3:用户属性  ruleType=4:组织属性
		var conDesc = [];
		var	paramKey = $("select[name='paramKey']",item).val(),
			dataType = $("select[name='paramKey']",item).find("option:selected").attr("title");
		var	paramCondition = $("select[name='paramCondition']",item).val();
		var paramValue = $("input[name='paramValue']",item).val();
		jobject.paramKey = paramKey;
		jobject.paramCondition = paramCondition;
		jobject.paramValue = paramValue;
		conDesc.push(paramKey);
		conDesc.push(paramCondition);
		conDesc.push(paramValue);
		jobject.conDesc = conDesc.join(' ');
		
		jobject.expression = paramKey+paramCondition+paramValue;
		jobject.dataType = dataType;
	}
	
	jobject.ruleType = ruleType;
	var compType = $("select.computing-select",item).val();
	if(compType){
		//运算类型 
		jobject.compType = compType;
	}
	return jobject;
};