/**
 * 导入Excel的自定义对话框
 * @function
 * @name baidu.editor.execCommands
 * @param    {String}    cmdName     cmdName="importexceldialog"
 */
UE.commands['importexceldialog'] = {	
	execCommand : function(cmdName) {
		var me=this;
		
		if(!me.ui._dialogs['importexceldialogDialog']){
			baidu.editor.ui['importexceldialog'](me);
		}
		me.ui._dialogs['importexceldialogDialog'].open();
	},
	queryCommandState : function() {
		return 0;
	}
}