/**
 * 事件脚本管理
 * @returns {ScriptManager}
 */
ScriptManage=function(){
	
	this.scripts=[];
	this.name='';
	this.body='';
	
	/**
	 * 设置脚本
	 */
	this.setScripts=function(aryScript){
		this.scripts=aryScript;
	};
	
	/**
	 * 设置当前函数的函数名
	 */
	this.setName=function(name){
		this.name=name;
	};
	
	/**
	 * 设置当前函数的函数体
	 */
	this.setBody=function(body){
		this.body=body;
	};
	
	/**
	 * 得到当前的所有函数 并解析保存在json数组中
	 */
	this.getAryScripts=function(){
		var functions=[];
		if(this.getScriptText()!=''){
			var script=this.getScriptText();
			
			var script=script.trim();
			
			//脚本开始位置有变量声明
			if(script.startWith('function')){
				var funs=script.split('function');
				for(var i=1;i<funs.length;i++){
					var fun=funs[i];
					var f=fun.trim();
					var name=f.substring(0,f.indexOf('{'));
					var body=f.substring(f.indexOf('{')+2,f.lastIndexOf('}'));
					var script=this.getFunction(name, body);
					functions.push(script);
					
					//function函数之间有变量声明
					if(f.length>f.lastIndexOf('}')+1){
						var onlyBody=f.substring(f.lastIndexOf('}')+2,f.length);
						onlyBody=onlyBody.trim();
						var b=this.getFunction('', onlyBody);
						functions.push(b);
					}
				}
				return functions;
			}
			//脚本开始位置没有变量声明
				var funs=script.split('function');
				
				for(var i=0;i<funs.length;i++){
					var fun=funs[i];
					var f=fun.trim();
					if(i==0){
						var body=this.getFunction('', f);
						functions.push(body);
					}else{
						var name=f.substring(0,f.indexOf('{'));
						var body=f.substring(f.indexOf('{')+2,f.lastIndexOf('}'));
						var script=this.getFunction(name, body);
						functions.push(script);
						if(f.length>f.lastIndexOf('}')+1){
							var onlyBody=f.substring(f.lastIndexOf('}')+2,f.length);
							var b=this.getFunction('', onlyBody);
							functions.push(b);
						}
					}
				}
				
		}
		return functions;
	}
	
	/**
	 * 得到当前的事件脚本文本
	 */
	this.getScriptText=function(){
		var editor=CKEDITOR.instances.html;
		var doc=editor.getData();
		var s=doc.indexOf("<script>");
		var e=doc.indexOf("</script>");
		if(s>=0&&e>0){
			var script=doc.substring(s+8,e);
			return script;
		}else 	
			return "";
		
	}
	
	/**
	 * 添加函数：如果函数名存在则删除并替换掉该数组中的函数；
	 *       如果所含参数中包含this关键字则替换成obj
	 */
	this.addScript=function(name,body){
		name=name.trim();
		//判断参数中是否含有this关键字  有则替换成obj
		var allPara=name.substring(name.indexOf('(')+1,name.indexOf(')'));
		if(allPara!=''){
		var paras=allPara.split(',');
		for(var i=0;i<paras.length;i++){
			var para=paras[i];
			if(para=='this'){
				name=name.replace(para,'obj');
			}
		}
		}
		var index=this.isScriptExist(name);
		var scriptObj=this.getFunction(name,body);
		if(index==-1){
			this.scripts.push(scriptObj);
		}else{
			this.scripts.splice(index, 1,scriptObj);
		}
	};
	
	/**
	 * 根据name body 字符串 返回相应的json对象
	 */
	this.getFunction=function(name,body){
		return {name:name,body:body};
	};
	
	/**
	 * 判断是否存在指定函数名的函数，
	 * 如果存在返回该对象在数组中的索引；
	 * 如果不存在返回-1；
	 */
	this.isScriptExist=function(name){
		name=name.substring(0,name.indexOf('('));
		for(var i=0;i<this.scripts.length;i++){
			var script=this.scripts[i];
			var funName=script.name
			funName=funName.trim().substring(0,funName.indexOf('(')).toLowerCase();
			name=name.toLowerCase();
			if(name==funName){
				return i;
			}
		}
		return -1;
	};
	
	/**
	 * 获得html
	 */
	this.getHtml=function(){
		if(this.scripts.length==0)
			return "";
		
		var sb=new StringBuffer();
		sb.append('<script>\n');
		
		for(var i=0;i<this.scripts.length;i++){
			var fun=this.scripts[i];
			var name=fun.name;
			var body=fun.body;
			if(name){
			sb.append('function'+' '+name+'{\n'+body+'\n}\n');
			}else{
				sb.append(body+'\n');
			}
		}
		sb.append("</script>");
		return sb.toString();f
	};
	
};