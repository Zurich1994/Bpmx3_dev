$().ready(function (){
	
	changeSftstk();	/*【是否特殊条款】*/
	changeSblx();	/*【申报类型】*/
	
	loadChangeSffs();	/*加载时，改变【每月收费项目】的【收费方式】*/
	
	loadChangeJsgz(); /*加载时，改变【结算标准信息】的【结算规则】*/
});


/*校验子表填充数据是否重复**/ 
function checkDataBeforeInsert(data,tableName){
	if(tableName == 'me_lypwxx'){
	      var rows =   $(".listRow",$("[tableName='me_lypwxx']"));  //左右子表列
	      for(var i =0,row;row=rows[i++];){
	        var id = $("[name='s:me_lypwxx:pwID']",$(row)).val();  //唯一值
	          if(id ==data.WLDPID) {                              //对话框，返回数据的那列
	    	   $.ligerDialog.warn(data.WLDPDM+"已经存在了！","提示信息");
	            return false
	         }
	      }
	    }
    
     return true;
  }

/*租赁时间变化事件**/
function zlsjChangeEvent(){
	validateJsbzxxDates();
	$("[name='s:me_lyklzgz:sjd']").trigger("change");
	validateWyfDates();
	validateMysfxm();
}

/*==============主表事件=================*/

/*改变【是否特殊条款】*/
function changeSftstk(){
	var sftstk = $("[name='m:me_lytksbb:sftstk']").val();
	if(typeof sftstk == "undefined"){
		sftstk = $.trim($("#sftstk").text());
	}
	if(sftstk == "1" || sftstk == "是"){
		$("#tstk_tr").show();
	}else{
		$("#tstk_tr").hide();
	}
}

/*改变【申报类型】*/
function changeSblx(){
	var sblx = $("[name='m:me_lytksbb:sblx']").val();
	if(typeof sblx == "undefined"){
		sblx = $.trim($("#sblx").text());
	}
	if(sblx == "2" || sblx == "重新申报"){
		$("#sbyy_tr").show();
	}else{
		$("#sbyy_tr").hide();
	}
}

/*计算所有铺位*/
function calAllPuwei(obj){
	var puweiTrs = $(".listRow:visible",$("[tablename='me_lypwxx']")); 
	var shangPuNums = "";
	puweiTrs.each(function (i) {
	  var pwId = $("[name$=':dph']",$(this)).val();
	  shangPuNums = shangPuNums + pwId;
	  if(i != puweiTrs.length-1)shangPuNums = shangPuNums+"-";
	});
	$("[name='m:me_lytksbb:sph']").val(shangPuNums); 
}

/*============每月收费项目=================*/

/*加载时，改变【每月收费项目】的【收费方式】*/
function loadChangeSffs(){
	var $sffs = $("[name$=':sffs']:visible",$("[tablename='me_lymysfxm']")); 
	$sffs.each(function (i) {
		changeSffs(this);
	});
}

/*每月收费项目  添加行事件*/
function me_lymysfxmAddRowAfterEvent(row){
	loadChangeSffs();
}

/*改变【每月收费项目】的【收费方式】*/
function changeSffs(obj){
	var $tr = $(obj).closest("tr");
	var sffs = $("[name$='sffs']",$tr).val(); /*收费方式*/
	$("[name$='dj']",$tr).off();
	 $("[name='shouFeiGuiZe']",$tr).show();
	switch(sffs){
		case "0":		/*固定金额类型 ： 只能录入收费金额*/
		case "4":		
			 $("[name$='dj']",$tr).attr("readonly","readonly").val("");
			 $("[name$='sfje']",$tr).removeAttr("readonly");
			 $("[name$='kl']",$tr).attr("readonly","readonly").val("");
		  break;
		case "1":		/*比率类型：只能录入比率*/
		case "2":
		case "5":
			 $("[name$=':sfgz']",$tr).val("");
			 $("[name$=':sfgzID']",$tr).val("");
			 $("[name='shouFeiGuiZe']",$tr).hide().parent().css("width","170px");
			$("[name$='dj']",$tr).attr("readonly","readonly").val("");
			$("[name$='sfje']",$tr).attr("readonly","readonly").val("");
			$("[name$='kl']",$tr).removeAttr("readonly");
		  break;
		case "3":		/*每平方米单价类型：只能录入单价*/
			 $("[name$='dj']",$tr).removeAttr("readonly");
			$("[name$='sfje']",$tr).attr("readonly","readonly").val("");
			$("[name$='kl']",$tr).attr("readonly","readonly").val("");
			//计算价格
			 $("[name$='dj']",$tr).on("blur",function(){
				 var dj =  FormUtil.commaback($(this).val()); 
				 var curRow = $(this).closest(".listRow"); 
				 var mianJi = $("[name='m:me_lytksbb:jzmj']").val();
				 if(!mianJi) alert("尚未生成面积");
				 $("[name$='sfje']",curRow).val(dj*mianJi);
			 });
		  break;
		default:
	}
}
/*校验每月收费项目*/
function validateMysfxm (){
	var zuLinStartDate  =$("[name='m:me_lytksbb:zlyxqq']").val();
	var zuLinEndDate  =$("[name='m:me_lytksbb:zlyxqz']").val();
	
	var sfxm = $(".listRow:visible",$("[tablename='me_lymysfxm']"));
	var sfxmArray =[];
	
	for(var i=0,row;row=sfxm[i++];){
		var ksrq = $("[name$='ksrq']",$(row)).val();
		var jsrq = $("[name$='jsrq']",$(row)).val();
		if(!ksrq) continue;
		var differTotalStartDate = FormDate.dateVal(ksrq, zuLinStartDate, "day");
		if(differTotalStartDate>0){
			$.ligerDialog.warn("每月收费项目开始日期不能早于 租赁开始日期！",'请核查');
			$("[name$='ksrq']",$(row)).val("")
		}
		
		var differTotalEndDate = FormDate.dateVal(jsrq, zuLinEndDate, "day");
		if(differTotalEndDate<0)  {
			$.ligerDialog.warn("每月收费项目结束日期不能晚于 租赁有效期止！",'请核查');
			$("[name$='jsrq']",$(row)).val("")
		}
		var curSfxmID =$("[name$='sfxmID']",$(row)).val();
		if(curSfxmID){
			for(var j=0,prevSfxm;prevSfxm=sfxmArray[j++];){
				var thisSfxmId = prevSfxm.split("$")[0];
				/*当前收费项目第二次出现*/
				if(thisSfxmId == curSfxmID){
					var differToPrevDate = FormDate.dateVal(ksrq,prevSfxm.split("$")[1], "day");
					if(differToPrevDate>=0){
						$.ligerDialog.warn("同一收费项目日期不得重复！",'请核查');
						$("[name$='ksrq']",$(row)).val("");
					}
				}
			  }
			
			sfxmArray.push(curSfxmID+"$"+jsrq);
			}
		}
}


/*显示租户信息*/
function showZh(){
	var zhid = $("[name$='zhid']").val();
	if(zhid == null || zhid == ""){
		$.ligerDialog.warn("请先选择租户!","提示");
		return ;
	}
	var url=__ctx + "/platform/form/bpmDataTemplate/detailData_shxxwh.ht?__pk__="+zhid;
	DialogUtil.open({
		height:600,
		width: 800,
		title : "查看租户信息",
		url: url, 
		isResize: true
	});
}


/*========== 结算标准信息 start =============*/

/*结算标准信息  添加行事件*/
function me_lyjsbzxxAddRowAfterEvent(row){
	var preRow = $(row).prev();
	if(preRow.attr("style") == 'display: none;')
		$("[name$=':xh']",row).val(1);
	else {
		var preNx = $("[name$=':xh']",preRow).val();
		$("[name$=':xh']",row).val(1+Number(preNx));
	}
	if(validateJsbzxxDates() ==false) $(row).remove();
	
	loadChangeJsgz();
}

/*结算标准信息   删除行事件*/
function me_lyjsbzxxDelRowBeforeEvent(row){
	var xh = $("[name$=':xh']",row).val();
	$("[name='s:me_lybdxx:xh'][value=" + xh + "]").closest("tr").remove();/*清除旧的记录*/
}

/*分解所有结算标准信息*/
function decomposeAllJs(){
	var $jsbzxxTrs = $(".listRow:visible",$("[tablename='me_lyjsbzxx']")); 
	$jsbzxxTrs.each(function (i) {
		decomposeSingleJs(this);
	});
	
	var $jsbzxxTrs = $("input[name='jsfj']:checked").closest("tr");
	showJsFj($jsbzxxTrs);
}

/*分解单个结算标准信息*/
function decomposeSingleJs(obj){
	var selectRow = $(obj);
	var isSingle = false;
	if(typeof obj == "undefined"){
		selectRow = $("input[name='fjsj']:checked").closest("tr");
		isSingle = true;
	}
	var xh = selectRow.find("[name$=':xh']").val();	/*obj 是结算标准对象*/
	if(!xh){
		$.ligerDialog.warn("请选择要分解的时间段","提示");
		return ;
	}
	
	$("[name='s:me_lybdxx:xh'][value=" + xh + "]").closest("tr").remove(); /*清除旧的记录*/
	
	var sjd =$("[name$='xh']",selectRow).val(); //序号
	var ksrq =$("[name$='ksrq']",selectRow).val();
	var jsrq =$("[name$='jsrq']",selectRow).val();
	var bdxs =FormUtil.commaback($("[name$='bdxs']",selectRow).val()); 
	var mbxs =FormUtil.commaback($("[name$='mbxs']",selectRow).val()); //目标销售
	
	var jsonData = decompose(sjd,ksrq,jsrq,bdxs,"",mbxs);
	/*填充数据*/
	var fenJieTableDiv =$('div[tablename="me_lybdxx"]');
	for (var i = 0, c; c = jsonData[i++];) {
		FormUtil.addRow(fenJieTableDiv);
		var rowcount=$("input[name$=':ny']",fenJieTableDiv).length;
		$($("input[name$=':xh']",fenJieTableDiv).get(rowcount-1)).val(xh);		
		$($("input[name$=':ny']",fenJieTableDiv).get(rowcount-1)).val(c.ZQY);				
		$($("input[name$=':ksrq']",fenJieTableDiv).get(rowcount-1)).val(c.KSRQ);			
		$($("input[name$=':jsrq']",fenJieTableDiv).get(rowcount-1)).val(c.JSRQ);			
		$($("input[name$=':bdxs']",fenJieTableDiv).get(rowcount-1)).val(c.YZJ);//保底销售/毛利
		$($("input[name$=':mbxs']",fenJieTableDiv).get(rowcount-1)).val(c.CDRQ);//目标销售/毛利
	}
	
	if(isSingle){
		showJsFj(selectRow);
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



/*显示保底信息列表*/
function showJsFj(obj){
	var $jsbzxxTrs = $(obj);
	if(typeof obj == "undefined"){
		$jsbzxxTrs = $("input[name='jsfj']:checked").closest("tr");
	}
	var xh = $jsbzxxTrs.find("[name$=':xh']").val();
	$(".listRow:visible",$("[tablename='me_lybdxx']")).hide();	/*先隐藏全部，在显示个体*/
	$("[name='s:me_lybdxx:xh'][value=" + xh + "]").closest("tr").show(); 
	
	$("td.tdNo", $(".listRow:visible",$("[tablename='me_lybdxx']"))).each(function(i) {
		$(this).text(i + 1);
	});
	
	$("[name='s:me_lybdxx:bdxs']").trigger("blur");
	$("[name='s:me_lybdxx:mbxs']").trigger("blur");
}

// 校验租金标准 时间
function validateJsbzxxDates(){
	//租赁有效期起，止
	var zuLinStartDate  =$("[name='m:me_lytksbb:zlyxqq']").val();
	var zuLinEndDate  =$("[name='m:me_lytksbb:zlyxqz']").val();
	if(!zuLinStartDate || !zuLinStartDate){ 
		$.ligerDialog.warn("尚未输入租赁起止日期！",'请核查'); 
		return false
	}
	
	var rows = $(".listRow:visible",$("[tablename='me_lyjsbzxx']"));
	for(var i=0,row;row=rows[i++];){
		var shiJianDuan = $("[name$='sjd']",$(row)).val(i); //时间段
		
		if(i>1){
			//开始日期等于上个结束日期加1
			var startDate = getNewDataStr($("[name$='jsrq']",$(rows[i-2])).val(),1,1);
			$("[name$='ksrq']",$(row)).val(startDate);
		}else{
			var differTotalStartDate = FormDate.dateVal($("[name$='ksrq']",$(rows)).val(), zuLinStartDate, "day");
			if(differTotalEndDate>=0)  {
				$.ligerDialog.warn("当前开始日期不能早于 租赁开始日期！",'请核查');
			} 
			$("[name$='ksrq']",$(row)).val(zuLinStartDate);
		}
		var differTotalEndDate = FormDate.dateVal($("[name$='jsrq']",$(rows)).val(), zuLinEndDate, "day");
		if(differTotalEndDate<0)  {
			$.ligerDialog.warn("当前结束日期不能晚于 租赁有效期止！",'请核查');
			$("[name$='jsrq']",$(rows)).val("")
			return ;
		}
	}
	$("[name='s:me_lyklzgz:sjd']").trigger("change");
}

//type: m=2/d=1/y=3   number:几天
function getNewDataStr(curDateStr,number,type){
	if(!curDateStr) return "";
	curDateStr = curDateStr.replace(/\-/g, "/");
	var curDate =  new Date(curDateStr);
	if(type==1) curDate.setDate(curDate.getDate()+number); 
	if(type==2) curDate.setMonth(curDate.getMonth()+number);
	if(type==3) curDate.setFullYear(curDate.getFullYear()+number); 
	
	return curDate.Format("yyyy-MM-dd");
}

/*加载时，改变【结算标准信息】的【结算规则】*/
function loadChangeJsgz(){
	var $jsgz = $("[name$=':jsgz']:visible",$("[tablename='me_lyjsbzxx']")); 
	$jsgz.each(function (i) {
		changeJsgz(this);
	});
}

/*改变【结算标准信息】的【结算规则】*/
function changeJsgz(obj){
	var $tr = $(obj).closest("tr");
	var jsgz = $("[name$=':jsgz']",$tr).val(); /*结算规则*/
	 generateKlz();
	switch(jsgz){
		case "1":		/*实销实结，产生一条扣率组，保底销售(只读)、保底扣率(只读)、目标销售(只读)、超额目标扣率(只读)、总保底(只读)、总目标(只读)，分解为(保底销售)*/
			 $("[name$=':bdxs']",$tr).attr("readonly","readonly").val("");	
			 $("[name$=':bdkl']",$tr).attr("readonly","readonly").val("");
			 $("[name$=':mbxs']",$tr).attr("readonly","readonly").val("");
			 
			 $("[name$=':cmbkl']",$tr).attr("readonly","readonly").val("");
			 $("[name$=':zbd']",$tr).attr("readonly","readonly").val("");
			 $("[name$=':zmb']",$tr).attr("readonly","readonly").val("");
			 
		 break;
		case "2":		/*固定毛利额，产生一条扣率组，保底毛利(可编辑)、保底扣率(只读)、目标销售(只读)、超额目标扣率(只读)、总保底(只读)、总目标(只读)，分解为(保底毛利)*/
			 $("[name$=':bdxs']",$tr).removeAttr("readonly");	
			 $("[name$=':bdkl']",$tr).attr("readonly","readonly").val("");
			 $("[name$=':mbxs']",$tr).attr("readonly","readonly").val("");
			 
			 $("[name$=':cmbkl']",$tr).attr("readonly","readonly").val("");
			 $("[name$=':zbd']",$tr).attr("readonly","readonly").val("");
			 $("[name$=':zmb']",$tr).attr("readonly","readonly").val("");
			 
			
		  break;
		case "3":		/*有保底销售无目标销售，产生一条扣率组，保底销售(可编辑)、保底扣率(可编辑)、目标销售(只读)、超额目标扣率(只读)、总保底(只读)、总目标(只读)，分解为(保底销售)*/
			 $("[name$=':bdxs']",$tr).removeAttr("readonly");
			 $("[name$=':bdkl']",$tr).removeAttr("readonly");
			 $("[name$=':mbxs']",$tr).attr("readonly","readonly").val("");
			 
			 $("[name$=':cmbkl']",$tr).attr("readonly","readonly").val("");
			 $("[name$=':zbd']",$tr).attr("readonly","readonly").val("");
			 $("[name$=':zmb']",$tr).attr("readonly","readonly").val("");
			 
		  break;
		case "4":		/*有保底毛利无目标毛利，产生一条扣率组，保底毛利(可编辑)、保底扣率(只读)、目标销售(只读)、超额目标扣率(只读)、总保底(只读)、总目标(只读)，分解为(保底毛利)*/
			 $("[name$=':bdxs']",$tr).removeAttr("readonly");	
			 $("[name$=':bdkl']",$tr).attr("readonly","readonly").val("");
			 $("[name$=':mbxs']",$tr).attr("readonly","readonly").val("");
			 
			 $("[name$=':cmbkl']",$tr).attr("readonly","readonly").val("");	
			 $("[name$=':zbd']",$tr).attr("readonly","readonly").val("");
			 $("[name$=':zmb']",$tr).attr("readonly","readonly").val("");
			 
			break;
		case "5":		/*有保底销售有目标销售，产生一条扣率组，保底毛利(可编辑)、保底扣率(可编辑)、目标销售(可编辑)、超额目标扣率(可编辑)、总保底(只读)、总目标(只读)，分解为(保底销售)*/
			 $("[name$=':bdxs']",$tr).removeAttr("readonly");		
			 $("[name$=':bdkl']",$tr).removeAttr("readonly");
			 $("[name$=':mbxs']",$tr).removeAttr("readonly");
			 
			 $("[name$=':cmbkl']",$tr).removeAttr("readonly");	
			 $("[name$=':zbd']",$tr).attr("readonly","readonly").val("");
			 $("[name$=':zmb']",$tr).attr("readonly","readonly").val("");	
			 
			break;
		case "6":		/*有保底毛利有目标毛利，产生一条扣率组，保底毛利(可编辑)、保底扣率(只读)、目标销售(可编辑)、超额目标扣率(可编辑)、总保底(只读)、总目标(只读)，分解为(保底毛利)*/
			 $("[name$=':bdxs']",$tr).removeAttr("readonly");	
			 $("[name$=':bdkl']",$tr).attr("readonly","readonly").val("");
			 $("[name$=':mbxs']",$tr).removeAttr("readonly");
			 
			 $("[name$=':cmbkl']",$tr).removeAttr("readonly");
			 $("[name$=':zbd']",$tr).attr("readonly","readonly").val("");
			 $("[name$=':zmb']",$tr).attr("readonly","readonly").val("");
			 
			 
			break;
		default:
	}
}

/*产生一条扣率组*/
function generateKlz(){
	var $kouLv = $(".listRow:visible",$("[tablename='me_lyklz']"));
	if($kouLv.size() == 0){
		FormUtil.addRow($('div[tablename$="me_lyklz"]'));  
		var appendRow = $(".listRow:visible",$("[tablename$='me_lyklz']"))[0];
		$("[name$='klzbh']",appendRow).val(1);
		$("[name$='ms']",appendRow).val("扣组率1");
	}
}

/*===============扣率规则==================*/

/*处理扣率时间段*/
function handelKoulvTime(obj){
	var curRow = $(obj).closest(".listRow");
	var shijianNo = $("[name$='sjd']",curRow).val();
	if(!shijianNo) return ;
	/*取的时间段*/
	var shiJianDuanNo =$("[name='s:me_lyjsbzxx:sjd'][value="+shijianNo+"]");
	if(shiJianDuanNo.length ==0) {
		$.ligerDialog.warn("该时间段不存在！ “"+shijianNo+"”",'请核查');
		$("[name$='sjd']",curRow).val("");
		return ;
	}
	var shiJianDuanRow = shiJianDuanNo.closest(".listRow");
	var startDate = $("[name$='ksrq']",shiJianDuanRow).val();
	var endDate = $("[name$='jsrq']",shiJianDuanRow).val();
	if(!startDate || !endDate) {
		$.ligerDialog.warn("改时间段信息不完善！ “"+shijianNo+"”",'请核查');
		$("[name$='sjd']",curRow).val("");
		return ;
	}
	
	$("[name$='ksrq']",curRow).val(startDate);
	$("[name$='jsrq']",curRow).val(endDate);
	
	initXSJEQvalidateRule();
	
	$("[name='s:me_lyklzgz:zqxsjeq']").trigger("blur");
	$("[name='s:me_lyklzgz:zqxsjez']").trigger("blur");
}

/*处理扣率组*/
function handelKoulvGroup(obj){
	var curRow = $(obj).closest(".listRow");
	var koulvNo = $(obj).val();
	if(!koulvNo) return ;
	/*取扣率组*/
	var KouLv =$("[name='s:me_lyklz:klzbh'][value="+koulvNo+"]");
	if(KouLv.length ==0) {
		$.ligerDialog.warn("该扣率组不存在！ “"+koulvNo+"”",'请核查');
		$(obj).val("");
		return ;
	}
	initXSJEQvalidateRule();
	
	$("[name='s:me_lyklzgz:zqxsjeq']").trigger("blur");
	$("[name='s:me_lyklzgz:zqxsjez']").trigger("blur");
}

/*校验周期销售金额止*/
function checkZqxsjez(obj){
	var curRow = $(obj).closest(".listRow");
	 var JeQi = $("[name$='zqxsjeq']",curRow).val();
	 var jeZhi =$(obj).val();
	 if(!JeQi || !jeZhi)
	 if( FormUtil.commaback(JeQi) > FormUtil.commaback(jeZhi)){
		 $.ligerDialog.warn("周期销售金额止:"+jeZhi+  "　不应该小于 周期销售金额起 ："+JeQi+"",'请核查！');
			$(obj).val("");
			return ;
	 }
	 initXSJEQvalidateRule();
	 
	 $("[name='s:me_lyklzgz:zqxsjeq']").trigger("blur");
	 $("[name='s:me_lyklzgz:zqxsjez']").trigger("blur");
}

/*循环判断扣率组，初始化销售金额起,校验扣率组金额*/
function initXSJEQvalidateRule(){ 
	var kouLvRules = $(".listRow:visible",$("[tablename='me_lyklzgz']"));
	var combineMessage = [];
	for(var i=0,rule;rule=kouLvRules[i++];){
		var sjd = $("[name$=':sjd']",rule).val(); /*时间段*/
		var klz = $("[name$=':klz']",rule).val();/*扣率组*/
		if(!sjd || !klz){
			return;
		}
		 /*第一次设置开始金额为当前金额为第几行*/	
		var len=-1;
		for(var j=0,r;r=combineMessage[j++];){
			if(r.split("-")[0] == sjd+","+klz){
				len =Number(r.split("-")[1]);
			}
		}
		if(len==-1){
			$("[name$=':zqxsjeq']",rule).val("0");
		}else{
			var JJJine = $("[name$=':zqxsjez']",$(kouLvRules[len])).val(); /*上一截止金额*/
			$("[name$='zqxsjeq']",rule).val(JJJine);
		}
		combineMessage.push(sjd+","+klz+"-"+(i-1));
	}
	
} 


/*=================物业费条款====================*/

/*添加物业费条款事件*/
function me_lywyftkAddRowAfterEvent(curRow){
	/*计算序号*/
	var preRow = $(curRow).prev();
	if(preRow.attr("style") == 'display: none;')
		$("[name$=':nx']",curRow).val(1);
	else {
		var preNx = $("[name$='nx']",preRow).val();
		$("[name$=':nx']",curRow).val(1+Number(preNx));
	}
	if(validateWyfDates() == false)$(curRow).remove();
}

/*校验物业费时间**/
function validateWyfDates(){
	//租赁有效期起 
	var zuLinStartDate  =$("[name='m:me_lytksbb:zlyxqq']").val();
	var zuLinEndDate  =$("[name='m:me_lytksbb:zlyxqz']").val();
	if(!zuLinStartDate || !zuLinStartDate){ 
		$.ligerDialog.warn("尚未输入租赁起止日期！",'请核查'); 
		return false;
	}
	//循环计算开始日期
	var rows = $(".listRow:visible",$("[tablename='me_lywyftk']"));
	for(var i=0,row;row=rows[i++];){
		var shiJianDuan = $("[name$='sjd']",$(row)).val(i); //时间段
		if(i>1){
			//开始日期等于上个结束日期加1
			var startDate = getNewDataStr($("[name$=':jsrq']",$(rows[i-2])).val(),1,1);
			$("[name$=':ksrq']",$(row)).val(startDate);
		}else{
			$("[name$=':ksrq']",$(row)).val(zuLinStartDate);
		}
		//校验结束日期
		var jsrq =$("[name$=':jsrq']",row).val();
		if(!jsrq) continue;
		var differTotalEndDate = FormDate.dateVal(jsrq, zuLinEndDate, "day");
		if(differTotalEndDate<0){
			$.ligerDialog.warn("结束日期不能晚于租赁有效期止！",'请核查！');
			$("[name$='jsrq']",curRow).val("")
		}
	}
}


/*总物业费**/
function calZongWuYeFei(obj){
	var curRow = $(obj).closest(".listRow"); 
	//校验是否超过总日期
	var zuLinEndDate =$("[name='m:me_lytksbb:zlyxqz']").val();
	var differTotalEndDate = FormDate.dateVal($("[name$=':jsrq']",curRow).val(), zuLinEndDate, "day");
	if(differTotalEndDate<0)  {
		$.ligerDialog.warn("当前结束日期不能晚于租赁有效期止！",'请核查');
		$("[name$=':jsrq']",curRow).val("");
		return ;
	}
	
	var danJia = FormUtil.commaback($("[name$=':dj']",curRow).val()); // 单价
	if(! danJia>0) return; 
	
	var mianJi = $("[name='m:me_lytksbb:jzmj']").val();
	if(!mianJi){
		$.ligerDialog.warn("建筑面积尚未计算生成!",'请核查');
		return ;
	}
	var baoDiType =$("[name$=':glfxx']",curRow).val();
	var jine = mianJi * danJia;
	$("[name$=':je']",curRow).val(jine);
	$("[name$=':je']",curRow).trigger("blur");
	
	var startDate = $("[name$=':ksrq']",curRow).val();
	var endDate = $("[name$=':jsrq']",curRow).val();
	if(baoDiType =="0"){ //按月
		var mounth = FormDate.dateVal(startDate, endDate, "day")+1;
		var zongJine = calMountZujin(startDate,endDate,jine);
	}else{
		var days = FormDate.dateVal(startDate, endDate, "day")+1;
		var zongJine = jine*days;
	}
	$("[name$=':zje']",curRow).val(zongJine); 
	$("[name$=':zje']",curRow).trigger("blur");
}

function calBaodiMubiao(obj,targetInput){
	var curRow = $(obj).closest(".listRow"); 
	var baodi = FormUtil.commaback($(obj).val());
	if(!baodi) return;
	var mianJi = $("[name='m:me_lytksbb:jzmj']").val();
	if(!mianJi){
		$.ligerDialog.warn("建筑面积尚未计算生成!",'请核查');
		return ;
	}
	var startDate = $("[name$=':ksrq']",curRow).val();
	var endDate = $("[name$=':jsrq']",curRow).val();
	var ZongJine = calMountZujin(startDate,endDate,baodi);
	$("[name$='"+targetInput+"']",curRow).val(ZongJine).trigger("change");
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

