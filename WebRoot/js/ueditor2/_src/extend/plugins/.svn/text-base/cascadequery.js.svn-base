
/**
 * 级联设置
 * @function
 * @name baidu.editor.execCommands
 * @param    {String}    cmdName     cmdName="customdialog"
 */
UE.commands['cascadequery'] = {	
	execCommand : function(cmdName) {
		var me=this;
		
		if(!me.ui._dialogs['cascadequeryDialog']){
			baidu.editor.ui['cascadequery'](me);
		}
		me.ui._dialogs['cascadequeryDialog'].open();
	},
	queryCommandState : function() {
		return 0;
	}
}
/**
 * 删除级联
 * @type 
 */
UE.commands['uncascadequery'] = {	
	execCommand : function(cmdName) {
		var node = this.curInput;
		if(node && "SELECT"==node.nodeName.toUpperCase()){
			node = getRealObj(node);
			node.removeAttribute("selectquery");
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
function getRealObj(obj){
	var curNode = obj;
	var selectId;
	var fieldName=obj.getAttribute("name");
	var parent=obj.parentNode;
	if(fieldName && parent){
		var idFieldName=fieldName+"ID";
		var tmp = parent.getElementsByTagName("select");
		if(tmp!=null){
			for(var i=0;i<tmp.length;i++){
				if(idFieldName == tmp[i].getAttribute("name")){
					selectId = tmp[i];
					break;
				}
			}
		}
		if(selectId){
			curNode=selectId;
		}
	}
	return curNode;
};