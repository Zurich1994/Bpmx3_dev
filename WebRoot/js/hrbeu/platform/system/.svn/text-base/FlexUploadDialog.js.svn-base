/**
 * 附件上传选择窗口。
 * dialogWidth：窗口宽度，默认值700
 * dialogWidth：窗口宽度，默认值500
 * callback：回调函数
* 回调参数如下：
 * key:参数key
 * name:参数名称
 * 使用方法如下：
 * 
 * FlexUploadDialog({isSingle:false,callback:picCallBack{
 *		//回调函数处理
 *	}});
 * @param conf
 */
function FlexUploadDialog(conf) {
	if(!conf) conf={};
	var isSingle=conf.isSingle?1:0;
	var url=__ctx + "/platform/system/sysFile/dialog.ht?isSingle="+isSingle;
	var winArgs="dialogWidth:800px;dialogHeight:500px;help:0;status:0;scroll:1;center:1";
	if (!conf.isSingle)conf.isSingle = false;
	url=url.getNewUrl();
	var rtn=window.showModalDialog(url,"",winArgs);
	if(rtn!=undefined){
		if(conf.callback){
			var fileIds=rtn.fileIds;
			var fileNames=rtn.fileNames;
			var filePaths=rtn.filePaths;
			var extPath=rtn.extPath;
			
			conf.callback.call(this,fileIds,fileNames,filePaths,extPath);
		}
	}
}

/**
 * 直接文件上传文本框。
 * 使用方法。
 * UploadDialog({callback:function(json){
 *			for(var i=0;i<json.length;i++){
 *				var obj=json[i];
 *				alert(obj.fileId +"," + obj.fileName);
 *			}
 *		}})	;
 * @param conf
 */
function DirectUploadDialog(conf) {
	if(!conf) conf={};
	var url=__ctx + "/platform/system/sysFile/uploadDialog.ht";
	url=url.getNewUrl();
	var param = window;
 	if(conf!=undefined&&conf!=null){
 		if(conf.callback){
 	 		param.returnValue = conf.callback;
 	 	}
 	 	if(conf.uploadType!=undefined&&conf.uploadType!=null&&conf.uploadType=="pictureShow"){
 		    url += "&uploadType=pictureShow";                 
 	 	}
 	 	if(conf.fileFormates!=undefined&&conf.fileFormates!=null&&conf.fileFormates!=""){
 		    url += "&fileFormates="+conf.fileFormates;                 
 	 	}
 	}	
 	
 	openwindow (url, '附件上传', '500', '300');
}

function openwindow(url,name,iWidth,iHeight)
{
 var url;                                 //转向网页的地址;
 var name;                           //网页名称，可为空;
 var iWidth;                          //弹出窗口的宽度;
 var iHeight;                        //弹出窗口的高度;
 var iTop = (window.screen.availHeight-30-iHeight)/2;       //获得窗口的垂直位置;
 var iLeft = (window.screen.availWidth-10-iWidth)/2;           //获得窗口的水平位置;
 window.open(url,"fileUpload",'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
}



/**
 * 文件上传文本框。
 * 使用方法。
 * UploadDialog({callback:function(json){
 *			for(var i=0;i<json.length;i++){
 *				var obj=json[i];
 *				alert(obj.fileId +"," + obj.fileName);
 *			}
 *		}})	;
 * @param conf
 */
function UploadDialog(conf) {
	if(!conf) conf={};
	var url=__ctx + "/platform/system/sysFile/uploadDialog.ht";
	var winArgs="dialogWidth:450px;dialogHeight:300px;help:0;status:0;scroll:1;center:1";
	url=url.getNewUrl();
	var rtn=window.showModalDialog(url,"",winArgs);
	if(rtn!=undefined){
		if(conf.callback){
			conf.callback.call(this,rtn);
		}
	}
}