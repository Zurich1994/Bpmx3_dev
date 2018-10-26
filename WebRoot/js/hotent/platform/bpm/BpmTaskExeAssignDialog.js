/**
 *  功能:流程转办对话框
 * 
 * @param conf:配置为一个JSON
 * 
 * dialogWidth:对话框的宽度。
 * dialogHeight：对话框高度。
 * 
 * taskId:任务ID。
 * taskName 任务名称
 */
function BpmTaskExeAssignDialog(conf)
{
	if(!conf) conf={};	
	var url=__ctx + '/platform/bpm/bpmTaskExe/assign.ht?taskId=' + conf.taskId+"&taskName="+conf.taskName;
	
	var dialogWidth=500;
	var dialogHeight=300;
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:1,center:1},conf);
	var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
		+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
	url=url.getNewUrl();
	/*var rtn=window.showModalDialog(url,"",winArgs);
	if (conf.callback) {
		conf.callback.call(this,rtn);
	}*/
	
	var that =this;
	/*KILLDIALOG*/
	DialogUtil.open({
		height:conf.dialogHeight,
		width: conf.dialogWidth,
		title : '流程转办对话框',
		url: url, 
		isResize: true,
		//自定义参数
		sucCall:function(rtn){
			if (conf.callback) {
				conf.callback.call(that,rtn);
			}
		}
	});
}