$(function() {
	var expandDepth = 1; 
	OrgTree = {
			orgTree:null,
			loadTree:function(){
				var setting = {
					data: {
							key : {
								name: "orgName",
								title: "orgName"
							}
						},
					// 拖动
					edit : {
						enable : true,
						showRemoveBtn : false,
						showRenameBtn : false,
						drag : {
							prev : true,
							inner : true,
							next : true,
							isMove : true,
							isCopy : true
						}
					},
					view : {
						selectedMulti : false
					},
					callback : {
						onClick : this.zTreeOnLeftClick,
						onRightClick : this.zTreeOnRightClick,
						onDrop : this.onDrop
					}
				};
				var url=__ctx + "/platform/system/grade/getOrgJsonByUserId.ht";
				
				$.post(url,function(result) {
					if($.isEmpty(result)) return;
					var zNodes=eval("(" +result +")");
					OrgTree.orgTree = $.fn.zTree.init($("#orgTree"), setting,zNodes);
					if(expandDepth!=0)
					{
						var nodes = OrgTree.orgTree.getNodesByFilter(function(node){
							return (node.level < expandDepth);
						});
						if(nodes.length>0){
							for(var i=0;i<nodes.length;i++){
								OrgTree.orgTree.expandNode(nodes[i],true,false);
							}
						}
					}else{
						OrgTree.orgTree.expandAll(true);
					}
				});
				


			},
			
			zTreeOnLeftClick:function(event, treeId, treeNode){
				var orgId = treeNode.orgId;
				
				var url=__ctx + "/platform/system/sysOrg/getGrade.ht?orgId=" +orgId ;
				
				$("#viewFrame").attr("src", url);
			},
			/**
			 * 右击事件
			 */
			zTreeOnRightClick:function(e, treeId, treeNode) {
				var treeObj = $.fn.zTree.getZTreeObj(treeId);
				treeObj.selectNode(treeNode);
				contextMenu.show({
					top : e.pageY,
					left : e.pageX
				});
				
			},
			getSelectNode:function(){
				var nodes = OrgTree.orgTree.getSelectedNodes();
				var treeNode = nodes[0];
				return treeNode;
			},
			//编辑组织参数属性
			orgParam:function(){
				var treeNode = OrgTree.getSelectNode();
				var orgId = treeNode.orgId;
				var url = __ctx + "/platform/system/sysOrgParam/editByOrgId.ht?orgId="+ orgId;
				$("#viewFrame").attr("src", url);
			},
			editOrg:function(){
				var treeNode=OrgTree.getSelectNode();
				var orgId = treeNode.orgId;
				var demId = treeNode.demId;
				var url=__ctx + "/platform/system/sysOrg/editGrade.ht?demId={0}&orgId={1}&action=edit";
				url=String.format(url,demId,orgId);
				$("#viewFrame").attr("src", url);
			},
			addOrg:function () {
				var treeNode=OrgTree.getSelectNode();
				var orgId = treeNode.orgId;
				var demId = treeNode.demId;
				var url=__ctx + "/platform/system/sysOrg/editGrade.ht?demId={0}&orgId={1}&action=add";
				url=String.format(url,demId,orgId);
				$("#viewFrame").attr("src", url);
			},
			delNode:function() {
				var treeNode=OrgTree.getSelectNode();
				var callback = function(rtn) {
					if (!rtn) return;
					var params = "orgId=" + treeNode.orgId;
					var url=__ctx + "/platform/system/sysOrg/orgdel.ht";
					$.post(url, params, function() {
						orgTree.removeNode(treeNode);
					});
				};
				$.ligerDialog.confirm("确认要删除此组织吗，其下组织也将被删除？",'提示信息', callback);

			},
			onDrop:function (event, treeId, treeNodes, targetNode, moveType) {
				if (targetNode == null || targetNode == undefined)	return;
				var targetId = targetNode.orgId;
				var dragId = treeNodes[0].orgId;
				var url = __ctx + "/platform/system/sysOrg/move.ht";
				var params = {
					targetId : targetId,
					dragId : dragId,
					moveType : moveType
				};

				$.post(url, params, function(result) {
					OrgTree.loadTree();
				});
			}
			
	};
});
