/**
 * 设置只读
 * @function
 * @name baidu.editor.execCommands
 * @param    {String}    cmdName     cmdName="setreadonly"
 */
UE.commands['setreadonly'] = {	
	execCommand : function(cmdName) {
		var node = this.curInput;
		if(node && ("SELECT"==node.nodeName.toUpperCase()||"INPUT"==node.nodeName.toUpperCase()||"TEXTAREA"==node.nodeName.toUpperCase())){
			node = __getRealObj(node);
			node.setAttribute("readonly","readonly");
		}
	},
	queryCommandState : function() {
		return 0;
	}
}
/**
 * 删除只读
 * @type 
 */
UE.commands['delreadonly'] = {	
	execCommand : function(cmdName) {
		var node = this.curInput;
		if(node && ("SELECT"==node.nodeName.toUpperCase()||"INPUT"==node.nodeName.toUpperCase()||"TEXTAREA"==node.nodeName.toUpperCase())){
			node = __getRealObj(node);
			node.removeAttribute("readonly");
		}
	},
	queryCommandState : function() {
			return 0;
	}
};
/**
 * 查找实际存放 自定义查询 数据的标签元素
 * @param obj
 * @returns
 */
function __getRealObj(obj){
	var curNode = obj;
	var Id;
	var fieldName=obj.getAttribute("name");
	var parent=obj.parentNode;
	if(fieldName && parent){
		var idFieldName=fieldName+"ID";
		var inputtmp = parent.getElementsByTagName("input");
		var selecttmp= parent.getElementsByTagName("select");
		var textareatmp= parent.getElementsByTagName("textarea");
		if(inputtmp!=null){
			for(var i=0;i<inputtmp.length;i++){
				if(idFieldName == inputtmp[i].getAttribute("name")){
					Id = inputtmp[i];
					break;
				}
			}
		}
		if(selecttmp!=null){
			for(var i=0;i<selecttmp.length;i++){
				if(idFieldName == selecttmp[i].getAttribute("name")){
					Id = selecttmp[i];
					break;
				}
			}
		}
		if(textareatmp!=null){
			for(var i=0;i<textareatmp.length;i++){
				if(idFieldName == textareatmp[i].getAttribute("name")){
					Id = textareatmp[i];
					break;
				}
			}
		}
		if(Id){
			curNode=Id;
		}
	}
	return curNode;
};