if (typeof SysAuditLink == 'undefined') {
	SysAuditLink = {};
}
SysAuditLink.initLink=function(){
$("body").delegate("a[hrefLink]","click",function(){
		var hrefLink=$(this).attr("hrefLink");
		if(!hrefLink){
			return;
		}
		var url=hrefLink+"&canReturn=1";
		var winArgs="dialogWidth:800px;dialogHeight:600px;help:0;status:1;scroll:1;center:1";
		window.showModalDialog(url,"",winArgs);	
})

}