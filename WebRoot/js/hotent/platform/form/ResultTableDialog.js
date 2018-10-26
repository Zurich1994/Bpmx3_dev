/**
 * 功能:选择表话框
 * 
 */
function ResultTableDialog(conf){
	var url=__ctx + '/dbFunc/dbFunc/dbFunc/tableSelector.ht';
	if(conf.isExternal!=undefined){
		url+="?isExternal=" +conf.isExternal;
	}
	var winArgs="dialogWidth:800px;dialogHeight:500px;help:0;status:1;scroll:1;center:1;resizable:1";
	url=url.getNewUrl();
	/*var rtn= window.showModalDialog(url,"",winArgs);

	if(rtn!=undefined){
		if(conf.callBack)
			conf.callBack.call(this,rtn.tableId,rtn.tableName);
	}*/
	/*KILLDIALOG*/
	var that =this;
	DialogUtil.open({
		height:500,
		width: 800,
		title : '选择表对话框',
		url: url, 
		isResize: true,
		//自定义参数
		sucCall:function(rtn){
			if(rtn!=undefined){
				if(conf.callBack)
					conf.callBack.call(that,rtn.tableId,rtn.tableName);
			}
		}
	});
}
/**zl........
 * 功能:选择事件类型对话框
 * 
 */
function FormTableDialogEvent(conf){
	var url=__ctx + '/eventType/eventType/eventType/listSelect.ht';
	if(conf.isExternal!=undefined){
		url+="?isExternal=" +conf.isExternal;
	}
	var winArgs="dialogWidth:800px;dialogHeight:500px;help:0;status:0;scroll:1;center:1;resizable:0;";
	url=url.getNewUrl();
	var rtn= window.showModalDialog(url,"选择事件类型对话框",winArgs);
	if(rtn!=undefined){
		if(conf.callBack)
			conf.callBack.call(this,rtn.eventId,rtn.EventName);
	}
}