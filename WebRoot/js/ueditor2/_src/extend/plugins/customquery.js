/**
 * 联动设置
 * @function
 * @name baidu.editor.execCommands
 * @param    {String}    cmdName     cmdName="customquery"
 */
UE.commands['customquery'] = {	
	
	execCommand : function(cmdName) {
		
	},
	queryCommandState : function(cmdName) {
		var node = this.selection.getStart();
		if(node && "INPUT"==node.nodeName.toUpperCase()){
			node = __getRealObj(node);
			this.curQuery=node;
			var query = node.getAttribute("query");
			if(query){
				return 1;
			}else{
				return 0;
			}
		}else{
			return -1;
		}
	}
};
UE.commands['uncustomquery'] = {	
	execCommand : function(cmdName) {
		var node = this.selection.getStart();
		if(node && "INPUT"==node.nodeName.toUpperCase()){
			node = __getRealObj(node);
			node.removeAttribute("query");
		}
	},
	queryCommandState : function(cmdName) {
		var node = this.selection.getStart();
		if(node && "INPUT"==node.nodeName.toUpperCase()){
			node = __getRealObj(node);
			var query = ""//node.getAttribute("query");
			if(query){
				return 0;
			}else{
				return -1;
			}
		}else{
			return -1;
		}
	}
};

/**
 * 查找实际存放 自定义查询 数据的标签元素
 * @param obj
 * @returns
 */
function __getRealObj(obj){
	var curNode = obj;
	var inputId;
	var fieldName=obj.getAttribute("name");
	var parent=obj.parentNode;
	if(fieldName && parent){
		var idFieldName=fieldName+"ID";
		var tmp = parent.getElementsByTagName("input");
		if(tmp!=null){
			for(var i=0;i<tmp.length;i++){
				if(idFieldName == tmp[i].getAttribute("name")){
					inputId = tmp[i];
					break;
				}
			}
		}
		if(inputId){
			curNode=inputId;
		}
	}
	return curNode;
};