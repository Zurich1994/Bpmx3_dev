/*【协议呈批】**/
$(function(){
	loadChangeSffs();
	$("[name='m:op_xycpb:xylx']").trigger("change");
});

/*切换协议类型*/
function changeXylx(obj){
	var xylx = $(obj).val();
	
	$("[xylxType*='xylx-']").hide();
	$("[xylxType='xylx-"+xylx+"']").show();
}


/* 表租金标准信息 添加行事件*/
function op_xycpb_zjbzxxAddRowAfterEvent(row){
	var preRow = $(row).prev();
	/*获取原结算标准最后一个序号*/
	if(preRow.length==0 ||preRow.attr("style") == 'display: none;'){
		preRow = $(".listRow:visible",$("[tablename='op_xycpb_yzjbzxx']")).last();
	}
	var preNx = $("[name$=':xh']",preRow).val();
	if(!preNx)preNx = 0;
	$("[name$=':xh']",row).val(1+Number(preNx));
	validateZujinBiaozhunDates(row)
};
// 校验租金标准 时间
function validateZujinBiaozhunDates(curRow){
	//租赁有效期起，止
	var zuLinStartDate  =$("[name='m:op_xycpb:zlyxqq']").val();
	var zuLinEndDate  =$("[name='m:op_xycpb:zlyxqz']").val();
	if(!zuLinStartDate || !zuLinStartDate){  
		$.ligerDialog.warn("尚未输入租赁起止日期！",'请核查'); 
		$(curRow).remove();
		return
	}
	
	var rows = $(".listRow:visible",$("[tablename='op_xycpb_zjbzxx']"));
	for(var i=0,row;row=rows[i++];){
		var shiJianDuan = $("[name$='sjd']",$(row)).val(i); //时间段
		
		if(i>1){
			//开始日期等于上个结束日期加1
			var startDate = getNewDataStr($("[name$='jsrq']",$(rows[i-2])).val(),1,1);
			$("[name$='ksrq']",$(row)).val(startDate);
		}else{
			var differTotalStartDate = FormDate.dateVal($("[name$='ksrq']",$(row)).val(), zuLinStartDate, "day");
			if(differTotalEndDate>=0)  {
				$.ligerDialog.warn("当前开始日期不能早于 租赁开始日期！",'请核查');
			} 
			$("[name$='ksrq']",$(row)).val(zuLinStartDate);
		}
		var differTotalEndDate = FormDate.dateVal($("[name$='jsrq']",$(row)).val(), zuLinEndDate, "day");
		if(differTotalEndDate<0)  {
			$.ligerDialog.warn("当前结束日期不能晚于 租赁有效期止！",'请核查');
			$("[name$='jsrq']",$(row)).val("")
			return ;
		}
	}
	calZongZuJin();
}
/*修改租金规则*/
function changeZujinGz(obj){
	var curRow = $(obj).closest(".listRow");
	var danJia = $("[name$='dj']",curRow);
	var zuJin = $("[name$='zj']",curRow);
	var rule = $(obj).val();
	
	if(rule == "2" || rule=="4"){ //纯扣
		danJia.val(0);zuJin.val(0);
		danJia.attr("readonly","readonly");
		zuJin.attr("readonly","readonly");
	}else{
		danJia.removeAttr("readonly");
		zuJin.removeAttr("readonly"); 
	}
	danJia.trigger("change");zuJin.trigger("change");
}

//单价计算总租金
function calZongZuJin(obj){
	var curRow = $(obj).closest(".listRow");
	var danJia = FormUtil.commaback($("[name$='dj']",curRow).val()); // 单价
	if(! danJia>0) return; 
	
	var mianJi = $("[name='m:op_xycpb:jzmj']").val();
	if(!mianJi){
		$.ligerDialog.warn(" 合同建筑面积不能为空！",'请核查');
		return ;
	}
	var baoDiType =$("[name$='bdxx']",curRow).val();
	var zuJin = mianJi * danJia;
	$("[name$=':zj']",curRow).val(zuJin); $("[name$=':zj']",curRow).trigger("change");
	
	var startDate = $("[name$='ksrq']",curRow).val();
	var endDate = $("[name$='jsrq']",curRow).val();
	if(baoDiType =="0"){ //按月
		var mounth = FormDate.dateVal(startDate, endDate, "day")+1;
		var zongZujin = calMountZujin(startDate,endDate,zuJin);
	}else{
		var days = FormDate.dateVal(startDate, endDate, "day")+1;
		var zongZujin = zuJin*days;
	}
	
	
	$("[name$='zzj']",curRow).val(zongZujin); $("[name$='zzj']",curRow).trigger("change");
 }
/*总租金。月度计算法*/
function calMountZujin(startTime,endTime,zuJin){
	startTime = startTime.replace(/\-/g, "/");
	endTime = endTime.replace(/\-/g, "/");
	var startDate = new Date(startTime); //开始时间
	var endDate = new Date(endTime); //结束时间
	
	var num=0;
	var year=endDate.getFullYear()-startDate.getFullYear();
		num+=year*12;
	var month=endDate.getMonth()-startDate.getMonth();
		num+=month;
		
	var amount = zuJin * num; //月租金
	var day=endDate.getDate()-startDate.getDate()+1;   //
	amount = amount + day*zuJin/30  // 少于一月 减相差金额，多于一月 加多的金额
	return amount;
}


/*加载时，改变【每月收费项目】的【收费方式】*/
function loadChangeSffs(){
	var sffs = $("[name$='sffs']:visible",$("[tablename='op_xycpb']"));
	sffs.each(function (i) {
		changeSffs(this);
	});
}

/*改变【每月收费项目】的【收费方式】*/
function changeSffs(obj){
	var $tr = $(obj).closest("tr");
	var sffs = $("[name$='sffs']",$tr).val(); /*收费方式*/
	switch(sffs){
		case "0":		/*固定金额类型 ： 只能录入收费金额*/
		case "4":		
			 $("[name$='dj']",$tr).attr("readonly","readonly");
			 $("[name$='sfje']",$tr).removeAttr("readonly");
			 $("[name$='kl']",$tr).attr("readonly","readonly");
		  break;
		case "1":		/*比率类型：只能录入比率*/
		case "2":
		case "5":
			$("[name$='dj']",$tr).attr("readonly","readonly");
			$("[name$='sfje']",$tr).attr("readonly","readonly");
			$("[name$='kl']",$tr).removeAttr("readonly");
		  break;
		case "3":		/*每平方米单价类型：只能录入单价*/
			 $("[name$='dj']",$tr).removeAttr("readonly");
			$("[name$='sfje']",$tr).attr("readonly","readonly");
			$("[name$='kl']",$tr).attr("readonly","readonly");
		  break;
		default:
	}
}


/*结算标准信息   删除行事件*/
function op_xycpb_zjbzxxDelRowBeforeEvent(row){
	var xh = $("[name$=':xh']",row).val();
	$("[name='s:me_lybdxx:xh'][value=" + xh + "]").closest("tr").remove();/*清除旧的记录*/
}

/*分解所有结算标准信息  */
function decomposeAllJs(tableName,fenjieTable){
	var $jsbzxxTrs = $(".listRow:visible",$("[tablename='"+tableName+"']"));   
	$jsbzxxTrs.each(function (i) {
		decomposeSingle($(this),fenjieTable);
	});
	
	var $jsbzxxTrs = $("input[name$=':fjsj']:checked",$("[tablename='"+tableName+"']")).closest("tr");
	showFjsj($jsbzxxTrs,fenjieTable);
}

function decomposeAllYzj(){
	decomposeAllJs('op_xycpb_zjbzxx','op_xycpb_ydzjfj');
}
function decomposeSingleYzj(){
	decomposeSingleJs('op_xycpb_zjbzxx','op_xycpb_ydzjfj');
}

/*单个分解*/
function decomposeSingleJs(tableName,fenjieTable){
	$jsbzxxTrs = $("input[name$=':fjsj']:checked",$("div[tablename='"+tableName+"']")).closest("tr");
	if($jsbzxxTrs.length ==0){
		$.ligerDialog.warn("请选择要分解的数据！","提示信息");
		return;
	}
	
	//分解
	decomposeSingle($jsbzxxTrs,fenjieTable);
	showFjsj($jsbzxxTrs,fenjieTable);
} 

/*【通过分解目标行来分解信息，将结果输出至分解表】 
 * 被选中的行：selectRow,分解表的表明fenJieTable*/
function decomposeSingle(selectRow,fenJieTable){
	var xh = selectRow.find("[name$=':xh']").val();	
	if(!xh){
		$.ligerDialog.warn("请选择要分解的时间段","提示");
		return ;
	}
	//分解表
	var fenJieTableDiv = $("div[tablename='"+fenJieTable+"']");
	/*将序号与当前选中行所有分解信息删除*/
	$("[name$=':xh'][value=" + xh + "]",fenJieTableDiv).closest("tr").remove(); /*清除旧的记录*/
	
	var sjd =$("[name$='xh']",selectRow).val(); //序号
	var ksrq =$("[name$='ksrq']",selectRow).val();
	var jsrq =$("[name$='jsrq']",selectRow).val();
	var zj =FormUtil.commaback($("[name$='zj']",selectRow).val()); 
	var sfgz =$("[name='m:op_xycpb:zjsfgzID']").val();

	var jsonData =  decompose(sjd,ksrq,jsrq,zj,sfgz);
	/*填充数据*/
	for (var i = 0, c; c = jsonData[i++];) {
		FormUtil.addRow(fenJieTableDiv);
		var rowcount=$("input[name$=':ny']",fenJieTableDiv).length;
		$($("input[name$=':xh']",fenJieTableDiv).get(rowcount-1)).val(xh);		
		$($("input[name$=':ny']",fenJieTableDiv).get(rowcount-1)).val(c.ZQY);				
		$($("input[name$=':ksrq']",fenJieTableDiv).get(rowcount-1)).val(c.KSRQ);			
		$($("input[name$=':jsrq']",fenJieTableDiv).get(rowcount-1)).val(c.JSRQ);			
		$($("input[name$=':je']",fenJieTableDiv).get(rowcount-1)).val(c.YZJ);	
		$($("input[name$=':scrq']",fenJieTableDiv).get(rowcount-1)).val(c.CDRQ);	
	}

}

/*分解数据*/
function decompose(sjd,ksrq,jsrq,zj,gzid,mbxs){
	if(!zj) zj =0;
	var paramJson = {jlbh:sjd,ksrq:ksrq,jsrq:jsrq,yzj:zj};
	if(mbxs) paramJson.mbxs =mbxs;
	if(gzid) paramJson.gzid =gzid;
	var jsonParams = [];
	jsonParams.push(paramJson);
	var conf = {aliasName:'decompose',paramJson:JSON.stringify(jsonParams)};
	var json = RunAliasScript(conf); 
    if(json.isSuccess==0){
    	return JSON.parse(json.result);
	 }else{
		 $.ligerDialog.error("分解失败："+json.result,"提示信息");
		 return [];
	 }
}


/*显示分解信息列表 */
function showFjsj(obj,fenJieTable){
	var fenJieTableDiv =$("div[tablename='"+fenJieTable+"']");
	var tableDiv = $(obj).closest("[tablename]");
	var selectRow =$("input[name=':fjsj']:checked",tableDiv).closest("tr");
	
	var xh = selectRow.find("[name$=':xh']").val();/*选择行*/
	
	$(".listRow:visible",fenJieTableDiv).hide();	/*先隐藏全部，在显示个体*/
	$("[name$=':xh'][value=" + xh + "]",fenJieTableDiv).closest("tr").show(); 
	
	$("td.tdNo", $(".listRow:visible",fenJieTableDiv)).each(function(i) {
		$(this).text(i + 1);
	});
	
	$("[name$=':zj']").trigger("blur");
}








/**分解代码 end**/

/**日期加上多少天     type: m=2/d=1/y=3   number:几天 */
function getNewDataStr(curDateStr,number,type){
	if(!curDateStr) return "";
	curDateStr = curDateStr.replace(/\-/g, "/");
	var curDate =  new Date(curDateStr);
	if(type==1) curDate.setDate(curDate.getDate()+number); 
	if(type==2) curDate.setMonth(curDate.getMonth()+number);
	if(type==3) curDate.setFullYear(curDate.getFullYear()+number); 

	var y=curDate.getFullYear();
	var m=curDate.getMonth()+1;
	var d=curDate.getDate();
	 
	if(m<=9)m="0"+m; if(d<=9)d="0"+d;
	var cdate=y+"-"+m+"-"+d;
	return cdate; //开始时间
} 