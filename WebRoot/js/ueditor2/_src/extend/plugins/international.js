/**
 * 提取国际化资源
 * @function
 * @name baidu.editor.execCommands
 * @param    {String}    cmdName     cmdName="international"
 */
UE.commands['international'] = {
	execCommand : function(cmdName) {
	},
	queryCommandState : function() {
		if(this.highlight)
			return -1;
		var range = this.selection.getRange(),
        	text = this.selection.getText();
		
		if(!text)return -1;
		var curNode =  range.startContainer;
		if(range.startContainer.nodeType!=3 )
			return -1;
		var parent = domUtils.findParents(curNode,false,null,true)[0];
		if(!parent)return -1;
		var alreadySet = (parent.tagName=='SPAN'&&parent.getAttribute("i18nkey")); 
		if(alreadySet)return -1;
		else{
			this.$curTextNode = curNode;
			return 0;
		}
	}
}