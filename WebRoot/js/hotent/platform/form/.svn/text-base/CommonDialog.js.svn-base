/**
 * 通用对话框。
 * @param alias		对话框别名。
 * @param callBack	回调函数。
 * 调用示例：
 * CommonDialog("role",function(data){
 * 
 * });
 * data:为json数组或者为json对象。
 * @param paramValueString	向对话框传递的"参数=值"字符串
 * 传入多个则需要使用"&"符号进行连接（user=admin&orgId=1）
 */
function CommonDialog(alias,callBack,paramValueString){
	
	//1毫秒内同时打开两次不允许 by xg
	if($(obj).data("hasInited")) return;
	$(obj).data("hasInited",true);
	window.setTimeout(function(){
		$(obj).data("hasInited",false);
	},1);
	window.__resultData__=0;
	if(alias==null || alias==undefined){
		$.ligerDialog.warn("别名为空！",'提示信息');
		return;
	}
	var url=__ctx + "/platform/form/bpmFormDialog/dialogObj.ht?alias=" +alias;
	url=url.getNewUrl();
	$.post(url,{"alias":alias},function(data){
		if(data.success==0){
			$.ligerDialog.warn("输入别名不正确！",'提示信息');
			return;
		}
		var obj=data.bpmFormDialog;
		var width=obj.width;
		var name=obj.name;
		var height=obj.height;
		var displayList=obj.displayfield.trim();
		var resultfield=obj.resultfield.trim();
		if( displayList==""){
			$.ligerDialog.warn("没有设置显示字段！",'提示信息');
			return;
		}
		if( resultfield==""){
			$.ligerDialog.warn("没有设置结果字段！",'提示信息');
			return;
		}
		
		var urlShow=__ctx + "/platform/form/bpmFormDialog/show.ht?dialog_alias_=" +alias;
		if(!paramValueString==false){
			urlShow = urlShow + "&" + encodeURI(paramValueString) ;
		}
		urlShow=urlShow.getNewUrl();
		var frameDialogWindow = "";
		window.top.__resultData__=0;
		window.top.$.ligerDialog.open({ url:urlShow, height: 600,width: 800, title :name,name:"frameDialog_",
			buttons: [ { text: '确定', onclick: function (item, dialog) {
				var result = window.top.__resultData__;
				if(result==-1 || result==0){
					alert("还没有选择数据项！");
					return;
				}
				if(callBack){
					callBack(result);
				}
				dialog.close();
			} }, 
			{ text: '重置', onclick: function (item, dialog) { 
				frameDialogWindow.resetMethod();
			} }, 
			{ text: '取消', onclick: function (item, dialog) { dialog.close(); } } ] });
		frameDialogWindow = window.top.$('[name="frameDialog_"]')[0].contentWindow;
	});
};
/**
 * 调用通用表单查询
 * @param alias 查询别名
 * @param callback 回调函数
 */
function CommonQuery(alias){
	window.__queryData__ = [];
	if(alias==null || alias==undefined){
		$.ligerDialog.warn("别名为空！",'提示信息');
		return;
	}
	var url=__ctx + "/platform/bpm/bpmFormQuery/queryObj.ht?alias=" +alias;
	url=url.getNewUrl();
	$.post(url,{"alias":alias},function(data){
		if(data.success==0){
			$.ligerDialog.warn("输入别名不正确！",'提示信息');
			return;
		}
		var obj=data.bpmFormQuery;
		var name=obj.name;
		var conditionfield=obj.conditionfield.trim();
		var resultfield=obj.resultfield.trim();
		
		if( conditionfield==""){
			$.ligerDialog.warn("没有设置条件字段！",'提示信息');
			return;
		}
		if( resultfield==""){
			$.ligerDialog.warn("没有设置结果字段！",'提示信息');
			return;
		}
		
		var urlShow=__ctx + "/platform/bpm/bpmFormQuery/get.ht?query_alias_=" +alias;
		urlShow=urlShow.getNewUrl();
		$.ligerDialog.open({ url:urlShow, height: 380,width: 600, isResize: true, title :name,name:"frameQuery_",
			buttons: [{ text: '关闭', onclick: function (item, dialog) { dialog.close(); } } ] });
	});
};

/** 
 * 执行查询
 * @param alias 查询别名
 * @param condition 查询条件
 * @param callback 查询完成后的回调
 * @param isSync 是否同步执行。
 */
function DoQuery(condition,callback,isSync){
	var url = __ctx + "/platform/bpm/bpmFormQuery/doQuery.ht";
	
	var isAsync=true;
	if(isSync!=undefined && isSync==true){
		isAsync=false;
	}
	
	$.ajax({
		   type: "POST",
		   url: url,
		   async:isAsync,
		   data: condition,
		   success: function(data){
			   if(callback)
					callback(data);
		   }
	});
	
};

/**
 * param.tree="ORGNAME=广州";
 * param.list="USERID=1";
 */
var CombinateDialogUtil = {
		open : function(bpmFormDialogCombinate,callBack,param){
			var url=__ctx+"/platform/form/bpmFormDialogCombinate/show.ht?id="+bpmFormDialogCombinate.id;
			window.top.$.ligerDialog.open({ url:url,param:param, height: bpmFormDialogCombinate.height,width: bpmFormDialogCombinate.width, title :bpmFormDialogCombinate.name,name:"CombinateDialog_",
				buttons: [ { text: '确定', onclick: function (item, dialog) {
					var result=window.top.__combinateDialogData__;
					if(!callBack){
						callBack=function(data){
							alert("返回数据:"+JSON.stringify(data));
						}
					}
					callBack(result);
					dialog.close();
				} }, 
				{ text: '重置', onclick: function (item, dialog) {
					window.top.$('[name="CombinateDialog_"]')[0].contentWindow.$('#listFrame')[0].contentWindow.resetMethod();
				} }, 
				{ text: '取消', onclick: function (item, dialog) { dialog.close(); } } ] 
			});
		},
		/**
		 * alias : 组合对话框的别名
		 * callBack:回调函数 格式：function(data){ alert('结果为:'+data) };
		 * param:(json格式) eg:
		 * var param={};
		 * param.tree="a=1&b=2";//树对话框条件
		 * param.list="c=3&d=4";//列表对话框条件
		 */
		openByAlias : function(alias,callBack,param){
			$.ajax({
				url: __ctx+'/platform/form/bpmFormDialogCombinate/getObject.ht?alias='+alias,
				type: 'POST',
				dataType: 'json',
				error: function(){alert('请求超时');},
				success: function(result){
					CombinateDialogUtil.open(result,callBack,param);
				}
			});
		}
}
