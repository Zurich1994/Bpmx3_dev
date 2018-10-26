function SysOrgSearch(conf){
	url=__ctx + "/platform/system/sysOrg/allList.ht";
	var winArgs="dialogWidth:"+600+"px;dialogHeight:"+500
	+"px;help:0 ;status:0;scroll: 1;center:1";
	url=url.getNewUrl();
	
	var rtn=window.showModalDialog(url,"",winArgs);
	
	if(rtn && conf.callback){
		var orgId=rtn.orgId;
		conf.callback.call(this,orgId);
	}
}