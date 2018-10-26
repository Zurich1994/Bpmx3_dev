/**可拖拽span插件,可与zTree的拖拽树配合使用**/
;(function($){
	$.fn.dragspan=$.fn.dragSpan=function(options){		
		options=$.extend({
			errorMsg:'目标对象有误',
			//拖拽目标样式
			targetClass:'drag-span',
			curTarget: null,
			curTmpTarget: null,
			treeDropHandler:null,
			targetTree: null,
			buddy:null,
			noSel: function() {
				try {
					window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
				} catch(e){}
			},
			dragTree2Dom: function(treeId, treeNodes) {
				return 1;
			},
			prevTree: function(treeId, treeNodes, targetNode) {
				return !targetNode.isParent && targetNode.parentTId == treeNodes[0].parentTId;
			},
			nextTree: function(treeId, treeNodes, targetNode) {
				return !targetNode.isParent && targetNode.parentTId == treeNodes[0].parentTId;
			},
			innerTree: function(treeId, treeNodes, targetNode) {
				return targetNode!=null && targetNode.isParent && targetNode.tId == treeNodes[0].parentTId;
			},
			dropTree2Dom: function(e, treeId, treeNodes, targetNode, moveType) {
				if (moveType == null) {	
					var target = null;						
					if(e.target.className.indexOf(options.targetClass) > -1)
						target = e.target;					
					else if($(e.target).parents("."+options.targetClass).length > 0)
						target = $(e.target).parents("."+options.targetClass);
					else if($(e.target).find("."+options.targetClass).length > 0)
						target = $(e.target).find("."+options.targetClass);
					if(options.treeDropHandler&&target){
						options.treeDropHandler(target,treeNodes[0]);
					}
				}
			},
			//初始化buddy tree的事件绑定
			initTreeEventBind:function(){
				options.targetTree = $.fn.zTree.getZTreeObj(options.buddy);
				if(options.targetTree){
					options.targetTree.setting.edit.drag.prev = options.prevTree;
					options.targetTree.setting.edit.drag.next = options.nextTree;
					options.targetTree.setting.edit.drag.inner = options.innerTree;
					options.targetTree.setting.callback.beforeDrag = options.dragTree2Dom;
					options.targetTree.setting.callback.onDrop = options.dropTree2Dom;
				}
			}
		},options||{});				
		return this.each(function(){
			if(options.buddy){
				$("#"+options.buddy).bind("mouseover",function(){				
					if(!options.targetTree)
						options.initTreeEventBind();
				});
			}
		});
	};
})(jQuery);