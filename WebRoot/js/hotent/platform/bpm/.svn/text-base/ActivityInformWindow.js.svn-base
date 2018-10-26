/**
 *任务信息设置
{actDefId:actDefId,nodeId:activitiId,activityName:activityName}
 * @param conf
 */
ActivityInformWindow=function(conf){
	if(!conf) conf={};
	var url=__ctx + "/activityDetail/activityDetail/activityDetail/activityInform.ht?actDefId=" + conf.actDefId+"&nodeId=" + conf.nodeId;
	var dialogWidth=1200;
	var dialogHeight=500;
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);
	var winArgs="dialogWidth:"+conf.dialogWidth+"px;dialogHeight:"+conf.dialogHeight
		+"px;help:" + conf.help +";status:" + conf.status +";scroll:" + conf.scroll +";center:" +conf.center;
	if(conf.parentActDefId){
		url += "&parentActDefId="+conf.parentActDefId;
	}
	url=url.getNewUrl();
	/*var rtn=window.showModalDialog(url,"",winArgs);
	if (conf.callback) {
		conf.callback.call(this,rtn);
	}*/
	
	var that=this;
	/*KILLDIALOG*/
	DialogUtil.open({
		height:conf.dialogHeight,
		width: conf.dialogWidth,
		title : '任务信息设置',
		url: url, 
		isResize: false,
		isDrag:false,
		//自定义参数
		sucCall:function(rtn){
			if (conf.callback) {
				conf.callback.call(that,rtn);
			}
		}
	});
};
parameterBinding=function(conf)
{
	 alert("主从参数绑定");	 
	 alert("conf.actDefId"+conf.actDefId+"nodeId"+conf.nodeId);
  	 var url= __ctx +"/platform/bpm/bpmDefinition/flexan.ht?actDefId=" + conf.actDefId+"&nodeId=" + conf.nodeId+"&defId=" + conf.defId;
  	 jQuery.openFullWindow(url);
	}