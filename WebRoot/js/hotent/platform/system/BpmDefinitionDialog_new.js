/**
 * 流程选择窗口。
 * dialogWidth：窗口宽度，默认值700
 * dialogWidth：窗口宽度，默认值400
 * callback：回调函数
 * 回调参数如下：
 * key:参数key
 * name:参数名称
 * defArry： 为流程初始化数据 ：  id#名称#key
 * defMark： 用id 或者key 做唯一标识
 * 使用方法如下：
 * 
 * BpmDefinitionDialog({isSingle:true,callback:dlgCallBack}){
 *		//回调函数处理
 *	}});
 * @param conf
 */
function BpmDefinitionDialog(conf)
{
	conf=conf||{ };
	var options={isSingle:false,showAll:1,validStatus:1,defArry:new Array(),defMark:"id"};
	conf= $.extend(options,conf);
	var url=__ctx +"/platform/bpm/bpmDefinition/dialog.ht?isSingle={0}&showAll={1}&validStatus={2}&sign=1";
	url=String.format(url,conf.isSingle,conf.showAll,conf.validStatus);
	
	var winArgs="dialogWidth=860px;dialogHeight=600px;help=0;status=0;scroll=1;center=1";
	url=url.getNewUrl();
	/*var rtn=window.showModalDialog(url,conf,winArgs);
	if(rtn!=undefined){
		if(conf.callback){
			var defIds=rtn.defIds;
			var subjects=rtn.subjects;
			if(conf.returnDefKey){
				var defKeys = rtn.defKeys;
				conf.callback.call(this,defIds,subjects,defKeys);
			}else{
				conf.callback.call(this,defIds,subjects);
			}
		}
	}*/
	var that =this;
	/*KILLDIALOG*/
	DialogUtil.open({
		height:600,
		width: 860,
		title : '事务图选择',
		url: url, 
		isResize: true,
		//自定义参数
		conf: conf,
		sucCall:function(rtn){
			alert(url);
			if(rtn!=undefined){
				if(conf.callback){
					var defIds=rtn.defIds;
					var subjects=rtn.subjects;
					if(conf.returnDefKey){
						var defKeys = rtn.defKeys;
						conf.callback.call(that,defIds,subjects,defKeys);
					}else{
						conf.callback.call(that,defIds,subjects);
					}
				}
			}
		}
	});
}