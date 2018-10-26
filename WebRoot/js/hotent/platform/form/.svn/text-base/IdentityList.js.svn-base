function IdentityList(callBack){
	var url=__ctx + '/platform/system/identity/selector.ht';
	var winArgs="dialogWidth:800px;dialogHeight:700px;help:0;status:1;scroll:1;center:1;resizable:1";
	url=url.getNewUrl();
	/*var rtn= window.showModalDialog(url,"",winArgs);
	if(rtn!=undefined){
		callBack.call(this,rtn.alias,rtn.name);
	}*/
	/*KILLDIALOG*/
	var that =this;
	DialogUtil.open({
		height:500,
		width: 800,
		title : '流水号',
		url: url, 
		isResize: true,
		//自定义参数
		sucCall:function(rtn){
			if(rtn!=undefined){
				callBack.call(that,rtn.alias,rtn.name);
			}
		}
	});
}