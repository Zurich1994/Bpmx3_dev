//显示执行人信息
if (typeof ShowExeInfo == 'undefined') {
	ShowExeInfo = {};
}
/**
 * 弹出窗口
 */
ShowExeInfo.showDialog = function(conf){
	var dialogWidth=650;
	var dialogHeight=500;
	
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);
	var winArgs="height="+conf.dialogHeight+",width="+conf.dialogWidth+",status=no,toolbar=no,menubar=no,location=no,scrollbars=yes,resizable=yes";
	var url= conf.url;
	url=url.getNewUrl();
	var win=window.open(url,null,winArgs);
	win.focus();
};

ShowExeInfo.showRole = function(id){
	ShowExeInfo.showDialog({url: __ctx+'/platform/system/sysRole/getByRoleId.ht?roleId='+id});
};

ShowExeInfo.showPos = function(id){
	ShowExeInfo.showDialog({url: __ctx+'/platform/system/position/getByPosId.ht?canReturn=2&posId='+id});
};

ShowExeInfo.showOrg = function(id){
	ShowExeInfo.showDialog({url: __ctx+'/platform/system/sysOrg/getByLink.ht?orgId='+id});
};