/**
 * 导入表单
 * 
 * @function
 * @name baidu.editor.execCommands
 * @param {String}
 *            cmdName cmdName="importform"导入表单
 */

UE.commands['importform'] = {
	execCommand : function(cmd, name) {
	},
	queryCommandState : function() {
		return this.highlight ? -1 : 0;
	}
};
