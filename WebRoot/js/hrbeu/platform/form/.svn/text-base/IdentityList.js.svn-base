function IdentityList(callBack){
	var url=__ctx + '/platform/system/identity/selector.ht';
	var winArgs="dialogWidth:800px;dialogHeight:700px;help:0;status:1;scroll:1;center:1;resizable:1";
	url=url.getNewUrl();
	var rtn= window.showModalDialog(url,"",winArgs);
	if(rtn!=undefined){
		callBack.call(this,rtn.alias,rtn.name);
	}
}