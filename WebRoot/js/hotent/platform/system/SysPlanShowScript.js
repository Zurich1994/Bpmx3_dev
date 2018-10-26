//初始化跳转事件和有指定的视图日期
function initGoToTheDate(){	
	//初始化跳转事件
	$("#goToTheDate").click(function() {
		goToTheDate();
	});
	
	//初始化指定的视图日期
	var currentViweDate = $("input[name='theDate']").val();
	if(typeof(currentViweDate)=='undefined'||currentViweDate==null||currentViweDate==''){
	     return;
	}
	goToTheDate();
	$("input[name='theDate']").val("");
}

//初始化跳转事件
function goToTheDate(){	
	
	if(!calendarObj){
		return;
	}
	
	var theDate = $("input[name='theDate']").val();
	if(typeof(theDate)=='undefined'||theDate==null||theDate==''){
    	$.ligerDialog.warn("请选择跳转日期！","消息提示");
    	return;
	}
	var arryDay = theDate.split("-"); //字符分割
	//这种方式才可以正常设置成时间：new Date(year, month, day, hour, miniute, second)  月份要减少1
	var date = new Date(arryDay[0],arryDay[1]-1,arryDay[2],0,0,0);
	calendarObj.goToTheDate(date);
	
}