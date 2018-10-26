/**
 * 绑定事务图 
 * @function
 * @name baidu.editor.execCommands
 * @param    {String}    cmdName     cmdName="transactionGraph"
 */
UE.commands['transactiongraph'] = {	
	execCommand : function(cmdName) {
		var me=this;
		alert("开始transactiongraph");
		if(!me.ui._dialogs['transactiongraphDialog']){
			baidu.editor.ui['transactiongraph'](me);
		}
		me.ui._dialogs['transactiongraphDialog'].open();
	},
	 
	queryCommandState : function() {
		return 0;
	}
}