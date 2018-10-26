/**
 * 信息内容模板选择窗口。
 * dialogWidth：窗口宽度，默认值500
 * dialogWidth：窗口宽度，默认值300
 * callback：回调函数
 * 回调参数如下：
 * key:参数key
 * name:参数名称
 * 使用方法如下：
 * 
 * TemplateDialog({callback:function(content){
 *		//回调函数处理
 *	}});
 * @param conf
 */
function TemplateDialog(conf)
{
	var url=__ctx + "/platform/system/sysTemplate/dialog.ht".getNewUrl();
	var winArgs="dialogWidth:600px;dialogHeight:300px;help:0;status0;scroll:1;center:1";
	url=url.getNewUrl();
	/*var rtn=window.showModalDialog(url,conf,winArgs);	
	if(rtn!=undefined){
		if(conf.callback){
			conf.callback.call(this,rtn);
		}
	}*/
	
	var that =this;
	/*KILLDIALOG*/
	DialogUtil.open({
		height:300,
		width: 600,
		title : '信息内容模板',
		url: url, 
		isResize: true,
		//自定义参数
		conf: conf,
		sucCall:function(rtn){
			if(rtn!=undefined){
				if(conf.callback){
					conf.callback.call(that,rtn);
				}
			}
		}
	});

}