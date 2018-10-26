function HtmlUploadDialog(conf) {
	if(!conf) conf={};
	var max = conf.max||0,
		type = conf.type||"",
		size = conf.size||0;
	var url=__ctx + "/platform/system/file/UploadDialog.ht?max="+max+"&type="+type+"&size="+size;
	
	var dialog = null;
	var def = {
		passConf : {dialog:dialog},
		title : " 附件上传",
		width : 800,
		height : 500,
		modal : true,
		resizable : true,
		buttons:[{
			text:'确定',
			onclick:function(item, dialog){
				    dialog.jiframe[0].contentWindow.getData()
				    if(!scope){
				    	$.ligerDialog.error("获取已上传文件信息时出错");
				    	return;
				    }
				    if(scope.uploader.getNotUploadedItems().length>0){
				    	$.ligerDialog.alertExt("提示信息","有文件尚未上传，请上传该文件或删除该文件.");
				    	return;
				    }
				    if(scope.uploader.queue.length==0){
				    	$.ligerDialog.alertExt("提示信息","至少需要上传一个文件.");
				    	return;
				    }
					if(conf.callback){
						var ary = [];
						angular.forEach(scope.uploader.queue,function(item){
							ary.push(item.json);
						});
						conf.callback(ary);
						dialog.dialog("close");
					}else{
						dialog.dialog("close");
					}
			}
		},{
			text:'取消',
			onclick: function (item,dialog) { dialog.close(); }
		}]
	};
	dialog = $.topCall.dialog({
		src:url,
		base:def
	});
}