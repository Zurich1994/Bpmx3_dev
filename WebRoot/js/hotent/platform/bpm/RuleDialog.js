function RuleDialog(conf){
	if(!conf) conf={};	
	var url=__ctx + "/platform/bpm/bpmNodeRule/dialog.ht?defId="+conf.defId+"&nodeId="+conf.nodeId;
	
	var dialogWidth=700;
	var dialogHeight=500;
	
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:1,center:1},conf);

	var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
		+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
	url=url.getNewUrl();
	/*var rtn=window.showModalDialog(url,conf,winArgs);
	if(rtn!=undefined){
		if(conf.callback){
			conf.callback.call(this,rtn);
		}
	}	*/
	
	var that =this;
	/*KILLDIALOG*/
	DialogUtil.open({
		height:conf.dialogHeight,
		width: conf.dialogWidth,
		title : '',
		url: url, 
		isResize: true,
		//自定义参数
		sucCall:function(rtn){
			if(rtn!=undefined){
				if(conf.callback){
					conf.callback.call(that,rtn);
				}
			}
		}
	});
}

//以下是生成 条件表达式

/**
 * 生成 条件表达式
 * @param json
 * @returns 条件表达式
 */
function genConditionCode(json){
	var code = new StringBuffer(),jsonLength = json.length;
	if(jsonLength<=0) return "";
	for(var i=0;i<jsonLength;i++){
		var obj=json[i],
		flowvarKey=obj.flowvarKey,//表单变量
		optType=obj.optType,//变量类型
		compType=obj.compType,//操作类型
		judgeCon1=obj.judgeCon1,//条件1
		judgeVal1=obj.judgeVal1,//条件1值
		judgeCon2=obj.judgeCon2,//条件2
		judgeVal2=obj.judgeVal2;//条件2值
		var script = "";
		if(optType == 5)
			script = _parseScript(flowvarKey,optType,judgeCon1,judgeVal1,judgeCon2,judgeVal2, false,true,jsonLength);
		else{
			script = _parseScript(flowvarKey,optType,judgeCon1,judgeVal1,judgeCon2,judgeVal2, (optType==1?true:false),false,jsonLength);
		}
		
		if($.isEmpty(script))continue;	
		if(i>0 && !$.isEmpty(code.toString()) )
			code.append(convertCompType(compType));		
		code.append(script);
	}
//	if(jsonLength>1 && !$.isEmpty(code.toString()) )
//		code = "("+code.toString()+")";
	return code.toString();
}

/**
 * 转换运算
 * @param compType 运算类型
 * @returns {String}   运算类型 字符串
 */
function  convertCompType(compType){
	if(compType == "1") //与运算
		return "&&";
	else// 或运算
		return "||";
}

/**
 * 转换脚本()
 * @param flowvarKey
 * @param optType
 * @param judgeCon1
 * @param judgeVal1
 * @param judgeCon2
 * @param judgeVal2
 * @param isNumber
 * @param isUser
 * @returns {String}
 */
function _parseScript(flowvarKey,optType,judgeCon1,judgeVal1,judgeCon2,judgeVal2,isNumber,isUser,jsonLength){
	var sb=new StringBuffer();
	if(isUser){
		if(!$.isEmpty(judgeVal1)){
			var v = judgeVal1.split("&&")[0];
			sb.append(_getCompareScript(flowvarKey,judgeCon1,v,isNumber));
		}
		
	}else{	
		if(!$.isEmpty(judgeVal1))
			sb.append(_getCompareScript(flowvarKey,judgeCon1,judgeVal1,isNumber));
		
		if(!$.isEmpty(judgeVal2)){
			if(!$.isEmpty(judgeVal1) )
					sb.append("&&");
			sb.append(_getCompareScript(flowvarKey,judgeCon2,judgeVal2,isNumber));
		}
	}
	if(jsonLength>1 &&!$.isEmpty(sb.toString()))
		sb = "("+sb.toString()+")";
	return sb.toString();
}


/**
 * 获取条件表达式
 * @param flowvarKey
 * @param judgeCon
 * @param judgeVal
 * @param isNumber
 * @returns {String}
 */
function _getCompareScript(flowvarKey,judgeCon,judgeVal,isNumber){
	switch(judgeCon){
		//数字或日期
		case "1"://等于
			return  flowvarKey +"==" + (isNumber?"":"\"")+judgeVal+(isNumber?"":"\"");
		case "2"://不等于
			return flowvarKey +"!=" + (isNumber?"":"\"")+judgeVal+(isNumber?"":"\"");
		case "3"://大于
			return flowvarKey +">" + (isNumber?"":"\"")+judgeVal+(isNumber?"":"\"");
		case "4"://小于
			return flowvarKey +"<" + (isNumber?"":"\"")+judgeVal+(isNumber?"":"\"");
		case "5"://大于等于
			return flowvarKey +">=" + (isNumber?"":"\"")+judgeVal+(isNumber?"":"\"");
		case "6"://小于等于
			return flowvarKey +"<=" + (isNumber?"":"\"")+judgeVal+(isNumber?"":"\"");	
		//字符串	
		case "7"://完全等于
			return  flowvarKey +".equals(\"" +judgeVal+"\")";
		case "8"://不等于
			return  flowvarKey +"!= \"" +judgeVal+"\"";
		case "9"://等于(忽略大小写)
			return flowvarKey +".equalsIgnoreCase(\"" +judgeVal+"\")";
		case "10"://包含
			return flowvarKey +".contains(" +judgeVal+")";
		case "11"://不包含
			return "!"+ flowvarKey +".contains(\"" +judgeVal+"\")";
		//下拉，复选，单选  数字字典	
		case "12"://等于
			return  flowvarKey +".equals(\"" +judgeVal+"\")";
		case "13"://不包含
			return  flowvarKey +"!=\"" +judgeVal+"\"";
		case "14"://
			return flowvarKey +".contains(\"" +judgeVal+"\")";
		case "15"://
			return "!"+ flowvarKey +".contains(\"" +judgeVal+"\")";
	 	//用户、角色、岗位、组织选择器
		case "16"://包含
			var v = judgeVal.split(","),sb= new StringBuffer();
			for ( var i = 0; i < v.length; i++) {
				var s = v[i];
				sb.append(flowvarKey +".equals(\""+s+"\")");
				if(i+1!=v.length ) sb.append("||");
			}
			return sb.toString();
		case "17"://不包含
			var v = judgeVal.split(","),sb= new StringBuffer();
			for ( var i = 0; i < v.length; i++) {
				var s = v[i];
				sb.append("!"+flowvarKey +".equals(\""+s+"\")");
				if(i+1!=v.length ) sb.append("||");
			}
			return  sb.toString();
	}
}
