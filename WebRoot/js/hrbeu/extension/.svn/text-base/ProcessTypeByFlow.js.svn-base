var processType=new ProcessType(catKey,"glTypeTree",{onClick:onClick,url:glTypeTreeUrl});
$(function (){
	//加载菜单 
	processType.loadGlobalTree();
	//布局
    $("#defLayout").ligerLayout({ leftWidth:210,height: '100%',allowLeftResize:false});
});
//左击
function onClick(treeNode){
	
	var url = leftClickUrl;
	if($.isEmpty(treeNode.parentId) || treeNode.parentId == 0 )
		url = parentClickUrl;
	else
		url= leftClickUrl+'?typeId='+treeNode.typeId;
		
	$("#defFrame").attr("src",url);
};

//展开收起
function treeExpandAll(type){
	processType.treeExpandAll(type);
};