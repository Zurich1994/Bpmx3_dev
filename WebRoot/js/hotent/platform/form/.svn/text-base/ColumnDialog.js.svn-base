function ColumnDialog(conf){
	if(!conf) conf={};
	var url=__ctx + '/platform/form/bpmFormTable/columnDialog.ht?isAdd=' ;
	url+=conf.isAdd?"1":"0";
	url+="&isMain="+conf.isMain;
	var winArgs="dialogWidth:700px;dialogHeight:540px;help:0;status:0;scroll:1;center:1";
	url=url.getNewUrl();
	//var rtn=window.showModalDialog(url,conf,winArgs);
	/*KILLDIALOG*/
	DialogUtil.open({
		height:540,
		width: 700,
		title : '添加列',
		url: url, 
		isResize: true,
		//自定义参数
		conf: conf
	});
}