function SysOrgSearch(conf){
	url=__ctx + "/platform/system/sysOrg/allList.ht";
	var winArgs="dialogWidth:"+650+"px;dialogHeight:"+500
	+"px;help:0 ;status:0;scroll: 1;center:1";
	url=url.getNewUrl();
	
	/*var rtn=window.showModalDialog(url,"",winArgs);
	
	if(rtn && conf.callback){
		var orgId=rtn.orgId;
		conf.callback.call(this,orgId);
	}*/
	
	/*KILLDIALOG*/
	var that =this;
	DialogUtil.open({
		height:570,
		width: 650,
		title : '组织树搜索',
		url: url, 
		isResize: true,
		//自定义参数
		sucCall:function(rtn){
			if(rtn && conf.callback){
				var orgId=rtn.orgId;
				conf.callback.call(that,orgId);
			}
		}
	});
}