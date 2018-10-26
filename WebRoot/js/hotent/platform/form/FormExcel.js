if (typeof FormExcel == 'undefined') {
	FormExcel = {};
}

/****************处理excel导入和导出********************/
/**
 * 导出excel
 * {'name':'test1','fields':[{'src':1,'target':['ouxb_test2-zbzd1']},{'src':2,'target':['ouxb_test2-zbzd2']}]};
 */
FormExcel.importExcel = function(obj){
	var me = $(obj),
		jsonStr = me.attr("importexcel");
	if(typeof jsonStr == "undefined")
		return ;
	var	jsonImport = eval("("+jsonStr+")"),
		fields = jsonImport.fields,
		target = fields[0].target[0],	//	获取第一个字段的前缀表名
		arry = target.split("-"),
		tableName = arry[0].toLowerCase(),
		$tableName = $("[tablename="+tableName+"]"),
		url = __ctx+"/platform/system/sysExcelTemp/importDialog.ht";
	DialogUtil.open({
		height:300,
		width: 650,
		title : '导入Excel',
		url: url,
		//自定义参数
		sucCall:function(delOld,data){	//[{1:测试数据1,2:测试数据2},{1:测试数据1,2:测试数据2}]
			if(delOld == "true"){
				$(".listRow:visible",$tableName).remove();
			}
			data = eval("("+data+")");
			for(var i=0,d;d=data[i++];){	// 行
				FormUtil.addRow($tableName);
				var rowcount = $(".listRow:visible",$tableName).length;
				for(var j=0,f;f=fields[j++];){	// 列
					var src = f.src,targetArr=f.target,val=d[src];
					for(var k=0;k<targetArr.length;k++){	// 多个相同字段，正常来说只有一个
						var ts =  targetArr[k].split("-");
						var targetFile = "s:"+ts[0]+":"+ts[1];
						$($("[name='"+targetFile+"']").get(rowcount-1)).val(val);	
					}
				}
			}
			$("input",$tableName).trigger("blur");
			$("input",$tableName).trigger("change");
		}
	});
};

/**
 * 导入excel
 */
FormExcel.exportExcel = function (obj){
	var me = $(obj),
		jsonStr = me.attr("exportexcel");
	if(typeof jsonStr == "undefined")
		return ;	
	var	jsonExport = eval("("+jsonStr+")"),
		tempCode = jsonExport.name,
		fields = jsonExport.fields,
		target = fields[0].target[0],	//	获取第一个字段的前缀表名
		arry = target.split("-"),
		tableName = arry[0].toLowerCase();
	
	var exportData = FormExcel.getExportData(tableName,fields);
	var newurl = __ctx+"/platform/system/sysExcelTemp/exportTempData.ht";
	var action = newurl.getNewUrl();
	var form = new com.hotent.form.Form();
	form.creatForm("form", action);
	form.addFormEl("tempCode",tempCode);
	form.addFormEl("exportData",exportData);
	form.submit();
};

/**
 * 获取导出数据
 * 
 */
FormExcel.getExportData = function (tableName,fields){
	// 获取字段数据 [{0:11,1:12,2:13,3:14},{0:21,1:22,2:23,3:24}]
	//{"src":0,"target":["ouxb_test2-zbzd3"]},{"src":1,"target":["ouxb_test2-zbzd1"]},{"src":2,"target":["ouxb_test2-zbzd2"]}
	
	var listRowArr = new Array();
	$(".listRow:visible",$("[tablename="+tableName+"]")).each(function (){
		var rowArr = new Array(),$listRow = $(this), 
	 		rowArr = {};
		for(var j=0,f;f=fields[j++];){	// 列
			var src = f.src, targetArr=f.target;
			for(var k=0;k<targetArr.length;k++){	// 多个相同字段，正常来说只有一个
				var ts =  targetArr[k].split("-"),
				 	targetFile = "s:"+ts[0]+":"+ts[1],
				 	val = $("[name='"+targetFile+"']",$listRow).val();
				if(typeof val == "undefined" ){
					val = $("[exportFlag='"+targetFile+"']").val();
				}
				rowArr[src] = val;
			}
		}
		listRowArr.push(rowArr);
	});
	return JSON2.stringify(listRowArr);
};


