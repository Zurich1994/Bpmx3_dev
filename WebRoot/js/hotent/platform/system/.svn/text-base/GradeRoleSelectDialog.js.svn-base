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
	/*var rtn=window.showModalDialog(url,"",winArgs);
	if(conf.callback){
		if(rtn!=undefined){
			 conf.callback.call(this,rtn.roleId,rtn.roleName);
		}
	}*/
	
	/*KILLDIALOG*/
	var that =this;
	DialogUtil.open({
		height:500,
		width: 695,
		title : '分级角色选择器',
		url: url, 
		isResize: true,
		//自定义参数
		sucCall:function(rtn){
			if(conf.callback){
				if(rtn!=undefined){
					 conf.callback.call(that,rtn.roleId,rtn.roleName);
				}
			}
		}
	});
}