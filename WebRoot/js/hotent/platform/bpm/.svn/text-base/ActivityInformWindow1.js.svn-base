/**
 *任务信息设置
{actDefId:actDefId,nodeId:activitiId,activityName:activityName}
 * @param conf
 */
ActivityInformWindow=function(conf){
	alert("start");
	if(!conf) conf={};
	//var url=__ctx + "/platform/form/bpmDataTemplate/edit2Data.ht?__formId__=" + conf.__formId__+"&__pk__=" + conf.__pk__;
	var url=__ctx + "platform/bpm/bpmDefinition/flexDefSave23.ht?isAjaxRequest=true";
	var dialogWidth=1200;
	var dialogHeight=500;
	alert("js");
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
		title : '任务信息设置1',
		url: url, 
		isResize: true,
		//自定义参数
		sucCall:function(rtn){
			if (conf.callback) {
				conf.callback.call(that,rtn);
			}
		}
	});
};