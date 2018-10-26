FileMenu=function(){
	{
		this.rootMenu=null;
		this.menuMenu=null;
		this.treeNode=null;
	};
	this.getMenu=function(treeNode,handler){
		this.treeNode=treeNode;
		var isRoot=0;
		if(treeNode.isRoot) isRoot=1;
		
		var items=[];
		if(treeNode.userId==0){
			items.push({ text: '增加分类',key:'add', click: handler});
		}
		items.push({ text: '添加我的分类',key:'addMy', click: handler});
		items.push({ text: '编辑分类',key:'edit', click: handler});
		items.push({ text: '删除分类',key:'del', click: handler});
		this.menuMenu = $.ligerMenu({top: 100, left: 100, width: 120, items:items});
		
		this.rootMenu=$.ligerMenu({ top: 100, left: 100, width: 120, items:
	        [{ text: '增加分类',key:'add', click: handler  },
	         { text: '添加我的分类',key:'addMy', click: handler  }]});
		if(isRoot==1){
			return this.rootMenu;
		}
		else{
			return this.menuMenu;
		}
	};
};
