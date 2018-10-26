$().ready(function() {
	$("[datefmt]").live('change', function() {
		var me = $(this), val = me.val();
		if (!$.isEmpty(val)) {
			var arry = val.split(".");
			var sTime = arry[0].replace(/\-/g, "/");
			var datefmt = me.attr("datefmt");
			var nowDate = new Date(sTime).Format(datefmt);
			me.val(nowDate);
		}
	});
	
});

/*显示租户信息*/
function showZh(){
	var zhid = $("[name='m:me_lyhtqd:zhID']").val();
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
    if(tableName == 'me_lyhtqd_qtzkft' || tableName == 'me_zlhtqd_hykzkft'){
      var rows =   $(".listRow",$("[tableName='"+tableName+"']"));  //左右子表列
      for(var i =0,row;row=rows[i++];){
        var id = $("[name='s:"+tableName+":ppID']",$(row)).val();  //唯一值
          if(id ==data.SBID) {                            //对话框，返回数据的那列
    	   $.ligerDialog.warn(data.NAME+"已经存在了！","提示信息");
            return false
         }
      }
    }
    
    if(tableName == 'me_lyhtqd_hyklx'){
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

/*================== 显示保底信息分解信息 =========================*/

/*显示保底信息列表*/
function showJsFj(obj){
	var $jsbzxxTrs = $(obj);
	if(typeof obj == "undefined"){
		$jsbzxxTrs = $("input[name='jsfj']:checked").closest("tr");
	}
	var xh = $jsbzxxTrs.find("[name$=':xh']").val();
	$(".listRow:visible",$("[tablename='me_lyhtqd_bdxx']")).hide();	/*先隐藏全部，在显示个体*/
	$("[name='s:me_lyhtqd_bdxx:xh'][value=" + xh + "]").closest("tr").show(); 
	
	$("td.tdNo", $(".listRow:visible",$("[tablename='me_lyhtqd_bdxx']"))).each(function(i) {
		$(this).text(i + 1);
	});
	
	$("[name='s:me_lyhtqd_bdxx:bdxsml']").trigger("blur");
	$("[name='s:me_lyhtqd_bdxx:mbxsml']").trigger("blur");
}

/*================== 物业费条款  =========================*/

/*物业费条款  添加行事件*/
function me_lyhtqd_wyftkAddRowAfterEvent(row){
	var preRow = $(row).prev();
	if(preRow.attr("style") == 'display: none;')
		$("[name$=':xh']",row).val(1);
	else {
		var preNx = $("[name$=':xh']",preRow).val();
		$("[name$=':xh']",row).val(1+Number(preNx));
	}
}

/*物业费条款   删除行事件*/
function me_lyhtqd_wyftkDelRowBeforeEvent(row){
	var xh = $("[name$=':xh']",row).val();
	$("[name='s:me_lyhtqd_wyffj:xh'][value=" + xh + "]").closest("tr").remove();/*清除旧的记录*/
}

/*分解所有物业费条款*/
function decomposeAllWy(){
	var $wyftkTrs = $(".listRow:visible",$("[tablename='me_lyhtqd_wyftk']")); 
	$wyftkTrs.each(function (i) {
		decomposeSingleWy(this);
	});
	
	var $wyftkTrs = $("input[name='wyfj']:checked").closest("tr");
	showWyfj($wyftkTrs);
}

/*分解单个物业费条款*/
function decomposeSingleWy(obj){
	var $wyftkTrs = $(obj);
	var isSingle = false;
	if(typeof obj == "undefined"){
		$wyftkTrs = $("input[name='wyfj']:checked").closest("tr");
		isSingle = true;
	}
	var xh = $wyftkTrs.find("[name$=':xh']").val();	/*obj 是物业费条款对象*/
	if(xh == null || xh == ""){
		$.ligerDialog.warn("请选择要分解的时间段","提示");
		return ;
	}
	
	$("[name='s:me_lyhtqd_wyffj:xh'][value=" + xh + "]").closest("tr").remove(); /*清除旧的记录*/
	
	var sjd =$("[name$='xh']",$wyftkTrs).val(); //序号
	var ksrq =$("[name$='ksrq']",$wyftkTrs).val();
	var jsrq =$("[name$='jsrq']",$wyftkTrs).val();
	var wyf =FormUtil.commaback($("[name$='je']",$wyftkTrs).val()); 

	var jsonData = decompose(sjd,ksrq,jsrq,wyf);
	/*填充数据*/
	var fenJieTableDiv =$('div[tablename="me_lyhtqd_wyffj"]');
	for (var i = 0, c; c = jsonData[i++];) {
		FormUtil.addRow(fenJieTableDiv);
		var rowcount=$("input[name='s:me_lyhtqd_wyffj:ny']").length;
		$($("input[name$=':xh']",fenJieTableDiv).get(rowcount-1)).val(xh);
		$($("input[name$=':ny']",fenJieTableDiv).get(rowcount-1)).val(c.ZQY);
		$($("input[name$=':ksrq']",fenJieTableDiv).get(rowcount-1)).val(c.KSRQ);
		$($("input[name$=':jsrq']",fenJieTableDiv).get(rowcount-1)).val(c.JSRQ);
		$($("input[name$=':wyf']",fenJieTableDiv).get(rowcount-1)).val(c.YZJ);
	}
 
	if(isSingle){
		showWyfj($wyftkTrs);
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
function showWyfj(obj){
	var $wyftkTrs = $(obj);
	if(typeof obj == "undefined"){
		$wyftkTrs = $("input[name='wyfj']:checked").closest("tr");
	}
	var xh = $wyftkTrs.find("[name$=':xh']").val();
	$(".listRow:visible",$("[tablename='me_lyhtqd_wyffj']")).hide();	/*先隐藏全部，在显示个体*/
	$("[name='s:me_lyhtqd_wyffj:xh'][value=" + xh + "]").closest("tr").show(); 
	
	$("td.tdNo", $(".listRow:visible",$("[tablename='me_lyhtqd_wyffj']"))).each(function(i) {
		$(this).text(i + 1);
	});
	
	$("[name='s:me_lyhtqd_wyffj:wyf']").trigger("blur");
}

















