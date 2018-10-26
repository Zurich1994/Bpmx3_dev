function SysOrgTacticDialog(conf){
	var orgTactic = conf.orgTactic;
	if(orgTactic == 0){
		var json =null;
		OrgDialog({isSingle:true,arguments:json,callback:function(orgId,orgName){
			conf.callback.call(this,orgId,orgName);
		}});
	}else{
		//策略出来的组织
		DialogUtil.open({
	        height:600,
	        width: 1000,
	        title : '组织选择器',
	        url: __ctx+"/platform/system/sysOrgTactic/dialog.ht", 
	        isResize: true,
	        //回调函数
	        sucCall:function(rtn){
	        	conf.callback.call(this,rtn.orgId,rtn.orgName);
	        }
	    });
	}
}