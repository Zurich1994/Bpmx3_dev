UE.commands['editaddframelist'] = {	
	execCommand : function(cmdName) {
		var me=this;
		if(!me.ui._dialogs['editaddframelistDialog']){
			baidu.editor.ui['editaddframelist'](me);
		}
		me.ui._dialogs['editaddframelistDialog'].open();
	},
	queryCommandState : function() {
		return 0;
	}
}