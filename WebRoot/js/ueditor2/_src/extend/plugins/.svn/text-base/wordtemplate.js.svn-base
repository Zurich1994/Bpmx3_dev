/**
 * @description word套打模板
 * @name baidu.editor.execCommand
 * @param {String}
 *            cmdName wordtemplate
 * @author guojh
 */
UE.commands['wordtemplate'] = {
	execCommand : function(cmdName) {
		var me=this;
		
		if(!me.ui._dialogs['wordtemplateDialog']){
			baidu.editor.ui['wordtemplate'](me);
		}
		me.ui._dialogs['wordtemplateDialog'].open();
	},
	queryCommandState : function() {
		return 0;
	}
};