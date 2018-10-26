/**可拖拽div插件,可与zTree的拖拽树配合使用**/
;(function($){
	var default_options = {
			errorMsg:'目标对象有误',
			curTarget: null,
			curTmpTarget: null,
			targetTree: null,
			buddy:null			
		};
	
	var methods = {
			init: function(options) {
		        var settings = $.extend({}, default_options, options || {});
		        return this.each(function () {
		            $(this).data("dragdivData", new $.DragdivList(this, settings));
		        });
		    },
		    bind: function(item) {
		        this.data("dragdivData").bind(item);
		        return this;
		    }
	};
	
	$.DragdivList = function(div,options){
		function noSel() {
			try {
				window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
			} catch(e){}
		};
		function dragTree2Dom(treeId, treeNodes) {
			return !treeNodes[0].isParent;
		};
		function prevTree(treeId, treeNodes, targetNode) {
			return !targetNode.isParent && targetNode.parentTId == treeNodes[0].parentTId;
		};
		function nextTree(treeId, treeNodes, targetNode) {
			return !targetNode.isParent && targetNode.parentTId == treeNodes[0].parentTId;
		};
		function innerTree(treeId, treeNodes, targetNode) {
			return targetNode!=null && targetNode.isParent && targetNode.tId == treeNodes[0].parentTId;
		};
		function dropTree2Dom(e, treeId, treeNodes, targetNode, moveType) {				
			if (moveType == null && (/item-div/gi.test(e.target.className) || $(e.target).parents(".item-div").length > 0)) {
				var zTree = $.fn.zTree.getZTreeObj(options.buddy);
				zTree.removeNode(treeNodes[0]);
				
				var newDom = $("span[itemId=" + treeNodes[0].id + "]");
				if(typeof currentTabId != 'undefined' && currentTabId){
					newDom = $("div[tabid="+currentTabId+"] span[itemId=" + treeNodes[0].id + "]");
				}
				if (newDom.length > 0) {
					newDom.removeClass("item-span-Disabled");
					newDom.addClass("item-span");
				} else {
					$("#" + itemId).append("<span class='item-span' itemId='" + treeNodes[0].id + "'>" + treeNodes[0].name + "</span>");
				}
				updateType();
			} else if ( $(e.target).parents('#'+options.buddy).length > 0) {
				
				var zTree = $.fn.zTree.getZTreeObj(options.buddy),
					html=$(e.target).html(),
			    	node = zTree.getNodeByParam("name",html,null),parentNode;
				
				if (node != null && node.isParent) {
					parentNode = node;
				} else if (node != null && !node.isParent) {
					parentNode = node.getParentNode();
				}
				if (!!parentNode) {						
					var nodes = zTree.addNodes(parentNode, treeNodes[0]);
					zTree.selectNode(nodes[0]);						
					zTree.removeNode(treeNodes[0]);
					updateType();
				}
			}
		};
		function dom2Tree(e, treeId, treeNode) {
			var target = options.curTarget, tmpTarget = options.curTmpTarget;
			if (!target) return;
			var zTree = $.fn.zTree.getZTreeObj(options.buddy), parentNode;
			if (treeNode != null && treeNode.isParent) {
				parentNode = treeNode;
			} else if (treeNode != null && !treeNode.isParent) {
				parentNode = treeNode.getParentNode();
			}

			if (tmpTarget) tmpTarget.remove();
			if (!!parentNode) {
				var nodes = zTree.addNodes(parentNode, {id:target.attr("itemId"), name: target.text()});
				zTree.selectNode(nodes[0]);
			} else {
				target.removeClass("item-span-Disabled");
				target.addClass("item-span");
				alert(options.errorMsg);
			}
			updateType();
			options.curTarget = null;
			options.curTmpTarget = null;
		};
		function updateType() {
			var zTree = $.fn.zTree.getZTreeObj(options.buddy),
			nodes = zTree.getNodes();
			for (var i=0, l=nodes.length; i<l; i++) {
				var num = nodes[i].children ? nodes[i].children.length : 0;
				nodes[i].name = nodes[i].name.replace(/ \(.*\)/gi, "") + " (" + num + ")";
				zTree.updateNode(nodes[i]);
			}
		};
		function bindMouseDown(e) {
			var target = e.target;
			if (target!=null && target.className=="item-span") {
				var doc = $(document), target = $(target),
				docScrollTop = doc.scrollTop(),
				docScrollLeft = doc.scrollLeft();
				target.addClass("item-span-Disabled");
				target.removeClass("item-span");
				curDom = $("<span class='item_tmp item-span'>" + target.text() + "</span>");
				curDom.appendTo("body");

				curDom.css({
					"top": (e.clientY + docScrollTop + 3) + "px",
					"left": (e.clientX + docScrollLeft + 3) + "px"
				});
				options.curTarget = target;
				options.curTmpTarget = curDom;

				doc.bind("mousemove", bindMouseMove);
				doc.bind("mouseup", bindMouseUp);
			}
			if(e.preventDefault) {
				e.preventDefault();
			}
		};
		function bindMouseMove(e) {
			noSel();
			var doc = $(document), 
			docScrollTop = doc.scrollTop(),
			docScrollLeft = doc.scrollLeft(),
			tmpTarget = options.curTmpTarget;
			if (tmpTarget) {
				tmpTarget.css({
					"top": (e.clientY + docScrollTop + 3) + "px",
					"left": (e.clientX + docScrollLeft + 3) + "px"
				});
			}
			return false;
		};
		//初始化buddy tree的事件绑定
		function initTreeEventBind(){
			options.targetTree = $.fn.zTree.getZTreeObj(options.buddy);
			if(options.targetTree){
				options.targetTree.setting.edit.drag.prev = prevTree;
				options.targetTree.setting.edit.drag.next = nextTree;
				options.targetTree.setting.edit.drag.inner = innerTree;
				options.targetTree.setting.callback.beforeDrag = dragTree2Dom;
				options.targetTree.setting.callback.onDrop = dropTree2Dom;
				options.targetTree.setting.callback.onMouseUp = dom2Tree;
			}
		};
		function bindMouseUp(e) {				
			var doc = $(document);
			doc.unbind("mousemove", bindMouseMove);
			doc.unbind("mouseup", bindMouseUp);

			var target = options.curTarget, tmpTarget = options.curTmpTarget;
			if (tmpTarget) tmpTarget.remove();

			if ($(e.target).parents('#'+options.buddy).length == 0) {
				if (target) {
					target.removeClass("item-span-Disabled");
					target.addClass("item-span");
				}
				options.curTarget = null;
				options.curTmpTarget = null;
			}
		};
		
		var parseItem = function(data,table){
			var html=[];
			if(!data.items){
				if(!table)table="";//所属表名 
				html.push(['<span class="item-span"']);
				//表明+-+字段名
				if(data.id) html.push(' itemId="'+table+"-"+data.id+'"'); 
				if(data.desc) html.push(' itemDesc="'+data.desc+'"');
				html.push('>');
				if(data.name) html.push(data.name);
				html.push('</span>');
			}
			else{				
				html.push('<div class="item-div"');
				if(data.id) html.push(' itemId="'+data.id+'"');
				if(data.desc) html.push(' itemDesc="'+data.desc+'"');
				html.push('><div class="title-div">');
				if(data.name) html.push(data.name);
				html.push('</div>');
				for(var i=0,c;c=data.items[i++];){
					//将主表信息放进去 data.id
					html.push(parseItem(c,data.id));
				}
				html.push('</div>');
			}
			return html.join('');
		};
		
		this.bind = function(buddy){
			if(buddy)
				options.buddy = buddy;
			initTreeEventBind();
		};
		
		if(!options.data)return;
		var html=parseItem(options.data);
		$(div).html(html).bind("mousedown",bindMouseDown);
	}
	
	$.fn.dragdiv = $.fn.dragDiv = function(method){
		if(methods[method]) {
	        return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
	    } else {
	        return methods.init.apply(this, arguments);
	    }
	};
})(jQuery);