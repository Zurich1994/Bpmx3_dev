/**
 * 添加流水号
 * @function
 * @name baidu.editor.execCommands
 * @param    {String}    cmdName     cmdName="serialnum"
 */
UE.commands['serialnum'] = {	
	execCommand : function(cmdName) {
		var me = this;
		var html='<span name="editable-input" class="onlydelete" serialnum="true">流水号</span>';
	    me.execCommand('insertHtml',html);
	},
	queryCommandState : function() {
		 return this.highlight ? -1 : 0;
	}
}