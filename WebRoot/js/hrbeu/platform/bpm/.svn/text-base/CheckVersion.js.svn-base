//检查版本升级
function checkVersion(conf){
	var u = __ctx +'/platform/bpm/bpmDefinition/checkVersion.ht';
	$.post(u,{defId:conf.defId},function(res){
		var result = eval("("+res+")");		
		if(result.success){
			var url = "";
			if(	conf.type)
				url = __ctx +'/platform/bpm/task/startFlowForm.ht?draftId='+conf.draftId+'&defId='+conf.defId;
	 		else
	 			url = __ctx +'/platform/bpm/task/startFlowForm.ht?defId='+conf.defId+'&copyKey='+conf.businessKey;

			if(result.isMain == 1){
				jQuery.openFullWindow(url);	
			}else{
				var msg =  conf.type? '已发布了新版本!<br/>此版本已过期，无法启动!':'已发布了新版本!<br/>此版本已过期，无法复制!';
				$.ligerDialog.warn(msg,'提示')
			}
		}else{
			$.ligerDialog.error(result.msg,'提示');
		}
	});
}