/**
 * 子表操作
 */
UE.plugins['subtable'] = function() {
	var me = this,
		keys = domUtils.keys,
		addClass = domUtils.addClass,
		removeClass = domUtils.removeClass;

	var tempDiv = null,
		subTableDiv = null;

	/**
	 * 剪切子表
	 */
	me.commands['cutsubtable'] = {
		queryCommandState: function(){
			if(this.highlight ){return -1;}
			var start = me.selection.getStart(),
				div = domUtils.findParentByTagName(start,'div',true);
			if(!div)return -1;
			if(div.className=='subTableToolBar')
				div = domUtils.findParentByTagName(div,'div',false);
			if(div.getAttribute("type")!='subtable')return -1;
			tempDiv = div;
			return 0;
		},
		execCommand: function(cmdName, opt){
			if(!tempDiv)return;
			subTableDiv = tempDiv;
			domUtils.remove(subTableDiv);
		}
	};

	/**
	 * 粘贴子表
	 */
	me.commands['pastesubtable'] = {
		queryCommandState: function() {
			if(this.highlight||!subTableDiv){return -1;}
			return 0;
		},
		execCommand: function(cmdName, opt) {
			var start = me.selection.getStart();
			if(!start||!subTableDiv)return;
			if(start.tagName=='TD'){
				start.appendChild(subTableDiv);
			}
			else{
				utils.insertAfter(subTableDiv, start);
			}
			subTableDiv = null;
		}
	};
};