if (typeof BpmNodeButton == 'undefined') {
	BpmNodeButton = {};
}

/**
 * 添加选项。
 * @param objOperator
 * @param obj
 * @param type
 */
BpmNodeButton.addOption =function(objOperator,obj,type){
	if(type==obj.operatortype){
		objOperator.append("<option value='"+ obj.operatortype + "' script='"+ obj.script +"' selected>"+obj.text +"</option>");  
	}
	else{
		objOperator.append("<option value='"+ obj.operatortype + "' script='"+ obj.script +"'>"+obj.text +"</option>"); 
	}
};

/**
 * 获取操作类型下拉框。
 */
BpmNodeButton.getOperatorType=function (bpmButtonList,isStartForm,isSign){
	var objOperator=$("#operatortype");
	objOperator.empty();
	objOperator.append("<option value='0'>请选择操作类型</option>"); 
	var type=objOperator.attr("operatortype");
	
	if(isStartForm==1){
		for(var i=0;i<bpmButtonList.length;i++){
			var obj=bpmButtonList[i];
			if(obj.type==0){
				BpmNodeButton.addOption(objOperator,obj,type); 
			}
		}
	}
	else{
		for(var i=0;i<bpmButtonList.length;i++){
			var obj=bpmButtonList[i];
			if(obj.type!=0 && obj.type!=1){
				//isSign==0表示普通节点
				if(isSign==0 && obj.type==2){
					continue;
				}
				if(isSign==1 && obj.type==3){
					continue;
				}
				BpmNodeButton.addOption(objOperator,obj,type);
			}
		}
	}
	
};

