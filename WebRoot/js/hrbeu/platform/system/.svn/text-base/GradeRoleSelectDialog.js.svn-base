/**
 * 分级角色选择器。
 * 回调函数
 * 返回角色ID和角色名称。
 */
function GradeRoleSelectDialog(conf)
{
	var winArgs="dialogWidth=695px;dialogHeight=500px;help=0;status=0;scroll=0;center=1" ;
	var url=__ctx + '/platform/system/grade/roleSelector.ht?orgId=' + conf.orgId;
	url=url.getNewUrl();
	var rtn=window.showModalDialog(url,"",winArgs);
	if(conf.callback){
		if(rtn!=undefined){
			 conf.callback.call(this,rtn.roleId,rtn.roleName);
		}
	}
}