/**
 * 下拉选项模版。
 */
var optiontemplate = '<option value="#value">#text</option>';

/**
 * 字段类型数据
 */
var varchar_="varchar";
var clob_="clob";
var date_="date";
var number_="number";


/**
 * 控件类型。
 *16是隐藏域
 *4 用户单选,8,用户多选,
 *17,角色单选,5,角色多选,
 *18,组织单选,6,组织多选
 *19,岗位单选,7,岗位多选
 *
 */
var controlList = [ {key : '1',value : '单行文本框'}, {key : '2',value : '多行文本框'},
                    {key : '10',value : '富文本框'}, {key : '3',value : '数据字典'}, 
                    {key : '4',value : '人员选择器(单选)'}, {key : '8',value : '人员选择器(多选)'}, 
                    {key : '17',value :'角色选择器(单选)'},{key : '5',value : '角色选择器(多选)'}, 
                    {key : '18',value : '组织选择器(单选)'},{key : '6',value : '组织选择器(多选)'}, 
                    {key : '19',value : '岗位选择器(单选)'}, {key : '7',value : '岗位选择器(多选)'},
                    {key : '16',value : '隐藏域'},{key : '9',value : '文件上传'},
                    {key : '11',value : '下拉选项'},{key : '13',value : '复选框'},
                    {key : '14',value : '单选按钮'},{key : '12',value : 'office控件'},
                    {key : '15',value : '日期控件'},{key : '20',value : '流程引用'},
                    {key : '21',value : 'WebSign控件'},{key : '22',value : '图片展示控件'}
                    ];

/**
 * 是否外部表。
 */
var isExternal=0;
/**
 * 值来源。
 */
var varFromList=[{key:0,value:'表单输入'},{key:1,value:'脚本运算(显示)'},
                 {key:2,value:'脚本运算(不显示)'},{key:3,value:'流水号'}];


/**
 * 判断字段名唯一
 */
jQuery.validator.addMethod("uniqueName", function(value, element) {
	var rtn=TableRow.fieldManage.isFieldExist(value);
	return !rtn;
}, '字段已存在');

jQuery.validator.addMethod("word", function(value, element) {
	return /^[A-Za-z]{1}([a-zA-Z0-9_]+)?$/gi.test(value);
}, '只能为字母开头,允许字母、数字和下划线');

jQuery.validator.addMethod("quotation", function(value, element) {
	return /^[^'|"]*$/im.test(value);
	
}, '不能有引号');


/**
 * 数据表验证器。
 * @returns
 */
function validTable(){
	var __valid__=$("#bpmTableForm").validate({
		rules: {
			name:{
				required:true,
				maxlength:20,
				word:true
			},
			comment:{
				maxlength:200,
				quotation:true
			}
		},
		messages: {
			name:{
				required:'表名必填',
				maxlength:'表名最多 20 个字符.'
			},
			comment:{
				maxlength:'注释 最多200字符.'
			}
		}
	});
	return __valid__;
}


/**
 * 验证字段填写是否正确。
 * @returns
 */
function validateField(){
	var __valid__ = $('#frmFields').validate({
		rules : {
			fieldDesc:{
				quotation:true
			},
			fieldName : {
				required : true,
				uniqueName : __isFieldAdd__,
				word: true
			},
			charLen : {
				required : true,
				digits : true,
				range:[1,2000]
			},
			intLen : {
				required : true,
				digits : true,
				range:[1,18]
				
			},
			decimalLen : {
				required : true,
				digits : true,
				range:[0,6]
			}
		},
		messages : {
			fieldName : {
				required : '字段名称必填'
			},
			charLen : {
				required : '文字长度必填',
				digits:'填写数字'
			},
			intLen : {
				required : '整数长度必填',
				digits: '填写数字'
			},
			decimalLen : {
				required : '小数长度必填',
				digits: '填写数字'
			}
		}
	});
	return __valid__;

}


/**
 * 初始点击主表，子表选项按钮。
 */
function handIsMain(){
	$("input[name='isMain']").click(function(){
		var curIsMain = $(this).val();
		var objTr=$("#spanMainTable");
		(curIsMain==1)?objTr.hide():objTr.show();
		handIsList(curIsMain);
	});
}

/**
 * 是主表，显示显示到列表列，否则不显示
 */
function handIsList(curIsMain){
	var objTh=$("th[name='Listth']");
	var objTd=$("td[name='Listtd']");
	if(curIsMain==1){
		objTh.show();
		objTd.show();
	}else{
		objTh.hide();
		objTd.hide();
	}
}


/**
 * 处理【值来源】的change事件。
 */
function handValueFrom(){
	$("#valueFrom").change(function(){
		var ft=$("#fieldType").val();
		var vf=$(this).val();
		//处理控制列表的显示
		hdlControlTypeShow(ft,vf);
		var ct=$("#controlType").val();
		hdlShowCurUserTrShow(ft,vf,ct);
		hdlShowCurOrgTrShow(ft,vf,ct);
		hdlShowCurPosTrShow(ft,vf,ct);
		hdlDictTrShow(ft,vf,ct);
		hdlIdentityTrShow(ft,vf,ct);
		hdlScriptTrShow(ft,vf,ct);
		hdlScriptIdTrShow(ft,vf,ct);
		hdlRuleTrShow(ft,vf,ct);
		hdlOptionTrShow(ft,vf,ct);
		hdlIsReferenceShow(vf,ct);
//		hdlShowComdifyShow(ft);
	});
}

/**
 * 判断控件类型是否为选择器。
 * @param ctlType
 * @returns {Boolean}
 */
function isExecutorSelector(ctlType){
	ctlType=parseInt(ctlType);
	if(ctlType==4 || ctlType==8 || ctlType==17 || ctlType==5 || ctlType==18 
			|| ctlType==6 || ctlType==19 || ctlType==7 ||  ctlType==20)
		return true;
	return false;
}


/**
 * 处理【字段类型】(fieldType)的change事件。
 */
function handFieldType(){
	$("#fieldType").change(function(){
		var val=$(this).val();
		if(val==varchar_ || val==clob_ ||  val==number_){
			$("#trControlType").show();
		}		
		//处理条件。
		handCondition();
		//处理数据长度
		var controlType=$("#controlType").val();
		if(val==varchar_){
			$("#spanCharLen").show();
			$("#spanIntLen,#spanDecimalLen,#spanDateFormat").hide();
			$("#showComdify,#spanCoin,#spanCoinType").hide();
			//下拉框，复选框，单选按钮
			if(controlType=="11" || controlType=="13" || controlType=="14"){
				$("#trOption").show();
			}
			else{
				$("#trOption").hide();
			}
		}else if(val==number_){
			$("#spanCharLen,#trOption,#spanDateFormat").hide();
			$("#spanDecimalLen,#spanIntLen,#spanCoin").show();
			$("#showComdify").show();
			$("#showidentity").hide();
			//下拉框，复选框，单选按钮
			if(controlType=="11" || controlType=="13" || controlType=="14"){
				$("#trOption").show();
			}
			else{
				$("#trOption").hide();
			}
		}else if(val==date_){
			$("#spanCharLen,#spanIntLen,#spanDecimalLen,#trOption").hide();
			$("#spanDateFormat").show();
			$("#showComdify,#spanCoin,#spanCoinType").hide();
			$("#showidentity").hide();
		}
		else{
			$("#spanCharLen,#spanIntLen,#spanDecimalLen,#trOption,#spanDateFormat").hide();
			$("#showComdify,#spanCoin,#spanCoinType").hide();
			$("#showidentity").hide();
		}
		//验证规则
		if(val ==varchar_ || val==clob_){
			$("#trRule").show();
		}
		else{
			$("#trRule").hide();
		}
		
		//设置值来源
		setValueFromByFieldType(val);
		//设置控件类型
		setControlByType(val);
		//脚本隐藏
		$("#trScript").hide();
		$("#trScriptID").hide();
		//处理条件
		
		
		//处理表单用户,组织，岗位
		$('#showCurUserTr').hide();
		$('#showCurOrgTr').hide();
		$('#showCurPosTr').hide();
		//设置流程变量
		var controlType = $("#controlType").val();
		setShowFlowVar(controlType);
	});
}
/**
 * 处理复选框【是否作为查询条件】(isQuery)点击事件。
 */
function handConditionClick(){
	$("#isQuery").click(handCondition);
}

/**
 * 处理【查询条件】选择的点击处理。
 */
function handCondition(){
	var obj=$("#isQuery");
	var isChecked=obj.attr("checked")=="checked";
	if(isChecked){
		var selObj=$("#selCondition");
		var fieldType=$("#fieldType").val();
		//initCondition(selObj,fieldType);
		if(fieldType!==clob_){
			//$("#trCondition").show();
		}
		else{
			//$("#trCondition").hide();
			obj.removeAttr("checked");
		}	
	}else{
		//$("#trCondition").hide();
	}
}

/**
 * 处理货币复选框【coin】事件
 */
function handCoinClick(){
	var obj=$("#coin");
	obj.click(handCoin);
	
}

function handCoin(){
	var obj=$("#coin");
	var comdifyObj=$("#isShowComdify");
	var isChecked=obj.attr("checked")=="checked";
	if(isChecked){
		var fieldType=$("#fieldType").val(); 
		if(fieldType ==number_){
			$("#spanCoinType").show();
			$("#decimalLen").val(2);
			comdifyObj.attr("checked",true);
			comdifyObj.attr('disabled', true);
		}else {
			$("#spanCoinType").hide();
			$("#decimalLen").val(0);
			obj.removeAttr("checked");
			comdifyObj.removeAttr("checked");
			comdifyObj.attr('disabled', false);
		}
		
	}else{
		$("#spanCoinType").hide();
		$("#decimalLen").val(0);
		comdifyObj.attr('disabled', false);
		comdifyObj.attr("checked", false);
	}
}



/**
 * 改变值的长度
 * @param len
 */
function changeCharLen(len){
	$("#charLen").val(len);
}

/**
 * 处理【控件类型】（ControlType）修改事件。
 */
function handControlType(){	
	//控件类型修改
	$("#controlType").change(function(){
		var val=parseInt($(this).val());
		$("#formUserTr").hide();
		$("#spanDateFormat").hide();
		//设置流程变量
		setShowFlowVar(val);

		if(val==15){
			$("#spanDateFormat").show();
		}
		
		var ft=$("#fieldType").val();
		var vf=$("#valueFrom").val();
		var ct=$(this).val();
		hdlShowCurUserTrShow(ft,vf,ct);
		hdlShowCurOrgTrShow(ft,vf,ct);
		hdlShowCurPosTrShow(ft,vf,ct);
		hdlDictTrShow(ft,vf,ct);
		hdlIdentityTrShow(ft,vf,ct);
		hdlScriptTrShow(ft,vf,ct);
		hdlScriptIdTrShow(ft,vf,ct);
		hdlRuleTrShow(ft,vf,ct);
		hdlOptionTrShow(ft,vf,ct);
		hdlIsReferenceShow(vf,ct);
		hdlCharLen(ct);
		hdlIsWebSignShow(ft,vf,ct);
		hdlShowUpLoadTrShow(ft,vf,ct);
	});
}



/**
 * 根据值来源和控件类型控制 超连接 选项 显示
 * @param vf
 * @param ct
 * @param value
 */
function hdlIsWebSignShow(ft,vf,value){
	$("#isWebSign").removeAttr("disabled");   //恢复之前的禁用WEB签章
	//Office和WEB签章
	if(value==12||value==21){
		$("#trRule").hide();
		$("#trDict").hide();
		$("#trScript").hide();
		$("#trScriptID").hide();
		$("#trOption").hide();
		$("#showCurUserTr").hide();
		$("#showCurOrgTr").hide();
//		$("#showCurUserTr").hide();
//		$("#showCurOrgTr").hide();	
		$("#spanDateFormat").hide();
		$("#isWebSign").removeAttr("checked");    //去掉WEB签章验证的选中
		$("#isWebSign").attr("disabled","disabled");  //禁用WEB签章验证
	}
};




/**
 * 根据【字段类型】设置控件类型。
 * @param fieldType 【字段类型】
 */
function setControlByType(fieldType){
	var objSelect=$('#controlType');
	objSelect.empty();
	//控件列表。
	$(controlList).each(function(i, d) {
		var option = optiontemplate.replaceAll('#value', d.key).replace('#text', d.value);
		//文本类型
		if(fieldType==varchar_){
			if(d.key!="10" )
				objSelect.append(option);
		}else if(fieldType==clob_){
			//富文本框和文件类型
			if(d.key=="2" || d.key=="10"  )
				objSelect.append(option);
		}else if(fieldType==date_ ){
			if(d.key=="1" || d.key=="16")
				objSelect.append(option);
		}
		else if(fieldType==number_){
			//数字
			if(d.key=="1"|| d.key=="11" || d.key=="16"  ){//单行文本框、下拉选项、隐藏域
				objSelect.append(option);
			}
		}
	});
}

/**
 * 设置字段来源。
 * @param fieldType
 */
function setValueFromByFieldType(fieldType){
	var objSelect=$('#valueFrom');
	objSelect.empty();
	$(varFromList).each(function(i, d) {
		var key=d.key;
		var option = optiontemplate.replaceAll('#value', key).replace('#text', d.value);
	
		//文本
		if(fieldType==varchar_){
			objSelect.append(option);
		//数字
		}else if(fieldType==number_){
			if(key!=3){
				objSelect.append(option);
			}
		//大文本
		}else if(fieldType==clob_){
			if(key==0){
				objSelect.append(option);
			}
		//日期	
		}else{
			if(key!=3){
				objSelect.append(option);
			}
		}
	});
	
}

/**
 * 添加列时初始化窗体的界面。
 * 设置数据。
 */
function initAdd(_isExternal){
	isExternal=_isExternal;
	$("#spanIntLen,#spanCoin,#spanCoinType,#spanDecimalLen,#spanDecimalLen,#showidentity,#trDict,#trScript,#trScriptID,#trOption,#spanDateFormat,#trCondition").hide();
	setControlByType("varchar");
	//动态加载数据字典。
	JsLoader.LoadCount=1;
	JsLoader.Load(__ctx +"/js/lg/plugins/htCatCombo.js","javascript1");
	var fieldType=$("#fieldType").val();
	if(fieldType=='number'){
		$("#showComdify").show();
		$("#spanCoin").show();
	}else{
		$("#showComdify").hide();
		$("#spanCoin").hide();
	}
}

/**
 * 重置字段。
 */
function resetField(){
	$("#spanIntLen,#spanDecimalLen,#spanDecimalLen,#trDict,#trScript,#trScriptID,#trOption,#spanDateFormat,#trCondition,#showIsReference,#showidentity,#trUpLoad,#fromTypeTr,#fromTypeScriptTr").hide();
	setControlByType("varchar");
	$("#fieldName,#fieldDesc").val("");
	$("#isRequired,#isFlowVar,#isQuery,#isReference,#isShowComdify,#coin,#isCurrentDate,#showCurUser,#showCurOrg,#showCurPos,#isWebSign,#isShowidentity").attr("checked",false);
	$("#isList").attr("checked","checked");
	$("#fieldType").val("varchar").change();
	$("#charLen").val(100);
	$("#intLen").val(13);
	$("#decimalLen").val(0);
	$("tr.normalTr",$("#option-table")).remove();
	$("tr.editable-tr",$("#option-table")).remove();
	//设置日期
	$("#selDateFormat").val("yyyy-MM-dd");
	//重置验证规则
	$("#validRule").get(0).selectedIndex=0;
	$("#identityName,#identityAlias").val("");
	$("#script,#scriptID,#fromScript").val("");
	$("#isShowComdify").attr('disabled', false);
	$("#fromType").val("all");
}

/**
 * 设置字段的长度。
 */
function setFieldLengthByFieldValue(filed){
	if(filed.fieldType==varchar_){
		var charLen=parseInt( $("#charLen").val());
		filed.charLen=charLen;
	}
	else if(filed.fieldType==number_){
		var intLen=parseInt($("#intLen").val());
		var decimalLen=parseInt($("#decimalLen").val());
		filed.intLen=intLen;
		filed.decimalLen=decimalLen;
	}
}
/**
 * 根据值来源设置相应的字段。
 * @param field
 */
function setFieldByValueFrom(field){
	
	var from=parseInt( field.valueFrom);
	
	switch(from){
		//表单
		case 0:
			break;
		//1,2脚本
		case 1:
		case 2:
			var ctlType=$("#controlType").val();
			var rtn=isExecutorSelector(ctlType);
			if(rtn){
				field.scriptID=$("#scriptID").val();
			}
			field.script=$("#script").val();
			break;
		//流水号
		case 3:
			field.identity=$("#identityAlias").val();
			field.script=$("#identityName").val();
			break;
	}
}



/**
 * 根据字段信息设置控件长度。
 * @param field
 */
function setFieldLengthByField(field){
	var fieldType=field.fieldType;
	switch(fieldType){
		case varchar_:
			$("#charLen").val(field.charLen);
			$("#spanCharLen").show();
			$("#spanIntLen,#spanDecimalLen,#showComdify,#spanCoin,#spanCoinType").hide();
			break;
		case number_:
			$("#intLen").val(field.intLen);
			$("#decimalLen").val(field.decimalLen);
			$("#spanCharLen").hide();
			$("#spanIntLen,#spanDecimalLen,#showComdify,#spanCoin").show();
			break;
		default:
			$("#spanCharLen,#spanIntLen,#spanDecimalLen,#showComdify,#spanCoin,#spanCoinType").hide();
			break;
	}
	
}


/**
 * 根据数据来源，设置相关控件的状态。
 * @param field
 */
function setValueFromByField(field){
	$("#valueFrom").val(field.valueFrom);
	var from=parseInt(field.valueFrom);
	switch(from){
		//表单输入
		case 0:
			$("#trScript,#trScriptID").hide();
			break;
		//脚本输入
		case 1://显示脚本
		case 2://隐藏脚本
			var controlType=field.controlType;
			if(isExecutorSelector(controlType)){
				$("#trScriptID").show();
				$("#scriptID").val(field.scriptID);
			}
			$("#trScript").show();
			$("#showidentity").hide();
			$("#script").val(field.script);
			break;
		//流水号
		case 3:
			$("#trScript").hide();
			$("#trScript,ID").hide();
			$("#showidentity").show();
			$("#identityAlias").val(field.identity);
			$("#identityName").val(field.identityName);
			break;
	}
}

/**
 * 设置验证规则
 * @param field
 */
function setValidRuleByField(field){
	var validRule=field.validRule;
	
	if(field.fieldType==varchar_ || field.fieldType==clob_){
		$("#trRule").show();
		$("#validRule").val(validRule);
	}
	else{
		$("#trRule").hide();
	}
}

/**
 * 设置字段的验证规则。
 * @param field
 */
function setFieldByValidRule(field){
	if(field.fieldType==varchar_ || field.fieldType==clob_ ){
		field.validRule=$("#validRule").val();
	}
}

function getOptionData(){
	var optionAry = [];
	$("tr.editable-tr",$("#option-table")).each(function(){
		var	me = $(this),
			optionKey = $("input[name='optionKey']",me).val();
		var optionValue = [];
		$("input.long",me).each(function(){
			var me = $(this),
		      name = me.attr("name"),
		      val = me.val(),
		      memo = me.attr("title");
			optionValue.push({lantype:name,lanres:val,lanmemo:memo});
		});
		optionAry.push({key:optionKey,value:optionValue});
	});
	return optionAry;;
};

//兼容3.2版本
function getOptionData1(){
	var optionAry = [];
	$("tr.editable-tr",$("#option-table")).each(function(){
		var	me = $(this),
			optionKey = $("input[name='optionKey']",me).val(),
			optionValue = $("input[name='optionValue']",me).val();
		optionAry.push({key:optionKey,value:optionValue});
	});
	return optionAry;
};


/**
 * 从页面控件获取字段数据对象。(编辑)
 * @returns 
 */
function getField(){
	
	var field={charLen:0,intLen:0,decimalLen:0,dictType:'',identity:'',validRule : '',isDeleted:0,
	valueFrom : 0,script:'',controlType : 1};
	field.fieldName=$("#fieldName").val();
	field.fieldDesc=$("#fieldDesc").val();
	field.fieldType=$("#fieldType").val();
	
	field.controlType=$("#controlType").val();
	

	//设置3：数据字典
	if(field.controlType==3){
		field.dictType=$("#dictType").val();
	}
	
	
	//控件类型为下拉框,复选框,单选按钮。
	//11,下拉选项
	//13,复选框
	//14,单选按钮
	if(field.controlType==11 || field.controlType==13 || field.controlType==14){
		var inputValues = $("input[name='optionValue']",$("#option-table")),
			optsAry;
		if(inputValues.length>0){//3.2版本
			optsAry = getOptionData1();
		}else{
			optsAry = getOptionData();
		}
		field.options = JSON2.stringify(optsAry);
	}
	//设置日期格式
	//数据类型为日期的时候，需要设置日期格式。
	//15,日期控件
	if(field.controlType==15 || field.fieldType==date_){
		var isCurrentDate=$("#isCurrentDate").attr("checked");
		var format=$("#selDateFormat").val().trim();
		var json;
		if(isCurrentDate!=undefined){
			json={"format":format,"displayDate":1};
		}else{
			json={"format":format,"displayDate":0};
		}
		field.ctlProperty=JSON2.stringify(json);
	}
	
	// 处理人员选择器和显示当前用户
	if(field.controlType==4|| field.controlType==8){
		var json={};
		if(field.controlType==4){
			var showCurUser=$("#showCurUser:checked").val()? 1:0;
			json.showCurUser = showCurUser;
		}
		var opt = $("#fromType").find('option:selected');
		var fromType = opt.attr("type");
		var typeVal = null;
		if(fromType == 'script'){
			typeVal = $("#fromScript").val();
		}else{
			typeVal = opt.val();
		}
		var scope ={};
		scope.type = fromType;
		scope.value = typeVal;
		json.scope = scope;
		field.ctlProperty=JSON2.stringify(json);
	}
	
	// 显示当前组织
	if(field.controlType==6 ||field.controlType==18){
		var json={};
		if(field.controlType==18){
			var showCurOrg=$("#showCurOrg:checked").val()? 1:0;
			json={"showCurOrg":showCurOrg};	
		}
		var opt = $("#fromType").find('option:selected');
		var fromType = opt.attr("type");
		var typeVal = null;
		if(fromType == 'script'){
			typeVal = $("#fromScript").val();
		}else{
			typeVal = opt.val();
		}
		var scope ={};
		scope.type = fromType;
		scope.value = typeVal;
		json.scope = scope;
		field.ctlProperty=JSON2.stringify(json);
	}
	// 显示当前岗位
	if(field.controlType==19 || field.controlType==7){
		var json={};
		if(field.controlType==19){
			var showCurPos=$("#showCurPos:checked").val()?1:0;
			json={"showCurPos":showCurPos}
		}
		var opt = $("#fromType").find('option:selected');
		var fromType = opt.attr("type");
		var typeVal = null;
		if(fromType == 'script'){
			typeVal = $("#fromScript").val();
		}else{
			typeVal = opt.val();
		}
		var scope ={};
		scope.type = fromType;
		scope.value = typeVal;
		json.scope = scope;
		field.ctlProperty=JSON2.stringify(json);
	}
	//必填
	field.isRequired=$("#isRequired").attr("checked")?1:0;
	//显示到列表
	field.isList=$("#isList").attr("checked")?1:0;
	//作为查询条件
	field.isQuery=$("#isQuery").attr("checked")?1:0;
	//是否流程变量
	field.isFlowVar=$("#isFlowVar").attr("checked")?1:0;
	//是否Web签章
	field.isWebSign=$("#isWebSign").attr("checked")?1:0;
	//是否直接上传文件
	if(field.controlType==9) {
		var isDirectUpLoad=$('#isDirectUpLoad').attr('checked')?1:0;
		json={"isDirectUpLoad":isDirectUpLoad}
		field.ctlProperty=JSON2.stringify(json);
	}
	//是否作为超链接
	if(isExecutorSelector(field.controlType)){
		field.isReference=$("#isReference").attr("checked")?1:0;
		//json={"showCurPos":showCurPos}
		//field.ctlProperty=JSON2.stringify(json);
	}
	
	
	if(field.fieldType==number_){
		var showCoin=$("input[name='coin']:checked").val();
		var showComdifyValue=$("#isShowComdify").attr("checked")?1:0;
		var json={};
		if(showCoin){
			var coinValue=$("#CoinType").val();
			json.coinValue=coinValue;
			json.isShowComdify=showComdifyValue;
			json.decimalValue=parseInt($("#decimalLen").val());
			field.ctlProperty=JSON2.stringify(json);
		}else{
			json.coinValue="";
			json.isShowComdify=showComdifyValue;
			json.decimalValue=parseInt($("#decimalLen").val());
			field.ctlProperty=JSON2.stringify(json);
		}
	}
	
		
		
//		if($("input[name='isShowComdify']:checked").val()){
//		json={"isShowComdify":true};
//		field.ctlProperty=JSON2.stringify(json);
//	}else{
//		json={"isShowComdify":false};
//		field.ctlProperty=JSON2.stringify(json);
//	}
//	
	
//	field.ccisread=$("#ccisread").attr("checked")?1:0;
//	field.isShowComdify=$("#isShowComdify").attr("checked")?1:0;
	//field.isAllowMobile=$('#isAllowMobile').attr('checked')?1:0;
	//设置字段长度
	setFieldLengthByFieldValue(field);
	//值来源
	field.valueFrom =$("#valueFrom").val();
	//值来源为流水号
	if(field.valueFrom==3){
		field.controlType=1;
		//isShowidentity  流水号是否显示在启动流程页面中  1=显示，  0=不显示
		if($("input[name='isShowidentity']:checked").val()){
			json={"isShowidentity":1};
			field.ctlProperty=JSON2.stringify(json);
		}else{
			json={"isShowidentity":0};
			field.ctlProperty=JSON2.stringify(json);
		}
	}
	//根据来源设置对应的属性值。
	setFieldByValueFrom(field);
	//设置验证规则
	setFieldByValidRule(field);
	//设置条件
	setCondition(field);
	//设置表单变量
	setShowFlowVar(field.controlType);
	return field;
}

/**
 * 控件类型或者字段类型
 * 限制表单变量
 */
function setShowFlowVar(controlType){
	//附件，office
	if(isHideFlowVar(controlType)){
		$("#showFlowVar").hide()
		$("#isFlowVar").attr("checked",false);
	}else{
		$("#showFlowVar").show();
	}		
}

function isHideFlowVar(controlType){
	if($.isEmpty(controlType)) 	return false;
	if(controlType==2 || controlType == 9 || controlType==10 || controlType== 12 || controlType== 21) return true;
	return false;
}

/**
 * 设置条件
 */
function setCondition(field){
	if(field.isQuery==0){
		return;
	}
	var condition=$("#selCondition").val();
	var condValFrom=$("#selValueFrom").val();
	var condValue=$("#selValInput").val();
	if(field.ctlProperty!=null && field.ctlProperty!=""){
		var json=jQuery.parseJSON(field.ctlProperty);
		json.condition=condition;
		json.condValFrom=condValFrom;
		json.condValue=condValue;
		field.ctlProperty=JSON2.stringify(json) ;
	}
	else{
		var json={
				condition:condition,
				condValFrom:condValFrom,
				condValue:condValue
		};
		field.ctlProperty=JSON2.stringify(json);
	}
}

function initNumber(field){
	if(field.fieldType=='number'){
		$("#showComdify").show();
		$("#spanCoin").show();
		try{
			
			var property=eval("(" + field.ctlProperty +")");
			$("#isShowComdify").attr("checked",property.isShowComdify);
			if(property.coinValue!=null && property.coinValue!=""){
				$("#isShowComdify").attr('disabled', true);
				$("#spanCoinType").show();
				$("#CoinType").val(property.coinValue);
				$("#coin").attr("checked","checked");
			}
		}catch(e){
		}
	}else{
		$("#showComdify").hide();
		$("#spanCoin").hide();
		$("#spanCoinType").hide();
	}
}


/**
 * 根据字段设置页面控件状态。
 */
function initControlByField(field,allowEditColName,_isExternal){
	//设置是否外部表。
	isExternal=_isExternal;
	$("#fieldName").val(field.fieldName);
	$("#fieldDesc").val(field.fieldDesc);
	$("#fieldType").val(field.fieldType);
	initNumber(field);
		 
	//设置字段选项。
	$("#isRequired").attr("checked",field.isRequired==1);
	$("#isList").attr("checked",field.isList==1);
	$("#isQuery").attr("checked",field.isQuery==1);
	$("#isFlowVar").attr("checked",field.isFlowVar==1);
	//$("#isAllowMobile").attr("checked",field.isAllowMobile==1);
	$("#isReference").attr("checked",field.isReference==1);
	$("#isWebSign").attr("checked",field.isWebSign==1);
//	$("#isReference").attr("checked",field.isReference==1);
//	$("#ccisread").attr("checked",field.ccisread==1);
	
	//设置数据长度
	setFieldLengthByField(field);

	//日期类型
	if(field.fieldType==date_ || field.controlType==15){
		$("#spanDateFormat").show();
		try{
			var property=eval("(" + field.ctlProperty +")");
			$("#selDateFormat").val(property.format);
			if(property.displayDate==1){
				$("#isCurrentDate").attr("checked","checked");
			}
		}catch(e){
		}
	}
	
	//设置条件字段
	bindCondition(field);
	//设置表单变量
	setShowFlowVar(field.controlType);
	
	//修改控件是否允许编辑字段的名字和数据类型。
	setEditStatus(allowEditColName);
	//渲染数据字典。
	JsLoader.LoadCount=1;
	JsLoader.Load(__ctx + "/js/lg/plugins/htCatCombo.js","javascript1");
	
	var ft=field.fieldType;
	var vf=field.valueFrom;
	var ct=field.controlType;
	
	hdlValueFromShow(ft,vf);
	hdlControlTypeShow(ft,vf,ct);
	hdlIdentityTrShow(ft,vf,ct,field.identity,field.script);
	hdlScriptTrShow(ft,vf,ct,field.script);
	hdlScriptIdTrShow(ft,vf,ct,field.scriptID);
	hdlOptionTrShow(ft,vf,ct,field.options);
	hdlRuleTrShow(ft,vf,ct,field.validRule);
	hdlDictTrShow(ft,vf,ct,field.dictType);
	hdlIsReferenceShow(vf,ct,field.isReference);
	var curUser=null,curOrg=null,curPos=null,scope=null;
	if(field.ctlProperty){
		var prop = $.parseJSON(field.ctlProperty);
		curUser=prop.showCurUser||null;
		curOrg=prop.showCurOrg||null;
		curPos=prop.showCurPos||null;
		scope = prop.scope||null;
	}
	hdlShowCurUserTrShow(ft,vf,ct,curUser,scope);
	hdlShowCurOrgTrShow(ft,vf,ct,curOrg,scope);
	hdlShowCurPosTrShow(ft,vf,ct,curPos,scope);
	hdlIsWebSignShow(ft,vf,ct);
	var isDirectUpLoad=null;
	var isShowidentity=null;
	if(field.ctlProperty){
		var prop = $.parseJSON(field.ctlProperty);
		isDirectUpLoad=prop.isDirectUpLoad||null;
		isShowidentity=prop.isShowidentity||null;
		$("#isShowidentity").attr("checked",isShowidentity==1);
	}
	hdlShowUpLoadTrShow(ft,vf,ct,isDirectUpLoad);
}

/**
 * 设置控件状态，是否允许编辑。
 * @param allowEditColName
 */
function setEditStatus(allowEditColName){
	//if(allowEditColName) return;	改为不设置只读
	return;
	$("#fieldName").attr('disabled', 'disabled');
	$("#charLen").attr('disabled', 'disabled');
	$("#intLen").attr('disabled', 'disabled');
	$("#decimalLen").attr('disabled', 'disabled');
	$("#fieldType").attr('disabled', 'disabled');
	if(isExternal==0){
		$("#valueFrom").attr('disabled', 'disabled');
		$("#controlType").attr('disabled', 'disabled');
	}
	
	/*
	if(isFlowVar){
		$("#isFlowVar").attr('disabled', 'disabled');	
	}*/
}

/**
 * 绑定表和字段数据。
 * @param table
 */
function bindTable(data,allowEditTbColName,mainTableIsPublished){
	var table=data.table;
	$("#name").val(table.tableName);
	$(":radio[name='deleteType'][value="+table.deleteType+"]").attr("checked","checked");
	//禁止编辑
	if(allowEditTbColName){
		$("#name").attr('disabled', 'disabled');
		$(":radio[name='isMain']").attr('disabled', 'disabled');
		$("#mainTable").attr('disabled', 'disabled');
	}
	
	
	//已生成的表不能再修改 是否主表 字段
	if(table.isPublished){
		$(":radio[name='isMain']").attr('disabled', 'disabled');
	}
	
	$("#comment").val(table.tableDesc);
	$(":radio[name='isMain'][value="+table.isMain+"]").attr("checked","checked");
	//是否是子表
	if(table.isMain==0 ){
		
		if(!allowEditTbColName && table.isPublished == 1)	{
			var href = 'get.ht?canClose=1&hasClose=true&tableId='+table.mainTableId;
			$("a.moreinfo",$("#showMainTable")).attr("hrefstr",href).append(table.mainTableDesc);
			
			$('#showMainTable').show();
			$('#spanMainTable').hide();
		}
		else{
			if(table.isPublished ==0){
				$("div.generate").show();
			}
			if(table.mainTableId==null || table.mainTableId==""  ){
				$('#spanMainTable').show();
				$('#showMainTable').hide();
			}
			else{
				var href = 'get.ht?canClose=1&hasClose=true&tableId='+table.mainTableId;
				$("a.moreinfo",$("#showMainTable")).attr("hrefstr",href).append(table.mainTableDesc);
				$("#showMainTable").show();
			}
			
		}
	}else{
		$('#spanMainTable').hide();
		$('#showMainTable').hide();
	}
	
	//赋给下拉框，这个下拉框只包含未生成的主表列表。
	$("#mainTable").val(table.mainTableId);
	//将主表id赋给隐藏表单。
	$("#mainTableId").val(table.mainTableId);
	if(mainTableIsPublished){
		$("#tableName").val(table.mainTableDesc);
	}
	var conf={showDel:false};
	if(!allowEditTbColName){
		conf.showDel=1;
	}
	var fieldList=data.fieldList;
	TableRow.fieldManage.setFields(fieldList);
	$("#tableColumnItem>tbody").append(TableRow.fieldManage.getHtml(conf));
	handisList();
	//$("#tableColumnItem>tbody tr").data("isAllowEdit",allowEditTbColName);
	if(!allowEditTbColName){
		$("[name=isFlowVar]:checked").each(function(){
			var _this = $(this);
			_this.attr("disabled","disabled");
			_this.data("isFlowVar",true);
		});
	}
}


/**
 * 绑定字段。
 * @param table
 */
function bindExtTable(data,hasForm){
	var table=data.table;
	$("#name").val(table.tableName);
	//禁止编辑
	$("#name").attr('disabled', 'disabled');
	$("#comment").val(table.tableDesc);
	var fieldList=data.fieldList;
	TableRow.setAllowEditColName(false);
	TableRow.fieldManage.setFields(fieldList);
	$("#tableColumnItem>tbody").append(TableRow.fieldManage.getHtml({showDel:false}));
	if(hasForm){
		$("[name=isFlowVar]:checked").each(function(){
			var _this = $(this);
			_this.attr("disabled","disabled");
			_this.data("isFlowVar",true);
		});
	}
	//绑定主键字段下拉框
	bindPkField("pkField",fieldList,table.pkField);
	//绑外键
	bindPkField("relation",fieldList,table.relation);
	var isMain=table.isMain;
	$(":radio[name='isMain'][value="+isMain+"]").attr("checked","checked");
	if(table.isMain==1){
		$("#trSubTable").hide();
	}
	else{
		$("#trSubTable").show();
	}
	
	$("#mainTableId").val(table.mainTableId);
	$("#mainTableName").val(table.mainTableName);
	//绑定流水号下拉框
	bindIdentity(data.identityList,"");
}
/**
 * 绑定主键字段下拉框。
 * @param pkField		控件ID值
 * @param fieldList		字段列表
 * @param defautValue	默认值
 */
function bindPkField(pkField,fieldList,defautValue){
	var obj=$("#" +pkField);
	for(var i=0;i<fieldList.length;i++){
		var field=fieldList[i];
		var option = optiontemplate.replaceAll('#value', field.fieldName).replace('#text', field.fieldDesc);
		obj.append(option);
	}
	
	if(defautValue!=undefined && defautValue!=null && defautValue!=""){
		obj.val(defautValue);
	}
}

/**
 * 绑定流水号下拉框。
 * @param identityList
 * @param defautValue
 */
function bindIdentity(identityList,defautValue){
	var obj=$("#keyValue");
	for(var i=0;i<identityList.length;i++){
		var d=identityList[i];
		var option = optiontemplate.replaceAll('#value', d.alias).replace('#text', d.name);
		obj.append(option);
	}
	if(defautValue!=undefined && defautValue==null && defautValue!=""){
		obj.val(defautValue);
	}
}

/**
 * 绑定字段条件式。
 * @param dbType
 * @param defaultValue
 */
function bindCondition(field){
	if(field.isQuery==0) {
		//$("#trCondition").hide();
		return;
	}
	//$("#trCondition").show();
	var fieldType=field.fieldType;
	var prop=field.ctlProperty;
	var jsonObj=null;
	if(prop!=null && prop!=undefined && prop!=""){
		try{
			jsonObj=eval("(" + prop +")");
		}
		catch(e){
			jsonObj=null;
		}
	}

	var obj=$("#selCondition");
	//initCondition(obj,fieldType);
	var valFrom=$('#selValueFrom');
	var value=$('#selValue');
	if(jsonObj!=null){
		//hack ie6
		setTimeout(function(){
			var condition=jsonObj.condition;
			var condValFrom=jsonObj.condValFrom;
			var condValue=jsonObj.condValue;
			
			obj.val(condition);
			valFrom.val(condValFrom);
			initSelValueFrom(value,condValFrom);
			if(condValFrom==2||condValFrom==3){
				$('#selValInput').val(condValue);
			}else if(condValFrom==1){
				$('#selValInput').html(condValue);
			}
		},100);
	}
	else{
		initSelValueFrom(value,-1);
	}
}

/**
 * 在条件下拉框添加字段。
 * @param selObj
 * @param fieldType
 *//*
function initCondition(selObj,fieldType){
	//在下拉框中设置字段数据类型
	var type=selObj.attr("fieldType");
	if(fieldType==type){
		return;
	}
	selObj.attr("fieldType",fieldType);
	selObj.width(100);
	selObj.empty(); 
	switch(fieldType){
		case "varchar":
			selObj.append("<option value='='>大于</option>");
			selObj.append("<option value='like'>LIKE</option>");
			selObj.append("<option value='likeEnd'>LIKEEND</option>");
			break;
		case "number":
			selObj.append("<option value='='>大于</option>");
			selObj.append("<option value='>='>大于等于</option>");
			selObj.append("<option value='>'>大于</option>");
			selObj.append("<option value='<'>小于</option>");
			selObj.append("<option value='<='>小于等于</option>");
			break;
		case "date":
			selObj.append("<option value='='>大于</option>");
			selObj.append("<option value='between'>'日期之间'</option>");
			break;	
	}
}
*/

function initSelValueFrom(selObj,val){
	switch(val){
		case "1":
			var html="<span id='selValInput'>表单输入</span>";
			selObj.html(html);
			break;
		case "2":
			var html="<textarea id='selValInput' cols='40' rows='3'></textarea>";
			selObj.html(html);
			break;
		case "3":
			var html = "<a href='#' class='link var' title='常用脚本' onclick='selSelectScript(this)'>常用脚本</a></br>";
			html+="<textarea  id='selValInput' cols='40' rows='3'></textarea>"; 
			selObj.html(html);
			break;
		default:
			var html="";
			selObj.html(html);
	}
}

/**
 * 查询条件处理。
 * @param obj
 *//*
function changeSelValFrom(obj){
	var val=$(obj).val();
	var selVal=$("#selValue");
	initSelValueFrom(selVal,val);
}*/

function selSelectScript(obj) {
	var txtObj=$('#selValInput');
	ScriptDialog({
		callback : function(script) {
			txtObj.val(script);
		}
	});
};




/**
 * 根据列类型，处理值来源显示
 * @param fy 字段类型
 */
function hdlValueFromShow(fy,value){
	var varFromList=[];
	switch(fy){
	case varchar_://文本
		var varFromList=[{key:0,value:'表单输入'},{key:1,value:'脚本运算(显示)'},
                 		{key:2,value:'脚本运算(不显示)'},{key:3,value:'流水号'}];
		break;
	case clob_://大文本
		var varFromList=[{key:0,value:'表单输入'}];
		break;
	case date_://日期	
		var varFromList=[{key:0,value:'表单输入'}];
		break;
	case number_://数字
		var varFromList=[{key:0,value:'表单输入'},{key:1,value:'脚本运算(显示)'},
		                 {key:2,value:'脚本运算(不显示)'}];
		break;
	}
	var objSelect=$('#valueFrom');
	objSelect.empty();
	
	$(varFromList).each(function(i, d) {
		var key=d.key;
		var option = $(optiontemplate.replaceAll('#value', key).replace('#text', d.value));
		if(value!=undefined && value!=null && value!=""){
			if(key==value){
				option.attr("selected","selected");	
			}
		}
		objSelect.append(option);
	});
};


/**
 * 根据字段类型、值来源，处理控件的显示
 * @param ft 字段类型
 * @param vf 值来源
 */
function hdlControlTypeShow(ft,vf,value){
	vf=""+vf;
	var controlTypeTr = $("#trControlType");
	var controlType = $("#controlType");
	controlType.empty();
	var controlList=[];
	controlTypeTr.hide();
	
	switch(ft){
	case varchar_://文本
		switch(vf){
		case '0'://表单输入
			controlTypeTr.show();
			controlList =[ {key : '1',value : '单行文本框'}, {key : '2',value : '多行文本框'},
                    {key : '10',value : '富文本框'}, {key : '3',value : '数据字典'}, 
                    {key : '4',value : '人员选择器(单选)'}, {key : '8',value : '人员选择器(多选)'}, 
                    {key : '17',value :'角色选择器(单选)'},{key : '5',value : '角色选择器(多选)'}, 
                    {key : '18',value : '组织选择器(单选)'},{key : '6',value : '组织选择器(多选)'}, 
                    {key : '19',value : '岗位选择器(单选)'}, {key : '7',value : '岗位选择器(多选)'},
                    {key : '16',value : '隐藏域'},{key : '9',value : '文件上传'},
                    {key : '11',value : '下拉选项'},{key : '13',value : '复选框'},
                    {key : '14',value : '单选按钮'},{key : '12',value : 'office控件'},
                    {key : '15',value : '日期控件'},{key : '20',value : '流程引用'},
                    {key : '21',value : 'WebSign控件'},{key : '22',value : '图片展示控件'}
                    ];
			break;
		case '1'://脚本输入 (显示)
		case '2'://脚本输入 (隐藏)
			controlTypeTr.show();
			controlList =  [ {key : '1',value : '单行文本框'}, {key : '2',value : '多行文本框'},
		                  	 {key : '3',value : '数据字典'}, 
		                    {key : '4',value : '人员选择器(单选)'}, {key : '8',value : '人员选择器(多选)'}, 
		                    {key : '17',value :'角色选择器(单选)'},{key : '5',value : '角色选择器(多选)'}, 
		                     {key : '18',value : '组织选择器(单选)'},{key : '6',value : '组织选择器(多选)'}, 
                    		{key : '19',value : '岗位选择器(单选)'}, {key : '7',value : '岗位选择器(多选)'},
                    		{key : '16',value : '隐藏域'},{key : '9',value : '文件上传'},
                    		{key : '11',value : '下拉选项'},{key : '13',value : '复选框'},
		                    {key : '14',value : '单选按钮'},
		                    {key : '15',value : '日期控件'},{key : '20',value : '流程引用'}];
			break;
		case '3'://流水号
			break;
		}
		
		break;
	case clob_://大文本
		switch(vf){
		case '0'://表单输入
			controlTypeTr.show();
			controlList = [ {key : '2',value : '多行文本框'},
                   			{key : '10',value : '富文本框'}];
			break;
		case '1'://脚本输入 (显示)
		case '2'://脚本输入 (隐藏)
		case '3'://流水号
			break;
		}
		
		break;
	case date_://日期
		switch(vf){
		case '0'://表单输入
			controlTypeTr.show();
			controlList = [ {key : '1',value : '单行文本框'},{key : '15',value : '日期控件'},{key : '16',value : '隐藏域'}];
			break;
		case '1'://脚本输入 (显示)
		case '2'://脚本输入 (隐藏)
		case '3'://流水号
			break;
		}
		break;
	case number_://数字
		switch(vf){
		case '0'://表单输入
			controlList[2] = {key : '11',value : '下拉选项'};
		case '1'://脚本输入 (显示)
		case '2'://脚本输入 (隐藏)
			controlTypeTr.show();
			controlList[0] = {key : '1',value : '单行文本框'};
			controlList[1] = {key : '16',value : '隐藏域'};
			break;
		case '3'://流水号
			break;
		}
		
		break;
	}

	$(controlList).each(function(i, d) {
		var option =$( optiontemplate.replaceAll('#value', d.key).replace('#text', d.value));
		if(value!=undefined && value!=null && value!=""){
			if(value==d.key){
				option.attr("selected","selected");
			}
		}
		if(isExternal==0){
			controlType.append(option);
		}
		//外部表的情况,不支持选择器类型。
		else{
			if( !isExecutorSelector(d.key)){
				controlType.append(option);
			}
		}
		
	});
	
};

/**
 * 根据字段类型、值来源和控件类型，处理是否显示当前用户,处理人员限定的范围
 * @param fy 字段类型
 * @param vf 值来源
 * @param ct 控制类型
 */
function hdlShowCurUserTrShow(fy,vf,ct,value,jsonObj){
	vf=""+vf;
	ct=""+ct;
	var tr=$("#showCurUserTr");
	var fromTypeTr=$("#fromTypeTr");
	var fromTypeScriptTr = $("#fromTypeScriptTr");
	tr.hide();
	fromTypeTr.hide();
	fromTypeScriptTr.hide();
	switch(vf){
		case '0'://表单输入
			switch(ct){
				case '1': //单行文本框
					break;
				case '2': //多行文本框
					break;
				case '3': //数据字典
					break;
				case '4': //人员选择器(单选)
					tr.show();
					fromTypeTr.show();
					handFromType(jsonObj,fromTypeScriptTr);
					break;
				case '8': //人员选择器(多选)
					fromTypeTr.show();
					handFromType(jsonObj,fromTypeScriptTr);
					break;
				case '17': //角色选择器(单选)
					break;
				case '5': //角色选择器(多选)
					break;
				case '18': //组织选择器(单选)
					break;
				case '6': //组织选择器(多选)
					break;
				case '19': //岗位选择器(单选)
					break;
				case '7': //岗位选择器(多选)
					break;
				case '16': //隐藏域
					break;
				case '9': //文件上传
					break;
				case '11': //下拉选项
					break;
				case '13': //复选框
					break;
				case '14': //单选按钮
					break;
				case '12': //Office控件
					break;
				case '15': //日期控件
					break;
				case '20': //流程引用
					break;
				case '21': //WebSign控件
					break;
				case '22': //图片展示控件
					break;
			}
			break;
		case '1'://脚本输入 (显示)
		case '2'://脚本输入 (隐藏)
		case '3'://流水号
			break;
	}
	if(value){
		$("#showCurUser").attr("checked","checked");
	}
};

//处理人员选择器的左边树的控制范围
function  handFromType(scope,ScriptTr){
	if(scope){
		var type = scope.type;
		var typeVal = scope.value;
		if(type == 'script'){
			$("#fromType").val("");
			$(ScriptTr).show();
			$("#fromScript").val(typeVal);
		}else{
			if(typeVal){
				$("#fromType").val(typeVal);
			}
		}
	}
}

/**
 * 根据字段类型、值来源和控件类型，处理是否显示直接上传文件
 * @param fy 字段类型
 * @param vf 值来源
 * @param ct 控制类型
 */
function hdlShowUpLoadTrShow(fy,vf,ct,value){
	vf=""+vf;
	ct=""+ct;
	var tr=$("#trUpLoad");
	tr.hide();
	switch(vf){
		case '0'://表单输入
			switch(ct){
				case '1': //单行文本框
					break;
				case '2': //多行文本框
					break;
				case '3': //数据字典
					break;
				case '4': //人员选择器(单选)
					break;
				case '8': //人员选择器(多选)
					break;
				case '17': //角色选择器(单选)
					break;
				case '5': //角色选择器(多选)
					break;
				case '18': //组织选择器(单选)
					break;
				case '6': //组织选择器(多选)
					break;
				case '19': //岗位选择器(单选)
					break;
				case '7': //岗位选择器(多选)
					break;
				case '16': //隐藏域
					break;
				case '9': //文件上传
					tr.show();
					break;
				case '11': //下拉选项
					break;
				case '13': //复选框
					break;
				case '14': //单选按钮
					break;
				case '12': //Office控件
					break;
				case '15': //日期控件
					break;
				case '20': //流程引用
					break;
				case '21': //WebSign控件
					break;
				case '22': //图片展示控件
					break;
			}
			break;
		case '1'://脚本输入 (显示)
		case '2'://脚本输入 (隐藏)
		case '3'://流水号
			break;
	}
	if(value!=undefined || value!=null || value!=""){
		if(value==1) {
			$("input[name='isDirectUpLoad']").attr("checked","checked")
		}
	}
};
	
/**
 * 根据字段类型、值来源和控件类型，处理是否显示脚本
 * @param fy 字段类型
 * @param vf 值来源
 * @param ct 控制类型
 */
function hdlScriptTrShow(ft,vf,ct,value){
	vf=""+vf;
	ct=""+ct;
	var tr=$("#trScript");
	tr.hide();
	switch(vf){
		case '0'://表单输入
			break;
		case '1'://脚本输入 (显示)
			tr.show();
		case '2'://脚本输入 (隐藏)
			tr.show();
		case '3'://流水号
			break;
	}
	
	if(value!=undefined && value!=null && value!=""){
		$("#script").val(value);
	}
};
/**
 * 根据字段类型、值来源和控件类型，处理是否显示脚本ID
 * @param fy 字段类型
 * @param vf 值来源
 * @param ct 控制类型
 */
function hdlScriptIdTrShow(ft,vf,ct,value){
	vf=""+vf;
	ct=""+ct;
	var tr=$("#trScriptID");
	tr.hide();
	switch(vf){
		case '0'://表单输入
			break;
		case '1'://脚本输入 (显示)
			switch(ct){
			case '1': //单行文本框
				break;
			case '2': //多行文本框
				break;
			case '3': //数据字典
				break;
			case '4': //人员选择器(单选)
				tr.show();
				break;
			case '8': //人员选择器(多选)
				tr.show();
				break;
			case '17': //角色选择器(单选)
				tr.show();
				break;
			case '5': //角色选择器(多选)
				tr.show();
				break;
			case '18': //组织选择器(单选)
				tr.show();
				break;
			case '6': //组织选择器(多选)
				tr.show();
				break;
			case '19': //岗位选择器(单选)
				tr.show();
				break;
			case '7': //岗位选择器(多选)
				tr.show();
				break;
			case '16': //隐藏域
				break;
			case '9': //文件上传
				break;
			case '11': //下拉选项
				break;
			case '13': //复选框
				break;
			case '14': //单选按钮
				break;
			case '12': //Office控件
				break;
			case '15': //日期控件
				break;
			case '20': //流程引用
				tr.show();
				break;
			case '21': //WebSign控件
				break;
			case '22': //图片展示控件
				break;
			}
			break;
		case '2'://脚本输入 (隐藏)
			switch(ct){
			case '1': //单行文本框
				break;
			case '2': //多行文本框
				break;
			case '3': //数据字典
				break;
			case '4': //人员选择器(单选)
				tr.show();
				break;
			case '8': //人员选择器(多选)
				tr.show();
				break;
			case '17': //角色选择器(单选)
				tr.show();
				break;
			case '5': //角色选择器(多选)
				tr.show();
				break;
			case '18': //组织选择器(单选)
				tr.show();
				break;
			case '6': //组织选择器(多选)
				tr.show();
				break;
			case '19': //岗位选择器(单选)
				tr.show();
				break;
			case '7': //岗位选择器(多选)
				tr.show();
				break;
			case '16': //隐藏域
				break;
			case '9': //文件上传
				break;
			case '11': //下拉选项
				break;
			case '13': //复选框
				break;
			case '14': //单选按钮
				break;
			case '12': //Office控件
				break;
			case '15': //日期控件
				break;
			case '20': //流程引用
				tr.show();
				break;
			case '21': //WebSign控件
				break;
			case '22': //图片展示控件
				break;
			}
			break;
		
		case '3'://流水号
			break;
			
	}
	
	if(value!=undefined && value!=null && value!=""){
		$("#scriptID").val(value);
	}
};

/**
 * 根据字段类型、值来源和控件类型，处理是否显示数据字典
 * @param fy 字段类型
 * @param vf 值来源
 * @param ct 控制类型
 */
function hdlDictTrShow(ft,vf,ct,value){
	vf=""+vf;
	ct=""+ct;
	var tr=$("#trDict");
	tr.hide();
	switch(vf){
		case '0'://表单输入
			switch(ct){
				case '1': //单行文本框
					break;
				case '2': //多行文本框
					break;
				case '3': //数据字典
					tr.show();
					break;
				case '4': //人员选择器(单选)
					break;
				case '8': //人员选择器(多选)
					break;
				case '17': //角色选择器(单选)
					break;
				case '5': //角色选择器(多选)
					break;
				case '18': //组织选择器(单选)
					break;
				case '6': //组织选择器(多选)
					break;
				case '19': //岗位选择器(单选)
					break;
				case '7': //岗位选择器(多选)
					break;
				case '16': //隐藏域
					break;
				case '9': //文件上传
					break;
				case '11': //下拉选项
					break;
				case '13': //复选框
					break;
				case '14': //单选按钮
					break;
				case '12': //Office控件
					break;
				case '15': //日期控件
					break;
				case '20': //流程引用
					break;
				case '21': //WebSign控件
					break;
				case '22': //图片展示控件
					break;
			}
			break;
		case '1'://脚本输入 (显示)
			break;
		case '2'://脚本输入 (隐藏)
			break;
		case '3'://流水号
			break;
	}
	
	if(value!=undefined && value!=null && value!=""){
		$("#dictTypeName").attr("catValue",value);
	}
	
	
};

/**
 * 根据字段类型、值来源和控件类型，处理是否显示验证规则
 * @param fy 字段类型
 * @param vf 值来源
 * @param ct 控制类型
 */
function hdlRuleTrShow(ft,vf,ct,value){
	vf=""+vf;
	ct=""+ct;
	var tr=$("#trRule");
	tr.hide();
	switch(vf){
		case '0'://表单输入
			switch(ct){
			case '1': //单行文本框
			case '2': //多行文本框
				tr.show();
				break;
			}
			break;
		case '1'://脚本输入 (显示)
			break;
		case '2'://脚本输入 (隐藏)
			break;
		case '3'://流水号
			break;
	}
	//数字类型不设置表单规则。
	if(ft==number_){
		tr.hide();
	}
	
	if(value!=undefined && value!=null && value!=""){
		$("#validRule").val(value);
	}
};

/**
 * 根据字段类型、值来源和控件类型，处理是否显示流水号
 * @param fy 字段类型
 * @param vf 值来源
 * @param ct 控制类型
 */
function hdlIdentityTrShow(ft,vf,ct,identityAlias,identityName){
	vf=""+vf;
	ct=""+ct;
	var tr=$("#showidentity");
	tr.hide();
	switch(vf){
		case '0'://表单输入
			break;
		case '1'://脚本输入 (显示)
			break;
		case '2'://脚本输入 (隐藏)
			break;
		case '3'://流水号
			tr.show();
			if(identityAlias){
				$("#identityAlias").val(identityAlias);
				$("#identityName").val(identityName);
			}
			break;
	}
};


/**
 * 根据字段类型、值来源和控件类型，处理是否显示数据字典
 * @param fy 字段类型
 * @param vf 值来源
 * @param ct 控制类型
 */
function hdlShowCurOrgTrShow(ft,vf,ct,value,jsonObj){
	vf=""+vf;
	ct=""+ct;
	var tr=$("#showCurOrgTr");
	var fromTypeTr=$("#fromTypeTr");
	var fromTypeScriptTr = $("#fromTypeScriptTr");
	tr.hide();
	switch(vf){
		case '0'://表单输入
			switch(ct){
			case '1': //单行文本框
				break;
			case '2': //多行文本框
				break;
			case '3': //数据字典
				break;
			case '4': //人员选择器(单选)
				break;
			case '8': //人员选择器(多选)
				break;
			case '17': //角色选择器(单选)
				break;
			case '5': //角色选择器(多选)
				break;
			case '18': //组织选择器(单选)
				tr.show();
				fromTypeTr.show();
				handFromType(jsonObj,fromTypeScriptTr);
				break;
			case '6': //组织选择器(多选)
				fromTypeTr.show();
				handFromType(jsonObj,fromTypeScriptTr);
				break;
			case '19': //岗位选择器(单选)
				break;
			case '7': //岗位选择器(多选)
				break;
			case '16': //隐藏域
				break;
			case '9': //文件上传
				break;
			case '11': //下拉选项
				break;
			case '13': //复选框
				break;
			case '14': //单选按钮
				break;
			case '12': //Office控件
				break;
			case '15': //日期控件
				break;
			case '20':
				break;
			case '21': //WebSign控件
				break;
			case '22': //图片展示控件
				break;
			}
			break;
		case '1'://脚本输入 (显示)
			break;
		case '2'://脚本输入 (隐藏)
			break;
		case '3'://流水号
			break;
	}
	if(value){
		$("#showCurOrg").attr("checked","checked");
	}
};

/**
 * 根据字段类型、值来源和控件类型，处理是否显示数据字典
 * @param fy 字段类型
 * @param vf 值来源
 * @param ct 控制类型
 */
function hdlShowCurPosTrShow(ft,vf,ct,value,jsonObj){
	vf=""+vf;
	ct=""+ct;
	var tr=$("#showCurPosTr");
	var fromTypeTr=$("#fromTypeTr");
	var fromTypeScriptTr = $("#fromTypeScriptTr");
	tr.hide();
	switch(vf){
		case '0'://表单输入
			switch(ct){
			case '1': //单行文本框
				break;
			case '2': //多行文本框
				break;
			case '3': //数据字典
				break;
			case '4': //人员选择器(单选)
				break;
			case '8': //人员选择器(多选)
				break;
			case '17': //角色选择器(单选)
				break;
			case '5': //角色选择器(多选)
				break;
			case '18': //组织选择器(单选)
				break;
			case '6': //组织选择器(多选)
				break;
			case '19': //岗位选择器(单选)
				tr.show();
				fromTypeTr.show();
				handFromType(jsonObj,fromTypeScriptTr);
				break;
			case '7': //岗位选择器(多选)
				fromTypeTr.show();
				handFromType(jsonObj,fromTypeScriptTr);
				break;
			case '16': //隐藏域
				break;
			case '9': //文件上传
				break;
			case '11': //下拉选项
				break;
			case '13': //复选框
				break;
			case '14': //单选按钮
				break;
			case '12': //Office控件
				break;
			case '15': //日期控件
				break;
			case '20': //流程引用
				break;
			case '21': //WebSign控件
				break;
			}
			break;
		case '1'://脚本输入 (显示)
			break;
		case '2'://脚本输入 (隐藏)
			break;
		case '3'://流水号
			break;
	}
	if(value){
		$("#showCurPos").attr("checked","checked");
	}
};

/**
 * 根据字段类型、值来源和控件类型，处理是否显示下拉选项
 * @param fy 字段类型
 * @param vf 值来源
 * @param ct 控制类型
 */
function hdlOptionTrShow(ft,vf,ct,value){
	vf=""+vf;
	ct=""+ct;
	var tr=$("#trOption");
	tr.hide();
	switch(ft){
	case varchar_:
		switch(vf){
			case '0'://表单输入
				switch(ct){
				case '1': //单行文本框
					break;
				case '2': //多行文本框
					break;
				case '3': //数据字典
					break;
				case '4': //人员选择器(单选)
					break;
				case '8': //人员选择器(多选)
					break;
				case '17': //角色选择器(单选)
					break;
				case '5': //角色选择器(多选)
					break;
				case '18': //组织选择器(单选)
					break;
				case '6': //组织选择器(多选)
					break;
				case '19': //岗位选择器(单选)
					break;
				case '7': //岗位选择器(多选)
					break;
				case '16': //隐藏域
					break;
				case '9': //文件上传
					break;
				case '11': //下拉选项
					tr.show();
					break;
				case '13': //复选框
					tr.show();
					break;
				case '14': //单选按钮
					tr.show();
					break;
				case '12': //Office控件
					break;
				case '15': //日期控件
					break;
				case '21': //WebSign控件
					break;
				case '22': //图片展示控件
					break;
				}
				break;
			case '1'://脚本输入 (显示)
				break;
			case '2'://脚本输入 (隐藏)
				break;
			case '3'://流水号
				break;
		}
		break;
	case number_:
		switch(vf){
			case '0'://表单输入
				switch(ct){
				case '1': //单行文本框
					break;
				case '2': //多行文本框
					break;
				case '3': //数据字典
					break;
				case '4': //人员选择器(单选)
					break;
				case '8': //人员选择器(多选)
					break;
				case '17': //角色选择器(单选)
					break;
				case '5': //角色选择器(多选)
					break;
				case '18': //组织选择器(单选)
					break;
				case '6': //组织选择器(多选)
					break;
				case '19': //岗位选择器(单选)
					break;
				case '7': //岗位选择器(多选)
					break;
				case '16': //隐藏域
					break;
				case '9': //文件上传
					break;
				case '11': //下拉选项
					tr.show();
					break;
				case '13': //复选框
					tr.show();
					break;
				case '14': //单选按钮
					tr.show();
					break;
				case '12': //Office控件
					break;
				case '15': //日期控件
					break;
				case '21': //WebSign控件
					break;
				case '22': //图片展示控件
					break;
				}
				break;
			case '1'://脚本输入 (显示)
				break;
			case '2'://脚本输入 (隐藏)
				break;
		}
		break;	
	}
	if(value!=undefined && value!=null && value!=""){
		var optsAry = {};
		try{
			optsAry = eval("("+value+")");
		}
		catch(e){
		}
		initOptionArray(optsAry);
	}
};

function initOptionArray(json) {
	var tbody = $('#option-table>tbody');
	if (typeof(json)!=undefined && json!=null && json != '' && json.length>0){  //有数据时
		for ( var i = 0, c; c = json[i++];) {
			var tr = addColumn1(c);
			tbody.append(tr);
		/*	if(typeof c.value ==='string'){
				var tr = addColumn1(c);
				tbody.append(tr);
			}else{
				var normalTr = addNormalColumn(c),
			    	newTr = addColumn(c);
				tbody.append(normalTr,newTr.hide());
			}*/
		}
	}else{
		var value = [];
		value.lantype=" ";
		value.lanmemo=" ";
		value.lanres=" ";
		var data ={key:" ",value:value};  //key 为一个空格
		var normalTr = addNormalColumn(data),
	        newTr = addColumn(data);
		tbody.append(normalTr,newTr.hide());
	}
	
};
/**
 * 终止事件冒泡
 * @param  {[type]} e [description]
 * @return {[type]}   [description]
 */
function stopBubble(e) {
    if (e && e.stopPropagation) e.stopPropagation();
    else window.event.cancelBubble = true;
};

/**
 * 添加选项(兼容3.2版本)
 * @param  {[json]} data [数据]
 * @return {[type]}      [description]
 */
function addColumn1(data){
  var hiddenTable = $("#hiddenTable"),
      tmpTr = $("tr.editable-tr",hiddenTable),
      newTr = tmpTr.clone();
  if(typeof(data.key)!= undefined && data.key!=null && data.key!=''){
	  $("input[name='optionKey']",newTr).val(data.key);
	  $("input[name='optionValue']",newTr).val(data.value);	
  }
  return newTr;
};

/**
 * 添加选项
 * @param  {[json]} data [数据]
 * @return {[type]}      [description]
 */
function addColumn(data){
  var hiddenTable = $("#hiddenTable"),
      tmpTr = $("tr.editable-tr",hiddenTable),
      newTr = tmpTr.clone();
  if( typeof(data.key)!= undefined && data.key!=null && data.key!=''){

	  $("input[name='optionKey']",newTr).val(data.key);
	  $("input.long",newTr).each(function(){
		  var opinionValue = data.value;
		  var me = $(this).val(''),
		      name = me.attr("name");
	      for(var i=0,c;c=opinionValue[i++];){
	          if(c.lantype==name){
	              me.val(c.lanres);
	          }
	      }
	  });
  }
  return newTr;
};

function addNormalColumn(data){
	 var hiddenTable = $("#hiddenTable"),
     tmpTr = $("tr.normalTr",hiddenTable),
     newTr = tmpTr.clone();
 if( typeof(data.key)!= undefined && data.key!=null && data.key!=''){
	  var lanDetail = $("span.lanDetail",newTr),
	  	  curLan = $("span.curLan",newTr),
	  	  selector = "input[name='"+locale+"']",
	      detail = getSelectValue(data.value),
	      curLanMsg = getCurLan(data.value);
	  lanDetail.text(detail);
	  curLan.text(curLanMsg);
 }
 return newTr;
}

/**
 * 获取选项值
 */
function getSelectValue(val){
	  if(!val||val.length==0)return '';
	  var str = [];
	  if(val.length==0)return str;
	  for(var i=0,c;c=val[i++];){
	    str.push('['+c.lanmemo + ']' + c.lanres);
	  }
	  return str.join('  ');
};

/**
 * 获取当前语言对应的选项值
 */
function getCurLan(val){
	  if(!val||val.length==0)return '';
	  var str = "";
	  if(val.length==0)return str;
	  for(var i=0,c;c=val[i++];){
		  if(c.lantype==locale){
			  str = c.lanres;
			  return str;
		  }
	  }
	  return str;
};


/**
 * 根据字段类型、值来源和控件类型，处理是否显示千分位显示
 * @param fy 字段类型
 */
function hdlShowComdifyShow(ft,value){
	var showComdify=$("#showComdify");
	showComdify.hide();
	switch(ft){
	case number_:
		showComdify.show();
	}
};
/**
 * 根据值来源和控件类型控制 超连接 选项 显示
 * @param vf
 * @param ct
 * @param value
 */
function hdlIsReferenceShow(vf,ct,value){
	ct = ""+ct;
	var showIsLink = $("#showIsReference");
	var isLink = $("#isReference");
	showIsLink.hide();
	switch(ct){
	case '4': //人员选择器(单选)
	case '8': //人员选择器(多选)
	case '17': //角色选择器(单选)
	case '5': //角色选择器(多选)
	case '18': //组织选择器(单选)
	case '6': //组织选择器(多选)
	case '19': //岗位选择器(单选)
	case '7': //岗位选择器(多选)
	case '20': //流程引用
		showIsLink.show();
		break;
	}
	if(value && value==1){
		isLink.attr("checked","checked");
	}
};

/**
 * 根据控件类型改变值的长度
 * @param len
 */
function hdlCharLen(ct){
	ct=""+ct;
	changeCharLen(2000);
	switch(ct){
		case '2':
		case '3':
			changeCharLen(2000);
			break;
		//部门选择器（单选）
		case '4':
		case '17':
		case '18':
		case '19':
			changeCharLen(2000);
			break;
		//选择器（多选）
		case '5':
		case '6':
		case '7':
		case '8':
			changeCharLen(2000);
			break;
		//隐藏域
		case '16':
			changeCharLen(100);
			break;
		//如果选择文件上传控件，将字符宽度默认修改为2000个字符。
		case '9':
			changeCharLen(2000);
			break;
		//下拉选项,单选框，复选框
		case '11':
		case '13':
		case '14':
			changeCharLen(2000);
			break;
		//日期控件
		case '15':
			changeCharLen(20);
			break;		
		case '20':
		case '22':
			changeCharLen(2000);
			break;
		case '12':
		case '21':
			changeCharLen(50);
			break;
	
	}
}