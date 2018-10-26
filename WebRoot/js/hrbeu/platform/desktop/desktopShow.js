/**
 * 添加到此列
 */
function addThisColumn(desktop) {	
	if ($(".active", $("#colshtml")).length == 0) {
		$.ligerDialog.warn("请选中一列以添加栏目!",'提示信息');
		return;
	}
	var obj = document.getElementById("columnName");
	selectOnChange(obj, desktop);
}

var tdObj = null;
/**
 * 改变选择区域的样式
 * 
 * @param id
 *            列的ID
 */
function changeClass(td) {
	if (tdObj != td) {
		tdObj = td;
		$("." + active, $("#colshtml")).each(function() {
			$(this).attr("class", nonactivated);
		});
	}
	$(td).toggleClass(active);
}

/**
 * 选择下拉框栏目后,单击"添加到此列"时改变布局栏目
 */
function selectOnChange(obj, desktop) {
	var columnName = obj[obj.selectedIndex].innerHTML;
	var columnId = $(obj).val();	
	$.post(
		__ctx + "/platform/system/desktopColumn/getTemp.ht?id="
				+ columnId,
		function(jsonStr) {
			// 创建布局窗格
			var htt = '<li columnId="'
					+ columnId
					+ '" class="widget color-blue"><div class="widget-head"><span><h3>'
					+ columnName
					+ '</h3></span></div><div class="widget-content">'
					+ jsonStr
					+ '</div></li>';
			$(".active", $("#colshtml")).find("ul.column").each(
					function() {						
						$(this).append(htt);
						iNettuts.init();
					});
		});
};
/**
 * 获取栏目模板
 */
function getColumnTemp(cols,width,showTable,layoutId){
	    var ary=width.split(","); //字符分割
	    var str;
	    if(showTable==0){
	    	str='<table width="100%"><tr layoutId="'+layoutId+'">';
	    }
		else
			{str='<table width="100%" class="wtMonth"><tr layoutId="'+layoutId+'">';}
	    for(var i=1;i<=cols;i++){
	    	str+='<td width="'+ary[i-1]+'%" valign="top">';
			str+='<div id="columns"><ul id="column'+i+'" class="column"><br></ul></div></td>';
	    }
	    str+='</tr></table>';
		return str;
};
/**
 * 保存个人布局设置、保存系统默认布局的栏目设置
 * @param u 请求保存的地址
 */
function saveMycolumn(u){
	var data="[";
	var colIndex=0;
	$("#colshtml td").each(function(e){
		colIndex++;
		var snIndex=0; 
		$(this).find("li").each(function(){			
			var columnId=$(this).attr("columnId");
			if(columnId!=undefined){
				snIndex++;
				if(data!="[")
					data+=",";
				data+='{"col":'+colIndex+',"sn":'+snIndex+',"columnId":'+columnId+'}';
			}
		});
	});
	data+="]";
	var layoutId=$("tr","#colshtml").attr("layoutId");	
	$.post(__ctx + u,
			{"layoutId":layoutId,"data":data},
			function(reData){				
				var parStr=$.parseJSON(reData);				
				if(parStr.result==1)
					$.ligerDialog.success(parStr.message,function(){location.reload();},'提示信息');
				else
					$.ligerDialog.warn(parStr.message,function(){location.reload();},'提示信息');
			});
};

