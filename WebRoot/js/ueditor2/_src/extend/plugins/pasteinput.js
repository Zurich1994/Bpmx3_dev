/**
 * 粘贴字段
 * @function
 * @name baidu.editor.execCommands
 * @param    {String}    cmdName     cmdName="pasteinput"
 */
UE.commands['pasteinput'] = {	
	execCommand : function(cmdName) {
		var start = this.selection.getStart();
		if(!start||!editor.curCutInput)return;
		if(start.tagName=='TD'){
			start.appendChild(editor.curCutInput);
		}
		else{
			start = domUtils.findEditableInput(start);
			utils.insertAfter(editor.curCutInput, start);
		}
		editor.curCutInput = null;
	},
	queryCommandState : function() {
		if(this.highlight||!editor.curCutInput){return -1;}
		return 0;
	}
}