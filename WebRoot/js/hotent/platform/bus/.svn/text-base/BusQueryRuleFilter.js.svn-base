/**
 * 数据模板过滤
 * @type 
 */
var editor=null;

$(function(){
	var width = $("#sql").width();
	var height = $("#sql").height();
	editor = CodeMirror.fromTextArea(document.getElementById("sql"), {
		mode: "text/x-mariadb",
		lineWrapping:true,
		lineNumbers: true
	 });
	editor.setSize(width,height);
	
	//var conf = window.dialogArguments;
	var conf = dialog.get('conf');
	
	if(conf){
		if(conf.source)
			$('#source').val(conf.source);
		if(conf.filter){
			var condition = conf.filter.condition,
				type = conf.filter.type;
			if(typeof condition == "object")
				condition = JSON.stringify(condition);
			if(conf.filter.name)
				$('#name').val(conf.filter.name);
			if(conf.filter.key)
				$('#key').val(conf.filter.key);	
			if(!$.isEmpty(type))
				condition = initType(type,condition);
			if(!$.isEmpty(condition))
				$('#filterTxt').val(condition);	
		}
	}
	
	var data = getInitData();
	
	
	//初始化日期控件
	FormUtil.initCalendar();	
	
	$("#ruleDiv").linkdiv({data:data,
						updateContent:updateContent,
						rule2json:rule2json});
						
	$("#type").change(function() {
		changeType($(this).val());
	});
	

	$("#sqlTip").qtip({
		content:{
			text:'<div>该脚本为 groovyScript脚本 ，返回值为可执行的sql语句,' +
				'<p>例：String sql ="select ID,field from table where 1=1";<br/>' +
				'Object field = map.get("field");<br/>' +
				'if(field!=null)<br/>' +
				'&nbsp;&nbsp;sql += " and field like \'%"+field+"%\'";<br/>' +
				'return sql; </p>' +
				'其中的map为系统所封装的一个参数;' +
				'<br/>在脚本中使用map.get("field")可以获取表单提交时所携带的field参数值，脚本应拼接并返回任意的可执行的sql语句.返回的sql中要含有ID</div>'
		}
	});
});

function changeType(type){
	if(type == 2){
		$('#filterSetting').hide();
		$('#sqlSetting').show();
		editor.setValue("");
	}else{
		$('#filterSetting').show();
		$('#sqlSetting').hide();
	}
}

function  initType(type,condition){
	$('#type').val(type);
	changeType(type);
	if(type == 2){
		editor.setValue(condition);
		condition = "";
	}
	return condition
}

function varsChange(){
		var me = $(this),
			val = me.val();
		if($.isEmpty(val))
			return;
		me.val('');
		editor.replaceSelection(val);
}



function fieldChange(){
		var me = $(this),
			selected=me.find("option:selected"),
			source = selected.attr('source'),
			val = me.val();
		if($.isEmpty(val))
			return;
		val =  (source ==1?("F_"+val):val);
		me.val('');
		editor.replaceSelection(val);
}

function tableChange(){
		var me = $(this),
			selected=me.find("option:selected"),
			source = selected.attr('source'),
			val = me.val();
		if($.isEmpty(val))
			return;
		val =  (source ==1?("W_"+val):val);
		me.val('');
		editor.replaceSelection(val);
}

/**
 * 获得初始化数据
 * @return {String}
 */
function getInitData(){
	var data = $("#filterTxt").val().trim();
	if(data=="") return '';
	var json = eval("("+data+")");
	if(json.length==0)return '';
	return json;
};


/**
 * 更新内容
 * @param {} item
 * @param {} data
 */
function updateContent(item,data){
	var nobr = $('<div class="nobr" style="float:left;margin-right:-999em;"></div>'),
		//字段克隆
		flowVarsSelect = $("#flowVarsSelect").clone(true).removeAttr("id"),
		paramKey = $("#paramKey").clone(true).removeAttr("id"),
		paramCondition = $("#paramCondition").clone(true).removeAttr("id"),
		paramValue = $("#paramValue").clone(true).removeAttr("id"),
		ruleType;
	if(!data){
		ruleType= "1";
	}else{
		ruleType = data.ruleType;
	}
	//普通条件
	if(ruleType=='1'){
		labelSpan = $('<span class="label-span left"></span>').attr("ruleType",ruleType).text('普通条件');
		nobr.append(labelSpan)
			.append(flowVarsSelect)
			.append($('<div class="judge left margin-set"></div>'));
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
		labelSpan = $('<span class="label-span left"></span>').attr("ruleType",ruleType).text('脚本条件');
		var judge = $('<div class="judge left margin-set"></div>').append($('<a name="script" href="###" onclick="editConditionScript(this)">脚本</a>'));
		nobr.append(labelSpan).append(judge);
		$("div.nobr",item).remove();
		item.append(nobr);
		if(data.script){
			item.data("script",data.script);
			item.data("tables",data.tables);
		}else if(typeof data.script==='undefined'){
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
			objSel=$("select[name='flowVars']",item).find("option:selected"),
			flowvarText = objSel.text(),
			source = objSel.attr("source"),
			table = objSel.attr("table"),
			mainTable = objSel.attr("maintable"),
			relation = objSel.attr("relation"),
			judgeExp = $("div.judgeExp",item),
			judgeExp2 = $("div.judgeExp2",item),
			isHidden = objSel.attr("ishidden"),
			conDesc = [];
		//数据来源通过前面传入
		jobject.source= source;
		//数据表
		jobject.table= table;
		//主表
		jobject.mainTable=	mainTable;
		//外键
		jobject.relation=	relation;
		//是否隐藏
		jobject.isHidden= isHidden;
		
		if(!judgeExp||judgeExp.length==0)return null;
		conDesc.push(flowvarText);
		var optType = judgeExp.attr("optType");
		jobject.optType = optType;
		jobject.flowvarKey = flowvarKey;

		jobject.datefmt= judgeExp.attr("datefmt");
	
		
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
		var script = item.data("script"),
			tables = item.data("tables");
		jobject.script = script;
		jobject.tables = tables;
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

/**
 * 脚本窗口
 * @param {} conf
 */
function ScriptDialog(conf){
	
	if(!conf) conf={};	
	var url=__ctx + "/platform/bus/busQueryRule/script.ht?tableName="+conf.tableName;	//条件脚本设计器
	
	var dialogWidth=800;
	var dialogHeight=600;
	
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:1,center:1},conf);

	var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
		+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
	url=url.getNewUrl();
	/*var rtn=window.showModalDialog(url,conf.data,winArgs);
	if(rtn!=undefined){
		if(conf.callback){
			conf.callback.call(this,rtn);
		}
	}*/
	var that =this;
	/*KILLDIALOG*/
	DialogUtil.open({
		height:conf.dialogHeight,
		width: conf.dialogWidth,
		title : '脚本窗口',
		url: url, 
		isResize: true,
		//自定义参数
		data: conf.data,
		sucCall:function(rtn){
			if(rtn!=undefined){
				if(conf.callback){
					conf.callback.call(that,rtn);
				}
			}
		}
	});
}


/**
 * 添加脚本条件
 * @param obj
 */
function addConditionScript(item){	
	ScriptDialog({
		tableName:$('#tableName').val(),
		callback:function(data){
			item.data("script",data.script);
			item.data("tables",data.tables);
		}
	});
};

/**
 * 编辑条件脚本
 * @param obj
 */
function editConditionScript(t){
	var me=$(t),
		item = me.parent().parent().parent(),
		script = item.data("script");
		tables = item.data("tables");
	ScriptDialog({
		tableName:$('#tableName').val(),
		data:{
			script:script,
			tables:tables
		},
		callback:function(data){
			item.data("script",data.script);
				item.data("tables",data.tables);
		}
	});
};


/**
 * 设置判定值
 * @param {} p
 * @param {} val
 * @param {} isJudgeCon
 */
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
	var sel= p.find("select");	
	sel.each(function(){
		var me = $(this),
			name = me.attr("name");
		if((name=="judgeCondition")==isJudgeCon){
				me.val(val);
		}
	});
	//处理变量问题
	if(isJudgeCon){
		sel.trigger("change");
	}
};


/**
 * 字段选择事件
 */
function flowVarChange(){
	var me = $(this),
		nobr = me.parents("div.nobr"),
		option = me.find("option:selected");
	
	if(!option.val())return;		
	
	var	optType = getFlowVarType(option),
		datefmt = option.attr("datefmt"),
		judgeCon = null;
	
	var judgeDiv = $("div.judge",nobr).empty(),
					//获取判定条件
		judgeExp = getJudgeExp(optType,option);
	
	judgeDiv.append(judgeExp);
	//数字或日期则为多个
	if(optType==1||optType==3){
		var judgeExp2 = judgeExp.clone(true).removeClass("judgeExp").addClass("judgeExp2");
		judgeDiv.append(judgeExp2);
	}
};

/**
 * 判断条件的改变
 */
function judgeConditionChange(){
	var me = $(this),
		judgeConditionSpan =me.parent(),
		judgeValueSpan =judgeConditionSpan.next(".judge-value"),
		type =judgeValueSpan.attr("type"),
		opttype =judgeConditionSpan.parent().attr("opttype");
	if(opttype != "5")
		return ;
	if(me.val() == 1 || me.val() ==2){
		if(type == 1)
			return ;
		var option =	judgeConditionSpan.parent().parent().parent().find("option:selected");
		var	optType = getFlowVarType(option);
		var input = getInput(optType,option);
		judgeValueSpan.remove();
		judgeConditionSpan.parent().append(input);
	}else{
		if(type == 2)
			return ;
			var commonVar =$('#commonVar').clone(true).removeAttr("id");
			judgeValueSpan.remove();
			judgeConditionSpan.parent().append(commonVar);
	}	
}


/**
 * 获取字段类型类型识别码
 *	1、 数字标识
 *	2、字符串标识
 *	3、 日期标识
 *	4、数据字典标识
 *  5、选择器标识
 *
 */
function getFlowVarType(opt){
	var value = opt.val(),
		ctltype = opt.attr("ctltype"),
		ftype = opt.attr("ftype");
	
	//首先判断控件类型
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

/**
 * 获取判定值表达式
 * @param {} optType
 * @param {} option
 * @return {}
 */
function getJudgeExp(optType,option){
	var	expDiv = $('<div class="judgeExp left"></div>'),
		judgeCon = $("#judgeCon-"+optType).clone(true).removeAttr("id");
	if(!judgeCon||judgeCon.length==0){
		judgeCon = $("#judgeCon-1").clone(true).removeAttr("id");
	}
		var selVal = judgeCon.find('select').val();
	expDiv.attr("optType",optType).append(judgeCon);
	var	input = getInput(optType,option);
	expDiv.append(input);
	return expDiv;
};

/**
 * 获取判定值表达式
 * @param {} optType
 * @param {} option
 * @return {}
 */
function getInput(optType,option){
	var input;
	switch(optType){
		case 1:
		case 2:
			input = $("#normal-input").clone(true).removeAttr("id");
			break;
		case 3:
			var datefmt = option.attr("datefmt");
			if($.isEmpty(datefmt))
				datefmt ='yyyy-MM-dd';
			input = $("#date-input").clone(true).removeAttr("id").attr("datefmt",datefmt);
			break;
		case 4:
			input = getDicControl(option);
			break;
		case 5:
			input = getSelector(option).children();
			break;
	}
	
	return input;
	
}

/**
 * 获取字典类型的 条件值 控件
 * @param {} option
 * @return {}
 */
function getDicControl(option){
	var value = option.val(),
		ctltype = option.attr("ctltype"),
		chosenopt = option.attr("chosenopt"),
		opts = eval("("+chosenopt+")"),
		html = '',
		data = [],
		type = "";
	var tempHtml = $("#dic-radio-checkbox").val();
	//控件13或者14都为多选
	if(ctltype.toString()=='13'||ctltype.toString()=='14')
		type = "checkbox";
	else{
		tempHtml = $("#dic-select").val();
	}
	
	for(var i=0,c;c=opts[i++];){
		data.push({type:type,name:value,option:c.key,memo:c.value});
	}
	
	html = easyTemplate(tempHtml,data).toString();
	return $(html);
};

/**
 * 获取不同类型的选择器
 * @param {} option
 * @return {}
 */
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



/**
 * 获取判定值
 * @param {} p
 * @return 
 */
function getJudgeValue(p){
	if(!p)return '';
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

/**
 * 获取判定text
 * @param {} p
 * @return {String}
 */
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

/**
 * 添加条件或脚本 
 * @param {} ruleType 1、条件；2、脚本
 */
function addDiv(ruleType){
	$("#ruleDiv").linkdiv("addDiv",{ruleType:ruleType});
};

/**
 * 删除条件
 */
function removeDiv(){
	$("#ruleDiv").linkdiv("removeDiv");	
};

/**
 * 组合条件
 */
function assembleDiv(){
	$("#ruleDiv").linkdiv("assembleDiv");
};

/**
 * 拆分条件
 */
function splitDiv(){
	$("#ruleDiv").linkdiv("splitDiv");
};

/**
 * 获得数据
 * @return {}
 */
function getData(){
	return $("#ruleDiv").linkdiv("getData");
};



/**
 * 保存
 */
function save(){
	var condition = "",
		name = $('#name'),
		nameV=name.val(),
		key = $('#key'),
		keyV=key.val(),
		type = $('#type').val(),
		rtn={};
	if(nameV == "" || keyV==""){
		alert("请输入过滤名称或key！");
		return;
	}
	if(type ==2){
		editor.save();
		condition = $('#sql').val();
	}else{
		condition = getData();
	}
	rtn.condition=condition;
	rtn.name=nameV;
	rtn.key =keyV;
	rtn.type =type;
	//window.returnValue= rtn;
	dialog.get('sucCall')(rtn);
	dialog.close();
}


function getPingyin(inputObj){
	var input=$(inputObj).val();
	if($.trim(input).length<1) return;
	var py =Share.getPingyin({input:input});
	if($.trim($('#key').val()).length>1) return;
	$('#key').val(py);
}