ScriptLoader = function() {
	this.timeout = 10;
	this.scripts = [];
	this.disableCaching = true;//false
	this.loadMask = null;
};

ScriptLoader.prototype = {
	processSuccess : function(response) {
		if (typeof response.argument.callback == 'function') {
			response.argument.callback.call(response.argument.scope,
			response.responseText,response.argument.url,response.argument.index);
		}
	},
	load : function(url, callback) {	
		var cfg, callerScope, index;
		if (typeof url == 'object') { // must be config object
			cfg = url;
			url = cfg.url;	
			index=cfg.index;
			callback = callback || cfg.callback;
			callerScope = cfg.scope;
			if (typeof cfg.timeout != 'undefined') {
				this.timeout = cfg.timeout;
			}
			if (typeof cfg.disableCaching != 'undefined') {
				this.disableCaching = cfg.disableCaching;
			}
		}
		if (this.scripts[url]) {
			if (typeof callback == 'function') {
				callback.call(callerScope || window);
			}
			return null;
		}
		$.ajax({
		  url:  url,
		  dataType: 'script',
		  cache:!this.disableCaching,
		  success: this.processSucess,
		  argument : {
						'url' : url,
						'scope' : callerScope || window,
						'callback' : callback,
						'options' : cfg,
						'index':index
			}
		});
	}
};
ScriptLoaderMgr=function() {
	//缓存加载内容
	this.mdCache=[];
	this.loader = new ScriptLoader();
	
	this.load = function(o) {
		this.loader.scope=o.scope;
		if(!o.scripts instanceof Array){
			o.scripts = [o.scripts];
		}
		//记数器
		o.lfiles=0;
		this.mdCache.length=0;
		var mdCache=this.mdCache;
		for(var i=0;i<o.scripts.length;i++){
			o.url = o.scripts[i];
			o.index=i;
			this.loader.load(o, function(rs,url,idx) {
				o.scope = this;
				mdCache[idx]={
					content:rs
				};
				o.lfiles++;
				if(o.lfiles>=o.scripts.length){
					//是否执行加载的js
					for(var j=0;j<mdCache.length;j++){
						try{
							window.execScript ? window.execScript(mdCache[j].content) : window.eval(mdCache[j].content);
						}catch(ex){
						}
					}
					if(o.callback!=null){
						o.callback.call(this);
					}
				}
			});
		}
	};
};

ScriptMgr = new ScriptLoaderMgr();
