
/**
 * 复制角色选择窗口。
 * dialogWidth：窗口宽度，默认值350
 * dialogWidth：窗口宽度，默认值150
 * callback：回调函数
 * 回调参数如下：
 * key:参数key
 * name:参数名称
 * 使用方法如下：
 * 
 * CopyRoleDialog({callback:function(content){
 *		//回调函数处理
 *	}});
 * @param conf
 */
function CopyFormDefDialog(conf)
{
	if(!conf) conf={};
	var formDefId=conf.formDefId;
	var url=__ctx + "/platform/form/bpmFormDef/copy.ht?formDefId=" +formDefId;
	
	$.extend(conf, {help:0,status:0,scroll:0,center:1});
    
	var winArgs="dialogWidth=600px;dialogHeight=350px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
	url=url.getNewUrl();
	var rtn=window.showModalDialog(url,"",winArgs);
	if(conf){
		location.reload();
	}
}


