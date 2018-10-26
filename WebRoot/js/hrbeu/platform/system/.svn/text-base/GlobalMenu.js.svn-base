GlobalMenu=function(){
	{
		this.rootMenu=null;
		this.menuMenu=null;
		this.treeNode=null;
		this.menuMenuFlat=null;
	};
	this.getMenu=function(treeNode,handler){
		this.treeNode=treeNode;
		var isRoot=0;
		if(treeNode.isRoot) isRoot=1;
		
		if(this.menuMenu==null){
			this.menuMenu=$.ligerMenu({ top: 100, left: 100, width: 120, items:
		        [{ text: '增加分类', click:handler  },
			        { text: '删除', click:handler },
			        {text:'导出',click:handler},
			        {text:'导入',click:handler},
			        {text:'排序',click:handler},
			        { text: '刷新', click: handler }]
			        });
			
			
		};
		if(this.menuMenuFlat==null){
				this.menuMenuFlat=$.ligerMenu({ top: 100, left: 100, width: 120, items:
			        [   { text: '删除', click:handler },
				        {text:'导出',click:handler},
				        {text:'导入',click:handler}]
				        });
		}
		if(this.rootMenu==null){
			this.rootMenu=$.ligerMenu({ top: 100, left: 100, width: 120, items:
		        [{ text: '增加分类', click: handler  },
		         {text:'导出',click:handler},
		         {text:'导入',click:handler},
		         {text:'排序',click:handler},
		         { text: '刷新', click: handler  }]
		         });
		};

		if(isRoot==1){
			return this.rootMenu;
		}
		else{
		
			if(treeNode.type==0){
			
				return this.menuMenuFlat;
			}
				
			return this.menuMenu;
		}
		
		
	};
};

FlowTypeMenu=function(){
	{
		this.rootMenu=null;
		this.menuMenu=null;
		this.treeNode=null;
	};
	this.getMenu=function(treeNode,handler){
		this.treeNode=treeNode;
		var isRoot=0;
		if(treeNode.isRoot) isRoot=1;
		
		if(this.menuMenu==null){
			this.menuMenu = $.ligerMenu({top: 100, left: 100, width: 120, items:
                [
                { text: '增加分类', click: handler },
                { text: '编辑分类', click: handler  },
                { text: '删除分类', click: handler }
                ]
                });
			
		
		};
	
		if(this.rootMenu==null){
			this.rootMenu=$.ligerMenu({ top: 100, left: 100, width: 120, items:
		        [{ text: '增加分类', click: handler  }]
		         });
		};

		if(isRoot==1){
			return this.rootMenu;
		}
		else{
			return this.menuMenu;
		}
		
		
	};
};

ReportTypeMenu=function(){
	{
		this.rootMenu=null;
		this.menuMenu=null;
		this.treeNode=null;
	};
	this.getMenu=function(treeNode,handler){
		this.treeNode=treeNode;
		var isRoot=0;
		if(treeNode.isRoot) isRoot=1;
		
		if(this.menuMenu==null){
			this.menuMenu = $.ligerMenu({top: 100, left: 100, width: 120, items:
                [
                { text: '增加分类', click: handler },
                { text: '编辑分类', click: handler  },
                { text: '删除分类', click: handler }
                ]
                });
			
		
		};
	
		if(this.rootMenu==null){
			this.rootMenu=$.ligerMenu({ top: 100, left: 100, width: 120, items:
		        [{ text: '增加分类', click: handler  }]
		         });
		};

		if(isRoot==1){
			return this.rootMenu;
		}
		else{
			return this.menuMenu;
		}
		
		
	};
};

/**
 * 数据字典菜单。
 * @returns {DiclMenu}
 */
DicMenu=function(){
	{
		this.rootMenu=null;
		this.menuMenu=null;
		this.treeNode=null;
	};
	this.getMenu=function(treeNode,handler){
		this.treeNode=treeNode;
		var isRoot=0;
		if(treeNode.isRoot) isRoot=1;
		
		if(this.menuMenu==null){
			var items=[{ text: '增加字典分类', click:handler  },
				         { text: '编辑分类', click: handler },
				         { text: '排序', click: handler },
					     { text: '删除', click:handler }];
			if(treeNode.type==0){
				items.splice(0, 1);
			}
			this.menuMenu=$.ligerMenu({ top: 100, left: 100, width: 120, items:items});
		};
	
		if(this.rootMenu==null){
			this.rootMenu=$.ligerMenu({ top: 100, left: 100, width: 120, items:
		        [{ text: '增加字典分类', click: handler  },
		         { text: '排序', click: handler }]
		         });
		};

		if(isRoot==1){
			return this.rootMenu;
		}
		else{
			return this.menuMenu;
		}
		
		
	};
};
