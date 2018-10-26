
function PersonScriptAddDialog(conf){
	var dialogWidth=700;
	var dialogHeight=480;
	
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);

	if(!conf.isSingle)conf.isSingle=false;
	var url=__ctx + '/platform/system/personScript/addDialog.ht?defId='+conf.data.defId;
	url=url.getNewUrl();
	
	var that = this;
	DialogUtil.open({
		height:conf.dialogHeight,
		width: conf.dialogWidth,
		title : '人员脚本',
		url: url, 
		isResize: true,
		//自定义参数
		data: conf.data,
		sucCall:function(rtn){
			conf.callback.call(that,rtn.data);
		}
	});
};

//解释data
function PersonScriptParser(data){
	var script = data.script;
	var inst = script.classInsName;
	var method = script.methodName;
	var str = "return "+inst +"."+method+ "( ";
	var paramStr="";
	if(script.argument != null){
		for(var i=0 ; i<script.argument.length; i++){
			var p=script.argument[i];
			switch(p.paraValType){
			case '1':
				paramStr += p.paraVal+" , " ;
				break;
			case '2':
				if(p.paraType.indexOf("String")>0){
					paramStr += "\"" + p.paraVal+ "\" , " ;
				}else{
					paramStr +=  p.paraVal+ " , " ;
				}
				break;
			}
		}
	}
	if(paramStr){
		paramStr = paramStr.substring(0,paramStr.length-2);
	}
	str += paramStr+");";
	return str;
} 

