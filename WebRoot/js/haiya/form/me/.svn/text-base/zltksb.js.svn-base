
/*【租赁确认书】**/
$(function(){
	
	changeSftstk();	/*【是否特殊条款】*/
	
	changeSblx();	/*【申报类型】*/
	
	loadChangeSffs();
	
});
/*校验子表填充数据是否重复**/
function checkDataBeforeInsert(data,tableName){
    if(tableName == 'me_zlpwxx'){
      var rows =   $(".listRow",$("[tableName='me_zlpwxx']"));  //左右子表列
      for(var i =0,row;row=rows[i++];){
        var id = $("[name='s:me_zlpwxx:pwID']",$(row)).val();  //唯一值
          if(id ==data.WLDPID) {                              //对话框，返回数据的那列
    	   $.ligerDialog.warn(data.WLDPDM+"已经存在了！","提示信息");
            return false
         }
      }
    }
     return true;
  }
/*显示租户信息*/
function showZh(){
	var zhid = $("[name='m:me_zltksbb:zhid']").val();
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
/*租赁时间变化事件**/
function zlsjChangeEvent(){
	validateZujinBiaozhunDates();
	$("[name='s:me_zlklzgz:sjd']").trigger("change");
	validateWyfDates();
	validateMysfxm();
	
}
//计算所有铺位,与面积
function calAllPuwei(obj){
	var puweiTrs = $(".listRow:visible",$("[tablename='me_zlpwxx']")); 
	var shangPuNums = "";
	puweiTrs.each(function (i) {
	  var pwId = $("[name$='dph']",$(this)).val();
	  shangPuNums = shangPuNums + pwId;
	  if(i != puweiTrs.length-1)shangPuNums = shangPuNums+"-";
	});
	$("[name='m:me_zltksbb:sph']").val(shangPuNums); 
}
// 表me_zlzjbzxx 添加行事件
function me_zlzjbzxxAddRowAfterEvent(row){validateZujinBiaozhunDates(row)};
// 校验租金标准 时间
function validateZujinBiaozhunDates(curRow){
	//租赁有效期起，止
	var zuLinStartDate  =$("[name='m:me_zltksbb:zlyxqq']").val();
	var zuLinEndDate  =$("[name='m:me_zltksbb:zlyxqz']").val();
	if(!zuLinStartDate || !zuLinStartDate){ 
		$.ligerDialog.warn("尚未输入租赁起止日期！",'请核查'); 
		$(curRow).remove();
		return
	}
	
	var rows = $(".listRow:visible",$("[tablename='me_zlzjbzxx']"));
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
	$("[name='s:me_zlklzgz:sjd']").trigger("change");
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
	createKoulvzu();
}

function createKoulvzu(){
	if($(".listRow:visible",$("[tablename$='klz']")).length ==0){
		FormUtil.addRow($('div[tablename$="klz"]'));  
		var appendRow = $(".listRow:visible",$("[tablename$='klz']"))[0];
		$("[name$='klzbh']",appendRow).val(1);
		$("[name$='ms']",appendRow).val("扣组率1");
		$("[name$='jckl']",appendRow).val("0");
	}
}

//单价计算总租金
function calZongZuJin(obj){
	var curRow = $(obj).closest(".listRow"); 
	var danJia = FormUtil.commaback($("[name$='dj']",curRow).val()); // 单价
	if(! danJia>0) return; 
	
	var mianJi = $("[name='m:me_zltksbb:jzmj']").val();
	if(!mianJi){
		$.ligerDialog.warn("建筑面积尚未计算生成!",'请核查');
		return ;
	}
	var baoDiType =$("[name$='bdxx']",curRow).val();
	var zuJin = mianJi * danJia;
	$("[name$='zj']",curRow).val(zuJin); setToJine($("[name$='zj']",curRow));
	
	var startDate = $("[name$='ksrq']",curRow).val();
	var endDate = $("[name$='jsrq']",curRow).val();
	if(baoDiType =="0"){ //按月
		var mounth = FormDate.dateVal(startDate, endDate, "day")+1;
		var zongZujin = calMountZujin(startDate,endDate,zuJin);
	}else{
		var days = FormDate.dateVal(startDate, endDate, "day")+1;
		var zongZujin = zuJin*days;
	}
	zongZujin = FormMath.tofixed(zongZujin,2);
	$("[name$='zzj']",curRow).val(zongZujin); setToJine($("[name$='zzj']",curRow));
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
	//租金标准信息
	var zjbzxx = $(".listRow:visible",$("[tablename='me_zlzjbzxx']"));
	var kouLvs = $(".listRow:visible",$("[tablename='me_zlklz']"));
	var klzggRows =$(".listRow:visible",$("[tablename='me_zlklzgz']")); //扣率组规则
	var klzggTableDiv =$("div[tablename='me_zlklzgz']");
	if(zjbzxx.length==0 ||kouLvs.length==0){
		$.ligerDialog.warn("租金标准信息或者扣率组尚未完善！",'请核查');return;
	}
	klzggRows.remove();
	var rowIndex=0;
	for(var j=0,kouLv;kouLv=kouLvs[j++];){
		for(var i=0,row;row=zjbzxx[i++];){
			var sjd = $("[name$='sjd']",row).val();
			var kouLvZu = $("[name$='klzbh']",kouLv).val();
			
			FormUtil.addRow(klzggTableDiv);
			var curRol = $(".listRow:visible",$("[tablename='me_zlklzgz']"))[rowIndex++];
			$("[name$='sjd']",curRol).val(sjd);$("[name$='sjd']",curRol).trigger("change");
			$("[name$='lvz']",curRol).val(kouLvZu);$("[name$='lvz']",curRol).trigger("change");
		}
	}
}

/*处理扣率时间段*/
function handelKoulvTime(obj){
	var curRow = $(obj).closest(".listRow");
	var shijianNo = $("[name$='sjd']",curRow).val();
	if(!shijianNo) return ;
	/*取的时间段*/
	var shiJianDuanNo =$("[name='s:me_zlzjbzxx:sjd'][value="+shijianNo+"]");
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
	var KouLv =$("[name='s:me_zlklz:klzbh'][value="+koulvNo+"]");
	if(KouLv.length ==0) {
		$.ligerDialog.warn("该扣率组不存在！ “"+koulvNo+"”",'请核查');
		$(obj).val("");
		return ;
	}
	initXSJEQvalidateRule();
}
/*循环判断扣率组，初始化销售金额起,校验扣率组金额*/
function initXSJEQvalidateRule(){
	var kouLvRules = $(".listRow:visible",$("[tablename='me_zlklzgz']"));
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
			$("[name$='zqxsjeq']",rule).val("0");setToJine($("[name$='zqxsjeq']",rule));
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
	 if(JeQi&&jeZhi)
	 if( FormUtil.commaback(JeQi) > FormUtil.commaback(jeZhi)){
		 $.ligerDialog.warn("周期销售金额止:"+jeZhi+  "　不应该小于 周期销售金额起 ："+JeQi+"",'请核查！');
			$(obj).val("");
			return ;
	 }
	 initXSJEQvalidateRule();
}

function me_zlwyftkAddRowAfterEvent(curRow){
	/*计算序号*/
	var preRow = $(curRow).prev();
	if(preRow.attr("style") == 'display: none;')
		$("[name$='nx']",curRow).val(1);
	else {
		var preNx = $("[name$='nx']",preRow).val();
		$("[name$='nx']",curRow).val(1+Number(preNx));
	}
	
	if(validateWyfDates() == false)$(curRow).remove();
	
}
/*校验物业费时间**/
function validateWyfDates(){
	//租赁有效期起
	var zuLinStartDate  =$("[name='m:me_zltksbb:zlyxqq']").val();
	var zuLinEndDate  =$("[name='m:me_zltksbb:zlyxqz']").val();
	if(!zuLinStartDate || !zuLinStartDate){ 
		$.ligerDialog.warn("尚未输入租赁起止日期！",'请核查'); 
		return false;
	}
	//循环计算开始日期
	var rows = $(".listRow:visible",$("[tablename='me_zlwyftk']"));
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
	var zuLinEndDate =$("[name='m:me_zltksbb:zlyxqz']").val();
	var differTotalEndDate = FormDate.dateVal($("[name$=':jsrq']",curRow).val(), zuLinEndDate, "day");
	if(differTotalEndDate<0)  {
		$.ligerDialog.warn("当前结束日期不能晚于租赁有效期止！",'请核查');
		$("[name$='jsrq']",curRow).val("")
		return ;
	}
	
	var danJia = FormUtil.commaback($("[name$='dj']",curRow).val()); // 单价
	if(! danJia>0) return; 
	
	var mianJi = $("[name='m:me_zltksbb:jzmj']").val();
	if(!mianJi){
		$.ligerDialog.warn("建筑面积尚未计算生成!",'请核查');
		return ;
	}
	var baoDiType =$("[name$='glfxx']",curRow).val();
	var jine = mianJi * danJia;
	$("[name$='je']",curRow).val(FormMath.tofixed(jine,2)); setToJine($("[name$='je']",curRow));
	
	var startDate = $("[name$='ksrq']",curRow).val();
	var endDate = $("[name$='jsrq']",curRow).val();
	if(baoDiType =="0"){ //按月
		var mounth = FormDate.dateVal(startDate, endDate, "day")+1;
		var zongJine = calMountZujin(startDate,endDate,jine);
	}else{
		var days = FormDate.dateVal(startDate, endDate, "day")+1;
		var zongJine = jine*days;
	}
	$("[name$='zje']",curRow).val(FormMath.tofixed(zongJine,2)); setToJine($("[name$='zje']",curRow));
 }

/*改变【是否特殊条款】*/
function changeSftstk(){
	var sftstk = $("[name='m:me_zltksbb:sftstk']").val();
	
	if(sftstk == "1") $("#tstk_tr").show();
	else{ 
		$("#tstk_tr").hide();
		$("textarea",$("#tstk_tr")).val("");
	}
}

/*改变【申报类型】*/
function changeSblx(){
	var sblx = $("[name='m:me_zltksbb:sblx']").val();
	
	if(sblx == "2") $("#sbyy_tr").show();
	else {	
		$("#sbyy_tr").hide();
		$("textarea",$("#sbyy_tr")).val("");
	}
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
	var zuLinStartDate  =$("[name='m:me_zltksbb:zlyxqq']").val();
	var zuLinEndDate  =$("[name='m:me_zltksbb:zlyxqz']").val();
	
	var sfxm = $(".listRow:visible",$("[tablename='me_zlmysfxm']"));
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
//设置成金额
function setToJine(me){
	var value = me.val();
	var json=null;
	try{
		var jsonStr=me.attr("showtype");
		json=eval('('+jsonStr+')');
	}
	catch(err){}
	if(json!=null){
		var coinvalue=json.coinValue;
		var iscomdify=json.isShowComdify;
		var decimallen=json.decimalValue;
		//去除货币标签
		if (coinvalue && value.split(coinvalue) != -1) {
			var ary = value.split(coinvalue);
			value = ary.join("");
		}
		
		if (value.indexOf(',') != -1) {
			value = Number(value);
		}
		if(iscomdify && iscomdify=='1'){
			var value = $.comdify(value);
		}
		
			// 小数处理
		if (decimallen > 0 && value) {
			var zeroLen = '';
			if (value.indexOf('.') != -1) {
				var ary = value.split('.');
				var temp = ary[ary.length - 1];
				if (temp.length > 0 && temp.length < decimallen) {
					for (var i = 0; i < decimallen- temp.length; i++) {
						zeroLen = zeroLen + '0';
					}
					value = value + zeroLen;
				}
			} else {
				for (var i = 0; i < decimallen; i++) {
					zeroLen = zeroLen + '0';
				}
				value = value + '.' + zeroLen;
			}
		}
		//添加货币标签
		if (coinvalue && value) {
			value = coinvalue + value;
		}
	}
	me.val(value);
}







