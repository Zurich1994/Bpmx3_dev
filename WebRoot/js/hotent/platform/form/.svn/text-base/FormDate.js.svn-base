if (typeof FormDate == 'undefined') {
	FormDate = {};
}

/****************日期计算的逻辑代码********************/
FormDate.doDateCalculate = function(obj){
	
	$("input[datecalculate]").each(function(){
		
		var me = $(this),
			curName = me.attr("name"),
			dateCalculate = me.attr("dateCalculate");
		
		// 判断是否有标识语
		dateCalculate = eval("("+dateCalculate+")");
		var dateCalculateStart =  dateCalculate.dateCalculateStart,
			dateCalculateEnd =  dateCalculate.dateCalculateEnd;
		if(typeof dateCalculateStart == "undefined" || dateCalculateStart == "" 
			|| typeof dateCalculateEnd == "undefined" || dateCalculateEnd == ""){
			return true;
		}
		
		try{
			//计算表达式进行运算
			var value = FormDate.getDateDiff(dateCalculate,me);
		}
		catch(e){
			return true;
		}
		me.val(value);
		me.trigger("change");
	});
};

/**
 * 获取两时间的事件差（月，天，小时，分，秒） 
 */
FormDate.getDateDiff = function (dateCalculate,t){
	var dateCalculateStart =  dateCalculate.dateCalculateStart,
	dateCalculateEnd =  dateCalculate.dateCalculateEnd,
	diffType = dateCalculate.diffType,
	isMain = dateCalculate.isMain,
	startTime = "",endTime = "";
	if(isMain){
		startTime = $("[name='"+dateCalculateStart+"']").val();
		endTime = $("[name='"+dateCalculateEnd+"']").val();
	} else {
		var pTr = $(t).closest(".listRow");
		startTime = $("[name='"+dateCalculateStart+"']",pTr).val();
		endTime = $("[name='"+dateCalculateEnd+"']",pTr).val();
	}
	return FormDate.getDateDiffVal(startTime, endTime, diffType); 
};


/**
 * 获取两时间的事件差（月，天，小时，分，秒） 
 * @param startTime
 * @param endTime
 * @param diffType
 * @returns
 */

FormDate.getDateDiffVal=function(startTime, endTime, diffType){
	if(typeof startTime == "undefined" || startTime == "" 
		|| typeof endTime == "undefined" || endTime == ""){
		return "";
	}
	var result;
	var temptype = diffType;
	if (diffType == "hour"){
		diffType = "minute";
	}
	if(startTime.indexOf("-") == -1 && endTime.indexOf("-") == -1){
		result=FormDate.timeVal(startTime,endTime,diffType);//日期格式为 hh:mm:ss
	}else{
		result=FormDate.dateVal(startTime,endTime,diffType);//日期格式YYYY-MM-DD
	}
	if (isNaN(result)){
		result = "";
	} else if (temptype == "hour") {
		//精确到半小时
		temp = parseInt(result / 60);
		if (result % 60 >= 30){
			temp = temp + 0.5;
		}
		result = temp;
	}
	return result;
}
//日期格式YYYY-MM-DD
FormDate.dateVal = function(startTime, endTime, diffType){
	startTime = startTime.replace(/\-/g, "/");
	endTime = endTime.replace(/\-/g, "/");
	diffType = diffType.toLowerCase();
	var sTime = new Date(startTime); //开始时间
	var eTime = new Date(endTime); //结束时间
	
	if(diffType == "month"){
		return FormDate.getMonthBetween(sTime,eTime);
	}
	
	//作为除数的数字 
	var divNum = FormDate.getDiffType(diffType);
	var temp = 0;
	return parseInt((eTime.getTime() - sTime.getTime()) / parseInt(divNum));
}

//日期格式为 hh:mm:ss
FormDate.timeVal = function(startTime, endTime, diffType){
	var temptype = diffType;
	if (diffType == "hour"){
		diffType = "minute";
	}
	var divNum = FormDate.getDiffType(diffType)
	var sTime = startTime.split(':', 3);
	var eTime = endTime.split(':', 3);
	var h=(parseInt(eTime[0])-parseInt(sTime[0]))*3600;
	var m=(parseInt(eTime[1])-parseInt(sTime[1]))*60;
	if(m<0){
		h=h-1;
		m=60+m;
	}
	var result =parseInt(((h+m)*1000/parseInt(divNum)));
	return result;
}

FormDate.getMonthBetween = function(startDate,endDate){
	var num=0;
	var year=endDate.getFullYear()-startDate.getFullYear();
		num+=year*12;
	var month=endDate.getMonth()-startDate.getMonth();
		num+=month;
	var day=endDate.getDate()-startDate.getDate();
	if(day>-1){ 
		num+=1;
	}
	return num;
}

FormDate.getDiffType=function(diffType){
	var divNum=1;
	switch (diffType) {
		case "second":
			divNum = 1000;
			break;
		case "minute":
			divNum = 1000 * 60;
			break;
		case "hour":
			divNum = 1000 * 3600;
			break;
		case "day":
			divNum = 1000 * 3600 * 24;
			break;
		default:
			break;
    }
	return divNum;
}