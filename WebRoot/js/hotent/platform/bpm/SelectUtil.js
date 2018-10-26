
/**
 * 选择标签
 */
function selectTag(objId,objName){
	var objIds = $('#'+objId);
	var objNames = $('#'+objName);
	TagDialog({isSingle:true,callback:function(ids,names){
		objIds.val(ids);
		objNames.val(names);
	}}); 
} 




/**
 * 选择组织
 */
function selectOrg(objId,objName){
	var objIds = $('#'+objId);
	var objNames = $('#'+objName);
	OrgDialog({isSingle:true,
		callback:function(ids,names){
			objIds.val(ids);
			objNames.val(names);
	}});	
}

/**
 * 选择用户
 */
function  selectUser(objId,objName){
	var objIds = $('#'+objId);
	var objNames = $('#'+objName);
	UserDialog({
		isSingle:true,
		callback:function(ids,names){
			objIds.val(ids);
			objNames.val(names);
		}});
}

/**
 * 标签选择窗口
 * @param conf
 */
function TagDialog(conf){
	var dialogWidth=650;
	var dialogHeight=500;
	
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:1,center:1},conf);

	var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
		+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
	if(!conf.isSingle)conf.isSingle=false;
	var url=__ctx +'/ecp/bpm/bpmTag/dialog.ht?isSingle=' + conf.isSingle;
	url=url.getNewUrl();
	/*var rtn=window.showModalDialog(url,"",winArgs);
	
	if(conf.callback)
	{
		if(rtn!=undefined){
			 conf.callback.call(this,rtn.tagid,rtn.tagname);
		}
	}*/
	var that =this;
	/*KILLDIALOG*/
	DialogUtil.open({
		height:conf.dialogHeight,
		width: conf.dialogWidth,
		title : '标签选择窗口',
		url: url, 
		isResize: true,
		//自定义参数
		sucCall:function(rtn){
			if(conf.callback)
			{
				if(rtn!=undefined){
					 conf.callback.call(this,rtn.tagid,rtn.tagname);
				}
			}
		}
	});
} 
