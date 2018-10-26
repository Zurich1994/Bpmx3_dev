$(function(){
	loadChangeSffs();
	loadChangeJsgz();
});
/*租赁时间变化事件**/
function zlsjChangeEvent(){
	validateZujinBiaozhunDates();
	$("[name='s:me_lyhtztbg_klzgz:sjd']").trigger("change");
	validateWyfDates();
	validateMysfxm();
}
/*显示租户信息*/
function showZh(){
	var zhid = $("[name='m:me_lyhtztbg:zhID']").val();
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
/*校验子表填充数据是否重复**/ 
function checkDataBeforeInsert(data,tableName){
    if(tableName == 'me_lyhtztbg_qtzkft' || tableName == 'me_lyhtztbg_hykzkft'){
      var rows =   $(".listRow",$("[tableName='"+tableName+"']"));  //左右子表列
      for(var i =0,row;row=rows[i++];){
        var id = $("[name='s:"+tableName+":ppID']",$(row)).val();  //唯一值
          if(id ==data.SBID) {                            //对话框，返回数据的那列
    	   $.ligerDialog.warn(data.NAME+"已经存在了！","提示信息");
            return false
         }
      }
    }
    
    if(tableName == 'me_lyhtztbg_hyklx'){
        var rows = $(".listRow",$("[tableName='"+tableName+"']"));  //左右子表列
        for(var i =0,row;row=rows[i++];){
          var id = $("[name='s:"+tableName+":hyklxID']",$(row)).val();  //唯一值
            if(id == data.HYKTYPE) {                            //对话框，返回数据的那列
      	   $.ligerDialog.warn(data.HYKNAME+"已经存在了！","提示信息");
              return false
           }
        }
      }
    
     return true;
  }

/* 【表结算标准信息】 添加行事件*/
function me_lyhtztbg_jsbzxxAddRowAfterEvent(row){
	var preRow = $(row).prev();
	/*获取原结算标准最后一个序号*/
	if(preRow.length==0 ||preRow.attr("style") == 'display: none;'){
		preRow = $(".listRow:visible",$("[tablename='me_lyhtztbg_yjsbzxx']")).last();
	}
	var preNx = $("[name$=':xh']",preRow).val();
	if(!preNx)preNx = 0;
	$("[name$=':xh']",row).val(1+Number(preNx));
	$("[name$='sjd']",row).val(1+Number(preNx));
	validateZujinBiaozhunDates(row)
	changeJsgz(row);
};
// 校验租金标准 时间
function validateZujinBiaozhunDates(curRow){
	//租赁有效期起，止 
	var zuLinStartDate  =$("[name='m:me_lyhtztbg:zlyxqq']").val();
	var zuLinEndDate  =$("[name='m:me_lyhtztbg:zlyxqz']").val();
	if(!zuLinStartDate || !zuLinStartDate){  
		$.ligerDialog.warn("尚未输入租赁起止日期！",'请核查'); 
		$(curRow).remove();
		return
	}
	
	var rows = $(".listRow:visible",$("[tablename='me_lyhtztbg_jsbzxx']"));
	for(var i=0,row;row=rows[i++];){
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
	$("[name='s:me_lyhtztbg_klzgz:sjd']").trigger("change");
}

/*加载时，改变【结算标准信息】的【结算规则】*/
function loadChangeJsgz(){
	var $jsgz = $("[name$=':jsgz']:visible",$("[tablename='me_lyhtztbg_jsbzxx']")); 
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

function calBaodiMubiao(obj,targetInput){
	var curRow = $(obj).closest(".listRow"); 
	var baodi = FormUtil.commaback($(obj).val());
	if(!baodi) return;
	var mianJi = $("[name='m:me_lyhtztbg:jzmj']").val();
	if(!mianJi){
		$.ligerDialog.warn("建筑面积尚未计算生成!",'请核查');
		return ;
	}
	var startDate = $("[name$=':ksrq']",curRow).val();
	var endDate = $("[name$=':jsrq']",curRow).val();
	var ZongJine = calMountZujin(startDate,endDate,baodi);
	$("[name$='"+targetInput+"']",curRow).val(ZongJine).trigger("change");
}


/*产生一条扣率组*/
function generateKlz(){
	if($(".listRow:visible",$("[tablename$='klz']")).length ==0){
		FormUtil.addRow($('div[tablename$="klz"]'));  
		var appendRow = $(".listRow:visible",$("[tablename$='klz']"))[0];
		$("[name$='klzbh']",appendRow).val(1);
		$("[name$='ms']",appendRow).val("扣组率1");
	}
}

//单价计算总租金
function calZongZuJin(obj){
	var curRow = $(obj).closest(".listRow");
	var danJia = FormUtil.commaback($("[name$='dj']",curRow).val()); // 单价
	if(! danJia>0) return; 
	
	var mianJi = $("[name='m:me_lyhtztbg:jzmj']").val();
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


/*生成扣率组规则*/
function createKoulvRules(){
	return;
	//时间段
	var rows = $(".listRow:visible",$("[tablename='me_zlzjbzxx']"));
	var kouLvs = $(".listRow:visible",$("[tablename='me_zlklz']"));
	for(var i=0,row;row=rows[i++];){
		for(var j=0,kouLv;kouLv=kouLvs[j++];){

		}
	}
}
/*处理扣率时间段*/
function handelKoulvTime(obj){
	var curRow = $(obj).closest(".listRow");
	var shijianNo = $("[name$='sjd']",curRow).val();
	if(!shijianNo) return ;
	/*取的时间段*/
	var shiJianDuanNo =$("[name='s:me_lyhtztbg_jsbzxx:sjd'][value="+shijianNo+"]");
	if(shiJianDuanNo.length ==0) {
		$.ligerDialog.warn("该时间段不存在！ “"+shijianNo+"”",'请核查');
		$("[name$='sjd']",curRow).val("");
		return ;
	}
	var shiJianDuanRow = shiJianDuanNo.closest(".listRow");
	var StartDate = $("[name$='ksrq']",shiJianDuanRow).val();
	var endDate = $("[name$='jsrq']",shiJianDuanRow).val();
	if(!StartDate || !endDate) {
		$.ligerDialog.warn("该时间段信息不完善！ “"+shijianNo+"”",'请核查');
		$("[name$='sjd']",curRow).val("");
		return ;
	}
	
	$("[name$='ksrq']",curRow).val(StartDate);
	$("[name$='jsrq']",curRow).val(endDate);
}

/*处理扣率组*/
function handelKoulvGroup(obj){
	var curRow = $(obj).closest(".listRow");
	var koulvNo = $(obj).val();
	if(!koulvNo) return ;
	/*取扣率组*/
	var KouLv =$("[name='s:me_lyhtztbg_klz:klzbh'][value="+koulvNo+"]");
	if(KouLv.length ==0) {
		$.ligerDialog.warn("该扣率组不存在！ “"+koulvNo+"”",'请核查');
		$(obj).val("");
		return ;
	}
}
/*【物业费条款添加事件】me_lyhtztbg_wyftk*/
function me_lyhtztbg_wyftkAddRowAfterEvent(curRow){
	var preRow = $(curRow).prev();
	/*获取原结算标准最后一个序号*/
	if(preRow.length==0 || preRow.attr("style") == 'display: none;'){
		preRow = $(".listRow:visible",$("[tablename='me_lyhtztbg_ywyftk']")).last();
	}
	var preNx = $("[name$='nx']",preRow).val();
	if(!preNx) preNx=0;
	$("[name$='nx']",curRow).val(1+Number(preNx));
	$("[name$='xh']",curRow).val(1+Number(preNx));
	if(validateWyfDates() == false)$(curRow).remove();
}

/*校验物业费时间**/
function validateWyfDates(){
	//租赁有效期起
	//租赁有效期起
	var zuLinStartDate  =$("[name='m:me_lyhtztbg:zlyxqq']").val();
	var zuLinEndDate  =$("[name='m:me_lyhtztbg:zlyxqz']").val();
	if(!zuLinStartDate || !zuLinStartDate){ 
		$.ligerDialog.warn("尚未输入租赁起止日期！",'请核查'); 
		return false;
	}
	//循环计算开始日期
	var rows = $(".listRow:visible",$("[tablename='me_lyhtztbg_wyftk']"));
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
	var zuLinEndDate =$("[name='m:me_lyhtztbg:zlyxqz']").val();
	var differTotalEndDate = FormDate.dateVal($("[name$=':jsrq']",curRow).val(), zuLinEndDate, "day");
	if(differTotalEndDate<0)  {
		$.ligerDialog.warn("当前结束日期不能晚于租赁有效期止！",'请核查');
		$("[name$='jsrq']",curRow).val("")
		return ;
	}
	
	var danJia = FormUtil.commaback($("[name$='dj']",curRow).val()); // 单价
	if(! danJia>0) return; 
	
	var mianJi = $("[name='m:me_lyhtztbg:jzmj']").val();
	if(!mianJi){
		$.ligerDialog.warn("建筑面积尚未计算生成!",'请核查');
		return ;
	}
	var baoDiType =$("[name$='glfxx']",curRow).val();
	var jine = mianJi * danJia;
	$("[name$='je']",curRow).val(jine); $("[name$='je']",curRow).trigger("change");
	
	var startDate = $("[name$='ksrq']",curRow).val();
	var endDate = $("[name$='jsrq']",curRow).val();
	if(baoDiType =="0"){ //按月
		var mounth = FormDate.dateVal(startDate, endDate, "day")+1;
		var zongJine = calMountZujin(startDate,endDate,jine);
	}else{
		var days = FormDate.dateVal(startDate, endDate, "day")+1;
		var zongJine = jine*days;
	}
	$("[name$='zje']",curRow).val(zongJine);$("[name$='zje']",curRow).trigger("change");
 }

/*加载时，改变【每月收费项目】的【收费方式】*/
function loadChangeSffs(){
	var sffs = $("[name$='sffs']:visible",$("[tablename='me_zlmysfxm']"));
	sffs.each(function (i) {
		changeSffs(this);
	});
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
				 var mianJi = $("[name='m:me_zltksbb:jzmj']").val();
				 if(!mianJi) alert("尚未生成面积");
				 $("[name$='sfje']",curRow).val(dj*mianJi);
			 });
		  break;
		default:
	}
}

/*校验每月收费项目*/
function validateMysfxm (){
	var zuLinStartDate  =$("[name='m:me_lyhtztbg:zlyxqq']").val();
	var zuLinEndDate  =$("[name='m:me_lyhtztbg:zlyxqz']").val();
	
	var sfxm = $(".listRow:visible",$("[tablename='me_lyhtztbg_mysfxm']"));
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




/*结算标准信息   删除行事件*/
function me_lyhtztbg_zjbzxxDelRowBeforeEvent(row){
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
function decomposeAllWyf(){
	decomposeAllJs('me_lyhtztbg_wyftk','me_lyhtztbg_wyffj');
}
function decomposeSingleWyf(){
	decomposeSingleJs('me_lyhtztbg_wyftk','me_lyhtztbg_wyffj');
}
function decomposeAllBaodi(){
	decomposeAllJs('me_lyhtztbg_jsbzxx','me_lyhtztbg_bdxx');
}
function decomposeSingleBaodi(){
	decomposeSingleJs('me_lyhtztbg_jsbzxx','me_lyhtztbg_bdxx');
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
	var yzj,mbxs;
	//物业费
	if(fenJieTable == 'me_lyhtztbg_wyffj'){
		yzj =FormUtil.commaback($("[name$='je']",selectRow).val());
	//标准租金
	}else{
		yzj=FormUtil.commaback($("[name$='bdxs']",selectRow).val()); 
		 mbxs =FormUtil.commaback($("[name$='mbxs']",selectRow).val()); 
	}
	var jsonData = decompose(sjd,ksrq,jsrq,yzj,"",mbxs);
	/*填充数据*/
	for (var i = 0, c; c = jsonData[i++];) {
		FormUtil.addRow(fenJieTableDiv);
		var rowcount=$("input[name$=':ny']",fenJieTableDiv).length;
		$($("input[name$=':xh']",fenJieTableDiv).get(rowcount-1)).val(xh);		
		$($("input[name$=':ny']",fenJieTableDiv).get(rowcount-1)).val(c.ZQY);				
		$($("input[name$=':ksrq']",fenJieTableDiv).get(rowcount-1)).val(c.KSRQ);			
		$($("input[name$=':jsrq']",fenJieTableDiv).get(rowcount-1)).val(c.JSRQ);			
		if(fenJieTable == 'me_lyhtztbg_wyffj'){
			$($("input[name$=':je']",fenJieTableDiv).get(rowcount-1)).val(c.YZJ);	
		}else{
			$($("input[name$=':bdxsml']",fenJieTableDiv).get(rowcount-1)).val(c.YZJ);	
			$($("input[name$=':mbxsml']",fenJieTableDiv).get(rowcount-1)).val(c.MBXS);
		}
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


/*格式化填充的日期*/
$().ready(function (){
	$("[datefmt]").live('change',function (){
		var me = $(this),val = me.val();
		if(!$.isEmpty(val)){
			var arry = val.split(".");
			var sTime = arry[0].replace(/\-/g, "/");
			var datefmt = me.attr("datefmt");
			var nowDate = new Date(sTime).Format(datefmt);
			me.val(nowDate);
		}
	});
	
	$("[issameas]").change(function(){
		var me = $(this),curVal = me.val();
		var curName = me.attr("name");
		if(!curName) return;
		curName = curName.split(":");
		var targetName = me.attr("isSameAs");
		if(targetName=='true' && curName.length == 3){
			targetName =curName[0] +":"+curName[1] +":y"+curName[2]
		}
		 
		var targetValue = $("[name='"+targetName+"']").val();
		if(targetValue != curVal){
			me.css("color","red");
		}else{
			me.css("color","");
		}
	});
	
});



