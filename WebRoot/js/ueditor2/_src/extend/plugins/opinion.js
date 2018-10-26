///import core
///import plugins\opinion.js
///commands 意见框
///commandsName  opinion
///commandsTitle  意见框
/**
 * @description 意见框
 * @name baidu.editor.execCommand
 * @param {String}
 *            cmdName 意见框
 * @author heyifan
 */
UE.plugins['opinion'] = function() {
	var me = this;
	me.commands['addopinion'] = {
		execCommand : function(cmdName) {
			me.curOpinion=null;
			this.ui._dialogs['opinionDialog'].open({height:'200px'});
			return true;
		},
		queryCommandState : function() {
			return this.highlight ? -1 : 0;
		}
	};
	me.commands['editopinion'] = {
		execCommand : function(cmdName) {
			me.curOpinion=this.selection.getRange().getClosedNode();
			this.ui._dialogs['opinionDialog'].open();
			return true;
		},
		queryCommandState : function() {
			 var el = this.selection.getRange().getClosedNode();
			 if(!el){
				 return -1;
			 }
			 else if(el.tagName.toLowerCase()=='input'||el.tagName.toLowerCase()=='textarea'||el.tagName.toLowerCase()=='select'){
				 return this.highlight ? -1 : 0;
			 }
			 return -1;
		}
	};
};