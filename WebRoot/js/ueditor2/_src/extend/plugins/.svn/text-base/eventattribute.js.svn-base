/**
 * 查看事件属性
 * @function
 * @name baidu.editor.execCommands
 * @param    {String}    cmdName     cmdName="eventattribute"
 */
UE.commands['eventattribute'] = {	
	execCommand : function(cmdName) {
		var me=this;
		alert("查看事件属性");
		var node = this.curInput;
		alert(node);
		if(!me.ui._dialogs['myeventDialog']){
			baidu.editor.ui['transactiongraph'](me);
		}
		me.ui._dialogs['myeventDialog'].open();
	},
	 
	queryCommandState : function() {
		return 0;
	}
}