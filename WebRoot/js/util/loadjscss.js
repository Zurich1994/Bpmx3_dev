/**
 * 功能：
 * 动态加载js和css文件。
 * 具体的使用方法见：
 * demo/loadjsdemo.htm
 */
var JsLoader={
	//预计加载个数
	LoadCount:0,
	//已经加载个数
	LoaderNumber:0,
	Load:function(js,id,callback){
		var scriptId = document.getElementById(id);
	    if (scriptId) {
	        if (callback)
	                callback();
	        JsLoader.LoaderNumber+=1;
	        //加载个数大于或等于预计加载个数，则触发加载完毕事件
	        if (JsLoader.LoaderNumber >= JsLoader.LoadCount){
	            if (JsLoader.OnLoad)
	                JsLoader.OnLoad();
	        }
	    }
	    else {
	        var script = document.createElement("script");
	        script.id = id;
	        script.type = "text/javascript";
	        
	        script.onload = script.onreadystatechange = function(){
	            if (script.readyState && script.readyState != 'loaded' && script.readyState != 'complete'){
	                return;
	            }
	            script.onreadystatechange = script.onload = null;
	            //当前文件加载完毕，触发回调事件
	            if (callback)
	                callback();
	            JsLoader.LoaderNumber+=1;
	            //加载个数大于或等于预计加载个数，则触发加载完毕事件
	            if (JsLoader.LoaderNumber >= JsLoader.LoadCount)
	                if (JsLoader.OnLoad)
	                    JsLoader.OnLoad();
	        };
	        script.src = js;
	        var head = document.getElementsByTagName('head').item(0);
	        head.appendChild (script);
	    }
	}
};



/*Css 动态加载*/
var CssLoader={
	//预计加载个数
	LoadCount:0,
	LoaderNumber:0,
	Load:function(css,id,callback){
		 var cssId = document.getElementById(id);
		    if (cssId) {
		        if (callback)
		            callback();
		        CssLoader.LoaderNumber+=1;
		        //加载个数大于或等于预计加载个数，则触发加载完毕事件
		        if (CssLoader.LoaderNumber >= CssLoader.LoadCount)
		            if (CssLoader.OnLoad)
		                CssLoader.OnLoad();
		    }
		    else{
		        var link = document.createElement("link");
		        link.id = id;
		        link.rel="stylesheet";
		        link.type = "text/css";
		        
		        link.onload = link.onreadystatechange = function(){
		            if (link.readyState && link.readyState != 'loaded' && link.readyState != 'complete'){
		                return;
		            }
		            link.onreadystatechange = link.onload = null;
		            //当前文件加载完毕，触发回调事件
		            if (callback)
		                callback();
		            CssLoader.LoaderNumber+=1;
		            //加载个数大于或等于预计加载个数，则触发加载完毕事件
		            if (CssLoader.LoaderNumber >= CssLoader.LoadCount)
		                if (CssLoader.OnLoad)
		                    CssLoader.OnLoad();
		        };
		        link.src = css;
		        var head = document.getElementsByTagName('head').item(0);
		        head.appendChild (link);
		    }
	}
};

