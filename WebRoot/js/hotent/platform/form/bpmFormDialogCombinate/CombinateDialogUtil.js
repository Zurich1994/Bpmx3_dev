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