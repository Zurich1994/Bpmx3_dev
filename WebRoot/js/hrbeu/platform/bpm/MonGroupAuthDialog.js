/**
 * 监控组授权对话框。
 * @param conf
 */
function MonGroupAuthDialog(conf){
	if(!conf) conf={};	
	var url=__ctx + '/platform/bpm/monGroup/auth.ht?groupId=' + conf.groupId;
	var winArgs="height=600,width=800,status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes";
	url=url.getNewUrl();
	window.open(url,null,winArgs);
}