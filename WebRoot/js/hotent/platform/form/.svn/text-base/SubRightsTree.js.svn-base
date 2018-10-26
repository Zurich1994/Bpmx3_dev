/**
 * 
 * @param {}
 *            divId
 * @param {}
 *            conf
 */
var SubRightsTree = function(divId, conf) {
	{
		this.tree = null;
		this.currentNode = null;
		this.conf = conf;
		this.divId = divId;
		var me = this;
	};

	this.loadTree = function() {
		var setting = {
			view:{
				nameIsHTML:true,
				showTitle:false
			},
			edit : {
				enable : true,
				showRemoveBtn : false,
				showRenameBtn : false
			},
			data : {
				key : {
					name : "showName"
				}
			},
			callback : {
				onClick: this.onClick,
				onDblClick : this.doubleClickHandler
			}
		};
		$.post(conf.url, conf.params, function(result) {
			var json = eval('(' + result + ')');
			
			var iconFolder = __ctx + '/styles/tree/';
			for ( var i = 0; i < json.length; i++) {
				var table = json[i];
				table.icon = iconFolder + 'table.png';
				for(var j=0;j<table.children.length;j++){
					var field = table.children[j];
					field.icon = iconFolder + field.fieldType + '.png';
				}
			}
			
			me.tree = $.fn.zTree.init($("#" + me.divId), setting, json);
			me.tree.expandAll(true);
		});
	};
	
	this.onClick = function(event, treeId, treeNode) {
		me.currentNode = treeNode;
		if (me.conf.params.onClick) {
			me.conf.params.onClick(treeId,treeNode);
		}
	};

	this.doubleClickHandler = function(event, treeId, treeNode) {
		me.currentNode = treeNode;
		if (me.conf.params.onDbClick) {
			me.conf.params.onDbClick(treeId,treeNode);
		}
	};

};
