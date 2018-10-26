var subTree,subHeight;

$(function() {
		// 布局
		$("#layout").ligerLayout({
					leftWidth : 250,
					height : '100%',
					allowLeftResize : false
				});
		//subHeight = $('#subTree').height();
		// 流程变量树
		subTree = new SubRightsTree("subTree", {
					url : __ctx + '/platform/form/bpmFormTable/getSubTree.ht',
					params : {
						tableId:tableId,
						nodeId:nodeId,
						actDefId:actDefId,
						parentActDefId:$("#parentActDefId").val(),
						onClick: onClick,
						onDbClick: onDbClick	
					}
				});
		subTree.loadTree();
		var height=$("#layout").height()-35;
		$("#subLayout").height(height);
});	

/**
 * 单击节点
 */
function onClick(treeId,treeNode) {
	if(typeof(treeId)=="undefined")
		return ;
	
	if (treeNode.level == 0 && treeNode.id!='-1') {
		
		$('#subName').text(treeNode.name);
		$('#tableid').val(treeNode.id);
		
		$.ajax({
			type : "POST",
			url : __ctx + "/platform/bpm/BpmSubtableRights/get.ht",
		    data: {actdefid:$('#actdefid').val(), nodeid:$('#nodeid').val(),
		    	tableid:treeNode.id, parentActDefId:$('#parentActDefId').val()},
			success : function(res) {
				var result = eval('('+res+')');
				var id = typeof(result.permissiontype)!="undefined"?result.id:'0';
				$('#rightid').val(id);
				if(typeof(result.permissiontype)!="undefined"){
					$('input:[name=permissiontype][value="'+result.permissiontype+'"]').attr("checked",true);
					$('#scriptAuthority').hide();
					if(result.permissiontype==2){
						$('#scriptAuthority').show();
						InitMirror.editor.setCode(result.permissionseting.replaceAll('<br>','\n').replaceAll('<032>','\"'));
					}
				}
			},
			error : function(res) {
				
			}
		});
	}
}
	
/**
 * 双击节点
 */
function onDbClick(treeId,treeNode) {
	
	if(typeof(treeId)=="undefined")
		return ;

	if (treeNode.level != 0) 
	{
		if($('#subName').text()==treeNode.getParentNode().name)
		{
			InitMirror.editor.insertCode(treeNode.name);
		}
		else if(treeNode.getParentNode().id=="-1"){
			InitMirror.editor.insertCode(treeNode.name);
		}
		else
		{
			$.ligerDialog.warn('选取的子表字段不属于当前操作子表','提示信息');
		}
	}
};

/**
 * 显示是否有权限时的图标
 */
function changeShow(subTree,node,state){
		if(!node.isParent)
			node=node.getParentNode();
		var showname=node.showName;
		var iconFolder = __ctx + '/styles/tree/';
		var lastIndex =-1;
		var imgurl='';
		if(state=="1"){
			 lastIndex=showname.lastIndexOf("<img src='"+iconFolder+"yes.png'>");
			 imgurl="<img src='"+iconFolder+"no.png'>"	
		}else{
			lastIndex=showname.lastIndexOf("<img src='"+iconFolder+"no.png'>");
			imgurl="<img src='"+iconFolder+"yes.png'>"
		}
		if(lastIndex!=-1){
			var newname=showname.substring(0,lastIndex)+imgurl;
			node.showName=newname;
			subTree.updateNode(node);
		}
}