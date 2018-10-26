/**
 * 统计函数
 * @function
 * @name baidu.editor.execCommands
 * @param    {String}    cmdName     cmdName="insertfunction"
 */
UE.commands['insertfunction'] = {	
	execCommand : function(cmdName) {
	},
	queryCommandState : function() {
		var range = this.selection.getRange();
        var el = range.getClosedNode();
        if(!el&&range){
            var startContainer = range.startContainer;
            if(startContainer &&  ('TD' == startContainer.nodeName.toUpperCase() || 'SPAN' == startContainer.nodeName.toUpperCase())){
                el = startContainer.childNodes[range.startOffset];
            }
        }
		return el ? 0 : -1;
	}
}