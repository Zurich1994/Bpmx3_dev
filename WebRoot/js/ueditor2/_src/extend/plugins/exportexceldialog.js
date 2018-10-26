/**
 * 导出Excel的自定义对话框
 * @function
 * @name baidu.editor.execCommands
 * @param    {String}    cmdName     cmdName="exportexceldialog"
 */
UE.commands['exportexceldialog'] = {	
	execCommand : function(cmdName) {
		var me=this;
		
		if(!me.ui._dialogs['exportexceldialogDialog']){
			baidu.editor.ui['exportexceldialog'](me);
		}
		me.ui._dialogs['exportexceldialogDialog'].open();
	},
	queryCommandState : function() {
		return 0;
	}
}