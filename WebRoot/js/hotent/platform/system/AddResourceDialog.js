function AddResourceDialog(conf)
{
	if(!conf){
		$.ligerDialog.error("添加资源请求，要求传入适当的参数!","错误");
		return;
	}
	var addUrl=conf.addUrl;
	if(!addUrl){
		$.ligerDialog.error("添加资源请求，要求传入要添加的资源的URL!","错误");
		return;
	}
	
	var url=__ctx + "/platform/system/resources/addResource.ht";
	
	var dialogWidth=900;
	var dialogHeight=600;
	
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);

	
	url=url.getNewUrl();
	var params={
		addUrl:addUrl	
	};
	
	if(conf.name){
		params.name=conf.name;
	}
	
	
	/*KILLDIALOG*/
	var that =this;
	DialogUtil.open({
		height:conf.dialogHeight,
		width: conf.dialogWidth,
		title : '添加资源',
		url: url, 
		isResize: true,
		//自定义参数
		params: params,
		sucCall:function(rtn){
			if(rtn!=undefined){
				if(conf.callback){
					conf.callback.call(that,rtn);
				}
			}
		}
	});
}