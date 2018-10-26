/*【租赁合同签订】**/
$(function(){
	changeTYShouYin();
	
});
/*显示租户信息*/
function showZh(){
	var zhid = $("[name='m:me_zlhtqd:zhID']").val();
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
    if(tableName == 'me_zlhtqd_qtzkft' || tableName == 'me_zlhtqd_hykzkft'){
      var rows =   $(".listRow",$("[tableName='"+tableName+"']"));  //左右子表列
      for(var i =0,row;row=rows[i++];){
        var id = $("[name='s:"+tableName+":ppID']",$(row)).val();  //唯一值
          if(id ==data.SBID) {                            //对话框，返回数据的那列
    	   $.ligerDialog.warn(data.NAME+"已经存在了！","提示信息");
            return false
         }
      }
    }
    
    if(tableName == 'me_zlhtqd_hyklx'){
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
/*切换收银方式*/
function changeTYShouYin(obj){
	var type = $("[name='m:me_zlhtqd:tysy']").val();
	if(type==0) {
		$(".tongyishouyin").hide();
		$("input",$(".tongyishouyin")).val("");
	}
	if(type==1) $(".tongyishouyin").show();
}
/*========== 结算标准信息 start =============*/

/*结算标准信息   删除行事件*/
function me_zlhtqd_zjbzxxDelRowBeforeEvent(row){
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
	decomposeAllJs('me_zlhtqd_wyftk','me_zlhtqd_wyffj');
}
function decomposeSingleWyf(){
	decomposeSingleJs('me_zlhtqd_wyftk','me_zlhtqd_wyffj');
}
function decomposeAllYzj(){
	decomposeAllJs('me_zlhtqd_zjbzxx','me_zlhtqd_ydzjfj');
}
function decomposeSingleYzj(){
	decomposeSingleJs('me_zlhtqd_zjbzxx','me_zlhtqd_ydzjfj');
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
		$.ligerDialog.warn("请选择要分解的租金标准信息","提示");
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
	if(fenJieTable == 'me_zlhtqd_wyffj'){
		var zj =FormUtil.commaback($("[name$='je']",selectRow).val());
		var sfgz =  $("[name='m:me_zlhtqd:wyfsfgzID']").val();
	//标准租金
	}else{
		var zj =FormUtil.commaback($("[name$='zj']",selectRow).val()); 
		var sfgz =$("[name='m:me_zlhtqd:zjsfgzID']").val();
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
/*格式化填充的日期*/
$().ready(function (){
	$("[formatDate]").live('change',function (){
		var me = $(this),val = me.val();
		if(!$.isEmpty(val)){
			var arry = val.split(".");
			var sTime = arry[0].replace(/\-/g, "/");
			var datefmt = me.attr("datefmt");
			var nowDate = new Date(sTime).Format(datefmt);
			me.val(nowDate);
		}
	});
});