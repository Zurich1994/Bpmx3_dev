/**
 * 导出表单
 * 
 * @function
 * @name baidu.editor.execCommands
 * @param {String}
 *            cmdName cmdName="exportform"导入表单
 */

UE.commands['exportform'] = {
	execCommand : function(cmd, name) {
		exportform();
	},
	queryCommandState : function() {
		return this.highlight ? -1 : 0;
	}
};
