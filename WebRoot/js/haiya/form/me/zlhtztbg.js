/*【租赁变更】**/
$(function(){
	loadChangeSffs();
	changeTYShouYin();
});

/*租赁时间变化事件**/
function zlsjChangeEvent(){
	validateZujinBiaozhunDates();
	$("[name='s:me_zlhtztbg_klzgz:sjd']").trigger("change");
	validateWyfDates();
	validateMysfxm();
}

/*切换收银方式*/
function changeTYShouYin(obj){
	var type = $("[name='m:me_zlhtztbg:tysy']").val();
	if(type==0) $(".tongyishouyin").hide();
	if(type==1) $(".tongyishouyin").show();
}
/*显示租户信息*/
function showZh(){
	var zhid = $("[name='m:me_zlhtztbg:zhID']").val();
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
    if(tableName == 'me_zlhtztbg_qtzkft' || tableName == 'me_zlhtztbg_hykzkft'){
      var rows =   $(".listRow",$("[tableName='"+tableName+"']"));  //左右子表列
      for(var i =0,row;row=rows[i++];){
        var id = $("[name='s:"+tableName+":ppID']",$(row)).val();  //唯一值
          if(id ==data.SBID) {                            //对话框，返回数据的那列
    	   $.ligerDialog.warn(data.NAME+"已经存在了！","提示信息");
            return false
         }
      }
    }
    
    if(tableName == 'me_zlhtztbg_hyklx'){
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
/* 表租金标准信息 添加行事件*/
function me_zlhtztbg_zjbzxxAddRowAfterEvent(row){
	var preRow = $(row).prev();
	/*获取原结算标准最后一个序号*/
	if(preRow.length==0 ||preRow.attr("style") == 'display: none;'){
		var preNx = 0;
	}else{
		var preNx = $("[name$='nx']",preRow).val();
	}
	$("[name$='nx']",$(row)).val(1+Number(preNx));
	$("[name$='xh']",$(row)).val(1+Number(preNx));
	validateZujinBiaozhunDates(row)
};

// 校验租金标准 时间
function validateZujinBiaozhunDates(curRow){ 
	//租赁有效期起，止
	var zuLinStartDate  =$("[name='m:me_zlhtztbg:zlyxqq']").val();
	var zuLinEndDate  =$("[name='m:me_zlhtztbg:zlyxqz']").val();
	if(!zuLinStartDate || !zuLinStartDate){  
		$.ligerDialog.warn("尚未输入租赁起止日期！",'请核查'); 
		$(curRow).remove();
		return
	}
	
	var rows = $(".listRow:visible",$("[tablename='me_zlhtztbg_zjbzxx']"));
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
	//ai 修改日期后，触发时间段chang事件
	$("[name='s:me_zlhtztbg_klzgz:sjd']").trigger("change");
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
	createKoulvzu();
}

function createKoulvzu(){
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
	
	var mianJi = $("[name='m:me_zlhtztbg:jzmj']").val();
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
	var shiJianDuanNo =$("[name='s:me_zlhtztbg_zjbzxx:sjd'][value="+shijianNo+"]");
	if(shiJianDuanNo.length ==0) {
		$.ligerDialog.warn("该时间段不存在！ “"+shijianNo+"”",'请核查');
		$("[name$='sjd']",curRow).val("");
		return ;
	}
	var shiJianDuanRow = shiJianDuanNo.closest(".listRow");
	var StartDate = $("[name$='ksrq']",shiJianDuanRow).val();
	var endDate = $("[name$='jsrq']",shiJianDuanRow).val();
	if(!StartDate || !endDate) {
		$.ligerDialog.warn("改时间段信息不完善！ “"+shijianNo+"”",'请核查');
		$("[name$='sjd']",curRow).val("");
		return ;
	}
	
	$("[name$='ksrq']",curRow).val(StartDate);
	$("[name$='jsrq']",curRow).val(endDate);
	
	initXSJEQvalidateRule();
}

/*处理扣率组*/
function handelKoulvGroup(obj){
	var curRow = $(obj).closest(".listRow");
	var koulvNo = $(obj).val();
	if(!koulvNo) return ;
	/*取扣率组*/
	var KouLv =$("[name='s:me_zlhtztbg_klz:klzbh'][value="+koulvNo+"]");
	if(KouLv.length ==0) {
		$.ligerDialog.warn("该扣率组不存在！ “"+koulvNo+"”",'请核查');
		$(obj).val("");
		return ;
	}
	initXSJEQvalidateRule();
}
/*循环判断扣率组，初始化销售金额起,校验扣率组金额*/
function initXSJEQvalidateRule(){ 
	var kouLvRules = $(".listRow:visible",$("[tablename='me_zlhtztbg_klzgz']"));
	var combineMessage = [];
	for(var i=0,rule;rule=kouLvRules[i++];){
		var sjd = $("[name$='sjd']",rule).val(); /*时间段*/
		var klz = $("[name$='klz']",rule).val();/*扣率组*/
		if(!sjd || !klz) return;
		 /*第一次设置开始金额为当前金额为第几行*/	
		var len=-1;
		for(var j=0,r;r=combineMessage[j++];){
			if(r.split("-")[0] == sjd+","+klz){
				len =Number(r.split("-")[1]);
			}
		}
		if(len==-1){
			$("[name$='zqxsjeq']",rule).val("0");$("[name$='zqxsjeq']",rule).trigger("change");
		}else{
			var JJJine = $("[name$='zqxsjez']",$(kouLvRules[len])).val(); /*上一截止金额*/
			$("[name$='zqxsjeq']",rule).val(JJJine);
		}
		combineMessage.push(sjd+","+klz+"-"+(i-1));
	}
} 
/*校验周期销售金额止*/
function 　checkZqxsjez(obj){
	var curRow = $(obj).closest(".listRow");
	 var JeQi = $("[name$='zqxsjeq']",curRow).val();
	 var jeZhi =$(obj).val();
	 if(JeQi && jeZhi)
	 if( FormUtil.commaback(JeQi) > FormUtil.commaback(jeZhi)){
		 $.ligerDialog.warn("周期销售金额止:"+jeZhi+  "　不应该小于 周期销售金额起 ："+JeQi+"",'请核查！');
			$(obj).val("");
			return ;
	 }
	 initXSJEQvalidateRule();
}
/*【物业费条款添加事件】me_zlhtztbg_wyftk*/
function me_zlhtztbg_wyftkAddRowAfterEvent(curRow){
	var preRow = $(curRow).prev();
	/*获取原结算标准最后一个序号*/
	if(preRow.length==0 ||preRow.attr("style") == 'display: none;'){
		var preNx = 0;
	}else{
		var preNx = $("[name$='nx']",preRow).val();
	}
	$("[name$='nx']",$(curRow)).val(1+Number(preNx));
	$("[name$='xh']",$(curRow)).val(1+Number(preNx));
	
	/*校验物业费日期*/
	if(validateWyfDates() == false)$(curRow).remove();
}

/*校验物业费时间**/
function validateWyfDates(){
	//租赁有效期起
	var zuLinStartDate  =$("[name='m:me_zlhtztbg:zlyxqq']").val();
	var zuLinEndDate  =$("[name='m:me_zlhtztbg:zlyxqz']").val();
	if(!zuLinStartDate || !zuLinStartDate){ 
		$.ligerDialog.warn("尚未输入租赁起止日期！",'请核查'); 
		return false;
	}
	//循环计算开始日期
	var rows = $(".listRow:visible",$("[tablename='me_zlhtztbg_wyftk']"));
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
	var zuLinEndDate =$("[name='m:me_zlhtztbg:zlyxqz']").val();
	var differTotalEndDate = FormDate.dateVal($("[name$=':jsrq']",curRow).val(), zuLinEndDate, "day");
	if(differTotalEndDate<0)  {
		$.ligerDialog.warn("当前结束日期不能晚于租赁有效期止！",'请核查');
		$("[name$='jsrq']",curRow).val("")
		return ;
	}
	
	var danJia = FormUtil.commaback($("[name$='dj']",curRow).val()); // 单价
	if(! danJia>0) return; 
	
	var mianJi = $("[name='m:me_zlhtztbg:jzmj']").val();
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
	var zuLinStartDate  =$("[name='m:me_zlhtztbg:zlyxqq']").val();
	var zuLinEndDate  =$("[name='m:me_zlhtztbg:zlyxqz']").val();
	
	var sfxm = $(".listRow:visible",$("[tablename='me_zlhtztbg_mysfxm']"));
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

/**分解代码 begin**/

/*测试分解数据*/
function testData(){
	var jsonStr = "[{sTime:'2014-01-01',eTime:'2014-02-02',money:'10000'},{sTime:'2014-02-01',eTime:'2014-03-01',money:'10000'}]";
	var jsonData = eval('(' + jsonStr + ')');
	return jsonData;
}



/*结算标准信息   删除行事件*/
function me_zlhtztbg_zjbzxxDelRowBeforeEvent(row){
	var xh = $("[name$=':xh']",row).val();
	$("[name='s:me_lybdxx:xh'][value=" + xh + "]").closest("tr").remove();/*清除旧的记录*/
}

/*分解所有结算标准信息  */
function decomposeAllJs(tableName,fenjieTable){
	debugger;
	var $jsbzxxTrs = $(".listRow:visible",$("[tablename='"+tableName+"']"));   
	$jsbzxxTrs.each(function (i) {
		decomposeSingle($(this),fenjieTable);
	});
	
	var $jsbzxxTrs = $("input[name$=':fjsj']:checked",$("[tablename='"+tableName+"']")).closest("tr");
	showFjsj($jsbzxxTrs,fenjieTable);
}
function decomposeAllWyf(){
	decomposeAllJs('me_zlhtztbg_wyftk','me_zlhtztbg_wyffj');
}
function decomposeSingleWyf(){
	decomposeSingleJs('me_zlhtztbg_wyftk','me_zlhtztbg_wyffj');
}
function decomposeAllYzj(){
	decomposeAllJs('me_zlhtztbg_zjbzxx','me_zlhtztbg_ydzjfj');
}
function decomposeSingleYzj(){
	decomposeSingleJs('me_zlhtztbg_zjbzxx','me_zlhtztbg_ydzjfj');
}

/*单个分解*/
function decomposeSingleJs(tableName,fenjieTable){
	debugger;
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
	
	//物业费
	if(fenJieTable == 'me_zlhtztbg_wyffj'){
		var zj =FormUtil.commaback($("[name$='je']",selectRow).val());
		var sfgz =  $("[name='m:me_zlhtztbg:wyfsfgzID']").val();
	//标准租金
	}else{
		var zj =FormUtil.commaback($("[name$='zj']",selectRow).val()); 
		var sfgz =$("[name='m:me_zlhtztbg:zjsfgzID']").val();
	}
	var jsonData = decompose(sjd,ksrq,jsrq,zj,sfgz);
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



