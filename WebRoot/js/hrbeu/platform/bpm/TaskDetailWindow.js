function TaskDetailWindow(conf)
{
	if(!conf) conf={};
	var url=__ctx + "/platform/bpm/task/detail.ht?taskId=" + conf.taskId;

	var dialogWidth=800;
	var dialogHeight=600;
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);
	var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
		+"px;help=0;status=0;scroll=1;center=1;resizable=1;" ;
	url=url.getNewUrl();
	var rtn=window.open(url,"",winArgs);
}