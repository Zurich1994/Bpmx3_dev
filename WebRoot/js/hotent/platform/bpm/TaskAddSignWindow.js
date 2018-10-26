//任务补签窗口  依赖于LigerWindow,UserDialog
function TaskAddSignWindow(conf)
{
	if(!conf) conf={};
	var url=__ctx+'/platform/bpm/task/toAddSign.ht?taskId=' + conf.taskId;
	var winArgs="dialogWidth=500px;dialogHeight=200px;help=0;status=0;scroll=0;center=1";
	url=url.getNewUrl();
	/*var rtn=window.showModalDialog(url,"",winArgs);
	if(conf.callback){
		if(rtn!=undefined){
			 conf.callback.call(this);
		}
	}*/
	var that =this;
	/*KILLDIALOG*/
	DialogUtil.open({
		height:200,
		width: 500,
		title : '任务补签窗口',
		url: url, 
		isResize: true,
		//自定义参数
		sucCall:function(rtn){
			if(conf.callback){
				if(rtn!=undefined){
					 conf.callback.call(that);
				}
			}
		}
	});
};



