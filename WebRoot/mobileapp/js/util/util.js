
(function () {
	window.HT_LS = {
		activeWin:'activeWin', // 主页激活其他窗口
		selectorToSelected:'selectorToSelected', // 联系人激活已选中
		formToSeletor:'formToSeletor', // 表单激活联系人
		selectorSelected:'selectorSelected', // 已选中联系人
		contactCommon:'contactCommon' // 常用联系人
	}
})();

//帮助类
(function () {
    window.HT = {
    	/**
    	 * 字符串解析为整数
    	 */
    	parseInt:function(string,radix){
    		if(this.isEmpty(string))
    			return 0;
    		if(this.isEmpty(radix))
    			return parseInt(string,10);
    		return parseInt(string,radix);
    	},
    	getI18n:function(lang){
    		if (lang === 'zh_cn')
    			$i18n$ = $i18n$_zh_cn;
    		else if(lang === 'zh_tw') 
    			$i18n$ = $i18n$_zh_tw;
    		else if(lang === 'en_us') 
    			$i18n$ = $i18n$_en_us;
    		else{
    			var language=  navigator.language;
    			switch (language) {
    				case 'zh-CN':
    				case 'zh-cn':	
    					$i18n$ = $i18n$_zh_cn;
    					break;
    				case 'zh-TW':
    				case 'zh-tw':	
    					$i18n$ = $i18n$_zh_tw;
    					break;
    				case 'en-US':
    				case 'en-us':	
    					$i18n$ = $i18n$_en_us;
    					break;	
    				default:
    					$i18n$ = $i18n$_zh_cn;
    					break;
    			}
    		}
    		return $i18n$;
    	},
    	// 文件的双引号
    	FILE_QUOTES:"￥@@￥",
    	//默认页大小
		pageSize:10,
		fileTarget:{},
		__flag:true,
		footer:"",
		/**
		 * 转换PageBean
		 */
		trustPageBean:function(param){
			var pageBean ={
         			start:1,
 			 		pageSize:this.pageSize
         		};
			if(!param)
				param ={};
			else{
				pageBean = param.pageBean?param.pageBean:pageBean;
				delete param.pageBean;
			}
		
			var pageSize = pageBean.pageSize,
				start    = pageBean.start,
				p        = this.parseInt(start/pageSize),
				page     = ((start%pageSize)>0)?(p+1):p;
				
			param.page = page;
			param.pageSize = pageSize;
			param.start = start;
			return param;
		},
		getDefaultPage :function(flag){
			if(!flag)
				flag = 2;
			
			return  {
	         			_flag:flag,
	         			start:1,
	 			 		pageSize:this.pageSize
	         		} 
		},
		// 刷新效果
		initBounce :function(funcTop, funcBottom) {
			uexWindow.setBounce("1");
			if (!funcTop && !funcBottom) {
				uexWindow.showBounceView("0", "rgba(0,0,0,0)", "0");
				uexWindow.showBounceView("1", "rgba(0,0,0,0)", "0");
				return;
			}
			var top = 0, btm = 1,params;
			//回调
			uexWindow.onBounceStateChange = function(type, state) {
				if (type == top && state == 2) { // 顶部弹动
					funcTop();
				}
				if (type == btm && state == 2) { // 底部弹动
					funcBottom();
				}
			}
			//顶部弹动
			if (funcTop) {
				params = "{'pullToReloadText':"+$i18n$.COMMON.PULL_RELOAD_TEXT
						+",'releaseToReloadText':"+$i18n$.COMMON.RELEASE_REFRESH_TEXT
						+",'loadingText':"+$i18n$.COMMON.LOADING_TEXT+"}";
				uexWindow.setBounceParams('0',params);
				uexWindow.showBounceView(top, "rgba(0,0,0,0)", 1);// 设置弹动位置及效果([1:显示内容;0:不显示])
				uexWindow.notifyBounceEvent(top, 1);// 注册接收弹动事件([0:不接收onBounceStateChange方法回调;1:接收])
			}
			// 底部弹动
			if (funcBottom) {
				params = "{'pullToReloadText':"+$i18n$.COMMON.LOAD_RELOAD_TEXT
						+",'releaseToReloadText':"+$i18n$.COMMON.LOAD_REFRESH_TEXT
						+",'loadingText':"+$i18n$.COMMON.LOAD_MORE_TEXT+"}";
				uexWindow.setBounceParams('1',params);
				uexWindow.showBounceView(btm, "rgba(0,0,0,0)", 1);// 设置弹动位置及效果([1:显示内容;0:不显示])
				uexWindow.notifyBounceEvent(btm, 1); // 注册接收弹动事件([0:不接收onBounceStateChange方法回调;1:接收])
			}
		},
		/**
		 * 加载分页的数据
		 */
		loadedPage:function(scope,flag,method){
			 if(!method)
	            	method = "search()";
	       var	pageBean = scope.pageBean;
		   	if(!pageBean)
				pageBean = this.getDefaultPage(flag);
        	if(flag == 0){//刷新功能 
        		pageBean._flag = flag;
        		pageBean.start = 1;
        		pageBean.pageSize=this.pageSize;
            } else if(flag == 1) {//加载成功
           		pageBean._flag = flag;
           		pageBean.start = pageBean.pageSize+pageBean.start;
           		pageBean.pageSize=this.pageSize;
           }
		    
	 		scope.pageBean = pageBean;
	 		try{
				eval("(scope."+method+")");
			}catch(e){}
	   		scope.$digest();
		},
		/**
		 * 默认的日期格式
		 */
		defaultDateFormate:'yyyy-MM-dd hh:mm:ss',
		iso8601TestRe:/\d\dT\d\d/,
		iso8601SplitRe:/[- :T\.Z\+]/,
		dashesRe:/-/g,
		/**
		 * 设置字符串类型的本地缓存
		 * 
		 * @param {String}
		 *            key 传入key
		 * @param {String}
		 *            value 字符串的值
		 * 
		 */
    	setStorage: function(key,value){
    		var sto = window.localStorage;
    		if (sto)
    			sto.setItem(key, value);
    	},
		/**
		 * 判断对象是否为空
		 */
		isObjNull:function(obj){
			for (var i in obj){
				return false;
			}
			return true;
		},
    	/**
		 * 设置JSON类型的本地缓存
		 * 
		 * @param {String}
		 *            key 指定key
		 * @param {String}
		 *            value JSON的值
		 */
		setStorageJSON: function(key,value){
			this.setStorage(key, JSON.stringify(value));
		},
    	/**
		 * 读取字符串类型的本地缓存
		 * 
		 * @param {String}
		 *            key 指定key
		 * @param {String}
		 *            isClear 是否清除当前key的缓存
		 * @return {String} 返回字符串类型的缓存
		 */
    	getStorage :function(key,isClear) {
    		var r = '',
    			sto = window.localStorage;
    		if (sto)
    			r = sto.getItem(key);
    		if(isClear)
    			sto.removeItem(key);
    		return r;
    	},
		/**
		 * 获取缓存中的boolean值
		 * @param {Object} key
		 * @param {Object} isClear
		 */
    	getStorageBoolean: function(key,isClear) {
    		var b = this.getStorage(key,isClear);
    		return b && b=='true';
    	},
    	/**
		 * 读取SON类型的本地缓存
		 * 
		 * @param {String}
		 *            key 指定key
		 * @param {String}
		 *            isClear 是否清除当前key的缓存
		 * @return {String} 返回JSON类型的缓存
		 */
    	getStorageJSON:function(key,isClear) {
    		var r = this.getStorage(key,isClear)
    		if (!this.isEmpty(r))
    			r = JSON.parse(r);
    		return r;
    	},
    	/**
		 * 清除本地缓存，如没指定key则为清空所有缓存
		 * 
		 * @param {String}
		 *            key 指定key
		 */
    	clearStorage:function(key) { 
    		var sto = window.localStorage;
    		if (!sto) 
    			return;
			if (key)
				sto.removeItem(key);
			else
				sto.clear();
    	},
    	/**
		 * 获取缓存的数据
		 */
    	getLocalStorage:function(key,value,isJson) { 
    		var v = this.getStorage(key);
			 if (this.isEmpty(v) && this.isEmpty(value))
				 return v;
			 if (this.isEmpty(v)){
				 if(isJson)
					v =JSON.stringify(value);
				 this.setStorage(key, v);
			 }else{
		 		 if(isJson)
	    			 v = JSON.parse(v); 
			 }
    		return v
    	},
        /**
		 * 记录打开浮动窗口的对象 curid：当前打开的页面id preid：前一次的页面id
		 */
        pages: {
            curid: '',
            preid: ''
        },
		/**
		 * 根据主窗口ID 关闭 该主窗口下面的浮动窗口
		 * @param {Object} id
		 */
		clearPages:function(id){
			if(id){
				uexWindow.evaluateScript(id,0,"HT.clearPages();");
			}else{
				for(var i in this.pages){
					if(i!="curid"&&i!="preid"){
						uexWindow.closePopover(i);
					}
				}
				HT.pages={
			            curid: '',
			            preid: ''
			        };
			}
		},
        /**
		 * 判断是否只有时间
		 * 
		 * @param {Object}
		 *            time
		 */
        isOnlyTime: function (time) {
        	if(this.isEmpty(time))
        		return false;
        	return !this.isDate(time) && !this.isObject(time) && time.indexOf(" ") == -1 && time.length == 8 && time.split(":").length == 3;
        },
        /**
		 * 判断是否是日期
		 * 
		 * @param {Object}
		 *            value
		 */
        isDate: function (value) {
			return toString.call(value) === '[object Date]';
		},
        /**
		 * 判断是否是字符串
		 * 
		 * @param {Object}
		 *            value
		 */
        isStirng: function (value) {
			return typeof value === 'string';
		},
		 /**
		  * 通过属性获取DOM对象，最好用document.querySelectorAll("[attr]")代替
		  * @param {Object} attr
		  */
		getDomsByAttrs:function(attr){
			var allDoms=this.$(document).find("*");
			var doms=[];
			for(var i=allDoms.length;i--;){
				var atts=allDoms[i].attributes;
				if(allDoms[i].hasAttributes(attr)&&atts&&this.isAttrInNamedNodeMap(atts,attr)&&this.$(allDoms[i]).attr(attr)!=undefined){
					doms.push(allDoms[i]);
				};
			}
			return doms;
		},
		 /**
		  * 判断一个attr是否在该对象的NodeMap集合中
		  * @param {Object} arr
		  * @param {Object} obj
		  */
		isAttrInNamedNodeMap:function(arr,obj){
			for(var i=arr.length;i--;){
				if(arr[i].name==obj)
					return true;
			}
			return false;
		},
		/**
		 * 通过dom获取当前的angular
		 */
		$:function(dom){
			return angular.element(dom);
		},
		 /**
		  * 通过ID获取ＤＯＭ对象
		  * @param {Object} id
		  */
		$$:function(id){
			return document.getElementById(id);
		},
		/**
		 * 通过id获取当前的angular的element
		 */
		getElement :function(id){
			return angular.element(this.$$(id));
		},
		/**
		 * 通过id获取当前的Scope
		 */
		getScope : function (id){
			return this.getElement(id).scope();
		},
 		/**
 		 * 当填写完子表数据时，点击确定按钮的操作。（将该数据插入对应的ｓｃｏｐｅ中的子表中）
 		 * @param {Object} e
 		 */
        addSubTableCol: function (e) {
			//通过点击确定按钮的事件e获取 ，子表的表名ID。obj
			var subTableId=this.$(e.currentTarget).parent().parent().attr("id");
			var subTabDIV = this.getElement(subTableId);
			var scope=subTabDIV.scope();
			//校验子表数据是否填写正确，传入subTabDIV[0] 表示只校验该DIV下面的需要validate的对象
			if(!scope.valid({dom:subTabDIV[0]})){
				scope.$parent.showWrongMsg("子表表单数据不正确");
				return false;
			}
			//判断当前子表数据是 修改状态还是添加状态
			if(scope.item.___index>=0){
				 scope.$parent.sub[subTableId.toLowerCase()][scope.item.___index]=scope.item;
			}else{
				 scope.$parent.sub=scope.$parent.sub||[];
				 scope.$parent.sub[subTableId.toLowerCase()]=scope.$parent.sub[subTableId.toLowerCase()]||[];
				 scope.$parent.sub[subTableId.toLowerCase()].push(scope.item);
			}
			//清除 子作用于下面 的数据   
			this.clearChildScopeVals(scope);
            scope.$parent.$digest();
			scope.item={};
			//关闭添加子表页面的窗口
            this.openSubTableIframe(e, true);
			//打开form主页面的窗口
			this.openSubTableIframe("mainWindow", false);
			//将form主窗口的页面从全屏改成原来的状态
			uexWindow.evaluateScript("taskInfo",'0',"HT.togglePoperFullWindow('form',false)");
			uexWindow.evaluateScript("newFlow_add",'0',"HT.togglePoperFullWindow('form',false)");
			return true;
        },
        openSubPopver: function (tagetId) {
//            var tagetId = angular.element(e.currentTarget).parent().attr("tablename");
            var tagetObj = angular.element(document.getElementById(tagetId.toLowerCase()));
			//打开添加子表页面的窗口
            this.openSubTableIframe(tagetId.toLowerCase(), false);
			//关闭form主页面的窗口
			this.openSubTableIframe("mainWindow", true);
			//将form主窗口的页面变成全屏状态
			uexWindow.evaluateScript("taskInfo",'0',"HT.togglePoperFullWindow('form',true)");
			uexWindow.evaluateScript("newFlow_add",'0',"HT.togglePoperFullWindow('form',true)");
			window.scrollTo(0,0);
        },
		getSelectedOption:function(elm){
			var ops=elm[0].options;
			for(var i=ops.length;i--;){
				if(elm.val()==ops[i].value){
					return ops[i].text;
				}
			}
		},
        /**
		 * 实现上下滑动效果
		 */
        scrollH: function (jqId, offsetHeight) {
			var wrapper = typeof jqId == 'object' ? jqId : this.$$(jqId);
			if(!wrapper) return;
			HT.$(document.body).css("overflow","hidden");
            return new iScroll(jqId, {
                mouseWheel: true,
                hScrollbar: false,
                vScrollbar: false,
				bottom:0
            });
        },
		/**
		 * 获取ＤＯＭ对象
		 * @param {Object} obj
		 * @param {Object} type
		 */
		getDoms:function(obj,type){
			switch (type) {
                case "id":
                    return document.getElementById(obj);
                    break;
                case "class":
                    return document.getElementsByClassName(obj);
                    break;
				case "target":
                    return document.getElementsByTagName(obj);
                    break;
            }
		},
        /**
		 * 实现左右滑动效果
		 */
        scrollW: function (obj, type) {
            var objs =  this.getDoms(obj,type);
			var scxs=[];
            for (var o = 0; o < objs.length; o++) {
                scxs.push(new iScroll(objs[o], {
                    scrollX: true,
                    scrollY: false,
                    mouseWheel: false,
                    hScrollbar: false,
                    vScrollbar: false
                }));
            }
			return scxs;
        },
        /**
		 * 刷新当前页面，可以在任何一个页面调用HT.reload()来刷新当前页面。
		 */
        reload: function () {
//            window.location.href = window.location.href;
			  window.location.reload(true);
//			  document.execCommand("Refresh");
//			  document.URL=window.location.href;
//			  window.navigate(window.location.href);
			  
        },
		reloadByScope:function(id){
			HT.getScope(id).reload();
		},
		/**
		 * 清除input框
		 * @param {Object} e
		 * @param {Object} ngModel
		 */
		clearInput:function(e,ngModel){
			var scope=angular.element(e.currentTarget).scope();
			var evalstr="scope."+ngModel+"='';";
			eval(evalstr);
			scope.$digest();
		},
		/**
		 * trigger 对象的click事件
		 * @param {Object} e
		 */
        trigger: function (e) {
            angular.element(e.currentTarget).next().triggerHandler("click");
        },
		/**
		 * 保存子表数据，该方法是在后台表单模板中调用的
		 * @param {Object} e
		 */
		saveSubtableDate:function(e){
			if(this.addSubTableCol(e)){
				this.hideSubTableIframe(e);
			}
		},
		/**
		 * 清楚子作用域的数据
		 * @param {Object} scope
		 */
		clearChildScopeVals:function(scope){
			if(scope.$$childTail){
				if(scope.$$childTail.$$prevSibling){
					this.setValueNull(scope.$$childTail.$$prevSibling);
				}
				if(scope.$$childTail.$$nextSibling){
					this.setValueNull(scope.$$childTail.$$nextSibling);
				}
				if(scope.$$childTail.$$childHead){
					this.setValueNull(scope.$$childTail.$$childHead);
				}
				this.setValueNull(scope.$$childTail);
			}
		},
		/**
		 * 清除数据
		 * @param {Object} s
		 */
		setValueNull:function(s){
				if(s.users||s.files){
					if(s.users instanceof Array){
						s.users=[];
					}
					if(s.files instanceof Array){
						s.files=[];
					}
				}
		},
		/**
		 * 隐藏子表页面
		 * @param {Object} e
		 * @param {Object} type
		 */
        hideSubTableIframe : function(e,type){
			//type==0 表示从添加子表的页面点击了取消按钮，从而隐藏该页面
			if(type=="0") HT.openSubTableIframe(e,true);
			var subTableId=HT.$(e.currentTarget).parent().parent().attr("id");
			var subTabDIV=this.getElement(subTableId);
			var scope=subTabDIV.scope();
			HT.clearChildScopeVals(scope);
			if (scope.item.___index >= 0) {
				scope.item = {};
				scope.$digest();
			}
			HT.openSubTableIframe('mainWindow', false);
			uexWindow.evaluateScript("taskInfo",'0',"HT.togglePoperFullWindow('form',false)");
			uexWindow.evaluateScript("newFlow_add",'0',"HT.togglePoperFullWindow('form',false)");
			window.scrollTo(0,document.body.scrollHeight);
			document.activeElement.blur();
			//当点击按钮的时候 添加一个透明的遮罩层，避免出现点一次触发两次的情况
			HT.addModal(document.body.scrollHeight+"px");
			 //1m之后删除遮罩层
			window.setTimeout(function(){HT.hideModal();},1000)
		},
		/**
		 * 通过传入的ID，和 状态 来判断是否是显示当前DOM 还是隐藏当前DOM 
		 * @param {Object} id
		 * @param {Object} s
		 */
        openSubTableIframe: function (id, s) {
            if (id.currentTarget) id = angular.element(id.currentTarget).parent().parent().attr("id");
            this.getElement(id).toggleClass("uhide", s).toggleClass("z-index9999", s);
        },
		triggerSelect:function(){
//			document.querySelectorAll("select")[0].onclick();
		},
        request: {
            getParameter: function (val) {
                var uri = window.location.search;
                var re = new RegExp("" + val + "=([^&>]*)", "ig");
                return ((uri.match(re)) ? (uri.match(re)[0].substr(val.length + 1)) : null);
            }
        },
        /**
		 * 获取随机数，范围是min和max之间
		 */
        getRandomNum: function (Min, Max) {
            var Range = Max - Min - 1;
            var Rand = Math.random(Math.round(Math.random(Range * Range)));
            return (Min + Math.round(Rand * Range));
        },
        /**
		 * 随机获取某个数组中的一个元素
		 */
        getByRan: function (arr) {
            if (!arr || !arr.length) return;
            return arr[this.getRandomNum(0, arr.length)];
        },
        /**
		 * 当打开一个浮动窗口时，记录当前浮动窗口的page对象信息。
		 */
        main_resize: function (id) {
            var s = window.getComputedStyle(this.$$('content'), null);
            this.pages['' + id + ''] = {
                'y': this.$$('header').offsetHeight,
                    'w': this.parseInt(s.width),
                    'h': this.parseInt(s.height),
                    's': this.parseInt(s.fontSize)
            };
        },
        /**
		 * 打开浮动窗口，如果ID存在，则直接调用;
		 */
        openPopFrame: function (id,isBackOpen,param) {
			var param = (typeof param!="undefined")?param:"";
            var url = id;
            this.pages.preid = this.pages.curid; // 赋值上次打开的页面id
            this.pages.curid = id; // 记录本次打开的
            if (this.pages.preid != '') {
                uexWindow.setPopoverFrame(this.pages.preid, 0, 0, 0, 0);
            }
            if (!this.pages['' + id + '']) {
                this.main_resize(id);
				var p = this.pages['' + id + ''];
				setTimeout(function(){
					if(isBackOpen){
						uexWindow.openPopover(id, '0', '' + url + '.html'+param, '', 0, 0, 0, 0, p.s, '0');
					}else{
						uexWindow.openPopover(id, '0', '' + url + '.html'+param, '', 0, p.y, p.w, p.h, p.s, '0');
					}
				},200);
            } else {
				if(id=="myUndertake"&&!HT.getStorage("__firstToMyUndertake")&&!HT.getStorage("__myUndertakeSearched")){
					uexWindow.evaluatePopoverScript("","myUndertake","HT.reloadByScope('myUndertake')");
					HT.setStorage("__myUndertakeSearched","true");
				}else if(id=="myLaunch"&&!HT.getStorage("__firstToMyLaunch")&&!HT.getStorage("__myLaunchSearched")){
					uexWindow.evaluatePopoverScript("","myLaunch","HT.reloadByScope('myLaunch')");
					HT.setStorage("__myLaunchSearched","true");
				}
                uexWindow.setPopoverFrame(id, 0, this.pages['' + id + ''].y, this.pages['' + id + ''].w, this.pages['' + id + ''].h);
            }
			HT.checkLangChange(id,true);
        },
		/**
		 * 改变浮动窗口的高度，在表单初始化的时候，因为主页面的footer是之后加载出来的，所以浮动窗口需要减去该footer的高
		 * @param {Object} id
		 * @param {Object} height
		 */
		changePopHeight: function(id,height){
			this.pages['' + id + ''].h=this.pages['' + id + ''].h+height;
			uexWindow.setPopoverFrame(id, 0, this.pages['' + id + ''].y, this.pages['' + id + ''].w, this.pages['' + id + ''].h);
		},
		/**
		 * 通过ID和状态 判断是否让浮动窗口全屏显示
		 * @param {Object} id
		 * @param {Object} flag
		 */
		togglePoperFullWindow:function(id,flag){
			if(flag){
				if (id.currentTarget) id = angular.element(id.currentTarget).parent().parent().attr("id");
				uexWindow.setPopoverFrame(id, 0, 0, document.width, document.height);
			}else{
				uexWindow.setPopoverFrame(id, 0, this.pages['' + id + ''].y, this.pages['' + id + ''].w, this.pages['' + id + ''].h);
			}
		},
        /**
		 * url：如果是数字，则返回上一个页面，数字表示页面切换的动画类型； 如果是string值 ，则直接打开string.html页面
		 */
        goback: function (url) {
            if (!/[^\d]/.test(Math.abs(url))) {
                uexWindow.close(url);
            } else if (url) {
                this.goToHtml(url);
            };
        },
		/**
		 * 一般的toast窗口
		 * @param {Object} msg
		 */
		toast:function(msg){
			uexWindow.toast("0","5",msg,"2000");
		},
		/**
		 * 在其他页面调用某页面的toast窗口
		 * @param {Object} id
		 * @param {Object} popwindowId
		 * @param {Object} waitmsg
		 * @param {Object} time
		 * @param {Object} type
		 */
		toastInWindow:function(id,popwindowId,waitmsg,time,type){
				var msg=waitmsg ? waitmsg : $i18n$.COMMON.WATTING_MSG;
				time=time?time:0;
				type=type==0?0:1;
                try {
                    if (id) {
                        if (popwindowId) {
                            uexWindow.evaluatePopoverScript(id, popwindowId, 'uexWindow.toast(' + type + ', 5,"' + msg + '", ' + time + ');');
                        }
                        else {
                            uexWindow.evaluateScript(id, 0, 'uexWindow.toast(' + type + ', 5,"' + msg + '", ' + time + ');');
                        }
                    }
                    else {
                        uexWindow.toast(type, 5, msg, time);
                    }
                } 
                catch (e) {
                    console.info(e);
                }
			
		},
		/**
		 * 关闭toast
		 * @param {Object} id
		 * @param {Object} popwindowId
		 */
		closeToastInWindow:function(id,popwindowId){
			try {
				if(id){
					if(popwindowId){
						uexWindow.evaluatePopoverScript(id,popwindowId,'uexWindow.closeToast();');
					}else{
						uexWindow.evaluateScript(id,0,'uexWindow.closeToast();');
					}
				}else{
					uexWindow.closeToast();
				}
			} 
			catch (e) {
				console.info(e);
			}
		},
		/**
		 * 判断对象是否在数组中
		 * @param {Object} arr
		 * @param {Object} obj
		 */
        checkInArr: function (arr, obj) {
            for (var i in arr) {
                if (obj == arr[i]) {
                    return true;
                }
            }
            return false;
        },
		checkLangChange:function(urlId,isPop){
			if(!urlId)return;
			var lang =  localStorage.getItem("__lang"),
				pageLang = HT.getStorageJSON("__pageLang");
			if(!pageLang)return;
			var curPageLang = pageLang[urlId];
			if(!curPageLang)return;
			if(curPageLang==lang)return;
			if(isPop){
				uexWindow.evaluatePopoverScript("",urlId,"HT.reload()");
			}
			else{
				uexWindow.evaluateScript(urlId,0,"HT.reload()");
			}
			HT.updatePageLang(urlId);
		},
		updatePageLang : function(urlId){
			var pageLang = HT.getStorageJSON("__pageLang");
			pageLang[urlId] = __lang;
			HT.setStorageJSON("__pageLang",pageLang);
		},
        /**
		 * 打开对应的页面； urlId：页面的地址； type：打开的类型，默认为0；
		 */
        goToHtml: function (urlId, type) {
            uexWindow.open(urlId, '0', urlId + ".html", '0', '0', '0', type | '0');
			HT.checkLangChange(urlId);
        },
        /**
		 * 获取只有header的页面中content的尺寸
		 */
        getContentSizeOnlyHeader: function () {
            var screenHeight = window.innerHeight,
                header = this.$$('header'),
                headerWidth = header.offsetWidth,
                headerHeight = header.offsetHeight,
                json = {};
            json.width = headerWidth;
            json.height = screenHeight - headerHeight;
            return json;
        },
        /**
		 * 为时间类型添加0的前缀
		 */
        addZero: function (n) {
            n = parseInt(n);
            if (n < 10) {
                return '0' + n;
            }
            return '' + n;
        },
        /**
		 * 获取伪类的class的属性值
		 */
        getFualClass: function (dom, place, key) {
            var obj = window.getComputedStyle(dom, ":" + place);
            if (obj) return obj.getPropertyValue(key);
        },
		/**
		 * 创建一个INPUT输入框，暂时没用到，也不起作用
		 */
		createInput:function(){
			window.setTimeout(function(){
				uexInput.cbCreate=function(opid,dataType,data){
					alert(opid,dataType,data);
				};
				uexInput.onButtonClick=function(btnName,TextContent){
					alert(btnName,TextContent);
				}
				uexInput.create('{"background": "login.jpg", "inputType": "0", "items": [{"type": "0", "name": "itemFace", "emotion": {"normal": "login.jpg", "pressed": "login.jpg", "default": "true"}, "keyboard": {"normal": "login.jpg", "pressed": "login.jpg"} }, {"type": "1", "name": "itemInput", "defText": "默认文字", "defHint": "提示语", "bg": {"normal": "login.jpg", "pressed": "login.jpg"}, "autofill": "true"}, {"type": "3", "name": "itemBtn", "label": "发送", "bg": {"normal": "login.jpg", "pressed": "login.jpg"}, }, {"type": "2", "name": "itemBtn", "label": "更多", "bg": {"normal": "login.jpg", "pressed": "login.jpg"}, } ] }')
			},10)
		},
		/**
		 * 获取某个元素的绝对位置
		 * @param {Object} element
		 */
		getAbsoluteLocation:function (element){
		      if ( arguments.length != 1 || element == null ){
		         return null;
		      }
		      var offsetTop = element.offsetTop;
		      var offsetLeft = element.offsetLeft;
		      var offsetWidth = element.offsetWidth;
		      var offsetHeight = element.offsetHeight;
		      while( element = element.offsetParent ){
		         offsetTop += element.offsetTop;
		         offsetLeft += element.offsetLeft;
		      }
		      return { absoluteTop: offsetTop, absoluteLeft: offsetLeft,
		         offsetWidth: offsetWidth, offsetHeight: offsetHeight 
		      };
		   },
		 /**
		  * 通过DOM集合main 减去 DOM集合sub  返回差集
		  * @param {Object} main
		  * @param {Object} sub
		  */
		getDomWithSubDom:function(main,sub){
			var alldom=[];
			var isIn=false;
			for(var i=0; i<main.length;i++){
				var temp=main[i];
				for(var j=0;j<sub.length;j++){
					if(temp==sub[j]){
						isIn=true;
						break;
					}
				}
				if(!isIn){
					alldom.push(temp);
					isIn=false;
				}
			}
			return alldom;
		},
        /**
		 * 设置伪类的class的属性值
		 */
        setFualClass: function (dom, place, value) {
            var obj = window.getComputedStyle(dom, ":" + place);
            if (obj) return obj.getPropertyValue(key);
        },
		/**
		 * 文件上传
		 * @param {Object} file
		 */
		httpReqPost:function (file){
		   var httpId = new Date().getTime().toString();
		   httpId=httpId.substring(httpId.length-3,httpId.length);
		   var url=__ctx+'/mobile/system/file/saveFile.ht';
		   //开始一个跨域异步请求
		   uexXmlHttpMgr.open(httpId,'post',url,30000);
		   if(file){
		     uexXmlHttpMgr.setPostData(httpId,'1','file',file);
		   }
		   uexXmlHttpMgr.send(httpId);
		       uexXmlHttpMgr.onData = function(inOpCode,inStatus,inResult){
			     if(inStatus == 1){
				 	var result=eval(inResult);
					if(result.success){
						HT.closeToastInWindow();
						var tempFile={id:result.file.fileId,name:result.file.fileName};
						var files=[];
						var currentFile=eval("HT.fileTarget.scope."+HT.fileTarget.ngModel);
                        if (currentFile) files = eval(currentFile.replaceAll("￥@@￥", "\""));
						files.push(tempFile);
						var evalstr='HT.fileTarget.scope.'+HT.fileTarget.ngModel+'=JSON.stringify(files).replaceAll("\\"", "￥@@￥")';
						eval(evalstr);
						HT.fileTarget.scope.$digest();
						HT.toastInWindow("","","文件上传成功",2000,0);
					}else{ 
						HT.closeToastInWindow(); 
						HT.setStorage("wrongmsg",result.msg);
						uexWindow.evaluatePopoverScript("","form","HT.showmsg(null,null,'formDiv')");
					}
			       uexXmlHttpMgr.close(httpId);
			     }
			   }
		 },
        /**
		 * 调用打开文件的方法（单个文件）
		 */
        explorerFile: function () {
            try {
                uexFileMgr.cbExplorer = function(opId, dataType, data){
                    HT.httpReqPost(data);
                }
                var path = '/sdcard';//android的用法
                uexFileMgr.explorer(path);
            } 
            catch (e) {
                alert(e)
            }
        },
        /**
		 * 调用打开文件的方法（多个文件）
		 */
        multiExplorerFile: function () {
				this.explorerFile();
//				return ;
//				uexFileMgr.cbMultiExplorer = function (opId, dataType, data) {
//	                if (dataType == 1) {
//					   HT.toastInWindow("","","文件上传中",0);
//					   var json=JSON.parse(data);
//	                   for(var i in json){
//						 	HT.httpReqPost(json[i]);
//					   }
//	                }
//	            }
//	            uexFileMgr.multiExplorer('/sdcard');
        },
		//打开图片浏览器
		openBrowser :function (){
		    uexImageBrowser.cbPick=function (opCode,dataType,data){
		       HT.httpReqPost(data);
		    }
		    uexImageBrowser.pick();
		},
        /**
		 * 如果传入的值是null、undefined或空字符串，则返回true。（可选的）
		 * 
		 * @param {Mixed}
		 *            value 要验证的值。
		 * @param {Boolean}
		 *            allowBlank （可选的） 如果该值为true，则空字符串不会当作空而返回true。
		 * @return {Boolean}
		 */
        isEmpty: function (v, allowBlank) {
            return v === null || v === undefined || (!allowBlank ? v === '' : false);
        },
        /**
		 * 获取日期选择的对象
		 */
        getDate: function (me) {
            var dateObj = {};
            var mydate = new Date();
            var ty = mydate.getFullYear();
            var tm = mydate.getMonth() + 1;
            var td = mydate.getDate();
            var dateInput = angular.element(me.currentTarget ? me.currentTarget : me).find("input");
            var val = dateInput.val();
            var dateFmt = dateInput.attr("dateFmt");
            if (val) {
                var date = new Date(val);
                dateObj.ty = date.getFullYear();
                dateObj.tm = date.getMonth() + 1;
                dateObj.td = date.getDate();
            } else {
                dateObj.ty = ty;
                dateObj.tm = tm;
                dateObj.td = td;
            }
            uexControl.cbOpenDatePicker = function (opCode, dataType, data) {
                var obj = eval('(' + data + ')');
                var y = obj.year;
                var mh = parseInt(obj.month);
                var d = parseInt(obj.day);
                var str = y + '-' + HT.addZero(mh) + '-' + HT.addZero(d);
                var time = dateInput.parent().next().find("input").val();
                if (time) str = str + " " + time;
                var scope = dateInput.scope();
//				var ngModel=dateInput.attr("ng-model").split(".");
				eval("scope."+dateInput.attr("ng-model")+"=new Date(str)");
//                scope[dateInput.attr("ng-model")] = new Date(str);
                scope.$digest();
                dateInput[0].blur();
                dateObj.ty = y;
                dateObj.tm = mh;
                dateObj.td = d;
            }
            uexControl.openDatePicker(dateObj.ty, dateObj.tm, dateObj.td);
        },
        /**
		 * 获取时间选择的对象
		 */
        getTime: function (me) {
            var dateObj = {},
            	mydate = new Date(),
            	ty = mydate.getHours(),
            	tm = mydate.getMinutes(),
            	td = mydate.getSeconds(),
            	dateInput = angular.element(me.currentTarget ? me.currentTarget : me).find("input"),
            	val = dateInput.val(),
            	dateFmt = dateInput.attr("dateFmt"),
              	scope = dateInput.scope(),
              	ngModel = dateInput.attr("ng-model"),
              	oldVal = eval("scope."+ngModel);
            if (val) {
                var date = val.split(":");
                dateObj.ty = parseInt(date[0]);
                dateObj.tm = parseInt(date[1]);
            } else {
                dateObj.ty = ty;
                dateObj.tm = tm;
                dateObj.td = td;
            }
            if (oldVal instanceof Date) oldVal = oldVal.Format("yyyy-MM-dd hh:mm:ss");
            uexControl.cbOpenTimePicker = function (opCode, dataType, data) {
                var obj = eval('(' + data + ')'),
                	y = obj.hour,
                	m = obj.minute,
                	str = HT.addZero(y) + ':' + HT.addZero(m);
              
                if (!HT.isOnlyTime(oldVal) && dateInput.attr("datefmt") != "HH:mm:ss" && dateInput.attr("datefmt") != "HH:mm:00") {
                    if (oldVal) {
                        str = oldVal.substring(0, oldVal.indexOf(" ")) + " " + str;
                    } else {
                    	var nd = mydate.Format('yyyy-MM-dd');
                        str = nd+" " + str;
                    }
					eval("scope."+ngModel+"=new Date(str)");
                } else {
                    oldVal = oldVal ? oldVal : "00:00:00";
//                    scope[dateInput.attr("ng-model")] = str + ":" + oldVal.split(":")[2];
					eval('scope.'+dateInput.attr('ng-model')+'=str + ":" + oldVal.split(":")[2]');
                }
				dateInput[0].blur();
                scope.$digest();
                dateObj.ty = y;
                dateObj.tm = m;
            }
            uexControl.openTimePicker(dateObj.ty, dateObj.tm);
        },
        /**
		 * 是否是对象
		 * 
		 */
    	isObject:function(value){
			return value instanceof Object && value.constructor == Object;
		},
		/**
		 * Parse a value into a formatted date using the specified format
		 * pattern.
		 * 
		 * @param {String/Date}
		 *            value The value to format. Strings must conform to the
		 *            format expected by the JavaScript Date object's [parse()
		 *            method](http://developer.mozilla.org/en-US/docs/JavaScript/Reference/Global_Objects/Date/parse).
		 * @param {String}
		 *            [format='m/d/Y'] (optional) Any valid date format string.
		 * @return {String} The formatted date string.
		 */
	    date: function(value, format) {
	        var date = value;
	        if (!value) {
	            return "";
	        }
	        if (!this.isDate(value)) {
        	   if(this.isObject(value)){
        		   value = new Date(value['time']);
	            }else{
        	
		            date = new Date(Date.parse(value));
		         
		             if (isNaN(date)) {
		                // Dates with ISO 8601 format are not well supported by
						// mobile devices, this can work around the issue.
		                if (this.iso8601TestRe.test(value)) {
		                    date = value.split(this.iso8601SplitRe);
		                    date = new Date(date[0], date[1]-1, date[2], date[3], date[4], date[5]);
		                }
		                if (isNaN(date)) {
		                    // Dates with the format "2012-01-20" fail, but
							// "2012/01/20" work in some browsers. We'll try and
		                    // get around that.
		                    date = new Date(Date.parse(value.replace(this.dashesRe, "/")));
		                    // <debug>
		                    if (isNaN(date)) {
		                       // "Cannot parse the passed value " + value + "
								// "
		                    }
		                    // </debug>
		                }
		            }
		            value = date;
	            }
	        }
	        return value.Format(format?format:this.defaultDateFormate);
    },
	/**
	 * 回调主页时触发 onStateChange
	 */
	winCallback : function(urlId){
		HT.checkLangChange(urlId);
		var win = getstorage(HT_LS.activeWin);
		switch(win){
			case "contactCommon":
				uexWindow.evaluatePopoverScript("","contactCommon","addContactCommon()");
				break;
			default:
				break;
		}
		HT.clearStorage(HT_LS.activeWin);
	},
	mergeContacts:function (a,b){
		for(var i=0;i<a.length;i++){
			for(var j=0;j<b.length;j++){
				if(a[i].id==b[j].id){
					b.splice(j,1);
				}
			}
		}
		for(var i=0;i<b.length;i++){
			a.push(b[i]);
		}
		return a;
	},
	addContact:function(cbfun){
		var newus = this.getStorageJSON(HT_LS.selectorSelected,true);
		if(!newus||newus.length==0)return;
		uexWindow.toast(1, 5, $i18n$.COMMON.WATTING_MSG, 5000);
		var ds = [];
		var idx=0;
		var sqls = [];
		var makeSql = function(us, cb){
			ds.push(us)
			uexDataBaseMgr.cbSelectSql = function(opId, type, data){
				if (type == 1) {
					var sql = "";
					var r = angular.fromJson(data);
					if(!r||r.length==0){
						var u = ds[idx];
						sql = "INSERT INTO frequent_contacts (id,account,name,position,refid) VALUES ('"+u.id+"','"+u.account+"','"+u.name+"','"+u.position+"','"+__curUserId+"')";
					}else{
						var o = r[0];
						sql = "UPDATE frequent_contacts SET name='"+o.name+"',account='"+o.account+"',position='"+o.position+"',refid='"+__curUserId+"' WHERE _id ='"+o._id+"'";
					}
					sqls.push(sql);
				}
				idx++;
			}
			uexDataBaseMgr.selectSql(HT.SQLiteDBName, HT.getOperationNum(), "select * from frequent_contacts where id='"+us.id+"' and refid='"+__curUserId+"'");
		}
		
		for (var i = 0; i < newus.length; i++) {
			makeSql(newus[i]);
		}
		
		setTimeout(function(){
			if (sqls.length > 0 && sqls.length == newus.length) {
				var rnd = HT.getOperationNum();
		        uexDataBaseMgr.cbTransaction=function(opId,dataType,data){
		            if(dataType==2){
		                if (data == 0) {
							if(!cbfun)
								HT.goback('-1');
							else{
								uexWindow.closeToast();
								cbfun();
							}
						}
						else if (data == 1) {
							//console.log('事务执行失败');
						}
		            }
		        }
		        uexDataBaseMgr.transaction(HT.SQLiteDBName,rnd,function(){
		            uexDataBaseMgr.cbExecuteSql = null;
					for(var i=0;i<sqls.length;i++){
						uexDataBaseMgr.executeSql(HT.SQLiteDBName,rnd,sqls[i]);
					}
		        });
			}
		}, 1000);
	},
		/**
		 * 返回选择用户
		 */
		selectedUsers:function(){
			var flag = this.getStorage(HT_LS.formToSeletor,true),
				u = this.getStorageJSON(HT_LS.selectorSelected,true),
				sel = this.getElement(flag),
				scope = sel.scope(),
				ngModel = sel.attr("ng-model"),
				refid = sel.attr("refid"),
				nameAry = [],
				idAry = [];

			angular.forEach(u,function(item){
				nameAry.push(item.name);
				idAry.push(item.id);
			});

			var names = nameAry.join(","),
				ids = idAry.join(","),
				un = 'scope.'+ngModel+' = "'+ names+'"',
			    id = 'scope.'+refid+' = "'+ ids+'"' ;

			eval('('+un+')');
			eval('('+id+')');
			scope.$digest();
	    },
		/**
		 * 通过flag 来寻找scope对象
		 * @param {Object} scope
		 * @param {Object} flag
		 */
		getScopeByNextSiblings:function(scope,flag){
			var f = scope.flag;
			while(!f||f.toLowerCase()!=flag){
				scope = scope.$$nextSibling;
				return HT.getScopeByNextSiblings(scope,flag);
			}
			return scope;
		},
		/**
		 * Get the index of the provided `item` in the given `array`, a supplement for the
		 * missing arrayPrototype.indexOf in Internet Explorer.
		 *
		 * @param {Array} array The array to check.
		 * @param {Object} item The item to look for.
		 * @param {Number} from (Optional) The index at which to begin the search.
		 * @return {Number} The index of item in the array (or -1 if it is not found).
		 */
		indexOf : function(array, item, from) {
		    var i, length = array.length;

		    for (i = (from < 0) ? Math.max(0, length + from) : from || 0; i < length; i++) {
		        if (array[i] === item) {
		            return i;
		        }
		    }

		    return -1;
		},

		/**
		 * Returns a new array with unique items.
		 *
		 * @param {Array} array
		 * @return {Array} results
		 */
		unique : function(array) {
		    var clone = [],
		        i = 0,
		        ln = array.length,
		        item;

		    for (; i < ln; i++) {
		        item = array[i];

		        if (this.indexOf(clone,item) === -1) {
		            clone.push(item);
		        }
		    }

		    return clone;
		},

		/**
		 * 合并
		 */
		merge : function(){
		    var args = Array.prototype.slice.call(arguments),
		    	array = [],
		    	i, ln;

			for (i = 0, ln = args.length; i < ln; i++) {
			    array = array.concat(args[i]);
			}
			
			return this.unique(array);
		},
		SQLiteDBName : 'ecpMobileDB',
		SQLCallbackList : {},
		oprationNum : 1,
		getOperationNum : function(){
			return HT.oprationNum++;
		},
		initTable:{
			cache_forms:"create table cache_forms(_id integer primary key,formid text,template BLOB,guid text)",
			cache_draft:"create table cache_draft(_id integer primary key,defId text,data BLOB)",
			frequent_contacts:"create table frequent_contacts(_id integer primary key,id text,account text,name text,position text,refid text)"
		},
		/**
		 * 执行sql语句
		 * @param {String} sql 要执行的sql语句
		 * @param {function} callback 回调函数,回调参数{Boolean} 执行结果 true:成功,false:失败
		 */
		executeSql : function(sql,callback){
			var rnd = HT.getOperationNum();
			if(callback){
				HT.SQLCallbackList[rnd] = callback;
			}
			uexDataBaseMgr.cbExecuteSql = function(opId, type, data){
				var curCallback = HT.SQLCallbackList[opId];
				if (curCallback) {
					delete HT.SQLCallbackList[opId];
					if (type == 2 && data == 0) {
						curCallback(true);
					}
					else {
						curCallback(false);
					}
				}
			}
	        uexDataBaseMgr.executeSql(HT.SQLiteDBName, rnd, sql);
		},
		/**
		 * 查询sql语句
		 * @param {String} sql 查询的sql语句
		 * @param {function} callback 回调函数,回调参数{Boolean|Array} 查询失败则返回false,成功则返回[{},{},...]
		 */
		selectSql : function(sql,callback){
			var rnd = HT.getOperationNum();
			if(callback){
				HT.SQLCallbackList[rnd] = callback;
			}
			uexDataBaseMgr.cbSelectSql = function(opId, type, data){
				var curCallback = HT.SQLCallbackList[opId];
				if (curCallback) {
					delete HT.SQLCallbackList[opId];
					if (type == 1) {
							if(data.indexOf('"template":')>-1){
								data=data.replaceAll("\\\\","￥@1@￥").replaceAll("\\r","￥@2@￥").replaceAll("\\n","￥@3@￥");
							}else{
								 if(data.indexOf('"data":"')>-1&&data.indexOf('"main"')>-1&&data.indexOf('"sub"')>-1&&data.indexOf('"opinion"')>-1){
									var str1=data.substring(data.indexOf('"data":"')+'"data":"'.length,data.indexOf(']}"')+']}"'.length-1);
									var str2=data.substring(0,data.indexOf(str1));
									var str3=data.substring(data.indexOf(str1)+str1.length,data.length);
								    data=str2+str1.replaceAll("\"","\\\"")+str3;	
								 }
							}
                            var json = eval(data);
                            curCallback(json);
					}
					else {
						curCallback(false);
					}
				}
			}
	        uexDataBaseMgr.selectSql(HT.SQLiteDBName, rnd, sql);
		},
		/**
		 * 初始化数据库连接(并创建常用联系人和表单缓存表)
		 */
		openDataBase : function(){
			uexDataBaseMgr.cbOpenDataBase = function(opId, dataType, data){
	            if (dataType == 2 && data == 0) {
//					HT.initFrequentTable(function(r){
//						if(!r)
//							alert("初始化联系人表失败");
//					});
//					HT.initCacheFormTable(function(r){
//						if(!r)
//							alert("初始化表单模板表失败");
//					});
//					HT.initCacheDraft(function(r){
//						if(!r)
//							alert("初始化草稿表失败");
//					});
	            }
	            else {
	                alert('手机本地数据库连接失败！');
	            }
	        }
			uexDataBaseMgr.openDataBase(HT.SQLiteDBName, HT.getOperationNum());
		},
		/**
		 * 关闭数据库
		 */
		closeDataBase : function(){
	        uexDataBaseMgr.cbCloseDataBase = function closeDataBaseCallBack(opid, type, data){
				//数据库关闭成功
	            if (type == 2 && data == 0) {
					return;
				}
				else{
                	alert('数据库关闭失败！');
				}
	        }
	        uexDataBaseMgr.closeDataBase(HT.SQLiteDBName, HT.getOperationNum());
	    },
		/**
		 * 添加常用联系人表
		 * @param {function} callback 回调函数,回调参数 {Boolean} 执行结果 true:成功,false:失败
		 */
		initFrequentTable : function(callback){
			var result = true;
			HT.isTableExist("frequent_contacts",function(r){
				//创建常用联系人表
				if(!r){
					var sql = "create table frequent_contacts(_id integer primary key,id text,account text,name text,position text)";
					HT.executeSql(sql,function(r1){
						if(!r1){
							//创建失败
							result = false;
						}
					});
				}
			});
			if(callback)callback(result);
		},
		/**
		 * 添加表单模板缓存表
		 * @param {function} callback 回调函数,回调参数 {Boolean} 执行结果 true:成功,false:失败
		 */
		initCacheFormTable : function(callback){
			var result = true;
			HT.isTableExist("cache_forms",function(r){
				//创建表单缓存表
				if(!r){
					var sql = "create table cache_forms(_id integer primary key,formid text,template BLOB,guid text)";
					HT.executeSql(sql,function(r1){
						if(!r1){
							//创建失败
							result = false;
						}
					});
				}
			});
			if(callback)callback(result);
		},
		/**
		 * 初始化草稿表缓存表
		 * @param {function} callback 回调函数,回调参数 {Boolean} 执行结果 true:成功,false:失败
		 */
		initCacheDraft : function(callback){
			var result = true;
			HT.isTableExist("cache_draft",function(r){
				//创建表单缓存表
				if(!r){
					var sql = "create table cache_draft(_id integer primary key,defId text,data BLOB)";
					HT.executeSql(sql,function(r1){
						if(!r1){
							//创建失败
							result = false;
						}
					});
				}
			});
			if(callback)callback(result);
		},
		/**
		 * 判断表是否存在
		 * @param {Object} tablename 表名
		 * @param {function} callback 回调函数,回调参数 {Boolean} 执行结果 true:存在,false:不存在
		 */
		isTableExist : function(tablename,callback){
			var sql = "SELECT COUNT(*) FROM sqlite_master where type='table' and name='"+tablename+"'";
			HT.selectSql(sql,function(json){
				if(callback){
					if (!json) {
						callback(json);
					}
					else {
						var firstItem = json[0],
							result = false;
						if(firstItem){
							var count = firstItem["COUNT(*)"];
							if(Number(count)>0)result = true;
						}
						callback(result);
					}
				}
			});
		},
		/**
		 * 添加、更新常用联系人
		 * @param {Object} frequent 常用联系人 e.g. {"id":"10001","account":"123456","name":"张三","position":"技术部工程师"}
		 * @param {function} callback 回调函数,回调参数 {Boolean} 执行结果 true:成功,false:失败
		 */
		addUpdateFrequent : function(frequent,callback){
			if(!angular.isObject(frequent)){
				if(callback)callback(false);
				return;
			}
			HT.getFrequent(frequent.id,function(r){
//				alert("addUpdateFrequent:"+r);
//				this.openDataBase();
				if (!r || r.length == 0) {
					var sql = "INSERT INTO frequent_contacts (id,account,name,position) VALUES ('"+frequent.id+"','"+frequent.account+"','"+frequent.name+"','"+frequent.position+"')";
					HT.executeSql(sql,callback);
				}else{
					var sql = "UPDATE frequent_contacts SET name='"+frequent.name+"',account='"+frequent.account+"',position='"+position+"' WHERE _id ='"+r[0]._id+"'";
					HT.executeSql(sql,callback);
				}
			});
		},
		/**
		 * 删除常用联系人
		 * @param {String} id 常用联系人ID
		 * @param {function} callback 回调函数,回调参数 {Boolean} 执行结果 true:成功,false:失败
		 */
		deleteFrequent : function(id,callback){
			if(!id){
				if(callback)callback(false);
				return;
			}
			var sql = "delete from frequent_contacts where id = '"+id+"'";
			callback(HT.executeSql(sql));
		},
		/**
		 * 清空所有常用联系人
		 * @param {function} callback 回调函数,回调参数 {Boolean} 执行结果 true:成功,false:失败
		 */
		clearFrequent : function(callback){
			var sql = "delete from frequent_contacts where refid='"+__curUserId+"'";
			HT.executeSql(sql,callback);
		},
		/**
		 * 查询常用联系人
		 * @param {String} id 常用联系人ID
		 * @param {function} callback 回调函数,回调参数 {Object} e.g. [{"id":"10001","account":"123456","name":"张三","position":"技术部工程师"}]
		 */
		getFrequent : function(id,callback){
			if(id!=0&&!id){
				return callback(false);
			}
			var sql = "select * from frequent_contacts where id='"+id+"' and refid='"+__curUserId+"'";
			HT.selectSql(sql,callback);
		},
		/**
		 * 查询所有常用联系人
		 * @param {function} callback 回调函数,回调参数 {Array} e.g. [{"id":"10001","account":"123456","name":"张三","position":"技术部工程师"},...]
		 */
		getAllFrequent : function(callback){
			var sql = "select * from frequent_contacts where refid='"+__curUserId+"'";
			HT.selectSql(sql,callback);
		},
		/**
		 * 添加、更新表单模板
		 * @param {Object} form 表单模板 e.g. {"formid":"10002","template":"","guid":""}
		 * @param {function} callback 回调函数,回调参数 {Boolean} 执行结果 true:成功,false:失败
		 */
		addUpdateCacheTemplate : function(form,callback){
			if(!angular.isObject(form)){
				if(callback)callback(false);
				return;
			}
			var sql = "";
			HT.getCacheTemplate(form.formid,function(r){
				var temp = form.template.replaceAll("\"","￥@@￥").replaceAll("'","#@@#");
				if(!r||r.length==0){
					sql = "INSERT INTO cache_forms (formid,template,guid) VALUES ('"+form.formid+"','"+temp+"','"+form.guid+"')";					
				}
				else{
					sql = "UPDATE cache_forms SET template='"+temp+"',guid='"+form.guid+"' WHERE _id ='"+r[0]._id+"'";
				}
			});
			
			setTimeout(function(){
				HT.executeSql(sql,callback);
			},1000);
					
		},
		/**
		 * 删除表单模板
		 * @param {String} formid
		 * @param {function} callback 回调函数,回调参数{Boolean} 执行结果 true:成功,false:失败
		 */
		deleteCacheTemplate : function(formid,callback){
			if(!formid){
				if(callback)callback(false);
				return;
			}
			var sql = "delete from cache_forms where formid = '"+formid+"'";
			HT.executeSql(sql,callback);
		},
		/**
		 * 删除所有表单模板
		 * @param {function} callback 回调函数,回调参数
		 */
		clearCacheTemplate : function(callback){
			var sql = "delete from cache_forms";
			HT.executeSql(sql,callback);
		},
		/**
		 * 获取表单模板
		 * @param {String} formid 表单ID
		 * @param {function} callback 回调函数,回调参数{Object} 表单模板 e.g. [{"formid":"10002","template":"","guid":""}]
		 */
		getCacheTemplate : function(formid,callback){
			if(!formid){
				return callback(false);
			}
			var sql = "select formid,template,_id,guid from cache_forms where formid='"+formid+"'";
			return HT.selectSql(sql,callback);
		},
		/**
		 * 查询所有缓存表单
		 * @param {function} callback 回调函数,回调参数 {Array} e.g. [{"formid":"10002","template":"","guid":""},...]
		 */
		getAllCacheTemplate : function(callback){
			var sql = "select * from cache_forms";					
			HT.selectSql(sql,callback);
		},
		///////////////////////////草稿开始
		/**
		 * 添加、更新草稿
		 * @param {Object} draft 表单模板 e.g. {"defId":"10002","data":""}
		 * @param {function} callback 回调函数,回调参数 {Boolean} 执行结果 true:成功,false:失败
		 */
		addUpdateCacheDraft : function(draft,callback){
			if(!angular.isObject(draft)){
				if(callback)callback(false);
				return;
			}
			var sql = "";
			try {
				HT.getCacheDraft(draft.defId,function(r){
							var data =JSON.stringify(draft.data);
							if(!r||r.length==0){
								sql = "INSERT INTO cache_draft (defId,data) VALUES ('"+draft.defId+"','"+data+"')";
							}
							else{
								sql = "UPDATE cache_draft SET data='"+data+"' WHERE _id ='"+r[0]._id+"'";
							}
							if(HT.isIOS()){
	                            HT.executeSql(sql, callback);
							}
				});
			} catch (e) {
				alert(e)
			}
			if(HT.isAndroid()){
				window.setTimeout(function(){
					HT.executeSql(sql,callback);
				},1000);
			}
			
		},
		/**
		 * 删除草稿
		 * @param {String} defId
		 * @param {function} callback 回调函数,回调参数{Boolean} 执行结果 true:成功,false:失败
		 */
		deleteCacheDraft : function(defId,callback){
			if(!defId){
				if(callback)callback(false);
				return;
			}
			var sql = "delete from cache_draft where defId = '"+defId+"'";
			HT.executeSql(sql,callback);
		},
		/**
		 * 删除所有草稿
		 * @param {function} callback 回调函数,回调参数
		 */
		clearCacheDraft : function(callback){
			var sql = "delete from cache_draft";
			HT.executeSql(sql,callback);
		},
		/**
		 * 获取草稿
		 * @param {String} defId 流程ID
		 * @param {function} callback 回调函数,
		 * 			回调参数{Array} 草稿
		 *  		e.g. [{"defId":"10002","data":""}]
		 */
		getCacheDraft : function(defId,callback){
			if(!defId){
				return callback(false);
			}
			var sql = "select * from cache_draft where defId='"+defId+"'";
			return HT.selectSql(sql,callback);
		},
		/**
		 * 查询所有缓存草稿
		 * @param {function} callback 回调函数,回调参数 {Array} e.g. [{"defId":"10002","data":""},...]
		 */
		getAllCacheDraft : function(callback){
			var sql = "select * from cache_draft";					
			HT.selectSql(sql,callback);
		},
		///////////////////////////草稿结束
		/**
		 *是否是手机
		 */
		isMobile:function(u){
			if(!u)
				u =navigator.userAgent;
			if(u.indexOf('Win')>-1  || u.indexOf('x11')>-1 )
				return false;
			return !!u.match(/AppleWebKit.*Mobile.*/)||!!u.match(/AppleWebKit/);
		},
		/**
		 *是否是安卓手机
		 */
		isAndroid:function(){
			var u =navigator.userAgent;
			if(this.isMobile(u)){
				if (/android/i.test(u))
					return true;
			}
		},
		/**
		 *是否是IOS手机
		 */
		isIOS:function(){
			var u =navigator.userAgent;
			if(this.isMobile(u)){
				if (/iphone|ipad|ipod/i.test(u))
					return true;
			}
		},
		networkStatus:function(){
			var status = uexDevice.getInfo('13');
			if(!this.isMobile() && status){
				return true;
			}else{
				if(status && status == -1){
					this.toast('网络状态：网络不可用');
					return false;
				}else{
					return true;
				}
			}
				
		},
		/**
		 * 判断数组中是否存在此id
		 * @param {Object} users
		 * @param {Object} id
		 */
		isInUsers:function(users,id){
			var f = false;
			angular.forEach(users,function(user){
				if(user.id == id){
					f = true;
				}
			})
			return f;
		},
		/**
		 * 整理选择器选中项
		 * @param {Object} sels
		 * @param {Object} choseItem
		 * @param {Object} pageItem
		 */
		mergeUsers: function(sels, choseItem, pageItem){
			if (sels.length > 0) {
				var del_sels = [];
				for (var i = 0; i < sels.length; i++) {
					var u = sels[i];
					var inPage = this.isInUsers(pageItem, u.id);
					var inChose = this.isInUsers(choseItem, u.id);
					if (inPage && !inChose) {
						var j = 0;
						angular.forEach(pageItem, function(c){
							if (u.id == c.id) {
								del_sels.push(u);
							}
							j++;
						})
					}
					else 
						if (inPage && inChose) {
							var j = 0;
							angular.forEach(choseItem, function(c){
								if (u.id == c.id) {
									choseItem.splice(j, 1);
								}
								j++;
							})
						}
				}
				if (del_sels.length > 0) {
					angular.forEach(del_sels, function(d){
						sels.remove(d);
					})
				}
			}
			return this.mergeContacts(choseItem, sels);
		},
		/**
		 * 选中已选中的项
		 */
		checkSelected:function(){
			var userChbs = angular.element(document.getElementsByName("userChb"));
			var sels = HT.getStorageJSON(HT_LS.selectorSelected);
			for(var i=0;i<userChbs.length;i++){
				var u = angular.element(userChbs[i]);
				var isExist = this.isInUsers(sels,u.val());
				if(isExist){
					u.prop("checked",true);
				}else{
					u.prop("checked",false);
				}
			}
		},
		/**
		 * 得到页面中选中的项
		 * @param {Object} users
		 */
		getChoseItem:function(users){
			var userChbs = angular.element(document.getElementsByName("userChb"));
			var choseItem = [];
			for(var i=0;i<userChbs.length;i++){
				var u = angular.element(userChbs[i]);
				if(u.prop("checked")){
					angular.forEach(users,function(o){
						if(u.val()==o.id){
							choseItem.push(o);
						}
					})
				}
			}
			return choseItem;
		},
		/**
		 * 封装alert方法
		 */
		alert:function(msg){
			uexWindow.alert($i18n$.COMMON.WARN,msg,$i18n$.COMMON.OK);
		},
		/**
		 *	联动改变
		 * @param scope       :当前的scope
		 * @param changefield :改变的值列表
		 * @param flag       : 标记正向（true）还是反向（false）操作
		 */
		gangedSetChange :function(scope,changefield,flag){
			for(var i=0,c;c=changefield[i++];){
				var t = this.parseInt(c.type),
					changeType = t;
				if(!flag && changeType<=6){
					if((changeType%2) == 0  ){
						changeType = flag?changeType :changeType -1;
					}else{
						changeType = flag?changeType :changeType +1
					}
				}		
				
				var keyAry = c.key.split(":"),
					key = keyAry[0]+"_"+keyAry[1]+"_"+keyAry[2],
					doms = document.getElementsByName(key);
				angular.forEach(doms,function(dom){
					var el = angular.element(dom),
						type=el.attr("type"),
						nodeName,
						elm = el;
					if(el)
						nodeName = el[0].nodeName.toLowerCase();
					switch (changeType) {
						case 1://隐藏
							if(type== 'checkbox' ||type == 'radio')//单选、复选
								elm = el.parent().parent().parent().parent().parent();
							else if(nodeName &&nodeName== "select")//下拉
								elm = el.parent();
							elm.addClass("uhide");
							break;
						case 2://非隐藏
							if(type== 'checkbox' ||type == 'radio')//单选、复选
								elm = el.parent().parent().parent().parent().parent();
							else if(nodeName &&nodeName== "select")//下拉
								elm = el.parent();
							elm.removeClass("uhide");
						break;
						case 3://只读(不可以添加)
							
							break;
						case 4://非只读(可以添加)
							
						break;
						case 5://必填
							var	validate = el.attr("validate");
							if(!validate){
								el.attr("validate","{required:true}");						
							}else{
								validate = eval("("+validate+")");
								if(!validate.required){
									validate.required = true;
									el.attr("validate",JSON.stringify(validate));
								}
							}
							break;
						case 6://非必填
							var	validate = el.attr("validate");
							if(validate){
								validate = eval("("+validate+")");
								if(validate.required){
									delete validate.required;
									el.attr("validate",JSON.stringify(validate));
								}
							}
							break;
						case 7://置空
							var ngModel = el.attr("ng-model");
							var s = '(scope.' + ngModel + '="")';
							try{
								eval(s);
							}catch(e){}
							break;
						case 8://级联
							break;
						default:
							break;
					}
					//再去验证下当前属性
					scope.valid({element:el});	
				});
			
			}
		},
		/**
		 * 联动设置
		 * @param conf
		 * 		scope    :当前的scope
		 * 		gangedSet:联动设置
		 */
		gangedSet:function(conf){
			var scope = conf.scope,
				gangedSet= conf.gangedSet;
			var $changeNoChoise= [];	
			for(var i=0,c;c=gangedSet[i++];){
				var v = c.value,
					name = c.name;
				scope.$watch(c.key,function(newVal,oldVal){
					if(newVal ==oldVal )
						return;
					//找到触发当前节点的
					var doms = document.getElementsByName(name),
						dom=  doms.length >0?doms[0]:false;
					if(!dom)
						return;
					var el   = angular.element(dom),
						type = el.attr("type");
					//还原修改的状态
					HT.gangedSetChange(scope,$changeNoChoise,false);
					$changeNoChoise = [];
					if(type == "checkbox"){//checkbox是值多值的，改变选择的
						if(HT.isEmpty(newVal))
							return;
						for(var j=0,m;m=v[j++];){
							var changefield = m.changefield,
								changeVal = m.value,
								newValAry = newVal.split(",");
							if(newValAry.indexOf(m.value) > -1){//包含的
								HT.gangedSetChange(scope,changefield,true);
								//记录改变的,还原修改的
								$changeNoChoise = $changeNoChoise.concat(changefield);
							}
						}	
					}else{
						for(var j=0,m;m=v[j++];){
							var changefield = m.changefield;
							if(newVal == m.value){
								HT.gangedSetChange(scope,changefield,true);
								//记录改变的,还原修改的
								$changeNoChoise = $changeNoChoise.concat(changefield);
							}
						}	
					}
				});
				
			}
			
//			var gangedSet= conf.gangedSet;
//			var $changeNoChoise= [];	
//			for(var i=0,c;c=gangedSet[i++];){
//				var doms = document.getElementsByName(c.key);
//				angular.forEach(doms,function(dom){
//					var el = angular.element(dom);
//					el.attr("ganged-set-bind",JSON.stringify(c.value));
//
//				});
//			}
		},
		/**
		 * 显示错误提示信息
		 * @param {Object} baseService
		 * @param {Object} msg
		 */
		showmsg:function(baseService,msg,scopeId){
			if(!baseService){
				if(!msg) msg=HT.getStorage("wrongmsg",true);
				HT.getScope(scopeId).showWrongMsg(msg);
				return;
			}
			baseService.openDialog({
	                title: $i18n$.COMMON.WARN,
	                html: msg,
					digest:true,
	                buttons: [{
	                    label: $i18n$.COMMON.CANCEL,
	                    callback: function(){
	                        baseService.closeDialog();
	                    }
	                }]
	            })
		},
		/**
		 * 为父窗口添加模态层
		 */
		addModalToParent:function(){
            try {
                uexWindow.evaluateScript("", 0, "HT.addModal(null,0.5)");
            } 
            catch (e) {
            }

		},
		/**
		 * 取消父窗口的模态层
		 */
		hideModalFromParent:function(){
            try {
                uexWindow.evaluateScript("", 0, "HT.hideModal()");
            } 
            catch (e) {
            
            }


		},
		/**
		 * 添加模态层
		 * @param {Object} height
		 */
		addModal:function(height,opacity){
			if(document.querySelectorAll(".ng-modal,.__modal").length>0) return;
			height=height||"100%";
			opacity=opacity||"0";
			var div='<div class="__modal" style="background-color:#333;top: 0;left: 0;position: absolute; width: 100%; height: '+height+'; z-index: 10001;  opacity: '+opacity+'; "></div>';
			HT.$(document.body).append(div);
		},
		/**
		 * 关闭模态层
		 */
		hideModal:function(){
			HT.$(document.querySelectorAll(".__modal")).remove();
		},
		/**
		 * 滑动窗口的事件
		 */
		touchmoveHandler: function(e){
			e.preventDefault();
			//滑动过程中，失去焦点
			document.activeElement.blur();
		},
		/**
		 * 删除可滑动的事件
		 */
		delTouchmove:function(){
			document.addEventListener('touchmove',HT.touchmoveHandler, false);
		},
		/**
		 * 恢复可滑动事件
		 */
		addTouchmove:function(){
			document.removeEventListener('touchmove',HT.touchmoveHandler, false);
		},
		// 设置选择器导航宽度
		setNaviWidth:function(last,navis, scope){
			var wh = document.body.offsetWidth;
			var fz = this.parseInt(window.getComputedStyle($$("navi"),null).fontSize);
			var ls = last.name.length;
			var s = this.parseInt(wh/fz);
			var ns = 0;
			if(navis&&navis.length>0){
				angular.forEach(navis,function(n){
					ns += n.name.length;
				})
			}
			var ex = s - 3 - ls - ns;
			scope.midNavi = ex;
			angular.element($$("midNavi")).css("max-width",ex+"em");
		},
		// 设置选择器导航显示
		setSeletorNavi:function(o,isNavi,head,scope){
			if (o.id != 0 && isNavi != 1) {
				var navis = scope.navis;
				var navi = {
					id: o.id,
					name: o.name
				};
				var lastNavi = scope.lastNavi;
				if (lastNavi != null) {
					navis.push(lastNavi);
					scope.prevNavi = lastNavi;
				}
				else {
					scope.prevNavi = head;
				}
				scope.navis = navis;
				scope.lastNavi = navi;
				HT.setNaviWidth(navi, navis, scope);
			}
			else 
				if (isNavi && o.id == 0) {
					scope.navis = [];
					scope.lastNavi = null;
					scope.midNavi = -1000;
				}
				else {
					if (isNavi && isNavi == 1) {
						var lastNavi;
						var navis = scope.navis;
						var flag = true;
						var total = navis.length;
						var i = total - 1;
						for (; i >= 0; i--) {
							if (flag) {
								if (navis[i].id == o.id) {
									flag = false;
									if (i < total) {
										lastNavi = navis[i];
										navis.splice(i, 1);
										if (i == 0) {
											scope.prevNavi = head;
										}
										else {
											scope.prevNavi = navis[i - 1];
										}
									}
								}
								else {
									navis.splice(i, 1);
								}
							}
						}
						scope.navis = navis;
						scope.lastNavi = lastNavi;
						HT.setNaviWidth(lastNavi, navis, scope);
					}
				}
		},
		contactSelUser:function(){
			var multi = HT.getStorage("isMulti");
			var contactCur = HT.getStorage("contactCur");
			if(multi!="1"){
				if(contactCur=="all"){
					uexWindow.evaluatePopoverScript("contact","contactAll","selectedUsers()");	
				}else if(contactCur=="common"){
					uexWindow.evaluatePopoverScript("contact","contactCommon","selectedUsers()");
				}
			}else{
				uexWindow.evaluatePopoverScript("contact","contactCommon","selectedUsers()");
				uexWindow.evaluatePopoverScript("contact","contactAll","selectedUsers()");
			}
		},
		toTaskInfo:function(baseService,taskid){
			baseService.post(__ctx+'/mobile/bpm/bpmMobileTask/isAllowMobile.ht',
					{taskId:taskid},function(data){
						if(!data.success){
							HT.alert(data.msg);
							uexWindow.evaluatePopoverScript("index","mytask","HT.reload();");
							uexWindow.evaluateScript("index",0,"pendingCount()");
						}else{
							if(data.type == 1){
								HT.alert(data.msg);	
							}else{
								HT.setStorage("formFlowType",1);
								HT.setStorage("taskId",taskid);
								HT.goToHtml("taskInfo");
							}
						}
			});
		},
		/**
		 * 向上回溯elm
		 * @param {Object} cur 当前页面elm对象
		 * @param {Object} tar 目标elm，字符串
		 * @param {Object} type 1:id;2:class;3:name
		 * @param {Object} cb 回调方法
		 */
		findPreElm:function(cur, tar, type, cb){
			var pat = cur.parentElement;
			if(!pat)return;
			var name = "";
			switch (type) {
				case 1: // id
					name = pat.id;
					break;
				case 2: // class
					name = pat.className;
					break;
				case 3: // name
					name = pat.nodeName;
					break;
				default:
					break;
			}
			if(name.indexOf(tar)!=-1){
				cb(cur);
			}else{
				HT.findPreElm(pat,tar,type,cb);
			}
		},
		/**
		 * 第一次打开index的时候 就直接打开 我的代办、我的发起、我的承接页面，这个时候让这三个浮动窗口一开始占满屏幕（为了解决在某些手机中，在有footer的情况下 无法触发上拉刷新的事件。并且可以提高应用的效率，因为第一次打开我的发起、我的承接的时候 并没有请求数据。）
		 */
		firstOpenIndexPage:function(){
			HT.clearStorage("__firstToMyLaunch");
			HT.clearStorage("__firstToMyUndertake");
			HT.clearStorage("__myUndertakeSearched");
			HT.clearStorage("__myLaunchSearched");
			HT.setStorage("__firstToMyLaunch","true");
			HT.setStorage("__firstToMyUndertake","true");
//			HT.openPopFrame('more',true);
//			HT.openPopFrame('contactCommon',true);
			HT.openPopFrame('myUndertake');
			HT.openPopFrame('myLaunch');
			HT.openPopFrame('mytask');
		},
		/** 
		 *  在这里做了一个延迟，等浮动窗口加载完成之后再去渲染index中的footer；
		 * @param {Object} $scope
		 * @param {Object} $compile
		 */
		addIndexFooter:function($scope,$compile){
			window.setTimeout(function(){
			 	var footer=angular.element('<div id="footer" class="uf t-gra ub t-wh c-wh footer" > <div class="ub-tccc ub ub-f1" ontouchstart="zy_touch(\'\')" ng-click="showSearch(this,\'mytask\',\'TITLE.MY_TODO\');"> <input class="uhide" type="radio" name="tabSwitch" checked="true"  id="ifooter_mytask"> <div class="ub-f1 ub ub-ver tab-act mar-t043 t-gra"> <span class="badge" ng-bind="pendingCount||\'\'"></span> <span class="badgenum" ng-bind="pendingCount||\'\'"></span> <div class="tc icon-house"> </div> <div class="uinn-a13  tx-c act-col icon-check-green mar-b0375 fs0625" ng-bind="\'MENU.TODO\'|translate"> </div> </div> </div> <div class="ub-tccc ub ub-f1" ontouchstart="zy_touch(\'\')" ng-click="showSearch(this,\'myLaunch\',\'TITLE.BPM_HISTORY\');"> <input class="uhide" type="radio" name="tabSwitch" id="ifooter_myLaunch"> <div class="ub-f1 ub ub-ver tab-act mar-t043 t-gra" > <div class=" tc icon-clock"> </div> <div class="uinn-a13  tx-c act-col icon-check-green mar-b0375 fs0625" ng-bind="\'MENU.HISTORY\'|translate"> </div> </div> </div> <div class="ub-tccc ub ub-f1" ontouchstart="zy_touch(\'\')" ng-click="showSearch(this,\'contactCommon\',\'MENU.CONTACT\');"> <input class="uhide" type="radio" name="tabSwitch" id="ifooter_contactCommon"> <div class="ub-f1 ub ub-ver tab-act mar-t043 t-gra" > <div class="tc icon-user4"> </div> <div class="uinn-a13  tx-c act-col icon-check-green mar-b0375 fs0625" ng-bind="\'MENU.CONTACT\'|translate"> </div> </div> </div> <div class="ub-tccc ub ub-f1" ontouchstart="zy_touch(\'\')" ng-click="showSearch(this,\'more\',\'MENU.MORE\');"> <input class="uhide" type="radio" name="tabSwitch" id="ifooter_more"> <div class="ub-f1 ub ub-ver tab-act mar-t043 t-gra" > <div class="tc icon-ellipsis"> </div> <div class="uinn-a13  tx-c act-col icon-check-green mar-b0375 fs0625" ng-bind="\'MENU.MORE\'|translate"> </div> </div> </div> </div>');
				HT.$($$("page_0")).append(footer);
				$compile(footer)($scope);
				var footerHeight=parseInt(window.getComputedStyle(this.$$('footer'), null).height);
				HT.pages['mytask'].h-=footerHeight;
				HT.pages['myLaunch'].h-=footerHeight;
				HT.pages['myUndertake'].h-=footerHeight;
//				HT.pages['contactCommon'].h-=footerHeight;
//				HT.pages['more'].h-=footerHeight;
				uexWindow.setPopoverFrame("myUndertake", 0, 0, 0, 0);
				uexWindow.setPopoverFrame("myLaunch", 0, 0, 0, 0);
//				uexWindow.setPopoverFrame("contactCommon", 0, HT.pages['contactCommon'].y, HT.pages['contactCommon'].w, HT.pages['contactCommon'].h);
//				uexWindow.setPopoverFrame("myUndertake", 0, HT.pages['myUndertake'].y, HT.pages['myUndertake'].w, HT.pages['myUndertake'].h);
//				uexWindow.setPopoverFrame("myLaunch", 0, HT.pages['myLaunch'].y, HT.pages['myLaunch'].w, HT.pages['myLaunch'].h);
				uexWindow.setPopoverFrame("mytask", 0, HT.pages['mytask'].y, HT.pages['mytask'].w, HT.pages['mytask'].h);
					    /**
						 * 解决代办数字显示的问题
						 */
						$$("ifooter_mytask").checked=false;
						$$("ifooter_mytask").checked=true;
						var v=HT.getStorage("__voteAgree",true);
						if(v=="17"||v=="42"){
							$$("ifooter_myLaunch").checked=true;
						}
				$scope.$digest();
		 }, 1100);
		},
		userInfo:function(baseService,info,type){
			var u = {};
			if(type=="formhistory"){
				info={id:info.exeUserId}
			}
						var btnAdd = {
									label:$i18n$.CONTACT.ADD_TO_COMMON,//	"添加到常用联系人"
//									backcolor: "#51aeec",
//									forecolor: "#fff",
//									bordercolor: "#226ee5",
									callback: function(){
										var us=[];
										us.push(u);
										HT.setStorageJSON(HT_LS.selectorSelected,us);
										HT.addContact(function(){
											uexWindow.evaluatePopoverScript("index","contactCommon","HT.reload()");
											HT.toast($i18n$.COMMON.ADD_SUCCESS);//"添加成功"
											baseService.closeDialog();
										});
									}
								}
						var btnClose = {
									label: $i18n$.COMMON.CANCEL,//"取消"
									callback: function(){
										baseService.closeDialog();
									}
								}
						var showdialog = function(u,buttons,digest){
							var html = '<ul ontouchstart="zy_touch()" class="ub ubb b-ddd t-bla ub-ac lis h275"><li class="ut-s t-111 f-sz10625">工号</li><li class="ub-f1 ulev-app1 t-888 t-right">' + u.account +
							'</li></ul><ul ontouchstart="zy_touch()" class=" ub ubb b-ddd t-bla ub-ac lis h275"><li class="ut-s t-111 f-sz10625">姓名</li><li class="ub-f1 ulev-app1 t-888 t-right">' +
							u.name +
							'</li></ul>' +
							'<ul ontouchstart="zy_touch()" class=" ub  t-bla ub-ac lis h275"><li class="ut-s t-111 f-sz10625">岗位</li><li class="ub-f1 ulev-app1 t-888 mar-l0625 ">' +
							u.position +
							'</li></ul>';
							baseService.openDialog({
								title: $i18n$.CONTACT.CONTACT_INFO,//"联系人信息" 
								html: html,
								digest:digest,
								buttons: buttons
							});
						}
						HT.getFrequent(info.id,function(rs){
							var buttons = [];
							if(rs&&rs.length>0){
								u = rs[0];
								buttons.push(btnClose);
								showdialog(u,buttons,true);
							}else{
								var url = __ctx+'/mobile/system/user/get.ht';
								baseService.post(url,{userId:info.id},function(data){
									u = {
										id:data.user.userId,
										name:data.user.fullname,
										account:data.user.account,
										position:data.user.posname!=null?data.user.posname:""
									}
									buttons.push(btnAdd);
									buttons.push(btnClose);
									showdialog(u,buttons);
								});
							}
						});
		}
	}
})();


/**
 * 
 * @param {Object}
 *            formatStr 时间对象的格式化
 * 
 */
Date.prototype.Format = function (formatStr) {
    var str = formatStr;
    var Week = ['日', '一', '二', '三', '四', '五', '六'];

    str = str.replace(/yyyy|YYYY/, this.getFullYear());
    str = str.replace(/yy|YY/, (this.getYear() % 100) > 9 ? (this.getYear() % 100).toString() : '0' + (this.getYear() % 100));

    str = str.replace(/MM/, (this.getMonth() + 1) > 9 ? (this.getMonth() + 1).toString() : '0' + (this.getMonth() + 1));
    str = str.replace(/M/g, (this.getMonth() + 1));

    str = str.replace(/w|W/g, Week[this.getDay()]);

    str = str.replace(/dd|DD/, this.getDate() > 9 ? this.getDate().toString() : '0' + this.getDate());
    str = str.replace(/d|D/g, this.getDate());

    str = str.replace(/hh|HH/, this.getHours() > 9 ? this.getHours().toString() : '0' + this.getHours());
    str = str.replace(/h|H/g, this.getHours());
    str = str.replace(/mm/, this.getMinutes() > 9 ? this.getMinutes().toString() : '0' + this.getMinutes());
    str = str.replace(/m/g, this.getMinutes());

    str = str.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds().toString() : '0' + this.getSeconds());
    str = str.replace(/s|S/g, this.getSeconds());

    return str;
};

/**
 * 检查指定的对象是否存在数组中
 * 
 * @param {Object}
 *            o 检查的对象
 * @param {Number}
 *            from （可选）指定要开始的索引
 * @return {Number} 数组的索引（或-1，表示未找到）
 */
Array.prototype.indexOf = function (o, from) {
    var len = this.length;
    from = from || 0;
    from += (from < 0) ? len : 0;
    for (; from < len; ++from) {
        if (this[from] === o) {
            return from;
        }
    }
}

/**
 * 删除数组中指定的对象。如果没找到该对象没有任何反应
 * 
 * @param {Object}
 *            o 要删除的对象
 * @return {Array} 这个数组
 */
Array.prototype.remove = function (o) {
    var i = this.indexOf(o);
    if (i != -1) {　this.splice(i, 1);
    }
    return this;
    
};



/**
 * 字符串去除左右空格
 */
String.prototype.trim=function(){
	return this.replace(/(^\s*)|(\s*$)/g,"");
}
/**
 * 字符串替换
 * 
 * @param s1
 *            需要替换的字符
 * @param s2
 *            替换的字符。
 * @returns
 */
String.prototype.replaceAll = function(s1, s2) {
	return this.replace(new RegExp(s1, "gm"), s2);
};
/**
 * var str=String.format("姓名:{0},性别:{1}","ray","男");
 * alert(str);
 * @returns
 */
String.format=function(){
	var template=arguments[0];
	var args=arguments;
	var str=template.replace(/\{(\d+)\}/g,function(m,i){
		var k=parseInt(i)+1;
		return args[k];
	});
	return str;
};

