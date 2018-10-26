function BpmNodeSqlWindow(conf){
	DialogUtil.open({
		height:600,
		width: 800,
		title : '节点sql设置',
		url: __ctx+"/platform/bpm/bpmNodeSql/edit.ht?nodeId="+conf.nodeId+"&actdefId="+conf.actDefId, 
		isResize: true
	});
}

function FlowSetWindow(conf)
{
	if(!conf) conf={};
	var url=__ctx+ "/platform/bpm/bpmDefinition/subFlowDetail.ht?defId="+conf.defId+"&actDefId=" + conf.actDefId +"&nodeId=" +conf.nodeId;
	url=url.getNewUrl();
	var rtn=window.open(url);
}

/**
 * 通知方式
{actDefId:actDefId,nodeId:activitiId,activityName:activityName}
 * @param conf
 */
InformTypeWindow=function(conf){
	if(!conf) conf={};
	var url=__ctx + "/platform/bpm/bpmDefinition/informType.ht?actDefId=" + conf.actDefId+"&nodeId=" + conf.nodeId;
	var dialogWidth=400;
	var dialogHeight=230;
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
		title : '通知方式',
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


/**
 * webService设置。 conf参数属性： actDefId： act流程定义ID nodeId:流程节点ID
 * 
 * @param conf
 */
function FlowWebServiceWindow(conf) {
	if (!conf)
		conf = {};
	var url = __ctx + "/platform/bpm/bpmNodeWebService/edit.ht?actDefId="
			+ conf.actDefId + "&nodeId=" + conf.nodeId+"&defId="+conf.defId;
	url = url.getNewUrl();
	jQuery.openFullWindow(url);
}

function TriggerNewFlowWindow(conf){
	DialogUtil.open({
		height:640,
		width: 720,
		title : '节点sql设置',
		url : __ctx + "/platform/bpm/bpmNewFlowTrigger/edit.ht?flowKey=" + conf.defKey + "&nodeId=" + conf.nodeId+ "&actDefId=" + conf.actDefId,
		isResize: true
	});
}

function BpmNodeSqlWindow(conf){
	DialogUtil.open({
		height:640,
		width: 720,
		title : '节点sql设置',
		url: __ctx+"/platform/bpm/bpmNodeSql/edit.ht?nodeId="+conf.nodeId+"&actdefId="+conf.actDefId, 
		isResize: true
	});
}

function NodeMsgTempWindow(conf){
	DialogUtil.open({
		height:800,
		width: 1000,
		title : '节点消息模板',
		url: __ctx+"/platform/bpm/nodeMsgTemplate/edit.ht?defId="+conf.defId+"&nodeId="+conf.nodeId+"&parentDefId="+conf.parentDefId, 
		isResize: true
	});
}